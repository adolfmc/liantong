package kotlin.random;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: XorWowRandom.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B7\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m1890d2 = {"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "seed1", "", "seed2", "(II)V", "x", "y", "z", "w", "v", "addend", "(IIIIII)V", "nextBits", "bitCount", "nextInt", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class XorWowRandom extends Random {
    private int addend;

    /* renamed from: v */
    private int f24898v;

    /* renamed from: w */
    private int f24899w;

    /* renamed from: x */
    private int f24900x;

    /* renamed from: y */
    private int f24901y;

    /* renamed from: z */
    private int f24902z;

    public XorWowRandom(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f24900x = i;
        this.f24901y = i2;
        this.f24902z = i3;
        this.f24899w = i4;
        this.f24898v = i5;
        this.addend = i6;
        if (!(((((this.f24900x | this.f24901y) | this.f24902z) | this.f24899w) | this.f24898v) != 0)) {
            throw new IllegalArgumentException("Initial state must have at least one non-zero element.".toString());
        }
        for (int i7 = 0; i7 < 64; i7++) {
            nextInt();
        }
    }

    public XorWowRandom(int i, int i2) {
        this(i, i2, 0, 0, ~i, (i << 10) ^ (i2 >>> 4));
    }

    @Override // kotlin.random.Random
    public int nextInt() {
        int i = this.f24900x;
        int i2 = i ^ (i >>> 2);
        this.f24900x = this.f24901y;
        this.f24901y = this.f24902z;
        this.f24902z = this.f24899w;
        int i3 = this.f24898v;
        this.f24899w = i3;
        int i4 = ((i2 ^ (i2 << 1)) ^ i3) ^ (i3 << 4);
        this.f24898v = i4;
        this.addend += 362437;
        return i4 + this.addend;
    }

    @Override // kotlin.random.Random
    public int nextBits(int i) {
        return RandomKt.takeUpperBits(nextInt(), i);
    }
}
