package com.p226hw.videoprocessor.util;

import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hw.videoprocessor.util.AudioFadeUtil */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AudioFadeUtil {
    public static void audioFade(String str, int i, int i2, float f, float f2) throws IOException {
        float f3 = ((i * 16) / 8) * i2;
        int i3 = (int) (f * f3);
        int i4 = (int) (f3 * f2);
        byte[] bArr = new byte[i3];
        byte[] bArr2 = new byte[i4];
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
        randomAccessFile.read(bArr, 0, i3);
        long j = i4;
        randomAccessFile.seek((int) (randomAccessFile.length() - j));
        randomAccessFile.read(bArr2, 0, i4);
        doFaceIn(bArr);
        doFaceOut(bArr2);
        randomAccessFile.seek(0L);
        randomAccessFile.write(bArr, 0, i3);
        randomAccessFile.seek((int) (randomAccessFile.length() - j));
        randomAccessFile.write(bArr2, 0, i4);
        randomAccessFile.close();
    }

    private static void doFaceIn(byte[] bArr) {
        float length = 1.0f / (bArr.length / 2.0f);
        float f = 0.0f;
        for (int i = 0; i < bArr.length; i += 2) {
            int i2 = i + 1;
            int i3 = (int) (((short) ((bArr[i] & 255) | ((bArr[i2] & 255) << 8))) * f);
            if (i3 > 32767) {
                i3 = 32767;
            } else if (i3 < -32768) {
                i3 = -32768;
            }
            bArr[i] = (byte) (i3 & 255);
            bArr[i2] = (byte) ((i3 >>> 8) & 255);
            f += length;
        }
    }

    private static void doFaceOut(byte[] bArr) {
        float f = 1.0f;
        float length = 1.0f / (bArr.length / 2.0f);
        for (int i = 0; i < bArr.length; i += 2) {
            int i2 = i + 1;
            int i3 = (int) (((short) ((bArr[i] & 255) | ((bArr[i2] & 255) << 8))) * f);
            if (i3 > 32767) {
                i3 = 32767;
            } else if (i3 < -32768) {
                i3 = -32768;
            }
            bArr[i] = (byte) (i3 & 255);
            bArr[i2] = (byte) ((i3 >>> 8) & 255);
            f -= length;
        }
    }
}
