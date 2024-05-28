package com.baidu.cloud.plugin;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.cloud.util.FileTool;
import com.baidu.cloud.util.INotProguard;
import com.baidu.cloud.util.ReflectionUtils;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownSoConstant implements INotProguard {
    private static final String DIR_RTC_SO = "rtc";
    private static final String DOMIN = "https://rtc-so.cdn.bcebos.com";
    private static final String NAME_CONNECT = "_";
    private static final String NAME_PATH = "rtc";
    private static final String NAME_ZIP_SUFFIX = ".zip";
    private static String RTC_DOWNLOAD_URL = null;
    private static final String ZIP_LIBS_NAME = "jniLibs";
    public static final String ZIP_LIBS_RTC_SO_NAME = "libjingle_peerconnection_so.so";
    private static String sCPU_TYPE;

    public static String getDefaultCPUType() {
        return "armeabi-v7a";
    }

    public static void setRtcDownloadUrl(String str) {
        RTC_DOWNLOAD_URL = str;
    }

    public static String getRtcDownloadUrl() {
        RTC_DOWNLOAD_URL = "https://rtc-so.cdn.bcebos.com" + getRemoteRtcFileDir() + getRemoteParams();
        return RTC_DOWNLOAD_URL;
    }

    private static String getRemoteRtcFileDir() {
        return File.separator + "1.0" + File.separator + getRtcZipName();
    }

    private static String getRtcZipName() {
        return getCPUType() + "_rtc" + JtClient.UXUE_TEMP_FILE_SUFFIX;
    }

    private static String getRemoteParams() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String md5 = md5(getDownloadCdnKey() + getRemoteRtcFileDir() + currentTimeMillis);
        return "?md5hash=" + md5 + "&timestamp=" + currentTimeMillis;
    }

    private static String getDownloadCdnKey() {
        return ReflectionUtils.getSecrectKey();
    }

    public static void setDownCPUType(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getDefaultCPUType();
        }
        sCPU_TYPE = str;
    }

    public static String getCPUType() {
        if (TextUtils.isEmpty(sCPU_TYPE)) {
            return getDefaultCPUType();
        }
        return sCPU_TYPE;
    }

    public static String getRtcSoLocalFullPath(Context context) {
        if (TextUtils.isEmpty(RTC_DOWNLOAD_URL)) {
            getRtcDownloadUrl();
        }
        return getDownLocalPath(context, RTC_DOWNLOAD_URL) + File.separator + "jniLibs" + File.separator + getCPUType();
    }

    public static String getRtcSoFileLocalFullPath(Context context) {
        if (TextUtils.isEmpty(RTC_DOWNLOAD_URL)) {
            getRtcDownloadUrl();
        }
        return getDownLocalPath(context, RTC_DOWNLOAD_URL) + File.separator + "jniLibs" + File.separator + getCPUType() + File.separator + "libjingle_peerconnection_so.so";
    }

    public static String getDownLocalPath(Context context, String str) {
        return getDownLocalPath(context, str, getDefaultDownloadSoFoler(context));
    }

    public static String getDownLocalPath(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = getDefaultDownloadSoFoler(context);
        }
        return str2 + File.separator + getDownloadUrlMd5(str);
    }

    public static String getDefaultDownloadSoFoler(Context context) {
        return getFileDir(context, "rtc");
    }

    protected static String getFileDir(Context context, String str) {
        if (context == null) {
            return null;
        }
        String absolutePath = context.getFilesDir().getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        sb.append(absolutePath);
        sb.append(File.separator);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        sb.append(str);
        File file = new File(sb.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static boolean isSoDownloaded(Context context, String str) {
        return isSoDownloaded(context, str, getDefaultDownloadSoFoler(context));
    }

    public static boolean isSoDownloaded(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || context == null) {
            return false;
        }
        String downLocalPath = getDownLocalPath(context, str, str2);
        return FileTool.isExists(downLocalPath + File.separator + "jniLibs" + File.separator + getCPUType() + File.separator + "libjingle_peerconnection_so.so");
    }

    private static String getDownloadUrlMd5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains("?")) {
            str = str.substring(0, str.indexOf("?"));
        }
        return md5(str);
    }

    private static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance("MD5").digest(str.getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
