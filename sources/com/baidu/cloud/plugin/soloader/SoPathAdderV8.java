package com.baidu.cloud.plugin.soloader;

import android.content.Context;
import com.baidu.cloud.util.ArrayUtils;
import com.baidu.cloud.util.ReflectionUtils;
import dalvik.system.PathClassLoader;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SoPathAdderV8 implements ISoPathAdder {
    @Override // com.baidu.cloud.plugin.soloader.ISoPathAdder
    public void addNativeDir(Context context, File file) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        String[] strArr = (String[]) ReflectionUtils.getFieldValue(pathClassLoader, "mLibPaths");
        if (strArr != null) {
            ReflectionUtils.setFieldValue(pathClassLoader, "mLibPaths", ArrayUtils.insertElement(String.class, strArr, file.getAbsolutePath()));
        }
    }

    @Override // com.baidu.cloud.plugin.soloader.ISoPathAdder
    public boolean containsNativeDir(Context context, File file) {
        return ArrayUtils.contains((String[]) ReflectionUtils.getFieldValue((PathClassLoader) context.getClassLoader(), "mLibPaths"), file.getAbsolutePath());
    }
}
