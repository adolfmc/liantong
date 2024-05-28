package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p035g0;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0502b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0639t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0504c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0590q0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0592r0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0594s0;

/* renamed from: a.a.a.a.a.e.a.c.g0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0532b implements InterfaceC0504c {

    /* renamed from: g */
    public static final BigInteger f1737g = BigInteger.valueOf(1);

    /* renamed from: f */
    public C0590q0 f1738f;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0504c
    /* renamed from: a */
    public void mo22887a(C0639t c0639t) {
        this.f1738f = (C0590q0) c0639t;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0504c
    /* renamed from: a */
    public C0502b mo22888a() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger multiply;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        int m22658b = this.f1738f.m22658b();
        int i = (m22658b + 1) / 2;
        int i2 = m22658b - i;
        int i3 = m22658b / 3;
        BigInteger m22769d = this.f1738f.m22769d();
        while (true) {
            bigInteger = new BigInteger(i, 1, this.f1738f.m22659a());
            if (!bigInteger.mod(m22769d).equals(f1737g) && bigInteger.isProbablePrime(this.f1738f.m22770c()) && m22769d.gcd(bigInteger.subtract(f1737g)).equals(f1737g)) {
                break;
            }
        }
        while (true) {
            bigInteger2 = new BigInteger(i2, 1, this.f1738f.m22659a());
            if (bigInteger2.subtract(bigInteger).abs().bitLength() >= i3 && !bigInteger2.mod(m22769d).equals(f1737g) && bigInteger2.isProbablePrime(this.f1738f.m22770c()) && m22769d.gcd(bigInteger2.subtract(f1737g)).equals(f1737g)) {
                multiply = bigInteger.multiply(bigInteger2);
                if (multiply.bitLength() == this.f1738f.m22658b()) {
                    break;
                }
                bigInteger = bigInteger.max(bigInteger2);
            }
        }
        if (bigInteger.compareTo(bigInteger2) < 0) {
            bigInteger4 = bigInteger2;
            bigInteger3 = bigInteger;
        } else {
            bigInteger3 = bigInteger2;
            bigInteger4 = bigInteger;
        }
        BigInteger subtract = bigInteger4.subtract(f1737g);
        BigInteger subtract2 = bigInteger3.subtract(f1737g);
        BigInteger modInverse = m22769d.modInverse(subtract.multiply(subtract2));
        return new C0502b((C0557a) new C0592r0(false, multiply, m22769d), (C0557a) new C0594s0(multiply, m22769d, modInverse, bigInteger4, bigInteger3, modInverse.remainder(subtract), modInverse.remainder(subtract2), bigInteger3.modInverse(bigInteger4)));
    }
}
