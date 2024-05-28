package p470p0;

import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.n */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13651n {
    /* renamed from: a */
    public static String m175a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes("GBK"));
            StringBuffer stringBuffer = new StringBuffer();
            byte[] digest = messageDigest.digest();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                stringBuffer.append(String.format("%02x", Integer.valueOf(digest[i] & 255)));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
