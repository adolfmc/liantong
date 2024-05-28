package com.loper7.date_time_picker.controller;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: DateTimeInterface.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J \u0010\t\u001a\u00020\u00052\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bH&J$\u0010\f\u001a\u00020\u00052\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0012"}, m1890d2 = {"Lcom/loper7/date_time_picker/controller/DateTimeInterface;", "", "getMillisecond", "", "setDefaultMillisecond", "", "time", "setMaxMillisecond", "setMinMillisecond", "setOnDateTimeChangedListener", "callback", "Lkotlin/Function1;", "setWrapSelectorWheel", "types", "", "", "wrapSelector", "", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DateTimeInterface {
    long getMillisecond();

    void setDefaultMillisecond(long j);

    void setMaxMillisecond(long j);

    void setMinMillisecond(long j);

    void setOnDateTimeChangedListener(@Nullable Function1<? super Long, Unit> function1);

    void setWrapSelectorWheel(@Nullable List<Integer> list, boolean z);

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: DateTimeInterface.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1889k = 3, m1888mv = {1, 1, 16})
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void setWrapSelectorWheel$default(DateTimeInterface dateTimeInterface, List list, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setWrapSelectorWheel");
            }
            if ((i & 1) != 0) {
                list = null;
            }
            if ((i & 2) != 0) {
                z = true;
            }
            dateTimeInterface.setWrapSelectorWheel(list, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void setOnDateTimeChangedListener$default(DateTimeInterface dateTimeInterface, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOnDateTimeChangedListener");
            }
            if ((i & 1) != 0) {
                function1 = null;
            }
            dateTimeInterface.setOnDateTimeChangedListener(function1);
        }
    }
}
