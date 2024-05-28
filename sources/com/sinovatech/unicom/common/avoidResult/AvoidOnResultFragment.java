package com.sinovatech.unicom.common.avoidResult;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.bytedance.applog.tracker.Tracker;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AvoidOnResultFragment extends Fragment {
    private static Map<Integer, AvoidOnResult.Callback> mCallbacksNine = new HashMap();
    private Map<Integer, PublishSubject<ActivityResultInfo>> mSubjects = new HashMap();
    private Map<Integer, AvoidOnResult.Callback> mCallbacks = new HashMap();

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public Observable<ActivityResultInfo> startForResult(final Intent intent) {
        final PublishSubject create = PublishSubject.create();
        return create.doOnSubscribe(new Consumer<Disposable>() { // from class: com.sinovatech.unicom.common.avoidResult.AvoidOnResultFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Disposable disposable) throws Exception {
                AvoidOnResultFragment.this.mSubjects.put(Integer.valueOf(create.hashCode()), create);
                AvoidOnResultFragment.this.startActivityForResult(intent, create.hashCode());
            }
        });
    }

    public void startForResult(Intent intent, AvoidOnResult.Callback callback) {
        this.mCallbacks.put(Integer.valueOf(callback.hashCode()), callback);
        if (Build.VERSION.SDK_INT == 28 && DeviceHelper.isXIAOMI()) {
            mCallbacksNine.put(Integer.valueOf(callback.hashCode()), callback);
        }
        startActivityForResult(intent, callback.hashCode());
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            PublishSubject<ActivityResultInfo> remove = this.mSubjects.remove(Integer.valueOf(i));
            if (remove != null) {
                remove.onNext(new ActivityResultInfo(i2, intent));
                remove.onComplete();
            }
            AvoidOnResult.Callback remove2 = this.mCallbacks.remove(Integer.valueOf(i));
            if (Build.VERSION.SDK_INT == 28 && DeviceHelper.isXIAOMI()) {
                remove2 = mCallbacksNine.remove(Integer.valueOf(i));
            }
            if (remove2 != null) {
                remove2.onActivityResult(i2, intent);
            }
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }
}
