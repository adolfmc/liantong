package com.huawei.secure.android.common.encrypt.utils;

import android.annotation.SuppressLint;
import android.os.Build;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RootKeyUtil {

    /* renamed from: b */
    private static final String f11976b = "RootKeyUtil";

    /* renamed from: a */
    private byte[] f11977a = null;

    private RootKeyUtil() {
    }

    /* renamed from: a */
    private void m13959a(String str, String str2, String str3, String str4) {
        m13958a(str, str2, str3, HexUtil.hexStr2ByteArray(str4));
    }

    public static RootKeyUtil newInstance(String str, String str2, String str3, String str4) {
        RootKeyUtil rootKeyUtil = new RootKeyUtil();
        rootKeyUtil.m13959a(str, str2, str3, str4);
        return rootKeyUtil;
    }

    public byte[] getRootKey() {
        return (byte[]) this.f11977a.clone();
    }

    public String getRootKeyHex() {
        return HexUtil.byteArray2HexStr(this.f11977a);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private void m13958a(String str, String str2, String str3, byte[] bArr) {
        if (Build.VERSION.SDK_INT < 26) {
            C5106b.m13941c(f11976b, "initRootKey: sha1");
            this.f11977a = BaseKeyUtil.exportRootKey(str, str2, str3, bArr, false);
            return;
        }
        C5106b.m13941c(f11976b, "initRootKey: sha256");
        this.f11977a = BaseKeyUtil.exportRootKey(str, str2, str3, bArr, true);
    }

    public static RootKeyUtil newInstance(String str, String str2, String str3, byte[] bArr) {
        RootKeyUtil rootKeyUtil = new RootKeyUtil();
        rootKeyUtil.m13958a(str, str2, str3, bArr);
        return rootKeyUtil;
    }
}
