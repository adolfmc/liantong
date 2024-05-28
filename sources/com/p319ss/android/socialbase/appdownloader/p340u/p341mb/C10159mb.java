package com.p319ss.android.socialbase.appdownloader.p340u.p341mb;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.ss.android.socialbase.appdownloader.u.mb.mb */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
class C10159mb implements InterfaceC10158lz {

    /* renamed from: h */
    private int[] f19636h;

    /* renamed from: hj */
    private C10162u f19637hj;

    /* renamed from: jb */
    private int f19638jb;

    /* renamed from: je */
    private int[] f19639je;

    /* renamed from: ko */
    private boolean f19640ko;

    /* renamed from: lc */
    private int f19641lc;

    /* renamed from: lz */
    private int f19642lz;

    /* renamed from: nk */
    private int f19643nk;

    /* renamed from: o */
    private int f19644o;

    /* renamed from: ox */
    private C10156hj f19645ox;

    /* renamed from: ww */
    private int f19647ww;

    /* renamed from: x */
    private int f19648x;

    /* renamed from: b */
    private boolean f19635b = false;

    /* renamed from: u */
    private C10160mb f19646u = new C10160mb();

    @Override // com.p319ss.android.socialbase.appdownloader.p340u.p341mb.InterfaceC10157ko
    /* renamed from: u */
    public int mo6523u() {
        return -1;
    }

    public C10159mb() {
        m6529ko();
    }

    /* renamed from: mb */
    public void m6526mb(InputStream inputStream) {
        m6528mb();
        if (inputStream != null) {
            this.f19645ox = new C10156hj(inputStream, false);
        }
    }

    /* renamed from: mb */
    public void m6528mb() {
        if (this.f19635b) {
            this.f19635b = false;
            this.f19645ox.m6541mb();
            this.f19645ox = null;
            this.f19637hj = null;
            this.f19636h = null;
            this.f19646u.m6518mb();
            m6529ko();
        }
    }

    /* renamed from: ox */
    public int m6525ox() throws C10163ww, IOException {
        if (this.f19645ox == null) {
            throw new C10163ww("Parser is not opened.", this, null);
        }
        try {
            m6522ww();
            return this.f19647ww;
        } catch (IOException e) {
            m6528mb();
            throw e;
        }
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p340u.p341mb.InterfaceC10157ko
    /* renamed from: b */
    public int mo6535b() {
        return this.f19642lz;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p340u.p341mb.InterfaceC10157ko
    /* renamed from: hj */
    public String mo6531hj() {
        return "XML line #" + mo6535b();
    }

    /* renamed from: h */
    public int m6533h() {
        if (this.f19647ww != 2) {
            return -1;
        }
        return this.f19639je.length / 5;
    }

    /* renamed from: mb */
    public String m6527mb(int i) {
        int i2 = this.f19639je[m6532h(i) + 1];
        return i2 == -1 ? "" : this.f19637hj.m6512mb(i2);
    }

    /* renamed from: ox */
    public int m6524ox(int i) {
        return this.f19639je[m6532h(i) + 3];
    }

    /* renamed from: b */
    public int m6534b(int i) {
        return this.f19639je[m6532h(i) + 4];
    }

    /* renamed from: hj */
    public String m6530hj(int i) {
        int m6532h = m6532h(i);
        int[] iArr = this.f19639je;
        if (iArr[m6532h + 3] == 3) {
            return this.f19637hj.m6512mb(iArr[m6532h + 2]);
        }
        int i2 = iArr[m6532h + 4];
        return "";
    }

    /* renamed from: h */
    private final int m6532h(int i) {
        if (this.f19647ww != 2) {
            throw new IndexOutOfBoundsException("Current event is not START_TAG.");
        }
        int i2 = i * 5;
        if (i2 < this.f19639je.length) {
            return i2;
        }
        throw new IndexOutOfBoundsException("Invalid attribute index (" + i + ").");
    }

    /* renamed from: ko */
    private final void m6529ko() {
        this.f19647ww = -1;
        this.f19642lz = -1;
        this.f19648x = -1;
        this.f19638jb = -1;
        this.f19639je = null;
        this.f19643nk = -1;
        this.f19644o = -1;
        this.f19641lc = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0190, code lost:
        throw new java.io.IOException("Invalid chunk type (" + r5 + ").");
     */
    /* renamed from: ww */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void m6522ww() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.p340u.p341mb.C10159mb.m6522ww():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.socialbase.appdownloader.u.mb.mb$mb */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public static final class C10160mb {

        /* renamed from: b */
        private int f19649b;

        /* renamed from: mb */
        private int[] f19650mb = new int[32];

        /* renamed from: ox */
        private int f19651ox;

        /* renamed from: mb */
        public final void m6518mb() {
            this.f19651ox = 0;
            this.f19649b = 0;
        }

        /* renamed from: ox */
        public final int m6515ox() {
            int i = this.f19651ox;
            if (i == 0) {
                return 0;
            }
            return this.f19650mb[i - 1];
        }

        /* renamed from: mb */
        public final void m6516mb(int i, int i2) {
            if (this.f19649b == 0) {
                m6520h();
            }
            m6517mb(2);
            int i3 = this.f19651ox;
            int i4 = i3 - 1;
            int[] iArr = this.f19650mb;
            int i5 = iArr[i4];
            int i6 = i5 + 1;
            iArr[(i4 - 1) - (i5 * 2)] = i6;
            iArr[i4] = i;
            iArr[i4 + 1] = i2;
            iArr[i4 + 2] = i6;
            this.f19651ox = i3 + 2;
        }

        /* renamed from: b */
        public final boolean m6521b() {
            int i;
            int[] iArr;
            int i2;
            int i3 = this.f19651ox;
            if (i3 == 0 || (i2 = (iArr = this.f19650mb)[i3 - 1]) == 0) {
                return false;
            }
            int i4 = i2 - 1;
            int i5 = i - 2;
            iArr[i5] = i4;
            iArr[i5 - ((i4 * 2) + 1)] = i4;
            this.f19651ox = i3 - 2;
            return true;
        }

        /* renamed from: hj */
        public final int m6519hj() {
            return this.f19649b;
        }

        /* renamed from: h */
        public final void m6520h() {
            m6517mb(2);
            int i = this.f19651ox;
            int[] iArr = this.f19650mb;
            iArr[i] = 0;
            iArr[i + 1] = 0;
            this.f19651ox = i + 2;
            this.f19649b++;
        }

        /* renamed from: u */
        public final void m6514u() {
            int i = this.f19651ox;
            if (i != 0) {
                int i2 = i - 1;
                int i3 = this.f19650mb[i2] * 2;
                if ((i2 - 1) - i3 != 0) {
                    this.f19651ox = i - (i3 + 2);
                    this.f19649b--;
                }
            }
        }

        /* renamed from: mb */
        private void m6517mb(int i) {
            int[] iArr = this.f19650mb;
            int length = iArr.length;
            int i2 = this.f19651ox;
            int i3 = length - i2;
            if (i3 <= i) {
                int[] iArr2 = new int[(iArr.length + i3) * 2];
                System.arraycopy(iArr, 0, iArr2, 0, i2);
                this.f19650mb = iArr2;
            }
        }
    }
}
