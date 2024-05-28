package com.baidu.cloud.media.download;

import android.content.Context;
import com.baidu.cloud.media.player.apm.AndroidIDCache;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LocalHlsSec {
    public static final int DECRYPT = 1;
    public static final int ENCRYPT = 0;

    public native byte[] crypt(Context context, String str, byte[] bArr, int i);

    static {
        System.loadLibrary("hls-download");
    }

    public static byte[] hexStr2Bytes(String str) {
        if (str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }

    public static String bytes2HexStr(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 255) < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Long.toHexString(bArr[i] & 255));
        }
        return stringBuffer.toString();
    }

    public static String decryptStr(Context context, String str) {
        return bytes2HexStr(new LocalHlsSec().crypt(context, new AndroidIDCache(context).getAndroidID(), hexStr2Bytes(str), 1));
    }

    public String getMD5(String str) {
        return DownloadUtils.getMD5(str);
    }
}
