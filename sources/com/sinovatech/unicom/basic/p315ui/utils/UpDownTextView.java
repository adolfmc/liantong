package com.sinovatech.unicom.basic.p315ui.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.utils.UpDownTextView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UpDownTextView extends LinearLayout {
    public static final int ANIM_MODE_DOWN = 1;
    public static final int ANIM_MODE_UP = 0;
    private int animMode;
    private TranslateAnimation animationDown;
    private TranslateAnimation animationUp;
    private String curText;
    private int currentIndex;
    private boolean isRunning;
    private Animation.AnimationListener listener;
    private LinearLayout llayout;
    private int mAnimTime;
    private Context mContext;
    private int mStillTime;
    private List<String> mTextList;
    OnTextScrollListener onTextScrollListener;
    private Runnable runnable;
    private TextView[] textViews;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.utils.UpDownTextView$OnTextScrollListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnTextScrollListener {
        void onTextScroll();
    }

    static /* synthetic */ int access$108(UpDownTextView upDownTextView) {
        int i = upDownTextView.currentIndex;
        upDownTextView.currentIndex = i + 1;
        return i;
    }

    public UpDownTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textViews = new TextView[3];
        this.curText = null;
        this.mAnimTime = 500;
        this.mStillTime = 5000;
        this.currentIndex = 0;
        this.animMode = 0;
        this.isRunning = false;
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.basic.ui.utils.UpDownTextView.2
            @Override // java.lang.Runnable
            public void run() {
                UpDownTextView.access$108(UpDownTextView.this);
                UpDownTextView.this.currentIndex %= UpDownTextView.this.mTextList.size();
                switch (UpDownTextView.this.animMode) {
                    case 0:
                        UpDownTextView upDownTextView = UpDownTextView.this;
                        upDownTextView.setTextUpAnim((String) upDownTextView.mTextList.get(UpDownTextView.this.currentIndex));
                        break;
                    case 1:
                        UpDownTextView upDownTextView2 = UpDownTextView.this;
                        upDownTextView2.setTextDownAnim((String) upDownTextView2.mTextList.get(UpDownTextView.this.currentIndex));
                        break;
                }
                UpDownTextView upDownTextView3 = UpDownTextView.this;
                upDownTextView3.postDelayed(upDownTextView3.runnable, UpDownTextView.this.mStillTime + UpDownTextView.this.mAnimTime);
                if (UpDownTextView.this.onTextScrollListener != null) {
                    UpDownTextView.this.onTextScrollListener.onTextScroll();
                }
            }
        };
        this.listener = new AnimationListenerImpl() { // from class: com.sinovatech.unicom.basic.ui.utils.UpDownTextView.3
            @Override // com.sinovatech.unicom.basic.p315ui.utils.AnimationListenerImpl, android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                UpDownTextView upDownTextView = UpDownTextView.this;
                upDownTextView.setText(upDownTextView.curText);
            }
        };
        this.mContext = context;
        if (isInEditMode()) {
            return;
        }
        initViews();
    }

    private void initViews() {
        this.llayout = new LinearLayout(this.mContext);
        this.llayout.setOrientation(1);
        addView(this.llayout);
        this.textViews[0] = addText();
        this.textViews[1] = addText();
        this.textViews[2] = addText();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoScroll();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (isInEditMode()) {
            return;
        }
        post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.utils.UpDownTextView.1
            @Override // java.lang.Runnable
            public void run() {
                UpDownTextView.this.setViewsHeight();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewsHeight() {
        TextView[] textViewArr;
        for (TextView textView : this.textViews) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.height = getHeight();
            layoutParams.width = getWidth();
            textView.setLayoutParams(layoutParams);
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.llayout.getLayoutParams();
        layoutParams2.height = getHeight() * this.llayout.getChildCount();
        layoutParams2.setMargins(0, -getHeight(), 0, 0);
        this.llayout.setLayoutParams(layoutParams2);
    }

    @Override // android.widget.LinearLayout
    public void setGravity(int i) {
        for (TextView textView : this.textViews) {
            textView.setGravity(i);
        }
    }

    public void setTextSize(int i) {
        for (TextView textView : this.textViews) {
            textView.setTextSize(1, i);
        }
    }

    public void setTextColor(int i) {
        for (TextView textView : this.textViews) {
            textView.setTextColor(i);
        }
    }

    private TextView addText() {
        TextView textView = new TextView(this.mContext);
        textView.setGravity(16);
        this.llayout.addView(textView);
        return textView;
    }

    public void setText(String str) {
        this.curText = str;
        this.textViews[1].setText(str);
    }

    public void startAutoScroll() {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        List<String> list = this.mTextList;
        if (list == null || list.size() == 0) {
            stopAutoScroll();
        } else {
            postDelayed(this.runnable, this.mStillTime + this.mAnimTime);
        }
    }

    public void stopAutoScroll() {
        if (this.isRunning) {
            this.isRunning = false;
            removeCallbacks(this.runnable);
        }
    }

    public void setTextUpAnim(String str) {
        this.curText = str;
        this.textViews[2].setText(str);
        m8002up();
    }

    public void setTextDownAnim(String str) {
        this.curText = str;
        this.textViews[0].setText(str);
        down();
    }

    public void setDuring(int i) {
        this.mAnimTime = i;
    }

    /* renamed from: up */
    private void m8002up() {
        this.llayout.clearAnimation();
        if (this.animationUp == null) {
            this.animationUp = new TranslateAnimation(0.0f, 0.0f, 0.0f, -getHeight());
        }
        this.animationUp.setDuration(this.mAnimTime);
        this.llayout.startAnimation(this.animationUp);
        this.animationUp.setAnimationListener(this.listener);
    }

    public void down() {
        this.llayout.clearAnimation();
        if (this.animationDown == null) {
            this.animationDown = new TranslateAnimation(0.0f, 0.0f, 0.0f, getHeight());
        }
        this.animationDown.setDuration(this.mAnimTime);
        this.llayout.startAnimation(this.animationDown);
        this.animationDown.setAnimationListener(this.listener);
    }

    public int getAnimTime() {
        return this.mAnimTime;
    }

    public void setAnimTime(int i) {
        this.mAnimTime = i;
    }

    public int getStillTime() {
        return this.mStillTime;
    }

    public void setStillTime(int i) {
        this.mStillTime = i;
    }

    public List<String> getTextList() {
        return this.mTextList;
    }

    public void setTextList(List<String> list) {
        this.mTextList = list;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public int getAnimMode() {
        return this.animMode;
    }

    public void setAnimMode(int i) {
        this.animMode = i;
    }

    public void setSingleLine() {
        TextView[] textViewArr;
        for (TextView textView : this.textViews) {
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    public void setOnTextScrollListener(OnTextScrollListener onTextScrollListener) {
        this.onTextScrollListener = onTextScrollListener;
    }
}
