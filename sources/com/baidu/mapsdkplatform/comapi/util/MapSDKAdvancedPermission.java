package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.util.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapSDKAdvancedPermission {

    /* renamed from: a */
    private int f7425a;

    /* renamed from: b */
    private Context f7426b;

    private MapSDKAdvancedPermission() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: MapSDKAdvancedPermission.java */
    /* renamed from: com.baidu.mapsdkplatform.comapi.util.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2957a {

        /* renamed from: a */
        private static final MapSDKAdvancedPermission f7427a = new MapSDKAdvancedPermission();
    }

    /* renamed from: a */
    public static MapSDKAdvancedPermission m18161a() {
        return C2957a.f7427a;
    }

    /* renamed from: a */
    public void m18159a(Context context) {
        this.f7426b = context;
    }

    /* renamed from: a */
    public void m18160a(int i) {
        if (i == -1 && (i = m18158a("ad_key")) == -101) {
            return;
        }
        this.f7425a = i;
        m18157a("ad_key", i);
    }

    /* renamed from: a */
    private int m18158a(String str) {
        Context context = this.f7426b;
        if (context == null) {
            return -101;
        }
        return context.getSharedPreferences("ad_auth", 0).getInt(str, 0);
    }

    /* renamed from: a */
    private void m18157a(String str, int i) {
        Context context = this.f7426b;
        if (context == null) {
            return;
        }
        context.getSharedPreferences("ad_auth", 0).edit().putInt(str, i).apply();
    }

    /* renamed from: b */
    public boolean m18156b() {
        int i = this.f7425a;
        return i >= 0 && (i & 1) == 1;
    }

    /* renamed from: c */
    public boolean m18155c() {
        int i = this.f7425a;
        return i >= 0 && (i & 1024) == 1024;
    }
}
