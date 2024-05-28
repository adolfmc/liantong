package p000;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: z */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ReverseUtil {
    /* renamed from: a */
    public static String m7a(String str) {
        int i = 0;
        if (m6a(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (i < str.length()) {
            int i2 = i + 1;
            if (i2 % 2 == 0) {
                char charAt = str.charAt(i - 1);
                sb.append(str.charAt(i));
                sb.append(charAt);
            }
            i = i2;
        }
        if (str.length() % 2 != 0) {
            sb.append(str.charAt(str.length() - 1));
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static boolean m6a(String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (str == null || str.length() < 1 || "null".equals(str)) {
                return true;
            }
        }
        return false;
    }
}
