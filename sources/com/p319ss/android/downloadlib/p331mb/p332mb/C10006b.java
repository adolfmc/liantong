package com.p319ss.android.downloadlib.p331mb.p332mb;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.mb.mb.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10006b {
    /* renamed from: mb */
    public static byte[] m7192mb(CharSequence charSequence, String str) throws Exception {
        return m7191mb(charSequence.toString().getBytes(), str);
    }

    /* renamed from: mb */
    public static byte[] m7191mb(byte[] bArr, String str) throws Exception {
        return m7193mb(new ByteArrayInputStream(bArr), str);
    }

    /* renamed from: mb */
    public static byte[] m7193mb(InputStream inputStream, String str) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return messageDigest.digest();
            }
            messageDigest.update(bArr, 0, read);
        }
    }
}
