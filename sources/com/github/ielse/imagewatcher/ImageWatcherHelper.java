package com.github.ielse.imagewatcher;

import android.app.Activity;
import android.net.Uri;
import android.support.p083v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.github.ielse.imagewatcher.ImageWatcher;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ImageWatcherHelper {
    private static final int VIEW_DECORATION_ID = C4376R.C4379id.view_decoration;
    private static final int VIEW_IMAGE_WATCHER_ID = C4376R.C4379id.view_image_watcher;
    private final ViewGroup activityDecorView;
    private final Activity holder;
    private ImageWatcher.IndexProvider indexProvider;
    private ImageWatcher.OnPictureLongPressListener listener;
    private ImageWatcher.Loader loader;
    private ImageWatcher.LoadingUIProvider loadingUIProvider;
    private ImageWatcher mImageWatcher;
    private final List<ViewPager.OnPageChangeListener> onPageChangeListeners = new ArrayList();
    private final List<ImageWatcher.OnStateChangedListener> onStateChangedListeners = new ArrayList();
    private View otherView;
    private Integer resErrorImage;
    private Integer statusBarHeight;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Provider {
        ImageWatcherHelper iwHelper();
    }

    private ImageWatcherHelper(Activity activity) {
        this.holder = activity;
        this.activityDecorView = (ViewGroup) activity.getWindow().getDecorView();
    }

    public static ImageWatcherHelper with(Activity activity, ImageWatcher.Loader loader) {
        if (activity != null) {
            if (loader == null) {
                throw new NullPointerException("loader is null");
            }
            ImageWatcherHelper imageWatcherHelper = new ImageWatcherHelper(activity);
            imageWatcherHelper.loader = loader;
            return imageWatcherHelper;
        }
        throw new NullPointerException("activity is null");
    }

    public ImageWatcherHelper setTranslucentStatus(int i) {
        this.statusBarHeight = Integer.valueOf(i);
        return this;
    }

    public ImageWatcherHelper setErrorImageRes(int i) {
        this.resErrorImage = Integer.valueOf(i);
        return this;
    }

    public ImageWatcherHelper setOnPictureLongPressListener(ImageWatcher.OnPictureLongPressListener onPictureLongPressListener) {
        this.listener = onPictureLongPressListener;
        return this;
    }

    public ImageWatcherHelper addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (!this.onPageChangeListeners.contains(onPageChangeListener)) {
            this.onPageChangeListeners.add(onPageChangeListener);
        }
        return this;
    }

    public ImageWatcherHelper setOtherView(View view) {
        this.otherView = view;
        return this;
    }

    public ImageWatcherHelper setIndexProvider(ImageWatcher.IndexProvider indexProvider) {
        this.indexProvider = indexProvider;
        return this;
    }

    public ImageWatcherHelper setLoadingUIProvider(ImageWatcher.LoadingUIProvider loadingUIProvider) {
        this.loadingUIProvider = loadingUIProvider;
        return this;
    }

    public ImageWatcherHelper setOnStateChangedListener(ImageWatcher.OnStateChangedListener onStateChangedListener) {
        if (!this.onStateChangedListeners.contains(onStateChangedListener)) {
            this.onStateChangedListeners.add(onStateChangedListener);
        }
        return this;
    }

    public void show(ImageView imageView, SparseArray<ImageView> sparseArray, List<Uri> list) {
        init();
        if (this.mImageWatcher.show(imageView, sparseArray, list)) {
            appendOtherView();
        }
    }

    public void show(List<Uri> list, int i) {
        init();
        this.mImageWatcher.show(list, i);
        appendOtherView();
    }

    private void init() {
        this.mImageWatcher = new ImageWatcher(this.holder);
        this.mImageWatcher.setId(VIEW_IMAGE_WATCHER_ID);
        this.mImageWatcher.setLoader(this.loader);
        this.mImageWatcher.setDetachAffirmative();
        Integer num = this.statusBarHeight;
        if (num != null) {
            this.mImageWatcher.setTranslucentStatus(num.intValue());
        }
        Integer num2 = this.resErrorImage;
        if (num2 != null) {
            this.mImageWatcher.setErrorImageRes(num2.intValue());
        }
        ImageWatcher.OnPictureLongPressListener onPictureLongPressListener = this.listener;
        if (onPictureLongPressListener != null) {
            this.mImageWatcher.setOnPictureLongPressListener(onPictureLongPressListener);
        }
        ImageWatcher.IndexProvider indexProvider = this.indexProvider;
        if (indexProvider != null) {
            this.mImageWatcher.setIndexProvider(indexProvider);
        }
        ImageWatcher.LoadingUIProvider loadingUIProvider = this.loadingUIProvider;
        if (loadingUIProvider != null) {
            this.mImageWatcher.setLoadingUIProvider(loadingUIProvider);
        }
        if (!this.onStateChangedListeners.isEmpty()) {
            for (ImageWatcher.OnStateChangedListener onStateChangedListener : this.onStateChangedListeners) {
                this.mImageWatcher.addOnStateChangedListener(onStateChangedListener);
            }
        }
        if (!this.onPageChangeListeners.isEmpty()) {
            for (ViewPager.OnPageChangeListener onPageChangeListener : this.onPageChangeListeners) {
                this.mImageWatcher.addOnPageChangeListener(onPageChangeListener);
            }
        }
        removeExistingOverlayInView(this.activityDecorView, this.mImageWatcher.getId());
        this.activityDecorView.addView(this.mImageWatcher);
    }

    public ImageWatcher getImageWatcher() {
        if (this.mImageWatcher == null) {
            Log.i("ImageWatcherHelper", "please invoke 'show' first");
        }
        return this.mImageWatcher;
    }

    public boolean handleBackPressed() {
        ImageWatcher imageWatcher = this.mImageWatcher;
        return imageWatcher != null && imageWatcher.handleBackPressed();
    }

    private void removeExistingOverlayInView(ViewGroup viewGroup, int i) {
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getId() == i) {
                viewGroup.removeView(childAt);
            }
            if (childAt instanceof ViewGroup) {
                removeExistingOverlayInView((ViewGroup) childAt, i);
            }
        }
    }

    private void appendOtherView() {
        View view = this.otherView;
        if (view != null) {
            if (view.getId() == -1) {
                this.otherView.setId(VIEW_DECORATION_ID);
            }
            removeExistingOverlayInView(this.activityDecorView, this.otherView.getId());
            this.activityDecorView.addView(this.otherView);
            this.mImageWatcher.addOnStateChangedListener(new ImageWatcher.OnStateChangedListener() { // from class: com.github.ielse.imagewatcher.ImageWatcherHelper.1
                @Override // com.github.ielse.imagewatcher.ImageWatcher.OnStateChangedListener
                public void onStateChangeUpdate(ImageWatcher imageWatcher, ImageView imageView, int i, Uri uri, float f, int i2) {
                }

                @Override // com.github.ielse.imagewatcher.ImageWatcher.OnStateChangedListener
                public void onStateChanged(ImageWatcher imageWatcher, int i, Uri uri, int i2) {
                    if (i2 != 4 || ImageWatcherHelper.this.otherView == null || ImageWatcherHelper.this.otherView.getParent() == null) {
                        return;
                    }
                    ((ViewGroup) ImageWatcherHelper.this.otherView.getParent()).removeView(ImageWatcherHelper.this.otherView);
                }
            });
        }
    }
}
