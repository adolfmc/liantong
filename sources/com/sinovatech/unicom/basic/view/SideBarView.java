package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SideBarView extends View {

    /* renamed from: b */
    public static String[] f18442b = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "W", "X", "Y", "Z", "#"};
    private int choose;
    private TextView mTextDialog;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private Paint paint;
    private int textSize;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String str);
    }

    public void setTextView(TextView textView) {
        this.mTextDialog = textView;
    }

    public SideBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.choose = -1;
        this.paint = new Paint();
        this.textSize = UIUtils.dip2px((Activity) context, 11.0f);
    }

    public SideBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.choose = -1;
        this.paint = new Paint();
        this.textSize = UIUtils.dip2px((Activity) context, 11.0f);
    }

    public SideBarView(Context context) {
        super(context);
        this.choose = -1;
        this.paint = new Paint();
        this.textSize = UIUtils.dip2px((Activity) context, 11.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int length = height / f18442b.length;
        for (int i = 0; i < f18442b.length; i++) {
            this.paint.setColor(-10066330);
            this.paint.setAntiAlias(true);
            this.paint.setTextSize(this.textSize);
            if (i == this.choose) {
                this.paint.setColor(Color.parseColor("#6666ff"));
                this.paint.setFakeBoldText(true);
            }
            canvas.drawText(f18442b[i], (width / 2) - (this.paint.measureText(f18442b[i]) / 2.0f), (length * i) + length, this.paint);
            this.paint.reset();
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.choose;
        OnTouchingLetterChangedListener onTouchingLetterChangedListener = this.onTouchingLetterChangedListener;
        float height = y / getHeight();
        String[] strArr = f18442b;
        int length = (int) (height * strArr.length);
        if (action == 1) {
            this.choose = -1;
            invalidate();
            TextView textView = this.mTextDialog;
            if (textView != null) {
                textView.setVisibility(4);
            }
        } else if (i != length && length >= 0 && length < strArr.length) {
            if (onTouchingLetterChangedListener != null) {
                onTouchingLetterChangedListener.onTouchingLetterChanged(strArr[length]);
            }
            TextView textView2 = this.mTextDialog;
            if (textView2 != null) {
                textView2.setText(f18442b[length]);
                this.mTextDialog.setVisibility(0);
            }
            this.choose = length;
            invalidate();
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
}
