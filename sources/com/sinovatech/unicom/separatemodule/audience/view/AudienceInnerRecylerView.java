package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceInnerRecylerView extends RecyclerView {
    public static boolean ISLANDSCAPE;
    public static boolean isInnerRecylerView;
    private int firstVisibleItemPosition;
    private boolean isBottomToTop;
    private boolean isNeedScroll;
    private boolean isTopToBottom;
    private int lastVisibleItemPosition;
    private float mLastY;

    public AudienceInnerRecylerView(@NonNull Context context) {
        super(context);
        this.mLastY = 0.0f;
        this.isTopToBottom = false;
        this.isBottomToTop = false;
        this.isNeedScroll = false;
        isInnerRecylerView = false;
    }

    public AudienceInnerRecylerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastY = 0.0f;
        this.isTopToBottom = false;
        this.isBottomToTop = false;
        this.isNeedScroll = false;
        isInnerRecylerView = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.mLastY = motionEvent.getY();
                isInnerRecylerView = true;
                break;
            case 1:
            case 3:
                isInnerRecylerView = false;
                break;
            case 2:
                float y = motionEvent.getY();
                isIntercept(y);
                if (!this.isNeedScroll) {
                    isInnerRecylerView = false;
                    return false;
                }
                isInnerRecylerView = true;
                this.mLastY = y;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void isIntercept(float f) {
        this.isBottomToTop = true;
        if (canScrollVertically(-1) || canScrollVertically(1)) {
            this.isNeedScroll = true;
        } else {
            this.isNeedScroll = false;
        }
    }
}
