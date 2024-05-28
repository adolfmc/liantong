package com.bytedance.pangle.util;

import android.content.SharedPreferences;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import java.util.Locale;

/* renamed from: com.bytedance.pangle.util.l */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3953l {

    /* renamed from: b */
    private static volatile C3953l f9378b;

    /* renamed from: a */
    public SharedPreferences f9379a = Zeus.getAppApplication().getSharedPreferences("pangle_meta_data_sp", 0);

    /* renamed from: a */
    public static C3953l m16606a() {
        if (f9378b == null) {
            synchronized (C3953l.class) {
                if (f9378b == null) {
                    f9378b = new C3953l();
                }
            }
        }
        return f9378b;
    }

    private C3953l() {
    }

    /* renamed from: a */
    public final int m16605a(String str) {
        int i = this.f9379a.getInt("PLUGIN_API_VERSION_".concat(String.valueOf(str)), 0);
        ZeusLogger.m16792i("Zeus/init_pangle", "ZeusSpUtils getPluginApiVersion pluginPKg = " + str + ", pluginApiVersion = " + i);
        return i;
    }

    /* renamed from: b */
    public final String m16602b(String str) {
        String string = this.f9379a.getString("HOST_IDENTITY_".concat(String.valueOf(str)), "");
        ZeusLogger.m16792i("Zeus/init_pangle", "ZeusSpUtils getHostIdentity pluginPKg = " + str + ", hostIdentity = " + string);
        return string;
    }

    /* renamed from: a */
    public final void m16603a(String str, int i, boolean z) {
        SharedPreferences.Editor edit = this.f9379a.edit();
        String str2 = "INSTALLED_" + str + "-" + i;
        if (z) {
            edit.putBoolean(str2, true);
        } else {
            edit.remove(str2);
        }
        edit.apply();
    }

    /* renamed from: a */
    public final boolean m16604a(String str, int i) {
        return this.f9379a.getBoolean(String.format(Locale.getDefault(), "INSTALLED_%s-%d", str, Integer.valueOf(i)), false);
    }
}
