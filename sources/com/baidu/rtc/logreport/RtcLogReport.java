package com.baidu.rtc.logreport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Base64;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.webrtc.Logging;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RtcLogReport {
    private static final String DEFAULT_HOST = "https://rtc-log.cdn.bcebos.com/collect?message=";
    private static final String LOG = "RTCLOGREPORT";
    public static final int RTC_REPORTING_LOG_TYPE_ANTI_WEAK = 5;
    public static final int RTC_REPORTING_LOG_TYPE_DEVICE_INFO = 0;
    public static final int RTC_REPORTING_LOG_TYPE_ERROR_INFO = 4;
    public static final int RTC_REPORTING_LOG_TYPE_QUALITY_INFO = 2;
    public static final int RTC_REPORTING_LOG_TYPE_ROOM_EVENTS = 1;
    public static final int RTC_REPORTING_LOG_TYPE_SLI_INFO = 3;
    private static final String UPLOAD_ANTI_WEAK = "[RTC_ANTI_WEAK]";
    private static final String UPLOAD_ERROR_PERFFIX = "[RTC_LOG]";
    private static final String UPLOAD_PERFFIX = "[RTC_QUALITY_CONTROL]";
    private static final String UPLOAD_SLI_PERFFIX = "[RTC_SLI]";
    private static RtcLogReport instance;
    private ScheduledExecutorService executor;
    public boolean mEnableReportLog = true;
    private OkHttpClient mOkHttpClient;

    public static String getDeviceModel() {
        return "Unknown";
    }

    public static synchronized RtcLogReport getInstance() {
        RtcLogReport rtcLogReport;
        synchronized (RtcLogReport.class) {
            if (instance == null) {
                instance = new RtcLogReport();
            }
            rtcLogReport = instance;
        }
        return rtcLogReport;
    }

    private RtcLogReport() {
        OkHttpClient.Builder writeTimeout = NBSOkHttp3Instrumentation.init().newBuilder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS);
        this.mOkHttpClient = !(writeTimeout instanceof OkHttpClient.Builder) ? writeTimeout.build() : NBSOkHttp3Instrumentation.builderInit(writeTimeout);
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public synchronized void report(final String str, final int i) {
        this.executor.execute(new Runnable() { // from class: com.baidu.rtc.logreport.RtcLogReport.1
            @Override // java.lang.Runnable
            public void run() {
                if (RtcLogReport.this.mEnableReportLog) {
                    Logging.m5305d("RTCLOGREPORT", "report msg: " + str);
                }
                RtcLogReport.this.sendGet(str, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendGet(String str, int i) {
        String str2 = i == 3 ? "[RTC_SLI]" : "[RTC_QUALITY_CONTROL]";
        if (i == 4) {
            str2 = "[RTC_LOG]";
        }
        if (i == 5) {
            str2 = "[RTC_ANTI_WEAK]";
        }
        String encodeToString = Base64.encodeToString((str2 + str).getBytes(), 2);
        Request.Builder builder = new Request.Builder();
        this.mOkHttpClient.newCall(builder.url("https://rtc-log.cdn.bcebos.com/collect?message=" + encodeToString).build()).enqueue(new Callback() { // from class: com.baidu.rtc.logreport.RtcLogReport.2
            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Logging.m5304e("RTCLOGREPORT", "qualityinfo send fail: " + iOException);
            }
        });
    }

    public static String getNetworkType(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        int i;
        NetworkInfo.State state;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return "null";
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo == null || (state = networkInfo.getState()) == null || !(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            if (Build.VERSION.SDK_INT < 30) {
                i = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
            } else {
                i = 0;
                Logging.m5301w("RTCLOGREPORT", "Api level higher than Android R do not get networkType on longer.");
            }
            switch (i) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return "3G";
                case 13:
                    return "4G";
                default:
                    return "MOBILE";
            }
        }
        return "wifi";
    }

    public void release() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.executor = null;
        }
    }
}
