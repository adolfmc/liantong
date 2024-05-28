package p470p0;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.p */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13653p {
    /* renamed from: a */
    public static boolean m173a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
