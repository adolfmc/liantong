package comp.android.app.face.p381sz.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import comp.android.app.face.p381sz.camera.listener.CaptureListener;
import comp.android.app.face.p381sz.camera.listener.ClickListener;
import comp.android.app.face.p381sz.camera.listener.ReturnListener;
import comp.android.app.face.p381sz.camera.listener.TypeListener;

/* renamed from: comp.android.app.face.sz.camera.CaptureLayout */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CaptureLayout extends FrameLayout {

    /* renamed from: a */
    private CaptureListener f23836a;

    /* renamed from: b */
    private TypeListener f23837b;

    /* renamed from: c */
    private ReturnListener f23838c;

    /* renamed from: d */
    private ClickListener f23839d;

    /* renamed from: e */
    private ClickListener f23840e;

    /* renamed from: f */
    private C11769b f23841f;

    /* renamed from: g */
    private C11779d f23842g;

    /* renamed from: h */
    private C11779d f23843h;

    /* renamed from: i */
    private C11778c f23844i;

    /* renamed from: j */
    private ImageView f23845j;

    /* renamed from: k */
    private ImageView f23846k;

    /* renamed from: l */
    private TextView f23847l;

    /* renamed from: m */
    private int f23848m;

    /* renamed from: n */
    private int f23849n;

    /* renamed from: o */
    private int f23850o;

    /* renamed from: p */
    private int f23851p;

    /* renamed from: q */
    private int f23852q;

    /* renamed from: r */
    private boolean f23853r;

    public CaptureLayout(Context context) {
        this(context, null);
    }

    public CaptureLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CaptureLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f23851p = 0;
        this.f23852q = 0;
        this.f23853r = true;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.f23848m = getResources().getConfiguration().orientation == 1 ? displayMetrics.widthPixels : displayMetrics.widthPixels / 2;
        this.f23850o = (int) (this.f23848m / 4.5f);
        int i2 = this.f23850o;
        this.f23849n = i2 + ((i2 / 5) * 2) + 100;
        m2241e();
        m2250a();
    }

    /* renamed from: e */
    private void m2241e() {
        setWillNotDraw(false);
        this.f23841f = new C11769b(getContext(), this.f23850o);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.f23841f.setLayoutParams(layoutParams);
        this.f23841f.setCaptureLisenter(new CaptureListener() { // from class: comp.android.app.face.sz.camera.CaptureLayout.2
            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordEnd(long j) {
                if (CaptureLayout.this.f23836a != null) {
                    CaptureLayout.this.f23836a.recordEnd(j);
                }
                CaptureLayout.this.m2243d();
                CaptureLayout.this.m2247b();
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordError() {
                if (CaptureLayout.this.f23836a != null) {
                    CaptureLayout.this.f23836a.recordError();
                }
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordShort(long j) {
                if (CaptureLayout.this.f23836a != null) {
                    CaptureLayout.this.f23836a.recordShort(j);
                }
                CaptureLayout.this.m2243d();
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordStart() {
                if (CaptureLayout.this.f23836a != null) {
                    CaptureLayout.this.f23836a.recordStart();
                }
                CaptureLayout.this.m2243d();
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void recordZoom(float f) {
                if (CaptureLayout.this.f23836a != null) {
                    CaptureLayout.this.f23836a.recordZoom(f);
                }
            }

            @Override // comp.android.app.face.p381sz.camera.listener.CaptureListener
            public void takePictures() {
                if (CaptureLayout.this.f23836a != null) {
                    CaptureLayout.this.f23836a.takePictures();
                }
            }
        });
        this.f23843h = new C11779d(getContext(), 1, this.f23850o);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 16;
        layoutParams2.setMargins((this.f23848m / 4) - (this.f23850o / 2), 0, 0, 0);
        this.f23843h.setLayoutParams(layoutParams2);
        this.f23843h.setOnClickListener(new View.OnClickListener() { // from class: comp.android.app.face.sz.camera.CaptureLayout.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (CaptureLayout.this.f23837b != null) {
                    CaptureLayout.this.f23837b.cancel();
                }
                CaptureLayout.this.m2243d();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f23842g = new C11779d(getContext(), 2, this.f23850o);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams3.gravity = 21;
        layoutParams3.setMargins(0, 0, (this.f23848m / 4) - (this.f23850o / 2), 0);
        this.f23842g.setLayoutParams(layoutParams3);
        this.f23842g.setOnClickListener(new View.OnClickListener() { // from class: comp.android.app.face.sz.camera.CaptureLayout.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (CaptureLayout.this.f23837b != null) {
                    CaptureLayout.this.f23837b.confirm();
                }
                CaptureLayout.this.m2243d();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f23844i = new C11778c(getContext(), (int) (this.f23850o / 2.5f));
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 16;
        layoutParams4.setMargins(this.f23848m / 6, 0, 0, 0);
        this.f23844i.setLayoutParams(layoutParams4);
        this.f23844i.setOnClickListener(new View.OnClickListener() { // from class: comp.android.app.face.sz.camera.CaptureLayout.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (CaptureLayout.this.f23839d != null) {
                    CaptureLayout.this.f23839d.onClick();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f23845j = new ImageView(getContext());
        int i = this.f23850o;
        FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams((int) (i / 2.5f), (int) (i / 2.5f));
        layoutParams5.gravity = 16;
        layoutParams5.setMargins(this.f23848m / 6, 0, 0, 0);
        this.f23845j.setLayoutParams(layoutParams5);
        this.f23845j.setOnClickListener(new View.OnClickListener() { // from class: comp.android.app.face.sz.camera.CaptureLayout.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (CaptureLayout.this.f23839d != null) {
                    CaptureLayout.this.f23839d.onClick();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f23846k = new ImageView(getContext());
        int i2 = this.f23850o;
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams((int) (i2 / 2.5f), (int) (i2 / 2.5f));
        layoutParams6.gravity = 21;
        layoutParams6.setMargins(0, 0, this.f23848m / 6, 0);
        this.f23846k.setLayoutParams(layoutParams6);
        this.f23846k.setOnClickListener(new View.OnClickListener() { // from class: comp.android.app.face.sz.camera.CaptureLayout.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (CaptureLayout.this.f23840e != null) {
                    CaptureLayout.this.f23840e.onClick();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f23847l = new TextView(getContext());
        FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams7.gravity = 1;
        layoutParams7.setMargins(0, 0, 0, 0);
        this.f23847l.setText("轻触拍照，长按摄像");
        this.f23847l.setTextColor(-1);
        this.f23847l.setGravity(17);
        this.f23847l.setLayoutParams(layoutParams7);
        addView(this.f23841f);
        addView(this.f23843h);
        addView(this.f23842g);
        addView(this.f23844i);
        addView(this.f23845j);
        addView(this.f23846k);
        addView(this.f23847l);
    }

    /* renamed from: a */
    public void m2250a() {
        this.f23846k.setVisibility(8);
        this.f23843h.setVisibility(8);
        this.f23842g.setVisibility(8);
    }

    /* renamed from: a */
    public void m2249a(int i, int i2) {
        this.f23851p = i;
        this.f23852q = i2;
        if (this.f23851p != 0) {
            this.f23845j.setImageResource(i);
            this.f23845j.setVisibility(0);
            this.f23844i.setVisibility(8);
        } else {
            this.f23845j.setVisibility(8);
            this.f23844i.setVisibility(0);
        }
        if (this.f23852q == 0) {
            this.f23846k.setVisibility(8);
            return;
        }
        this.f23846k.setImageResource(i2);
        this.f23846k.setVisibility(0);
    }

    /* renamed from: b */
    public void m2247b() {
        if (this.f23851p != 0) {
            this.f23845j.setVisibility(8);
        } else {
            this.f23844i.setVisibility(8);
        }
        if (this.f23852q != 0) {
            this.f23846k.setVisibility(8);
        }
        this.f23841f.setVisibility(8);
        this.f23843h.setVisibility(0);
        this.f23842g.setVisibility(0);
        this.f23843h.setClickable(false);
        this.f23842g.setClickable(false);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f23843h, "translationX", this.f23848m / 4, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f23842g, "translationX", (-this.f23848m) / 4, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: comp.android.app.face.sz.camera.CaptureLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                CaptureLayout.this.f23843h.setClickable(true);
                CaptureLayout.this.f23842g.setClickable(true);
            }
        });
        animatorSet.setDuration(200L);
        animatorSet.start();
    }

    /* renamed from: c */
    public void m2245c() {
        this.f23841f.m2173a();
        this.f23843h.setVisibility(8);
        this.f23842g.setVisibility(8);
        this.f23841f.setVisibility(0);
        if (this.f23851p != 0) {
            this.f23845j.setVisibility(0);
        } else {
            this.f23844i.setVisibility(0);
        }
        if (this.f23852q != 0) {
            this.f23846k.setVisibility(0);
        }
    }

    /* renamed from: d */
    public void m2243d() {
        if (this.f23853r) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f23847l, "alpha", 1.0f, 0.0f);
            ofFloat.setDuration(500L);
            ofFloat.start();
            this.f23853r = false;
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(this.f23848m, this.f23849n);
    }

    public void setButtonFeatures(int i) {
        this.f23841f.setButtonFeatures(i);
    }

    public void setCaptureLisenter(CaptureListener captureListener) {
        this.f23836a = captureListener;
    }

    public void setDuration(int i) {
        this.f23841f.setDuration(i);
    }

    public void setLeftClickListener(ClickListener clickListener) {
        this.f23839d = clickListener;
    }

    public void setReturnLisenter(ReturnListener returnListener) {
        this.f23838c = returnListener;
    }

    public void setRightClickListener(ClickListener clickListener) {
        this.f23840e = clickListener;
    }

    public void setTextWithAnimation(String str) {
        this.f23847l.setText(str);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f23847l, "alpha", 0.0f, 1.0f, 1.0f, 0.0f);
        ofFloat.setDuration(2500L);
        ofFloat.start();
    }

    public void setTip(String str) {
        this.f23847l.setText(str);
    }

    public void setTypeLisenter(TypeListener typeListener) {
        this.f23837b = typeListener;
    }
}
