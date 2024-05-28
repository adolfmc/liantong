package com.sinovatech.unicom.separatemodule.advtise.service;

import android.arch.lifecycle.LifecycleObserver;
import android.view.View;
import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface IAdInterface extends LifecycleObserver {
    public static final int fail = 11;
    public static final int reset = 0;
    public static final int success = 10;
    public static final int videoCached = 14;
    public static final int videoComplete = 15;
    public static final int videoError = 12;
    public static final int videoRewardVerify = 13;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IAdCallBack {
        void onResult(int i, View view);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IAdClickListener {
        void onAdClick();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IBannerAdCallBack {
        void onClose();

        void onResult(int i, View view);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IDrawCallBack {
        void onCallBack(View view);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IInteractionCallBack {
        void onClose();

        void onResult(int i, View view);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ISplashAdCallBack {
        void onResult(int i, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IVideoCallBack {
        void onResult(int i, String str);
    }

    void loadBanner(IBannerAdCallBack iBannerAdCallBack);

    void loadDraw(IDrawCallBack iDrawCallBack);

    void loadInteraction(IInteractionCallBack iInteractionCallBack);

    void loadSplash(ViewGroup viewGroup, long j, ISplashAdCallBack iSplashAdCallBack, IAdClickListener iAdClickListener);

    void loadVido(String str, String str2, IVideoCallBack iVideoCallBack);
}
