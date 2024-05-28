package com.heytap.mcssdk.p203a;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.StringUtils;

/* renamed from: com.heytap.mcssdk.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC4713b implements BinaryDecoder, BinaryEncoder {

    /* renamed from: a */
    private static final int f10564a = 2;

    /* renamed from: b */
    public static final int f10565b = 76;

    /* renamed from: c */
    public static final int f10566c = 64;

    /* renamed from: d */
    protected static final int f10567d = 255;

    /* renamed from: e */
    protected static final byte f10568e = 61;

    /* renamed from: m */
    private static final int f10569m = 8192;

    /* renamed from: f */
    protected final byte f10570f = f10568e;

    /* renamed from: g */
    protected final int f10571g;

    /* renamed from: h */
    protected byte[] f10572h;

    /* renamed from: i */
    protected int f10573i;

    /* renamed from: j */
    protected boolean f10574j;

    /* renamed from: k */
    protected int f10575k;

    /* renamed from: l */
    protected int f10576l;

    /* renamed from: n */
    private final int f10577n;

    /* renamed from: o */
    private final int f10578o;

    /* renamed from: p */
    private final int f10579p;

    /* renamed from: q */
    private int f10580q;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC4713b(int i, int i2, int i3, int i4) {
        this.f10577n = i;
        this.f10578o = i2;
        this.f10571g = (i3 <= 0 || i4 <= 0) ? 0 : (i3 / i2) * i2;
        this.f10579p = i4;
    }

    /* renamed from: a */
    private void m15595a() {
        byte[] bArr = this.f10572h;
        if (bArr == null) {
            this.f10572h = new byte[m15584d()];
            this.f10573i = 0;
            this.f10580q = 0;
            return;
        }
        byte[] bArr2 = new byte[bArr.length * 2];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        this.f10572h = bArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public static boolean m15587c(byte b) {
        if (b == 13 || b == 32) {
            return true;
        }
        switch (b) {
            case 9:
            case 10:
                return true;
            default:
                return false;
        }
    }

    /* renamed from: e */
    private void m15582e() {
        this.f10572h = null;
        this.f10573i = 0;
        this.f10580q = 0;
        this.f10575k = 0;
        this.f10576l = 0;
        this.f10574j = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m15594a(int i) {
        byte[] bArr = this.f10572h;
        if (bArr == null || bArr.length < this.f10573i + i) {
            m15595a();
        }
    }

    /* renamed from: a */
    abstract void mo15593a(byte[] bArr, int i, int i2);

    /* renamed from: b */
    abstract void mo15590b(byte[] bArr, int i, int i2);

    /* renamed from: b */
    boolean m15592b() {
        return this.f10572h != null;
    }

    /* renamed from: b */
    protected abstract boolean mo15591b(byte b);

    /* renamed from: b */
    public boolean m15589b(byte[] bArr, boolean z) {
        for (int i = 0; i < bArr.length; i++) {
            if (!mo15591b(bArr[i]) && (!z || (bArr[i] != 61 && !m15587c(bArr[i])))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    int m15588c() {
        if (this.f10572h != null) {
            return this.f10573i - this.f10580q;
        }
        return 0;
    }

    /* renamed from: c */
    int m15585c(byte[] bArr, int i, int i2) {
        if (this.f10572h == null) {
            return this.f10574j ? -1 : 0;
        }
        int min = Math.min(m15588c(), i2);
        System.arraycopy(this.f10572h, this.f10580q, bArr, i, min);
        this.f10580q += min;
        if (this.f10580q >= this.f10573i) {
            this.f10572h = null;
        }
        return min;
    }

    /* renamed from: c */
    public byte[] m15586c(String str) {
        return decode(StringUtils.getBytesUtf8(str));
    }

    /* renamed from: d */
    protected int m15584d() {
        return 8192;
    }

    /* renamed from: d */
    public boolean m15583d(String str) {
        return m15589b(StringUtils.getBytesUtf8(str), true);
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return m15586c((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        m15582e();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        mo15590b(bArr, 0, bArr.length);
        mo15590b(bArr, 0, -1);
        byte[] bArr2 = new byte[this.f10573i];
        m15585c(bArr2, 0, bArr2.length);
        return bArr2;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) {
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        m15582e();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        mo15593a(bArr, 0, bArr.length);
        mo15593a(bArr, 0, -1);
        byte[] bArr2 = new byte[this.f10573i - this.f10580q];
        m15585c(bArr2, 0, bArr2.length);
        return bArr2;
    }

    /* renamed from: j */
    public String m15581j(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    /* renamed from: k */
    public String m15580k(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: l */
    public boolean m15579l(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (61 == b || mo15591b(b)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: m */
    public long m15578m(byte[] bArr) {
        int length = bArr.length;
        int i = this.f10577n;
        long j = (((length + i) - 1) / i) * this.f10578o;
        int i2 = this.f10571g;
        return i2 > 0 ? j + ((((i2 + j) - 1) / i2) * this.f10579p) : j;
    }
}
