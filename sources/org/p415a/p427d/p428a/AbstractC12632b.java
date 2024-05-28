package org.p415a.p427d.p428a;

import org.p415a.p427d.InterfaceC12726k;
import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12632b implements InterfaceC12726k {

    /* renamed from: i */
    static final long[] f25638i = {4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};

    /* renamed from: a */
    protected long f25639a;

    /* renamed from: b */
    protected long f25640b;

    /* renamed from: c */
    protected long f25641c;

    /* renamed from: d */
    protected long f25642d;

    /* renamed from: e */
    protected long f25643e;

    /* renamed from: f */
    protected long f25644f;

    /* renamed from: g */
    protected long f25645g;

    /* renamed from: h */
    protected long f25646h;

    /* renamed from: l */
    private long f25649l;

    /* renamed from: m */
    private long f25650m;

    /* renamed from: o */
    private int f25652o;

    /* renamed from: j */
    private byte[] f25647j = new byte[8];

    /* renamed from: n */
    private long[] f25651n = new long[80];

    /* renamed from: k */
    private int f25648k = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC12632b() {
        mo1480c();
    }

    /* renamed from: a */
    private long m1526a(long j) {
        return ((j >>> 39) | (j << 25)) ^ (((j << 36) | (j >>> 28)) ^ ((j << 30) | (j >>> 34)));
    }

    /* renamed from: a */
    private long m1524a(long j, long j2, long j3) {
        return ((~j) & j3) ^ (j2 & j);
    }

    /* renamed from: b */
    private long m1522b(long j) {
        return ((j >>> 41) | (j << 23)) ^ (((j << 50) | (j >>> 14)) ^ ((j << 46) | (j >>> 18)));
    }

    /* renamed from: b */
    private long m1521b(long j, long j2, long j3) {
        return ((j & j3) ^ (j & j2)) ^ (j2 & j3);
    }

    /* renamed from: c */
    private long m1519c(long j) {
        return (j >>> 7) ^ (((j << 63) | (j >>> 1)) ^ ((j << 56) | (j >>> 8)));
    }

    /* renamed from: d */
    private long m1517d(long j) {
        return (j >>> 6) ^ (((j << 45) | (j >>> 19)) ^ ((j << 3) | (j >>> 61)));
    }

    /* renamed from: e */
    private void m1516e() {
        long j = this.f25649l;
        if (j > 2305843009213693951L) {
            this.f25650m += j >>> 61;
            this.f25649l = j & 2305843009213693951L;
        }
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public void mo1313a(byte b) {
        byte[] bArr = this.f25647j;
        int i = this.f25648k;
        this.f25648k = i + 1;
        bArr[i] = b;
        if (this.f25648k == bArr.length) {
            m1520b(bArr, 0);
            this.f25648k = 0;
        }
        this.f25649l++;
    }

    /* renamed from: a */
    protected void m1525a(long j, long j2) {
        if (this.f25652o > 14) {
            m1518d();
        }
        long[] jArr = this.f25651n;
        jArr[14] = j2;
        jArr[15] = j;
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public void mo1311a(byte[] bArr, int i, int i2) {
        while (this.f25648k != 0 && i2 > 0) {
            mo1313a(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.f25647j.length) {
            m1520b(bArr, i);
            byte[] bArr2 = this.f25647j;
            i += bArr2.length;
            i2 -= bArr2.length;
            this.f25649l += bArr2.length;
        }
        while (i2 > 0) {
            mo1313a(bArr[i]);
            i++;
            i2--;
        }
    }

    /* renamed from: b */
    public void m1523b() {
        m1516e();
        long j = this.f25649l << 3;
        long j2 = this.f25650m;
        byte b = Byte.MIN_VALUE;
        while (true) {
            mo1313a(b);
            if (this.f25648k == 0) {
                m1525a(j, j2);
                m1518d();
                return;
            }
            b = 0;
        }
    }

    /* renamed from: b */
    protected void m1520b(byte[] bArr, int i) {
        this.f25651n[this.f25652o] = AbstractC12971e.m397b(bArr, i);
        int i2 = this.f25652o + 1;
        this.f25652o = i2;
        if (i2 == 16) {
            m1518d();
        }
    }

    /* renamed from: c */
    public void mo1480c() {
        this.f25649l = 0L;
        this.f25650m = 0L;
        int i = 0;
        this.f25648k = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f25647j;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = 0;
            i2++;
        }
        this.f25652o = 0;
        while (true) {
            long[] jArr = this.f25651n;
            if (i == jArr.length) {
                return;
            }
            jArr[i] = 0;
            i++;
        }
    }

    /* renamed from: d */
    protected void m1518d() {
        m1516e();
        for (int i = 16; i <= 79; i++) {
            long[] jArr = this.f25651n;
            long m1517d = m1517d(jArr[i - 2]);
            long[] jArr2 = this.f25651n;
            jArr[i] = m1517d + jArr2[i - 7] + m1519c(jArr2[i - 15]) + this.f25651n[i - 16];
        }
        long j = this.f25639a;
        long j2 = this.f25640b;
        long j3 = this.f25641c;
        long j4 = this.f25642d;
        long j5 = this.f25643e;
        long j6 = this.f25644f;
        long j7 = this.f25645g;
        long j8 = this.f25646h;
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
            long m1522b = j8 + m1522b(j15) + m1524a(j15, j10, j9) + f25638i[i3] + this.f25651n[i3];
            long j17 = j13 + m1522b;
            long j18 = j12;
            long m1526a = m1522b + m1526a(j12) + m1521b(j12, j11, j14);
            int i5 = i4 + 1;
            long m1522b2 = j9 + m1522b(j17) + m1524a(j17, j16, j10) + f25638i[i4] + this.f25651n[i4];
            long j19 = j14 + m1522b2;
            int i6 = i2;
            long m1526a2 = m1522b2 + m1526a(m1526a) + m1521b(m1526a, j18, j11);
            int i7 = i5 + 1;
            long m1522b3 = j10 + m1522b(j19) + m1524a(j19, j17, j16) + f25638i[i5] + this.f25651n[i5];
            long j20 = j11 + m1522b3;
            long m1526a3 = m1522b3 + m1526a(m1526a2) + m1521b(m1526a2, m1526a, j18);
            int i8 = i7 + 1;
            long m1522b4 = j16 + m1522b(j20) + m1524a(j20, j19, j17) + f25638i[i7] + this.f25651n[i7];
            long j21 = j18 + m1522b4;
            long m1526a4 = m1522b4 + m1526a(m1526a3) + m1521b(m1526a3, m1526a2, m1526a);
            int i9 = i8 + 1;
            long m1522b5 = j17 + m1522b(j21) + m1524a(j21, j20, j19) + f25638i[i8] + this.f25651n[i8];
            long j22 = m1526a + m1522b5;
            long m1526a5 = m1522b5 + m1526a(m1526a4) + m1521b(m1526a4, m1526a3, m1526a2);
            int i10 = i9 + 1;
            long m1522b6 = j19 + m1522b(j22) + m1524a(j22, j21, j20) + f25638i[i9] + this.f25651n[i9];
            long j23 = m1526a2 + m1522b6;
            long m1526a6 = m1522b6 + m1526a(m1526a5) + m1521b(m1526a5, m1526a4, m1526a3);
            int i11 = i10 + 1;
            long m1522b7 = j20 + m1522b(j23) + m1524a(j23, j22, j21) + f25638i[i10] + this.f25651n[i10];
            long j24 = m1526a3 + m1522b7;
            long m1526a7 = m1522b7 + m1526a(m1526a6) + m1521b(m1526a6, m1526a5, m1526a4);
            j10 = j24;
            long m1522b8 = j21 + m1522b(j24) + m1524a(j24, j23, j22) + f25638i[i11] + this.f25651n[i11];
            long m1526a8 = m1522b8 + m1526a(m1526a7) + m1521b(m1526a7, m1526a6, m1526a5);
            j15 = m1526a4 + m1522b8;
            j13 = m1526a5;
            j14 = m1526a6;
            j8 = j22;
            j9 = j23;
            j12 = m1526a8;
            j11 = m1526a7;
            i3 = i11 + 1;
            i2 = i6 + 1;
        }
        this.f25639a += j12;
        this.f25640b += j11;
        this.f25641c += j14;
        this.f25642d += j13;
        this.f25643e += j15;
        this.f25644f += j10;
        this.f25645g += j9;
        this.f25646h += j8;
        this.f25652o = 0;
        for (int i12 = 0; i12 < 16; i12++) {
            this.f25651n[i12] = 0;
        }
    }
}
