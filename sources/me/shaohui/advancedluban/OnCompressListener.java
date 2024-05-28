package me.shaohui.advancedluban;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface OnCompressListener {
    void onError(Throwable th);

    void onStart();

    void onSuccess(File file);
}
