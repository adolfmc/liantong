package com.sinovatech.unicom.basic.p314po;

import android.text.TextUtils;

/* renamed from: com.sinovatech.unicom.basic.po.LoginAccountEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginAccountEntity {
    private String accountname;
    private String accountnameJiami;
    private String accouttype;
    private BindNumberActivityEntity activityEntity = new BindNumberActivityEntity();
    private String areaid;
    private String cid;
    private String cidJiami;
    private String iconurl;
    private String intact;
    private String keyversion;
    private String password;

    public LoginAccountEntity(String str) {
        this.accountname = str;
    }

    public LoginAccountEntity(String str, String str2, String str3) {
        this.accountname = str;
        this.areaid = str2;
        this.accouttype = str3;
    }

    public LoginAccountEntity(String str, String str2, String str3, String str4, String str5) {
        this.accountname = str;
        this.areaid = str2;
        this.accouttype = str3;
        this.password = str4;
        this.keyversion = str5;
    }

    public LoginAccountEntity(String str, String str2, String str3, String str4, String str5, String str6) {
        this.accountname = str;
        this.areaid = str2;
        this.accouttype = str3;
        this.password = str4;
        this.keyversion = str5;
        this.iconurl = str6;
    }

    public LoginAccountEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.accountname = str;
        this.areaid = str2;
        this.accouttype = str3;
        this.password = str4;
        this.keyversion = str5;
        this.iconurl = str6;
        this.cid = str7;
    }

    public String getAccountname() {
        return this.accountname;
    }

    public void setAccountname(String str) {
        this.accountname = str;
    }

    public String getAreaid() {
        return TextUtils.isEmpty(this.areaid) ? "" : this.areaid;
    }

    public void setAreaid(String str) {
        this.areaid = str;
    }

    public String getAccouttype() {
        return this.accouttype;
    }

    public void setAccouttype(String str) {
        this.accouttype = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getKeyversion() {
        return this.keyversion;
    }

    public void setKeyversion(String str) {
        this.keyversion = str;
    }

    public String getIconurl() {
        return this.iconurl;
    }

    public void setIconurl(String str) {
        this.iconurl = str;
    }

    public String getAccountnameJiami() {
        return this.accountnameJiami;
    }

    public void setAccountnameJiami(String str) {
        this.accountnameJiami = str;
    }

    public String getIntact() {
        return this.intact;
    }

    public void setIntact(String str) {
        this.intact = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getCidJiami() {
        return this.cidJiami;
    }

    public void setCidJiami(String str) {
        this.cidJiami = str;
    }

    public BindNumberActivityEntity getActivityEntity() {
        BindNumberActivityEntity bindNumberActivityEntity = this.activityEntity;
        return bindNumberActivityEntity == null ? new BindNumberActivityEntity() : bindNumberActivityEntity;
    }

    public void setActivityEntity(BindNumberActivityEntity bindNumberActivityEntity) {
        this.activityEntity = bindNumberActivityEntity;
    }
}
