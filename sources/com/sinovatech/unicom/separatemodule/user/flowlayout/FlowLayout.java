package com.sinovatech.unicom.separatemodule.user.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.sinovatech.unicom.p318ui.C9718R;
import com.sinovatech.unicom.separatemodule.user.flowlayout.FlowLayoutAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FlowLayout extends ViewGroup {
    private static final String TAG = "NewSearchFlowLayout";
    private FlowLayoutAdapter flowLayoutAdapter;
    private float horizontal_space;
    private boolean isAvg;
    private Line mCurrentLine;
    private List<Line> mLines;
    private int mMaxWidth;
    private int maxLines;
    private float vertical_space;

    public boolean isAvg() {
        return this.isAvg;
    }

    public void setAvg(boolean z) {
        this.isAvg = z;
    }

    public void setAdapter(FlowLayoutAdapter flowLayoutAdapter) {
        this.flowLayoutAdapter = flowLayoutAdapter;
        this.flowLayoutAdapter.setOnDataSetChangedListener(new FlowLayoutAdapter.OnDataSetChangedListener() { // from class: com.sinovatech.unicom.separatemodule.user.flowlayout.FlowLayout.1
            @Override // com.sinovatech.unicom.separatemodule.user.flowlayout.FlowLayoutAdapter.OnDataSetChangedListener
            public void onDataSetChanged() {
                FlowLayout flowLayout = FlowLayout.this;
                flowLayout.setAdapter(flowLayout.flowLayoutAdapter);
            }
        });
        removeAllViews();
        int count = flowLayoutAdapter.getCount();
        Log.d(TAG, "setAdapter() called with: size = [" + count + "]");
        for (int i = 0; i < count; i++) {
            addView(flowLayoutAdapter.getView(this, i));
        }
    }

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, float f, float f2) {
        this(context, null);
        this.horizontal_space = f;
        this.vertical_space = f2;
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLines = new ArrayList();
        this.isAvg = false;
        this.maxLines = Integer.MAX_VALUE;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.FlowLayout);
        this.horizontal_space = obtainStyledAttributes.getDimension(3, 0.0f);
        this.vertical_space = obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
        Log.d(TAG, "NewSearchFlowLayout() called with: horizontal_space = [" + this.horizontal_space + "], vertical_space = [" + this.vertical_space + "]");
    }

    public void setMaxLines(int i) {
        this.maxLines = i;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.mLines.clear();
        this.mCurrentLine = null;
        int size = View.MeasureSpec.getSize(i);
        this.mMaxWidth = (size - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        Log.d(TAG, "onMeasure() called with: childCount = [" + childCount + "]");
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            measureChild(childAt, i, i2);
            Line line = this.mCurrentLine;
            if (line == null) {
                this.mCurrentLine = new Line(this.mMaxWidth, this.horizontal_space);
                if (i3 == childCount - 1) {
                    this.mCurrentLine.setLast(true);
                }
                this.mCurrentLine.addView(childAt);
                this.mLines.add(this.mCurrentLine);
            } else if (line.canAddView(childAt)) {
                this.mCurrentLine.addView(childAt);
                if (i3 == childCount - 1) {
                    this.mCurrentLine.setLast(true);
                }
            } else if (this.maxLines > 0 && this.mLines.size() == this.maxLines) {
                break;
            } else {
                this.mCurrentLine = new Line(this.mMaxWidth, this.horizontal_space);
                this.mCurrentLine.addView(childAt);
                if (i3 == childCount - 1) {
                    this.mCurrentLine.setLast(true);
                }
                this.mLines.add(this.mCurrentLine);
            }
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        for (int i4 = 0; i4 < this.mLines.size(); i4++) {
            paddingTop += this.mLines.get(i4).height;
        }
        setMeasuredDimension(size, (int) (paddingTop + ((this.mLines.size() - 1) * this.vertical_space)));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int size = this.mLines.size();
        Log.d(TAG, "onLayout() called with: lineSize = [" + size + "]");
        int i5 = this.maxLines;
        if (size >= i5) {
            size = i5;
        }
        int i6 = this.maxLines;
        if (size >= i6) {
            size = i6;
        }
        for (int i7 = 0; i7 < size; i7++) {
            Line line = this.mLines.get(i7);
            line.layout(paddingTop, paddingLeft);
            paddingTop += line.height;
            if (i7 != this.mLines.size() - 1) {
                paddingTop = (int) (paddingTop + this.vertical_space);
            }
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class Line {
        private int height;
        private boolean isLast;
        private int maxWidth;
        private float space;
        private int usedWidth;
        private List<View> views = new ArrayList();

        public Line(int i, float f) {
            this.maxWidth = i;
            this.space = f;
        }

        public boolean isLast() {
            return this.isLast;
        }

        public void setLast(boolean z) {
            this.isLast = z;
        }

        public void addView(View view) {
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            if (this.views.size() == 0) {
                int i = this.maxWidth;
                if (measuredWidth > i) {
                    this.usedWidth = i;
                    this.height = measuredHeight;
                } else {
                    this.usedWidth = measuredWidth;
                    this.height = measuredHeight;
                }
            } else {
                this.usedWidth = (int) (this.usedWidth + measuredWidth + this.space);
                int i2 = this.height;
                if (measuredHeight > i2) {
                    i2 = measuredHeight;
                }
                this.height = i2;
            }
            this.views.add(view);
        }

        public boolean canAddView(View view) {
            return this.views.size() == 0 || ((float) view.getMeasuredWidth()) <= ((float) (this.maxWidth - this.usedWidth)) - this.space;
        }

        public void layout(int i, int i2) {
            int size = FlowLayout.this.isAvg ? (this.maxWidth - this.usedWidth) / this.views.size() : 0;
            for (View view : this.views) {
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                if (this.isLast) {
                    view.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
                } else {
                    view.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth + size, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
                }
                int measuredWidth2 = view.getMeasuredWidth();
                view.layout(i2, i, measuredWidth2 + i2, measuredHeight + i);
                i2 = (int) (i2 + measuredWidth2 + this.space);
            }
        }
    }
}
