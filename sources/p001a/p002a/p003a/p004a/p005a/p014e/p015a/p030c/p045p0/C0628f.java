package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p045p0;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.DataLengthException;
import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0502b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p035g0.C0531a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0591r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0593s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0646a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0647b;

/* renamed from: a.a.a.a.a.e.a.c.p0.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0628f implements InterfaceC0551l {

    /* renamed from: f */
    public boolean f1950f;

    /* renamed from: g */
    public C0593s f1951g;

    /* renamed from: h */
    public SecureRandom f1952h;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public void mo22702a(boolean z, InterfaceC0542i interfaceC0542i) {
        this.f1950f = z;
        if (z) {
            if (interfaceC0542i instanceof C0578k0) {
                C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
                this.f1952h = c0578k0.m22800b();
                this.f1951g = (C0595t) c0578k0.m22801a();
                return;
            }
            this.f1952h = new SecureRandom();
            this.f1951g = (C0595t) interfaceC0542i;
            return;
        }
        this.f1951g = (C0596u) interfaceC0542i;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public BigInteger[] mo22701a(byte[] bArr) {
        C0502b mo22888a;
        BigInteger mod;
        if (this.f1950f) {
            BigInteger m22772d = ((C0595t) this.f1951g).m22765b().m22772d();
            int bitLength = m22772d.bitLength();
            BigInteger bigInteger = new BigInteger(1, bArr);
            int bitLength2 = bigInteger.bitLength();
            C0595t c0595t = (C0595t) this.f1951g;
            if (bitLength2 <= bitLength) {
                do {
                    C0531a c0531a = new C0531a();
                    c0531a.mo22887a(new C0591r(c0595t.m22765b(), this.f1952h));
                    mo22888a = c0531a.mo22888a();
                    mod = ((C0596u) mo22888a.m22973b()).m22757c().m22578d().mo22584g().add(bigInteger).mod(m22772d);
                } while (mod.equals(InterfaceC0647b.f1976a));
                return new BigInteger[]{mod, ((C0595t) mo22888a.m22974a()).m22758c().subtract(mod.multiply(c0595t.m22758c())).mod(m22772d)};
            }
            throw new DataLengthException("input too large for ECNR key.");
        }
        throw new IllegalStateException("not initialised for signing");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public boolean mo22700a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        if (!this.f1950f) {
            C0596u c0596u = (C0596u) this.f1951g;
            BigInteger m22772d = c0596u.m22765b().m22772d();
            int bitLength = m22772d.bitLength();
            BigInteger bigInteger3 = new BigInteger(1, bArr);
            if (bigInteger3.bitLength() <= bitLength) {
                if (bigInteger.compareTo(InterfaceC0647b.f1977b) < 0 || bigInteger.compareTo(m22772d) >= 0 || bigInteger2.compareTo(InterfaceC0647b.f1976a) < 0 || bigInteger2.compareTo(m22772d) >= 0) {
                    return false;
                }
                AbstractC0655f m22623c = C0646a.m22623c(c0596u.m22765b().m22774b(), bigInteger2, c0596u.m22757c(), bigInteger);
                if (m22623c.m22575g()) {
                    return false;
                }
                return bigInteger.subtract(m22623c.m22578d().mo22584g()).mod(m22772d).equals(bigInteger3);
            }
            throw new DataLengthException("input too large for ECNR key.");
        }
        throw new IllegalStateException("not initialised for verifying");
    }
}
