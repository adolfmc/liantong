package com.networkbench.agent.impl.p254f;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.f.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6392d {

    /* renamed from: a */
    static final char f16141a = '{';

    /* renamed from: b */
    static final char f16142b = '}';

    /* renamed from: c */
    static final String f16143c = "{}";

    /* renamed from: d */
    private static final char f16144d = '\\';

    /* renamed from: a */
    public static final C6389a m10166a(String str, Object obj) {
        return m10164a(str, new Object[]{obj});
    }

    /* renamed from: a */
    public static final C6389a m10165a(String str, Object obj, Object obj2) {
        return m10164a(str, new Object[]{obj, obj2});
    }

    /* renamed from: a */
    static final Throwable m10152a(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        Object obj = objArr[objArr.length - 1];
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        return null;
    }

    /* renamed from: a */
    public static final C6389a m10164a(String str, Object[] objArr) {
        int i;
        Throwable m10152a = m10152a(objArr);
        if (str == null) {
            return new C6389a(null, objArr, m10152a);
        }
        if (objArr == null) {
            return new C6389a(str);
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 50);
        int i2 = 0;
        int i3 = 0;
        while (i2 < objArr.length) {
            int indexOf = str.indexOf("{}", i3);
            if (indexOf == -1) {
                if (i3 == 0) {
                    return new C6389a(str, objArr, m10152a);
                }
                stringBuffer.append(str.substring(i3, str.length()));
                return new C6389a(stringBuffer.toString(), objArr, m10152a);
            }
            if (m10167a(str, indexOf)) {
                if (!m10151b(str, indexOf)) {
                    i2--;
                    stringBuffer.append(str.substring(i3, indexOf - 1));
                    stringBuffer.append('{');
                    i = indexOf + 1;
                } else {
                    stringBuffer.append(str.substring(i3, indexOf - 1));
                    m10162a(stringBuffer, objArr[i2], new HashMap());
                    i = indexOf + 2;
                }
            } else {
                stringBuffer.append(str.substring(i3, indexOf));
                m10162a(stringBuffer, objArr[i2], new HashMap());
                i = indexOf + 2;
            }
            i3 = i;
            i2++;
        }
        stringBuffer.append(str.substring(i3, str.length()));
        if (i2 < objArr.length - 1) {
            return new C6389a(stringBuffer.toString(), objArr, m10152a);
        }
        return new C6389a(stringBuffer.toString(), objArr, null);
    }

    /* renamed from: a */
    static final boolean m10167a(String str, int i) {
        return i != 0 && str.charAt(i - 1) == '\\';
    }

    /* renamed from: b */
    static final boolean m10151b(String str, int i) {
        return i >= 2 && str.charAt(i - 2) == '\\';
    }

    /* renamed from: a */
    private static void m10162a(StringBuffer stringBuffer, Object obj, Map<Object[], ?> map) {
        if (obj == null) {
            stringBuffer.append("null");
        } else if (!obj.getClass().isArray()) {
            m10163a(stringBuffer, obj);
        } else if (obj instanceof boolean[]) {
            m10153a(stringBuffer, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            m10161a(stringBuffer, (byte[]) obj);
        } else if (obj instanceof char[]) {
            m10160a(stringBuffer, (char[]) obj);
        } else if (obj instanceof short[]) {
            m10154a(stringBuffer, (short[]) obj);
        } else if (obj instanceof int[]) {
            m10157a(stringBuffer, (int[]) obj);
        } else if (obj instanceof long[]) {
            m10156a(stringBuffer, (long[]) obj);
        } else if (obj instanceof float[]) {
            m10158a(stringBuffer, (float[]) obj);
        } else if (obj instanceof double[]) {
            m10159a(stringBuffer, (double[]) obj);
        } else {
            m10155a(stringBuffer, (Object[]) obj, map);
        }
    }

    /* renamed from: a */
    private static void m10163a(StringBuffer stringBuffer, Object obj) {
        try {
            stringBuffer.append(obj.toString());
        } catch (Throwable th) {
            th.printStackTrace();
            stringBuffer.append("[FAILED toString()]");
        }
    }

    /* renamed from: a */
    private static void m10155a(StringBuffer stringBuffer, Object[] objArr, Map<Object[], ?> map) {
        stringBuffer.append('[');
        if (!map.containsKey(objArr)) {
            map.put(objArr, null);
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                m10162a(stringBuffer, objArr[i], map);
                if (i != length - 1) {
                    stringBuffer.append(", ");
                }
            }
            map.remove(objArr);
        } else {
            stringBuffer.append("...");
        }
        stringBuffer.append(']');
    }

    /* renamed from: a */
    private static void m10153a(StringBuffer stringBuffer, boolean[] zArr) {
        stringBuffer.append('[');
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(zArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(']');
    }

    /* renamed from: a */
    private static void m10161a(StringBuffer stringBuffer, byte[] bArr) {
        stringBuffer.append('[');
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append((int) bArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(']');
    }

    /* renamed from: a */
    private static void m10160a(StringBuffer stringBuffer, char[] cArr) {
        stringBuffer.append('[');
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(cArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(']');
    }

    /* renamed from: a */
    private static void m10154a(StringBuffer stringBuffer, short[] sArr) {
        stringBuffer.append('[');
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append((int) sArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(']');
    }

    /* renamed from: a */
    private static void m10157a(StringBuffer stringBuffer, int[] iArr) {
        stringBuffer.append('[');
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(iArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(']');
    }

    /* renamed from: a */
    private static void m10156a(StringBuffer stringBuffer, long[] jArr) {
        stringBuffer.append('[');
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(jArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(']');
    }

    /* renamed from: a */
    private static void m10158a(StringBuffer stringBuffer, float[] fArr) {
        stringBuffer.append('[');
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(fArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(']');
    }

    /* renamed from: a */
    private static void m10159a(StringBuffer stringBuffer, double[] dArr) {
        stringBuffer.append('[');
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(dArr[i]);
            if (i != length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(']');
    }
}
