package com.bytedance.android.live.base.api;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.android.live.base.api.outer.ILiveProvider;
import com.bytedance.android.live.base.api.push.ILivePush;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IOuterLiveRoomService {
    Object callExpandMethod(String str, Object... objArr);

    void enterRoomWithDraw();

    ILiveProvider getLiveProvider();

    ILivePush getPushService();

    IBaseHorizontalLiveListView makeFollowListView(Context context, Bundle bundle, ILiveBorderAnimController iLiveBorderAnimController);

    void setEventListener(IEventListener iEventListener);

    void setNeedPersonalRecommend(boolean z);
}
