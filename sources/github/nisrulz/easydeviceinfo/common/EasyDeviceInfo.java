package github.nisrulz.easydeviceinfo.common;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class EasyDeviceInfo {
    public static boolean debuggable = false;
    public static final String nameOfLib = "EasyDeviceInfo";
    public static String notFoundVal = "unknown";

    private EasyDeviceInfo() {
    }

    public static void setNotFoundVal(String str) {
        notFoundVal = str;
    }

    public static void setConfigs(String str, boolean z) {
        notFoundVal = str;
        debuggable = z;
    }

    public static String getLibraryVersion() {
        return "EasyDeviceInfo : v2.4.1 [build-v" + String.valueOf(25) + "]";
    }

    public static void debug() {
        debuggable = true;
    }
}
