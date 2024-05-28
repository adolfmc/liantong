package com.sinovatech.unicom.separatemodule.fuchuangs.view;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FloatWindowView extends LinearLayout {
    private static final String TAG = "CustomViews";
    public static boolean isMove;
    private float downXs;
    private float downYs;
    private long endTime;
    private boolean mClick;
    private int mSlops;
    private long startTime;
    private float upXs;
    private float upYs;
    private String url;

    public FloatWindowView(Context context) {
        super(context);
        this.mClick = false;
        this.startTime = 0L;
        this.endTime = 0L;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.startTime = System.currentTimeMillis();
                Log.d(TAG, "onInterceptTouchEvent: 按下");
                return true;
            case 1:
                Log.d(TAG, "onInterceptTouchEvent: 抬起");
                return System.currentTimeMillis() - this.startTime >= 100;
            case 2:
                return false;
            default:
                return true;
        }
    }
}
