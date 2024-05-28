package org.bouncycastle.pqc.crypto.falcon;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class SHAKE256 {

    /* renamed from: RC */
    private long[] f27152RC = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};

    /* renamed from: A */
    long[] f27151A = new long[25];
    byte[] dbuf = new byte[200];
    long dptr = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i_shake256_flip() {
        int i = (int) this.dptr;
        long[] jArr = this.f27151A;
        int i2 = i >> 3;
        jArr[i2] = jArr[i2] ^ (31 << ((i & 7) << 3));
        jArr[16] = jArr[16] ^ Long.MIN_VALUE;
        this.dptr = 136L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void inner_shake256_extract(byte[] bArr, int i, int i2) {
        int i3 = (int) this.dptr;
        while (i2 > 0) {
            if (i3 == 136) {
                process_block(this.f27151A);
                i3 = 0;
            }
            int i4 = 136 - i3;
            if (i4 > i2) {
                i4 = i2;
            }
            i2 -= i4;
            while (true) {
                int i5 = i4 - 1;
                if (i4 > 0) {
                    bArr[i] = (byte) (this.f27151A[i3 >> 3] >>> ((i3 & 7) << 3));
                    i3++;
                    i++;
                    i4 = i5;
                }
            }
        }
        this.dptr = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void inner_shake256_init() {
        this.dptr = 0L;
        int i = 0;
        while (true) {
            long[] jArr = this.f27151A;
            if (i >= jArr.length) {
                return;
            }
            jArr[i] = 0;
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void inner_shake256_inject(byte[] bArr, int i, int i2) {
        int i3 = i;
        long j = this.dptr;
        int i4 = i2;
        while (i4 > 0) {
            long j2 = 136 - j;
            long j3 = i4;
            if (j2 > j3) {
                j2 = j3;
            }
            long j4 = 0;
            while (j4 < j2) {
                long j5 = j4 + j;
                long[] jArr = this.f27151A;
                int i5 = (int) (j5 >> 3);
                jArr[i5] = jArr[i5] ^ ((bArr[((int) j4) + i3] & 255) << ((int) ((j5 & 7) << 3)));
                j4++;
                j3 = j3;
            }
            j += j2;
            i3 = (int) (i3 + j2);
            i4 = (int) (j3 - j2);
            if (j == 136) {
                process_block(this.f27151A);
                j = 0;
            }
        }
        this.dptr = j;
    }

    /*  JADX ERROR: Type inference failed with exception
        jadx.core.utils.exceptions.JadxOverflowException: Type update terminated with stack overflow, arg: (r28v36 ?? I:??[long, double])
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:114)
        */
    void process_block(long[] r37) {
        /*
            Method dump skipped, instructions count: 2593
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.falcon.SHAKE256.process_block(long[]):void");
    }
}
