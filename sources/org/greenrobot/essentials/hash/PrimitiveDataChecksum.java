package org.greenrobot.essentials.hash;

import java.io.UnsupportedEncodingException;
import java.util.zip.Checksum;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PrimitiveDataChecksum implements Checksum {
    private final Checksum checksum;

    public PrimitiveDataChecksum(Checksum checksum) {
        this.checksum = checksum;
    }

    @Override // java.util.zip.Checksum
    public void update(int i) {
        this.checksum.update(i);
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        this.checksum.update(bArr, i, i2);
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return this.checksum.getValue();
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.checksum.reset();
    }

    public void updateUtf8(String str) {
        if (str != null) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                update(bytes, 0, bytes.length);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateUtf8(String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                updateUtf8(str);
            }
        }
    }

    public void updateBoolean(boolean z) {
        update(z ? 1 : 0);
    }

    public void updateShort(short s) {
        update((s >>> 8) & 255);
        update(s & 255);
    }

    public void updateInt(int i) {
        update((i >>> 24) & 255);
        update((i >>> 16) & 255);
        update((i >>> 8) & 255);
        update(i & 255);
    }

    public void updateLong(long j) {
        update(((int) (j >>> 56)) & 255);
        update(((int) (j >>> 48)) & 255);
        update(((int) (j >>> 40)) & 255);
        update(((int) (j >>> 32)) & 255);
        update(((int) (j >>> 24)) & 255);
        update(((int) (j >>> 16)) & 255);
        update(((int) (j >>> 8)) & 255);
        update((int) (j & 255));
    }

    public void updateFloat(float f) {
        updateInt(Float.floatToIntBits(f));
    }

    public void updateDouble(double d) {
        updateLong(Double.doubleToLongBits(d));
    }

    public void update(byte[] bArr) {
        if (bArr != null) {
            update(bArr, 0, bArr.length);
        }
    }

    public void update(short[] sArr) {
        if (sArr != null) {
            for (short s : sArr) {
                updateShort(s);
            }
        }
    }

    public void update(int[] iArr) {
        if (iArr != null) {
            for (int i : iArr) {
                updateInt(i);
            }
        }
    }

    public void update(long[] jArr) {
        if (jArr != null) {
            for (long j : jArr) {
                updateLong(j);
            }
        }
    }

    public void update(float[] fArr) {
        if (fArr != null) {
            for (float f : fArr) {
                updateFloat(f);
            }
        }
    }

    public void update(double[] dArr) {
        if (dArr != null) {
            for (double d : dArr) {
                updateDouble(d);
            }
        }
    }
}
