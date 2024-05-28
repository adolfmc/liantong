package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";
    private List<List<View>> mAllViews;
    private List<Integer> mLineHeight;

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAllViews = new ArrayList();
        this.mLineHeight = new ArrayList();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -1);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            measureChild(childAt, i, i2);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            i6 = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            int i8 = i3 + measuredWidth;
            if (i8 > size) {
                i4 = Math.max(i3, measuredWidth);
                i5 = i6;
                i3 = measuredWidth;
            } else {
                i5 = Math.max(i5, i6);
                i3 = i8;
            }
            if (i7 == childCount - 1) {
                i4 = Math.max(i4, i3);
            }
        }
        if (mode != 1073741824) {
            size = i4;
        }
        if (mode2 != 1073741824) {
            size2 = i6;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mAllViews.clear();
        this.mLineHeight.clear();
        int width = getWidth();
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        ArrayList arrayList2 = arrayList;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (marginLayoutParams.leftMargin + measuredWidth + marginLayoutParams.rightMargin + i6 > width) {
                this.mAllViews.add(arrayList2);
                arrayList2 = new ArrayList();
                i6 = 0;
            }
            i6 += measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            i5 = Math.max(i5, measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
            arrayList2.add(childAt);
        }
        this.mLineHeight.add(Integer.valueOf(i5));
        this.mAllViews.add(arrayList2);
        int size = this.mAllViews.size();
        if (size >= 1) {
            size = 1;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            List<View> list = this.mAllViews.get(i9);
            int intValue = this.mLineHeight.get(i9).intValue();
            int i10 = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                View view = list.get(i11);
                if (view.getVisibility() != 8) {
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int i12 = marginLayoutParams2.leftMargin + i10;
                    int i13 = marginLayoutParams2.topMargin + i8;
                    view.layout(i12, i13, view.getMeasuredWidth() + i12, view.getMeasuredHeight() + i13);
                    i10 += view.getMeasuredWidth() + marginLayoutParams2.rightMargin + marginLayoutParams2.leftMargin;
                }
            }
            i8 += intValue;
        }
    }
}
