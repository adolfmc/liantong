package cn.ltzf.passguard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class ClearEditText extends LTPassGuardEdit implements TextWatcher {
    private boolean hasFoucs;
    private Drawable mClearDrawable;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        Drawable drawable = getCompoundDrawables()[2];
        this.mClearDrawable = drawable;
        if (drawable == null) {
            this.mClearDrawable = getResources().getDrawable(R.drawable.logo);
        }
        Drawable drawable2 = this.mClearDrawable;
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    public static Animation shakeAnimation(int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 10.0f, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new CycleInterpolator(i));
        translateAnimation.setDuration(1000L);
        return translateAnimation;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // cn.ltzf.passguard.LTPassGuardEdit, android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        this.hasFoucs = z;
        boolean z2 = false;
        if (z && getText().length() > 0) {
            z2 = true;
        }
        setClearIconVisible(z2);
    }

    @Override // android.widget.TextView, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.hasFoucs) {
            setClearIconVisible(charSequence.length() > 0);
        }
    }

    @Override // cn.ltzf.passguard.LTPassGuardEdit, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && getCompoundDrawables()[2] != null) {
            if (motionEvent.getX() > ((float) (getWidth() - getTotalPaddingRight())) && motionEvent.getX() < ((float) (getWidth() - getPaddingRight()))) {
                clear();
                try {
                    Toast.makeText(getContext(), "您触发了事件", 0).show();
                } catch (Exception unused) {
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setClearIconVisible(boolean z) {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], z ? this.mClearDrawable : null, getCompoundDrawables()[3]);
    }

    public void setShakeAnimation() {
        setAnimation(shakeAnimation(5));
    }
}
