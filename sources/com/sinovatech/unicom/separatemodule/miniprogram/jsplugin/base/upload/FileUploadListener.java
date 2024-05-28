package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface FileUploadListener {
    void onCancel();

    void onComplete(int i, String str);

    void onError(String str);

    void onProgressUpdate(long j, long j2, String str);
}
