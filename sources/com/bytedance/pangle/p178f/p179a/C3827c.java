package com.bytedance.pangle.p178f.p179a;

/* renamed from: com.bytedance.pangle.f.a.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class C3827c {

    /* renamed from: a */
    int[] f9163a = new int[32];

    /* renamed from: b */
    int f9164b;

    /* renamed from: c */
    int f9165c;

    /* renamed from: a */
    public final void m16860a() {
        m16859b();
        int i = this.f9164b;
        int[] iArr = this.f9163a;
        iArr[i] = 0;
        iArr[i + 1] = 0;
        this.f9164b = i + 2;
        this.f9165c++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m16859b() {
        int[] iArr = this.f9163a;
        int length = iArr.length;
        int i = this.f9164b;
        int i2 = length - i;
        if (i2 <= 2) {
            int[] iArr2 = new int[(iArr.length + i2) * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.f9163a = iArr2;
        }
    }
}
