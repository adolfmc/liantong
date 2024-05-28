package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SipHash128 extends SipHash {
    public SipHash128() {
    }

    public SipHash128(int i, int i2) {
        super(i, i2);
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        this.f26711m >>>= (7 - this.wordPos) << 3;
        this.f26711m >>>= 8;
        this.f26711m |= (((this.wordCount << 3) + this.wordPos) & 255) << 56;
        processMessageWord();
        this.f26714v2 ^= 238;
        applySipRounds(this.f26708d);
        long j = ((this.f26712v0 ^ this.f26713v1) ^ this.f26714v2) ^ this.f26715v3;
        this.f26713v1 ^= 221;
        applySipRounds(this.f26708d);
        reset();
        Pack.longToLittleEndian(j, bArr, i);
        Pack.longToLittleEndian(((this.f26712v0 ^ this.f26713v1) ^ this.f26714v2) ^ this.f26715v3, bArr, i + 8);
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash
    public long doFinal() throws DataLengthException, IllegalStateException {
        throw new UnsupportedOperationException("doFinal() is not supported");
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash128-" + this.f26707c + "-" + this.f26708d;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public void reset() {
        super.reset();
        this.f26713v1 ^= 238;
    }
}
