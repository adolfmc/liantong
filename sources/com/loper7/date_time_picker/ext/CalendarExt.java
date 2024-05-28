package com.loper7.date_time_picker.ext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00000\n\u0000\n\u0002\u0010!\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u0007\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\f\u0010\t\u001a\u00020\u0005*\u00020\u0003H\u0000\u001a\u0016\u0010\n\u001a\u00020\u0005*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u000b\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\"\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u0001*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u000f\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0012\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0013\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0015\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0000¨\u0006\u0017"}, m1890d2 = {"getDaysOfWeek", "", "", "Ljava/util/Calendar;", "year", "", "week", "getMaxDayAtYear", "Ljava/util/GregorianCalendar;", "getMaxDayInMonth", "getMaxWeekOfYear", "getWeekOfYear", "date", "Ljava/util/Date;", "getWeeksOfYear", "isSameDay", "", "calendar", "isSameHour", "isSameMinute", "isSameMonth", "isSameSecond", "isSameYear", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: com.loper7.date_time_picker.ext.CalendarExtKt */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CalendarExt {
    public static /* synthetic */ List getWeeksOfYear$default(Calendar calendar, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Calendar.getInstance().get(1);
        }
        return getWeeksOfYear(calendar, i);
    }

    @NotNull
    public static final List<List<Long>> getWeeksOfYear(@NotNull Calendar getWeeksOfYear, int i) {
        Intrinsics.checkParameterIsNotNull(getWeeksOfYear, "$this$getWeeksOfYear");
        if (i < 1900 || i > 9999) {
            throw new NullPointerException("The year must be within 1900-9999");
        }
        getWeeksOfYear.setFirstDayOfWeek(2);
        getWeeksOfYear.set(7, 2);
        getWeeksOfYear.setMinimalDaysInFirstWeek(7);
        int i2 = 1;
        getWeeksOfYear.set(1, i);
        ArrayList arrayList = new ArrayList();
        int maxWeekOfYear = getMaxWeekOfYear(getWeeksOfYear, i);
        if (1 <= maxWeekOfYear) {
            while (true) {
                arrayList.add(getDaysOfWeek(getWeeksOfYear, i, i2));
                if (i2 == maxWeekOfYear) {
                    break;
                }
                i2++;
            }
        }
        return arrayList;
    }

    public static /* synthetic */ int getMaxWeekOfYear$default(Calendar calendar, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Calendar.getInstance().get(1);
        }
        return getMaxWeekOfYear(calendar, i);
    }

    public static final int getMaxWeekOfYear(@NotNull Calendar getMaxWeekOfYear, int i) {
        Intrinsics.checkParameterIsNotNull(getMaxWeekOfYear, "$this$getMaxWeekOfYear");
        getMaxWeekOfYear.set(i, 11, 31, 0, 0, 0);
        Date time = getMaxWeekOfYear.getTime();
        Intrinsics.checkExpressionValueIsNotNull(time, "time");
        return getWeekOfYear(getMaxWeekOfYear, time);
    }

    public static final int getWeekOfYear(@NotNull Calendar getWeekOfYear, @NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(getWeekOfYear, "$this$getWeekOfYear");
        Intrinsics.checkParameterIsNotNull(date, "date");
        getWeekOfYear.setFirstDayOfWeek(2);
        getWeekOfYear.setMinimalDaysInFirstWeek(7);
        getWeekOfYear.setTime(date);
        return getWeekOfYear.get(3);
    }

    public static /* synthetic */ List getDaysOfWeek$default(Calendar calendar, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = Calendar.getInstance().get(1);
        }
        return getDaysOfWeek(calendar, i, i2);
    }

    @NotNull
    public static final List<Long> getDaysOfWeek(@NotNull Calendar getDaysOfWeek, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(getDaysOfWeek, "$this$getDaysOfWeek");
        if (i < 1900 || i > 9999) {
            throw new NullPointerException("The year must be within 1900-9999");
        }
        getDaysOfWeek.setFirstDayOfWeek(2);
        getDaysOfWeek.set(7, 2);
        getDaysOfWeek.setMinimalDaysInFirstWeek(7);
        getDaysOfWeek.set(1, i);
        getDaysOfWeek.set(3, i2);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < 7; i3++) {
            arrayList.add(Long.valueOf(getDaysOfWeek.getTimeInMillis() + (86400000 * i3)));
        }
        return arrayList;
    }

    public static final int getMaxDayAtYear(@NotNull GregorianCalendar getMaxDayAtYear, int i) {
        Intrinsics.checkParameterIsNotNull(getMaxDayAtYear, "$this$getMaxDayAtYear");
        getMaxDayAtYear.set(1, i);
        return (getMaxDayAtYear.isLeapYear(i) ? 1 : 0) + 365;
    }

    public static final int getMaxDayInMonth(@NotNull Calendar getMaxDayInMonth) {
        Intrinsics.checkParameterIsNotNull(getMaxDayInMonth, "$this$getMaxDayInMonth");
        return getMaxDayInMonth.getActualMaximum(5);
    }

    public static final boolean isSameYear(@NotNull Calendar isSameYear, @NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(isSameYear, "$this$isSameYear");
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        return isSameYear.get(1) == calendar.get(1);
    }

    public static final boolean isSameMonth(@NotNull Calendar isSameMonth, @NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(isSameMonth, "$this$isSameMonth");
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        return isSameYear(isSameMonth, calendar) && isSameMonth.get(2) == calendar.get(2);
    }

    public static final boolean isSameDay(@NotNull Calendar isSameDay, @NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(isSameDay, "$this$isSameDay");
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        return isSameYear(isSameDay, calendar) && isSameDay.get(6) == calendar.get(6);
    }

    public static final boolean isSameHour(@NotNull Calendar isSameHour, @NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(isSameHour, "$this$isSameHour");
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        return isSameDay(isSameHour, calendar) && isSameHour.get(11) == calendar.get(11);
    }

    public static final boolean isSameMinute(@NotNull Calendar isSameMinute, @NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(isSameMinute, "$this$isSameMinute");
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        return isSameHour(isSameMinute, calendar) && isSameMinute.get(12) == calendar.get(12);
    }

    public static final boolean isSameSecond(@NotNull Calendar isSameSecond, @NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(isSameSecond, "$this$isSameSecond");
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        return isSameMinute(isSameSecond, calendar) && isSameSecond.get(13) == calendar.get(13);
    }
}
