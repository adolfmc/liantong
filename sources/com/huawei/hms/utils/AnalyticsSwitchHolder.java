package com.huawei.hms.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AndroidException;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.stats.AnalyticsCacheManager;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import java.sql.Timestamp;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AnalyticsSwitchHolder {
    public static final int ANALYTICS_DISABLED = 2;
    public static final int ANALYTICS_ENABLED = 1;

    /* renamed from: a */
    private static volatile int f11794a;

    /* renamed from: b */
    private static final Object f11795b = new Object();

    /* renamed from: c */
    private static volatile Long f11796c = 0L;

    /* renamed from: d */
    private static volatile boolean f11797d = false;

    /* renamed from: e */
    private static volatile boolean f11798e = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.utils.AnalyticsSwitchHolder$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5087a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f11799a;

        RunnableC5087a(Context context) {
            this.f11799a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            AnalyticsSwitchHolder.m14093f(this.f11799a);
            HMSLog.m14110i("AnalyticsSwitchHolder", "getStateForHmsAnalyticsProvider");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.utils.AnalyticsSwitchHolder$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5088b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f11800a;

        RunnableC5088b(Context context) {
            this.f11800a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.m14110i("AnalyticsSwitchHolder", "enter setAnalyticsStateAndTimestamp");
            AnalyticsSwitchHolder.m14093f(this.f11800a);
            HMSLog.m14110i("AnalyticsSwitchHolder", "quit setAnalyticsStateAndTimestamp");
        }
    }

    /* renamed from: b */
    private static boolean m14097b(Context context) {
        Bundle bundle;
        if (context == null) {
            HMSLog.m14112e("AnalyticsSwitchHolder", "In getBiIsReportSetting, context is null.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
                if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                    return bundle.getBoolean("com.huawei.hms.client.bireport.setting");
                }
            } catch (AndroidException unused) {
                HMSLog.m14112e("AnalyticsSwitchHolder", "In getBiIsReportSetting, Failed to read meta data bi report setting.");
            } catch (RuntimeException e) {
                HMSLog.m14111e("AnalyticsSwitchHolder", "In getBiIsReportSetting, Failed to read meta data bi report setting.", e);
            }
        }
        HMSLog.m14110i("AnalyticsSwitchHolder", "In getBiIsReportSetting, configuration not found for bi report setting.");
        return false;
    }

    /* renamed from: c */
    private static void m14096c(Context context) {
        f11796c = Long.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
        new Thread(new RunnableC5087a(context), "Thread-getStateForHmsAnalyticsProvider").start();
    }

    /* renamed from: d */
    private static boolean m14095d(Context context) {
        return "CN".equalsIgnoreCase(GrsApp.getInstance().getIssueCountryCode(context));
    }

    /* renamed from: e */
    private static void m14094e(Context context) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (timestamp.getTime() - f11796c.longValue() < 86400000 || f11796c.longValue() <= 0) {
            return;
        }
        f11796c = Long.valueOf(timestamp.getTime());
        new Thread(new RunnableC5088b(context), "Thread-refreshOobeAnalyticsState").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public static void m14093f(Context context) {
        if (context == null) {
            HMSLog.m14112e("AnalyticsSwitchHolder", "In setAnalyticsState、, context is null.");
        } else if (HiAnalyticsUtils.getInstance().getOobeAnalyticsState(context) == 1) {
            synchronized (f11795b) {
                f11794a = 1;
            }
            if (HiAnalyticsUtils.getInstance().getInitFlag() || f11797d) {
                return;
            }
            HMSBIInitializer.getInstance(context).initHaSDK();
            f11797d = true;
        } else {
            synchronized (f11795b) {
                f11794a = 2;
            }
            AnalyticsCacheManager.m14174c().m14177a();
        }
    }

    public static int getAndRefreshAnalyticsState(Context context) {
        int i;
        synchronized (f11795b) {
            isAnalyticsDisabled(context);
            i = f11794a;
        }
        return i;
    }

    public static boolean getBiSetting(Context context) {
        Bundle bundle;
        if (context == null) {
            HMSLog.m14112e("AnalyticsSwitchHolder", "In getBiSetting, context is null.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
                if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                    return bundle.getBoolean("com.huawei.hms.client.bi.setting");
                }
            } catch (AndroidException unused) {
                HMSLog.m14112e("AnalyticsSwitchHolder", "In getBiSetting, Failed to read meta data bisetting.");
            } catch (RuntimeException e) {
                HMSLog.m14111e("AnalyticsSwitchHolder", "In getBiSetting, Failed to read meta data bisetting.", e);
            }
        }
        HMSLog.m14110i("AnalyticsSwitchHolder", "In getBiSetting, configuration not found for bisetting.");
        return false;
    }

    public static boolean isAnalyticsDisabled(Context context) {
        synchronized (f11795b) {
            if (f11794a == 0) {
                if (context == null) {
                    return true;
                }
                if (m14097b(context)) {
                    HMSLog.m14110i("AnalyticsSwitchHolder", "Builder->biReportSetting :true");
                    f11794a = 1;
                } else if (getBiSetting(context)) {
                    HMSLog.m14110i("AnalyticsSwitchHolder", "Builder->biSetting :true");
                    f11794a = 2;
                } else if (m14095d(context)) {
                    f11794a = 1;
                } else {
                    HMSLog.m14110i("AnalyticsSwitchHolder", "not ChinaROM");
                    f11794a = 3;
                    f11798e = true;
                    m14096c(context);
                }
            } else if (f11798e) {
                m14094e(context);
            }
            return f11794a != 1;
        }
    }
}
