package com.bytedance.applog;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.k2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3615k2 {

    /* renamed from: a */
    public static int f8537a;

    /* renamed from: b */
    public static String[] f8538b = {"goldfish"};

    /* renamed from: c */
    public static final String[] f8539c = {"000000000000000", "012345678912345"};

    /* JADX WARN: Code restructure failed: missing block: B:49:0x012c, code lost:
        if (r8 < 0) goto L30;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m17248a(android.content.Context r19, org.json.JSONObject r20, com.bytedance.applog.C3735y r21) {
        /*
            Method dump skipped, instructions count: 1073
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3615k2.m17248a(android.content.Context, org.json.JSONObject, com.bytedance.applog.y):void");
    }

    /* renamed from: a */
    public static boolean m17250a() {
        boolean isUserAMonkey = ActivityManager.isUserAMonkey();
        return Build.VERSION.SDK_INT >= 11 ? isUserAMonkey || ActivityManager.isRunningInTestHarness() : isUserAMonkey;
    }

    /* renamed from: a */
    public static boolean m17249a(Context context) {
        for (Sensor sensor : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
            if (sensor.getType() == 9) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m17247a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        try {
            new FileInputStream(file);
            return true;
        } catch (FileNotFoundException e) {
            return !e.getMessage().contains("No such file or directory");
        }
    }
}
