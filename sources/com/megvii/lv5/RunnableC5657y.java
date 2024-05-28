package com.megvii.lv5;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.y */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5657y implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C5641x f13921a;

    public RunnableC5657y(C5641x c5641x) {
        this.f13921a = c5641x;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f13921a.f13857p = new C5475i1(this.f13921a.getView().getContext(), "action_jd");
            C5641x c5641x = this.f13921a;
            C5475i1 c5475i1 = c5641x.f13857p;
            c5475i1.f12804h = EnumC5488k1.AllFrames;
            c5475i1.f12803g = c5641x.f13866y.f12946P1;
            if (c5641x.f13858q) {
                new C5482j1(c5475i1, null, c5641x.f13831Z, c5641x.getCameraHeight(), this.f13921a.getCameraWidth(), 0);
            }
            this.f13921a.f13857p.m13453c();
            this.f13921a.f13857p.m13452d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
