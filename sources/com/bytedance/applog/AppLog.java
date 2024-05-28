package com.bytedance.applog;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.text.TextUtils;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.applog.network.INetworkClient;
import com.bytedance.applog.profile.UserProfileCallback;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class AppLog {
    public static final String EVENT_V1_CATEGORY = "event_v1";
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a */
    public static volatile C3726x f8262a;
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: b */
    public static volatile C3735y f8263b;

    /* renamed from: c */
    public static volatile C3557c f8264c;

    /* renamed from: d */
    public static IHeaderCustomTimelyCallback f8265d;

    /* renamed from: e */
    public static Application f8266e;

    /* renamed from: f */
    public static volatile boolean f8267f;

    /* renamed from: g */
    public static C3591h f8268g;

    /* renamed from: h */
    public static Integer f8269h;
    public static int sLaunchFrom;

    public AppLog() {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
    }

    public static String addCommonParams(String str, boolean z) {
        if (f8263b != null) {
            return C3614k1.m17254a(f8266e, f8263b.m17017b(), new StringBuilder(str), z);
        }
        return null;
    }

    public static void addDataObserver(IDataObserver iDataObserver) {
        C3577e2.m17309a().m17308a(iDataObserver);
    }

    public static void addEventObserver(IEventObserver iEventObserver) {
        C3622l2.m17245a().m17244a(iEventObserver);
    }

    public static String addNetCommonParams(Context context, String str, boolean z) {
        StringBuilder sb = new StringBuilder(str);
        addNetCommonParams(context, sb, z);
        return sb.toString();
    }

    public static void addNetCommonParams(Context context, StringBuilder sb, boolean z) {
        if (f8263b != null) {
            C3614k1.m17254a(context, f8263b.m17017b(), sb, z);
        } else {
            C3704u2.m17108a("addNetCommonParams no init", (Throwable) null);
        }
    }

    public static void addSessionHook(ISessionObserver iSessionObserver) {
        C3688s2.m17118a().m17117a(iSessionObserver);
    }

    public static void flush() {
        C3591h c3591h = f8268g;
        if (c3591h != null) {
            c3591h.m17290a(null, true);
        }
    }

    @Nullable
    public static <T> T getAbConfig(String str, T t) {
        if (f8263b != null) {
            C3735y c3735y = f8263b;
            JSONObject optJSONObject = c3735y.f8935c.m17055a().optJSONObject(str);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("vid");
                T t2 = (T) optJSONObject.opt("val");
                c3735y.m17025a(optString);
                if (t2 == null) {
                    t2 = null;
                }
                if (t2 != null) {
                    return t2;
                }
            }
            return t;
        }
        return null;
    }

    public static String getAbSdkVersion() {
        if (f8263b != null) {
            C3735y c3735y = f8263b;
            if (c3735y.f8933a) {
                return c3735y.f8936d.optString("ab_sdk_version", "");
            }
            C3726x c3726x = c3735y.f8935c;
            return c3726x != null ? c3726x.m17050b() : "";
        }
        return null;
    }

    public static String getAid() {
        return f8263b != null ? f8263b.f8936d.optString("aid", "") : "";
    }

    public static String getClientUdid() {
        return f8263b != null ? f8263b.f8936d.optString("clientudid", "") : "";
    }

    public static Context getContext() {
        return f8266e;
    }

    public static String getDid() {
        return f8263b != null ? f8263b.f8936d.optString("bd_did", "") : "";
    }

    public static boolean getEncryptAndCompress() {
        return true;
    }

    @Nullable
    public static JSONObject getHeader() {
        if (f8263b == null) {
            C3704u2.m17108a("U SHALL NOT PASS!", new RuntimeException("init come first"));
            return null;
        }
        return f8263b.m17017b();
    }

    public static IHeaderCustomTimelyCallback getHeaderCustomCallback() {
        return f8265d;
    }

    public static <T> T getHeaderValue(String str, T t) {
        if (f8263b != null) {
            return (T) C3614k1.m17251a(f8263b.f8936d, str, t);
        }
        return null;
    }

    public static int getHttpMonitorPort() {
        Integer num = f8269h;
        if (num != null) {
            return num.intValue();
        }
        if (f8262a != null) {
            return f8262a.f8900e.getInt("http_monitor_port", 0);
        }
        return 0;
    }

    public static String getIid() {
        return f8263b != null ? f8263b.f8936d.optString("install_id", "") : "";
    }

    public static InitConfig getInitConfig() {
        if (f8262a != null) {
            return f8262a.f8897b;
        }
        return null;
    }

    public static INetworkClient getNetClient() {
        return f8262a.f8897b.getNetworkClient();
    }

    public static String getOpenUdid() {
        return f8263b != null ? f8263b.f8936d.optString("openudid", "") : "";
    }

    public static String getSsid() {
        return f8263b != null ? f8263b.f8936d.optString("ssid", "") : "";
    }

    public static void getSsidGroup(Map<String, String> map) {
        String did = getDid();
        if (!TextUtils.isEmpty(did)) {
            map.put("device_id", did);
        }
        String iid = getIid();
        if (!TextUtils.isEmpty(iid)) {
            map.put("install_id", iid);
        }
        String openUdid = getOpenUdid();
        if (!TextUtils.isEmpty(openUdid)) {
            map.put("openudid", openUdid);
        }
        String clientUdid = getClientUdid();
        if (TextUtils.isEmpty(clientUdid)) {
            return;
        }
        map.put("clientudid", clientUdid);
    }

    public static int getSuccRate() {
        if (f8262a != null) {
            return f8262a.f8900e.getInt("bav_monitor_rate", 0);
        }
        return 0;
    }

    public static String getUdid() {
        return f8263b != null ? f8263b.f8936d.optString("udid", "") : "";
    }

    public static String getUserID() {
        return String.valueOf(C3624m.f8551m);
    }

    public static String getUserUniqueID() {
        return f8263b != null ? f8263b.m17012d() : "";
    }

    public static boolean hasStarted() {
        return f8267f;
    }

    public static void init(@NonNull Context context, @NonNull InitConfig initConfig) {
        synchronized (AppLog.class) {
            if (f8266e == null) {
                C3704u2.m17109a(context, initConfig.getLogger());
                C3704u2.m17108a("Inited Begin", (Throwable) null);
                f8266e = (Application) context.getApplicationContext();
                f8262a = new C3726x(f8266e, initConfig);
                f8263b = new C3735y(f8266e, f8262a);
                f8264c = new C3557c(initConfig.getPicker());
                f8268g = new C3591h(f8266e, f8262a, f8263b);
                if (initConfig.m17352a()) {
                    f8266e.registerActivityLifecycleCallbacks(f8264c);
                }
                sLaunchFrom = 1;
                f8267f = initConfig.autoStart();
                C3704u2.m17108a("Inited End", (Throwable) null);
            }
        }
    }

    public static boolean isNewUser() {
        if (f8263b != null) {
            return f8263b.f8941i;
        }
        return false;
    }

    public static boolean isNewUserMode(Context context) {
        return C3536a0.m17343b(context);
    }

    public static boolean manualActivate() {
        C3591h c3591h = f8268g;
        if (c3591h != null) {
            return c3591h.m17291a(false);
        }
        return false;
    }

    public static void onActivityPause() {
        if (f8264c != null) {
            f8264c.onActivityPaused(null);
        }
    }

    public static void onActivityResumed(String str, int i) {
        if (f8264c != null) {
            f8264c.m17323a(str, i);
        }
    }

    public static void onEvent(String str) {
        onEvent("event_v1", str, null, 0L, 0L, null);
    }

    public static void onEvent(String str, String str2) {
        onEvent("event_v1", str, str2, 0L, 0L, null);
    }

    public static void onEvent(String str, String str2, String str3, long j, long j2) {
        onEvent(str, str2, str3, j, j2, null);
    }

    public static void onEvent(@NonNull String str, @NonNull String str2, String str3, long j, long j2, JSONObject jSONObject) {
        String str4 = null;
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str3)) {
            C3704u2.m17108a("category and label is empty", (Throwable) null);
            return;
        }
        if (jSONObject != null) {
            str4 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        }
        C3591h.m17294a(new C3673q1(str, str2, str3, j, j2, str4));
    }

    public static void onEventV3(@NonNull String str) {
        C3591h.m17294a(new C3687s1(str, false, null));
    }

    public static void onEventV3(@NonNull String str, @Nullable Bundle bundle) {
        JSONObject jSONObject = null;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str2 : bundle.keySet()) {
                            jSONObject2.put(str2, bundle.get(str2));
                        }
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        C3704u2.m17108a("U SHALL NOT PASS!", th);
                        onEventV3(str, jSONObject);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        onEventV3(str, jSONObject);
    }

    public static void onEventV3(@NonNull String str, @Nullable JSONObject jSONObject) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            C3704u2.m17108a("eventName is empty", (Throwable) null);
        }
        if (jSONObject != null) {
            str2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        }
        C3591h.m17294a(new C3687s1(str, false, str2));
    }

    public static void onInternalEventV3(@NonNull String str, @Nullable Bundle bundle, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        JSONObject jSONObject = null;
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            C3704u2.m17108a("both second appid and second app name is empty, return", (Throwable) null);
            return;
        }
        String str5 = "second_app_" + str;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str6 : bundle.keySet()) {
                            jSONObject2.put(str6, bundle.get(str6));
                        }
                        jSONObject2.put("params_for_special", "second_app");
                        jSONObject2.put("second_appid", str2);
                        jSONObject2.put("second_appname", str3);
                        jSONObject2.put("product_type", str4);
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        C3704u2.m17108a("U SHALL NOT PASS!", th);
                        onEventV3(str5, jSONObject);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        onEventV3(str5, jSONObject);
    }

    public static void onMiscEvent(@NonNull String str, @NonNull JSONObject jSONObject) {
        if (!TextUtils.isEmpty(str) && jSONObject != null && jSONObject.length() > 0) {
            try {
                C3591h.m17294a(new C3679r1(str, jSONObject));
                return;
            } catch (Exception e) {
                C3704u2.m17108a("call onEventData get exception: ", e);
                return;
            }
        }
        C3704u2.m17108a("call onEventData with invalid params, return", (Throwable) null);
    }

    public static void onPause(Context context) {
        if (context instanceof Activity) {
            onActivityPause();
        }
    }

    public static void onResume(Context context) {
        if (context instanceof Activity) {
            onActivityResumed(context.getClass().getName(), context.hashCode());
        }
    }

    public static void putCommonParams(Map<String, String> map, boolean z) {
        if (f8263b != null) {
            C3614k1.m17253a(f8266e, f8263b.m17017b(), z, map);
        }
    }

    public static void registerHeaderCustomCallback(IHeaderCustomTimelyCallback iHeaderCustomTimelyCallback) {
        f8265d = iHeaderCustomTimelyCallback;
    }

    public static void removeAllDataObserver() {
        C3577e2.m17309a().f8436a.clear();
    }

    public static void removeDataObserver(IDataObserver iDataObserver) {
        C3577e2.m17309a().m17307b(iDataObserver);
    }

    public static void removeEventObserver(IEventObserver iEventObserver) {
        C3622l2.m17245a().m17243b(iEventObserver);
    }

    public static void removeHeaderInfo(String str) {
        if (f8263b == null || TextUtils.isEmpty(str)) {
            return;
        }
        f8263b.m17013c(str);
    }

    public static void removeSessionHook(ISessionObserver iSessionObserver) {
        C3688s2.m17118a().m17116b(iSessionObserver);
    }

    public static boolean reportPhoneDetailInfo() {
        return f8263b.m17005h();
    }

    public static void setAccount(Account account) {
        if (f8263b != null) {
            C3704u2.m17108a("setAccount " + account, (Throwable) null);
            ((C3603i2) f8263b.f8939g).m17281a(account);
        }
    }

    public static void setAppLanguageAndRegion(String str, String str2) {
        boolean z;
        C3591h c3591h = f8268g;
        if (c3591h != null) {
            C3735y c3735y = c3591h.f8469h;
            boolean z2 = true;
            if (c3735y.m17024a("app_language", (Object) str)) {
                C3535a.m17350a(c3735y.f8935c.f8900e, "app_language", str);
                z = true;
            } else {
                z = false;
            }
            C3735y c3735y2 = c3591h.f8469h;
            if (c3735y2.m17024a("app_region", (Object) str2)) {
                C3535a.m17350a(c3735y2.f8935c.f8900e, "app_region", str2);
            } else {
                z2 = false;
            }
            if (z || z2) {
                c3591h.m17295a(c3591h.f8472k);
                c3591h.m17295a(c3591h.f8466e);
            }
        }
    }

    public static void setAppTrack(JSONObject jSONObject) {
        if (jSONObject == null || f8263b == null) {
            return;
        }
        C3735y c3735y = f8263b;
        if (c3735y.m17024a("app_track", jSONObject)) {
            C3535a.m17350a(c3735y.f8935c.f8898c, "app_track", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    public static void setEncryptAndCompress(boolean z) {
    }

    public static void setEventSenderEnable(boolean z) {
        C3591h c3591h = f8268g;
        if (c3591h != null) {
            c3591h.m17288b(z);
        }
    }

    public static void setExternalAbVersion(String str) {
        if (f8263b != null) {
            f8263b.m17009e(str);
        }
    }

    public static void setExtraParams(IExtraParams iExtraParams) {
        C3614k1.f8534a = iExtraParams;
    }

    public static void setForbidReportPhoneDetailInfo(boolean z) {
        if (f8263b != null) {
            C3735y c3735y = f8263b;
            c3735y.f8942j = z;
            if (c3735y.m17005h()) {
                return;
            }
            c3735y.m17024a("sim_serial_number", (Object) null);
            return;
        }
        throw new IllegalStateException("Applog还未init()");
    }

    public static void setGoogleAid(String str) {
        if (f8263b != null) {
            C3735y c3735y = f8263b;
            if (c3735y.m17024a("google_aid", (Object) str)) {
                C3535a.m17350a(c3735y.f8935c.f8900e, "google_aid", str);
            }
        }
    }

    public static void setHeaderInfo(String str, Object obj) {
        if (f8263b == null || TextUtils.isEmpty(str)) {
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(str, obj);
        f8263b.m17022a(hashMap);
    }

    public static void setHeaderInfo(HashMap<String, Object> hashMap) {
        if (f8263b != null) {
            f8263b.m17022a(hashMap);
        }
    }

    public static void setHttpMonitorPort(int i) {
        f8269h = Integer.valueOf(i);
    }

    public static void setNewUserMode(Context context, boolean z) {
        C3536a0.m17340c(context, z);
    }

    @AnyThread
    public static void setOaidObserver(@Nullable IOaidObserver iOaidObserver) {
        C3572d3.m17312a(iOaidObserver);
    }

    public static void setRangersEventVerifyEnable(boolean z, String str) {
        C3591h c3591h = f8268g;
        if (c3591h != null) {
            if (z) {
                if (c3591h.f8481t == null) {
                    c3591h.f8481t = new C3605j(c3591h, str);
                    c3591h.f8482u.add(c3591h.f8481t);
                    c3591h.f8470i.removeMessages(6);
                    c3591h.f8470i.sendEmptyMessage(6);
                    return;
                }
                return;
            }
            C3605j c3605j = c3591h.f8481t;
            if (c3605j != null) {
                c3605j.setStop(true);
                c3591h.f8482u.remove(c3591h.f8481t);
                c3591h.f8481t = null;
            }
        }
    }

    public static void setTouchPoint(String str) {
        setHeaderInfo("touch_point", str);
    }

    public static void setUriRuntime(UriConfig uriConfig) {
        if (f8268g != null) {
            StringBuilder m17349a = C3535a.m17349a("setUriRuntime ");
            m17349a.append(uriConfig.getRegisterUri());
            C3704u2.m17108a(m17349a.toString(), (Throwable) null);
            C3591h c3591h = f8268g;
            c3591h.f8476o = uriConfig;
            c3591h.m17295a(c3591h.f8472k);
            if (c3591h.f8465d.f8897b.isAutoActive()) {
                c3591h.m17291a(true);
            }
        }
    }

    public static void setUserAgent(String str) {
        if (f8263b != null) {
            C3735y c3735y = f8263b;
            if (c3735y.m17024a("user_agent", (Object) str)) {
                C3535a.m17350a(c3735y.f8935c.f8900e, "user_agent", str);
            }
        }
    }

    public static void setUserID(long j) {
        C3624m.f8551m = j;
    }

    public static void setUserUniqueID(String str) {
        C3591h c3591h = f8268g;
        if (c3591h != null) {
            c3591h.m17293a(str);
        }
    }

    public static void start() {
        if (f8267f) {
            return;
        }
        f8267f = true;
        C3591h c3591h = f8268g;
        if (c3591h.f8479r) {
            return;
        }
        c3591h.f8479r = true;
        c3591h.f8477p.sendEmptyMessage(1);
    }

    public static void startSimulator(String str) {
        C3591h c3591h = f8268g;
        if (c3591h != null) {
            AbstractC3579f abstractC3579f = c3591h.f8480s;
            if (abstractC3579f != null) {
                abstractC3579f.setStop(true);
            }
            try {
                Constructor<?> constructor = Class.forName("com.bytedance.applog.picker.DomSender").getConstructor(C3591h.class, String.class);
                new HandlerThread("bd_tracker_d").start();
                c3591h.f8480s = (AbstractC3579f) constructor.newInstance(C3591h.f8461A, str);
                c3591h.f8470i.sendMessage(c3591h.f8470i.obtainMessage(9, c3591h.f8480s));
            } catch (Exception e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
            }
        }
    }

    public static void userProfileSetOnce(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        C3591h c3591h = f8268g;
        if (c3591h == null || c3591h.f8470i == null) {
            return;
        }
        C3581f1.m17300a(c3591h, 0, jSONObject, userProfileCallback, c3591h.f8470i, false);
    }

    public static void userProfileSync(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        C3591h c3591h = f8268g;
        if (c3591h == null || c3591h.f8470i == null) {
            return;
        }
        C3581f1.m17300a(c3591h, 1, jSONObject, userProfileCallback, c3591h.f8470i, false);
    }

    public static void onInternalEventV3(@NonNull String str, @Nullable JSONObject jSONObject, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            C3704u2.m17108a("both second appid and second app name is empty, return", (Throwable) null);
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String str5 = "second_app_" + str;
        try {
            jSONObject.put("params_for_special", "second_app");
            jSONObject.put("second_appid", str2);
            jSONObject.put("second_appname", str3);
            jSONObject.put("product_type", str4);
        } catch (Throwable th) {
            C3704u2.m17108a("U SHALL NOT PASS!", th);
        }
        C3591h.m17294a(new C3687s1(str5, false, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)));
    }
}
