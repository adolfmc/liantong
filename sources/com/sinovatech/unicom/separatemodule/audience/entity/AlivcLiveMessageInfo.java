package com.sinovatech.unicom.separatemodule.audience.entity;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AlivcLiveMessageInfo implements Serializable {
    private int acType;
    private String avatar;
    private String dataContent;
    private String giftCode;
    private String giftNum;
    private boolean isKickout = false;
    private boolean isMgr;
    private String level;
    private String sendName;
    private int type;
    private String userId;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum AlivcMsgType {
        ALIVC_MESSAGE_TYPE_LOGIN(0),
        ALIVC_MESSAGE_TYPE_ALLOWSENDMSG(1),
        ALIVC_MESSAGE_TYPE_FORBIDSENDMSG(2),
        ALIVC_MESSAGE_TYPE_ALLOWALLSENDMSG(3),
        ALIVC_MESSAGE_TYPE_FORBIDALLSENDMSG(4),
        ALIVC_MESSAGE_TYPE_KICKOUT(5),
        ALIVC_MESSAGE_TYPE_CHAT(6),
        ALIVC_MESSAGE_TYPE_SENDGIFT(7),
        ALIVC_MESSAGE_TYPE_LIKE(8),
        ALIVC_MESSAGE_TYPE_LOGOUT_ROOM(9),
        ALIVC_MESSAGE_UP_MIC(10),
        ALIVC_MESSAGE_ROOM_DESC(12),
        ALIVC_MESSAGE_EXPLAIN_GOODS(11);
        
        private int msgType;

        AlivcMsgType(int i) {
            this.msgType = i;
        }

        public int getMsgType() {
            return this.msgType;
        }
    }

    public boolean isMgr() {
        return this.isMgr;
    }

    public void setMgr(boolean z) {
        this.isMgr = z;
    }

    public boolean isKickout() {
        return this.isKickout;
    }

    public void setKickout(boolean z) {
        this.isKickout = z;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getSendName() {
        return this.sendName;
    }

    public void setSendName(String str) {
        this.sendName = str;
    }

    public String getDataContent() {
        return this.dataContent;
    }

    public void setDataContent(String str) {
        this.dataContent = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getGiftNum() {
        return this.giftNum;
    }

    public void setGiftNum(String str) {
        this.giftNum = str;
    }

    public String getGiftCode() {
        return this.giftCode;
    }

    public void setGiftCode(String str) {
        this.giftCode = str;
    }

    public int getAcType() {
        return this.acType;
    }

    public void setAcType(int i) {
        this.acType = i;
    }
}
