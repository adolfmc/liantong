package com.bytedance.android.live.base.api.push.model;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PushData {
    String anchorOpenId;
    String avatar;
    String infoText;
    String logPb;
    String nickName;
    public String openRoomId;
    String requestId;
    Long roomId;

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getInfoText() {
        return this.infoText;
    }

    public void setInfoText(String str) {
        this.infoText = str;
    }

    public String getAnchorOpenId() {
        return this.anchorOpenId;
    }

    public void setAnchorOpenId(String str) {
        this.anchorOpenId = str;
    }

    public Long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(Long l) {
        this.roomId = l;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String getLogPb() {
        return this.logPb;
    }

    public void setLogPb(String str) {
        this.logPb = str;
    }
}
