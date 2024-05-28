package com.xiaomi.push;

import com.xiaomi.push.C11213bw;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.bs */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11209bs extends C11213bw.C11219d {

    /* renamed from: a */
    protected String f21636a;

    public C11209bs(String str, String str2, String[] strArr, String str3) {
        super(str, str2, strArr);
        this.f21636a = "MessageDeleteJob";
        this.f21636a = str3;
    }

    /* renamed from: a */
    public static C11209bs m4688a(String str) {
        return new C11209bs(str, "status = ?", new String[]{String.valueOf(2)}, "a job build to delete uploaded job");
    }
}
