package com.sinovatech.unicom.separatemodule.audience.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AudienceUser {
    private String avatar;
    private boolean isLogin;
    private boolean isMgr;
    private String level;
    private String nick;

    public boolean isMgr() {
        return this.isMgr;
    }

    public void setMgr(boolean z) {
        this.isMgr = z;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void setLogin(boolean z) {
        this.isLogin = z;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String str) {
        this.nick = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }
}
