package com.p343ta.utdid2.p346b.p347a;

import com.p343ta.utdid2.p346b.p347a.InterfaceC10318b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: com.ta.utdid2.b.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10322d {

    /* renamed from: b */
    private static final Object f19752b = new Object();

    /* renamed from: a */
    private File f19753a;

    /* renamed from: a */
    private final Object f19754a = new Object();

    /* renamed from: a */
    private HashMap<File, C10323a> f19755a = new HashMap<>();

    public C10322d(String str) {
        if (str != null && str.length() > 0) {
            this.f19753a = new File(str);
            return;
        }
        throw new RuntimeException("Directory can not be empty");
    }

    /* renamed from: a */
    private File m6419a(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    /* renamed from: a */
    private File m6422a() {
        File file;
        synchronized (this.f19754a) {
            file = this.f19753a;
        }
        return file;
    }

    /* renamed from: b */
    private File m6416b(String str) {
        File m6422a = m6422a();
        return m6419a(m6422a, str + ".xml");
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x004d, code lost:
        if (r0 == null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x006c, code lost:
        if (r3 == null) goto L36;
     */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.p343ta.utdid2.p346b.p347a.InterfaceC10318b m6418a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.io.File r5 = r4.m6416b(r5)
            java.lang.Object r0 = com.p343ta.utdid2.p346b.p347a.C10322d.f19752b
            monitor-enter(r0)
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r1 = r4.f19755a     // Catch: java.lang.Throwable -> La2
            java.lang.Object r1 = r1.get(r5)     // Catch: java.lang.Throwable -> La2
            com.ta.utdid2.b.a.d$a r1 = (com.p343ta.utdid2.p346b.p347a.C10322d.C10323a) r1     // Catch: java.lang.Throwable -> La2
            if (r1 == 0) goto L19
            boolean r2 = r1.m6407d()     // Catch: java.lang.Throwable -> La2
            if (r2 != 0) goto L19
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La2
            return r1
        L19:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La2
            java.io.File r0 = m6420a(r5)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L2a
            r5.delete()
            r0.renameTo(r5)
        L2a:
            boolean r0 = r5.exists()
            r2 = 0
            if (r0 == 0) goto L7f
            boolean r0 = r5.canRead()
            if (r0 == 0) goto L7f
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c org.xmlpull.v1.XmlPullParserException -> L50
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c org.xmlpull.v1.XmlPullParserException -> L50
            java.util.HashMap r2 = com.p343ta.utdid2.p346b.p347a.C10325e.m6398a(r0)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4d org.xmlpull.v1.XmlPullParserException -> L51
            r0.close()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4d org.xmlpull.v1.XmlPullParserException -> L51
        L43:
            r0.close()     // Catch: java.lang.Throwable -> L7f
            goto L7f
        L47:
            r5 = move-exception
            r2 = r0
            goto L74
        L4a:
            r5 = move-exception
            goto L74
        L4c:
            r0 = r2
        L4d:
            if (r0 == 0) goto L7f
            goto L43
        L50:
            r0 = r2
        L51:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L6b
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L6b
            int r0 = r3.available()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L6c
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L6c
            r3.read(r0)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L6c
            goto L6e
        L60:
            r5 = move-exception
            r2 = r3
            goto L65
        L63:
            r5 = move-exception
            r2 = r0
        L65:
            if (r2 == 0) goto L6a
            r2.close()     // Catch: java.lang.Throwable -> L6a
        L6a:
            throw r5     // Catch: java.lang.Throwable -> L4a
        L6b:
            r3 = r0
        L6c:
            if (r3 == 0) goto L7a
        L6e:
            r3.close()     // Catch: java.lang.Throwable -> L7a
            goto L7a
        L72:
            r5 = move-exception
            r2 = r3
        L74:
            if (r2 == 0) goto L79
            r2.close()     // Catch: java.lang.Throwable -> L79
        L79:
            throw r5
        L7a:
            if (r3 == 0) goto L7f
            r3.close()     // Catch: java.lang.Throwable -> L7f
        L7f:
            java.lang.Object r3 = com.p343ta.utdid2.p346b.p347a.C10322d.f19752b
            monitor-enter(r3)
            if (r1 == 0) goto L88
            r1.m6410a(r2)     // Catch: java.lang.Throwable -> L9f
            goto L9d
        L88:
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r0 = r4.f19755a     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r0 = r0.get(r5)     // Catch: java.lang.Throwable -> L9f
            r1 = r0
            com.ta.utdid2.b.a.d$a r1 = (com.p343ta.utdid2.p346b.p347a.C10322d.C10323a) r1     // Catch: java.lang.Throwable -> L9f
            if (r1 != 0) goto L9d
            com.ta.utdid2.b.a.d$a r1 = new com.ta.utdid2.b.a.d$a     // Catch: java.lang.Throwable -> L9f
            r1.<init>(r5, r6, r2)     // Catch: java.lang.Throwable -> L9f
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r6 = r4.f19755a     // Catch: java.lang.Throwable -> L9f
            r6.put(r5, r1)     // Catch: java.lang.Throwable -> L9f
        L9d:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L9f
            return r1
        L9f:
            r5 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L9f
            throw r5
        La2:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La2
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p343ta.utdid2.p346b.p347a.C10322d.m6418a(java.lang.String, int):com.ta.utdid2.b.a.b");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static File m6420a(File file) {
        return new File(file.getPath() + ".bak");
    }

    /* renamed from: com.ta.utdid2.b.a.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class C10323a implements InterfaceC10318b {

        /* renamed from: c */
        private static final Object f19756c = new Object();

        /* renamed from: a */
        private Map f19757a;

        /* renamed from: a */
        private WeakHashMap<InterfaceC10318b.InterfaceC10320b, Object> f19758a;

        /* renamed from: b */
        private final File f19759b;

        /* renamed from: c */
        private final int f19760c;

        /* renamed from: c */
        private final File f19761c;

        /* renamed from: j */
        private boolean f19762j = false;

        C10323a(File file, int i, Map map) {
            this.f19759b = file;
            this.f19761c = C10322d.m6420a(file);
            this.f19760c = i;
            this.f19757a = map == null ? new HashMap() : map;
            this.f19758a = new WeakHashMap<>();
        }

        @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b
        /* renamed from: b */
        public boolean mo6408b() {
            File file = this.f19759b;
            return file != null && new File(file.getAbsolutePath()).exists();
        }

        /* renamed from: a */
        public void m6409a(boolean z) {
            synchronized (this) {
                this.f19762j = z;
            }
        }

        /* renamed from: d */
        public boolean m6407d() {
            boolean z;
            synchronized (this) {
                z = this.f19762j;
            }
            return z;
        }

        /* renamed from: a */
        public void m6410a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.f19757a = map;
                }
            }
        }

        @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b
        public Map<String, ?> getAll() {
            HashMap hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.f19757a);
            }
            return hashMap;
        }

        @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b
        public String getString(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.f19757a.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b
        public long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.f19757a.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        /* renamed from: com.ta.utdid2.b.a.d$a$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public final class C10324a implements InterfaceC10318b.InterfaceC10319a {

            /* renamed from: b */
            private final Map<String, Object> f19764b = new HashMap();

            /* renamed from: k */
            private boolean f19765k = false;

            public C10324a() {
            }

            @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b.InterfaceC10319a
            /* renamed from: a */
            public InterfaceC10318b.InterfaceC10319a mo6401a(String str, String str2) {
                synchronized (this) {
                    this.f19764b.put(str, str2);
                }
                return this;
            }

            @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b.InterfaceC10319a
            /* renamed from: a */
            public InterfaceC10318b.InterfaceC10319a mo6403a(String str, int i) {
                synchronized (this) {
                    this.f19764b.put(str, Integer.valueOf(i));
                }
                return this;
            }

            @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b.InterfaceC10319a
            /* renamed from: a */
            public InterfaceC10318b.InterfaceC10319a mo6402a(String str, long j) {
                synchronized (this) {
                    this.f19764b.put(str, Long.valueOf(j));
                }
                return this;
            }

            @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b.InterfaceC10319a
            /* renamed from: a */
            public InterfaceC10318b.InterfaceC10319a mo6404a(String str, float f) {
                synchronized (this) {
                    this.f19764b.put(str, Float.valueOf(f));
                }
                return this;
            }

            @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b.InterfaceC10319a
            /* renamed from: a */
            public InterfaceC10318b.InterfaceC10319a mo6400a(String str, boolean z) {
                synchronized (this) {
                    this.f19764b.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b.InterfaceC10319a
            /* renamed from: a */
            public InterfaceC10318b.InterfaceC10319a mo6405a(String str) {
                synchronized (this) {
                    this.f19764b.put(str, this);
                }
                return this;
            }

            @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b.InterfaceC10319a
            /* renamed from: b */
            public InterfaceC10318b.InterfaceC10319a mo6399b() {
                synchronized (this) {
                    this.f19765k = true;
                }
                return this;
            }

            @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b.InterfaceC10319a
            public boolean commit() {
                boolean z;
                ArrayList arrayList;
                HashSet<InterfaceC10318b.InterfaceC10320b> hashSet;
                boolean m6406e;
                synchronized (C10322d.f19752b) {
                    z = C10323a.this.f19758a.size() > 0;
                    arrayList = null;
                    if (z) {
                        arrayList = new ArrayList();
                        hashSet = new HashSet(C10323a.this.f19758a.keySet());
                    } else {
                        hashSet = null;
                    }
                    synchronized (this) {
                        if (this.f19765k) {
                            C10323a.this.f19757a.clear();
                            this.f19765k = false;
                        }
                        for (Map.Entry<String, Object> entry : this.f19764b.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            if (value == this) {
                                C10323a.this.f19757a.remove(key);
                            } else {
                                C10323a.this.f19757a.put(key, value);
                            }
                            if (z) {
                                arrayList.add(key);
                            }
                        }
                        this.f19764b.clear();
                    }
                    m6406e = C10323a.this.m6406e();
                    if (m6406e) {
                        C10323a.this.m6409a(true);
                    }
                }
                if (z) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        String str = (String) arrayList.get(size);
                        for (InterfaceC10318b.InterfaceC10320b interfaceC10320b : hashSet) {
                            if (interfaceC10320b != null) {
                                interfaceC10320b.m6429a(C10323a.this, str);
                            }
                        }
                    }
                }
                return m6406e;
            }
        }

        @Override // com.p343ta.utdid2.p346b.p347a.InterfaceC10318b
        /* renamed from: a */
        public InterfaceC10318b.InterfaceC10319a mo6415a() {
            return new C10324a();
        }

        /* renamed from: a */
        private FileOutputStream m6411a(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException unused) {
                if (file.getParentFile().mkdir()) {
                    try {
                        return new FileOutputStream(file);
                    } catch (FileNotFoundException unused2) {
                        return null;
                    }
                }
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public boolean m6406e() {
            if (this.f19759b.exists()) {
                if (!this.f19761c.exists()) {
                    if (!this.f19759b.renameTo(this.f19761c)) {
                        return false;
                    }
                } else {
                    this.f19759b.delete();
                }
            }
            try {
                FileOutputStream m6411a = m6411a(this.f19759b);
                if (m6411a == null) {
                    return false;
                }
                C10325e.m6395a(this.f19757a, m6411a);
                m6411a.close();
                this.f19761c.delete();
                return true;
            } catch (Exception unused) {
                if (this.f19759b.exists()) {
                    this.f19759b.delete();
                }
                return false;
            }
        }
    }
}
