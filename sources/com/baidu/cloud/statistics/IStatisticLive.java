package com.baidu.cloud.statistics;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IStatisticLive {
    void onLiveConnected(long j, int i, String str);

    void onLiveEnd(int i, long j);

    void onLiveError(int i);

    void onLiveMetadata(int i, int i2);

    void onLiveMute(int i);

    void onLivePause(boolean z);

    void onLivePushImage(int i);

    void onLiveStart();

    void onLiveUpdateInfo(int i, int i2, int i3, double d, double d2, float f, String str);

    void release();

    void setLiveRelated(int i, int i2, int i3, int i4);

    void updateSession(String str);
}
