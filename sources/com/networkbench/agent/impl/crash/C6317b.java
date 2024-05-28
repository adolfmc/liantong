package com.networkbench.agent.impl.crash;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6317b {

    /* renamed from: a */
    private static final InterfaceC6393e f15884a = C6394f.m10150a();

    /* renamed from: b */
    private static long f15885b = -1;

    /* renamed from: c */
    private static final int[] f15886c = {Process.myPid()};

    /* renamed from: d */
    private static final int f15887d = 1024;

    /* renamed from: c */
    public static int m10447c(Context context) {
        return 0;
    }

    /* renamed from: a */
    public static int m10451a(Context context) {
        return context.getResources().getConfiguration().orientation == 2 ? 1 : 0;
    }

    /* renamed from: b */
    public static boolean m10449b(Context context) {
        try {
            return m10451a(context) != 1;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* renamed from: d */
    public static final int m10445d(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", NBSAgent.getApplicationInformation().getPackageId()) == -1) {
                return -1;
            }
            return locationManager.isProviderEnabled("gps") ? 1 : 0;
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15884a;
            interfaceC6393e.mo10122a("gps error = " + e.getMessage());
            return -1;
        }
    }

    /* renamed from: e */
    public static final int m10444e(Context context) {
        BluetoothAdapter defaultAdapter;
        if (context.getPackageManager().checkPermission("android.permission.BLUETOOTH", NBSAgent.getApplicationInformation().getPackageId()) == -1 || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
            return -1;
        }
        return defaultAdapter.isEnabled() ? 1 : 0;
    }

    /* renamed from: f */
    public static long m10443f(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        long j = memoryInfo.availMem;
        if (j == 0) {
            return 0L;
        }
        long j2 = j >> 20;
        if (j2 < 0) {
            return 0L;
        }
        return j2;
    }

    /* renamed from: g */
    public static boolean m10442g(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* renamed from: h */
    public static int m10441h(Context context) {
        String str = Build.TAGS;
        return ((str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists() || new File("/system/xbin/su").exists()) ? 1 : 0;
    }

    /* renamed from: a */
    public static String m10452a() {
        String str = "";
        if (Build.VERSION.SDK_INT < 21) {
            str = Build.CPU_ABI;
        } else {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr != null && strArr.length > 0) {
                str = strArr[0];
            }
        }
        if (TextUtils.isEmpty(str)) {
            f15884a.mo10122a("Architecture#getValue()::Build.CPU_ABI returned null or empty");
            return "";
        }
        return str.toLowerCase(Locale.US);
    }

    /* renamed from: b */
    public static long m10450b() {
        String absolutePath = Environment.getDataDirectory().getAbsolutePath();
        InterfaceC6393e interfaceC6393e = f15884a;
        interfaceC6393e.mo10117c("rootpath is " + absolutePath);
        StatFs statFs = new StatFs(absolutePath);
        return (statFs.getBlockSize() * statFs.getAvailableBlocks()) >> 20;
    }

    /* renamed from: c */
    public static long m10448c() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                InterfaceC6393e interfaceC6393e = f15884a;
                interfaceC6393e.mo10117c("sd path is " + absolutePath);
                if (absolutePath.equals(Environment.getDataDirectory().getPath())) {
                    return -1L;
                }
                StatFs statFs = new StatFs(absolutePath);
                return (statFs.getAvailableBlocks() * statFs.getBlockSize()) >> 20;
            }
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e2 = f15884a;
            interfaceC6393e2.mo10115e("getAvailableSDMemroyInMB error:" + th.getMessage());
        }
        return -1L;
    }

    /* renamed from: d */
    public static String m10446d() {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader;
        BufferedReader bufferedReader2;
        Exception e;
        IOException e2;
        FileNotFoundException e3;
        InterfaceC6393e interfaceC6393e;
        StringBuilder sb;
        String readLine;
        try {
            try {
                fileReader = new FileReader("/proc/cpuinfo");
                try {
                    bufferedReader2 = new BufferedReader(fileReader);
                } catch (FileNotFoundException e4) {
                    bufferedReader2 = null;
                    e3 = e4;
                } catch (IOException e5) {
                    bufferedReader2 = null;
                    e2 = e5;
                } catch (Exception e6) {
                    bufferedReader2 = null;
                    e = e6;
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e7) {
                            f15884a.mo10115e("IOException:" + e7.getMessage());
                            throw th;
                        }
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                bufferedReader2 = null;
                e3 = e8;
                fileReader = null;
            } catch (IOException e9) {
                bufferedReader2 = null;
                e2 = e9;
                fileReader = null;
            } catch (Exception e10) {
                bufferedReader2 = null;
                e = e10;
                fileReader = null;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                fileReader = null;
            }
            do {
                try {
                    readLine = bufferedReader2.readLine();
                } catch (FileNotFoundException e11) {
                    e3 = e11;
                    f15884a.mo10115e("FileNotFoundException:" + e3.getMessage());
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e12) {
                            e = e12;
                            interfaceC6393e = f15884a;
                            sb = new StringBuilder();
                            sb.append("IOException:");
                            sb.append(e.getMessage());
                            interfaceC6393e.mo10115e(sb.toString());
                            f15884a.mo10117c("can't get cpu model");
                            return "UNKNOWN";
                        }
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    f15884a.mo10117c("can't get cpu model");
                    return "UNKNOWN";
                } catch (IOException e13) {
                    e2 = e13;
                    f15884a.mo10115e("IOException:" + e2.getMessage());
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e14) {
                            e = e14;
                            interfaceC6393e = f15884a;
                            sb = new StringBuilder();
                            sb.append("IOException:");
                            sb.append(e.getMessage());
                            interfaceC6393e.mo10115e(sb.toString());
                            f15884a.mo10117c("can't get cpu model");
                            return "UNKNOWN";
                        }
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    f15884a.mo10117c("can't get cpu model");
                    return "UNKNOWN";
                } catch (Exception e15) {
                    e = e15;
                    f15884a.mo10115e("Exception:" + e.getMessage());
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e16) {
                            e = e16;
                            interfaceC6393e = f15884a;
                            sb = new StringBuilder();
                            sb.append("IOException:");
                            sb.append(e.getMessage());
                            interfaceC6393e.mo10115e(sb.toString());
                            f15884a.mo10117c("can't get cpu model");
                            return "UNKNOWN";
                        }
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    f15884a.mo10117c("can't get cpu model");
                    return "UNKNOWN";
                }
                if (readLine == null) {
                    bufferedReader2.close();
                    try {
                        bufferedReader2.close();
                        fileReader.close();
                    } catch (IOException e17) {
                        e = e17;
                        interfaceC6393e = f15884a;
                        sb = new StringBuilder();
                        sb.append("IOException:");
                        sb.append(e.getMessage());
                        interfaceC6393e.mo10115e(sb.toString());
                        f15884a.mo10117c("can't get cpu model");
                        return "UNKNOWN";
                    }
                    f15884a.mo10117c("can't get cpu model");
                    return "UNKNOWN";
                }
            } while (!readLine.toUpperCase().contains("Hardware".toUpperCase()));
            f15884a.mo10117c("cpu model text is.." + readLine + ",");
            String[] split = readLine.replace(":", "").split("\\s+", 2);
            f15884a.mo10117c("get cpu model,model is..." + split[1]);
            String str = split[1];
            try {
                bufferedReader2.close();
                fileReader.close();
            } catch (IOException e18) {
                f15884a.mo10115e("IOException:" + e18.getMessage());
            }
            return str;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: i */
    public static boolean m10440i(Context context) {
        return m10442g(context);
    }

    /* renamed from: j */
    public static float m10439j(Context context) {
        try {
            int totalPss = ((ActivityManager) context.getSystemService("activity")).getProcessMemoryInfo(f15886c)[0].getTotalPss();
            if (totalPss >= 0) {
                return totalPss / 1024;
            }
            return 0.0f;
        } catch (Throwable unused) {
            return 0.0f;
        }
    }
}
