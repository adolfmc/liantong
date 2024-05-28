package org.simalliance.openmobileapi.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import java.security.AccessControlException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Util {
    public static final byte END = -1;

    public static byte[] appendResponse(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[bArr.length + i];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, i);
        return bArr3;
    }

    @Deprecated
    public static String bytesToString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x ", Integer.valueOf(bArr[i] & 255)));
        }
        String sb2 = sb.toString();
        return sb2.length() > 0 ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    public static byte clearChannelNumber(byte b) {
        return (byte) ((b & 64) == 0 ? b & 252 : b & 240);
    }

    public static String createMessage(String str, int i) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            sb.append(" ");
        }
        sb.append("SW1/2 error: ");
        sb.append(Integer.toHexString(65536 | i).substring(1));
        return sb.toString();
    }

    public static byte[] getMid(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static String getPackageNameFromCallingUid(Context context, int i) {
        String[] packagesForUid;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null && (packagesForUid = packageManager.getPackagesForUid(i)) != null && packagesForUid.length > 0) {
            return packagesForUid[0];
        }
        throw new AccessControlException("Caller PackageName can not be determined");
    }

    public static byte[] mergeBytes(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static int parseChannelNumber(byte b) {
        return (b & 64) == 0 ? b & 3 : (b & 15) + 4;
    }

    public static byte setChannelToClassByte(byte b, int i) {
        int i2;
        if (i < 4) {
            i2 = (b & 188) | i;
        } else if (i >= 20) {
            throw new IllegalArgumentException("Channel number must be within [0..19]");
        } else {
            boolean z = (b & 12) != 0;
            byte b2 = (byte) ((b & 176) | 64 | (i - 4));
            if (!z) {
                return b2;
            }
            i2 = b2 | 32;
        }
        return (byte) i2;
    }

    @Deprecated
    public static String bytesToString(byte[] bArr, int i, int i2, String str) {
        if (bArr == null) {
            return null;
        }
        if (i2 == -1) {
            i2 = bArr.length - i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i + i2; i3++) {
            sb.append(str);
            sb.append(Integer.toHexString((bArr[i3] & 255) + 256).substring(1));
        }
        return sb.toString();
    }

    public static String createMessage(String str, String str2) {
        if (str == null) {
            return str2;
        }
        return str + " " + str2;
    }
}
