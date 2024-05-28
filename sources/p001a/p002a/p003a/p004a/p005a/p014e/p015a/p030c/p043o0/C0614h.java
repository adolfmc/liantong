package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0;

/* renamed from: a.a.a.a.a.e.a.c.o0.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0614h {

    /* renamed from: a.a.a.a.a.e.a.c.o0.h$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class RunnableC0616b implements Runnable {

        /* renamed from: C */
        public volatile int f1911C;

        /* renamed from: D */
        public volatile boolean f1912D;

        public RunnableC0616b() {
            this.f1911C = 0;
            this.f1912D = false;
        }

        /* renamed from: a */
        public byte[] m22728a(int i, boolean z) {
            Thread thread = new Thread(this);
            byte[] bArr = new byte[i];
            this.f1911C = 0;
            this.f1912D = false;
            thread.start();
            if (!z) {
                i *= 8;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                while (this.f1911C == i2) {
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException unused) {
                    }
                }
                i2 = this.f1911C;
                if (z) {
                    bArr[i3] = (byte) (i2 & 255);
                } else {
                    int i4 = i3 / 8;
                    bArr[i4] = (byte) ((bArr[i4] << 1) | (i2 & 1));
                }
            }
            this.f1912D = true;
            return bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!this.f1912D) {
                this.f1911C++;
            }
        }
    }

    /* renamed from: a */
    public byte[] m22729a(int i, boolean z) {
        return new RunnableC0616b().m22728a(i, z);
    }
}
