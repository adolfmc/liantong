package p000;

import java.io.PrintStream;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a0 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class StringUtil {
    /* renamed from: a */
    public static String m22228a(String str) {
        String hexString = Integer.toHexString(str.length() / 2);
        PrintStream printStream = System.out;
        printStream.println("hexLc:" + hexString);
        return String.format("%2s", hexString).replace(" ", "0").toUpperCase();
    }

    /* renamed from: b */
    public static boolean m22227b(String str) {
        return str == null || str.length() == 0 || "".equals(str) || "null".equals(str);
    }
}
