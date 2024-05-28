package com.sinovatech.unicom.separatemodule.user.entity;

import android.text.TextUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserNewTopentity {
    private String broadActId;
    private String broadActType;
    private String broadBuriedPointData;
    private String broadBusinessType;
    private String broadGoodsId;
    private String broadGoodsUrl;
    private String broadHostTitle;
    private String broadId;
    private String broadImgScr;
    private String broadManrongActivity;
    private String broadManrongType;
    private String broadPosition;
    private String broadTitle;
    private String broadToucheID;
    private String broadToucheTemplate;
    private String broadUnit;
    private String broadUrl;
    private String broadbandNum;
    private String cardActId;
    private String cardActType;
    private String cardBuriedPointData;
    private String cardBusinessType;
    private boolean cardDataIsSuccess;
    private String cardDataType;
    private String cardFloorBackgroundImgSrc;
    private String cardFloorButtonImgSrc;
    private String cardFloorButtonUrl;
    private String cardFloorDataType;
    private String cardFloorGoodsId;
    private String cardFloorGoodsUrlName;
    private String cardFloorLevelImgSrc;
    private String cardFloorMaterialId;
    private String cardFloorTemplateType;
    private String cardFloorTitle;
    private String cardFloorToucheID;
    private String cardFloorToucheTemplate;
    private String cardFloorType;
    private String cardFloorViceTitle;
    private String cardFloorViceTitle2;
    private String cardGoodsId;
    private String cardGoodsUrl;
    private String cardHostTitle;
    private String cardId;
    private String cardImgSrc;
    private String cardManrongActivity;
    private String cardManrongType;
    private String cardNum;
    private String cardPosition;
    private String cardTitle;
    private String cardToucheID;
    private String cardToucheTemplate;
    private String cardUnit;
    private String cardUrl;
    private String channelCode;
    private String cloudUsedContent1;
    private String cloudUsedContent2;
    private String cloudUsedData;
    private String cloudUsedImgSrc;
    private String cloudUsedTitle;
    private String cloudUsedUrl;
    private String code;
    private String contractData;
    private String contractImgSrc;
    private String contractTitle;
    private String contractUrl;
    private String currentPhoneNumber;
    private String doneBroadbandNum;
    private String doneCardNum;
    private String doneDataType;
    private String floorActId;
    private String floorActType;
    private String floorAlgorithmType;
    private String floorBatchId;
    private String floorBuriedPointData;
    private String floorBusinessType;
    private String floorDataType;
    private String floorGoodsId;
    private String floorGoodsUrl;
    private String floorId;
    private String floorManrongActivity;
    private String floorManrongType;
    private String floorMaterialId;
    private String floorPosition;
    private String floorTemplateType;
    private String floorTraceId;
    private String headTitle;
    private Boolean isOpenSet;
    private String myPackageConfigurationContext;
    private String myPackageConfigurationTitle;
    private String packageContext;
    private String packageTitle;
    private String packageUrl;
    private boolean userInfoDataIsSuccess;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public Boolean getIsOpenSet() {
        return this.isOpenSet;
    }

    public void setIsOpenSet(Boolean bool) {
        this.isOpenSet = bool;
    }

    public String getHeadTitle() {
        return this.headTitle;
    }

    public void setHeadTitle(String str) {
        this.headTitle = str;
    }

    public String getPackageTitle() {
        if (TextUtils.isEmpty(this.packageTitle)) {
            this.packageTitle = "我的套餐";
        }
        return this.packageTitle;
    }

    public void setPackageTitle(String str) {
        this.packageTitle = str;
    }

    public String getPackageContext() {
        if (TextUtils.isEmpty(this.packageContext)) {
            this.packageContext = "--";
        }
        return this.packageContext;
    }

    public void setPackageContext(String str) {
        this.packageContext = str;
    }

    public String getCloudUsedTitle() {
        if (TextUtils.isEmpty(this.cloudUsedTitle)) {
            this.cloudUsedTitle = "云盘";
        }
        return this.cloudUsedTitle;
    }

    public void setCloudUsedTitle(String str) {
        this.cloudUsedTitle = str;
    }

    public String getCloudUsedData() {
        if (TextUtils.isEmpty(this.cloudUsedData)) {
            this.cloudUsedData = "-";
        }
        return this.cloudUsedData;
    }

    public void setCloudUsedData(String str) {
        this.cloudUsedData = str;
    }

    public String getContractTitle() {
        if (TextUtils.isEmpty(this.contractTitle)) {
            this.contractTitle = "合约";
        }
        return this.contractTitle;
    }

    public void setContractTitle(String str) {
        this.contractTitle = str;
    }

    public String getContractData() {
        if (TextUtils.isEmpty(this.contractData)) {
            this.contractData = "-";
        }
        return this.contractData;
    }

    public void setContractData(String str) {
        this.contractData = str;
    }

    public String getContractUrl() {
        return this.contractUrl;
    }

    public void setContractUrl(String str) {
        this.contractUrl = str;
    }

    public String getCardTitle() {
        if (TextUtils.isEmpty(this.cardTitle)) {
            this.cardTitle = "副卡";
        }
        return this.cardTitle;
    }

    public void setCardTitle(String str) {
        this.cardTitle = str;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String str) {
        this.cardNum = str;
    }

    public String getDoneCardNum() {
        return this.doneCardNum;
    }

    public void setDoneCardNum(String str) {
        this.doneCardNum = str;
    }

    public String getCardUnit() {
        return this.cardUnit;
    }

    public void setCardUnit(String str) {
        this.cardUnit = str;
    }

    public String getCardDataType() {
        return this.cardDataType;
    }

    public void setCardDataType(String str) {
        this.cardDataType = str;
    }

    public String getBroadTitle() {
        if (TextUtils.isEmpty(this.broadTitle)) {
            this.broadTitle = "宽带";
        }
        return this.broadTitle;
    }

    public void setBroadTitle(String str) {
        this.broadTitle = str;
    }

    public String getBroadbandNum() {
        return this.broadbandNum;
    }

    public void setBroadbandNum(String str) {
        this.broadbandNum = str;
    }

    public String getDoneBroadbandNum() {
        return this.doneBroadbandNum;
    }

    public void setDoneBroadbandNum(String str) {
        this.doneBroadbandNum = str;
    }

    public String getDoneDataType() {
        return this.doneDataType;
    }

    public void setBroadDataType(String str) {
        this.doneDataType = str;
    }

    public String getCardImgSrc() {
        return this.cardImgSrc;
    }

    public void setCardImgSrc(String str) {
        this.cardImgSrc = str;
    }

    public String getBroadUnit() {
        return this.broadUnit;
    }

    public void setBroadUnit(String str) {
        this.broadUnit = str;
    }

    public String getCardUrl() {
        return this.cardUrl;
    }

    public void setCardUrl(String str) {
        this.cardUrl = str;
    }

    public String getBroadImgScr() {
        return this.broadImgScr;
    }

    public void setBroadImgScr(String str) {
        this.broadImgScr = str;
    }

    public String getBroadUrl() {
        return this.broadUrl;
    }

    public void setBroadUrl(String str) {
        this.broadUrl = str;
    }

    public String getCardFloorTitle() {
        return this.cardFloorTitle;
    }

    public void setCardFloorTitle(String str) {
        this.cardFloorTitle = str;
    }

    public String getCardFloorViceTitle() {
        return this.cardFloorViceTitle;
    }

    public void setCardFloorViceTitle(String str) {
        this.cardFloorViceTitle = str;
    }

    public String getCardFloorViceTitle2() {
        return this.cardFloorViceTitle2;
    }

    public void setCardFloorViceTitle2(String str) {
        this.cardFloorViceTitle2 = str;
    }

    public String getCardFloorBackgroundImgSrc() {
        return this.cardFloorBackgroundImgSrc;
    }

    public void setCardFloorBackgroundImgSrc(String str) {
        this.cardFloorBackgroundImgSrc = str;
    }

    public String getCardFloorLevelImgSrc() {
        return this.cardFloorLevelImgSrc;
    }

    public void setCardFloorLevelImgSrc(String str) {
        this.cardFloorLevelImgSrc = str;
    }

    public String getCardFloorButtonImgSrc() {
        return this.cardFloorButtonImgSrc;
    }

    public void setCardFloorButtonImgSrc(String str) {
        this.cardFloorButtonImgSrc = str;
    }

    public String getCardFloorButtonUrl() {
        return this.cardFloorButtonUrl;
    }

    public void setCardFloorButtonUrl(String str) {
        this.cardFloorButtonUrl = str;
    }

    public String getPackageUrl() {
        return this.packageUrl;
    }

    public void setPackageUrl(String str) {
        this.packageUrl = str;
    }

    public String getCloudUsedUrl() {
        return this.cloudUsedUrl;
    }

    public void setCloudUsedUrl(String str) {
        this.cloudUsedUrl = str;
    }

    public String getCardHostTitle() {
        return this.cardHostTitle;
    }

    public void setCardHostTitle(String str) {
        this.cardHostTitle = str;
    }

    public String getBroadHostTitle() {
        return this.broadHostTitle;
    }

    public void setBroadHostTitle(String str) {
        this.broadHostTitle = str;
    }

    public Boolean getOpenSet() {
        return this.isOpenSet;
    }

    public void setOpenSet(Boolean bool) {
        this.isOpenSet = bool;
    }

    public boolean isUserInfoDataIsSuccess() {
        return this.userInfoDataIsSuccess;
    }

    public void setUserInfoDataIsSuccess(boolean z) {
        this.userInfoDataIsSuccess = z;
    }

    public String getCloudUsedImgSrc() {
        return this.cloudUsedImgSrc;
    }

    public void setCloudUsedImgSrc(String str) {
        this.cloudUsedImgSrc = str;
    }

    public String getContractImgSrc() {
        return this.contractImgSrc;
    }

    public void setContractImgSrc(String str) {
        this.contractImgSrc = str;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public void setChannelCode(String str) {
        this.channelCode = str;
    }

    public String getCardToucheID() {
        return this.cardToucheID;
    }

    public void setCardToucheID(String str) {
        this.cardToucheID = str;
    }

    public String getCardToucheTemplate() {
        return this.cardToucheTemplate;
    }

    public void setCardToucheTemplate(String str) {
        this.cardToucheTemplate = str;
    }

    public String getBroadToucheID() {
        return this.broadToucheID;
    }

    public void setBroadToucheID(String str) {
        this.broadToucheID = str;
    }

    public String getBroadToucheTemplate() {
        return this.broadToucheTemplate;
    }

    public void setBroadToucheTemplate(String str) {
        this.broadToucheTemplate = str;
    }

    public String getCardFloorToucheID() {
        return this.cardFloorToucheID;
    }

    public void setCardFloorToucheID(String str) {
        this.cardFloorToucheID = str;
    }

    public String getCardFloorToucheTemplate() {
        return this.cardFloorToucheTemplate;
    }

    public void setCardFloorToucheTemplate(String str) {
        this.cardFloorToucheTemplate = str;
    }

    public String getMyPackageConfigurationContext() {
        return this.myPackageConfigurationContext;
    }

    public void setMyPackageConfigurationContext(String str) {
        this.myPackageConfigurationContext = str;
    }

    public String getMyPackageConfigurationTitle() {
        return this.myPackageConfigurationTitle;
    }

    public void setMyPackageConfigurationTitle(String str) {
        this.myPackageConfigurationTitle = str;
    }

    public String getCloudUsedContent1() {
        return this.cloudUsedContent1;
    }

    public void setCloudUsedContent1(String str) {
        this.cloudUsedContent1 = str;
    }

    public String getCloudUsedContent2() {
        return this.cloudUsedContent2;
    }

    public void setCloudUsedContent2(String str) {
        this.cloudUsedContent2 = str;
    }

    public String getCardActType() {
        return this.cardActType;
    }

    public void setCardActType(String str) {
        this.cardActType = str;
    }

    public String getCardActId() {
        return this.cardActId;
    }

    public void setCardActId(String str) {
        this.cardActId = str;
    }

    public String getCardGoodsId() {
        return this.cardGoodsId;
    }

    public void setCardGoodsId(String str) {
        this.cardGoodsId = str;
    }

    public String getCardBusinessType() {
        return this.cardBusinessType;
    }

    public void setCardBusinessType(String str) {
        this.cardBusinessType = str;
    }

    public String getCardGoodsUrl() {
        return this.cardGoodsUrl;
    }

    public void setCardGoodsUrl(String str) {
        this.cardGoodsUrl = str;
    }

    public String getCardManrongActivity() {
        return this.cardManrongActivity;
    }

    public void setCardManrongActivity(String str) {
        this.cardManrongActivity = str;
    }

    public String getCardManrongType() {
        return this.cardManrongType;
    }

    public void setCardManrongType(String str) {
        this.cardManrongType = str;
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setCardId(String str) {
        this.cardId = str;
    }

    public String getCardBuriedPointData() {
        return this.cardBuriedPointData;
    }

    public void setCardBuriedPointData(String str) {
        this.cardBuriedPointData = str;
    }

    public String getCardPosition() {
        return this.cardPosition;
    }

    public void setCardPosition(String str) {
        this.cardPosition = str;
    }

    public String getBroadActType() {
        return this.broadActType;
    }

    public void setBroadActType(String str) {
        this.broadActType = str;
    }

    public String getBroadActId() {
        return this.broadActId;
    }

    public void setBroadActId(String str) {
        this.broadActId = str;
    }

    public String getBroadGoodsId() {
        return this.broadGoodsId;
    }

    public void setBroadGoodsId(String str) {
        this.broadGoodsId = str;
    }

    public String getBroadBusinessType() {
        return this.broadBusinessType;
    }

    public void setBroadBusinessType(String str) {
        this.broadBusinessType = str;
    }

    public String getBroadGoodsUrl() {
        return this.broadGoodsUrl;
    }

    public void setBroadGoodsUrl(String str) {
        this.broadGoodsUrl = str;
    }

    public String getBroadManrongActivity() {
        return this.broadManrongActivity;
    }

    public void setBroadManrongActivity(String str) {
        this.broadManrongActivity = str;
    }

    public String getBroadManrongType() {
        return this.broadManrongType;
    }

    public void setBroadManrongType(String str) {
        this.broadManrongType = str;
    }

    public String getBroadId() {
        return this.broadId;
    }

    public void setBroadId(String str) {
        this.broadId = str;
    }

    public String getBroadBuriedPointData() {
        return this.broadBuriedPointData;
    }

    public void setBroadBuriedPointData(String str) {
        this.broadBuriedPointData = str;
    }

    public String getBroadPosition() {
        return this.broadPosition;
    }

    public void setBroadPosition(String str) {
        this.broadPosition = str;
    }

    public void setDoneDataType(String str) {
        this.doneDataType = str;
    }

    public String getFloorActType() {
        return this.floorActType;
    }

    public void setFloorActType(String str) {
        this.floorActType = str;
    }

    public String getFloorActId() {
        return this.floorActId;
    }

    public void setFloorActId(String str) {
        this.floorActId = str;
    }

    public String getFloorGoodsId() {
        return this.floorGoodsId;
    }

    public void setFloorGoodsId(String str) {
        this.floorGoodsId = str;
    }

    public String getFloorBusinessType() {
        return this.floorBusinessType;
    }

    public void setFloorBusinessType(String str) {
        this.floorBusinessType = str;
    }

    public String getFloorGoodsUrl() {
        return this.floorGoodsUrl;
    }

    public void setFloorGoodsUrl(String str) {
        this.floorGoodsUrl = str;
    }

    public String getFloorManrongActivity() {
        return this.floorManrongActivity;
    }

    public void setFloorManrongActivity(String str) {
        this.floorManrongActivity = str;
    }

    public String getFloorManrongType() {
        return this.floorManrongType;
    }

    public void setFloorManrongType(String str) {
        this.floorManrongType = str;
    }

    public String getFloorId() {
        return this.floorId;
    }

    public void setFloorId(String str) {
        this.floorId = str;
    }

    public String getFloorBuriedPointData() {
        return this.floorBuriedPointData;
    }

    public void setFloorBuriedPointData(String str) {
        this.floorBuriedPointData = str;
    }

    public String getFloorPosition() {
        return this.floorPosition;
    }

    public void setFloorPosition(String str) {
        this.floorPosition = str;
    }

    public String getCardFloorMaterialId() {
        return this.cardFloorMaterialId;
    }

    public void setCardFloorMaterialId(String str) {
        this.cardFloorMaterialId = str;
    }

    public String getCardFloorGoodsId() {
        return this.cardFloorGoodsId;
    }

    public void setCardFloorGoodsId(String str) {
        this.cardFloorGoodsId = str;
    }

    public String getCardFloorGoodsUrlName() {
        return this.cardFloorGoodsUrlName;
    }

    public void setCardFloorGoodsUrlName(String str) {
        this.cardFloorGoodsUrlName = str;
    }

    public String getCardFloorDataType() {
        return this.cardFloorDataType;
    }

    public void setCardFloorDataType(String str) {
        this.cardFloorDataType = str;
    }

    public String getCardFloorTemplateType() {
        return this.cardFloorTemplateType;
    }

    public void setCardFloorTemplateType(String str) {
        this.cardFloorTemplateType = str;
    }

    public String getCardFloorType() {
        return this.cardFloorType;
    }

    public void setCardFloorType(String str) {
        this.cardFloorType = str;
    }

    public String getCurrentPhoneNumber() {
        return this.currentPhoneNumber;
    }

    public void setCurrentPhoneNumber(String str) {
        this.currentPhoneNumber = str;
    }

    public String getFloorDataType() {
        return this.floorDataType;
    }

    public void setFloorDataType(String str) {
        this.floorDataType = str;
    }

    public String getFloorMaterialId() {
        return this.floorMaterialId;
    }

    public void setFloorMaterialId(String str) {
        this.floorMaterialId = str;
    }

    public String getFloorTemplateType() {
        return this.floorTemplateType;
    }

    public void setFloorTemplateType(String str) {
        this.floorTemplateType = str;
    }

    public String getFloorTraceId() {
        return this.floorTraceId;
    }

    public void setFloorTraceId(String str) {
        this.floorTraceId = str;
    }

    public String getFloorBatchId() {
        return this.floorBatchId;
    }

    public void setFloorBatchId(String str) {
        this.floorBatchId = str;
    }

    public String getFloorAlgorithmType() {
        return this.floorAlgorithmType;
    }

    public void setFloorAlgorithmType(String str) {
        this.floorAlgorithmType = str;
    }

    public String toString() {
        return "UserNewTopentity{code='" + this.code + "', isOpenSet=" + this.isOpenSet + ", headTitle='" + this.headTitle + "', packageTitle='" + this.packageTitle + "', packageContext='" + this.packageContext + "', contextTitle='" + this.cloudUsedTitle + "', contextData='" + this.cloudUsedData + "', contractTitle='" + this.contractTitle + "', contractData='" + this.contractData + "', contractUrl='" + this.contractUrl + "', cardTitle='" + this.cardTitle + "', cardNum='" + this.cardNum + "', doneCardNum='" + this.doneCardNum + "', cardUnit='" + this.cardUnit + "', cardDataType='" + this.cardDataType + "', broadTitle='" + this.broadTitle + "', broadbandNum='" + this.broadbandNum + "', doneBroadbandNum='" + this.doneBroadbandNum + "', doneDataType='" + this.doneDataType + "', cardImgSrc='" + this.cardImgSrc + "', broadUnit='" + this.broadUnit + "', cardUrl='" + this.cardUrl + "', broadImgScr='" + this.broadImgScr + "', broadUrl='" + this.broadUrl + "', cardFloorTitle='" + this.cardFloorTitle + "', cardFloorViceTitle='" + this.cardFloorViceTitle + "', cardFloorViceTitle2='" + this.cardFloorViceTitle2 + "', cardFloorBackgroundImgSrc='" + this.cardFloorBackgroundImgSrc + "', cardFloorLevelImgSrc='" + this.cardFloorLevelImgSrc + "', cardFloorButtonImgSrc='" + this.cardFloorButtonImgSrc + "', cardFloorButtonUrl='" + this.cardFloorButtonUrl + "', packageUrl='" + this.packageUrl + "', contextUrl='" + this.cloudUsedUrl + "'}";
    }
}
