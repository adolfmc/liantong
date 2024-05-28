package com.sinovatech.unicom.basic.view.decklayout;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.p083v4.view.ViewCompat;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.p318ui.C9718R;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecyclerTabLayout extends RecyclerView {
    protected static final float DEFAULT_POSITION_THRESHOLD = 0.6f;
    protected static final long DEFAULT_SCROLL_DURATION = 2000;
    protected static final float POSITION_THRESHOLD_ALLOWABLE = 0.001f;
    private Activity activityContext;
    private IPagerSelect iPagerSelect;
    protected Adapter<?> mAdapter;
    protected int mIndicatorGap;
    protected int mIndicatorHeight;
    protected Paint mIndicatorPaint;
    protected int mIndicatorPosition;
    protected int mIndicatorScroll;
    protected LinearLayoutManager mLinearLayoutManager;
    private int mOldPosition;
    protected float mOldPositionOffset;
    private int mOldScrollOffset;
    protected float mPositionThreshold;
    protected RecyclerOnScrollListener mRecyclerOnScrollListener;
    protected boolean mRequestScrollToTab;
    protected boolean mScrollEanbled;
    protected int mTabBackgroundResId;
    protected int mTabMaxWidth;
    protected int mTabMinWidth;
    protected int mTabOnScreenLimit;
    protected int mTabPaddingBottom;
    protected int mTabPaddingEnd;
    protected int mTabPaddingStart;
    protected int mTabPaddingTop;
    protected int mTabSelectedTextColor;
    protected boolean mTabSelectedTextColorSet;
    protected int mTabTextAppearance;
    protected ViewPager mViewPager;
    private ViewPagerOnPageChangeListener pageChangeListener;
    private ImageView tabIcon1;
    private TextView tabTilte1;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IPagerSelect {
        void onSeclect(int i);
    }

    @Override // android.support.p086v7.widget.RecyclerView, android.view.View
    public void onDraw(Canvas canvas) {
    }

    public void setAutoSelectionMode(boolean z) {
    }

    public RecyclerTabLayout(Context context) {
        this(context, null);
    }

    public RecyclerTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.activityContext = (Activity) context;
        setWillNotDraw(false);
        this.mIndicatorPaint = new Paint();
        getAttributes(context, attributeSet, i);
        this.mLinearLayoutManager = new LinearLayoutManager(getContext()) { // from class: com.sinovatech.unicom.basic.view.decklayout.RecyclerTabLayout.1
            @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return RecyclerTabLayout.this.mScrollEanbled;
            }
        };
        this.mLinearLayoutManager.setOrientation(0);
        setLayoutManager(this.mLinearLayoutManager);
        setItemAnimator(null);
        this.pageChangeListener = new ViewPagerOnPageChangeListener(this);
        this.mPositionThreshold = DEFAULT_POSITION_THRESHOLD;
    }

    private void getAttributes(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.rtl_RecyclerTabLayout, i, 2131952238);
        setIndicatorColor(obtainStyledAttributes.getColor(2, 0));
        setIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(3, 0));
        this.mTabTextAppearance = obtainStyledAttributes.getResourceId(13, 2131952239);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.mTabPaddingBottom = dimensionPixelSize;
        this.mTabPaddingEnd = dimensionPixelSize;
        this.mTabPaddingTop = dimensionPixelSize;
        this.mTabPaddingStart = dimensionPixelSize;
        this.mTabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(10, this.mTabPaddingStart);
        this.mTabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(11, this.mTabPaddingTop);
        this.mTabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(9, this.mTabPaddingEnd);
        this.mTabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(8, this.mTabPaddingBottom);
        if (obtainStyledAttributes.hasValue(12)) {
            this.mTabSelectedTextColor = obtainStyledAttributes.getColor(12, 0);
            this.mTabSelectedTextColorSet = true;
        }
        this.mTabOnScreenLimit = obtainStyledAttributes.getInteger(6, 0);
        if (this.mTabOnScreenLimit == 0) {
            this.mTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(5, 0);
            this.mTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        }
        this.mTabBackgroundResId = obtainStyledAttributes.getResourceId(1, 0);
        this.mScrollEanbled = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    @Override // android.support.p086v7.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        RecyclerOnScrollListener recyclerOnScrollListener = this.mRecyclerOnScrollListener;
        if (recyclerOnScrollListener != null) {
            removeOnScrollListener(recyclerOnScrollListener);
            this.mRecyclerOnScrollListener = null;
        }
        super.onDetachedFromWindow();
    }

    public void setIndicatorColor(int i) {
        this.mIndicatorPaint.setColor(i);
    }

    public void setIndicatorHeight(int i) {
        this.mIndicatorHeight = i;
    }

    public void setPositionThreshold(float f) {
        this.mPositionThreshold = f;
    }

    public void setUpWithViewPager(View view, ViewPager viewPager) {
        setFirstView(view, viewPager);
        setUpWithAdapter(new DefaultAdapter(viewPager));
    }

    public void setUpWithAdapter(Adapter<?> adapter) {
        try {
            this.mAdapter = adapter;
            this.mViewPager = adapter.getViewPager();
            if (this.mViewPager.getAdapter() == null) {
                throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
            }
            this.mViewPager.removeOnPageChangeListener(this.pageChangeListener);
            this.mViewPager.addOnPageChangeListener(this.pageChangeListener);
            setAdapter(this.mAdapter);
            if (this.mViewPager.getCurrentItem() > 0) {
                scrollToTab(this.mViewPager.getCurrentItem() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFirstView(View view, ViewPager viewPager) {
        try {
            this.tabTilte1 = (TextView) view.findViewById(2131298732);
            this.tabIcon1 = (ImageView) view.findViewById(2131298731);
            this.tabTilte1.setText(viewPager.getAdapter().getPageTitle(0));
            setTabSelect(true, this.tabTilte1, this.tabIcon1);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.decklayout.RecyclerTabLayout.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    RecyclerTabLayout.this.choseItem(0);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrentItem(int i, boolean z) {
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.setCurrentItem(i, false);
            scrollToTab(this.mViewPager.getCurrentItem());
        } else if (z && i != this.mIndicatorPosition) {
            startAnimation(i);
        } else {
            scrollToTab(i);
        }
    }

    protected void startAnimation(final int i) {
        View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(i);
        float abs = findViewByPosition != null ? Math.abs((getMeasuredWidth() / 2.0f) - (findViewByPosition.getX() + (findViewByPosition.getMeasuredWidth() / 2.0f))) / findViewByPosition.getMeasuredWidth() : 1.0f;
        ValueAnimator ofFloat = i < this.mIndicatorPosition ? ValueAnimator.ofFloat(abs, 0.0f) : ValueAnimator.ofFloat(-abs, 0.0f);
        ofFloat.setDuration(2000L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.basic.view.decklayout.RecyclerTabLayout.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                RecyclerTabLayout.this.scrollToTab(i, ((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
            }
        });
        ofFloat.start();
    }

    protected void scrollToTab(int i) {
        scrollToTab(i, 0.0f, false);
        this.mAdapter.setCurrentIndicatorPosition(i);
        this.mAdapter.notifyDataSetChanged();
    }

    protected void scrollToTab(int i, float f, boolean z) {
        int measuredWidth;
        int i2;
        int i3;
        View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(i);
        View findViewByPosition2 = this.mLinearLayoutManager.findViewByPosition(i + 1);
        if (findViewByPosition != null) {
            int measuredWidth2 = getMeasuredWidth();
            float measuredWidth3 = i == 0 ? 0.0f : (measuredWidth2 / 2.5f) - (findViewByPosition.getMeasuredWidth() / 2.0f);
            float measuredWidth4 = findViewByPosition.getMeasuredWidth() + measuredWidth3;
            if (findViewByPosition2 != null) {
                float measuredWidth5 = (measuredWidth4 - ((measuredWidth2 / 2.5f) - (findViewByPosition2.getMeasuredWidth() / 2.0f))) * f;
                measuredWidth = (int) (measuredWidth3 - measuredWidth5);
                if (i == 0) {
                    float measuredWidth6 = (findViewByPosition2.getMeasuredWidth() - findViewByPosition.getMeasuredWidth()) / 2;
                    this.mIndicatorGap = (int) (measuredWidth6 * f);
                    this.mIndicatorScroll = (int) ((findViewByPosition.getMeasuredWidth() + measuredWidth6) * f);
                } else {
                    this.mIndicatorGap = (int) (((findViewByPosition2.getMeasuredWidth() - findViewByPosition.getMeasuredWidth()) / 2) * f);
                    this.mIndicatorScroll = (int) measuredWidth5;
                }
            } else {
                measuredWidth = (int) measuredWidth3;
                this.mIndicatorScroll = 0;
                this.mIndicatorGap = 0;
            }
            if (z) {
                this.mIndicatorScroll = 0;
                this.mIndicatorGap = 0;
            }
        } else {
            measuredWidth = (getMeasuredWidth() <= 0 || (i2 = this.mTabMaxWidth) <= 0 || (i3 = this.mTabMinWidth) != i2) ? 0 : ((int) ((-i3) * f)) + ((int) ((getMeasuredWidth() - i3) / 2.5f));
            this.mRequestScrollToTab = true;
        }
        this.mIndicatorPosition = i;
        stopScroll();
        if (i != this.mOldPosition || measuredWidth != this.mOldScrollOffset) {
            this.mLinearLayoutManager.scrollToPositionWithOffset(i, measuredWidth);
        }
        if (this.mIndicatorHeight > 0) {
            invalidate();
        }
        this.mOldPosition = i;
        this.mOldScrollOffset = measuredWidth;
        this.mOldPositionOffset = f;
    }

    protected void updateCurrentIndicatorPosition(int i, float f, float f2) {
        Adapter<?> adapter = this.mAdapter;
        if (adapter == null || i < 0 || i == adapter.getCurrentIndicatorPosition()) {
            return;
        }
        this.mAdapter.setCurrentIndicatorPosition(i);
        this.mAdapter.notifyDataSetChanged();
    }

    protected boolean isLayoutRtl() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {
        public int mDx;
        protected LinearLayoutManager mLinearLayoutManager;
        protected RecyclerTabLayout mRecyclerTabLayout;

        public RecyclerOnScrollListener(RecyclerTabLayout recyclerTabLayout, LinearLayoutManager linearLayoutManager) {
            this.mRecyclerTabLayout = recyclerTabLayout;
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            this.mDx += i;
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i != 0) {
                return;
            }
            if (this.mDx > 0) {
                selectCenterTabForRightScroll();
            } else {
                selectCenterTabForLeftScroll();
            }
            this.mDx = 0;
        }

        protected void selectCenterTabForRightScroll() {
            int findLastVisibleItemPosition = this.mLinearLayoutManager.findLastVisibleItemPosition();
            int width = this.mRecyclerTabLayout.getWidth() / 2;
            for (int findFirstVisibleItemPosition = this.mLinearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                if (findViewByPosition.getLeft() + findViewByPosition.getWidth() >= width) {
                    this.mRecyclerTabLayout.setCurrentItem(findFirstVisibleItemPosition, false);
                    return;
                }
            }
        }

        protected void selectCenterTabForLeftScroll() {
            int findFirstVisibleItemPosition = this.mLinearLayoutManager.findFirstVisibleItemPosition();
            int width = this.mRecyclerTabLayout.getWidth() / 2;
            for (int findLastVisibleItemPosition = this.mLinearLayoutManager.findLastVisibleItemPosition(); findLastVisibleItemPosition >= findFirstVisibleItemPosition; findLastVisibleItemPosition--) {
                if (this.mLinearLayoutManager.findViewByPosition(findLastVisibleItemPosition).getLeft() <= width) {
                    this.mRecyclerTabLayout.setCurrentItem(findLastVisibleItemPosition, false);
                    return;
                }
            }
        }
    }

    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ViewPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private final RecyclerTabLayout mRecyclerTabLayout;
        private int mScrollState;

        public ViewPagerOnPageChangeListener(RecyclerTabLayout recyclerTabLayout) {
            this.mRecyclerTabLayout = recyclerTabLayout;
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            if (i > 0) {
                this.mRecyclerTabLayout.scrollToTab(i - 1, f, false);
            }
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.mScrollState = i;
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            NBSActionInstrumentation.onPageSelectedEnter(i, this);
            if (this.mScrollState != 0) {
                if (RecyclerTabLayout.this.iPagerSelect != null) {
                    RecyclerTabLayout.this.choseItem(i);
                    RecyclerTabLayout.this.iPagerSelect.onSeclect(i);
                }
            } else {
                if (this.mRecyclerTabLayout.mIndicatorPosition != i) {
                    if (i > 0) {
                        this.mRecyclerTabLayout.scrollToTab(i - 1);
                    } else {
                        this.mRecyclerTabLayout.scrollToTab(0);
                    }
                }
                if (RecyclerTabLayout.this.iPagerSelect != null) {
                    RecyclerTabLayout.this.iPagerSelect.onSeclect(i);
                }
            }
            NBSActionInstrumentation.onPageSelectedExit();
        }
    }

    private void startAnime(final int i, float f, boolean z) {
        ValueAnimator ofFloat = i < this.mIndicatorPosition ? ValueAnimator.ofFloat(f, 0.0f) : ValueAnimator.ofFloat(-f, 0.0f);
        ofFloat.setDuration(2000L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.basic.view.decklayout.RecyclerTabLayout.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                RecyclerTabLayout.this.scrollToTab(i, ((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
            }
        });
        ofFloat.start();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static abstract class Adapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
        protected int mIndicatorPosition = -1;
        protected ViewPager mViewPager;

        public Adapter(ViewPager viewPager) {
            this.mViewPager = viewPager;
        }

        public ViewPager getViewPager() {
            return this.mViewPager;
        }

        public void setCurrentIndicatorPosition(int i) {
            this.mIndicatorPosition = i;
        }

        public int getCurrentIndicatorPosition() {
            return this.mIndicatorPosition;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class DefaultAdapter extends Adapter<ViewHolder> {
        protected static final int MAX_TAB_TEXT_LINES = 2;
        private int mTabBackgroundResId;
        private int mTabMaxWidth;
        private int mTabMinWidth;
        private int mTabOnScreenLimit;
        protected int mTabPaddingBottom;
        protected int mTabPaddingEnd;
        protected int mTabPaddingStart;
        protected int mTabPaddingTop;
        protected int mTabSelectedTextColor;
        protected boolean mTabSelectedTextColorSet;
        protected int mTabTextAppearance;

        public DefaultAdapter(ViewPager viewPager) {
            super(viewPager);
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493456, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.title.setText(getViewPager().getAdapter().getPageTitle(i + 1));
            viewHolder.title.setSelected(getCurrentIndicatorPosition() == i);
            RecyclerTabLayout.this.setTabSelect(getCurrentIndicatorPosition() == i, viewHolder.title, viewHolder.icon);
            if (getCurrentIndicatorPosition() == i) {
                RecyclerTabLayout recyclerTabLayout = RecyclerTabLayout.this;
                recyclerTabLayout.setTabSelect(false, recyclerTabLayout.tabTilte1, RecyclerTabLayout.this.tabIcon1);
            }
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return getViewPager().getAdapter().getCount() - 1;
        }

        protected RecyclerView.LayoutParams createLayoutParamsForTabs() {
            return new RecyclerView.LayoutParams(-2, -1);
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView icon;
            public TextView title;

            public ViewHolder(View view) {
                super(view);
                this.title = (TextView) view.findViewById(2131298732);
                this.icon = (ImageView) view.findViewById(2131298731);
                view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.decklayout.RecyclerTabLayout.DefaultAdapter.ViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NBSActionInstrumentation.onClickEventEnter(view2, this);
                        Tracker.onClick(view2);
                        int adapterPosition = ViewHolder.this.getAdapterPosition();
                        if (adapterPosition != -1) {
                            RecyclerTabLayout.this.choseItem(adapterPosition + 1);
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            }
        }
    }

    public void setTabSelect(boolean z, TextView textView, ImageView imageView) {
        if (z) {
            imageView.setVisibility(0);
            textView.setTextSize(1, 17.0f);
            textView.setTypeface(Typeface.defaultFromStyle(1));
            return;
        }
        imageView.setVisibility(4);
        textView.setTextSize(1, 14.0f);
        textView.setTypeface(Typeface.defaultFromStyle(0));
    }

    public int getCurrentIndex() {
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            return viewPager.getCurrentItem();
        }
        return 0;
    }

    public void choseItem(int i) {
        if (i != -1) {
            try {
                if (i < this.mViewPager.getChildCount()) {
                    this.mViewPager.setCurrentItem(i, false);
                    if (i == 0) {
                        setTabSelect(true, this.tabTilte1, this.tabIcon1);
                        smoothScrollToPosition(0);
                        this.mAdapter.setCurrentIndicatorPosition(-1);
                    } else if (i >= 0) {
                        this.mAdapter.setCurrentIndicatorPosition(i - 1);
                    }
                    this.mAdapter.notifyDataSetChanged();
                    hideSoftInput(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setIPagerSelect(IPagerSelect iPagerSelect) {
        this.iPagerSelect = iPagerSelect;
    }

    private void hideSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.activityContext.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }
}
