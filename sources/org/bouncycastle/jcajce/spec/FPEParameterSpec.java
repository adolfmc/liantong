package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.util.RadixConverter;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FPEParameterSpec implements AlgorithmParameterSpec {
    private final RadixConverter radixConverter;
    private final byte[] tweak;
    private final boolean useInverse;

    public FPEParameterSpec(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public FPEParameterSpec(int i, byte[] bArr, boolean z) {
        this(new RadixConverter(i), bArr, z);
    }

    public FPEParameterSpec(RadixConverter radixConverter, byte[] bArr, boolean z) {
        this.radixConverter = radixConverter;
        this.tweak = Arrays.clone(bArr);
        this.useInverse = z;
    }

    public int getRadix() {
        return this.radixConverter.getRadix();
    }

    public int getRadixConverter() {
        return this.radixConverter.getRadix();
    }

    public byte[] getTweak() {
        return Arrays.clone(this.tweak);
    }

    public boolean isUsingInverseFunction() {
        return this.useInverse;
    }
}
