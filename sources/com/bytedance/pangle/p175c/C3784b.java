package com.bytedance.pangle.p175c;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.bytedance.pangle.c.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3784b {

    /* renamed from: b */
    public static String f9036b = "request_finish";

    /* renamed from: c */
    public static String f9037c = "download_start";

    /* renamed from: d */
    public static String f9038d = "download_finish";

    /* renamed from: e */
    public static String f9039e = "install_start";

    /* renamed from: f */
    public static String f9040f = "install_finish";

    /* renamed from: g */
    public static String f9041g = "load_start";

    /* renamed from: h */
    public static String f9042h = "load_finish";

    /* renamed from: i */
    private static volatile C3784b f9043i;

    /* renamed from: a */
    public final List<InterfaceC3783a> f9044a = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.c.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3785a {

        /* renamed from: A */
        public static int f9045A = 41000;

        /* renamed from: B */
        public static int f9046B = 42000;

        /* renamed from: a */
        public static int f9047a = 12000;

        /* renamed from: b */
        public static int f9048b = 12001;

        /* renamed from: c */
        public static int f9049c = 12002;

        /* renamed from: d */
        public static int f9050d = 12003;

        /* renamed from: e */
        public static int f9051e = 12004;

        /* renamed from: f */
        public static int f9052f = 20000;

        /* renamed from: g */
        public static int f9053g = 21000;

        /* renamed from: h */
        public static int f9054h = 21001;

        /* renamed from: i */
        public static int f9055i = 21002;

        /* renamed from: j */
        public static int f9056j = 22000;

        /* renamed from: k */
        public static int f9057k = 22001;

        /* renamed from: l */
        public static int f9058l = 22002;

        /* renamed from: m */
        public static int f9059m = 22999;

        /* renamed from: n */
        public static int f9060n = 30000;

        /* renamed from: o */
        public static int f9061o = 31000;

        /* renamed from: p */
        public static int f9062p = 32000;

        /* renamed from: q */
        public static int f9063q = 32001;

        /* renamed from: r */
        public static int f9064r = 32002;

        /* renamed from: s */
        public static int f9065s = 32003;

        /* renamed from: t */
        public static int f9066t = 32004;

        /* renamed from: u */
        public static int f9067u = 32005;

        /* renamed from: v */
        public static int f9068v = 32006;

        /* renamed from: w */
        public static int f9069w = 32007;

        /* renamed from: x */
        public static int f9070x = 32008;

        /* renamed from: y */
        public static int f9071y = 32999;

        /* renamed from: z */
        public static int f9072z = 40000;
    }

    /* renamed from: a */
    public static C3784b m16961a() {
        if (f9043i == null) {
            synchronized (C3784b.class) {
                f9043i = new C3784b();
            }
        }
        return f9043i;
    }

    private C3784b() {
    }

    /* renamed from: a */
    public final void m16960a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        synchronized (this.f9044a) {
            for (InterfaceC3783a interfaceC3783a : this.f9044a) {
                interfaceC3783a.mo16796a(str, jSONObject, jSONObject2, jSONObject3);
            }
        }
    }
}
