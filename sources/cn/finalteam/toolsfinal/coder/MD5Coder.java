package cn.finalteam.toolsfinal.coder;

import com.example.asus.detectionandalign.animation.C4280b;
import com.sdk.p285a.C6960d;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MD5Coder {
    private static final String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", C4280b.f10047a, "c", C6960d.f18019d, "e", "f"};

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r3 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String byteToArrayString(byte r3) {
        /*
            if (r3 >= 0) goto L4
            int r3 = r3 + 256
        L4:
            int r0 = r3 / 16
            int r3 = r3 % 16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[] r2 = cn.finalteam.toolsfinal.coder.MD5Coder.strDigits
            r0 = r2[r0]
            r1.append(r0)
            java.lang.String[] r0 = cn.finalteam.toolsfinal.coder.MD5Coder.strDigits
            r3 = r0[r3]
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.toolsfinal.coder.MD5Coder.byteToArrayString(byte):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r0 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String byteToNum(byte r0) {
        /*
            if (r0 >= 0) goto L4
            int r0 = r0 + 256
        L4:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.toolsfinal.coder.MD5Coder.byteToNum(byte):java.lang.String");
    }

    private static String byteToString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(byteToArrayString(b));
        }
        return stringBuffer.toString();
    }

    public static String getMD5Code(String str) {
        String str2;
        try {
            str2 = new String(str);
        } catch (NoSuchAlgorithmException e) {
            e = e;
            str2 = null;
        }
        try {
            return byteToString(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            e.printStackTrace();
            return str2;
        }
    }
}
