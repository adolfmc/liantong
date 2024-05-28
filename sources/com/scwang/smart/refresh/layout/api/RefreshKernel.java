package com.scwang.smart.refresh.layout.api;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import com.scwang.smart.refresh.layout.constant.RefreshState;

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

    RefreshKernel requestDefaultTranslationContentFor(@NonNull RefreshComponent refreshComponent, boolean z);

    RefreshKernel requestDrawBackgroundFor(@NonNull RefreshComponent refreshComponent, int i);

    RefreshKernel requestFloorBottomPullUpToCloseRate(float f);

    RefreshKernel requestFloorDuration(int i);

    RefreshKernel requestNeedTouchEventFor(@NonNull RefreshComponent refreshComponent, boolean z);

    RefreshKernel requestRemeasureHeightFor(@NonNull RefreshComponent refreshComponent);

    RefreshKernel setState(@NonNull RefreshState refreshState);

    RefreshKernel startTwoLevel(boolean z);
}
