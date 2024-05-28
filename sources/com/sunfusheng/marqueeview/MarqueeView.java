package com.sunfusheng.marqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MarqueeView extends ViewFlipper {
    private static final int DIRECTION_BOTTOM_TO_TOP = 0;
    private static final int DIRECTION_LEFT_TO_RIGHT = 3;
    private static final int DIRECTION_RIGHT_TO_LEFT = 2;
    private static final int DIRECTION_TOP_TO_BOTTOM = 1;
    private static final int GRAVITY_CENTER = 1;
    private static final int GRAVITY_LEFT = 0;
    private static final int GRAVITY_RIGHT = 2;
    private int animDuration;
    private int direction;
    private int gravity;
    private boolean hasSetAnimDuration;
    private boolean hasSetDirection;
    @AnimRes
    private int inAnimResId;
    private int interval;
    private List<? extends CharSequence> notices;
    private OnItemClickListener onItemClickListener;
    @AnimRes
    private int outAnimResId;
    private int position;
    private boolean singleLine;
    private int textColor;
    private int textSize;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface OnItemClickListener {
        void onItemClick(int i, TextView textView);
    }

    static /* synthetic */ int access$208(MarqueeView marqueeView) {
        int i = marqueeView.position;
        marqueeView.position = i + 1;
        return i;
    }

    public MarqueeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.interval = 3000;
        this.hasSetAnimDuration = false;
        this.animDuration = 1000;
        this.textSize = 14;
        this.textColor = -1;
        this.singleLine = false;
        this.gravity = 19;
        this.hasSetDirection = false;
        this.direction = 0;
        this.inAnimResId = C10298R.anim.anim_bottom_in;
        this.outAnimResId = C10298R.anim.anim_top_out;
        this.notices = new ArrayList();
        init(context, attributeSet, 0);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C10298R.styleable.MarqueeViewStyle, i, 0);
        this.interval = obtainStyledAttributes.getInteger(C10298R.styleable.MarqueeViewStyle_mvInterval, this.interval);
        this.hasSetAnimDuration = obtainStyledAttributes.hasValue(C10298R.styleable.MarqueeViewStyle_mvAnimDuration);
        this.animDuration = obtainStyledAttributes.getInteger(C10298R.styleable.MarqueeViewStyle_mvAnimDuration, this.animDuration);
        this.singleLine = obtainStyledAttributes.getBoolean(C10298R.styleable.MarqueeViewStyle_mvSingleLine, false);
        if (obtainStyledAttributes.hasValue(C10298R.styleable.MarqueeViewStyle_mvTextSize)) {
            this.textSize = (int) obtainStyledAttributes.getDimension(C10298R.styleable.MarqueeViewStyle_mvTextSize, this.textSize);
            this.textSize = Utils.px2sp(context, this.textSize);
        }
        this.textColor = obtainStyledAttributes.getColor(C10298R.styleable.MarqueeViewStyle_mvTextColor, this.textColor);
        switch (obtainStyledAttributes.getInt(C10298R.styleable.MarqueeViewStyle_mvGravity, 0)) {
            case 0:
                this.gravity = 19;
                break;
            case 1:
                this.gravity = 17;
                break;
            case 2:
                this.gravity = 21;
                break;
        }
        this.hasSetDirection = obtainStyledAttributes.hasValue(C10298R.styleable.MarqueeViewStyle_mvDirection);
        this.direction = obtainStyledAttributes.getInt(C10298R.styleable.MarqueeViewStyle_mvDirection, this.direction);
        if (this.hasSetDirection) {
            switch (this.direction) {
                case 0:
                    this.inAnimResId = C10298R.anim.anim_bottom_in;
                    this.outAnimResId = C10298R.anim.anim_top_out;
                    break;
                case 1:
                    this.inAnimResId = C10298R.anim.anim_top_in;
                    this.outAnimResId = C10298R.anim.anim_bottom_out;
                    break;
                case 2:
                    this.inAnimResId = C10298R.anim.anim_right_in;
                    this.outAnimResId = C10298R.anim.anim_left_out;
                    break;
                case 3:
                    this.inAnimResId = C10298R.anim.anim_left_in;
                    this.outAnimResId = C10298R.anim.anim_right_out;
                    break;
            }
        } else {
            this.inAnimResId = C10298R.anim.anim_bottom_in;
            this.outAnimResId = C10298R.anim.anim_top_out;
        }
        obtainStyledAttributes.recycle();
        setFlipInterval(this.interval);
    }

    public void startWithText(String str) {
        startWithText(str, this.inAnimResId, this.outAnimResId);
    }

    public void startWithText(final String str, @AnimRes final int i, @AnimRes final int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.sunfusheng.marqueeview.MarqueeView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= 16) {
                    MarqueeView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    MarqueeView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                MarqueeView.this.startWithFixedWidth(str, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWithFixedWidth(String str, @AnimRes int i, @AnimRes int i2) {
        int length = str.length();
        int px2dip = Utils.px2dip(getContext(), getWidth());
        if (px2dip == 0) {
            throw new RuntimeException("Please set the width of MarqueeView !");
        }
        int i3 = px2dip / this.textSize;
        ArrayList arrayList = new ArrayList();
        if (length <= i3) {
            arrayList.add(str);
        } else {
            int i4 = 0;
            int i5 = (length / i3) + (length % i3 != 0 ? 1 : 0);
            while (i4 < i5) {
                int i6 = i4 * i3;
                i4++;
                int i7 = i4 * i3;
                if (i7 >= length) {
                    i7 = length;
                }
                arrayList.add(str.substring(i6, i7));
            }
        }
        if (this.notices == null) {
            this.notices = new ArrayList();
        }
        this.notices.clear();
        this.notices.addAll(arrayList);
        postStart(i, i2);
    }

    public void startWithList(List<? extends CharSequence> list) {
        startWithList(list, this.inAnimResId, this.outAnimResId);
    }

    public void startWithList(List<? extends CharSequence> list, @AnimRes int i, @AnimRes int i2) {
        if (Utils.isEmpty(list)) {
            return;
        }
        setNotices(list);
        postStart(i, i2);
    }

    private void postStart(@AnimRes final int i, @AnimRes final int i2) {
        post(new Runnable() { // from class: com.sunfusheng.marqueeview.MarqueeView.2
            @Override // java.lang.Runnable
            public void run() {
                MarqueeView.this.start(i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void start(@AnimRes int i, @AnimRes int i2) {
        removeAllViews();
        clearAnimation();
        this.position = 0;
        addView(createTextView(this.notices.get(this.position)));
        if (this.notices.size() > 1) {
            setInAndOutAnimation(i, i2);
            startFlipping();
        }
        if (getInAnimation() != null) {
            getInAnimation().setAnimationListener(new Animation.AnimationListener() { // from class: com.sunfusheng.marqueeview.MarqueeView.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    MarqueeView.access$208(MarqueeView.this);
                    if (MarqueeView.this.position >= MarqueeView.this.notices.size()) {
                        MarqueeView.this.position = 0;
                    }
                    MarqueeView marqueeView = MarqueeView.this;
                    TextView createTextView = marqueeView.createTextView((CharSequence) marqueeView.notices.get(MarqueeView.this.position));
                    if (createTextView.getParent() == null) {
                        MarqueeView.this.addView(createTextView);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TextView createTextView(CharSequence charSequence) {
        TextView textView = (TextView) getChildAt((getDisplayedChild() + 1) % 3);
        if (textView == null) {
            textView = new TextView(getContext());
            textView.setGravity(this.gravity);
            textView.setTextColor(this.textColor);
            textView.setTextSize(this.textSize);
            textView.setSingleLine(this.singleLine);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.sunfusheng.marqueeview.MarqueeView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (MarqueeView.this.onItemClickListener != null) {
                    MarqueeView.this.onItemClickListener.onItemClick(MarqueeView.this.getPosition(), (TextView) view);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        textView.setText(charSequence);
        textView.setTag(Integer.valueOf(this.position));
        return textView;
    }

    public int getPosition() {
        return ((Integer) getCurrentView().getTag()).intValue();
    }

    public List<? extends CharSequence> getNotices() {
        return this.notices;
    }

    public void setNotices(List<? extends CharSequence> list) {
        this.notices = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void setInAndOutAnimation(@AnimRes int i, @AnimRes int i2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), i);
        if (this.hasSetAnimDuration) {
            loadAnimation.setDuration(this.animDuration);
        }
        setInAnimation(loadAnimation);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(getContext(), i2);
        if (this.hasSetAnimDuration) {
            loadAnimation2.setDuration(this.animDuration);
        }
        setOutAnimation(loadAnimation2);
    }
}
