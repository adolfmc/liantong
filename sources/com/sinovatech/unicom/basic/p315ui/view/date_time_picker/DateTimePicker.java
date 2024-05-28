package com.sinovatech.unicom.basic.p315ui.view.date_time_picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.p083v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.example.asus.detectionandalign.animation.C4280b;
import com.loper7.date_time_picker.controller.BaseDateTimeController;
import com.loper7.date_time_picker.controller.DateTimeController;
import com.loper7.date_time_picker.controller.DateTimeInterface;
import com.loper7.tab_expand.ext.ContextExt;
import com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker;
import com.sinovatech.unicom.p318ui.C9718R;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DateTimePicker.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u000bJ\u0010\u0010)\u001a\u00020*2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\b\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0010\u001a\u00020\bJ\b\u0010.\u001a\u00020*H\u0002J\u0010\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020,H\u0016J\u0010\u00101\u001a\u00020*2\b\u00102\u001a\u0004\u0018\u00010\u0011J\u0010\u00103\u001a\u00020*2\b\b\u0001\u00104\u001a\u00020\bJ\u000e\u00105\u001a\u00020*2\u0006\u0010\u0013\u001a\u00020\bJB\u00106\u001a\u00020*2\b\b\u0002\u00107\u001a\u00020\u000f2\b\b\u0002\u00108\u001a\u00020\u000f2\b\b\u0002\u00109\u001a\u00020\u000f2\b\b\u0002\u0010:\u001a\u00020\u000f2\b\b\u0002\u0010;\u001a\u00020\u000f2\b\b\u0002\u0010<\u001a\u00020\u000fJ\u0010\u0010=\u001a\u00020*2\b\b\u0001\u0010>\u001a\u00020\bJ\u0010\u0010?\u001a\u00020*2\u0006\u00100\u001a\u00020,H\u0016J\u0010\u0010@\u001a\u00020*2\u0006\u00100\u001a\u00020,H\u0016J\u001e\u0010A\u001a\u00020*2\u0014\u0010B\u001a\u0010\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020*\u0018\u00010CH\u0016J\u000e\u0010D\u001a\u00020*2\u0006\u0010\"\u001a\u00020#J\u000e\u0010E\u001a\u00020*2\u0006\u0010%\u001a\u00020#J\u0010\u0010F\u001a\u00020*2\b\b\u0001\u00104\u001a\u00020\bJ\u001a\u0010G\u001a\u00020*2\b\b\u0001\u0010H\u001a\u00020\b2\b\b\u0001\u0010I\u001a\u00020\bJ\u0010\u0010J\u001a\u00020*2\b\b\u0001\u00104\u001a\u00020\bJ\u000e\u0010K\u001a\u00020*2\u0006\u0010L\u001a\u00020#J\u001a\u0010K\u001a\u00020*2\n\u00102\u001a\u00020\u0011\"\u00020\b2\u0006\u0010L\u001a\u00020#J \u0010K\u001a\u00020*2\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010M2\u0006\u0010L\u001a\u00020#H\u0016J\u000e\u0010$\u001a\u00020*2\u0006\u0010N\u001a\u00020#R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, m1890d2 = {"Lcom/sinovatech/unicom/basic/ui/view/date_time_picker/DateTimePicker;", "Landroid/widget/FrameLayout;", "Lcom/loper7/date_time_picker/controller/DateTimeInterface;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "controller", "Lcom/loper7/date_time_picker/controller/BaseDateTimeController;", "dayLabel", "", "displayType", "", "dividerColor", "global", "hourLabel", "layoutResId", "mDaySpinner", "Lcom/sinovatech/unicom/basic/ui/view/date_time_picker/number_picker/NumberPicker;", "mHourSpinner", "mMinuteSpinner", "mMonthSpinner", "mSecondSpinner", "mYearSpinner", "minLabel", "monthLabel", "normalTextSize", "secondLabel", "selectTextSize", "selectedTextBold", "", "showLabel", "textBold", "textColor", "themeColor", "yearLabel", "bindController", "", "getMillisecond", "", "getPicker", "init", "setDefaultMillisecond", "time", "setDisplayType", "types", "setDividerColor", "color", "setGlobal", "setLabelText", "year", "month", "day", "hour", "min", "second", "setLayout", "layout", "setMaxMillisecond", "setMinMillisecond", "setOnDateTimeChangedListener", "callback", "Lkotlin/Function1;", "setSelectedTextBold", "setTextBold", "setTextColor", "setTextSize", "normal", "select", "setThemeColor", "setWrapSelectorWheel", "wrapSelector", "", C4280b.f10047a, "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.DateTimePicker */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DateTimePicker extends FrameLayout implements DateTimeInterface {
    private BaseDateTimeController controller;
    private String dayLabel;
    private int[] displayType;
    private int dividerColor;
    private int global;
    private String hourLabel;
    private int layoutResId;
    private NumberPicker mDaySpinner;
    private NumberPicker mHourSpinner;
    private NumberPicker mMinuteSpinner;
    private NumberPicker mMonthSpinner;
    private NumberPicker mSecondSpinner;
    private NumberPicker mYearSpinner;
    private String minLabel;
    private String monthLabel;
    private int normalTextSize;
    private String secondLabel;
    private int selectTextSize;
    private boolean selectedTextBold;
    private boolean showLabel;
    private boolean textBold;
    private int textColor;
    private int themeColor;
    private String yearLabel;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DateTimePicker(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateTimePicker(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.displayType = new int[]{0, 1, 2, 3, 4, 5};
        this.showLabel = true;
        this.yearLabel = "年";
        this.monthLabel = "月";
        this.dayLabel = "日";
        this.hourLabel = "时";
        this.minLabel = "分";
        this.secondLabel = "秒";
        this.layoutResId = 2131493106;
        this.textBold = true;
        this.selectedTextBold = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.DateTimePicker);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr…styleable.DateTimePicker)");
        this.showLabel = obtainStyledAttributes.getBoolean(5, true);
        this.themeColor = obtainStyledAttributes.getColor(8, ContextCompat.getColor(context, 2131099722));
        this.textColor = obtainStyledAttributes.getColor(7, ContextCompat.getColor(context, 2131099732));
        this.dividerColor = obtainStyledAttributes.getColor(0, ContextCompat.getColor(context, 2131099726));
        this.selectTextSize = ContextExt.px2dip(context, obtainStyledAttributes.getDimensionPixelSize(3, ContextExt.dip2px(context, 0.0f)));
        this.normalTextSize = ContextExt.px2dip(context, obtainStyledAttributes.getDimensionPixelSize(2, ContextExt.dip2px(context, 0.0f)));
        this.layoutResId = obtainStyledAttributes.getResourceId(1, 2131493106);
        this.textBold = obtainStyledAttributes.getBoolean(6, this.textBold);
        this.selectedTextBold = obtainStyledAttributes.getBoolean(4, this.selectedTextBold);
        obtainStyledAttributes.recycle();
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateTimePicker(@NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.displayType = new int[]{0, 1, 2, 3, 4, 5};
        this.showLabel = true;
        this.yearLabel = "年";
        this.monthLabel = "月";
        this.dayLabel = "日";
        this.hourLabel = "时";
        this.minLabel = "分";
        this.secondLabel = "秒";
        this.layoutResId = 2131493106;
        this.textBold = true;
        this.selectedTextBold = true;
        init();
    }

    private final void init() {
        removeAllViews();
        try {
            if (!DateTimeConfig.INSTANCE.showChina(this.global) && this.layoutResId == 2131493106) {
                View.inflate(getContext(), 2131493107, this);
            } else {
                View.inflate(getContext(), this.layoutResId, this);
            }
            this.mYearSpinner = (NumberPicker) findViewById(2131298158);
            if (this.mYearSpinner == null) {
                this.mYearSpinner = (NumberPicker) findViewWithTag("np_datetime_year");
            }
            this.mMonthSpinner = (NumberPicker) findViewById(2131298156);
            if (this.mMonthSpinner == null) {
                this.mMonthSpinner = (NumberPicker) findViewWithTag("np_datetime_month");
            }
            this.mDaySpinner = (NumberPicker) findViewById(2131298153);
            if (this.mDaySpinner == null) {
                this.mDaySpinner = (NumberPicker) findViewWithTag("np_datetime_day");
            }
            this.mHourSpinner = (NumberPicker) findViewById(2131298154);
            if (this.mHourSpinner == null) {
                this.mHourSpinner = (NumberPicker) findViewWithTag("np_datetime_hour");
            }
            this.mMinuteSpinner = (NumberPicker) findViewById(2131298155);
            if (this.mMinuteSpinner == null) {
                this.mMinuteSpinner = (NumberPicker) findViewWithTag("np_datetime_minute");
            }
            this.mSecondSpinner = (NumberPicker) findViewById(2131298157);
            if (this.mSecondSpinner == null) {
                this.mSecondSpinner = (NumberPicker) findViewWithTag("np_datetime_second");
            }
            setThemeColor(this.themeColor);
            setTextSize(this.normalTextSize, this.selectTextSize);
            showLabel(this.showLabel);
            setDisplayType(this.displayType);
            setSelectedTextBold(this.selectedTextBold);
            setTextBold(this.textBold);
            setTextColor(this.textColor);
            setDividerColor(this.dividerColor);
            DateTimeController dateTimeController = this.controller;
            if (dateTimeController == null) {
                dateTimeController = new DateTimeController();
            }
            bindController(dateTimeController);
        } catch (Exception unused) {
            throw new Exception("layoutResId is it right or not?");
        }
    }

    public final void bindController(@Nullable BaseDateTimeController baseDateTimeController) {
        BaseDateTimeController bindPicker;
        BaseDateTimeController bindPicker2;
        BaseDateTimeController bindPicker3;
        BaseDateTimeController bindPicker4;
        BaseDateTimeController bindPicker5;
        BaseDateTimeController bindPicker6;
        BaseDateTimeController bindGlobal;
        this.controller = baseDateTimeController;
        BaseDateTimeController baseDateTimeController2 = this.controller;
        if (baseDateTimeController2 == null) {
            DateTimeController bindGlobal2 = new DateTimeController().bindPicker(0, this.mYearSpinner).bindPicker(1, this.mMonthSpinner).bindPicker(2, this.mDaySpinner).bindGlobal(this.global);
            this.controller = bindGlobal2 != null ? bindGlobal2.build() : null;
        } else if (baseDateTimeController2 == null || (bindPicker = baseDateTimeController2.bindPicker(0, this.mYearSpinner)) == null || (bindPicker2 = bindPicker.bindPicker(1, this.mMonthSpinner)) == null || (bindPicker3 = bindPicker2.bindPicker(2, this.mDaySpinner)) == null || (bindPicker4 = bindPicker3.bindPicker(3, this.mHourSpinner)) == null || (bindPicker5 = bindPicker4.bindPicker(4, this.mMinuteSpinner)) == null || (bindPicker6 = bindPicker5.bindPicker(5, this.mSecondSpinner)) == null || (bindGlobal = bindPicker6.bindGlobal(this.global)) == null) {
        } else {
            bindGlobal.build();
        }
    }

    public final void setGlobal(int i) {
        this.global = i;
        init();
    }

    public final void setLayout(@NotNull int i) {
        if (i == 0) {
            return;
        }
        this.layoutResId = i;
        init();
    }

    public final void setDisplayType(@Nullable int[] iArr) {
        NumberPicker numberPicker;
        NumberPicker numberPicker2;
        NumberPicker numberPicker3;
        NumberPicker numberPicker4;
        NumberPicker numberPicker5;
        NumberPicker numberPicker6;
        if (iArr != null) {
            if (iArr.length == 0) {
                return;
            }
            this.displayType = iArr;
            if (!ArraysKt.contains(this.displayType, 0) && (numberPicker6 = this.mYearSpinner) != null) {
                numberPicker6.setVisibility(8);
            }
            if (!ArraysKt.contains(this.displayType, 1) && (numberPicker5 = this.mMonthSpinner) != null) {
                numberPicker5.setVisibility(8);
            }
            if (!ArraysKt.contains(this.displayType, 2) && (numberPicker4 = this.mDaySpinner) != null) {
                numberPicker4.setVisibility(8);
            }
            if (!ArraysKt.contains(this.displayType, 3) && (numberPicker3 = this.mHourSpinner) != null) {
                numberPicker3.setVisibility(8);
            }
            if (!ArraysKt.contains(this.displayType, 4) && (numberPicker2 = this.mMinuteSpinner) != null) {
                numberPicker2.setVisibility(8);
            }
            if (ArraysKt.contains(this.displayType, 5) || (numberPicker = this.mSecondSpinner) == null) {
                return;
            }
            numberPicker.setVisibility(8);
        }
    }

    public final void showLabel(boolean z) {
        this.showLabel = z;
        if (z) {
            NumberPicker numberPicker = this.mYearSpinner;
            if (numberPicker != null) {
                numberPicker.setLabel(this.yearLabel);
            }
            NumberPicker numberPicker2 = this.mMonthSpinner;
            if (numberPicker2 != null) {
                numberPicker2.setLabel(this.monthLabel);
            }
            NumberPicker numberPicker3 = this.mDaySpinner;
            if (numberPicker3 != null) {
                numberPicker3.setLabel(this.dayLabel);
            }
            NumberPicker numberPicker4 = this.mHourSpinner;
            if (numberPicker4 != null) {
                numberPicker4.setLabel(this.hourLabel);
            }
            NumberPicker numberPicker5 = this.mMinuteSpinner;
            if (numberPicker5 != null) {
                numberPicker5.setLabel(this.minLabel);
            }
            NumberPicker numberPicker6 = this.mSecondSpinner;
            if (numberPicker6 != null) {
                numberPicker6.setLabel(this.secondLabel);
                return;
            }
            return;
        }
        NumberPicker numberPicker7 = this.mYearSpinner;
        if (numberPicker7 != null) {
            numberPicker7.setLabel("");
        }
        NumberPicker numberPicker8 = this.mMonthSpinner;
        if (numberPicker8 != null) {
            numberPicker8.setLabel("");
        }
        NumberPicker numberPicker9 = this.mDaySpinner;
        if (numberPicker9 != null) {
            numberPicker9.setLabel("");
        }
        NumberPicker numberPicker10 = this.mHourSpinner;
        if (numberPicker10 != null) {
            numberPicker10.setLabel("");
        }
        NumberPicker numberPicker11 = this.mMinuteSpinner;
        if (numberPicker11 != null) {
            numberPicker11.setLabel("");
        }
        NumberPicker numberPicker12 = this.mSecondSpinner;
        if (numberPicker12 != null) {
            numberPicker12.setLabel("");
        }
    }

    public final void setThemeColor(@ColorInt int i) {
        if (i == 0) {
            return;
        }
        this.themeColor = i;
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            numberPicker.setSelectedTextColor(this.themeColor);
        }
        NumberPicker numberPicker2 = this.mMonthSpinner;
        if (numberPicker2 != null) {
            numberPicker2.setSelectedTextColor(this.themeColor);
        }
        NumberPicker numberPicker3 = this.mDaySpinner;
        if (numberPicker3 != null) {
            numberPicker3.setSelectedTextColor(this.themeColor);
        }
        NumberPicker numberPicker4 = this.mHourSpinner;
        if (numberPicker4 != null) {
            numberPicker4.setSelectedTextColor(this.themeColor);
        }
        NumberPicker numberPicker5 = this.mMinuteSpinner;
        if (numberPicker5 != null) {
            numberPicker5.setSelectedTextColor(this.themeColor);
        }
        NumberPicker numberPicker6 = this.mSecondSpinner;
        if (numberPicker6 != null) {
            numberPicker6.setSelectedTextColor(this.themeColor);
        }
    }

    public final void setTextColor(@ColorInt int i) {
        if (i == 0) {
            return;
        }
        this.textColor = i;
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            numberPicker.setTextColor(this.textColor);
        }
        NumberPicker numberPicker2 = this.mMonthSpinner;
        if (numberPicker2 != null) {
            numberPicker2.setTextColor(this.textColor);
        }
        NumberPicker numberPicker3 = this.mDaySpinner;
        if (numberPicker3 != null) {
            numberPicker3.setTextColor(this.textColor);
        }
        NumberPicker numberPicker4 = this.mHourSpinner;
        if (numberPicker4 != null) {
            numberPicker4.setTextColor(this.textColor);
        }
        NumberPicker numberPicker5 = this.mMinuteSpinner;
        if (numberPicker5 != null) {
            numberPicker5.setTextColor(this.textColor);
        }
        NumberPicker numberPicker6 = this.mSecondSpinner;
        if (numberPicker6 != null) {
            numberPicker6.setTextColor(this.textColor);
        }
    }

    public final void setDividerColor(@ColorInt int i) {
        if (i == 0) {
            return;
        }
        this.dividerColor = i;
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            numberPicker.setDividerColor(i);
        }
        NumberPicker numberPicker2 = this.mMonthSpinner;
        if (numberPicker2 != null) {
            numberPicker2.setDividerColor(i);
        }
        NumberPicker numberPicker3 = this.mDaySpinner;
        if (numberPicker3 != null) {
            numberPicker3.setDividerColor(i);
        }
        NumberPicker numberPicker4 = this.mHourSpinner;
        if (numberPicker4 != null) {
            numberPicker4.setDividerColor(i);
        }
        NumberPicker numberPicker5 = this.mMinuteSpinner;
        if (numberPicker5 != null) {
            numberPicker5.setDividerColor(i);
        }
        NumberPicker numberPicker6 = this.mSecondSpinner;
        if (numberPicker6 != null) {
            numberPicker6.setDividerColor(i);
        }
    }

    public final void setTextSize(@Dimension int i, @Dimension int i2) {
        if (i == 0 || i2 == 0) {
            return;
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        int dip2px = ContextExt.dip2px(context, i2);
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        int dip2px2 = ContextExt.dip2px(context2, i);
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            numberPicker.setTextSize(dip2px2);
        }
        NumberPicker numberPicker2 = this.mMonthSpinner;
        if (numberPicker2 != null) {
            numberPicker2.setTextSize(dip2px2);
        }
        NumberPicker numberPicker3 = this.mDaySpinner;
        if (numberPicker3 != null) {
            numberPicker3.setTextSize(dip2px2);
        }
        NumberPicker numberPicker4 = this.mHourSpinner;
        if (numberPicker4 != null) {
            numberPicker4.setTextSize(dip2px2);
        }
        NumberPicker numberPicker5 = this.mMinuteSpinner;
        if (numberPicker5 != null) {
            numberPicker5.setTextSize(dip2px2);
        }
        NumberPicker numberPicker6 = this.mSecondSpinner;
        if (numberPicker6 != null) {
            numberPicker6.setTextSize(dip2px2);
        }
        NumberPicker numberPicker7 = this.mYearSpinner;
        if (numberPicker7 != null) {
            numberPicker7.setSelectedTextSize(dip2px);
        }
        NumberPicker numberPicker8 = this.mMonthSpinner;
        if (numberPicker8 != null) {
            numberPicker8.setSelectedTextSize(dip2px);
        }
        NumberPicker numberPicker9 = this.mDaySpinner;
        if (numberPicker9 != null) {
            numberPicker9.setSelectedTextSize(dip2px);
        }
        NumberPicker numberPicker10 = this.mHourSpinner;
        if (numberPicker10 != null) {
            numberPicker10.setSelectedTextSize(dip2px);
        }
        NumberPicker numberPicker11 = this.mMinuteSpinner;
        if (numberPicker11 != null) {
            numberPicker11.setSelectedTextSize(dip2px);
        }
        NumberPicker numberPicker12 = this.mSecondSpinner;
        if (numberPicker12 != null) {
            numberPicker12.setSelectedTextSize(dip2px);
        }
    }

    public static /* synthetic */ void setLabelText$default(DateTimePicker dateTimePicker, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setLabelText");
        }
        if ((i & 1) != 0) {
            str = dateTimePicker.yearLabel;
        }
        dateTimePicker.setLabelText(str, (i & 2) != 0 ? dateTimePicker.monthLabel : str2, (i & 4) != 0 ? dateTimePicker.dayLabel : str3, (i & 8) != 0 ? dateTimePicker.hourLabel : str4, (i & 16) != 0 ? dateTimePicker.minLabel : str5, (i & 32) != 0 ? dateTimePicker.secondLabel : str6);
    }

    public final void setLabelText(@NotNull String year, @NotNull String month, @NotNull String day, @NotNull String hour, @NotNull String min, @NotNull String second) {
        Intrinsics.checkParameterIsNotNull(year, "year");
        Intrinsics.checkParameterIsNotNull(month, "month");
        Intrinsics.checkParameterIsNotNull(day, "day");
        Intrinsics.checkParameterIsNotNull(hour, "hour");
        Intrinsics.checkParameterIsNotNull(min, "min");
        Intrinsics.checkParameterIsNotNull(second, "second");
        this.yearLabel = year;
        this.monthLabel = month;
        this.dayLabel = day;
        this.hourLabel = hour;
        this.minLabel = min;
        this.secondLabel = second;
        showLabel(this.showLabel);
    }

    public final void setWrapSelectorWheel(@NotNull int[] types, boolean z) {
        Intrinsics.checkParameterIsNotNull(types, "types");
        setWrapSelectorWheel(ArraysKt.toMutableList(types), z);
    }

    public final void setWrapSelectorWheel(boolean z) {
        setWrapSelectorWheel((List<Integer>) null, z);
    }

    @Nullable
    public final NumberPicker getPicker(int i) {
        switch (i) {
            case 0:
                return this.mYearSpinner;
            case 1:
                return this.mMonthSpinner;
            case 2:
                return this.mDaySpinner;
            case 3:
                return this.mHourSpinner;
            case 4:
                return this.mMinuteSpinner;
            case 5:
                return this.mSecondSpinner;
            default:
                return null;
        }
    }

    public final void setTextBold(boolean z) {
        this.textBold = z;
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            numberPicker.setTextBold(z);
        }
        NumberPicker numberPicker2 = this.mMonthSpinner;
        if (numberPicker2 != null) {
            numberPicker2.setTextBold(z);
        }
        NumberPicker numberPicker3 = this.mDaySpinner;
        if (numberPicker3 != null) {
            numberPicker3.setTextBold(z);
        }
        NumberPicker numberPicker4 = this.mHourSpinner;
        if (numberPicker4 != null) {
            numberPicker4.setTextBold(z);
        }
        NumberPicker numberPicker5 = this.mMinuteSpinner;
        if (numberPicker5 != null) {
            numberPicker5.setTextBold(z);
        }
        NumberPicker numberPicker6 = this.mSecondSpinner;
        if (numberPicker6 != null) {
            numberPicker6.setTextBold(z);
        }
    }

    public final void setSelectedTextBold(boolean z) {
        this.selectedTextBold = z;
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            numberPicker.setSelectedTextBold(z);
        }
        NumberPicker numberPicker2 = this.mMonthSpinner;
        if (numberPicker2 != null) {
            numberPicker2.setSelectedTextBold(z);
        }
        NumberPicker numberPicker3 = this.mDaySpinner;
        if (numberPicker3 != null) {
            numberPicker3.setSelectedTextBold(z);
        }
        NumberPicker numberPicker4 = this.mHourSpinner;
        if (numberPicker4 != null) {
            numberPicker4.setSelectedTextBold(z);
        }
        NumberPicker numberPicker5 = this.mMinuteSpinner;
        if (numberPicker5 != null) {
            numberPicker5.setSelectedTextBold(z);
        }
        NumberPicker numberPicker6 = this.mSecondSpinner;
        if (numberPicker6 != null) {
            numberPicker6.setSelectedTextBold(z);
        }
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setDefaultMillisecond(long j) {
        BaseDateTimeController baseDateTimeController = this.controller;
        if (baseDateTimeController != null) {
            baseDateTimeController.setDefaultMillisecond(j);
        }
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setMinMillisecond(long j) {
        BaseDateTimeController baseDateTimeController = this.controller;
        if (baseDateTimeController != null) {
            baseDateTimeController.setMinMillisecond(j);
        }
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setMaxMillisecond(long j) {
        BaseDateTimeController baseDateTimeController = this.controller;
        if (baseDateTimeController != null) {
            baseDateTimeController.setMaxMillisecond(j);
        }
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setWrapSelectorWheel(@Nullable List<Integer> list, boolean z) {
        BaseDateTimeController baseDateTimeController = this.controller;
        if (baseDateTimeController != null) {
            baseDateTimeController.setWrapSelectorWheel(list, z);
        }
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setOnDateTimeChangedListener(@Nullable Function1<? super Long, Unit> function1) {
        BaseDateTimeController baseDateTimeController = this.controller;
        if (baseDateTimeController != null) {
            baseDateTimeController.setOnDateTimeChangedListener(function1);
        }
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public long getMillisecond() {
        BaseDateTimeController baseDateTimeController = this.controller;
        if (baseDateTimeController != null) {
            return baseDateTimeController.getMillisecond();
        }
        return 0L;
    }
}
