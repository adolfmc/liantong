package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.xiaomi.push.C11649x;

/* renamed from: com.xiaomi.push.service.ap */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11559ap {

    /* renamed from: a */
    private static C11559ap f23603a;

    /* renamed from: a */
    private int f23604a = 0;

    /* renamed from: a */
    private Context f23605a;

    /* renamed from: a */
    public static C11559ap m2653a(Context context) {
        if (f23603a == null) {
            f23603a = new C11559ap(context);
        }
        return f23603a;
    }

    private C11559ap(Context context) {
        this.f23605a = context.getApplicationContext();
    }

    /* renamed from: a */
    public boolean m2654a() {
        return C11649x.f23811a.contains("xmsf") || C11649x.f23811a.contains("xiaomi") || C11649x.f23811a.contains("miui");
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public int m2656a() {
        int i = this.f23604a;
        if (i != 0) {
            return i;
        }
        try {
            this.f23604a = Settings.Global.getInt(this.f23605a.getContentResolver(), "device_provisioned", 0);
        } catch (Exception unused) {
        }
        return this.f23604a;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public Uri m2655a() {
        return Settings.Global.getUriFor("device_provisioned");
    }
}
