package p000;

import java.io.PrintStream;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: t */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ApduUtil {
    /* renamed from: a */
    public static String m111a(String str) {
        if (str.toUpperCase().startsWith("00A4")) {
            return str;
        }
        String hexString = Integer.toHexString(str.length() / 2);
        PrintStream printStream = System.out;
        printStream.println("hexLc:" + hexString);
        String replace = String.format("%2s", hexString).replace(" ", "0");
        return "00A40400" + replace + str;
    }
}
