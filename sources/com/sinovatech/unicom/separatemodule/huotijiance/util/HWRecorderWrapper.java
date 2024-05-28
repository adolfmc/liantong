package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HWRecorderWrapper {
    private static final long MAX_TIMEOUT = 3000;
    private static boolean isStoped = true;
    private int frame_rate;
    private int height;
    private boolean isface;
    private ExecutorService mAExecutor;
    private int mImageFormat;
    private HWRecorder mRecorder = new HWRecorder();
    private ExecutorService mVExecutor;
    private int width;

    public boolean init(int i, int i2, int i3, int i4, int i5, int i6, String str, boolean z, int i7) {
        int i8;
        isStoped = false;
        this.width = i;
        this.height = i2;
        this.isface = z;
        this.frame_rate = i7;
        this.mImageFormat = i3;
        if (i3 == 17) {
            i8 = 21;
        } else if (i3 != 842094169) {
            Log.e(getClass().getSimpleName(), "HWRecorderWrapper image format not NV21 or YV21");
            return false;
        } else {
            i8 = 19;
        }
        try {
            this.mRecorder.init(i2, i, i8, i4, i5, i6, str, i7);
            this.mVExecutor = Executors.newSingleThreadExecutor();
            this.mAExecutor = Executors.newSingleThreadExecutor();
            return true;
        } catch (Exception unused) {
            Log.e(getClass().getSimpleName(), "HWRecorder init error");
            return false;
        }
    }

    public void recordImage(final byte[] bArr) {
        this.mVExecutor.execute(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.util.HWRecorderWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    byte[] bArr2 = bArr;
                    HWRecorderWrapper.this.mRecorder.recordImage(HWRecorderWrapper.this.isface ? HWRecorderWrapper.this.rotateYUV420Degree270AndMirror(bArr2, HWRecorderWrapper.this.width, HWRecorderWrapper.this.height) : HWRecorderWrapper.this.rotateYUV420Degree90(bArr2, HWRecorderWrapper.this.width, HWRecorderWrapper.this.height));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private byte[] convertToYuv420(byte[] bArr, int i) {
        if (i == 17) {
            return nv21ToYuv420sp(bArr);
        }
        if (i == 842094169) {
            return yv12ToYuv420p(bArr);
        }
        return null;
    }

    private byte[] nv21ToYuv420sp(byte[] bArr) {
        byte[] bArr2 = (byte[]) bArr.clone();
        for (int length = (bArr2.length * 2) / 3; length < bArr2.length - 1; length += 2) {
            byte b = bArr2[length];
            int i = length + 1;
            bArr2[length] = bArr2[i];
            bArr2[i] = b;
        }
        return bArr2;
    }

    private byte[] yv12ToYuv420p(byte[] bArr) {
        byte[] bArr2 = (byte[]) bArr.clone();
        int length = (bArr2.length * 2) / 3;
        int i = length / 2;
        for (int i2 = length; i2 < length + i; i2++) {
            byte b = bArr2[i2];
            int i3 = i2 + i;
            bArr2[i2] = bArr2[i3];
            bArr2[i3] = b;
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] rotateYUV420Degree270AndMirror(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[(i3 * 3) / 2];
        int i4 = i - 1;
        int i5 = i4;
        int i6 = 0;
        while (i5 >= 0) {
            int i7 = ((i2 - 1) * i) + (i5 * 2);
            int i8 = i6;
            for (int i9 = 0; i9 < i2; i9++) {
                bArr2[i8] = bArr[i7 - ((i9 * i) + i5)];
                i8++;
            }
            i5--;
            i6 = i8;
        }
        int i10 = i3;
        while (i4 > 0) {
            int i11 = i2 / 2;
            int i12 = ((i11 - 1) * i) + (i4 * 2) + i3;
            int i13 = i10;
            for (int i14 = 0; i14 < i11; i14++) {
                int i15 = (i14 * i) + i4;
                bArr2[i13] = bArr[(i12 - 2) - (i15 - 1)];
                int i16 = i13 + 1;
                bArr2[i16] = bArr[i12 - i15];
                i13 = i16 + 1;
            }
            i4 -= 2;
            i10 = i13;
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] rotateYUV420Degree90(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i; i6++) {
            for (int i7 = i2 - 1; i7 >= 0; i7--) {
                bArr2[i5] = bArr[(i7 * i) + i6];
                i5++;
            }
        }
        int i8 = i4 - 1;
        int i9 = i - 1;
        while (i9 > 0) {
            int i10 = i8;
            for (int i11 = 0; i11 < i2 / 2; i11++) {
                int i12 = (i11 * i) + i3;
                bArr2[i10] = bArr[i12 + i9];
                int i13 = i10 - 1;
                bArr2[i13] = bArr[i12 + (i9 - 1)];
                i10 = i13 - 1;
            }
            i9 -= 2;
            i8 = i10;
        }
        return bArr2;
    }

    public void recordSample(final byte[] bArr) {
        this.mAExecutor.execute(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.util.HWRecorderWrapper.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    HWRecorderWrapper.this.mRecorder.recordSample(bArr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stop() {
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.util.HWRecorderWrapper.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    HWRecorderWrapper.this.mVExecutor.shutdown();
                    HWRecorderWrapper.this.mVExecutor.awaitTermination(3000L, TimeUnit.MILLISECONDS);
                    HWRecorderWrapper.this.mAExecutor.shutdown();
                    HWRecorderWrapper.this.mAExecutor.awaitTermination(3000L, TimeUnit.MICROSECONDS);
                    HWRecorderWrapper.this.mRecorder.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean unused = HWRecorderWrapper.isStoped = true;
            }
        });
    }

    public static boolean isStoped() {
        return isStoped;
    }
}
