package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.content.pm.PackageManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.ssl.util.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5119f {

    /* renamed from: a */
    private static final String f12116a = "f";

    /* renamed from: a */
    public static String m13850a(String str) {
        Context contextUtil = ContextUtil.getInstance();
        if (contextUtil == null) {
            return "";
        }
        try {
            return contextUtil.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            String str2 = f12116a;
            C5118e.m13854b(str2, "getVersion NameNotFoundException : " + e.getMessage());
            return "";
        } catch (Exception e2) {
            String str3 = f12116a;
            C5118e.m13854b(str3, "getVersion: " + e2.getMessage());
            return "";
        } catch (Throwable unused) {
            C5118e.m13854b(f12116a, "throwable");
            return "";
        }
    }

    /* renamed from: b */
    public static int m13849b(String str) {
        Context contextUtil = ContextUtil.getInstance();
        if (contextUtil == null) {
            return 0;
        }
        try {
            return contextUtil.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            C5118e.m13854b(f12116a, "getVersion NameNotFoundException");
            return 0;
        } catch (Exception e) {
            String str2 = f12116a;
            C5118e.m13854b(str2, "getVersion: " + e.getMessage());
            return 0;
        }
    }
}
