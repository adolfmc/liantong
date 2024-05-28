package com.baidu.mapapi.animation;

import android.view.animation.Interpolator;
import com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class Animation {
    public AbstractC2870c bdAnimation;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface AnimationListener {
        void onAnimationCancel();

        void onAnimationEnd();

        void onAnimationRepeat();

        void onAnimationStart();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RepeatMode {
        RESTART,
        REVERSE
    }

    public abstract void cancel();

    public abstract void setAnimationListener(AnimationListener animationListener);

    public abstract void setDuration(long j);

    public abstract void setInterpolator(Interpolator interpolator);
}
