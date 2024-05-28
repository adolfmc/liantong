package com.loper7.tab_expand.ext;

import android.content.Context;
import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¨\u0006\u0007"}, m1890d2 = {"dip2px", "", "Landroid/content/Context;", "dpValue", "", "px2dip", "pxValue", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: com.loper7.tab_expand.ext.ContextExtKt */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class ContextExt {
    public static final int dip2px(@NotNull Context dip2px, float f) {
        Intrinsics.checkParameterIsNotNull(dip2px, "$this$dip2px");
        Resources resources = dip2px.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((f * resources.getDisplayMetrics().density) + 0.5f);
    }

    public static final int px2dip(@NotNull Context px2dip, float f) {
        Intrinsics.checkParameterIsNotNull(px2dip, "$this$px2dip");
        Resources resources = px2dip.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((f / resources.getDisplayMetrics().density) + 0.5f);
    }
}
