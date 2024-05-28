package com.sinovatech.unicom.basic.p315ui.fuwu.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.view.MyRelativeLayout */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyRelativeLayout extends RelativeLayout {
    private Context context;

    public MyRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public MyRelativeLayout(Context context) {
        super(context);
        this.context = context;
    }

    public MyRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    public boolean isSoftKeyboardOpen() {
        Rect rect = new Rect();
        ((Activity) this.context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return ((Activity) this.context).getWindow().getDecorView().getRootView().getHeight() - (rect.bottom - rect.top) > 500;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        View focusedChild = getFocusedChild();
        if (focusedChild != null && FuWuConstant.isBianJiState && isSoftKeyboardOpen()) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(focusedChild.getWindowToken(), 0);
            focusedChild.clearFocus();
            return true;
        }
        return false;
    }
}
