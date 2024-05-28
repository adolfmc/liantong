package com.huawei.secure.android.common.encrypt.utils;

import android.annotation.SuppressLint;
import com.huawei.secure.android.common.encrypt.hash.AbstractC5104PBKDF2;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BaseKeyUtil {

    /* renamed from: a */
    private static final String f11964a = "BaseKeyUtil";

    /* renamed from: b */
    private static final int f11965b = 16;

    /* renamed from: c */
    private static final int f11966c = 16;

    /* renamed from: d */
    private static final int f11967d = 10000;

    /* renamed from: e */
    private static final int f11968e = 32;

    /* renamed from: f */
    private static final int f11969f = 1;

    /* renamed from: a */
    private static int m13964a(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    /* renamed from: a */
    private static boolean m13965a(int i) {
        return i >= 16;
    }

    /* renamed from: a */
    private static boolean m13963a(int i, byte[] bArr) {
        return m13965a(i) & m13962a(bArr);
    }

    public static String exportHexRootKey(String str, String str2, String str3, byte[] bArr, int i, boolean z) {
        return HexUtil.byteArray2HexStr(exportRootKey(str, str2, str3, bArr, i, z));
    }

    @SuppressLint({"NewApi"})
    public static byte[] exportRootKey(String str, String str2, String str3, byte[] bArr, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 16, z);
    }

    public static byte[] exportRootKey32(String str, String str2, String str3, byte[] bArr, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 32, z);
    }

    public static byte[] exportRootKey32Iteration1(String str, String str2, String str3, byte[] bArr, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 1, 32, z);
    }

    @SuppressLint({"NewApi"})
    public static byte[] exportRootKeyIteration1(String str, String str2, String str3, byte[] bArr, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 1, 16, z);
    }

    @SuppressLint({"NewApi"})
    public static byte[] exportRootKey(String str, String str2, String str3, byte[] bArr, int i, boolean z) {
        return exportRootKey(str, str2, str3, bArr, 10000, i, z);
    }

    /* renamed from: a */
    private static boolean m13962a(byte[] bArr) {
        return bArr.length >= 16;
    }

    public static byte[] exportRootKey(String str, String str2, String str3, byte[] bArr, int i, int i2, boolean z) {
        byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str);
        byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(str2);
        byte[] hexStr2ByteArray3 = HexUtil.hexStr2ByteArray(str3);
        int m13964a = m13964a(hexStr2ByteArray.length, hexStr2ByteArray2.length, hexStr2ByteArray3.length);
        if (m13963a(m13964a, bArr)) {
            char[] cArr = new char[m13964a];
            for (int i3 = 0; i3 < m13964a; i3++) {
                cArr[i3] = (char) ((hexStr2ByteArray[i3] ^ hexStr2ByteArray2[i3]) ^ hexStr2ByteArray3[i3]);
            }
            if (!z) {
                C5106b.m13941c(f11964a, "exportRootKey: sha1");
                return AbstractC5104PBKDF2.pbkdf2(cArr, bArr, i, i2 * 8);
            }
            C5106b.m13941c(f11964a, "exportRootKey: sha256");
            return AbstractC5104PBKDF2.pbkdf2SHA256(cArr, bArr, i, i2 * 8);
        }
        throw new IllegalArgumentException("key length must be more than 128bit.");
    }

    public static byte[] exportRootKey(String str, String str2, String str3, String str4, int i, boolean z) {
        return exportRootKey(str, str2, str3, HexUtil.hexStr2ByteArray(str4), i, z);
    }
}
