package com.baidu.p120ar.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.microedition.khronos.opengles.GL10;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.SystemInfoUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SystemInfoUtils {
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String LINE_END = "\r\n";

    /* renamed from: NA */
    public static final String f4095NA = "N/A";
    public static String log = "";
    public static String name = "";
    public static String value = "";

    public static List<String> getAllInfos(Context context, GL10 gl10) {
        log = "";
        name = "";
        value = "";
        long[] romMemroy = getRomMemroy();
        long sDCardTotalSize = getSDCardTotalSize();
        long sDAvailableSizeByM = getSDAvailableSizeByM();
        log += "基本信息：\n";
        getBaseInfo(context);
        log += "\n存储：\n";
        log += getLogStr("可用/内存大小", getAvailMemory(context) + "/" + getRamMemory(context) + "MB");
        log += getLogStr("可用/ROM存储", romMemroy[1] + "/" + romMemroy[0] + "MB");
        log += getLogStr("可用/SDCard存储", sDAvailableSizeByM + "/" + sDCardTotalSize + "MB");
        log += getLogStr("Heap Size", ((int) (Runtime.getRuntime().maxMemory() / 1048576)) + "MB");
        log += "\nCPU信息：\n";
        log += getLogStr("CPU名称", getCpuName());
        log += getLogStr("核心数", String.valueOf(getNumCores()));
        log += getLogStr("最低频率", getMinCpuFreq() + "MHz");
        log += getLogStr("最高频率", getMaxCpuFreq() + "MHz");
        log += getLogStr("当前频率", getCurCpuFreq() + "MHz");
        log += "\nGPU信息：\n";
        getGPUInfo(gl10);
        ArrayList arrayList = new ArrayList();
        arrayList.add(log);
        arrayList.add(name);
        arrayList.add(value);
        return arrayList;
    }

    public static String getLogStr(String str, String str2) {
        if (!TextUtils.isEmpty(name)) {
            name += ",";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(TextUtils.isEmpty(str) ? "N/A" : str);
        name = sb.toString();
        if (!TextUtils.isEmpty(value)) {
            value += ",";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(value);
        sb2.append(TextUtils.isEmpty(str2) ? "N/A" : str2);
        value = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            str = "N/A";
        }
        sb3.append(str);
        sb3.append(": ");
        if (TextUtils.isEmpty(str2)) {
            str2 = "N/A";
        }
        sb3.append(str2);
        sb3.append("\n");
        return sb3.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v20, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.io.Reader, java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.InputStreamReader] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0071 -> B:53:0x0076). Please submit an issue!!! */
    public static String getBaseInfo(Context context) {
        ?? r4;
        String[] strArr = {"null", "null", "null", "null"};
        BufferedReader bufferedReader = 0;
        r3 = null;
        BufferedReader bufferedReader2 = null;
        bufferedReader = 0;
        try {
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        try {
            try {
                r4 = new InputStreamReader(new FileInputStream("/proc/version"), Charset.forName("utf-8"));
                try {
                    BufferedReader bufferedReader3 = new BufferedReader(r4, 8192);
                    try {
                        strArr[0] = bufferedReader3.readLine().split("\\s+")[2];
                        bufferedReader3.close();
                        try {
                            bufferedReader3.close();
                        } catch (IOException e2) {
                            e2.printStackTrace(System.out);
                        }
                        r4.close();
                    } catch (Exception e3) {
                        bufferedReader2 = bufferedReader3;
                        e = e3;
                        e.printStackTrace(System.out);
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace(System.out);
                            }
                        }
                        if (r4 != 0) {
                            r4.close();
                        }
                        strArr[1] = Build.VERSION.RELEASE;
                        strArr[2] = Build.MODEL;
                        strArr[3] = Build.DISPLAY;
                        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                        log += getLogStr("手机厂商", Build.BRAND);
                        log += getLogStr("手机型号", Build.MODEL);
                        log += getLogStr("SDK版本", Build.VERSION.SDK);
                        log += getLogStr("系统版本", Build.VERSION.RELEASE);
                        StringBuilder sb = new StringBuilder();
                        sb.append(log);
                        r4 = new StringBuilder();
                        r4.append(displayMetrics.widthPixels);
                        r4.append("*");
                        r4.append(displayMetrics.heightPixels);
                        sb.append(getLogStr("屏幕分辨率", r4.toString()));
                        log = sb.toString();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(log);
                        bufferedReader = new StringBuilder();
                        bufferedReader.append(context.getResources().getDisplayMetrics().densityDpi);
                        bufferedReader.append("dpi");
                        sb2.append(getLogStr("屏幕密度", bufferedReader.toString()));
                        log = sb2.toString();
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader3;
                        if (bufferedReader != 0) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                                e5.printStackTrace(System.out);
                            }
                        }
                        if (r4 != 0) {
                            try {
                                r4.close();
                            } catch (IOException e6) {
                                e6.printStackTrace(System.out);
                            }
                        }
                        throw th;
                    }
                } catch (Exception e7) {
                    e = e7;
                }
            } catch (Exception e8) {
                e = e8;
                r4 = 0;
            } catch (Throwable th2) {
                th = th2;
                r4 = 0;
            }
            strArr[1] = Build.VERSION.RELEASE;
            strArr[2] = Build.MODEL;
            strArr[3] = Build.DISPLAY;
            DisplayMetrics displayMetrics2 = context.getResources().getDisplayMetrics();
            log += getLogStr("手机厂商", Build.BRAND);
            log += getLogStr("手机型号", Build.MODEL);
            log += getLogStr("SDK版本", Build.VERSION.SDK);
            log += getLogStr("系统版本", Build.VERSION.RELEASE);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(log);
            r4 = new StringBuilder();
            r4.append(displayMetrics2.widthPixels);
            r4.append("*");
            r4.append(displayMetrics2.heightPixels);
            sb3.append(getLogStr("屏幕分辨率", r4.toString()));
            log = sb3.toString();
            StringBuilder sb22 = new StringBuilder();
            sb22.append(log);
            bufferedReader = new StringBuilder();
            bufferedReader.append(context.getResources().getDisplayMetrics().densityDpi);
            bufferedReader.append("dpi");
            sb22.append(getLogStr("屏幕密度", bufferedReader.toString()));
            log = sb22.toString();
            return "";
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.io.BufferedReader] */
    public static long getRamMemory(Context context) {
        InputStreamReader inputStreamReader;
        Throwable th;
        Exception e;
        BufferedReader bufferedReader;
        long j;
        ?? r10 = "/proc/meminfo";
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream("/proc/meminfo"), Charset.forName("utf-8"));
            } catch (Exception e2) {
                inputStreamReader = null;
                e = e2;
                bufferedReader = null;
            } catch (Throwable th2) {
                inputStreamReader = null;
                th = th2;
                r10 = 0;
            }
            try {
                bufferedReader = new BufferedReader(inputStreamReader, 8192);
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                r10 = 0;
                if (r10 != 0) {
                    try {
                        r10.close();
                    } catch (IOException e4) {
                        e4.printStackTrace(System.out);
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e5) {
                        e5.printStackTrace(System.out);
                    }
                }
                throw th;
            }
            try {
                String readLine = bufferedReader.readLine();
                String[] split = readLine.split("\\s+");
                for (String str : split) {
                    Log.i("bdar:", readLine + ", " + str + "\t");
                }
                j = Integer.parseInt(split[1]);
                try {
                    bufferedReader.close();
                } catch (IOException e6) {
                    e6.printStackTrace(System.out);
                }
                try {
                    inputStreamReader.close();
                } catch (IOException e7) {
                    e7.printStackTrace(System.out);
                }
            } catch (Exception e8) {
                e = e8;
                e.printStackTrace(System.out);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e9) {
                        e9.printStackTrace(System.out);
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e10) {
                        e10.printStackTrace(System.out);
                    }
                }
                j = 0;
                return j / 1024;
            }
            return j / 1024;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static long getAvailMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / 1048576;
    }

    public static long[] getRomMemroy() {
        StatFs statFs;
        long blockSize = new StatFs(Environment.getDataDirectory().getPath()).getBlockSize();
        return new long[]{(statFs.getBlockCount() * blockSize) / 1048576, (blockSize * statFs.getAvailableBlocks()) / 1048576};
    }

    public static long getSDCardTotalSize() {
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                return (statFs.getBlockSize() * statFs.getBlockCount()) / 1048576;
            }
            return 0L;
        } catch (Throwable th) {
            th.printStackTrace(System.out);
            return 0L;
        }
    }

    public static long getSDAvailableSizeByM() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            try {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                return (statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1048576;
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return 0L;
    }

    public static String getCpuName() {
        BufferedReader bufferedReader;
        Throwable th;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader2;
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream("/proc/cpuinfo"), Charset.forName("utf-8"));
            } catch (Exception e) {
                e = e;
                inputStreamReader = null;
                bufferedReader2 = null;
            } catch (Throwable th2) {
                bufferedReader = null;
                th = th2;
                inputStreamReader = null;
            }
            try {
                bufferedReader2 = new BufferedReader(inputStreamReader);
            } catch (Exception e2) {
                e = e2;
                bufferedReader2 = null;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace(System.out);
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace(System.out);
                    }
                }
                throw th;
            }
            try {
                String[] split = bufferedReader2.readLine().split(":\\s+", 2);
                for (int i = 0; i < split.length; i++) {
                }
                String str = split[1];
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                    e5.printStackTrace(System.out);
                }
                try {
                    inputStreamReader.close();
                } catch (IOException e6) {
                    e6.printStackTrace(System.out);
                }
                return str;
            } catch (Exception e7) {
                e = e7;
                e.printStackTrace(System.out);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e8) {
                        e8.printStackTrace(System.out);
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e9) {
                        e9.printStackTrace(System.out);
                    }
                }
                return null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static int getNumCores() {
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() { // from class: com.baidu.ar.utils.SystemInfoUtils.1CpuFilter
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return Pattern.matches("cpu[0-9]", file.getName());
                }
            });
            Log.d("bdar", "CPU Count: " + listFiles.length);
            return listFiles.length;
        } catch (Exception e) {
            Log.d("bdar", "CPU Count: Failed.");
            e.printStackTrace(System.out);
            return 1;
        }
    }

    public static String getMinCpuFreq() {
        String str;
        byte[] bArr;
        String str2 = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq").start().getInputStream();
            while (inputStream.read(new byte[24]) != -1) {
                str2 = str2 + new String(bArr, "utf-8");
            }
            inputStream.close();
            str = String.valueOf(Integer.parseInt(str2.trim()) / 1000);
        } catch (Exception unused) {
            str = "N/A";
        }
        return str.trim();
    }

    public static boolean isHighPerformence() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static String getMaxCpuFreq() {
        String str;
        byte[] bArr;
        String str2 = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            while (inputStream.read(new byte[24]) != -1) {
                str2 = str2 + new String(bArr, "utf-8");
            }
            inputStream.close();
            str = String.valueOf(Integer.parseInt(str2.trim()) / 1000);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            str = "N/A";
        }
        return String.valueOf(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getCurCpuFreq() {
        /*
            java.lang.String r0 = "N/A"
            r1 = 0
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
            java.lang.String r4 = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
            java.lang.String r4 = "utf-8"
            java.nio.charset.Charset r4 = java.nio.charset.Charset.forName(r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5b
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L49
            java.lang.String r0 = r1.trim()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L49
            java.lang.String r1 = r0.trim()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L49
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L49
            int r1 = r1 / 1000
            java.lang.String r0 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L49
            r3.close()     // Catch: java.io.IOException -> L35
            goto L3b
        L35:
            r1 = move-exception
            java.io.PrintStream r3 = java.lang.System.out
            r1.printStackTrace(r3)
        L3b:
            r2.close()     // Catch: java.io.IOException -> L3f
            goto L7c
        L3f:
            r1 = move-exception
            java.io.PrintStream r2 = java.lang.System.out
            r1.printStackTrace(r2)
            goto L7c
        L46:
            r0 = move-exception
            r1 = r3
            goto L81
        L49:
            r1 = move-exception
            r5 = r3
            r3 = r0
            r0 = r2
            r2 = r1
            r1 = r5
            goto L5e
        L50:
            r0 = move-exception
            goto L81
        L52:
            r3 = move-exception
            r5 = r3
            r3 = r0
            r0 = r2
            r2 = r5
            goto L5e
        L58:
            r0 = move-exception
            r2 = r1
            goto L81
        L5b:
            r2 = move-exception
            r3 = r0
            r0 = r1
        L5e:
            java.io.PrintStream r4 = java.lang.System.out     // Catch: java.lang.Throwable -> L7d
            r2.printStackTrace(r4)     // Catch: java.lang.Throwable -> L7d
            if (r1 == 0) goto L6f
            r1.close()     // Catch: java.io.IOException -> L69
            goto L6f
        L69:
            r1 = move-exception
            java.io.PrintStream r2 = java.lang.System.out
            r1.printStackTrace(r2)
        L6f:
            if (r0 == 0) goto L7b
            r0.close()     // Catch: java.io.IOException -> L75
            goto L7b
        L75:
            r0 = move-exception
            java.io.PrintStream r1 = java.lang.System.out
            r0.printStackTrace(r1)
        L7b:
            r0 = r3
        L7c:
            return r0
        L7d:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
        L81:
            if (r1 == 0) goto L8d
            r1.close()     // Catch: java.io.IOException -> L87
            goto L8d
        L87:
            r1 = move-exception
            java.io.PrintStream r3 = java.lang.System.out
            r1.printStackTrace(r3)
        L8d:
            if (r2 == 0) goto L99
            r2.close()     // Catch: java.io.IOException -> L93
            goto L99
        L93:
            r1 = move-exception
            java.io.PrintStream r2 = java.lang.System.out
            r1.printStackTrace(r2)
        L99:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.utils.SystemInfoUtils.getCurCpuFreq():java.lang.String");
    }

    public static void getGPUInfo(GL10 gl10) {
        log += getLogStr("GL_VENDOR", gl10.glGetString(7936));
        log += getLogStr("GL_RENDERER", gl10.glGetString(7937));
        log += getLogStr("GL_VERSION", gl10.glGetString(7938));
    }

    public static boolean isHasGyroscope(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(4) != null;
    }

    public static String getAppName(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public static boolean isMateX(Context context) {
        return "RLIAN00".equalsIgnoreCase(Build.MODEL) || "RLIN29".equalsIgnoreCase(Build.MODEL) || "TAH-AN00".equalsIgnoreCase(Build.MODEL) || "TAHN29".equalsIgnoreCase(Build.MODEL) || "TAH-AN00m".equalsIgnoreCase(Build.MODEL) || "RLI-N29".equalsIgnoreCase(Build.MODEL) || "TAH-N29".equalsIgnoreCase(Build.MODEL) || "RHA-AN00m".equalsIgnoreCase(Build.MODEL);
    }

    public static boolean isNexus6P() {
        return "Nexus 6P".equals(Build.MODEL) || "AOSP on angler".equals(Build.MODEL);
    }

    public static boolean isNexus5X() {
        return "Nexus 5X".equals(Build.MODEL);
    }

    public static boolean isScreenOrientationLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
