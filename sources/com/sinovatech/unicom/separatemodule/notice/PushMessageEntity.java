package com.sinovatech.unicom.separatemodule.notice;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PushMessageEntity implements Comparable<PushMessageEntity> {
    private String MsgType;
    private String bannerState;
    private String content;
    private String date;
    private String date2;
    private String endTime;
    private String firstLevel;

    /* renamed from: id */
    private String f18590id;
    private boolean isGroup;
    private String msgType;
    private String phone;
    private String saleDialogUrl;
    private String saleImgUrl;
    private String saleMessListUrl;
    private String saleNewTime;
    private String saleUnReadCount;
    private String saleUserMob;
    private String secondLevel;
    private boolean select;
    private String sendDate;
    private String serviceId;
    private String status;
    private String timeTile;
    private String title;
    private String url;
    private String userMobile;

    public PushMessageEntity() {
    }

    public PushMessageEntity(String str) {
        this.date = str;
    }

    public PushMessageEntity(boolean z, boolean z2) {
        this.select = z;
        this.isGroup = z2;
    }

    public String getUserMobile() {
        return this.userMobile;
    }

    public void setUserMobile(String str) {
        this.userMobile = str;
    }

    public String getMsgType() {
        return this.MsgType;
    }

    public void setMsgType(String str) {
        this.MsgType = str;
    }

    public String getId() {
        return this.f18590id;
    }

    public void setId(String str) {
        this.f18590id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean z) {
        this.select = z;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public boolean isGroup() {
        return this.isGroup;
    }

    public void setGroup(boolean z) {
        this.isGroup = z;
    }

    public String getTimeTile() {
        return this.timeTile;
    }

    public void setTimeTile(String str) {
        this.timeTile = str;
    }

    public String getFirstLevel() {
        return this.firstLevel;
    }

    public void setFirstLevel(String str) {
        this.firstLevel = str;
    }

    public String getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(String str) {
        this.sendDate = str;
    }

    public String getDate2() {
        return this.date2;
    }

    public void setDate2(String str) {
        this.date2 = str;
    }

    public String getSecondLevel() {
        return this.secondLevel;
    }

    public void setSecondLevel(String str) {
        this.secondLevel = str;
    }

    public String getSaleUnReadCount() {
        return this.saleUnReadCount;
    }

    public void setSaleUnReadCount(String str) {
        this.saleUnReadCount = str;
    }

    public String getSaleImgUrl() {
        return this.saleImgUrl;
    }

    public void setSaleImgUrl(String str) {
        this.saleImgUrl = str;
    }

    public String getSaleUserMob() {
        return this.saleUserMob;
    }

    public void setSaleUserMob(String str) {
        this.saleUserMob = str;
    }

    public String getSaleDialogUrl() {
        return this.saleDialogUrl;
    }

    public void setSaleDialogUrl(String str) {
        this.saleDialogUrl = str;
    }

    public String getSaleMessListUrl() {
        return this.saleMessListUrl;
    }

    public void setSaleMessListUrl(String str) {
        this.saleMessListUrl = str;
    }

    public String getSaleNewTime() {
        return this.saleNewTime;
    }

    public void setSaleNewTime(String str) {
        this.saleNewTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public String getNewMsgType() {
        return this.msgType;
    }

    public void setNewMsgType(String str) {
        this.msgType = str;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public String getBannerState() {
        return this.bannerState;
    }

    public void setBannerState(String str) {
        this.bannerState = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(PushMessageEntity pushMessageEntity) {
        return pushMessageEntity.getDate().compareTo(this.date);
    }
}
