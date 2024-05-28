package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0631q;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p046q0.AbstractC0632a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f;

/* renamed from: a.a.a.a.a.e.a.c.d0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0508b implements InterfaceC0631q, InterfaceC0674f {

    /* renamed from: o */
    public static final int f1628o = 128;

    /* renamed from: p */
    public static final long[] f1629p = {4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};

    /* renamed from: a */
    public byte[] f1630a;

    /* renamed from: b */
    public int f1631b;

    /* renamed from: c */
    public long f1632c;

    /* renamed from: d */
    public long f1633d;

    /* renamed from: e */
    public long f1634e;

    /* renamed from: f */
    public long f1635f;

    /* renamed from: g */
    public long f1636g;

    /* renamed from: h */
    public long f1637h;

    /* renamed from: i */
    public long f1638i;

    /* renamed from: j */
    public long f1639j;

    /* renamed from: k */
    public long f1640k;

    /* renamed from: l */
    public long f1641l;

    /* renamed from: m */
    public long[] f1642m;

    /* renamed from: n */
    public int f1643n;

    public AbstractC0508b() {
        this.f1642m = new long[80];
        this.f1630a = new byte[8];
        this.f1631b = 0;
        mo22744b();
    }

    /* renamed from: a */
    private long m22962a(long j) {
        return (j >>> 7) ^ (((j << 63) | (j >>> 1)) ^ ((j << 56) | (j >>> 8)));
    }

    /* renamed from: a */
    private long m22960a(long j, long j2, long j3) {
        return ((~j) & j3) ^ (j2 & j);
    }

    /* renamed from: b */
    private long m22958b(long j) {
        return (j >>> 6) ^ (((j << 45) | (j >>> 19)) ^ ((j << 3) | (j >>> 61)));
    }

    /* renamed from: b */
    private long m22957b(long j, long j2, long j3) {
        return ((j & j3) ^ (j & j2)) ^ (j2 & j3);
    }

    /* renamed from: c */
    private long m22955c(long j) {
        return ((j >>> 39) | (j << 25)) ^ (((j << 36) | (j >>> 28)) ^ ((j << 30) | (j >>> 34)));
    }

    /* renamed from: d */
    private long m22954d(long j) {
        return ((j >>> 41) | (j << 23)) ^ (((j << 50) | (j >>> 14)) ^ ((j << 46) | (j >>> 18)));
    }

    /* renamed from: h */
    private void m22951h() {
        long j = this.f1632c;
        if (j > 2305843009213693951L) {
            this.f1633d += j >>> 61;
            this.f1632c = j & 2305843009213693951L;
        }
    }

    /* renamed from: a */
    public void m22959a(AbstractC0508b abstractC0508b) {
        byte[] bArr = abstractC0508b.f1630a;
        System.arraycopy(bArr, 0, this.f1630a, 0, bArr.length);
        this.f1631b = abstractC0508b.f1631b;
        this.f1632c = abstractC0508b.f1632c;
        this.f1633d = abstractC0508b.f1633d;
        this.f1634e = abstractC0508b.f1634e;
        this.f1635f = abstractC0508b.f1635f;
        this.f1636g = abstractC0508b.f1636g;
        this.f1637h = abstractC0508b.f1637h;
        this.f1638i = abstractC0508b.f1638i;
        this.f1639j = abstractC0508b.f1639j;
        this.f1640k = abstractC0508b.f1640k;
        this.f1641l = abstractC0508b.f1641l;
        long[] jArr = abstractC0508b.f1642m;
        System.arraycopy(jArr, 0, this.f1642m, 0, jArr.length);
        this.f1643n = abstractC0508b.f1643n;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: b */
    public void mo22744b() {
        this.f1632c = 0L;
        this.f1633d = 0L;
        int i = 0;
        this.f1631b = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f1630a;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = 0;
            i2++;
        }
        this.f1643n = 0;
        while (true) {
            long[] jArr = this.f1642m;
            if (i == jArr.length) {
                return;
            }
            jArr[i] = 0;
            i++;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0631q
    /* renamed from: d */
    public int mo22696d() {
        return 128;
    }

    /* renamed from: f */
    public void m22953f() {
        m22951h();
        long j = this.f1632c << 3;
        long j2 = this.f1633d;
        mo22747a(Byte.MIN_VALUE);
        while (this.f1631b != 0) {
            mo22747a((byte) 0);
        }
        m22961a(j, j2);
        m22952g();
    }

    /* renamed from: g */
    public void m22952g() {
        m22951h();
        for (int i = 16; i <= 79; i++) {
            long[] jArr = this.f1642m;
            long m22958b = m22958b(jArr[i - 2]);
            long[] jArr2 = this.f1642m;
            jArr[i] = m22958b + jArr2[i - 7] + m22962a(jArr2[i - 15]) + this.f1642m[i - 16];
        }
        long j = this.f1634e;
        long j2 = this.f1635f;
        long j3 = this.f1636g;
        long j4 = this.f1637h;
        long j5 = this.f1638i;
        long j6 = this.f1639j;
        long j7 = this.f1640k;
        long j8 = this.f1641l;
        long j9 = j7;
        long j10 = j6;
        int i2 = 0;
        int i3 = 0;
        long j11 = j2;
        long j12 = j;
        long j13 = j4;
        long j14 = j3;
        long j15 = j5;
        while (i2 < 10) {
            long j16 = j15;
            int i4 = i3 + 1;
            long m22954d = j8 + m22954d(j15) + m22960a(j15, j10, j9) + f1629p[i3] + this.f1642m[i3];
            long j17 = j13 + m22954d;
            long j18 = j12;
            long m22955c = m22954d + m22955c(j12) + m22957b(j12, j11, j14);
            int i5 = i4 + 1;
            long m22954d2 = j9 + m22954d(j17) + m22960a(j17, j16, j10) + f1629p[i4] + this.f1642m[i4];
            long j19 = j14 + m22954d2;
            int i6 = i2;
            long m22955c2 = m22954d2 + m22955c(m22955c) + m22957b(m22955c, j18, j11);
            int i7 = i5 + 1;
            long m22954d3 = j10 + m22954d(j19) + m22960a(j19, j17, j16) + f1629p[i5] + this.f1642m[i5];
            long j20 = j11 + m22954d3;
            long m22955c3 = m22954d3 + m22955c(m22955c2) + m22957b(m22955c2, m22955c, j18);
            int i8 = i7 + 1;
            long m22954d4 = j16 + m22954d(j20) + m22960a(j20, j19, j17) + f1629p[i7] + this.f1642m[i7];
            long j21 = j18 + m22954d4;
            long m22955c4 = m22954d4 + m22955c(m22955c3) + m22957b(m22955c3, m22955c2, m22955c);
            int i9 = i8 + 1;
            long m22954d5 = j17 + m22954d(j21) + m22960a(j21, j20, j19) + f1629p[i8] + this.f1642m[i8];
            long j22 = m22955c + m22954d5;
            long m22955c5 = m22954d5 + m22955c(m22955c4) + m22957b(m22955c4, m22955c3, m22955c2);
            int i10 = i9 + 1;
            long m22954d6 = j19 + m22954d(j22) + m22960a(j22, j21, j20) + f1629p[i9] + this.f1642m[i9];
            long j23 = m22955c2 + m22954d6;
            long m22955c6 = m22954d6 + m22955c(m22955c5) + m22957b(m22955c5, m22955c4, m22955c3);
            int i11 = i10 + 1;
            long m22954d7 = j20 + m22954d(j23) + m22960a(j23, j22, j21) + f1629p[i10] + this.f1642m[i10];
            long j24 = m22955c3 + m22954d7;
            long m22955c7 = m22954d7 + m22955c(m22955c6) + m22957b(m22955c6, m22955c5, m22955c4);
            j10 = j24;
            long m22954d8 = j21 + m22954d(j24) + m22960a(j24, j23, j22) + f1629p[i11] + this.f1642m[i11];
            long m22955c8 = m22954d8 + m22955c(m22955c7) + m22957b(m22955c7, m22955c6, m22955c5);
            j15 = m22955c4 + m22954d8;
            j13 = m22955c5;
            j14 = m22955c6;
            j8 = j22;
            j9 = j23;
            j12 = m22955c8;
            j11 = m22955c7;
            i3 = i11 + 1;
            i2 = i6 + 1;
        }
        this.f1634e += j12;
        this.f1635f += j11;
        this.f1636g += j14;
        this.f1637h += j13;
        this.f1638i += j15;
        this.f1639j += j10;
        this.f1640k += j9;
        this.f1641l += j8;
        this.f1643n = 0;
        for (int i12 = 0; i12 < 16; i12++) {
            this.f1642m[i12] = 0;
        }
    }

    public AbstractC0508b(AbstractC0508b abstractC0508b) {
        this.f1642m = new long[80];
        this.f1630a = new byte[abstractC0508b.f1630a.length];
        m22959a(abstractC0508b);
    }

    /* renamed from: b */
    public void m22956b(byte[] bArr, int i) {
        this.f1642m[this.f1643n] = AbstractC0632a.m22680b(bArr, i);
        int i2 = this.f1643n + 1;
        this.f1643n = i2;
        if (i2 == 16) {
            m22952g();
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public void mo22747a(byte b) {
        byte[] bArr = this.f1630a;
        int i = this.f1631b;
        int i2 = i + 1;
        this.f1631b = i2;
        bArr[i] = b;
        if (i2 == bArr.length) {
            m22956b(bArr, 0);
            this.f1631b = 0;
        }
        this.f1632c++;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public void mo22745a(byte[] bArr, int i, int i2) {
        while (this.f1631b != 0 && i2 > 0) {
            mo22747a(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.f1630a.length) {
            m22956b(bArr, i);
            byte[] bArr2 = this.f1630a;
            i += bArr2.length;
            i2 -= bArr2.length;
            this.f1632c += bArr2.length;
        }
        while (i2 > 0) {
            mo22747a(bArr[i]);
            i++;
            i2--;
        }
    }

    /* renamed from: a */
    public void m22961a(long j, long j2) {
        if (this.f1643n > 14) {
            m22952g();
        }
        long[] jArr = this.f1642m;
        jArr[14] = j2;
        jArr[15] = j;
    }
}
