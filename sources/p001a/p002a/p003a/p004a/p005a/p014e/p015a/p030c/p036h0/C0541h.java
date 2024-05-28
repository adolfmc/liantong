package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p036h0;

import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.h0.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0541h extends OutputStream {

    /* renamed from: C */
    public InterfaceC0643x f1758C;

    public C0541h(InterfaceC0643x interfaceC0643x) {
        this.f1758C = interfaceC0643x;
    }

    /* renamed from: a */
    public InterfaceC0643x m22878a() {
        return this.f1758C;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f1758C.mo22638a((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f1758C.mo22636a(bArr, i, i2);
    }
}
