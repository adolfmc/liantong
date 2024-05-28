package com.huawei.hms.push;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: CommFun.java */
/* renamed from: com.huawei.hms.push.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC5048c {

    /* renamed from: a */
    private static final Object f11621a = new Object();

    /* renamed from: b */
    private static int f11622b = -1;

    /* renamed from: a */
    private static boolean m14295a(Context context) {
        HMSLog.m14115d("CommFun", "existFrameworkPush:" + f11622b);
        try {
            File file = new File("/system/framework/hwpush.jar");
            if (m14296a()) {
                HMSLog.m14115d("CommFun", "push jarFile is exist");
            } else if (!file.isFile()) {
                return false;
            } else {
                HMSLog.m14115d("CommFun", "push jarFile is exist");
            }
            return true;
        } catch (Exception e) {
            HMSLog.m14112e("CommFun", "get Apk version faild ,Exception e= " + e.toString());
            return false;
        }
    }

    /* renamed from: b */
    public static long m14292b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.huawei.android.pushagent", 16384).versionCode;
        } catch (Exception unused) {
            HMSLog.m14112e("CommFun", "get nc versionCode error");
            return -1L;
        }
    }

    /* renamed from: c */
    public static boolean m14291c() {
        return HwBuildEx.VERSION.EMUI_SDK_INT < 19;
    }

    /* renamed from: d */
    public static boolean m14289d(Context context) {
        HMSLog.m14115d("CommFun", "existFrameworkPush:" + f11622b);
        synchronized (f11621a) {
            int i = f11622b;
            if (-1 != i) {
                return 1 == i;
            }
            if (m14295a(context)) {
                f11622b = 1;
            } else {
                f11622b = 0;
            }
            return 1 == f11622b;
        }
    }

    /* renamed from: c */
    public static String m14290c(Context context) {
        return AGConnectServicesConfig.fromContext(context).getString("client/project_id");
    }

    /* renamed from: b */
    public static boolean m14293b() {
        return HwBuildEx.VERSION.EMUI_SDK_INT >= 21;
    }

    /* renamed from: a */
    private static boolean m14296a() {
        try {
            Class<?> cls = Class.forName("huawei.cust.HwCfgFilePolicy");
            File file = (File) cls.getDeclaredMethod("getCfgFile", String.class, Integer.TYPE).invoke(cls, "jars/hwpush.jar", Integer.valueOf(((Integer) cls.getDeclaredField("CUST_TYPE_CONFIG").get(cls)).intValue()));
            if (file != null && file.exists()) {
                HMSLog.m14115d("CommFun", "get push cust File path success.");
                return true;
            }
        } catch (ClassNotFoundException unused) {
            HMSLog.m14112e("CommFun", "HwCfgFilePolicy ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            HMSLog.m14112e("CommFun", "check cust exist push IllegalAccessException.");
        } catch (IllegalArgumentException unused3) {
            HMSLog.m14112e("CommFun", "check cust exist push IllegalArgumentException.");
        } catch (NoSuchFieldException unused4) {
            HMSLog.m14112e("CommFun", "check cust exist push NoSuchFieldException.");
        } catch (NoSuchMethodException unused5) {
            HMSLog.m14112e("CommFun", "check cust exist push NoSuchMethodException.");
        } catch (SecurityException unused6) {
            HMSLog.m14112e("CommFun", "check cust exist push SecurityException.");
        } catch (InvocationTargetException unused7) {
            HMSLog.m14112e("CommFun", "check cust exist push InvocationTargetException.");
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m14294a(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        return jSONObject == null || (TextUtils.isEmpty(str) && jSONObject2 == null);
    }
}
