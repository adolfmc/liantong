package com.baidu.license.subtitle;

import com.baidu.license.INotProguard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IBDSubtitleCallback extends INotProguard {
    void getResolveJobResult(String str);

    void getSubtitleResult(String str);

    void getUploadParams(String str);

    void onFail(int i, String str);
}
