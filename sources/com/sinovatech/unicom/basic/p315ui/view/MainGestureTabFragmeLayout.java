package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.widget.FrameLayout;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.view.MainGestureTabFragmeLayout */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MainGestureTabFragmeLayout extends FrameLayout {
    public static String TAG = "MainGestureTabFragmeLayout";
    public static boolean allowFlipTouch = true;
    public static boolean isHomeTab;
    private float displayWidthPercentage;
    private boolean isChangeTab;
    private float latestX;
    private float latestY;
    private MainGestureTabListener listener;
    private VelocityTracker mVelocityTracker;
    private int minXDistance;
    private int minXVelocity;
    private int minYDistance;
    private float startX;
    private float startY;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.MainGestureTabFragmeLayout$MainGestureTabListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface MainGestureTabListener {
        void onFlipLeft();

        void onFlipRight();
    }

    public void restartTime() {
    }

    public MainGestureTabFragmeLayout(@NonNull Context context) {
        super(context);
        this.listener = null;
        this.minXDistance = 200;
        this.minYDistance = 200;
        this.minXVelocity = 800;
        this.displayWidthPercentage = 0.0f;
        this.isChangeTab = false;
    }

    public MainGestureTabFragmeLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.listener = null;
        this.minXDistance = 200;
        this.minYDistance = 200;
        this.minXVelocity = 800;
        this.displayWidthPercentage = 0.0f;
        this.isChangeTab = false;
    }

    public void setListener(MainGestureTabListener mainGestureTabListener) {
        this.listener = mainGestureTabListener;
    }

    public void setDisplayWidthPercentage(float f) {
        this.displayWidthPercentage = f;
    }

    public void setMinXDistance(int i) {
        this.minXDistance = i;
    }

    public void setMinYDistance(int i) {
        this.minYDistance = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003b A[Catch: Exception -> 0x016a, TryCatch #0 {Exception -> 0x016a, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000a, B:9:0x001f, B:11:0x0029, B:16:0x0037, B:18:0x003b, B:20:0x003f, B:21:0x0045, B:22:0x004e, B:24:0x0053, B:25:0x0061, B:27:0x0065, B:29:0x006d, B:30:0x008c, B:32:0x0090, B:34:0x00f2, B:36:0x00f9, B:38:0x0100, B:40:0x0107, B:43:0x0112, B:44:0x0116, B:46:0x0137, B:48:0x013c, B:47:0x013a, B:49:0x0165), top: B:54:0x0001 }] */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.view.MainGestureTabFragmeLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void restartVelocity() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            MsLogUtil.m7979d("cececece", TAG + "=> 重置 = " + allowFlipTouch);
        }
    }

    private boolean checkAndChangeTab(float f, float f2) {
        try {
            MsLogUtil.m7979d(TAG, "触发切换tab逻辑");
            if (f - f2 > this.minXDistance) {
                this.listener.onFlipRight();
            }
            if (f2 - f > this.minXDistance) {
                this.listener.onFlipLeft();
            }
            restartTime();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            MsLogUtil.m7977e(str, "MainGestureFrameLayout checkAndChangeTab报错:" + e.getMessage());
            return true;
        }
    }
}
