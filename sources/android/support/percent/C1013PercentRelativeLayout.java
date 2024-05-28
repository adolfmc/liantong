package android.support.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.percent.PercentLayoutHelper;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: android.support.percent.PercentRelativeLayout */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1013PercentRelativeLayout extends RelativeLayout {
    private final PercentLayoutHelper mHelper;

    public C1013PercentRelativeLayout(Context context) {
        super(context);
        this.mHelper = new PercentLayoutHelper(this);
    }

    public C1013PercentRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHelper = new PercentLayoutHelper(this);
    }

    public C1013PercentRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHelper = new PercentLayoutHelper(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.RelativeLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        this.mHelper.adjustChildren(i, i2);
        super.onMeasure(i, i2);
        if (this.mHelper.handleMeasuredStateTooSmall()) {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mHelper.restoreOriginalParams();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* renamed from: android.support.percent.PercentRelativeLayout$LayoutParams */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class LayoutParams extends RelativeLayout.LayoutParams implements PercentLayoutHelper.PercentLayoutParams {
        private PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mPercentLayoutInfo = PercentLayoutHelper.getPercentLayoutInfo(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        @Override // android.support.percent.PercentLayoutHelper.PercentLayoutParams
        public PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo() {
            if (this.mPercentLayoutInfo == null) {
                this.mPercentLayoutInfo = new PercentLayoutHelper.PercentLayoutInfo();
            }
            return this.mPercentLayoutInfo;
        }

        @Override // android.view.ViewGroup.LayoutParams
        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            PercentLayoutHelper.fetchWidthAndHeight(this, typedArray, i, i2);
        }
    }
}
