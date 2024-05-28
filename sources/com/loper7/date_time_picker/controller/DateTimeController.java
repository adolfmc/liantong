package com.loper7.date_time_picker.controller;

import com.loper7.date_time_picker.ext.CalendarExt;
import com.sinovatech.unicom.basic.p315ui.view.date_time_picker.DateTimeConfig;
import com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DateTimeController.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\b\u0013\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0002J\u0010\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001a\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\u001f\u001a\u00020\u0000H\u0016J\b\u0010 \u001a\u00020\u000eH\u0016J\b\u0010!\u001a\u00020\u000fH\u0002J\b\u0010\"\u001a\u00020\u000fH\u0002J\u0010\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0010\u0010%\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0010\u0010&\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u001e\u0010'\u001a\u00020\u000f2\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rH\u0016J \u0010)\u001a\u00020\u000f2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00192\u0006\u0010+\u001a\u00020\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, m1890d2 = {"Lcom/loper7/date_time_picker/controller/DateTimeController;", "Lcom/loper7/date_time_picker/controller/BaseDateTimeController;", "()V", "calendar", "Ljava/util/Calendar;", "global", "", "mDaySpinner", "Lcom/sinovatech/unicom/basic/ui/view/date_time_picker/number_picker/NumberPicker;", "mHourSpinner", "mMinuteSpinner", "mMonthSpinner", "mOnDateTimeChangedListener", "Lkotlin/Function1;", "", "", "mSecondSpinner", "mYearSpinner", "maxCalendar", "minCalendar", "onChangeListener", "Lcom/sinovatech/unicom/basic/ui/view/date_time_picker/number_picker/NumberPicker$OnValueChangeListener;", "wrapSelectorWheel", "", "wrapSelectorWheelTypes", "", "applyDateData", "bindGlobal", "bindPicker", "type", "picker", "build", "getMillisecond", "limitMaxAndMin", "onDateTimeChanged", "setDefaultMillisecond", "time", "setMaxMillisecond", "setMinMillisecond", "setOnDateTimeChangedListener", "callback", "setWrapSelectorWheel", "types", "wrapSelector", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DateTimeController extends BaseDateTimeController {
    private Calendar calendar;
    private int global;
    private NumberPicker mDaySpinner;
    private NumberPicker mHourSpinner;
    private NumberPicker mMinuteSpinner;
    private NumberPicker mMonthSpinner;
    private Function1<? super Long, Unit> mOnDateTimeChangedListener;
    private NumberPicker mSecondSpinner;
    private NumberPicker mYearSpinner;
    private Calendar maxCalendar;
    private Calendar minCalendar;
    private List<Integer> wrapSelectorWheelTypes;
    private boolean wrapSelectorWheel = true;
    private final NumberPicker.OnValueChangeListener onChangeListener = new NumberPicker.OnValueChangeListener() { // from class: com.loper7.date_time_picker.controller.DateTimeController$onChangeListener$1
        @Override // com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker.OnValueChangeListener
        public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
            DateTimeController.this.applyDateData();
            DateTimeController.this.limitMaxAndMin();
            DateTimeController.this.onDateTimeChanged();
        }
    };

    @Override // com.loper7.date_time_picker.controller.BaseDateTimeController
    @NotNull
    public DateTimeController bindPicker(int i, @Nullable NumberPicker numberPicker) {
        switch (i) {
            case 0:
                this.mYearSpinner = numberPicker;
                break;
            case 1:
                this.mMonthSpinner = numberPicker;
                break;
            case 2:
                this.mDaySpinner = numberPicker;
                break;
            case 3:
                this.mHourSpinner = numberPicker;
                break;
            case 4:
                this.mMinuteSpinner = numberPicker;
                break;
            case 5:
                this.mSecondSpinner = numberPicker;
                break;
        }
        return this;
    }

    @Override // com.loper7.date_time_picker.controller.BaseDateTimeController
    @NotNull
    public DateTimeController bindGlobal(int i) {
        this.global = i;
        return this;
    }

    @Override // com.loper7.date_time_picker.controller.BaseDateTimeController
    @NotNull
    public DateTimeController build() {
        NumberPicker.Formatter globalMonthFormatter;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
        this.calendar = calendar;
        Calendar calendar2 = this.calendar;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        calendar2.set(14, 0);
        Calendar calendar3 = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar3, "Calendar.getInstance()");
        this.minCalendar = calendar3;
        Calendar calendar4 = this.minCalendar;
        if (calendar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        calendar4.set(1, 1900);
        Calendar calendar5 = this.minCalendar;
        if (calendar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        calendar5.set(2, 0);
        Calendar calendar6 = this.minCalendar;
        if (calendar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        calendar6.set(5, 1);
        Calendar calendar7 = this.minCalendar;
        if (calendar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        calendar7.set(11, 0);
        Calendar calendar8 = this.minCalendar;
        if (calendar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        calendar8.set(12, 0);
        Calendar calendar9 = this.minCalendar;
        if (calendar9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        calendar9.set(13, 0);
        Calendar calendar10 = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar10, "Calendar.getInstance()");
        this.maxCalendar = calendar10;
        Calendar calendar11 = this.maxCalendar;
        if (calendar11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        Calendar calendar12 = this.calendar;
        if (calendar12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        calendar11.set(1, calendar12.get(1) + 1900);
        Calendar calendar13 = this.maxCalendar;
        if (calendar13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        calendar13.set(2, 11);
        Calendar calendar14 = this.maxCalendar;
        if (calendar14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        Calendar calendar15 = this.maxCalendar;
        if (calendar15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        calendar14.set(5, CalendarExt.getMaxDayInMonth(calendar15));
        Calendar calendar16 = this.maxCalendar;
        if (calendar16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        calendar16.set(11, 23);
        Calendar calendar17 = this.maxCalendar;
        if (calendar17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        calendar17.set(12, 59);
        Calendar calendar18 = this.maxCalendar;
        if (calendar18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        calendar18.set(13, 59);
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            Calendar calendar19 = this.maxCalendar;
            if (calendar19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            numberPicker.setMaxValue(calendar19.get(1));
            Calendar calendar20 = this.minCalendar;
            if (calendar20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            numberPicker.setMinValue(calendar20.get(1));
            Calendar calendar21 = this.calendar;
            if (calendar21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker.setValue(calendar21.get(1));
            numberPicker.setFocusable(true);
            numberPicker.setFocusableInTouchMode(true);
            numberPicker.setDescendantFocusability(393216);
            numberPicker.setOnValueChangedListener(this.onChangeListener);
        }
        NumberPicker numberPicker2 = this.mMonthSpinner;
        if (numberPicker2 != null) {
            Calendar calendar22 = this.maxCalendar;
            if (calendar22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            numberPicker2.setMaxValue(calendar22.get(2) + 1);
            Calendar calendar23 = this.minCalendar;
            if (calendar23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            numberPicker2.setMinValue(calendar23.get(2) + 1);
            Calendar calendar24 = this.calendar;
            if (calendar24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker2.setValue(calendar24.get(2) + 1);
            numberPicker2.setFocusable(true);
            numberPicker2.setFocusableInTouchMode(true);
            if (DateTimeConfig.INSTANCE.showChina(this.global)) {
                globalMonthFormatter = DateTimeConfig.INSTANCE.getFormatter();
            } else {
                globalMonthFormatter = DateTimeConfig.INSTANCE.getGlobalMonthFormatter();
            }
            numberPicker2.setFormatter(globalMonthFormatter);
            numberPicker2.setDescendantFocusability(393216);
            numberPicker2.setOnValueChangedListener(this.onChangeListener);
        }
        NumberPicker numberPicker3 = this.mDaySpinner;
        if (numberPicker3 != null) {
            Calendar calendar25 = this.maxCalendar;
            if (calendar25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            numberPicker3.setMaxValue(calendar25.get(5));
            Calendar calendar26 = this.minCalendar;
            if (calendar26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            numberPicker3.setMinValue(calendar26.get(5));
            Calendar calendar27 = this.calendar;
            if (calendar27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker3.setValue(calendar27.get(5));
            numberPicker3.setFocusable(true);
            numberPicker3.setFocusableInTouchMode(true);
            numberPicker3.setFormatter(DateTimeConfig.INSTANCE.getFormatter());
            numberPicker3.setDescendantFocusability(393216);
            numberPicker3.setOnValueChangedListener(this.onChangeListener);
        }
        NumberPicker numberPicker4 = this.mHourSpinner;
        if (numberPicker4 != null) {
            Calendar calendar28 = this.maxCalendar;
            if (calendar28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            numberPicker4.setMaxValue(calendar28.get(11));
            Calendar calendar29 = this.minCalendar;
            if (calendar29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            numberPicker4.setMinValue(calendar29.get(11));
            numberPicker4.setFocusable(true);
            numberPicker4.setFocusableInTouchMode(true);
            Calendar calendar30 = this.calendar;
            if (calendar30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker4.setValue(calendar30.get(11));
            numberPicker4.setFormatter(DateTimeConfig.INSTANCE.getFormatter());
            numberPicker4.setDescendantFocusability(393216);
            numberPicker4.setOnValueChangedListener(this.onChangeListener);
        }
        NumberPicker numberPicker5 = this.mMinuteSpinner;
        if (numberPicker5 != null) {
            Calendar calendar31 = this.maxCalendar;
            if (calendar31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            numberPicker5.setMaxValue(calendar31.get(12));
            Calendar calendar32 = this.minCalendar;
            if (calendar32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            numberPicker5.setMinValue(calendar32.get(12));
            numberPicker5.setFocusable(true);
            numberPicker5.setFocusableInTouchMode(true);
            Calendar calendar33 = this.calendar;
            if (calendar33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker5.setValue(calendar33.get(12));
            numberPicker5.setFormatter(DateTimeConfig.INSTANCE.getFormatter());
            numberPicker5.setDescendantFocusability(393216);
            numberPicker5.setOnValueChangedListener(this.onChangeListener);
        }
        NumberPicker numberPicker6 = this.mSecondSpinner;
        if (numberPicker6 != null) {
            Calendar calendar34 = this.maxCalendar;
            if (calendar34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            numberPicker6.setMaxValue(calendar34.get(13));
            Calendar calendar35 = this.minCalendar;
            if (calendar35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            numberPicker6.setMinValue(calendar35.get(13));
            numberPicker6.setFocusable(true);
            numberPicker6.setFocusableInTouchMode(true);
            Calendar calendar36 = this.calendar;
            if (calendar36 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker6.setValue(calendar36.get(13));
            numberPicker6.setFormatter(DateTimeConfig.INSTANCE.getFormatter());
            numberPicker6.setDescendantFocusability(393216);
            numberPicker6.setOnValueChangedListener(this.onChangeListener);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyDateData() {
        NumberPicker numberPicker;
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        calendar.clear();
        NumberPicker numberPicker2 = this.mYearSpinner;
        if (numberPicker2 != null) {
            Calendar calendar2 = this.calendar;
            if (calendar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            calendar2.set(1, numberPicker2.getValue());
        }
        NumberPicker numberPicker3 = this.mMonthSpinner;
        if (numberPicker3 != null) {
            Calendar calendar3 = this.calendar;
            if (calendar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            calendar3.set(2, numberPicker3.getValue() - 1);
        }
        NumberPicker numberPicker4 = this.mYearSpinner;
        Integer valueOf = numberPicker4 != null ? Integer.valueOf(numberPicker4.getValue()) : null;
        NumberPicker numberPicker5 = this.mMonthSpinner;
        int maxDayInMonth = getMaxDayInMonth(valueOf, Integer.valueOf((numberPicker5 != null ? numberPicker5.getValue() : 0) - 1));
        NumberPicker numberPicker6 = this.mDaySpinner;
        if ((numberPicker6 != null ? numberPicker6.getValue() : 0) >= maxDayInMonth && (numberPicker = this.mDaySpinner) != null) {
            numberPicker.setValue(maxDayInMonth);
        }
        NumberPicker numberPicker7 = this.mDaySpinner;
        if (numberPicker7 != null) {
            Calendar calendar4 = this.calendar;
            if (calendar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            calendar4.set(5, numberPicker7.getValue());
        }
        NumberPicker numberPicker8 = this.mHourSpinner;
        if (numberPicker8 != null) {
            Calendar calendar5 = this.calendar;
            if (calendar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            calendar5.set(11, numberPicker8.getValue());
        }
        NumberPicker numberPicker9 = this.mMinuteSpinner;
        if (numberPicker9 != null) {
            Calendar calendar6 = this.calendar;
            if (calendar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            calendar6.set(12, numberPicker9.getValue());
        }
        NumberPicker numberPicker10 = this.mSecondSpinner;
        if (numberPicker10 != null) {
            Calendar calendar7 = this.calendar;
            if (calendar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            calendar7.set(13, numberPicker10.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDateTimeChanged() {
        Function1<? super Long, Unit> function1 = this.mOnDateTimeChangedListener;
        if (function1 == null || function1 == null) {
            return;
        }
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        function1.invoke(Long.valueOf(calendar.getTimeInMillis()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void limitMaxAndMin() {
        NumberPicker numberPicker;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar calendar2 = this.minCalendar;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        if (timeInMillis < calendar2.getTimeInMillis()) {
            Calendar calendar3 = this.calendar;
            if (calendar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            calendar3.clear();
            Calendar calendar4 = this.calendar;
            if (calendar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar5 = this.minCalendar;
            if (calendar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            calendar4.setTimeInMillis(calendar5.getTimeInMillis());
        }
        Calendar calendar6 = this.calendar;
        if (calendar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        long timeInMillis2 = calendar6.getTimeInMillis();
        Calendar calendar7 = this.maxCalendar;
        if (calendar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        if (timeInMillis2 > calendar7.getTimeInMillis()) {
            Calendar calendar8 = this.calendar;
            if (calendar8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            calendar8.clear();
            Calendar calendar9 = this.calendar;
            if (calendar9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar10 = this.maxCalendar;
            if (calendar10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            calendar9.setTimeInMillis(calendar10.getTimeInMillis());
        }
        Calendar calendar11 = this.calendar;
        if (calendar11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        Integer valueOf = calendar11 != null ? Integer.valueOf(calendar11.get(1)) : null;
        Calendar calendar12 = this.calendar;
        if (calendar12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        int maxDayInMonth = getMaxDayInMonth(valueOf, calendar12 != null ? Integer.valueOf(calendar12.get(2)) : null);
        NumberPicker numberPicker2 = this.mMonthSpinner;
        if (numberPicker2 != null) {
            Calendar calendar13 = this.calendar;
            if (calendar13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar14 = this.minCalendar;
            if (calendar14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            if (CalendarExt.isSameYear(calendar13, calendar14)) {
                Calendar calendar15 = this.minCalendar;
                if (calendar15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
                }
                i8 = calendar15.get(2) + 1;
            } else {
                i8 = 1;
            }
            numberPicker2.setMinValue(i8);
            Calendar calendar16 = this.calendar;
            if (calendar16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar17 = this.maxCalendar;
            if (calendar17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            if (CalendarExt.isSameYear(calendar16, calendar17)) {
                Calendar calendar18 = this.maxCalendar;
                if (calendar18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
                }
                i9 = calendar18.get(2) + 1;
            } else {
                i9 = 12;
            }
            numberPicker2.setMaxValue(i9);
            Unit unit = Unit.INSTANCE;
        }
        NumberPicker numberPicker3 = this.mDaySpinner;
        if (numberPicker3 != null) {
            Calendar calendar19 = this.calendar;
            if (calendar19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar20 = this.minCalendar;
            if (calendar20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            if (CalendarExt.isSameMonth(calendar19, calendar20)) {
                Calendar calendar21 = this.minCalendar;
                if (calendar21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
                }
                i6 = calendar21.get(5);
            } else {
                i6 = 1;
            }
            numberPicker3.setMinValue(i6);
            Calendar calendar22 = this.calendar;
            if (calendar22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar23 = this.maxCalendar;
            if (calendar23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            if (CalendarExt.isSameMonth(calendar22, calendar23)) {
                Calendar calendar24 = this.maxCalendar;
                if (calendar24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
                }
                i7 = calendar24.get(5);
            } else {
                i7 = maxDayInMonth;
            }
            numberPicker3.setMaxValue(i7);
            Unit unit2 = Unit.INSTANCE;
        }
        NumberPicker numberPicker4 = this.mHourSpinner;
        if (numberPicker4 != null) {
            Calendar calendar25 = this.calendar;
            if (calendar25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar26 = this.minCalendar;
            if (calendar26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            if (CalendarExt.isSameDay(calendar25, calendar26)) {
                Calendar calendar27 = this.minCalendar;
                if (calendar27 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
                }
                i4 = calendar27.get(11);
            } else {
                i4 = 0;
            }
            numberPicker4.setMinValue(i4);
            Calendar calendar28 = this.calendar;
            if (calendar28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar29 = this.maxCalendar;
            if (calendar29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            if (CalendarExt.isSameDay(calendar28, calendar29)) {
                Calendar calendar30 = this.maxCalendar;
                if (calendar30 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
                }
                i5 = calendar30.get(11);
            } else {
                i5 = 23;
            }
            numberPicker4.setMaxValue(i5);
            Unit unit3 = Unit.INSTANCE;
        }
        NumberPicker numberPicker5 = this.mMinuteSpinner;
        int i10 = 59;
        if (numberPicker5 != null) {
            Calendar calendar31 = this.calendar;
            if (calendar31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar32 = this.minCalendar;
            if (calendar32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            if (CalendarExt.isSameHour(calendar31, calendar32)) {
                Calendar calendar33 = this.minCalendar;
                if (calendar33 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
                }
                i2 = calendar33.get(12);
            } else {
                i2 = 0;
            }
            numberPicker5.setMinValue(i2);
            Calendar calendar34 = this.calendar;
            if (calendar34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar35 = this.maxCalendar;
            if (calendar35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            if (CalendarExt.isSameHour(calendar34, calendar35)) {
                Calendar calendar36 = this.maxCalendar;
                if (calendar36 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
                }
                i3 = calendar36.get(12);
            } else {
                i3 = 59;
            }
            numberPicker5.setMaxValue(i3);
            Unit unit4 = Unit.INSTANCE;
        }
        NumberPicker numberPicker6 = this.mSecondSpinner;
        if (numberPicker6 != null) {
            Calendar calendar37 = this.calendar;
            if (calendar37 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar38 = this.minCalendar;
            if (calendar38 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            if (CalendarExt.isSameMinute(calendar37, calendar38)) {
                Calendar calendar39 = this.minCalendar;
                if (calendar39 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
                }
                i = calendar39.get(13);
            } else {
                i = 0;
            }
            numberPicker6.setMinValue(i);
            Calendar calendar40 = this.calendar;
            if (calendar40 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            Calendar calendar41 = this.maxCalendar;
            if (calendar41 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            if (CalendarExt.isSameMinute(calendar40, calendar41)) {
                Calendar calendar42 = this.maxCalendar;
                if (calendar42 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
                }
                i10 = calendar42.get(13);
            }
            numberPicker6.setMaxValue(i10);
            Unit unit5 = Unit.INSTANCE;
        }
        NumberPicker numberPicker7 = this.mYearSpinner;
        if (numberPicker7 != null) {
            Calendar calendar43 = this.calendar;
            if (calendar43 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker7.setValue(calendar43.get(1));
        }
        NumberPicker numberPicker8 = this.mMonthSpinner;
        if (numberPicker8 != null) {
            Calendar calendar44 = this.calendar;
            if (calendar44 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker8.setValue(calendar44.get(2) + 1);
        }
        NumberPicker numberPicker9 = this.mDaySpinner;
        if (numberPicker9 != null) {
            Calendar calendar45 = this.calendar;
            if (calendar45 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker9.setValue(calendar45.get(5));
        }
        NumberPicker numberPicker10 = this.mHourSpinner;
        if (numberPicker10 != null) {
            Calendar calendar46 = this.calendar;
            if (calendar46 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker10.setValue(calendar46.get(11));
        }
        NumberPicker numberPicker11 = this.mMinuteSpinner;
        if (numberPicker11 != null) {
            Calendar calendar47 = this.calendar;
            if (calendar47 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker11.setValue(calendar47.get(12));
        }
        NumberPicker numberPicker12 = this.mSecondSpinner;
        if (numberPicker12 != null) {
            Calendar calendar48 = this.calendar;
            if (calendar48 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendar");
            }
            numberPicker12.setValue(calendar48.get(13));
        }
        NumberPicker numberPicker13 = this.mDaySpinner;
        if ((numberPicker13 != null ? numberPicker13.getValue() : 0) >= maxDayInMonth && (numberPicker = this.mDaySpinner) != null) {
            numberPicker.setValue(maxDayInMonth);
        }
        setWrapSelectorWheel(this.wrapSelectorWheelTypes, this.wrapSelectorWheel);
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setDefaultMillisecond(long j) {
        if (j == 0) {
            return;
        }
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        calendar.clear();
        Calendar calendar2 = this.calendar;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        calendar2.setTimeInMillis(j);
        limitMaxAndMin();
        onDateTimeChanged();
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setMinMillisecond(long j) {
        if (j == 0) {
            return;
        }
        Calendar calendar = this.maxCalendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        long longValue = (calendar != null ? Long.valueOf(calendar.getTimeInMillis()) : null).longValue();
        if (1 <= longValue && j > longValue) {
            return;
        }
        Calendar calendar2 = this.minCalendar;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        if (calendar2 == null) {
            Calendar calendar3 = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar3, "Calendar.getInstance()");
            this.minCalendar = calendar3;
        }
        Calendar calendar4 = this.minCalendar;
        if (calendar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        if (calendar4 != null) {
            calendar4.setTimeInMillis(j);
        }
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            Calendar calendar5 = this.minCalendar;
            if (calendar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            numberPicker.setMinValue((calendar5 != null ? Integer.valueOf(calendar5.get(1)) : null).intValue());
        }
        Calendar calendar6 = this.calendar;
        if (calendar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        setDefaultMillisecond(calendar6.getTimeInMillis());
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setMaxMillisecond(long j) {
        if (j == 0) {
            return;
        }
        Calendar calendar = this.minCalendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
        }
        if ((calendar != null ? Long.valueOf(calendar.getTimeInMillis()) : null).longValue() > 0) {
            Calendar calendar2 = this.minCalendar;
            if (calendar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("minCalendar");
            }
            if (j < (calendar2 != null ? Long.valueOf(calendar2.getTimeInMillis()) : null).longValue()) {
                return;
            }
        }
        Calendar calendar3 = this.maxCalendar;
        if (calendar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        if (calendar3 == null) {
            Calendar calendar4 = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar4, "Calendar.getInstance()");
            this.maxCalendar = calendar4;
        }
        Calendar calendar5 = this.maxCalendar;
        if (calendar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
        }
        if (calendar5 != null) {
            calendar5.setTimeInMillis(j);
        }
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            Calendar calendar6 = this.maxCalendar;
            if (calendar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("maxCalendar");
            }
            numberPicker.setMaxValue((calendar6 != null ? Integer.valueOf(calendar6.get(1)) : null).intValue());
        }
        Calendar calendar7 = this.calendar;
        if (calendar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        setDefaultMillisecond(calendar7.getTimeInMillis());
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0011, code lost:
        if (r2.isEmpty() != false) goto L62;
     */
    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setWrapSelectorWheel(@org.jetbrains.annotations.Nullable java.util.List<java.lang.Integer> r2, boolean r3) {
        /*
            r1 = this;
            r1.wrapSelectorWheelTypes = r2
            r1.wrapSelectorWheel = r3
            java.util.List<java.lang.Integer> r2 = r1.wrapSelectorWheelTypes
            if (r2 == 0) goto L13
            if (r2 != 0) goto Ld
            kotlin.jvm.internal.Intrinsics.throwNpe()
        Ld:
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L76
        L13:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.List r2 = (java.util.List) r2
            r1.wrapSelectorWheelTypes = r2
            java.util.List<java.lang.Integer> r2 = r1.wrapSelectorWheelTypes
            if (r2 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L23:
            r0 = 0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.add(r0)
            java.util.List<java.lang.Integer> r2 = r1.wrapSelectorWheelTypes
            if (r2 != 0) goto L32
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L32:
            r0 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.add(r0)
            java.util.List<java.lang.Integer> r2 = r1.wrapSelectorWheelTypes
            if (r2 != 0) goto L41
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L41:
            r0 = 2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.add(r0)
            java.util.List<java.lang.Integer> r2 = r1.wrapSelectorWheelTypes
            if (r2 != 0) goto L50
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L50:
            r0 = 3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.add(r0)
            java.util.List<java.lang.Integer> r2 = r1.wrapSelectorWheelTypes
            if (r2 != 0) goto L5f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L5f:
            r0 = 4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.add(r0)
            java.util.List<java.lang.Integer> r2 = r1.wrapSelectorWheelTypes
            if (r2 != 0) goto L6e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L6e:
            r0 = 5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.add(r0)
        L76:
            java.util.List<java.lang.Integer> r2 = r1.wrapSelectorWheelTypes
            if (r2 != 0) goto L7d
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L7d:
            java.util.Iterator r2 = r2.iterator()
        L81:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto Lc5
            java.lang.Object r0 = r2.next()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            switch(r0) {
                case 0: goto Lbd;
                case 1: goto Lb5;
                case 2: goto Lad;
                case 3: goto La5;
                case 4: goto L9d;
                case 5: goto L95;
                default: goto L94;
            }
        L94:
            goto L81
        L95:
            com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker r0 = r1.mSecondSpinner
            if (r0 == 0) goto L81
            r0.setWrapSelectorWheel(r3)
            goto L81
        L9d:
            com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker r0 = r1.mMinuteSpinner
            if (r0 == 0) goto L81
            r0.setWrapSelectorWheel(r3)
            goto L81
        La5:
            com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker r0 = r1.mHourSpinner
            if (r0 == 0) goto L81
            r0.setWrapSelectorWheel(r3)
            goto L81
        Lad:
            com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker r0 = r1.mDaySpinner
            if (r0 == 0) goto L81
            r0.setWrapSelectorWheel(r3)
            goto L81
        Lb5:
            com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker r0 = r1.mMonthSpinner
            if (r0 == 0) goto L81
            r0.setWrapSelectorWheel(r3)
            goto L81
        Lbd:
            com.sinovatech.unicom.basic.ui.view.date_time_picker.number_picker.NumberPicker r0 = r1.mYearSpinner
            if (r0 == 0) goto L81
            r0.setWrapSelectorWheel(r3)
            goto L81
        Lc5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loper7.date_time_picker.controller.DateTimeController.setWrapSelectorWheel(java.util.List, boolean):void");
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public void setOnDateTimeChangedListener(@Nullable Function1<? super Long, Unit> function1) {
        this.mOnDateTimeChangedListener = function1;
        onDateTimeChanged();
    }

    @Override // com.loper7.date_time_picker.controller.DateTimeInterface
    public long getMillisecond() {
        Calendar calendar = this.calendar;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendar");
        }
        return calendar.getTimeInMillis();
    }
}
