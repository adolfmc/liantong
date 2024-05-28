package org.json.alipay;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.json.alipay.b */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class C13436b {

    /* renamed from: a */
    public static final Object f27414a = new C13437a((byte) 0);

    /* renamed from: b */
    private Map f27415b;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.json.alipay.b$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class C13437a {
        private C13437a() {
        }

        /* synthetic */ C13437a(byte b) {
            this();
        }

        protected final Object clone() {
            return this;
        }

        public final boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public final String toString() {
            return "null";
        }
    }

    public C13436b() {
        this.f27415b = new HashMap();
    }

    public C13436b(String str) {
        this(new C13438c(str));
    }

    public C13436b(Map map) {
        this.f27415b = map == null ? new HashMap() : map;
    }

    public C13436b(C13438c c13438c) {
        this();
        if (c13438c.m213c() != '{') {
            throw c13438c.m215a("A JSONObject text must begin with '{'");
        }
        while (true) {
            char m213c = c13438c.m213c();
            if (m213c == 0) {
                throw c13438c.m215a("A JSONObject text must end with '}'");
            }
            if (m213c == '}') {
                return;
            }
            c13438c.m217a();
            String obj = c13438c.m212d().toString();
            char m213c2 = c13438c.m213c();
            if (m213c2 == '=') {
                if (c13438c.m214b() != '>') {
                    c13438c.m217a();
                }
            } else if (m213c2 != ':') {
                throw c13438c.m215a("Expected a ':' after a key");
            }
            Object m212d = c13438c.m212d();
            if (obj == null) {
                throw new JSONException("Null key.");
            }
            if (m212d != null) {
                m220b(m212d);
                this.f27415b.put(obj, m212d);
            } else {
                this.f27415b.remove(obj);
            }
            char m213c3 = c13438c.m213c();
            if (m213c3 != ',' && m213c3 != ';') {
                if (m213c3 != '}') {
                    throw c13438c.m215a("Expected a ',' or '}'");
                }
                return;
            } else if (c13438c.m213c() == '}') {
                return;
            } else {
                c13438c.m217a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m222a(Object obj) {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (!(obj instanceof Number)) {
            return ((obj instanceof Boolean) || (obj instanceof C13436b) || (obj instanceof C13435a)) ? obj.toString() : obj instanceof Map ? new C13436b((Map) obj).toString() : obj instanceof Collection ? new C13435a((Collection) obj).toString() : obj.getClass().isArray() ? new C13435a(obj).toString() : m218c(obj.toString());
        }
        Number number = (Number) obj;
        if (number != null) {
            m220b(number);
            String obj2 = number.toString();
            if (obj2.indexOf(46) <= 0 || obj2.indexOf(101) >= 0 || obj2.indexOf(69) >= 0) {
                return obj2;
            }
            while (obj2.endsWith("0")) {
                obj2 = obj2.substring(0, obj2.length() - 1);
            }
            return obj2.endsWith(".") ? obj2.substring(0, obj2.length() - 1) : obj2;
        }
        throw new JSONException("Null pointer");
    }

    /* renamed from: b */
    private static void m220b(Object obj) {
        if (obj != null) {
            if (obj instanceof Double) {
                Double d = (Double) obj;
                if (d.isInfinite() || d.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            } else if (obj instanceof Float) {
                Float f = (Float) obj;
                if (f.isInfinite() || f.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0085, code lost:
        if (r4 == '<') goto L36;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m218c(java.lang.String r8) {
        /*
            if (r8 == 0) goto L99
            int r0 = r8.length()
            if (r0 != 0) goto La
            goto L99
        La:
            int r0 = r8.length()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            int r2 = r0 + 4
            r1.<init>(r2)
            r2 = 34
            r1.append(r2)
            r3 = 0
            r4 = r3
        L1c:
            if (r3 >= r0) goto L91
            char r5 = r8.charAt(r3)
            r6 = 92
            if (r5 == r2) goto L87
            r7 = 47
            if (r5 == r7) goto L83
            if (r5 == r6) goto L87
            switch(r5) {
                case 8: goto L7d;
                case 9: goto L7a;
                case 10: goto L77;
                default: goto L2f;
            }
        L2f:
            switch(r5) {
                case 12: goto L74;
                case 13: goto L71;
                default: goto L32;
            }
        L32:
            r4 = 32
            if (r5 < r4) goto L46
            r4 = 128(0x80, float:1.794E-43)
            if (r5 < r4) goto L3e
            r4 = 160(0xa0, float:2.24E-43)
            if (r5 < r4) goto L46
        L3e:
            r4 = 8192(0x2000, float:1.14794E-41)
            if (r5 < r4) goto L8a
            r4 = 8448(0x2100, float:1.1838E-41)
            if (r5 >= r4) goto L8a
        L46:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "000"
            r4.<init>(r6)
            java.lang.String r6 = java.lang.Integer.toHexString(r5)
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "\\u"
            r6.<init>(r7)
            int r7 = r4.length()
            int r7 = r7 + (-4)
            java.lang.String r4 = r4.substring(r7)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            goto L7f
        L71:
            java.lang.String r4 = "\\r"
            goto L7f
        L74:
            java.lang.String r4 = "\\f"
            goto L7f
        L77:
            java.lang.String r4 = "\\n"
            goto L7f
        L7a:
            java.lang.String r4 = "\\t"
            goto L7f
        L7d:
            java.lang.String r4 = "\\b"
        L7f:
            r1.append(r4)
            goto L8d
        L83:
            r7 = 60
            if (r4 != r7) goto L8a
        L87:
            r1.append(r6)
        L8a:
            r1.append(r5)
        L8d:
            int r3 = r3 + 1
            r4 = r5
            goto L1c
        L91:
            r1.append(r2)
            java.lang.String r8 = r1.toString()
            return r8
        L99:
            java.lang.String r8 = "\"\""
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.C13436b.m218c(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public final Object m221a(String str) {
        Object obj = str == null ? null : this.f27415b.get(str);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONObject[" + m218c(str) + "] not found.");
    }

    /* renamed from: a */
    public final Iterator m223a() {
        return this.f27415b.keySet().iterator();
    }

    /* renamed from: b */
    public final boolean m219b(String str) {
        return this.f27415b.containsKey(str);
    }

    public String toString() {
        try {
            Iterator m223a = m223a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (m223a.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = m223a.next();
                stringBuffer.append(m218c(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(m222a(this.f27415b.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
