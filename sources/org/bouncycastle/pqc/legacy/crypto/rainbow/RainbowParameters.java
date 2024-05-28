package org.bouncycastle.pqc.legacy.crypto.rainbow;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RainbowParameters implements CipherParameters {
    private final int[] DEFAULT_VI;

    /* renamed from: vi */
    private int[] f27350vi;

    public RainbowParameters() {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.f27350vi = this.DEFAULT_VI;
    }

    public RainbowParameters(int[] iArr) {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.f27350vi = iArr;
        checkParams();
    }

    private void checkParams() {
        int[] iArr;
        int i;
        int[] iArr2 = this.f27350vi;
        if (iArr2 == null) {
            throw new IllegalArgumentException("no layers defined.");
        }
        if (iArr2.length <= 1) {
            throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
        int i2 = 0;
        do {
            iArr = this.f27350vi;
            if (i2 >= iArr.length - 1) {
                return;
            }
            i = iArr[i2];
            i2++;
        } while (i < iArr[i2]);
        throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
    }

    public int getDocLength() {
        int[] iArr = this.f27350vi;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int getNumOfLayers() {
        return this.f27350vi.length - 1;
    }

    public int[] getVi() {
        return this.f27350vi;
    }
}
