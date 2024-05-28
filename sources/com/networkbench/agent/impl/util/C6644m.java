package com.networkbench.agent.impl.util;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.m */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6644m {

    /* renamed from: a */
    public static final String f17220a = " \r\n\t\u3000   ";

    /* renamed from: b */
    private static final Pattern f17221b = Pattern.compile("(.*)\\{(\\d+),(\\d+)\\}(.*)");

    /* renamed from: e */
    public static String m8884e(String str) {
        return str == null ? "" : str;
    }

    private C6644m() {
    }

    /* renamed from: a */
    public static boolean m8892a(String str) {
        return Pattern.compile("^[a-zA-Z0-9\\-\\.]+\\.(com|org|net|mil|edu|COM|ORG|NET|MIL|EDU)$").matcher(str).matches();
    }

    /* renamed from: a */
    public static String m8890a(String str, boolean z, boolean z2, String str2) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = str.length() - 1;
        while (z && i <= length && str2.indexOf(str.charAt(i)) >= 0) {
            i++;
        }
        while (z2 && length >= i && str2.indexOf(str.charAt(length)) >= 0) {
            length--;
        }
        return str.substring(i, length + 1);
    }

    /* renamed from: b */
    public static String m8889b(String str) {
        return m8890a(str, true, true, " \r\n\t\u3000   ");
    }

    /* renamed from: c */
    public static boolean m8887c(String str) {
        return m8884e(str).length() == 0;
    }

    /* renamed from: d */
    public static boolean m8885d(String str) {
        String m8884e = m8884e(str);
        int length = m8884e.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(m8884e.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m8891a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    /* renamed from: b */
    public static boolean m8888b(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    /* renamed from: c */
    public static String[] m8886c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return str.split(str2);
    }

    /* renamed from: a */
    public static String m8893a(Serializable serializable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.close();
        return new String(C6636f.m9094a(byteArrayOutputStream.toByteArray()));
    }
}
