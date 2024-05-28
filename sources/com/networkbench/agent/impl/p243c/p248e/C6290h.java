package com.networkbench.agent.impl.p243c.p248e;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6290h {

    /* renamed from: a */
    public String f15729a = "";

    /* renamed from: b */
    public String f15730b = "";

    /* renamed from: c */
    public String f15731c = "";

    /* renamed from: a */
    public void m10593a(String str, String str2) {
        this.f15730b = str;
        this.f15731c = str2;
        if (m10591b(str, str2)) {
            this.f15729a = str;
        }
    }

    /* renamed from: a */
    public boolean m10594a() {
        return m10591b(this.f15730b, this.f15731c) || TextUtils.isEmpty(this.f15729a);
    }

    /* renamed from: b */
    private boolean m10591b(String str, String str2) {
        return !TextUtils.isEmpty(str) && str.equals(str2);
    }

    /* renamed from: b */
    public void m10592b() {
        this.f15730b = "";
        this.f15731c = "";
        this.f15729a = "";
    }
}
