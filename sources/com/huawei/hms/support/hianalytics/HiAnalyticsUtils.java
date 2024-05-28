package com.huawei.hms.support.hianalytics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hianalytics.util.HiAnalyticTools;
import com.huawei.hms.hatool.HmsHiAnalyticsUtils;
import com.huawei.hms.stats.AnalyticsCacheManager;
import com.huawei.hms.stats.HiAnalyticsOfCpUtils;
import com.huawei.hms.stats.HianalyticsExist;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.AnalyticsSwitchHolder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HiAnalyticsUtils {

    /* renamed from: c */
    private static final Object f11738c = new Object();

    /* renamed from: d */
    private static final Object f11739d = new Object();

    /* renamed from: e */
    private static HiAnalyticsUtils f11740e;

    /* renamed from: a */
    private int f11741a = 0;

    /* renamed from: b */
    private final boolean f11742b = HianalyticsExist.m14168a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.hianalytics.HiAnalyticsUtils$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5074a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f11743a;

        /* renamed from: b */
        final /* synthetic */ String f11744b;

        /* renamed from: c */
        final /* synthetic */ Map f11745c;

        RunnableC5074a(Context context, String str, Map map) {
            this.f11743a = context;
            this.f11744b = str;
            this.f11745c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onEvent(this.f11743a, this.f11744b, this.f11745c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.hianalytics.HiAnalyticsUtils$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5075b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f11747a;

        /* renamed from: b */
        final /* synthetic */ String f11748b;

        /* renamed from: c */
        final /* synthetic */ String f11749c;

        RunnableC5075b(Context context, String str, String str2) {
            this.f11747a = context;
            this.f11748b = str;
            this.f11749c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onEvent2(this.f11747a, this.f11748b, this.f11749c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.hianalytics.HiAnalyticsUtils$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5076c implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f11751a;

        /* renamed from: b */
        final /* synthetic */ String f11752b;

        /* renamed from: c */
        final /* synthetic */ Map f11753c;

        RunnableC5076c(Context context, String str, Map map) {
            this.f11751a = context;
            this.f11752b = str;
            this.f11753c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onNewEvent(this.f11751a, this.f11752b, this.f11753c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.hianalytics.HiAnalyticsUtils$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5077d implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f11755a;

        /* renamed from: b */
        final /* synthetic */ String f11756b;

        /* renamed from: c */
        final /* synthetic */ Map f11757c;

        /* renamed from: d */
        final /* synthetic */ int f11758d;

        RunnableC5077d(Context context, String str, Map map, int i) {
            this.f11755a = context;
            this.f11756b = str;
            this.f11757c = map;
            this.f11758d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onNewEvent(this.f11755a, this.f11756b, this.f11757c, this.f11758d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.hianalytics.HiAnalyticsUtils$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5078e implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f11760a;

        /* renamed from: b */
        final /* synthetic */ String f11761b;

        /* renamed from: c */
        final /* synthetic */ Map f11762c;

        RunnableC5078e(Context context, String str, Map map) {
            this.f11760a = context;
            this.f11761b = str;
            this.f11762c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onReport(this.f11760a, this.f11761b, this.f11762c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.hianalytics.HiAnalyticsUtils$f */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5079f implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f11764a;

        /* renamed from: b */
        final /* synthetic */ String f11765b;

        /* renamed from: c */
        final /* synthetic */ Map f11766c;

        /* renamed from: d */
        final /* synthetic */ int f11767d;

        RunnableC5079f(Context context, String str, Map map, int i) {
            this.f11764a = context;
            this.f11765b = str;
            this.f11766c = map;
            this.f11767d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAnalyticsUtils.getInstance().onReport(this.f11764a, this.f11765b, this.f11766c, this.f11767d);
        }
    }

    private HiAnalyticsUtils() {
    }

    /* renamed from: a */
    private static LinkedHashMap<String, String> m14127a(Map<String, String> map) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    /* renamed from: b */
    private void m14125b(Context context) {
        synchronized (f11739d) {
            int i = this.f11741a;
            if (i < 60) {
                this.f11741a = i + 1;
            } else {
                this.f11741a = 0;
                if (!this.f11742b) {
                    HmsHiAnalyticsUtils.onReport();
                } else {
                    HiAnalyticsOfCpUtils.m14172a(context, 0);
                    HiAnalyticsOfCpUtils.m14172a(context, 1);
                }
            }
        }
    }

    /* renamed from: c */
    private void m14121c(Context context, String str, Map map) {
        try {
            AnalyticsCacheManager.m14174c().m14176a(new RunnableC5078e(context.getApplicationContext(), str, map));
        } catch (Throwable th) {
            HMSLog.m14112e("HiAnalyticsUtils", "<addOnReportToCache> failed. " + th.getMessage());
        }
    }

    public static HiAnalyticsUtils getInstance() {
        HiAnalyticsUtils hiAnalyticsUtils;
        synchronized (f11738c) {
            if (f11740e == null) {
                f11740e = new HiAnalyticsUtils();
            }
            hiAnalyticsUtils = f11740e;
        }
        return hiAnalyticsUtils;
    }

    public static String versionCodeToName(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() == 8 || str.length() == 9) {
            try {
                Integer.parseInt(str);
                return Integer.parseInt(str.substring(0, str.length() - 7)) + "." + Integer.parseInt(str.substring(str.length() - 7, str.length() - 5)) + "." + Integer.parseInt(str.substring(str.length() - 5, str.length() - 3)) + "." + Integer.parseInt(str.substring(str.length() - 3));
            } catch (NumberFormatException unused) {
                return "";
            }
        }
        return "";
    }

    public void enableLog(Context context) {
        HMSLog.m14110i("HiAnalyticsUtils", "Enable Log");
        if (!this.f11742b) {
            HmsHiAnalyticsUtils.enableLog();
        } else {
            HiAnalyticTools.enableLog(context);
        }
    }

    public boolean getInitFlag() {
        if (!this.f11742b) {
            return HmsHiAnalyticsUtils.getInitFlag();
        }
        return HiAnalyticsManager.getInitFlag("hms_config_tag");
    }

    public int getOobeAnalyticsState(Context context) {
        if (context == null) {
            return 0;
        }
        int m14132a = m14132a(context);
        if (m14132a == 1) {
            return m14132a;
        }
        Bundle bundle = new Bundle();
        bundle.putString("hms_cp_bundle_key", "content://com.huawei.hms.contentprovider/com.huawei.hms.privacy.HmsAnalyticsStateProvider");
        try {
            Bundle call = context.getApplicationContext().getContentResolver().call(Uri.parse("content://com.huawei.hms.contentprovider"), "getAnalyticsState", (String) null, bundle);
            if (call != null) {
                m14132a = call.getInt("SWITCH_IS_CHECKED");
                HMSLog.m14110i("HiAnalyticsUtils", "get hms analyticsOobe state " + m14132a);
                return m14132a;
            }
            return m14132a;
        } catch (IllegalArgumentException unused) {
            HMSLog.m14110i("HiAnalyticsUtils", "getOobeAnalyticsState IllegalArgumentException ");
            return m14132a;
        } catch (SecurityException unused2) {
            HMSLog.m14110i("HiAnalyticsUtils", "getOobeAnalyticsState SecurityException ");
            return m14132a;
        } catch (Exception unused3) {
            HMSLog.m14110i("HiAnalyticsUtils", "getOobeAnalyticsState Exception ");
            return m14132a;
        }
    }

    public boolean hasError(Context context) {
        return AnalyticsSwitchHolder.isAnalyticsDisabled(context);
    }

    public void onBuoyEvent(Context context, String str, String str2) {
        onEvent2(context, str, str2);
    }

    public void onEvent(Context context, String str, Map<String, String> map) {
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (m14126a(initFlag, andRefreshAnalyticsState != 2, map)) {
                m14130a(context, str, map);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.f11742b) {
                    HmsHiAnalyticsUtils.onEvent(0, str, m14127a(map));
                    HmsHiAnalyticsUtils.onEvent(1, str, m14127a(map));
                } else {
                    HiAnalyticsOfCpUtils.m14171a(context, 0, str, m14127a(map));
                    HiAnalyticsOfCpUtils.m14171a(context, 1, str, m14127a(map));
                }
                m14125b(context);
                return;
            }
            return;
        }
        HMSLog.m14112e("HiAnalyticsUtils", "<onEvent> map or context is null, state: " + andRefreshAnalyticsState);
    }

    public void onEvent2(Context context, String str, String str2) {
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (context == null) {
            HMSLog.m14112e("HiAnalyticsUtils", "<onEvent2> context is null, state: " + andRefreshAnalyticsState);
            return;
        }
        boolean initFlag = getInitFlag();
        if (!initFlag && andRefreshAnalyticsState != 2 && m14128a(str2)) {
            m14131a(context, str, str2);
        }
        if (andRefreshAnalyticsState == 1 && initFlag) {
            if (!this.f11742b) {
                HmsHiAnalyticsUtils.onEvent(context, str, str2);
            } else {
                HiAnalyticsOfCpUtils.m14170a(context, str, str2);
            }
        }
    }

    public void onNewEvent(Context context, String str, Map map) {
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (m14126a(initFlag, andRefreshAnalyticsState != 2, map)) {
                m14124b(context, str, map);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.f11742b) {
                    HmsHiAnalyticsUtils.onEvent(0, str, m14127a(map));
                    HmsHiAnalyticsUtils.onEvent(1, str, m14127a(map));
                } else {
                    HiAnalyticsOfCpUtils.m14171a(context, 0, str, m14127a(map));
                    HiAnalyticsOfCpUtils.m14171a(context, 1, str, m14127a(map));
                }
                m14125b(context);
                return;
            }
            return;
        }
        HMSLog.m14112e("HiAnalyticsUtils", "<onNewEvent> map or context is null, state: " + andRefreshAnalyticsState);
    }

    public void onReport(Context context, String str, Map map) {
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (m14126a(initFlag, andRefreshAnalyticsState != 2, map)) {
                m14121c(context, str, map);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.f11742b) {
                    HmsHiAnalyticsUtils.onStreamEvent(0, str, m14127a(map));
                    HmsHiAnalyticsUtils.onStreamEvent(1, str, m14127a(map));
                    return;
                }
                HiAnalyticsOfCpUtils.m14169b(context, 0, str, m14127a(map));
                HiAnalyticsOfCpUtils.m14169b(context, 1, str, m14127a(map));
                return;
            }
            return;
        }
        HMSLog.m14112e("HiAnalyticsUtils", "<onReport> map or context is null, state: " + andRefreshAnalyticsState);
    }

    public void enableLog() {
        HMSLog.m14110i("HiAnalyticsUtils", "Enable Log");
        if (!this.f11742b) {
            HmsHiAnalyticsUtils.enableLog();
        } else {
            HMSLog.m14110i("HiAnalyticsUtils", "cp needs to pass in the context, this method is not supported");
        }
    }

    /* renamed from: a */
    private int m14132a(Context context) {
        int i = 0;
        try {
            i = Settings.Secure.getInt(context.getContentResolver(), "hw_app_analytics_state");
            HMSLog.m14110i("HiAnalyticsUtils", "getOobeStateForSettings value is " + i);
            return i;
        } catch (Settings.SettingNotFoundException e) {
            HMSLog.m14110i("HiAnalyticsUtils", "Settings.SettingNotFoundException " + e.getMessage());
            return i;
        }
    }

    /* renamed from: a */
    private boolean m14126a(boolean z, boolean z2, Map<?, ?> map) {
        return !z && z2 && m14122b(map);
    }

    /* renamed from: a */
    private boolean m14128a(String str) {
        if (str == null) {
            return false;
        }
        try {
            return str.getBytes(Charset.forName("UTF-8")).length <= 512;
        } catch (Throwable th) {
            HMSLog.m14112e("HiAnalyticsUtils", "<isValidSize String> Exception: " + th.getMessage());
            return false;
        }
    }

    /* renamed from: b */
    private boolean m14122b(Map<?, ?> map) {
        try {
            long j = 0;
            for (Object obj : map.values()) {
                if (obj instanceof String) {
                    j += ((String) obj).getBytes(Charset.forName("UTF-8")).length;
                }
            }
            return j <= 512;
        } catch (Throwable th) {
            HMSLog.m14112e("HiAnalyticsUtils", "<isValidSize map> Exception: " + th.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private void m14130a(Context context, String str, Map<String, String> map) {
        try {
            AnalyticsCacheManager.m14174c().m14176a(new RunnableC5074a(context.getApplicationContext(), str, map));
        } catch (Throwable th) {
            HMSLog.m14112e("HiAnalyticsUtils", "<addOnEventToCache> failed. " + th.getMessage());
        }
    }

    /* renamed from: b */
    private void m14124b(Context context, String str, Map map) {
        try {
            AnalyticsCacheManager.m14174c().m14176a(new RunnableC5076c(context.getApplicationContext(), str, map));
        } catch (Throwable th) {
            HMSLog.m14112e("HiAnalyticsUtils", "<addOnNewEventToCache> failed. " + th.getMessage());
        }
    }

    public void onReport(Context context, String str, Map map, int i) {
        if (i != 0 && i != 1) {
            HMSLog.m14112e("HiAnalyticsUtils", "<onReport with type> Data reporting type is not supported");
            return;
        }
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (m14126a(initFlag, andRefreshAnalyticsState != 2, map)) {
                m14123b(context, str, map, i);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.f11742b) {
                    HmsHiAnalyticsUtils.onStreamEvent(i, str, m14127a(map));
                    return;
                } else {
                    HiAnalyticsOfCpUtils.m14169b(context, i, str, m14127a(map));
                    return;
                }
            }
            return;
        }
        HMSLog.m14112e("HiAnalyticsUtils", "<onReport with type> map or context is null, state: " + andRefreshAnalyticsState);
    }

    /* renamed from: a */
    private void m14131a(Context context, String str, String str2) {
        try {
            AnalyticsCacheManager.m14174c().m14176a(new RunnableC5075b(context.getApplicationContext(), str, str2));
        } catch (Throwable th) {
            HMSLog.m14112e("HiAnalyticsUtils", "<addOnEvent2ToCache> Failed. " + th.getMessage());
        }
    }

    public void onNewEvent(Context context, String str, Map map, int i) {
        if (i != 0 && i != 1) {
            HMSLog.m14112e("HiAnalyticsUtils", "<onNewEvent with type> Data reporting type is not supported");
            return;
        }
        int andRefreshAnalyticsState = AnalyticsSwitchHolder.getAndRefreshAnalyticsState(context);
        if (map != null && !map.isEmpty() && context != null) {
            boolean initFlag = getInitFlag();
            if (m14126a(initFlag, andRefreshAnalyticsState != 2, map)) {
                m14129a(context, str, map, i);
            }
            if (andRefreshAnalyticsState == 1 && initFlag) {
                if (!this.f11742b) {
                    HmsHiAnalyticsUtils.onEvent(i, str, m14127a(map));
                } else {
                    HiAnalyticsOfCpUtils.m14171a(context, i, str, m14127a(map));
                }
                m14125b(context);
                return;
            }
            return;
        }
        HMSLog.m14112e("HiAnalyticsUtils", "<onNewEvent with type> map or context is null, state: " + andRefreshAnalyticsState);
    }

    /* renamed from: b */
    private void m14123b(Context context, String str, Map map, int i) {
        try {
            AnalyticsCacheManager.m14174c().m14176a(new RunnableC5079f(context.getApplicationContext(), str, map, i));
        } catch (Throwable th) {
            HMSLog.m14112e("HiAnalyticsUtils", "<addOnReportToCache with type> failed. " + th.getMessage());
        }
    }

    /* renamed from: a */
    private void m14129a(Context context, String str, Map map, int i) {
        try {
            AnalyticsCacheManager.m14174c().m14176a(new RunnableC5077d(context.getApplicationContext(), str, map, i));
        } catch (Throwable th) {
            HMSLog.m14112e("HiAnalyticsUtils", "<addOnNewEventToCache with type> failed. " + th.getMessage());
        }
    }
}
