package com.sinovatech.unicom.basic.p315ui.home.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.HomeYwRecycleview */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeYwRecycleview extends RecyclerView {
    private float ComparedX;

    /* renamed from: X1 */
    private float f18422X1;

    /* renamed from: X2 */
    private float f18423X2;

    public HomeYwRecycleview(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.support.p086v7.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.support.p086v7.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f18422X1 = motionEvent.getRawX();
        } else if (action == 2) {
            this.f18423X2 = motionEvent.getRawX();
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
            if (linearLayoutManager != null) {
                int findFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                MsLogUtil.m7980d("yiwang = " + Action(this.f18422X1, this.f18423X2) + findFirstCompletelyVisibleItemPosition);
                if (findFirstCompletelyVisibleItemPosition == 0 && Action(this.f18422X1, this.f18423X2)) {
                    return true;
                }
            }
        }
        invalidate();
        return super.onTouchEvent(motionEvent);
    }

    public boolean Action(float f, float f2) {
        MsLogUtil.m7980d("yiwang = x1,x2 = " + f + "/" + f2);
        this.ComparedX = f2 - f;
        return this.ComparedX > 0.0f;
    }
}
