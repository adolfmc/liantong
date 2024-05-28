package com.mahc.custombottomsheetbehavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.p083v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BackdropBottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private WeakReference<BottomSheetBehaviorGoogleMapsLike> mBottomSheetBehaviorRef;
    private int mCurrentChildY;

    public BackdropBottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view2 instanceof NestedScrollView) {
            try {
                BottomSheetBehaviorGoogleMapsLike.from(view2);
                return true;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        WeakReference<BottomSheetBehaviorGoogleMapsLike> weakReference = this.mBottomSheetBehaviorRef;
        if (weakReference == null || weakReference.get() == null) {
            getBottomSheetBehavior(coordinatorLayout);
        }
        int height = view2.getHeight() - this.mBottomSheetBehaviorRef.get().getPeekHeight();
        int height2 = view.getHeight();
        int i = this.mCurrentChildY;
        int y = (int) (((view2.getY() - height2) * height) / (height - height2));
        this.mCurrentChildY = y;
        if (y <= 0) {
            this.mCurrentChildY = 0;
            view.setY(0);
        } else {
            view.setY(this.mCurrentChildY);
        }
        return i == this.mCurrentChildY;
    }

    private void getBottomSheetBehavior(@NonNull CoordinatorLayout coordinatorLayout) {
        for (int i = 0; i < coordinatorLayout.getChildCount(); i++) {
            View childAt = coordinatorLayout.getChildAt(i);
            if (childAt instanceof NestedScrollView) {
                try {
                    this.mBottomSheetBehaviorRef = new WeakReference<>(BottomSheetBehaviorGoogleMapsLike.from(childAt));
                    return;
                } catch (IllegalArgumentException unused) {
                    continue;
                }
            }
        }
    }
}
