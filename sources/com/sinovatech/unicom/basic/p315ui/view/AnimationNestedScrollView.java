package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.widget.NestedScrollView;
import android.util.AttributeSet;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.view.AnimationNestedScrollView */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AnimationNestedScrollView extends NestedScrollView {
    private OnAnimationScrollChangeListener listener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.AnimationNestedScrollView$OnAnimationScrollChangeListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnAnimationScrollChangeListener {
        void onScrollChanged(float f);
    }

    public AnimationNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public AnimationNestedScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimationNestedScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnAnimationScrollListener(OnAnimationScrollChangeListener onAnimationScrollChangeListener) {
        this.listener = onAnimationScrollChangeListener;
    }

    @Override // android.support.p083v4.widget.NestedScrollView, android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        OnAnimationScrollChangeListener onAnimationScrollChangeListener = this.listener;
        if (onAnimationScrollChangeListener != null) {
            onAnimationScrollChangeListener.onScrollChanged(getScrollY());
        }
    }
}
