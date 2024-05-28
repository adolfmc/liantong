package cn.finalteam.toolsfinal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class DeviceUtils {
    public static final int NETWORK_CLASS_2_G = 2;
    public static final int NETWORK_CLASS_3_G = 3;
    public static final int NETWORK_CLASS_4_G = 4;
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    public static final int NETWORK_WIFI = 1;

    public static boolean existSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getLocalIPAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress()) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
            return "0.0.0.0";
        } catch (SocketException unused) {
            return "0.0.0.0";
        }
    }

    public static String getExternalStorageDirectory() {
        Map<String, String> map = System.getenv();
        String[] strArr = new String[map.values().size()];
        map.values().toArray(strArr);
        String str = strArr[strArr.length - 1];
        if (!str.startsWith("/mnt/") || Environment.getExternalStorageDirectory().getAbsolutePath().equals(str)) {
            return null;
        }
        return str;
    }

    public static long getAvailaleSize() {
        if (existSDCard()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        }
        return 0L;
    }

    public static long getAllSize() {
        if (existSDCard()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockCount() * statFs.getBlockSize();
        }
        return 0L;
    }

    public static boolean isOnline(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isServiceRunning(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((android.app.ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices.size() == 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isProessRunning(Context context, String str) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((android.app.ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String getIMEI(Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        return StringUtils.isEmpty(deviceId) ? "" : deviceId;
    }

    public static String getMac(Context context) {
        String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        return StringUtils.isEmpty(macAddress) ? "" : macAddress;
    }

    public static String getUDID(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (StringUtils.isEmpty(string) || string.equals("9774d56d682e549c") || string.length() < 15) {
            string = new BigInteger(64, new SecureRandom()).toString(16);
        }
        return StringUtils.isEmpty(string) ? "" : string;
    }

    public static void vibrate(Context context, long j) {
        ((Vibrator) context.getSystemService("vibrator")).vibrate(new long[]{0, j}, -1);
    }

    public static String getLatestCameraPicture(Context context) {
        if (existSDCard()) {
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "bucket_display_name", "datetaken", "mime_type"}, null, null, "datetaken DESC");
            if (query.moveToFirst()) {
                return query.getString(1);
            }
            return null;
        }
        return null;
    }

    public static DisplayMetrics getScreenPix(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    @TargetApi(11)
    public static void coptyToClipBoard(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 11) {
            ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str));
        } else {
            ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(str);
        }
    }

    public static List<String> getAppPackageNamelist(Context context) {
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            arrayList.add(packageInfo.packageName);
        }
        return arrayList;
    }

    public static boolean isAppInstall(Context context, String str) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        ArrayList arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                arrayList.add(installedPackages.get(i).packageName);
            }
        }
        return arrayList.contains(str);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public boolean isSoftKeyAvail(Activity activity) {
        final boolean[] zArr = {false};
        final View findViewById = activity.getWindow().getDecorView().findViewById(16908290);
        findViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: cn.finalteam.toolsfinal.DeviceUtils.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (findViewById.getRootView().getHeight() - findViewById.getHeight() > 100) {
                    zArr[0] = true;
                }
            }
        });
        return zArr[0];
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getTopBarHeight(Activity activity) {
        return activity.getWindow().findViewById(16908290).getTop();
    }

    @SuppressLint({"NewApi"})
    public static boolean startActivityForPackage(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(131072);
            intent.setPackage(packageInfo.packageName);
            ResolveInfo next = context.getPackageManager().queryIntentActivities(intent, 0).iterator().next();
            if (next != null) {
                String str2 = next.activityInfo.packageName;
                String str3 = next.activityInfo.name;
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setFlags(270532608);
                intent2.setComponent(new ComponentName(str2, str3));
                context.startActivity(intent2);
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void hideInputSoftFromWindowMethod(Context context, View view) {
        try {
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showInputSoftFromWindowMethod(Context context, View view) {
        try {
            ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isActiveSoftInput(Context context) {
        return ((InputMethodManager) context.getSystemService("input_method")).isActive();
    }

    public static void goHome(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(270532608);
        context.startActivity(intent);
    }

    public static int getPhoneType(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getPhoneType();
    }

    public static int getNetType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                return 1;
            }
            if (type == 0) {
                switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 2;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 3;
                    case 13:
                        return 4;
                    default:
                        return 0;
                }
            }
        }
        return 0;
    }

    public static void callPhone(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + str)));
    }

    public static void callDial(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    public static void sendSms(Context context, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("smsto:");
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        sb.append(str);
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(sb.toString()));
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        intent.putExtra("sms_body", str2);
        context.startActivity(intent);
    }

    public static boolean isPhone(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getPhoneType() != 0;
    }
}
