package com.baidu.cloud.plugin.soloader;

import android.content.Context;
import com.baidu.cloud.util.ArrayUtils;
import com.baidu.cloud.util.ReflectionUtils;
import java.io.File;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SoPathAdderV14 implements ISoPathAdder {
    @Override // com.baidu.cloud.plugin.soloader.ISoPathAdder
    public void addNativeDir(Context context, File file) {
        Object fieldValue = ReflectionUtils.getFieldValue(context.getClassLoader(), "pathList");
        Object fieldValue2 = ReflectionUtils.getFieldValue(fieldValue, "nativeLibraryDirectories");
        if (fieldValue2 instanceof File[]) {
            ReflectionUtils.setFieldValue(fieldValue, "nativeLibraryDirectories", ArrayUtils.insertElement(File.class, (File[]) fieldValue2, file));
        } else {
            ((ArrayList) fieldValue2).add(file);
        }
    }

    @Override // com.baidu.cloud.plugin.soloader.ISoPathAdder
    public boolean containsNativeDir(Context context, File file) {
        Object fieldValue = ReflectionUtils.getFieldValue(ReflectionUtils.getFieldValue(context.getClassLoader(), "pathList"), "nativeLibraryDirectories");
        if (fieldValue instanceof File[]) {
            return ArrayUtils.contains((File[]) fieldValue, file);
        }
        return ((ArrayList) fieldValue).contains(file);
    }
}
