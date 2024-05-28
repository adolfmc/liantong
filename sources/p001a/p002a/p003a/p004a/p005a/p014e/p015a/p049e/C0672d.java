package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0672d {
    /* renamed from: a */
    public static boolean m22462a(String str) {
        return m22460b(str) || m22458d(str);
    }

    /* renamed from: b */
    public static boolean m22460b(String str) {
        int indexOf;
        if (str.length() == 0) {
            return false;
        }
        String str2 = str + ".";
        int i = 0;
        int i2 = 0;
        while (i < str2.length() && (indexOf = str2.indexOf(46, i)) > i) {
            if (i2 == 4) {
                return false;
            }
            try {
                int parseInt = Integer.parseInt(str2.substring(i, indexOf));
                if (parseInt >= 0 && parseInt <= 255) {
                    i = indexOf + 1;
                    i2++;
                }
            } catch (NumberFormatException unused) {
            }
            return false;
        }
        return i2 == 4;
    }

    /* renamed from: c */
    public static boolean m22459c(String str) {
        int indexOf = str.indexOf("/");
        String substring = str.substring(indexOf + 1);
        if (indexOf <= 0 || !m22460b(str.substring(0, indexOf))) {
            return false;
        }
        return m22460b(substring) || m22461a(substring, 32);
    }

    /* renamed from: d */
    public static boolean m22458d(String str) {
        int indexOf;
        if (str.length() == 0) {
            return false;
        }
        String str2 = str + ":";
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i < str2.length() && (indexOf = str2.indexOf(58, i)) >= i) {
            if (i2 == 8) {
                return false;
            }
            if (i != indexOf) {
                String substring = str2.substring(i, indexOf);
                if (indexOf == str2.length() - 1 && substring.indexOf(46) > 0) {
                    if (!m22460b(substring)) {
                        return false;
                    }
                    i2++;
                } else {
                    try {
                        int parseInt = Integer.parseInt(str2.substring(i, indexOf), 16);
                        if (parseInt >= 0 && parseInt <= 65535) {
                        }
                    } catch (NumberFormatException unused) {
                    }
                    return false;
                }
            } else if (indexOf != 1 && indexOf != str2.length() - 1 && z) {
                return false;
            } else {
                z = true;
            }
            i = indexOf + 1;
            i2++;
        }
        return i2 == 8 || z;
    }

    /* renamed from: e */
    public static boolean m22457e(String str) {
        int indexOf = str.indexOf("/");
        String substring = str.substring(indexOf + 1);
        if (indexOf <= 0 || !m22458d(str.substring(0, indexOf))) {
            return false;
        }
        return m22458d(substring) || m22461a(substring, 128);
    }

    /* renamed from: f */
    public static boolean m22456f(String str) {
        return m22459c(str) || m22457e(str);
    }

    /* renamed from: a */
    public static boolean m22461a(String str, int i) {
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt >= 0 && parseInt <= i;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
