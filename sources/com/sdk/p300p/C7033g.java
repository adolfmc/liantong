package com.sdk.p300p;

import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.p290f.C6998d;
import com.sdk.p292h.C7006a;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.p.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7033g extends C7006a {

    /* renamed from: a */
    public static final String f18195a = "com.sdk.p.g";

    /* renamed from: b */
    public static boolean f18196b = C6998d.f18135a;

    /* renamed from: a */
    public static String m8135a(String str, String str2, TreeMap<String, Object> treeMap) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(str2);
            stringBuffer.append('?');
            for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                String key = entry.getKey();
                String str3 = entry.getValue() + "";
                if (entry.getValue() != null && str3.length() > 0 && !"null".equals(str3) && !"sign".equals(key) && !key.startsWith("_") && !"file".equals(key)) {
                    stringBuffer.append(key);
                    stringBuffer.append('=');
                    stringBuffer.append(entry.getValue());
                    stringBuffer.append('&');
                }
            }
            if (stringBuffer.charAt(stringBuffer.length() - 1) == '&') {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            return C7032f.m8136a().f18193a.mo8134a(stringBuffer.toString());
        } catch (Exception e) {
            LogUtils.m8186e(f18195a, e.getMessage(), Boolean.valueOf(f18196b));
            return null;
        }
    }
}
