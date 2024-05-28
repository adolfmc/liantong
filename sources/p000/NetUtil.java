package p000;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: w */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NetUtil {
    /* renamed from: a */
    public static boolean m32a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) Utils.m22196a().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }
}
