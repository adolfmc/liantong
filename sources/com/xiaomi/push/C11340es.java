package com.xiaomi.push;

import android.util.Log;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11343ev;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.es */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11340es {

    /* renamed from: a */
    private static final boolean f22192a = Log.isLoggable("BCompressed", 3);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m3938a(C11339er c11339er, byte[] bArr) {
        try {
            byte[] m3920a = C11343ev.C11344a.m3920a(bArr);
            if (f22192a) {
                AbstractC11049b.m5280a("BCompressed", "decompress " + bArr.length + " to " + m3920a.length + " for " + c11339er);
                if (c11339er.f22186a == 1) {
                    AbstractC11049b.m5280a("BCompressed", "decompress not support upStream");
                }
            }
            return m3920a;
        } catch (Exception e) {
            AbstractC11049b.m5280a("BCompressed", "decompress error " + e);
            return bArr;
        }
    }
}
