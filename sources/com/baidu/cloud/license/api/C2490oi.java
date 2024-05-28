package com.baidu.cloud.license.api;

import android.text.TextUtils;
import java.util.ArrayList;
import retrofit2.Call;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ApiManager.java */
/* renamed from: com.baidu.cloud.license.api.oi */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2490oi {

    /* renamed from: a */
    public static ArrayList<String> f4354a = new ArrayList<>();

    /* renamed from: b */
    private static volatile C2490oi f4355b;

    /* renamed from: c */
    private C2489nx f4356c;

    private C2490oi() {
    }

    /* renamed from: a */
    public static C2490oi m20085a() {
        if (f4355b == null) {
            synchronized (C2490oi.class) {
                if (f4355b == null) {
                    f4355b = new C2490oi();
                }
            }
        }
        return f4355b;
    }

    /* renamed from: a */
    public final void m20084a(String str, Call call) {
        if (this.f4356c == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (str.contains("$")) {
            str = str.substring(0, str.indexOf("$"));
        }
        C2489nx.m20086a(str, call);
    }
}
