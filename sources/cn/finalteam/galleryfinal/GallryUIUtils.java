package cn.finalteam.galleryfinal;

import android.app.Activity;
import android.os.Build;
import android.view.Window;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GallryUIUtils {
    public static void setStatusBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            Window window = activity.getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(-1);
            window.getDecorView().setSystemUiVisibility(8192);
        }
    }
}
