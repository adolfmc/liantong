package com.tydic.softphone.tydic_tracker.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.tydic.softphone.tydic_tracker.tydicDB.TyDB;
import com.tydic.softphone.tydic_tracker.tydicDB.TyRequestEntity;
import com.tydic.softphone.tydic_tracker.tydicDB.TyResourceEntity;
import com.tydic.softphone.tydic_tracker.tydicDB.TySessionEntity;
import com.tydic.softphone.tydic_tracker.tydicDB.TyStartupsEntity;
import java.io.PrintStream;
import java.util.UUID;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TYApplication {
    public static final String TAG = "tyLog";
    public static String access = "";
    public static String app_key = "";
    private static String callMode = "";
    private static String cid = "";
    private static Context context = null;
    public static String dev_val = "";
    public static String env = "";
    private static String fouseCall = "";
    public static int is_new = 0;
    private static String pageName = "";
    private static String phoneNumber = "";
    public static String ses_id = null;
    private static long startMilli = 0;
    public static TimeLoop timeLoop = null;
    public static String uid = "";
    private boolean networkState;

    public static void clearTable() {
    }

    public static void sendAllData() {
    }

    public TYApplication() {
    }

    public TYApplication(Context context2, String str, String str2, String str3, String str4) {
        callMode = str4;
        pageName = str;
        phoneNumber = str2;
        fouseCall = str3;
        SharedPreferences sharedPreferences = context2.getSharedPreferences("lz_cid", 0);
        String string = sharedPreferences.getString("cid", "");
        Log.i("tydiccid", "cid:" + string);
        if (string.equals("")) {
            UUID randomUUID = UUID.randomUUID();
            int version = randomUUID.version();
            PrintStream printStream = System.out;
            printStream.println("UUID:" + randomUUID + " 版本 " + version);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("cid", String.valueOf(randomUUID));
            edit.commit();
            cid = String.valueOf(randomUUID);
            return;
        }
        String string2 = sharedPreferences.getString("cid", "");
        cid = string2;
        Log.i("tydiccid", "cid1:" + string2);
    }

    public static void exceptionSoft(String str) {
        Log.i("tydicerror", str);
        try {
            TYHTTP tyhttp = new TYHTTP();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pbName", "报错内容");
            jSONObject.put("pageName", pageName);
            jSONObject.put("cid", cid);
            jSONObject.put("reportErrors", str);
            jSONObject.put("triggerCodeBtn", "");
            jSONObject.put("fouseCall", fouseCall);
            jSONObject.put("callMode", callMode);
            jSONObject.put("phoneNumber", phoneNumber);
            jSONObject.put("type", 2);
            tyhttp.sendRequest(jSONObject);
        } catch (JSONException e) {
            Log.e("tydic", e.toString());
        }
    }

    public static long getTimeStame() {
        return System.currentTimeMillis();
    }

    public static JSONObject getBasic() throws JSONException {
        String networkState = KqwNetworkUtil.getNetworkState(context);
        System.currentTimeMillis();
        Log.i("tyLog", String.valueOf(networkState));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("access", networkState);
        jSONObject.put("app_key", app_key);
        jSONObject.put("uid", uid);
        jSONObject.put("is_new", is_new);
        jSONObject.put("dev_val", dev_val);
        return jSONObject;
    }

    public static void cancleTime() {
        timeLoop.cancleTime();
    }

    public void init(Context context2, String str) {
        context = context2;
        env = str;
        timeLoop = new TimeLoop();
        timeLoop.startLister();
        MyCrashHandler myCrashHandler = new MyCrashHandler(context);
        Thread.setDefaultUncaughtExceptionHandler(myCrashHandler);
        myCrashHandler.StartANRWatchDog();
    }

    public static void InfoTracker(String str, String str2, int i, String str3) {
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
    }

    public static void EventTracker(String str, String str2) {
        Log.i("tydicclick", str2 + " :" + str);
        try {
            TYHTTP tyhttp = new TYHTTP();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pbName", str);
            jSONObject.put("pageName", pageName);
            jSONObject.put("cid", cid);
            jSONObject.put("triggerCodeBtn", str2);
            jSONObject.put("reportErrors", "");
            jSONObject.put("fouseCall", fouseCall);
            jSONObject.put("callMode", callMode);
            jSONObject.put("phoneNumber", phoneNumber);
            jSONObject.put("type", 2);
            tyhttp.sendRequest(jSONObject);
        } catch (JSONException e) {
            Log.e("tydic", e.toString());
        }
    }

    public static void WebviewResource(TyResourceEntity tyResourceEntity) {
        TyDB tyDB = new TyDB();
        tyDB.OpenUserDb(context);
        tyDB.insertAllResourceData(tyResourceEntity);
    }

    public static void ViewTracker(String str, String str2) {
        try {
            TYHTTP tyhttp = new TYHTTP();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pageName", str);
            jSONObject.put("fouseCall", fouseCall);
            jSONObject.put("cid", cid);
            jSONObject.put("callMode", callMode);
            jSONObject.put("pageCode", str2);
            jSONObject.put("phoneNumber", phoneNumber);
            jSONObject.put("type", 1);
            tyhttp.sendRequest(jSONObject);
        } catch (JSONException unused) {
        }
    }

    public static void setEndMilli() {
        long timeStame = getTimeStame();
        TyDB tyDB = new TyDB();
        tyDB.OpenUserDb(context);
        TyStartupsEntity tyStartupsEntity = new TyStartupsEntity();
        tyStartupsEntity.setSes_id(ses_id);
        tyStartupsEntity.setStart_milli(startMilli);
        tyStartupsEntity.setEnd_milli(timeStame);
        tyDB.insertStartupsData(tyStartupsEntity);
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
            Log.i("tyLog", "已经收到数据");
            Log.i("tyLog", stripRequestStr(request));
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
