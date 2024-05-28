package com.gmrz.appsdk.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Base64;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"DefaultLocale"})
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Compatibility {
    private static final String TAG = Compatibility.class.getSimpleName() + "_fido";
    private static final String strBrand = Build.BRAND;
    private static final String strManufacturer = Build.MANUFACTURER;

    public static String AesEncrypt(String str) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(C0108a.f85c);
            keyGenerator.init(256);
            SecretKey generateKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, generateKey);
            return Base64.encodeToString(cipher.doFinal(str.getBytes()), 0);
        } catch (Exception unused) {
            Logger.m15757e(TAG, "aes encrypt is error!");
            return null;
        }
    }

    public static String getMac() {
        String readLine;
        String str = "";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address").getInputStream()));
            while (true) {
                if (lineNumberReader.readLine() == null) {
                    break;
                }
                str = str + readLine.trim();
            }
            lineNumberReader.close();
        } catch (IOException unused) {
            Logger.m15757e(TAG, "get mac is error!");
        }
        return str;
    }

    public static void getModel() {
        String str = Build.MODEL;
        String str2 = TAG;
        Logger.m15757e(str2, "model is " + str);
    }

    public static boolean isAndroidM() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isHuawei() {
        String str;
        String str2 = strBrand;
        if (str2 == null || (str = strManufacturer) == null) {
            return false;
        }
        return str2.compareToIgnoreCase("HUAWEI") == 0 || str.compareToIgnoreCase("HUAWEI") == 0;
    }

    public static boolean isK52() {
        String str = Build.MODEL;
        if (str == null || !str.toLowerCase().contains("gmrz k52")) {
            return false;
        }
        Logger.m15757e(TAG, str);
        return true;
    }

    public static boolean isP1() {
        String str = Build.MODEL;
        if (str == null || !str.toLowerCase().contains("gmrz p1")) {
            return false;
        }
        Logger.m15757e(TAG, str);
        return true;
    }

    public static boolean isSamsungDevice() {
        String str;
        String str2 = strBrand;
        if (str2 == null || (str = strManufacturer) == null) {
            return false;
        }
        return str2.compareToIgnoreCase("Samsung") == 0 || str.compareToIgnoreCase("Samsung") == 0;
    }

    public static boolean isZ1() {
        String str = Build.MODEL;
        if (str == null || !str.toLowerCase().contains("zuk z1")) {
            return false;
        }
        Logger.m15757e(TAG, str);
        return true;
    }
}
