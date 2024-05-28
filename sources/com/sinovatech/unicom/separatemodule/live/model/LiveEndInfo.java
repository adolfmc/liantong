package com.sinovatech.unicom.separatemodule.live.model;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LiveEndInfo implements Serializable {
    private String comment;
    private String gifts;
    private String gkrs;
    private String gzrs;
    private String liverId;
    private String time;
    private String userImg;
    private String userName;

    public String getGifts() {
        return this.gifts;
    }

    public void setGifts(String str) {
        this.gifts = str;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public String getLiverId() {
        return this.liverId;
    }

    public void setLiverId(String str) {
        this.liverId = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getUserImg() {
        return this.userImg;
    }

    public void setUserImg(String str) {
        this.userImg = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getGkrs() {
        return this.gkrs;
    }

    public void setGkrs(String str) {
        this.gkrs = str;
    }

    public String getGzrs() {
        return this.gzrs;
    }

    public void setGzrs(String str) {
        this.gzrs = str;
    }
}
