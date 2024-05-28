package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.util.Log;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class PermissionUtil {
    private PermissionUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasPermission(Context context, String str) {
        boolean z = context.checkCallingOrSelfPermission(str) == 0;
        if (EasyDeviceInfo.debuggable && !z) {
            Log.e("EasyDeviceInfo", ">\t" + str);
            Log.w("EasyDeviceInfo", "\nPermission not granted/missing!\nMake sure you have declared the permission in your manifest file (and granted it on API 23+).\n");
        }
        return z;
    }
}
