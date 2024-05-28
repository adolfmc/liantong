package com.sinovatech.unicom.basic.p315ui.view.date_time_picker;

import com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker;
import java.text.DateFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: DateTimeConfig.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012¨\u0006\u001b"}, m1890d2 = {"Lcom/sinovatech/unicom/basic/ui/view/date_time_picker/DateTimeConfig;", "", "()V", "DATE_DEFAULT", "", "DATE_LUNAR", "DAY", "GLOBAL_CHINA", "GLOBAL_LOCAL", "GLOBAL_US", "HOUR", "MIN", "MONTH", "SECOND", "YEAR", "formatter", "Lcom/sinovatech/unicom/basic/ui/view/date_time_picker/number_picker/NumberPicker$Formatter;", "getFormatter", "()Lcom/sinovatech/unicom/basic/ui/view/date_time_picker/number_picker/NumberPicker$Formatter;", "globalMonthFormatter", "getGlobalMonthFormatter", "globalizationMonthFormatter", "getGlobalizationMonthFormatter", "isChina", "", "showChina", "global", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.sinovatech.unicom.basic.ui.view.date_time_picker.DateTimeConfig */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class DateTimeConfig {
    public static final int DATE_DEFAULT = 0;
    public static final int DATE_LUNAR = 1;
    public static final int DAY = 2;
    public static final int GLOBAL_CHINA = 1;
    public static final int GLOBAL_LOCAL = 0;
    public static final int GLOBAL_US = 2;
    public static final int HOUR = 3;
    public static final int MIN = 4;
    public static final int MONTH = 1;
    public static final int SECOND = 5;
    public static final int YEAR = 0;
    public static final DateTimeConfig INSTANCE = new DateTimeConfig();
    @NotNull
    private static final NumberPicker.Formatter formatter = new NumberPicker.Formatter() { // from class: com.sinovatech.unicom.basic.ui.view.date_time_picker.DateTimeConfig$formatter$1
        @Override // com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker.Formatter
        @NotNull
        public final String format(int i) {
            String valueOf = String.valueOf(i);
            if (i < 10) {
                return '0' + valueOf;
            }
            return valueOf;
        }
    };
    @NotNull
    private static final NumberPicker.Formatter globalizationMonthFormatter = new NumberPicker.Formatter() { // from class: com.sinovatech.unicom.basic.ui.view.date_time_picker.DateTimeConfig$globalizationMonthFormatter$1
        @Override // com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker.Formatter
        @NotNull
        public final String format(int i) {
            String valueOf = String.valueOf(i);
            if (1 <= i && 12 >= i) {
                String[] months = new DateFormatSymbols(Locale.US).getMonths();
                Intrinsics.checkExpressionValueIsNotNull(months, "DateFormatSymbols(Locale.US).months");
                Object obj = ArraysKt.toList(months).get(i - 1);
                Intrinsics.checkExpressionValueIsNotNull(obj, "DateFormatSymbols(Locale…onths.toList()[value - 1]");
                return (String) obj;
            }
            return valueOf;
        }
    };
    @NotNull
    private static final NumberPicker.Formatter globalMonthFormatter = new NumberPicker.Formatter() { // from class: com.sinovatech.unicom.basic.ui.view.date_time_picker.DateTimeConfig$globalMonthFormatter$1
        @Override // com.sinovatech.unicom.basic.p315ui.view.date_time_picker.number_picker.NumberPicker.Formatter
        @NotNull
        public final String format(int i) {
            String valueOf = String.valueOf(i);
            if (1 <= i && 12 >= i) {
                String[] months = new DateFormatSymbols(Locale.US).getMonths();
                Intrinsics.checkExpressionValueIsNotNull(months, "DateFormatSymbols(Locale.US).months");
                String month = (String) ArraysKt.toList(months).get(i - 1);
                if (month.length() > 3) {
                    Intrinsics.checkExpressionValueIsNotNull(month, "month");
                    if (month != null) {
                        String substring = month.substring(0, 3);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        return substring;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                Intrinsics.checkExpressionValueIsNotNull(month, "month");
                return month;
            }
            return valueOf;
        }
    };

    private DateTimeConfig() {
    }

    @NotNull
    public final NumberPicker.Formatter getFormatter() {
        return formatter;
    }

    @NotNull
    public final NumberPicker.Formatter getGlobalizationMonthFormatter() {
        return globalizationMonthFormatter;
    }

    @NotNull
    public final NumberPicker.Formatter getGlobalMonthFormatter() {
        return globalMonthFormatter;
    }

    private final boolean isChina() {
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        String language = locale.getLanguage();
        Intrinsics.checkExpressionValueIsNotNull(language, "Locale.getDefault().language");
        return StringsKt.contains((CharSequence) language, (CharSequence) "zh", true);
    }

    public final boolean showChina(int i) {
        if (i != 1) {
            return i == 0 && isChina();
        }
        return true;
    }
}
