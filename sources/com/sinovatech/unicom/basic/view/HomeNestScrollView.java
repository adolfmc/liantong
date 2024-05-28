package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.sinovatech.unicom.basic.p315ui.HomeScrollView;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeNestScrollView extends NestedScrollView {
    private OnScrollChangedListener listener;
    private List<HomeScrollView.OnScrollChangedListener> listenerList;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnScrollChangedListener {
        void onScroll(int i, int i2);

        void onTouch(boolean z);
    }

    public HomeNestScrollView(@NonNull Context context) {
        super(context);
    }

    public HomeNestScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addOnScrollChangedListener(HomeScrollView.OnScrollChangedListener onScrollChangedListener) {
        this.listenerList.add(onScrollChangedListener);
    }

    @Override // android.support.p083v4.widget.NestedScrollView, android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        OnScrollChangedListener onScrollChangedListener = this.listener;
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScroll(i2, i4);
        }
    }

    public void setOnScrollChanged(OnScrollChangedListener onScrollChangedListener) {
        this.listener = onScrollChangedListener;
    }

    @Override // android.support.p083v4.widget.NestedScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
            case 2:
                OnScrollChangedListener onScrollChangedListener = this.listener;
                if (onScrollChangedListener != null) {
                    onScrollChangedListener.onTouch(true);
                    break;
                }
                break;
            case 1:
            case 3:
                OnScrollChangedListener onScrollChangedListener2 = this.listener;
                if (onScrollChangedListener2 != null) {
                    onScrollChangedListener2.onTouch(false);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
