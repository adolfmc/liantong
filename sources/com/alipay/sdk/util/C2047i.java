package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C2000a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2047i {

    /* renamed from: a */
    public static final String f3887a = "pref_trade_token";

    /* renamed from: b */
    public static final String f3888b = ";";

    /* renamed from: c */
    public static final String f3889c = "result={";

    /* renamed from: d */
    public static final String f3890d = "}";

    /* renamed from: e */
    public static final String f3891e = "trade_token=\"";

    /* renamed from: f */
    public static final String f3892f = "\"";

    /* renamed from: g */
    public static final String f3893g = "trade_token=";

    /* renamed from: a */
    public static void m20690a(Context context, String str) {
        try {
            String m20689a = m20689a(str);
            C2040c.m20714b("", "PayResultUtil::saveTradeToken > tradeToken:" + m20689a);
            if (TextUtils.isEmpty(m20689a)) {
                return;
            }
            C2048j.m20686a(context, "pref_trade_token", m20689a);
        } catch (Throwable th) {
            C2000a.m20898a("biz", "SaveTradeTokenError", th);
            C2040c.m20715a(th);
        }
    }

    /* renamed from: a */
    public static String m20689a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(";");
        String str2 = null;
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("result={") && split[i].endsWith("}")) {
                String[] split2 = split[i].substring(8, split[i].length() - 1).split("&");
                int i2 = 0;
                while (true) {
                    if (i2 >= split2.length) {
                        break;
                    } else if (split2[i2].startsWith("trade_token=\"") && split2[i2].endsWith("\"")) {
                        str2 = split2[i2].substring(13, split2[i2].length() - 1);
                        break;
                    } else if (split2[i2].startsWith("trade_token=")) {
                        str2 = split2[i2].substring(12);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static String m20691a(Context context) {
        String m20684b = C2048j.m20684b(context, "pref_trade_token", "");
        C2040c.m20714b("", "PayResultUtil::fetchTradeToken > tradeToken:" + m20684b);
        return m20684b;
    }
}
