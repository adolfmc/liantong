package com.zhy.android.percent.support;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.p083v4.view.MarginLayoutParamsCompat;
import android.support.p083v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PercentLayoutHelper {
    private static final String REGEX_PERCENT = "^(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)%([s]?[wh]?)$";
    private static final String TAG = "PercentLayout";
    private static int mHeightScreen;
    private static int mWidthScreen;
    private final ViewGroup mHost;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface PercentLayoutParams {
        PercentLayoutInfo getPercentLayoutInfo();
    }

    public PercentLayoutHelper(ViewGroup viewGroup) {
        this.mHost = viewGroup;
        getScreenSize();
    }

    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.mHost.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        mWidthScreen = displayMetrics.widthPixels;
        mHeightScreen = displayMetrics.heightPixels;
    }

    public static void fetchWidthAndHeight(ViewGroup.LayoutParams layoutParams, TypedArray typedArray, int i, int i2) {
        layoutParams.width = typedArray.getLayoutDimension(i, 0);
        layoutParams.height = typedArray.getLayoutDimension(i2, 0);
    }

    public void adjustChildren(int i, int i2) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "adjustChildren: " + this.mHost + " widthMeasureSpec: " + View.MeasureSpec.toString(i) + " heightMeasureSpec: " + View.MeasureSpec.toString(i2));
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "widthHint = " + size + " , heightHint = " + size2);
        }
        int childCount = this.mHost.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.mHost.getChildAt(i3);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "should adjust " + childAt + " " + layoutParams);
            }
            if (layoutParams instanceof PercentLayoutParams) {
                PercentLayoutInfo percentLayoutInfo = ((PercentLayoutParams) layoutParams).getPercentLayoutInfo();
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "using " + percentLayoutInfo);
                }
                if (percentLayoutInfo != null) {
                    supportTextSize(size, size2, childAt, percentLayoutInfo);
                    supportPadding(size, size2, childAt, percentLayoutInfo);
                    supportMinOrMaxDimesion(size, size2, childAt, percentLayoutInfo);
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        percentLayoutInfo.fillMarginLayoutParams((ViewGroup.MarginLayoutParams) layoutParams, size, size2);
                    } else {
                        percentLayoutInfo.fillLayoutParams(layoutParams, size, size2);
                    }
                }
            }
        }
    }

    private void supportPadding(int i, int i2, View view, PercentLayoutInfo percentLayoutInfo) {
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        PercentLayoutInfo.PercentVal percentVal = percentLayoutInfo.paddingLeftPercent;
        if (percentVal != null) {
            paddingLeft = (int) (getBaseByModeAndVal(i, i2, percentVal.basemode) * percentVal.percent);
        }
        PercentLayoutInfo.PercentVal percentVal2 = percentLayoutInfo.paddingRightPercent;
        if (percentVal2 != null) {
            paddingRight = (int) (getBaseByModeAndVal(i, i2, percentVal2.basemode) * percentVal2.percent);
        }
        PercentLayoutInfo.PercentVal percentVal3 = percentLayoutInfo.paddingTopPercent;
        if (percentVal3 != null) {
            paddingTop = (int) (getBaseByModeAndVal(i, i2, percentVal3.basemode) * percentVal3.percent);
        }
        PercentLayoutInfo.PercentVal percentVal4 = percentLayoutInfo.paddingBottomPercent;
        if (percentVal4 != null) {
            paddingBottom = (int) (getBaseByModeAndVal(i, i2, percentVal4.basemode) * percentVal4.percent);
        }
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    private void supportMinOrMaxDimesion(int i, int i2, View view, PercentLayoutInfo percentLayoutInfo) {
        try {
            Class<?> cls = view.getClass();
            invokeMethod("setMaxWidth", i, i2, view, cls, percentLayoutInfo.maxWidthPercent);
            invokeMethod("setMaxHeight", i, i2, view, cls, percentLayoutInfo.maxHeightPercent);
            invokeMethod("setMinWidth", i, i2, view, cls, percentLayoutInfo.minWidthPercent);
            invokeMethod("setMinHeight", i, i2, view, cls, percentLayoutInfo.minHeightPercent);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    private void invokeMethod(String str, int i, int i2, View view, Class cls, PercentLayoutInfo.PercentVal percentVal) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, str + " ==> " + percentVal);
        }
        if (percentVal != null) {
            Method method = cls.getMethod(str, Integer.TYPE);
            method.setAccessible(true);
            method.invoke(view, Integer.valueOf((int) (getBaseByModeAndVal(i, i2, percentVal.basemode) * percentVal.percent)));
        }
    }

    private void supportTextSize(int i, int i2, View view, PercentLayoutInfo percentLayoutInfo) {
        PercentLayoutInfo.PercentVal percentVal = percentLayoutInfo.textSizePercent;
        if (percentVal == null) {
            return;
        }
        float baseByModeAndVal = (int) (getBaseByModeAndVal(i, i2, percentVal.basemode) * percentVal.percent);
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(0, baseByModeAndVal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getBaseByModeAndVal(int i, int i2, PercentLayoutInfo.BASEMODE basemode) {
        switch (basemode) {
            case BASE_HEIGHT:
                return i2;
            case BASE_WIDTH:
                return i;
            case BASE_SCREEN_WIDTH:
                return mWidthScreen;
            case BASE_SCREEN_HEIGHT:
                return mHeightScreen;
            default:
                return 0;
        }
    }

    public static PercentLayoutInfo getPercentLayoutInfo(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C11714R.styleable.PercentLayout_Layout2);
        PercentLayoutInfo paddingRelatedVal = setPaddingRelatedVal(obtainStyledAttributes, setMinMaxWidthHeightRelatedVal(obtainStyledAttributes, setTextSizeSupportVal(obtainStyledAttributes, setMarginRelatedVal(obtainStyledAttributes, setWidthAndHeightVal(obtainStyledAttributes, null)))));
        obtainStyledAttributes.recycle();
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "constructed: " + paddingRelatedVal);
        }
        return paddingRelatedVal;
    }

    private static PercentLayoutInfo setWidthAndHeightVal(TypedArray typedArray, PercentLayoutInfo percentLayoutInfo) {
        PercentLayoutInfo.PercentVal percentVal = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_widthPercent2, true);
        if (percentVal != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent width: " + percentVal.percent);
            }
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.widthPercent = percentVal;
        }
        PercentLayoutInfo.PercentVal percentVal2 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_heightPercent2, false);
        if (percentVal2 != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent height: " + percentVal2.percent);
            }
            PercentLayoutInfo checkForInfoExists = checkForInfoExists(percentLayoutInfo);
            checkForInfoExists.heightPercent = percentVal2;
            return checkForInfoExists;
        }
        return percentLayoutInfo;
    }

    private static PercentLayoutInfo setTextSizeSupportVal(TypedArray typedArray, PercentLayoutInfo percentLayoutInfo) {
        PercentLayoutInfo.PercentVal percentVal = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_textSizePercent2, false);
        if (percentVal != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent text size: " + percentVal.percent);
            }
            PercentLayoutInfo checkForInfoExists = checkForInfoExists(percentLayoutInfo);
            checkForInfoExists.textSizePercent = percentVal;
            return checkForInfoExists;
        }
        return percentLayoutInfo;
    }

    private static PercentLayoutInfo setMinMaxWidthHeightRelatedVal(TypedArray typedArray, PercentLayoutInfo percentLayoutInfo) {
        PercentLayoutInfo.PercentVal percentVal = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_maxWidthPercent2, true);
        if (percentVal != null) {
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.maxWidthPercent = percentVal;
        }
        PercentLayoutInfo.PercentVal percentVal2 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_maxHeightPercent2, false);
        if (percentVal2 != null) {
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.maxHeightPercent = percentVal2;
        }
        PercentLayoutInfo.PercentVal percentVal3 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_minWidthPercent2, true);
        if (percentVal3 != null) {
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.minWidthPercent = percentVal3;
        }
        PercentLayoutInfo.PercentVal percentVal4 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_minHeightPercent2, false);
        if (percentVal4 != null) {
            PercentLayoutInfo checkForInfoExists = checkForInfoExists(percentLayoutInfo);
            checkForInfoExists.minHeightPercent = percentVal4;
            return checkForInfoExists;
        }
        return percentLayoutInfo;
    }

    private static PercentLayoutInfo setMarginRelatedVal(TypedArray typedArray, PercentLayoutInfo percentLayoutInfo) {
        PercentLayoutInfo.PercentVal percentVal = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_marginPercent2, true);
        if (percentVal != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent margin: " + percentVal.percent);
            }
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.leftMarginPercent = percentVal;
            percentLayoutInfo.topMarginPercent = percentVal;
            percentLayoutInfo.rightMarginPercent = percentVal;
            percentLayoutInfo.bottomMarginPercent = percentVal;
        }
        PercentLayoutInfo.PercentVal percentVal2 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_marginLeftPercent2, true);
        if (percentVal2 != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent left margin: " + percentVal2.percent);
            }
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.leftMarginPercent = percentVal2;
        }
        PercentLayoutInfo.PercentVal percentVal3 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_marginTopPercent2, false);
        if (percentVal3 != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent top margin: " + percentVal3.percent);
            }
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.topMarginPercent = percentVal3;
        }
        PercentLayoutInfo.PercentVal percentVal4 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_marginRightPercent2, true);
        if (percentVal4 != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent right margin: " + percentVal4.percent);
            }
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.rightMarginPercent = percentVal4;
        }
        PercentLayoutInfo.PercentVal percentVal5 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_marginBottomPercent2, false);
        if (percentVal5 != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent bottom margin: " + percentVal5.percent);
            }
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.bottomMarginPercent = percentVal5;
        }
        PercentLayoutInfo.PercentVal percentVal6 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_marginStartPercent2, true);
        if (percentVal6 != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent start margin: " + percentVal6.percent);
            }
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.startMarginPercent = percentVal6;
        }
        PercentLayoutInfo.PercentVal percentVal7 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_marginEndPercent2, true);
        if (percentVal7 != null) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "percent end margin: " + percentVal7.percent);
            }
            PercentLayoutInfo checkForInfoExists = checkForInfoExists(percentLayoutInfo);
            checkForInfoExists.endMarginPercent = percentVal7;
            return checkForInfoExists;
        }
        return percentLayoutInfo;
    }

    private static PercentLayoutInfo setPaddingRelatedVal(TypedArray typedArray, PercentLayoutInfo percentLayoutInfo) {
        PercentLayoutInfo.PercentVal percentVal = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_paddingPercent2, true);
        if (percentVal != null) {
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.paddingLeftPercent = percentVal;
            percentLayoutInfo.paddingRightPercent = percentVal;
            percentLayoutInfo.paddingBottomPercent = percentVal;
            percentLayoutInfo.paddingTopPercent = percentVal;
        }
        PercentLayoutInfo.PercentVal percentVal2 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_paddingLeftPercent2, true);
        if (percentVal2 != null) {
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.paddingLeftPercent = percentVal2;
        }
        PercentLayoutInfo.PercentVal percentVal3 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_paddingRightPercent2, true);
        if (percentVal3 != null) {
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.paddingRightPercent = percentVal3;
        }
        PercentLayoutInfo.PercentVal percentVal4 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_paddingTopPercent2, true);
        if (percentVal4 != null) {
            percentLayoutInfo = checkForInfoExists(percentLayoutInfo);
            percentLayoutInfo.paddingTopPercent = percentVal4;
        }
        PercentLayoutInfo.PercentVal percentVal5 = getPercentVal(typedArray, C11714R.styleable.PercentLayout_Layout2_layout_paddingBottomPercent2, true);
        if (percentVal5 != null) {
            PercentLayoutInfo checkForInfoExists = checkForInfoExists(percentLayoutInfo);
            checkForInfoExists.paddingBottomPercent = percentVal5;
            return checkForInfoExists;
        }
        return percentLayoutInfo;
    }

    private static PercentLayoutInfo.PercentVal getPercentVal(TypedArray typedArray, int i, boolean z) {
        return getPercentVal(typedArray.getString(i), z);
    }

    @NonNull
    private static PercentLayoutInfo checkForInfoExists(PercentLayoutInfo percentLayoutInfo) {
        return percentLayoutInfo != null ? percentLayoutInfo : new PercentLayoutInfo();
    }

    private static PercentLayoutInfo.PercentVal getPercentVal(String str, boolean z) {
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile(REGEX_PERCENT).matcher(str);
        if (!matcher.matches()) {
            throw new RuntimeException("the value of layout_xxxPercent invalid! ==>" + str);
        }
        int length = str.length();
        String group2 = matcher.group(1);
        str.substring(length - 1);
        PercentLayoutInfo.PercentVal percentVal = new PercentLayoutInfo.PercentVal();
        percentVal.percent = Float.parseFloat(group2) / 100.0f;
        if (str.endsWith("sw")) {
            percentVal.basemode = PercentLayoutInfo.BASEMODE.BASE_SCREEN_WIDTH;
        } else if (str.endsWith("sh")) {
            percentVal.basemode = PercentLayoutInfo.BASEMODE.BASE_SCREEN_HEIGHT;
        } else if (str.endsWith("%")) {
            if (z) {
                percentVal.basemode = PercentLayoutInfo.BASEMODE.BASE_WIDTH;
            } else {
                percentVal.basemode = PercentLayoutInfo.BASEMODE.BASE_HEIGHT;
            }
        } else if (str.endsWith("w")) {
            percentVal.basemode = PercentLayoutInfo.BASEMODE.BASE_WIDTH;
        } else if (str.endsWith("h")) {
            percentVal.basemode = PercentLayoutInfo.BASEMODE.BASE_HEIGHT;
        } else {
            throw new IllegalArgumentException("the " + str + " must be endWith [%|w|h|sw|sh]");
        }
        return percentVal;
    }

    public void restoreOriginalParams() {
        int childCount = this.mHost.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mHost.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "should restore " + childAt + " " + layoutParams);
            }
            if (layoutParams instanceof PercentLayoutParams) {
                PercentLayoutInfo percentLayoutInfo = ((PercentLayoutParams) layoutParams).getPercentLayoutInfo();
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "using " + percentLayoutInfo);
                }
                if (percentLayoutInfo != null) {
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        percentLayoutInfo.restoreMarginLayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
                    } else {
                        percentLayoutInfo.restoreLayoutParams(layoutParams);
                    }
                }
            }
        }
    }

    public boolean handleMeasuredStateTooSmall() {
        PercentLayoutInfo percentLayoutInfo;
        int childCount = this.mHost.getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mHost.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "should handle measured state too small " + childAt + " " + layoutParams);
            }
            if ((layoutParams instanceof PercentLayoutParams) && (percentLayoutInfo = ((PercentLayoutParams) layoutParams).getPercentLayoutInfo()) != null) {
                if (shouldHandleMeasuredWidthTooSmall(childAt, percentLayoutInfo)) {
                    layoutParams.width = -2;
                    z = true;
                }
                if (shouldHandleMeasuredHeightTooSmall(childAt, percentLayoutInfo)) {
                    layoutParams.height = -2;
                    z = true;
                }
            }
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "should trigger second measure pass: " + z);
        }
        return z;
    }

    private static boolean shouldHandleMeasuredWidthTooSmall(View view, PercentLayoutInfo percentLayoutInfo) {
        return percentLayoutInfo != null && percentLayoutInfo.widthPercent != null && (ViewCompat.getMeasuredWidthAndState(view) & (-16777216)) == 16777216 && percentLayoutInfo.widthPercent.percent >= 0.0f && percentLayoutInfo.mPreservedParams.width == -2;
    }

    private static boolean shouldHandleMeasuredHeightTooSmall(View view, PercentLayoutInfo percentLayoutInfo) {
        return percentLayoutInfo != null && percentLayoutInfo.heightPercent != null && (ViewCompat.getMeasuredHeightAndState(view) & (-16777216)) == 16777216 && percentLayoutInfo.heightPercent.percent >= 0.0f && percentLayoutInfo.mPreservedParams.height == -2;
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class PercentLayoutInfo {
        public PercentVal bottomMarginPercent;
        public PercentVal endMarginPercent;
        public PercentVal heightPercent;
        public PercentVal leftMarginPercent;
        final ViewGroup.MarginLayoutParams mPreservedParams = new ViewGroup.MarginLayoutParams(0, 0);
        public PercentVal maxHeightPercent;
        public PercentVal maxWidthPercent;
        public PercentVal minHeightPercent;
        public PercentVal minWidthPercent;
        public PercentVal paddingBottomPercent;
        public PercentVal paddingLeftPercent;
        public PercentVal paddingRightPercent;
        public PercentVal paddingTopPercent;
        public PercentVal rightMarginPercent;
        public PercentVal startMarginPercent;
        public PercentVal textSizePercent;
        public PercentVal topMarginPercent;
        public PercentVal widthPercent;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public enum BASEMODE {
            BASE_WIDTH,
            BASE_HEIGHT,
            BASE_SCREEN_WIDTH,
            BASE_SCREEN_HEIGHT;
            

            /* renamed from: H */
            public static final String f23828H = "h";
            public static final String PERCENT = "%";

            /* renamed from: SH */
            public static final String f23829SH = "sh";

            /* renamed from: SW */
            public static final String f23830SW = "sw";

            /* renamed from: W */
            public static final String f23831W = "w";
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class PercentVal {
            public BASEMODE basemode;
            public float percent;

            public PercentVal() {
                this.percent = -1.0f;
            }

            public PercentVal(float f, BASEMODE basemode) {
                this.percent = -1.0f;
                this.percent = f;
                this.basemode = basemode;
            }

            public String toString() {
                return "PercentVal{percent=" + this.percent + ", basemode=" + this.basemode.name() + '}';
            }
        }

        public void fillLayoutParams(ViewGroup.LayoutParams layoutParams, int i, int i2) {
            this.mPreservedParams.width = layoutParams.width;
            this.mPreservedParams.height = layoutParams.height;
            PercentVal percentVal = this.widthPercent;
            if (percentVal != null) {
                layoutParams.width = (int) (PercentLayoutHelper.getBaseByModeAndVal(i, i2, percentVal.basemode) * this.widthPercent.percent);
            }
            PercentVal percentVal2 = this.heightPercent;
            if (percentVal2 != null) {
                layoutParams.height = (int) (PercentLayoutHelper.getBaseByModeAndVal(i, i2, percentVal2.basemode) * this.heightPercent.percent);
            }
            if (Log.isLoggable(PercentLayoutHelper.TAG, 3)) {
                Log.d(PercentLayoutHelper.TAG, "after fillLayoutParams: (" + layoutParams.width + ", " + layoutParams.height + ")");
            }
        }

        public void fillMarginLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
            fillLayoutParams(marginLayoutParams, i, i2);
            this.mPreservedParams.leftMargin = marginLayoutParams.leftMargin;
            this.mPreservedParams.topMargin = marginLayoutParams.topMargin;
            this.mPreservedParams.rightMargin = marginLayoutParams.rightMargin;
            this.mPreservedParams.bottomMargin = marginLayoutParams.bottomMargin;
            MarginLayoutParamsCompat.setMarginStart(this.mPreservedParams, MarginLayoutParamsCompat.getMarginStart(marginLayoutParams));
            MarginLayoutParamsCompat.setMarginEnd(this.mPreservedParams, MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams));
            PercentVal percentVal = this.leftMarginPercent;
            if (percentVal != null) {
                marginLayoutParams.leftMargin = (int) (PercentLayoutHelper.getBaseByModeAndVal(i, i2, percentVal.basemode) * this.leftMarginPercent.percent);
            }
            PercentVal percentVal2 = this.topMarginPercent;
            if (percentVal2 != null) {
                marginLayoutParams.topMargin = (int) (PercentLayoutHelper.getBaseByModeAndVal(i, i2, percentVal2.basemode) * this.topMarginPercent.percent);
            }
            PercentVal percentVal3 = this.rightMarginPercent;
            if (percentVal3 != null) {
                marginLayoutParams.rightMargin = (int) (PercentLayoutHelper.getBaseByModeAndVal(i, i2, percentVal3.basemode) * this.rightMarginPercent.percent);
            }
            PercentVal percentVal4 = this.bottomMarginPercent;
            if (percentVal4 != null) {
                marginLayoutParams.bottomMargin = (int) (PercentLayoutHelper.getBaseByModeAndVal(i, i2, percentVal4.basemode) * this.bottomMarginPercent.percent);
            }
            PercentVal percentVal5 = this.startMarginPercent;
            if (percentVal5 != null) {
                MarginLayoutParamsCompat.setMarginStart(marginLayoutParams, (int) (PercentLayoutHelper.getBaseByModeAndVal(i, i2, percentVal5.basemode) * this.startMarginPercent.percent));
            }
            PercentVal percentVal6 = this.endMarginPercent;
            if (percentVal6 != null) {
                MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, (int) (PercentLayoutHelper.getBaseByModeAndVal(i, i2, percentVal6.basemode) * this.endMarginPercent.percent));
            }
            if (Log.isLoggable(PercentLayoutHelper.TAG, 3)) {
                Log.d(PercentLayoutHelper.TAG, "after fillMarginLayoutParams: (" + marginLayoutParams.width + ", " + marginLayoutParams.height + ")");
            }
        }

        public String toString() {
            return "PercentLayoutInfo{widthPercent=" + this.widthPercent + ", heightPercent=" + this.heightPercent + ", leftMarginPercent=" + this.leftMarginPercent + ", topMarginPercent=" + this.topMarginPercent + ", rightMarginPercent=" + this.rightMarginPercent + ", bottomMarginPercent=" + this.bottomMarginPercent + ", startMarginPercent=" + this.startMarginPercent + ", endMarginPercent=" + this.endMarginPercent + ", textSizePercent=" + this.textSizePercent + ", maxWidthPercent=" + this.maxWidthPercent + ", maxHeightPercent=" + this.maxHeightPercent + ", minWidthPercent=" + this.minWidthPercent + ", minHeightPercent=" + this.minHeightPercent + ", paddingLeftPercent=" + this.paddingLeftPercent + ", paddingRightPercent=" + this.paddingRightPercent + ", paddingTopPercent=" + this.paddingTopPercent + ", paddingBottomPercent=" + this.paddingBottomPercent + ", mPreservedParams=" + this.mPreservedParams + '}';
        }

        public void restoreMarginLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            restoreLayoutParams(marginLayoutParams);
            marginLayoutParams.leftMargin = this.mPreservedParams.leftMargin;
            marginLayoutParams.topMargin = this.mPreservedParams.topMargin;
            marginLayoutParams.rightMargin = this.mPreservedParams.rightMargin;
            marginLayoutParams.bottomMargin = this.mPreservedParams.bottomMargin;
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams, MarginLayoutParamsCompat.getMarginStart(this.mPreservedParams));
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, MarginLayoutParamsCompat.getMarginEnd(this.mPreservedParams));
        }

        public void restoreLayoutParams(ViewGroup.LayoutParams layoutParams) {
            layoutParams.width = this.mPreservedParams.width;
            layoutParams.height = this.mPreservedParams.height;
        }
    }
}
