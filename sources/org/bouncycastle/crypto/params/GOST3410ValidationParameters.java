package org.bouncycastle.crypto.params;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GOST3410ValidationParameters {

    /* renamed from: c */
    private int f26816c;

    /* renamed from: cL */
    private long f26817cL;

    /* renamed from: x0 */
    private int f26818x0;
    private long x0L;

    public GOST3410ValidationParameters(int i, int i2) {
        this.f26818x0 = i;
        this.f26816c = i2;
    }

    public GOST3410ValidationParameters(long j, long j2) {
        this.x0L = j;
        this.f26817cL = j2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410ValidationParameters) {
            GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
            return gOST3410ValidationParameters.f26816c == this.f26816c && gOST3410ValidationParameters.f26818x0 == this.f26818x0 && gOST3410ValidationParameters.f26817cL == this.f26817cL && gOST3410ValidationParameters.x0L == this.x0L;
        }
        return false;
    }

    public int getC() {
        return this.f26816c;
    }

    public long getCL() {
        return this.f26817cL;
    }

    public int getX0() {
        return this.f26818x0;
    }

    public long getX0L() {
        return this.x0L;
    }

    public int hashCode() {
        int i = this.f26818x0 ^ this.f26816c;
        long j = this.x0L;
        int i2 = (i ^ ((int) j)) ^ ((int) (j >> 32));
        long j2 = this.f26817cL;
        return (i2 ^ ((int) j2)) ^ ((int) (j2 >> 32));
    }
}
