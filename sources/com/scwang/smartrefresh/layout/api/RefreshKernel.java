package com.scwang.smartrefresh.layout.api;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface RefreshKernel {
    ValueAnimator animSpinner(int i);

    RefreshKernel finishTwoLevel();

    @NonNull
    RefreshContent getRefreshContent();

    @NonNull
    RefreshLayout getRefreshLayout();

    RefreshKernel moveSpinner(int i, boolean z);

    RefreshKernel requestDefaultTranslationContentFor(@NonNull RefreshInternal refreshInternal, boolean z);

    RefreshKernel requestDrawBackgroundFor(@NonNull RefreshInternal refreshInternal, int i);

    RefreshKernel requestFloorDuration(int i);

    RefreshKernel requestNeedTouchEventFor(@NonNull RefreshInternal refreshInternal, boolean z);

    RefreshKernel requestRemeasureHeightFor(@NonNull RefreshInternal refreshInternal);

    RefreshKernel setState(@NonNull RefreshState refreshState);

    RefreshKernel startTwoLevel(boolean z);
}
