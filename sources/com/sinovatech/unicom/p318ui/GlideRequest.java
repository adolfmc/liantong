package com.sinovatech.unicom.p318ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.fort.andjni.JniLib;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import java.io.File;
import java.net.URL;

/* renamed from: com.sinovatech.unicom.ui.GlideRequest */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> implements Cloneable {
    @Override // com.bumptech.glide.RequestBuilder
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> addListener(@Nullable RequestListener<TranscodeType> requestListener) {
        Object m15920cL = JniLib.m15920cL(this, requestListener, 250);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> apply(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        Object m15920cL = JniLib.m15920cL(this, baseRequestOptions, 251);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> decode(@NonNull Class<?> cls) {
        Object m15920cL = JniLib.m15920cL(this, cls, 252);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        Object m15920cL = JniLib.m15920cL(this, diskCacheStrategy, 253);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        Object m15920cL = JniLib.m15920cL(this, downsampleStrategy, 254);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        Object m15920cL = JniLib.m15920cL(this, compressFormat, 255);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> encodeQuality(@IntRange(from = 0, m22209to = 100) int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 256);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> error(@DrawableRes int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 257);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> error(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(this, drawable, 258);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder
    @NonNull
    public GlideRequest<TranscodeType> error(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        Object m15920cL = JniLib.m15920cL(this, requestBuilder, 259);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> fallback(@DrawableRes int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 260);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> fallback(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(this, drawable, 261);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> format(@NonNull DecodeFormat decodeFormat) {
        Object m15920cL = JniLib.m15920cL(this, decodeFormat, 262);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> frame(@IntRange(from = 0) long j) {
        Object m15920cL = JniLib.m15920cL(this, Long.valueOf(j), 263);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> listener(@Nullable RequestListener<TranscodeType> requestListener) {
        Object m15920cL = JniLib.m15920cL(this, requestListener, 264);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> load(@Nullable Bitmap bitmap) {
        Object m15920cL = JniLib.m15920cL(this, bitmap, 265);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> load(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(this, drawable, 266);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> load(@Nullable Uri uri) {
        Object m15920cL = JniLib.m15920cL(this, uri, 267);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> load(@Nullable File file) {
        Object m15920cL = JniLib.m15920cL(this, file, 268);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> load(@RawRes @DrawableRes @Nullable Integer num) {
        Object m15920cL = JniLib.m15920cL(this, num, 269);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> load(@Nullable Object obj) {
        Object m15920cL = JniLib.m15920cL(this, obj, Integer.valueOf((int) SubsamplingScaleImageView.ORIENTATION_270));
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> load(@Nullable String str) {
        Object m15920cL = JniLib.m15920cL(this, str, 271);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @Deprecated
    public GlideRequest<TranscodeType> load(@Nullable URL url) {
        Object m15920cL = JniLib.m15920cL(this, url, 272);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> load(@Nullable byte[] bArr) {
        Object m15920cL = JniLib.m15920cL(this, bArr, Integer.valueOf((int) BaseQuickAdapter.HEADER_VIEW));
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean z) {
        Object m15920cL = JniLib.m15920cL(this, Boolean.valueOf(z), 274);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> optionalTransform(@NonNull Transformation<Bitmap> transformation) {
        Object m15920cL = JniLib.m15920cL(this, transformation, 275);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public <Y> GlideRequest<TranscodeType> optionalTransform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        Object m15920cL = JniLib.m15920cL(this, cls, transformation, 276);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> override(int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 277);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> override(int i, int i2) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), Integer.valueOf(i2), 278);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> placeholder(@DrawableRes int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 279);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> placeholder(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(this, drawable, 280);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> priority(@NonNull Priority priority) {
        Object m15920cL = JniLib.m15920cL(this, priority, 281);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public <Y> GlideRequest<TranscodeType> set(@NonNull Option<Y> option, @NonNull Y y) {
        Object m15920cL = JniLib.m15920cL(this, option, y, 282);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> signature(@NonNull Key key) {
        Object m15920cL = JniLib.m15920cL(this, key, 283);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> sizeMultiplier(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        Object m15920cL = JniLib.m15920cL(this, Float.valueOf(f), 284);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> skipMemoryCache(boolean z) {
        Object m15920cL = JniLib.m15920cL(this, Boolean.valueOf(z), 285);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> theme(@Nullable Resources.Theme theme) {
        Object m15920cL = JniLib.m15920cL(this, theme, 286);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> thumbnail(float f) {
        Object m15920cL = JniLib.m15920cL(this, Float.valueOf(f), 287);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        Object m15920cL = JniLib.m15920cL(this, requestBuilder, 288);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> timeout(@IntRange(from = 0) int i) {
        Object m15920cL = JniLib.m15920cL(this, Integer.valueOf(i), 289);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> transform(@NonNull Transformation<Bitmap> transformation) {
        Object m15920cL = JniLib.m15920cL(this, transformation, 290);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public <Y> GlideRequest<TranscodeType> transform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        Object m15920cL = JniLib.m15920cL(this, cls, transformation, 291);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> transition(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        Object m15920cL = JniLib.m15920cL(this, transitionOptions, 292);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> useAnimationPool(boolean z) {
        Object m15920cL = JniLib.m15920cL(this, Boolean.valueOf(z), 293);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean z) {
        Object m15920cL = JniLib.m15920cL(this, Boolean.valueOf(z), 294);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ RequestBuilder apply(@NonNull BaseRequestOptions baseRequestOptions) {
        return apply((BaseRequestOptions<?>) baseRequestOptions);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ BaseRequestOptions apply(@NonNull BaseRequestOptions baseRequestOptions) {
        return apply((BaseRequestOptions<?>) baseRequestOptions);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ BaseRequestOptions decode(@NonNull Class cls) {
        return decode((Class<?>) cls);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ BaseRequestOptions optionalTransform(@NonNull Transformation transformation) {
        return optionalTransform((Transformation<Bitmap>) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ BaseRequestOptions set(@NonNull Option option, @NonNull Object obj) {
        return set((Option<Option>) option, (Option) obj);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ BaseRequestOptions transform(@NonNull Transformation transformation) {
        return transform((Transformation<Bitmap>) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public /* bridge */ /* synthetic */ BaseRequestOptions transform(@NonNull Transformation[] transformationArr) {
        return transform((Transformation<Bitmap>[]) transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    @Deprecated
    public /* bridge */ /* synthetic */ BaseRequestOptions transforms(@NonNull Transformation[] transformationArr) {
        return transforms((Transformation<Bitmap>[]) transformationArr);
    }

    GlideRequest(@NonNull Class<TranscodeType> cls, @NonNull RequestBuilder<?> requestBuilder) {
        super(cls, requestBuilder);
    }

    GlideRequest(@NonNull Glide glide, @NonNull RequestManager requestManager, @NonNull Class<TranscodeType> cls, @NonNull Context context) {
        super(glide, requestManager, cls, context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.RequestBuilder
    @CheckResult
    @NonNull
    public GlideRequest<File> getDownloadOnlyRequest() {
        return new GlideRequest(File.class, this).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        return (GlideRequest) super.disallowHardwareConfig();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> optionalCenterCrop() {
        return (GlideRequest) super.optionalCenterCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> centerCrop() {
        return (GlideRequest) super.centerCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> optionalFitCenter() {
        return (GlideRequest) super.optionalFitCenter();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> fitCenter() {
        return (GlideRequest) super.fitCenter();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> optionalCenterInside() {
        return (GlideRequest) super.optionalCenterInside();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> centerInside() {
        return (GlideRequest) super.centerInside();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> optionalCircleCrop() {
        return (GlideRequest) super.optionalCircleCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> circleCrop() {
        return (GlideRequest) super.circleCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> transform(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideRequest) super.transform(transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    @Deprecated
    public GlideRequest<TranscodeType> transforms(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideRequest) super.transforms(transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> dontTransform() {
        return (GlideRequest) super.dontTransform();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public GlideRequest<TranscodeType> dontAnimate() {
        return (GlideRequest) super.dontAnimate();
    }

    @Override // com.bumptech.glide.RequestBuilder
    @SafeVarargs
    @CheckResult
    @NonNull
    public final GlideRequest<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType>... requestBuilderArr) {
        return (GlideRequest) super.thumbnail((RequestBuilder[]) requestBuilderArr);
    }

    @Override // com.bumptech.glide.RequestBuilder, com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    /* renamed from: clone */
    public GlideRequest<TranscodeType> mo24465clone() {
        return (GlideRequest) super.mo24465clone();
    }
}
