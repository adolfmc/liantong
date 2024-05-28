package com.scwang.smart.refresh.layout.simple;

import android.support.annotation.NonNull;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SimpleMultiListener implements OnMultiListener {
    @Override // com.scwang.smart.refresh.layout.listener.OnMultiListener
    public void onFooterFinish(RefreshFooter refreshFooter, boolean z) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnMultiListener
    public void onFooterMoving(RefreshFooter refreshFooter, boolean z, float f, int i, int i2, int i3) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnMultiListener
    public void onFooterReleased(RefreshFooter refreshFooter, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnMultiListener
    public void onFooterStartAnimator(RefreshFooter refreshFooter, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnMultiListener
    public void onHeaderFinish(RefreshHeader refreshHeader, boolean z) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnMultiListener
    public void onHeaderMoving(RefreshHeader refreshHeader, boolean z, float f, int i, int i2, int i3) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnMultiListener
    public void onHeaderReleased(RefreshHeader refreshHeader, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnMultiListener
    public void onHeaderStartAnimator(RefreshHeader refreshHeader, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnStateChangedListener
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
    }
}
