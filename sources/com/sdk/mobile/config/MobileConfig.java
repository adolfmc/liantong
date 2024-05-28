package com.sdk.mobile.config;

import com.sdk.base.module.config.BaseConfig;
import com.sdk.p290f.InterfaceC6997c;
import com.sdk.p295k.C7009a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MobileConfig implements InterfaceC6997c {
    public String apk = BaseConfig.apk;

    /* renamed from: cm */
    public String f18166cm = BaseConfig.f18081cm;

    /* renamed from: c */
    public int f18165c = 1;

    /* renamed from: v */
    public String f18169v = "1.0";

    /* renamed from: n */
    public String f18167n = "OAuth";

    /* renamed from: r */
    public long f18168r = System.currentTimeMillis();

    public String getApiKey() {
        return this.apk;
    }

    public String getCM() {
        return this.f18166cm;
    }

    public String toJsonString() {
        return C7009a.m8153a(this);
    }
}
