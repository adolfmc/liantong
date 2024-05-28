package com.networkbench.nbslens.nbsnativecrashlib;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.system.Os;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.nbslens.nbsnativecrashlib.n */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6805n {

    /* renamed from: a */
    static final String f17670a = "nbscrash";

    /* renamed from: b */
    static final String f17671b = "*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***";

    /* renamed from: c */
    static final String f17672c = "--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---";

    /* renamed from: d */
    static final String f17673d = "+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++";

    /* renamed from: e */
    static final String f17674e = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /* renamed from: f */
    static final String f17675f = "java";

    /* renamed from: g */
    static final String f17676g = "native";

    /* renamed from: h */
    static final String f17677h = "anr";

    /* renamed from: i */
    static final String f17678i = "tombstone";

    /* renamed from: j */
    static final String f17679j = ".java.nbscrash";

    /* renamed from: k */
    static final String f17680k = ".native.nbscrash";

    /* renamed from: l */
    static final String f17681l = ".anr.nbscrash";

    /* renamed from: m */
    static final String f17682m = ".trace.nbscrash";

    /* renamed from: n */
    private static final String f17683n = "%21s %8s\n";

    /* renamed from: o */
    private static final String f17684o = "%21s %8s %21s %8s\n";

    /* renamed from: p */
    private static final String[] f17685p = {"/data/local/su", "/data/local/bin/su", "/data/local/xbin/su", "/system/xbin/su", "/system/bin/su", "/system/bin/.ext/su", "/system/bin/failsafe/su", "/system/sd/xbin/su", "/system/usr/we-need-root/su", "/sbin/su", "/su/bin/su"};

    private C6805n() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
        if (r0 == null) goto L21;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m8384a(android.content.Context r4, int r5) {
        /*
            r4 = 0
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            r2.<init>()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            java.lang.String r3 = "/proc/"
            r2.append(r3)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            r2.append(r5)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            java.lang.String r5 = "/cmdline"
            r2.append(r5)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L48
            java.lang.String r5 = r0.readLine()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L49
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L49
            if (r1 != 0) goto L39
            java.lang.String r5 = r5.trim()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L49
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L49
            if (r1 != 0) goto L39
            r0.close()     // Catch: java.lang.Exception -> L38
        L38:
            return r5
        L39:
            r0.close()     // Catch: java.lang.Exception -> L4c
            goto L4c
        L3d:
            r4 = move-exception
            r5 = r4
            r4 = r0
            goto L42
        L41:
            r5 = move-exception
        L42:
            if (r4 == 0) goto L47
            r4.close()     // Catch: java.lang.Exception -> L47
        L47:
            throw r5
        L48:
            r0 = r4
        L49:
            if (r0 == 0) goto L4c
            goto L39
        L4c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.nbslens.nbsnativecrashlib.C6805n.m8384a(android.content.Context, int):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m8388a() {
        try {
            for (String str : f17685p) {
                if (new File(str).exists()) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m8379b() {
        if (Build.VERSION.SDK_INT >= 21) {
            return TextUtils.join(",", Build.SUPPORTED_ABIS);
        }
        String str = Build.CPU_ABI;
        String str2 = Build.CPU_ABI2;
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str + "," + str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m8385a(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            str = null;
        }
        return TextUtils.isEmpty(str) ? "unknown" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m8377c() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Process Summary (From: android.os.Debug.MemoryInfo)\n");
        sb.append(String.format(Locale.US, "%21s %8s\n", "", "Pss(KB)"));
        sb.append(String.format(Locale.US, "%21s %8s\n", "", "------"));
        try {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            if (Build.VERSION.SDK_INT >= 23) {
                sb.append(String.format(Locale.US, "%21s %8s\n", "Java Heap:", memoryInfo.getMemoryStat("summary.java-heap")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Native Heap:", memoryInfo.getMemoryStat("summary.native-heap")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Code:", memoryInfo.getMemoryStat("summary.code")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Stack:", memoryInfo.getMemoryStat("summary.stack")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Graphics:", memoryInfo.getMemoryStat("summary.graphics")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Private Other:", memoryInfo.getMemoryStat("summary.private-other")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "System:", memoryInfo.getMemoryStat("summary.system")));
                sb.append(String.format(Locale.US, "%21s %8s %21s %8s\n", "TOTAL:", memoryInfo.getMemoryStat("summary.total-pss"), "TOTAL SWAP:", memoryInfo.getMemoryStat("summary.total-swap")));
            } else {
                Locale locale = Locale.US;
                sb.append(String.format(locale, "%21s %8s\n", "Java Heap:", "~ " + memoryInfo.dalvikPrivateDirty));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Native Heap:", String.valueOf(memoryInfo.nativePrivateDirty)));
                Locale locale2 = Locale.US;
                sb.append(String.format(locale2, "%21s %8s\n", "Private Other:", "~ " + memoryInfo.otherPrivateDirty));
                if (Build.VERSION.SDK_INT >= 19) {
                    sb.append(String.format(Locale.US, "%21s %8s\n", "System:", String.valueOf((memoryInfo.getTotalPss() - memoryInfo.getTotalPrivateDirty()) - memoryInfo.getTotalPrivateClean())));
                } else {
                    Locale locale3 = Locale.US;
                    sb.append(String.format(locale3, "%21s %8s\n", "System:", "~ " + (memoryInfo.getTotalPss() - memoryInfo.getTotalPrivateDirty())));
                }
                sb.append(String.format(Locale.US, "%21s %8s\n", "TOTAL:", String.valueOf(memoryInfo.getTotalPss())));
            }
        } catch (Exception e) {
            NBSNativeCrash.m8519d().mo8437c("nbscrash", "Util getProcessMemoryInfo failed", e);
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static String m8378b(String str) {
        return m8381a(str, 0);
    }

    /* renamed from: a */
    private static String m8381a(String str, int i) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                    int i2 = 0;
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            String trim = readLine.trim();
                            if (trim.length() > 0) {
                                i2++;
                                if (i == 0 || i2 <= i) {
                                    sb.append("  ");
                                    sb.append(trim);
                                    sb.append("\n");
                                }
                            }
                        } catch (Exception e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            InterfaceC6794h m8519d = NBSNativeCrash.m8519d();
                            m8519d.mo8437c("nbscrash", "Util getInfo(" + str + ") failed", e);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception unused) {
                                }
                            }
                            throw th;
                        }
                    }
                    if (i > 0 && i2 > i) {
                        sb.append("  ......\n");
                        sb.append("  (number of records: ");
                        sb.append(i2);
                        sb.append(")\n");
                    }
                    bufferedReader2.close();
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception unused2) {
                return sb.toString();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m8382a(String str) {
        File file = new File(str);
        try {
            if (!file.exists()) {
                file.mkdirs();
                return file.exists() && file.isDirectory();
            }
            return file.isDirectory();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m8383a(Context context, long j) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        int myPid = Process.myPid();
        long j2 = j / 500;
        for (int i = 0; i < j2; i++) {
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.pid == myPid && processErrorStateInfo.condition == 2) {
                        return true;
                    }
                }
            }
            try {
                Thread.sleep(500L);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m8380a(Date date, Date date2, String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        StringBuilder sb = new StringBuilder();
        sb.append("*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***\nTombstone maker: 'NBSNativeCrash 2.0.2.1'\nCrash type: '");
        sb.append(str);
        sb.append("'\nStart time: '");
        sb.append(simpleDateFormat.format(date));
        sb.append("'\nCrash time: '");
        sb.append(simpleDateFormat.format(date2));
        sb.append("'\nApp ID: '");
        sb.append(str2);
        sb.append("'\nApp version: '");
        sb.append(str3);
        sb.append("'\nRooted: '");
        sb.append(m8388a() ? "Yes" : "No");
        sb.append("'\nAPI level: '");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("'\nOS version: '");
        sb.append(Build.VERSION.RELEASE);
        sb.append("'\nABI list: '");
        sb.append(m8379b());
        sb.append("'\nManufacturer: '");
        sb.append(Build.MANUFACTURER);
        sb.append("'\nBrand: '");
        sb.append(Build.BRAND);
        sb.append("'\nModel: '");
        sb.append(Build.MODEL);
        sb.append("'\nBuild fingerprint: '");
        sb.append(Build.FINGERPRINT);
        sb.append("'\n");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static String m8376d() {
        return "memory info:\n System Summary (From: /proc/meminfo)\n" + m8378b("/proc/meminfo") + "-\n Process Status (From: /proc/PID/status)\n" + m8378b("/proc/self/status") + "-\n Process Limits (From: /proc/PID/limits)\n" + m8378b("/proc/self/limits") + "-\n" + m8377c() + "\n";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static String m8375e() {
        if (Build.VERSION.SDK_INT >= 29) {
            return "network info:\nNot supported on Android Q (API level 29) and later.\n\n";
        }
        return "network info:\n TCP over IPv4 (From: /proc/PID/net/tcp)\n" + m8381a("/proc/self/net/tcp", 1024) + "-\n TCP over IPv6 (From: /proc/PID/net/tcp6)\n" + m8381a("/proc/self/net/tcp6", 1024) + "-\n UDP over IPv4 (From: /proc/PID/net/udp)\n" + m8381a("/proc/self/net/udp", 1024) + "-\n UDP over IPv6 (From: /proc/PID/net/udp6)\n" + m8381a("/proc/self/net/udp6", 1024) + "-\n ICMP in IPv4 (From: /proc/PID/net/icmp)\n" + m8381a("/proc/self/net/icmp", 256) + "-\n ICMP in IPv6 (From: /proc/PID/net/icmp6)\n" + m8381a("/proc/self/net/icmp6", 256) + "-\n UNIX domain (From: /proc/PID/net/unix)\n" + m8381a("/proc/self/net/unix", 256) + "\n";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static String m8374f() {
        StringBuilder sb = new StringBuilder("open files:\n");
        try {
            File[] listFiles = new File("/proc/self/fd").listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.n.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    return TextUtils.isDigitsOnly(str);
                }
            });
            if (listFiles != null) {
                int i = 0;
                for (File file : listFiles) {
                    String str = null;
                    try {
                        if (Build.VERSION.SDK_INT >= 21) {
                            str = Os.readlink(file.getAbsolutePath());
                        } else {
                            str = file.getCanonicalPath();
                        }
                    } catch (Exception unused) {
                    }
                    sb.append("    fd ");
                    sb.append(file.getName());
                    sb.append(": ");
                    sb.append(TextUtils.isEmpty(str) ? "???" : str.trim());
                    sb.append('\n');
                    i++;
                    if (i > 1024) {
                        break;
                    }
                }
                if (listFiles.length > 1024) {
                    sb.append("    ......\n");
                }
                sb.append("    (number of FDs: ");
                sb.append(listFiles.length);
                sb.append(")\n");
            }
        } catch (Exception unused2) {
        }
        sb.append('\n');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m8387a(int i, int i2, int i3) {
        int myPid = Process.myPid();
        StringBuilder sb = new StringBuilder();
        sb.append("logcat:\n");
        if (i > 0) {
            m8386a(myPid, sb, "main", i, 'D');
        }
        if (i2 > 0) {
            m8386a(myPid, sb, "system", i2, 'W');
        }
        if (i3 > 0) {
            m8386a(myPid, sb, "events", i2, 'I');
        }
        sb.append("\n");
        return sb.toString();
    }

    /* renamed from: a */
    private static void m8386a(int i, StringBuilder sb, String str, int i2, char c) {
        boolean z = Build.VERSION.SDK_INT >= 24;
        String num = Integer.toString(i);
        String str2 = " " + num + " ";
        ArrayList arrayList = new ArrayList();
        arrayList.add("/system/bin/logcat");
        arrayList.add("-b");
        arrayList.add(str);
        arrayList.add("-d");
        arrayList.add("-v");
        arrayList.add("threadtime");
        arrayList.add("-t");
        if (!z) {
            i2 = (int) (i2 * 1.2d);
        }
        arrayList.add(Integer.toString(i2));
        if (z) {
            arrayList.add("--pid");
            arrayList.add(num);
        }
        arrayList.add("*:" + c);
        Object[] array = arrayList.toArray();
        sb.append("--------- tail end of log ");
        sb.append(str);
        sb.append(" (");
        sb.append(TextUtils.join(" ", array));
        sb.append(")\n");
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new ProcessBuilder(new String[0]).command(arrayList).start().getInputStream()));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            } else if (z || readLine.contains(str2)) {
                                sb.append(readLine);
                                sb.append("\n");
                            }
                        } catch (Exception e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            NBSNativeCrash.m8519d().mo8435d("nbscrash", "Util run logcat command failed", e);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException unused) {
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (IOException unused2) {
        }
    }
}
