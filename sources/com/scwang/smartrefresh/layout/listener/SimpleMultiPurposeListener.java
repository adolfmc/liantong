package com.scwang.smartrefresh.layout.listener;

import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SimpleMultiPurposeListener implements OnMultiPurposeListener {
    @Override // com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onFooterFinish(RefreshFooter refreshFooter, boolean z) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onFooterMoving(RefreshFooter refreshFooter, boolean z, float f, int i, int i2, int i3) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onFooterReleased(RefreshFooter refreshFooter, int i, int i2) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onFooterStartAnimator(RefreshFooter refreshFooter, int i, int i2) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onHeaderFinish(RefreshHeader refreshHeader, boolean z) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onHeaderMoving(RefreshHeader refreshHeader, boolean z, float f, int i, int i2, int i3) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onHeaderReleased(RefreshHeader refreshHeader, int i, int i2) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onHeaderStartAnimator(RefreshHeader refreshHeader, int i, int i2) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnStateChangedListener
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
    }
}
