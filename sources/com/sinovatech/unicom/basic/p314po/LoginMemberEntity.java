package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.po.LoginMemberEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LoginMemberEntity {
    private String currentNum;
    private String encryption;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18386id;
    private String mainFlag;
    private String num;
    private String origin;
    private String type;
    private String typeName;

    public long getId() {
        return this.f18386id;
    }

    public void setId(long j) {
        this.f18386id = j;
    }

    public LoginMemberEntity() {
    }

    public LoginMemberEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mainFlag = str;
        this.encryption = str2;
        this.num = str3;
        this.type = str4;
        this.currentNum = str5;
        this.typeName = str6;
        this.origin = str7;
    }

    public String getMainFlag() {
        return this.mainFlag;
    }

    public void setMainFlag(String str) {
        this.mainFlag = str;
    }

    public String getEncryption() {
        return this.encryption;
    }

    public void setEncryption(String str) {
        this.encryption = str;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getCurrentNum() {
        return this.currentNum;
    }

    public void setCurrentNum(String str) {
        this.currentNum = str;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }
}
