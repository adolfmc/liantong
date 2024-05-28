package com.sinovatech.unicom.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.p083v4.app.Fragment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HideIMEUtil {
    public static void wrap(Activity activity) {
        wrap((ViewGroup) activity.findViewById(16908290));
    }

    public static void wrap(Fragment fragment) {
        wrap((ViewGroup) fragment.getView().getParent());
    }

    public static void wrap(ViewGroup viewGroup) {
        View childAt = viewGroup.getChildAt(0);
        viewGroup.removeView(childAt);
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        AutoHideIMEFrameLayout autoHideIMEFrameLayout = new AutoHideIMEFrameLayout(childAt.getContext());
        autoHideIMEFrameLayout.addView(childAt);
        viewGroup.addView(autoHideIMEFrameLayout, new ViewGroup.LayoutParams(layoutParams.width, layoutParams.height));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class AutoHideIMEFrameLayout extends FrameLayout {
        public AutoHideIMEFrameLayout(Context context) {
            super(context);
        }

        public AutoHideIMEFrameLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public AutoHideIMEFrameLayout(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                Context context = getContext();
                if (context == null || !(context instanceof Activity)) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                View currentFocus = ((Activity) context).getCurrentFocus();
                if (currentFocus != null && shouldHideInputMethod(currentFocus, motionEvent)) {
                    hideInputMethod(currentFocus);
                }
            }
            return super.dispatchTouchEvent(motionEvent);
        }

        private boolean shouldHideInputMethod(View view, MotionEvent motionEvent) {
            Rect rect = new Rect();
            view.getHitRect(rect);
            return !rect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        }

        private void hideInputMethod(View view) {
            if (view == null) {
                return;
            }
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }
}
