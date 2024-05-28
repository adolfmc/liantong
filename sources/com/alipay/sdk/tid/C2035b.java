package com.alipay.sdk.tid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.encrypt.C2012b;
import com.alipay.sdk.util.C2040c;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Random;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.tid.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2035b {

    /* renamed from: a */
    public static final String f3833a = "alipay_tid_storage";

    /* renamed from: b */
    public static final String f3834b = "tidinfo";

    /* renamed from: c */
    public static final String f3835c = "upgraded_from_db";

    /* renamed from: d */
    public static final String f3836d = "tid";

    /* renamed from: e */
    public static final String f3837e = "client_key";

    /* renamed from: f */
    public static final String f3838f = "timestamp";

    /* renamed from: g */
    public static final String f3839g = "vimei";

    /* renamed from: h */
    public static final String f3840h = "vimsi";

    /* renamed from: i */
    private static Context f3841i;

    /* renamed from: o */
    private static C2035b f3842o;

    /* renamed from: j */
    private String f3843j;

    /* renamed from: k */
    private String f3844k;

    /* renamed from: l */
    private long f3845l;

    /* renamed from: m */
    private String f3846m;

    /* renamed from: n */
    private String f3847n;

    /* renamed from: p */
    private boolean f3848p = false;

    /* renamed from: o */
    private void m20740o() {
    }

    /* renamed from: a */
    public static synchronized C2035b m20758a(Context context) {
        C2035b c2035b;
        synchronized (C2035b.class) {
            if (f3842o == null) {
                C2040c.m20714b("TidStorage", "getInstance");
                f3842o = new C2035b();
            }
            if (f3841i == null) {
                f3842o.m20753b(context);
            }
            c2035b = f3842o;
        }
        return c2035b;
    }

    /* renamed from: b */
    private void m20753b(Context context) {
        if (context != null) {
            C2040c.m20714b("TidStorage", "TidStorage.initialize context != null");
            f3841i = context.getApplicationContext();
        }
        if (this.f3848p) {
            return;
        }
        this.f3848p = true;
        m20744k();
        m20743l();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: k */
    private void m20744k() {
        /*
            r8 = this;
            android.content.Context r0 = com.alipay.sdk.tid.C2035b.f3841i
            if (r0 != 0) goto L5
            return
        L5:
            java.lang.String r1 = "alipay_tid_storage"
            java.lang.String r2 = "upgraded_from_db"
            boolean r1 = com.alipay.sdk.tid.C2035b.C2036a.m20730d(r1, r2)
            if (r1 == 0) goto L19
            java.lang.String r0 = "TidStorage"
            java.lang.String r1 = "transferTidFromOldDb: already migrated. returning"
            com.alipay.sdk.util.C2040c.m20714b(r0, r1)
            return
        L19:
            r1 = 0
            java.lang.String r2 = "TidStorage"
            java.lang.String r3 = "transferTidFromOldDb: tid from db: "
            com.alipay.sdk.util.C2040c.m20714b(r2, r3)     // Catch: java.lang.Throwable -> L76
            com.alipay.sdk.tid.a r2 = new com.alipay.sdk.tid.a     // Catch: java.lang.Throwable -> L76
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L76
            com.alipay.sdk.util.a r1 = com.alipay.sdk.util.C2037a.m20728a(r0)     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = r1.m20726b()     // Catch: java.lang.Throwable -> L71
            com.alipay.sdk.util.a r3 = com.alipay.sdk.util.C2037a.m20728a(r0)     // Catch: java.lang.Throwable -> L71
            java.lang.String r3 = r3.m20729a()     // Catch: java.lang.Throwable -> L71
            java.lang.String r4 = r2.m20762a(r1, r3)     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = r2.m20761b(r1, r3)     // Catch: java.lang.Throwable -> L71
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L71
            if (r3 != 0) goto L6d
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L71
            if (r3 != 0) goto L6d
            java.lang.String r3 = "TidStorage"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L71
            r5.<init>()     // Catch: java.lang.Throwable -> L71
            java.lang.String r6 = "transferTidFromOldDb: tid from db is "
            r5.append(r6)     // Catch: java.lang.Throwable -> L71
            r5.append(r4)     // Catch: java.lang.Throwable -> L71
            java.lang.String r6 = ", "
            r5.append(r6)     // Catch: java.lang.Throwable -> L71
            r5.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L71
            com.alipay.sdk.util.C2040c.m20714b(r3, r5)     // Catch: java.lang.Throwable -> L71
            r8.m20757a(r4, r1)     // Catch: java.lang.Throwable -> L71
        L6d:
            r2.close()
            goto L80
        L71:
            r1 = move-exception
            goto L7a
        L73:
            r0 = move-exception
            r2 = r1
            goto Lb9
        L76:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
        L7a:
            com.alipay.sdk.util.C2040c.m20715a(r1)     // Catch: java.lang.Throwable -> Lb8
            if (r2 == 0) goto L80
            goto L6d
        L80:
            java.lang.String r1 = "TidStorage"
            java.lang.String r3 = "transferTidFromOldDb: removing database table"
            com.alipay.sdk.util.C2040c.m20714b(r1, r3)     // Catch: java.lang.Throwable -> L9c
            com.alipay.sdk.tid.a r1 = new com.alipay.sdk.tid.a     // Catch: java.lang.Throwable -> L9c
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L9c
            r1.m20763a()     // Catch: java.lang.Throwable -> L97
            r1.close()
            goto La5
        L94:
            r0 = move-exception
            r2 = r1
            goto Lb2
        L97:
            r0 = move-exception
            r2 = r1
            goto L9d
        L9a:
            r0 = move-exception
            goto Lb2
        L9c:
            r0 = move-exception
        L9d:
            com.alipay.sdk.util.C2040c.m20715a(r0)     // Catch: java.lang.Throwable -> L9a
            if (r2 == 0) goto La5
            r2.close()
        La5:
            java.lang.String r0 = "alipay_tid_storage"
            java.lang.String r1 = "upgraded_from_db"
            java.lang.String r2 = "updated"
            r3 = 0
            com.alipay.sdk.tid.C2035b.C2036a.m20735a(r0, r1, r2, r3)
            return
        Lb2:
            if (r2 == 0) goto Lb7
            r2.close()
        Lb7:
            throw r0
        Lb8:
            r0 = move-exception
        Lb9:
            if (r2 == 0) goto Lbe
            r2.close()
        Lbe:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C2035b.m20744k():void");
    }

    /* renamed from: a */
    public String m20759a() {
        C2040c.m20714b("TidStorage", "TidStorage.getTid " + this.f3843j);
        return this.f3843j;
    }

    /* renamed from: b */
    public String m20754b() {
        C2040c.m20714b("TidStorage", "TidStorage.getClientKey " + this.f3844k);
        return this.f3844k;
    }

    /* renamed from: c */
    public String m20752c() {
        C2040c.m20714b("TidStorage", "TidStorage.getVirtualImei " + this.f3846m);
        return this.f3846m;
    }

    /* renamed from: d */
    public String m20751d() {
        C2040c.m20714b("TidStorage", "TidStorage.getVirtualImsi " + this.f3847n);
        return this.f3847n;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00aa  */
    /* renamed from: l */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m20743l() {
        /*
            r9 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r1 = 0
            java.lang.String r2 = "alipay_tid_storage"
            java.lang.String r3 = "tidinfo"
            r4 = 1
            java.lang.String r2 = com.alipay.sdk.tid.C2035b.C2036a.m20734a(r2, r3, r4)     // Catch: java.lang.Exception -> L60
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L60
            if (r3 != 0) goto L5c
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L60
            r3.<init>(r2)     // Catch: java.lang.Exception -> L60
            java.lang.String r2 = "tid"
            java.lang.String r4 = ""
            java.lang.String r2 = r3.optString(r2, r4)     // Catch: java.lang.Exception -> L60
            java.lang.String r4 = "client_key"
            java.lang.String r5 = ""
            java.lang.String r4 = r3.optString(r4, r5)     // Catch: java.lang.Exception -> L59
            java.lang.String r5 = "timestamp"
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L56
            long r5 = r3.optLong(r5, r6)     // Catch: java.lang.Exception -> L56
            java.lang.Long r0 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> L56
            java.lang.String r5 = "vimei"
            java.lang.String r6 = ""
            java.lang.String r5 = r3.optString(r5, r6)     // Catch: java.lang.Exception -> L56
            java.lang.String r6 = "vimsi"
            java.lang.String r7 = ""
            java.lang.String r1 = r3.optString(r6, r7)     // Catch: java.lang.Exception -> L54
            r8 = r2
            r2 = r1
            r1 = r8
            goto L6a
        L54:
            r3 = move-exception
            goto L64
        L56:
            r3 = move-exception
            r5 = r1
            goto L64
        L59:
            r3 = move-exception
            r4 = r1
            goto L63
        L5c:
            r2 = r1
            r4 = r2
            r5 = r4
            goto L6a
        L60:
            r3 = move-exception
            r2 = r1
            r4 = r2
        L63:
            r5 = r4
        L64:
            com.alipay.sdk.util.C2040c.m20715a(r3)
            r8 = r2
            r2 = r1
            r1 = r8
        L6a:
            java.lang.String r3 = "TidStorage"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TidStorage.load "
            r6.append(r7)
            r6.append(r1)
            java.lang.String r7 = " "
            r6.append(r7)
            r6.append(r4)
            java.lang.String r7 = " "
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = " "
            r6.append(r7)
            r6.append(r5)
            java.lang.String r7 = " "
            r6.append(r7)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.alipay.sdk.util.C2040c.m20714b(r3, r6)
            boolean r3 = r9.m20756a(r1, r4, r5, r2)
            if (r3 == 0) goto Laa
            r9.m20742m()
            goto Lb8
        Laa:
            r9.f3843j = r1
            r9.f3844k = r4
            long r0 = r0.longValue()
            r9.f3845l = r0
            r9.f3846m = r5
            r9.f3847n = r2
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C2035b.m20743l():void");
    }

    /* renamed from: a */
    private boolean m20756a(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    /* renamed from: e */
    public boolean m20750e() {
        return TextUtils.isEmpty(this.f3843j) || TextUtils.isEmpty(this.f3844k) || TextUtils.isEmpty(this.f3846m) || TextUtils.isEmpty(this.f3847n);
    }

    /* renamed from: m */
    private void m20742m() {
        this.f3843j = "";
        this.f3844k = m20749f();
        this.f3845l = System.currentTimeMillis();
        this.f3846m = m20741n();
        this.f3847n = m20741n();
        C2036a.m20732b("alipay_tid_storage", "tidinfo");
    }

    /* renamed from: n */
    private String m20741n() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        return hexString + (random.nextInt(9000) + 1000);
    }

    /* renamed from: f */
    public String m20749f() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }

    /* renamed from: g */
    public void m20748g() {
        String format = String.format("TidStorage::delete > %s，%s，%s，%s，%s", this.f3843j, this.f3844k, Long.valueOf(this.f3845l), this.f3846m, this.f3847n);
        C2040c.m20714b("TidStorage", "TidStorage.delete " + format);
        m20742m();
    }

    /* renamed from: h */
    public boolean m20747h() {
        return m20750e();
    }

    /* renamed from: i */
    public Long m20746i() {
        return Long.valueOf(this.f3845l);
    }

    /* renamed from: a */
    public void m20757a(String str, String str2) {
        C2040c.m20714b("TidStorage", "TidStorage.save " + ("tid=" + str + ",clientKey=" + str2));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.f3843j = str;
        this.f3844k = str2;
        this.f3845l = System.currentTimeMillis();
        m20739p();
        m20740o();
    }

    /* renamed from: a */
    private void m20755a(String str, String str2, String str3, String str4, Long l) {
        if (m20756a(str, str2, str3, str4)) {
            return;
        }
        this.f3843j = str;
        this.f3844k = str2;
        this.f3846m = str3;
        this.f3847n = str4;
        if (l == null) {
            this.f3845l = System.currentTimeMillis();
        } else {
            this.f3845l = l.longValue();
        }
        m20739p();
    }

    /* renamed from: p */
    private void m20739p() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.f3843j);
            jSONObject.put("client_key", this.f3844k);
            jSONObject.put("timestamp", this.f3845l);
            jSONObject.put("vimei", this.f3846m);
            jSONObject.put("vimsi", this.f3847n);
            C2036a.m20735a("alipay_tid_storage", "tidinfo", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), true);
        } catch (Exception e) {
            C2040c.m20715a(e);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.tid.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2036a {
        /* renamed from: a */
        private static String m20738a() {
            return "!@#23457";
        }

        /* renamed from: a */
        public static boolean m20737a(String str, String str2) {
            if (C2035b.f3841i == null) {
                return false;
            }
            return C2035b.f3841i.getSharedPreferences(str, 0).contains(str2);
        }

        /* renamed from: b */
        public static void m20732b(String str, String str2) {
            if (C2035b.f3841i == null) {
                return;
            }
            C2035b.f3841i.getSharedPreferences(str, 0).edit().remove(str2).apply();
        }

        /* renamed from: c */
        public static String m20731c(String str, String str2) {
            return m20734a(str, str2, true);
        }

        /* renamed from: d */
        public static boolean m20730d(String str, String str2) {
            if (C2035b.f3841i == null) {
                return false;
            }
            return C2035b.f3841i.getSharedPreferences(str, 0).contains(str2);
        }

        /* renamed from: a */
        public static String m20734a(String str, String str2, boolean z) {
            String str3;
            if (C2035b.f3841i == null) {
                return null;
            }
            String string = C2035b.f3841i.getSharedPreferences(str, 0).getString(str2, null);
            if (TextUtils.isEmpty(string) || !z) {
                str3 = string;
            } else {
                String m20733b = m20733b();
                str3 = C2012b.m20836b(string, m20733b);
                if (TextUtils.isEmpty(str3)) {
                    str3 = C2012b.m20836b(string, m20738a());
                    if (!TextUtils.isEmpty(str3)) {
                        m20735a(str, str2, str3, true);
                    }
                }
                if (TextUtils.isEmpty(str3)) {
                    String.format("LocalPreference::getLocalPreferences failed %s，%s", string, m20733b);
                    C2040c.m20714b("TidStorage", "TidStorage.save LocalPreference::getLocalPreferences failed");
                }
            }
            C2040c.m20714b("TidStorage", "TidStorage.save LocalPreference::getLocalPreferences value " + string);
            return str3;
        }

        /* renamed from: a */
        public static void m20736a(String str, String str2, String str3) {
            m20735a(str, str2, str3, true);
        }

        /* renamed from: a */
        public static void m20735a(String str, String str2, String str3, boolean z) {
            if (C2035b.f3841i == null) {
                return;
            }
            SharedPreferences sharedPreferences = C2035b.f3841i.getSharedPreferences(str, 0);
            if (z) {
                String m20733b = m20733b();
                String m20837a = C2012b.m20837a(str3, m20733b);
                if (TextUtils.isEmpty(m20837a)) {
                    String.format("LocalPreference::putLocalPreferences failed %s，%s", str3, m20733b);
                }
                str3 = m20837a;
            }
            sharedPreferences.edit().putString(str2, str3).apply();
        }

        /* renamed from: b */
        private static String m20733b() {
            String str = "";
            try {
                str = C2035b.f3841i.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                C2040c.m20715a(th);
            }
            if (TextUtils.isEmpty(str)) {
                str = "unknow";
            }
            return (str + "00000000").substring(0, 8);
        }
    }
}
