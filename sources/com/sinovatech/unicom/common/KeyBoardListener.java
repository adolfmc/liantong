package com.sinovatech.unicom.common;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class KeyBoardListener {
    private static KeyBoardListener keyBoardListener;
    private Activity activity;
    private FrameLayout.LayoutParams frameLayoutParams;
    private View mChildOfContent;
    private int usableHeightPrevious = 0;
    private int flag01 = 0;

    public static KeyBoardListener getInstance(Activity activity) {
        keyBoardListener = new KeyBoardListener(activity);
        return keyBoardListener;
    }

    public KeyBoardListener(Activity activity) {
        this.activity = activity;
    }

    public void init() {
        this.mChildOfContent = ((FrameLayout) this.activity.findViewById(16908290)).getChildAt(0);
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.sinovatech.unicom.common.-$$Lambda$KeyBoardListener$jUMDrogWG7_1x-enK19hk-liAps
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                KeyBoardListener.this.possiblyResizeChildOfContent();
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) this.mChildOfContent.getLayoutParams();
        this.flag01 = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent() {
        int computeUsableHeight = computeUsableHeight();
        int height = this.mChildOfContent.getRootView().getHeight() - UIUtils.getNavigationBarHeight(this.activity);
        MsLogUtil.m7980d("全屏webview键盘适配：调整实际高度前" + computeUsableHeight + " " + this.mChildOfContent.getRootView().getHeight() + " " + UIUtils.getNavigationBarHeight(this.activity) + " " + height);
        if (computeUsableHeight > height) {
            this.flag01++;
            height = computeUsableHeight;
        }
        MsLogUtil.m7980d("全屏webview键盘适配：调整实际高度后" + computeUsableHeight + " " + this.mChildOfContent.getRootView().getHeight() + " " + UIUtils.getNavigationBarHeight(this.activity) + " " + height);
        int i = this.usableHeightPrevious;
        if (computeUsableHeight != i && i != 0) {
            MsLogUtil.m7980d("全屏webview键盘适配：重新调整布局");
            if (height - computeUsableHeight > height / 4) {
                this.frameLayoutParams.height = computeUsableHeight;
            } else {
                this.frameLayoutParams.height = height;
            }
            this.mChildOfContent.requestLayout();
        } else {
            int i2 = this.flag01;
            if (i2 == 1) {
                this.flag01 = i2 + 1;
                MsLogUtil.m7980d("全屏webview-动态全屏调整：-----------------------！");
                this.frameLayoutParams.height = computeUsableHeight;
                this.mChildOfContent.requestLayout();
            }
        }
        this.usableHeightPrevious = computeUsableHeight;
        MsLogUtil.m7980d("全屏webview键盘适配：frameLayoutParams.height = " + this.mChildOfContent.getLayoutParams().height);
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return (rect.bottom - rect.top) + UIUtils.getStatusBarHeight(this.activity);
    }
}
