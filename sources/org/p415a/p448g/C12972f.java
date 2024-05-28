package org.p415a.p448g;

import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12972f {

    /* renamed from: a */
    private static final ThreadLocal f26251a = new ThreadLocal();

    /* renamed from: a */
    public static boolean m394a(String str) {
        try {
            String m393b = m393b(str);
            if (m393b != null) {
                return "true".equals(C12975h.m387b(m393b));
            }
        } catch (AccessControlException unused) {
        }
        return false;
    }

    /* renamed from: b */
    private static String m393b(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.a.g.f.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                Map map = (Map) C12972f.f26251a.get();
                return map != null ? map.get(str) : System.getProperty(str);
            }
        });
    }
}
