package com.chinaunicon.jtwifilib.jtcommon.model;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtIntnetInfo {
    private String isUnicomNet;
    private String result;
    private String serviceProvider;
    private String transactionID;
    private String wanIp;
    private String wifiSpeedLocaltion;
    private List<WifiSpeedNode> wifiSpeedNodeList;

    public String getIsUnicomNet() {
        return this.isUnicomNet;
    }

    public void setIsUnicomNet(String str) {
        this.isUnicomNet = str;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getServiceProvider() {
        return this.serviceProvider;
    }

    public void setServiceProvider(String str) {
        this.serviceProvider = str;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(String str) {
        this.transactionID = str;
    }

    public String getWanIp() {
        return this.wanIp;
    }

    public void setWanIp(String str) {
        this.wanIp = str;
    }

    public String getWifiSpeedLocaltion() {
        return this.wifiSpeedLocaltion;
    }

    public void setWifiSpeedLocaltion(String str) {
        this.wifiSpeedLocaltion = str;
    }

    public List<WifiSpeedNode> getWifiSpeedNodeList() {
        return this.wifiSpeedNodeList;
    }

    public void setWifiSpeedNodeList(List<WifiSpeedNode> list) {
        this.wifiSpeedNodeList = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class WifiSpeedNode {
        private String pingIP;
        private String speedUrl;

        public String getSpeedUrl() {
            return this.speedUrl;
        }

        public void setSpeedUrl(String str) {
            this.speedUrl = str;
        }

        public String getPingIP() {
            return this.pingIP;
        }

        public void setPingIP(String str) {
            this.pingIP = str;
        }
    }
}
