package com.sinovatech.unicom.separatemodule.baidumap.entity;

import java.io.Serializable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BusinessDetailsEntity implements Serializable {
    private List<ActListBean> actList;
    private String businessType;
    private List<String> businsessPic;
    private String cityCode;
    private String code;
    private String collectionStatus;
    private String complaintUrl;
    private List<CouponListBean> couponList;
    private String dataDate;
    private String distance;
    private int distance1;
    private List<DynamicListBean> dynamicList;
    private List<String> ehallLabelMsgs;
    private String ehall_frontAddress;
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
    private String epProvincename;
    private String epScope;
    private String epSignBuilt;
    private String epState;
    private Object epTab;
    private String epType;
    private double epWeiDu;
    private String epXianname;
    private List<HuiListBean> huiList;

    /* renamed from: id */
    private String f18502id;
    private String isLineUp;
    private String isLive;
    private String msg;
    private String provCode;
    private String queueNum;
    private String showStarFlag;
    private String starScore;
    private String starScoreImg;
    private List<TitleListBean> titleList;
    private String typeIdentifier;
    private String typeName;
    private String waitTime;
    private String whetherLive;

    public List<TitleListBean> getTitleList() {
        return this.titleList;
    }

    public void setTitleList(List<TitleListBean> list) {
        this.titleList = list;
    }

    public String getShowStarFlag() {
        return this.showStarFlag;
    }

    public void setShowStarFlag(String str) {
        this.showStarFlag = str;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public String getEhall_frontAddress() {
        return this.ehall_frontAddress;
    }

    public void setEhall_frontAddress(String str) {
        this.ehall_frontAddress = str;
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

    public String getEpActImg() {
        return this.epActImg;
    }

    public void setEpActImg(String str) {
        this.epActImg = str;
    }

    public String getEpXianname() {
        return this.epXianname;
    }

    public void setEpXianname(String str) {
        this.epXianname = str;
    }

    public String getEpLinkTelphone() {
        return this.epLinkTelphone;
    }

    public void setEpLinkTelphone(String str) {
        this.epLinkTelphone = str;
    }

    public String getWaitTime() {
        return this.waitTime;
    }

    public void setWaitTime(String str) {
        this.waitTime = str;
    }

    public String getEpProvincename() {
        return this.epProvincename;
    }

    public void setEpProvincename(String str) {
        this.epProvincename = str;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public List<ActListBean> getActList() {
        return this.actList;
    }

    public void setActList(List<ActListBean> list) {
        this.actList = list;
    }

    public String getIsLive() {
        return this.isLive;
    }

    public void setIsLive(String str) {
        this.isLive = str;
    }

    public String getWhetherLive() {
        return this.whetherLive;
    }

    public void setWhetherLive(String str) {
        this.whetherLive = str;
    }

    public String getId() {
        return this.f18502id;
    }

    public void setId(String str) {
        this.f18502id = str;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String str) {
        this.distance = str;
    }

    public int getDistance1() {
        return this.distance1;
    }

    public void setDistance1(int i) {
        this.distance1 = i;
    }

    public String getEpLinkman() {
        return this.epLinkman;
    }

    public void setEpLinkman(String str) {
        this.epLinkman = str;
    }

    public String getEpBusLine() {
        return this.epBusLine;
    }

    public void setEpBusLine(String str) {
        this.epBusLine = str;
    }

    public double getEpWeiDu() {
        return this.epWeiDu;
    }

    public void setEpWeiDu(double d) {
        this.epWeiDu = d;
    }

    public double getEpJingDu() {
        return this.epJingDu;
    }

    public void setEpJingDu(double d) {
        this.epJingDu = d;
    }

    public String getEpSignBuilt() {
        return this.epSignBuilt;
    }

    public void setEpSignBuilt(String str) {
        this.epSignBuilt = str;
    }

    public String getEpType() {
        return this.epType;
    }

    public void setEpType(String str) {
        this.epType = str;
    }

    public String getQueueNum() {
        return this.queueNum;
    }

    public void setQueueNum(String str) {
        this.queueNum = str;
    }

    public String getEpCityname() {
        return this.epCityname;
    }

    public void setEpCityname(String str) {
        this.epCityname = str;
    }

    public String getIsLineUp() {
        return this.isLineUp;
    }

    public void setIsLineUp(String str) {
        this.isLineUp = str;
    }

    public String getEpName() {
        return this.epName;
    }

    public void setEpName(String str) {
        this.epName = str;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String str) {
        this.businessType = str;
    }

    public String getEpId() {
        return this.epId;
    }

    public void setEpId(String str) {
        this.epId = str;
    }

    public String getEpBusinessTime() {
        return this.epBusinessTime;
    }

    public void setEpBusinessTime(String str) {
        this.epBusinessTime = str;
    }

    public String getEpAddress() {
        return this.epAddress;
    }

    public void setEpAddress(String str) {
        this.epAddress = str;
    }

    public String getEpEditReason() {
        return this.epEditReason;
    }

    public void setEpEditReason(String str) {
        this.epEditReason = str;
    }

    public String getDataDate() {
        return this.dataDate;
    }

    public void setDataDate(String str) {
        this.dataDate = str;
    }

    public String getEpState() {
        return this.epState;
    }

    public void setEpState(String str) {
        this.epState = str;
    }

    public String getEpBusinessImg() {
        return this.epBusinessImg;
    }

    public void setEpBusinessImg(String str) {
        this.epBusinessImg = str;
    }

    public String getEpEditState() {
        return this.epEditState;
    }

    public void setEpEditState(String str) {
        this.epEditState = str;
    }

    public String getProvCode() {
        return this.provCode;
    }

    public void setProvCode(String str) {
        this.provCode = str;
    }

    public String getCollectionStatus() {
        return this.collectionStatus;
    }

    public void setCollectionStatus(String str) {
        this.collectionStatus = str;
    }

    public Object getEpTab() {
        return this.epTab;
    }

    public void setEpTab(Object obj) {
        this.epTab = obj;
    }

    public String getEpScope() {
        return this.epScope;
    }

    public void setEpScope(String str) {
        this.epScope = str;
    }

    public String getComplaintUrl() {
        return this.complaintUrl;
    }

    public void setComplaintUrl(String str) {
        this.complaintUrl = str;
    }

    public String getTypeIdentifier() {
        return this.typeIdentifier;
    }

    public void setTypeIdentifier(String str) {
        this.typeIdentifier = str;
    }

    public List<DynamicListBean> getDynamicList() {
        return this.dynamicList;
    }

    public void setDynamicList(List<DynamicListBean> list) {
        this.dynamicList = list;
    }

    public List<String> getBusinsessPic() {
        return this.businsessPic;
    }

    public void setBusinsessPic(List<String> list) {
        this.businsessPic = list;
    }

    public List<HuiListBean> getHuiList() {
        return this.huiList;
    }

    public void setHuiList(List<HuiListBean> list) {
        this.huiList = list;
    }

    public List<CouponListBean> getCouponList() {
        return this.couponList;
    }

    public void setCouponList(List<CouponListBean> list) {
        this.couponList = list;
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

        public String getDynamicCode() {
            return this.dynamicCode;
        }

        public void setDynamicCode(String str) {
            this.dynamicCode = str;
        }

        public String getDownLine() {
            return this.downLine;
        }

        public void setDownLine(String str) {
            this.downLine = str;
        }

        public String getOnLine() {
            return this.onLine;
        }

        public void setOnLine(String str) {
            this.onLine = str;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
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
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class HuiListBean {
        private String downLine;
        private String huiCode;
        private String huiImg;
        private String huiText;
        private String onLine;

        public String getHuiImg() {
            return this.huiImg;
        }

        public void setHuiImg(String str) {
            this.huiImg = str;
        }

        public String getDownLine() {
            return this.downLine;
        }

        public void setDownLine(String str) {
            this.downLine = str;
        }

        public String getOnLine() {
            return this.onLine;
        }

        public void setOnLine(String str) {
            this.onLine = str;
        }

        public String getHuiText() {
            return this.huiText;
        }

        public void setHuiText(String str) {
            this.huiText = str;
        }

        public String getHuiCode() {
            return this.huiCode;
        }

        public void setHuiCode(String str) {
            this.huiCode = str;
        }
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
        private String dataDate;
        private String discount;
        private String downLine;
        private String effectiveTime;
        private String eqId;
        private String exchangeUrl;
        private String haveReceivedImg;

        /* renamed from: id */
        private String f18503id;
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

        public String getEffectiveTime() {
            return this.effectiveTime;
        }

        public void setEffectiveTime(String str) {
            this.effectiveTime = str;
        }

        public String getDiscount() {
            return this.discount;
        }

        public void setDiscount(String str) {
            this.discount = str;
        }

        public String getDataDate() {
            return this.dataDate;
        }

        public void setDataDate(String str) {
            this.dataDate = str;
        }

        public String getDownLine() {
            return this.downLine;
        }

        public void setDownLine(String str) {
            this.downLine = str;
        }

        public String getOnLine() {
            return this.onLine;
        }

        public void setOnLine(String str) {
            this.onLine = str;
        }

        public String getCouponNum() {
            return this.couponNum;
        }

        public void setCouponNum(String str) {
            this.couponNum = str;
        }

        public String getIsCommend() {
            return this.isCommend;
        }

        public void setIsCommend(String str) {
            this.isCommend = str;
        }

        public String getCompany() {
            return this.company;
        }

        public void setCompany(String str) {
            this.company = str;
        }

        public String getLostEffectiveTime() {
            return this.lostEffectiveTime;
        }

        public void setLostEffectiveTime(String str) {
            this.lostEffectiveTime = str;
        }

        public String getExchangeUrl() {
            return this.exchangeUrl;
        }

        public void setExchangeUrl(String str) {
            this.exchangeUrl = str;
        }

        public String getCouponListImg() {
            return this.couponListImg;
        }

        public void setCouponListImg(String str) {
            this.couponListImg = str;
        }

        public String getCouponGetImg() {
            return this.couponGetImg;
        }

        public void setCouponGetImg(String str) {
            this.couponGetImg = str;
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

        public String getCouponName() {
            return this.couponName;
        }

        public void setCouponName(String str) {
            this.couponName = str;
        }

        public String getCouponTypeStr() {
            return this.couponTypeStr;
        }

        public void setCouponTypeStr(String str) {
            this.couponTypeStr = str;
        }

        public String getPeopleTypeStr() {
            return this.peopleTypeStr;
        }

        public void setPeopleTypeStr(String str) {
            this.peopleTypeStr = str;
        }

        public String getIsShowStr() {
            return this.isShowStr;
        }

        public void setIsShowStr(String str) {
            this.isShowStr = str;
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

        public String getHaveReceivedImg() {
            return this.haveReceivedImg;
        }

        public void setHaveReceivedImg(String str) {
            this.haveReceivedImg = str;
        }

        public String getLootingImg() {
            return this.lootingImg;
        }

        public void setLootingImg(String str) {
            this.lootingImg = str;
        }

        public String getEqId() {
            return this.eqId;
        }

        public void setEqId(String str) {
            this.eqId = str;
        }

        public String getCouponType() {
            return this.couponType;
        }

        public void setCouponType(String str) {
            this.couponType = str;
        }

        public String getCouponRule() {
            return this.couponRule;
        }

        public void setCouponRule(String str) {
            this.couponRule = str;
        }

        public String getPeopleType() {
            return this.peopleType;
        }

        public void setPeopleType(String str) {
            this.peopleType = str;
        }

        public String getIsShow() {
            return this.isShow;
        }

        public void setIsShow(String str) {
            this.isShow = str;
        }

        public String getCouponImg() {
            return this.couponImg;
        }

        public void setCouponImg(String str) {
            this.couponImg = str;
        }

        public String getId() {
            return this.f18503id;
        }

        public void setId(String str) {
            this.f18503id = str;
        }
    }

    public List<String> getEhallLabelMsgs() {
        return this.ehallLabelMsgs;
    }

    public void setEhallLabelMsgs(List<String> list) {
        this.ehallLabelMsgs = list;
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
