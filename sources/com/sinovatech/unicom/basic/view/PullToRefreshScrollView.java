package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import com.sinovatech.unicom.basic.view.PullToRefreshBase;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PullToRefreshScrollView extends PullToRefreshBase<CScrollView> {
    private final PullToRefreshBase.OnRefreshListener defaultOnRefreshListener;

    public PullToRefreshScrollView(Context context) {
        super(context);
        this.defaultOnRefreshListener = new PullToRefreshBase.OnRefreshListener() { // from class: com.sinovatech.unicom.basic.view.PullToRefreshScrollView.1
            @Override // com.sinovatech.unicom.basic.view.PullToRefreshBase.OnRefreshListener
            public void onRefresh() {
                PullToRefreshScrollView.this.onRefreshComplete();
            }
        };
        setOnRefreshListener(this.defaultOnRefreshListener);
    }

    public PullToRefreshScrollView(Context context, int i) {
        super(context, i);
        this.defaultOnRefreshListener = new PullToRefreshBase.OnRefreshListener() { // from class: com.sinovatech.unicom.basic.view.PullToRefreshScrollView.1
            @Override // com.sinovatech.unicom.basic.view.PullToRefreshBase.OnRefreshListener
            public void onRefresh() {
                PullToRefreshScrollView.this.onRefreshComplete();
            }
        };
        setOnRefreshListener(this.defaultOnRefreshListener);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultOnRefreshListener = new PullToRefreshBase.OnRefreshListener() { // from class: com.sinovatech.unicom.basic.view.PullToRefreshScrollView.1
            @Override // com.sinovatech.unicom.basic.view.PullToRefreshBase.OnRefreshListener
            public void onRefresh() {
                PullToRefreshScrollView.this.onRefreshComplete();
            }
        };
        setOnRefreshListener(this.defaultOnRefreshListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sinovatech.unicom.basic.view.PullToRefreshBase
    public CScrollView createRefreshableView(Context context, AttributeSet attributeSet) {
        CScrollView cScrollView = new CScrollView(context, attributeSet);
        cScrollView.setId(2131299588);
        return cScrollView;
    }

    @Override // com.sinovatech.unicom.basic.view.PullToRefreshBase
    protected boolean isReadyForPullDown() {
        return ((CScrollView) this.refreshableView).getScrollY() == 0;
    }

    @Override // com.sinovatech.unicom.basic.view.PullToRefreshBase
    protected boolean isReadyForPullUp() {
        CScrollView refreshableView = getRefreshableView();
        return (refreshableView.getScrollY() + refreshableView.getHeight()) - refreshableView.getChildAt(0).getHeight() == 0;
    }
}
