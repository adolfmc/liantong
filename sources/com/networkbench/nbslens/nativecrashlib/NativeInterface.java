package com.networkbench.nbslens.nativecrashlib;

import com.networkbench.agent.impl.crash.NativeCrashInterface;
import com.networkbench.nbslens.nbsnativecrashlib.NBSNativeCrash;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NativeInterface {
    public static String getNativeVersion() {
        return "2.0.2.1";
    }

    public static String initNativeCrash() {
        if (NativeCrashInterface.getNativeContext() != null) {
            return NBSNativeCrash.init(NativeCrashInterface.getNativeContext()) + "";
        }
        throw new RuntimeException("error initNativeCrash context is null");
    }
}
