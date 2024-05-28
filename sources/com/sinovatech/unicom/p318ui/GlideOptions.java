package com.sinovatech.unicom.p318ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fort.andjni.JniLib;

/* renamed from: com.sinovatech.unicom.ui.GlideOptions */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class GlideOptions extends RequestOptions implements Cloneable {
    private static GlideOptions centerCropTransform2;
    private static GlideOptions centerInsideTransform1;
    private static GlideOptions circleCropTransform3;
    private static GlideOptions fitCenterTransform0;
    private static GlideOptions noAnimation5;
    private static GlideOptions noTransformation4;

    @CheckResult
    @NonNull
    public static GlideOptions bitmapTransform(@NonNull Transformation<Bitmap> transformation) {
        Object m15920cL = JniLib.m15920cL(transformation, 224);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions centerCropTransform() {
        Object m15920cL = JniLib.m15920cL(225);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions centerInsideTransform() {
        Object m15920cL = JniLib.m15920cL(226);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions circleCropTransform() {
        Object m15920cL = JniLib.m15920cL(227);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions decodeTypeOf(@NonNull Class<?> cls) {
        Object m15920cL = JniLib.m15920cL(cls, 228);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy diskCacheStrategy) {
        Object m15920cL = JniLib.m15920cL(diskCacheStrategy, 229);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions downsampleOf(@NonNull DownsampleStrategy downsampleStrategy) {
        Object m15920cL = JniLib.m15920cL(downsampleStrategy, 230);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions encodeFormatOf(@NonNull Bitmap.CompressFormat compressFormat) {
        Object m15920cL = JniLib.m15920cL(compressFormat, 231);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions encodeQualityOf(@IntRange(from = 0, m22209to = 100) int i) {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf(i), 232);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions errorOf(@DrawableRes int i) {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf(i), 233);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions errorOf(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(drawable, 234);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions fitCenterTransform() {
        Object m15920cL = JniLib.m15920cL(235);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions formatOf(@NonNull DecodeFormat decodeFormat) {
        Object m15920cL = JniLib.m15920cL(decodeFormat, 236);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions frameOf(@IntRange(from = 0) long j) {
        Object m15920cL = JniLib.m15920cL(Long.valueOf(j), 237);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions noAnimation() {
        Object m15920cL = JniLib.m15920cL(238);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions noTransformation() {
        Object m15920cL = JniLib.m15920cL(239);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static <T> GlideOptions option(@NonNull Option<T> option, @NonNull T t) {
        Object m15920cL = JniLib.m15920cL(option, t, 240);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions overrideOf(@IntRange(from = 0) int i) {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf(i), 241);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions overrideOf(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf(i), Integer.valueOf(i2), 242);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions placeholderOf(@DrawableRes int i) {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf(i), 243);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions placeholderOf(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(drawable, 244);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions priorityOf(@NonNull Priority priority) {
        Object m15920cL = JniLib.m15920cL(priority, 245);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions signatureOf(@NonNull Key key) {
        Object m15920cL = JniLib.m15920cL(key, 246);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions sizeMultiplierOf(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        Object m15920cL = JniLib.m15920cL(Float.valueOf(f), 247);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions skipMemoryCacheOf(boolean z) {
        Object m15920cL = JniLib.m15920cL(Boolean.valueOf(z), 248);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @CheckResult
    @NonNull
    public static GlideOptions timeoutOf(@IntRange(from = 0) int i) {
        Object m15920cL = JniLib.m15920cL(Integer.valueOf(i), 249);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public RequestOptions apply2(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        Object m15920cL = JniLib.m15920cL(this, baseRequestOptions, 194);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: decode  reason: avoid collision after fix types in other method */
    public RequestOptions decode2(@NonNull Class<?> cls) {
        Object m15920cL = JniLib.m15920cL(this, cls, 195);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        Object m15920cL = JniLib.m15920cL(this, diskCacheStrategy, 196);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        Object m15920cL = JniLib.m15920cL(this, downsampleStrategy, 197);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        Object m15920cL = JniLib.m15920cL(this, compressFormat, 198);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions encodeQuality(@IntRange(from = 0, m22209to = 100) int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 199);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions error(@DrawableRes int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 200);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions error(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(this, drawable, 201);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions fallback(@DrawableRes int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 202);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions fallback(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(this, drawable, 203);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions format(@NonNull DecodeFormat decodeFormat) {
        Object m15920cL = JniLib.m15920cL(this, decodeFormat, 204);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions frame(@IntRange(from = 0) long j) {
        Object m15920cL = JniLib.m15920cL(this, Long.valueOf(j), 205);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions onlyRetrieveFromCache(boolean z) {
        Object m15920cL = JniLib.m15920cL(this, Boolean.valueOf(z), 206);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: optionalTransform  reason: avoid collision after fix types in other method */
    public RequestOptions optionalTransform2(@NonNull Transformation<Bitmap> transformation) {
        Object m15920cL = JniLib.m15920cL(this, transformation, 207);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public <Y> RequestOptions optionalTransform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        Object m15920cL = JniLib.m15920cL(this, cls, transformation, 208);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions override(int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 209);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions override(int i, int i2) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), Integer.valueOf(i2), 210);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions placeholder(@DrawableRes int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 211);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions placeholder(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(this, drawable, 212);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions priority(@NonNull Priority priority) {
        Object m15920cL = JniLib.m15920cL(this, priority, 213);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: set  reason: avoid collision after fix types in other method */
    public <Y> RequestOptions set2(@NonNull Option<Y> option, @NonNull Y y) {
        Object m15920cL = JniLib.m15920cL(this, option, y, 214);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions signature(@NonNull Key key) {
        Object m15920cL = JniLib.m15920cL(this, key, 215);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions sizeMultiplier(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        Object m15920cL = JniLib.m15920cL(this, Float.valueOf(f), 216);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions skipMemoryCache(boolean z) {
        Object m15920cL = JniLib.m15920cL(this, Boolean.valueOf(z), 217);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions theme(@Nullable Resources.Theme theme) {
        Object m15920cL = JniLib.m15920cL(this, theme, 218);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions timeout(@IntRange(from = 0) int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 219);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: transform  reason: avoid collision after fix types in other method */
    public RequestOptions transform2(@NonNull Transformation<Bitmap> transformation) {
        Object m15920cL = JniLib.m15920cL(this, transformation, 220);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public <Y> RequestOptions transform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        Object m15920cL = JniLib.m15920cL(this, cls, transformation, 221);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions useAnimationPool(boolean z) {
        Object m15920cL = JniLib.m15920cL(this, Boolean.valueOf(z), 222);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions useUnlimitedSourceGeneratorsPool(boolean z) {
        Object m15920cL = JniLib.m15920cL(this, Boolean.valueOf(z), 223);
        if (m15920cL == null) {
            return null;
        }
        return (GlideOptions) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ RequestOptions apply(@NonNull BaseRequestOptions baseRequestOptions) {
        return apply2((BaseRequestOptions<?>) baseRequestOptions);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ RequestOptions decode(@NonNull Class cls) {
        return decode2((Class<?>) cls);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ RequestOptions optionalTransform(@NonNull Transformation transformation) {
        return optionalTransform2((Transformation<Bitmap>) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ RequestOptions set(@NonNull Option option, @NonNull Object obj) {
        return set2((Option<Option>) option, (Option) obj);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ RequestOptions transform(@NonNull Transformation transformation) {
        return transform2((Transformation<Bitmap>) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @SafeVarargs
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ RequestOptions transform(@NonNull Transformation[] transformationArr) {
        return transform2((Transformation<Bitmap>[]) transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @Deprecated
    @SafeVarargs
    @CheckResult
    public /* bridge */ /* synthetic */ RequestOptions transforms(@NonNull Transformation[] transformationArr) {
        return transforms2((Transformation<Bitmap>[]) transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    /* renamed from: clone  reason: avoid collision after fix types in other method */
    public RequestOptions mo24465clone() {
        return (GlideOptions) super.mo24465clone();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions disallowHardwareConfig() {
        return (GlideOptions) super.disallowHardwareConfig();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions optionalCenterCrop() {
        return (GlideOptions) super.optionalCenterCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions centerCrop() {
        return (GlideOptions) super.centerCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions optionalFitCenter() {
        return (GlideOptions) super.optionalFitCenter();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions fitCenter() {
        return (GlideOptions) super.fitCenter();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions optionalCenterInside() {
        return (GlideOptions) super.optionalCenterInside();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions centerInside() {
        return (GlideOptions) super.centerInside();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions optionalCircleCrop() {
        return (GlideOptions) super.optionalCircleCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions circleCrop() {
        return (GlideOptions) super.circleCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @SafeVarargs
    @CheckResult
    @NonNull
    /* renamed from: transform  reason: avoid collision after fix types in other method */
    public final RequestOptions transform2(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideOptions) super.transform(transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @Deprecated
    @SafeVarargs
    @CheckResult
    /* renamed from: transforms  reason: avoid collision after fix types in other method */
    public final RequestOptions transforms2(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideOptions) super.transforms(transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions dontTransform() {
        return (GlideOptions) super.dontTransform();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions dontAnimate() {
        return (GlideOptions) super.dontAnimate();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    public RequestOptions lock() {
        return (GlideOptions) super.lock();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    public RequestOptions autoClone() {
        return (GlideOptions) super.autoClone();
    }
}
