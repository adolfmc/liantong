package com.megvii.safe.lv5;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import com.megvii.lv5.C5443f5;
import com.megvii.lv5.C5456g5;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CommonProtectorManager {
    public static List<String> checkAppList(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            boolean z = true;
            try {
                context.getPackageManager().getPackageInfo(list.get(i), 1);
            } catch (PackageManager.NameNotFoundException unused) {
                z = false;
            }
            if (z) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    public static int checkCharggingLevel(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return -1;
        }
        return registerReceiver.getIntExtra("level", -1);
    }

    public static int checkCharggingType(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return -1;
        }
        return registerReceiver.getIntExtra("plugged", -1);
    }

    public static boolean checkFileExist(String str) {
        return new File(str).exists();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
        if (r3 == null) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:6:0x001b A[Catch: all -> 0x0035, Exception -> 0x0040, TryCatch #6 {Exception -> 0x0040, all -> 0x0035, blocks: (B:4:0x0015, B:6:0x001b, B:7:0x001f, B:9:0x0025, B:11:0x0031), top: B:31:0x0015 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> checkFilesByPath(java.lang.String r6, java.util.List<java.lang.String> r7) {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
        L15:
            java.lang.String r6 = r3.readLine()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L40
            if (r6 == 0) goto L42
            java.util.Iterator r2 = r7.iterator()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L40
        L1f:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L40
            if (r4 == 0) goto L15
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L40
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L40
            boolean r5 = r6.contains(r4)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L40
            if (r5 == 0) goto L1f
            r0.add(r4)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L40
            goto L1f
        L35:
            r6 = move-exception
            r2 = r3
            goto L39
        L38:
            r6 = move-exception
        L39:
            if (r2 == 0) goto L3e
            r2.close()     // Catch: java.io.IOException -> L3e
        L3e:
            throw r6
        L3f:
            r3 = r2
        L40:
            if (r3 == 0) goto L45
        L42:
            r3.close()     // Catch: java.io.IOException -> L45
        L45:
            r1.addAll(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.safe.lv5.CommonProtectorManager.checkFilesByPath(java.lang.String, java.util.List):java.util.List");
    }

    public static boolean checkIsChargging(Context context) {
        int intExtra;
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return registerReceiver != null && ((intExtra = registerReceiver.getIntExtra("status", -1)) == 2 || intExtra == 5);
    }

    public static List<String> exec_cus(Context context, String str) {
        return C5443f5.C5444a.f12607a.m13531a(context, str);
    }

    public static List<String> getCustomProperty(Context context, String str) {
        return C5443f5.C5444a.f12607a.m13530a(context, str, null);
    }

    public static List<String> getCustomProperty(Context context, String str, List<String> list) {
        return C5443f5.C5444a.f12607a.m13530a(context, str, list);
    }

    public static String getProperty(String str) {
        String str2;
        Object invoke;
        try {
            invoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Throwable unused) {
        }
        if (invoke != null) {
            str2 = (String) invoke;
            if (str.equals("ro.secure") || str2 == null) {
                return str2;
            }
            if ("0".equals(str2)) {
                return "0";
            }
            return null;
        }
        str2 = null;
        if (str.equals("ro.secure")) {
        }
        return str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007d A[EDGE_INSN: B:36:0x007d->B:28:0x007d ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isRoot() {
        /*
            java.lang.String r0 = "ro.secure"
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r4 = "android.os.SystemProperties"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch: java.lang.Throwable -> L24
            java.lang.String r5 = "get"
            java.lang.Class[] r6 = new java.lang.Class[r3]
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r2] = r7
            java.lang.reflect.Method r4 = r4.getMethod(r5, r6)
            java.lang.Object[] r5 = new java.lang.Object[r3]
            r5[r2] = r0
            java.lang.Object r0 = r4.invoke(r1, r5)
            if (r0 == 0) goto L24
            java.lang.String r0 = (java.lang.String) r0
            goto L25
        L24:
            r0 = r1
        L25:
            if (r0 != 0) goto L28
            goto L32
        L28:
            java.lang.String r4 = "0"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L32
            r0 = r2
            goto L33
        L32:
            r0 = r3
        L33:
            if (r0 != 0) goto L36
            goto L8f
        L36:
            java.lang.String r0 = "/system/bin/busybox"
            java.io.File r4 = new java.io.File
            r4.<init>(r0)
            boolean r0 = r4.exists()
            if (r0 == 0) goto L44
            goto L8f
        L44:
            java.lang.String r0 = "/system/xbin/busybox"
            java.io.File r4 = new java.io.File
            r4.<init>(r0)
            boolean r0 = r4.exists()
            if (r0 == 0) goto L52
            goto L8f
        L52:
            r0 = 8
            java.lang.String r4 = "/sbin/su"
            java.lang.String r5 = "/system/bin/su"
            java.lang.String r6 = "/system/xbin/su"
            java.lang.String r7 = "/data/local/xbin/su"
            java.lang.String r8 = "/data/local/bin/su"
            java.lang.String r9 = "/system/sd/xbin/su"
            java.lang.String r10 = "/system/bin/failsafe/su"
            java.lang.String r11 = "/data/local/su"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7, r8, r9, r10, r11}
            r5 = r2
        L69:
            if (r5 >= r0) goto L7d
            r6 = r4[r5]
            java.io.File r7 = new java.io.File
            r7.<init>(r6)
            boolean r6 = r7.exists()
            if (r6 == 0) goto L7a
            r2 = r3
            goto L7d
        L7a:
            int r5 = r5 + 1
            goto L69
        L7d:
            if (r2 == 0) goto L80
            goto L8f
        L80:
            com.megvii.lv5.f5 r0 = com.megvii.lv5.C5443f5.C5444a.f12607a
            java.lang.String r2 = "FIND_SU_BY_WHICH"
            java.util.List r0 = r0.m13531a(r1, r2)
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            boolean r0 = r0.isEmpty()
            r3 = r3 ^ r0
        L8f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.safe.lv5.CommonProtectorManager.isRoot():boolean");
    }

    public static boolean isVertical(Context context) {
        C5456g5 c5456g5 = C5456g5.C5457a.f12693a;
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                c5456g5.f12690a = sensorManager;
                Sensor defaultSensor = sensorManager.getDefaultSensor(1);
                c5456g5.f12691b = defaultSensor;
                if (defaultSensor != null) {
                    c5456g5.f12690a.registerListener(c5456g5, defaultSensor, 3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return C5456g5.C5457a.f12693a.f12692c >= 8.0f;
    }

    public static void releaseSenso() {
        SensorManager sensorManager;
        C5456g5 c5456g5 = C5456g5.C5457a.f12693a;
        try {
            if (c5456g5.f12691b == null || (sensorManager = c5456g5.f12690a) == null) {
                return;
            }
            sensorManager.unregisterListener(c5456g5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
