package com.sinovatech.unicom.separatemodule.live.clearscreen;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AndroidBug5497Workaround {
    private FrameLayout.LayoutParams frameLayoutParams;
    private View mChildOfContent;
    private int usableHeightPrevious;

    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private AndroidBug5497Workaround(Activity activity) {
        this.mChildOfContent = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.sinovatech.unicom.separatemodule.live.clearscreen.AndroidBug5497Workaround.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AndroidBug5497Workaround.this.possiblyResizeChildOfContent();
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) this.mChildOfContent.getLayoutParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent() {
        int computeUsableHeight = computeUsableHeight();
        if (computeUsableHeight != this.usableHeightPrevious) {
            int height = this.mChildOfContent.getRootView().getHeight();
            int i = height - computeUsableHeight;
            if (i > height / 4) {
                this.frameLayoutParams.height = height - i;
            } else {
                this.frameLayoutParams.height = height;
            }
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = computeUsableHeight;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }
}
