package com.huawei.hms.hatool;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.Pair;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.x0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5035x0 extends AbstractC5011o {
    /* renamed from: c */
    public static String m14430c() {
        String str;
        String str2;
        String str3;
        try {
            str = (String) Class.forName("com.huawei.android.os.BuildEx").getMethod("getUDID", new Class[0]).invoke(null, new Object[0]);
        } catch (AndroidRuntimeException unused) {
            str = "";
        } catch (ClassNotFoundException unused2) {
            str = "";
        } catch (IllegalAccessException unused3) {
            str = "";
        } catch (IllegalArgumentException unused4) {
            str = "";
        } catch (NoSuchMethodException unused5) {
            str = "";
        } catch (InvocationTargetException unused6) {
            str = "";
        }
        try {
            C5029v.m14455c("hmsSdk", "getUDID success");
        } catch (AndroidRuntimeException unused7) {
            str2 = "hmsSdk";
            str3 = "getUDID getudid failed, RuntimeException is AndroidRuntimeException";
            C5029v.m14451f(str2, str3);
            return str;
        } catch (ClassNotFoundException unused8) {
            str2 = "hmsSdk";
            str3 = "getUDID method invoke failed";
            C5029v.m14451f(str2, str3);
            return str;
        } catch (IllegalAccessException unused9) {
            str2 = "hmsSdk";
            str3 = "getUDID method invoke failed : Illegal AccessException";
            C5029v.m14451f(str2, str3);
            return str;
        } catch (IllegalArgumentException unused10) {
            str2 = "hmsSdk";
            str3 = "getUDID method invoke failed : Illegal ArgumentException";
            C5029v.m14451f(str2, str3);
            return str;
        } catch (NoSuchMethodException unused11) {
            str2 = "hmsSdk";
            str3 = "getUDID method invoke failed : NoSuchMethodException";
            C5029v.m14451f(str2, str3);
            return str;
        } catch (InvocationTargetException unused12) {
            str2 = "hmsSdk";
            str3 = "getUDID method invoke failed : InvocationTargetException";
            C5029v.m14451f(str2, str3);
            return str;
        }
        return str;
    }

    /* renamed from: e */
    public static Pair<String, String> m14429e(Context context) {
        if (C4973c0.m14779a(context, "android.permission.READ_PHONE_STATE")) {
            C5029v.m14451f("hmsSdk", "getMccAndMnc() Pair value is empty");
            return new Pair<>("", "");
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null && telephonyManager.getSimState() == 5) {
            String networkOperator = telephonyManager.getNetworkOperator();
            return (TextUtils.isEmpty(networkOperator) || TextUtils.equals(networkOperator, "null")) ? new Pair<>("", "") : networkOperator.length() > 3 ? new Pair<>(networkOperator.substring(0, 3), networkOperator.substring(3)) : new Pair<>("", "");
        }
        return new Pair<>("", "");
    }
}
