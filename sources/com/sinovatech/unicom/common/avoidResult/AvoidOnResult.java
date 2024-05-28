package com.sinovatech.unicom.common.avoidResult;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AvoidOnResult {
    private static final String TAG = "AvoidOnResult";
    private AvoidOnResultFragment mAvoidOnResultFragment;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface Callback {
        void onActivityResult(int i, Intent intent);
    }

    public AvoidOnResult(Activity activity) {
        this.mAvoidOnResultFragment = getAvoidOnResultFragment(activity);
    }

    public AvoidOnResult(Fragment fragment) {
        this(fragment.getActivity());
    }

    private AvoidOnResultFragment getAvoidOnResultFragment(Activity activity) {
        AvoidOnResultFragment findAvoidOnResultFragment = findAvoidOnResultFragment(activity);
        if (findAvoidOnResultFragment == null) {
            findAvoidOnResultFragment = new AvoidOnResultFragment();
            try {
                FragmentManager fragmentManager = activity.getFragmentManager();
                fragmentManager.beginTransaction().add(findAvoidOnResultFragment, TAG).commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            } catch (Exception e) {
                MsLogUtil.m7978e("AvoidOnResult异常:" + e.getMessage());
            }
        }
        return findAvoidOnResultFragment;
    }

    private AvoidOnResultFragment findAvoidOnResultFragment(Activity activity) {
        return (AvoidOnResultFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    public Observable<ActivityResultInfo> startForResult(Intent intent) {
        return this.mAvoidOnResultFragment.startForResult(intent);
    }

    public Observable<ActivityResultInfo> startForResult(Class<?> cls) {
        return startForResult(new Intent(this.mAvoidOnResultFragment.getActivity(), cls));
    }

    public void startForResult(Intent intent, Callback callback) {
        try {
            this.mAvoidOnResultFragment.startForResult(intent, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startForResult(Class<?> cls, Callback callback) {
        try {
            startForResult(new Intent(this.mAvoidOnResultFragment.getActivity(), cls), callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startForResult(Class<?> cls, Callback callback, boolean z) {
        startForResult(new Intent(this.mAvoidOnResultFragment.getActivity(), cls), callback);
    }
}
