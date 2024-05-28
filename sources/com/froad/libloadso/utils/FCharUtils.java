package com.froad.libloadso.utils;

import android.annotation.SuppressLint;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.DisplayMetrics;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FCharUtils {
    private static String TAG = "FCharUtils";

    public static String base64Str(String str) {
        String encodeToString = Base64.encodeToString(hexString2ByteArray(str), 0);
        return encodeToString.contains("\n") ? encodeToString.replaceAll("\n", "") : encodeToString;
    }

    public static int bcdStr2Int(String str) {
        if (str == null || "".equals(str)) {
            return -1;
        }
        return Integer.parseInt(str, 10);
    }

    public static char byte2char(byte b) {
        return (char) (((char) b) & 255);
    }

    public static byte[] byteConvert32Bytes(BigInteger bigInteger) {
        if (bigInteger == null) {
            return null;
        }
        if (bigInteger.toByteArray().length == 33) {
            byte[] bArr = new byte[32];
            System.arraycopy(bigInteger.toByteArray(), 1, bArr, 0, 32);
            return bArr;
        } else if (bigInteger.toByteArray().length == 32) {
            return bigInteger.toByteArray();
        } else {
            byte[] bArr2 = new byte[32];
            for (int i = 0; i < 32 - bigInteger.toByteArray().length; i++) {
                bArr2[i] = 0;
            }
            System.arraycopy(bigInteger.toByteArray(), 0, bArr2, 32 - bigInteger.toByteArray().length, bigInteger.toByteArray().length);
            return bArr2;
        }
    }

    public static char[] byteToCharArray(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (((char) bArr[i]) & 255);
        }
        return cArr;
    }

    public static int byteToInt(byte[] bArr) {
        return ((bArr[3] & 255) << 24) | 0 | ((bArr[0] & 255) << 0) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    public static String bytesToHexStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static boolean checkBleVer() {
        try {
            Class.forName("android.bluetooth.BluetoothAdapter$LeScanCallback");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkHexData(String str) {
        return Pattern.compile("[0-9a-fA-F]*").matcher(str).matches();
    }

    public static boolean checkHexStr(String str) {
        char[] charArray;
        Pattern compile = Pattern.compile("[^(0-9a-fA-F)]");
        int length = str.toCharArray().length;
        for (int i = 0; i < length; i++) {
            if (compile.matcher("" + charArray[i]).find()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNum(String str) {
        char[] charArray;
        Pattern compile = Pattern.compile("[^0-9]");
        int length = str.toCharArray().length;
        for (int i = 0; i < length; i++) {
            if (compile.matcher("" + charArray[i]).find()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNumIsMobile(String str) {
        char[] charArray;
        if (isNotEmpty(str)) {
            Pattern compile = Pattern.compile("^((13)|(15)|(18))\\\\d{9}$");
            int length = str.toCharArray().length;
            for (int i = 0; i < length; i++) {
                if (compile.matcher("" + charArray[i]).find()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean checkNumIsMoney(String str) {
        char[] charArray;
        Pattern compile = Pattern.compile("[^0-9.]");
        int length = str.toCharArray().length;
        for (int i = 0; i < length; i++) {
            if (compile.matcher("" + charArray[i]).find()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNumOrZ(String str) {
        char[] charArray;
        Pattern compile = Pattern.compile("[^(0-9a-zA-Z)]");
        int length = str.toCharArray().length;
        for (int i = 0; i < length; i++) {
            if (compile.matcher("" + charArray[i]).find()) {
                return false;
            }
        }
        return true;
    }

    public static String checkOutStrSum(String str) {
        try {
            int length = str.length();
            if (length < 3) {
                return "ERROR";
            }
            int i = length - 1;
            String substring = str.substring(0, i);
            String substring2 = str.substring(i, length);
            char[] charArray = substring.toCharArray();
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                i2 += charArray[i3];
            }
            String hexString = Integer.toHexString(i2);
            int length2 = hexString.length();
            if (length2 < 2) {
                return substring2.charAt(0) == i2 ? substring : "ERROR";
            }
            String substring3 = hexString.substring(length2 - 2);
            char charAt = substring2.charAt(0);
            StringBuilder sb = new StringBuilder();
            sb.append("0x");
            sb.append(substring3);
            return charAt == Integer.decode(sb.toString()).intValue() ? substring : "ERROR";
        } catch (Exception unused) {
            return "ERROR";
        }
    }

    public static String checkOutStr_21(String str) {
        try {
            int length = str.length();
            int i = length - 1;
            int parseInt = Integer.parseInt(str.substring(i, length));
            char[] charArray = str.substring(0, i).toCharArray();
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                i2 += charArray[i3] * (i3 % 2 == 0 ? (char) 2 : (char) 1);
            }
            return parseInt == (10 - (i2 % 10)) % 10 ? str.substring(0, i) : "ERROR";
        } catch (Exception unused) {
            return "ERROR";
        }
    }

    public static boolean checkOutStr_LRC(String str) {
        int length = str.length();
        if (length < 3) {
            return false;
        }
        int i = length - 1;
        String substring = str.substring(0, i);
        String substring2 = str.substring(i, length);
        char[] charArray = substring.toCharArray();
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += charArray[i3];
        }
        String hexString = Integer.toHexString(i2);
        int length2 = hexString.length();
        if (length2 > 2) {
            hexString = hexString.substring(length2 - 2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        sb.append(hexString);
        return substring2.charAt(0) == ((char) (((char) (((char) (255 - ((char) Integer.decode(sb.toString()).intValue()))) + 1)) & 255));
    }

    public static boolean checkOutStr_XOR(String str) {
        int length = str.length();
        if (length < 3) {
            return false;
        }
        int i = length - 1;
        String substring = str.substring(0, i);
        String substring2 = str.substring(i, length);
        char[] charArray = substring.toCharArray();
        char c = 0;
        for (int i2 = 0; i2 < length - 2; i2++) {
            c = (char) (c ^ charArray[i2]);
        }
        return substring2.charAt(0) == c;
    }

    public static byte[] cutBytes(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return bArr2;
    }

    public static String enc21Int(String str) {
        try {
            int length = str.length();
            char[] charArray = str.toCharArray();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                i += charArray[i2] * (i2 % 2 == 0 ? (char) 2 : (char) 1);
            }
            return str + ((10 - (i % 10)) % 10);
        } catch (Exception unused) {
            return "ERROR";
        }
    }

    public static char encLRCInt(String str) {
        int length = str.length();
        char[] charArray = str.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += charArray[i2];
        }
        String hexString = Integer.toHexString(i);
        int length2 = hexString.length();
        if (length2 > 2) {
            hexString = hexString.substring(length2 - 2);
        }
        return (char) (((char) (((char) (255 - ((char) Integer.decode("0x" + hexString).intValue()))) + 1)) & 255);
    }

    public static String encSumInt(String str) {
        try {
            int length = str.length();
            char[] charArray = str.toCharArray();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                i += charArray[i2];
            }
            String hexString = Integer.toHexString(i);
            int length2 = hexString.length();
            if (length2 < 2) {
                return str + ((char) i);
            }
            String substring = hexString.substring(length2 - 2);
            return str + ((char) Integer.decode("0x" + substring).intValue());
        } catch (Exception unused) {
            return "ERROR";
        }
    }

    public static char encXORInt(String str) {
        int length = str.length();
        char[] charArray = str.toCharArray();
        char c = 0;
        for (int i = 0; i < length; i++) {
            c = (char) (c ^ charArray[i]);
        }
        return c;
    }

    public static String getMD5(Signature signature) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(signature.toByteArray());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String upperCase = Integer.toHexString(b & 255).toUpperCase();
                if (upperCase.length() < 2) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(upperCase);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] getMD5(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRandom(int i) {
        String[] strArr = {"A", "B", "C", "D", "E", "F"};
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i2 = 0; i2 < i * 2; i2++) {
            int nextInt = random.nextInt(16);
            stringBuffer.append(nextInt > 9 ? strArr[nextInt % 10] : "" + nextInt);
        }
        return stringBuffer.toString();
    }

    public static String hexStr2LV(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        return len2HexStr(str.length() / 2) + str;
    }

    public static String hexStr2LV00(String str) {
        if (str == null || "".equals(str)) {
            return "00";
        }
        return len2HexStr(str.length() / 2) + str;
    }

    public static String hexStr2LV00_2(String str) {
        if (str == null || "".equals(str)) {
            return "0000";
        }
        return int2HexStr2(str.length() / 2) + str;
    }

    public static String hexStr2LV_1(String str, boolean z) {
        if (str == null || "".equals(str)) {
            return z ? "00" : "";
        }
        return int2HexStr(str.length() / 2) + str;
    }

    public static int hexStr2Len(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        if (str.length() == 4) {
            return ((Integer.parseInt(str.substring(0, 2), 16) - 128) << 8) + Integer.parseInt(str.substring(2, 4), 16);
        } else if (str.length() == 2 || str.length() == 1) {
            return Integer.parseInt(str, 16);
        } else {
            return 0;
        }
    }

    public static String hexStr2String(String str, String str2) {
        try {
            return new String(hexString2ByteArray(str), str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hexStr2UCString(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (length % 4 != 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < length) {
            int i2 = i + 4;
            stringBuffer.append((char) Integer.parseInt(str.substring(i, i2), 16));
            i = i2;
        }
        return stringBuffer.toString();
    }

    public static byte[] hexStrToBytes(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    public static byte[] hexString2ByteArray(String str) {
        byte[] bArr = null;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length != 0 && length % 2 == 0) {
            bArr = new byte[length / 2];
            int i = 0;
            while (i < length) {
                int i2 = i + 2;
                bArr[i / 2] = (byte) Integer.parseInt(str.substring(i, i2), 16);
                i = i2;
            }
        }
        return bArr;
    }

    public static String int2BCDStr(int i) {
        String str = "" + i;
        if (str.length() % 2 != 0) {
            return "0" + str;
        }
        return str;
    }

    public static String int2HexStr(int i) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() % 2 != 0) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    public static String int2HexStr2(int i) {
        String stringBuffer;
        String hexString = Integer.toHexString(i);
        int length = hexString.length();
        if (length >= 4) {
            stringBuffer = hexString.substring(length - 4);
        } else {
            int i2 = 4 - length;
            StringBuffer stringBuffer2 = new StringBuffer();
            for (int i3 = 0; i3 < i2; i3++) {
                stringBuffer2.append("0");
            }
            stringBuffer2.append(hexString);
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer.toUpperCase();
    }

    public static String intToByte(int i) {
        return showResult16Str(new byte[]{(byte) (i & 255)});
    }

    public static byte[] intToBytes(int i) {
        return new byte[]{(byte) ((i >> 0) & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static boolean isNotEmpty(String str) {
        return (str == null || "".equals(str.replaceAll(" ", "")) || "null".equals(str.replaceAll(" ", ""))) ? false : true;
    }

    public static String len2HexStr(int i) {
        String int2HexStr;
        if (i > 127) {
            char c = (char) (i >> 8);
            int2HexStr = int2HexStr(c + 128) + int2HexStr((char) (i - (c << '\b')));
        } else {
            int2HexStr = int2HexStr(i);
        }
        return int2HexStr.toUpperCase();
    }

    public static String[] len2P1P2(int i) {
        String hexString = Integer.toHexString(i);
        int length = 4 - hexString.length();
        if (length < 0) {
            return null;
        }
        String str = hexString;
        for (int i2 = 0; i2 < length; i2++) {
            str = "0" + str;
        }
        return new String[]{str.substring(0, 2), str.substring(2)};
    }

    public static byte[] long2Bytes(long j) {
        byte[] bArr = new byte[8];
        int i = 0;
        while (i < 8) {
            int i2 = i + 1;
            bArr[i] = (byte) ((j >> (64 - (i2 * 8))) & 255);
            i = i2;
        }
        return bArr;
    }

    public static byte[] longToBytes(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((j >> (i * 8)) & 255);
        }
        return bArr;
    }

    public static ArrayList<String> parseDataLV(String str, boolean z) {
        if (str == null || "".equals(str)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        int length = str.length();
        int i = 0;
        if (z) {
            str.substring(0, 2);
            i = 2;
        }
        while (i < length) {
            int i2 = i + 2;
            int hexStr2Len = hexStr2Len(str.substring(i, i2));
            if (hexStr2Len != 0) {
                if (hexStr2Len >= 128) {
                    int i3 = i2 - 2;
                    i2 += 2;
                    hexStr2Len = hexStr2Len(str.substring(i3, i2));
                }
                i = (hexStr2Len * 2) + i2;
                if (i > length) {
                    return null;
                }
                if (z) {
                    arrayList.add(str.substring(i2, i));
                    i2 = i + 8;
                    arrayList.add(str.substring(i, i2));
                } else {
                    arrayList.add(str.substring(i2, i));
                }
            }
            i = i2;
        }
        return arrayList;
    }

    public static Map<String, String> parseDataTLVToMap(int i, String str) {
        int i2;
        if (str == null || "".equals(str)) {
            return null;
        }
        while (true) {
            i2 = 0;
            if (!str.endsWith("FF")) {
                break;
            }
            str = str.substring(0, str.length() - 2);
        }
        HashMap hashMap = new HashMap();
        int length = str.length();
        while (i2 < length) {
            int i3 = (i * 2) + i2;
            String substring = str.substring(i2, i3);
            int i4 = i3 + 2;
            int hexStr2Len = hexStr2Len(str.substring(i3, i4));
            if (hexStr2Len == 0) {
                i2 = i4;
            } else {
                if (hexStr2Len >= 128) {
                    int i5 = i4 - 2;
                    i4 += 2;
                    hexStr2Len = hexStr2Len(str.substring(i5, i4));
                }
                int i6 = (hexStr2Len * 2) + i4;
                if (i6 > length) {
                    return null;
                }
                hashMap.put(substring, str.substring(i4, i6));
                i2 = i6;
            }
        }
        return hashMap;
    }

    public static byte[] reverse(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            bArr2[bArr.length - i2] = bArr[i];
            i = i2;
        }
        return bArr2;
    }

    public static String showResult0xStr(byte[] bArr) {
        StringBuilder sb;
        String str;
        String sb2;
        String str2 = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b);
            int length = hexString.length();
            if (length > 2) {
                sb2 = "0x" + hexString.substring(length - 2);
            } else {
                if (length == 1) {
                    sb = new StringBuilder();
                    str = "0x0";
                } else {
                    sb = new StringBuilder();
                    str = "0x";
                }
                sb.append(str);
                sb.append(hexString);
                sb2 = sb.toString();
            }
            str2 = str2 + sb2 + " ";
        }
        return str2;
    }

    @SuppressLint({"DefaultLocale"})
    public static String showResult16Str(byte[] bArr) {
        String str;
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b);
            int length = hexString.length();
            if (length > 2) {
                hexString = hexString.substring(length - 2);
            } else if (length == 1) {
                str = "0" + hexString.toUpperCase();
                stringBuffer.append(str);
            }
            str = hexString.toUpperCase();
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static String showUninstallAPKSignatures(String str) {
        try {
            Class<?> cls = Class.forName("android.content.pm.PackageParser");
            Object newInstance = cls.getConstructor(String.class).newInstance(str);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Class<?> cls2 = Integer.TYPE;
            Object invoke = cls.getDeclaredMethod("parsePackage", File.class, String.class, DisplayMetrics.class, cls2).invoke(newInstance, new File(str), str, displayMetrics, 64);
            cls.getDeclaredMethod("collectCertificates", invoke.getClass(), cls2).invoke(newInstance, invoke, 64);
            return ((Signature[]) invoke.getClass().getDeclaredField("mSignatures").get(invoke))[0].toCharsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String string2HexStr(String str) {
        try {
            return showResult16Str(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String string2HexStr(String str, String str2) {
        try {
            return showResult16Str(str.getBytes(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static char[] stringToASCIIArray(String str) {
        return str == null ? new char[0] : str.toCharArray();
    }

    public static byte[] stringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    @SuppressLint({"DefaultLocale"})
    public static String toHexCode(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i3 = i; i3 < i + i2; i3++) {
            sb.append(String.format("%02X", Byte.valueOf(bArr[i3])).toUpperCase());
        }
        return sb.toString();
    }

    public static boolean verifyIP(String str) {
        String[] split;
        int length;
        if (str == null || "".equals(str) || (length = (split = str.split("\\.")).length) != 4) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            String str2 = split[i];
            if (!checkNum(str2) || str2.length() > 3 || str2.length() < 1) {
                return false;
            }
        }
        return true;
    }

    public static byte[] xor(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) ((bArr[i] ^ 255) & 255);
        }
        byte[] bArr3 = new byte[bArr.length * 2];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, length);
        return bArr3;
    }
}
