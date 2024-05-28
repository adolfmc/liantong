package com.sdk.base.module.config;

import com.sdk.p290f.InterfaceC6997c;
import com.sdk.p295k.C7009a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BaseConfig implements InterfaceC6997c {
    public static String apk = "com.cucc.sdk.api_key";

    /* renamed from: c */
    public static int f18080c = 69;

    /* renamed from: cm */
    public static String f18081cm = "CUCC";

    /* renamed from: n */
    public static String f18082n = "SDKFactory";

    /* renamed from: v */
    public static String f18083v = "安卓6.0.9通用版20230808";

    /* renamed from: r */
    public long f18084r = System.currentTimeMillis();

    public String getApiKey() {
        return apk;
    }

    public String getCM() {
        return f18081cm;
    }

    public String toJsonString() {
        return C7009a.m8153a(this);
    }
}
