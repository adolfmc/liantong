package com.baidu.location.p137b;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.baidu.location.Jni;
import com.baidu.location.p140e.AbstractC2729f;
import com.baidu.location.p140e.C2725d;
import com.baidu.location.p140e.C2735k;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;
import szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.location.b.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2650l {

    /* renamed from: c */
    private static Object f5293c = new Object();

    /* renamed from: d */
    private static C2650l f5294d = null;

    /* renamed from: e */
    private static final String f5295e = C2735k.m19041g() + "/hst.db";

    /* renamed from: f */
    private SQLiteDatabase f5298f = null;

    /* renamed from: g */
    private boolean f5299g = false;

    /* renamed from: a */
    C2651a f5296a = null;

    /* renamed from: b */
    C2651a f5297b = null;

    /* renamed from: h */
    private String f5300h = null;

    /* renamed from: i */
    private int f5301i = -2;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.baidu.location.b.l$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2651a extends AbstractC2729f {

        /* renamed from: b */
        private String f5303b = null;

        /* renamed from: c */
        private String f5304c = null;

        /* renamed from: d */
        private boolean f5305d = true;

        /* renamed from: e */
        private boolean f5306e = false;

        C2651a() {
            this.f5727j = new HashMap();
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19077a() {
            this.f5725h = 1;
            String encodeTp4 = Jni.encodeTp4(this.f5304c);
            this.f5304c = null;
            this.f5727j.put(BaseLocationBox.TYPE, encodeTp4);
        }

        /* renamed from: a */
        public void m19425a(String str, String str2) {
            if (C2650l.this.f5299g) {
                return;
            }
            C2650l.this.f5299g = true;
            this.f5303b = str;
            this.f5304c = str2;
            ExecutorService m19308c = C2678w.m19310a().m19308c();
            if (m19308c != null) {
                m19076a(m19308c, C2725d.f5705c);
            } else {
                m19073b(C2725d.f5705c);
            }
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19074a(boolean z) {
            if (z && this.f5726i != null) {
                try {
                    String str = this.f5726i;
                    if (this.f5305d) {
                        JSONObject jSONObject = new JSONObject(str);
                        JSONObject jSONObject2 = jSONObject.has("content") ? jSONObject.getJSONObject("content") : null;
                        if (jSONObject2 != null && jSONObject2.has("imo")) {
                            Long valueOf = Long.valueOf(jSONObject2.getJSONObject("imo").getString("mac"));
                            int i = jSONObject2.getJSONObject("imo").getInt("mv");
                            if (Jni.encode3(this.f5303b).longValue() == valueOf.longValue()) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
                                contentValues.put("hst", Integer.valueOf(i));
                                try {
                                    SQLiteDatabase sQLiteDatabase = C2650l.this.f5298f;
                                    String str2 = "id = \"" + valueOf + "\"";
                                    if ((!(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.update("hstdata", contentValues, str2, null) : NBSSQLiteInstrumentation.update(sQLiteDatabase, "hstdata", contentValues, str2, null)) <= 0) {
                                        contentValues.put("id", valueOf);
                                        SQLiteDatabase sQLiteDatabase2 = C2650l.this.f5298f;
                                        if (sQLiteDatabase2 instanceof SQLiteDatabase) {
                                            NBSSQLiteInstrumentation.insert(sQLiteDatabase2, "hstdata", null, contentValues);
                                        } else {
                                            sQLiteDatabase2.insert("hstdata", null, contentValues);
                                        }
                                    }
                                } catch (Exception unused) {
                                }
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("mac", this.f5303b.getBytes());
                                bundle.putInt("hotspot", i);
                                C2650l.this.m19438a(bundle);
                            }
                        }
                    }
                } catch (Exception unused2) {
                }
            } else if (this.f5305d) {
                C2650l.this.m19426f();
            }
            if (this.f5727j != null) {
                this.f5727j.clear();
            }
            C2650l.this.f5299g = false;
        }
    }

    /* renamed from: a */
    public static C2650l m19439a() {
        C2650l c2650l;
        synchronized (f5293c) {
            if (f5294d == null) {
                f5294d = new C2650l();
            }
            c2650l = f5294d;
        }
        return c2650l;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0094  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m19433a(boolean r5) {
        /*
            r4 = this;
            com.baidu.location.c.b r0 = com.baidu.location.p138c.C2689b.m19259a()
            com.baidu.location.c.a r0 = r0.m19237f()
            com.baidu.location.c.l r1 = com.baidu.location.p138c.C2711l.m19133a()
            com.baidu.location.c.k r1 = r1.m19111o()
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3)
            if (r0 == 0) goto L26
            boolean r3 = r0.m19267b()
            if (r3 == 0) goto L26
            java.lang.String r0 = r0.m19261h()
            r2.append(r0)
        L26:
            if (r1 == 0) goto L36
            int r0 = r1.m19156a()
            r3 = 1
            if (r0 <= r3) goto L36
            r0 = 15
            java.lang.String r0 = r1.m19155a(r0)
            goto L48
        L36:
            com.baidu.location.c.l r0 = com.baidu.location.p138c.C2711l.m19133a()
            java.lang.String r0 = r0.m19114l()
            if (r0 == 0) goto L4b
            com.baidu.location.c.l r0 = com.baidu.location.p138c.C2711l.m19133a()
            java.lang.String r0 = r0.m19114l()
        L48:
            r2.append(r0)
        L4b:
            if (r5 == 0) goto L52
            java.lang.String r5 = "&imo=1"
            r2.append(r5)
        L52:
            com.baidu.location.c.f r5 = com.baidu.location.p138c.C2697f.m19228a()
            java.lang.String r5 = r5.m19158m()
            r2.append(r5)
            com.baidu.location.e.b r5 = com.baidu.location.p140e.C2721b.m19096a()
            r0 = 0
            java.lang.String r5 = r5.m19093a(r0)
            r2.append(r5)
            com.baidu.location.b.b r5 = com.baidu.location.p137b.C2628b.m19560a()
            java.lang.String r5 = r5.m19548c()
            r2.append(r5)
            com.baidu.location.b.c r5 = com.baidu.location.p137b.C2631c.m19525a()
            java.lang.String r5 = r5.m19515c()
            r2.append(r5)
            android.content.Context r5 = com.baidu.location.ServiceC2737f.getServiceContext()
            java.lang.String r5 = com.baidu.location.p140e.C2735k.m19047d(r5)
            r2.append(r5)
            android.content.Context r5 = com.baidu.location.ServiceC2737f.getServiceContext()
            int r5 = com.baidu.location.p140e.C2735k.m19055b(r5)
            if (r5 < 0) goto L9c
            java.lang.String r0 = "&lmd="
            r2.append(r0)
            r2.append(r5)
        L9c:
            java.lang.String r5 = r2.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2650l.m19433a(boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19438a(Bundle bundle) {
        C2628b.m19560a().m19559a(bundle, 406);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m19426f() {
        Bundle bundle = new Bundle();
        bundle.putInt("hotspot", -1);
        m19438a(bundle);
    }

    /* renamed from: a */
    public void m19434a(String str) {
        if (this.f5299g) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.has("content") ? jSONObject.getJSONObject("content") : null;
            if (jSONObject2 == null || !jSONObject2.has("imo")) {
                return;
            }
            Long valueOf = Long.valueOf(jSONObject2.getJSONObject("imo").getString("mac"));
            int i = jSONObject2.getJSONObject("imo").getInt("mv");
            ContentValues contentValues = new ContentValues();
            contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
            contentValues.put("hst", Integer.valueOf(i));
            SQLiteDatabase sQLiteDatabase = this.f5298f;
            String str2 = "id = \"" + valueOf + "\"";
            if ((!(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.update("hstdata", contentValues, str2, null) : NBSSQLiteInstrumentation.update(sQLiteDatabase, "hstdata", contentValues, str2, null)) <= 0) {
                contentValues.put("id", valueOf);
                SQLiteDatabase sQLiteDatabase2 = this.f5298f;
                if (sQLiteDatabase2 instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.insert(sQLiteDatabase2, "hstdata", null, contentValues);
                } else {
                    sQLiteDatabase2.insert("hstdata", null, contentValues);
                }
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public void m19432b() {
        try {
            File file = new File(f5295e);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                this.f5298f = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
                SQLiteDatabase sQLiteDatabase = this.f5298f;
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS hstdata(id Long PRIMARY KEY,hst INT,tt INT);");
                } else {
                    sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS hstdata(id Long PRIMARY KEY,hst INT,tt INT);");
                }
                this.f5298f.setVersion(1);
            }
        } catch (Exception unused) {
            this.f5298f = null;
        }
    }

    /* renamed from: c */
    public void m19430c() {
        SQLiteDatabase sQLiteDatabase = this.f5298f;
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.f5298f = null;
                throw th;
            }
            this.f5298f = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
        if (r3 != null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0093, code lost:
        if (r3 != null) goto L36;
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int m19428d() {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = -3
            boolean r1 = r8.f5299g     // Catch: java.lang.Throwable -> L9a
            if (r1 == 0) goto L8
            monitor-exit(r8)
            return r0
        L8:
            com.baidu.location.c.l r1 = com.baidu.location.p138c.C2711l.m19133a()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            boolean r1 = r1.m19117i()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            if (r1 == 0) goto L96
            android.database.sqlite.SQLiteDatabase r1 = r8.f5298f     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            if (r1 == 0) goto L96
            com.baidu.location.c.l r1 = com.baidu.location.p138c.C2711l.m19133a()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            android.net.wifi.WifiInfo r1 = r1.m19115k()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            if (r1 == 0) goto L96
            java.lang.String r2 = r1.getBSSID()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            if (r2 == 0) goto L96
            java.lang.String r1 = r1.getBSSID()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            java.lang.String r2 = ":"
            java.lang.String r3 = ""
            java.lang.String r1 = r1.replace(r2, r3)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            java.lang.Long r2 = com.baidu.location.Jni.encode3(r1)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            java.lang.String r3 = r8.f5300h     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            r4 = -2
            if (r3 == 0) goto L4a
            java.lang.String r3 = r8.f5300h     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            boolean r3 = r1.equals(r3)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            if (r3 == 0) goto L4a
            int r3 = r8.f5301i     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            if (r3 <= r4) goto L4a
            int r0 = r8.f5301i     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            goto L96
        L4a:
            r3 = 0
            android.database.sqlite.SQLiteDatabase r5 = r8.f5298f     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            r6.<init>()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            java.lang.String r7 = "select * from hstdata where id = \""
            r6.append(r7)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            r6.append(r2)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            java.lang.String r2 = "\";"
            r6.append(r2)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            java.lang.String r2 = r6.toString()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            boolean r6 = r5 instanceof android.database.sqlite.SQLiteDatabase     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            if (r6 != 0) goto L6c
            android.database.Cursor r2 = r5.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            goto L72
        L6c:
            android.database.sqlite.SQLiteDatabase r5 = (android.database.sqlite.SQLiteDatabase) r5     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            android.database.Cursor r2 = com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation.rawQuery(r5, r2, r3)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
        L72:
            r3 = r2
            if (r3 == 0) goto L85
            boolean r2 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            if (r2 == 0) goto L85
            r2 = 1
            int r0 = r3.getInt(r2)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            r8.f5300h = r1     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            r8.f5301i = r0     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L93
            goto L86
        L85:
            r0 = r4
        L86:
            if (r3 == 0) goto L96
        L88:
            r3.close()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
            goto L96
        L8c:
            r1 = move-exception
            if (r3 == 0) goto L92
            r3.close()     // Catch: java.lang.Exception -> L92 java.lang.Throwable -> L9a
        L92:
            throw r1     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> L9a
        L93:
            if (r3 == 0) goto L96
            goto L88
        L96:
            r8.f5301i = r0     // Catch: java.lang.Throwable -> L9a
            monitor-exit(r8)
            return r0
        L9a:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2650l.m19428d():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0098, code lost:
        if (r3 == null) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00aa A[Catch: Exception -> 0x00c6, TryCatch #1 {Exception -> 0x00c6, blocks: (B:5:0x0005, B:7:0x000f, B:9:0x0013, B:11:0x001d, B:13:0x0023, B:39:0x00aa, B:41:0x00ae, B:42:0x00b5, B:44:0x00b9, B:45:0x00c3, B:15:0x0036, B:17:0x0052, B:21:0x0060, B:23:0x0066, B:26:0x0080, B:18:0x0057), top: B:50:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m19427e() {
        /*
            r10 = this;
            boolean r0 = r10.f5299g
            if (r0 == 0) goto L5
            return
        L5:
            com.baidu.location.c.l r0 = com.baidu.location.p138c.C2711l.m19133a()     // Catch: java.lang.Exception -> Lc6
            boolean r0 = r0.m19117i()     // Catch: java.lang.Exception -> Lc6
            if (r0 == 0) goto Lc3
            android.database.sqlite.SQLiteDatabase r0 = r10.f5298f     // Catch: java.lang.Exception -> Lc6
            if (r0 == 0) goto Lc3
            com.baidu.location.c.l r0 = com.baidu.location.p138c.C2711l.m19133a()     // Catch: java.lang.Exception -> Lc6
            android.net.wifi.WifiInfo r0 = r0.m19115k()     // Catch: java.lang.Exception -> Lc6
            if (r0 == 0) goto Lc3
            java.lang.String r1 = r0.getBSSID()     // Catch: java.lang.Exception -> Lc6
            if (r1 == 0) goto Lc3
            java.lang.String r0 = r0.getBSSID()     // Catch: java.lang.Exception -> Lc6
            java.lang.String r1 = ":"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.replace(r1, r2)     // Catch: java.lang.Exception -> Lc6
            java.lang.Long r1 = com.baidu.location.Jni.encode3(r0)     // Catch: java.lang.Exception -> Lc6
            r2 = 0
            r3 = 0
            r4 = 1
            android.database.sqlite.SQLiteDatabase r5 = r10.f5298f     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            r6.<init>()     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            java.lang.String r7 = "select * from hstdata where id = \""
            r6.append(r7)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            r6.append(r1)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            java.lang.String r1 = "\";"
            r6.append(r1)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            java.lang.String r1 = r6.toString()     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            boolean r6 = r5 instanceof android.database.sqlite.SQLiteDatabase     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            if (r6 != 0) goto L57
            android.database.Cursor r1 = r5.rawQuery(r1, r3)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            goto L5d
        L57:
            android.database.sqlite.SQLiteDatabase r5 = (android.database.sqlite.SQLiteDatabase) r5     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            android.database.Cursor r1 = com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation.rawQuery(r5, r1, r3)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
        L5d:
            r3 = r1
            if (r3 == 0) goto L97
            boolean r1 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            if (r1 == 0) goto L97
            int r1 = r3.getInt(r4)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            r5 = 2
            int r5 = r3.getInt(r5)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            long r8 = (long) r5     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            long r6 = r6 - r8
            r8 = 259200(0x3f480, double:1.28062E-318)
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 <= 0) goto L80
            goto L97
        L80:
            android.os.Bundle r5 = new android.os.Bundle     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            r5.<init>()     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            java.lang.String r6 = "mac"
            byte[] r7 = r0.getBytes()     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            r5.putByteArray(r6, r7)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            java.lang.String r6 = "hotspot"
            r5.putInt(r6, r1)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            r10.m19438a(r5)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La5
            goto L98
        L97:
            r2 = r4
        L98:
            if (r3 == 0) goto La8
        L9a:
            r3.close()     // Catch: java.lang.Exception -> La8
            goto La8
        L9e:
            r0 = move-exception
            if (r3 == 0) goto La4
            r3.close()     // Catch: java.lang.Exception -> La4
        La4:
            throw r0     // Catch: java.lang.Exception -> Lc6
        La5:
            if (r3 == 0) goto La8
            goto L9a
        La8:
            if (r2 == 0) goto Lc6
            com.baidu.location.b.l$a r1 = r10.f5296a     // Catch: java.lang.Exception -> Lc6
            if (r1 != 0) goto Lb5
            com.baidu.location.b.l$a r1 = new com.baidu.location.b.l$a     // Catch: java.lang.Exception -> Lc6
            r1.<init>()     // Catch: java.lang.Exception -> Lc6
            r10.f5296a = r1     // Catch: java.lang.Exception -> Lc6
        Lb5:
            com.baidu.location.b.l$a r1 = r10.f5296a     // Catch: java.lang.Exception -> Lc6
            if (r1 == 0) goto Lc6
            com.baidu.location.b.l$a r1 = r10.f5296a     // Catch: java.lang.Exception -> Lc6
            java.lang.String r2 = r10.m19433a(r4)     // Catch: java.lang.Exception -> Lc6
            r1.m19425a(r0, r2)     // Catch: java.lang.Exception -> Lc6
            goto Lc6
        Lc3:
            r10.m19426f()     // Catch: java.lang.Exception -> Lc6
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2650l.m19427e():void");
    }
}
