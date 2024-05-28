package com.sdk.p297m;

import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.p290f.C6998d;
import com.sdk.p302r.C7037a;
import java.util.Properties;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.m.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7012a {

    /* renamed from: a */
    public static final String f18163a = "a";

    /* renamed from: b */
    public static final boolean f18164b = C6998d.f18135a;

    /* renamed from: a */
    public static String m8147a(String str, String str2) {
        if (C7037a.m8130a(str2).booleanValue()) {
            return null;
        }
        Properties properties = new Properties();
        try {
            properties.load(SDKManager.getContext().getAssets().open(str));
        } catch (Exception unused) {
            String str3 = f18163a;
            LogUtils.m8186e(str3, "域名读取失败！《" + str2 + "+》", Boolean.valueOf(f18164b));
        }
        return properties.getProperty(str2);
    }
}
