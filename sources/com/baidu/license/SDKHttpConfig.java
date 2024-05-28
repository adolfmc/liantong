package com.baidu.license;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SDKHttpConfig implements INotProguard {
    public static String HOST = "";
    public static final String PLATFORM = "Android";
    public static final int RESPONSE_EXPIRE = 5012;
    public static final int RESPONSE_SUCCESS = 3010;
    public static String appId = "";
    public static int arVersion = 0;
    public static String packageName = "";
    public static String sdkVersion = "";

    public static String getAppId() {
        return appId;
    }

    public static int getArVersion() {
        return arVersion;
    }

    public static String getFaceSignStr(long j) {
        return appId + "#" + j + "#" + sdkVersion + "#" + packageName + "#Android";
    }

    public static String getHOST() {
        return HOST;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static String getSdkVersion() {
        return sdkVersion;
    }

    public static String getSignStr() {
        return appId + "#" + sdkVersion + "#" + packageName + "#Android";
    }

    public static void init(Context context) {
        Throwable th;
        InputStream inputStream;
        if (context == null) {
            Log.e("Config", "配置信息加载失败 context is null!");
            return;
        }
        InputStream inputStream2 = null;
        try {
            try {
                try {
                    inputStream = context.getAssets().open("sdkcore.properties", 3);
                    try {
                        Properties properties = new Properties();
                        properties.load(inputStream);
                        HOST = properties.getProperty("host", null);
                    } catch (IOException e) {
                        inputStream2 = inputStream;
                        e = e;
                        Log.e("Config", "配置信息加载失败", e);
                        if (inputStream2 != null) {
                            inputStream2.close();
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                Log.e("Config", "配置文件关闭失败", e2);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    InputStream inputStream3 = inputStream2;
                    th = th3;
                    inputStream = inputStream3;
                }
            } catch (IOException e3) {
                e = e3;
            }
            if (HOST != null) {
                if (HOST.endsWith("/")) {
                    HOST = HOST.substring(0, HOST.length() - 1);
                }
                inputStream.close();
                inputStream.close();
                return;
            }
            throw new NullPointerException("host can not be null, please check sdkcore.properties");
        } catch (IOException e4) {
            Log.e("Config", "配置文件关闭失败", e4);
        }
    }

    public static void setAppId(String str) {
        appId = str;
    }

    public static void setArVersion(int i) {
        arVersion = i;
    }

    public static void setPackageName(String str) {
        packageName = str;
    }

    public static void setSdkVersion(String str) {
        sdkVersion = str;
    }
}
