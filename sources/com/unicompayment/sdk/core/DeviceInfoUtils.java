package com.unicompayment.sdk.core;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import cn.ltzf.passguard.C1730a;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import p482w.C14261e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DeviceInfoUtils {
    private static final int BUFFER = 1024;
    private static final int FILTER_APPLIST = 1;
    private static final int FILTER_LINKED_FILES = 4;
    private static final int FILTER_SENSORS = 8;
    private static final int FILTER_WEBINFO = 2;
    private static final int REFRESH_APPLIST = 16;
    public static final List<String> SENSENTIVE_PERMISSIONS;
    private static final String TAG = "unicompay_DevInfo";
    private static String _cacheLinkedFiles = null;
    private static int batteryL = -1;
    private static double batteryT = 0.0d;
    private static int batteryV = 0;
    private static String bt_mac = null;
    public static LocationManager locManager = null;
    private static List<AppItem> mAppList = null;
    private static String mAuthenticatorId = null;
    private static Context mCtx = null;
    private static int mFilter = 0;
    public static Location mLastLocation = null;
    private static String mUserAgent = "";
    private static int mVersion = 7;
    private static WebView mWebview = null;
    private static long runCount = 0;
    private static TelephonyManager telephonyManager = null;
    private static String webinfo = "{}";
    private static WifiManager wifi;
    private static String wifi_ip;
    public static long appStartTime = SystemClock.elapsedRealtime();
    public static boolean bWebviewStopped = false;
    private static boolean bBatteryReceiverReg = false;
    private static BroadcastReceiver batteryReceiver = new BroadcastReceiver() { // from class: com.unicompayment.sdk.core.DeviceInfoUtils.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int unused = DeviceInfoUtils.batteryL = intent.getIntExtra("level", 0);
            int unused2 = DeviceInfoUtils.batteryV = intent.getIntExtra("voltage", 0);
            double unused3 = DeviceInfoUtils.batteryT = intent.getIntExtra("temperature", 0) * 0.1d;
        }
    };
    public static long lastLocUpdateTime = 0;
    private static String cell_id = "{}";
    private static String boot_id = null;
    private static String htmlData = "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";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class AppItem {
        public String cert;
        public String cert_sha;
        public String is_sysapp;
        public String is_virus;
        public String md5;
        public String name;
        public String pkg_name;
        public List<String> sensitive_permissions;
        public String version;
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class JavascriptObjectInterface {
        @JavascriptInterface
        public void report(String str) {
            String unused = DeviceInfoUtils.webinfo = str;
            StringBuilder sb = new StringBuilder();
            sb.append("JS报告延迟：");
            sb.append(SystemClock.elapsedRealtime() - DeviceInfoUtils.appStartTime);
            sb.append(", 长度：");
            sb.append(str == null ? 0 : str.length());
            MyLog.m6021w("unicompay_DevInfo", sb.toString());
            new MyHandler(DeviceInfoUtils.mCtx.getMainLooper()).sendEmptyMessage(-1);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                DeviceInfoUtils.stopWebView();
                return;
            }
            StringBuilder m22016a = C1730a.m22016a("MyHandler 收到消息, mFilter=");
            m22016a.append(DeviceInfoUtils.mFilter);
            MyLog.m6021w("unicompay_DevInfo", m22016a.toString());
            try {
                if (DeviceInfoUtils.mWebview == null) {
                    WebView unused = DeviceInfoUtils.mWebview = new WebView(DeviceInfoUtils.mCtx.getApplicationContext());
                    String unused2 = DeviceInfoUtils.mUserAgent = DeviceInfoUtils.mWebview.getSettings().getUserAgentString().replaceAll("\"", "");
                    MyLog.m6021w("unicompay_DevInfo", "mUserAgent: " + DeviceInfoUtils.mUserAgent);
                    if ((DeviceInfoUtils.mFilter & 2) == 0) {
                        MyLog.m6021w("unicompay_DevInfo", "MyHandler 准备获取webview信息");
                        DeviceInfoUtils.mWebview.getSettings().setJavaScriptEnabled(true);
                        WebView webView = DeviceInfoUtils.mWebview;
                        NBSWebViewClient nBSWebViewClient = new NBSWebViewClient() { // from class: com.unicompayment.sdk.core.DeviceInfoUtils.MyHandler.1
                            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
                            public void onPageFinished(WebView webView2, String str) {
                                Tracker.onPageFinished(this, webView2, str);
                                super.onPageFinished(webView2, str);
                            }

                            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
                            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                                Tracker.onPageStarted(this, webView2, str, bitmap);
                                super.onPageStarted(webView2, str, bitmap);
                            }

                            @Override // android.webkit.WebViewClient
                            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                                if (webView2 instanceof Object) {
                                    NBSWebLoadInstrument.loadUrl((Object) webView2, str);
                                    return true;
                                }
                                webView2.loadUrl(str);
                                return true;
                            }
                        };
                        if (webView instanceof WebView) {
                            NBSWebLoadInstrument.setWebViewClient(webView, nBSWebViewClient);
                        } else {
                            webView.setWebViewClient(nBSWebViewClient);
                        }
                        DeviceInfoUtils.mWebview.addJavascriptInterface(new JavascriptObjectInterface(), "wvObj");
                        String str = new String(DeviceInfoUtils.decompress(Base64.decode(DeviceInfoUtils.htmlData, 2)));
                        WebView webView2 = DeviceInfoUtils.mWebview;
                        if (webView2 instanceof Object) {
                            NBSWebLoadInstrument.loadDataWithBaseURL((Object) webView2, "file:///android_asset/", str, (String) null, "UTF-8", (String) null);
                        } else {
                            webView2.loadDataWithBaseURL("file:///android_asset/", str, null, "UTF-8", null);
                        }
                        MyLog.m6021w("unicompay_DevInfo", "已经启动html>>>>>");
                        new Thread(new Runnable() { // from class: com.unicompayment.sdk.core.DeviceInfoUtils.MyHandler.2
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    SystemClock.sleep(15000L);
                                    new MyHandler(DeviceInfoUtils.mCtx.getMainLooper()).sendEmptyMessage(-1);
                                } catch (Throwable th) {
                                    MyLog.m6020w("unicompay_DevInfo", "webview", th);
                                }
                            }
                        }).start();
                    }
                }
            } catch (Exception e) {
                try {
                    MyLog.m6020w("unicompay_DevInfo", "get web info", e);
                } catch (Exception e2) {
                    MyLog.m6020w("unicompay_DevInfo", "", e2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class MyLog {
        /* renamed from: i */
        public static void m6022i(String str, String str2) {
        }

        /* renamed from: w */
        public static void m6021w(String str, String str2) {
        }

        /* renamed from: w */
        public static void m6020w(String str, String str2, Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class SortByPackageName implements Comparator<Object> {
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            AppItem appItem = (AppItem) obj2;
            String str = ((AppItem) obj).pkg_name;
            if (str == null) {
                return -1;
            }
            return str.compareTo(appItem.pkg_name);
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        SENSENTIVE_PERMISSIONS = arrayList;
        arrayList.add("android.permission.RECORD_AUDIO");
        arrayList.add("android.permission.CALL_PHONE");
        arrayList.add("android.permission.MODIFY_PHONE_STATE");
        arrayList.add("android.permission.READ_PRIVILEGED_PHONE_STATE");
        arrayList.add("android.permission.WRITE_CALL_LOG");
    }

    private static void decompress(InputStream inputStream, OutputStream outputStream) {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = gZIPInputStream.read(bArr, 0, 1024);
            if (read == -1) {
                gZIPInputStream.close();
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static byte[] decompress(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decompress(byteArrayInputStream, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byteArrayInputStream.close();
        return byteArray;
    }

    private static String getAndroidID() {
        Context context = mCtx;
        String string = context != null ? Settings.Secure.getString(context.getContentResolver(), "android_id") : null;
        return string == null ? "" : string;
    }

    public static String getApkSign(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            return signatureArr != null ? get_sha1(signatureArr[0].toByteArray()) : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getAppInstallTime() {
        try {
            return mCtx.getPackageManager().getPackageInfo(mCtx.getPackageName(), 0).firstInstallTime + "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getAppName() {
        String str;
        try {
            str = mCtx.getPackageManager().getPackageInfo(mCtx.getPackageName(), 0).applicationInfo.loadLabel(mCtx.getPackageManager()).toString();
        } catch (Exception e) {
            MyLog.m6020w("unicompay_DevInfo", "", e);
            str = null;
        }
        return str == null ? "" : str;
    }

    private static String getAppSignInfo() {
        String str = "";
        try {
            Signature[] signatureArr = mCtx.getPackageManager().getPackageInfo(mCtx.getPackageName(), 64).signatures;
            if (signatureArr != null) {
                str = "";
                for (Signature signature : signatureArr) {
                    str = str + get_sha1(signature.toByteArray()) + ";;";
                }
            }
        } catch (Exception unused) {
        }
        return str;
    }

    private static String getAppVersionCode() {
        try {
            return mCtx.getPackageManager().getPackageInfo(mCtx.getPackageName(), 0).versionCode + "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getAppVersionName() {
        try {
            return mCtx.getPackageManager().getPackageInfo(mCtx.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    private static synchronized List<AppItem> getApps(boolean z) {
        List<PackageInfo> arrayList;
        List<AppItem> list;
        synchronized (DeviceInfoUtils.class) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (mAppList == null || !z) {
                try {
                    arrayList = mCtx.getPackageManager().getInstalledPackages(20544);
                } catch (Exception unused) {
                    arrayList = new ArrayList<>();
                }
                try {
                    List<AppItem> list2 = mAppList;
                    if (list2 != null) {
                        list2.clear();
                    } else {
                        mAppList = new ArrayList();
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (PackageInfo packageInfo : arrayList) {
                        String[] strArr = packageInfo.requestedPermissions;
                        ArrayList arrayList3 = new ArrayList();
                        boolean z2 = true;
                        if (strArr != null && strArr.length > 0) {
                            for (int i = 0; i < strArr.length; i++) {
                                if (SENSENTIVE_PERMISSIONS.contains(strArr[i])) {
                                    arrayList3.add(strArr[i]);
                                }
                            }
                        }
                        if ((packageInfo.applicationInfo.flags & 1) == 0) {
                            AppItem appItem = new AppItem();
                            appItem.name = packageInfo.applicationInfo.loadLabel(mCtx.getPackageManager()).toString();
                            appItem.pkg_name = packageInfo.packageName;
                            appItem.version = packageInfo.versionCode + "";
                            StringBuilder sb = new StringBuilder();
                            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                                z2 = false;
                            }
                            sb.append(z2);
                            sb.append("");
                            appItem.is_sysapp = sb.toString();
                            Signature[] signatureArr = packageInfo.signatures;
                            if (signatureArr != null && signatureArr.length > 0) {
                                appItem.cert = "";
                                String str = get_sha1(signatureArr[0].toByteArray());
                                appItem.cert_sha = str;
                                if (arrayList2.contains(str)) {
                                    appItem.cert = "";
                                } else {
                                    arrayList2.add(appItem.cert_sha);
                                }
                            }
                            appItem.sensitive_permissions = arrayList3;
                            MyLog.m6021w("xdsd_appinfo", appItem.is_sysapp + "," + appItem.name + "," + appItem.pkg_name + "," + appItem.version + "," + appItem.md5);
                            mAppList.add(appItem);
                        }
                    }
                    Collections.sort(mAppList, new SortByPackageName());
                } catch (Exception unused2) {
                    MyLog.m6022i("unicompay_DevInfo", "getApps exception");
                }
            }
            StringBuilder m22016a = C1730a.m22016a("xdsd_appinfo sse_delay获取应用列表时间：");
            m22016a.append(SystemClock.elapsedRealtime() - elapsedRealtime);
            MyLog.m6021w("unicompay_DevInfo", m22016a.toString());
            list = mAppList;
        }
        return list;
    }

    private static String getBaseband(Context context) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
            return invoke != null ? invoke.toString().replaceAll("\"", "") : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getBootId() {
        String str = boot_id;
        if (str != null) {
            return str;
        }
        String readSmallFile = readSmallFile("/proc/sys/kernel/random/boot_id", 36);
        boot_id = (readSmallFile == null || readSmallFile.trim().isEmpty()) ? null : readSmallFile.trim().split("\n")[0];
        return boot_id;
    }

    public static String getBtMac() {
        String str = bt_mac;
        if (str != null) {
            return str;
        }
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                String address = defaultAdapter.getAddress();
                bt_mac = address;
                if (address == null && !defaultAdapter.isEnabled()) {
                    defaultAdapter.enable();
                    for (int i = 0; i < 20; i++) {
                        String address2 = defaultAdapter.getAddress();
                        bt_mac = address2;
                        if (address2 != null) {
                            break;
                        }
                        try {
                            Thread.sleep(500L);
                        } catch (Exception unused) {
                        }
                    }
                    defaultAdapter.disable();
                }
            }
        } catch (Exception unused2) {
            bt_mac = null;
        }
        if (bt_mac == null) {
            bt_mac = "";
        }
        return bt_mac;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0171 A[Catch: Exception -> 0x042b, TryCatch #0 {Exception -> 0x042b, blocks: (B:6:0x0007, B:7:0x0060, B:45:0x03a5, B:11:0x006a, B:13:0x0079, B:14:0x00c4, B:20:0x0164, B:21:0x016b, B:23:0x0171, B:24:0x017b, B:26:0x0181, B:28:0x0193, B:29:0x01e4, B:39:0x0339, B:17:0x00ce, B:19:0x00dd, B:30:0x01eb, B:32:0x01fa, B:33:0x0281, B:35:0x0290, B:36:0x02dd, B:38:0x02ec, B:46:0x03a8, B:48:0x03b0, B:50:0x03b7, B:51:0x03f2), top: B:57:0x0007 }] */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getCellInfo() {
        /*
            Method dump skipped, instructions count: 1140
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicompayment.sdk.core.DeviceInfoUtils.getCellInfo():java.lang.String");
    }

    private static int getCpuCount() {
        try {
            return Runtime.getRuntime().availableProcessors();
        } catch (Exception e) {
            MyLog.m6020w("unicompay_DevInfo", "get cpu exception", e);
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getCpuMaxFreq() {
        /*
            java.lang.String r0 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r1 = 32
            java.lang.String r0 = readSmallFile(r0, r1)     // Catch: java.lang.Exception -> L23
            if (r0 == 0) goto L2b
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Exception -> L23
            java.lang.String r1 = "[0-9]+"
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)     // Catch: java.lang.Exception -> L23
            java.util.regex.Matcher r0 = r1.matcher(r0)     // Catch: java.lang.Exception -> L23
            boolean r1 = r0.find()     // Catch: java.lang.Exception -> L23
            if (r1 == 0) goto L2b
            java.lang.String r0 = r0.group()     // Catch: java.lang.Exception -> L23
            goto L2c
        L23:
            java.lang.String r0 = "unicompay_DevInfo"
            java.lang.String r1 = "get cpuinfo_max_freq exception"
            com.unicompayment.sdk.core.DeviceInfoUtils.MyLog.m6021w(r0, r1)
        L2b:
            r0 = 0
        L2c:
            if (r0 != 0) goto L30
            java.lang.String r0 = ""
        L30:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicompayment.sdk.core.DeviceInfoUtils.getCpuMaxFreq():java.lang.String");
    }

    private static String getCpuName() {
        String str = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    if (readLine.startsWith("Hardware\t:")) {
                        str = readLine.substring(11);
                        break;
                    }
                } else {
                    break;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            MyLog.m6020w("unicompay_DevInfo", "get cpu exception", e);
        }
        return str;
    }

    private static Location getCurrentLocation() {
        try {
            if (locManager == null) {
                locManager = (LocationManager) mCtx.getSystemService("location");
            }
            LocationManager locationManager = locManager;
            if (locationManager != null) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("network");
                if (lastKnownLocation == null) {
                    lastKnownLocation = locManager.getLastKnownLocation("gps");
                }
                if (lastKnownLocation != null) {
                    mLastLocation = lastKnownLocation;
                    lastLocUpdateTime = SystemClock.elapsedRealtime();
                }
            }
        } catch (Exception unused) {
        }
        StringBuilder m22016a = C1730a.m22016a("xdkj_loc 当前位置：");
        m22016a.append(mLastLocation);
        MyLog.m6021w("unicompay_DevInfo", m22016a.toString());
        return mLastLocation;
    }

    public static String getDeviceInfo(Context context, int i) {
        String str;
        if (context == null) {
            return null;
        }
        mFilter = i;
        try {
            mCtx = context.getApplicationContext();
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (!bBatteryReceiverReg) {
                mCtx.registerReceiver(batteryReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                bBatteryReceiverReg = true;
            }
            try {
                wifi = (WifiManager) context.getSystemService("wifi");
                new MyHandler(mCtx.getMainLooper()).sendEmptyMessage(0);
            } catch (Exception unused) {
            }
            long j = runCount;
            if (j < 9223372036854775803L) {
                if (j < 0) {
                    runCount = 0L;
                }
                runCount++;
            }
            StringBuilder sb = new StringBuilder(65536);
            sb.append("{");
            StringBuilder m22016a = C1730a.m22016a("\"version\":");
            m22016a.append(mVersion);
            m22016a.append(",");
            sb.append(m22016a.toString());
            sb.append("\"static_info\":{");
            sb.append("\"imei\":\"");
            StringBuilder m6015a = C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(sb, getIMEI(context), "\",", sb, "\"imsi\":\""), getIMSI(context), "\",", sb, "\"baseband\":\""), getBaseband(context), "\",", sb, "\"op\":\""), getProvidersName(), "\",", sb, "\"msisdn\":\""), getNativePhoneNumber(context), "\",", sb, "\"android_id\":\""), getAndroidID(), "\",", sb, "\"is_root\":\"");
            m6015a.append(isRooted());
            m6015a.append("\",");
            sb.append("\"model\":\"");
            String str2 = Build.MODEL;
            StringBuilder m6015a2 = C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(sb, str2, "\",", sb, "\"board\":\""), Build.BOARD, "\",", sb, "\"bootloader\":\""), Build.BOOTLOADER, "\",", sb, "\"brand\":\""), Build.BRAND, "\",", sb, "\"cpu_abi\":\""), Build.CPU_ABI, "\",", sb, "\"cpu_abi2\":\""), Build.CPU_ABI2, "\",", sb, "\"cpu_cnt\":\"");
            m6015a2.append(getCpuCount());
            m6015a2.append("\",");
            sb.append("\"cpu_maxf\":\"");
            StringBuilder m6015a3 = C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(sb, getCpuMaxFreq(), "\",", sb, "\"cpu_name\":\""), getCpuName(), "\",", sb, "\"device\":\""), Build.DEVICE, "\",", sb, "\"devname\":\"");
            StringBuilder sb2 = new StringBuilder();
            String str3 = Build.MANUFACTURER;
            sb2.append(str3);
            sb2.append(" ");
            sb2.append(str2);
            m6015a3.append(sb2.toString());
            m6015a3.append("\",");
            sb.append("\"display\":\"");
            String str4 = Build.DISPLAY;
            StringBuilder m6015a4 = C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(sb, str4, "\",", sb, "\"os_name\":\""), str4, "\",", sb, "\"fingerprint\":\""), Build.FINGERPRINT, "\",", sb, "\"hardware\":\""), Build.HARDWARE, "\",", sb, "\"host\":\""), Build.HOST, "\",", sb, "\"id\":\""), Build.ID, "\",", sb, "\"manufacture\":\""), str3, "\",", sb, "\"product\":\""), Build.PRODUCT, "\",", sb, "\"radio\":\""), Build.RADIO, "\",", sb, "\"serial\":\""), Build.SERIAL, "\",", sb, "\"tags\":\""), Build.TAGS, "\",", sb, "\"time\":\"");
            m6015a4.append(Build.TIME);
            m6015a4.append("\",");
            sb.append("\"type\":\"");
            StringBuilder m6015a5 = C10734a.m6015a(C10734a.m6015a(sb, Build.TYPE, "\",", sb, "\"user\":\""), Build.USER, "\",", sb, "\"sdk_version\":\"");
            m6015a5.append(Build.VERSION.SDK_INT);
            m6015a5.append("\",");
            sb.append("\"os_version\":\"");
            StringBuilder m6015a6 = C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(sb, Build.VERSION.RELEASE, "\",", sb, "\"resolution\":\""), getResolution(), "\",", sb, "\"runtime\":\""), getRuntime(), "\",", sb, "\"bt_mac\":\"");
            m6015a6.append(getBtMac());
            m6015a6.append("\",");
            String fingerprintAuthenticatorId = getFingerprintAuthenticatorId();
            mAuthenticatorId = fingerprintAuthenticatorId;
            if (fingerprintAuthenticatorId != null) {
                sb.append("\"fpid\":\"");
                sb.append(mAuthenticatorId);
                sb.append("\",");
            }
            sb.append("\"bBatteryReceiverReg\":\"");
            sb.append(bBatteryReceiverReg);
            sb.append("\",");
            sb.append("\"batteryL\":\"");
            sb.append(batteryL);
            sb.append("\",");
            sb.append("\"batteryV\":\"");
            sb.append(batteryV);
            sb.append("\",");
            sb.append("\"batteryT\":\"");
            sb.append(batteryT);
            sb.append("\",");
            sb.append("\"inmem\":\"");
            sb.append(getInternalMemorySize());
            sb.append("\",");
            sb.append("\"sdmem\":\"");
            sb.append(getSDCardMemorySize());
            sb.append("\",");
            sb.append("\"ua\":\"");
            sb.append(mUserAgent);
            sb.append("\"");
            sb.append("},");
            sb.append("\"dynamic_info\":{");
            sb.append("\"cell_info\":");
            StringBuilder m6015a7 = C10734a.m6015a(sb, getCellInfo(), ",", sb, "\"is_fg\":");
            m6015a7.append(isAppOnForeground());
            m6015a7.append(",");
            sb.append("\"is_vpn\":");
            sb.append(isVpnConnected());
            sb.append(",");
            sb.append("\"wifi_ap\":\"");
            StringBuilder m6015a8 = C10734a.m6015a(C10734a.m6015a(sb, getWifiBssid(), "\",", sb, "\"wifi_ip\":\""), getWifiIp(), "\",", sb, "\"loc_lat\":\"");
            m6015a8.append(getLat());
            m6015a8.append("\",");
            sb.append("\"loc_lng\":\"");
            sb.append(getLng());
            sb.append("\",");
            sb.append("\"headset\":\"");
            StringBuilder m6015a9 = C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(sb, getHeadsetStatus(), "\",", sb, "\"wifi_nearby\":"), getWifiScanResult(), ",", sb, "\"linker_addr\":\""), getLinkerMappedAddress(), "\",", sb, "\"boot_id\":\"");
            m6015a9.append(getBootId());
            m6015a9.append("\"");
            sb.append("},");
            Map<String, Integer> mtu = getMTU();
            if (mtu != null && !mtu.isEmpty()) {
                sb.append("\"mtu_info\":{");
                for (String str5 : mtu.keySet()) {
                    sb.append("\"" + str5 + "\":");
                    sb.append(mtu.get(str5));
                    sb.append(",");
                }
                sb.append("\"lo\":0},");
            }
            sb.append("\"apps_info\":{");
            sb.append("\"pkg_name\":\"");
            sb.append(mCtx.getPackageName());
            sb.append("\",");
            sb.append("\"name\":\"");
            StringBuilder m6015a10 = C10734a.m6015a(C10734a.m6015a(C10734a.m6015a(sb, getAppName(), "\",", sb, "\"inst_time\":\""), getAppInstallTime(), "\",", sb, "\"version\":\""), getAppVersionName(), "\",", sb, "\"version_code\":\"");
            m6015a10.append(getAppVersionCode());
            m6015a10.append("\",");
            if ((i & 1) == 0) {
                StringBuilder sb3 = new StringBuilder(65536);
                List<AppItem> apps = getApps((i & 16) == 0);
                if (apps == null || apps.isEmpty()) {
                    str = "\"apps_list\":[], ";
                } else {
                    sb3.append("\"apps_list\":[");
                    for (AppItem appItem : apps) {
                        sb3.append("{");
                        if (appItem.cert_sha != null) {
                            sb3.append("\"cert_sha\":\"");
                            sb3.append(appItem.cert_sha);
                            sb3.append("\",");
                        }
                        if (appItem.is_sysapp != null) {
                            sb3.append("\"is_sysapp\":\"");
                            sb3.append(appItem.is_sysapp);
                            sb3.append("\",");
                        }
                        if (appItem.is_virus != null) {
                            sb3.append("\"is_virus\":\"");
                            sb3.append(appItem.is_virus);
                            sb3.append("\",");
                        }
                        if (appItem.sensitive_permissions != null) {
                            sb3.append("\"sensitive_permissions\":[");
                            for (String str6 : appItem.sensitive_permissions) {
                                sb3.append("\"");
                                sb3.append(str6);
                                sb3.append("\",");
                            }
                            sb3.append("\"\"],");
                        }
                        sb3.append("\"ver\":\"");
                        StringBuilder m6015a11 = C10734a.m6015a(C10734a.m6015a(sb3, appItem.version, "\",", sb3, "\"md5\":\""), appItem.md5, "\",", sb3, "\"pkg_name\":\"");
                        m6015a11.append(appItem.pkg_name);
                        m6015a11.append("\"");
                        sb3.append("},");
                    }
                    str = "{}],";
                }
                sb3.append(str);
                String str7 = get_sha1(sb3.toString().getBytes());
                sb.append((CharSequence) sb3);
                StringBuilder m22016a2 = C1730a.m22016a("sse_delay 应用列表长度：");
                m22016a2.append(sb3.length());
                MyLog.m6021w("unicompay_DevInfo", m22016a2.toString());
                sb.append("\"apps_list_digest\":\"");
                sb.append(str7);
                sb.append("\",");
            }
            sb.append("\"cert_info\":\"");
            sb.append(getAppSignInfo());
            sb.append("\"");
            sb.append("},");
            if ((i & 2) == 0) {
                String str8 = webinfo;
                if (str8 == null || str8.isEmpty()) {
                    webinfo = "{}";
                }
                sb.append("\"web\":");
                sb.append(webinfo);
                sb.append(",");
                MyLog.m6021w("unicompay_DevInfo", "webview信息长度：" + webinfo.length());
            }
            if ((i & 4) == 0) {
                sb.append("\"links\":\"");
                sb.append(getLinkedFiles());
                sb.append("\",");
            }
            if ((i & 8) == 0) {
                sb.append("\"sensors\":");
                sb.append(getSensors());
                sb.append(",");
            }
            sb.append("\"os_type\":\"android\"");
            sb.append("}");
            return sb.toString();
        } catch (Exception e) {
            MyLog.m6020w("unicompay_DevInfo", "", e);
            return "{}";
        }
    }

    private static String getFingerprintAuthenticatorId() {
        String str;
        Object systemService;
        try {
            systemService = mCtx.getSystemService("fingerprint");
        } catch (Throwable th) {
            MyLog.m6020w("unicompay_DevInfo", "获取getAuthenticatorId失败！", th);
        }
        if (systemService != null) {
            str = String.format("%X", Long.valueOf(((Long) Class.forName("android.hardware.fingerprint.FingerprintManager").getDeclaredMethod("getAuthenticatorId", new Class[0]).invoke(systemService, new Object[0])).longValue()));
            MyLog.m6021w("unicompay_DevInfo", C14261e.m22a("getAuthenticatorId: ", str));
            return str;
        }
        str = null;
        MyLog.m6021w("unicompay_DevInfo", C14261e.m22a("getAuthenticatorId: ", str));
        return str;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [boolean, int] */
    private static String getHeadsetStatus() {
        try {
            String readSmallFile = readSmallFile("/sys/class/switch/h2w/state", 16);
            if (readSmallFile != null) {
                return readSmallFile.trim();
            }
            AudioManager audioManager = (AudioManager) mCtx.getSystemService("audio");
            ?? isWiredHeadsetOn = audioManager.isWiredHeadsetOn();
            int i = isWiredHeadsetOn;
            if (audioManager.isSpeakerphoneOn()) {
                i = isWiredHeadsetOn + 1;
            }
            return "" + i;
        } catch (Exception unused) {
            MyLog.m6021w("unicompay_DevInfo", "getHeadsetStatus exception");
            return "";
        }
    }

    private static String getIMEI(Context context) {
        String str = "";
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            MyLog.m6022i("unicompay_DevInfo", "get imei failed");
        }
        if (str == null) {
            str = "";
        }
        MyLog.m6021w("unicompay_DevInfo", C14261e.m22a("getIMEI: ", str));
        return str;
    }

    private static String getIMSI(Context context) {
        String str = "";
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception unused) {
        }
        return str == null ? "" : str;
    }

    public static long getInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockCount() * statFs.getBlockSize();
    }

    private static Double getLat() {
        Location currentLocation = getCurrentLocation();
        return currentLocation != null ? Double.valueOf(currentLocation.getLatitude()) : Double.valueOf(0.0d);
    }

    private static String getLinkedFiles() {
        String str = "[]";
        String str2 = _cacheLinkedFiles;
        if (str2 != null) {
            return str2;
        }
        try {
            FileReader fileReader = new FileReader(String.format("/proc/%d/maps", Integer.valueOf(Process.myPid())));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            HashSet hashSet = new HashSet();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.endsWith(".so") || readLine.endsWith(".jar") || readLine.endsWith(".apk") || readLine.endsWith(".odex")) {
                    int lastIndexOf = readLine.lastIndexOf(32);
                    if (lastIndexOf < 0) {
                        lastIndexOf = readLine.lastIndexOf(9);
                    }
                    if (lastIndexOf > 0) {
                        hashSet.add(readLine.substring(lastIndexOf).trim());
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
            StringBuilder sb = new StringBuilder();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                sb.append(((String) it.next()).replaceAll("/system/", "/s/").replaceAll("/lib/", "/l/").replaceAll("/framework/", "/f/"));
                sb.append(';');
            }
            if (sb.charAt(sb.length() - 1) == ';') {
                sb.deleteCharAt(sb.length() - 1);
            }
            str = sb.toString();
            _cacheLinkedFiles = str;
            return str;
        } catch (Exception e) {
            MyLog.m6020w("unicompay_DevInfo", "get linked files exception", e);
            return str;
        }
    }

    private static String getLinkerMappedAddress() {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(String.format("/proc/%d/maps", Integer.valueOf(Process.myPid()))))));
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
            } while (!readLine.contains("/system/bin/linker"));
            bufferedReader.close();
            return readLine != null ? readLine.substring(0, readLine.indexOf("-")) : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static Double getLng() {
        Location currentLocation = getCurrentLocation();
        return currentLocation != null ? Double.valueOf(currentLocation.getLongitude()) : Double.valueOf(0.0d);
    }

    private static String getMD5(String str) {
        int i;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[2048];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Integer> getMTU() {
        HashMap hashMap = new HashMap();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                Iterator it = Collections.list(networkInterfaces).iterator();
                while (it.hasNext()) {
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0) {
                        MyLog.m6021w("unicompay_DevInfo", "网络接口MTU：" + networkInterface.getMTU() + ", " + networkInterface.getName());
                        if (!"lo".equals(networkInterface.getName())) {
                            hashMap.put(networkInterface.getName(), Integer.valueOf(networkInterface.getMTU()));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return hashMap;
    }

    private static String getNativePhoneNumber(Context context) {
        String str = "";
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        } catch (Exception unused) {
        }
        return str == null ? "" : str;
    }

    private static String getProvidersName() {
        String str;
        try {
            if (telephonyManager.getNetworkType() != 4) {
                str = telephonyManager.getNetworkOperatorName();
            } else {
                str = "CDMA_" + telephonyManager.getNetworkOperatorName();
            }
        } catch (Exception unused) {
            MyLog.m6022i("unicompay_DevInfo", "get provider failed");
            str = null;
        }
        return str == null ? "" : str.replaceAll("\"", "");
    }

    private static String getResolution() {
        StringBuilder sb;
        try {
            Display defaultDisplay = ((WindowManager) mCtx.getSystemService("window")).getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            if (width < height) {
                sb = new StringBuilder();
                sb.append(height);
                sb.append("x");
                sb.append(width);
            } else {
                sb = new StringBuilder();
                sb.append(width);
                sb.append("x");
                sb.append(height);
            }
            return sb.toString();
        } catch (Exception e) {
            MyLog.m6020w("unicompay_DevInfo", "get resolution exception", e);
            return "";
        }
    }

    private static String getRuntime() {
        String property = System.getProperty("java.vm.version");
        return (property == null || !property.startsWith("2")) ? "Dalvik" : "ART";
    }

    public static long getSDCardMemorySize() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockSize() * statFs.getBlockCount();
        }
        return 0L;
    }

    private static String getSensors() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        try {
            for (Sensor sensor : ((SensorManager) mCtx.getSystemService("sensor")).getSensorList(-1)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("{");
                sb2.append("\"t\":\"");
                sb2.append(sensor.getType());
                sb2.append("\",");
                sb2.append("\"n\":\"");
                sb2.append(sensor.getName());
                sb2.append("\",");
                sb2.append("\"V\":\"");
                sb2.append(sensor.getVendor());
                sb2.append("\",");
                sb2.append("\"v\":\"");
                sb2.append(sensor.getVersion());
                sb2.append("\",");
                sb2.append("\"r\":\"");
                sb2.append(sensor.getResolution());
                sb2.append("\"");
                sb2.append("},");
                sb.append((CharSequence) sb2);
            }
        } catch (Exception unused) {
            MyLog.m6022i("unicompay_DevInfo", "get sensors info failed");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    private static String getWifiBssid() {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) mCtx.getSystemService("wifi");
            wifi = wifiManager;
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return null;
            }
            String ssid = connectionInfo.getSSID();
            String encodeToString = ssid == null ? "unknown" : Base64.encodeToString(ssid.getBytes(), 2);
            return encodeToString + ";;" + connectionInfo.getBSSID();
        } catch (Exception unused) {
            return null;
        }
    }

    private static String getWifiIp() {
        WifiManager wifiManager;
        try {
            wifiManager = (WifiManager) mCtx.getSystemService("wifi");
            wifi = wifiManager;
        } catch (Exception unused) {
        }
        if (wifiManager == null) {
            return wifi_ip;
        }
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        if (ipAddress != 0) {
            wifi_ip = (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
        }
        return wifi_ip;
    }

    private static String getWifiScanResult() {
        WifiManager wifiManager;
        String str = null;
        try {
            wifiManager = (WifiManager) mCtx.getSystemService("wifi");
            wifi = wifiManager;
        } catch (Exception unused) {
        }
        if (wifiManager == null) {
            return null;
        }
        List<ScanResult> scanResults = wifiManager.getScanResults();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (scanResults != null) {
            for (ScanResult scanResult : scanResults) {
                sb.append("\"");
                sb.append(scanResult.BSSID);
                sb.append(";;");
                sb.append(Base64.encodeToString(scanResult.SSID.getBytes(), 2));
                sb.append("\",");
            }
        }
        sb.append("\"\"]");
        str = sb.toString();
        MyLog.m6021w("unicompay_DevInfo", C14261e.m22a("getWifiScanResult: ", str));
        return str;
    }

    public static String get_sha1(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException unused) {
            MyLog.m6021w("unicompay_DevInfo", "cannot use SHA-1 algo");
            return "";
        }
    }

    public static boolean isAppOnForeground() {
        try {
            String packageName = mCtx.getPackageName();
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) mCtx.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (packageName.equals(runningAppProcessInfo.processName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            MyLog.m6022i("unicompay_DevInfo", e.getLocalizedMessage());
        }
        return false;
    }

    private static boolean isRooted() {
        boolean z;
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (true) {
            if (i >= 5) {
                z = false;
                break;
            }
            File file = new File(strArr[i] + "su");
            if (file.isFile() && file.canExecute()) {
                z = true;
                break;
            }
            i++;
        }
        String str = System.getenv("PATH");
        if (z || str == null || str.isEmpty()) {
            return z;
        }
        String[] split = str.split(File.pathSeparator);
        for (int i2 = 0; !z && i2 < split.length; i2++) {
            File file2 = new File(split[i2] + File.separator + "su");
            if (file2.isFile() && file2.canExecute()) {
                return true;
            }
        }
        return z;
    }

    public static boolean isVpnConnected() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                Iterator it = Collections.list(networkInterfaces).iterator();
                while (it.hasNext()) {
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0 && ("tun0".equals(networkInterface.getName()) || "ppp0".equals(networkInterface.getName()))) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } catch (Throwable th) {
            MyLog.m6020w("unicompay_DevInfo", "check vpn", th);
            return false;
        }
    }

    private static String readSmallFile(String str, int i) {
        File file = new File(str);
        if (file.isFile() && file.canRead()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[i];
                for (int i2 = 0; i2 < i; i2++) {
                    bArr[i2] = 0;
                }
                r2 = fileInputStream.read(bArr) > 0 ? new String(bArr, "ISO-8859-1") : null;
                fileInputStream.close();
            } catch (Exception unused) {
                MyLog.m6022i("unicompay_DevInfo", C14261e.m22a("cannot read file ", str));
            }
        }
        return r2;
    }

    public static void stopWebView() {
        WebView webView = mWebview;
        if (webView == null) {
            return;
        }
        if (bWebviewStopped) {
            MyLog.m6021w("unicompay_DevInfo", "webview已经停止！！！！！！！！");
            return;
        }
        try {
            webView.removeAllViews();
            mWebview.getSettings().setJavaScriptEnabled(false);
            mWebview.destroy();
            MyLog.m6021w("unicompay_DevInfo", "webview stopped!!!1");
            bWebviewStopped = true;
        } catch (Throwable th) {
            MyLog.m6020w("unicompay_DevInfo", "", th);
        }
    }
}
