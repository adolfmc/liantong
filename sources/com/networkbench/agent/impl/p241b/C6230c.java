package com.networkbench.agent.impl.p241b;

import android.os.HandlerThread;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6653u;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6230c implements InterfaceC6234f {

    /* renamed from: c */
    private static InterfaceC6393e f15416c = C6394f.m10150a();

    /* renamed from: a */
    private C6231d f15417a = new C6231d(m10937a(), C6653u.m8700g());

    /* renamed from: b */
    private HandlerThread f15418b;

    /* renamed from: a */
    public HandlerThread m10937a() {
        HandlerThread handlerThread;
        synchronized (C6230c.class) {
            if (this.f15418b == null) {
                this.f15418b = new HandlerThread("nbs_block_probe");
                this.f15418b.start();
            }
            handlerThread = this.f15418b;
        }
        return handlerThread;
    }

    @Override // com.networkbench.agent.impl.p241b.InterfaceC6234f
    /* renamed from: a */
    public void mo10931a(long j) {
        C6396h.m10138d("BlockBeat   doFrame ....");
        this.f15417a.m10934b();
        this.f15417a.m10935a(j);
    }

    @Override // com.networkbench.agent.impl.p241b.InterfaceC6234f
    /* renamed from: b */
    public void mo10930b() {
        this.f15417a.m10934b();
    }

    @Override // com.networkbench.agent.impl.p241b.InterfaceC6234f
    /* renamed from: c */
    public synchronized Long mo10929c() {
        return Long.valueOf(this.f15417a.f15421a);
    }

    @Override // com.networkbench.agent.impl.p241b.InterfaceC6234f
    /* renamed from: d */
    public void mo10928d() {
        HandlerThread handlerThread = this.f15418b;
        if (handlerThread != null || handlerThread.isAlive()) {
            this.f15418b.quit();
        }
    }
}
