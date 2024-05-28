package org.bouncycastle.pqc.legacy.crypto.rainbow;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    private short[][] A1inv;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f27351b1;

    /* renamed from: b2 */
    private short[] f27352b2;
    private Layer[] layers;

    /* renamed from: vi */
    private int[] f27353vi;

    public RainbowPrivateKeyParameters(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        super(true, iArr[iArr.length - 1] - iArr[0]);
        this.A1inv = sArr;
        this.f27351b1 = sArr2;
        this.A2inv = sArr3;
        this.f27352b2 = sArr4;
        this.f27353vi = iArr;
        this.layers = layerArr;
    }

    public short[] getB1() {
        return this.f27351b1;
    }

    public short[] getB2() {
        return this.f27352b2;
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.f27353vi;
    }
}
