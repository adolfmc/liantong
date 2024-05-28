package com.networkbench.agent.impl.instrumentation;

import android.content.Context;
import com.networkbench.agent.impl.p243c.p248e.C6282a;
import com.networkbench.agent.impl.p243c.p248e.C6287e;
import com.networkbench.agent.impl.p243c.p248e.C6294l;
import com.networkbench.agent.impl.p254f.C6396h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSAppInstrumentation {
    public static C6294l activityTrace;
    public static C6287e appStateTimeInfo;
    public static volatile boolean isAppInBackground;

    static {
        try {
            appStateTimeInfo = C6287e.m10630a();
            activityTrace = new C6282a();
        } catch (Throwable unused) {
        }
        isAppInBackground = true;
    }

    @NBSReplaceCallSite
    public static void attachBaseContextBeginIns(Context context) {
        try {
            appStateTimeInfo.m10620b(context);
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  attachBaseContextBeginIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void attachBaseContextEndIns() {
        try {
            appStateTimeInfo.m10614e();
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  attachBaseContextEndIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void applicationCreateBeginIns() {
        try {
            appStateTimeInfo.m10612f();
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  applicationCreateBeginIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void applicationCreateEndIns() {
        try {
            appStateTimeInfo.m10611g();
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  applicationCreateEndIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void activityCreateBeginIns(String str) {
        try {
            activityTrace.mo10539a(str, "#onCreate");
            if (isAppInBackground) {
                appStateTimeInfo.m10622a(str);
            }
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  activityCreateBeginIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void activityCreateEndIns() {
        try {
            activityTrace.mo10538b();
            if (isAppInBackground) {
                appStateTimeInfo.m10610h();
            }
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  activityCreateEndIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void activityRestartBeginIns(String str) {
        try {
            activityTrace.mo10539a(str, "#onRestart");
            if (isAppInBackground) {
                appStateTimeInfo.m10617c(str);
            }
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  activityRestartBeginIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void activityRestartEndIns() {
        try {
            activityTrace.mo10538b();
            if (isAppInBackground) {
                appStateTimeInfo.m10608j();
            }
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  activityRestartEndIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void activityResumeBeginIns(String str) {
        try {
            activityTrace.mo10540a(str);
            if (isAppInBackground) {
                appStateTimeInfo.m10615d(str);
            }
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  activityResumeBeginIns() has an error :" + th);
        }
    }

    @NBSReplaceCallSite
    public static void activityStartBeginIns(String str) {
        try {
            activityTrace.mo10537b(str);
            if (isAppInBackground) {
                appStateTimeInfo.m10619b(str);
            }
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  activityStartBeginIns() has an error :" + th);
        }
    }

    public static void activityStartEndIns() {
        try {
            activityTrace.mo10536c();
            if (isAppInBackground) {
                appStateTimeInfo.m10609i();
            }
        } catch (Exception e) {
            C6396h.m10133i("NBSAppInstrumentation  activityStartEndIns() has an error :" + e);
        }
    }

    @NBSReplaceCallSite
    public static void activityResumeEndIns() {
        try {
            if (isAppInBackground) {
                appStateTimeInfo.m10607k();
            }
            activityTrace.mo10541a();
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  activityResumeEndIns() has an error :" + th);
        }
    }

    public static void HybridOnResumeEndIns(String str) {
        try {
            appStateTimeInfo.m10613e(str);
        } catch (Throwable th) {
            C6396h.m10133i("NBSAppInstrumentation  HybirdOnResumeEndIns() has an error :" + th);
        }
    }

    public static void setCustomPageName(String str) {
        try {
            activityTrace.m10533d(str);
        } catch (Throwable th) {
            C6396h.m10133i("setCustomPageName  has an  error!!" + th.getMessage());
        }
    }
}
