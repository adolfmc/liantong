package com.baidu.cloud.plugin.soloader;

import android.content.Context;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ISoPathAdder {
    void addNativeDir(Context context, File file);

    boolean containsNativeDir(Context context, File file);
}
