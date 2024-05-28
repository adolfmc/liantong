package com.bytedance.pangle.p180g;

import java.nio.ByteBuffer;

/* renamed from: com.bytedance.pangle.g.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class C3853h implements InterfaceC3856k {

    /* renamed from: a */
    private final ByteBuffer f9195a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3853h(ByteBuffer byteBuffer) {
        this.f9195a = byteBuffer.slice();
    }

    @Override // com.bytedance.pangle.p180g.InterfaceC3856k
    /* renamed from: a */
    public final long mo16810a() {
        return this.f9195a.capacity();
    }

    @Override // com.bytedance.pangle.p180g.InterfaceC3856k
    /* renamed from: a */
    public final void mo16809a(InterfaceC3855j interfaceC3855j, long j, int i) {
        ByteBuffer slice;
        synchronized (this.f9195a) {
            this.f9195a.position(0);
            int i2 = (int) j;
            this.f9195a.limit(i + i2);
            this.f9195a.position(i2);
            slice = this.f9195a.slice();
        }
        interfaceC3855j.mo16811a(slice);
    }
}
