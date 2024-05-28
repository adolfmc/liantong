package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.apmobilesecuritysdk.p104a.C1959a;
import com.alipay.security.mobile.module.p110a.C2081a;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SecurityClientMobile {
    public static synchronized String GetApdid(Context context, Map<String, String> map) {
        String m21058a;
        synchronized (SecurityClientMobile.class) {
            HashMap hashMap = new HashMap();
            hashMap.put("utdid", C2081a.m20574a(map, "utdid", ""));
            hashMap.put("tid", C2081a.m20574a(map, "tid", ""));
            hashMap.put("userId", C2081a.m20574a(map, "userId", ""));
            APSecuritySdk.getInstance(context).initToken(0, hashMap, null);
            m21058a = C1959a.m21058a(context);
        }
        return m21058a;
    }
}
