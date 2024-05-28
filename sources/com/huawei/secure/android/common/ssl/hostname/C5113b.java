package com.huawei.secure.android.common.ssl.hostname;

import com.huawei.secure.android.common.ssl.util.C5118e;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.SSLException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.ssl.hostname.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5113b {

    /* renamed from: a */
    private static final Pattern f12085a = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    /* renamed from: b */
    private static final String[] f12086b = {"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};

    static {
        Arrays.sort(f12086b);
    }

    /* renamed from: a */
    public static final void m13904a(String str, X509Certificate x509Certificate, boolean z) throws SSLException {
        String[] m13902a = m13902a(x509Certificate);
        String[] m13900b = m13900b(x509Certificate);
        C5118e.m13856a("", "cn is : " + Arrays.toString(m13902a));
        C5118e.m13856a("", "san is : " + Arrays.toString(m13900b));
        m13903a(str, m13902a, m13900b, z);
    }

    /* renamed from: b */
    public static String[] m13900b(X509Certificate x509Certificate) {
        Collection<List<?>> collection;
        LinkedList linkedList = new LinkedList();
        try {
            collection = x509Certificate.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
            C5118e.m13855a("", "Error parsing certificate.", e);
            collection = null;
        }
        if (collection != null) {
            for (List<?> list : collection) {
                if (((Integer) list.get(0)).intValue() == 2) {
                    linkedList.add((String) list.get(1));
                }
            }
        }
        if (linkedList.isEmpty()) {
            return null;
        }
        String[] strArr = new String[linkedList.size()];
        linkedList.toArray(strArr);
        return strArr;
    }

    /* renamed from: c */
    private static boolean m13899c(String str) {
        return f12085a.matcher(str).matches();
    }

    /* renamed from: a */
    public static final void m13903a(String str, String[] strArr, String[] strArr2, boolean z) throws SSLException {
        LinkedList linkedList = new LinkedList();
        if (strArr != null && strArr.length > 0 && strArr[0] != null) {
            linkedList.add(strArr[0]);
        }
        if (strArr2 != null) {
            for (String str2 : strArr2) {
                if (str2 != null) {
                    linkedList.add(str2);
                }
            }
        }
        if (!linkedList.isEmpty()) {
            StringBuffer stringBuffer = new StringBuffer();
            String lowerCase = str.trim().toLowerCase(Locale.ENGLISH);
            Iterator it = linkedList.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                String lowerCase2 = ((String) it.next()).toLowerCase(Locale.ENGLISH);
                stringBuffer.append(" <");
                stringBuffer.append(lowerCase2);
                stringBuffer.append('>');
                if (it.hasNext()) {
                    stringBuffer.append(" OR");
                }
                if (lowerCase2.startsWith("*.") && lowerCase2.indexOf(46, 2) != -1 && m13905a(lowerCase2) && !m13899c(str)) {
                    boolean endsWith = lowerCase.endsWith(lowerCase2.substring(1));
                    if (!endsWith || !z) {
                        z2 = endsWith;
                        continue;
                    } else if (m13901b(lowerCase) == m13901b(lowerCase2)) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                } else {
                    z2 = lowerCase.equals(lowerCase2);
                    continue;
                }
                if (z2) {
                    break;
                }
            }
            if (z2) {
                return;
            }
            throw new SSLException("hostname in certificate didn't match: <" + str + "> !=" + ((Object) stringBuffer));
        }
        throw new SSLException("Certificate for <" + str + "> doesn't contain CN or DNS subjectAlt");
    }

    /* renamed from: b */
    public static int m13901b(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '.') {
                i++;
            }
        }
        return i;
    }

    /* renamed from: a */
    public static boolean m13905a(String str) {
        int length = str.length();
        if (length < 7 || length > 9) {
            return true;
        }
        int i = length - 3;
        if (str.charAt(i) == '.') {
            return Arrays.binarySearch(f12086b, str.substring(2, i)) < 0;
        }
        return true;
    }

    /* renamed from: a */
    public static String[] m13902a(X509Certificate x509Certificate) {
        List<String> m13910b = new C5112a(x509Certificate.getSubjectX500Principal()).m13910b("cn");
        if (m13910b.isEmpty()) {
            return null;
        }
        String[] strArr = new String[m13910b.size()];
        m13910b.toArray(strArr);
        return strArr;
    }
}
