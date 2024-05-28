package szcom.coremedia.iso;

import java.io.UnsupportedEncodingException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Ascii {
    public static String convert(byte[] bArr) {
        if (bArr != null) {
            try {
                return new String(bArr, "us-ascii");
            } catch (UnsupportedEncodingException e) {
                throw new Error(e);
            }
        }
        return null;
    }

    public static byte[] convert(String str) {
        if (str != null) {
            try {
                return str.getBytes("us-ascii");
            } catch (UnsupportedEncodingException e) {
                throw new Error(e);
            }
        }
        return null;
    }
}
