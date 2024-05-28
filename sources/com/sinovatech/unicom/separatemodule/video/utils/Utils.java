package com.sinovatech.unicom.separatemodule.video.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class Utils {
    private static float sDensity;
    private static Method systemProperties_get;

    public static void sendText(Context context, String str, String str2, String str3, Intent intent) {
    }

    public boolean selfPermissionGranted(String str, Context context) {
        return true;
    }

    public static void backgroundAlpha(Activity activity, float f) {
        if (activity != null) {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            if (Build.VERSION.SDK_INT < 21) {
                attributes.alpha = 1.0f;
            } else {
                attributes.alpha = f;
            }
            activity.getWindow().setAttributes(attributes);
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            Log.w("Utility", "couldn't get connectivity manager");
        } else {
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo.isAvailable()) {
                        Log.d("Utility", "network is available");
                        return true;
                    }
                }
            }
        }
        Log.d("Utility", "network is not available");
        return false;
    }

    public static final void RemoveValue(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.remove(str);
        if (edit.commit()) {
            return;
        }
        Log.e("移除Shared", "save " + str + " failed");
    }

    private static final SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static final String getValue(Context context, String str) {
        return getSharedPreference(context).getString(str, "");
    }

    public static final Boolean getBooleanValue(Context context, String str) {
        return Boolean.valueOf(getSharedPreference(context).getBoolean(str, false));
    }

    public static final void putBooleanValue(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static final int getIntValue(Context context, String str) {
        return getSharedPreference(context).getInt(str, 0);
    }

    public static final long getLongValue(Context context, String str, long j) {
        return getSharedPreference(context).getLong(str, j);
    }

    public static final boolean putLongValue(Context context, String str, Long l) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putLong(str, l.longValue());
        return edit.commit();
    }

    public static final Boolean hasValue(Context context, String str) {
        return Boolean.valueOf(getSharedPreference(context).contains(str));
    }

    public static final boolean putValue(Context context, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(str, str2);
        return edit.commit();
    }

    public static final boolean putIntValue(Context context, String str, int i) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putInt(str, i);
        return edit.commit();
    }

    public static Date stringToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isEmail(String str) {
        return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(str).matches();
    }

    public static boolean isMobileNO(String str) {
        return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0-9]))\\d{8}$").matcher(str).matches();
    }

    public static boolean isNumber(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static String getVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getPointVersion(Context context) {
        return getVersion(context).split("-")[0];
    }

    public static String getFormatBersion(Context context) {
        return getVersion(context).replace(".", "").split("-")[0];
    }

    public static String getHasDotVersion(Context context) {
        return getVersion(context);
    }

    public static boolean isServiceWork(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(4000);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().toString().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCongruent(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        if (split == null || split2 == null || split.length != split2.length) {
            return true;
        }
        for (int i = 0; i < split.length; i++) {
            if (Integer.parseInt(split[i]) > Integer.parseInt(split2[i])) {
                return true;
            }
            if (Integer.parseInt(split[i]) < Integer.parseInt(split2[i])) {
                return false;
            }
        }
        return false;
    }

    public static boolean isValidUrl(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return isHttpUrl(str) || isHttpsUrl(str);
    }

    public static boolean isHttpUrl(String str) {
        return str != null && str.length() > 6 && str.substring(0, 7).equalsIgnoreCase("http://");
    }

    public static boolean isHttpsUrl(String str) {
        return str != null && str.length() > 7 && str.substring(0, 8).equalsIgnoreCase("https://");
    }

    public static int dipToPixel(Context context, float f) {
        if (sDensity == 0.0f) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            sDensity = displayMetrics.density;
        }
        return (int) (sDensity * f);
    }

    public static int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int pixelToDip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String md5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        }
    }

    public static String md5(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(bArr);
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getCurrentTime(String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date());
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }

    public static String removeDot(String str) {
        return String.valueOf((int) Float.parseFloat(str));
    }

    private boolean isMarshmallow(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName.equals("6.0");
    }

    public static String removeMark(String str) {
        int indexOf = str.indexOf("!");
        return indexOf != -1 ? str.substring(0, indexOf) : str;
    }

    public static byte[] File2byte(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] File2byte(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getMd5ByFile(File file) {
        FileInputStream fileInputStream;
        String str;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(map);
                    String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                    int length = 32 - bigInteger.length();
                    for (int i = 0; i < length; i++) {
                        bigInteger = "0" + bigInteger;
                    }
                    try {
                        fileInputStream.close();
                        return bigInteger;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return bigInteger;
                    }
                } catch (Exception e2) {
                    e = e2;
                    str = null;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return str;
                } catch (Throwable th) {
                    th = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
            }
        } catch (Exception e5) {
            e = e5;
            str = null;
        }
    }

    public static String getFileMD5(File file, long j) {
        RandomAccessFile randomAccessFile;
        String str;
        long length;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                if (j >= file.length()) {
                    j = file.length();
                    length = 0;
                } else {
                    length = file.length() - j;
                }
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    randomAccessFile.seek(length);
                    byte[] bArr = new byte[(int) j];
                    randomAccessFile.read(bArr);
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(bArr);
                    String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                    int length2 = 32 - bigInteger.length();
                    for (int i = 0; i < length2; i++) {
                        bigInteger = "0" + bigInteger;
                    }
                    try {
                        randomAccessFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return bigInteger;
                } catch (Exception e2) {
                    e = e2;
                    str = null;
                    randomAccessFile2 = randomAccessFile;
                    e.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                            return str;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            return str;
                        }
                    }
                    return str;
                } catch (Throwable th) {
                    th = th;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = randomAccessFile2;
            }
        } catch (Exception e5) {
            e = e5;
            str = null;
        }
    }

    public static String fileMD5(File file) throws IOException {
        FileInputStream fileInputStream;
        Throwable th;
        DigestInputStream digestInputStream;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            try {
                digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
                try {
                    while (digestInputStream.read(new byte[262144]) > 0) {
                    }
                    String byteArrayToHex = byteArrayToHex(digestInputStream.getMessageDigest().digest());
                    if (byteArrayToHex != null) {
                        int length = 32 - byteArrayToHex.length();
                        for (int i = 0; i < length; i++) {
                            byteArrayToHex = "0" + byteArrayToHex;
                        }
                    }
                    try {
                        digestInputStream.close();
                    } catch (Exception unused) {
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception unused2) {
                    }
                    return byteArrayToHex;
                } catch (NoSuchAlgorithmException unused3) {
                    try {
                        digestInputStream.close();
                    } catch (Exception unused4) {
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception unused5) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        digestInputStream.close();
                    } catch (Exception unused6) {
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception unused7) {
                    }
                    throw th;
                }
            } catch (NoSuchAlgorithmException unused8) {
                digestInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                digestInputStream = null;
            }
        } catch (NoSuchAlgorithmException unused9) {
            digestInputStream = null;
            fileInputStream = null;
        } catch (Throwable th4) {
            fileInputStream = null;
            th = th4;
            digestInputStream = null;
        }
    }

    public static String byteArrayToHex(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b >>> 4) & 15];
            i = i2 + 1;
            cArr2[i2] = cArr[b & 15];
        }
        return new String(cArr2);
    }

    public static String getDisplay() {
        return Build.DISPLAY;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getDeviceId() {
        return Build.ID;
    }

    public static String getUniquePsuedoID() {
        String str = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        try {
            return new UUID(str.hashCode(), Build.class.getField("SERIAL").get(null).toString().hashCode()).toString();
        } catch (Exception unused) {
            return new UUID(str.hashCode(), "serial".hashCode()).toString();
        }
    }

    public static String filterStrBlank(String str) {
        return str != null ? Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("") : "";
    }

    public static String formatNumber(long j) {
        if (j < 10000) {
            return j + "";
        }
        return new DecimalFormat("######0.0").format(j / 10000.0d) + "万";
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String formatTimeStr(long j) {
        return new SimpleDateFormat("yyyy.MM.dd").format(Long.valueOf(j));
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String formatTimeDetailStr(long j) {
        return new SimpleDateFormat("yyyy.MM.dd hh:mm").format(Long.valueOf(j));
    }

    public static String getWifiMac(Context context) {
        String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        return macAddress == null ? "" : macAddress;
    }

    public static String getOSSDK() {
        return Build.VERSION.SDK;
    }

    public static String getOSRelease() {
        return Build.VERSION.RELEASE;
    }

    public static String getDeviceName() {
        return Build.MODEL;
    }

    public static String getDeviceFactory() {
        return Build.BRAND;
    }

    public static int getDeviceWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getDeviceHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getDeviceDpi(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    public static float getDeviceDensity(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public static String getDeviceUUID(Context context) {
        try {
            return UUID.nameUUIDFromBytes(getDeviceAndroidId(context).getBytes("utf8")).toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getDeviceAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getDeviceIMEI(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getAndroidOsSystemProperties(String str) {
        try {
            systemProperties_get = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            String str2 = (String) systemProperties_get.invoke(null, str);
            return str2 != null ? str2 : "";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getSerialNo() {
        String[] strArr = {"ro.boot.serialno", "ro.serialno"};
        String str = "";
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str2 = strArr[i];
            String androidOsSystemProperties = getAndroidOsSystemProperties(str2);
            Log.e("", "get " + str2 + " : " + androidOsSystemProperties);
            i++;
            str = androidOsSystemProperties;
        }
        return str;
    }
}
