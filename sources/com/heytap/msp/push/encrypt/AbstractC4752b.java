package com.heytap.msp.push.encrypt;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.StringUtils;

/* renamed from: com.heytap.msp.push.encrypt.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC4752b implements BinaryDecoder, BinaryEncoder {

    /* renamed from: a */
    private static final int f10738a = 2;

    /* renamed from: b */
    public static final int f10739b = 76;

    /* renamed from: c */
    public static final int f10740c = 64;

    /* renamed from: d */
    protected static final int f10741d = 255;

    /* renamed from: e */
    protected static final byte f10742e = 61;

    /* renamed from: m */
    private static final int f10743m = 8192;

    /* renamed from: f */
    protected final byte f10744f = f10742e;

    /* renamed from: g */
    protected final int f10745g;

    /* renamed from: h */
    protected byte[] f10746h;

    /* renamed from: i */
    protected int f10747i;

    /* renamed from: j */
    protected boolean f10748j;

    /* renamed from: k */
    protected int f10749k;

    /* renamed from: l */
    protected int f10750l;

    /* renamed from: n */
    private final int f10751n;

    /* renamed from: o */
    private final int f10752o;

    /* renamed from: p */
    private final int f10753p;

    /* renamed from: q */
    private int f10754q;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC4752b(int i, int i2, int i3, int i4) {
        this.f10751n = i;
        this.f10752o = i2;
        this.f10745g = (i3 <= 0 || i4 <= 0) ? 0 : (i3 / i2) * i2;
        this.f10753p = i4;
    }

    /* renamed from: a */
    private void m15446a() {
        byte[] bArr = this.f10746h;
        if (bArr == null) {
            this.f10746h = new byte[m15435d()];
            this.f10747i = 0;
            this.f10754q = 0;
            return;
        }
        byte[] bArr2 = new byte[bArr.length * 2];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        this.f10746h = bArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public static boolean m15438c(byte b) {
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
    private void m15433e() {
        this.f10746h = null;
        this.f10747i = 0;
        this.f10754q = 0;
        this.f10749k = 0;
        this.f10750l = 0;
        this.f10748j = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m15445a(int i) {
        byte[] bArr = this.f10746h;
        if (bArr == null || bArr.length < this.f10747i + i) {
            m15446a();
        }
    }

    /* renamed from: a */
    abstract void mo15444a(byte[] bArr, int i, int i2);

    /* renamed from: b */
    abstract void mo15441b(byte[] bArr, int i, int i2);

    /* renamed from: b */
    boolean m15443b() {
        return this.f10746h != null;
    }

    /* renamed from: b */
    protected abstract boolean mo15442b(byte b);

    /* renamed from: b */
    public boolean m15440b(byte[] bArr, boolean z) {
        for (int i = 0; i < bArr.length; i++) {
            if (!mo15442b(bArr[i]) && (!z || (bArr[i] != 61 && !m15438c(bArr[i])))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    int m15439c() {
        if (this.f10746h != null) {
            return this.f10747i - this.f10754q;
        }
        return 0;
    }

    /* renamed from: c */
    int m15436c(byte[] bArr, int i, int i2) {
        if (this.f10746h == null) {
            return this.f10748j ? -1 : 0;
        }
        int min = Math.min(m15439c(), i2);
        System.arraycopy(this.f10746h, this.f10754q, bArr, i, min);
        this.f10754q += min;
        if (this.f10754q >= this.f10747i) {
            this.f10746h = null;
        }
        return min;
    }

    /* renamed from: c */
    public byte[] m15437c(String str) {
        return decode(StringUtils.getBytesUtf8(str));
    }

    /* renamed from: d */
    protected int m15435d() {
        return 8192;
    }

    /* renamed from: d */
    public boolean m15434d(String str) {
        return m15440b(StringUtils.getBytesUtf8(str), true);
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return m15437c((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        m15433e();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        mo15441b(bArr, 0, bArr.length);
        mo15441b(bArr, 0, -1);
        byte[] bArr2 = new byte[this.f10747i];
        m15436c(bArr2, 0, bArr2.length);
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
        m15433e();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        mo15444a(bArr, 0, bArr.length);
        mo15444a(bArr, 0, -1);
        byte[] bArr2 = new byte[this.f10747i - this.f10754q];
        m15436c(bArr2, 0, bArr2.length);
        return bArr2;
    }

    /* renamed from: j */
    public String m15432j(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    /* renamed from: k */
    public String m15431k(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: l */
    public boolean m15430l(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (61 == b || mo15442b(b)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: m */
    public long m15429m(byte[] bArr) {
        int length = bArr.length;
        int i = this.f10751n;
        long j = (((length + i) - 1) / i) * this.f10752o;
        int i2 = this.f10745g;
        return i2 > 0 ? j + ((((i2 + j) - 1) / i2) * this.f10753p) : j;
    }
}
