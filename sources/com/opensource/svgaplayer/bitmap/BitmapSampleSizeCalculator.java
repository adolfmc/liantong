package com.opensource.svgaplayer.bitmap;

import android.graphics.BitmapFactory;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: BitmapSampleSizeCalculator.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, m1890d2 = {"Lcom/opensource/svgaplayer/bitmap/BitmapSampleSizeCalculator;", "", "()V", "calculate", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class BitmapSampleSizeCalculator {
    public static final BitmapSampleSizeCalculator INSTANCE = new BitmapSampleSizeCalculator();

    private BitmapSampleSizeCalculator() {
    }

    public final int calculate(@NotNull BitmapFactory.Options options, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(options, "options");
        Tuples m1883to = TuplesKt.m1883to(Integer.valueOf(options.outHeight), Integer.valueOf(options.outWidth));
        int intValue = ((Number) m1883to.component1()).intValue();
        int intValue2 = ((Number) m1883to.component2()).intValue();
        int i3 = 1;
        if (i2 <= 0 || i <= 0) {
            return 1;
        }
        if (intValue > i2 || intValue2 > i) {
            int i4 = intValue / 2;
            int i5 = intValue2 / 2;
            while (i4 / i3 >= i2 && i5 / i3 >= i) {
                i3 *= 2;
            }
        }
        return i3;
    }
}
