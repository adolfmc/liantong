package com.sinovatech.unicom.basic.p315ui.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.home.fragment.LazyFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class LazyFragment extends BaseLazyFragment {
    public static final String INTENT_BOOLEAN_LAZYLOAD = "intent_boolean_lazyLoad";
    private static final String TAG = "LazyFragment";
    private static final int VISIBLE_STATE_GONE = 0;
    private static final int VISIBLE_STATE_NOTSET = -1;
    private static final int VISIBLE_STATE_VISIABLE = 1;
    private ConsecutiveScrollerLayout layout;
    private Bundle savedInstanceState;
    private boolean isInit = false;
    private boolean isStart = false;
    private boolean isLazyLoad = true;
    private String tabName = "";
    private int isVisibleToUserState = -1;
    private boolean isResume = false;

    protected View getPreviewLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return null;
    }

    protected void onDestroyViewLazy() {
    }

    protected void onFragmentStartLazy() {
    }

    protected void onFragmentStopLazy() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.BaseLazyFragment
    @Deprecated
    public final void onCreateView(Bundle bundle) {
        boolean z;
        super.onCreateView(bundle);
        if (getContentView() == null || UnicomHomeConstants.isInitViewPager) {
            this.savedInstanceState = bundle;
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.isLazyLoad = arguments.getBoolean(INTENT_BOOLEAN_LAZYLOAD, this.isLazyLoad);
                this.tabName = arguments.getString("tagName");
            }
            int i = this.isVisibleToUserState;
            if (i == -1) {
                z = getUserVisibleHint();
            } else {
                z = i == 1;
            }
            if (this.isLazyLoad) {
                if (z && !this.isInit) {
                    this.isInit = true;
                    onCreateViewLazy(bundle);
                    return;
                }
                LayoutInflater layoutInflater = this.inflater;
                if (layoutInflater == null) {
                    layoutInflater = LayoutInflater.from(getApplicationContext());
                }
                this.layout = new ConsecutiveScrollerLayout(layoutInflater.getContext());
                View previewLayout = getPreviewLayout(layoutInflater, this.layout);
                if (previewLayout != null) {
                    this.layout.addView(previewLayout);
                }
                this.layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                super.setContentView(this.layout);
                return;
            }
            this.isInit = true;
            onCreateViewLazy(bundle);
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.isVisibleToUserState = z ? 1 : 0;
        boolean z2 = true;
        if (!z || this.isInit || getContentView() == null) {
            z2 = false;
        } else {
            this.isInit = true;
            onCreateViewLazy(this.savedInstanceState);
            onResumeLazy(false);
        }
        if (!this.isInit || getContentView() == null) {
            return;
        }
        if (!z) {
            onPauseLazy(false);
        } else if (z2) {
        } else {
            onResumeLazy(false);
        }
    }

    @Override // android.support.p083v4.app.Fragment
    @Deprecated
    public void onStart() {
        super.onStart();
        if (this.isInit && !this.isStart && getUserVisibleHint()) {
            this.isStart = true;
        }
    }

    @Override // android.support.p083v4.app.Fragment
    @Deprecated
    public void onStop() {
        super.onStop();
        if (this.isInit && this.isStart && getUserVisibleHint()) {
            this.isStart = false;
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.BaseLazyFragment
    public void setContentView(int i) {
        if (this.isLazyLoad && getContentView() != null && getContentView().getParent() != null) {
            this.layout.removeAllViews();
            this.layout.addView(this.inflater.inflate(i, (ViewGroup) this.layout, false));
            return;
        }
        super.setContentView(i);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.BaseLazyFragment
    public void setContentView(View view) {
        if (this.isLazyLoad && getContentView() != null && getContentView().getParent() != null) {
            this.layout.removeAllViews();
            this.layout.addView(view);
            return;
        }
        super.setContentView(view);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    @Deprecated
    public void onResume() {
        super.onResume();
        if (this.isLazyLoad && this.isInit && getUserVisibleHint()) {
            onResumeLazy(true);
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    @Deprecated
    public void onPause() {
        super.onPause();
        if (this.isLazyLoad && this.isInit && getUserVisibleHint()) {
            onPauseLazy(true);
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.BaseLazyFragment, android.support.p083v4.app.Fragment
    @Deprecated
    public final void onDestroyView() {
        super.onDestroyView();
        boolean z = this.isInit;
        if (UnicomHomeConstants.isInitViewPager) {
            this.isInit = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreateViewLazy(Bundle bundle) {
        Log.d("TESHU", this.tabName + " onCreateViewLazy() called with: savedInstanceState = [" + bundle + "]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onResumeLazy(boolean z) {
        Log.d("TESHU", this.tabName + " onResumeLazy() called with: isOnResume = [" + z + "]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPauseLazy(boolean z) {
        Log.d("TESHU", this.tabName + " onPauseLazy() called with: isOnPause = [" + z + "]");
    }
}
