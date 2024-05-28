package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TYCJConfigEntity {
    private List<ChannelRule> channelRuleList;
    private String code;
    private CommonRule commonRule;

    /* renamed from: ls */
    private String f18614ls;

    /* renamed from: pv */
    private String f18615pv;
    private List<TouchuanRule> touchuanRuleList;

    public String getLs() {
        return this.f18614ls;
    }

    public void setLs(String str) {
        this.f18614ls = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public CommonRule getCommonRule() {
        CommonRule commonRule = this.commonRule;
        return commonRule == null ? new CommonRule() : commonRule;
    }

    public void setCommonRule(CommonRule commonRule) {
        this.commonRule = commonRule;
    }

    public String getPv() {
        return this.f18615pv;
    }

    public void setPv(String str) {
        this.f18615pv = str;
    }

    public List<ChannelRule> getChannelRuleList() {
        if (this.channelRuleList == null) {
            this.channelRuleList = new ArrayList();
        }
        return this.channelRuleList;
    }

    public void setChannelRuleList(List<ChannelRule> list) {
        this.channelRuleList = list;
    }

    public List<TouchuanRule> getTouchuanRuleList() {
        if (this.touchuanRuleList == null) {
            this.touchuanRuleList = new ArrayList();
        }
        return this.touchuanRuleList;
    }

    public void setTouchuanRuleList(List<TouchuanRule> list) {
        this.touchuanRuleList = list;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class CommonRule {
        private int isNotJsInjection;
        private int isNotZip;
        private String jsUrl;
        private String numberLog;
        private String spaceUrl;
        private long uploadPackageNum;
        private long uploadPackageSize;
        private int uploadTimeInterval;
        private long uploadZipSize;

        public String getSpaceUrl() {
            return this.spaceUrl;
        }

        public void setSpaceUrl(String str) {
            this.spaceUrl = str;
        }

        public void setNumberLog(String str) {
            this.numberLog = str;
        }

        public long getNumberLog() {
            try {
                return Long.parseLong(this.numberLog);
            } catch (Exception e) {
                e.printStackTrace();
                return 2000L;
            }
        }

        public long getUploadPackageNum() {
            return this.uploadPackageNum;
        }

        public void setUploadPackageNum(long j) {
            this.uploadPackageNum = j;
        }

        public long getUploadPackageSize() {
            return this.uploadPackageSize;
        }

        public void setUploadPackageSize(long j) {
            this.uploadPackageSize = j;
        }

        public int getIsNotJsInjection() {
            return this.isNotJsInjection;
        }

        public void setIsNotJsInjection(int i) {
            this.isNotJsInjection = i;
        }

        public int getUploadTimeInterval() {
            return this.uploadTimeInterval;
        }

        public void setUploadTimeInterval(int i) {
            this.uploadTimeInterval = i;
        }

        public int getIsNotZip() {
            return this.isNotZip;
        }

        public void setIsNotZip(int i) {
            this.isNotZip = i;
        }

        public void setUploadZipSize(long j) {
            this.uploadZipSize = j;
        }

        public long getUploadZipSize() {
            return this.uploadZipSize;
        }

        public String getJsUrl() {
            return this.jsUrl;
        }

        public void setJsUrl(String str) {
            this.jsUrl = str;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ChannelRule {
        private ArrayList<String> blackUrlList;
        private String channelName;
        private String channelTopic;
        private String collectProportion;
        private String isNotMatchingUrl;
        private String mobileList;
        private String provice;
        private String state;
        private ArrayList<String> whiteUrlList;

        public String getChannelTopic() {
            return this.channelTopic;
        }

        public void setChannelTopic(String str) {
            this.channelTopic = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getMobileList() {
            return this.mobileList;
        }

        public void setMobileList(String str) {
            this.mobileList = str;
        }

        public String getProvice() {
            return this.provice;
        }

        public void setProvice(String str) {
            this.provice = str;
        }

        public ArrayList<String> getWhiteUrlList() {
            if (this.whiteUrlList == null) {
                this.whiteUrlList = new ArrayList<>();
            }
            return this.whiteUrlList;
        }

        public void setWhiteUrlList(ArrayList<String> arrayList) {
            this.whiteUrlList = arrayList;
        }

        public ArrayList<String> getBlackUrlList() {
            if (this.blackUrlList == null) {
                this.blackUrlList = new ArrayList<>();
            }
            return this.blackUrlList;
        }

        public void setBlackUrlList(ArrayList<String> arrayList) {
            this.blackUrlList = arrayList;
        }

        public String getChannelName() {
            return this.channelName;
        }

        public void setChannelName(String str) {
            this.channelName = str;
        }

        public String getCollectProportion() {
            return this.collectProportion;
        }

        public void setCollectProportion(String str) {
            this.collectProportion = str;
        }

        public String getIsNotMatchingUrl() {
            return this.isNotMatchingUrl;
        }

        public void setIsNotMatchingUrl(String str) {
            this.isNotMatchingUrl = str;
        }

        public boolean isOpen() {
            return "1".equals(this.state);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class TouchuanRule {
        private String businessId;
        private String number;
        private String realTimeUpload;

        public String getBusinessId() {
            return this.businessId;
        }

        public void setBusinessId(String str) {
            this.businessId = str;
        }

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String str) {
            this.number = str;
        }

        public String getRealTimeUpload() {
            return this.realTimeUpload;
        }

        public void setRealTimeUpload(String str) {
            this.realTimeUpload = str;
        }
    }
}
