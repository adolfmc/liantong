package me.shaohui.advancedluban;

import java.io.File;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface OnMultiCompressListener {
    void onError(Throwable th);

    void onStart();

    void onSuccess(List<File> list);
}
