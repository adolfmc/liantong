package com.bytedance.sdk.openadsdk.api.plugin.p184mb;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb.mb */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4015mb implements HostnameVerifier {

    /* renamed from: mb */
    public static final C4015mb f9592mb = new C4015mb();

    /* renamed from: ox */
    private static final Pattern f9593ox = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private C4015mb() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return m16475mb(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException unused) {
            return false;
        }
    }

    /* renamed from: mb */
    private boolean m16475mb(String str, X509Certificate x509Certificate) {
        if (m16477mb(str)) {
            return m16473ox(str, x509Certificate);
        }
        return m16479b(str, x509Certificate);
    }

    /* renamed from: ox */
    private boolean m16473ox(String str, X509Certificate x509Certificate) {
        List<String> m16474mb = m16474mb(x509Certificate, 7);
        int size = m16474mb.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(m16474mb.get(i))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private boolean m16479b(String str, X509Certificate x509Certificate) {
        String m16466mb;
        String lowerCase = str.toLowerCase(Locale.US);
        List<String> m16474mb = m16474mb(x509Certificate, 2);
        int size = m16474mb.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            if (m16476mb(lowerCase, m16474mb.get(i))) {
                return true;
            }
            i++;
            z = true;
        }
        if (z || (m16466mb = new C4016ox(x509Certificate.getSubjectX500Principal()).m16466mb("cn")) == null) {
            return false;
        }
        return m16476mb(lowerCase, m16466mb);
    }

    /* renamed from: mb */
    private static boolean m16477mb(String str) {
        return f9593ox.matcher(str).matches();
    }

    /* renamed from: mb */
    private static List<String> m16474mb(X509Certificate x509Certificate, int i) {
        Integer num;
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && (num = (Integer) list.get(0)) != null && num.intValue() == i && (str = (String) list.get(1)) != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    /* renamed from: mb */
    private boolean m16476mb(String str, String str2) {
        if (str == null || str.length() == 0 || str.startsWith(".") || str.endsWith("..") || str2 == null || str2.length() == 0 || str2.startsWith(".") || str2.endsWith("..")) {
            return false;
        }
        if (!str.endsWith(".")) {
            str = str + '.';
        }
        if (!str2.endsWith(".")) {
            str2 = str2 + '.';
        }
        String lowerCase = str2.toLowerCase(Locale.US);
        if (!lowerCase.contains("*")) {
            return str.equals(lowerCase);
        }
        if (!lowerCase.startsWith("*.") || lowerCase.indexOf(42, 1) != -1 || str.length() < lowerCase.length() || "*.".equals(lowerCase)) {
            return false;
        }
        String substring = lowerCase.substring(1);
        if (str.endsWith(substring)) {
            int length = str.length() - substring.length();
            return length <= 0 || str.lastIndexOf(46, length - 1) == -1;
        }
        return false;
    }
}
