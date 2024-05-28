package com.unicom.pay.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import cn.ltzf.passguard.LTPassGuardEdit;
import com.unicom.pay.C10531R;
import p470p0.C13659r;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPPassEditText extends LTPassGuardEdit {

    /* renamed from: n */
    public static final int f20460n = C10531R.C10533drawable.wp_shape_rectangle_round_stroke;

    /* renamed from: a */
    public Paint f20461a;

    /* renamed from: b */
    public String f20462b;

    /* renamed from: c */
    public StringBuilder f20463c;

    /* renamed from: d */
    public int f20464d;

    /* renamed from: e */
    public float f20465e;

    /* renamed from: f */
    public RectF[] f20466f;

    /* renamed from: g */
    public Drawable f20467g;

    /* renamed from: h */
    public boolean f20468h;

    /* renamed from: i */
    public InterfaceC10713b f20469i;

    /* renamed from: j */
    public Paint f20470j;

    /* renamed from: k */
    public int f20471k;

    /* renamed from: l */
    public Rect f20472l;

    /* renamed from: m */
    public Paint f20473m;

    /* renamed from: com.unicom.pay.widget.WPPassEditText$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10712a extends AnimatorListenerAdapter {
        public C10712a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            WPPassEditText wPPassEditText = WPPassEditText.this;
            InterfaceC10713b interfaceC10713b = wPPassEditText.f20469i;
            if (interfaceC10713b != null) {
                interfaceC10713b.mo57a(wPPassEditText.getText().toString());
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.WPPassEditText$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10713b {
        /* renamed from: a */
        void mo57a(String str);
    }

    public WPPassEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f20462b = "●";
        this.f20465e = C13659r.m170a(1.0f);
        this.f20468h = true;
        C13659r.m170a(8.0f);
        m6052b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m6053a(ValueAnimator valueAnimator) {
        this.f20470j.setTextSize(((Float) valueAnimator.getAnimatedValue()).floatValue());
        invalidate();
    }

    private CharSequence getFullText() {
        return this.f20462b == null ? getText() : getMaskChars();
    }

    private StringBuilder getMaskChars() {
        if (this.f20463c == null) {
            this.f20463c = new StringBuilder();
        }
        int length = getText().length();
        while (this.f20463c.length() != length) {
            if (this.f20463c.length() < length) {
                this.f20463c.append(this.f20462b);
            } else {
                StringBuilder sb = this.f20463c;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return this.f20463c;
    }

    /* renamed from: a */
    public final void m6054a() {
        ValueAnimator duration = ValueAnimator.ofFloat(1.0f, getTextSize()).setDuration(200L);
        duration.setInterpolator(new OvershootInterpolator());
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.unicom.pay.widget.-$$Lambda$WPPassEditText$LXFotVT1Md7DKRa66C8NtvlvTvc
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                WPPassEditText.this.m6053a(valueAnimator);
            }
        });
        duration.addListener(new C10712a());
        duration.start();
    }

    /* renamed from: b */
    public final void m6052b() {
        setLongClickable(false);
        this.f20467g = getResources().getDrawable(f20460n);
        this.f20464d = 6;
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.f20464d)});
        this.f20461a = new Paint(getPaint());
        this.f20470j = new Paint(getPaint());
        this.f20461a.setTextAlign(Paint.Align.CENTER);
        this.f20470j.setTextAlign(Paint.Align.CENTER);
        Paint paint = new Paint(getPaint());
        this.f20473m = paint;
        paint.setAntiAlias(true);
        this.f20473m.setStrokeWidth(1.0f);
        this.f20473m.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f20473m.setColor(getResources().getColor(C10531R.C10532color.wp_pass_progress_background_rec_color));
        new RectF();
        this.f20472l = new Rect();
        setCursorVisible(false);
    }

    /* renamed from: c */
    public final void m6051c() {
        LTPassGuardEdit.setNO_OFF(true);
        setButtonPressAnim(false);
        setEncrypt(true);
        setMaxLength(6);
        useNumberPad(true);
        setButtonPress(true);
        super.initPassGuardKeyBoard();
    }

    @Override // cn.ltzf.passguard.LTPassGuardEdit
    public final void initPassGuardKeyBoard() {
        LTPassGuardEdit.setNO_OFF(true);
        setButtonPressAnim(false);
        setEncrypt(true);
        setMaxLength(6);
        useNumberPad(true);
        setButtonPress(true);
        super.initPassGuardKeyBoard();
        StartPassGuardKeyBoard();
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        int i;
        Canvas canvas2;
        CharSequence charSequence;
        int i2;
        int i3;
        float f;
        Paint paint;
        CharSequence fullText = getFullText();
        int length = fullText.length();
        getPaint().getTextBounds(fullText.toString(), 0, length, this.f20472l);
        this.f20467g.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() + getPaddingTop());
        this.f20467g.draw(canvas);
        int i4 = 0;
        while (true) {
            int i5 = this.f20464d;
            if (i4 >= i5) {
                return;
            }
            if (i4 != i5 - 1) {
                RectF[] rectFArr = this.f20466f;
                canvas.drawLine(rectFArr[i4].right, rectFArr[i4].top, rectFArr[i4].right, rectFArr[i4].bottom, this.f20473m);
            }
            RectF[] rectFArr2 = this.f20466f;
            float f2 = (this.f20471k / 2.0f) + rectFArr2[i4].left;
            if (length > i4) {
                if (this.f20468h && i4 == length - 1) {
                    float height = (rectFArr2[i4].height() / 2.0f) + (this.f20472l.height() / 2);
                    canvas2 = canvas;
                    charSequence = fullText;
                    i2 = i4;
                    i3 = i4 + 1;
                    i = i4;
                    f = height;
                    paint = this.f20470j;
                } else {
                    i = i4;
                    float height2 = (rectFArr2[i].height() / 2.0f) + (this.f20472l.height() / 2);
                    canvas2 = canvas;
                    charSequence = fullText;
                    i2 = i;
                    i3 = i + 1;
                    f = height2;
                    paint = this.f20461a;
                }
                canvas2.drawText(charSequence, i2, i3, f2, f, paint);
            } else {
                i = i4;
            }
            i4 = i + 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003b  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onSizeChanged(int r7, int r8, int r9, int r10) {
        /*
            r6 = this;
            super.onSizeChanged(r7, r8, r9, r10)
            int r7 = r6.getWidth()
            int r8 = r6.getPaddingLeft()
            int r7 = r7 - r8
            int r8 = r6.getPaddingRight()
            int r7 = r7 - r8
            float r8 = r6.f20465e
            r9 = 0
            int r10 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r10 != 0) goto L1e
            int r8 = r6.f20464d
            int r7 = r7 / r8
        L1b:
            r6.f20471k = r7
            goto L2c
        L1e:
            if (r10 <= 0) goto L2c
            float r7 = (float) r7
            int r10 = r6.f20464d
            int r0 = r10 + (-1)
            float r0 = (float) r0
            float r8 = r8 * r0
            float r7 = r7 - r8
            float r8 = (float) r10
            float r7 = r7 / r8
            int r7 = (int) r7
            goto L1b
        L2c:
            int r7 = r6.getPaddingLeft()
            int r8 = r6.f20464d
            android.graphics.RectF[] r8 = new android.graphics.RectF[r8]
            r6.f20466f = r8
            r8 = 0
        L37:
            int r10 = r6.f20464d
            if (r8 >= r10) goto L7e
            android.graphics.RectF[] r10 = r6.f20466f
            android.graphics.RectF r0 = new android.graphics.RectF
            float r1 = (float) r7
            int r2 = r6.getPaddingTop()
            float r2 = (float) r2
            int r3 = r6.f20471k
            int r3 = r3 + r7
            float r3 = (float) r3
            int r4 = r6.getHeight()
            int r5 = r6.getPaddingBottom()
            int r4 = r4 - r5
            float r4 = (float) r4
            r0.<init>(r1, r2, r3, r4)
            r10[r8] = r0
            android.graphics.drawable.Drawable r10 = r6.f20467g
            if (r10 == 0) goto L69
            android.graphics.RectF[] r10 = r6.f20466f
            r0 = r10[r8]
            r10 = r10[r8]
            float r10 = r10.width()
            float r10 = r10 + r1
            r0.right = r10
        L69:
            float r10 = r6.f20465e
            int r0 = (r10 > r9 ? 1 : (r10 == r9 ? 0 : -1))
            if (r0 != 0) goto L73
            int r10 = r6.f20471k
            int r7 = r7 + r10
            goto L7b
        L73:
            if (r0 <= 0) goto L7b
            int r7 = r6.f20471k
            float r7 = (float) r7
            float r7 = r7 + r10
            float r7 = r7 + r1
            int r7 = (int) r7
        L7b:
            int r8 = r8 + 1
            goto L37
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.widget.WPPassEditText.onSizeChanged(int, int, int, int):void");
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.f20468h) {
            if (i3 > i2) {
                m6054a();
            }
        } else if (this.f20469i == null || charSequence.length() != this.f20464d) {
        } else {
            this.f20469i.mo57a(charSequence.toString());
        }
    }

    public void setAnimate(boolean z) {
        this.f20468h = z;
    }

    public void setNumCount(int i) {
        this.f20464d = i;
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
    }

    public void setOnTextChangedListener(InterfaceC10713b interfaceC10713b) {
        this.f20469i = interfaceC10713b;
    }

    public void setSpaceWidth(float f) {
        this.f20465e = f;
    }
}
