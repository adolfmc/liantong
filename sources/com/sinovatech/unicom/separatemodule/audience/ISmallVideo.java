package com.sinovatech.unicom.separatemodule.audience;

import android.content.Context;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface ISmallVideo {
    void fullScreen();

    String getLiveChannel();

    int getScreenOrientation(Context context);

    BDCloudVideoView playVideo(String str);

    BDCloudVideoView playVideo(String str, String str2);

    void preload(List<String> list);

    void returnRingList();

    void setTabVisibility(int i);

    void showFreeTips(long j);
}
