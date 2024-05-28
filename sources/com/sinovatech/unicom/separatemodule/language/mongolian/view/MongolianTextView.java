package com.sinovatech.unicom.separatemodule.language.mongolian.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.p086v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MongolianTextView extends AppCompatTextView {
    private TextPaint textPaint;

    public MongolianTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public MongolianTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MongolianTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/GLTQaganTig.ttf"));
    }

    @Override // android.support.p086v7.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.textPaint = getPaint();
        this.textPaint.setColor(getCurrentTextColor());
        this.textPaint.drawableState = getDrawableState();
        float textSize = getTextSize();
        String charSequence = getText().toString();
        StaticLayout staticLayout = new StaticLayout(charSequence, this.textPaint, canvas.getHeight(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        int lineCount = staticLayout.getLineCount();
        int i = 0;
        while (i < lineCount) {
            int i2 = i + 1;
            drawText(canvas, charSequence.substring(staticLayout.getLineStart(i), staticLayout.getLineStart(i2)), textSize * i, 0.0f, this.textPaint, 90.0f);
            i = i2;
            charSequence = charSequence;
            staticLayout = staticLayout;
        }
    }

    void drawText(Canvas canvas, String str, float f, float f2, Paint paint, float f3) {
        int i = (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1));
        if (i != 0) {
            canvas.rotate(f3, f, f2);
        }
        canvas.drawText(str, f, f2, paint);
        if (i != 0) {
            canvas.rotate(-f3, f, f2);
        }
    }
}
