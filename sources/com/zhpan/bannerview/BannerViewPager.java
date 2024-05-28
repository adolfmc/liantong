package com.zhpan.bannerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.zhpan.bannerview.adapter.BannerPagerAdapter;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.indicator.DashIndicatorView;
import com.zhpan.bannerview.indicator.IIndicator;
import com.zhpan.bannerview.indicator.IndicatorFactory;
import com.zhpan.bannerview.provider.ViewStyleSetter;
import com.zhpan.bannerview.transform.PageTransformerFactory;
import com.zhpan.bannerview.transform.pagestyle.ScaleInTransformer;
import com.zhpan.bannerview.utils.BannerUtils;
import com.zhpan.bannerview.utils.PositionUtils;
import com.zhpan.bannerview.view.CatchViewPager;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BannerViewPager<T, VH extends ViewHolder> extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private int checkedIndicatorWidth;
    private int currentPosition;
    private boolean disableTouchScroll;
    private HolderCreator<VH> holderCreator;
    private int indicatorCheckedColor;
    private int indicatorGap;
    private int indicatorGravity;
    private int indicatorHeight;
    private int indicatorNormalColor;
    private int interval;
    private boolean isAutoPlay;
    private boolean isCanLoop;
    private boolean isCustomIndicator;
    private boolean isLooping;
    private Handler mHandler;
    private RelativeLayout mIndicatorLayout;
    private IndicatorMargin mIndicatorMargin;
    private int mIndicatorSlideMode;
    private int mIndicatorStyle;
    private IIndicator mIndicatorView;
    private int mIndicatorVisibility;
    private List<T> mList;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private OnPageClickListener mOnPageClickListener;
    private int mPageMargin;
    private int mPageStyle;
    private int mRevealWidth;
    private int mRoundCorner;
    private Runnable mRunnable;
    private int mScrollDuration;
    private CatchViewPager mViewPager;
    private int normalIndicatorWidth;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface OnPageClickListener {
        void onPageClick(int i);
    }

    public BannerViewPager(Context context) {
        this(context, null);
    }

    public BannerViewPager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerViewPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isAutoPlay = false;
        this.mPageStyle = 0;
        this.mHandler = new Handler();
        this.mRunnable = new Runnable() { // from class: com.zhpan.bannerview.BannerViewPager.1
            @Override // java.lang.Runnable
            public void run() {
                if (BannerViewPager.this.mList.size() > 1) {
                    BannerViewPager bannerViewPager = BannerViewPager.this;
                    bannerViewPager.currentPosition = bannerViewPager.mViewPager.getCurrentItem() + 1;
                    if (BannerViewPager.this.isCanLoop) {
                        if (BannerViewPager.this.currentPosition == 2147483646) {
                            BannerViewPager.this.currentPosition = 0;
                            BannerViewPager.this.mViewPager.setCurrentItem(BannerViewPager.this.currentPosition, false);
                            BannerViewPager.this.mHandler.post(BannerViewPager.this.mRunnable);
                            return;
                        }
                        BannerViewPager.this.mViewPager.setCurrentItem(BannerViewPager.this.currentPosition);
                        BannerViewPager.this.mHandler.postDelayed(BannerViewPager.this.mRunnable, BannerViewPager.this.interval);
                    } else if (BannerViewPager.this.currentPosition < Integer.MAX_VALUE) {
                        BannerViewPager.this.mViewPager.setCurrentItem(BannerViewPager.this.currentPosition);
                        BannerViewPager.this.mHandler.postDelayed(BannerViewPager.this.mRunnable, BannerViewPager.this.interval);
                    } else {
                        BannerViewPager.this.stopLoop();
                    }
                }
            }
        };
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        initAttrs(attributeSet);
        initView();
    }

    private void initView() {
        inflate(getContext(), C11706R.C11710layout.layout_banner_view_pager, this);
        this.mViewPager = (CatchViewPager) findViewById(C11706R.C11709id.vp_main);
        this.mIndicatorLayout = (RelativeLayout) findViewById(C11706R.C11709id.rl_indicator);
        this.mList = new ArrayList();
    }

    private void initAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C11706R.styleable.BannerViewPager);
            this.interval = obtainStyledAttributes.getInteger(C11706R.styleable.BannerViewPager_bvp_interval, 3000);
            this.indicatorCheckedColor = obtainStyledAttributes.getColor(C11706R.styleable.BannerViewPager_bvp_indicator_checked_color, Color.parseColor("#8C18171C"));
            this.indicatorNormalColor = obtainStyledAttributes.getColor(C11706R.styleable.BannerViewPager_bvp_indicator_normal_color, Color.parseColor("#8C6C6D72"));
            this.normalIndicatorWidth = (int) obtainStyledAttributes.getDimension(C11706R.styleable.BannerViewPager_bvp_indicator_radius, BannerUtils.dp2px(8.0f));
            this.isAutoPlay = obtainStyledAttributes.getBoolean(C11706R.styleable.BannerViewPager_bvp_auto_play, true);
            this.isCanLoop = obtainStyledAttributes.getBoolean(C11706R.styleable.BannerViewPager_bvp_can_loop, true);
            this.mPageMargin = (int) obtainStyledAttributes.getDimension(C11706R.styleable.BannerViewPager_bvp_page_margin, 0.0f);
            this.mRoundCorner = (int) obtainStyledAttributes.getDimension(C11706R.styleable.BannerViewPager_bvp_round_corner, 0.0f);
            this.mRevealWidth = (int) obtainStyledAttributes.getDimension(C11706R.styleable.BannerViewPager_bvp_reveal_width, 0.0f);
            this.indicatorGravity = obtainStyledAttributes.getInt(C11706R.styleable.BannerViewPager_bvp_indicator_gravity, 0);
            this.mPageStyle = obtainStyledAttributes.getInt(C11706R.styleable.BannerViewPager_bvp_page_style, 0);
            this.mIndicatorStyle = obtainStyledAttributes.getInt(C11706R.styleable.BannerViewPager_bvp_indicator_style, 0);
            this.mIndicatorSlideMode = obtainStyledAttributes.getInt(C11706R.styleable.BannerViewPager_bvp_indicator_slide_mode, 0);
            this.mIndicatorVisibility = obtainStyledAttributes.getInt(C11706R.styleable.BannerViewPager_bvp_indicator_visibility, 0);
            this.mScrollDuration = obtainStyledAttributes.getInt(C11706R.styleable.BannerViewPager_bvp_scroll_duration, 800);
            obtainStyledAttributes.recycle();
            int i = this.normalIndicatorWidth;
            this.indicatorGap = i;
            this.indicatorHeight = i / 2;
            this.checkedIndicatorWidth = i;
        }
    }

    private void initBannerData(List<T> list) {
        IIndicator iIndicator;
        if (list != null) {
            this.mList.clear();
            this.mList.addAll(list);
            if (this.mList.size() > 0) {
                if (this.mList.size() > 1) {
                    if (this.isCustomIndicator && (iIndicator = this.mIndicatorView) != null) {
                        initIndicator(iIndicator);
                    } else {
                        initIndicator(IndicatorFactory.createIndicatorView(getContext(), this.mIndicatorStyle));
                    }
                }
                if (this.isCanLoop) {
                    this.currentPosition = (1073741823 - (1073741823 % this.mList.size())) + 1;
                }
                setupViewPager();
                setIndicatorValues();
            }
        }
    }

    private void setIndicatorValues() {
        IIndicator iIndicator = this.mIndicatorView;
        if (iIndicator != null) {
            iIndicator.setPageSize(this.mList.size());
            this.mIndicatorView.setCheckedColor(this.indicatorCheckedColor);
            this.mIndicatorView.setNormalColor(this.indicatorNormalColor);
            this.mIndicatorView.setIndicatorGap(this.indicatorGap);
            this.mIndicatorView.setSlideMode(this.mIndicatorSlideMode);
            this.mIndicatorView.setIndicatorWidth(this.normalIndicatorWidth, this.checkedIndicatorWidth);
            IIndicator iIndicator2 = this.mIndicatorView;
            if (iIndicator2 instanceof DashIndicatorView) {
                ((DashIndicatorView) iIndicator2).setSliderHeight(this.indicatorHeight);
            }
            this.mIndicatorView.notifyDataChanged();
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void setTouchListener() {
        this.mViewPager.setOnTouchListener(new View.OnTouchListener() { // from class: com.zhpan.bannerview.-$$Lambda$BannerViewPager$QTwWAx8jNM0cumL-t8j3MziM-eo
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return BannerViewPager.lambda$setTouchListener$0(BannerViewPager.this, view, motionEvent);
            }
        });
    }

    public static /* synthetic */ boolean lambda$setTouchListener$0(BannerViewPager bannerViewPager, View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
            case 2:
                bannerViewPager.isLooping = true;
                bannerViewPager.stopLoop();
                break;
            case 1:
            case 3:
                bannerViewPager.isLooping = false;
                bannerViewPager.startLoop();
                break;
        }
        return false;
    }

    private void initIndicator(IIndicator iIndicator) {
        this.mIndicatorLayout.setVisibility(this.mIndicatorVisibility);
        this.mIndicatorView = iIndicator;
        if (((View) this.mIndicatorView).getParent() == null) {
            this.mIndicatorLayout.removeAllViews();
            this.mIndicatorLayout.addView((View) this.mIndicatorView);
            initIndicatorViewMargin();
            initIndicatorGravity();
        }
    }

    private void initIndicatorGravity() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((View) this.mIndicatorView).getLayoutParams();
        switch (this.indicatorGravity) {
            case 0:
                layoutParams.addRule(14);
                return;
            case 1:
                layoutParams.addRule(20);
                return;
            case 2:
                layoutParams.addRule(21);
                return;
            default:
                return;
        }
    }

    private void initIndicatorViewMargin() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ((View) this.mIndicatorView).getLayoutParams();
        IndicatorMargin indicatorMargin = this.mIndicatorMargin;
        if (indicatorMargin == null) {
            int dp2px = BannerUtils.dp2px(10.0f);
            marginLayoutParams.setMargins(dp2px, dp2px, dp2px, dp2px);
            return;
        }
        marginLayoutParams.setMargins(indicatorMargin.left, this.mIndicatorMargin.top, this.mIndicatorMargin.right, this.mIndicatorMargin.bottom);
    }

    private void setupViewPager() {
        if (this.holderCreator != null) {
            removeAllViews();
            BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(this.mList, this.holderCreator);
            bannerPagerAdapter.setCanLoop(this.isCanLoop);
            bannerPagerAdapter.setPageClickListener(new BannerPagerAdapter.PageClickListener() { // from class: com.zhpan.bannerview.-$$Lambda$BannerViewPager$KGm-BZxuJeI2_5AvKakbplGj0Ac
                @Override // com.zhpan.bannerview.adapter.BannerPagerAdapter.PageClickListener
                public final void onPageClick(int i) {
                    BannerViewPager.lambda$setupViewPager$1(BannerViewPager.this, i);
                }
            });
            this.mViewPager.setAdapter(bannerPagerAdapter);
            this.mViewPager.setCurrentItem(this.currentPosition);
            this.mViewPager.addOnPageChangeListener(this);
            this.mViewPager.setScrollDuration(this.mScrollDuration);
            this.mViewPager.disableTouchScroll(this.disableTouchScroll);
            addView(this.mViewPager);
            addView(this.mIndicatorLayout);
            initPageStyle();
            startLoop();
            setTouchListener();
            if (this.mRoundCorner <= 0 || Build.VERSION.SDK_INT < 21) {
                return;
            }
            new ViewStyleSetter(this).setRoundCorner(this.mRoundCorner);
            return;
        }
        throw new NullPointerException("You must set HolderCreator for BannerViewPager");
    }

    public static /* synthetic */ void lambda$setupViewPager$1(BannerViewPager bannerViewPager, int i) {
        OnPageClickListener onPageClickListener = bannerViewPager.mOnPageClickListener;
        if (onPageClickListener != null) {
            onPageClickListener.onPageClick(i);
        }
    }

    private void initPageStyle() {
        switch (this.mPageStyle) {
            case 1:
                setMultiPageStyle(false, 0.999f);
                return;
            case 2:
                setMultiPageStyle(true, 0.85f);
                return;
            case 3:
                setMultiPageStyle(false, 0.85f);
                return;
            default:
                return;
        }
    }

    private void setMultiPageStyle(boolean z, float f) {
        int i = this.mPageMargin;
        if (i == 0) {
            i = BannerUtils.dp2px(20.0f);
        }
        this.mPageMargin = i;
        int i2 = this.mRevealWidth;
        if (i2 == 0) {
            i2 = BannerUtils.dp2px(20.0f);
        }
        this.mRevealWidth = i2;
        setClipChildren(false);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mViewPager.getLayoutParams();
        int i3 = this.mPageMargin;
        int i4 = this.mRevealWidth;
        marginLayoutParams.leftMargin = i3 + i4;
        marginLayoutParams.rightMargin = i3 + i4;
        this.mViewPager.setOverlapStyle(z);
        this.mViewPager.setPageMargin(z ? -this.mPageMargin : this.mPageMargin);
        this.mViewPager.setOffscreenPageLimit(2);
        setPageTransformer(new ScaleInTransformer(f));
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        this.currentPosition = PositionUtils.getRealPosition(this.isCanLoop, i, this.mList.size());
        ViewPager.OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(this.currentPosition);
        }
        IIndicator iIndicator = this.mIndicatorView;
        if (iIndicator != null) {
            iIndicator.onPageSelected(this.currentPosition);
        }
        NBSActionInstrumentation.onPageSelectedExit();
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        IIndicator iIndicator = this.mIndicatorView;
        if (iIndicator != null) {
            iIndicator.onPageScrollStateChanged(i);
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(PositionUtils.getRealPosition(this.isCanLoop, i, this.mList.size()), f, i2);
        }
        IIndicator iIndicator = this.mIndicatorView;
        if (iIndicator != null) {
            iIndicator.onPageScrolled(PositionUtils.getRealPosition(this.isCanLoop, i, this.mList.size()), f, i2);
        }
    }

    public List<T> getList() {
        return this.mList;
    }

    public void startLoop() {
        if (this.isLooping || !this.isAutoPlay || this.mList.size() <= 1) {
            return;
        }
        this.mHandler.postDelayed(this.mRunnable, this.interval);
        this.isLooping = true;
    }

    public void stopLoop() {
        if (this.isLooping) {
            this.mHandler.removeCallbacks(this.mRunnable);
            this.isLooping = false;
        }
    }

    public BannerViewPager<T, VH> setHolderCreator(HolderCreator<VH> holderCreator) {
        this.holderCreator = holderCreator;
        return this;
    }

    public BannerViewPager<T, VH> setRoundCorner(int i) {
        this.mRoundCorner = i;
        return this;
    }

    public BannerViewPager<T, VH> setAutoPlay(boolean z) {
        this.isAutoPlay = z;
        if (this.isAutoPlay) {
            this.isCanLoop = true;
        }
        return this;
    }

    public BannerViewPager<T, VH> setCanLoop(boolean z) {
        this.isCanLoop = z;
        if (!z) {
            this.isAutoPlay = false;
        }
        return this;
    }

    public BannerViewPager<T, VH> setInterval(int i) {
        this.interval = i;
        return this;
    }

    public BannerViewPager<T, VH> setPageTransformerStyle(int i) {
        this.mViewPager.setPageTransformer(true, new PageTransformerFactory().createPageTransformer(i));
        return this;
    }

    public void setPageTransformer(@Nullable ViewPager.PageTransformer pageTransformer) {
        this.mViewPager.setPageTransformer(true, pageTransformer);
    }

    public BannerViewPager<T, VH> setOnPageClickListener(OnPageClickListener onPageClickListener) {
        this.mOnPageClickListener = onPageClickListener;
        return this;
    }

    public BannerViewPager<T, VH> setScrollDuration(int i) {
        this.mScrollDuration = i;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorColor(@ColorInt int i, @ColorInt int i2) {
        this.indicatorCheckedColor = i2;
        this.indicatorNormalColor = i;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorRadius(int i) {
        int i2 = i * 2;
        this.normalIndicatorWidth = i2;
        this.checkedIndicatorWidth = i2;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorRadius(int i, int i2) {
        this.normalIndicatorWidth = i * 2;
        this.checkedIndicatorWidth = i2 * 2;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorWidth(int i) {
        this.normalIndicatorWidth = i;
        this.checkedIndicatorWidth = i;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorWidth(int i, int i2) {
        this.normalIndicatorWidth = i;
        this.checkedIndicatorWidth = i2;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorHeight(int i) {
        this.indicatorHeight = i;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorGap(int i) {
        this.indicatorGap = i;
        return this;
    }

    @Deprecated
    public BannerViewPager<T, VH> showIndicator(boolean z) {
        this.mIndicatorLayout.setVisibility(z ? 0 : 8);
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorVisibility(int i) {
        this.mIndicatorVisibility = i;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorGravity(int i) {
        this.indicatorGravity = i;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorSlideMode(int i) {
        this.mIndicatorSlideMode = i;
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorView(IIndicator iIndicator) {
        if (iIndicator instanceof View) {
            this.isCustomIndicator = true;
            this.mIndicatorView = iIndicator;
        }
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorStyle(int i) {
        this.mIndicatorStyle = i;
        return this;
    }

    public void create(List<T> list) {
        initBannerData(list);
    }

    public int getCurrentItem() {
        return this.currentPosition;
    }

    public void setCurrentItem(int i) {
        CatchViewPager catchViewPager = this.mViewPager;
        if (this.isCanLoop) {
            i += (1073741823 - (1073741823 % this.mList.size())) + 1;
        }
        catchViewPager.setCurrentItem(i);
    }

    public void setCurrentItem(int i, boolean z) {
        CatchViewPager catchViewPager = this.mViewPager;
        if (this.isCanLoop) {
            i += (1073741823 - (1073741823 % this.mList.size())) + 1;
        }
        catchViewPager.setCurrentItem(i, z);
    }

    public BannerViewPager<T, VH> setPageStyle(int i) {
        this.mPageStyle = i;
        return this;
    }

    public BannerViewPager<T, VH> setPageMargin(int i) {
        this.mPageMargin = i;
        this.mViewPager.setPageMargin(i);
        return this;
    }

    public BannerViewPager<T, VH> setRevealWidth(int i) {
        this.mRevealWidth = i;
        return this;
    }

    @Deprecated
    public ViewPager getViewPager() {
        return this.mViewPager;
    }

    public BannerViewPager<T, VH> setIndicatorMargin(int i, int i2, int i3, int i4) {
        this.mIndicatorMargin = new IndicatorMargin();
        this.mIndicatorMargin.bottom = i4;
        this.mIndicatorMargin.left = i;
        this.mIndicatorMargin.top = i2;
        this.mIndicatorMargin.right = i3;
        return this;
    }

    public BannerViewPager<T, VH> disableTouchScroll(boolean z) {
        this.disableTouchScroll = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class IndicatorMargin {
        private int bottom;
        private int left;
        private int right;
        private int top;

        private IndicatorMargin() {
        }
    }

    public BannerViewPager<T, VH> setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
        return this;
    }
}
