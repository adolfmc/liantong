package com.baidu.cloud.plugin.soloader;

import android.content.Context;
import android.os.Build;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SysSoLoaderUtils {
    public static void addNativeDir(Context context, File file) {
        try {
            getSoPathAdder().addNativeDir(context, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean containsNativeDir(Context context, File file) {
        if (context == null) {
            return false;
        }
        return getSoPathAdder().containsNativeDir(context, file);
    }

    private static ISoPathAdder getSoPathAdder() {
        if (Build.VERSION.SDK_INT >= 26) {
            return new SoPathAdderV26();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return new SoPathAdderV23();
        }
        return Build.VERSION.SDK_INT >= 14 ? new SoPathAdderV14() : new SoPathAdderV8();
    }

    private static boolean hasDexClassLoader() {
        try {
            Class.forName("dalvik.system.BaseDexClassLoader");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
