package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RainbowParameterSpec implements AlgorithmParameterSpec {
    private static final int[] DEFAULT_VI = {6, 12, 17, 22, 33};

    /* renamed from: vi */
    private int[] f27286vi;

    public RainbowParameterSpec() {
        this.f27286vi = DEFAULT_VI;
    }

    public RainbowParameterSpec(int[] iArr) {
        this.f27286vi = iArr;
        checkParams();
    }

    private void checkParams() {
        int[] iArr;
        int i;
        int[] iArr2 = this.f27286vi;
        if (iArr2 == null) {
            throw new IllegalArgumentException("no layers defined.");
        }
        if (iArr2.length <= 1) {
            throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
        int i2 = 0;
        do {
            iArr = this.f27286vi;
            if (i2 >= iArr.length - 1) {
                return;
            }
            i = iArr[i2];
            i2++;
        } while (i < iArr[i2]);
        throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
    }

    public int getDocumentLength() {
        int[] iArr = this.f27286vi;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int getNumOfLayers() {
        return this.f27286vi.length - 1;
    }

    public int[] getVi() {
        return Arrays.clone(this.f27286vi);
    }
}
