package android.support.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RequiresApi;
import android.support.percent.PercentLayoutHelper;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PercentFrameLayout extends FrameLayout {
    private final PercentLayoutHelper mHelper;

    public PercentFrameLayout(Context context) {
        super(context);
        this.mHelper = new PercentLayoutHelper(this);
    }

    public PercentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHelper = new PercentLayoutHelper(this);
    }

    public PercentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHelper = new PercentLayoutHelper(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        this.mHelper.adjustChildren(i, i2);
        super.onMeasure(i, i2);
        if (this.mHelper.handleMeasuredStateTooSmall()) {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mHelper.restoreOriginalParams();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class LayoutParams extends FrameLayout.LayoutParams implements PercentLayoutHelper.PercentLayoutParams {
        private PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mPercentLayoutInfo = PercentLayoutHelper.getPercentLayoutInfo(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(FrameLayout.LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = layoutParams.gravity;
        }

        @RequiresApi(19)
        public LayoutParams(LayoutParams layoutParams) {
            this((FrameLayout.LayoutParams) layoutParams);
            this.mPercentLayoutInfo = layoutParams.mPercentLayoutInfo;
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
