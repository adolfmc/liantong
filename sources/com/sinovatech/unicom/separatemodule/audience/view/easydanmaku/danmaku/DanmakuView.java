package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.p086v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.utils.ScreenUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DanmakuView extends AppCompatTextView {
    private Danmaku mDanmaku;
    private int mDuration;
    private ListenerInfo mListenerInfo;
    private Scroller mScroller;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnEnterListener {
        void onEnter(DanmakuView danmakuView);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnExitListener {
        void onExit(DanmakuView danmakuView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ListenerInfo {
        private ArrayList<OnEnterListener> mOnEnterListeners;
        private List<OnExitListener> mOnExitListener;

        private ListenerInfo() {
        }
    }

    public DanmakuView(Context context) {
        super(context);
    }

    public DanmakuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DanmakuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDanmaku(Danmaku danmaku) {
        this.mDanmaku = danmaku;
        setText(danmaku.spanText != null ? danmaku.spanText : danmaku.text);
        switch (danmaku.mode) {
            case top:
            case bottom:
                setGravity(17);
                return;
            default:
                setGravity(8388627);
                return;
        }
    }

    public Danmaku getDanmaku() {
        return this.mDanmaku;
    }

    public void show(final ViewGroup viewGroup, int i) {
        this.mDuration = i;
        switch (this.mDanmaku.mode) {
            case top:
            case bottom:
                showFixedDanmaku(viewGroup, i);
                break;
            default:
                showScrollAnimation(viewGroup, i);
                break;
        }
        if (hasOnEnterListener()) {
            Iterator it = getListenerInfo().mOnEnterListeners.iterator();
            while (it.hasNext()) {
                ((OnEnterListener) it.next()).onEnter(this);
            }
        }
        postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.-$$Lambda$DanmakuView$Z9h2RKSw-DGzmrs9jJsyaIc9xKQ
            @Override // java.lang.Runnable
            public final void run() {
                DanmakuView.lambda$show$0(DanmakuView.this, viewGroup);
            }
        }, i);
    }

    public static /* synthetic */ void lambda$show$0(DanmakuView danmakuView, ViewGroup viewGroup) {
        danmakuView.setVisibility(8);
        if (danmakuView.hasOnExitListener()) {
            for (OnExitListener onExitListener : danmakuView.getListenerInfo().mOnExitListener) {
                onExitListener.onExit(danmakuView);
            }
        }
        viewGroup.removeView(danmakuView);
    }

    private void showScrollDanmaku(ViewGroup viewGroup, int i) {
        int screenWidth = ScreenUtil.getScreenWidth();
        int textLength = getTextLength();
        scrollTo((-screenWidth) * 2, 0);
        viewGroup.addView(this);
        smoothScrollTo(textLength, 0, i);
    }

    private void showScrollAnimation(ViewGroup viewGroup, int i) {
        int max = Math.max(ScreenUtil.getScreenWidth(), ScreenUtil.getScreenHeight());
        int textLength = getTextLength();
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.leftMargin = max * 2;
        viewGroup.addView(this, layoutParams);
        ValueAnimator ofInt = ValueAnimator.ofInt(max, -textLength);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.-$$Lambda$DanmakuView$CNP9oTKVEwyqrCy89SnwY8KEBfw
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DanmakuView.lambda$showScrollAnimation$1(DanmakuView.this, layoutParams, valueAnimator);
            }
        });
        ofInt.setDuration(i);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.start();
    }

    public static /* synthetic */ void lambda$showScrollAnimation$1(DanmakuView danmakuView, FrameLayout.LayoutParams layoutParams, ValueAnimator valueAnimator) {
        layoutParams.leftMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        danmakuView.setLayoutParams(layoutParams);
    }

    private void showFixedDanmaku(ViewGroup viewGroup, int i) {
        setGravity(17);
        viewGroup.addView(this);
    }

    private ListenerInfo getListenerInfo() {
        if (this.mListenerInfo == null) {
            this.mListenerInfo = new ListenerInfo();
        }
        return this.mListenerInfo;
    }

    public void addOnEnterListener(OnEnterListener onEnterListener) {
        ListenerInfo listenerInfo = getListenerInfo();
        if (listenerInfo.mOnEnterListeners == null) {
            listenerInfo.mOnEnterListeners = new ArrayList();
        }
        if (listenerInfo.mOnEnterListeners.contains(onEnterListener)) {
            return;
        }
        listenerInfo.mOnEnterListeners.add(onEnterListener);
    }

    public void clearOnEnterListeners() {
        ListenerInfo listenerInfo = getListenerInfo();
        if (listenerInfo.mOnEnterListeners == null || listenerInfo.mOnEnterListeners.size() == 0) {
            return;
        }
        listenerInfo.mOnEnterListeners.clear();
    }

    public void addOnExitListener(OnExitListener onExitListener) {
        ListenerInfo listenerInfo = getListenerInfo();
        if (listenerInfo.mOnExitListener == null) {
            listenerInfo.mOnExitListener = new CopyOnWriteArrayList();
        }
        if (listenerInfo.mOnExitListener.contains(onExitListener)) {
            return;
        }
        listenerInfo.mOnExitListener.add(onExitListener);
    }

    public void clearOnExitListeners() {
        ListenerInfo listenerInfo = getListenerInfo();
        if (listenerInfo.mOnExitListener == null || listenerInfo.mOnExitListener.size() == 0) {
            return;
        }
        listenerInfo.mOnExitListener.clear();
    }

    public boolean hasOnEnterListener() {
        ListenerInfo listenerInfo = getListenerInfo();
        return (listenerInfo.mOnEnterListeners == null || listenerInfo.mOnEnterListeners.size() == 0) ? false : true;
    }

    public boolean hasOnExitListener() {
        ListenerInfo listenerInfo = getListenerInfo();
        return (listenerInfo.mOnExitListener == null || listenerInfo.mOnExitListener.size() == 0) ? false : true;
    }

    public int getTextLength() {
        return (int) getPaint().measureText(getText().toString());
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void restore() {
        clearOnEnterListeners();
        clearOnExitListeners();
        setVisibility(0);
        setScrollX(0);
        setScrollY(0);
    }

    public void smoothScrollTo(int i, int i2, int i3) {
        if (this.mScroller == null) {
            this.mScroller = new Scroller(getContext(), new LinearInterpolator());
            setScroller(this.mScroller);
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        this.mScroller.startScroll(scrollX, scrollY, i - scrollX, i2 - scrollY, i3);
    }

    @Override // android.widget.TextView, android.view.View
    public void computeScroll() {
        Scroller scroller = this.mScroller;
        if (scroller == null || !scroller.computeScrollOffset()) {
            return;
        }
        scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
        postInvalidate();
    }

    void callExitListener() {
        for (OnExitListener onExitListener : getListenerInfo().mOnExitListener) {
            onExitListener.onExit(this);
        }
    }
}
