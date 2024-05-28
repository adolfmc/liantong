package com.sinovatech.unicom.separatemodule.live.model;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveMsg {
    private String acType;
    private String avatar;
    private String chatImgUrl;
    private String giftNum;

    /* renamed from: id */
    private String f18546id;
    private String info;
    private boolean isGift;
    private String level;
    private String mType;
    private String msg;
    private String nick;
    private String packageName;

    public String getChatImgUrl() {
        return this.chatImgUrl;
    }

    public void setChatImgUrl(String str) {
        this.chatImgUrl = str;
    }

    public String getAcType() {
        return this.acType;
    }

    public void setAcType(String str) {
        this.acType = str;
    }

    public String getGiftNum() {
        return this.giftNum;
    }

    public void setGiftNum(String str) {
        this.giftNum = str;
    }

    public boolean isGift() {
        return this.isGift;
    }

    public void setGift(boolean z) {
        this.isGift = z;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String str) {
        this.nick = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getmType() {
        return this.mType;
    }

    public void setmType(String str) {
        this.mType = str;
    }

    public String getId() {
        return this.f18546id;
    }

    public void setId(String str) {
        this.f18546id = str;
    }

    public String toString() {
        return "LiveMsg{level='" + this.level + "', nick='" + this.nick + "', msg='" + this.msg + "', mType='" + this.mType + "', id='" + this.f18546id + "', info='" + this.info + "', avatar='" + this.avatar + "', packageName='" + this.packageName + "', isGift=" + this.isGift + ", giftNum='" + this.giftNum + "', acType=" + this.acType + '}';
    }
}
