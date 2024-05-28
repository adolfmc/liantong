package com.mahc.custombottomsheetbehavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.p083v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    private float offset = 0.0f;
    private WeakReference<BottomSheetBehaviorGoogleMapsLike> mBottomSheetBehaviorRef = null;

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view, View view2, int i) {
        return i == 2;
    }

    public ScrollAwareFABBehavior(Context context, AttributeSet attributeSet) {
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
        if (view instanceof NestedScrollView) {
            try {
                BottomSheetBehaviorGoogleMapsLike.from(view);
                return true;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // android.support.design.widget.FloatingActionButton.BaseBehavior, android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
        if (this.offset == 0.0f) {
            setOffsetValue(coordinatorLayout);
        }
        if (this.mBottomSheetBehaviorRef == null) {
            getBottomSheetBehavior(coordinatorLayout);
        }
        float dyBetweenChildAndDependency = getDyBetweenChildAndDependency(floatingActionButton, view);
        if (floatingActionButton.getY() + dyBetweenChildAndDependency < this.offset) {
            floatingActionButton.hide();
            return false;
        } else if (floatingActionButton.getY() + dyBetweenChildAndDependency >= this.offset) {
            WeakReference<BottomSheetBehaviorGoogleMapsLike> weakReference = this.mBottomSheetBehaviorRef;
            if (weakReference == null || weakReference.get() == null) {
                getBottomSheetBehavior(coordinatorLayout);
            }
            if (floatingActionButton.getY() + dyBetweenChildAndDependency > view.getHeight() - this.mBottomSheetBehaviorRef.get().getPeekHeight()) {
                floatingActionButton.hide();
                return false;
            }
            floatingActionButton.show();
            return false;
        } else {
            return false;
        }
    }

    private int getDyBetweenChildAndDependency(@NonNull FloatingActionButton floatingActionButton, @NonNull View view) {
        if (view.getY() == 0.0f || view.getY() < this.offset || view.getY() - floatingActionButton.getY() <= floatingActionButton.getHeight()) {
            return 0;
        }
        return Math.max(0, (int) ((view.getY() - (floatingActionButton.getHeight() / 2)) - floatingActionButton.getY()));
    }

    private void setOffsetValue(CoordinatorLayout coordinatorLayout) {
        for (int i = 0; i < coordinatorLayout.getChildCount(); i++) {
            View childAt = coordinatorLayout.getChildAt(i);
            if (childAt instanceof MergedAppBarLayout) {
                this.offset = childAt.getY() + childAt.getHeight();
                return;
            }
        }
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
