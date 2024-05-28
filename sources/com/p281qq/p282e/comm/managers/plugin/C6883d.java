package com.p281qq.p282e.comm.managers.plugin;

import com.example.asus.detectionandalign.animation.C4280b;
import com.sdk.p285a.C6960d;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.managers.plugin.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
class C6883d {

    /* renamed from: a */
    private static final String[] f17950a = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", C4280b.f10047a, "c", C6960d.f18019d, "e", "f"};

    /* renamed from: a */
    public static String m8255a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : bArr) {
            if (i < 0) {
                i += 256;
            }
            stringBuffer.append(f17950a[i / 16] + f17950a[i % 16]);
        }
        return stringBuffer.toString();
    }
}
