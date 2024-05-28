package com.baidu.mapapi.animation;

import android.graphics.Point;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c;
import com.baidu.mapsdkplatform.comapi.p141a.C2879l;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Transformation extends Animation {
    public Transformation(Point... pointArr) {
        if (pointArr == null || pointArr.length == 0) {
            throw new NullPointerException("BDMapSDKException: the points is null");
        }
        this.bdAnimation = new C2879l(pointArr);
    }

    public Transformation(LatLng... latLngArr) {
        if (latLngArr == null || latLngArr.length == 0) {
            throw new NullPointerException("BDMapSDKException: the latlngs is null");
        }
        this.bdAnimation = new C2879l(latLngArr);
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
