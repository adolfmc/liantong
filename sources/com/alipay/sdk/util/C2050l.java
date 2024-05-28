package com.alipay.sdk.util;

import com.alipay.sdk.app.EnumC1999k;
import com.alipay.sdk.app.statistic.C2000a;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2050l {

    /* renamed from: a */
    public static final String f3898a = "resultStatus";

    /* renamed from: b */
    public static final String f3899b = "memo";

    /* renamed from: c */
    public static final String f3900c = "result";

    /* renamed from: a */
    public static Map<String, String> m20679a(String str) {
        Map<String, String> m20680a = m20680a();
        try {
            return m20677b(str);
        } catch (Throwable th) {
            C2000a.m20898a("biz", "FormatResultEx", th);
            return m20680a;
        }
    }

    /* renamed from: a */
    private static Map<String, String> m20680a() {
        EnumC1999k m20903b = EnumC1999k.m20903b(EnumC1999k.CANCELED.m20907a());
        HashMap hashMap = new HashMap();
        hashMap.put("resultStatus", Integer.toString(m20903b.m20907a()));
        hashMap.put("memo", m20903b.m20904b());
        hashMap.put("result", "");
        return hashMap;
    }

    /* renamed from: b */
    public static Map<String, String> m20677b(String str) {
        String[] split = str.split(";");
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            String substring = str2.substring(0, str2.indexOf("={"));
            hashMap.put(substring, m20678a(str2, substring));
        }
        return hashMap;
    }

    /* renamed from: a */
    private static String m20678a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
    }
}
