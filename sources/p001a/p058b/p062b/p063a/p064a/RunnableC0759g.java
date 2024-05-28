package p001a.p058b.p062b.p063a.p064a;

import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0728e;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0730g;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0736b;
import p001a.p058b.p062b.p063a.p064a.p067c.p070c.C0742e;

/* renamed from: a.b.b.a.a.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class RunnableC0759g implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        if (C0762h.f2359d.size() == 0) {
            return;
        }
        while (!C0762h.f2359d.isEmpty()) {
            try {
                Object remove = C0762h.f2359d.remove();
                if (remove instanceof C0736b) {
                    C0742e.f2275c.f2196f.f2245c.m22300a((C0736b) remove);
                } else if (remove instanceof C0730g) {
                    C0742e.f2275c.f2199i.m22306a((C0730g) remove);
                } else if (remove instanceof C0728e) {
                    C0742e.f2275c.f2200j.m22309a((C0728e) remove);
                }
            } catch (Exception e) {
                C0762h.f2356a.mo17426a("Caught error while TaskQueue dequeue: ", e);
                C0735a.m22302a(e);
            }
        }
    }
}
