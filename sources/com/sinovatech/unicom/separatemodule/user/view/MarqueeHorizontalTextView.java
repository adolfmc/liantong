package com.sinovatech.unicom.separatemodule.user.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.p086v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MarqueeHorizontalTextView extends AppCompatTextView {
    private static final int scrollTile = 1;
    private static final long waitTime = 2000;
    private int baseline;
    private float drawTextX;
    public boolean isStarting;
    private Paint paint;
    private String text;
    private float textLength;

    public MarqueeHorizontalTextView(Context context) {
        super(context);
        this.textLength = 0.0f;
        this.drawTextX = 0.0f;
        this.isStarting = false;
        this.paint = null;
        this.text = "";
        initView(context);
    }

    public MarqueeHorizontalTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textLength = 0.0f;
        this.drawTextX = 0.0f;
        this.isStarting = false;
        this.paint = null;
        this.text = "";
        initView(context);
    }

    public MarqueeHorizontalTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textLength = 0.0f;
        this.drawTextX = 0.0f;
        this.isStarting = false;
        this.paint = null;
        this.text = "";
        initView(context);
    }

    private void initView(Context context) {
        this.paint = getPaint();
        this.paint.setColor(getTextColors().getColorForState(getDrawableState(), 0));
        this.text = getText().toString();
        if (TextUtils.isEmpty(this.text)) {
            return;
        }
        this.textLength = this.paint.measureText(this.text);
        this.isStarting = true;
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
        this.paint.setColor(i);
        start();
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        this.text = charSequence.toString();
        this.textLength = getPaint().measureText(charSequence.toString());
        this.drawTextX = 0.0f;
        start();
    }

    public void start() {
        this.isStarting = true;
        invalidate();
    }

    public void stop() {
        this.isStarting = false;
        invalidate();
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        Paint.FontMetricsInt fontMetricsInt = this.paint.getFontMetricsInt();
        this.baseline = ((getHeight() - fontMetricsInt.bottom) - fontMetricsInt.top) / 2;
        if (this.textLength <= getWidth() || !this.isStarting) {
            canvas.drawText(this.text, 0.0f, this.baseline, this.paint);
            return;
        }
        canvas.drawText(this.text, -this.drawTextX, this.baseline, this.paint);
        float f = this.drawTextX;
        if (f == 0.0f) {
            postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.user.view.-$$Lambda$MarqueeHorizontalTextView$HlrKpKndjL1kCp_wPnYDmBq_nyM
                @Override // java.lang.Runnable
                public final void run() {
                    MarqueeHorizontalTextView.lambda$onDraw$0(MarqueeHorizontalTextView.this);
                }
            }, 2000L);
            this.isStarting = false;
            return;
        }
        this.drawTextX = f + 1.0f;
        if (this.drawTextX > (this.textLength - getWidth()) + 20.0f) {
            postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.user.view.-$$Lambda$MarqueeHorizontalTextView$9_plHe36jcKlvV6Ux-k-4y45-vs
                @Override // java.lang.Runnable
                public final void run() {
                    MarqueeHorizontalTextView.lambda$onDraw$1(MarqueeHorizontalTextView.this);
                }
            }, 2000L);
            this.isStarting = false;
            return;
        }
        invalidate();
    }

    public static /* synthetic */ void lambda$onDraw$0(MarqueeHorizontalTextView marqueeHorizontalTextView) {
        marqueeHorizontalTextView.drawTextX = 1.0f;
        marqueeHorizontalTextView.isStarting = true;
        marqueeHorizontalTextView.invalidate();
    }

    public static /* synthetic */ void lambda$onDraw$1(MarqueeHorizontalTextView marqueeHorizontalTextView) {
        marqueeHorizontalTextView.drawTextX = 0.0f;
        marqueeHorizontalTextView.isStarting = true;
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.7f);
        alphaAnimation.setDuration(100L);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setRepeatCount(1);
        marqueeHorizontalTextView.startAnimation(alphaAnimation);
        marqueeHorizontalTextView.invalidate();
    }
}
