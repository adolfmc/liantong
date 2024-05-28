package com.tencent.p348mm.p349a;

import android.util.Base64;
import javax.crypto.Cipher;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10368a {

    /* renamed from: a */
    private Cipher f19946a;

    /* renamed from: a */
    public final String m6230a(String str) {
        try {
            return new String(this.f19946a.doFinal(Base64.decode(str, 0)), "UTF8");
        } catch (Exception e) {
            return "[des]" + str + "|" + e.toString();
        }
    }
}
