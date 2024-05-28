package com.sinovatech.unicom.basic.p315ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.home.fragment.BaseLazyFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaseLazyFragment extends BaseWebFragment {
    private ViewGroup container;
    private View contentView;
    private Context context;
    protected LayoutInflater inflater;

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreateView(Bundle bundle) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = getActivity().getApplicationContext();
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.inflater = layoutInflater;
        this.container = viewGroup;
        onCreateView(bundle);
        View view = this.contentView;
        return view == null ? super.onCreateView(layoutInflater, viewGroup, bundle) : view;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    public Context getApplicationContext() {
        return this.context;
    }

    public void setContentView(int i) {
        setContentView((ViewGroup) this.inflater.inflate(i, this.container, false));
    }

    public void setContentView(View view) {
        this.contentView = view;
    }

    public View getContentView() {
        return this.contentView;
    }

    public <T extends View> T findViewById(@IdRes int i) {
        View view = this.contentView;
        if (view != null) {
            return (T) view.findViewById(i);
        }
        return null;
    }
}
