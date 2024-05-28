package com.baidu.mapapi.animation;

import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapsdkplatform.comapi.p141a.C2871d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AnimationSet extends Animation {
    public AnimationSet() {
        this.bdAnimation = new C2871d();
    }

    public void addAnimation(Animation animation) {
        if (animation != null) {
            ((C2871d) this.bdAnimation).m18526a(animation);
        }
    }

    @Override // com.baidu.mapapi.animation.Animation
    public void cancel() {
        this.bdAnimation.mo18504b();
    }

    @Override // com.baidu.mapapi.animation.Animation
    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.bdAnimation.mo18508a(animationListener);
    }

    public void setAnimatorSetMode(int i) {
        this.bdAnimation.mo18501c(i);
    }

    @Override // com.baidu.mapapi.animation.Animation
    public void setDuration(long j) {
        this.bdAnimation.mo18512a(j);
    }

    @Override // com.baidu.mapapi.animation.Animation
    public void setInterpolator(Interpolator interpolator) {
        this.bdAnimation.mo18509a(interpolator);
    }
}
