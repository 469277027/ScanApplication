package com.cly.scanapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cly.scanapplication.bean.Receive;
import com.cly.scanapplication.bean.Send;
import com.cly.scanapplication.internet.InitCountryService;
import com.cly.scanlibrary.Scan;
import com.cly.scanlibrary.business.InternetBusiness;
import com.cly.scanlibrary.entity.BillCodeDatasBean;
import com.cly.scanlibrary.entity.ScanInitDatas;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.lc.greendaolibrary.DbManager;
import com.lc.greendaolibrary.dao.ReceiveCountry;
import com.lc.greendaolibrary.dao.SenderCountry;
import com.lc.greendaolibrary.gen.DaoMaster;
import com.lc.greendaolibrary.gen.DaoSession;
import com.lc.greendaolibrary.utils.MyOpenHelper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements InternetBusiness.OnGetBillCodeListener {

    private static final String TAG = "MainActivity";
    private DaoSession daoSession;

    private long sendId, receiveId;
    private boolean isSendClear = false, isReceiveClear = false;
    private EditText billCode;
    private TextView billCodeInfo;

    private boolean isDBFinish = false;
    private boolean isBillCodeDatasFinish = false;


    private StringBuilder info = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        initLog();
        initDatabase();
        initCountryDatas();


    }

    private void initViews() {
        billCode = ((EditText) findViewById(R.id.et_transport_bill_code));
        billCodeInfo = ((TextView) findViewById(R.id.tv_info));

        View getInfo = findViewById(R.id.button);
        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDBFinish) {
                    Scan.getBillCodeDatas(billCode.getText().toString(), MainActivity.this);
                }
            }
        });

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBillCodeDatasFinish)
                    Scan.start(true);
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scan.stop();
                info.append("正在停止....").append("\n");
                billCodeInfo.setText(info.toString());
            }
        });

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scan.closeCar(billCode.getText().toString());
            }
        });
    }

    private void initCountryDatas() {

        Observable
                .mergeDelayError(getConn().getSend(InitCountryService.SEND_URL), getConn().getReceive(InitCountryService.RECEIVEURL))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .flatMap(new Func1<List<? extends Object>, Observable<?>>() {
                    @Override
                    public Observable<?> call(List<? extends Object> objects) {
                        if (!isSendClear) {
                            daoSession.getSenderCountryDao().deleteAll();
                            isSendClear = true;
                        }
                        if (!isReceiveClear) {
                            daoSession.getReceiveCountryDao().deleteAll();
                            isReceiveClear = true;
                        }
                        return Observable.from(objects);
                    }
                })
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        isDBFinish = true;

                        Scan.context = MainActivity.this.getApplicationContext();

                        ScanInitDatas initDatas = new ScanInitDatas("http://118.178.187.230:1880/web/App.asq?/",
                                "baa66be21da6e96f4f17298008e878e6",
                                "装车",
                                "孟凡生1",
                                "哈尔滨");

                        Scan.init(initDatas);
//                        testDB();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        finish();
                    }

                    @Override
                    public void onNext(Object o) {
                        if (o instanceof Send) {

                            Send send = (Send) o;
                            daoSession
                                    .getSenderCountryDao()
                                    .insert(new SenderCountry(
                                            ++sendId
                                            , send.getCnName()
                                            , String.valueOf(send.isDefaultValue())
                                            , send.getBarCodeNumber()));
                        }

                        if (o instanceof Receive) {
                            Receive receive = (Receive) o;
                            daoSession
                                    .getReceiveCountryDao()
                                    .insert(new ReceiveCountry(
                                            ++receiveId,
                                            receive.getCnName(),
                                            receive.getPid() == null ? "" : receive.getPid().toString(),
                                            receive.getBarCodeNumber()
                                    ));
                        }
                    }
                });

    }

    private void testDB() {
        List<SenderCountry> senderCountries = daoSession.getSenderCountryDao().loadAll();
        for (SenderCountry senderCountry : senderCountries) {
            android.util.Log.d(TAG, "--> senderCountry:" + senderCountry.getCnName() + ":" + senderCountry.getBarCodeNumber());
        }

        List<ReceiveCountry> receiveCountries = daoSession.getReceiveCountryDao().loadAll();
        for (ReceiveCountry receiveCountry : receiveCountries) {
            android.util.Log.d(TAG, "--> receiveCountry:" + receiveCountry.getCnName() + ":" + receiveCountry.getBarCodeNumber());
        }
    }


    private InitCountryService getConn() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(20, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

//                Log.d(TAG, "url = " + original.url().url());

                XLog.d(original);


                Request request = original.newBuilder()
                        .header("postman-token", "e22671c2-56b1-2c77-4d0f-3729bf128b2b")
                        .header("Authorization", "Basic baa66be21da6e96f4f17298008e878e6")
                        .header("AppVersion", String.valueOf(com.cly.scanlibrary.BuildConfig.MODULE_VERSION))
                        .method(original.method(), original.body())
                        .build();


                Response response = chain.proceed(request);

                int code = response.code();

                XLog.d(code);

                if (code == 200) {
                    ResponseBody responseBody = response.peekBody(1024 * 1024);
                    XLog.json(responseBody.string());
                    return response;
                } else if (code == 401) {
                    XLog.e("授权失败");
                    throw new InternetBusiness.AuthorizationException("授权失败");
                } else {
                    XLog.e("未知错误");
                    throw new RuntimeException("未知错误");
                }
            }

        });


        OkHttpClient client = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InitCountryService.DOMAIN_NAME)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
//        Log.i(TAG, domainName);
        return retrofit.create(InitCountryService.class);
    }

    private void initDatabase() {
//        MyOpenHelper helper = new MyOpenHelper(getApplicationContext(), "scan_db", null);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        DaoMaster master = new DaoMaster(db);
        daoSession = DbManager.getINSTANCE(this).getDaoSession();
    }

    private void initLog() {
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL             // Specify log level, logs below this level won't be printed, default: LogLevel.ALL
                        : LogLevel.NONE)
                .tag("MY_TAG")                                         // Specify TAG, default: "X-LOG"
                .t()                                                   // Enable thread info, disabled by default
                .st(2)                                                 // Enable stack trace info with depth 2, disabled by default
                .b()                                                   // Enable border, disabled by default
                // Default: DefaultBorderFormatter
                // Add a log interceptor
                .build();

        XLog.init(                                                // Initialize XLog
                config                                                // Specify the log configuration, if not specified, will use new LogConfiguration.Builder().build()
        );
    }

    @Override
    public void onSuccess(BillCodeDatasBean scanBillCodeDatas) {
        isBillCodeDatasFinish = true;
        info.append(scanBillCodeDatas);
        info.append("\n");
        billCodeInfo.setText(info.toString());
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
