package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.p318ui.C9718R;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TitleIndicator extends LinearLayout implements View.OnClickListener, View.OnFocusChangeListener {
    private static final int FOOTER_COLOR = -15291;
    private static final float FOOTER_LINE_HEIGHT = 4.0f;
    private static final float FOOTER_TRIANGLE_HEIGHT = 10.0f;
    private static final String TAG = "TitleFlowIndicator";
    private final int BSSEEID;
    private boolean DEBUG;
    private boolean mChangeOnClick;
    private Context mContext;
    private int mCurrID;
    private int mCurrentScroll;
    private float mFooterLineHeight;
    private float mFooterTriangleHeight;
    private LayoutInflater mInflater;
    private Paint mPaintFooterLine;
    private Paint mPaintFooterTriangle;
    private Path mPath;
    private int mPerItemWidth;
    private int mSelectedTab;
    private ColorStateList mTextColorNormal;
    private ColorStateList mTextColorSelected;
    private float mTextSizeNormal;
    private float mTextSizeSelected;
    private int mTotal;
    private ViewPager mViewPager;
    private List<String> titles;

    public TitleIndicator(Context context) {
        super(context);
        this.DEBUG = false;
        this.mCurrentScroll = 0;
        this.mPath = new Path();
        this.mSelectedTab = 0;
        this.BSSEEID = 16776960;
        this.mChangeOnClick = true;
        this.mCurrID = 0;
        this.mPerItemWidth = 0;
        this.mTotal = 0;
        initDraw(4.0f, FOOTER_COLOR);
    }

    public TitleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DEBUG = false;
        this.mCurrentScroll = 0;
        this.mPath = new Path();
        this.mSelectedTab = 0;
        this.BSSEEID = 16776960;
        this.mChangeOnClick = true;
        this.mCurrID = 0;
        this.mPerItemWidth = 0;
        this.mTotal = 0;
        setFocusable(true);
        setOnFocusChangeListener(this);
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.TitleIndicator);
        int color = obtainStyledAttributes.getColor(1, FOOTER_COLOR);
        this.mTextColorNormal = obtainStyledAttributes.getColorStateList(4);
        this.mTextColorSelected = obtainStyledAttributes.getColorStateList(5);
        this.mTextSizeNormal = obtainStyledAttributes.getDimension(6, 0.0f);
        this.mTextSizeSelected = obtainStyledAttributes.getDimension(7, this.mTextSizeNormal);
        this.mFooterLineHeight = obtainStyledAttributes.getDimension(2, 4.0f);
        this.mFooterTriangleHeight = obtainStyledAttributes.getDimension(3, 10.0f);
        initDraw(this.mFooterLineHeight, color);
        obtainStyledAttributes.recycle();
    }

    private void initDraw(float f, int i) {
        this.mPaintFooterLine = new Paint();
        this.mPaintFooterLine.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaintFooterLine.setStrokeWidth(f);
        this.mPaintFooterLine.setColor(i);
        this.mPaintFooterTriangle = new Paint();
        this.mPaintFooterTriangle.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaintFooterTriangle.setColor(i);
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        if (this.mTotal != 0) {
            this.mPerItemWidth = getWidth() / this.mTotal;
            f = (this.mCurrentScroll - (this.mSelectedTab * (getWidth() + this.mViewPager.getPageMargin()))) / this.mTotal;
        } else {
            this.mPerItemWidth = getWidth();
            f = this.mCurrentScroll;
        }
        Path path = this.mPath;
        path.rewind();
        int i = this.mSelectedTab;
        int i2 = this.mPerItemWidth;
        float f2 = (i * i2) + 0.0f + f;
        float f3 = (((i + 1) * i2) - 0.0f) + f;
        float height = ((getHeight() - this.mFooterLineHeight) - this.mFooterTriangleHeight) + 1.0f;
        path.moveTo(f2, height);
        path.lineTo(f3, height);
        float height2 = (getHeight() - this.mFooterLineHeight) + 1.0f;
        path.lineTo(f3, height2);
        path.lineTo(f2, height2);
        path.close();
        canvas.drawPath(path, this.mPaintFooterTriangle);
    }

    public void onScrolled(int i) {
        this.mCurrentScroll = i;
        invalidate();
    }

    public synchronized void onSwitched(int i) {
        if (this.mSelectedTab == i) {
            return;
        }
        setCurrentTab(i);
        invalidate();
    }

    public void init(int i, List<String> list, ViewPager viewPager) {
        removeAllViews();
        this.mViewPager = viewPager;
        this.titles = list;
        this.mTotal = list.size();
        for (int i2 = 0; i2 < this.mTotal; i2++) {
            add(i2, list.get(i2));
        }
        setCurrentTab(i);
        invalidate();
    }

    protected void add(int i, String str) {
        View inflate = this.mInflater.inflate(2131493459, (ViewGroup) this, false);
        TextView textView = (TextView) inflate.findViewById(2131298735);
        ColorStateList colorStateList = this.mTextColorNormal;
        if (colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
        float f = this.mTextSizeNormal;
        if (f > 0.0f) {
            textView.setTextSize(0, f);
        }
        textView.setText(str);
        int i2 = this.mCurrID;
        this.mCurrID = i2 + 1;
        inflate.setId(i2 + 16776960);
        inflate.setOnClickListener(this);
        ((LinearLayout.LayoutParams) inflate.getLayoutParams()).gravity = 16;
        addView(inflate);
    }

    public void setDisplayedPage(int i) {
        this.mSelectedTab = i;
    }

    public void setChangeOnClick(boolean z) {
        this.mChangeOnClick = z;
    }

    public boolean getChangeOnClick() {
        return this.mChangeOnClick;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        setCurrentTab(view.getId() - 16776960);
        NBSActionInstrumentation.onClickEventExit();
    }

    public int getTabCount() {
        return getChildCount();
    }

    public synchronized void setCurrentTab(int i) {
        if (i >= 0) {
            if (i < getTabCount()) {
                View childAt = getChildAt(this.mSelectedTab);
                childAt.setSelected(false);
                setTabTextSize(childAt, false);
                this.mSelectedTab = i;
                View childAt2 = getChildAt(this.mSelectedTab);
                childAt2.setSelected(true);
                setTabTextSize(childAt2, true);
                this.mViewPager.setCurrentItem(this.mSelectedTab);
                invalidate();
            }
        }
    }

    private void setTabTextSize(View view, boolean z) {
        TextView textView = (TextView) view.findViewById(2131298735);
        textView.setTextSize(0, z ? this.mTextSizeSelected : this.mTextSizeNormal);
        textView.setTextColor(z ? this.mTextColorSelected : this.mTextColorNormal);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        Tracker.onFocusChange(view, z);
        if (view == this && z && getTabCount() > 0) {
            getChildAt(this.mSelectedTab).requestFocus();
        } else if (z) {
            int tabCount = getTabCount();
            for (int i = 0; i < tabCount; i++) {
                if (getChildAt(i) == view) {
                    setCurrentTab(i);
                    return;
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mCurrentScroll != 0 || this.mSelectedTab == 0) {
            return;
        }
        this.mCurrentScroll = (getWidth() + this.mViewPager.getPageMargin()) * this.mSelectedTab;
    }
}
