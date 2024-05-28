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
import android.support.p086v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import com.unicom.pay.C10531R;
import java.util.Timer;
import p470p0.C13650m;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPVerifyCodeEditText extends AppCompatEditText {

    /* renamed from: l */
    public static final int f20498l = C10531R.C10533drawable.wp_shapre_verify_code_bg_selector;

    /* renamed from: a */
    public Paint f20499a;

    /* renamed from: b */
    public StringBuilder f20500b;

    /* renamed from: c */
    public int f20501c;

    /* renamed from: d */
    public float f20502d;

    /* renamed from: e */
    public RectF[] f20503e;

    /* renamed from: f */
    public Drawable f20504f;

    /* renamed from: g */
    public boolean f20505g;

    /* renamed from: h */
    public InterfaceC10719b f20506h;

    /* renamed from: i */
    public Paint f20507i;

    /* renamed from: j */
    public int f20508j;

    /* renamed from: k */
    public Rect f20509k;

    /* renamed from: com.unicom.pay.widget.WPVerifyCodeEditText$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10718a extends AnimatorListenerAdapter {
        public C10718a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            WPVerifyCodeEditText wPVerifyCodeEditText = WPVerifyCodeEditText.this;
            InterfaceC10719b interfaceC10719b = wPVerifyCodeEditText.f20506h;
            if (interfaceC10719b != null) {
                interfaceC10719b.mo70a(wPVerifyCodeEditText.getText().toString());
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.WPVerifyCodeEditText$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10719b {
        /* renamed from: a */
        void mo70a(String str);
    }

    public WPVerifyCodeEditText(Context context) {
        super(context);
        this.f20502d = 0.0f;
        this.f20505g = true;
        m6036c();
    }

    public WPVerifyCodeEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f20502d = 0.0f;
        this.f20505g = true;
        m6036c();
    }

    public WPVerifyCodeEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f20502d = 0.0f;
        this.f20505g = true;
        m6036c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m6038a(ValueAnimator valueAnimator) {
        this.f20507i.setTextSize(((Float) valueAnimator.getAnimatedValue()).floatValue());
        invalidate();
    }

    private CharSequence getFullText() {
        return getText();
    }

    private StringBuilder getMaskChars() {
        if (this.f20500b == null) {
            this.f20500b = new StringBuilder();
        }
        int length = getText().length();
        while (this.f20500b.length() != length) {
            if (this.f20500b.length() < length) {
                this.f20500b.append((String) null);
            } else {
                StringBuilder sb = this.f20500b;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return this.f20500b;
    }

    /* renamed from: a */
    public final void m6039a() {
        ValueAnimator duration = ValueAnimator.ofFloat(1.0f, getTextSize()).setDuration(200L);
        duration.setInterpolator(new OvershootInterpolator());
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.unicom.pay.widget.-$$Lambda$WPVerifyCodeEditText$uYwASexpIC0pgm09eQl0T0oClmY
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                WPVerifyCodeEditText.this.m6038a(valueAnimator);
            }
        });
        duration.addListener(new C10718a());
        duration.start();
    }

    /* renamed from: b */
    public final void m6037b() {
        requestFocus();
        new Timer().schedule(new C13650m(getContext()), 300L);
    }

    /* renamed from: c */
    public final void m6036c() {
        this.f20504f = getResources().getDrawable(f20498l);
        this.f20501c = 6;
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.f20501c)});
        this.f20499a = new Paint(getPaint());
        this.f20507i = new Paint(getPaint());
        this.f20499a.setTextAlign(Paint.Align.CENTER);
        this.f20507i.setTextAlign(Paint.Align.CENTER);
        this.f20509k = new Rect();
        setInputType(2);
        setCursorVisible(false);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        int i;
        float height;
        Paint paint;
        Drawable drawable;
        int[] iArr;
        CharSequence fullText = getFullText();
        int length = fullText.length();
        getPaint().getTextBounds(fullText.toString(), 0, length, this.f20509k);
        int i2 = 0;
        while (i2 < this.f20501c) {
            boolean z = i2 == length;
            if (isFocused()) {
                if (z) {
                    drawable = this.f20504f;
                    iArr = new int[]{16842908};
                } else {
                    drawable = this.f20504f;
                    iArr = new int[]{-16842908};
                }
                drawable.setState(iArr);
            }
            Drawable drawable2 = this.f20504f;
            RectF[] rectFArr = this.f20503e;
            drawable2.setBounds((int) rectFArr[i2].left, (int) rectFArr[i2].top, (int) rectFArr[i2].right, (int) rectFArr[i2].bottom);
            this.f20504f.draw(canvas);
            RectF[] rectFArr2 = this.f20503e;
            float f = (this.f20508j / 2.0f) + rectFArr2[i2].left;
            if (length > i2) {
                if (this.f20505g && i2 == length - 1) {
                    i = i2 + 1;
                    height = (this.f20509k.height() / 2.0f) + (rectFArr2[i2].height() / 2.0f);
                    paint = this.f20507i;
                } else {
                    i = i2 + 1;
                    height = (this.f20509k.height() / 2.0f) + (rectFArr2[i2].height() / 2.0f);
                    paint = this.f20499a;
                }
                canvas.drawText(fullText, i2, i, f, height, paint);
            }
            i2++;
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
            float r8 = r6.f20502d
            r9 = 0
            int r10 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r10 != 0) goto L1e
            int r8 = r6.f20501c
            int r7 = r7 / r8
        L1b:
            r6.f20508j = r7
            goto L2c
        L1e:
            if (r10 <= 0) goto L2c
            float r7 = (float) r7
            int r10 = r6.f20501c
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
            int r8 = r6.f20501c
            android.graphics.RectF[] r8 = new android.graphics.RectF[r8]
            r6.f20503e = r8
            r8 = 0
        L37:
            int r10 = r6.f20501c
            if (r8 >= r10) goto L7e
            android.graphics.RectF[] r10 = r6.f20503e
            android.graphics.RectF r0 = new android.graphics.RectF
            float r1 = (float) r7
            int r2 = r6.getPaddingTop()
            float r2 = (float) r2
            int r3 = r6.f20508j
            int r3 = r3 + r7
            float r3 = (float) r3
            int r4 = r6.getHeight()
            int r5 = r6.getPaddingBottom()
            int r4 = r4 - r5
            float r4 = (float) r4
            r0.<init>(r1, r2, r3, r4)
            r10[r8] = r0
            android.graphics.drawable.Drawable r10 = r6.f20504f
            if (r10 == 0) goto L69
            android.graphics.RectF[] r10 = r6.f20503e
            r0 = r10[r8]
            r10 = r10[r8]
            float r10 = r10.width()
            float r10 = r10 + r1
            r0.right = r10
        L69:
            float r10 = r6.f20502d
            int r0 = (r10 > r9 ? 1 : (r10 == r9 ? 0 : -1))
            if (r0 != 0) goto L73
            int r10 = r6.f20508j
            int r7 = r7 + r10
            goto L7b
        L73:
            if (r0 <= 0) goto L7b
            int r7 = r6.f20508j
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
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.widget.WPVerifyCodeEditText.onSizeChanged(int, int, int, int):void");
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.f20505g) {
            if (i3 > i2) {
                m6039a();
                return;
            }
            InterfaceC10719b interfaceC10719b = this.f20506h;
            if (interfaceC10719b != null) {
                interfaceC10719b.mo70a(charSequence.toString());
            }
        }
    }

    public void setAnimate(boolean z) {
        this.f20505g = z;
    }

    public void setNumCount(int i) {
        this.f20501c = i;
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
    }

    public void setOnTextChangedListener(InterfaceC10719b interfaceC10719b) {
        this.f20506h = interfaceC10719b;
    }

    public void setSpaceWidth(float f) {
        this.f20502d = f;
    }
}
