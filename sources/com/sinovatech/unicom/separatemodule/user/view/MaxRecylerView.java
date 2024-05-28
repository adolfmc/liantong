package com.sinovatech.unicom.separatemodule.user.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.C9718R;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MaxRecylerView extends RecyclerView {
    private int mMaxHeight;

    public MaxRecylerView(@NonNull Context context) {
        super(context);
        this.mMaxHeight = UIUtils.dip2px(690.0f);
    }

    public MaxRecylerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxHeight = UIUtils.dip2px(690.0f);
        init(context, attributeSet);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.MaxRecyclerView);
        this.mMaxHeight = obtainStyledAttributes.getLayoutDimension(0, this.mMaxHeight);
        obtainStyledAttributes.recycle();
    }

    @Override // android.support.p086v7.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredHeight = getMeasuredHeight();
        int i3 = this.mMaxHeight;
        if (measuredHeight > i3) {
            setMeasuredDimension(i, i3);
        }
    }
}
