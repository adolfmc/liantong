package com.networkbench.nbslens.nbsnativecrashlib;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSNativeCrash {
    private static String appId;
    private static String appVersion;
    private static boolean initialized;
    private static String logDir;
    private static InterfaceC6794h logger = new C6779c();

    private NBSNativeCrash() {
    }

    public static int init(Context context) {
        return init(context, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00c9 A[Catch: all -> 0x0188, TryCatch #0 {, blocks: (B:4:0x0003, B:10:0x000b, B:15:0x0012, B:20:0x001e, B:22:0x0026, B:24:0x002a, B:25:0x002e, B:27:0x003c, B:28:0x0041, B:30:0x0049, B:31:0x004f, B:33:0x005b, B:34:0x0072, B:36:0x008c, B:48:0x00aa, B:50:0x00c9, B:52:0x00ff, B:54:0x0103, B:55:0x0105, B:57:0x010b, B:59:0x010f, B:71:0x017f, B:63:0x0117, B:65:0x0147, B:70:0x0156, B:40:0x0093, B:42:0x009b, B:44:0x00a1, B:46:0x00a7), top: B:77:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0103 A[Catch: all -> 0x0188, TryCatch #0 {, blocks: (B:4:0x0003, B:10:0x000b, B:15:0x0012, B:20:0x001e, B:22:0x0026, B:24:0x002a, B:25:0x002e, B:27:0x003c, B:28:0x0041, B:30:0x0049, B:31:0x004f, B:33:0x005b, B:34:0x0072, B:36:0x008c, B:48:0x00aa, B:50:0x00c9, B:52:0x00ff, B:54:0x0103, B:55:0x0105, B:57:0x010b, B:59:0x010f, B:71:0x017f, B:63:0x0117, B:65:0x0147, B:70:0x0156, B:40:0x0093, B:42:0x009b, B:44:0x00a1, B:46:0x00a7), top: B:77:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0147 A[Catch: all -> 0x0188, TryCatch #0 {, blocks: (B:4:0x0003, B:10:0x000b, B:15:0x0012, B:20:0x001e, B:22:0x0026, B:24:0x002a, B:25:0x002e, B:27:0x003c, B:28:0x0041, B:30:0x0049, B:31:0x004f, B:33:0x005b, B:34:0x0072, B:36:0x008c, B:48:0x00aa, B:50:0x00c9, B:52:0x00ff, B:54:0x0103, B:55:0x0105, B:57:0x010b, B:59:0x010f, B:71:0x017f, B:63:0x0117, B:65:0x0147, B:70:0x0156, B:40:0x0093, B:42:0x009b, B:44:0x00a1, B:46:0x00a7), top: B:77:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0152  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized int init(android.content.Context r30, com.networkbench.nbslens.nbsnativecrashlib.NBSNativeCrash.C6774a r31) {
        /*
            Method dump skipped, instructions count: 395
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.nbslens.nbsnativecrashlib.NBSNativeCrash.init(android.content.Context, com.networkbench.nbslens.nbsnativecrashlib.NBSNativeCrash$a):int");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.nbslens.nbsnativecrashlib.NBSNativeCrash$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6774a {

        /* renamed from: a */
        String f17516a = null;

        /* renamed from: b */
        String f17517b = null;

        /* renamed from: c */
        int f17518c = 5000;

        /* renamed from: d */
        InterfaceC6794h f17519d = null;

        /* renamed from: e */
        int f17520e = 0;

        /* renamed from: f */
        int f17521f = 128;

        /* renamed from: g */
        boolean f17522g = true;

        /* renamed from: h */
        boolean f17523h = true;

        /* renamed from: i */
        int f17524i = 10;

        /* renamed from: j */
        int f17525j = 50;

        /* renamed from: k */
        int f17526k = 50;

        /* renamed from: l */
        int f17527l = 200;

        /* renamed from: m */
        boolean f17528m = true;

        /* renamed from: n */
        boolean f17529n = true;

        /* renamed from: o */
        boolean f17530o = true;

        /* renamed from: p */
        int f17531p = 0;

        /* renamed from: q */
        String[] f17532q = null;

        /* renamed from: r */
        InterfaceC6792f f17533r = null;

        /* renamed from: s */
        boolean f17534s = true;

        /* renamed from: t */
        boolean f17535t = true;

        /* renamed from: u */
        int f17536u = 10;

        /* renamed from: v */
        int f17537v = 50;

        /* renamed from: w */
        int f17538w = 50;

        /* renamed from: x */
        int f17539x = 200;

        /* renamed from: y */
        boolean f17540y = true;

        /* renamed from: z */
        boolean f17541z = false;

        /* renamed from: A */
        boolean f17500A = false;

        /* renamed from: B */
        boolean f17501B = false;

        /* renamed from: C */
        boolean f17502C = false;

        /* renamed from: D */
        int f17503D = 0;

        /* renamed from: E */
        String[] f17504E = null;

        /* renamed from: F */
        InterfaceC6792f f17505F = null;

        /* renamed from: G */
        boolean f17506G = false;

        /* renamed from: H */
        boolean f17507H = true;

        /* renamed from: I */
        boolean f17508I = true;

        /* renamed from: J */
        int f17509J = 10;

        /* renamed from: K */
        int f17510K = 50;

        /* renamed from: L */
        int f17511L = 50;

        /* renamed from: M */
        int f17512M = 200;

        /* renamed from: N */
        boolean f17513N = true;

        /* renamed from: O */
        boolean f17514O = true;

        /* renamed from: P */
        InterfaceC6792f f17515P = null;

        /* renamed from: a */
        public C6774a m8514a(String str) {
            this.f17516a = str;
            return this;
        }

        /* renamed from: b */
        public C6774a m8508b(String str) {
            this.f17517b = str;
            return this;
        }

        /* renamed from: a */
        public C6774a m8517a(int i) {
            if (i < 0) {
                i = 0;
            }
            this.f17518c = i;
            return this;
        }

        /* renamed from: a */
        public C6774a m8515a(InterfaceC6794h interfaceC6794h) {
            this.f17519d = interfaceC6794h;
            return this;
        }

        /* renamed from: b */
        public C6774a m8510b(int i) {
            if (i < 0) {
                i = 0;
            }
            this.f17520e = i;
            return this;
        }

        /* renamed from: c */
        public C6774a m8504c(int i) {
            if (i < 0) {
                i = 0;
            }
            this.f17521f = i;
            return this;
        }

        /* renamed from: a */
        public C6774a m8518a() {
            this.f17522g = true;
            return this;
        }

        /* renamed from: b */
        public C6774a m8511b() {
            this.f17522g = false;
            return this;
        }

        /* renamed from: a */
        public C6774a m8513a(boolean z) {
            this.f17523h = z;
            return this;
        }

        /* renamed from: d */
        public C6774a m8500d(int i) {
            if (i < 1) {
                i = 1;
            }
            this.f17524i = i;
            return this;
        }

        /* renamed from: e */
        public C6774a m8497e(int i) {
            this.f17525j = i;
            return this;
        }

        /* renamed from: f */
        public C6774a m8494f(int i) {
            this.f17526k = i;
            return this;
        }

        /* renamed from: g */
        public C6774a m8492g(int i) {
            this.f17527l = i;
            return this;
        }

        /* renamed from: b */
        public C6774a m8507b(boolean z) {
            this.f17528m = z;
            return this;
        }

        /* renamed from: c */
        public C6774a m8502c(boolean z) {
            this.f17529n = z;
            return this;
        }

        /* renamed from: d */
        public C6774a m8499d(boolean z) {
            this.f17530o = z;
            return this;
        }

        /* renamed from: h */
        public C6774a m8490h(int i) {
            if (i < 0) {
                i = 0;
            }
            this.f17531p = i;
            return this;
        }

        /* renamed from: a */
        public C6774a m8512a(String[] strArr) {
            this.f17532q = strArr;
            return this;
        }

        /* renamed from: a */
        public C6774a m8516a(InterfaceC6792f interfaceC6792f) {
            this.f17533r = interfaceC6792f;
            return this;
        }

        /* renamed from: c */
        public C6774a m8505c() {
            this.f17534s = true;
            return this;
        }

        /* renamed from: d */
        public C6774a m8501d() {
            this.f17534s = false;
            return this;
        }

        /* renamed from: e */
        public C6774a m8496e(boolean z) {
            this.f17535t = z;
            return this;
        }

        /* renamed from: i */
        public C6774a m8488i(int i) {
            if (i < 1) {
                i = 1;
            }
            this.f17536u = i;
            return this;
        }

        /* renamed from: j */
        public C6774a m8486j(int i) {
            this.f17537v = i;
            return this;
        }

        /* renamed from: k */
        public C6774a m8484k(int i) {
            this.f17538w = i;
            return this;
        }

        /* renamed from: l */
        public C6774a m8482l(int i) {
            this.f17539x = i;
            return this;
        }

        /* renamed from: f */
        public C6774a m8493f(boolean z) {
            this.f17540y = z;
            return this;
        }

        /* renamed from: g */
        public C6774a m8491g(boolean z) {
            this.f17541z = z;
            return this;
        }

        /* renamed from: h */
        public C6774a m8489h(boolean z) {
            this.f17500A = z;
            return this;
        }

        /* renamed from: i */
        public C6774a m8487i(boolean z) {
            this.f17501B = z;
            return this;
        }

        /* renamed from: j */
        public C6774a m8485j(boolean z) {
            this.f17502C = z;
            return this;
        }

        /* renamed from: m */
        public C6774a m8480m(int i) {
            if (i < 0) {
                i = 0;
            }
            this.f17503D = i;
            return this;
        }

        /* renamed from: b */
        public C6774a m8506b(String[] strArr) {
            this.f17504E = strArr;
            return this;
        }

        /* renamed from: b */
        public C6774a m8509b(InterfaceC6792f interfaceC6792f) {
            this.f17505F = interfaceC6792f;
            return this;
        }

        /* renamed from: e */
        public C6774a m8498e() {
            this.f17506G = true;
            return this;
        }

        /* renamed from: f */
        public C6774a m8495f() {
            this.f17506G = false;
            return this;
        }

        /* renamed from: k */
        public C6774a m8483k(boolean z) {
            this.f17507H = z;
            return this;
        }

        /* renamed from: l */
        public C6774a m8481l(boolean z) {
            this.f17508I = z;
            return this;
        }

        /* renamed from: n */
        public C6774a m8478n(int i) {
            if (i < 1) {
                i = 1;
            }
            this.f17509J = i;
            return this;
        }

        /* renamed from: o */
        public C6774a m8476o(int i) {
            this.f17510K = i;
            return this;
        }

        /* renamed from: p */
        public C6774a m8475p(int i) {
            this.f17511L = i;
            return this;
        }

        /* renamed from: q */
        public C6774a m8474q(int i) {
            this.f17512M = i;
            return this;
        }

        /* renamed from: m */
        public C6774a m8479m(boolean z) {
            this.f17513N = z;
            return this;
        }

        /* renamed from: n */
        public C6774a m8477n(boolean z) {
            this.f17514O = z;
            return this;
        }

        /* renamed from: c */
        public C6774a m8503c(InterfaceC6792f interfaceC6792f) {
            this.f17515P = interfaceC6792f;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m8522a() {
        return appId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m8521b() {
        return appVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m8520c() {
        return logDir;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static InterfaceC6794h m8519d() {
        return logger;
    }
}
