package com.utils.p366ak;

import android.annotation.SuppressLint;
import com.gmrz.android.client.utils.Logger;
import java.util.concurrent.ThreadLocalRandom;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.utils.ak.Util */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Util {
    private static final String TAG = "Util";

    public static short makeShort(byte b, byte b2) {
        return (short) ((b << 8) | (b2 & 255));
    }

    public static int arrayCopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length < i) {
            Logger.m15892e(TAG, "arrayCopy return error, src.length < srcOff");
            return -1;
        } else if (bArr.length < i3 + i) {
            Logger.m15892e(TAG, "arrayCopy return error, src.length < srcOff");
            return -1;
        } else if (bArr2.length >= i2 && bArr2.length >= i2 + i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                bArr2[i2] = bArr[i];
                i2++;
                i++;
            }
            return i2;
        } else {
            return -1;
        }
    }

    public static short getShort(byte[] bArr, int i) {
        return (short) ((bArr[i] & 255) | (bArr[i + 1] << 8));
    }

    public static short setShort16(byte[] bArr, short s, short s2) {
        bArr[s + 1] = (byte) (s2 >> 8);
        bArr[s] = (byte) s2;
        return (short) (s + 2);
    }

    public static short setShort8(byte[] bArr, short s, short s2) {
        bArr[s] = (byte) s2;
        return (short) (s + 1);
    }

    @SuppressLint({"NewApi"})
    public static String random(int i) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append((char) ThreadLocalRandom.current().nextInt(33, 128));
        }
        return sb.toString();
    }
}
