package com.huawei.secure.android.common.encrypt.utils;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class HexUtil {

    /* renamed from: a */
    private static final String f11974a = "";

    /* renamed from: b */
    private static final String f11975b = "HexUtil";

    private HexUtil() {
    }

    public static String byteArray2HexStr(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArray(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            byte[] bArr = new byte[upperCase.length() / 2];
            try {
                byte[] bytes = upperCase.getBytes("UTF-8");
                for (int i2 = 0; i2 < bArr.length; i2++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("0x");
                    sb.append(new String(new byte[]{bytes[i2 * 2]}, "UTF-8"));
                    bArr[i2] = (byte) (((byte) (Byte.decode(sb.toString()).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[i + 1]}, "UTF-8")).byteValue());
                }
                return bArr;
            } catch (UnsupportedEncodingException | NumberFormatException e) {
                C5106b.m13942b("HexUtil", "hex string 2 byte array exception : " + e.getMessage());
                return new byte[0];
            }
        } catch (Throwable th) {
            C5106b.m13942b("HexUtil", "hex string toUpperCase exception : " + th.getMessage());
            return new byte[0];
        }
    }

    public static String byteArray2HexStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return byteArray2HexStr(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            C5106b.m13942b("HexUtil", "byte array 2 hex string exception : " + e.getMessage());
            return "";
        }
    }
}
