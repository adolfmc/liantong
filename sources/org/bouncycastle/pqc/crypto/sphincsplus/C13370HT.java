package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.LinkedList;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.pqc.crypto.sphincsplus.HT */
/* loaded from: E:\9227576_dexfile_execute.dex */
class C13370HT {
    SPHINCSPlusEngine engine;
    final byte[] htPubKey;
    private final byte[] pkSeed;
    private final byte[] skSeed;
    WotsPlus wots;

    public C13370HT(SPHINCSPlusEngine sPHINCSPlusEngine, byte[] bArr, byte[] bArr2) {
        this.skSeed = bArr;
        this.pkSeed = bArr2;
        this.engine = sPHINCSPlusEngine;
        this.wots = new WotsPlus(sPHINCSPlusEngine);
        ADRS adrs = new ADRS();
        adrs.setLayerAddress(sPHINCSPlusEngine.f27247D - 1);
        adrs.setTreeAddress(0L);
        this.htPubKey = bArr != null ? xmss_PKgen(bArr, bArr2, adrs) : null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] sign(byte[] bArr, long j, int i) {
        ADRS adrs = new ADRS();
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j);
        SIG_XMSS xmss_sign = xmss_sign(bArr, this.skSeed, i, this.pkSeed, adrs);
        SIG_XMSS[] sig_xmssArr = new SIG_XMSS[this.engine.f27247D];
        sig_xmssArr[0] = xmss_sign;
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j);
        byte[] xmss_pkFromSig = xmss_pkFromSig(i, xmss_sign, bArr, this.pkSeed, adrs);
        for (int i2 = 1; i2 < this.engine.f27247D; i2++) {
            int i3 = (int) (((1 << this.engine.H_PRIME) - 1) & j);
            j >>>= this.engine.H_PRIME;
            adrs.setLayerAddress(i2);
            adrs.setTreeAddress(j);
            SIG_XMSS xmss_sign2 = xmss_sign(xmss_pkFromSig, this.skSeed, i3, this.pkSeed, adrs);
            sig_xmssArr[i2] = xmss_sign2;
            if (i2 < this.engine.f27247D - 1) {
                xmss_pkFromSig = xmss_pkFromSig(i3, xmss_sign2, xmss_pkFromSig, this.pkSeed, adrs);
            }
        }
        byte[][] bArr2 = new byte[sig_xmssArr.length];
        for (int i4 = 0; i4 != bArr2.length; i4++) {
            bArr2[i4] = Arrays.concatenate(sig_xmssArr[i4].sig, Arrays.concatenate(sig_xmssArr[i4].auth));
        }
        return Arrays.concatenate(bArr2);
    }

    byte[] treehash(byte[] bArr, int i, int i2, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        LinkedList linkedList = new LinkedList();
        int i3 = 1 << i2;
        if (i % i3 != 0) {
            return null;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            adrs2.setType(0);
            int i5 = i + i4;
            adrs2.setKeyPairAddress(i5);
            byte[] pkGen = this.wots.pkGen(bArr, bArr2, adrs2);
            adrs2.setType(2);
            adrs2.setTreeHeight(1);
            adrs2.setTreeIndex(i5);
            while (!linkedList.isEmpty() && ((NodeEntry) linkedList.get(0)).nodeHeight == adrs2.getTreeHeight()) {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                pkGen = this.engine.mo244H(bArr2, adrs2, ((NodeEntry) linkedList.remove(0)).nodeValue, pkGen);
                adrs2.setTreeHeight(adrs2.getTreeHeight() + 1);
            }
            linkedList.add(0, new NodeEntry(pkGen, adrs2.getTreeHeight()));
        }
        return ((NodeEntry) linkedList.get(0)).nodeValue;
    }

    public boolean verify(byte[] bArr, SIG_XMSS[] sig_xmssArr, byte[] bArr2, long j, int i, byte[] bArr3) {
        ADRS adrs = new ADRS();
        SIG_XMSS sig_xmss = sig_xmssArr[0];
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j);
        byte[] xmss_pkFromSig = xmss_pkFromSig(i, sig_xmss, bArr, bArr2, adrs);
        for (int i2 = 1; i2 < this.engine.f27247D; i2++) {
            int i3 = (int) (((1 << this.engine.H_PRIME) - 1) & j);
            j >>>= this.engine.H_PRIME;
            SIG_XMSS sig_xmss2 = sig_xmssArr[i2];
            adrs.setLayerAddress(i2);
            adrs.setTreeAddress(j);
            xmss_pkFromSig = xmss_pkFromSig(i3, sig_xmss2, xmss_pkFromSig, bArr2, adrs);
        }
        return Arrays.areEqual(bArr3, xmss_pkFromSig);
    }

    byte[] xmss_PKgen(byte[] bArr, byte[] bArr2, ADRS adrs) {
        return treehash(bArr, 0, this.engine.H_PRIME, bArr2, adrs);
    }

    byte[] xmss_pkFromSig(int i, SIG_XMSS sig_xmss, byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int i2 = 0;
        adrs2.setType(0);
        adrs2.setKeyPairAddress(i);
        byte[] wOTSSig = sig_xmss.getWOTSSig();
        byte[][] xmssauth = sig_xmss.getXMSSAUTH();
        byte[] pkFromSig = this.wots.pkFromSig(wOTSSig, bArr, bArr2, adrs2);
        adrs2.setType(2);
        adrs2.setTreeIndex(i);
        while (i2 < this.engine.H_PRIME) {
            int i3 = i2 + 1;
            adrs2.setTreeHeight(i3);
            if ((i / (1 << i2)) % 2 == 0) {
                adrs2.setTreeIndex(adrs2.getTreeIndex() / 2);
                pkFromSig = this.engine.mo244H(bArr2, adrs2, pkFromSig, xmssauth[i2]);
            } else {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                pkFromSig = this.engine.mo244H(bArr2, adrs2, xmssauth[i2], pkFromSig);
            }
            i2 = i3;
        }
        return pkFromSig;
    }

    SIG_XMSS xmss_sign(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, ADRS adrs) {
        byte[][] bArr4 = new byte[this.engine.H_PRIME];
        ADRS adrs2 = new ADRS(adrs);
        adrs2.setType(2);
        adrs2.setLayerAddress(adrs.getLayerAddress());
        adrs2.setTreeAddress(adrs.getTreeAddress());
        for (int i2 = 0; i2 < this.engine.H_PRIME; i2++) {
            int i3 = 1 << i2;
            bArr4[i2] = treehash(bArr2, (1 ^ (i / i3)) * i3, i2, bArr3, adrs2);
        }
        ADRS adrs3 = new ADRS(adrs);
        adrs3.setType(1);
        adrs3.setKeyPairAddress(i);
        return new SIG_XMSS(this.wots.sign(bArr, bArr2, bArr3, adrs3), bArr4);
    }
}
