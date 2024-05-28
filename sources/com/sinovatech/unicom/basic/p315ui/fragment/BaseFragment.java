package com.sinovatech.unicom.basic.p315ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import com.bytedance.applog.tracker.Tracker;
import com.sinovatech.unicom.common.CustomDensityHandler;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.fragment.BaseFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaseFragment extends Fragment {
    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    public static BaseFragment newInstance() {
        return new BaseFragment();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            CustomDensityHandler.setCustomDensity(activity, activity.getApplication());
        }
        try {
            UnicomCollectManager.getInstance().pageOnResume();
        } catch (Exception e) {
            MsLogUtil.m7979d("UnicomCollectManager", "感知埋点获取页面时间异常" + e.getMessage());
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}
