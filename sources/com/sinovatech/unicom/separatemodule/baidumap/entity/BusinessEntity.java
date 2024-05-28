package com.sinovatech.unicom.separatemodule.baidumap.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BusinessEntity {
    private List<BusinessBean> businessList;
    private String code;
    private List<DynamicListBean> dynamicList;
    private String liveFlag;
    private String liveFrontAddress;
    private List<LivingListBean> livingList;
    private String msg;
    private String num;

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public String getLiveFlag() {
        return this.liveFlag;
    }

    public void setLiveFlag(String str) {
        this.liveFlag = str;
    }

    public String getLiveFrontAddress() {
        return this.liveFrontAddress;
    }

    public void setLiveFrontAddress(String str) {
        this.liveFrontAddress = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public List<DynamicListBean> getDynamicList() {
        return this.dynamicList;
    }

    public void setDynamicList(List<DynamicListBean> list) {
        this.dynamicList = list;
    }

    public List<LivingListBean> getLivingList() {
        return this.livingList;
    }

    public void setLivingList(List<LivingListBean> list) {
        this.livingList = list;
    }

    public List<BusinessBean> getEhallList() {
        return this.businessList;
    }

    public void setEhallList(List<BusinessBean> list) {
        this.businessList = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DynamicListBean {
        private String commentsAmount;
        private String dynamicAddress;
        private String horizontalPicture;
        private String likesNum;
        private String topic;
        private String viewsNum;

        public String getCommentsAmount() {
            return this.commentsAmount;
        }

        public void setCommentsAmount(String str) {
            this.commentsAmount = str;
        }

        public String getDynamicAddress() {
            return this.dynamicAddress;
        }

        public void setDynamicAddress(String str) {
            this.dynamicAddress = str;
        }

        public String getHorizontalPicture() {
            return this.horizontalPicture;
        }

        public void setHorizontalPicture(String str) {
            this.horizontalPicture = str;
        }

        public String getLikesNum() {
            return this.likesNum;
        }

        public void setLikesNum(String str) {
            this.likesNum = str;
        }

        public String getViewsNum() {
            return this.viewsNum;
        }

        public void setViewsNum(String str) {
            this.viewsNum = str;
        }

        public String getTopic() {
            return this.topic;
        }

        public void setTopic(String str) {
            this.topic = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LivingListBean {
        private String hallName;
        private String headImg;
        private String liveUrl;
        private String living;

        public String getHallName() {
            return this.hallName;
        }

        public void setHallName(String str) {
            this.hallName = str;
        }

        public String getHeadImg() {
            return this.headImg;
        }

        public void setHeadImg(String str) {
            this.headImg = str;
        }

        public String getLiveUrl() {
            return this.liveUrl;
        }

        public void setLiveUrl(String str) {
            this.liveUrl = str;
        }

        public String getLiving() {
            return this.living;
        }

        public void setLiving(String str) {
            this.living = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class BusinessBean {
        private String PinEpActImg;
        private String WhetherLiveFlag;
        private List<ActListBean> actList;
        private String businessType;
        private List<String> businsessPic;
        private String cityCode;
        private String code;
        private List<CouponListBean> couponList;
        private String dataDate;
        private String distance;
        private int distance1;
        private String distanceUnit;
        private List<DynamicListBean> dynamicList;
        private String dynamicTitle;
        private String dynamicTopicColor;
        private List<String> ehallLabel;
        private List<String> ehallLabelMsgs;
        private int ehall_color;
        private String ehall_frontAddress;
        private String ehall_localLatitudee;
        private String ehall_localLongitude;
        private String ehall_radius;
        private String epActImg;
        private String epAddress;
        private String epBusLine;
        private String epBusinessImg;
        private String epBusinessTime;
        private String epCityname;
        private String epEditReason;
        private String epEditState;
        private String epId;
        private double epJingDu;
        private String epLinkTelphone;
        private String epLinkman;
        private String epName;
        private String epNewBusinessImg;
        private String epProvincename;
        private String epScope;
        private String epSignBuilt;
        private String epState;
        private String epTab;
        private String epType;
        private double epWeiDu;
        private String epXianname;
        private String headPortrait;

        /* renamed from: id */
        private String f18504id;
        private String is5GHall;
        private String isLineUp;
        private String isLive;
        private String liveContent;
        private String liveImg;
        private String livePath;
        private List<MoreActListBean> moreActList;
        private List<String> newBusinsessPic;
        private String provCode;
        private String showStarFlag;
        private String starScore;
        private String starScoreImg;
        private List<TitleListBean> titleList;
        private String typeIdentifier;
        private String typeName;
        private String whetherLive;

        public String getDynamicTitle() {
            return this.dynamicTitle;
        }

        public void setDynamicTitle(String str) {
            this.dynamicTitle = str;
        }

        public String getDynamicTopicColor() {
            return this.dynamicTopicColor;
        }

        public void setDynamicTopicColor(String str) {
            this.dynamicTopicColor = str;
        }

        public String getEpXianname() {
            return this.epXianname;
        }

        public void setEpXianname(String str) {
            this.epXianname = str;
        }

        public String getTypeName() {
            return this.typeName;
        }

        public void setTypeName(String str) {
            this.typeName = str;
        }

        public String getShowStarFlag() {
            return this.showStarFlag;
        }

        public void setShowStarFlag(String str) {
            this.showStarFlag = str;
        }

        public List<TitleListBean> getTitleList() {
            return this.titleList;
        }

        public void setTitleList(List<TitleListBean> list) {
            this.titleList = list;
        }

        public String getWhetherLiveFlag() {
            return this.WhetherLiveFlag;
        }

        public void setWhetherLiveFlag(String str) {
            this.WhetherLiveFlag = str;
        }

        public String getBusinessType() {
            return this.businessType;
        }

        public void setBusinessType(String str) {
            this.businessType = str;
        }

        public String getCityCode() {
            return this.cityCode;
        }

        public void setCityCode(String str) {
            this.cityCode = str;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String getDataDate() {
            return this.dataDate;
        }

        public void setDataDate(String str) {
            this.dataDate = str;
        }

        public String getDistance() {
            return this.distance;
        }

        public void setDistance(String str) {
            this.distance = str;
        }

        public String getDistanceUnit() {
            return this.distanceUnit;
        }

        public void setDistanceUnit(String str) {
            this.distanceUnit = str;
        }

        public int getEhall_color() {
            return this.ehall_color;
        }

        public void setEhall_color(int i) {
            this.ehall_color = i;
        }

        public String getEhall_frontAddress() {
            return this.ehall_frontAddress;
        }

        public void setEhall_frontAddress(String str) {
            this.ehall_frontAddress = str;
        }

        public String getEhall_localLatitudee() {
            return this.ehall_localLatitudee;
        }

        public void setEhall_localLatitudee(String str) {
            this.ehall_localLatitudee = str;
        }

        public String getEhall_localLongitude() {
            return this.ehall_localLongitude;
        }

        public void setEhall_localLongitude(String str) {
            this.ehall_localLongitude = str;
        }

        public String getEhall_radius() {
            return this.ehall_radius;
        }

        public void setEhall_radius(String str) {
            this.ehall_radius = str;
        }

        public String getEpActImg() {
            return this.epActImg;
        }

        public void setEpActImg(String str) {
            this.epActImg = str;
        }

        public String getEpAddress() {
            return this.epAddress;
        }

        public void setEpAddress(String str) {
            this.epAddress = str;
        }

        public String getEpBusLine() {
            return this.epBusLine;
        }

        public void setEpBusLine(String str) {
            this.epBusLine = str;
        }

        public String getEpBusinessImg() {
            return this.epBusinessImg;
        }

        public void setEpBusinessImg(String str) {
            this.epBusinessImg = str;
        }

        public String getEpBusinessTime() {
            return this.epBusinessTime;
        }

        public void setEpBusinessTime(String str) {
            this.epBusinessTime = str;
        }

        public String getEpCityname() {
            return this.epCityname;
        }

        public void setEpCityname(String str) {
            this.epCityname = str;
        }

        public String getEpEditReason() {
            return this.epEditReason;
        }

        public void setEpEditReason(String str) {
            this.epEditReason = str;
        }

        public String getEpEditState() {
            return this.epEditState;
        }

        public void setEpEditState(String str) {
            this.epEditState = str;
        }

        public String getEpId() {
            return this.epId;
        }

        public void setEpId(String str) {
            this.epId = str;
        }

        public double getEpJingDu() {
            return this.epJingDu;
        }

        public void setEpJingDu(double d) {
            this.epJingDu = d;
        }

        public String getEpLinkTelphone() {
            return this.epLinkTelphone;
        }

        public void setEpLinkTelphone(String str) {
            this.epLinkTelphone = str;
        }

        public String getEpLinkman() {
            return this.epLinkman;
        }

        public void setEpLinkman(String str) {
            this.epLinkman = str;
        }

        public String getEpName() {
            return this.epName;
        }

        public void setEpName(String str) {
            this.epName = str;
        }

        public String getEpNewBusinessImg() {
            return this.epNewBusinessImg;
        }

        public void setEpNewBusinessImg(String str) {
            this.epNewBusinessImg = str;
        }

        public String getEpProvincename() {
            return this.epProvincename;
        }

        public void setEpProvincename(String str) {
            this.epProvincename = str;
        }

        public String getEpScope() {
            return this.epScope;
        }

        public void setEpScope(String str) {
            this.epScope = str;
        }

        public String getEpSignBuilt() {
            return this.epSignBuilt;
        }

        public void setEpSignBuilt(String str) {
            this.epSignBuilt = str;
        }

        public String getEpState() {
            return this.epState;
        }

        public void setEpState(String str) {
            this.epState = str;
        }

        public String getEpTab() {
            return this.epTab;
        }

        public void setEpTab(String str) {
            this.epTab = str;
        }

        public String getEpType() {
            return this.epType;
        }

        public void setEpType(String str) {
            this.epType = str;
        }

        public double getEpWeiDu() {
            return this.epWeiDu;
        }

        public void setEpWeiDu(double d) {
            this.epWeiDu = d;
        }

        public String getHeadPortrait() {
            return this.headPortrait;
        }

        public void setHeadPortrait(String str) {
            this.headPortrait = str;
        }

        public String getId() {
            return this.f18504id;
        }

        public void setId(String str) {
            this.f18504id = str;
        }

        public String getIs5GHall() {
            return this.is5GHall;
        }

        public void setIs5GHall(String str) {
            this.is5GHall = str;
        }

        public String getIsLineUp() {
            return this.isLineUp;
        }

        public void setIsLineUp(String str) {
            this.isLineUp = str;
        }

        public String getIsLive() {
            return this.isLive;
        }

        public void setIsLive(String str) {
            this.isLive = str;
        }

        public String getLiveContent() {
            return this.liveContent;
        }

        public void setLiveContent(String str) {
            this.liveContent = str;
        }

        public String getLiveImg() {
            return this.liveImg;
        }

        public void setLiveImg(String str) {
            this.liveImg = str;
        }

        public String getLivePath() {
            return this.livePath;
        }

        public void setLivePath(String str) {
            this.livePath = str;
        }

        public String getProvCode() {
            return this.provCode;
        }

        public void setProvCode(String str) {
            this.provCode = str;
        }

        public String getStarScore() {
            return this.starScore;
        }

        public void setStarScore(String str) {
            this.starScore = str;
        }

        public String getStarScoreImg() {
            return this.starScoreImg;
        }

        public void setStarScoreImg(String str) {
            this.starScoreImg = str;
        }

        public String getWhetherLive() {
            return this.whetherLive;
        }

        public void setWhetherLive(String str) {
            this.whetherLive = str;
        }

        public String getPinEpActImg() {
            return this.PinEpActImg;
        }

        public void setPinEpActImg(String str) {
            this.PinEpActImg = str;
        }

        public String getTypeIdentifier() {
            return this.typeIdentifier;
        }

        public void setTypeIdentifier(String str) {
            this.typeIdentifier = str;
        }

        public int getDistance1() {
            return this.distance1;
        }

        public void setDistance1(int i) {
            this.distance1 = i;
        }

        public List<ActListBean> getActList() {
            return this.actList;
        }

        public void setActList(List<ActListBean> list) {
            this.actList = list;
        }

        public List<String> getEhallLabel() {
            return this.ehallLabel;
        }

        public void setEhallLabel(List<String> list) {
            this.ehallLabel = list;
        }

        public List<String> getBusinsessPic() {
            return this.businsessPic;
        }

        public void setBusinsessPic(List<String> list) {
            this.businsessPic = list;
        }

        public List<String> getEhallLabelMsgs() {
            return this.ehallLabelMsgs;
        }

        public void setEhallLabelMsgs(List<String> list) {
            this.ehallLabelMsgs = list;
        }

        public List<String> getNewBusinsessPic() {
            return this.newBusinsessPic;
        }

        public void setNewBusinsessPic(List<String> list) {
            this.newBusinsessPic = list;
        }

        public List<CouponListBean> getCouponList() {
            return this.couponList;
        }

        public void setCouponList(List<CouponListBean> list) {
            this.couponList = list;
        }

        public List<DynamicListBean> getDynamicList() {
            return this.dynamicList;
        }

        public void setDynamicList(List<DynamicListBean> list) {
            this.dynamicList = list;
        }

        public List<MoreActListBean> getMoreActList() {
            return this.moreActList;
        }

        public void setMoreActList(List<MoreActListBean> list) {
            this.moreActList = list;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class CouponListBean {
            private String company;
            private String couponGetImg;
            private String couponImg;
            private String couponListImg;
            private String couponName;
            private String couponNum;
            private String couponRule;
            private String couponType;
            private String couponTypeStr;
            private String couponUseImg;
            private String couponUseURL;
            private String cycle;
            private String dataDate;
            private String discount;
            private String downLine;
            private String effectiveTime;
            private String eqId;
            private String exchangeUrl;
            private String haveReceivedImg;

            /* renamed from: id */
            private String f18505id;
            private String isCommend;
            private String isCommendStr;
            private String isDraw;
            private String isShow;
            private String isShowStr;
            private String lootingImg;
            private String lostEffectiveTime;
            private String onLine;
            private String peopleType;
            private String peopleTypeStr;

            public String getCompany() {
                return this.company;
            }

            public void setCompany(String str) {
                this.company = str;
            }

            public String getCouponGetImg() {
                return this.couponGetImg;
            }

            public void setCouponGetImg(String str) {
                this.couponGetImg = str;
            }

            public String getCouponImg() {
                return this.couponImg;
            }

            public void setCouponImg(String str) {
                this.couponImg = str;
            }

            public String getCouponListImg() {
                return this.couponListImg;
            }

            public void setCouponListImg(String str) {
                this.couponListImg = str;
            }

            public String getCouponName() {
                return this.couponName;
            }

            public void setCouponName(String str) {
                this.couponName = str;
            }

            public String getCouponNum() {
                return this.couponNum;
            }

            public void setCouponNum(String str) {
                this.couponNum = str;
            }

            public String getCouponRule() {
                return this.couponRule;
            }

            public void setCouponRule(String str) {
                this.couponRule = str;
            }

            public String getCouponType() {
                return this.couponType;
            }

            public void setCouponType(String str) {
                this.couponType = str;
            }

            public String getCouponTypeStr() {
                return this.couponTypeStr;
            }

            public void setCouponTypeStr(String str) {
                this.couponTypeStr = str;
            }

            public String getCouponUseImg() {
                return this.couponUseImg;
            }

            public void setCouponUseImg(String str) {
                this.couponUseImg = str;
            }

            public String getCouponUseURL() {
                return this.couponUseURL;
            }

            public void setCouponUseURL(String str) {
                this.couponUseURL = str;
            }

            public String getCycle() {
                return this.cycle;
            }

            public void setCycle(String str) {
                this.cycle = str;
            }

            public String getDataDate() {
                return this.dataDate;
            }

            public void setDataDate(String str) {
                this.dataDate = str;
            }

            public String getDiscount() {
                return this.discount;
            }

            public void setDiscount(String str) {
                this.discount = str;
            }

            public String getDownLine() {
                return this.downLine;
            }

            public void setDownLine(String str) {
                this.downLine = str;
            }

            public String getEffectiveTime() {
                return this.effectiveTime;
            }

            public void setEffectiveTime(String str) {
                this.effectiveTime = str;
            }

            public String getEqId() {
                return this.eqId;
            }

            public void setEqId(String str) {
                this.eqId = str;
            }

            public String getExchangeUrl() {
                return this.exchangeUrl;
            }

            public void setExchangeUrl(String str) {
                this.exchangeUrl = str;
            }

            public String getHaveReceivedImg() {
                return this.haveReceivedImg;
            }

            public void setHaveReceivedImg(String str) {
                this.haveReceivedImg = str;
            }

            public String getId() {
                return this.f18505id;
            }

            public void setId(String str) {
                this.f18505id = str;
            }

            public String getIsCommend() {
                return this.isCommend;
            }

            public void setIsCommend(String str) {
                this.isCommend = str;
            }

            public String getIsCommendStr() {
                return this.isCommendStr;
            }

            public void setIsCommendStr(String str) {
                this.isCommendStr = str;
            }

            public String getIsDraw() {
                return this.isDraw;
            }

            public void setIsDraw(String str) {
                this.isDraw = str;
            }

            public String getIsShow() {
                return this.isShow;
            }

            public void setIsShow(String str) {
                this.isShow = str;
            }

            public String getIsShowStr() {
                return this.isShowStr;
            }

            public void setIsShowStr(String str) {
                this.isShowStr = str;
            }

            public String getLootingImg() {
                return this.lootingImg;
            }

            public void setLootingImg(String str) {
                this.lootingImg = str;
            }

            public String getLostEffectiveTime() {
                return this.lostEffectiveTime;
            }

            public void setLostEffectiveTime(String str) {
                this.lostEffectiveTime = str;
            }

            public String getOnLine() {
                return this.onLine;
            }

            public void setOnLine(String str) {
                this.onLine = str;
            }

            public String getPeopleType() {
                return this.peopleType;
            }

            public void setPeopleType(String str) {
                this.peopleType = str;
            }

            public String getPeopleTypeStr() {
                return this.peopleTypeStr;
            }

            public void setPeopleTypeStr(String str) {
                this.peopleTypeStr = str;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class DynamicListBean {
            private String downLine;
            private String dynamicCode;
            private String message;
            private String onLine;

            public String getDownLine() {
                return this.downLine;
            }

            public void setDownLine(String str) {
                this.downLine = str;
            }

            public String getDynamicCode() {
                return this.dynamicCode;
            }

            public void setDynamicCode(String str) {
                this.dynamicCode = str;
            }

            public String getMessage() {
                return this.message;
            }

            public void setMessage(String str) {
                this.message = str;
            }

            public String getOnLine() {
                return this.onLine;
            }

            public void setOnLine(String str) {
                this.onLine = str;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class MoreActListBean {
            private String actCode;
            private String actDetail;
            private String actState;
            private String actUrl;
            private String describe;
            private String detailPitches;
            private String downLine;
            private String encoderImg;

            /* renamed from: id */
            private String f18506id;
            private String isCommend;
            private String isUseTemplet;
            private String newActImg;
            private List<String> newActImgs;
            private String onLine;
            private String pageViewsNum;
            private String pitches;
            private String searchWord;
            private String targetUser;
            private String title;

            public String getActCode() {
                return this.actCode;
            }

            public void setActCode(String str) {
                this.actCode = str;
            }

            public String getActDetail() {
                return this.actDetail;
            }

            public void setActDetail(String str) {
                this.actDetail = str;
            }

            public String getActState() {
                return this.actState;
            }

            public void setActState(String str) {
                this.actState = str;
            }

            public String getActUrl() {
                return this.actUrl;
            }

            public void setActUrl(String str) {
                this.actUrl = str;
            }

            public String getDescribe() {
                return this.describe;
            }

            public void setDescribe(String str) {
                this.describe = str;
            }

            public String getDetailPitches() {
                return this.detailPitches;
            }

            public void setDetailPitches(String str) {
                this.detailPitches = str;
            }

            public String getDownLine() {
                return this.downLine;
            }

            public void setDownLine(String str) {
                this.downLine = str;
            }

            public String getEncoderImg() {
                return this.encoderImg;
            }

            public void setEncoderImg(String str) {
                this.encoderImg = str;
            }

            public String getId() {
                return this.f18506id;
            }

            public void setId(String str) {
                this.f18506id = str;
            }

            public String getIsCommend() {
                return this.isCommend;
            }

            public void setIsCommend(String str) {
                this.isCommend = str;
            }

            public String getIsUseTemplet() {
                return this.isUseTemplet;
            }

            public void setIsUseTemplet(String str) {
                this.isUseTemplet = str;
            }

            public String getNewActImg() {
                return this.newActImg;
            }

            public void setNewActImg(String str) {
                this.newActImg = str;
            }

            public String getOnLine() {
                return this.onLine;
            }

            public void setOnLine(String str) {
                this.onLine = str;
            }

            public String getPageViewsNum() {
                return this.pageViewsNum;
            }

            public void setPageViewsNum(String str) {
                this.pageViewsNum = str;
            }

            public String getPitches() {
                return this.pitches;
            }

            public void setPitches(String str) {
                this.pitches = str;
            }

            public String getSearchWord() {
                return this.searchWord;
            }

            public void setSearchWord(String str) {
                this.searchWord = str;
            }

            public String getTargetUser() {
                return this.targetUser;
            }

            public void setTargetUser(String str) {
                this.targetUser = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public List<String> getNewActImgs() {
                return this.newActImgs;
            }

            public void setNewActImgs(List<String> list) {
                this.newActImgs = list;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class ActListBean {
            private String actCode;
            private String actDetail;
            private String actDetailImg;
            private String actState;
            private String actUrl;
            private String activityImg;
            private String company;
            private String custom1;
            private String custom2;
            private String custom3;
            private String customContent1;
            private String customContent2;
            private String customContent3;
            private String describe;
            private String detailImg;
            private String downLine;
            private String encoderImg;
            private String isCommend;
            private String isShow;
            private String isUseTemplet;
            private String newActImg;
            private List<String> newActImgs;
            private String onLine;
            private String pitches;
            private String pitches2;
            private String pitches3;
            private String searchWord;
            private String title;

            public String getActivityImg() {
                return this.activityImg;
            }

            public void setActivityImg(String str) {
                this.activityImg = str;
            }

            public String getActCode() {
                return this.actCode;
            }

            public void setActCode(String str) {
                this.actCode = str;
            }

            public String getActUrl() {
                return this.actUrl;
            }

            public void setActUrl(String str) {
                this.actUrl = str;
            }

            public String getDescribe() {
                return this.describe;
            }

            public void setDescribe(String str) {
                this.describe = str;
            }

            public String getDownLine() {
                return this.downLine;
            }

            public void setDownLine(String str) {
                this.downLine = str;
            }

            public String getEncoderImg() {
                return this.encoderImg;
            }

            public void setEncoderImg(String str) {
                this.encoderImg = str;
            }

            public String getIsCommend() {
                return this.isCommend;
            }

            public void setIsCommend(String str) {
                this.isCommend = str;
            }

            public String getIsShow() {
                return this.isShow;
            }

            public void setIsShow(String str) {
                this.isShow = str;
            }

            public String getIsUseTemplet() {
                return this.isUseTemplet;
            }

            public void setIsUseTemplet(String str) {
                this.isUseTemplet = str;
            }

            public String getNewActImg() {
                return this.newActImg;
            }

            public void setNewActImg(String str) {
                this.newActImg = str;
            }

            public String getOnLine() {
                return this.onLine;
            }

            public void setOnLine(String str) {
                this.onLine = str;
            }

            public String getSearchWord() {
                return this.searchWord;
            }

            public void setSearchWord(String str) {
                this.searchWord = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getActDetail() {
                return this.actDetail;
            }

            public void setActDetail(String str) {
                this.actDetail = str;
            }

            public String getActDetailImg() {
                return this.actDetailImg;
            }

            public void setActDetailImg(String str) {
                this.actDetailImg = str;
            }

            public String getCompany() {
                return this.company;
            }

            public void setCompany(String str) {
                this.company = str;
            }

            public String getCustom1() {
                return this.custom1;
            }

            public void setCustom1(String str) {
                this.custom1 = str;
            }

            public String getCustom2() {
                return this.custom2;
            }

            public void setCustom2(String str) {
                this.custom2 = str;
            }

            public String getCustom3() {
                return this.custom3;
            }

            public void setCustom3(String str) {
                this.custom3 = str;
            }

            public String getCustomContent1() {
                return this.customContent1;
            }

            public void setCustomContent1(String str) {
                this.customContent1 = str;
            }

            public String getCustomContent2() {
                return this.customContent2;
            }

            public void setCustomContent2(String str) {
                this.customContent2 = str;
            }

            public String getCustomContent3() {
                return this.customContent3;
            }

            public void setCustomContent3(String str) {
                this.customContent3 = str;
            }

            public String getDetailImg() {
                return this.detailImg;
            }

            public void setDetailImg(String str) {
                this.detailImg = str;
            }

            public String getActState() {
                return this.actState;
            }

            public void setActState(String str) {
                this.actState = str;
            }

            public List<String> getNewActImgs() {
                return this.newActImgs;
            }

            public void setNewActImgs(List<String> list) {
                this.newActImgs = list;
            }

            public String getPitches() {
                return this.pitches;
            }

            public void setPitches(String str) {
                this.pitches = str;
            }

            public String getPitches2() {
                return this.pitches2;
            }

            public void setPitches2(String str) {
                this.pitches2 = str;
            }

            public String getPitches3() {
                return this.pitches3;
            }

            public void setPitches3(String str) {
                this.pitches3 = str;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class TitleListBean {
            private String icon;
            private String title;

            public String getIcon() {
                return this.icon;
            }

            public void setIcon(String str) {
                this.icon = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }
        }
    }
}
