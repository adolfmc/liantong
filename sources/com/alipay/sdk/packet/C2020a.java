package com.alipay.sdk.packet;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.packet.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2020a {
    /* renamed from: a */
    public static String m20821a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("&");
        if (split.length == 0) {
            return "";
        }
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        for (String str6 : split) {
            if (TextUtils.isEmpty(str2)) {
                str2 = m20820b(str6);
            }
            if (TextUtils.isEmpty(str3)) {
                str3 = m20819c(str6);
            }
            if (TextUtils.isEmpty(str4)) {
                str4 = m20818d(str6);
            }
            if (TextUtils.isEmpty(str5)) {
                str5 = m20816f(str6);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str2)) {
            sb.append("biz_type=" + str2 + ";");
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append("biz_no=" + str3 + ";");
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append("trade_no=" + str4 + ";");
        }
        if (!TextUtils.isEmpty(str5)) {
            sb.append("app_userid=" + str5 + ";");
        }
        String sb2 = sb.toString();
        return sb2.endsWith(";") ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    /* renamed from: b */
    private static String m20820b(String str) {
        if (str.contains("biz_type")) {
            return m20817e(str);
        }
        return null;
    }

    /* renamed from: c */
    private static String m20819c(String str) {
        if (str.contains("biz_no")) {
            return m20817e(str);
        }
        return null;
    }

    /* renamed from: d */
    private static String m20818d(String str) {
        if (!str.contains("trade_no") || str.startsWith("out_trade_no")) {
            return null;
        }
        return m20817e(str);
    }

    /* renamed from: e */
    private static String m20817e(String str) {
        String[] split = str.split("=");
        if (split.length > 1) {
            String str2 = split[1];
            return str2.contains("\"") ? str2.replaceAll("\"", "") : str2;
        }
        return null;
    }

    /* renamed from: f */
    private static String m20816f(String str) {
        if (str.contains("app_userid")) {
            return m20817e(str);
        }
        return null;
    }
}
