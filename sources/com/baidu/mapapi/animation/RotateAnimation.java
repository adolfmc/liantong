package com.baidu.mapapi.animation;

import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c;
import com.baidu.mapsdkplatform.comapi.p141a.C2873f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RotateAnimation extends Animation {
    public RotateAnimation(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            throw new NullPointerException("BDMapSDKException: the degrees can't less than zero");
        }
        this.bdAnimation = new C2873f(f, f2);
    }

    @Override // com.baidu.mapapi.animation.Animation
    public void cancel() {
        this.bdAnimation.mo18504b();
    }

    @Override // com.baidu.mapapi.animation.Animation
    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.bdAnimation.mo18508a(animationListener);
    }

    @Override // com.baidu.mapapi.animation.Animation
    public void setDuration(long j) {
        this.bdAnimation.mo18512a(j);
    }

    @Override // com.baidu.mapapi.animation.Animation
    public void setInterpolator(Interpolator interpolator) {
        this.bdAnimation.mo18509a(interpolator);
    }

    public void setRepeatCount(int i) {
        this.bdAnimation.mo18503b(i);
    }

    public void setRepeatMode(Animation.RepeatMode repeatMode) {
        AbstractC2870c abstractC2870c;
        int i;
        if (repeatMode == Animation.RepeatMode.RESTART) {
            abstractC2870c = this.bdAnimation;
            i = 1;
        } else if (repeatMode != Animation.RepeatMode.REVERSE) {
            return;
        } else {
            abstractC2870c = this.bdAnimation;
            i = 2;
        }
        abstractC2870c.mo18513a(i);
    }
}
