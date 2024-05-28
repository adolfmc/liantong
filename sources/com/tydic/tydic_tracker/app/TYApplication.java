package com.tydic.tydic_tracker.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.WindowManager;
import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;
import com.tydic.tydic_tracker.tydicDB.TyAnrEntity;
import com.tydic.tydic_tracker.tydicDB.TyBreakdownsEntity;
import com.tydic.tydic_tracker.tydicDB.TyDB;
import com.tydic.tydic_tracker.tydicDB.TyEventEntity;
import com.tydic.tydic_tracker.tydicDB.TyRequestEntity;
import com.tydic.tydic_tracker.tydicDB.TyResourceEntity;
import com.tydic.tydic_tracker.tydicDB.TySessionEntity;
import com.tydic.tydic_tracker.tydicDB.TyStartupsEntity;
import com.tydic.tydic_tracker.tydicDB.TyViewEntity;
import com.tydic.tydic_tracker.tydicDB.TyWebHttpEntity;
import java.io.PrintWriter;
import java.io.StringWriter;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TYApplication {
    public static final String TAG = "tyLog";
    public static String access = "";
    public static String app_key = "wS0n2SF8WRDB0iOsCaTCO**BpxQfmmgrWePMkg17wVlGOfKDohyLqHcxuEa0IEdsh";
    private static Context context = null;
    public static String dev_val = "";
    public static String env = "";
    public static int is_new = 0;
    public static String ses_id = null;
    private static long startMilli = 0;
    public static TimeLoop timeLoop = null;
    public static String uid = "";
    private boolean networkState;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class ImageDataType {
        public static final String pubuliuCode = "unicomApp_waterfall_flow_image";
        public static final String pubuliuName = "联通APP-瀑布流图片加载指标";
        public static final String wurukouCode = "unicomApp_five_entrance_image";
        public static final String wurukouName = "联通APP-五入口图片加载指标";
    }

    public static void sendAllData() {
    }

    public static long getTimeStame() {
        return System.currentTimeMillis();
    }

    public static JSONObject getBasic(String str, String str2, String str3, String str4, String str5) throws JSONException {
        String networkState = KqwNetworkUtil.getNetworkState(context);
        long currentTimeMillis = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("access", networkState);
        jSONObject.put("app_key", "wS0n2SF8WRDB0iOsCaTCO**BpxQfmmgrWePMkg17wVlGOfKDohyLqHcxuEa0IEdsh");
        jSONObject.put("uid", str5);
        jSONObject.put("is_new", is_new);
        jSONObject.put("dev_val", dev_val);
        jSONObject.put("cpu_used", 0);
        jSONObject.put("mem_used", MemoryTools.getMemoryFree(context));
        try {
            jSONObject.put("mem_free", Long.parseLong(DeviceHelper.leftMem(DeviceHelper.getMemInfo())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        jSONObject.put("root", RootUtil.isDeviceRooted() ? 1 : 0);
        jSONObject.put("orientation", 1);
        jSONObject.put("device_type", 0);
        jSONObject.put("dump_energy", DeviceHelper.getBatteryLevel(context));
        jSONObject.put("useful_space", 0);
        jSONObject.put("cpu_model", CpuManager.getCpuName());
        jSONObject.put("os", "Android");
        jSONObject.put("branch", DeviceHelper.getDeviceOSVersion());
        jSONObject.put("device_name", DeviceHelper.getDeviceBranD());
        jSONObject.put("device_version", DeviceHelper.getDeviceModel());
        jSONObject.put("operator", String.valueOf(DeviceHelper.getCarrierType(context)));
        int width = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
        int height = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
        jSONObject.put("screen", width + "x" + height);
        jSONObject.put("os_enum", 2);
        jSONObject.put("current_time", currentTimeMillis);
        jSONObject.put("country", str);
        jSONObject.put("province", str2);
        jSONObject.put("city", str3);
        jSONObject.put("version", str4);
        return jSONObject;
    }

    public static void clearTable() {
        try {
            TyDB tyDB = new TyDB();
            tyDB.OpenUserDb(context);
            tyDB.clearTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancleTime() {
        timeLoop.cancleTime();
    }

    public void init(Context context2, String str) {
        context = context2;
        startMilli = getTimeStame();
        env = str;
    }

    public static void setHotStartTime() {
        startMilli = getTimeStame();
    }

    public static void collectCrash(Throwable th) {
        int i;
        String str = "";
        String stackTraceInfo = getStackTraceInfo(th);
        String cls = th.getClass().toString();
        long currTime = TYUtil.getCurrTime();
        long currTime2 = TYUtil.getCurrTime();
        if (Thread.currentThread().getStackTrace().length > 0) {
            str = th.getStackTrace()[1].getClassName();
            i = th.getStackTrace()[1].getLineNumber();
            th.getStackTrace()[1].getMethodName();
        } else {
            i = 0;
        }
        TyDB tyDB = new TyDB();
        tyDB.OpenUserDb(context);
        TyBreakdownsEntity tyBreakdownsEntity = new TyBreakdownsEntity();
        tyBreakdownsEntity.setSes_id(ses_id);
        tyBreakdownsEntity.class_name = str;
        tyBreakdownsEntity.setLineNum(String.valueOf(i));
        tyBreakdownsEntity.setCrash_type(1);
        tyBreakdownsEntity.setTrack_details(stackTraceInfo);
        tyBreakdownsEntity.setTarget_name("");
        tyBreakdownsEntity.setEvent_tag("");
        tyBreakdownsEntity.setCrash_name(cls);
        tyBreakdownsEntity.setIs_thread_break(1);
        tyBreakdownsEntity.setTimestamp(currTime);
        tyBreakdownsEntity.setCollect_time(currTime2);
        tyBreakdownsEntity.setEvent_id("");
        tyDB.insertBreakdownData(tyBreakdownsEntity);
    }

    public static void collectCrash(int i, String str, String str2, String str3) {
        try {
            TyDB tyDB = new TyDB();
            tyDB.OpenUserDb(context);
            TyBreakdownsEntity tyBreakdownsEntity = new TyBreakdownsEntity();
            tyBreakdownsEntity.setSes_id(ses_id);
            tyBreakdownsEntity.setCrash_type(i);
            tyBreakdownsEntity.setTrack_details(str3);
            tyBreakdownsEntity.setTarget_name(str);
            tyBreakdownsEntity.setCrash_name(str2);
            tyBreakdownsEntity.setCollect_time(System.currentTimeMillis());
            tyBreakdownsEntity.setTimestamp(System.currentTimeMillis());
            tyBreakdownsEntity.setClass_name(str2);
            tyBreakdownsEntity.setMethod_name(str2);
            tyDB.insertBreakdownData(tyBreakdownsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getStackTraceInfo(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = null;
        try {
            PrintWriter printWriter2 = new PrintWriter(stringWriter);
            try {
                th.printStackTrace(printWriter2);
                printWriter2.close();
                return stringWriter.toString();
            } catch (Exception unused) {
                printWriter = printWriter2;
                if (printWriter != null) {
                    printWriter.close();
                }
                return "";
            } catch (Throwable th2) {
                th = th2;
                printWriter = printWriter2;
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static int getAllDataSize() {
        try {
            TyDB tyDB = new TyDB();
            tyDB.OpenUserDb(context);
            return tyDB.FindAllSession().length() + tyDB.FindAllStartup().length() + tyDB.FindAllBreakdown().length() + tyDB.FindAllAnr().length();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static JSONObject getAllData(String str, String str2, String str3, String str4, String str5, JSONArray jSONArray, JSONArray jSONArray2) {
        try {
            TyDB tyDB = new TyDB();
            tyDB.OpenUserDb(context);
            JSONObject jSONObject = new JSONObject();
            JSONArray FindAllSession = tyDB.FindAllSession();
            JSONArray FindAllStartup = tyDB.FindAllStartup();
            JSONArray FindAllBreakdown = tyDB.FindAllBreakdown();
            JSONArray FindAllAnr = tyDB.FindAllAnr();
            jSONObject.put("basic", getBasic(str, str2, str3, str4, str5));
            jSONObject.put("nest_sessions", FindAllSession);
            jSONObject.put("nest_startups", FindAllStartup);
            jSONObject.put("nest_event_tracking", tyDB.FindAllEvent());
            jSONObject.put("requests", tyDB.FindAllHttp());
            jSONObject.put("nest_view_tracking", tyDB.FindAllView());
            jSONObject.put("thread_breakdowns", FindAllBreakdown);
            jSONObject.put("nest_anr", FindAllAnr);
            jSONObject.put("nest_ajax_tracking", tyDB.FindAllWebHttp());
            jSONObject.put("request_infos", jSONArray);
            jSONObject.put("business_info", jSONArray2);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static void startANRWatchDog(int i) {
        if (i < 5000) {
            i = 5000;
        }
        new ANRWatchDog(i).setANRListener(new ANRWatchDog.ANRListener() { // from class: com.tydic.tydic_tracker.app.TYApplication.1
            @Override // com.github.anrwatchdog.ANRWatchDog.ANRListener
            public void onAppNotResponding(ANRError aNRError) {
                try {
                    String str = TYApplication.ses_id;
                    String random = TYUtil.getRandom();
                    long currTime = TYUtil.getCurrTime();
                    String message = aNRError.getMessage();
                    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                    StringBuilder sb = new StringBuilder();
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        sb.append(stackTraceElement.getClassName());
                        sb.append(stackTraceElement.getClassName() + stackTraceElement.getMethodName());
                    }
                    String sb2 = sb.toString();
                    TyAnrEntity tyAnrEntity = new TyAnrEntity();
                    tyAnrEntity.setSes_id(str);
                    tyAnrEntity.setAnr_id(random);
                    tyAnrEntity.setSt(currTime);
                    tyAnrEntity.setAt("ANR");
                    tyAnrEntity.setSd_raw(message);
                    tyAnrEntity.setAth(sb2);
                    TyDB tyDB = new TyDB();
                    tyDB.OpenUserDb(TYApplication.context);
                    tyDB.insertAnrData(tyAnrEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void InfoTracker(String str, String str2, int i, String str3) {
        try {
            ses_id = str3;
            uid = str;
            dev_val = str2;
            is_new = i;
            TyDB tyDB = new TyDB();
            tyDB.OpenUserDb(context);
            TySessionEntity tySessionEntity = new TySessionEntity();
            tySessionEntity.setSes_id(str3);
            tySessionEntity.setSt(TYUtil.getCurrTime());
            tyDB.insertSessionData(tySessionEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void EventTracker(String str) {
        getTimeStame();
        TyDB tyDB = new TyDB();
        tyDB.OpenUserDb(context);
        TyEventEntity tyEventEntity = new TyEventEntity();
        tyEventEntity.setSes_id(ses_id);
        tyEventEntity.setTimestamp(TYUtil.getCurrTime());
        tyEventEntity.setAction_title(str);
        tyEventEntity.setEvent_tag(str);
        tyDB.insertEventData(tyEventEntity);
    }

    public static void WebviewResource(TyResourceEntity tyResourceEntity) {
        TyDB tyDB = new TyDB();
        tyDB.OpenUserDb(context);
        tyDB.insertAllResourceData(tyResourceEntity);
    }

    public static void WebviewHttp(TyWebHttpEntity tyWebHttpEntity) {
        TyDB tyDB = new TyDB();
        tyDB.OpenUserDb(context);
        tyDB.insertAllWebHttpData(tyWebHttpEntity);
    }

    public static void ViewTracker(String str) {
        getTimeStame();
        TyDB tyDB = new TyDB();
        tyDB.OpenUserDb(context);
        TyViewEntity tyViewEntity = new TyViewEntity();
        tyViewEntity.setSes_id(ses_id);
        tyViewEntity.setPage_name(str);
        tyViewEntity.setPre_cn("");
        tyViewEntity.setTimestamp(TYUtil.getCurrTime());
        tyViewEntity.setView_event("");
        tyViewEntity.setView_class("");
        tyDB.insertViewData(tyViewEntity);
    }

    public static void setEndMilli() {
        try {
            long timeStame = getTimeStame();
            TyDB tyDB = new TyDB();
            tyDB.OpenUserDb(context);
            TyStartupsEntity tyStartupsEntity = new TyStartupsEntity();
            tyStartupsEntity.setSes_id(ses_id);
            tyStartupsEntity.setStart_milli(startMilli);
            tyStartupsEntity.setEnd_milli(timeStame);
            tyDB.insertStartupsData(tyStartupsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class HTTPListener extends EventListener {
        private long mRequestBytes;
        private long mResponseBytes;
        private int mResponseCode;
        private long requestStartTime;

        @Override // okhttp3.EventListener
        public void callStart(Call call) {
            super.callStart(call);
            this.requestStartTime = TYUtil.getCurrTime();
        }

        @Override // okhttp3.EventListener
        public void requestHeadersEnd(Call call, Request request) {
            super.requestHeadersEnd(call, request);
            this.mRequestBytes += request.headers().byteCount();
        }

        @Override // okhttp3.EventListener
        public void requestBodyEnd(Call call, long j) {
            super.requestBodyEnd(call, j);
            this.mRequestBytes += j;
        }

        @Override // okhttp3.EventListener
        public void responseHeadersEnd(Call call, Response response) {
            super.responseHeadersEnd(call, response);
            this.mResponseBytes += response.headers().byteCount();
            this.mResponseCode = response.code();
        }

        @Override // okhttp3.EventListener
        public void responseBodyEnd(Call call, long j) {
            super.responseBodyEnd(call, j);
            this.mResponseBytes += j;
        }

        @Override // okhttp3.EventListener
        public void callEnd(Call call) {
            super.callEnd(call);
            buryTag(call.request());
            saveCallEntity(call.request());
        }

        private void saveCallEntity(Request request) {
            TyRequestEntity tyRequestEntity = new TyRequestEntity();
            tyRequestEntity.ses_id = TYApplication.ses_id;
            tyRequestEntity.url = request.url().toString();
            tyRequestEntity.domain = request.url().host();
            tyRequestEntity.http_method = request.method();
            tyRequestEntity.send = (int) this.mRequestBytes;
            tyRequestEntity.reveive = (int) this.mResponseBytes;
            tyRequestEntity.execute_time = this.requestStartTime;
            if (this.mResponseCode != 200) {
                boolean okGo = TYApplication.okGo(TYApplication.context);
                tyRequestEntity.is_error = 1;
                if (okGo) {
                    tyRequestEntity.error_type = "nonet";
                } else {
                    tyRequestEntity.error_type = "http";
                }
                tyRequestEntity.error_code = String.valueOf(this.mResponseCode);
            } else {
                tyRequestEntity.is_error = 0;
                tyRequestEntity.error_type = "0";
                tyRequestEntity.error_code = "0";
            }
            tyRequestEntity.response_time = (int) (this.requestStartTime - System.currentTimeMillis());
            TyDB tyDB = new TyDB();
            tyDB.OpenUserDb(TYApplication.context);
            tyDB.insertHttpData(tyRequestEntity);
        }

        private void buryTag(Request request) {
            long j = this.mRequestBytes;
            long j2 = this.mResponseBytes;
        }

        private String stripRequestStr(Request request) {
            return "Request{method=" + request.method() + ", url=" + request.url() + ", contentType=" + request.header("Content-Type") + '}';
        }
    }

    public static boolean okGo(Context context2) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }
}
