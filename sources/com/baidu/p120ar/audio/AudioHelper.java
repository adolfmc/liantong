package com.baidu.p120ar.audio;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Build;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.AudioHelper */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AudioHelper {
    public static final int CHECK_VOLUME_FRAME_NUMBER_MAX = 20;
    private static final String TAG = "AudioHelper";

    private static int pcmByte2Int(byte b, byte b2) {
        int i = (b & 255) + ((b2 & 255) << 8);
        return i >= 32768 ? i - 65535 : i;
    }

    AudioHelper() {
    }

    public static boolean checkAudioRecordPermission(Context context) {
        String packageName = context.getApplicationContext().getPackageName();
        if (Build.VERSION.SDK_INT >= 23) {
            return checkPermissionOverVersionM(context, packageName);
        }
        return checkPermissionUnderVersionM();
    }

    private static boolean checkPermissionOverVersionM(Context context, String str) {
        return context.getPackageManager().checkPermission("android.permission.RECORD_AUDIO", str) == 0;
    }

    private static boolean checkPermissionUnderVersionM() {
        AudioRecord audioRecord = new AudioRecord(1, 16000, 16, 2, 1024);
        try {
            audioRecord.startRecording();
            byte[] bArr = new byte[32768];
            boolean z = audioRecord.getRecordingState() == 3;
            for (int i = 0; i < 20; i++) {
                audioRecord.read(bArr, 0, bArr.length);
                double calculateVolume = calculateVolume(bArr);
                ARLog.m20417i(TAG, "checkPermissionUnderVersionM volume = " + calculateVolume);
                z = calculateVolume != 0.0d;
                if (z) {
                    break;
                }
            }
            audioRecord.stop();
            audioRecord.release();
            return z;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static double calculateVolume(byte[] bArr) {
        double d = 0.0d;
        for (int i = 0; i < bArr.length; i += 2) {
            d += Math.abs(pcmByte2Int(bArr[i], bArr[i + 1]));
        }
        return Math.log10(((d / bArr.length) / 2.0d) + 1.0d) * 10.0d;
    }

    public static void amplifyVolume(byte[] bArr, double d) {
        for (int i = 0; i < bArr.length; i += 2) {
            int i2 = i + 1;
            int pcmByte2Int = pcmByte2Int(bArr[i], bArr[i2]);
            int i3 = (int) (pcmByte2Int * d);
            if (i3 < 32767 && i3 > -32768) {
                pcmByte2Int = (short) i3;
            } else if (i3 > 32767) {
                pcmByte2Int = 32767;
            } else if (i3 < -32768) {
                pcmByte2Int = -32768;
            }
            bArr[i] = (byte) (pcmByte2Int & 255);
            bArr[i2] = (byte) ((pcmByte2Int >> 8) & 255);
        }
    }

    public static void amplifyVolume(byte[] bArr, int i) {
        amplifyVolume(bArr, Math.pow(10.0d, i / 20.0d));
    }

    public static double calculateVolumePercent(byte[] bArr) {
        double d = 0.0d;
        for (int i = 0; i < bArr.length; i += 2) {
            int pcmByte2Int = pcmByte2Int(bArr[i], bArr[i + 1]);
            d += pcmByte2Int * pcmByte2Int;
        }
        return Math.min(5000.0d, Math.sqrt((d / bArr.length) / 2.0d)) / 50.0d;
    }
}
