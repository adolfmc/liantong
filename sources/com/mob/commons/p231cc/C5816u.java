package com.mob.commons.p231cc;

import com.mob.commons.C5855l;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.cc.u */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5816u {
    /* renamed from: a */
    public static int m12390a() {
        return 70;
    }

    private C5816u() {
    }

    /* renamed from: a */
    public static C5820c m12388a(String... strArr) {
        return m12389a((Object[]) strArr);
    }

    /* renamed from: a */
    public static C5820c m12387a(byte[]... bArr) {
        return m12389a((Object[]) bArr);
    }

    /* renamed from: a */
    private static C5820c m12389a(Object[] objArr) {
        if (objArr.length == 0) {
            return null;
        }
        C5820c c5820c = new C5820c(objArr[0]);
        for (int i = 1; i < objArr.length; i++) {
            c5820c.m12381a(objArr[i]);
        }
        return c5820c;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.commons.cc.u$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5820c {

        /* renamed from: a */
        private C5821d f14304a;

        private C5820c(Object obj) {
            this.f14304a = new C5821d(obj);
        }

        /* renamed from: a */
        public C5820c m12381a(Object obj) {
            this.f14304a.m12374a(obj);
            return this;
        }

        /* renamed from: a */
        public C5821d m12379a(String str, Object obj) {
            return this.f14304a.m12371a(str, obj);
        }

        /* renamed from: a */
        public C5821d m12380a(String str, Class<?> cls) {
            return this.f14304a.m12372a(str, cls);
        }

        /* renamed from: a */
        public void m12382a() throws Throwable {
            this.f14304a.m12378a();
        }
    }

    /* renamed from: com.mob.commons.cc.u$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5821d {

        /* renamed from: a */
        private ArrayList<Object> f14305a;

        /* renamed from: b */
        private ArrayList<Object> f14306b;

        /* renamed from: c */
        private HashMap<String, Object> f14307c;

        /* renamed from: d */
        private HashMap<String, Object> f14308d;

        /* renamed from: e */
        private String f14309e;

        /* renamed from: f */
        private HashMap<Class<?>, Class<? extends InterfaceC5812q<?>>> f14310f;

        private C5821d(Object obj) {
            this.f14305a = new ArrayList<>();
            this.f14305a.add(obj);
            this.f14306b = new ArrayList<>();
            this.f14307c = new HashMap<>();
            this.f14308d = new HashMap<>();
            this.f14310f = new HashMap<>();
            this.f14307c.put("t_map", this.f14308d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m12374a(Object obj) {
            this.f14305a.add(obj);
        }

        /* renamed from: a */
        public C5821d m12371a(String str, Object obj) {
            this.f14307c.put(str, obj);
            return this;
        }

        /* renamed from: a */
        public C5821d m12372a(String str, Class<?> cls) {
            C5815t.f14298a.put(str, cls);
            return this;
        }

        /* renamed from: a */
        public C5821d m12373a(String str) {
            this.f14309e = str;
            return this;
        }

        /* renamed from: a */
        public <T> C5821d m12375a(Class<T> cls, Class<? extends InterfaceC5812q<T>> cls2) {
            this.f14310f.put(cls, cls2);
            return this;
        }

        /* renamed from: a */
        public void m12378a() throws Throwable {
            byte[] bArr;
            InputStream byteArrayInputStream;
            ArrayList<C5822v> arrayList = new ArrayList<>();
            String str = this.f14309e;
            if (str != null) {
                bArr = str.getBytes("UTF-8");
                System.arraycopy(bArr, 0, new byte[16], 0, Math.min(bArr.length, 16));
            } else {
                bArr = null;
            }
            try {
                C5813r c5813r = new C5813r();
                Iterator<Object> it = this.f14305a.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof String) {
                        byteArrayInputStream = new FileInputStream((String) next);
                    } else if (next instanceof byte[]) {
                        byteArrayInputStream = new ByteArrayInputStream((byte[]) next);
                    } else {
                        throw new ClassCastException("program is not string or byte array");
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    m12376a(byteArrayInputStream, arrayList, c5813r);
                    this.f14308d.put("l_t", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
                for (Map.Entry<Class<?>, Class<? extends InterfaceC5812q<?>>> entry : this.f14310f.entrySet()) {
                    c5813r.m12402a(entry.getKey(), entry.getValue());
                }
                new C5815t(arrayList, this.f14306b).m12391a(this.f14307c, c5813r);
            } catch (Throwable th) {
                th = th;
                if (bArr != null) {
                    String cls = th.getMessage() == null ? th.getClass().toString() : th.getMessage();
                    if (th instanceof C5814s) {
                        th = th.getCause();
                    }
                    throw new C5814s(m12369a(bArr, cls + " " + m12370a(th)), th);
                }
                throw th;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x003d A[Catch: all -> 0x0036, TRY_LEAVE, TryCatch #1 {all -> 0x0036, blocks: (B:23:0x0039, B:25:0x003d, B:29:0x0049), top: B:42:0x0039 }] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0049 A[Catch: all -> 0x0036, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0036, blocks: (B:23:0x0039, B:25:0x003d, B:29:0x0049), top: B:42:0x0039 }] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.lang.String m12370a(java.lang.Throwable r4) {
            /*
                r3 = this;
                if (r4 != 0) goto L5
                java.lang.String r4 = ""
                return r4
            L5:
                r0 = 0
                r1 = r4
            L7:
                if (r1 == 0) goto L15
                boolean r2 = r1 instanceof java.net.UnknownHostException     // Catch: java.lang.Throwable -> L38
                if (r2 == 0) goto L10
                java.lang.String r4 = ""
                return r4
            L10:
                java.lang.Throwable r1 = r1.getCause()     // Catch: java.lang.Throwable -> L38
                goto L7
            L15:
                java.io.StringWriter r1 = new java.io.StringWriter     // Catch: java.lang.Throwable -> L38
                r1.<init>()     // Catch: java.lang.Throwable -> L38
                java.io.PrintWriter r0 = new java.io.PrintWriter     // Catch: java.lang.Throwable -> L33
                r0.<init>(r1)     // Catch: java.lang.Throwable -> L33
                r4.printStackTrace(r0)     // Catch: java.lang.Throwable -> L33
                r0.flush()     // Catch: java.lang.Throwable -> L33
                r0.close()     // Catch: java.lang.Throwable -> L33
                java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L33
                r1.close()     // Catch: java.lang.Throwable -> L2f
            L2f:
                return r4
            L30:
                r4 = move-exception
                r0 = r1
                goto L53
            L33:
                r4 = move-exception
                r0 = r1
                goto L39
            L36:
                r4 = move-exception
                goto L53
            L38:
                r4 = move-exception
            L39:
                boolean r1 = r4 instanceof java.lang.OutOfMemoryError     // Catch: java.lang.Throwable -> L36
                if (r1 == 0) goto L49
                java.lang.String r4 = "023?ggWhk-glLkfe*fngmfl]fehOgl6kTflfk=gLggkhgfgffh"
                java.lang.String r4 = com.mob.commons.C5855l.m12238a(r4)     // Catch: java.lang.Throwable -> L36
                if (r0 == 0) goto L48
                r0.close()     // Catch: java.lang.Throwable -> L48
            L48:
                return r4
            L49:
                java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> L36
                if (r0 == 0) goto L52
                r0.close()     // Catch: java.lang.Throwable -> L52
            L52:
                return r4
            L53:
                if (r0 == 0) goto L58
                r0.close()     // Catch: java.lang.Throwable -> L58
            L58:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p231cc.C5816u.C5821d.m12370a(java.lang.Throwable):java.lang.String");
        }

        /* renamed from: a */
        private String m12369a(byte[] bArr, String str) {
            Cipher cipher;
            if (bArr != null) {
                try {
                    byte[] bytes = str.getBytes("UTF-8");
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, C5855l.m12238a("003Ogniigl"));
                    StringBuilder sb = new StringBuilder();
                    sb.append(C5855l.m12238a("003 gniigl"));
                    sb.append(C5855l.m12238a("003nKiiil"));
                    sb.append(C5855l.m12238a("008=hkMnJimkeilglklim"));
                    sb.append(C5855l.m12238a("006fFfefefk:g@gg"));
                    Provider provider = Security.getProvider(C5855l.m12238a("002Chkil"));
                    if (provider != null) {
                        cipher = Cipher.getInstance(sb.toString(), provider);
                    } else {
                        cipher = Cipher.getInstance(sb.toString(), C5855l.m12238a("002Hhkil"));
                    }
                    cipher.init(1, secretKeySpec);
                    byte[] bArr2 = new byte[cipher.getOutputSize(bytes.length)];
                    cipher.doFinal(bArr2, cipher.update(bytes, 0, bytes.length, bArr2, 0));
                    return new BigInteger(1, bArr2).toString(16);
                } catch (Throwable unused) {
                    return "";
                }
            }
            return str;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:6|7|8|(21:(24:12|13|14|15|16|17|(1:19)|20|21|(1:23)|24|25|(1:27)|28|29|(1:31)|32|33|(1:35)|36|37|(9:39|40|41|43|44|45|(3:47|48|49)|58|59)(3:123|(1:125)|126)|60|(4:62|(1:64)(1:115)|65|(4:(3:68|(2:70|71)(1:73)|72)|74|75|(8:77|78|79|(7:89|90|91|92|94|95|96)(1:81)|82|83|84|85)(2:111|112))(2:113|114))(2:116|117))|16|17|(0)|20|21|(0)|24|25|(0)|28|29|(0)|32|33|(0)|36|37|(0)(0)|60|(0)(0))|142|143|144|145|13|14|15) */
        /* JADX WARN: Code restructure failed: missing block: B:101:0x01e3, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:102:0x01e4, code lost:
            r7 = r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:103:0x01e6, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:107:0x01eb, code lost:
            r6 = null;
         */
        /* JADX WARN: Removed duplicated region for block: B:109:0x01ee A[Catch: Throwable -> 0x01f5, TRY_ENTER, TryCatch #10 {Throwable -> 0x01f5, blocks: (B:109:0x01ee, B:110:0x01f2), top: B:134:0x01ec }] */
        /* JADX WARN: Removed duplicated region for block: B:110:0x01f2 A[Catch: Throwable -> 0x01f5, TRY_LEAVE, TryCatch #10 {Throwable -> 0x01f5, blocks: (B:109:0x01ee, B:110:0x01f2), top: B:134:0x01ec }] */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0043 A[Catch: all -> 0x01e1, LOOP:0: B:18:0x0041->B:19:0x0043, LOOP_END, TryCatch #2 {all -> 0x01e1, blocks: (B:17:0x0033, B:19:0x0043, B:20:0x0051, B:22:0x0058, B:23:0x0066, B:25:0x006d, B:26:0x007b, B:28:0x0082, B:29:0x0090, B:31:0x0097, B:32:0x00a5, B:34:0x00ab, B:43:0x00da, B:56:0x00fb, B:58:0x0103, B:60:0x011a, B:62:0x012d, B:65:0x013f, B:67:0x014c, B:68:0x014f, B:69:0x015a, B:71:0x0162, B:93:0x01c9, B:94:0x01d0, B:95:0x01d1, B:96:0x01d8, B:61:0x0124, B:97:0x01d9, B:98:0x01e0, B:50:0x00e6, B:52:0x00ed, B:51:0x00ea, B:55:0x00f1), top: B:119:0x0033 }] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0058 A[Catch: all -> 0x01e1, LOOP:1: B:21:0x0056->B:22:0x0058, LOOP_END, TryCatch #2 {all -> 0x01e1, blocks: (B:17:0x0033, B:19:0x0043, B:20:0x0051, B:22:0x0058, B:23:0x0066, B:25:0x006d, B:26:0x007b, B:28:0x0082, B:29:0x0090, B:31:0x0097, B:32:0x00a5, B:34:0x00ab, B:43:0x00da, B:56:0x00fb, B:58:0x0103, B:60:0x011a, B:62:0x012d, B:65:0x013f, B:67:0x014c, B:68:0x014f, B:69:0x015a, B:71:0x0162, B:93:0x01c9, B:94:0x01d0, B:95:0x01d1, B:96:0x01d8, B:61:0x0124, B:97:0x01d9, B:98:0x01e0, B:50:0x00e6, B:52:0x00ed, B:51:0x00ea, B:55:0x00f1), top: B:119:0x0033 }] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x006d A[Catch: all -> 0x01e1, LOOP:2: B:24:0x006b->B:25:0x006d, LOOP_END, TryCatch #2 {all -> 0x01e1, blocks: (B:17:0x0033, B:19:0x0043, B:20:0x0051, B:22:0x0058, B:23:0x0066, B:25:0x006d, B:26:0x007b, B:28:0x0082, B:29:0x0090, B:31:0x0097, B:32:0x00a5, B:34:0x00ab, B:43:0x00da, B:56:0x00fb, B:58:0x0103, B:60:0x011a, B:62:0x012d, B:65:0x013f, B:67:0x014c, B:68:0x014f, B:69:0x015a, B:71:0x0162, B:93:0x01c9, B:94:0x01d0, B:95:0x01d1, B:96:0x01d8, B:61:0x0124, B:97:0x01d9, B:98:0x01e0, B:50:0x00e6, B:52:0x00ed, B:51:0x00ea, B:55:0x00f1), top: B:119:0x0033 }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0082 A[Catch: all -> 0x01e1, LOOP:3: B:27:0x0080->B:28:0x0082, LOOP_END, TryCatch #2 {all -> 0x01e1, blocks: (B:17:0x0033, B:19:0x0043, B:20:0x0051, B:22:0x0058, B:23:0x0066, B:25:0x006d, B:26:0x007b, B:28:0x0082, B:29:0x0090, B:31:0x0097, B:32:0x00a5, B:34:0x00ab, B:43:0x00da, B:56:0x00fb, B:58:0x0103, B:60:0x011a, B:62:0x012d, B:65:0x013f, B:67:0x014c, B:68:0x014f, B:69:0x015a, B:71:0x0162, B:93:0x01c9, B:94:0x01d0, B:95:0x01d1, B:96:0x01d8, B:61:0x0124, B:97:0x01d9, B:98:0x01e0, B:50:0x00e6, B:52:0x00ed, B:51:0x00ea, B:55:0x00f1), top: B:119:0x0033 }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0097 A[Catch: all -> 0x01e1, LOOP:4: B:30:0x0095->B:31:0x0097, LOOP_END, TryCatch #2 {all -> 0x01e1, blocks: (B:17:0x0033, B:19:0x0043, B:20:0x0051, B:22:0x0058, B:23:0x0066, B:25:0x006d, B:26:0x007b, B:28:0x0082, B:29:0x0090, B:31:0x0097, B:32:0x00a5, B:34:0x00ab, B:43:0x00da, B:56:0x00fb, B:58:0x0103, B:60:0x011a, B:62:0x012d, B:65:0x013f, B:67:0x014c, B:68:0x014f, B:69:0x015a, B:71:0x0162, B:93:0x01c9, B:94:0x01d0, B:95:0x01d1, B:96:0x01d8, B:61:0x0124, B:97:0x01d9, B:98:0x01e0, B:50:0x00e6, B:52:0x00ed, B:51:0x00ea, B:55:0x00f1), top: B:119:0x0033 }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00ab A[Catch: all -> 0x01e1, TRY_LEAVE, TryCatch #2 {all -> 0x01e1, blocks: (B:17:0x0033, B:19:0x0043, B:20:0x0051, B:22:0x0058, B:23:0x0066, B:25:0x006d, B:26:0x007b, B:28:0x0082, B:29:0x0090, B:31:0x0097, B:32:0x00a5, B:34:0x00ab, B:43:0x00da, B:56:0x00fb, B:58:0x0103, B:60:0x011a, B:62:0x012d, B:65:0x013f, B:67:0x014c, B:68:0x014f, B:69:0x015a, B:71:0x0162, B:93:0x01c9, B:94:0x01d0, B:95:0x01d1, B:96:0x01d8, B:61:0x0124, B:97:0x01d9, B:98:0x01e0, B:50:0x00e6, B:52:0x00ed, B:51:0x00ea, B:55:0x00f1), top: B:119:0x0033 }] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00ee  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0103 A[Catch: all -> 0x01e1, TryCatch #2 {all -> 0x01e1, blocks: (B:17:0x0033, B:19:0x0043, B:20:0x0051, B:22:0x0058, B:23:0x0066, B:25:0x006d, B:26:0x007b, B:28:0x0082, B:29:0x0090, B:31:0x0097, B:32:0x00a5, B:34:0x00ab, B:43:0x00da, B:56:0x00fb, B:58:0x0103, B:60:0x011a, B:62:0x012d, B:65:0x013f, B:67:0x014c, B:68:0x014f, B:69:0x015a, B:71:0x0162, B:93:0x01c9, B:94:0x01d0, B:95:0x01d1, B:96:0x01d8, B:61:0x0124, B:97:0x01d9, B:98:0x01e0, B:50:0x00e6, B:52:0x00ed, B:51:0x00ea, B:55:0x00f1), top: B:119:0x0033 }] */
        /* JADX WARN: Removed duplicated region for block: B:97:0x01d9 A[Catch: all -> 0x01e1, TryCatch #2 {all -> 0x01e1, blocks: (B:17:0x0033, B:19:0x0043, B:20:0x0051, B:22:0x0058, B:23:0x0066, B:25:0x006d, B:26:0x007b, B:28:0x0082, B:29:0x0090, B:31:0x0097, B:32:0x00a5, B:34:0x00ab, B:43:0x00da, B:56:0x00fb, B:58:0x0103, B:60:0x011a, B:62:0x012d, B:65:0x013f, B:67:0x014c, B:68:0x014f, B:69:0x015a, B:71:0x0162, B:93:0x01c9, B:94:0x01d0, B:95:0x01d1, B:96:0x01d8, B:61:0x0124, B:97:0x01d9, B:98:0x01e0, B:50:0x00e6, B:52:0x00ed, B:51:0x00ea, B:55:0x00f1), top: B:119:0x0033 }] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void m12376a(java.io.InputStream r16, java.util.ArrayList<com.mob.commons.p231cc.C5822v> r17, com.mob.commons.p231cc.C5813r r18) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 502
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p231cc.C5816u.C5821d.m12376a(java.io.InputStream, java.util.ArrayList, com.mob.commons.cc.r):void");
        }
    }

    /* renamed from: com.mob.commons.cc.u$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5818a {

        /* renamed from: a */
        protected ArrayList<Object> f14301a;

        /* renamed from: b */
        protected DataInputStream f14302b;

        /* renamed from: c */
        protected int f14303c;

        private C5818a(ArrayList<Object> arrayList, DataInputStream dataInputStream, int i) {
            this.f14301a = arrayList;
            this.f14302b = dataInputStream;
            this.f14303c = i;
        }

        /* renamed from: a */
        public void mo12385a() throws Throwable {
            this.f14302b.readShort();
        }

        /* renamed from: b */
        public <T> T mo12383b() throws Throwable {
            return (T) this.f14301a.get(this.f14302b.readShort());
        }

        /* renamed from: a */
        public void mo12384a(C5822v c5822v) throws Throwable {
            c5822v.f14312b = (String) this.f14301a.get(this.f14302b.readShort());
            c5822v.f14313c = this.f14302b.readShort();
        }

        /* renamed from: c */
        public int m12386c() {
            return this.f14303c;
        }
    }

    /* renamed from: com.mob.commons.cc.u$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5819b extends C5818a {
        private C5819b(ArrayList<Object> arrayList, DataInputStream dataInputStream, int i) {
            super(arrayList, dataInputStream, i);
        }

        @Override // com.mob.commons.p231cc.C5816u.C5818a
        /* renamed from: a */
        public void mo12385a() throws Throwable {
            this.f14302b.readInt();
        }

        @Override // com.mob.commons.p231cc.C5816u.C5818a
        /* renamed from: b */
        public <T> T mo12383b() throws Throwable {
            return (T) this.f14301a.get(this.f14302b.readInt());
        }

        @Override // com.mob.commons.p231cc.C5816u.C5818a
        /* renamed from: a */
        public void mo12384a(C5822v c5822v) throws Throwable {
            c5822v.f14312b = (String) this.f14301a.get(this.f14302b.readInt());
            c5822v.f14313c = this.f14302b.readInt();
        }
    }
}
