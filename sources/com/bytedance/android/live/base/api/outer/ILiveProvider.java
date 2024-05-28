package com.bytedance.android.live.base.api.outer;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.android.live.base.api.outer.data.RoomInfo;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ILiveProvider {
    String getIdentity();

    ILiveView getLiveView(Context context, int i, String str, boolean z, Bundle bundle);

    List<RoomInfo> getRoomInfoList(int i, int i2, int i3);

    void startLive(Context context, int i, String str, Bundle bundle);
}
