package com.bytedance.pangle.p180g;

import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.C3950i;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.g.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3846e {
    /* renamed from: a */
    public static boolean m16839a(String str, String str2) {
        C3860o m16850a;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (C3950i.m16623a()) {
                m16850a = C3845d.m16841a(str);
            } else {
                m16850a = C3838a.m16850a(str);
            }
            Signature[] signatureArr = m16850a != null ? m16850a.f9206b : null;
            byte[] decode = Base64.decode(Zeus.getPlugin(str2).mSignature, 0);
            if (decode == null || decode.length == 0) {
                ZeusLogger.m16788w("Zeus/install_pangle", "ApkSignatureVerify get hostSignature error : ".concat(String.valueOf(str)));
                return false;
            }
            int i = 0;
            for (Signature signature : signatureArr) {
                i += signature.toByteArray().length;
            }
            byte[] bArr = new byte[i];
            int i2 = 0;
            for (Signature signature2 : signatureArr) {
                System.arraycopy(signature2.toByteArray(), 0, bArr, i2, signature2.toByteArray().length);
                i2 += signature2.toByteArray().length;
            }
            boolean m16808a = C3860o.m16808a(bArr, decode);
            if (!m16808a) {
                ZeusLogger.m16788w("Zeus/install_pangle", "ApkSignatureVerify verify plugin signature error : ".concat(String.valueOf(str)));
            }
            return m16808a;
        } catch (C3862q unused) {
            ZeusLogger.m16788w("Zeus/install_pangle", "ApkSignatureVerify verify plugin signature error : ".concat(String.valueOf(str)));
            return false;
        }
    }
}
