package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.sinovatech.unicom.basic.ui.view.HomeWebViewPager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeWebViewPager extends ViewPager {
    private static final String TAG = "HomeWebViewPager";
    private int current;
    private Map<Integer, Integer> heightMap;
    private HomeWebViewPager instance;
    private Map<Integer, View> mChildrenViews;

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean canScrollHorizontally(int i) {
        return false;
    }

    public HomeWebViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChildrenViews = new LinkedHashMap();
        this.heightMap = new LinkedHashMap();
        this.instance = this;
        setOffscreenPageLimit(100);
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, new FixedSpeedScroller(context, new DecelerateInterpolator(), 300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public void onMeasure(int i, int i2) {
        if (this.current == getCurrentItem()) {
            int size = this.mChildrenViews.size();
            int i3 = this.current;
            if (size > i3) {
                View view = this.mChildrenViews.get(Integer.valueOf(i3));
                view.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = view.getMeasuredHeight();
                if (measuredHeight < UIUtils.dip2px(640.0f) && this.current != 0) {
                    measuredHeight = UIUtils.dip2px(640.0f);
                }
                if (this.current == 0 && measuredHeight < UIUtils.dip2px(400.0f) && UIUtils.getScreenHeight(getContext()) > 2300) {
                    measuredHeight = UIUtils.dip2px(400.0f);
                }
                this.heightMap.put(Integer.valueOf(this.current), Integer.valueOf(measuredHeight));
                i2 = View.MeasureSpec.makeMeasureSpec(this.heightMap.get(Integer.valueOf(this.current)).intValue(), 1073741824);
            }
        }
        super.onMeasure(i, i2);
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            getChildAt(i4).requestLayout();
        }
    }

    public void resetHeight(int i) {
        this.current = i;
        if (this.mChildrenViews.size() > i) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(-1, 0);
            } else {
                layoutParams.height = 0;
            }
            setLayoutParams(layoutParams);
        }
    }

    public void setObjectForPosition(View view, int i) {
        this.mChildrenViews.put(Integer.valueOf(i), view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            int action = motionEvent.getAction();
            if (action == 0) {
                MainGestureTabFragmeLayout.allowFlipTouch = false;
                String str = MainGestureTabFragmeLayout.TAG;
                MsLogUtil.m7979d(str, "HomeWebViewPager=> ACTION_DOWN = " + MainGestureTabFragmeLayout.allowFlipTouch);
            } else if (action == 3) {
                return false;
            }
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception e) {
            MainGestureTabFragmeLayout.allowFlipTouch = false;
            MsLogUtil.m7977e(TAG, "首页腰部tab==>dispatchTouchEvent异常:" + e.getMessage());
            return false;
        }
    }

    @Override // android.support.p083v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }
}
