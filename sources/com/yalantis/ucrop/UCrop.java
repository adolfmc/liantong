package com.yalantis.ucrop;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.yalantis.ucrop.model.AspectRatio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class UCrop {
    public static final String EXTRA_ASPECT_RATIO_X = "com.yalantis.ucrop.AspectRatioX";
    public static final String EXTRA_ASPECT_RATIO_Y = "com.yalantis.ucrop.AspectRatioY";
    public static final String EXTRA_ERROR = "com.yalantis.ucrop.Error";
    public static final String EXTRA_INPUT_URI = "com.yalantis.ucrop.InputUri";
    public static final String EXTRA_MAX_SIZE_X = "com.yalantis.ucrop.MaxSizeX";
    public static final String EXTRA_MAX_SIZE_Y = "com.yalantis.ucrop.MaxSizeY";
    public static final String EXTRA_OUTPUT_CROP_ASPECT_RATIO = "com.yalantis.ucrop.CropAspectRatio";
    public static final String EXTRA_OUTPUT_IMAGE_HEIGHT = "com.yalantis.ucrop.ImageHeight";
    public static final String EXTRA_OUTPUT_IMAGE_WIDTH = "com.yalantis.ucrop.ImageWidth";
    public static final String EXTRA_OUTPUT_OFFSET_X = "com.yalantis.ucrop.OffsetX";
    public static final String EXTRA_OUTPUT_OFFSET_Y = "com.yalantis.ucrop.OffsetY";
    public static final String EXTRA_OUTPUT_URI = "com.yalantis.ucrop.OutputUri";
    private static final String EXTRA_PREFIX = "com.yalantis.ucrop";
    public static final int MIN_SIZE = 10;
    public static final int REQUEST_CROP = 69;
    public static final int RESULT_ERROR = 96;
    private Intent mCropIntent = new Intent();
    private Bundle mCropOptionsBundle = new Bundle();

    /* renamed from: of */
    public static UCrop m2255of(@NonNull Uri uri, @NonNull Uri uri2) {
        return new UCrop(uri, uri2);
    }

    private UCrop(@NonNull Uri uri, @NonNull Uri uri2) {
        this.mCropOptionsBundle.putParcelable(EXTRA_INPUT_URI, uri);
        this.mCropOptionsBundle.putParcelable(EXTRA_OUTPUT_URI, uri2);
    }

    public UCrop withAspectRatio(float f, float f2) {
        this.mCropOptionsBundle.putFloat(EXTRA_ASPECT_RATIO_X, f);
        this.mCropOptionsBundle.putFloat(EXTRA_ASPECT_RATIO_Y, f2);
        return this;
    }

    public UCrop useSourceImageAspectRatio() {
        this.mCropOptionsBundle.putFloat(EXTRA_ASPECT_RATIO_X, 0.0f);
        this.mCropOptionsBundle.putFloat(EXTRA_ASPECT_RATIO_Y, 0.0f);
        return this;
    }

    public UCrop withMaxResultSize(@IntRange(from = 10) int i, @IntRange(from = 10) int i2) {
        if (i < 10) {
            i = 10;
        }
        if (i2 < 10) {
            i2 = 10;
        }
        this.mCropOptionsBundle.putInt(EXTRA_MAX_SIZE_X, i);
        this.mCropOptionsBundle.putInt(EXTRA_MAX_SIZE_Y, i2);
        return this;
    }

    public UCrop withOptions(@NonNull Options options) {
        this.mCropOptionsBundle.putAll(options.getOptionBundle());
        return this;
    }

    public void start(@NonNull Activity activity) {
        start(activity, 69);
    }

    public void start(@NonNull Activity activity, int i) {
        activity.startActivityForResult(getIntent(activity), i);
    }

    public void start(@NonNull Context context, @NonNull Fragment fragment) {
        start(context, fragment, 69);
    }

    public void start(@NonNull Context context, @NonNull android.support.p083v4.app.Fragment fragment) {
        start(context, fragment, 69);
    }

    @TargetApi(11)
    public void start(@NonNull Context context, @NonNull Fragment fragment, int i) {
        fragment.startActivityForResult(getIntent(context), i);
    }

    public void start(@NonNull Context context, @NonNull android.support.p083v4.app.Fragment fragment, int i) {
        fragment.startActivityForResult(getIntent(context), i);
    }

    public Intent getIntent(@NonNull Context context) {
        this.mCropIntent.setClass(context, UCropActivity.class);
        this.mCropIntent.putExtras(this.mCropOptionsBundle);
        return this.mCropIntent;
    }

    public UCropFragment getFragment() {
        return UCropFragment.newInstance(this.mCropOptionsBundle);
    }

    public UCropFragment getFragment(Bundle bundle) {
        this.mCropOptionsBundle = bundle;
        return getFragment();
    }

    @Nullable
    public static Uri getOutput(@NonNull Intent intent) {
        return (Uri) intent.getParcelableExtra(EXTRA_OUTPUT_URI);
    }

    public static int getOutputImageWidth(@NonNull Intent intent) {
        return intent.getIntExtra(EXTRA_OUTPUT_IMAGE_WIDTH, -1);
    }

    public static int getOutputImageHeight(@NonNull Intent intent) {
        return intent.getIntExtra(EXTRA_OUTPUT_IMAGE_HEIGHT, -1);
    }

    public static float getOutputCropAspectRatio(@NonNull Intent intent) {
        return intent.getFloatExtra(EXTRA_OUTPUT_CROP_ASPECT_RATIO, 0.0f);
    }

    @Nullable
    public static Throwable getError(@NonNull Intent intent) {
        return (Throwable) intent.getSerializableExtra(EXTRA_ERROR);
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class Options {
        public static final String EXTRA_ALLOWED_GESTURES = "com.yalantis.ucrop.AllowedGestures";
        public static final String EXTRA_ASPECT_RATIO_OPTIONS = "com.yalantis.ucrop.AspectRatioOptions";
        public static final String EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT = "com.yalantis.ucrop.AspectRatioSelectedByDefault";
        public static final String EXTRA_CIRCLE_DIMMED_LAYER = "com.yalantis.ucrop.CircleDimmedLayer";
        public static final String EXTRA_COMPRESSION_FORMAT_NAME = "com.yalantis.ucrop.CompressionFormatName";
        public static final String EXTRA_COMPRESSION_QUALITY = "com.yalantis.ucrop.CompressionQuality";
        public static final String EXTRA_CROP_FRAME_COLOR = "com.yalantis.ucrop.CropFrameColor";
        public static final String EXTRA_CROP_FRAME_STROKE_WIDTH = "com.yalantis.ucrop.CropFrameStrokeWidth";
        public static final String EXTRA_CROP_GRID_COLOR = "com.yalantis.ucrop.CropGridColor";
        public static final String EXTRA_CROP_GRID_COLUMN_COUNT = "com.yalantis.ucrop.CropGridColumnCount";
        public static final String EXTRA_CROP_GRID_ROW_COUNT = "com.yalantis.ucrop.CropGridRowCount";
        public static final String EXTRA_CROP_GRID_STROKE_WIDTH = "com.yalantis.ucrop.CropGridStrokeWidth";
        public static final String EXTRA_DIMMED_LAYER_COLOR = "com.yalantis.ucrop.DimmedLayerColor";
        public static final String EXTRA_FREE_STYLE_CROP = "com.yalantis.ucrop.FreeStyleCrop";
        public static final String EXTRA_HIDE_BOTTOM_CONTROLS = "com.yalantis.ucrop.HideBottomControls";
        public static final String EXTRA_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION = "com.yalantis.ucrop.ImageToCropBoundsAnimDuration";
        public static final String EXTRA_MAX_BITMAP_SIZE = "com.yalantis.ucrop.MaxBitmapSize";
        public static final String EXTRA_MAX_SCALE_MULTIPLIER = "com.yalantis.ucrop.MaxScaleMultiplier";
        public static final String EXTRA_SHOW_CROP_FRAME = "com.yalantis.ucrop.ShowCropFrame";
        public static final String EXTRA_SHOW_CROP_GRID = "com.yalantis.ucrop.ShowCropGrid";
        public static final String EXTRA_STATUS_BAR_COLOR = "com.yalantis.ucrop.StatusBarColor";
        public static final String EXTRA_TOOL_BAR_COLOR = "com.yalantis.ucrop.ToolbarColor";
        public static final String EXTRA_UCROP_COLOR_CONTROLS_WIDGET_ACTIVE = "com.yalantis.ucrop.UcropColorControlsWidgetActive";
        public static final String EXTRA_UCROP_COLOR_WIDGET_ACTIVE = "com.yalantis.ucrop.UcropColorWidgetActive";
        public static final String EXTRA_UCROP_LOGO_COLOR = "com.yalantis.ucrop.UcropLogoColor";
        public static final String EXTRA_UCROP_ROOT_VIEW_BACKGROUND_COLOR = "com.yalantis.ucrop.UcropRootViewBackgroundColor";
        public static final String EXTRA_UCROP_TITLE_TEXT_TOOLBAR = "com.yalantis.ucrop.UcropToolbarTitleText";
        public static final String EXTRA_UCROP_WIDGET_CANCEL_DRAWABLE = "com.yalantis.ucrop.UcropToolbarCancelDrawable";
        public static final String EXTRA_UCROP_WIDGET_COLOR_TOOLBAR = "com.yalantis.ucrop.UcropToolbarWidgetColor";
        public static final String EXTRA_UCROP_WIDGET_CROP_DRAWABLE = "com.yalantis.ucrop.UcropToolbarCropDrawable";
        private final Bundle mOptionBundle = new Bundle();

        @NonNull
        public Bundle getOptionBundle() {
            return this.mOptionBundle;
        }

        public void setCompressionFormat(@NonNull Bitmap.CompressFormat compressFormat) {
            this.mOptionBundle.putString(EXTRA_COMPRESSION_FORMAT_NAME, compressFormat.name());
        }

        public void setCompressionQuality(@IntRange(from = 0) int i) {
            this.mOptionBundle.putInt(EXTRA_COMPRESSION_QUALITY, i);
        }

        public void setAllowedGestures(int i, int i2, int i3) {
            this.mOptionBundle.putIntArray(EXTRA_ALLOWED_GESTURES, new int[]{i, i2, i3});
        }

        public void setMaxScaleMultiplier(@FloatRange(from = 1.0d, fromInclusive = false) float f) {
            this.mOptionBundle.putFloat(EXTRA_MAX_SCALE_MULTIPLIER, f);
        }

        public void setImageToCropBoundsAnimDuration(@IntRange(from = 10) int i) {
            this.mOptionBundle.putInt(EXTRA_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, i);
        }

        public void setMaxBitmapSize(@IntRange(from = 10) int i) {
            this.mOptionBundle.putInt(EXTRA_MAX_BITMAP_SIZE, i);
        }

        public void setDimmedLayerColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_DIMMED_LAYER_COLOR, i);
        }

        public void setCircleDimmedLayer(boolean z) {
            this.mOptionBundle.putBoolean(EXTRA_CIRCLE_DIMMED_LAYER, z);
        }

        public void setShowCropFrame(boolean z) {
            this.mOptionBundle.putBoolean(EXTRA_SHOW_CROP_FRAME, z);
        }

        public void setCropFrameColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_CROP_FRAME_COLOR, i);
        }

        public void setCropFrameStrokeWidth(@IntRange(from = 0) int i) {
            this.mOptionBundle.putInt(EXTRA_CROP_FRAME_STROKE_WIDTH, i);
        }

        public void setShowCropGrid(boolean z) {
            this.mOptionBundle.putBoolean(EXTRA_SHOW_CROP_GRID, z);
        }

        public void setCropGridRowCount(@IntRange(from = 0) int i) {
            this.mOptionBundle.putInt(EXTRA_CROP_GRID_ROW_COUNT, i);
        }

        public void setCropGridColumnCount(@IntRange(from = 0) int i) {
            this.mOptionBundle.putInt(EXTRA_CROP_GRID_COLUMN_COUNT, i);
        }

        public void setCropGridColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_CROP_GRID_COLOR, i);
        }

        public void setCropGridStrokeWidth(@IntRange(from = 0) int i) {
            this.mOptionBundle.putInt(EXTRA_CROP_GRID_STROKE_WIDTH, i);
        }

        public void setToolbarColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_TOOL_BAR_COLOR, i);
        }

        public void setStatusBarColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_STATUS_BAR_COLOR, i);
        }

        public void setActiveWidgetColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_UCROP_COLOR_WIDGET_ACTIVE, i);
        }

        public void setActiveControlsWidgetColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_UCROP_COLOR_CONTROLS_WIDGET_ACTIVE, i);
        }

        public void setToolbarWidgetColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_UCROP_WIDGET_COLOR_TOOLBAR, i);
        }

        public void setToolbarTitle(@Nullable String str) {
            this.mOptionBundle.putString(EXTRA_UCROP_TITLE_TEXT_TOOLBAR, str);
        }

        public void setToolbarCancelDrawable(@DrawableRes int i) {
            this.mOptionBundle.putInt(EXTRA_UCROP_WIDGET_CANCEL_DRAWABLE, i);
        }

        public void setToolbarCropDrawable(@DrawableRes int i) {
            this.mOptionBundle.putInt(EXTRA_UCROP_WIDGET_CROP_DRAWABLE, i);
        }

        public void setLogoColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_UCROP_LOGO_COLOR, i);
        }

        public void setHideBottomControls(boolean z) {
            this.mOptionBundle.putBoolean(EXTRA_HIDE_BOTTOM_CONTROLS, z);
        }

        public void setFreeStyleCropEnabled(boolean z) {
            this.mOptionBundle.putBoolean(EXTRA_FREE_STYLE_CROP, z);
        }

        public void setAspectRatioOptions(int i, AspectRatio... aspectRatioArr) {
            if (i > aspectRatioArr.length) {
                throw new IllegalArgumentException(String.format(Locale.US, "Index [selectedByDefault = %d] cannot be higher than aspect ratio options count [count = %d].", Integer.valueOf(i), Integer.valueOf(aspectRatioArr.length)));
            }
            this.mOptionBundle.putInt(EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, i);
            this.mOptionBundle.putParcelableArrayList(EXTRA_ASPECT_RATIO_OPTIONS, new ArrayList<>(Arrays.asList(aspectRatioArr)));
        }

        public void setRootViewBackgroundColor(@ColorInt int i) {
            this.mOptionBundle.putInt(EXTRA_UCROP_ROOT_VIEW_BACKGROUND_COLOR, i);
        }

        public void withAspectRatio(float f, float f2) {
            this.mOptionBundle.putFloat(UCrop.EXTRA_ASPECT_RATIO_X, f);
            this.mOptionBundle.putFloat(UCrop.EXTRA_ASPECT_RATIO_Y, f2);
        }

        public void useSourceImageAspectRatio() {
            this.mOptionBundle.putFloat(UCrop.EXTRA_ASPECT_RATIO_X, 0.0f);
            this.mOptionBundle.putFloat(UCrop.EXTRA_ASPECT_RATIO_Y, 0.0f);
        }

        public void withMaxResultSize(@IntRange(from = 10) int i, @IntRange(from = 10) int i2) {
            this.mOptionBundle.putInt(UCrop.EXTRA_MAX_SIZE_X, i);
            this.mOptionBundle.putInt(UCrop.EXTRA_MAX_SIZE_Y, i2);
        }
    }
}
