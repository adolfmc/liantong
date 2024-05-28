package com.sinovatech.unicom.separatemodule.user.entity;

import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserServerEntity {
    private String channelAddr;
    private String channelCode;
    private String channelName;
    private String copywriting;
    private String engineerType;
    private GoodData goodData;
    private String groupType;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18626id;
    private String isPartyMen;
    private String onlineStatus;
    private String reminderTitle;
    private Object salemenHeadImg;
    private String salemenJobNum;
    private String salemenLevel;
    private String salemenLevelName;
    private String salemenName;
    private String salemenStar;
    private int salemenState;
    private String salemenType;
    private String sex;
    private String tenantCode;
    private String url;
    private String url2;
    private String url3;
    private String url3Callee;
    private String url3Caller;
    private String url3CallerType;
    private String url3ReplaceSentence;
    private String url3ReplaceType;
    private String url3Tag;
    private String workYears;

    public String getUrl3ReplaceType() {
        return this.url3ReplaceType;
    }

    public void setUrl3ReplaceType(String str) {
        this.url3ReplaceType = str;
    }

    public String getUrl3ReplaceSentence() {
        return this.url3ReplaceSentence;
    }

    public void setUrl3ReplaceSentence(String str) {
        this.url3ReplaceSentence = str;
    }

    public String getReminderTitle() {
        return this.reminderTitle;
    }

    public void setReminderTitle(String str) {
        this.reminderTitle = str;
    }

    public String getEngineerType() {
        return this.engineerType;
    }

    public void setEngineerType(String str) {
        this.engineerType = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getSalemenJobNum() {
        return this.salemenJobNum;
    }

    public void setSalemenJobNum(String str) {
        this.salemenJobNum = str;
    }

    public String getSalemenLevelName() {
        return this.salemenLevelName;
    }

    public void setSalemenLevelName(String str) {
        this.salemenLevelName = str;
    }

    public int getSalemenState() {
        return this.salemenState;
    }

    public void setSalemenState(int i) {
        this.salemenState = i;
    }

    public String getSalemenName() {
        return this.salemenName;
    }

    public void setSalemenName(String str) {
        this.salemenName = str;
    }

    public String getSalemenType() {
        return this.salemenType;
    }

    public void setSalemenType(String str) {
        this.salemenType = str;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String str) {
        this.channelName = str;
    }

    public String getChannelAddr() {
        return this.channelAddr;
    }

    public void setChannelAddr(String str) {
        this.channelAddr = str;
    }

    public String getTenantCode() {
        return this.tenantCode;
    }

    public void setTenantCode(String str) {
        this.tenantCode = str;
    }

    public String getSalemenLevel() {
        return this.salemenLevel;
    }

    public void setSalemenLevel(String str) {
        this.salemenLevel = str;
    }

    public Object getSalemenHeadImg() {
        return this.salemenHeadImg;
    }

    public void setSalemenHeadImg(Object obj) {
        this.salemenHeadImg = obj;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public void setChannelCode(String str) {
        this.channelCode = str;
    }

    public String getGroupType() {
        return this.groupType;
    }

    public void setGroupType(String str) {
        this.groupType = str;
    }

    public String getIsPartyMen() {
        return this.isPartyMen;
    }

    public void setIsPartyMen(String str) {
        this.isPartyMen = str;
    }

    public String getWorkYears() {
        return this.workYears;
    }

    public void setWorkYears(String str) {
        this.workYears = str;
    }

    public String getSalemenStar() {
        return this.salemenStar;
    }

    public void setSalemenStar(String str) {
        this.salemenStar = str;
    }

    public String getOnlineStatus() {
        return this.onlineStatus;
    }

    public void setOnlineStatus(String str) {
        this.onlineStatus = str;
    }

    public String getCopywriting() {
        return this.copywriting;
    }

    public void setCopywriting(String str) {
        this.copywriting = str;
    }

    public GoodData getGoodData() {
        return this.goodData;
    }

    public void setGoodData(GoodData goodData) {
        this.goodData = goodData;
    }

    public long getId() {
        return this.f18626id;
    }

    public void setId(long j) {
        this.f18626id = j;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getUrl3Tag() {
        return this.url3Tag;
    }

    public void setUrl3Tag(String str) {
        this.url3Tag = str;
    }

    public String getUrl2() {
        return this.url2;
    }

    public void setUrl2(String str) {
        this.url2 = str;
    }

    public String getUrl3() {
        return this.url3;
    }

    public String getUrl3Caller() {
        return this.url3Caller;
    }

    public void setUrl3Caller(String str) {
        this.url3Caller = str;
    }

    public void setUrl3(String str) {
        this.url3 = str;
    }

    public String getUrl3Callee() {
        return this.url3Callee;
    }

    public void setUrl3Callee(String str) {
        this.url3Callee = str;
    }

    public String getUrl3CallerType() {
        return this.url3CallerType;
    }

    public void setUrl3CallerType(String str) {
        this.url3CallerType = str;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class GoodData {
        private String backImg;
        private String backTitle;
        private String goodDes;
        private String goodImg;
        private String goodName;
        private String goodQuan;
        private String goodUrl;
        private String laceImg;
        private String originalPrice;
        private String price;
        private String subtitle;
        private String title;

        public String getGoodName() {
            return this.goodName;
        }

        public void setGoodName(String str) {
            this.goodName = str;
        }

        public String getGoodImg() {
            return this.goodImg;
        }

        public void setGoodImg(String str) {
            this.goodImg = str;
        }

        public String getGoodUrl() {
            return this.goodUrl;
        }

        public void setGoodUrl(String str) {
            this.goodUrl = str;
        }

        public String getPrice() {
            return this.price;
        }

        public void setPrice(String str) {
            this.price = str;
        }

        public String getOriginalPrice() {
            return this.originalPrice;
        }

        public void setOriginalPrice(String str) {
            this.originalPrice = str;
        }

        public String getSubtitle() {
            return this.subtitle;
        }

        public void setSubtitle(String str) {
            this.subtitle = str;
        }

        public String getLaceImg() {
            return this.laceImg;
        }

        public void setLaceImg(String str) {
            this.laceImg = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getGoodDes() {
            return this.goodDes;
        }

        public void setGoodDes(String str) {
            this.goodDes = str;
        }

        public String getGoodQuan() {
            return this.goodQuan;
        }

        public void setGoodQuan(String str) {
            if ("null".equals(str)) {
                str = "";
            }
            this.goodQuan = str;
        }

        public String getBackImg() {
            return this.backImg;
        }

        public void setBackImg(String str) {
            this.backImg = str;
        }

        public String getBackTitle() {
            return this.backTitle;
        }

        public void setBackTitle(String str) {
            this.backTitle = str;
        }
    }
}
