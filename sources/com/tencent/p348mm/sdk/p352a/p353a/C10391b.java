package com.tencent.p348mm.sdk.p352a.p353a;

import com.tencent.p348mm.p349a.C10369b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.a.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10391b {
    /* renamed from: a */
    public static byte[] m6199a(String str, int i, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        return C10369b.m6229a(stringBuffer.toString().substring(1, 9).getBytes()).getBytes();
    }
}
