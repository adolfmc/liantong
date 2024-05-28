package com.loper7.date_time_picker.controller;

import com.loper7.date_time_picker.ext.CalendarExt;
import com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BaseDateTimeController.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010\n\u001a\u00020\u0000H&J!\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0004¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, m1890d2 = {"Lcom/loper7/date_time_picker/controller/BaseDateTimeController;", "Lcom/loper7/date_time_picker/controller/DateTimeInterface;", "()V", "bindGlobal", "global", "", "bindPicker", "type", "picker", "Lcom/sinovatech/unicom/basic/ui/view/date_time_picker/number_picker/NumberPicker;", "build", "getMaxDayInMonth", "year", "month", "(Ljava/lang/Integer;Ljava/lang/Integer;)I", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class BaseDateTimeController implements DateTimeInterface {
    @NotNull
    public abstract BaseDateTimeController bindGlobal(int i);

    @NotNull
    public abstract BaseDateTimeController bindPicker(int i, @Nullable NumberPicker numberPicker);

    @NotNull
    public abstract BaseDateTimeController build();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getMaxDayInMonth(@Nullable Integer num, @Nullable Integer num2) {
        if (num == null || num2 == null || num.intValue() <= 0 || num2.intValue() < 0) {
            return 0;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
            calendar.clear();
            calendar.set(1, num.intValue());
            calendar.set(2, num2.intValue());
            return CalendarExt.getMaxDayInMonth(calendar);
        } catch (Exception unused) {
            return 0;
        }
    }
}
