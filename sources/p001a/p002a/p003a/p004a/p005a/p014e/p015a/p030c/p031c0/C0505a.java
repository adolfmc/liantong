package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p031c0;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.DataLengthException;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0545j;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0549k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0631q;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.c.c0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0505a implements InterfaceC0549k {

    /* renamed from: a */
    public final InterfaceC0605o f1621a;

    /* renamed from: b */
    public final int f1622b;

    /* renamed from: c */
    public final SecureRandom f1623c;

    public C0505a(InterfaceC0631q interfaceC0631q, SecureRandom secureRandom) {
        this.f1621a = interfaceC0631q;
        this.f1622b = interfaceC0631q.mo22696d();
        this.f1623c = secureRandom;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0549k
    /* renamed from: a */
    public C0545j mo22867a(byte[] bArr) {
        int length = bArr.length;
        int i = this.f1622b;
        if (length <= i / 2) {
            byte[] bArr2 = new byte[i - bArr.length];
            this.f1623c.nextBytes(bArr2);
            return new C0545j(bArr2, m22968a(bArr2, bArr));
        }
        throw new DataLengthException("Message to be committed to too large for digest.");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0549k
    /* renamed from: a */
    public boolean mo22868a(C0545j c0545j, byte[] bArr) {
        return C0669a.m22466c(c0545j.m22873a(), m22968a(c0545j.m22872b(), bArr));
    }

    /* renamed from: a */
    private byte[] m22968a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[this.f1621a.mo22743c()];
        this.f1621a.mo22745a(bArr, 0, bArr.length);
        this.f1621a.mo22745a(bArr2, 0, bArr2.length);
        this.f1621a.mo22746a(bArr3, 0);
        return bArr3;
    }
}
