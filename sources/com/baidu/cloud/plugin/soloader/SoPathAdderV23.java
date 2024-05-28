package com.baidu.cloud.plugin.soloader;

import android.content.Context;
import com.baidu.cloud.util.ArrayUtils;
import com.baidu.cloud.util.ReflectionUtils;
import dalvik.system.DexFile;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SoPathAdderV23 implements ISoPathAdder {
    @Override // com.baidu.cloud.plugin.soloader.ISoPathAdder
    public void addNativeDir(Context context, File file) {
        Object fieldValue = ReflectionUtils.getFieldValue(context.getClassLoader(), "pathList");
        Class elementClass = getElementClass();
        if (elementClass != null) {
            Object[] objArr = (Object[]) ReflectionUtils.getFieldValue(fieldValue, "nativeLibraryPathElements");
            Object createElement = createElement(elementClass, file);
            if (objArr == null || createElement == null) {
                return;
            }
            ReflectionUtils.setFieldValue(fieldValue, "nativeLibraryPathElements", ArrayUtils.insertElement(elementClass, objArr, createElement));
        }
    }

    @Override // com.baidu.cloud.plugin.soloader.ISoPathAdder
    public boolean containsNativeDir(Context context, File file) {
        Object fieldValue = ReflectionUtils.getFieldValue(context.getClassLoader(), "pathList");
        if (getElementClass() != null) {
            for (Object obj : (Object[]) ReflectionUtils.getFieldValue(fieldValue, "nativeLibraryPathElements")) {
                if (obj.toString().contains(file.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Class getElementClass() {
        try {
            return Class.forName("dalvik.system.DexPathList$Element");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected Object createElement(Class cls, File file) {
        return ReflectionUtils.newInstance(cls, new Class[]{File.class, Boolean.TYPE, File.class, DexFile.class}, new Object[]{file, true, null, null});
    }
}
