package org.bouncycastle.pqc.legacy.crypto.gmss;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.legacy.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GMSSLeaf {
    private byte[] concHashs;
    private GMSSRandom gmssRandom;

    /* renamed from: i */
    private int f27291i;

    /* renamed from: j */
    private int f27292j;
    private int keysize;
    private byte[] leaf;
    private int mdsize;
    private Digest messDigestOTS;
    byte[] privateKeyOTS;
    private byte[] seed;
    private int steps;
    private int two_power_w;

    /* renamed from: w */
    private int f27293w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GMSSLeaf(Digest digest, int i, int i2) {
        int ceil;
        int i3;
        this.f27293w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = i;
        this.keysize = ((int) Math.ceil((this.mdsize << 3) / d)) + ((int) Math.ceil(getLog((ceil << i) + 1) / d));
        this.two_power_w = 1 << i;
        int i4 = this.keysize;
        this.steps = (int) Math.ceil(((((i3 - 1) * i4) + 1) + i4) / i2);
        int i5 = this.mdsize;
        this.seed = new byte[i5];
        this.leaf = new byte[i5];
        this.privateKeyOTS = new byte[i5];
        this.concHashs = new byte[i5 * this.keysize];
    }

    public GMSSLeaf(Digest digest, int i, int i2, byte[] bArr) {
        int ceil;
        int i3;
        this.f27293w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = i;
        this.keysize = ((int) Math.ceil((this.mdsize << 3) / d)) + ((int) Math.ceil(getLog((ceil << i) + 1) / d));
        this.two_power_w = 1 << i;
        int i4 = this.keysize;
        this.steps = (int) Math.ceil(((((i3 - 1) * i4) + 1) + i4) / i2);
        int i5 = this.mdsize;
        this.seed = new byte[i5];
        this.leaf = new byte[i5];
        this.privateKeyOTS = new byte[i5];
        this.concHashs = new byte[i5 * this.keysize];
        initLeafCalc(bArr);
    }

    public GMSSLeaf(Digest digest, byte[][] bArr, int[] iArr) {
        this.f27291i = iArr[0];
        this.f27292j = iArr[1];
        this.steps = iArr[2];
        this.f27293w = iArr[3];
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        int ceil = (int) Math.ceil((this.mdsize << 3) / this.f27293w);
        this.keysize = ceil + ((int) Math.ceil(getLog((ceil << this.f27293w) + 1) / this.f27293w));
        this.two_power_w = 1 << this.f27293w;
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.concHashs = bArr[2];
        this.leaf = bArr[3];
    }

    private GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.messDigestOTS = gMSSLeaf.messDigestOTS;
        this.mdsize = gMSSLeaf.mdsize;
        this.keysize = gMSSLeaf.keysize;
        this.gmssRandom = gMSSLeaf.gmssRandom;
        this.leaf = Arrays.clone(gMSSLeaf.leaf);
        this.concHashs = Arrays.clone(gMSSLeaf.concHashs);
        this.f27291i = gMSSLeaf.f27291i;
        this.f27292j = gMSSLeaf.f27292j;
        this.two_power_w = gMSSLeaf.two_power_w;
        this.f27293w = gMSSLeaf.f27293w;
        this.steps = gMSSLeaf.steps;
        this.seed = Arrays.clone(gMSSLeaf.seed);
        this.privateKeyOTS = Arrays.clone(gMSSLeaf.privateKeyOTS);
    }

    private int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    private void updateLeafCalc() {
        byte[] bArr = new byte[this.messDigestOTS.getDigestSize()];
        for (int i = 0; i < this.steps + 10000; i++) {
            if (this.f27291i == this.keysize && this.f27292j == this.two_power_w - 1) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.concHashs;
                digest.update(bArr2, 0, bArr2.length);
                this.leaf = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.leaf, 0);
                return;
            }
            if (this.f27291i == 0 || this.f27292j == this.two_power_w - 1) {
                this.f27291i++;
                this.f27292j = 0;
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr3 = this.privateKeyOTS;
                digest2.update(bArr3, 0, bArr3.length);
                this.privateKeyOTS = bArr;
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                this.f27292j++;
                if (this.f27292j == this.two_power_w - 1) {
                    byte[] bArr4 = this.privateKeyOTS;
                    byte[] bArr5 = this.concHashs;
                    int i2 = this.mdsize;
                    System.arraycopy(bArr4, 0, bArr5, (this.f27291i - 1) * i2, i2);
                }
            }
        }
        throw new IllegalStateException("unable to updateLeaf in steps: " + this.steps + " " + this.f27291i + " " + this.f27292j);
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.leaf);
    }

    public byte[][] getStatByte() {
        return new byte[][]{this.privateKeyOTS, this.seed, this.concHashs, this.leaf};
    }

    public int[] getStatInt() {
        return new int[]{this.f27291i, this.f27292j, this.steps, this.f27293w};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initLeafCalc(byte[] bArr) {
        this.f27291i = 0;
        this.f27292j = 0;
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, this.seed.length);
        this.seed = this.gmssRandom.nextSeed(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GMSSLeaf nextLeaf() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.updateLeafCalc();
        return gMSSLeaf;
    }

    public String toString() {
        StringBuilder sb;
        String str;
        String str2 = "";
        for (int i = 0; i < 4; i++) {
            str2 = str2 + getStatInt()[i] + " ";
        }
        String str3 = str2 + " " + this.mdsize + " " + this.keysize + " " + this.two_power_w + " ";
        byte[][] statByte = getStatByte();
        for (int i2 = 0; i2 < 4; i2++) {
            if (statByte[i2] != null) {
                sb = new StringBuilder();
                sb.append(str3);
                sb.append(new String(Hex.encode(statByte[i2])));
                str = " ";
            } else {
                sb = new StringBuilder();
                sb.append(str3);
                str = "null ";
            }
            sb.append(str);
            str3 = sb.toString();
        }
        return str3;
    }
}
