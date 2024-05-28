package com.baidu.p120ar.record;

import android.view.Surface;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.record.MovieRecorderCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface MovieRecorderCallback {
    void onRecorderComplete(boolean z, String str);

    void onRecorderError(int i);

    void onRecorderInit(Surface surface);

    void onRecorderProcess(int i);

    void onRecorderStart(boolean z);
}
