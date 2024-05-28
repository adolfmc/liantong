package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0;

import java.io.ByteArrayOutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;

/* renamed from: a.a.a.a.a.e.a.c.d0.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0511e implements InterfaceC0605o {

    /* renamed from: a */
    public ByteArrayOutputStream f1668a = new ByteArrayOutputStream();

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public String mo22748a() {
        return "NULL";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public void mo22747a(byte b) {
        this.f1668a.write(b);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: b */
    public void mo22744b() {
        this.f1668a.reset();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: c */
    public int mo22743c() {
        return this.f1668a.size();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public void mo22745a(byte[] bArr, int i, int i2) {
        this.f1668a.write(bArr, i, i2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public int mo22746a(byte[] bArr, int i) {
        byte[] byteArray = this.f1668a.toByteArray();
        System.arraycopy(byteArray, 0, bArr, i, byteArray.length);
        mo22744b();
        return byteArray.length;
    }
}
