package org.bouncycastle.pqc.crypto.crystals.dilithium;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DilithiumPrivateKeyParameters extends DilithiumKeyParameters {

    /* renamed from: k */
    final byte[] f27122k;
    final byte[] rho;

    /* renamed from: s1 */
    final byte[] f27123s1;

    /* renamed from: s2 */
    final byte[] f27124s2;

    /* renamed from: t0 */
    final byte[] f27125t0;

    /* renamed from: t1 */
    private final byte[] f27126t1;

    /* renamed from: tr */
    final byte[] f27127tr;

    public DilithiumPrivateKeyParameters(DilithiumParameters dilithiumParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        super(true, dilithiumParameters);
        this.rho = Arrays.clone(bArr);
        this.f27122k = Arrays.clone(bArr2);
        this.f27127tr = Arrays.clone(bArr3);
        this.f27123s1 = Arrays.clone(bArr4);
        this.f27124s2 = Arrays.clone(bArr5);
        this.f27125t0 = Arrays.clone(bArr6);
        this.f27126t1 = Arrays.clone(bArr7);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(new byte[][]{this.rho, this.f27122k, this.f27127tr, this.f27123s1, this.f27124s2, this.f27125t0});
    }

    public byte[] getK() {
        return Arrays.clone(this.f27122k);
    }

    public byte[] getPrivateKey() {
        return getEncoded();
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getS1() {
        return Arrays.clone(this.f27123s1);
    }

    public byte[] getS2() {
        return Arrays.clone(this.f27124s2);
    }

    public byte[] getT0() {
        return Arrays.clone(this.f27125t0);
    }

    public byte[] getT1() {
        return this.f27126t1;
    }

    public byte[] getTr() {
        return Arrays.clone(this.f27127tr);
    }
}
