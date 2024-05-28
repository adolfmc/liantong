package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MyHeaderView extends InternalAbstract {
    public static String REFRESH_HEADER_LOADING = "刷新中...";
    public static String REFRESH_HEADER_PULLING = "下拉刷新";
    public static String REFRESH_HEADER_RELEASE = "松开刷新";
    private TextView mTitleText;

    public MyHeaderView(Context context) {
        this(context, null);
    }

    public MyHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTitleText = (TextView) LayoutInflater.from(context).inflate(2131493180, this).findViewById(2131297191);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.listener.OnStateChangedListener
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        switch (refreshState2) {
            case PullDownToRefresh:
                this.mTitleText.setText(REFRESH_HEADER_PULLING);
                return;
            case ReleaseToRefresh:
                this.mTitleText.setText(REFRESH_HEADER_RELEASE);
                return;
            case Refreshing:
                this.mTitleText.setText(REFRESH_HEADER_LOADING);
                return;
            default:
                return;
        }
    }
}
