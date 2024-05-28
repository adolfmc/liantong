package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DESedeEngine extends DESBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private boolean forEncryption;
    private int[] workingKey1 = null;
    private int[] workingKey2 = null;
    private int[] workingKey3 = null;

    public DESedeEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity()));
    }

    private int bitsOfSecurity() {
        int[] iArr = this.workingKey1;
        return (iArr == null || iArr != this.workingKey3) ? 112 : 80;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DESede";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        int[] iArr;
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to DESede init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 24 && key.length != 16) {
            throw new IllegalArgumentException("key size must be 16 or 24 bytes.");
        }
        this.forEncryption = z;
        byte[] bArr = new byte[8];
        System.arraycopy(key, 0, bArr, 0, bArr.length);
        this.workingKey1 = generateWorkingKey(z, bArr);
        byte[] bArr2 = new byte[8];
        System.arraycopy(key, 8, bArr2, 0, bArr2.length);
        this.workingKey2 = generateWorkingKey(!z, bArr2);
        if (key.length == 24) {
            byte[] bArr3 = new byte[8];
            System.arraycopy(key, 16, bArr3, 0, bArr3.length);
            iArr = generateWorkingKey(z, bArr3);
        } else {
            iArr = this.workingKey1;
        }
        this.workingKey3 = iArr;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(this.forEncryption)));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        DESedeEngine dESedeEngine;
        int i3;
        byte[] bArr3;
        int[] iArr;
        int[] iArr2 = this.workingKey1;
        if (iArr2 != null) {
            if (i + 8 <= bArr.length) {
                if (i2 + 8 <= bArr2.length) {
                    byte[] bArr4 = new byte[8];
                    if (this.forEncryption) {
                        dESedeEngine = this;
                        dESedeEngine.desFunc(iArr2, bArr, i, bArr4, 0);
                        i3 = 0;
                        bArr3 = bArr4;
                        dESedeEngine.desFunc(this.workingKey2, bArr3, 0, bArr4, 0);
                        iArr = this.workingKey3;
                    } else {
                        dESedeEngine = this;
                        dESedeEngine.desFunc(this.workingKey3, bArr, i, bArr4, 0);
                        i3 = 0;
                        bArr3 = bArr4;
                        dESedeEngine.desFunc(this.workingKey2, bArr3, 0, bArr4, 0);
                        iArr = this.workingKey1;
                    }
                    dESedeEngine.desFunc(iArr, bArr3, i3, bArr2, i2);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("DESede engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
