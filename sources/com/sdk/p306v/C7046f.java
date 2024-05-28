package com.sdk.p306v;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.v.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7046f extends Exception {
    public C7046f(EnumC7041a enumC7041a) {
        super(enumC7041a.m8122b());
        Integer.parseInt(enumC7041a.m8123a());
    }

    public C7046f(EnumC7041a enumC7041a, Exception exc) {
        super(enumC7041a.m8122b());
        Integer.parseInt(enumC7041a.m8123a());
        String str = enumC7041a.m8122b() + " case by : " + exc.getMessage();
    }
}
