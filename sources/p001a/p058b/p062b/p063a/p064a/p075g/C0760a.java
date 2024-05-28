package p001a.p058b.p062b.p063a.p064a.p075g;

import com.baidu.uaq.agent.android.UAQ;
import java.util.concurrent.ConcurrentHashMap;
import p001a.p058b.p062b.p063a.p064a.p073e.C0754a;

/* renamed from: a.b.b.a.a.g.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0760a {

    /* renamed from: a */
    public static final C0760a f2350a = new C0760a();

    /* renamed from: b */
    public static final UAQ f2351b = UAQ.getInstance();

    /* renamed from: c */
    public ConcurrentHashMap<String, C0754a> f2352c = new ConcurrentHashMap<>();

    /* renamed from: a */
    public void m22252a(String str) {
        C0754a m22250b = m22250b(str);
        synchronized (m22250b) {
            m22250b.f2311j++;
        }
    }

    /* renamed from: a */
    public void m22251a(String str, float f) {
        C0754a m22250b = m22250b(str);
        synchronized (m22250b) {
            m22250b.m22269a(f);
        }
    }

    /* renamed from: b */
    public final C0754a m22250b(String str) {
        C0754a c0754a = this.f2352c.get(str);
        if (c0754a == null) {
            c0754a = new C0754a(str);
            if (f2351b.getConfig().isEnableStatsEngine()) {
                this.f2352c.put(str, c0754a);
            }
        }
        return c0754a;
    }
}
