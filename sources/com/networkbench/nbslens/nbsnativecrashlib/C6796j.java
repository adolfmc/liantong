package com.networkbench.nbslens.nbsnativecrashlib;

import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.networkbench.agent.impl.crash.NativeCrashInterface;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.nbslens.nbsnativecrashlib.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6796j {

    /* renamed from: a */
    private Map<String, String> f17610a;

    /* renamed from: b */
    private boolean f17611b;

    /* renamed from: c */
    private String f17612c = new UUID(new Random().nextLong(), new Random().nextLong()).toString();

    /* renamed from: a */
    public String m8426a() {
        return this.f17612c;
    }

    public C6796j(Map<String, String> map, boolean z) {
        this.f17610a = map;
        this.f17611b = z;
    }

    /* renamed from: b */
    public JsonArray m8423b() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS))));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(NativeCrashInterface.getInitAgentTime())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(NativeCrashInterface.isInitSuccess() ? 1 : 0)));
        jsonArray.add(new JsonPrimitive(this.f17612c));
        jsonArray.add(new JsonPrimitive(this.f17610a.get("nbs err msg") == null ? "" : this.f17610a.get("nbs err msg")));
        jsonArray.add(m8416f());
        jsonArray.add(new JsonPrimitive(NativeCrashInterface.getCurrentActivity()));
        jsonArray.add(NativeCrashInterface.getDeviceData());
        jsonArray.add(NativeCrashInterface.getAppInfo());
        jsonArray.add(new JsonPrimitive(NativeCrashInterface.getAddAppInfo()));
        jsonArray.add(new JsonPrimitive(""));
        jsonArray.add(m8420c());
        jsonArray.add(new JsonPrimitive(NativeCrashInterface.getSystemLogs()));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(NativeCrashInterface.getAppPhase())));
        jsonArray.add(new JsonPrimitive(NativeCrashInterface.getUserActionId()));
        jsonArray.add(new JsonObject());
        try {
            if (NativeCrashInterface.isEnableBrsAgent()) {
                jsonArray.add(new JsonPrimitive(this.f17612c));
            }
        } catch (Throwable unused) {
        }
        try {
            jsonArray.add(new JsonPrimitive(NativeCrashInterface.getDataTag()));
        } catch (Throwable unused2) {
        }
        return jsonArray;
    }

    /* renamed from: c */
    public JsonArray m8420c() {
        String key;
        JsonArray jsonArray = new JsonArray();
        String str = this.f17610a.get("build id") == null ? "" : this.f17610a.get("build id");
        if (str != null && !str.isEmpty()) {
            Map<String, String> m8422b = m8422b(str);
            String m8417e = m8417e();
            for (Map.Entry<String, String> entry : m8422b.entrySet()) {
                jsonArray.add(new JsonPrimitive(entry.getKey().startsWith("/system/") ? m8419c(key) + "," + ((Object) entry.getValue()) + "," + m8417e + ",0" : m8419c(key) + "," + ((Object) entry.getValue()) + "," + m8417e + ",1"));
            }
        }
        return jsonArray;
    }

    /* renamed from: e */
    private String m8417e() {
        return m8418d();
    }

    /* renamed from: d */
    public static String m8418d() {
        String str = "";
        if (Build.VERSION.SDK_INT < 21) {
            str = Build.CPU_ABI;
        } else {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr != null && strArr.length > 0) {
                str = strArr[0];
            }
        }
        if (TextUtils.isEmpty(str)) {
            NBSNativeCrash.m8519d().mo8440b("nbscrash", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
            return "";
        }
        return str.toLowerCase(Locale.US);
    }

    /* renamed from: b */
    private Map<String, String> m8422b(String str) {
        HashMap hashMap = new HashMap();
        try {
            for (String str2 : str.split("\n")) {
                String[] split = str2.split("\\(");
                if (split.length == 2) {
                    String trim = split[0].trim();
                    String trim2 = split[1].trim();
                    String trim3 = trim2.substring(0, trim2.indexOf(".")).split(":")[1].trim();
                    if (!hashMap.containsKey(trim)) {
                        if (trim3.length() > 32) {
                            trim3 = trim3.substring(trim3.length() - 32);
                        }
                        hashMap.put(trim, trim3);
                    }
                }
            }
        } catch (Throwable th) {
            NBSNativeCrash.m8519d().mo8434e("nbscrash", "parseBuildInfo error:" + th.getMessage());
        }
        return hashMap;
    }

    /* renamed from: c */
    private static String m8419c(String str) {
        return str.substring(str.lastIndexOf("/") + 1, str.length());
    }

    /* renamed from: f */
    private JsonArray m8416f() {
        JsonArray jsonArray = new JsonArray();
        String m8425a = m8425a(this.f17610a.get("backtrace") == null ? "" : this.f17610a.get("backtrace"));
        if (m8425a != null && !m8425a.isEmpty()) {
            JsonArray jsonArray2 = new JsonArray();
            if (this.f17611b) {
                jsonArray2.add(new JsonPrimitive((Number) 0));
                jsonArray2.add(new JsonPrimitive("main"));
            } else {
                jsonArray2.add(new JsonPrimitive((Number) 1));
                jsonArray2.add(new JsonPrimitive("subthread"));
            }
            jsonArray2.add(new JsonPrimitive(m8421b(m8425a, this.f17610a.get("java stacktrace"))));
            jsonArray.add(jsonArray2);
        }
        if (!this.f17611b) {
            String sb = new C6797k(Looper.getMainLooper().getThread()).m8415a().toString();
            JsonArray jsonArray3 = new JsonArray();
            jsonArray3.add(new JsonPrimitive((Number) 0));
            jsonArray3.add(new JsonPrimitive("main"));
            jsonArray3.add(new JsonPrimitive(sb));
            jsonArray.add(jsonArray3);
        }
        return jsonArray;
    }

    /* renamed from: a */
    public static String m8425a(String str) {
        return (str == null || str.isEmpty()) ? str : str.replace("  ", " ");
    }

    /* renamed from: b */
    private String m8421b(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        return str + m8424a(str2, "1##");
    }

    /* renamed from: a */
    public String m8424a(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        String[] split = str.split("\n");
        for (int i = 0; i < split.length; i++) {
            stringBuffer.append(split[i]);
            if (i != split.length - 1) {
                stringBuffer.append("\n");
                stringBuffer.append(str2);
            }
        }
        return stringBuffer.toString();
    }
}
