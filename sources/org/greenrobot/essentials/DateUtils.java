package org.greenrobot.essentials;

import java.util.Calendar;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DateUtils {
    private static ThreadLocal<Calendar> calendarThreadLocal = new DefaultCalendarThreadLocal();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DefaultCalendarThreadLocal extends ThreadLocal<Calendar> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Calendar initialValue() {
            return Calendar.getInstance();
        }
    }

    public static long getTimeForDay(int i, int i2, int i3) {
        return getTimeForDay(calendarThreadLocal.get(), i, i2, i3);
    }

    public static long getTimeForDay(Calendar calendar, int i, int i2, int i3) {
        calendar.clear();
        calendar.set(i, i2 - 1, i3);
        return calendar.getTimeInMillis();
    }

    public static void setTime(Calendar calendar, int i, int i2, int i3, int i4) {
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, i4);
    }

    public static int getDayAsReadableInt(long j) {
        Calendar calendar = calendarThreadLocal.get();
        calendar.setTimeInMillis(j);
        return getDayAsReadableInt(calendar);
    }

    public static int getDayAsReadableInt(Calendar calendar) {
        return (calendar.get(1) * 10000) + ((calendar.get(2) + 1) * 100) + calendar.get(5);
    }

    public static long getTimeFromDayReadableInt(int i) {
        return getTimeFromDayReadableInt(calendarThreadLocal.get(), i, 0);
    }

    public static long getTimeFromDayReadableInt(Calendar calendar, int i, int i2) {
        calendar.clear();
        calendar.set(11, i2);
        calendar.set(5, i % 100);
        calendar.set(2, ((i / 100) % 100) - 1);
        calendar.set(1, i / 10000);
        return calendar.getTimeInMillis();
    }

    public static int getDayDifferenceOfReadableInts(int i, int i2) {
        return Math.round(((float) ((((getTimeFromDayReadableInt(i2) - getTimeFromDayReadableInt(i)) / 1000) / 60) / 60)) / 24.0f);
    }

    public static int getDayDifference(long j, long j2) {
        return (int) (((((j2 - j) / 1000) / 60) / 60) / 24);
    }

    public static long addDays(long j, int i) {
        Calendar calendar = calendarThreadLocal.get();
        calendar.setTimeInMillis(j);
        calendar.add(6, i);
        return calendar.getTimeInMillis();
    }

    public static void addDays(Calendar calendar, int i) {
        calendar.add(6, i);
    }
}
