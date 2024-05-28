package com.baidu.cloud.mediaprocess.filter;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RingByteBuffer {

    /* renamed from: a */
    public byte[] f4737a;

    /* renamed from: b */
    public volatile int f4738b;

    /* renamed from: c */
    public volatile int f4739c;

    /* renamed from: d */
    public volatile int f4740d;

    /* renamed from: e */
    public int f4741e;

    public RingByteBuffer() {
        this(4194304);
    }

    public RingByteBuffer(int i) {
        this.f4737a = null;
        this.f4738b = 0;
        this.f4739c = 0;
        this.f4740d = 0;
        this.f4741e = 0;
        this.f4741e = i;
        this.f4737a = new byte[i];
    }

    public synchronized boolean get(byte[] bArr, int i) {
        if (bArr != null) {
            if (bArr.length >= i && i <= this.f4738b) {
                if (this.f4739c + i <= this.f4741e) {
                    System.arraycopy(this.f4737a, this.f4739c, bArr, 0, i);
                    this.f4739c += i;
                    this.f4738b -= i;
                    return true;
                }
                System.arraycopy(this.f4737a, this.f4739c, bArr, 0, this.f4741e - this.f4739c);
                System.arraycopy(this.f4737a, 0, bArr, this.f4741e - this.f4739c, (this.f4739c + i) - this.f4741e);
                this.f4739c = (i - this.f4741e) + this.f4739c;
                this.f4738b -= i;
                return true;
            }
        }
        return false;
    }

    public synchronized boolean put(byte[] bArr, int i) {
        if (bArr != null) {
            if (bArr.length >= i && this.f4738b + i <= this.f4741e) {
                if (this.f4740d + i <= this.f4741e) {
                    System.arraycopy(bArr, 0, this.f4737a, this.f4740d, i);
                    this.f4740d += i;
                    this.f4738b += i;
                    return true;
                }
                System.arraycopy(bArr, 0, this.f4737a, this.f4740d, this.f4741e - this.f4740d);
                System.arraycopy(bArr, this.f4741e - this.f4740d, this.f4737a, 0, (this.f4740d + i) - this.f4741e);
                this.f4740d = (i - this.f4741e) + this.f4740d;
                this.f4738b += i;
                return true;
            }
        }
        return false;
    }

    public synchronized void reset() {
        this.f4738b = 0;
        this.f4739c = 0;
        this.f4740d = 0;
    }

    public int size() {
        return this.f4738b;
    }
}
