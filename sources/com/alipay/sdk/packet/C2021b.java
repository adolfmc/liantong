package com.alipay.sdk.packet;

import android.text.TextUtils;
import com.alipay.sdk.util.C2040c;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.packet.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2021b {

    /* renamed from: a */
    private final String f3775a;

    /* renamed from: b */
    private final String f3776b;

    public C2021b(String str, String str2) {
        this.f3775a = str;
        this.f3776b = str2;
    }

    /* renamed from: a */
    public String m20815a() {
        return this.f3775a;
    }

    /* renamed from: b */
    public String m20814b() {
        return this.f3776b;
    }

    /* renamed from: c */
    public JSONObject m20813c() {
        if (TextUtils.isEmpty(this.f3776b)) {
            return null;
        }
        try {
            return new JSONObject(this.f3776b);
        } catch (Exception e) {
            C2040c.m20715a(e);
            return null;
        }
    }

    public String toString() {
        return String.format("<Letter envelop=%s body=%s>", this.f3775a, this.f3776b);
    }
}
