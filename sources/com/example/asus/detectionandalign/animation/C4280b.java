package com.example.asus.detectionandalign.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;
import com.example.asus.detectionandalign.C4243R;
import com.example.asus.detectionandalign.PercentRelativeLayout.C4238PercentRelativeLayout;
import com.example.asus.detectionandalign.PercentRelativeLayout.C4240a;
import com.example.asus.detectionandalign.utils.LogUtils;
import java.util.ArrayList;

/* renamed from: com.example.asus.detectionandalign.animation.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4280b {

    /* renamed from: a */
    public static final String f10047a = "b";

    /* renamed from: c */
    private Activity f10049c;

    /* renamed from: d */
    private ImageView f10050d;

    /* renamed from: e */
    private C4238PercentRelativeLayout f10051e;

    /* renamed from: f */
    private ImageView f10052f;

    /* renamed from: g */
    private ImageView f10053g;

    /* renamed from: h */
    private ImageView f10054h;

    /* renamed from: i */
    private ImageView f10055i;

    /* renamed from: j */
    private ImageView f10056j;

    /* renamed from: k */
    private ImageView f10057k;

    /* renamed from: m */
    private int f10059m;

    /* renamed from: n */
    private int f10060n;

    /* renamed from: l */
    private final long f10058l = 250;

    /* renamed from: b */
    private LogUtils f10048b = LogUtils.m15966a(true);

    public C4280b(Activity activity, int i, int i2) {
        this.f10049c = activity;
        this.f10059m = i;
        this.f10060n = i2;
        m16003a();
    }

    /* renamed from: a */
    public void m16003a() {
        this.f10050d = (ImageView) this.f10049c.findViewById(C4243R.C4246id.oliveapp_start_frame);
        this.f10051e = (C4238PercentRelativeLayout) this.f10049c.findViewById(C4243R.C4246id.oliveapp_detected_layout);
    }

    /* renamed from: a */
    public void m16002a(Pair<Double, Double> pair) {
        if (this.f10051e == null) {
            this.f10051e = (C4238PercentRelativeLayout) this.f10049c.findViewById(C4243R.C4246id.oliveapp_detected_layout);
        }
        this.f10056j = new ImageView(this.f10049c);
        this.f10057k = new ImageView(this.f10049c);
        this.f10056j.setImageResource(C4243R.mipmap.oliveapp_mouth_close);
        this.f10057k.setImageResource(C4243R.mipmap.oliveapp_mouth_open);
        this.f10056j.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f10057k.setScaleType(ImageView.ScaleType.FIT_XY);
        C4238PercentRelativeLayout.C4239a c4239a = (C4238PercentRelativeLayout.C4239a) this.f10050d.getLayoutParams();
        float f = c4239a.mo16279a().f9751a;
        float f2 = c4239a.mo16279a().f9752b;
        float f3 = c4239a.mo16279a().f9753c;
        float f4 = c4239a.mo16279a().f9754d;
        C4238PercentRelativeLayout.C4239a c4239a2 = new C4238PercentRelativeLayout.C4239a(-1, -1);
        C4240a.C4241a mo16279a = c4239a2.mo16279a();
        mo16279a.f9751a = f / 4.0f;
        mo16279a.f9752b = f2 / 8.0f;
        mo16279a.f9753c = (1.0f - ((float) ((Double) pair.first).doubleValue())) - 0.08f;
        mo16279a.f9757g = mo16279a.f9753c;
        mo16279a.f9754d = ((float) ((Double) pair.second).doubleValue()) - 0.015f;
        this.f10056j.requestLayout();
        this.f10057k.requestLayout();
        this.f10056j.setLayoutParams(c4239a2);
        this.f10057k.setLayoutParams(c4239a2);
        this.f10051e.addView(this.f10056j);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f10056j, "scaleX", 2.0f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f10056j, "scaleY", 2.0f, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f10056j, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(500L);
        animatorSet2.playTogether(ofFloat, ofFloat2, ofFloat3);
        animatorSet2.addListener(new Animator.AnimatorListener() { // from class: com.example.asus.detectionandalign.animation.b.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                C4280b.this.f10051e.removeAllViews();
                C4280b.this.f10051e.addView(C4280b.this.f10057k);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f10057k, "scaleX", 1.0f, 2.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.f10057k, "scaleY", 1.0f, 2.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.f10057k, "alpha", 1.0f, 0.0f);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.setDuration(500L);
        animatorSet3.playTogether(ofFloat4, ofFloat5, ofFloat6);
        animatorSet.play(animatorSet2).before(animatorSet3);
        animatorSet.start();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public void m16000a(String str, float[] fArr) {
        char c;
        this.f10051e.removeView(this.f10056j);
        this.f10051e.removeView(this.f10057k);
        this.f10051e.removeView(this.f10052f);
        this.f10051e.removeView(this.f10053g);
        this.f10051e.removeView(this.f10054h);
        this.f10051e.removeView(this.f10055i);
        Log.e(f10047a, this.f10059m + "  " + this.f10060n);
        switch (str.hashCode()) {
            case -1221271397:
                if (str.equals("headUp")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 100913:
                if (str.equals("eye")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 99151468:
                if (str.equals("headL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 99151474:
                if (str.equals("headR")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 104086727:
                if (str.equals("mouth")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ArrayList<Pair<Double, Double>> arrayList = new ArrayList<>();
                Pair<Double, Double> pair = new Pair<>(Double.valueOf(fArr[0] / this.f10059m), Double.valueOf(fArr[1] / this.f10060n));
                Pair<Double, Double> pair2 = new Pair<>(Double.valueOf(fArr[2] / this.f10059m), Double.valueOf(fArr[3] / this.f10060n));
                arrayList.add(pair);
                arrayList.add(pair2);
                m15999a(arrayList);
                return;
            case 1:
                m16002a(new Pair<>(Double.valueOf(fArr[8] / this.f10059m), Double.valueOf(fArr[9] / this.f10060n)));
                return;
            case 2:
                m15996c();
                return;
            case 3:
                m15995d();
                return;
            case 4:
                m15994e();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m15999a(ArrayList<Pair<Double, Double>> arrayList) {
        Pair<Double, Double> pair = arrayList.get(0);
        Pair<Double, Double> pair2 = arrayList.get(1);
        if (this.f10051e == null) {
            this.f10051e = (C4238PercentRelativeLayout) this.f10049c.findViewById(C4243R.C4246id.oliveapp_detected_layout);
        }
        this.f10052f = new ImageView(this.f10049c);
        this.f10053g = new ImageView(this.f10049c);
        this.f10052f.setImageResource(C4243R.mipmap.oliveapp_detect_eye_location);
        this.f10053g.setImageResource(C4243R.mipmap.oliveapp_detect_eye_location);
        this.f10052f.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f10053g.setScaleType(ImageView.ScaleType.FIT_XY);
        C4238PercentRelativeLayout.C4239a c4239a = new C4238PercentRelativeLayout.C4239a(-1, -1);
        C4240a.C4241a mo16279a = c4239a.mo16279a();
        mo16279a.f9751a = 0.15f;
        mo16279a.f9752b = 0.1f;
        mo16279a.f9753c = (1.0f - ((float) ((Double) pair.first).doubleValue())) - 0.1f;
        mo16279a.f9757g = mo16279a.f9753c;
        mo16279a.f9754d = ((float) ((Double) pair.second).doubleValue()) - 0.05f;
        this.f10052f.requestLayout();
        C4238PercentRelativeLayout.C4239a c4239a2 = new C4238PercentRelativeLayout.C4239a(-2, -2);
        C4240a.C4241a mo16279a2 = c4239a2.mo16279a();
        mo16279a2.f9751a = 0.15f;
        mo16279a2.f9752b = 0.1f;
        mo16279a2.f9753c = (1.0f - ((float) ((Double) pair2.first).doubleValue())) - 0.1f;
        mo16279a2.f9757g = mo16279a2.f9753c;
        mo16279a2.f9754d = ((float) ((Double) pair2.second).doubleValue()) - 0.05f;
        this.f10053g.requestLayout();
        this.f10052f.setLayoutParams(c4239a);
        this.f10053g.setLayoutParams(c4239a2);
        this.f10051e.addView(this.f10052f);
        this.f10051e.addView(this.f10053g);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        AnimatorSet animatorSet3 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f10052f, "scaleX", 1.0f, 0.35f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f10052f, "scaleY", 1.0f, 0.35f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f10053g, "scaleX", 1.0f, 0.35f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f10053g, "scaleY", 1.0f, 0.35f);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.setDuration(500L);
        animatorSet4.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.f10052f, "scaleX", 0.35f, 0.6f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.f10052f, "scaleY", 0.35f, 0.6f);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.f10053g, "scaleX", 0.35f, 0.6f);
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(this.f10053g, "scaleY", 0.35f, 0.6f);
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.setDuration(500L);
        animatorSet5.playTogether(ofFloat5, ofFloat6, ofFloat7, ofFloat8);
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(this.f10052f, "scaleX", 0.6f, 0.3f);
        ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(this.f10052f, "scaleY", 0.6f, 0.3f);
        ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(this.f10053g, "scaleX", 0.6f, 0.3f);
        ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(this.f10053g, "scaleY", 0.6f, 0.3f);
        AnimatorSet animatorSet6 = new AnimatorSet();
        animatorSet6.setDuration(500L);
        animatorSet6.playTogether(ofFloat9, ofFloat10, ofFloat11, ofFloat12);
        animatorSet2.play(animatorSet4).before(animatorSet5).before(animatorSet6);
        ObjectAnimator ofFloat13 = ObjectAnimator.ofFloat(this.f10052f, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat14 = ObjectAnimator.ofFloat(this.f10053g, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet7 = new AnimatorSet();
        animatorSet7.setDuration(500L);
        animatorSet7.playTogether(ofFloat13, ofFloat14);
        ObjectAnimator ofFloat15 = ObjectAnimator.ofFloat(this.f10052f, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat16 = ObjectAnimator.ofFloat(this.f10053g, "alpha", 1.0f, 0.0f);
        AnimatorSet animatorSet8 = new AnimatorSet();
        animatorSet8.setDuration(500L);
        animatorSet8.playTogether(ofFloat15, ofFloat16);
        animatorSet3.play(animatorSet8).after(500L).after(animatorSet7);
        animatorSet.playTogether(animatorSet2, animatorSet3);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.example.asus.detectionandalign.animation.b.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                C4280b.this.m15993f();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        animatorSet.start();
    }

    /* renamed from: b */
    public void m15998b() {
    }

    /* renamed from: c */
    public void m15996c() {
        if (this.f10051e == null) {
            this.f10051e = (C4238PercentRelativeLayout) this.f10049c.findViewById(C4243R.C4246id.oliveapp_detected_layout);
        }
        this.f10054h = new ImageView(this.f10049c);
        this.f10055i = new ImageView(this.f10049c);
        this.f10054h.setImageResource(C4243R.mipmap.oliveapp_chin);
        this.f10055i.setImageResource(C4243R.mipmap.oliveapp_chin_up);
        this.f10054h.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f10055i.setScaleType(ImageView.ScaleType.FIT_XY);
        C4238PercentRelativeLayout.C4239a c4239a = (C4238PercentRelativeLayout.C4239a) this.f10050d.getLayoutParams();
        float f = c4239a.mo16279a().f9751a;
        float f2 = c4239a.mo16279a().f9752b;
        float f3 = c4239a.mo16279a().f9753c;
        float f4 = c4239a.mo16279a().f9754d;
        LogUtils logUtils = this.f10048b;
        logUtils.m15967a("animation_test", "detectFrame: " + f + "   " + f2);
        C4238PercentRelativeLayout.C4239a c4239a2 = new C4238PercentRelativeLayout.C4239a(-2, -2);
        C4240a.C4241a mo16279a = c4239a2.mo16279a();
        mo16279a.f9751a = (f / 4.0f) * 3.0f;
        mo16279a.f9752b = f2 / 2.0f;
        mo16279a.f9754d = ((f2 / 6.0f) * 3.0f) + f4;
        c4239a2.addRule(14);
        this.f10054h.requestLayout();
        LogUtils logUtils2 = this.f10048b;
        logUtils2.m15967a("animation_test   ", "chinInfo:  " + mo16279a.f9751a + "   " + mo16279a.f9752b);
        C4238PercentRelativeLayout.C4239a c4239a3 = new C4238PercentRelativeLayout.C4239a(-2, -2);
        C4240a.C4241a mo16279a2 = c4239a3.mo16279a();
        mo16279a2.f9751a = f / 7.0f;
        mo16279a2.f9752b = f2 / 5.0f;
        mo16279a2.f9754d = f4 + ((f2 / 4.0f) * 3.0f);
        c4239a3.addRule(14);
        this.f10055i.requestLayout();
        this.f10054h.setLayoutParams(c4239a2);
        this.f10055i.setLayoutParams(c4239a3);
        this.f10051e.addView(this.f10054h);
        this.f10051e.addView(this.f10055i);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f10054h, "scaleX", 1.5f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f10054h, "scaleY", 1.5f, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f10054h, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(500L);
        animatorSet2.playTogether(ofFloat, ofFloat2, ofFloat3);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f10055i, "alpha", 0.0f, 1.0f);
        ofFloat4.setDuration(500L);
        ofFloat4.setRepeatCount(-1);
        ofFloat4.setRepeatMode(2);
        animatorSet.play(animatorSet2).before(ofFloat4);
        animatorSet.start();
    }

    /* renamed from: d */
    public void m15995d() {
        if (this.f10051e == null) {
            this.f10051e = (C4238PercentRelativeLayout) this.f10049c.findViewById(C4243R.C4246id.oliveapp_detected_layout);
        }
        this.f10055i = new ImageView(this.f10049c);
        this.f10055i.setImageResource(C4243R.mipmap.oliveapp_chin_left);
        this.f10055i.setScaleType(ImageView.ScaleType.FIT_XY);
        C4238PercentRelativeLayout.C4239a c4239a = (C4238PercentRelativeLayout.C4239a) this.f10050d.getLayoutParams();
        float f = c4239a.mo16279a().f9751a;
        float f2 = c4239a.mo16279a().f9752b;
        float f3 = c4239a.mo16279a().f9753c;
        float f4 = c4239a.mo16279a().f9754d;
        C4238PercentRelativeLayout.C4239a c4239a2 = new C4238PercentRelativeLayout.C4239a(-2, -2);
        C4240a.C4241a mo16279a = c4239a2.mo16279a();
        mo16279a.f9751a = f / 5.0f;
        mo16279a.f9752b = f2 / 5.0f;
        mo16279a.f9754d = f4 * 1.8f;
        mo16279a.f9753c = f3 * 0.05f;
        mo16279a.f9757g = mo16279a.f9753c;
        c4239a2.addRule(9);
        this.f10055i.requestLayout();
        this.f10055i.setLayoutParams(c4239a2);
        this.f10051e.addView(this.f10055i);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f10055i, "translationX", 0.0f, 200.0f);
        ofFloat.setDuration(500L);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(2);
        animatorSet.play(ofFloat);
        animatorSet.start();
    }

    /* renamed from: e */
    public void m15994e() {
        if (this.f10051e == null) {
            this.f10051e = (C4238PercentRelativeLayout) this.f10049c.findViewById(C4243R.C4246id.oliveapp_detected_layout);
        }
        this.f10055i = new ImageView(this.f10049c);
        this.f10055i.setImageResource(C4243R.mipmap.oliveapp_chin_right);
        this.f10055i.setScaleType(ImageView.ScaleType.FIT_XY);
        C4238PercentRelativeLayout.C4239a c4239a = (C4238PercentRelativeLayout.C4239a) this.f10050d.getLayoutParams();
        float f = c4239a.mo16279a().f9751a;
        float f2 = c4239a.mo16279a().f9752b;
        float f3 = c4239a.mo16279a().f9755e;
        float f4 = c4239a.mo16279a().f9754d;
        C4238PercentRelativeLayout.C4239a c4239a2 = new C4238PercentRelativeLayout.C4239a(-2, -2);
        C4240a.C4241a mo16279a = c4239a2.mo16279a();
        mo16279a.f9751a = f / 5.0f;
        mo16279a.f9752b = f2 / 5.0f;
        mo16279a.f9754d = f4 * 1.8f;
        mo16279a.f9755e = f3 * (-0.05f);
        c4239a2.addRule(11);
        this.f10055i.requestLayout();
        this.f10055i.setLayoutParams(c4239a2);
        this.f10051e.addView(this.f10055i);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f10055i, "translationX", 200.0f, 0.0f);
        ofFloat.setDuration(500L);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(2);
        animatorSet.play(ofFloat);
        animatorSet.start();
    }

    /* renamed from: f */
    public void m15993f() {
        C4238PercentRelativeLayout c4238PercentRelativeLayout = this.f10051e;
        if (c4238PercentRelativeLayout != null) {
            ImageView imageView = this.f10052f;
            if (imageView != null) {
                c4238PercentRelativeLayout.removeView(imageView);
                this.f10052f = null;
            }
            ImageView imageView2 = this.f10053g;
            if (imageView2 != null) {
                this.f10051e.removeView(imageView2);
                this.f10053g = null;
            }
        }
    }
}
