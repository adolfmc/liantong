package p470p0;

import com.ijiami.JMEncryptBoxByRandom;
import com.ijiami.ResultData;

/* renamed from: p0.d */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13639d {

    /* renamed from: a */
    public static C13639d f27485a;

    /* JADX WARN: Code restructure failed: missing block: B:32:0x004f, code lost:
        if (r2 == null) goto L25;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m185b(java.lang.String r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            byte[] r7 = com.ijiami.Base64.decode(r7)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L44
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L44
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L44
            java.util.zip.GZIPInputStream r7 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3b
            r7.<init>(r2)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3b
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
        L1b:
            int r4 = r7.read(r3)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            r5 = -1
            if (r4 == r5) goto L27
            r5 = 0
            r1.write(r3, r5, r4)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            goto L1b
        L27:
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            r7.close()     // Catch: java.io.IOException -> L2e
        L2e:
            r2.close()     // Catch: java.io.IOException -> L52
            goto L52
        L32:
            r0 = move-exception
            goto L56
        L34:
            r3 = move-exception
            goto L47
        L36:
            r7 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L56
        L3b:
            r7 = move-exception
            r3 = r7
            r7 = r0
            goto L47
        L3f:
            r7 = move-exception
            r2 = r0
            r0 = r7
            r7 = r2
            goto L56
        L44:
            r3 = move-exception
            r7 = r0
            r2 = r7
        L47:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L32
            if (r7 == 0) goto L4f
            r7.close()     // Catch: java.io.IOException -> L4f
        L4f:
            if (r2 == 0) goto L52
            goto L2e
        L52:
            r1.close()     // Catch: java.io.IOException -> L55
        L55:
            return r0
        L56:
            if (r7 == 0) goto L5b
            r7.close()     // Catch: java.io.IOException -> L5b
        L5b:
            if (r2 == 0) goto L60
            r2.close()     // Catch: java.io.IOException -> L60
        L60:
            r1.close()     // Catch: java.io.IOException -> L63
        L63:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p470p0.C13639d.m185b(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public final String m186a(String str) {
        ResultData resultData;
        try {
            resultData = JMEncryptBoxByRandom.decryptFromBase64(str, 2);
        } catch (Exception e) {
            e.printStackTrace();
            resultData = null;
        }
        if (resultData == null || resultData.getStatus() != 1) {
            C13652o.m174a("UnicomPaySDK decode:", "失败");
            return str;
        }
        C13652o.m174a("UnicomPaySDK decode:", resultData.getText());
        return resultData.getText();
    }
}
