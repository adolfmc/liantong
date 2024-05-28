package com.billy.android.swipe.refresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.billy.android.swipe.C3336R;
import com.billy.android.swipe.SmartSwipeRefresh;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ClassicFooter extends ClassicHeader implements SmartSwipeRefresh.SmartSwipeRefreshFooter {
    public boolean mNoMoreData;

    public ClassicFooter(Context context) {
        super(context);
    }

    public ClassicFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ClassicFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public ClassicFooter(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // com.billy.android.swipe.refresh.ClassicHeader, com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onProgress(boolean z, float f) {
        if (this.mNoMoreData) {
            cancelAnimation();
        } else if (z) {
            setText(f >= 1.0f ? C3336R.string.ssr_footer_release : C3336R.string.ssr_footer_pulling);
        } else if (f <= 0.0f) {
            cancelAnimation();
        }
    }

    @Override // com.billy.android.swipe.refresh.ClassicHeader, com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public long onFinish(boolean z) {
        cancelAnimation();
        if (this.mNoMoreData) {
            return 500L;
        }
        setText(z ? C3336R.string.ssr_footer_finish : C3336R.string.ssr_footer_failed);
        return 500L;
    }

    @Override // com.billy.android.swipe.refresh.ClassicHeader, com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onDataLoading() {
        if (this.mNoMoreData) {
            return;
        }
        showAnimation();
        setText(C3336R.string.ssr_footer_refreshing);
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshFooter
    public void setNoMoreData(boolean z) {
        this.mNoMoreData = z;
        setText(C3336R.string.ssr_footer_no_more_data);
    }
}
