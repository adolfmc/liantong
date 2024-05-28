package com.sinovatech.unicom.separatemodule.live.model;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RtcEvent {
    private String anchorName;
    private String avatar;
    private int code;
    private String keepMsg;
    private String roomName;
    private String sourceJobNum;
    private String targetJobNumber;

    public String getSourceJobNum() {
        return this.sourceJobNum;
    }

    public void setSourceJobNum(String str) {
        this.sourceJobNum = str;
    }

    public String getTargetJobNumber() {
        return this.targetJobNumber;
    }

    public void setTargetJobNumber(String str) {
        this.targetJobNumber = str;
    }

    public String getAnchorName() {
        return this.anchorName;
    }

    public void setAnchorName(String str) {
        this.anchorName = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getKeepMsg() {
        return this.keepMsg;
    }

    public void setKeepMsg(String str) {
        this.keepMsg = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String str) {
        this.roomName = str;
    }

    public String toString() {
        return "RtcEvent{code=" + this.code + ", roomName='" + this.roomName + "', anchorName='" + this.anchorName + "', avatar='" + this.avatar + "', sourceJobNum='" + this.sourceJobNum + "', targetJobNumber='" + this.targetJobNumber + "', keepMsg='" + this.keepMsg + "'}";
    }
}
