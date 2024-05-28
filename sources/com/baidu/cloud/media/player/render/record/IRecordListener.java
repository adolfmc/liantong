package com.baidu.cloud.media.player.render.record;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IRecordListener {
    void onError(int i, String str);

    void onProgress(long j);

    void onStartSuccess();

    void onStopSuccess();
}
