package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p045p0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0577k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0581m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0583n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0585o;

/* renamed from: a.a.a.a.a.e.a.c.p0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0624b implements InterfaceC0551l {

    /* renamed from: f */
    public C0577k f1941f;

    /* renamed from: g */
    public SecureRandom f1942g;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public void mo22702a(boolean z, InterfaceC0542i interfaceC0542i) {
        if (z) {
            if (interfaceC0542i instanceof C0578k0) {
                C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
                this.f1942g = c0578k0.m22800b();
                this.f1941f = (C0583n) c0578k0.m22801a();
                return;
            }
            this.f1942g = new SecureRandom();
            this.f1941f = (C0583n) interfaceC0542i;
            return;
        }
        this.f1941f = (C0585o) interfaceC0542i;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public BigInteger[] mo22701a(byte[] bArr) {
        BigInteger bigInteger;
        C0581m m22802b = this.f1941f.m22802b();
        BigInteger m22708a = m22708a(m22802b.m22790c(), bArr);
        int bitLength = m22802b.m22790c().bitLength();
        do {
            bigInteger = new BigInteger(bitLength, this.f1942g);
        } while (bigInteger.compareTo(m22802b.m22790c()) >= 0);
        BigInteger mod = m22802b.m22792a().modPow(bigInteger, m22802b.m22791b()).mod(m22802b.m22790c());
        return new BigInteger[]{mod, bigInteger.modInverse(m22802b.m22790c()).multiply(m22708a.add(((C0583n) this.f1941f).m22786c().multiply(mod))).mod(m22802b.m22790c())};
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public boolean mo22700a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        C0581m m22802b = this.f1941f.m22802b();
        BigInteger m22708a = m22708a(m22802b.m22790c(), bArr);
        BigInteger valueOf = BigInteger.valueOf(0L);
        if (valueOf.compareTo(bigInteger) >= 0 || m22802b.m22790c().compareTo(bigInteger) <= 0 || valueOf.compareTo(bigInteger2) >= 0 || m22802b.m22790c().compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger modInverse = bigInteger2.modInverse(m22802b.m22790c());
        return m22802b.m22792a().modPow(m22708a.multiply(modInverse).mod(m22802b.m22790c()), m22802b.m22791b()).multiply(((C0585o) this.f1941f).m22783c().modPow(bigInteger.multiply(modInverse).mod(m22802b.m22790c()), m22802b.m22791b())).mod(m22802b.m22791b()).mod(m22802b.m22790c()).equals(bigInteger);
    }

    /* renamed from: a */
    private BigInteger m22708a(BigInteger bigInteger, byte[] bArr) {
        if (bigInteger.bitLength() >= bArr.length * 8) {
            return new BigInteger(1, bArr);
        }
        int bitLength = bigInteger.bitLength() / 8;
        byte[] bArr2 = new byte[bitLength];
        System.arraycopy(bArr, 0, bArr2, 0, bitLength);
        return new BigInteger(1, bArr2);
    }
}
