package com.baidu.location;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Jni {

    /* renamed from: a */
    private static int f5066a = 0;

    /* renamed from: b */
    private static int f5067b = 1;

    /* renamed from: c */
    private static int f5068c = 2;

    /* renamed from: d */
    private static int f5069d = 11;

    /* renamed from: e */
    private static int f5070e = 12;

    /* renamed from: f */
    private static int f5071f = 13;

    /* renamed from: g */
    private static int f5072g = 14;

    /* renamed from: h */
    private static int f5073h = 15;

    /* renamed from: i */
    private static int f5074i = 1024;

    /* renamed from: j */
    private static boolean f5075j;

    static {
        try {
            System.loadLibrary("locSDK8b");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            f5075j = true;
        }
    }

    /* renamed from: a */
    private static native String m19635a(byte[] bArr, int i);

    /* renamed from: b */
    private static native String m19634b(double d, double d2, int i, int i2);

    /* renamed from: c */
    private static native String m19633c(byte[] bArr, int i);

    public static double[] coorEncrypt(double d, double d2, String str) {
        double[] dArr = {0.0d, 0.0d};
        if (f5075j) {
            return dArr;
        }
        int i = -1;
        if (str.equals("bd09")) {
            i = f5066a;
        } else if (str.equals("bd09ll")) {
            i = f5067b;
        } else if (str.equals("gcj02")) {
            i = f5068c;
        } else if (str.equals("gps2gcj")) {
            i = f5069d;
        } else if (str.equals("bd092gcj")) {
            i = f5070e;
        } else if (str.equals("bd09ll2gcj")) {
            i = f5071f;
        } else if (str.equals("wgs842mc")) {
            i = f5073h;
        }
        if (str.equals("gcj2wgs")) {
            i = 16;
        }
        try {
            String[] split = m19634b(d, d2, i, 132456).split(":");
            dArr[0] = Double.parseDouble(split[0]);
            dArr[1] = Double.parseDouble(split[1]);
        } catch (Throwable unused) {
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
        }
        return dArr;
    }

    /* renamed from: ee */
    private static native String m19632ee(String str, int i);

    public static String en1(String str) {
        if (f5075j) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[f5074i];
        int length = bytes.length;
        if (length > 740) {
            length = 740;
        }
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (bytes[i2] != 0) {
                bArr[i] = bytes[i2];
                i++;
            }
        }
        try {
            return m19635a(bArr, 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    public static String encode(String str) {
        if (f5075j) {
            return "err!";
        }
        return en1(str) + "|tp=3";
    }

    public static String encode2(String str) {
        if (f5075j) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        try {
            return m19633c(str.getBytes(), 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    public static Long encode3(String str) {
        if (f5075j) {
            return null;
        }
        String str2 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception unused) {
        }
        try {
            return Long.valueOf(murmur(str2));
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return null;
        }
    }

    private static native String encodeNotLimit(String str, int i);

    public static String encodeOfflineLocationUpdateRequest(String str) {
        String str2;
        if (f5075j) {
            return "err!";
        }
        String str3 = "";
        try {
            str3 = new String(str.getBytes(), "UTF-8");
        } catch (Exception unused) {
        }
        try {
            str2 = encodeNotLimit(str3, 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            str2 = "err!";
        }
        return str2 + "|tp=3";
    }

    public static String encodeTp4(String str) {
        String str2;
        if (f5075j) {
            return "err!";
        }
        String str3 = "";
        try {
            str3 = new String(str.getBytes(), "UTF-8");
        } catch (Exception unused) {
        }
        try {
            str2 = m19632ee(str3, 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            str2 = "err!";
        }
        return str2 + "|tp=4";
    }

    public static String encodeTp4NoTag(String str) {
        if (f5075j) {
            return "err!";
        }
        String str2 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception unused) {
        }
        try {
            return m19632ee(str2, 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    private static native byte[] encrypt(byte[] bArr);

    /* renamed from: f */
    private static native void m19631f(byte[] bArr, byte[] bArr2);

    /* renamed from: g */
    private static native String m19630g(byte[] bArr);

    public static double getGpsSwiftRadius(float f, double d, double d2) {
        if (f5075j) {
            return 0.0d;
        }
        try {
            return gsr(f, d, d2);
        } catch (UnsatisfiedLinkError unused) {
            return 0.0d;
        }
    }

    public static String getldkaiv() {
        if (f5075j) {
            return null;
        }
        try {
            return ldkaiv();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return null;
        }
    }

    private static native double gsr(float f, double d, double d2);

    private static native String ldkaiv();

    private static native long murmur(String str);
}
