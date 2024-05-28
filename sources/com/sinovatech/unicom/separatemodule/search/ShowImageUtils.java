package com.sinovatech.unicom.separatemodule.search;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShowImageUtils {
    private static final int DEFAULT_IMAG = 2131231244;
    private static final int ERROR_IMAG = 2131231244;

    public static void showImageView(Context context, String str, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231244).error(2131231244).fallback(2131231244)).into(imageView);
        }
    }

    public static void showOriginalImageView(Context context, String str, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().override(Integer.MIN_VALUE, Integer.MIN_VALUE).placeholder(2131232222).error(2131232222).fallback(2131232222)).into(imageView);
        }
    }

    public static void showOriginalImageView(Context context, Bitmap bitmap, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(bitmap).apply((BaseRequestOptions<?>) new RequestOptions().override(Integer.MIN_VALUE, Integer.MIN_VALUE).placeholder(2131232222).error(2131232222).fallback(2131232222)).into(imageView);
        }
    }

    public static void showImageView(Context context, Bitmap bitmap, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(bitmap).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231244).error(2131231244).fallback(2131231244)).into(imageView);
        }
    }

    public static void showImageView(Context context, int i, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231244).error(2131231244).fallback(2131231244)).into(imageView);
        }
    }

    public static void showImageView(Context context, String str, int i, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(i).fallback(i)).into(imageView);
        }
    }

    public static void showImageViewPreLoad(Context context, String str, RequestListener<Drawable> requestListener) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).addListener(requestListener).into(UIUtils.dip2px(25.0f), UIUtils.dip2px(25.0f));
        }
    }

    public static void showImageView(Context context, String str, int i, int i2, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(i2).fallback(i)).into(imageView);
        }
    }

    public static void showImageView(Context context, int i, int i2, int i3, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i2).error(i3).fallback(i2)).into(imageView);
        }
    }

    public static void showRoundImageView(Context context, String str, ImageView imageView, int i) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231244).error(2131231244).fallback(2131231244).transform(new RoundedCornersTransformation(i, 0, RoundedCornersTransformation.CornerType.ALL))).into(imageView);
        }
    }

    public static void showRoundImageView(Context context, int i, ImageView imageView, int i2) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231244).error(2131231244).fallback(2131231244).transform(new RoundedCornersTransformation(i2, 0, RoundedCornersTransformation.CornerType.ALL))).into(imageView);
        }
    }

    public static void showRoundImageView(Context context, int i, ImageView imageView, int i2, RoundedCornersTransformation.CornerType cornerType) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231244).error(2131231244).fallback(2131231244).transform(new RoundedCornersTransformation(i2, 0, cornerType))).into(imageView);
        }
    }

    public static void showRoundImageView(Context context, int i, int i2, ImageView imageView, int i3, RoundedCornersTransformation.CornerType cornerType) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i2).error(2131231244).fallback(i2).transform(new RoundedCornersTransformation(i3, 0, cornerType))).into(imageView);
        }
    }

    public static void showRoundImageView(Context context, int i, int i2, ImageView imageView, int i3) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i2).error(2131231244).fallback(i2).transform(new RoundedCornersTransformation(i3, 0, RoundedCornersTransformation.CornerType.ALL))).into(imageView);
        }
    }

    public static void showRoundImageView(Context context, String str, int i, ImageView imageView, int i2) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(2131231244).fallback(i).transform(new RoundedCornersTransformation(i2, 0, RoundedCornersTransformation.CornerType.ALL))).into(imageView);
        }
    }

    public static void showCenterCropRoundImageView(Context context, String str, int i, ImageView imageView, int i2) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(i).fallback(i).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i2, 0, RoundedCornersTransformation.CornerType.ALL)))).into(imageView);
        }
    }

    public static void showCenterCropRoundImageView(Context context, String str, ImageView imageView, int i) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231244).error(2131231244).fallback(2131231244).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i, 0, RoundedCornersTransformation.CornerType.ALL)))).into(imageView);
        }
    }

    public static void showCenterCropRoundImageView(Context context, int i, ImageView imageView, int i2) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231244).error(2131231244).fallback(2131231244).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i2, 0, RoundedCornersTransformation.CornerType.ALL)))).into(imageView);
        }
    }

    public static void showCenterCropRoundImageView(Context context, int i, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(Integer.valueOf(i)).into(imageView);
        }
    }

    public static void showCenterCropRoundImageView(Context context, String str, int i, ImageView imageView, int i2, RoundedCornersTransformation.CornerType cornerType) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(2131231244).fallback(i).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i2, 0, cornerType)))).into(imageView);
        }
    }

    private static boolean isValidContextForGlide(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            return Build.VERSION.SDK_INT >= 17 ? (activity.isDestroyed() || activity.isFinishing()) ? false : true : !activity.isFinishing();
        }
        return true;
    }

    public static void showImageViewImg(Context context, String str, int i, int i2, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).asBitmap().load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(i2).fallback(i)).into(imageView);
        }
    }

    public static void showImageViewImg(Context context, String str, int i, int i2, ImageView imageView, RequestListener<Bitmap> requestListener) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).asBitmap().load(str).listener(requestListener).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(i2).fallback(i)).into(imageView);
        }
    }

    public static void showImageViewGIF(Context context, String str, int i, int i2, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(i2).fallback(i)).into(imageView);
        }
    }

    public static void showImageViewGIF(Context context, String str, int i, int i2, ImageView imageView, RequestListener<Drawable> requestListener) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).listener(requestListener).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i).error(i2).fallback(i)).into(imageView);
        }
    }
}
