package com.mob.tools.utils;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import com.mob.commons.C5859o;
import com.mob.commons.C5873u;
import com.mob.commons.C5892y;
import com.mob.commons.InterfaceC5858n;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.EverythingKeeper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.Buffer;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MobPersistence {

    /* renamed from: a */
    private static final int f15187a = Process.myPid();

    /* renamed from: b */
    private volatile File f15188b;

    /* renamed from: i */
    private volatile C6174d f15195i;

    /* renamed from: j */
    private volatile C6176f f15196j;

    /* renamed from: m */
    private volatile ScheduledExecutorService f15199m;

    /* renamed from: n */
    private String f15200n;

    /* renamed from: c */
    private ReentrantReadWriteLock f15189c = new ReentrantReadWriteLock();

    /* renamed from: d */
    private ReentrantReadWriteLock.WriteLock f15190d = this.f15189c.writeLock();

    /* renamed from: e */
    private ReentrantReadWriteLock.ReadLock f15191e = this.f15189c.readLock();

    /* renamed from: f */
    private ReentrantReadWriteLock f15192f = new ReentrantReadWriteLock();

    /* renamed from: g */
    private ReentrantReadWriteLock.WriteLock f15193g = this.f15192f.writeLock();

    /* renamed from: h */
    private ReentrantReadWriteLock.ReadLock f15194h = this.f15192f.readLock();

    /* renamed from: k */
    private volatile List<C6173c> f15197k = new ArrayList();

    /* renamed from: l */
    private Map<String, C6175e> f15198l = new HashMap();

    /* renamed from: a */
    static /* synthetic */ File m11258a(MobPersistence mobPersistence) {
        return mobPersistence.f15188b;
    }

    /* renamed from: a */
    static /* synthetic */ Map m11252a(MobPersistence mobPersistence, Map map) {
        return mobPersistence.m11241a((Map<String, C6175e>) map);
    }

    /* renamed from: a */
    static /* synthetic */ void m11254a(MobPersistence mobPersistence, String str) {
        mobPersistence.m11229c(str);
    }

    /* renamed from: a */
    static /* synthetic */ void m11253a(MobPersistence mobPersistence, Throwable th) {
        mobPersistence.m11243a(th);
    }

    /* renamed from: a */
    static /* synthetic */ boolean m11250a(MobPersistence mobPersistence, boolean z) {
        return mobPersistence.m11240a(z);
    }

    /* renamed from: b */
    static /* synthetic */ void m11234b(MobPersistence mobPersistence, String str) {
        mobPersistence.m11226d(str);
    }

    /* renamed from: c */
    static /* synthetic */ ScheduledExecutorService m11230c(MobPersistence mobPersistence) {
        return mobPersistence.f15199m;
    }

    /* renamed from: d */
    static /* synthetic */ C6174d m11227d(MobPersistence mobPersistence) {
        return mobPersistence.f15195i;
    }

    /* renamed from: e */
    static /* synthetic */ C6176f m11225e(MobPersistence mobPersistence) {
        return mobPersistence.f15196j;
    }

    /* renamed from: f */
    static /* synthetic */ List m11224f(MobPersistence mobPersistence) {
        return mobPersistence.f15197k;
    }

    /* renamed from: g */
    static /* synthetic */ Map m11223g(MobPersistence mobPersistence) {
        return mobPersistence.f15198l;
    }

    /* renamed from: h */
    static /* synthetic */ ReentrantReadWriteLock.WriteLock m11222h(MobPersistence mobPersistence) {
        return mobPersistence.f15193g;
    }

    /* renamed from: i */
    static /* synthetic */ ReentrantReadWriteLock.WriteLock m11221i(MobPersistence mobPersistence) {
        return mobPersistence.f15190d;
    }

    public MobPersistence(final Context context, final String str) {
        try {
            this.f15200n = C5859o.f14442h + str;
            C5859o.m12225a(C5859o.m12223a(this.f15200n), new InterfaceC5858n() { // from class: com.mob.tools.utils.MobPersistence.1
                @Override // com.mob.commons.InterfaceC5858n
                /* renamed from: a */
                public boolean mo11219a(FileLocker fileLocker) {
                    final String str2;
                    try {
                        if (context != null) {
                            File m11236b = MobPersistence.m11236b(context);
                            MobPersistence.this.f15188b = new File(m11236b, str);
                            if (!MobPersistence.this.f15188b.getParentFile().exists()) {
                                MobPersistence.this.f15188b.getParentFile().mkdirs();
                            }
                            if (MobPersistence.this.f15188b.exists() && MobPersistence.this.f15188b.length() < 43008) {
                                MobPersistence mobPersistence = MobPersistence.this;
                                mobPersistence.m11229c("Del dirty, size: " + MobPersistence.this.f15188b.length() + ", min: 43008");
                                MobPersistence.this.f15188b.delete();
                            }
                            if (!MobPersistence.this.f15188b.exists()) {
                                MobPersistence.this.f15188b.createNewFile();
                                MobPersistence mobPersistence2 = MobPersistence.this;
                                mobPersistence2.m11229c("Create file: " + str);
                            }
                        }
                        MobPersistence.this.m11228d();
                        if (str != null && str.startsWith(".") && str.length() > 1) {
                            str2 = str.substring(1);
                        } else {
                            str2 = str;
                        }
                        MobPersistence.this.f15199m = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: com.mob.tools.utils.MobPersistence.1.1
                            @Override // java.util.concurrent.ThreadFactory
                            public Thread newThread(Runnable runnable) {
                                return new Thread(runnable, C5892y.f14524b + "MP-" + str2);
                            }
                        });
                        MobPersistence.this.f15199m.schedule(new RunnableC6171a(), 3000L, TimeUnit.MILLISECONDS);
                        return false;
                    } catch (Throwable th) {
                        MobPersistence.this.m11243a(th);
                        return false;
                    }
                }
            });
        } catch (Throwable th) {
            m11243a(th);
        }
    }

    /* renamed from: a */
    public static synchronized boolean m11261a(Context context, String str) {
        synchronized (MobPersistence.class) {
            return new File(m11236b(context), str).exists();
        }
    }

    /* renamed from: a */
    public <T> T m11260a(C6172b<T> c6172b) throws NoValidDataException {
        boolean z;
        if (c6172b == null) {
            throw new IllegalArgumentException("deserializer is null");
        }
        String m11218a = c6172b.m11218a();
        if (TextUtils.isEmpty(m11218a)) {
            throw new IllegalArgumentException("Key: " + m11218a);
        }
        this.f15194h.lock();
        try {
            try {
                if (!this.f15198l.isEmpty() && this.f15198l.containsKey(m11218a)) {
                    C6175e c6175e = this.f15198l.get(m11218a);
                    if (!c6175e.m11164e()) {
                        m11226d("Get done, f-m: " + m11218a);
                        return (T) c6175e.m11166b();
                    }
                    m11226d("Get done, exp-m: " + m11218a);
                    throw new NoValidDataException();
                }
            } catch (NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                m11243a(th);
            }
            this.f15194h.unlock();
            final byte[] m11233b = m11233b(m11218a);
            final byte[][] bArr = {new byte[0]};
            final int[] iArr = new int[1];
            this.f15190d.lock();
            try {
                z = C5859o.m12225a(C5859o.m12223a(this.f15200n), new InterfaceC5858n() { // from class: com.mob.tools.utils.MobPersistence.2
                    @Override // com.mob.commons.InterfaceC5858n
                    /* renamed from: a */
                    public boolean mo11219a(FileLocker fileLocker) {
                        try {
                            if (MobPersistence.this.f15196j.m11157b(MobPersistence.this.f15195i)) {
                                MobPersistence.this.m11240a(true);
                            }
                            if (MobPersistence.this.f15197k != null && !MobPersistence.this.f15197k.isEmpty()) {
                                C6173c c6173c = null;
                                Iterator it = MobPersistence.this.f15197k.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    C6173c c6173c2 = (C6173c) it.next();
                                    if (c6173c2.m11208b() == 0 && Arrays.equals(m11233b, c6173c2.m11205c())) {
                                        c6173c = c6173c2;
                                        break;
                                    }
                                }
                                if (c6173c != null) {
                                    if (c6173c.m11199g()) {
                                        iArr[0] = 2;
                                        MobPersistence.this.f15195i.m11196a(c6173c.m11217a(), (byte) 1);
                                        MobPersistence.this.f15195i.m11192a(System.currentTimeMillis());
                                    } else {
                                        iArr[0] = 0;
                                        bArr[0] = MobPersistence.this.f15195i.m11191a(c6173c.m11202d(), c6173c.m11201e());
                                    }
                                } else {
                                    iArr[0] = 1;
                                }
                            } else {
                                iArr[0] = 1;
                            }
                        } catch (Throwable th2) {
                            MobPersistence.this.m11243a(th2);
                        }
                        return false;
                    }
                });
            } catch (Throwable th2) {
                try {
                    m11243a(th2);
                    this.f15190d.unlock();
                    z = false;
                } finally {
                    this.f15190d.unlock();
                }
            }
            if (iArr[0] == 1) {
                m11226d("Get done, nof-f: " + m11218a);
                throw new NoValidDataException();
            } else if (iArr[0] == 2) {
                m11226d("Get done, lc-fail: " + m11218a);
                throw new NoValidDataException();
            } else {
                if (!z) {
                    m11226d("Get done, lc-fl: " + m11218a);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Get done, f-f: ");
                    sb.append(m11218a);
                    sb.append(", length: ");
                    sb.append(bArr[0] == null ? null : Integer.valueOf(bArr[0].length));
                    m11226d(sb.toString());
                }
                KVEntry kVEntry = (KVEntry) m11239a(bArr[0]);
                if (kVEntry != null) {
                    return c6172b.mo11106a(kVEntry.getValue());
                }
                return null;
            }
        } finally {
            this.f15194h.unlock();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:1|(3:2|3|(3:5|(4:8|(3:10|11|12)(1:14)|13|6)|15))|17|18|19|20|21|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0098, code lost:
        throw r0;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.HashMap<java.lang.String, java.lang.Object> m11263a() {
        /*
            r4 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r4.f15194h
            r1.lock()
            java.util.Map<java.lang.String, com.mob.tools.utils.MobPersistence$e> r1 = r4.f15198l     // Catch: java.lang.Throwable -> L42
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L42
            if (r1 != 0) goto L46
            java.util.Map<java.lang.String, com.mob.tools.utils.MobPersistence$e> r1 = r4.f15198l     // Catch: java.lang.Throwable -> L42
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L42
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L42
        L1c:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L42
            if (r2 == 0) goto L46
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L42
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch: java.lang.Throwable -> L42
            java.lang.Object r2 = r2.getValue()     // Catch: java.lang.Throwable -> L42
            com.mob.tools.utils.MobPersistence$e r2 = (com.mob.tools.utils.MobPersistence.C6175e) r2     // Catch: java.lang.Throwable -> L42
            boolean r3 = r2.m11164e()     // Catch: java.lang.Throwable -> L42
            if (r3 != 0) goto L1c
            java.lang.String r3 = r2.m11170a()     // Catch: java.lang.Throwable -> L42
            java.lang.Object r2 = r2.m11166b()     // Catch: java.lang.Throwable -> L42
            r0.put(r3, r2)     // Catch: java.lang.Throwable -> L42
            goto L1c
        L40:
            r0 = move-exception
            goto L99
        L42:
            r1 = move-exception
            r4.m11243a(r1)     // Catch: java.lang.Throwable -> L40
        L46:
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r4.f15194h
            r1.unlock()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r2 = r4.f15190d
            r2.lock()
            java.lang.String r2 = r4.f15200n     // Catch: java.lang.Throwable -> L66
            java.io.File r2 = com.mob.commons.C5859o.m12223a(r2)     // Catch: java.lang.Throwable -> L66
            com.mob.tools.utils.MobPersistence$3 r3 = new com.mob.tools.utils.MobPersistence$3     // Catch: java.lang.Throwable -> L66
            r3.<init>()     // Catch: java.lang.Throwable -> L66
            com.mob.commons.C5859o.m12225a(r2, r3)     // Catch: java.lang.Throwable -> L66
            goto L6a
        L64:
            r0 = move-exception
            goto L93
        L66:
            r2 = move-exception
            r4.m11243a(r2)     // Catch: java.lang.Throwable -> L64
        L6a:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r2 = r4.f15190d
            r2.unlock()
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r2.putAll(r1)
            r2.putAll(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "GetA done: "
            r0.append(r1)
            int r1 = r2.size()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.m11226d(r0)
            return r2
        L93:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r4.f15190d
            r1.unlock()
            throw r0
        L99:
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r4.f15194h
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.m11263a():java.util.HashMap");
    }

    /* renamed from: a */
    public void m11259a(C6175e c6175e) {
        if (c6175e == null) {
            throw new IllegalArgumentException("dataEntry is null");
        }
        String m11170a = c6175e.m11170a();
        long m11165d = c6175e.m11165d();
        m11226d("Set: " + m11170a + ", expAt: " + m11165d);
        if (TextUtils.isEmpty(m11170a) || m11165d < 0) {
            throw new IllegalArgumentException("Key: " + m11170a + ", expAt: " + m11165d);
        }
        c6175e.m11167a(m11233b(m11170a));
        this.f15193g.lock();
        try {
            this.f15198l.put(m11170a, c6175e);
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:3|(3:4|5|(1:9))|11|12|13|14|15) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0061, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a4, code lost:
        throw r13;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m11245a(java.lang.String r13) {
        /*
            r12 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 != 0) goto Lab
            byte[] r3 = r12.m11233b(r13)
            r0 = 1
            boolean[] r7 = new boolean[r0]
            r8 = 0
            r7[r8] = r8
            r1 = 0
            java.lang.String[] r9 = new java.lang.String[]{r1}
            int[] r10 = new int[r0]
            r1 = -1
            r10[r8] = r1
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r12.f15193g
            r1.lock()
            java.util.Map<java.lang.String, com.mob.tools.utils.MobPersistence$e> r1 = r12.f15198l     // Catch: java.lang.Throwable -> L3d
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L3d
            if (r1 != 0) goto L41
            java.util.Map<java.lang.String, com.mob.tools.utils.MobPersistence$e> r1 = r12.f15198l     // Catch: java.lang.Throwable -> L3d
            boolean r1 = r1.containsKey(r13)     // Catch: java.lang.Throwable -> L3d
            if (r1 == 0) goto L41
            java.util.Map<java.lang.String, com.mob.tools.utils.MobPersistence$e> r1 = r12.f15198l     // Catch: java.lang.Throwable -> L3d
            r1.remove(r13)     // Catch: java.lang.Throwable -> L3d
            r7[r8] = r0     // Catch: java.lang.Throwable -> L3d
            java.lang.String r0 = "m"
            r9[r8] = r0     // Catch: java.lang.Throwable -> L3d
            goto L41
        L3b:
            r13 = move-exception
            goto La5
        L3d:
            r0 = move-exception
            r12.m11243a(r0)     // Catch: java.lang.Throwable -> L3b
        L41:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r12.f15193g
            r0.unlock()
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r12.f15190d
            r0.lock()
            java.lang.String r0 = r12.f15200n     // Catch: java.lang.Throwable -> L61
            java.io.File r0 = com.mob.commons.C5859o.m12223a(r0)     // Catch: java.lang.Throwable -> L61
            com.mob.tools.utils.MobPersistence$4 r11 = new com.mob.tools.utils.MobPersistence$4     // Catch: java.lang.Throwable -> L61
            r1 = r11
            r2 = r12
            r4 = r10
            r5 = r7
            r6 = r9
            r1.<init>()     // Catch: java.lang.Throwable -> L61
            com.mob.commons.C5859o.m12225a(r0, r11)     // Catch: java.lang.Throwable -> L61
            goto L65
        L5f:
            r13 = move-exception
            goto L9f
        L61:
            r0 = move-exception
            r12.m11243a(r0)     // Catch: java.lang.Throwable -> L5f
        L65:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r12.f15190d
            r0.unlock()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Remove: "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = ", from: "
            r0.append(r13)
            r13 = r9[r8]
            r0.append(r13)
            java.lang.String r13 = ", idx: "
            r0.append(r13)
            r13 = r10[r8]
            r0.append(r13)
            java.lang.String r13 = ", "
            r0.append(r13)
            boolean r13 = r7[r8]
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            r12.m11226d(r13)
            boolean r13 = r7[r8]
            return r13
        L9f:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r12.f15190d
            r0.unlock()
            throw r13
        La5:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r12.f15193g
            r0.unlock()
            throw r13
        Lab:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Key: "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.m11245a(java.lang.String):boolean");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:1|(3:2|3|(1:5))|7|8|9|10|11|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
        throw r0;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m11237b() {
        /*
            r6 = this;
            java.lang.String r0 = "Clear"
            r6.m11226d(r0)
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r6.f15193g
            r0.lock()
            java.util.Map<java.lang.String, com.mob.tools.utils.MobPersistence$e> r0 = r6.f15198l     // Catch: java.lang.Throwable -> L1a
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L1a
            if (r0 != 0) goto L1e
            java.util.Map<java.lang.String, com.mob.tools.utils.MobPersistence$e> r0 = r6.f15198l     // Catch: java.lang.Throwable -> L1a
            r0.clear()     // Catch: java.lang.Throwable -> L1a
            goto L1e
        L18:
            r0 = move-exception
            goto L69
        L1a:
            r0 = move-exception
            r6.m11243a(r0)     // Catch: java.lang.Throwable -> L18
        L1e:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r6.f15193g
            r0.unlock()
            r0 = 1
            boolean[] r1 = new boolean[r0]
            r2 = 0
            r1[r2] = r2
            long[] r0 = new long[r0]
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r3 = r6.f15190d
            r3.lock()
            java.lang.String r3 = r6.f15200n     // Catch: java.lang.Throwable -> L41
            java.io.File r3 = com.mob.commons.C5859o.m12223a(r3)     // Catch: java.lang.Throwable -> L41
            com.mob.tools.utils.MobPersistence$5 r4 = new com.mob.tools.utils.MobPersistence$5     // Catch: java.lang.Throwable -> L41
            r4.<init>()     // Catch: java.lang.Throwable -> L41
            com.mob.commons.C5859o.m12225a(r3, r4)     // Catch: java.lang.Throwable -> L41
            goto L45
        L3f:
            r0 = move-exception
            goto L63
        L41:
            r3 = move-exception
            r6.m11243a(r3)     // Catch: java.lang.Throwable -> L3f
        L45:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r3 = r6.f15190d
            r3.unlock()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Clear done, new size: "
            r3.append(r4)
            r4 = r0[r2]
            r3.append(r4)
            java.lang.String r0 = r3.toString()
            r6.m11226d(r0)
            boolean r0 = r1[r2]
            return r0
        L63:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r6.f15190d
            r1.unlock()
            throw r0
        L69:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r6.f15193g
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.m11237b():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static synchronized File m11236b(Context context) {
        File file;
        synchronized (MobPersistence.class) {
            file = new File(context.getFilesDir(), C5731l.m12674a("007Cidfegfhl_g0ekgi"));
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Map<String, C6175e> m11241a(final Map<String, C6175e> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("Submit, size: ");
        sb.append(map == null ? null : Integer.valueOf(map.size()));
        m11226d(sb.toString());
        final HashMap hashMap = new HashMap();
        if (map != null && !map.isEmpty()) {
            this.f15190d.lock();
            try {
                C5859o.m12225a(C5859o.m12223a(this.f15200n), new InterfaceC5858n() { // from class: com.mob.tools.utils.MobPersistence.6
                    /* JADX WARN: Code restructure failed: missing block: B:33:0x010e, code lost:
                        r12.f15222c.m11226d("Submit: " + ((com.mob.tools.utils.MobPersistence.C6175e) r4.getValue()).m11170a() + ", nof index");
                        r12.f15222c.m11248a(r2, 1024);
                     */
                    @Override // com.mob.commons.InterfaceC5858n
                    /* renamed from: a */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public boolean mo11219a(com.mob.tools.utils.FileLocker r13) {
                        /*
                            Method dump skipped, instructions count: 359
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.C61696.mo11219a(com.mob.tools.utils.FileLocker):boolean");
                    }
                });
            } finally {
                try {
                } finally {
                }
            }
        }
        int size = map == null ? 0 : map.size();
        m11226d("Submit done, succ: " + (size - hashMap.size()) + ", remain: " + hashMap.size());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m11247a(RandomAccessFile randomAccessFile, C6173c c6173c, C6175e c6175e) {
        if (randomAccessFile != null && c6173c != null && c6175e != null) {
            this.f15190d.lock();
            try {
                byte[] m11246a = m11246a(new KVEntry(c6175e.m11170a(), c6175e.mo11108c()));
                long length = randomAccessFile.length();
                randomAccessFile.seek(length);
                randomAccessFile.write(m11246a);
                this.f15195i.m11196a(c6173c.m11217a(), (byte) 0);
                this.f15195i.m11193a(c6173c.m11217a(), c6175e.m11163f());
                this.f15195i.m11194a(c6173c.m11217a(), length);
                this.f15195i.m11184b(c6173c.m11217a(), m11246a.length);
                this.f15195i.m11181c(c6173c.m11217a(), c6175e.m11165d());
                this.f15195i.m11192a(System.currentTimeMillis());
                c6173c.m11216a((byte) 0);
                c6173c.m11209a(c6175e.m11163f());
                c6173c.m11214a(length);
                c6173c.m11207b(m11246a.length);
                c6173c.m11204c(c6175e.m11165d());
            } catch (Throwable th) {
                try {
                    m11243a(th);
                    return false;
                } finally {
                    this.f15190d.unlock();
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m11248a(RandomAccessFile randomAccessFile, int i) {
        boolean z;
        m11229c("Expand: " + i);
        if (randomAccessFile == null || i <= 0) {
            throw new IllegalArgumentException("Raf: " + randomAccessFile + "Expand: " + i);
        }
        this.f15190d.lock();
        try {
            if (this.f15196j.m11157b(this.f15195i)) {
                m11240a(true);
            }
            long m11183c = this.f15195i.m11183c() * i;
            Collections.sort(this.f15197k);
            long m11180d = this.f15195i.m11180d() + this.f15195i.m11178e();
            int i2 = 0;
            while (true) {
                if (i2 >= this.f15197k.size()) {
                    z = false;
                    break;
                }
                C6173c c6173c = this.f15197k.get(i2);
                if ((c6173c.m11202d() + c6173c.m11201e()) - m11180d >= m11183c) {
                    z = true;
                    break;
                }
                i2++;
            }
            long length = randomAccessFile.length();
            if (i2 > 0 && !z) {
                C6173c c6173c2 = this.f15197k.get(this.f15197k.size() - 1);
                length += m11183c - (c6173c2.m11202d() + c6173c2.m11201e());
                i2--;
            }
            m11229c("Mv: " + i2);
            for (int i3 = 0; i3 <= i2; i3++) {
                C6173c c6173c3 = this.f15197k.get(i3);
                if (c6173c3.m11208b() == 0) {
                    randomAccessFile.seek(length);
                    randomAccessFile.write(this.f15195i.m11191a(c6173c3.m11202d(), c6173c3.m11201e()));
                    this.f15195i.m11194a(c6173c3.m11217a(), length);
                    length += c6173c3.m11201e();
                }
            }
            this.f15195i.m11192a(System.currentTimeMillis());
            int m11187b = this.f15195i.m11187b() + i;
            this.f15195i.m11197a(m11187b);
            m11229c("Expand done: " + m11187b);
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m11231c() {
        m11229c("Trim");
        this.f15190d.lock();
        try {
            C5859o.m12225a(C5859o.m12223a(this.f15200n), new InterfaceC5858n() { // from class: com.mob.tools.utils.MobPersistence.7
                /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
                    jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
                    	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
                    	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
                    	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
                    	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
                    	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
                    */
                @Override // com.mob.commons.InterfaceC5858n
                /* renamed from: a */
                public boolean mo11219a(com.mob.tools.utils.FileLocker r12) {
                    /*
                        Method dump skipped, instructions count: 359
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.C61707.mo11219a(com.mob.tools.utils.FileLocker):boolean");
                }
            });
        } finally {
            try {
                this.f15190d.unlock();
                m11229c("Trim done");
            } catch (Throwable th) {
            }
        }
        this.f15190d.unlock();
        m11229c("Trim done");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m11228d() {
        /*
            r20 = this;
            r8 = r20
            java.io.File r0 = r8.f15188b
            if (r0 == 0) goto Ld4
            java.io.File r0 = r8.f15188b
            boolean r0 = r0.exists()
            if (r0 == 0) goto Ld4
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r8.f15190d
            r0.lock()
            r1 = 0
            r9 = 2
            r10 = 1
            r11 = 0
            java.io.RandomAccessFile r12 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> Lae
            java.io.File r0 = r8.f15188b     // Catch: java.lang.Throwable -> Lae
            java.lang.String r2 = "0021ekgg"
            java.lang.String r2 = com.mob.commons.p229a.C5731l.m12674a(r2)     // Catch: java.lang.Throwable -> Lae
            r12.<init>(r0, r2)     // Catch: java.lang.Throwable -> Lae
            java.nio.channels.FileChannel r19 = r12.getChannel()     // Catch: java.lang.Throwable -> La7
            r0 = 43008(0xa800, double:2.1249E-319)
            long r2 = r12.length()     // Catch: java.lang.Throwable -> La3
            long r6 = java.lang.Math.max(r0, r2)     // Catch: java.lang.Throwable -> La3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3
            r0.<init>()     // Catch: java.lang.Throwable -> La3
            java.lang.String r1 = "Map whole, size: "
            r0.append(r1)     // Catch: java.lang.Throwable -> La3
            r0.append(r6)     // Catch: java.lang.Throwable -> La3
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La3
            r8.m11229c(r0)     // Catch: java.lang.Throwable -> La3
            java.nio.channels.FileChannel$MapMode r14 = java.nio.channels.FileChannel.MapMode.READ_WRITE     // Catch: java.lang.Throwable -> La3
            r15 = 0
            r13 = r19
            r17 = r6
            java.nio.MappedByteBuffer r3 = r13.map(r14, r15, r17)     // Catch: java.lang.Throwable -> La3
            com.mob.tools.utils.MobPersistence$d r0 = new com.mob.tools.utils.MobPersistence$d     // Catch: java.lang.Throwable -> La3
            r4 = 41
            r5 = 0
            r1 = r0
            r2 = r20
            r1.<init>(r3, r4, r5, r6)     // Catch: java.lang.Throwable -> La3
            r8.f15195i = r0     // Catch: java.lang.Throwable -> La3
            java.lang.String r0 = "Sync s-block"
            r8.m11229c(r0)     // Catch: java.lang.Throwable -> La3
            com.mob.tools.utils.MobPersistence$f r0 = new com.mob.tools.utils.MobPersistence$f     // Catch: java.lang.Throwable -> La3
            com.mob.tools.utils.MobPersistence$d r1 = r8.f15195i     // Catch: java.lang.Throwable -> La3
            r0.<init>(r1)     // Catch: java.lang.Throwable -> La3
            r8.f15196j = r0     // Catch: java.lang.Throwable -> La3
            com.mob.tools.utils.MobPersistence$f r0 = r8.f15196j     // Catch: java.lang.Throwable -> La3
            int r0 = r0.m11158b()     // Catch: java.lang.Throwable -> La3
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3
            r1.<init>()     // Catch: java.lang.Throwable -> La3
            java.lang.String r2 = "Sync i-block, num: "
            r1.append(r2)     // Catch: java.lang.Throwable -> La3
            r1.append(r0)     // Catch: java.lang.Throwable -> La3
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La3
            r8.m11229c(r1)     // Catch: java.lang.Throwable -> La3
            r1 = r11
        L89:
            if (r1 >= r0) goto L9a
            java.util.List<com.mob.tools.utils.MobPersistence$c> r2 = r8.f15197k     // Catch: java.lang.Throwable -> La3
            com.mob.tools.utils.MobPersistence$c r3 = new com.mob.tools.utils.MobPersistence$c     // Catch: java.lang.Throwable -> La3
            com.mob.tools.utils.MobPersistence$d r4 = r8.f15195i     // Catch: java.lang.Throwable -> La3
            r3.<init>(r4, r1)     // Catch: java.lang.Throwable -> La3
            r2.add(r3)     // Catch: java.lang.Throwable -> La3
            int r1 = r1 + 1
            goto L89
        L9a:
            java.io.Closeable[] r0 = new java.io.Closeable[r9]
            r0[r11] = r19
            r0[r10] = r12
            goto Lb9
        La1:
            r0 = move-exception
            goto Lc5
        La3:
            r0 = move-exception
            r1 = r19
            goto Lb0
        La7:
            r0 = move-exception
            goto Lb0
        La9:
            r0 = move-exception
            r12 = r1
            r19 = r12
            goto Lc5
        Lae:
            r0 = move-exception
            r12 = r1
        Lb0:
            r8.m11243a(r0)     // Catch: java.lang.Throwable -> Lc2
            java.io.Closeable[] r0 = new java.io.Closeable[r9]
            r0[r11] = r1
            r0[r10] = r12
        Lb9:
            com.mob.commons.C5873u.m12179a(r0)
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r8.f15190d
            r0.unlock()
            goto Ld4
        Lc2:
            r0 = move-exception
            r19 = r1
        Lc5:
            java.io.Closeable[] r1 = new java.io.Closeable[r9]
            r1[r11] = r19
            r1[r10] = r12
            com.mob.commons.C5873u.m12179a(r1)
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r8.f15190d
            r1.unlock()
            throw r0
        Ld4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.m11228d():void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m11240a(boolean r22) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.m11240a(boolean):boolean");
    }

    /* renamed from: b */
    private byte[] m11233b(String str) {
        return Data.rawMD5(str);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    private byte[] m11246a(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 == 0) goto L4e
            r1 = 0
            r2 = 1
            r3 = 2
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L30
            r4.<init>()     // Catch: java.lang.Throwable -> L30
            java.io.ObjectOutputStream r5 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L2b
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L2b
            r5.writeObject(r7)     // Catch: java.lang.Throwable -> L28
            byte[] r7 = r4.toByteArray()     // Catch: java.lang.Throwable -> L28
            if (r7 != 0) goto L1b
            byte[] r7 = new byte[r0]     // Catch: java.lang.Throwable -> L28
        L1b:
            java.io.Closeable[] r1 = new java.io.Closeable[r3]
            r1[r0] = r5
            r1[r2] = r4
            com.mob.commons.C5873u.m12179a(r1)
            return r7
        L25:
            r7 = move-exception
            r1 = r5
            goto L44
        L28:
            r7 = move-exception
            r1 = r5
            goto L32
        L2b:
            r7 = move-exception
            goto L32
        L2d:
            r7 = move-exception
            r4 = r1
            goto L44
        L30:
            r7 = move-exception
            r4 = r1
        L32:
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L43
            r5.m11325w(r7)     // Catch: java.lang.Throwable -> L43
            java.io.Closeable[] r7 = new java.io.Closeable[r3]
            r7[r0] = r1
            r7[r2] = r4
            com.mob.commons.C5873u.m12179a(r7)
            goto L4e
        L43:
            r7 = move-exception
        L44:
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r0] = r1
            r3[r2] = r4
            com.mob.commons.C5873u.m12179a(r3)
            throw r7
        L4e:
            byte[] r7 = new byte[r0]
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.m11246a(java.lang.Object):byte[]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Object m11239a(byte[] bArr) {
        return m11238a(bArr, (Object) null);
    }

    /* renamed from: a */
    private Object m11238a(byte[] bArr, Object obj) {
        try {
            return m11232b(bArr);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return obj;
        }
    }

    /* renamed from: b */
    private Object m11232b(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream = null;
        }
        try {
            Object readObject = objectInputStream.readObject();
            C5873u.m12179a(objectInputStream, byteArrayInputStream);
            return readObject;
        } catch (Throwable th3) {
            objectInputStream2 = objectInputStream;
            th = th3;
            C5873u.m12179a(objectInputStream2, byteArrayInputStream);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m11229c(String str) {
        m11244a(str, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m11226d(String str) {
        m11244a(str, false);
    }

    /* renamed from: a */
    private void m11244a(String str, boolean z) {
        if (z) {
            String str2 = "[MPF][" + f15187a + "]";
            if (this.f15188b != null) {
                str2 = str2 + "[" + this.f15188b.getName() + "]";
            }
            MobLog.getInstance().m11342d(str2 + str, new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m11243a(Throwable th) {
        m11242a(th, true);
    }

    /* renamed from: a */
    private void m11242a(Throwable th, boolean z) {
        if (z) {
            String str = "[MPF][" + f15187a + "]";
            if (this.f15188b != null) {
                str = str + "[" + this.f15188b.getName() + "]";
            }
            MobLog.getInstance().m11340d(th, str, new Object[0]);
        }
    }

    /* renamed from: com.mob.tools.utils.MobPersistence$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class RunnableC6171a implements Runnable {

        /* renamed from: b */
        private Map<String, C6175e> f15225b;

        private RunnableC6171a() {
            this.f15225b = new HashMap();
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:26:0x00c3
            	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
            */
        @Override // java.lang.Runnable
        public void run() {
            /*
                Method dump skipped, instructions count: 348
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.MobPersistence.RunnableC6171a.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.tools.utils.MobPersistence$g */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC6177g implements Runnable {
        private RunnableC6177g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                MobPersistence.this.m11229c("T-task start");
                MobPersistence.this.m11231c();
                MobPersistence.this.m11229c("T-task done");
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.tools.utils.MobPersistence$d */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C6174d {

        /* renamed from: b */
        private MappedByteBuffer f15234b;

        /* renamed from: c */
        private final int f15235c;

        /* renamed from: e */
        private long f15237e;

        /* renamed from: f */
        private final long f15238f;

        /* renamed from: g */
        private boolean f15239g = false;

        /* renamed from: d */
        private int f15236d = 1024;

        public C6174d(MappedByteBuffer mappedByteBuffer, int i, int i2, long j) {
            this.f15234b = mappedByteBuffer;
            this.f15235c = i;
            this.f15238f = j;
            m11173g(i2);
        }

        /* renamed from: a */
        public long m11198a() {
            try {
                if (this.f15234b != null) {
                    return this.f15234b.getLong(0);
                }
                return 0L;
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
                return 0L;
            }
        }

        /* renamed from: a */
        public void m11192a(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("lMdfTime: " + j);
            }
            try {
                if (this.f15234b != null) {
                    this.f15234b.putLong(0, j);
                }
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
        }

        /* renamed from: b */
        public int m11187b() {
            try {
                if (this.f15234b != null) {
                    return this.f15234b.getInt(8);
                }
                return 0;
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
                return 0;
            }
        }

        /* renamed from: a */
        public void m11197a(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("indexNum : " + i);
            }
            try {
                if (this.f15234b != null) {
                    this.f15234b.putInt(8, i);
                }
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
        }

        /* renamed from: b */
        public byte m11186b(int i) {
            m11171h(i);
            try {
                if (this.f15234b != null) {
                    return this.f15234b.get((this.f15235c * i) + 1024);
                }
                return (byte) 0;
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
                return (byte) 0;
            }
        }

        /* renamed from: a */
        public void m11196a(int i, byte b) {
            m11171h(i);
            try {
                if (this.f15234b != null) {
                    this.f15234b.put((this.f15235c * i) + 1024, b);
                }
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
        }

        /* renamed from: c */
        public byte[] m11182c(int i) {
            m11171h(i);
            byte[] bArr = new byte[16];
            try {
                if (this.f15234b != null) {
                    m11189a(this.f15234b, (this.f15235c * i) + 1025);
                    this.f15234b.get(bArr);
                    return bArr;
                }
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
            return bArr;
        }

        /* renamed from: a */
        public void m11193a(int i, byte[] bArr) {
            m11171h(i);
            if (bArr == null || bArr.length != 16) {
                String str = "field: " + bArr;
                if (bArr != null) {
                    str = str + "(length: " + bArr.length + ")";
                }
                throw new IllegalArgumentException(str);
            }
            try {
                if (this.f15234b != null) {
                    m11189a(this.f15234b, (this.f15235c * i) + 1025);
                    this.f15234b.put(bArr);
                }
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
        }

        /* renamed from: d */
        public long m11179d(int i) {
            m11171h(i);
            try {
                if (this.f15234b != null) {
                    return this.f15234b.getLong((this.f15235c * i) + 1041);
                }
                return -1L;
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
                return -1L;
            }
        }

        /* renamed from: a */
        public void m11194a(int i, long j) {
            m11171h(i);
            if (j < 0) {
                throw new IllegalArgumentException("start: " + j);
            }
            try {
                if (this.f15234b != null) {
                    this.f15234b.putLong((this.f15235c * i) + 1041, j);
                }
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
        }

        /* renamed from: e */
        public long m11177e(int i) {
            m11171h(i);
            try {
                if (this.f15234b != null) {
                    return this.f15234b.getLong((this.f15235c * i) + 1049);
                }
                return -1L;
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
                return -1L;
            }
        }

        /* renamed from: b */
        public void m11184b(int i, long j) {
            m11171h(i);
            if (j < 0) {
                throw new IllegalArgumentException("length: " + j);
            }
            try {
                if (this.f15234b != null) {
                    this.f15234b.putLong((this.f15235c * i) + 1049, j);
                }
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
        }

        /* renamed from: f */
        public long m11175f(int i) {
            m11171h(i);
            try {
                if (this.f15234b != null) {
                    return this.f15234b.getLong((this.f15235c * i) + 1057);
                }
                return 0L;
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
                return 0L;
            }
        }

        /* renamed from: c */
        public void m11181c(int i, long j) {
            m11171h(i);
            if (j < 0) {
                throw new IllegalArgumentException("exp: " + j);
            }
            try {
                if (this.f15234b != null) {
                    this.f15234b.putLong((this.f15235c * i) + 1057, j);
                }
            } catch (Throwable th) {
                MobPersistence.this.m11243a(th);
            }
        }

        /* renamed from: a */
        public byte[] m11191a(long j, long j2) {
            long m11180d = m11180d() + m11178e();
            if (j < m11180d || j > m11176f()) {
                throw new IllegalArgumentException("Start: " + j + ", allowed rang: [" + m11180d + ", " + m11176f() + "]");
            } else if (j2 < 0 || j + j2 > m11176f()) {
                throw new IllegalArgumentException("Start: " + j + ", length: " + j2 + ", max: " + m11176f());
            } else {
                byte[] bArr = new byte[(int) j2];
                try {
                    if (this.f15234b != null) {
                        m11189a(this.f15234b, (int) j);
                        this.f15234b.get(bArr);
                        return bArr;
                    }
                } catch (Throwable th) {
                    MobPersistence.this.m11243a(th);
                }
                return bArr;
            }
        }

        /* renamed from: a */
        public boolean m11190a(long j, byte[] bArr) {
            long m11180d = m11180d() + m11178e();
            if (j < m11180d || j > m11176f()) {
                throw new IllegalArgumentException("Start: " + j + ", allowed rang: [" + m11180d + ", " + m11176f() + "]");
            } else if (bArr == null || bArr.length + j > m11176f()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Start: ");
                sb.append(j);
                sb.append(", v.len: ");
                sb.append(bArr == null ? null : Integer.valueOf(bArr.length));
                sb.append(", total: ");
                sb.append(m11176f());
                throw new IllegalArgumentException(sb.toString());
            } else {
                try {
                    m11189a(this.f15234b, (int) j);
                    this.f15234b.put(bArr);
                    return true;
                } catch (Throwable th) {
                    MobPersistence.this.m11243a(th);
                    return false;
                }
            }
        }

        /* renamed from: c */
        public int m11183c() {
            return this.f15235c;
        }

        /* renamed from: d */
        public int m11180d() {
            return this.f15236d;
        }

        /* renamed from: e */
        public long m11178e() {
            return this.f15237e;
        }

        /* renamed from: f */
        public long m11176f() {
            return this.f15238f;
        }

        /* renamed from: g */
        private boolean m11174g() {
            return this.f15239g;
        }

        /* renamed from: a */
        private void m11188a(boolean z) {
            this.f15239g = z;
        }

        /* renamed from: g */
        private void m11173g(int i) {
            int m11172h = m11172h();
            this.f15237e = this.f15235c * m11172h;
            if (m11174g()) {
                m11195a(0, m11172h);
            } else if (i < 1024) {
            } else {
                m11195a(i, m11172h);
            }
        }

        /* renamed from: h */
        private int m11172h() {
            if (m11198a() == 0) {
                m11188a(true);
                m11192a(System.currentTimeMillis());
            }
            if (m11187b() == 0) {
                m11188a(true);
                m11197a(1024);
            }
            if (m11174g()) {
                MobPersistence.this.m11229c("File initialize: true, init s-block");
            }
            return m11187b();
        }

        /* renamed from: a */
        private void m11195a(int i, int i2) {
            if (i != i2) {
                MobPersistence mobPersistence = MobPersistence.this;
                mobPersistence.m11229c("Init i-block, from: " + i + ", to: " + i2);
            }
            while (i < i2) {
                m11196a(i, (byte) 1);
                i++;
            }
        }

        /* renamed from: h */
        private void m11171h(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("index : " + i);
            }
            int m11187b = m11187b();
            if (i >= m11187b) {
                throw new IndexOutOfBoundsException(m11185b(i, m11187b));
            }
        }

        /* renamed from: b */
        private String m11185b(int i, int i2) {
            return "Index: " + i + ", Size: " + i2;
        }

        /* renamed from: a */
        private void m11189a(MappedByteBuffer mappedByteBuffer, int i) {
            ((Buffer) new Object[]{mappedByteBuffer}[0]).position(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.tools.utils.MobPersistence$f */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C6176f {

        /* renamed from: a */
        private volatile long f15244a;

        /* renamed from: b */
        private int f15245b;

        public C6176f(C6174d c6174d) {
            m11159a(c6174d);
        }

        /* renamed from: a */
        public long m11162a() {
            return this.f15244a;
        }

        /* renamed from: a */
        private void m11160a(long j) {
            this.f15244a = j;
        }

        /* renamed from: b */
        public int m11158b() {
            return this.f15245b;
        }

        /* renamed from: a */
        private void m11161a(int i) {
            this.f15245b = i;
        }

        /* renamed from: a */
        public void m11159a(C6174d c6174d) {
            if (c6174d != null) {
                m11160a(c6174d.m11198a());
                m11161a(c6174d.m11187b());
            }
        }

        /* renamed from: b */
        public boolean m11157b(C6174d c6174d) {
            return c6174d == null || m11162a() != c6174d.m11198a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.tools.utils.MobPersistence$c */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C6173c implements Comparable<C6173c> {

        /* renamed from: a */
        private int f15227a;

        /* renamed from: b */
        private byte f15228b;

        /* renamed from: c */
        private byte[] f15229c;

        /* renamed from: d */
        private long f15230d;

        /* renamed from: e */
        private long f15231e;

        /* renamed from: f */
        private long f15232f;

        public C6173c(C6174d c6174d, int i) {
            if (c6174d != null) {
                m11215a(i);
                m11216a(c6174d.m11186b(i));
                m11209a(c6174d.m11182c(i));
                m11214a(c6174d.m11179d(i));
                m11207b(c6174d.m11177e(i));
                m11204c(c6174d.m11175f(i));
            }
        }

        /* renamed from: a */
        public int m11217a() {
            return this.f15227a;
        }

        /* renamed from: a */
        private void m11215a(int i) {
            this.f15227a = i;
        }

        /* renamed from: b */
        public byte m11208b() {
            return this.f15228b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m11216a(byte b) {
            this.f15228b = b;
        }

        /* renamed from: c */
        public byte[] m11205c() {
            return this.f15229c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m11209a(byte[] bArr) {
            this.f15229c = bArr;
        }

        /* renamed from: d */
        public long m11202d() {
            return this.f15230d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m11214a(long j) {
            this.f15230d = j;
        }

        /* renamed from: e */
        public long m11201e() {
            return this.f15231e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m11207b(long j) {
            this.f15231e = j;
        }

        /* renamed from: f */
        public long m11200f() {
            return this.f15232f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public void m11204c(long j) {
            this.f15232f = j;
        }

        /* renamed from: g */
        public boolean m11199g() {
            return m11200f() != 0 && m11200f() <= System.currentTimeMillis();
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(C6173c c6173c) {
            if (m11202d() > c6173c.m11202d()) {
                return 1;
            }
            return m11202d() == c6173c.m11202d() ? 0 : -1;
        }
    }

    /* renamed from: com.mob.tools.utils.MobPersistence$e */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C6175e {

        /* renamed from: a */
        private String f15240a;

        /* renamed from: b */
        private Object f15241b;

        /* renamed from: c */
        private long f15242c;

        /* renamed from: d */
        private byte[] f15243d;

        public C6175e(String str, Object obj, long j) {
            this.f15240a = str;
            this.f15241b = obj;
            this.f15242c = j;
        }

        /* renamed from: a */
        public String m11170a() {
            return this.f15240a;
        }

        /* renamed from: b */
        public Object m11166b() {
            return this.f15241b;
        }

        /* renamed from: c */
        public Object mo11108c() {
            return this.f15241b;
        }

        /* renamed from: d */
        public long m11165d() {
            return this.f15242c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public byte[] m11163f() {
            return this.f15243d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m11167a(byte[] bArr) {
            this.f15243d = bArr;
        }

        /* renamed from: e */
        public boolean m11164e() {
            return m11165d() != 0 && m11165d() <= System.currentTimeMillis();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.MobPersistence$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6172b<T> {

        /* renamed from: a */
        private String f15226a;

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public T mo11106a(Object obj) {
            return obj;
        }

        public C6172b(String str) {
            this.f15226a = str;
        }

        /* renamed from: a */
        public String m11218a() {
            return this.f15226a;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class NoValidDataException extends Exception {
        public NoValidDataException() {
            this(C5731l.m12674a("019AfifejgeeHehGejedjgedQejeHjgfgfeeh0fVed"));
        }

        public NoValidDataException(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class KVEntry<T> implements EverythingKeeper, Serializable {
        private static final long serialVersionUID = -1538971823189206429L;
        private String key;
        private T value;

        public KVEntry(String str, T t) {
            this.key = str;
            this.value = t;
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T t) {
            this.value = t;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class SerializableParcel<T extends Parcelable> implements EverythingKeeper, Serializable {
        private static final long serialVersionUID = -2769878423373647357L;
        private Class<T> clazz;
        private byte[] data;

        public SerializableParcel(Parcelable parcelable) {
            this.clazz = (Class<T>) parcelable.getClass();
            this.data = parcelable2Byte(parcelable);
        }

        public Class getClazz() {
            return this.clazz;
        }

        private void setClazz(Class cls) {
            this.clazz = cls;
        }

        public byte[] getData() {
            return this.data;
        }

        public T getParcel(T t) {
            return byte2Parcelable(this.data, this.clazz, t);
        }

        private void setData(byte[] bArr) {
            this.data = bArr;
        }

        private byte[] parcelable2Byte(Parcelable parcelable) {
            if (parcelable != null) {
                Parcel obtain = Parcel.obtain();
                parcelable.writeToParcel(obtain, 0);
                return obtain.marshall();
            }
            return new byte[0];
        }

        private T byte2Parcelable(byte[] bArr, Class<T> cls, T t) {
            if (bArr == null || bArr.length == 0) {
                return t;
            }
            try {
                Parcel obtain = Parcel.obtain();
                obtain.unmarshall(bArr, 0, bArr.length);
                obtain.setDataPosition(0);
                return (T) ((Parcelable.Creator) cls.getDeclaredField(C5731l.m12674a("007Ehkhihhfmflhmhi")).get(null)).createFromParcel(obtain);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return t;
            }
        }
    }
}
