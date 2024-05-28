package com.megvii.kas.livenessdetection.obf;

import android.content.Context;
import android.content.SharedPreferences;
import java.security.InvalidParameterException;

/* renamed from: com.megvii.kas.livenessdetection.obf.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5331e {

    /* renamed from: a */
    private String f12350a;

    /* renamed from: b */
    private SharedPreferences f12351b;

    private C5331e(Context context, String str, String str2) {
        this.f12350a = "";
        if (context == null) {
            throw new InvalidParameterException();
        }
        this.f12350a = str2;
        this.f12351b = context.getApplicationContext().getSharedPreferences(str, 0);
    }

    public C5331e(Context context) {
        this(context, "MegviiSDKPreference", "");
    }

    /* renamed from: a */
    public final synchronized void m13625a(String str, String str2) {
        SharedPreferences.Editor edit = this.f12351b.edit();
        edit.putString(str + this.f12350a, str2).apply();
    }

    /* renamed from: a */
    public final synchronized String m13626a(String str) {
        SharedPreferences sharedPreferences;
        sharedPreferences = this.f12351b;
        return sharedPreferences.getString(str + this.f12350a, null);
    }

    /* renamed from: b */
    public final synchronized String m13624b(String str) {
        String string;
        SharedPreferences sharedPreferences = this.f12351b;
        string = sharedPreferences.getString(str + this.f12350a, null);
        SharedPreferences.Editor edit = this.f12351b.edit();
        edit.remove(str + this.f12350a).apply();
        return string;
    }
}
