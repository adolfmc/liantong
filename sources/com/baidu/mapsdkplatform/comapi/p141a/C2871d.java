package com.baidu.mapsdkplatform.comapi.p141a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.annotation.TargetApi;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.AlphaAnimation;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.RotateAnimation;
import com.baidu.mapapi.animation.ScaleAnimation;
import com.baidu.mapapi.animation.SingleScaleAnimation;
import com.baidu.mapapi.animation.Transformation;
import com.baidu.mapapi.map.Marker;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2871d extends AbstractC2870c {

    /* renamed from: a */
    private Animator f7078a = null;

    /* renamed from: b */
    private long f7079b = 0;

    /* renamed from: c */
    private Interpolator f7080c = null;

    /* renamed from: d */
    private Animation.AnimationListener f7081d = null;

    /* renamed from: e */
    private int f7082e = 0;

    /* renamed from: f */
    private ArrayList<Animation> f7083f = new ArrayList<>();

    @TargetApi(11)
    /* renamed from: b */
    private ObjectAnimator m18524b(Marker marker, Animation animation) {
        if (animation instanceof AlphaAnimation) {
            return ((C2868a) animation.bdAnimation).m18529a(marker);
        }
        if (animation instanceof RotateAnimation) {
            return ((C2873f) animation.bdAnimation).m18523a(marker);
        }
        if (animation instanceof Transformation) {
            return ((C2879l) animation.bdAnimation).m18507a(marker);
        }
        if (animation instanceof ScaleAnimation) {
            return ((C2875h) animation.bdAnimation).m18520a(marker);
        }
        if (animation instanceof SingleScaleAnimation) {
            return ((C2877j) animation.bdAnimation).m18517a(marker);
        }
        return null;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    public void mo18514a() {
        Animator animator = this.f7078a;
        if (animator == null) {
            return;
        }
        animator.start();
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18513a(int i) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18512a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.f7079b = j;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    protected void mo18511a(Animator animator) {
        if (animator == null) {
            return;
        }
        animator.addListener(new C2872e(this));
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18510a(TypeEvaluator typeEvaluator) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18509a(Interpolator interpolator) {
        this.f7080c = interpolator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18508a(Animation.AnimationListener animationListener) {
        this.f7081d = animationListener;
    }

    /* renamed from: a */
    public void m18526a(Animation animation) {
        if (this.f7083f.contains(animation)) {
            return;
        }
        this.f7083f.add(animation);
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    public void mo18506a(Marker marker, Animation animation) {
        ObjectAnimator m18524b;
        this.f7078a = new AnimatorSet();
        ArrayList<Animation> arrayList = this.f7083f;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            Animation animation2 = arrayList.get(i);
            if (animation2 != null && (m18524b = m18524b(marker, animation2)) != null) {
                arrayList2.add(m18524b);
            }
        }
        long j = this.f7079b;
        if (j != 0) {
            this.f7078a.setDuration(j);
        }
        Interpolator interpolator = this.f7080c;
        if (interpolator != null) {
            this.f7078a.setInterpolator(interpolator);
        }
        if (arrayList2.size() != 0) {
            int i2 = this.f7082e;
            if (i2 == 0) {
                ((AnimatorSet) this.f7078a).playTogether(arrayList2);
            } else if (i2 == 1) {
                ((AnimatorSet) this.f7078a).playSequentially(arrayList2);
            }
        }
        mo18511a(this.f7078a);
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: b */
    public void mo18504b() {
        Animator animator = this.f7078a;
        if (animator != null) {
            animator.cancel();
            this.f7078a = null;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: b */
    public void mo18503b(int i) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: c */
    public void mo18501c(int i) {
        this.f7082e = i;
    }
}
