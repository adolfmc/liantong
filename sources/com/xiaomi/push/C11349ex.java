package com.xiaomi.push;

import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.push.ex */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11349ex extends C11339er {
    public C11349ex() {
        m3956a("PING", (String) null);
        m3958a("0");
        m3962a(0);
    }

    @Override // com.xiaomi.push.C11339er
    /* renamed from: c */
    public int mo3914c() {
        if (m3968a().length == 0) {
            return 0;
        }
        return super.mo3914c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaomi.push.C11339er
    /* renamed from: a */
    public ByteBuffer mo3915a(ByteBuffer byteBuffer) {
        return m3968a().length == 0 ? byteBuffer : super.mo3915a(byteBuffer);
    }
}
