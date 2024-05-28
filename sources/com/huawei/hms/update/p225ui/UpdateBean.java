package com.huawei.hms.update.p225ui;

import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.huawei.hms.update.ui.UpdateBean */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UpdateBean implements Serializable {

    /* renamed from: a */
    private boolean f11786a;

    /* renamed from: b */
    private boolean f11787b;

    /* renamed from: c */
    private String f11788c;

    /* renamed from: d */
    private int f11789d;

    /* renamed from: e */
    private String f11790e;

    /* renamed from: f */
    private String f11791f;

    /* renamed from: g */
    private ArrayList f11792g;

    /* renamed from: h */
    private boolean f11793h = true;

    /* renamed from: a */
    private static <T> T m14099a(T t) {
        return t;
    }

    public String getClientAppId() {
        return (String) m14099a(this.f11790e);
    }

    public String getClientAppName() {
        return (String) m14099a(this.f11791f);
    }

    public String getClientPackageName() {
        return (String) m14099a(this.f11788c);
    }

    public int getClientVersionCode() {
        return ((Integer) m14099a(Integer.valueOf(this.f11789d))).intValue();
    }

    public boolean getResolutionInstallHMS() {
        return this.f11787b;
    }

    public ArrayList getTypeList() {
        return (ArrayList) m14099a(this.f11792g);
    }

    public boolean isHmsOrApkUpgrade() {
        return ((Boolean) m14099a(Boolean.valueOf(this.f11786a))).booleanValue();
    }

    public boolean isNeedConfirm() {
        return ((Boolean) m14099a(Boolean.valueOf(this.f11793h))).booleanValue();
    }

    public void setClientAppId(String str) {
        this.f11790e = str;
    }

    public void setClientAppName(String str) {
        this.f11791f = str;
    }

    public void setClientPackageName(String str) {
        this.f11788c = str;
    }

    public void setClientVersionCode(int i) {
        this.f11789d = i;
    }

    public void setHmsOrApkUpgrade(boolean z) {
        this.f11786a = z;
    }

    public void setNeedConfirm(boolean z) {
        this.f11793h = z;
    }

    public void setResolutionInstallHMS(boolean z) {
        this.f11787b = z;
    }

    public void setTypeList(ArrayList arrayList) {
        this.f11792g = arrayList;
    }
}
