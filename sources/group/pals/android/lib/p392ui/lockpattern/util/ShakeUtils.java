package group.pals.android.lib.p392ui.lockpattern.util;

import android.app.Activity;
import android.os.Vibrator;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: group.pals.android.lib.ui.lockpattern.util.ShakeUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ShakeUtils {
    public static void vibrate(Activity activity, long j) {
        ((Vibrator) activity.getSystemService("vibrator")).vibrate(j);
    }

    public static void vibrate(Activity activity, long[] jArr, int i) {
        ((Vibrator) activity.getSystemService("vibrator")).vibrate(jArr, i);
    }

    public static void virateCancle(Activity activity) {
        ((Vibrator) activity.getSystemService("vibrator")).cancel();
    }
}
