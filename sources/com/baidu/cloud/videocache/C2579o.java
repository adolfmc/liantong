package com.baidu.cloud.videocache;

import android.net.Uri;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2579o {
    /* renamed from: a */
    public static Uri m19771a(String str, String str2) {
        return Uri.parse(m19769b(str, str2));
    }

    /* renamed from: a */
    private static String m19770a(StringBuilder sb, int i, int i2) {
        int i3;
        int i4;
        if (i >= i2) {
            return sb.toString();
        }
        if (sb.charAt(i) == '/') {
            i++;
        }
        int i5 = i;
        int i6 = i2;
        while (true) {
            for (int i7 = i5; i7 <= i6; i7++) {
                if (i7 == i6) {
                    i3 = i7;
                } else if (sb.charAt(i7) == '/') {
                    i3 = i7 + 1;
                }
                int i8 = i5 + 1;
                if (i7 == i8 && sb.charAt(i5) == '.') {
                    sb.delete(i5, i3);
                    i6 -= i3 - i5;
                } else {
                    if (i7 == i5 + 2 && sb.charAt(i5) == '.' && sb.charAt(i8) == '.') {
                        i4 = sb.lastIndexOf("/", i5 - 2) + 1;
                        int i9 = i4 > i ? i4 : i;
                        sb.delete(i9, i3);
                        i6 -= i3 - i9;
                    } else {
                        i4 = i7 + 1;
                    }
                    i5 = i4;
                }
            }
            return sb.toString();
        }
    }

    /* renamed from: a */
    private static int[] m19772a(String str) {
        int i;
        int[] iArr = new int[4];
        if (TextUtils.isEmpty(str)) {
            iArr[0] = -1;
            return iArr;
        }
        int length = str.length();
        int indexOf = str.indexOf("#");
        if (indexOf != -1) {
            length = indexOf;
        }
        int indexOf2 = str.indexOf("?");
        if (indexOf2 == -1 || indexOf2 > length) {
            indexOf2 = length;
        }
        int indexOf3 = str.indexOf("/");
        if (indexOf3 == -1 && indexOf3 > indexOf2) {
            indexOf3 = indexOf2;
        }
        int indexOf4 = str.indexOf(":");
        if (indexOf4 > indexOf3) {
            indexOf4 = -1;
        }
        int i2 = indexOf4 + 2;
        if (i2 < indexOf2 && str.charAt(indexOf4 + 1) == '/' && str.charAt(i2) == '/') {
            i = str.indexOf(47, indexOf4 + 3);
            if (i == -1 || i > indexOf2) {
                i = indexOf2;
            }
        } else {
            i = indexOf4 + 1;
        }
        iArr[0] = indexOf4;
        iArr[1] = i;
        iArr[2] = indexOf2;
        iArr[3] = length;
        return iArr;
    }

    /* renamed from: b */
    private static String m19769b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] m19772a = m19772a(str2);
        if (m19772a[0] != -1) {
            sb.append(str2);
            m19770a(sb, m19772a[1], m19772a[2]);
            return sb.toString();
        }
        int[] m19772a2 = m19772a(str);
        if (m19772a[3] == 0) {
            sb.append((CharSequence) str, 0, m19772a2[3]);
            sb.append(str2);
            return sb.toString();
        } else if (m19772a[2] == 0) {
            sb.append((CharSequence) str, 0, m19772a2[2]);
            sb.append(str2);
            return sb.toString();
        } else if (m19772a[1] != 0) {
            int i = m19772a2[0] + 1;
            sb.append((CharSequence) str, 0, i);
            sb.append(str2);
            return m19770a(sb, m19772a[1] + i, i + m19772a[2]);
        } else if (str2.charAt(m19772a[1]) == '/') {
            sb.append((CharSequence) str, 0, m19772a2[1]);
            sb.append(str2);
            return m19770a(sb, m19772a2[1], m19772a2[1] + m19772a[2]);
        } else if (m19772a2[0] + 2 < m19772a2[1] && m19772a2[1] == m19772a2[2]) {
            sb.append((CharSequence) str, 0, m19772a2[1]);
            sb.append('/');
            sb.append(str2);
            return m19770a(sb, m19772a2[1], m19772a2[1] + m19772a[2] + 1);
        } else {
            int lastIndexOf = str.lastIndexOf(47, m19772a2[2] - 1);
            int i2 = lastIndexOf == -1 ? m19772a2[1] : lastIndexOf + 1;
            sb.append((CharSequence) str, 0, i2);
            sb.append(str2);
            return m19770a(sb, m19772a2[1], i2 + m19772a[2]);
        }
    }
}
