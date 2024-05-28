package com.sinovatech.unicom.separatemodule.audience.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SharpnessEntity {
    private List<LiveUrlBean> data;
    private String dataChannel;
    private String freeTime;
    private List<GoodListEntity> goodlist;
    private String isNeedCheck;
    private String liveViewAngleMore;
    private List<AngleMoreEntity> liveViewAngleMoreList;
    private String logo;
    private String message;
    private String paidAd;
    private String paidLinks;
    private String paidLive;
    private String passwordCheckErrorNum;
    private String payingUser;
    private PlayBackPayInfoEntity pbPayInfo;
    private String promptText;
    private ShopEntity shopEntity;
    private String statusCode;
    private ZhuboDataEntity userInfo;
    private String videoId;
    private String videoPraiseNum;

    public String getPasswordCheckErrorNum() {
        return this.passwordCheckErrorNum;
    }

    public void setPasswordCheckErrorNum(String str) {
        this.passwordCheckErrorNum = str;
    }

    public String getIsNeedCheck() {
        return this.isNeedCheck;
    }

    public void setIsNeedCheck(String str) {
        this.isNeedCheck = str;
    }

    public PlayBackPayInfoEntity getPbPayInfo() {
        return this.pbPayInfo;
    }

    public void setPbPayInfo(PlayBackPayInfoEntity playBackPayInfoEntity) {
        this.pbPayInfo = playBackPayInfoEntity;
    }

    public String getVideoPraiseNum() {
        return this.videoPraiseNum;
    }

    public void setVideoPraiseNum(String str) {
        this.videoPraiseNum = str;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String str) {
        this.videoId = str;
    }

    public List<GoodListEntity> getGoodlist() {
        return this.goodlist;
    }

    public void setGoodlist(List<GoodListEntity> list) {
        this.goodlist = list;
    }

    public ShopEntity getShopEntity() {
        return this.shopEntity;
    }

    public void setShopEntity(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }

    public ZhuboDataEntity getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(ZhuboDataEntity zhuboDataEntity) {
        this.userInfo = zhuboDataEntity;
    }

    public String getPromptText() {
        return this.promptText;
    }

    public void setPromptText(String str) {
        this.promptText = str;
    }

    public String getLiveViewAngleMore() {
        return this.liveViewAngleMore;
    }

    public void setLiveViewAngleMore(String str) {
        this.liveViewAngleMore = str;
    }

    public List<AngleMoreEntity> getLiveViewAngleMoreList() {
        return this.liveViewAngleMoreList;
    }

    public void setLiveViewAngleMoreList(List<AngleMoreEntity> list) {
        this.liveViewAngleMoreList = list;
    }

    public String getPaidLive() {
        return this.paidLive;
    }

    public void setPaidLive(String str) {
        this.paidLive = str;
    }

    public String getPayingUser() {
        return this.payingUser;
    }

    public void setPayingUser(String str) {
        this.payingUser = str;
    }

    public String getPaidLinks() {
        return this.paidLinks;
    }

    public void setPaidLinks(String str) {
        this.paidLinks = str;
    }

    public String getPaidAd() {
        return this.paidAd;
    }

    public void setPaidAd(String str) {
        this.paidAd = str;
    }

    public String getFreeTime() {
        return this.freeTime;
    }

    public void setFreeTime(String str) {
        this.freeTime = str;
    }

    public String getDataChannel() {
        return this.dataChannel;
    }

    public void setDataChannel(String str) {
        this.dataChannel = str;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public List<LiveUrlBean> getData() {
        return this.data;
    }

    public void setData(List<LiveUrlBean> list) {
        this.data = list;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LiveUrlBean {
        private String dataChannel;
        private String jobNumber;
        private String liveFreePullUrl;
        private String liveFreePullUrlByFlv;
        private String livePullUrl;
        private String livePullUrlByFlv;
        private String sharpnessLevel;
        private String sharpnessName;

        public String getDataChannel() {
            return this.dataChannel;
        }

        public void setDataChannel(String str) {
            this.dataChannel = str;
        }

        public String getJobNumber() {
            return this.jobNumber;
        }

        public void setJobNumber(String str) {
            this.jobNumber = str;
        }

        public String getLiveFreePullUrl() {
            return this.liveFreePullUrl;
        }

        public void setLiveFreePullUrl(String str) {
            this.liveFreePullUrl = str;
        }

        public String getLiveFreePullUrlByFlv() {
            return this.liveFreePullUrlByFlv;
        }

        public void setLiveFreePullUrlByFlv(String str) {
            this.liveFreePullUrlByFlv = str;
        }

        public String getLivePullUrl() {
            return this.livePullUrl;
        }

        public void setLivePullUrl(String str) {
            this.livePullUrl = str;
        }

        public String getLivePullUrlByFlv() {
            return this.livePullUrlByFlv;
        }

        public void setLivePullUrlByFlv(String str) {
            this.livePullUrlByFlv = str;
        }

        public String getSharpnessLevel() {
            return this.sharpnessLevel;
        }

        public void setSharpnessLevel(String str) {
            this.sharpnessLevel = str;
        }

        public String getSharpnessName() {
            return this.sharpnessName;
        }

        public void setSharpnessName(String str) {
            this.sharpnessName = str;
        }
    }
}
