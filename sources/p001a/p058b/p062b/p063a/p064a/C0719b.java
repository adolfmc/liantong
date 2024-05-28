package p001a.p058b.p062b.p063a.p064a;

import com.baidu.uaq.agent.android.InterfaceC3316b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.b.b.a.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0719b {

    /* renamed from: a */
    public static final InterfaceC3316b f2184a = new C0753e();

    /* renamed from: b */
    public static final Object f2185b = new Object();

    /* renamed from: c */
    public static InterfaceC3316b f2186c = f2184a;

    /* renamed from: a */
    public static InterfaceC3316b m22326a() {
        InterfaceC3316b interfaceC3316b;
        synchronized (f2185b) {
            interfaceC3316b = f2186c;
        }
        return interfaceC3316b;
    }

    /* renamed from: a */
    public static void m22325a(InterfaceC3316b interfaceC3316b) {
        synchronized (f2185b) {
            if (interfaceC3316b == null) {
                interfaceC3316b = f2184a;
            }
            f2186c = interfaceC3316b;
        }
    }

    /* renamed from: b */
    public static String m22324b() {
        return "1";
    }

    /* renamed from: c */
    public static String m22323c() {
        return "5.0.0";
    }
}
