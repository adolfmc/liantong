package com.sdk.base.framework.bean;

import com.sdk.p295k.C7009a;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PInfo {

    /* renamed from: c */
    private String f18073c;
    private ArrayList<String> imei;
    private String mac;

    /* renamed from: n */
    private String f18074n;

    /* renamed from: os */
    private String f18075os;

    public String getC() {
        return this.f18073c;
    }

    public ArrayList<String> getImei() {
        return this.imei;
    }

    public String getMac() {
        return this.mac;
    }

    public String getN() {
        return this.f18074n;
    }

    public String getOs() {
        return this.f18075os;
    }

    public void setC(String str) {
        this.f18073c = str;
    }

    public void setImei(ArrayList<String> arrayList) {
        this.imei = arrayList;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setN(String str) {
        this.f18074n = str;
    }

    public void setOs(String str) {
        this.f18075os = str;
    }

    public String toString() {
        return C7009a.m8153a(this);
    }
}
