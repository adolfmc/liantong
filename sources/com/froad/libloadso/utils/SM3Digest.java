package com.froad.libloadso.utils;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SM3Digest {
    private static final int BLOCK_LENGTH = 64;
    private static final int BUFFER_LENGTH = 64;
    private static final int BYTE_LENGTH = 32;

    /* renamed from: V */
    private byte[] f10134V;
    private int cntBlock;
    private byte[] xBuf;
    private int xBufOff;

    public SM3Digest() {
        this.xBuf = new byte[64];
        this.f10134V = (byte[]) SM3.f10133iv.clone();
        this.cntBlock = 0;
    }

    public SM3Digest(SM3Digest sM3Digest) {
        this.xBuf = new byte[64];
        this.f10134V = (byte[]) SM3.f10133iv.clone();
        this.cntBlock = 0;
        byte[] bArr = sM3Digest.xBuf;
        System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
        this.xBufOff = sM3Digest.xBufOff;
        byte[] bArr2 = sM3Digest.f10134V;
        System.arraycopy(bArr2, 0, this.f10134V, 0, bArr2.length);
    }

    private byte[] doFinal() {
        byte[] bArr = new byte[64];
        int i = this.xBufOff;
        byte[] bArr2 = new byte[i];
        System.arraycopy(this.xBuf, 0, bArr2, 0, i);
        byte[] padding = SM3.padding(bArr2, this.cntBlock);
        for (int i2 = 0; i2 < padding.length; i2 += 64) {
            System.arraycopy(padding, i2, bArr, 0, 64);
            doHash(bArr);
        }
        return this.f10134V;
    }

    private void doHash(byte[] bArr) {
        byte[] m15915CF = SM3.m15915CF(this.f10134V, bArr);
        byte[] bArr2 = this.f10134V;
        System.arraycopy(m15915CF, 0, bArr2, 0, bArr2.length);
        this.cntBlock++;
    }

    private void doUpdate() {
        byte[] bArr = new byte[64];
        for (int i = 0; i < 64; i += 64) {
            System.arraycopy(this.xBuf, i, bArr, 0, 64);
            doHash(bArr);
        }
        this.xBufOff = 0;
    }

    public static void main(String[] strArr) {
        byte[] bArr = new byte[32];
        byte[] bytes = "abc".getBytes();
        SM3Digest sM3Digest = new SM3Digest();
        sM3Digest.update(bytes, 0, bytes.length);
        sM3Digest.doFinal(bArr, 0);
        System.out.println(new String(bArr));
    }

    public int doFinal(byte[] bArr, int i) {
        byte[] doFinal = doFinal();
        System.arraycopy(doFinal, 0, bArr, 0, doFinal.length);
        return 32;
    }

    public int getDigestSize() {
        return 32;
    }

    public void reset() {
        this.xBufOff = 0;
        this.cntBlock = 0;
        this.f10134V = (byte[]) SM3.f10133iv.clone();
    }

    public void update(byte b) {
        update(new byte[]{b}, 0, 1);
    }

    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.xBufOff;
        int i4 = 64 - i3;
        if (i4 < i2) {
            System.arraycopy(bArr, i, this.xBuf, i3, i4);
            i2 -= i4;
            i += i4;
            while (true) {
                doUpdate();
                if (i2 <= 64) {
                    break;
                }
                System.arraycopy(bArr, i, this.xBuf, 0, 64);
                i2 -= 64;
                i += 64;
            }
        }
        System.arraycopy(bArr, i, this.xBuf, this.xBufOff, i2);
        this.xBufOff += i2;
    }
}
