package com.sdk.p299o;

import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.p290f.C6998d;
import com.sdk.p300p.C7030d;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.o.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7025a {

    /* renamed from: a */
    public static final String f18180a = "a";

    /* renamed from: b */
    public static Boolean f18181b = Boolean.valueOf(C6998d.f18135a);

    /* renamed from: a */
    public static PublicKey m8143a(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(C7030d.m8137a(str)));
        } catch (Exception e) {
            LogUtils.m8186e(f18180a, e.toString(), f18181b);
            return null;
        }
    }
}
