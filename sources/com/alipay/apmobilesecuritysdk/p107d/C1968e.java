package com.alipay.apmobilesecuritysdk.p107d;

import android.content.Context;
import com.alipay.security.mobile.module.p110a.p111a.C2083b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.d.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1968e {

    /* renamed from: a */
    private static Map<String, String> f3480a;

    /* renamed from: b */
    private static final String[] f3481b = {"AD1", "AD2", "AD3", "AD8", "AD9", "AD10", "AD11", "AD12", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD34", "AA1", "AA2", "AA3", "AA4", "AC4", "AC10", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15"};

    /* renamed from: a */
    private static String m21035a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        while (i < arrayList.size()) {
            String str = (String) arrayList.get(i);
            String str2 = map.get(str);
            if (str2 == null) {
                str2 = "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(i == 0 ? "" : "&");
            sb.append(str);
            sb.append("=");
            sb.append(str2);
            stringBuffer.append(sb.toString());
            i++;
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static synchronized Map<String, String> m21036a(Context context, Map<String, String> map) {
        Map<String, String> map2;
        synchronized (C1968e.class) {
            if (f3480a == null) {
                m21033c(context, map);
            }
            f3480a.putAll(C1967d.m21039a());
            map2 = f3480a;
        }
        return map2;
    }

    /* renamed from: a */
    public static synchronized void m21037a() {
        synchronized (C1968e.class) {
            f3480a = null;
        }
    }

    /* renamed from: b */
    public static synchronized String m21034b(Context context, Map<String, String> map) {
        String[] strArr;
        String m20565a;
        synchronized (C1968e.class) {
            m21036a(context, map);
            TreeMap treeMap = new TreeMap();
            for (String str : f3481b) {
                if (f3480a.containsKey(str)) {
                    treeMap.put(str, f3480a.get(str));
                }
            }
            m20565a = C2083b.m20565a(m21035a(treeMap));
        }
        return m20565a;
    }

    /* renamed from: c */
    private static synchronized void m21033c(Context context, Map<String, String> map) {
        synchronized (C1968e.class) {
            TreeMap treeMap = new TreeMap();
            f3480a = treeMap;
            treeMap.putAll(C1965b.m21041a(context, map));
            f3480a.putAll(C1967d.m21038a(context));
            f3480a.putAll(C1966c.m21040a(context));
            f3480a.putAll(C1964a.m21042a(context, map));
        }
    }
}
