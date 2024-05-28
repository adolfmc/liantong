package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class KDFFeedbackParameters implements DerivationParameters {
    private static final int UNUSED_R = -1;
    private final byte[] fixedInputData;

    /* renamed from: iv */
    private final byte[] f26823iv;

    /* renamed from: ki */
    private final byte[] f26824ki;

    /* renamed from: r */
    private final int f26825r;
    private final boolean useCounter;

    private KDFFeedbackParameters(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.f26824ki = Arrays.clone(bArr);
        this.fixedInputData = bArr3 == null ? new byte[0] : Arrays.clone(bArr3);
        this.f26825r = i;
        this.f26823iv = bArr2 == null ? new byte[0] : Arrays.clone(bArr2);
        this.useCounter = z;
    }

    public static KDFFeedbackParameters createWithCounter(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        if (i == 8 || i == 16 || i == 24 || i == 32) {
            return new KDFFeedbackParameters(bArr, bArr2, bArr3, i, true);
        }
        throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
    }

    public static KDFFeedbackParameters createWithoutCounter(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return new KDFFeedbackParameters(bArr, bArr2, bArr3, -1, false);
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputData);
    }

    public byte[] getIV() {
        return this.f26823iv;
    }

    public byte[] getKI() {
        return this.f26824ki;
    }

    public int getR() {
        return this.f26825r;
    }

    public boolean useCounter() {
        return this.useCounter;
    }
}
