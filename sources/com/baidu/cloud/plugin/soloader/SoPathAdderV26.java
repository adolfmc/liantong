package com.baidu.cloud.plugin.soloader;

import com.baidu.cloud.util.ReflectionUtils;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SoPathAdderV26 extends SoPathAdderV23 {
    @Override // com.baidu.cloud.plugin.soloader.SoPathAdderV23
    protected Class getElementClass() {
        try {
            return Class.forName("dalvik.system.DexPathList$NativeLibraryElement");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.baidu.cloud.plugin.soloader.SoPathAdderV23
    protected Object createElement(Class cls, File file) {
        return ReflectionUtils.newInstance(cls, new Class[]{File.class}, new Object[]{file});
    }
}
