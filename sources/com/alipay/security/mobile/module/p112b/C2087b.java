package com.alipay.security.mobile.module.p112b;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.security.mobile.module.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2087b {

    /* renamed from: a */
    private static C2087b f3989a = new C2087b();

    private C2087b() {
    }

    /* renamed from: a */
    public static C2087b m20555a() {
        return f3989a;
    }

    /* renamed from: a */
    public static String m20554a(Context context) {
        if (m20553a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    /* renamed from: a */
    private static boolean m20553a(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    /* renamed from: b */
    public static String m20552b() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    /* renamed from: b */
    public static String m20551b(Context context) {
        String str = "";
        if (m20553a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getSubscriberId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    /* renamed from: c */
    public static String m20550c() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(C2081a.m20578a().getPath());
                j = statFs.getBlockSize() * statFs.getAvailableBlocks();
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    /* renamed from: c */
    public static String m20549c(Context context) {
        int i = 0;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Throwable unused) {
        }
        return i == 1 ? "1" : "0";
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: d */
    public static java.lang.String m20548d() {
        /*
            java.lang.String r0 = "0000000000000000"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6d
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L6d
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6d
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L6d
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L58
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L58
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch: java.lang.Throwable -> L6f
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L6f
            r1 = 1
            r5 = r1
        L1b:
            r6 = 100
            if (r5 >= r6) goto L49
            java.lang.String r6 = r4.readLine()     // Catch: java.lang.Throwable -> L47
            if (r6 == 0) goto L49
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch: java.lang.Throwable -> L47
            if (r7 < 0) goto L41
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch: java.lang.Throwable -> L47
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch: java.lang.Throwable -> L47
            java.lang.String r1 = r6.substring(r5, r1)     // Catch: java.lang.Throwable -> L47
            java.lang.String r0 = r1.trim()     // Catch: java.lang.Throwable -> L47
            goto L49
        L41:
            int r5 = r5 + 1
            goto L1b
        L44:
            r0 = move-exception
            r1 = r4
            goto L5d
        L47:
            r1 = r4
            goto L6f
        L49:
            r4.close()     // Catch: java.lang.Throwable -> L4c
        L4c:
            r3.close()     // Catch: java.lang.Throwable -> L4f
        L4f:
            r2.close()     // Catch: java.lang.Throwable -> L7c
            goto L7c
        L53:
            r0 = move-exception
            goto L5d
        L55:
            r0 = move-exception
            r3 = r1
            goto L5d
        L58:
            r3 = r1
            goto L6f
        L5a:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L5d:
            if (r1 == 0) goto L62
            r1.close()     // Catch: java.lang.Throwable -> L62
        L62:
            if (r3 == 0) goto L67
            r3.close()     // Catch: java.lang.Throwable -> L67
        L67:
            if (r2 == 0) goto L6c
            r2.close()     // Catch: java.lang.Throwable -> L6c
        L6c:
            throw r0
        L6d:
            r2 = r1
            r3 = r2
        L6f:
            if (r1 == 0) goto L74
            r1.close()     // Catch: java.lang.Throwable -> L74
        L74:
            if (r3 == 0) goto L79
            r3.close()     // Catch: java.lang.Throwable -> L79
        L79:
            if (r2 == 0) goto L7c
            goto L4f
        L7c:
            if (r0 != 0) goto L80
            java.lang.String r0 = ""
        L80:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p112b.C2087b.m20548d():java.lang.String");
    }

    /* renamed from: d */
    public static String m20547d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put("call", String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put("alarm", String.valueOf(streamVolume5));
        } catch (Throwable unused) {
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: e */
    public static String m20545e(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getNetworkOperatorName();
                }
            } catch (Throwable unused) {
            }
        }
        return (str == null || "null".equals(str)) ? "" : str;
    }

    /* renamed from: f */
    public static String m20544f() {
        String m20513v = m20513v();
        return !C2081a.m20577a(m20513v) ? m20513v : m20512w();
    }

    /* renamed from: f */
    public static String m20543f(Context context) {
        List<Sensor> sensorList;
        String str = null;
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Sensor sensor : sensorList) {
                        sb.append(sensor.getName());
                        sb.append(sensor.getVersion());
                        sb.append(sensor.getVendor());
                    }
                    str = C2081a.m20569e(sb.toString());
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: g */
    public static java.lang.String m20542g() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L46
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L46
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L47
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L47
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L30
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r0 = r0.split(r3, r4)     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L27
            int r3 = r0.length     // Catch: java.lang.Throwable -> L30
            r4 = 1
            if (r3 <= r4) goto L27
            r0 = r0[r4]     // Catch: java.lang.Throwable -> L30
            r1.close()     // Catch: java.lang.Throwable -> L23
        L23:
            r2.close()     // Catch: java.lang.Throwable -> L26
        L26:
            return r0
        L27:
            r1.close()     // Catch: java.lang.Throwable -> L2a
        L2a:
            r2.close()     // Catch: java.lang.Throwable -> L51
            goto L51
        L2e:
            r0 = move-exception
            goto L3b
        L30:
            r0 = r2
            goto L47
        L32:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L3b
        L37:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L3b:
            if (r1 == 0) goto L40
            r1.close()     // Catch: java.lang.Throwable -> L40
        L40:
            if (r2 == 0) goto L45
            r2.close()     // Catch: java.lang.Throwable -> L45
        L45:
            throw r0
        L46:
            r1 = r0
        L47:
            if (r1 == 0) goto L4c
            r1.close()     // Catch: java.lang.Throwable -> L4c
        L4c:
            if (r0 == 0) goto L51
            r0.close()     // Catch: java.lang.Throwable -> L51
        L51:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p112b.C2087b.m20542g():java.lang.String");
    }

    /* renamed from: g */
    public static String m20541g(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    for (Sensor sensor : sensorList) {
                        if (sensor != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", sensor.getName());
                            jSONObject.put("version", sensor.getVersion());
                            jSONObject.put("vendor", sensor.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: h */
    public static java.lang.String m20540h() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L45
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L45
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L46
            r5 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r4, r5)     // Catch: java.lang.Throwable -> L46
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L26
            java.lang.String r5 = "\\s+"
            java.lang.String[] r1 = r1.split(r5)     // Catch: java.lang.Throwable -> L2f
            r5 = 1
            r1 = r1[r5]     // Catch: java.lang.Throwable -> L2f
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Throwable -> L2f
            long r1 = (long) r1
            r2 = r1
        L26:
            r4.close()     // Catch: java.lang.Throwable -> L29
        L29:
            r0.close()     // Catch: java.lang.Throwable -> L50
            goto L50
        L2d:
            r1 = move-exception
            goto L3a
        L2f:
            r1 = r0
            goto L46
        L31:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L3a
        L36:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L3a:
            if (r4 == 0) goto L3f
            r4.close()     // Catch: java.lang.Throwable -> L3f
        L3f:
            if (r0 == 0) goto L44
            r0.close()     // Catch: java.lang.Throwable -> L44
        L44:
            throw r1
        L45:
            r4 = r1
        L46:
            if (r4 == 0) goto L4b
            r4.close()     // Catch: java.lang.Throwable -> L4b
        L4b:
            if (r1 == 0) goto L50
            r1.close()     // Catch: java.lang.Throwable -> L50
        L50:
            java.lang.String r0 = java.lang.String.valueOf(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p112b.C2087b.m20540h():java.lang.String");
    }

    /* renamed from: h */
    public static String m20539h(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: i */
    public static String m20538i() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    /* renamed from: i */
    public static String m20537i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: j */
    public static String m20536j() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = statFs.getBlockSize() * statFs.getBlockCount();
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    /* renamed from: j */
    public static String m20535j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: k */
    public static String m20534k() {
        String str = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: k */
    public static String m20533k(Context context) {
        if (m20553a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        String str = "";
        try {
            str = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (str == null || str.length() == 0 || "02:00:00:00:00:00".equals(str)) {
                return m20514u();
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    /* renamed from: l */
    public static String m20532l() {
        String str = "";
        try {
            str = Locale.getDefault().toString();
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: l */
    public static String m20531l(Context context) {
        if (m20553a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = "";
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (str != null) {
                if (str == null) {
                    return str;
                }
                if (str.length() != 0) {
                    return str;
                }
            }
            return "";
        } catch (Throwable unused) {
            return str;
        }
    }

    /* renamed from: m */
    public static String m20530m() {
        String str = "";
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: m */
    public static String m20529m(Context context) {
        String str = "";
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: n */
    public static String m20528n() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append(currentTimeMillis - (currentTimeMillis % 1000));
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: n */
    public static String m20527n(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: o */
    public static String m20526o() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: o */
    public static String m20525o(Context context) {
        String str = "";
        if (m20553a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                str = wifiManager.getConnectionInfo().getBSSID();
            }
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: p */
    public static String m20524p() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00:");
            for (int i = 0; i < 7; i++) {
                sb.append(new File(strArr[i]).exists() ? "1" : "0");
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: p */
    public static String m20523p(Context context) {
        String str = "";
        try {
            str = Build.VERSION.SDK_INT >= 29 ? "" : (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 28) ? Build.SERIAL : Build.getSerial();
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: q */
    public static String m20522q() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Throwable unused) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    /* renamed from: q */
    public static String m20521q(Context context) {
        try {
            String m20515t = m20515t(context);
            String m20511x = m20511x();
            if (C2081a.m20573b(m20515t) && C2081a.m20573b(m20511x)) {
                return m20515t + ":" + m20511x();
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: r */
    public static java.lang.String m20520r() {
        /*
            java.lang.String r0 = "00"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            java.lang.String r3 = "/system/build.prop"
            java.lang.String r4 = "ro.product.name=sdk"
            r2.put(r3, r4)
            java.lang.String r3 = "/proc/tty/drivers"
            java.lang.String r4 = "goldfish"
            r2.put(r3, r4)
            java.lang.String r3 = "/proc/cpuinfo"
            java.lang.String r4 = "goldfish"
            r2.put(r3, r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ":"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.append(r0)
            java.util.Set r0 = r2.keySet()
            java.util.Iterator r0 = r0.iterator()
        L3d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L92
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            r4 = 0
            r5 = 48
            java.io.LineNumberReader r6 = new java.io.LineNumberReader     // Catch: java.lang.Throwable -> L89
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L89
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L89
            r8.<init>(r3)     // Catch: java.lang.Throwable -> L89
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L89
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L89
        L5b:
            java.lang.String r4 = r6.readLine()     // Catch: java.lang.Throwable -> L7d
            if (r4 == 0) goto L73
            java.lang.String r4 = r4.toLowerCase()     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r7 = r2.get(r3)     // Catch: java.lang.Throwable -> L7d
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch: java.lang.Throwable -> L7d
            boolean r4 = r4.contains(r7)     // Catch: java.lang.Throwable -> L7d
            if (r4 == 0) goto L5b
            r5 = 49
        L73:
            r1.append(r5)
            r6.close()     // Catch: java.lang.Throwable -> L3d
            goto L3d
        L7a:
            r0 = move-exception
            r4 = r6
            goto L80
        L7d:
            r4 = r6
            goto L89
        L7f:
            r0 = move-exception
        L80:
            r1.append(r5)
            if (r4 == 0) goto L88
            r4.close()     // Catch: java.lang.Throwable -> L88
        L88:
            throw r0
        L89:
            r1.append(r5)
            if (r4 == 0) goto L3d
            r4.close()     // Catch: java.lang.Throwable -> L3d
            goto L3d
        L92:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p112b.C2087b.m20520r():java.lang.String");
    }

    /* renamed from: r */
    public static String m20519r(Context context) {
        try {
            long j = 0;
            if (((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
                String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
                for (int i = 0; i < 5; i++) {
                    long j2 = -1;
                    try {
                        j2 = new File(strArr[i]).lastModified();
                    } catch (Throwable unused) {
                    }
                    j = Math.max(j2, j);
                }
                return "1:" + j;
            }
            return "0:0";
        } catch (Throwable unused2) {
            return "";
        }
    }

    /* renamed from: s */
    public static String m20518s() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", "unknown");
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            try {
                String str2 = (String) Build.class.getField(str).get(null);
                String str3 = (String) linkedHashMap.get(str);
                String lowerCase = str2 != null ? str2.toLowerCase() : null;
                if (lowerCase != null && lowerCase.contains(str3)) {
                    c = '1';
                }
            } catch (Throwable unused) {
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* renamed from: s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m20517s(android.content.Context r3) {
        /*
            android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch: java.lang.Throwable -> L40
            java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L40
            r1 = 0
            android.content.Intent r3 = r3.registerReceiver(r1, r0)     // Catch: java.lang.Throwable -> L40
            java.lang.String r0 = "level"
            r1 = -1
            int r0 = r3.getIntExtra(r0, r1)     // Catch: java.lang.Throwable -> L40
            java.lang.String r2 = "status"
            int r3 = r3.getIntExtra(r2, r1)     // Catch: java.lang.Throwable -> L40
            r1 = 2
            if (r3 == r1) goto L23
            r1 = 5
            if (r3 != r1) goto L21
            goto L23
        L21:
            r3 = 0
            goto L24
        L23:
            r3 = 1
        L24:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L40
            r1.<init>()     // Catch: java.lang.Throwable -> L40
            if (r3 == 0) goto L2e
            java.lang.String r3 = "1"
            goto L30
        L2e:
            java.lang.String r3 = "0"
        L30:
            r1.append(r3)     // Catch: java.lang.Throwable -> L40
            java.lang.String r3 = ":"
            r1.append(r3)     // Catch: java.lang.Throwable -> L40
            r1.append(r0)     // Catch: java.lang.Throwable -> L40
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> L40
            return r3
        L40:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p112b.C2087b.m20517s(android.content.Context):java.lang.String");
    }

    /* renamed from: t */
    public static String m20516t() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            String str2 = (String) linkedHashMap.get(str);
            String m20572b = C2081a.m20572b(str, "");
            if (m20572b != null && m20572b.contains(str2)) {
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /* renamed from: t */
    private static String m20515t(Context context) {
        if (m20553a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                if (subtype != 4 && subtype != 1 && subtype != 2 && subtype != 7 && subtype != 11) {
                    if (subtype != 3 && subtype != 5 && subtype != 6 && subtype != 8 && subtype != 9 && subtype != 10 && subtype != 12 && subtype != 14 && subtype != 15) {
                        return subtype == 13 ? "4G" : "UNKNOW";
                    }
                    return "3G";
                }
                return "2G";
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: u */
    private static String m20514u() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list != null) {
                for (NetworkInterface networkInterface : list) {
                    if (networkInterface != null && networkInterface.getName() != null && networkInterface.getName().equalsIgnoreCase("wlan0")) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            return "02:00:00:00:00:00";
                        }
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02X:", Integer.valueOf(hardwareAddress[i] & 255)));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        return sb.toString();
                    }
                }
                return "02:00:00:00:00:00";
            }
            return "02:00:00:00:00:00";
        } catch (Throwable unused) {
            return "02:00:00:00:00:00";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: v */
    private static java.lang.String m20513v() {
        /*
            java.lang.String r0 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L41
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L41
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L42
            r3 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L42
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L30
            boolean r3 = com.alipay.security.mobile.module.p110a.C2081a.m20577a(r1)     // Catch: java.lang.Throwable -> L30
            if (r3 != 0) goto L24
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L30
            r0.close()     // Catch: java.lang.Throwable -> L20
        L20:
            r2.close()     // Catch: java.lang.Throwable -> L23
        L23:
            return r1
        L24:
            r0.close()     // Catch: java.lang.Throwable -> L27
        L27:
            r2.close()     // Catch: java.lang.Throwable -> L4a
            goto L4a
        L2b:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L36
        L30:
            r1 = r0
            goto L42
        L32:
            r0 = move-exception
            goto L36
        L34:
            r0 = move-exception
            r2 = r1
        L36:
            if (r1 == 0) goto L3b
            r1.close()     // Catch: java.lang.Throwable -> L3b
        L3b:
            if (r2 == 0) goto L40
            r2.close()     // Catch: java.lang.Throwable -> L40
        L40:
            throw r0
        L41:
            r2 = r1
        L42:
            if (r1 == 0) goto L47
            r1.close()     // Catch: java.lang.Throwable -> L47
        L47:
            if (r2 == 0) goto L4a
            goto L27
        L4a:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p112b.C2087b.m20513v():java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: w */
    private static java.lang.String m20512w() {
        /*
            java.lang.String r0 = "/proc/cpuinfo"
            java.lang.String r1 = ""
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L56
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L56
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L57
            r4 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r3, r4)     // Catch: java.lang.Throwable -> L57
        L11:
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Throwable -> L43
            if (r2 == 0) goto L3a
            boolean r4 = com.alipay.security.mobile.module.p110a.C2081a.m20577a(r2)     // Catch: java.lang.Throwable -> L43
            if (r4 != 0) goto L11
            java.lang.String r4 = ":"
            java.lang.String[] r2 = r2.split(r4)     // Catch: java.lang.Throwable -> L43
            if (r2 == 0) goto L11
            int r4 = r2.length     // Catch: java.lang.Throwable -> L43
            r5 = 1
            if (r4 <= r5) goto L11
            r4 = 0
            r4 = r2[r4]     // Catch: java.lang.Throwable -> L43
            java.lang.String r6 = "BogoMIPS"
            boolean r4 = r4.contains(r6)     // Catch: java.lang.Throwable -> L43
            if (r4 == 0) goto L11
            r2 = r2[r5]     // Catch: java.lang.Throwable -> L43
            java.lang.String r1 = r2.trim()     // Catch: java.lang.Throwable -> L43
        L3a:
            r3.close()     // Catch: java.lang.Throwable -> L3d
        L3d:
            r0.close()     // Catch: java.lang.Throwable -> L61
            goto L61
        L41:
            r1 = move-exception
            goto L4b
        L43:
            r2 = r0
            goto L57
        L45:
            r1 = move-exception
            r0 = r2
            goto L4b
        L48:
            r1 = move-exception
            r0 = r2
            r3 = r0
        L4b:
            if (r3 == 0) goto L50
            r3.close()     // Catch: java.lang.Throwable -> L50
        L50:
            if (r0 == 0) goto L55
            r0.close()     // Catch: java.lang.Throwable -> L55
        L55:
            throw r1
        L56:
            r3 = r2
        L57:
            if (r3 == 0) goto L5c
            r3.close()     // Catch: java.lang.Throwable -> L5c
        L5c:
            if (r2 == 0) goto L61
            r2.close()     // Catch: java.lang.Throwable -> L61
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p112b.C2087b.m20512w():java.lang.String");
    }

    /* renamed from: x */
    private static String m20511x() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: e */
    public final String m20546e() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new C2088c(this)).length);
        } catch (Throwable unused) {
            return "1";
        }
    }
}
