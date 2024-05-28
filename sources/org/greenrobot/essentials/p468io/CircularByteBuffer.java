package org.greenrobot.essentials.p468io;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.greenrobot.essentials.io.CircularByteBuffer */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CircularByteBuffer {
    private int available;
    private final byte[] buffer;
    private final int capacity;
    private int idxGet;
    private int idxPut;

    public CircularByteBuffer() {
        this(8192);
    }

    public CircularByteBuffer(int i) {
        this.capacity = i;
        this.buffer = new byte[this.capacity];
    }

    public synchronized void clear() {
        this.available = 0;
        this.idxPut = 0;
        this.idxGet = 0;
    }

    public synchronized int get() {
        if (this.available == 0) {
            return -1;
        }
        byte b = this.buffer[this.idxGet];
        this.idxGet = (this.idxGet + 1) % this.capacity;
        this.available--;
        return b;
    }

    public int get(byte[] bArr) {
        return get(bArr, 0, bArr.length);
    }

    public synchronized int get(byte[] bArr, int i, int i2) {
        if (this.available == 0) {
            return 0;
        }
        int min = Math.min((this.idxGet < this.idxPut ? this.idxPut : this.capacity) - this.idxGet, i2);
        System.arraycopy(this.buffer, this.idxGet, bArr, i, min);
        this.idxGet += min;
        if (this.idxGet == this.capacity) {
            int min2 = Math.min(i2 - min, this.idxPut);
            if (min2 > 0) {
                System.arraycopy(this.buffer, 0, bArr, i + min, min2);
                this.idxGet = min2;
                min += min2;
            } else {
                this.idxGet = 0;
            }
        }
        this.available -= min;
        return min;
    }

    public synchronized boolean put(byte b) {
        if (this.available == this.capacity) {
            return false;
        }
        this.buffer[this.idxPut] = b;
        this.idxPut = (this.idxPut + 1) % this.capacity;
        this.available++;
        return true;
    }

    public int put(byte[] bArr) {
        return put(bArr, 0, bArr.length);
    }

    public synchronized int put(byte[] bArr, int i, int i2) {
        if (this.available == this.capacity) {
            return 0;
        }
        int min = Math.min((this.idxPut < this.idxGet ? this.idxGet : this.capacity) - this.idxPut, i2);
        System.arraycopy(bArr, i, this.buffer, this.idxPut, min);
        this.idxPut += min;
        if (this.idxPut == this.capacity) {
            int min2 = Math.min(i2 - min, this.idxGet);
            if (min2 > 0) {
                System.arraycopy(bArr, i + min, this.buffer, 0, min2);
                this.idxPut = min2;
                min += min2;
            } else {
                this.idxPut = 0;
            }
        }
        this.available += min;
        return min;
    }

    public synchronized int peek() {
        return this.available > 0 ? this.buffer[this.idxGet] : (byte) -1;
    }

    public synchronized int skip(int i) {
        if (i > this.available) {
            i = this.available;
        }
        this.idxGet = (this.idxGet + i) % this.capacity;
        this.available -= i;
        return i;
    }

    public int capacity() {
        return this.capacity;
    }

    public synchronized int available() {
        return this.available;
    }

    public synchronized int free() {
        return this.capacity - this.available;
    }
}
