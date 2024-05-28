package com.networkbench.agent.impl.crash;

import android.content.Context;
import android.os.Build;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6646o;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.nbslens.nativecrashlib.NativeInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NativeCrashInterface {
    private static InterfaceC6393e log = C6394f.m10150a();

    public static String getAppSoPath() {
        try {
            return Build.VERSION.SDK_INT >= 9 ? C6638h.m8963w().m9076K().getApplicationInfo().nativeLibraryDir : "";
        } catch (Throwable unused) {
            log.mo10116d("get app so path error");
            return "";
        }
    }

    public static void initNativeCrash() {
        if (isClassExist()) {
            try {
                NativeInterface.initNativeCrash();
            } catch (Throwable th) {
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10115e("initNativeCrash failed: " + th.getMessage());
            }
        }
    }

    public static long getInitAgentTime() {
        try {
            return C6638h.m8963w().m9068S();
        } catch (Exception unused) {
            log.mo10116d("getInitAgentTime error");
            return 0L;
        }
    }

    public static boolean isInitSuccess() {
        return C6653u.m8703f() == 1;
    }

    public static void saveFeature() {
        if (isInitSuccess()) {
            return;
        }
        log.mo10122a("init failed ,native crash set feature 0");
        C6653u.m8730b(0);
    }

    public static String getCurrentActivity() {
        return Harvest.currentActivityName == null ? "" : Harvest.currentActivityName;
    }

    public static JsonArray getDeviceData() {
        return C6642k.m8902f(C6638h.m8963w().m9076K());
    }

    public static JsonArray getAppInfo() {
        return C6642k.m8907c("2");
    }

    public static String getAddAppInfo() {
        return C6653u.m8751a(C6638h.m8963w().m9076K(), true);
    }

    public static String getCpuArch() {
        C6638h.m8963w();
        return C6638h.f17111k;
    }

    public static String getSystemLogs() {
        return C6638h.f17115o ? C6330i.m10379a() : "";
    }

    public static int getAppPhase() {
        return C6638h.f17113m.get();
    }

    public static String getUserActionId() {
        return C6255f.m10820a() == null ? "" : C6255f.m10820a();
    }

    public static void storeCrashInfo(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("error storeCrashInfo params is null");
        }
        new C6332j(C6638h.m8963w().m9076K(), C6328g.f15929c).mo10375a(str, str2);
        try {
            if (isInitSuccess()) {
                return;
            }
            C6653u.m8730b(0);
        } catch (Throwable unused) {
            log.mo10115e("process error");
        }
    }

    public static void storeUUID(String str) {
        C6328g.m10394a().m10391a(NBSErrorEventType.crash, str);
        new C6646o(C6638h.m8963w().m9076K()).m8847b(str);
    }

    public static int getStackTraceLimitCount() {
        if (NBSAgent.getStackTraceLimit() == 0) {
            return 100;
        }
        return NBSAgent.getStackTraceLimit();
    }

    private static boolean isClassExist() {
        try {
            Class.forName("com.networkbench.nbslens.nativecrashlib.NativeInterface");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Context getNativeContext() {
        try {
            return C6638h.m8963w().m9076K();
        } catch (Throwable unused) {
            log.mo10116d("getNativeContext error");
            return null;
        }
    }

    public static String getNativeVersion() {
        try {
            return NativeInterface.getNativeVersion();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean isEnableBrsAgent() {
        return C6638h.m8963w().m9065V();
    }

    public static String getDataTag() {
        return C6653u.m8701f(C6638h.m8963w().m9001h());
    }
}
