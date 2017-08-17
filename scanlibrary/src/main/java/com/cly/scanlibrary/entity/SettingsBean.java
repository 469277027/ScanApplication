package com.cly.scanlibrary.entity;

import java.util.List;

/**
 * 存储扫码设置的类
 * Created by 丛龙宇 on 2017/8/16.
 */

public class SettingsBean {


    /**
     * barcodeRule : {"numbercount":6,"expressions":"[开票地点条码编码][到货公司条码编码][序号]"}
     * line : [{"sendCompany":"哈尔滨","receiveCompany":"沈阳","lines":"哈尔滨-长春-沈阳"}]
     * main : {"scanDateSaveDay":"3","mainType":"快递","mainAutoUpload":"10"}
     */

    private BarcodeRuleBean barcodeRule;
    private MainBean main;
    private List<LineBean> line;

    public BarcodeRuleBean getBarcodeRule() {
        return barcodeRule;
    }

    public void setBarcodeRule(BarcodeRuleBean barcodeRule) {
        this.barcodeRule = barcodeRule;
    }

    public MainBean getMain() {
        return main;
    }

    public void setMain(MainBean main) {
        this.main = main;
    }

    public List<LineBean> getLine() {
        return line;
    }

    public void setLine(List<LineBean> line) {
        this.line = line;
    }

    public static class BarcodeRuleBean {
        /**
         * numbercount : 6
         * expressions : [开票地点条码编码][到货公司条码编码][序号]
         */

        private int numbercount;
        private String expressions;

        public int getNumbercount() {
            return numbercount;
        }

        public void setNumbercount(int numbercount) {
            this.numbercount = numbercount;
        }

        public String getExpressions() {
            return expressions;
        }

        public void setExpressions(String expressions) {
            this.expressions = expressions;
        }
    }

    public static class MainBean {
        /**
         * scanDateSaveDay : 3
         * mainType : 快递
         * mainAutoUpload : 10
         */

        private String scanDateSaveDay;
        private String mainType;
        private String mainAutoUpload;

        public String getScanDateSaveDay() {
            return scanDateSaveDay;
        }

        public void setScanDateSaveDay(String scanDateSaveDay) {
            this.scanDateSaveDay = scanDateSaveDay;
        }

        public String getMainType() {
            return mainType;
        }

        public void setMainType(String mainType) {
            this.mainType = mainType;
        }

        public String getMainAutoUpload() {
            return mainAutoUpload;
        }

        public void setMainAutoUpload(String mainAutoUpload) {
            this.mainAutoUpload = mainAutoUpload;
        }
    }

    public static class LineBean {
        /**
         * sendCompany : 哈尔滨
         * receiveCompany : 沈阳
         * lines : 哈尔滨-长春-沈阳
         */

        private String sendCompany;
        private String receiveCompany;
        private String lines;

        public String getSendCompany() {
            return sendCompany;
        }

        public void setSendCompany(String sendCompany) {
            this.sendCompany = sendCompany;
        }

        public String getReceiveCompany() {
            return receiveCompany;
        }

        public void setReceiveCompany(String receiveCompany) {
            this.receiveCompany = receiveCompany;
        }

        public String getLines() {
            return lines;
        }

        public void setLines(String lines) {
            this.lines = lines;
        }
    }

}
