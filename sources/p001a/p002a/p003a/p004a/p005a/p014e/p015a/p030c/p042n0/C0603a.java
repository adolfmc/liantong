package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p042n0;

import java.io.InputStream;
import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0640u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0571h;

/* renamed from: a.a.a.a.a.e.a.c.n0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0603a implements InterfaceC0640u {

    /* renamed from: a */
    public C0567f f1896a;

    public C0603a(C0567f c0567f) {
        this.f1896a = c0567f;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0640u
    /* renamed from: a */
    public C0557a mo22657a(InputStream inputStream) {
        int bitLength = (this.f1896a.m22819e().bitLength() + 7) / 8;
        byte[] bArr = new byte[bitLength];
        inputStream.read(bArr, 0, bitLength);
        return new C0571h(new BigInteger(1, bArr), this.f1896a);
    }
}
