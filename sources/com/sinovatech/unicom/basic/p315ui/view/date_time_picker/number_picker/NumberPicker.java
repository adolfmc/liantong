package com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.p083v4.content.ContextCompat;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.sinovatech.unicom.p318ui.C9718R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NumberPicker extends LinearLayout {
    public static final int ASCENDING = 0;
    public static final int CENTER = 1;
    private static final int DEFAULT_DIVIDER_COLOR = -16777216;
    private static final float DEFAULT_FADING_EDGE_STRENGTH = 0.9f;
    private static final float DEFAULT_LINE_SPACING_MULTIPLIER = 1.0f;
    private static final long DEFAULT_LONG_PRESS_UPDATE_INTERVAL = 300;
    private static final int DEFAULT_MAX_FLING_VELOCITY_COEFFICIENT = 8;
    private static final int DEFAULT_MAX_HEIGHT = 180;
    private static final int DEFAULT_MAX_VALUE = 100;
    private static final int DEFAULT_MIN_VALUE = 1;
    private static final int DEFAULT_MIN_WIDTH = 58;
    private static final int DEFAULT_TEXT_ALIGN = 1;
    private static final int DEFAULT_TEXT_COLOR = -16777216;
    private static final float DEFAULT_TEXT_SIZE = 15.0f;
    private static final int DEFAULT_WHEEL_ITEM_COUNT = 3;
    public static final int DESCENDING = 1;
    public static final int HORIZONTAL = 0;
    public static final int LEFT = 2;
    public static final int RIGHT = 0;
    private static final int SELECTOR_ADJUSTMENT_DURATION_MILLIS = 800;
    public static final int SIDE_LINES = 0;
    private static final int SIZE_UNSPECIFIED = -1;
    private static final int SNAP_SCROLL_DURATION = 300;
    public static final int UNDERLINE = 1;
    private static final int UNSCALED_DEFAULT_DIVIDER_DISTANCE = 48;
    private static final int UNSCALED_DEFAULT_DIVIDER_THICKNESS = 2;
    public static final int VERTICAL = 1;
    private String label;
    private boolean mAccessibilityDescriptionEnabled;
    private final Scroller mAdjustScroller;
    private int mBottomDividerBottom;
    private ChangeCurrentByOneFromLongPressCommand mChangeCurrentByOneFromLongPressCommand;
    private final boolean mComputeMaxWidth;
    private Context mContext;
    private int mCurrentScrollOffset;
    private String[] mDisplayedValues;
    private int mDividerColor;
    private int mDividerDistance;
    private Drawable mDividerDrawable;
    private int mDividerLength;
    private int mDividerThickness;
    private int mDividerType;
    private boolean mFadingEdgeEnabled;
    private float mFadingEdgeStrength;
    private final Scroller mFlingScroller;
    private Formatter mFormatter;
    private boolean mHideWheelUntilFocused;
    private int mInitialScrollOffset;
    private int mItemSpacing;
    private float mLastDownEventX;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventX;
    private float mLastDownOrMoveEventY;
    private int mLastHandledDownDpadKeyCode;
    private int mLeftDividerLeft;
    private float mLineSpacingMultiplier;
    private long mLongPressUpdateInterval;
    private int mMaxFlingVelocityCoefficient;
    private int mMaxHeight;
    private int mMaxValue;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private int mMinHeight;
    private int mMinValue;
    private int mMinWidth;
    private int mMinimumFlingVelocity;
    private NumberFormat mNumberFormatter;
    private View.OnClickListener mOnClickListener;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private int mOrder;
    private int mOrientation;
    private int mPreviousScrollerX;
    private int mPreviousScrollerY;
    private int mRealWheelItemCount;
    private int mRightDividerRight;
    private int mScrollState;
    private boolean mScrollerEnabled;
    private final EditText mSelectedText;
    private int mSelectedTextAlign;
    private float mSelectedTextCenterX;
    private float mSelectedTextCenterY;
    private int mSelectedTextColor;
    private float mSelectedTextSize;
    private boolean mSelectedTextStrikeThru;
    private boolean mSelectedTextUnderline;
    private Typeface mSelectedTypeface;
    private int mSelectorElementSize;
    private final SparseArray<String> mSelectorIndexToStringCache;
    private int[] mSelectorIndices;
    private int mSelectorTextGapHeight;
    private int mSelectorTextGapWidth;
    private final Paint mSelectorWheelPaint;
    private SetSelectionCommand mSetSelectionCommand;
    private int mTextAlign;
    private int mTextColor;
    private float mTextSize;
    private boolean mTextStrikeThru;
    private boolean mTextUnderline;
    private int mTopDividerTop;
    private int mTouchSlop;
    private Typeface mTypeface;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private ViewConfiguration mViewConfiguration;
    private int mWheelItemCount;
    private int mWheelMiddleItemIndex;
    private boolean mWrapSelectorWheel;
    private boolean mWrapSelectorWheelPreferred;
    private boolean selectedTextBold;
    private boolean textBold;
    private static final TwoDigitFormatter sTwoDigitFormatter = new TwoDigitFormatter();
    private static final char[] DIGIT_CHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785, 2406, 2407, 2408, 2409, 2410, 2411, 2412, 2413, 2414, 2415, 2534, 2535, 2536, 2537, 2538, 2539, 2540, 2541, 2542, 2543, 3302, 3303, 3304, 3305, 3306, 3307, 3308, 3309, 3310, 3311, '-'};

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$Align */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface Align {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$DividerType */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface DividerType {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$Formatter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface Formatter {
        String format(int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$OnScrollListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnScrollListener {
        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        @Retention(RetentionPolicy.SOURCE)
        /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$OnScrollListener$ScrollState */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public @interface ScrollState {
        }

        void onScrollStateChange(NumberPicker numberPicker, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$OnValueChangeListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnValueChangeListener {
        void onValueChange(NumberPicker numberPicker, int i, int i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$Order */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface Order {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$Orientation */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface Orientation {
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$TwoDigitFormatter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static class TwoDigitFormatter implements Formatter {
        java.util.Formatter mFmt;
        char mZeroDigit;
        final StringBuilder mBuilder = new StringBuilder();
        final Object[] mArgs = new Object[1];

        TwoDigitFormatter() {
            init(Locale.getDefault());
        }

        private void init(Locale locale) {
            this.mFmt = createFormatter(locale);
            this.mZeroDigit = getZeroDigit(locale);
        }

        @Override // com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker.Formatter
        public String format(int i) {
            Locale locale = Locale.getDefault();
            if (this.mZeroDigit != getZeroDigit(locale)) {
                init(locale);
            }
            this.mArgs[0] = Integer.valueOf(i);
            StringBuilder sb = this.mBuilder;
            sb.delete(0, sb.length());
            this.mFmt.format("%02d", this.mArgs);
            return this.mFmt.toString();
        }

        private static char getZeroDigit(Locale locale) {
            return new DecimalFormatSymbols(locale).getZeroDigit();
        }

        private java.util.Formatter createFormatter(Locale locale) {
            return new java.util.Formatter(this.mBuilder, locale);
        }
    }

    public static Formatter getTwoDigitFormatter() {
        return sTwoDigitFormatter;
    }

    public NumberPicker(Context context) {
        this(context, null);
    }

    public NumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NumberPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.label = "";
        this.textBold = true;
        this.selectedTextBold = true;
        this.mSelectedTextAlign = 1;
        this.mSelectedTextColor = -16777216;
        this.mSelectedTextSize = DEFAULT_TEXT_SIZE;
        this.mTextAlign = 1;
        this.mTextColor = -16777216;
        this.mTextSize = DEFAULT_TEXT_SIZE;
        this.mMinValue = 1;
        this.mMaxValue = 100;
        this.mLongPressUpdateInterval = 300L;
        this.mSelectorIndexToStringCache = new SparseArray<>();
        this.mWheelItemCount = 3;
        this.mRealWheelItemCount = 3;
        int i2 = this.mWheelItemCount;
        this.mWheelMiddleItemIndex = i2 / 2;
        this.mSelectorIndices = new int[i2];
        this.mInitialScrollOffset = Integer.MIN_VALUE;
        this.mWrapSelectorWheelPreferred = true;
        this.mDividerColor = -16777216;
        this.mScrollState = 0;
        this.mLastHandledDownDpadKeyCode = -1;
        this.mFadingEdgeEnabled = true;
        this.mFadingEdgeStrength = DEFAULT_FADING_EDGE_STRENGTH;
        this.mScrollerEnabled = true;
        this.mLineSpacingMultiplier = 1.0f;
        this.mMaxFlingVelocityCoefficient = 8;
        this.mAccessibilityDescriptionEnabled = true;
        this.mItemSpacing = 0;
        this.mContext = context;
        this.mNumberFormatter = NumberFormat.getInstance();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.NumberPicker, i, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        if (drawable != null) {
            drawable.setCallback(this);
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
            this.mDividerDrawable = drawable;
        } else {
            this.mDividerColor = obtainStyledAttributes.getColor(2, this.mDividerColor);
            setDividerColor(this.mDividerColor);
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mDividerDistance = obtainStyledAttributes.getDimensionPixelSize(3, (int) TypedValue.applyDimension(1, 48.0f, displayMetrics));
        this.mDividerLength = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        this.mDividerThickness = obtainStyledAttributes.getDimensionPixelSize(5, (int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.mDividerType = obtainStyledAttributes.getInt(6, 0);
        this.mOrder = obtainStyledAttributes.getInt(17, 0);
        this.mOrientation = obtainStyledAttributes.getInt(18, 1);
        float dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(36, -1);
        float dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(10, -1);
        setWidthAndHeight();
        this.mComputeMaxWidth = true;
        this.mValue = obtainStyledAttributes.getInt(34, this.mValue);
        this.mMaxValue = obtainStyledAttributes.getInt(14, this.mMaxValue);
        this.mMinValue = obtainStyledAttributes.getInt(16, this.mMinValue);
        this.mSelectedTextAlign = obtainStyledAttributes.getInt(20, this.mSelectedTextAlign);
        this.mSelectedTextColor = obtainStyledAttributes.getColor(22, this.mSelectedTextColor);
        this.mSelectedTextSize = obtainStyledAttributes.getDimension(23, spToPx(this.mSelectedTextSize));
        this.mSelectedTextStrikeThru = obtainStyledAttributes.getBoolean(24, this.mSelectedTextStrikeThru);
        this.mSelectedTextUnderline = obtainStyledAttributes.getBoolean(25, this.mSelectedTextUnderline);
        this.mSelectedTypeface = Typeface.create(obtainStyledAttributes.getString(26), 0);
        this.mTextAlign = obtainStyledAttributes.getInt(27, this.mTextAlign);
        this.mTextColor = obtainStyledAttributes.getColor(29, this.mTextColor);
        this.mTextSize = obtainStyledAttributes.getDimension(30, spToPx(this.mTextSize));
        this.mTextStrikeThru = obtainStyledAttributes.getBoolean(31, this.mTextStrikeThru);
        this.mTextUnderline = obtainStyledAttributes.getBoolean(32, this.mTextUnderline);
        this.mTypeface = Typeface.create(obtainStyledAttributes.getString(33), 0);
        this.mFormatter = stringToFormatter(obtainStyledAttributes.getString(9));
        this.mFadingEdgeEnabled = obtainStyledAttributes.getBoolean(7, this.mFadingEdgeEnabled);
        this.mFadingEdgeStrength = obtainStyledAttributes.getFloat(8, this.mFadingEdgeStrength);
        this.mScrollerEnabled = obtainStyledAttributes.getBoolean(19, this.mScrollerEnabled);
        this.mWheelItemCount = obtainStyledAttributes.getInt(35, this.mWheelItemCount);
        this.mLineSpacingMultiplier = obtainStyledAttributes.getFloat(13, this.mLineSpacingMultiplier);
        this.mMaxFlingVelocityCoefficient = obtainStyledAttributes.getInt(15, this.mMaxFlingVelocityCoefficient);
        this.mHideWheelUntilFocused = obtainStyledAttributes.getBoolean(11, false);
        this.mAccessibilityDescriptionEnabled = obtainStyledAttributes.getBoolean(0, true);
        this.mItemSpacing = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        this.textBold = obtainStyledAttributes.getBoolean(28, this.textBold);
        this.selectedTextBold = obtainStyledAttributes.getBoolean(21, this.selectedTextBold);
        setWillNotDraw(false);
        this.mSelectedText = new EditText(context);
        this.mSelectedText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mSelectedText.setGravity(17);
        this.mSelectedText.setSingleLine(true);
        this.mSelectedText.setImportantForAccessibility(2);
        this.mSelectedText.setEnabled(false);
        this.mSelectedText.setFocusable(false);
        this.mSelectedText.setVisibility(4);
        this.mSelectedText.setImeOptions(1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        this.mSelectorWheelPaint = paint;
        setSelectedTextColor(this.mSelectedTextColor);
        setTextColor(this.mTextColor);
        setTextSize(this.mTextSize);
        setSelectedTextSize(this.mSelectedTextSize);
        setTypeface(this.mTypeface);
        setSelectedTypeface(this.mSelectedTypeface);
        setFormatter(this.mFormatter);
        updateInputTextView();
        setValue(this.mValue);
        setMaxValue(this.mMaxValue);
        setMinValue(this.mMinValue);
        setWheelItemCount(this.mWheelItemCount);
        this.mWrapSelectorWheel = obtainStyledAttributes.getBoolean(37, this.mWrapSelectorWheel);
        setWrapSelectorWheel(this.mWrapSelectorWheel);
        int i3 = (dimensionPixelSize > (-1.0f) ? 1 : (dimensionPixelSize == (-1.0f) ? 0 : -1));
        if (i3 != 0 && dimensionPixelSize2 != -1.0f) {
            setScaleX(dimensionPixelSize / this.mMinWidth);
            setScaleY(dimensionPixelSize2 / this.mMaxHeight);
        } else if (i3 != 0) {
            float f = dimensionPixelSize / this.mMinWidth;
            setScaleX(f);
            setScaleY(f);
        } else if (dimensionPixelSize2 != -1.0f) {
            float f2 = dimensionPixelSize2 / this.mMaxHeight;
            setScaleX(f2);
            setScaleY(f2);
        }
        this.mViewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = this.mViewConfiguration.getScaledTouchSlop();
        this.mMinimumFlingVelocity = this.mViewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = this.mViewConfiguration.getScaledMaximumFlingVelocity() / this.mMaxFlingVelocityCoefficient;
        this.mFlingScroller = new Scroller(context, null, true);
        this.mAdjustScroller = new Scroller(context, new DecelerateInterpolator(2.5f));
        if (Build.VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        if (Build.VERSION.SDK_INT >= 26 && getFocusable() == 16) {
            setFocusable(1);
            setFocusableInTouchMode(true);
        }
        obtainStyledAttributes.recycle();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth2 = this.mSelectedText.getMeasuredWidth();
        int measuredHeight2 = this.mSelectedText.getMeasuredHeight();
        int i5 = (measuredWidth - measuredWidth2) / 2;
        int i6 = (measuredHeight - measuredHeight2) / 2;
        this.mSelectedText.layout(i5, i6, measuredWidth2 + i5, measuredHeight2 + i6);
        this.mSelectedTextCenterX = (this.mSelectedText.getX() + (this.mSelectedText.getMeasuredWidth() / 2.0f)) - 2.0f;
        this.mSelectedTextCenterY = (this.mSelectedText.getY() + (this.mSelectedText.getMeasuredHeight() / 2.0f)) - 5.0f;
        if (z) {
            initializeSelectorWheel();
            initializeFadingEdges();
            int i7 = (this.mDividerThickness * 2) + this.mDividerDistance;
            if (isHorizontalMode()) {
                this.mLeftDividerLeft = ((getWidth() - this.mDividerDistance) / 2) - this.mDividerThickness;
                this.mRightDividerRight = this.mLeftDividerLeft + i7;
                this.mBottomDividerBottom = getHeight();
                return;
            }
            this.mTopDividerTop = ((getHeight() - this.mDividerDistance) / 2) - this.mDividerThickness;
            this.mBottomDividerBottom = this.mTopDividerTop + i7;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(makeMeasureSpec(i, this.mMaxWidth), makeMeasureSpec(i2, this.mMaxHeight));
        setMeasuredDimension(resolveSizeAndStateRespectingMinSize(this.mMinWidth, getMeasuredWidth(), i), resolveSizeAndStateRespectingMinSize(this.mMinHeight, getMeasuredHeight(), i2));
    }

    private boolean moveToFinalScrollerPosition(Scroller scroller) {
        scroller.forceFinished(true);
        if (isHorizontalMode()) {
            int finalX = scroller.getFinalX() - scroller.getCurrX();
            int i = this.mInitialScrollOffset - ((this.mCurrentScrollOffset + finalX) % this.mSelectorElementSize);
            if (i != 0) {
                int abs = Math.abs(i);
                int i2 = this.mSelectorElementSize;
                if (abs > i2 / 2) {
                    i = i > 0 ? i - i2 : i + i2;
                }
                scrollBy(finalX + i, 0);
                return true;
            }
        } else {
            int finalY = scroller.getFinalY() - scroller.getCurrY();
            int i3 = this.mInitialScrollOffset - ((this.mCurrentScrollOffset + finalY) % this.mSelectorElementSize);
            if (i3 != 0) {
                int abs2 = Math.abs(i3);
                int i4 = this.mSelectorElementSize;
                if (abs2 > i4 / 2) {
                    i3 = i3 > 0 ? i3 - i4 : i3 + i4;
                }
                scrollBy(0, finalY + i3);
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && (motionEvent.getAction() & 255) == 0) {
            removeAllCallbacks();
            getParent().requestDisallowInterceptTouchEvent(true);
            if (isHorizontalMode()) {
                float x = motionEvent.getX();
                this.mLastDownEventX = x;
                this.mLastDownOrMoveEventX = x;
                if (!this.mFlingScroller.isFinished()) {
                    this.mFlingScroller.forceFinished(true);
                    this.mAdjustScroller.forceFinished(true);
                    onScrollerFinished(this.mFlingScroller);
                    onScrollStateChange(0);
                } else if (!this.mAdjustScroller.isFinished()) {
                    this.mFlingScroller.forceFinished(true);
                    this.mAdjustScroller.forceFinished(true);
                    onScrollerFinished(this.mAdjustScroller);
                } else {
                    float f = this.mLastDownEventX;
                    if (f >= this.mLeftDividerLeft && f <= this.mRightDividerRight) {
                        View.OnClickListener onClickListener = this.mOnClickListener;
                        if (onClickListener != null) {
                            onClickListener.onClick(this);
                        }
                    } else {
                        float f2 = this.mLastDownEventX;
                        if (f2 < this.mLeftDividerLeft) {
                            postChangeCurrentByOneFromLongPress(false);
                        } else if (f2 > this.mRightDividerRight) {
                            postChangeCurrentByOneFromLongPress(true);
                        }
                    }
                }
            } else {
                float y = motionEvent.getY();
                this.mLastDownEventY = y;
                this.mLastDownOrMoveEventY = y;
                if (!this.mFlingScroller.isFinished()) {
                    this.mFlingScroller.forceFinished(true);
                    this.mAdjustScroller.forceFinished(true);
                    onScrollStateChange(0);
                } else if (!this.mAdjustScroller.isFinished()) {
                    this.mFlingScroller.forceFinished(true);
                    this.mAdjustScroller.forceFinished(true);
                } else {
                    float f3 = this.mLastDownEventY;
                    if (f3 >= this.mTopDividerTop && f3 <= this.mBottomDividerBottom) {
                        View.OnClickListener onClickListener2 = this.mOnClickListener;
                        if (onClickListener2 != null) {
                            onClickListener2.onClick(this);
                        }
                    } else {
                        float f4 = this.mLastDownEventY;
                        if (f4 < this.mTopDividerTop) {
                            postChangeCurrentByOneFromLongPress(false);
                        } else if (f4 > this.mBottomDividerBottom) {
                            postChangeCurrentByOneFromLongPress(true);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && isScrollerEnabled()) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            switch (motionEvent.getAction() & 255) {
                case 1:
                    removeChangeCurrentByOneFromLongPress();
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
                    if (isHorizontalMode()) {
                        int xVelocity = (int) velocityTracker.getXVelocity();
                        if (Math.abs(xVelocity) > this.mMinimumFlingVelocity) {
                            fling(xVelocity);
                            onScrollStateChange(2);
                        } else {
                            int x = (int) motionEvent.getX();
                            if (((int) Math.abs(x - this.mLastDownEventX)) <= this.mTouchSlop) {
                                int i = (x / this.mSelectorElementSize) - this.mWheelMiddleItemIndex;
                                if (i > 0) {
                                    changeValueByOne(true);
                                } else if (i < 0) {
                                    changeValueByOne(false);
                                } else {
                                    ensureScrollWheelAdjusted();
                                }
                            } else {
                                ensureScrollWheelAdjusted();
                            }
                            onScrollStateChange(0);
                        }
                    } else {
                        int yVelocity = (int) velocityTracker.getYVelocity();
                        if (Math.abs(yVelocity) > this.mMinimumFlingVelocity) {
                            fling(yVelocity);
                            onScrollStateChange(2);
                        } else {
                            int y = (int) motionEvent.getY();
                            if (((int) Math.abs(y - this.mLastDownEventY)) <= this.mTouchSlop) {
                                int i2 = (y / this.mSelectorElementSize) - this.mWheelMiddleItemIndex;
                                if (i2 > 0) {
                                    changeValueByOne(true);
                                } else if (i2 < 0) {
                                    changeValueByOne(false);
                                } else {
                                    ensureScrollWheelAdjusted();
                                }
                            } else {
                                ensureScrollWheelAdjusted();
                            }
                            onScrollStateChange(0);
                        }
                    }
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                case 2:
                    if (isHorizontalMode()) {
                        float x2 = motionEvent.getX();
                        if (this.mScrollState != 1) {
                            if (((int) Math.abs(x2 - this.mLastDownEventX)) > this.mTouchSlop) {
                                removeAllCallbacks();
                                onScrollStateChange(1);
                            }
                        } else {
                            scrollBy((int) (x2 - this.mLastDownOrMoveEventX), 0);
                            invalidate();
                        }
                        this.mLastDownOrMoveEventX = x2;
                        break;
                    } else {
                        float y2 = motionEvent.getY();
                        if (this.mScrollState != 1) {
                            if (((int) Math.abs(y2 - this.mLastDownEventY)) > this.mTouchSlop) {
                                removeAllCallbacks();
                                onScrollStateChange(1);
                            }
                        } else {
                            scrollBy(0, (int) (y2 - this.mLastDownOrMoveEventY));
                            invalidate();
                        }
                        this.mLastDownOrMoveEventY = y2;
                        break;
                    }
            }
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 1 || action == 3) {
            removeAllCallbacks();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 23 || keyCode == 66) {
            removeAllCallbacks();
        } else {
            switch (keyCode) {
                case 19:
                case 20:
                    switch (keyEvent.getAction()) {
                        case 0:
                            if (this.mWrapSelectorWheel || (keyCode != 20 ? getValue() > getMinValue() : getValue() < getMaxValue())) {
                                requestFocus();
                                this.mLastHandledDownDpadKeyCode = keyCode;
                                removeAllCallbacks();
                                if (this.mFlingScroller.isFinished()) {
                                    changeValueByOne(keyCode == 20);
                                }
                                return true;
                            }
                        case 1:
                            if (this.mLastHandledDownDpadKeyCode == keyCode) {
                                this.mLastHandledDownDpadKeyCode = -1;
                                return true;
                            }
                            break;
                    }
                    break;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 1 || action == 3) {
            removeAllCallbacks();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (isScrollerEnabled()) {
            Scroller scroller = this.mFlingScroller;
            if (scroller.isFinished()) {
                scroller = this.mAdjustScroller;
                if (scroller.isFinished()) {
                    return;
                }
            }
            scroller.computeScrollOffset();
            if (isHorizontalMode()) {
                int currX = scroller.getCurrX();
                if (this.mPreviousScrollerX == 0) {
                    this.mPreviousScrollerX = scroller.getStartX();
                }
                scrollBy(currX - this.mPreviousScrollerX, 0);
                this.mPreviousScrollerX = currX;
            } else {
                int currY = scroller.getCurrY();
                if (this.mPreviousScrollerY == 0) {
                    this.mPreviousScrollerY = scroller.getStartY();
                }
                scrollBy(0, currY - this.mPreviousScrollerY);
                this.mPreviousScrollerY = currY;
            }
            if (scroller.isFinished()) {
                onScrollerFinished(scroller);
            } else {
                postInvalidate();
            }
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mSelectedText.setEnabled(z);
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        int i3;
        if (isScrollerEnabled()) {
            int[] selectorIndices = getSelectorIndices();
            int i4 = this.mCurrentScrollOffset;
            int maxTextSize = (int) getMaxTextSize();
            if (isHorizontalMode()) {
                if (isAscendingOrder()) {
                    if (!this.mWrapSelectorWheel && i > 0 && selectorIndices[this.mWheelMiddleItemIndex] <= this.mMinValue) {
                        this.mCurrentScrollOffset = this.mInitialScrollOffset;
                        return;
                    } else if (!this.mWrapSelectorWheel && i < 0 && selectorIndices[this.mWheelMiddleItemIndex] >= this.mMaxValue) {
                        this.mCurrentScrollOffset = this.mInitialScrollOffset;
                        return;
                    }
                } else if (!this.mWrapSelectorWheel && i > 0 && selectorIndices[this.mWheelMiddleItemIndex] >= this.mMaxValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                    return;
                } else if (!this.mWrapSelectorWheel && i < 0 && selectorIndices[this.mWheelMiddleItemIndex] <= this.mMinValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                    return;
                }
                this.mCurrentScrollOffset += i;
            } else {
                if (isAscendingOrder()) {
                    if (!this.mWrapSelectorWheel && i2 > 0 && selectorIndices[this.mWheelMiddleItemIndex] <= this.mMinValue) {
                        this.mCurrentScrollOffset = this.mInitialScrollOffset;
                        return;
                    } else if (!this.mWrapSelectorWheel && i2 < 0 && selectorIndices[this.mWheelMiddleItemIndex] >= this.mMaxValue) {
                        this.mCurrentScrollOffset = this.mInitialScrollOffset;
                        return;
                    }
                } else if (!this.mWrapSelectorWheel && i2 > 0 && selectorIndices[this.mWheelMiddleItemIndex] >= this.mMaxValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                    return;
                } else if (!this.mWrapSelectorWheel && i2 < 0 && selectorIndices[this.mWheelMiddleItemIndex] <= this.mMinValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                    return;
                }
                this.mCurrentScrollOffset += i2;
            }
            while (true) {
                int i5 = this.mCurrentScrollOffset;
                if (i5 - this.mInitialScrollOffset <= maxTextSize) {
                    break;
                }
                this.mCurrentScrollOffset = i5 - this.mSelectorElementSize;
                if (isAscendingOrder()) {
                    decrementSelectorIndices(selectorIndices);
                } else {
                    incrementSelectorIndices(selectorIndices);
                }
                setValueInternal(selectorIndices[this.mWheelMiddleItemIndex], true);
                if (!this.mWrapSelectorWheel && selectorIndices[this.mWheelMiddleItemIndex] < this.mMinValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                }
            }
            while (true) {
                i3 = this.mCurrentScrollOffset;
                if (i3 - this.mInitialScrollOffset >= (-maxTextSize)) {
                    break;
                }
                this.mCurrentScrollOffset = i3 + this.mSelectorElementSize;
                if (isAscendingOrder()) {
                    incrementSelectorIndices(selectorIndices);
                } else {
                    decrementSelectorIndices(selectorIndices);
                }
                setValueInternal(selectorIndices[this.mWheelMiddleItemIndex], true);
                if (!this.mWrapSelectorWheel && selectorIndices[this.mWheelMiddleItemIndex] > this.mMaxValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                }
            }
            if (i4 != i3) {
                if (isHorizontalMode()) {
                    onScrollChanged(this.mCurrentScrollOffset, 0, i4, 0);
                } else {
                    onScrollChanged(0, this.mCurrentScrollOffset, 0, i4);
                }
            }
        }
    }

    private int computeScrollOffset(boolean z) {
        if (z) {
            return this.mCurrentScrollOffset;
        }
        return 0;
    }

    private int computeScrollRange(boolean z) {
        if (z) {
            return ((this.mMaxValue - this.mMinValue) + 1) * this.mSelectorElementSize;
        }
        return 0;
    }

    private int computeScrollExtent(boolean z) {
        return z ? getWidth() : getHeight();
    }

    @Override // android.view.View
    protected int computeHorizontalScrollOffset() {
        return computeScrollOffset(isHorizontalMode());
    }

    @Override // android.view.View
    protected int computeHorizontalScrollRange() {
        return computeScrollRange(isHorizontalMode());
    }

    @Override // android.view.View
    protected int computeHorizontalScrollExtent() {
        return computeScrollExtent(isHorizontalMode());
    }

    @Override // android.view.View
    protected int computeVerticalScrollOffset() {
        return computeScrollOffset(!isHorizontalMode());
    }

    @Override // android.view.View
    protected int computeVerticalScrollRange() {
        return computeScrollRange(!isHorizontalMode());
    }

    @Override // android.view.View
    protected int computeVerticalScrollExtent() {
        return computeScrollExtent(isHorizontalMode());
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mNumberFormatter = NumberFormat.getInstance();
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.mOnValueChangeListener = onValueChangeListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void setFormatter(Formatter formatter) {
        if (formatter == this.mFormatter) {
            return;
        }
        this.mFormatter = formatter;
        initializeSelectorWheelIndices();
        updateInputTextView();
    }

    public void setValue(int i) {
        setValueInternal(i, false);
    }

    private float getMaxTextSize() {
        return Math.max(this.mTextSize, this.mSelectedTextSize);
    }

    private float getPaintCenterY(Paint.FontMetrics fontMetrics) {
        if (fontMetrics == null) {
            return 0.0f;
        }
        return Math.abs(fontMetrics.top + fontMetrics.bottom) / 2.0f;
    }

    private void tryComputeMaxWidth() {
        int i;
        if (this.mComputeMaxWidth) {
            this.mSelectorWheelPaint.setTextSize(getMaxTextSize());
            String[] strArr = this.mDisplayedValues;
            int i2 = 0;
            if (strArr == null) {
                float f = 0.0f;
                for (int i3 = 0; i3 <= 9; i3++) {
                    float measureText = this.mSelectorWheelPaint.measureText(formatNumber(i3));
                    if (measureText > f) {
                        f = measureText;
                    }
                }
                for (int i4 = this.mMaxValue; i4 > 0; i4 /= 10) {
                    i2++;
                }
                i = (int) (i2 * f);
            } else {
                int length = strArr.length;
                int i5 = 0;
                while (i2 < length) {
                    float measureText2 = this.mSelectorWheelPaint.measureText(strArr[i2]);
                    if (measureText2 > i5) {
                        i5 = (int) measureText2;
                    }
                    i2++;
                }
                i = i5;
            }
            int paddingLeft = i + this.mSelectedText.getPaddingLeft() + this.mSelectedText.getPaddingRight();
            if (this.mMaxWidth != paddingLeft) {
                this.mMaxWidth = Math.max(paddingLeft, this.mMinWidth);
                invalidate();
            }
        }
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    public void setWrapSelectorWheel(boolean z) {
        this.mWrapSelectorWheelPreferred = z;
        updateWrapSelectorWheel();
    }

    private void updateWrapSelectorWheel() {
        this.mWrapSelectorWheel = isWrappingAllowed() && this.mWrapSelectorWheelPreferred;
    }

    private boolean isWrappingAllowed() {
        return this.mMaxValue - this.mMinValue >= this.mSelectorIndices.length - 1;
    }

    public void setOnLongPressUpdateInterval(long j) {
        this.mLongPressUpdateInterval = j;
    }

    public int getValue() {
        return this.mValue;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    public void setMinValue(int i) {
        this.mMinValue = i;
        int i2 = this.mMinValue;
        if (i2 > this.mValue) {
            this.mValue = i2;
        }
        updateWrapSelectorWheel();
        initializeSelectorWheelIndices();
        updateInputTextView();
        tryComputeMaxWidth();
        invalidate();
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public void setMaxValue(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("maxValue must be >= 0");
        }
        this.mMaxValue = i;
        int i2 = this.mMaxValue;
        if (i2 < this.mValue) {
            this.mValue = i2;
        }
        updateWrapSelectorWheel();
        initializeSelectorWheelIndices();
        updateInputTextView();
        tryComputeMaxWidth();
        invalidate();
    }

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.mDisplayedValues == strArr) {
            return;
        }
        this.mDisplayedValues = strArr;
        if (this.mDisplayedValues != null) {
            this.mSelectedText.setRawInputType(655360);
        } else {
            this.mSelectedText.setRawInputType(655360);
        }
        updateInputTextView();
        initializeSelectorWheelIndices();
        tryComputeMaxWidth();
    }

    private float getFadingEdgeStrength(boolean z) {
        if (z && this.mFadingEdgeEnabled) {
            return this.mFadingEdgeStrength;
        }
        return 0.0f;
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        return getFadingEdgeStrength(!isHorizontalMode());
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        return getFadingEdgeStrength(!isHorizontalMode());
    }

    @Override // android.view.View
    protected float getLeftFadingEdgeStrength() {
        return getFadingEdgeStrength(isHorizontalMode());
    }

    @Override // android.view.View
    protected float getRightFadingEdgeStrength() {
        return getFadingEdgeStrength(isHorizontalMode());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllCallbacks();
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mDividerDrawable;
        if (drawable != null && drawable.isStateful() && this.mDividerDrawable.setState(getDrawableState())) {
            invalidateDrawable(this.mDividerDrawable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mDividerDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        float right;
        float f;
        String replace;
        int i;
        int i2;
        canvas.save();
        boolean z = !this.mHideWheelUntilFocused || hasFocus();
        if (isHorizontalMode()) {
            right = this.mCurrentScrollOffset;
            f = this.mSelectedText.getBaseline() + this.mSelectedText.getTop();
            if (this.mRealWheelItemCount < 3) {
                canvas.clipRect(this.mLeftDividerLeft, 0, this.mRightDividerRight, getBottom());
            }
        } else {
            right = (getRight() - getLeft()) / 2.0f;
            f = this.mCurrentScrollOffset;
            if (this.mRealWheelItemCount < 3) {
                canvas.clipRect(0, this.mTopDividerTop, getRight(), this.mBottomDividerBottom);
            }
        }
        int[] selectorIndices = getSelectorIndices();
        float f2 = right;
        for (int i3 = 0; i3 < selectorIndices.length; i3++) {
            String str = this.mSelectorIndexToStringCache.get(selectorIndices[isAscendingOrder() ? i3 : (selectorIndices.length - i3) - 1]);
            if (i3 == this.mWheelMiddleItemIndex) {
                this.mSelectorWheelPaint.setTextAlign(Paint.Align.values()[this.mSelectedTextAlign]);
                this.mSelectorWheelPaint.setTextSize(this.mSelectedTextSize);
                this.mSelectorWheelPaint.setColor(this.mSelectedTextColor);
                this.mSelectorWheelPaint.setFakeBoldText(this.selectedTextBold);
                this.mSelectorWheelPaint.setStrikeThruText(this.mSelectedTextStrikeThru);
                this.mSelectorWheelPaint.setUnderlineText(this.mSelectedTextUnderline);
                this.mSelectorWheelPaint.setTypeface(this.mSelectedTypeface);
                replace = str + this.label;
            } else {
                this.mSelectorWheelPaint.setTextAlign(Paint.Align.values()[this.mTextAlign]);
                this.mSelectorWheelPaint.setTextSize(this.mTextSize);
                this.mSelectorWheelPaint.setColor(this.mTextColor);
                this.mSelectorWheelPaint.setFakeBoldText(this.textBold);
                this.mSelectorWheelPaint.setStrikeThruText(this.mTextStrikeThru);
                this.mSelectorWheelPaint.setUnderlineText(this.mTextUnderline);
                this.mSelectorWheelPaint.setTypeface(this.mTypeface);
                replace = str.replace(this.label, "");
            }
            if (replace != null) {
                if ((z && i3 != this.mWheelMiddleItemIndex) || (i3 == this.mWheelMiddleItemIndex && this.mSelectedText.getVisibility() != 0)) {
                    float paintCenterY = !isHorizontalMode() ? getPaintCenterY(this.mSelectorWheelPaint.getFontMetrics()) + f : f;
                    if (i3 == this.mWheelMiddleItemIndex || this.mItemSpacing == 0) {
                        i = 0;
                        i2 = 0;
                    } else if (isHorizontalMode()) {
                        if (i3 > this.mWheelMiddleItemIndex) {
                            i = this.mItemSpacing;
                            i2 = 0;
                        } else {
                            i = -this.mItemSpacing;
                            i2 = 0;
                        }
                    } else if (i3 > this.mWheelMiddleItemIndex) {
                        i2 = this.mItemSpacing;
                        i = 0;
                    } else {
                        i2 = -this.mItemSpacing;
                        i = 0;
                    }
                    drawText(replace, f2 + i, paintCenterY + i2, this.mSelectorWheelPaint, canvas);
                }
                if (isHorizontalMode()) {
                    f2 += this.mSelectorElementSize;
                } else {
                    f += this.mSelectorElementSize;
                }
            }
        }
        canvas.restore();
        if (!z || this.mDividerDrawable == null) {
            return;
        }
        if (isHorizontalMode()) {
            drawHorizontalDividers(canvas);
        } else {
            drawVerticalDividers(canvas);
        }
    }

    private void drawHorizontalDividers(Canvas canvas) {
        int i;
        int bottom;
        int i2;
        int i3;
        int i4;
        int i5;
        switch (this.mDividerType) {
            case 0:
                int i6 = this.mDividerLength;
                if (i6 > 0 && i6 <= (i2 = this.mMaxHeight)) {
                    i = (i2 - i6) / 2;
                    bottom = i6 + i;
                } else {
                    i = 0;
                    bottom = getBottom();
                }
                int i7 = this.mLeftDividerLeft;
                this.mDividerDrawable.setBounds(i7, i, this.mDividerThickness + i7, bottom);
                this.mDividerDrawable.draw(canvas);
                int i8 = this.mRightDividerRight;
                this.mDividerDrawable.setBounds(i8 - this.mDividerThickness, i, i8, bottom);
                this.mDividerDrawable.draw(canvas);
                return;
            case 1:
                int i9 = this.mDividerLength;
                if (i9 > 0 && i9 <= (i5 = this.mMaxWidth)) {
                    i3 = (i5 - i9) / 2;
                    i4 = i9 + i3;
                } else {
                    i3 = this.mLeftDividerLeft;
                    i4 = this.mRightDividerRight;
                }
                int i10 = this.mBottomDividerBottom;
                this.mDividerDrawable.setBounds(i3, i10 - this.mDividerThickness, i4, i10);
                this.mDividerDrawable.draw(canvas);
                return;
            default:
                return;
        }
    }

    private void drawVerticalDividers(Canvas canvas) {
        int i;
        int right;
        int i2;
        int i3 = this.mDividerLength;
        if (i3 > 0 && i3 <= (i2 = this.mMaxWidth)) {
            i = (i2 - i3) / 2;
            right = i3 + i;
        } else {
            i = 0;
            right = getRight();
        }
        switch (this.mDividerType) {
            case 0:
                int i4 = this.mTopDividerTop;
                this.mDividerDrawable.setBounds(i, i4, right, this.mDividerThickness + i4);
                this.mDividerDrawable.draw(canvas);
                int i5 = this.mBottomDividerBottom;
                this.mDividerDrawable.setBounds(i, i5 - this.mDividerThickness, right, i5);
                this.mDividerDrawable.draw(canvas);
                return;
            case 1:
                int i6 = this.mBottomDividerBottom;
                this.mDividerDrawable.setBounds(i, i6 - this.mDividerThickness, right, i6);
                this.mDividerDrawable.draw(canvas);
                return;
            default:
                return;
        }
    }

    private void drawText(String str, float f, float f2, Paint paint, Canvas canvas) {
        if (str.contains("\n")) {
            String[] split = str.split("\n");
            float abs = Math.abs(paint.descent() + paint.ascent()) * this.mLineSpacingMultiplier;
            float length = f2 - (((split.length - 1) * abs) / 2.0f);
            for (String str2 : split) {
                canvas.drawText(str2, f, length, paint);
                length += abs;
            }
            return;
        }
        canvas.drawText(str, f, f2, paint);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(NumberPicker.class.getName());
        accessibilityEvent.setScrollable(isScrollerEnabled());
        int i = this.mMinValue;
        int i2 = this.mSelectorElementSize;
        int i3 = (this.mValue + i) * i2;
        int i4 = (this.mMaxValue - i) * i2;
        if (isHorizontalMode()) {
            accessibilityEvent.setScrollX(i3);
            accessibilityEvent.setMaxScrollX(i4);
            return;
        }
        accessibilityEvent.setScrollY(i3);
        accessibilityEvent.setMaxScrollY(i4);
    }

    private int makeMeasureSpec(int i, int i2) {
        if (i2 == -1) {
            return i;
        }
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        if (mode != Integer.MIN_VALUE) {
            if (mode != 0) {
                if (mode == 1073741824) {
                    return i;
                }
                throw new IllegalArgumentException("Unknown measure mode: " + mode);
            }
            return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(size, i2), 1073741824);
    }

    private int resolveSizeAndStateRespectingMinSize(int i, int i2, int i3) {
        return i != -1 ? resolveSizeAndState(Math.max(i, i2), i3, 0) : i2;
    }

    public static int resolveSizeAndState(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE) {
            if (mode != 0 && mode == 1073741824) {
                i = size;
            }
        } else if (size < i) {
            i = 16777216 | size;
        }
        return i | ((-16777216) & i3);
    }

    private void initializeSelectorWheelIndices() {
        this.mSelectorIndexToStringCache.clear();
        int[] selectorIndices = getSelectorIndices();
        int value = getValue();
        for (int i = 0; i < selectorIndices.length; i++) {
            int i2 = (i - this.mWheelMiddleItemIndex) + value;
            if (this.mWrapSelectorWheel) {
                i2 = getWrappedSelectorIndex(i2);
            }
            selectorIndices[i] = i2;
            ensureCachedScrollSelectorValue(selectorIndices[i]);
        }
    }

    private void setValueInternal(int i, boolean z) {
        int min;
        if (this.mValue == i) {
            return;
        }
        if (this.mWrapSelectorWheel) {
            min = getWrappedSelectorIndex(i);
        } else {
            min = Math.min(Math.max(i, this.mMinValue), this.mMaxValue);
        }
        int i2 = this.mValue;
        this.mValue = min;
        if (this.mScrollState != 2) {
            updateInputTextView();
        }
        if (z) {
            notifyChange(i2, min);
        }
        initializeSelectorWheelIndices();
        updateAccessibilityDescription();
        invalidate();
    }

    private void updateAccessibilityDescription() {
        if (this.mAccessibilityDescriptionEnabled) {
            setContentDescription(String.valueOf(getValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeValueByOne(boolean z) {
        if (!moveToFinalScrollerPosition(this.mFlingScroller)) {
            moveToFinalScrollerPosition(this.mAdjustScroller);
        }
        smoothScroll(z, 1);
    }

    public void smoothScrollToPosition(int i) {
        int i2 = getSelectorIndices()[this.mWheelMiddleItemIndex];
        if (i2 == i) {
            return;
        }
        smoothScroll(i > i2, Math.abs(i - i2));
    }

    public void smoothScroll(boolean z, int i) {
        int i2 = (z ? -this.mSelectorElementSize : this.mSelectorElementSize) * i;
        if (isHorizontalMode()) {
            this.mPreviousScrollerX = 0;
            this.mFlingScroller.startScroll(0, 0, i2, 0, 300);
        } else {
            this.mPreviousScrollerY = 0;
            this.mFlingScroller.startScroll(0, 0, 0, i2, 300);
        }
        invalidate();
    }

    private void initializeSelectorWheel() {
        initializeSelectorWheelIndices();
        int[] selectorIndices = getSelectorIndices();
        int length = (int) (((selectorIndices.length - 1) * this.mTextSize) + this.mSelectedTextSize);
        float length2 = selectorIndices.length;
        if (isHorizontalMode()) {
            this.mSelectorTextGapWidth = (int) (((getRight() - getLeft()) - length) / length2);
            this.mSelectorElementSize = ((int) getMaxTextSize()) + this.mSelectorTextGapWidth;
            this.mInitialScrollOffset = (int) (this.mSelectedTextCenterX - (this.mSelectorElementSize * this.mWheelMiddleItemIndex));
        } else {
            this.mSelectorTextGapHeight = (int) (((getBottom() - getTop()) - length) / length2);
            this.mSelectorElementSize = ((int) getMaxTextSize()) + this.mSelectorTextGapHeight;
            this.mInitialScrollOffset = (int) (this.mSelectedTextCenterY - (this.mSelectorElementSize * this.mWheelMiddleItemIndex));
        }
        this.mCurrentScrollOffset = this.mInitialScrollOffset;
        updateInputTextView();
    }

    private void initializeFadingEdges() {
        if (isHorizontalMode()) {
            setHorizontalFadingEdgeEnabled(true);
            setVerticalFadingEdgeEnabled(false);
            setFadingEdgeLength(((getRight() - getLeft()) - ((int) this.mTextSize)) / 2);
            return;
        }
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - ((int) this.mTextSize)) / 2);
    }

    private void onScrollerFinished(Scroller scroller) {
        if (scroller == this.mFlingScroller) {
            ensureScrollWheelAdjusted();
            updateInputTextView();
            onScrollStateChange(0);
        } else if (this.mScrollState != 1) {
            updateInputTextView();
        }
    }

    private void onScrollStateChange(int i) {
        if (this.mScrollState == i) {
            return;
        }
        this.mScrollState = i;
        OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChange(this, i);
        }
    }

    private void fling(int i) {
        if (isHorizontalMode()) {
            this.mPreviousScrollerX = 0;
            if (i > 0) {
                this.mFlingScroller.fling(0, 0, i, 0, 0, Integer.MAX_VALUE, 0, 0);
            } else {
                this.mFlingScroller.fling(Integer.MAX_VALUE, 0, i, 0, 0, Integer.MAX_VALUE, 0, 0);
            }
        } else {
            this.mPreviousScrollerY = 0;
            if (i > 0) {
                this.mFlingScroller.fling(0, 0, 0, i, 0, 0, 0, Integer.MAX_VALUE);
            } else {
                this.mFlingScroller.fling(0, Integer.MAX_VALUE, 0, i, 0, 0, 0, Integer.MAX_VALUE);
            }
        }
        invalidate();
    }

    private int getWrappedSelectorIndex(int i) {
        int i2 = this.mMaxValue;
        if (i > i2) {
            int i3 = this.mMinValue;
            return (i3 + ((i - i2) % (i2 - i3))) - 1;
        }
        int i4 = this.mMinValue;
        return i < i4 ? (i2 - ((i4 - i) % (i2 - i4))) + 1 : i;
    }

    private int[] getSelectorIndices() {
        return this.mSelectorIndices;
    }

    private void incrementSelectorIndices(int[] iArr) {
        int i = 0;
        while (i < iArr.length - 1) {
            int i2 = i + 1;
            iArr[i] = iArr[i2];
            i = i2;
        }
        int i3 = iArr[iArr.length - 2] + 1;
        if (this.mWrapSelectorWheel && i3 > this.mMaxValue) {
            i3 = this.mMinValue;
        }
        iArr[iArr.length - 1] = i3;
        ensureCachedScrollSelectorValue(i3);
    }

    private void decrementSelectorIndices(int[] iArr) {
        for (int length = iArr.length - 1; length > 0; length--) {
            iArr[length] = iArr[length - 1];
        }
        int i = iArr[1] - 1;
        if (this.mWrapSelectorWheel && i < this.mMinValue) {
            i = this.mMaxValue;
        }
        iArr[0] = i;
        ensureCachedScrollSelectorValue(i);
    }

    private void ensureCachedScrollSelectorValue(int i) {
        String str;
        SparseArray<String> sparseArray = this.mSelectorIndexToStringCache;
        if (sparseArray.get(i) != null) {
            return;
        }
        int i2 = this.mMinValue;
        if (i < i2 || i > this.mMaxValue) {
            str = "";
        } else {
            String[] strArr = this.mDisplayedValues;
            if (strArr != null) {
                int i3 = i - i2;
                if (i3 >= strArr.length) {
                    sparseArray.remove(i);
                    return;
                }
                str = strArr[i3];
            } else {
                str = formatNumber(i);
            }
        }
        sparseArray.put(i, str);
    }

    private String formatNumber(int i) {
        Formatter formatter = this.mFormatter;
        return formatter != null ? formatter.format(i) : formatNumberWithLocale(i);
    }

    private void updateInputTextView() {
        String[] strArr = this.mDisplayedValues;
        String formatNumber = strArr == null ? formatNumber(this.mValue) : strArr[this.mValue - this.mMinValue];
        if (TextUtils.isEmpty(formatNumber) || formatNumber.equals(this.mSelectedText.getText().toString())) {
            return;
        }
        EditText editText = this.mSelectedText;
        editText.setText(formatNumber + this.label);
    }

    private void notifyChange(int i, int i2) {
        OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
        if (onValueChangeListener != null) {
            onValueChangeListener.onValueChange(this, i, i2);
        }
    }

    private void postChangeCurrentByOneFromLongPress(boolean z, long j) {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand == null) {
            this.mChangeCurrentByOneFromLongPressCommand = new ChangeCurrentByOneFromLongPressCommand();
        } else {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        this.mChangeCurrentByOneFromLongPressCommand.setStep(z);
        postDelayed(this.mChangeCurrentByOneFromLongPressCommand, j);
    }

    private void postChangeCurrentByOneFromLongPress(boolean z) {
        postChangeCurrentByOneFromLongPress(z, ViewConfiguration.getLongPressTimeout());
    }

    private void removeChangeCurrentByOneFromLongPress() {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
    }

    private void removeAllCallbacks() {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        SetSelectionCommand setSelectionCommand = this.mSetSelectionCommand;
        if (setSelectionCommand != null) {
            setSelectionCommand.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSelectedPos(String str) {
        try {
            if (this.mDisplayedValues == null) {
                return Integer.parseInt(str);
            }
            for (int i = 0; i < this.mDisplayedValues.length; i++) {
                str = str.toLowerCase();
                if (this.mDisplayedValues[i].toLowerCase().startsWith(str)) {
                    return this.mMinValue + i;
                }
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return this.mMinValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postSetSelectionCommand(int i, int i2) {
        SetSelectionCommand setSelectionCommand = this.mSetSelectionCommand;
        if (setSelectionCommand == null) {
            this.mSetSelectionCommand = new SetSelectionCommand(this.mSelectedText);
        } else {
            setSelectionCommand.post(i, i2);
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$InputTextFilter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class InputTextFilter extends NumberKeyListener {
        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 1;
        }

        InputTextFilter() {
        }

        @Override // android.text.method.NumberKeyListener
        protected char[] getAcceptedChars() {
            return NumberPicker.DIGIT_CHARACTERS;
        }

        @Override // android.text.method.NumberKeyListener, android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            String valueOf;
            String[] strArr;
            if (NumberPicker.this.mSetSelectionCommand != null) {
                NumberPicker.this.mSetSelectionCommand.cancel();
            }
            if (NumberPicker.this.mDisplayedValues == null) {
                CharSequence filter2 = super.filter(charSequence, i, i2, spanned, i3, i4);
                if (filter2 == null) {
                    filter2 = charSequence.subSequence(i, i2);
                }
                String str = String.valueOf(spanned.subSequence(0, i3)) + ((Object) filter2) + ((Object) spanned.subSequence(i4, spanned.length()));
                return "".equals(str) ? str : (NumberPicker.this.getSelectedPos(str) > NumberPicker.this.mMaxValue || str.length() > String.valueOf(NumberPicker.this.mMaxValue).length()) ? "" : filter2;
            }
            if (TextUtils.isEmpty(String.valueOf(charSequence.subSequence(i, i2)))) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i3)) + ((Object) valueOf) + ((Object) spanned.subSequence(i4, spanned.length()));
            String lowerCase = String.valueOf(str2).toLowerCase();
            for (String str3 : NumberPicker.this.mDisplayedValues) {
                if (str3.toLowerCase().startsWith(lowerCase)) {
                    NumberPicker.this.postSetSelectionCommand(str2.length(), str3.length());
                    return str3.subSequence(i3, str3.length());
                }
            }
            return "";
        }
    }

    private void ensureScrollWheelAdjusted() {
        int i;
        int i2 = this.mInitialScrollOffset - this.mCurrentScrollOffset;
        if (i2 == 0) {
            return;
        }
        int abs = Math.abs(i2);
        int i3 = this.mSelectorElementSize;
        if (abs > i3 / 2) {
            if (i2 > 0) {
                i3 = -i3;
            }
            i = i2 + i3;
        } else {
            i = i2;
        }
        if (isHorizontalMode()) {
            this.mPreviousScrollerX = 0;
            this.mAdjustScroller.startScroll(0, 0, i, 0, 800);
        } else {
            this.mPreviousScrollerY = 0;
            this.mAdjustScroller.startScroll(0, 0, 0, i, 800);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$SetSelectionCommand */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class SetSelectionCommand implements Runnable {
        private final EditText mInputText;
        private boolean mPosted;
        private int mSelectionEnd;
        private int mSelectionStart;

        SetSelectionCommand(EditText editText) {
            this.mInputText = editText;
        }

        void post(int i, int i2) {
            this.mSelectionStart = i;
            this.mSelectionEnd = i2;
            if (this.mPosted) {
                return;
            }
            this.mInputText.post(this);
            this.mPosted = true;
        }

        void cancel() {
            if (this.mPosted) {
                this.mInputText.removeCallbacks(this);
                this.mPosted = false;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mPosted = false;
            this.mInputText.setSelection(this.mSelectionStart, this.mSelectionEnd);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker$ChangeCurrentByOneFromLongPressCommand */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ChangeCurrentByOneFromLongPressCommand implements Runnable {
        private boolean mIncrement;

        ChangeCurrentByOneFromLongPressCommand() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStep(boolean z) {
            this.mIncrement = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.changeValueByOne(this.mIncrement);
            NumberPicker numberPicker = NumberPicker.this;
            numberPicker.postDelayed(this, numberPicker.mLongPressUpdateInterval);
        }
    }

    private String formatNumberWithLocale(int i) {
        return i + "";
    }

    private float dpToPx(float f) {
        return f * getResources().getDisplayMetrics().density;
    }

    private float pxToDp(float f) {
        return f / getResources().getDisplayMetrics().density;
    }

    private float spToPx(float f) {
        return TypedValue.applyDimension(2, f, getResources().getDisplayMetrics());
    }

    private float pxToSp(float f) {
        return f / getResources().getDisplayMetrics().scaledDensity;
    }

    private Formatter stringToFormatter(final String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new Formatter() { // from class: com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker.1
            @Override // com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker.Formatter
            public String format(int i) {
                return String.format(Locale.getDefault(), str, Integer.valueOf(i));
            }
        };
    }

    private void setWidthAndHeight() {
        if (isHorizontalMode()) {
            this.mMinHeight = -1;
            this.mMaxHeight = (int) dpToPx(58.0f);
            this.mMinWidth = (int) dpToPx(180.0f);
            this.mMaxWidth = -1;
            return;
        }
        this.mMinHeight = -1;
        this.mMaxHeight = (int) dpToPx(180.0f);
        this.mMinWidth = (int) dpToPx(58.0f);
        this.mMaxWidth = -1;
    }

    public void setAccessibilityDescriptionEnabled(boolean z) {
        this.mAccessibilityDescriptionEnabled = z;
    }

    public void setDividerColor(@ColorInt int i) {
        this.mDividerColor = i;
        this.mDividerDrawable = new ColorDrawable(i);
        invalidate();
    }

    public void setDividerColorResource(@ColorRes int i) {
        setDividerColor(ContextCompat.getColor(this.mContext, i));
    }

    public void setDividerDistance(int i) {
        this.mDividerDistance = i;
    }

    public void setDividerDistanceResource(@DimenRes int i) {
        setDividerDistance(getResources().getDimensionPixelSize(i));
    }

    public void setDividerType(int i) {
        this.mDividerType = i;
        invalidate();
    }

    public void setDividerThickness(int i) {
        this.mDividerThickness = i;
    }

    public void setDividerThicknessResource(@DimenRes int i) {
        setDividerThickness(getResources().getDimensionPixelSize(i));
    }

    public void setOrder(int i) {
        this.mOrder = i;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        this.mOrientation = i;
        setWidthAndHeight();
        requestLayout();
    }

    public void setWheelItemCount(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Wheel item count must be >= 1");
        }
        this.mRealWheelItemCount = i;
        this.mWheelItemCount = Math.max(i, 3);
        int i2 = this.mWheelItemCount;
        this.mWheelMiddleItemIndex = i2 / 2;
        this.mSelectorIndices = new int[i2];
    }

    public void setFormatter(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setFormatter(stringToFormatter(str));
    }

    public void setFormatter(@StringRes int i) {
        setFormatter(getResources().getString(i));
    }

    public void setFadingEdgeEnabled(boolean z) {
        this.mFadingEdgeEnabled = z;
    }

    public void setFadingEdgeStrength(float f) {
        this.mFadingEdgeStrength = f;
    }

    public void setScrollerEnabled(boolean z) {
        this.mScrollerEnabled = z;
    }

    public void setSelectedTextAlign(int i) {
        this.mSelectedTextAlign = i;
    }

    public void setSelectedTextColor(@ColorInt int i) {
        this.mSelectedTextColor = i;
        this.mSelectedText.setTextColor(this.mSelectedTextColor);
        invalidate();
    }

    public void setSelectedTextColorResource(@ColorRes int i) {
        setSelectedTextColor(ContextCompat.getColor(this.mContext, i));
    }

    public void setSelectedTextSize(float f) {
        this.mSelectedTextSize = f;
        this.mSelectedText.setTextSize(pxToSp(this.mSelectedTextSize));
        invalidate();
    }

    public void setSelectedTextSize(@DimenRes int i) {
        setSelectedTextSize(getResources().getDimension(i));
    }

    public void setSelectedTextStrikeThru(boolean z) {
        this.mSelectedTextStrikeThru = z;
    }

    public void setSelectedTextUnderline(boolean z) {
        this.mSelectedTextUnderline = z;
    }

    public void setSelectedTypeface(Typeface typeface) {
        this.mSelectedTypeface = typeface;
        Typeface typeface2 = this.mSelectedTypeface;
        if (typeface2 != null) {
            this.mSelectorWheelPaint.setTypeface(typeface2);
        } else {
            Typeface typeface3 = this.mTypeface;
            if (typeface3 != null) {
                this.mSelectorWheelPaint.setTypeface(typeface3);
            } else {
                this.mSelectorWheelPaint.setTypeface(Typeface.MONOSPACE);
            }
        }
        invalidate();
    }

    public void setSelectedTypeface(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setSelectedTypeface(Typeface.create(str, i));
    }

    public void setSelectedTypeface(String str) {
        setSelectedTypeface(str, 0);
    }

    public void setSelectedTypeface(@StringRes int i, int i2) {
        setSelectedTypeface(getResources().getString(i), i2);
    }

    public void setSelectedTypeface(@StringRes int i) {
        setSelectedTypeface(i, 0);
    }

    public void setTextAlign(int i) {
        this.mTextAlign = i;
    }

    public void setTextColor(@ColorInt int i) {
        this.mTextColor = i;
        this.mSelectorWheelPaint.setColor(this.mTextColor);
        invalidate();
    }

    public void setTextColorResource(@ColorRes int i) {
        setTextColor(ContextCompat.getColor(this.mContext, i));
    }

    public void setTextSize(float f) {
        this.mTextSize = f;
        this.mSelectorWheelPaint.setTextSize(this.mTextSize);
        invalidate();
    }

    public void setTextSize(@DimenRes int i) {
        setTextSize(getResources().getDimension(i));
    }

    public void setTextStrikeThru(boolean z) {
        this.mTextStrikeThru = z;
    }

    public void setTextUnderline(boolean z) {
        this.mTextUnderline = z;
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
        Typeface typeface2 = this.mTypeface;
        if (typeface2 != null) {
            this.mSelectedText.setTypeface(typeface2);
            setSelectedTypeface(this.mSelectedTypeface);
        } else {
            this.mSelectedText.setTypeface(Typeface.MONOSPACE);
        }
        invalidate();
    }

    public void setTypeface(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setTypeface(Typeface.create(str, i));
    }

    public void setTypeface(String str) {
        setTypeface(str, 0);
    }

    public void setTypeface(@StringRes int i, int i2) {
        setTypeface(getResources().getString(i), i2);
    }

    public void setTypeface(@StringRes int i) {
        setTypeface(i, 0);
    }

    public void setLineSpacingMultiplier(float f) {
        this.mLineSpacingMultiplier = f;
    }

    public void setMaxFlingVelocityCoefficient(int i) {
        this.mMaxFlingVelocityCoefficient = i;
        this.mMaximumFlingVelocity = this.mViewConfiguration.getScaledMaximumFlingVelocity() / this.mMaxFlingVelocityCoefficient;
    }

    public void setItemSpacing(int i) {
        this.mItemSpacing = i;
    }

    public boolean isHorizontalMode() {
        return getOrientation() == 0;
    }

    public boolean isAscendingOrder() {
        return getOrder() == 0;
    }

    public boolean isAccessibilityDescriptionEnabled() {
        return this.mAccessibilityDescriptionEnabled;
    }

    public int getDividerColor() {
        return this.mDividerColor;
    }

    public float getDividerDistance() {
        return pxToDp(this.mDividerDistance);
    }

    public float getDividerThickness() {
        return pxToDp(this.mDividerThickness);
    }

    public int getOrder() {
        return this.mOrder;
    }

    @Override // android.widget.LinearLayout
    public int getOrientation() {
        return this.mOrientation;
    }

    public int getWheelItemCount() {
        return this.mWheelItemCount;
    }

    public Formatter getFormatter() {
        return this.mFormatter;
    }

    public boolean isFadingEdgeEnabled() {
        return this.mFadingEdgeEnabled;
    }

    public float getFadingEdgeStrength() {
        return this.mFadingEdgeStrength;
    }

    public boolean isScrollerEnabled() {
        return this.mScrollerEnabled;
    }

    public int getSelectedTextAlign() {
        return this.mSelectedTextAlign;
    }

    public int getSelectedTextColor() {
        return this.mSelectedTextColor;
    }

    public float getSelectedTextSize() {
        return this.mSelectedTextSize;
    }

    public boolean getSelectedTextStrikeThru() {
        return this.mSelectedTextStrikeThru;
    }

    public boolean getSelectedTextUnderline() {
        return this.mSelectedTextUnderline;
    }

    public int getTextAlign() {
        return this.mTextAlign;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public float getTextSize() {
        return spToPx(this.mTextSize);
    }

    public boolean getTextStrikeThru() {
        return this.mTextStrikeThru;
    }

    public boolean getTextUnderline() {
        return this.mTextUnderline;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public float getLineSpacingMultiplier() {
        return this.mLineSpacingMultiplier;
    }

    public int getMaxFlingVelocityCoefficient() {
        return this.mMaxFlingVelocityCoefficient;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getLabel() {
        return this.label;
    }

    public boolean isTextBold() {
        return this.textBold;
    }

    public void setTextBold(boolean z) {
        this.textBold = z;
    }

    public boolean isSelectedTextBold() {
        return this.selectedTextBold;
    }

    public void setSelectedTextBold(boolean z) {
        this.selectedTextBold = z;
    }
}
