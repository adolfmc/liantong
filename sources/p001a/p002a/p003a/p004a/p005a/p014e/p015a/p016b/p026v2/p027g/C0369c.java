package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.p027g;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0499z1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0361a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0363c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0365e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.InterfaceC0366f;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.C0684f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.v2.g.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0369c {
    /* renamed from: a */
    public static int m23515a(char c) {
        if ('0' > c || c > '9') {
            return (('a' > c || c > 'f') ? c - 'A' : c - 'a') + 10;
        }
        return c - '0';
    }

    /* renamed from: a */
    public static C0363c[] m23508a(String str, InterfaceC0366f interfaceC0366f) {
        C0370d c0370d = new C0370d(str);
        C0365e c0365e = new C0365e(interfaceC0366f);
        while (c0370d.m23497a()) {
            String m23496b = c0370d.m23496b();
            if (m23496b.indexOf(43) > 0) {
                C0370d c0370d2 = new C0370d(m23496b, '+');
                C0370d c0370d3 = new C0370d(c0370d2.m23496b(), '=');
                String m23496b2 = c0370d3.m23496b();
                if (c0370d3.m23497a()) {
                    String m23496b3 = c0370d3.m23496b();
                    C0178n mo23516b = interfaceC0366f.mo23516b(m23496b2.trim());
                    if (c0370d2.m23497a()) {
                        Vector vector = new Vector();
                        Vector vector2 = new Vector();
                        vector.addElement(mo23516b);
                        vector2.addElement(m23498d(m23496b3));
                        while (c0370d2.m23497a()) {
                            C0370d c0370d4 = new C0370d(c0370d2.m23496b(), '=');
                            String m23496b4 = c0370d4.m23496b();
                            if (c0370d4.m23497a()) {
                                String m23496b5 = c0370d4.m23496b();
                                vector.addElement(interfaceC0366f.mo23516b(m23496b4.trim()));
                                vector2.addElement(m23498d(m23496b5));
                            } else {
                                throw new IllegalArgumentException("badly formatted directory string");
                            }
                        }
                        c0365e.m23528a(m23504a(vector), m23500b(vector2));
                    } else {
                        c0365e.m23531a(mo23516b, m23498d(m23496b3));
                    }
                } else {
                    throw new IllegalArgumentException("badly formatted directory string");
                }
            } else {
                C0370d c0370d5 = new C0370d(m23496b, '=');
                String m23496b6 = c0370d5.m23496b();
                if (c0370d5.m23497a()) {
                    c0365e.m23531a(interfaceC0366f.mo23516b(m23496b6.trim()), m23498d(c0370d5.m23496b()));
                } else {
                    throw new IllegalArgumentException("badly formatted directory string");
                }
            }
        }
        return c0365e.m23533a().m23535j();
    }

    /* renamed from: b */
    public static boolean m23502b(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F');
    }

    /* renamed from: b */
    public static String[] m23500b(Vector vector) {
        int size = vector.size();
        String[] strArr = new String[size];
        for (int i = 0; i != size; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    /* renamed from: c */
    public static String m23499c(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() != 0) {
            char charAt = str.charAt(0);
            stringBuffer.append(charAt);
            int i = 1;
            while (i < str.length()) {
                char charAt2 = str.charAt(i);
                if (charAt != ' ' || charAt2 != ' ') {
                    stringBuffer.append(charAt2);
                }
                i++;
                charAt = charAt2;
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: d */
    public static String m23498d(String str) {
        int i;
        if (str.length() != 0 && (str.indexOf(92) >= 0 || str.indexOf(34) >= 0)) {
            char[] charArray = str.toCharArray();
            StringBuffer stringBuffer = new StringBuffer(str.length());
            if (charArray[0] == '\\' && charArray[1] == '#') {
                i = 2;
                stringBuffer.append("\\#");
            } else {
                i = 0;
            }
            boolean z = false;
            int i2 = 0;
            boolean z2 = false;
            char c = 0;
            boolean z3 = false;
            while (i != charArray.length) {
                char c2 = charArray[i];
                if (c2 != ' ') {
                    z3 = true;
                }
                if (c2 == '\"') {
                    if (z) {
                        stringBuffer.append(c2);
                    } else {
                        z2 = !z2;
                    }
                    z = false;
                } else if (c2 == '\\' && !z && !z2) {
                    i2 = stringBuffer.length();
                    z = true;
                } else if (c2 != ' ' || z || z3) {
                    if (!z || !m23502b(c2)) {
                        stringBuffer.append(c2);
                        z = false;
                    } else if (c != 0) {
                        stringBuffer.append((char) ((m23515a(c) * 16) + m23515a(c2)));
                        z = false;
                        c = 0;
                    } else {
                        c = c2;
                    }
                }
                i++;
            }
            if (stringBuffer.length() > 0) {
                while (stringBuffer.charAt(stringBuffer.length() - 1) == ' ' && i2 != stringBuffer.length() - 1) {
                    stringBuffer.setLength(stringBuffer.length() - 1);
                }
            }
            return stringBuffer.toString();
        }
        return str.trim();
    }

    /* renamed from: b */
    public static AbstractC0258r m23501b(String str) {
        try {
            return AbstractC0258r.m23755a(C0684f.m22419a(str.substring(1)));
        } catch (IOException e) {
            throw new IllegalStateException("unknown encoding in name: " + e);
        }
    }

    /* renamed from: a */
    public static C0178n[] m23504a(Vector vector) {
        int size = vector.size();
        C0178n[] c0178nArr = new C0178n[size];
        for (int i = 0; i != size; i++) {
            c0178nArr[i] = (C0178n) vector.elementAt(i);
        }
        return c0178nArr;
    }

    /* renamed from: a */
    public static String[] m23513a(C0178n c0178n, Hashtable hashtable) {
        Enumeration elements = hashtable.elements();
        int i = 0;
        int i2 = 0;
        while (elements.hasMoreElements()) {
            if (c0178n.equals(elements.nextElement())) {
                i2++;
            }
        }
        String[] strArr = new String[i2];
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            if (c0178n.equals(hashtable.get(str))) {
                strArr[i] = str;
                i++;
            }
        }
        return strArr;
    }

    /* renamed from: a */
    public static C0178n m23507a(String str, Hashtable hashtable) {
        if (C0678j.m22438d(str).startsWith("OID.")) {
            return new C0178n(str.substring(4));
        }
        if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
            return new C0178n(str);
        }
        C0178n c0178n = (C0178n) hashtable.get(C0678j.m22443b(str));
        if (c0178n != null) {
            return c0178n;
        }
        throw new IllegalArgumentException("Unknown object id - " + str + " - passed to distinguished name");
    }

    /* renamed from: a */
    public static InterfaceC0136d m23509a(String str, int i) {
        int length = (str.length() - i) / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            int i3 = (i2 * 2) + i;
            char charAt = str.charAt(i3);
            bArr[i2] = (byte) (m23515a(str.charAt(i3 + 1)) | (m23515a(charAt) << 4));
        }
        return AbstractC0258r.m23755a(bArr);
    }

    /* renamed from: a */
    public static void m23505a(StringBuffer stringBuffer, C0363c c0363c, Hashtable hashtable) {
        if (c0363c.m23543k()) {
            C0361a[] m23544j = c0363c.m23544j();
            boolean z = true;
            for (int i = 0; i != m23544j.length; i++) {
                if (z) {
                    z = false;
                } else {
                    stringBuffer.append('+');
                }
                m23506a(stringBuffer, m23544j[i], hashtable);
            }
            return;
        }
        m23506a(stringBuffer, c0363c.m23545i(), hashtable);
    }

    /* renamed from: a */
    public static void m23506a(StringBuffer stringBuffer, C0361a c0361a, Hashtable hashtable) {
        String str = (String) hashtable.get(c0361a.m23550i());
        if (str != null) {
            stringBuffer.append(str);
        } else {
            stringBuffer.append(c0361a.m23550i().m24113n());
        }
        stringBuffer.append('=');
        stringBuffer.append(m23514a(c0361a.m23549j()));
    }

    /* renamed from: a */
    public static String m23514a(InterfaceC0136d interfaceC0136d) {
        StringBuffer stringBuffer = new StringBuffer();
        if ((interfaceC0136d instanceof InterfaceC0452x) && !(interfaceC0136d instanceof C0499z1)) {
            String mo22978e = ((InterfaceC0452x) interfaceC0136d).mo22978e();
            if (mo22978e.length() > 0 && mo22978e.charAt(0) == '#') {
                stringBuffer.append("\\" + mo22978e);
            } else {
                stringBuffer.append(mo22978e);
            }
        } else {
            try {
                stringBuffer.append("#" + m23503a(C0684f.m22413b(interfaceC0136d.mo23015d().m24102a("DER"))));
            } catch (IOException unused) {
                throw new IllegalArgumentException("Other value has no encoded form");
            }
        }
        int length = stringBuffer.length();
        int i = 2;
        i = (stringBuffer.length() >= 2 && stringBuffer.charAt(0) == '\\' && stringBuffer.charAt(1) == '#') ? 0 : 0;
        while (i != length) {
            if (stringBuffer.charAt(i) == ',' || stringBuffer.charAt(i) == '\"' || stringBuffer.charAt(i) == '\\' || stringBuffer.charAt(i) == '+' || stringBuffer.charAt(i) == '=' || stringBuffer.charAt(i) == '<' || stringBuffer.charAt(i) == '>' || stringBuffer.charAt(i) == ';') {
                stringBuffer.insert(i, "\\");
                i++;
                length++;
            }
            i++;
        }
        if (stringBuffer.length() > 0) {
            for (int i2 = 0; stringBuffer.charAt(i2) == ' '; i2 += 2) {
                stringBuffer.insert(i2, "\\");
            }
        }
        for (int length2 = stringBuffer.length() - 1; length2 >= 0 && stringBuffer.charAt(length2) == ' '; length2--) {
            stringBuffer.insert(length2, '\\');
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m23503a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i != length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static String m23510a(String str) {
        String m22443b = C0678j.m22443b(str.trim());
        if (m22443b.length() > 0 && m22443b.charAt(0) == '#') {
            AbstractC0258r m23501b = m23501b(m22443b);
            if (m23501b instanceof InterfaceC0452x) {
                m22443b = C0678j.m22443b(((InterfaceC0452x) m23501b).mo22978e().trim());
            }
        }
        return m23499c(m22443b);
    }

    /* renamed from: a */
    public static boolean m23511a(C0363c c0363c, C0363c c0363c2) {
        if (c0363c.m23543k()) {
            if (c0363c2.m23543k()) {
                C0361a[] m23544j = c0363c.m23544j();
                C0361a[] m23544j2 = c0363c2.m23544j();
                if (m23544j.length != m23544j2.length) {
                    return false;
                }
                for (int i = 0; i != m23544j.length; i++) {
                    if (!m23512a(m23544j[i], m23544j2[i])) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } else if (c0363c2.m23543k()) {
            return false;
        } else {
            return m23512a(c0363c.m23545i(), c0363c2.m23545i());
        }
    }

    /* renamed from: a */
    public static boolean m23512a(C0361a c0361a, C0361a c0361a2) {
        if (c0361a == c0361a2) {
            return true;
        }
        return c0361a != null && c0361a2 != null && c0361a.m23550i().equals(c0361a2.m23550i()) && m23510a(m23514a(c0361a.m23549j())).equals(m23510a(m23514a(c0361a2.m23549j())));
    }
}
