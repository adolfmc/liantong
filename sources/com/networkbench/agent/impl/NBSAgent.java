package com.networkbench.agent.impl;

import com.networkbench.agent.impl.harvest.ApplicationInformation;
import com.networkbench.agent.impl.harvest.DeviceData;
import com.networkbench.agent.impl.harvest.DeviceInformation;
import com.networkbench.agent.impl.harvest.SystemInfo;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.NBSAndroidAgentImpl;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSAgent {
    private static final String VERSION = "2.15.5.26";
    private static final InterfaceC6393e log = C6394f.m10150a();
    private static NBSAndroidAgentImpl sImpl = null;

    public static String getBuildId() {
        return "67fc3c3e-7a1d-4158-a03d-e76b4cc08352";
    }

    public static String getHttpDataVersion() {
        return "2.4.2";
    }

    public static String getSocketDataVersion() {
        return "2.5.3";
    }

    public static String getTaskDataVersion() {
        return "1.0.0";
    }

    public static String getVersion() {
        return "2.15.5.26";
    }

    private void pokeCanary() {
    }

    public static void setLocation(String str, String str2) {
    }

    public static void setImpl(NBSAndroidAgentImpl nBSAndroidAgentImpl) {
        sImpl = nBSAndroidAgentImpl;
    }

    public static NBSAndroidAgentImpl getImpl() {
        return sImpl;
    }

    public static int getStackTraceLimit() {
        NBSAndroidAgentImpl nBSAndroidAgentImpl = sImpl;
        if (nBSAndroidAgentImpl == null) {
            return 1024;
        }
        return nBSAndroidAgentImpl.m9156g();
    }

    public static int getResponseBodyLimit() {
        NBSAndroidAgentImpl nBSAndroidAgentImpl = sImpl;
        if (nBSAndroidAgentImpl == null) {
            return 1024;
        }
        return nBSAndroidAgentImpl.m9155h();
    }

    public static List<C6281e> getAndClearErrorData() {
        NBSAndroidAgentImpl nBSAndroidAgentImpl = sImpl;
        if (nBSAndroidAgentImpl == null) {
            return null;
        }
        return nBSAndroidAgentImpl.m9157f();
    }

    public static void mergeErrorData(List<C6281e> list) {
        NBSAndroidAgentImpl nBSAndroidAgentImpl = sImpl;
        if (nBSAndroidAgentImpl == null) {
            return;
        }
        nBSAndroidAgentImpl.m9171a(list);
    }

    public static String getActiveNetworkCarrier() {
        NBSAndroidAgentImpl nBSAndroidAgentImpl = sImpl;
        if (nBSAndroidAgentImpl == null) {
            return null;
        }
        return nBSAndroidAgentImpl.m9151l();
    }

    public static void disable() {
        sImpl.m9153j();
    }

    public static boolean isDisabled() {
        return sImpl.m9152k();
    }

    public static void start() {
        sImpl.m9178a();
    }

    public static void stop() {
        sImpl.m9154i();
    }

    public static DeviceInformation getDeviceInformation() {
        return getImpl().m9164c();
    }

    public static ApplicationInformation getApplicationInformation() {
        return getImpl().m9158e();
    }

    public static DeviceData getDeviceData() {
        return getImpl().m9161d();
    }

    public static SystemInfo getSystemInfo() {
        return getImpl().m9149n();
    }
}
