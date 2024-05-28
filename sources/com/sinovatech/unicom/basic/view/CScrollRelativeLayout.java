package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CScrollRelativeLayout extends RelativeLayout {
    private boolean canDrag;
    private float initX;
    private float initY;
    private boolean justStartDrag;
    private float justStartY;
    private float lastX;
    private float lastY;
    private ScrollInfoListener scrollInfoListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ScrollInfoListener {
        float getScrollY();

        void scrollBy(float f, float f2);

        void scrollTo(float f, float f2);
    }

    public CScrollRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.canDrag = false;
        this.justStartDrag = true;
    }

    public void setScrollInfoListener(ScrollInfoListener scrollInfoListener) {
        this.scrollInfoListener = scrollInfoListener;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float scrollY = this.scrollInfoListener.getScrollY();
        switch (motionEvent.getAction()) {
            case 0:
                this.initX = motionEvent.getX();
                this.initY = motionEvent.getY();
                this.lastX = this.initX;
                this.lastY = this.initY;
                if (scrollY >= 0.0f) {
                    this.canDrag = false;
                    break;
                }
                break;
            case 1:
                this.canDrag = false;
                break;
            case 2:
                if (scrollY <= 1.0f && motionEvent.getY() - this.initY > 0.0f && motionEvent.getY() - this.initY > ViewConfiguration.get(getContext()).getScaledTouchSlop() && motionEvent.getY() - this.initY > motionEvent.getX() - this.initX) {
                    this.canDrag = true;
                }
                this.lastX = motionEvent.getX();
                this.lastY = motionEvent.getY();
                break;
            case 3:
                this.canDrag = false;
                break;
        }
        return this.canDrag;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
            default:
                return true;
            case 1:
                this.canDrag = false;
                this.scrollInfoListener.scrollTo(0.0f, 0.0f);
                return true;
            case 2:
                if (this.justStartDrag) {
                    this.justStartY = motionEvent.getY();
                    this.justStartDrag = false;
                }
                this.scrollInfoListener.scrollBy(0.0f, motionEvent.getY() - this.lastY);
                this.lastX = motionEvent.getX();
                this.lastY = motionEvent.getY();
                return true;
            case 3:
                this.canDrag = false;
                this.scrollInfoListener.scrollTo(0.0f, 0.0f);
                return true;
        }
    }
}
