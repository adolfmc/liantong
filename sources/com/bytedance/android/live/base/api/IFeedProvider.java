package com.bytedance.android.live.base.api;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.android.live.base.IService;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IFeedProvider extends IService {
    IBaseHorizontalLiveListView getFollowListView(Context context, Bundle bundle, ILiveBorderAnimController iLiveBorderAnimController);
}
