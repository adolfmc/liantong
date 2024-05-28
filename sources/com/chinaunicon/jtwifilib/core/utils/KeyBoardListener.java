package com.chinaunicon.jtwifilib.core.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class KeyBoardListener {
    private static KeyBoardListener keyBoardListener;
    private Activity activity;
    private FrameLayout.LayoutParams frameLayoutParams;
    private View mChildOfContent;
    private int usableHeightPrevious;

    public static KeyBoardListener getInstance(Activity activity) {
        keyBoardListener = new KeyBoardListener(activity);
        return keyBoardListener;
    }

    public KeyBoardListener(Activity activity) {
        this.activity = activity;
    }

    public void init() {
        this.mChildOfContent = ((FrameLayout) this.activity.findViewById(16908290)).getChildAt(0);
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.chinaunicon.jtwifilib.core.utils.KeyBoardListener.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                KeyBoardListener.this.possiblyResizeChildOfContent();
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
