package com.cly.scanlibrary.internet;

import com.cly.scanlibrary.business.InternetBusiness;
import com.cly.scanlibrary.entity.ScanCommon;

import rx.Subscriber;

/**
 * Created by 丛龙宇 on 2017/8/17.
 */

public abstract class ScanSubscriber<T extends HttpResult<K>, K> extends Subscriber<T> {

    private K t;

    @Override
    public void onCompleted() {
        onSuccess(t);
    }

    protected abstract void onSuccess(K t);

    protected abstract void onError(String msg);

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onError(e.getMessage());
        if (e instanceof InternetBusiness.LogicException) {

        } else if (e instanceof InternetBusiness.ServerException) {

        } else if (e instanceof InternetBusiness.AuthorizationException) {

        } else {

        }
    }

    @Override
    public void onNext(T t) {
        int code = t.getCode();
        if (code == 200) {
            this.t = t.getContent();
        } else {
            String msg = t.getMsg();
            if (code == 100) {
                throw new InternetBusiness.LogicException(msg == null ? "逻辑错误" : msg);
            } else if (code == 500) {
                throw new InternetBusiness.ServerException(msg == null ? "服务器错误" : msg);
            } else {
                throw new RuntimeException(msg == null ? "未知错误" : msg);
            }
        }
    }
}
