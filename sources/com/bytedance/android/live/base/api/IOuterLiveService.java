package com.bytedance.android.live.base.api;

import android.content.Context;
import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IOuterLiveService extends IOuterLiveRoomService {
    void enterLiveRoom(Context context, long j, Bundle bundle);

    ILiveOuterPreviewCoverView makePreviewCoverView(Context context, Bundle bundle);

    ILiveOuterPreviewFragment makePreviewFragment();
}
