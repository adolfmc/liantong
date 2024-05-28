package com.liang.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p083v4.graphics.drawable.DrawableCompat;
import android.support.p083v4.util.Pools;
import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewCompat;
import android.support.p083v4.view.ViewPager;
import android.support.p083v4.view.animation.FastOutSlowInInterpolator;
import android.support.p086v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.liang.jtablayout.adapter.TabAdapter;
import com.liang.jtablayout.indicator.DefIndicatorEvaluator;
import com.liang.jtablayout.indicator.Indicator;
import com.liang.jtablayout.indicator.IndicatorPoint;
import com.liang.jtablayout.indicator.TransitionIndicatorEvaluator;
import com.liang.jtablayout.tab.Tab;
import com.liang.jtablayout.utils.ColorUtils;
import com.liang.jtablayout.utils.MaterialResources;
import com.liang.jtablayout.utils.ViewUtils;
import com.liang.jtablayoutx.C5196R;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@ViewPager.DecorView
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JTabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;
    @Dimension(unit = 0)
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    @Dimension(unit = 0)
    private static final int DEFAULT_HEIGHT = 48;
    @Dimension(unit = 0)
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    public static final TimeInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    @Dimension(unit = 0)
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    public static final int INDICATOR_TIER_BACK = 0;
    public static final int INDICATOR_TIER_FRONT = 1;
    private static final int INVALID_WIDTH = -1;
    @Dimension(unit = 0)
    private static final int MIN_INDICATOR_WIDTH = 24;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    @Dimension(unit = 0)
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private AdapterChangeListener adapterChangeListener;
    private int contentInsetStart;
    private BaseOnTabSelectedListener currentVpSelectedListener;
    boolean inlineLabel;
    int mode;
    private TabLayoutOnPageChangeListener pageChangeListener;
    private PagerAdapter pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scaleAnimator;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;
    private BaseOnTabSelectedListener selectedListener;
    private final ArrayList<BaseOnTabSelectedListener> selectedListeners;
    private int selectedPosition;
    private Tab selectedTab;
    private boolean setupViewPagerImplicitly;
    private final SlidingTabIndicator slidingTabIndicator;
    private final int tabBackgroundResId;
    boolean tabColorTransitionScroll;
    private int tabDividerColor;
    private int tabDividerHeight;
    private int tabDividerWidth;
    int tabGravity;
    private ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabIndicatorMargin;
    int tabIndicatorTier;
    boolean tabIndicatorTransitionScroll;
    int tabIndicatorWidth;
    float tabIndicatorWidthScale;
    int tabMaxWidth;
    private int tabPaddingBottom;
    private int tabPaddingEnd;
    private int tabPaddingStart;
    private int tabPaddingTop;
    private ColorStateList tabRippleColorStateList;
    float tabScaleTransitionScroll;
    @Nullable
    Drawable tabSelectedIndicator;
    private final boolean tabTextBold;
    private ColorStateList tabTextColors;
    private int tabTextSize;
    private final RectF tabViewContentBounds;
    private final Pools.Pool<TabView> tabViewPool;
    private final ArrayList<Tab> tabs;
    private boolean unboundedRipple;
    ViewPager viewPager;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface BaseOnTabSelectedListener<T extends Tab> {
        void onTabReselected(@NonNull T t);

        void onTabSelected(@NonNull T t);

        void onTabUnselected(@NonNull T t);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface Mode {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface TabGravity {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface TabIndicatorGravity {
    }

    public JTabLayout(Context context) {
        this(context, null);
    }

    public JTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectedPosition = -1;
        this.tabs = new ArrayList<>();
        this.tabViewContentBounds = new RectF();
        this.tabMaxWidth = Integer.MAX_VALUE;
        this.selectedListeners = new ArrayList<>();
        this.tabViewPool = new Pools.SimplePool(12);
        setHorizontalScrollBarEnabled(false);
        this.slidingTabIndicator = new SlidingTabIndicator(context);
        super.addView(this.slidingTabIndicator, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5196R.styleable.TabLayout, i, 0);
        this.slidingTabIndicator.setSelectedIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabIndicatorHeight, -1));
        this.slidingTabIndicator.setSelectedIndicatorColor(obtainStyledAttributes.getColor(C5196R.styleable.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(MaterialResources.getDrawable(context, obtainStyledAttributes, C5196R.styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(obtainStyledAttributes.getInt(C5196R.styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(obtainStyledAttributes.getBoolean(C5196R.styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabPadding, 0);
        this.tabPaddingBottom = dimensionPixelSize;
        this.tabPaddingEnd = dimensionPixelSize;
        this.tabPaddingTop = dimensionPixelSize;
        this.tabPaddingStart = dimensionPixelSize;
        this.tabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabPaddingStart, this.tabPaddingStart);
        this.tabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabPaddingTop, this.tabPaddingTop);
        this.tabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabPaddingEnd, this.tabPaddingEnd);
        this.tabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabPaddingBottom, this.tabPaddingBottom);
        this.tabDividerWidth = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabDividerWidth, 0);
        this.tabDividerHeight = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabDividerHeight, -1);
        this.tabDividerColor = obtainStyledAttributes.getColor(C5196R.styleable.TabLayout_tabDividerColor, 0);
        this.tabIndicatorTier = obtainStyledAttributes.getInt(C5196R.styleable.TabLayout_tabIndicatorTier, 0);
        this.tabIndicatorWidth = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabIndicatorWidth, 0);
        this.tabIndicatorMargin = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabIndicatorMargin, 0);
        this.tabIndicatorWidthScale = obtainStyledAttributes.getFloat(C5196R.styleable.TabLayout_tabIndicatorWidthScale, 0.0f);
        if (obtainStyledAttributes.hasValue(C5196R.styleable.TabLayout_tabTextColor)) {
            this.tabTextColors = MaterialResources.getColorStateList(context, obtainStyledAttributes, C5196R.styleable.TabLayout_tabTextColor);
        }
        this.tabTextSize = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabTextSize, 0);
        this.tabIconTint = MaterialResources.getColorStateList(context, obtainStyledAttributes, C5196R.styleable.TabLayout_tabIconTint);
        this.tabIconTintMode = ViewUtils.parseTintMode(obtainStyledAttributes.getInt(C5196R.styleable.TabLayout_tabIconTintMode, -1), null);
        this.tabIndicatorFullWidth = obtainStyledAttributes.getBoolean(C5196R.styleable.TabLayout_tabIndicatorFullWidth, false);
        this.tabIndicatorTransitionScroll = obtainStyledAttributes.getBoolean(C5196R.styleable.TabLayout_tabIndicatorTransitionScroll, false);
        this.tabScaleTransitionScroll = obtainStyledAttributes.getFloat(C5196R.styleable.TabLayout_tabScaleTransitionScroll, 1.0f);
        this.tabColorTransitionScroll = obtainStyledAttributes.getBoolean(C5196R.styleable.TabLayout_tabTextColorTransitionScroll, false);
        this.tabTextBold = obtainStyledAttributes.getBoolean(C5196R.styleable.TabLayout_tabTextBold, false);
        this.tabRippleColorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, C5196R.styleable.TabLayout_tabRippleColor);
        this.tabIndicatorAnimationDuration = obtainStyledAttributes.getInt(C5196R.styleable.TabLayout_tabIndicatorAnimationDuration, 300);
        this.requestedTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabMinWidth, -1);
        this.requestedTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabMaxWidth, -1);
        this.tabBackgroundResId = obtainStyledAttributes.getResourceId(C5196R.styleable.TabLayout_tabBackground, 0);
        this.contentInsetStart = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabLayout_tabContentStart, 0);
        this.mode = obtainStyledAttributes.getInt(C5196R.styleable.TabLayout_tabMode, 1);
        this.tabGravity = obtainStyledAttributes.getInt(C5196R.styleable.TabLayout_tabGravity, 0);
        this.inlineLabel = obtainStyledAttributes.getBoolean(C5196R.styleable.TabLayout_tabInlineLabel, false);
        this.unboundedRipple = obtainStyledAttributes.getBoolean(C5196R.styleable.TabLayout_tabUnboundedRipple, false);
        obtainStyledAttributes.recycle();
        this.scrollableTabMinWidth = getResources().getDimensionPixelSize(C5196R.dimen.design_tab_scrollable_min_width);
        applyModeAndGravity();
    }

    public void setIndicator(@NonNull Indicator indicator) {
        this.tabIndicatorWidth = indicator.getWidth();
        this.tabIndicatorMargin = indicator.getMargin();
        this.tabIndicatorWidthScale = indicator.getWidthScale();
        this.tabIndicatorFullWidth = indicator.isFullWidth();
        this.tabIndicatorGravity = indicator.getGravity();
        this.tabIndicatorTransitionScroll = indicator.isTransitionScroll();
        this.tabSelectedIndicator = indicator.getIndicator();
        this.slidingTabIndicator.setSelectedIndicatorHeight(indicator.getHeight());
        this.slidingTabIndicator.setSelectedIndicatorColor(indicator.getColor());
    }

    public void setSelectedTabIndicatorColor(@ColorInt int i) {
        this.slidingTabIndicator.setSelectedIndicatorColor(i);
    }

    public void setTabIndicatorTier(int i) {
        this.tabIndicatorTier = i;
        ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i) {
        this.slidingTabIndicator.setSelectedIndicatorHeight(i);
    }

    public void setScrollPosition(int i, float f, boolean z) {
        setScrollPosition(i, f, z, true);
    }

    void setScrollPosition(int i, float f, boolean z, boolean z2) {
        int i2;
        int round = Math.round(i + f);
        if (round < 0 || round >= this.slidingTabIndicator.getChildCount()) {
            return;
        }
        if (z2) {
            this.slidingTabIndicator.setIndicatorPositionFromTabPosition(i, f);
        }
        ValueAnimator valueAnimator = this.scrollAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.scrollAnimator.cancel();
        }
        scrollTo(calculateScrollXForTab(i, f), 0);
        if (z) {
            setSelectedTabView(round, true);
        }
        if (round < 0 || round >= this.slidingTabIndicator.getChildCount() || f <= 0.0f || (i2 = i + 1) >= this.slidingTabIndicator.getChildCount()) {
            return;
        }
        float f2 = this.tabScaleTransitionScroll;
        if (f2 > 1.0f) {
            float f3 = f2 - 1.0f;
            ((Tab) this.slidingTabIndicator.getChildAt(i)).updateScale(((1.0f - f) * f3) + 1.0f);
            ((Tab) this.slidingTabIndicator.getChildAt(i2)).updateScale((f3 * f) + 1.0f);
        }
        if (this.tabColorTransitionScroll) {
            ((Tab) this.slidingTabIndicator.getChildAt(i)).updateColor(1.0f - f);
            ((Tab) this.slidingTabIndicator.getChildAt(i2)).updateColor(f);
        }
    }

    void updateScaleOrColorPosition(int i, float f) {
        if (i < 0 || i > getTabCount() - 1) {
            return;
        }
        Tab tab = this.selectedTab;
        if (tab == null || i != tab.getPosition()) {
            Tab tab2 = this.selectedTab;
            if (tab2 != null && tab2.getPosition() > -1) {
                ((Tab) this.slidingTabIndicator.getChildAt(this.selectedTab.getPosition())).updateScale(1.0f);
            }
            ((Tab) this.slidingTabIndicator.getChildAt(i)).updateScale(((this.tabScaleTransitionScroll - 1.0f) * f) + 1.0f);
        }
    }

    public void showBadgeMsg(int i) {
        showBadgeMsg(i, "", true);
    }

    public void showBadgeMsg(int i, int i2) {
        showBadgeMsg(i, i2 + "", i2 > 0);
    }

    public void showBadgeMsg(int i, @NonNull String str) {
        showBadgeMsg(i, str, str.trim().length() > 0);
    }

    public void showBadgeMsg(int i, String str, boolean z) {
        Tab tab = (Tab) this.slidingTabIndicator.getChildAt(i);
        if (tab != null) {
            if (z) {
                tab.showBadge(str);
            } else {
                tab.hideBadge();
            }
        }
    }

    public void setBadgeTextColor(@ColorInt int i) {
        for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
            setBadgeTextColor(i2, i);
        }
    }

    public void setBadgeTextColor(int i, @ColorInt int i2) {
        Tab tab = (Tab) this.slidingTabIndicator.getChildAt(i);
        if (tab != null) {
            tab.setBadgeTextColor(i2);
        }
    }

    public void setBadgeTextSize(int i, int i2) {
        Tab tab = (Tab) this.slidingTabIndicator.getChildAt(i);
        if (tab != null) {
            tab.setBadgeTextSize(i2);
        }
    }

    public void setBadgeTextSize(int i) {
        for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
            setBadgeTextSize(i2, i);
        }
    }

    public void setBadgeColor(@ColorInt int i) {
        for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
            setBadgeColor(i2, i);
        }
    }

    public void setBadgeColor(int i, @ColorInt int i2) {
        Tab tab = (Tab) this.slidingTabIndicator.getChildAt(i);
        if (tab != null) {
            tab.setBadgeBackgroundColor(i2);
        }
    }

    public void setBadgeStroke(int i, @ColorInt int i2) {
        for (int i3 = 0; i3 < this.slidingTabIndicator.getChildCount(); i3++) {
            setBadgeStroke(i3, i, i2);
        }
    }

    public void setBadgeStroke(int i, int i2, @ColorInt int i3) {
        Tab tab = (Tab) this.slidingTabIndicator.getChildAt(i);
        if (tab != null) {
            tab.setBadgeStroke(i2, i3);
        }
    }

    public void addTab(@NonNull Tab tab) {
        addTab(tab, this.tabs.isEmpty());
    }

    public void addTab(@NonNull Tab tab, int i) {
        addTab(tab, i, this.tabs.isEmpty());
    }

    public void addTab(@NonNull Tab tab, boolean z) {
        addTab(tab, this.tabs.size(), z);
    }

    public void addTab(@NonNull Tab tab, int i, boolean z) {
        tab.setTabLayout(this);
        configureTab(tab, i);
        addTabView(tab);
        if (z) {
            tab.select();
        }
    }

    @Deprecated
    public void setOnTabSelectedListener(@Nullable BaseOnTabSelectedListener baseOnTabSelectedListener) {
        BaseOnTabSelectedListener baseOnTabSelectedListener2 = this.selectedListener;
        if (baseOnTabSelectedListener2 != null) {
            removeOnTabSelectedListener(baseOnTabSelectedListener2);
        }
        this.selectedListener = baseOnTabSelectedListener;
        if (baseOnTabSelectedListener != null) {
            addOnTabSelectedListener(baseOnTabSelectedListener);
        }
    }

    public void addOnTabSelectedListener(@NonNull BaseOnTabSelectedListener baseOnTabSelectedListener) {
        if (this.selectedListeners.contains(baseOnTabSelectedListener)) {
            return;
        }
        this.selectedListeners.add(baseOnTabSelectedListener);
    }

    public void removeOnTabSelectedListener(@NonNull BaseOnTabSelectedListener baseOnTabSelectedListener) {
        this.selectedListeners.remove(baseOnTabSelectedListener);
    }

    public void clearOnTabSelectedListeners() {
        this.selectedListeners.clear();
    }

    @NonNull
    public Tab newTab() {
        return createTabView();
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    @Nullable
    public Tab getTabAt(int i) {
        if (i < 0 || i >= getTabCount()) {
            return null;
        }
        return this.tabs.get(i);
    }

    public int getSelectedTabPosition() {
        Tab tab = this.selectedTab;
        if (tab != null) {
            return tab.getPosition();
        }
        return -1;
    }

    public void removeTab(Tab tab) {
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i) {
        Tab tab = this.selectedTab;
        int position = tab != null ? tab.getPosition() : 0;
        removeTabViewAt(i);
        Tab remove = this.tabs.remove(i);
        if (remove != null) {
            remove.reset();
        }
        int size = this.tabs.size();
        for (int i2 = i; i2 < size; i2++) {
            this.tabs.get(i2).setPosition(i2);
        }
        if (position == i) {
            selectTab(this.tabs.isEmpty() ? null : this.tabs.get(Math.max(0, i - 1)));
        }
    }

    public void removeAllTabs() {
        for (int childCount = this.slidingTabIndicator.getChildCount() - 1; childCount >= 0; childCount--) {
            removeTabViewAt(childCount);
        }
        Iterator<Tab> it = this.tabs.iterator();
        while (it.hasNext()) {
            it.remove();
            it.next().reset();
        }
        this.selectedTab = null;
        this.selectedPosition = -1;
    }

    public void setTabMode(int i) {
        if (i != this.mode) {
            this.mode = i;
            applyModeAndGravity();
        }
    }

    public int getTabMode() {
        return this.mode;
    }

    public void setTabGravity(int i) {
        if (this.tabGravity != i) {
            this.tabGravity = i;
            applyModeAndGravity();
        }
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public void setSelectedTabIndicatorGravity(int i) {
        if (this.tabIndicatorGravity != i) {
            this.tabIndicatorGravity = i;
            ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
        }
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.tabIndicatorFullWidth = z;
        ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
    }

    public boolean isTabIndicatorFullWidth() {
        return this.tabIndicatorFullWidth;
    }

    public void setInlineLabel(boolean z) {
        if (this.inlineLabel != z) {
            this.inlineLabel = z;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View childAt = this.slidingTabIndicator.getChildAt(i);
                if (childAt instanceof Tab) {
                    ((Tab) childAt).setInlineLabel(this.inlineLabel);
                }
            }
            applyModeAndGravity();
        }
    }

    public void setInlineLabelResource(@BoolRes int i) {
        setInlineLabel(getResources().getBoolean(i));
    }

    public boolean isInlineLabel() {
        return this.inlineLabel;
    }

    public void setUnboundedRipple(boolean z) {
        if (this.unboundedRipple != z) {
            this.unboundedRipple = z;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View childAt = this.slidingTabIndicator.getChildAt(i);
                if (childAt instanceof Tab) {
                    ((Tab) childAt).updateBackgroundDrawable();
                }
            }
        }
    }

    public void setUnboundedRippleResource(@BoolRes int i) {
        setUnboundedRipple(getResources().getBoolean(i));
    }

    public boolean hasUnboundedRipple() {
        return this.unboundedRipple;
    }

    public void setTabTextColors(@Nullable ColorStateList colorStateList) {
        if (this.tabTextColors != colorStateList) {
            this.tabTextColors = colorStateList;
            updateAllTabs();
        }
    }

    @Nullable
    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public void setTabTextColors(int i, int i2) {
        setTabTextColors(ColorUtils.createColorStateList(i, i2));
    }

    public void setTabTextSize(int i) {
        int i2 = this.tabTextSize;
        if (i2 != i2) {
            this.tabTextSize = i;
            updateAllTabs();
        }
    }

    public void setTabIconTint(@Nullable ColorStateList colorStateList) {
        if (this.tabIconTint != colorStateList) {
            this.tabIconTint = colorStateList;
            updateAllTabs();
        }
    }

    public void setTabIconTintResource(@ColorRes int i) {
        setTabIconTint(AppCompatResources.getColorStateList(getContext(), i));
    }

    @Nullable
    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    @Nullable
    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    public void setTabRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.tabRippleColorStateList != colorStateList) {
            this.tabRippleColorStateList = colorStateList;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View childAt = this.slidingTabIndicator.getChildAt(i);
                Tab tab = this.tabs.get(i);
                if (tab != null && tab.getTabRippleColorStateList() == null) {
                    tab.setTabRippleColorStateList(this.tabRippleColorStateList);
                    if (childAt instanceof Tab) {
                        ((Tab) childAt).updateBackgroundDrawable();
                    }
                }
            }
        }
    }

    public void setTabRippleColorResource(@ColorRes int i) {
        setTabRippleColor(AppCompatResources.getColorStateList(getContext(), i));
    }

    @Nullable
    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public void setSelectedTabIndicator(@Nullable Drawable drawable) {
        if (this.tabSelectedIndicator != drawable) {
            this.tabSelectedIndicator = drawable;
            ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
        }
    }

    public void setSelectedTabIndicator(@DrawableRes int i) {
        if (i != 0) {
            setSelectedTabIndicator(AppCompatResources.getDrawable(getContext(), i));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager, boolean z) {
        setupWithViewPager(viewPager, z, false);
    }

    private void setupWithViewPager(@Nullable ViewPager viewPager, boolean z, boolean z2) {
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.pageChangeListener;
            if (tabLayoutOnPageChangeListener != null) {
                viewPager2.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
            }
            AdapterChangeListener adapterChangeListener = this.adapterChangeListener;
            if (adapterChangeListener != null) {
                this.viewPager.removeOnAdapterChangeListener(adapterChangeListener);
            }
        }
        BaseOnTabSelectedListener baseOnTabSelectedListener = this.currentVpSelectedListener;
        if (baseOnTabSelectedListener != null) {
            removeOnTabSelectedListener(baseOnTabSelectedListener);
            this.currentVpSelectedListener = null;
        }
        if (viewPager != null) {
            this.viewPager = viewPager;
            if (this.pageChangeListener == null) {
                this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            this.pageChangeListener.reset();
            viewPager.addOnPageChangeListener(this.pageChangeListener);
            this.currentVpSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            addOnTabSelectedListener(this.currentVpSelectedListener);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, z);
            }
            if (this.adapterChangeListener == null) {
                this.adapterChangeListener = new AdapterChangeListener();
            }
            this.adapterChangeListener.setAutoRefresh(z);
            viewPager.addOnAdapterChangeListener(this.adapterChangeListener);
            setScrollPosition(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.viewPager = null;
            setPagerAdapter(null, false);
        }
        this.setupViewPagerImplicitly = z2;
    }

    @Deprecated
    public void setTabsFromPagerAdapter(@Nullable PagerAdapter pagerAdapter) {
        setPagerAdapter(pagerAdapter, false);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.viewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                setupWithViewPager((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager(null);
            this.setupViewPagerImplicitly = false;
        }
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    void setPagerAdapter(@Nullable PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.pagerAdapter;
        if (pagerAdapter2 != null && (dataSetObserver = this.pagerAdapterObserver) != null) {
            pagerAdapter2.unregisterDataSetObserver(dataSetObserver);
        }
        this.pagerAdapter = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter.registerDataSetObserver(this.pagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    void populateFromPagerAdapter() {
        int currentItem;
        removeAllTabs();
        PagerAdapter pagerAdapter = this.pagerAdapter;
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            for (int i = 0; i < count; i++) {
                PagerAdapter pagerAdapter2 = this.pagerAdapter;
                if (pagerAdapter2 instanceof TabAdapter) {
                    addTab(((TabAdapter) pagerAdapter2).getTab(i).setTitle(this.pagerAdapter.getPageTitle(i)), false);
                } else {
                    addTab(newTab().setTitle(this.pagerAdapter.getPageTitle(i)), false);
                }
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            selectTab(getTabAt(currentItem));
        }
    }

    private void updateAllTabs() {
        int size = this.tabs.size();
        for (int i = 0; i < size; i++) {
            this.tabs.get(i).setTitleColor(this.tabTextColors);
            this.tabs.get(i).setTabIconTint(this.tabIconTint);
            this.tabs.get(i).setTabTextSize(this.tabTextSize);
        }
    }

    private TabView createTabView() {
        Pools.Pool<TabView> pool = this.tabViewPool;
        TabView acquire = pool != null ? pool.acquire() : null;
        if (acquire == null) {
            acquire = new TabView(getContext());
        }
        acquire.getView().setFocusable(true);
        acquire.getView().setMinimumWidth(getTabMinWidth());
        return acquire;
    }

    private void configureTab(Tab tab, int i) {
        tab.setPosition(i);
        if (tab.getTitleColor() == null) {
            tab.setTitleColor(this.tabTextColors);
        }
        if (tab.getTabIconTint() == null) {
            tab.setTabIconTint(this.tabIconTint);
        }
        if (tab.getTabIconTintMode() == null) {
            tab.setTabIconTintMode(this.tabIconTintMode);
        }
        if (tab.getTabTextSize() == 0.0f) {
            tab.setTabTextSize(this.tabTextSize);
        }
        if (tab.getTabBackgroundResId() == 0) {
            tab.setTabBackgroundResId(this.tabBackgroundResId);
        }
        if (tab.getTabRippleColorStateList() == null) {
            tab.setTabRippleColorStateList(this.tabRippleColorStateList);
        }
        tab.setInlineLabel(this.inlineLabel);
        tab.setTabTextBold(this.tabTextBold);
        tab.setTabPadding(tab.getTabPaddingStart() > 0 ? tab.getTabPaddingStart() : this.tabPaddingStart, tab.getTabPaddingTop() > 0 ? tab.getTabPaddingStart() : this.tabPaddingTop, tab.getTabPaddingEnd() > 0 ? tab.getTabPaddingStart() : this.tabPaddingEnd, tab.getTabPaddingBottom() > 0 ? tab.getTabPaddingStart() : this.tabPaddingBottom);
        this.tabs.add(i, tab);
        int size = this.tabs.size();
        while (true) {
            i++;
            if (i >= size) {
                return;
            }
            this.tabs.get(i).setPosition(i);
        }
    }

    private void addTabView(Tab tab) {
        this.slidingTabIndicator.addView(tab.getView(), tab.getPosition(), createLayoutParamsForTabs(tab.getPosition()));
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        addViewInternal(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i) {
        addViewInternal(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    private void addViewInternal(View view) {
        if (view instanceof Tab) {
            addTab((Tab) view);
            return;
        }
        throw new IllegalArgumentException("Only Tab instances can be added to TabLayout");
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs(int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        updateTabViewLayoutParams(layoutParams, i > 0 ? this.tabDividerWidth : 0);
        return layoutParams;
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams layoutParams, int i) {
        layoutParams.leftMargin = i;
        if (this.mode == 1 && this.tabGravity == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    @Dimension(unit = 1)
    int dpToPx(@Dimension(unit = 0) int i) {
        return Math.round(getResources().getDisplayMetrics().density * i);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
            View childAt = this.slidingTabIndicator.getChildAt(i);
            if (childAt instanceof Tab) {
                ((Tab) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int dpToPx = dpToPx(getDefaultHeight()) + getPaddingTop() + getPaddingBottom();
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(dpToPx, View.MeasureSpec.getSize(i2)), 1073741824);
        } else if (mode == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(dpToPx, 1073741824);
        }
        int size = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i) != 0) {
            int i3 = this.requestedTabMaxWidth;
            if (i3 <= 0) {
                i3 = size - dpToPx(56);
            }
            this.tabMaxWidth = i3;
        }
        super.onMeasure(i, i2);
        if (getChildCount() == 1) {
            boolean z = false;
            View childAt = getChildAt(0);
            switch (this.mode) {
                case 0:
                    if (childAt.getMeasuredWidth() < getMeasuredWidth()) {
                        z = true;
                        break;
                    }
                    break;
                case 1:
                    if (childAt.getMeasuredWidth() != getMeasuredWidth()) {
                        z = true;
                        break;
                    }
                    break;
            }
            if (z) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
            }
        }
    }

    private void removeTabViewAt(int i) {
        View childAt = this.slidingTabIndicator.getChildAt(i);
        this.slidingTabIndicator.removeViewAt(i);
        if (childAt instanceof Tab) {
            ((Tab) childAt).reset();
        }
        requestLayout();
    }

    private void animateToTab(int i) {
        if (i != -1) {
            if (getWindowToken() != null && ViewCompat.isLaidOut(this) && !this.slidingTabIndicator.childrenNeedLayout()) {
                int scrollX = getScrollX();
                int calculateScrollXForTab = calculateScrollXForTab(i, 0.0f);
                if (scrollX != calculateScrollXForTab) {
                    ensureScrollAnimator();
                    this.scrollAnimator.setIntValues(scrollX, calculateScrollXForTab);
                    this.scrollAnimator.start();
                }
                this.slidingTabIndicator.animateIndicatorToPosition(i, this.tabIndicatorAnimationDuration);
                return;
            }
            setScrollPosition(i, 0.0f, true);
        }
    }

    private void ensureScrollAnimator() {
        if (this.scrollAnimator == null) {
            this.scrollAnimator = new ValueAnimator();
            this.scrollAnimator.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.scrollAnimator.setDuration(this.tabIndicatorAnimationDuration);
            this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.liang.widget.JTabLayout.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    JTabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
                }
            });
        }
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        ensureScrollAnimator();
        this.scrollAnimator.addListener(animatorListener);
    }

    private void setSelectedTabView(int i) {
        setSelectedTabView(i, false);
    }

    private void setSelectedTabView(int i, boolean z) {
        int i2 = this.selectedPosition;
        if (i2 == i) {
            return;
        }
        final View childAt = this.slidingTabIndicator.getChildAt(i2);
        final View childAt2 = this.slidingTabIndicator.getChildAt(i);
        this.selectedPosition = i;
        if (z) {
            updateTab(childAt, childAt2, false, this.tabColorTransitionScroll);
            return;
        }
        final boolean z2 = this.tabScaleTransitionScroll > 1.0f;
        if (!z2 && !this.tabColorTransitionScroll) {
            updateTab(childAt, childAt2, false, false);
            return;
        }
        ValueAnimator valueAnimator = this.scaleAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.scaleAnimator.cancel();
        }
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat("selectedScale", this.tabScaleTransitionScroll, 1.0f), PropertyValuesHolder.ofFloat("newScale", 1.0f, this.tabScaleTransitionScroll), PropertyValuesHolder.ofFloat("selectedColor", 1.0f, 0.0f), PropertyValuesHolder.ofFloat("newColor", 0.0f, 1.0f));
        this.scaleAnimator = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setDuration(200L);
        ofPropertyValuesHolder.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
        ofPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.liang.widget.JTabLayout.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                float floatValue = ((Float) valueAnimator2.getAnimatedValue("selectedScale")).floatValue();
                float floatValue2 = ((Float) valueAnimator2.getAnimatedValue("newScale")).floatValue();
                float floatValue3 = ((Float) valueAnimator2.getAnimatedValue("selectedColor")).floatValue();
                float floatValue4 = ((Float) valueAnimator2.getAnimatedValue("newColor")).floatValue();
                View view = childAt;
                if (view instanceof Tab) {
                    if (z2) {
                        ((Tab) view).updateScale(floatValue);
                    }
                    if (JTabLayout.this.tabColorTransitionScroll) {
                        ((Tab) childAt).updateColor(floatValue3);
                    }
                }
                View view2 = childAt2;
                if (view2 instanceof Tab) {
                    if (z2) {
                        ((Tab) view2).updateScale(floatValue2);
                    }
                    if (JTabLayout.this.tabColorTransitionScroll) {
                        ((Tab) childAt2).updateColor(floatValue4);
                    }
                }
            }
        });
        ofPropertyValuesHolder.addListener(new Animator.AnimatorListener() { // from class: com.liang.widget.JTabLayout.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                JTabLayout jTabLayout = JTabLayout.this;
                jTabLayout.updateTab(childAt, childAt2, z2, jTabLayout.tabColorTransitionScroll);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                JTabLayout jTabLayout = JTabLayout.this;
                jTabLayout.updateTab(childAt, childAt2, z2, jTabLayout.tabColorTransitionScroll);
            }
        });
        ofPropertyValuesHolder.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTab(View view, View view2, boolean z, boolean z2) {
        if (view != null) {
            view.setSelected(false);
            view.setActivated(false);
        }
        if (view2 != null) {
            view2.setSelected(true);
            view2.setActivated(true);
        }
        if (view instanceof Tab) {
            if (z) {
                ((Tab) view).updateScale(1.0f);
            }
            if (z2) {
                ((Tab) view).updateColor(0.0f);
            }
        }
        if (view2 instanceof Tab) {
            if (z) {
                ((Tab) view2).updateScale(this.tabScaleTransitionScroll);
            }
            if (z2) {
                ((Tab) view2).updateColor(1.0f);
            }
        }
    }

    public void selectTab(int i) {
        selectTab(i, true);
    }

    public void selectTab(int i, boolean z) {
        if (i < this.tabs.size()) {
            selectTab(this.tabs.get(i), z);
        }
    }

    public void selectTab(int i, boolean z, boolean z2) {
        if (i < this.tabs.size()) {
            selectTab(this.tabs.get(i), z, z2);
        }
    }

    public void selectTab(Tab tab) {
        selectTab(tab, true);
    }

    public void selectTab(Tab tab, boolean z) {
        selectTab(tab, true, z);
    }

    public void selectTab(Tab tab, boolean z, boolean z2) {
        Tab tab2 = this.selectedTab;
        if (tab2 == tab) {
            if (tab2 != null) {
                dispatchTabReselected(tab);
                animateToTab(tab.getPosition());
                return;
            }
            return;
        }
        int position = tab != null ? tab.getPosition() : -1;
        if (z) {
            if ((tab2 == null || tab2.getPosition() == -1) && position != -1) {
                setScrollPosition(position, 0.0f, true);
            } else {
                animateToTab(position);
            }
            if (position != -1) {
                setSelectedTabView(position);
                updateScaleOrColorPosition(position, 1.0f);
            }
        }
        this.selectedTab = tab;
        if (tab2 != null && z2) {
            dispatchTabUnselected(tab2);
        }
        if (tab == null || !z2) {
            return;
        }
        dispatchTabSelected(tab);
    }

    private void dispatchTabSelected(@NonNull Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabSelected(tab);
        }
    }

    private void dispatchTabUnselected(@NonNull Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabUnselected(tab);
        }
    }

    private void dispatchTabReselected(@NonNull Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabReselected(tab);
        }
    }

    private int calculateScrollXForTab(int i, float f) {
        if (this.mode == 0) {
            View childAt = this.slidingTabIndicator.getChildAt(i);
            int i2 = i + 1;
            View childAt2 = i2 < this.slidingTabIndicator.getChildCount() ? this.slidingTabIndicator.getChildAt(i2) : null;
            int width = childAt != null ? childAt.getWidth() : 0;
            int width2 = childAt2 != null ? childAt2.getWidth() : 0;
            int left = childAt.getLeft();
            int i3 = this.tabDividerWidth;
            int width3 = ((left - (i3 / 2)) + ((i3 + width) / 2)) - (getWidth() / 2);
            int i4 = (int) ((width + width2 + (this.tabDividerWidth * 2)) * 0.5f * f);
            return ViewCompat.getLayoutDirection(this) == 0 ? width3 + i4 : width3 - i4;
        }
        return 0;
    }

    private void applyModeAndGravity() {
        ViewCompat.setPaddingRelative(this.slidingTabIndicator, this.mode == 0 ? Math.max(0, this.contentInsetStart - this.tabPaddingStart) : 0, 0, 0, 0);
        switch (this.mode) {
            case 0:
                this.slidingTabIndicator.setGravity(8388611);
                break;
            case 1:
                this.slidingTabIndicator.setGravity(1);
                break;
        }
        updateTabViews(true);
    }

    void updateTabViews(boolean z) {
        int i = 0;
        while (i < this.slidingTabIndicator.getChildCount()) {
            View childAt = this.slidingTabIndicator.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) childAt.getLayoutParams(), i > 0 ? this.tabDividerWidth : 0);
            if (z) {
                childAt.requestLayout();
            }
            i++;
        }
    }

    @Dimension(unit = 0)
    private int getDefaultHeight() {
        int size = this.tabs.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i < size) {
                Tab tab = this.tabs.get(i);
                if (tab != null && tab.getNormalIcon() != null && !TextUtils.isEmpty(tab.getTitle())) {
                    z = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return (!z || this.inlineLabel) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i = this.requestedTabMinWidth;
        if (i != -1) {
            return i;
        }
        if (this.mode == 0) {
            return this.scrollableTabMinWidth;
        }
        return 0;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
        private boolean autoRefresh;

        AdapterChangeListener() {
        }

        @Override // android.support.p083v4.view.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2) {
            if (JTabLayout.this.viewPager == viewPager) {
                JTabLayout.this.setPagerAdapter(pagerAdapter2, this.autoRefresh);
            }
        }

        void setAutoRefresh(boolean z) {
            this.autoRefresh = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class PagerAdapterObserver extends DataSetObserver {
        PagerAdapterObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            JTabLayout.this.populateFromPagerAdapter();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            JTabLayout.this.populateFromPagerAdapter();
        }
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager viewPager;

        @Override // com.liang.widget.JTabLayout.BaseOnTabSelectedListener
        public void onTabReselected(Tab tab) {
        }

        @Override // com.liang.widget.JTabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(Tab tab) {
        }

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override // com.liang.widget.JTabLayout.BaseOnTabSelectedListener
        public void onTabSelected(Tab tab) {
            this.viewPager.setCurrentItem(tab.getPosition());
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<JTabLayout> tabLayoutRef;

        public TabLayoutOnPageChangeListener(JTabLayout jTabLayout) {
            this.tabLayoutRef = new WeakReference<>(jTabLayout);
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.previousScrollState = this.scrollState;
            this.scrollState = i;
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            JTabLayout jTabLayout = this.tabLayoutRef.get();
            if (jTabLayout != null) {
                boolean z = false;
                boolean z2 = this.scrollState != 2 || this.previousScrollState == 1;
                if (this.scrollState != 2 || this.previousScrollState != 0) {
                    z = true;
                }
                jTabLayout.setScrollPosition(i, f, z2, z);
            }
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            NBSActionInstrumentation.onPageSelectedEnter(i, this);
            JTabLayout jTabLayout = this.tabLayoutRef.get();
            if (jTabLayout != null && jTabLayout.getSelectedTabPosition() != i && i < jTabLayout.getTabCount()) {
                int i2 = this.scrollState;
                jTabLayout.selectTab(jTabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.previousScrollState == 0), true);
            }
            NBSActionInstrumentation.onPageSelectedExit();
        }

        void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class SlidingTabIndicator extends LinearLayout {
        private final GradientDrawable defaultSelectionIndicator;
        private Paint dividerPaint;
        private ValueAnimator indicatorAnimator;
        private IndicatorPoint indicatorPoint;
        private int layoutDirection;
        private int selectedIndicatorHeight;
        private final Paint selectedIndicatorPaint;
        int selectedPosition;
        float selectionOffset;

        SlidingTabIndicator(Context context) {
            super(context);
            this.selectedPosition = -1;
            this.layoutDirection = -1;
            this.indicatorPoint = new IndicatorPoint();
            setWillNotDraw(false);
            this.selectedIndicatorPaint = new Paint();
            this.defaultSelectionIndicator = new GradientDrawable();
            this.dividerPaint = new Paint();
        }

        void setSelectedIndicatorColor(int i) {
            if (this.selectedIndicatorPaint.getColor() != i) {
                this.selectedIndicatorPaint.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setSelectedIndicatorHeight(int i) {
            if (this.selectedIndicatorHeight != i) {
                this.selectedIndicatorHeight = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        boolean childrenNeedLayout() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void setIndicatorPositionFromTabPosition(int i, float f) {
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            this.selectedPosition = i;
            this.selectionOffset = f;
            updateIndicatorPosition();
        }

        float getIndicatorPosition() {
            return this.selectedPosition + this.selectionOffset;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
            if (Build.VERSION.SDK_INT >= 23 || this.layoutDirection == i) {
                return;
            }
            requestLayout();
            this.layoutDirection = i;
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) == 1073741824) {
                boolean z = true;
                if (JTabLayout.this.mode == 1 && JTabLayout.this.tabGravity == 1) {
                    int childCount = getChildCount();
                    int i3 = 0;
                    for (int i4 = 0; i4 < childCount; i4++) {
                        View childAt = getChildAt(i4);
                        if (childAt.getVisibility() == 0) {
                            i3 = Math.max(i3, childAt.getMeasuredWidth());
                        }
                    }
                    if (i3 <= 0) {
                        return;
                    }
                    if (i3 * childCount > getMeasuredWidth() - (JTabLayout.this.dpToPx(16) * 2)) {
                        JTabLayout jTabLayout = JTabLayout.this;
                        jTabLayout.tabGravity = 0;
                        jTabLayout.updateTabViews(false);
                    } else {
                        boolean z2 = false;
                        for (int i5 = 0; i5 < childCount; i5++) {
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
                            if (layoutParams.width != i3 || layoutParams.weight != 0.0f) {
                                layoutParams.width = i3;
                                layoutParams.weight = 0.0f;
                                z2 = true;
                            }
                        }
                        z = z2;
                    }
                    if (z) {
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
                animateIndicatorToPosition(this.selectedPosition, Math.round((1.0f - this.indicatorAnimator.getAnimatedFraction()) * ((float) this.indicatorAnimator.getDuration())));
                return;
            }
            updateIndicatorPosition();
        }

        private void updateIndicatorPosition() {
            int i;
            float f;
            float f2;
            View childAt = getChildAt(this.selectedPosition);
            int i2 = -1;
            if (childAt == null || childAt.getWidth() <= 0) {
                i = -1;
            } else {
                i2 = childAt.getLeft();
                int right = childAt.getRight();
                if (!JTabLayout.this.tabIndicatorFullWidth && (childAt instanceof Tab)) {
                    calculateTabViewContentBounds((Tab) childAt, JTabLayout.this.tabViewContentBounds);
                    right = (int) JTabLayout.this.tabViewContentBounds.right;
                    i2 = (int) JTabLayout.this.tabViewContentBounds.left;
                }
                if (this.selectionOffset <= 0.0f || this.selectedPosition >= getChildCount() - 1) {
                    i = right;
                } else {
                    View childAt2 = getChildAt(this.selectedPosition + 1);
                    int left = childAt2.getLeft();
                    int right2 = childAt2.getRight();
                    if (!JTabLayout.this.tabIndicatorFullWidth && (childAt2 instanceof Tab)) {
                        calculateTabViewContentBounds((Tab) childAt2, JTabLayout.this.tabViewContentBounds);
                        left = (int) JTabLayout.this.tabViewContentBounds.left;
                        right2 = (int) JTabLayout.this.tabViewContentBounds.right;
                    }
                    if (JTabLayout.this.tabIndicatorTransitionScroll) {
                        float f3 = this.selectionOffset;
                        float f4 = (f3 * 2.0f) - 1.0f;
                        float f5 = f3 * 2.0f;
                        int i3 = this.selectedPosition;
                        if (i3 + 1 >= i3 || f3 <= 0.0f) {
                            float f6 = this.selectionOffset;
                            f = (f6 * 2.0f) - 1.0f;
                            f2 = f6 * 2.0f;
                            if (f < 0.0f) {
                                f = 0.0f;
                            }
                            if (1.0f - f2 < 0.0f) {
                                f2 = 1.0f;
                            }
                        } else {
                            if (f4 < 0.0f) {
                                f4 = 0.0f;
                            }
                            if (1.0f - f5 < 0.0f) {
                                f2 = f4;
                                f = 1.0f;
                            } else {
                                f2 = f4;
                                f = f5;
                            }
                        }
                        i2 = (int) (i2 + ((left - i2) * f));
                        i = (int) (right + ((right2 - right) * f2));
                    } else {
                        float f7 = this.selectionOffset;
                        i2 = (int) ((left * f7) + ((1.0f - f7) * i2));
                        i = (int) ((right2 * f7) + ((1.0f - f7) * right));
                    }
                }
            }
            setIndicatorPosition(i2, i);
        }

        void setIndicatorPosition(float f, float f2) {
            if (f == this.indicatorPoint.left && f2 == this.indicatorPoint.right) {
                return;
            }
            IndicatorPoint indicatorPoint = this.indicatorPoint;
            indicatorPoint.left = f;
            indicatorPoint.right = f2;
            ViewCompat.postInvalidateOnAnimation(this);
        }

        void animateIndicatorToPosition(final int i, int i2) {
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            View childAt = getChildAt(i);
            if (childAt == null) {
                updateIndicatorPosition();
                return;
            }
            IndicatorPoint indicatorPoint = new IndicatorPoint();
            indicatorPoint.left = childAt.getLeft();
            indicatorPoint.right = childAt.getRight();
            if (!JTabLayout.this.tabIndicatorFullWidth && (childAt instanceof Tab)) {
                calculateTabViewContentBounds((Tab) childAt, JTabLayout.this.tabViewContentBounds);
                indicatorPoint.left = (int) JTabLayout.this.tabViewContentBounds.left;
                indicatorPoint.right = (int) JTabLayout.this.tabViewContentBounds.right;
            }
            if (this.indicatorPoint.equals(indicatorPoint)) {
                return;
            }
            ValueAnimator ofObject = ValueAnimator.ofObject(JTabLayout.this.tabIndicatorTransitionScroll ? new TransitionIndicatorEvaluator() : new DefIndicatorEvaluator(), this.indicatorPoint, indicatorPoint);
            this.indicatorAnimator = ofObject;
            ofObject.setInterpolator(JTabLayout.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ofObject.setDuration(i2);
            ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.liang.widget.JTabLayout.SlidingTabIndicator.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    IndicatorPoint indicatorPoint2 = (IndicatorPoint) valueAnimator2.getAnimatedValue();
                    SlidingTabIndicator.this.setIndicatorPosition(indicatorPoint2.left, indicatorPoint2.right);
                }
            });
            ofObject.addListener(new AnimatorListenerAdapter() { // from class: com.liang.widget.JTabLayout.SlidingTabIndicator.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SlidingTabIndicator slidingTabIndicator = SlidingTabIndicator.this;
                    slidingTabIndicator.selectedPosition = i;
                    slidingTabIndicator.selectionOffset = 0.0f;
                }
            });
            ofObject.start();
        }

        private void calculateTabViewContentBounds(Tab tab, RectF rectF) {
            if (JTabLayout.this.tabIndicatorWidth > 0 || JTabLayout.this.tabIndicatorWidthScale > 0.0f) {
                int left = tab.getView().getLeft();
                int right = tab.getView().getRight() - left;
                JTabLayout jTabLayout = JTabLayout.this;
                jTabLayout.tabIndicatorWidth = Math.min(right, jTabLayout.tabIndicatorWidth > 0 ? JTabLayout.this.tabIndicatorWidth : (int) (right * JTabLayout.this.tabIndicatorWidthScale));
                int max = left + (Math.max(0, right - JTabLayout.this.tabIndicatorWidth) / 2);
                rectF.set(max, 0.0f, JTabLayout.this.tabIndicatorWidth + max, 0.0f);
                return;
            }
            int contentWidth = tab.getContentWidth();
            if (contentWidth < JTabLayout.this.dpToPx(24)) {
                contentWidth = JTabLayout.this.dpToPx(24);
            }
            int left2 = (tab.getView().getLeft() + tab.getView().getRight()) / 2;
            int i = contentWidth / 2;
            rectF.set(left2 - i, 0.0f, left2 + i, 0.0f);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            drawDivider(canvas);
            if (JTabLayout.this.tabIndicatorTier == 0) {
                drawIndicator(canvas);
                super.draw(canvas);
                return;
            }
            super.draw(canvas);
            drawIndicator(canvas);
        }

        private void drawDivider(Canvas canvas) {
            if (JTabLayout.this.tabDividerWidth <= 0 || getChildCount() <= 0) {
                return;
            }
            this.dividerPaint.setStrokeWidth(JTabLayout.this.tabDividerWidth);
            this.dividerPaint.setColor(JTabLayout.this.tabDividerColor);
            JTabLayout jTabLayout = JTabLayout.this;
            jTabLayout.tabDividerHeight = jTabLayout.tabDividerHeight > -1 ? JTabLayout.this.tabDividerHeight : getHeight();
            for (int i = 0; i < getChildCount() - 1; i++) {
                View childAt = getChildAt(i);
                canvas.drawRect(childAt.getRight(), (getHeight() - JTabLayout.this.tabDividerHeight) / 2, childAt.getRight() + JTabLayout.this.tabDividerWidth, ((getHeight() - JTabLayout.this.tabDividerHeight) / 2) + JTabLayout.this.tabDividerHeight, this.dividerPaint);
            }
        }

        private void drawIndicator(Canvas canvas) {
            int i = 0;
            int intrinsicHeight = JTabLayout.this.tabSelectedIndicator != null ? JTabLayout.this.tabSelectedIndicator.getIntrinsicHeight() : 0;
            int i2 = this.selectedIndicatorHeight;
            if (i2 >= 0) {
                intrinsicHeight = i2;
            }
            switch (JTabLayout.this.tabIndicatorGravity) {
                case 0:
                    i = (getHeight() - intrinsicHeight) - JTabLayout.this.tabIndicatorMargin;
                    intrinsicHeight = getHeight() - JTabLayout.this.tabIndicatorMargin;
                    break;
                case 1:
                    i = (getHeight() - intrinsicHeight) / 2;
                    intrinsicHeight = (getHeight() + intrinsicHeight) / 2;
                    break;
                case 2:
                    i = JTabLayout.this.tabIndicatorMargin;
                    break;
                case 3:
                    i = JTabLayout.this.tabIndicatorMargin;
                    intrinsicHeight = getHeight() - JTabLayout.this.tabIndicatorMargin;
                    break;
                default:
                    intrinsicHeight = 0;
                    break;
            }
            if (this.indicatorPoint.left < 0.0f || this.indicatorPoint.right <= this.indicatorPoint.left) {
                return;
            }
            Drawable wrap = DrawableCompat.wrap(JTabLayout.this.tabSelectedIndicator != null ? JTabLayout.this.tabSelectedIndicator : this.defaultSelectionIndicator);
            wrap.setBounds((int) this.indicatorPoint.left, i, (int) this.indicatorPoint.right, intrinsicHeight);
            Paint paint = this.selectedIndicatorPaint;
            if (paint != null && paint.getColor() != 0) {
                if (Build.VERSION.SDK_INT == 21) {
                    wrap.setColorFilter(this.selectedIndicatorPaint.getColor(), PorterDuff.Mode.SRC_IN);
                } else {
                    DrawableCompat.setTint(wrap, this.selectedIndicatorPaint.getColor());
                }
            }
            wrap.draw(canvas);
        }
    }
}
