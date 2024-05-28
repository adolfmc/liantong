package com.sinovatech.unicom.separatemodule.advtise.admanager;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractAdManager implements IAdInterface {
    protected abstract void init(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public abstract void onDestroy();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public abstract void onPause();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public abstract void onResume();

    protected abstract void uploadPV(String str, String str2);
}
