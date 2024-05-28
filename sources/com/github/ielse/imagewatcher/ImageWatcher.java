package com.github.ielse.imagewatcher;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ImageWatcher extends FrameLayout implements ViewPager.OnPageChangeListener, GestureDetector.OnGestureListener {
    private static final int DATA_INITIAL = 2;
    private static final int SINGLE_TAP_UP_CONFIRMED = 1;
    public static final int STATE_ENTER_DISPLAYING = 3;
    public static final int STATE_EXIT_HIDING = 4;
    protected static final int TOUCH_MODE_AUTO_FLING = 7;
    protected static final int TOUCH_MODE_DOWN = 1;
    protected static final int TOUCH_MODE_DRAG = 2;
    protected static final int TOUCH_MODE_EXIT = 3;
    protected static final int TOUCH_MODE_NONE = 0;
    protected static final int TOUCH_MODE_SCALE = 5;
    protected static final int TOUCH_MODE_SCALE_LOCK = 6;
    protected static final int TOUCH_MODE_SLIDE = 4;
    protected final float MAX_SCALE;
    protected final float MIN_SCALE;
    private final AccelerateInterpolator accelerateInterpolator;
    private ImagePagerAdapter adapter;
    private ValueAnimator animBackground;
    private ValueAnimator animFling;
    private ValueAnimator animImageTransform;
    private int currentPosition;
    private final DecelerateInterpolator decelerateInterpolator;
    private boolean detachAffirmative;
    private boolean detachedParent;
    protected float edgeResilience;
    private ImageView iSource;
    private View idxView;
    private IndexProvider indexProvider;
    protected ImageView initI;
    protected SparseArray<ImageView> initImageGroupList;
    protected int initPosition;
    protected List<Uri> initUrlList;
    private boolean isInTransformAnimation;
    private boolean isInitLayout;
    private Loader loader;
    private LoadingUIProvider loadingUIProvider;
    private final AnimatorListenerAdapter mAnimTransitionStateListener;
    private int mBackgroundColor;
    private final TypeEvaluator<Integer> mColorEvaluator;
    protected int mErrorImageRes;
    private float mExitRef;
    private float mFingersCenterX;
    private float mFingersCenterY;
    private float mFingersDistance;
    private final GestureDetector mGestureDetector;
    private final Handler mHandler;
    private int mHeight;
    protected SparseArray<ImageView> mImageGroupList;
    private int mPagerPositionOffsetPixels;
    protected int mStatusBarHeight;
    private int mTouchMode;
    private final float mTouchSlop;
    protected List<Uri> mUrlList;
    private int mWidth;
    private final List<ViewPager.OnPageChangeListener> onPageChangeListeners;
    private final List<OnStateChangedListener> onStateChangedListeners;
    private OnPictureLongPressListener pictureLongPressListener;
    protected float scaleSensitivity;
    private final ViewPager vPager;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface IndexProvider {
        View initialView(Context context);

        void onPageChanged(ImageWatcher imageWatcher, int i, List<Uri> list);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface LoadCallback {
        void onLoadFailed(Drawable drawable);

        void onLoadStarted(Drawable drawable);

        void onResourceReady(Drawable drawable);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Loader {
        void load(Context context, Uri uri, LoadCallback loadCallback);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface LoadingUIProvider {
        View initialView(Context context);

        void start(View view);

        void stop(View view);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnPictureLongPressListener {
        void onPictureLongPress(ImageView imageView, Uri uri, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnStateChangedListener {
        void onStateChangeUpdate(ImageWatcher imageWatcher, ImageView imageView, int i, Uri uri, float f, int i2);

        void onStateChanged(ImageWatcher imageWatcher, int i, Uri uri, int i2);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    public ImageWatcher(Context context) {
        this(context, null);
    }

    public ImageWatcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.MIN_SCALE = 0.5f;
        this.MAX_SCALE = 3.6f;
        this.scaleSensitivity = 0.3f;
        this.edgeResilience = 0.16f;
        this.mErrorImageRes = C4376R.mipmap.error_picture;
        this.mBackgroundColor = 0;
        this.mTouchMode = 0;
        this.isInitLayout = false;
        this.onStateChangedListeners = new ArrayList();
        this.onPageChangeListeners = new ArrayList();
        this.mAnimTransitionStateListener = new AnimatorListenerAdapter() { // from class: com.github.ielse.imagewatcher.ImageWatcher.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                ImageWatcher.this.isInTransformAnimation = false;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ImageWatcher.this.isInTransformAnimation = true;
                ImageWatcher.this.mTouchMode = 7;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ImageWatcher.this.isInTransformAnimation = false;
            }
        };
        this.mColorEvaluator = new TypeEvaluator<Integer>() { // from class: com.github.ielse.imagewatcher.ImageWatcher.2
            @Override // android.animation.TypeEvaluator
            public Integer evaluate(float f, Integer num, Integer num2) {
                float interpolation = ImageWatcher.this.accelerateInterpolator.getInterpolation(f);
                int intValue = num.intValue();
                int intValue2 = num2.intValue();
                return Integer.valueOf(Color.argb((int) (Color.alpha(intValue) + ((Color.alpha(intValue2) - Color.alpha(intValue)) * interpolation)), (int) (Color.red(intValue) + ((Color.red(intValue2) - Color.red(intValue)) * interpolation)), (int) (Color.green(intValue) + ((Color.green(intValue2) - Color.green(intValue)) * interpolation)), (int) (Color.blue(intValue) + (interpolation * (Color.blue(intValue2) - Color.blue(intValue))))));
            }
        };
        this.decelerateInterpolator = new DecelerateInterpolator();
        this.accelerateInterpolator = new AccelerateInterpolator();
        this.mHandler = new RefHandler(this);
        this.mGestureDetector = new GestureDetector(context, this);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        ViewPager viewPager = new ViewPager(context);
        this.vPager = viewPager;
        addView(viewPager);
        this.vPager.addOnPageChangeListener(this);
        setVisibility(4);
        setIndexProvider(new DefaultIndexProvider());
        setLoadingUIProvider(new DefaultLoadingUIProvider());
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public void setDetachAffirmative() {
        this.detachAffirmative = true;
    }

    public void setIndexProvider(IndexProvider indexProvider) {
        this.indexProvider = indexProvider;
        if (this.indexProvider != null) {
            View view = this.idxView;
            if (view != null) {
                removeView(view);
            }
            this.idxView = this.indexProvider.initialView(getContext());
            addView(this.idxView);
        }
    }

    public void setLoadingUIProvider(LoadingUIProvider loadingUIProvider) {
        this.loadingUIProvider = loadingUIProvider;
    }

    public void addOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        if (this.onStateChangedListeners.contains(onStateChangedListener)) {
            return;
        }
        this.onStateChangedListeners.add(onStateChangedListener);
    }

    public void setOnPictureLongPressListener(OnPictureLongPressListener onPictureLongPressListener) {
        this.pictureLongPressListener = onPictureLongPressListener;
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (this.onPageChangeListeners.contains(onPageChangeListener)) {
            return;
        }
        this.onPageChangeListeners.add(onPageChangeListener);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class DefaultIndexProvider implements IndexProvider {
        TextView tCurrentIdx;

        public DefaultIndexProvider() {
        }

        @Override // com.github.ielse.imagewatcher.ImageWatcher.IndexProvider
        public View initialView(Context context) {
            this.tCurrentIdx = new TextView(context);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 49;
            this.tCurrentIdx.setLayoutParams(layoutParams);
            this.tCurrentIdx.setTextColor(-1);
            this.tCurrentIdx.setTranslationY(TypedValue.applyDimension(1, 30.0f, context.getResources().getDisplayMetrics()) + 0.5f);
            return this.tCurrentIdx;
        }

        @Override // com.github.ielse.imagewatcher.ImageWatcher.IndexProvider
        public void onPageChanged(ImageWatcher imageWatcher, int i, List<Uri> list) {
            if (ImageWatcher.this.mUrlList.size() > 1) {
                this.tCurrentIdx.setVisibility(0);
                this.tCurrentIdx.setText((i + 1) + " / " + ImageWatcher.this.mUrlList.size());
                return;
            }
            this.tCurrentIdx.setVisibility(8);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class DefaultLoadingUIProvider implements LoadingUIProvider {
        final FrameLayout.LayoutParams lpCenterInParent = new FrameLayout.LayoutParams(-2, -2);
        private Runnable runDelayDisplay;

        public DefaultLoadingUIProvider() {
        }

        @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadingUIProvider
        public View initialView(Context context) {
            this.lpCenterInParent.gravity = 17;
            ProgressView progressView = new ProgressView(context);
            progressView.setLayoutParams(this.lpCenterInParent);
            return progressView;
        }

        @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadingUIProvider
        public void start(final View view) {
            if (this.runDelayDisplay != null) {
                ImageWatcher.this.mHandler.removeCallbacks(this.runDelayDisplay);
            }
            this.runDelayDisplay = new Runnable() { // from class: com.github.ielse.imagewatcher.ImageWatcher.DefaultLoadingUIProvider.1
                @Override // java.lang.Runnable
                public void run() {
                    view.setVisibility(0);
                    if (((ProgressView) view).isRunning()) {
                        return;
                    }
                    ((ProgressView) view).start();
                }
            };
            ImageWatcher.this.mHandler.postDelayed(this.runDelayDisplay, 500L);
        }

        @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadingUIProvider
        public void stop(View view) {
            if (this.runDelayDisplay != null) {
                ImageWatcher.this.mHandler.removeCallbacks(this.runDelayDisplay);
            }
            this.runDelayDisplay = null;
            ProgressView progressView = (ProgressView) view;
            if (progressView.isRunning()) {
                progressView.stop();
            }
            view.setVisibility(8);
        }
    }

    public void show(List<Uri> list, int i) {
        if (list == null) {
            throw new NullPointerException("urlList[null]");
        }
        if (i >= list.size() || i < 0) {
            throw new IndexOutOfBoundsException("initPos[" + i + "]  urlList.size[" + list.size() + "]");
        }
        this.initPosition = i;
        showInternal(null, null, list);
    }

    public boolean show(ImageView imageView, SparseArray<ImageView> sparseArray, List<Uri> list) {
        if (imageView == null || sparseArray == null || list == null) {
            throw new NullPointerException("i[" + imageView + "]  imageGroupList[" + sparseArray + "]  urlList[" + list + "]");
        }
        this.initPosition = -1;
        int i = 0;
        while (true) {
            if (i >= sparseArray.size()) {
                break;
            } else if (sparseArray.get(sparseArray.keyAt(i)) == imageView) {
                this.initPosition = sparseArray.keyAt(i);
                break;
            } else {
                i++;
            }
        }
        if (this.initPosition < 0) {
            throw new IllegalArgumentException("param ImageView i must be a member of the List <ImageView> imageGroupList!");
        }
        if (imageView.getDrawable() == null) {
            return false;
        }
        showInternal(imageView, sparseArray, list);
        return true;
    }

    private void showInternal(ImageView imageView, SparseArray<ImageView> sparseArray, List<Uri> list) {
        if (this.loader == null) {
            throw new NullPointerException("please invoke `setLoader` first [loader == null]");
        }
        if (!this.isInitLayout) {
            this.initI = imageView;
            this.initImageGroupList = sparseArray;
            this.initUrlList = list;
            return;
        }
        this.currentPosition = this.initPosition;
        ValueAnimator valueAnimator = this.animImageTransform;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.animImageTransform = null;
        this.mImageGroupList = sparseArray;
        this.mUrlList = list;
        this.iSource = null;
        setVisibility(0);
        ViewPager viewPager = this.vPager;
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter();
        this.adapter = imagePagerAdapter;
        viewPager.setAdapter(imagePagerAdapter);
        this.vPager.setCurrentItem(this.initPosition);
        IndexProvider indexProvider = this.indexProvider;
        if (indexProvider != null) {
            indexProvider.onPageChanged(this, this.initPosition, this.mUrlList);
        }
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public Uri getDisplayingUri() {
        return getUri(getCurrentPosition());
    }

    public Uri getUri(int i) {
        List<Uri> list = this.mUrlList;
        if (list == null || list.size() <= i || i < 0) {
            return null;
        }
        return this.mUrlList.get(i);
    }

    public void notifyItemChanged(int i, Uri uri) {
        List<Uri> list = this.mUrlList;
        if (list == null || list.size() <= i || i < 0) {
            return;
        }
        this.mUrlList.set(i, uri);
        this.adapter.notifyItemChanged(i);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mPagerPositionOffsetPixels == 0;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.iSource == null || this.isInTransformAnimation) {
            return true;
        }
        ValueAnimator valueAnimator = this.animFling;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.animFling = null;
            this.mTouchMode = 1;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 1) {
            onUp(motionEvent);
        } else {
            switch (action) {
                case 5:
                    if (this.mPagerPositionOffsetPixels == 0) {
                        if (this.mTouchMode != 5) {
                            this.mFingersDistance = 0.0f;
                            this.mFingersCenterX = 0.0f;
                            this.mFingersCenterY = 0.0f;
                            ViewState.write(this.iSource, ViewState.STATE_TOUCH_SCALE);
                        }
                        this.mTouchMode = 5;
                        break;
                    } else {
                        dispatchEventToViewPager(motionEvent);
                        break;
                    }
                case 6:
                    if (this.mPagerPositionOffsetPixels == 0) {
                        if (motionEvent.getPointerCount() - 1 < 2) {
                            this.mTouchMode = 6;
                            onUp(motionEvent);
                            break;
                        }
                    } else {
                        dispatchEventToViewPager(motionEvent);
                        break;
                    }
                    break;
            }
        }
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        this.mTouchMode = 1;
        dispatchEventToViewPager(motionEvent);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUp(MotionEvent motionEvent) {
        int i = this.mTouchMode;
        if (i == 5 || i == 6) {
            handleScaleTouchResult();
        } else if (i == 3) {
            handleExitTouchResult();
        } else if (i == 2) {
            handleDragTouchResult();
        } else if (i == 4) {
            dispatchEventToViewPager(motionEvent);
        }
    }

    private void dispatchEventToViewPager(MotionEvent motionEvent) {
        dispatchEventToViewPager(motionEvent, null);
    }

    private void dispatchEventToViewPager(MotionEvent motionEvent, MotionEvent motionEvent2) {
        float x;
        if (motionEvent != null) {
            if (motionEvent2 != null) {
                try {
                    x = motionEvent.getX() - motionEvent2.getX();
                } catch (Exception unused) {
                    return;
                }
            } else {
                x = 0.0f;
            }
            if (Math.abs(motionEvent2 != null ? motionEvent.getY() - motionEvent2.getY() : 0.0f) > this.mTouchSlop * 3.0f && Math.abs(x) < this.mTouchSlop && this.mPagerPositionOffsetPixels == 0) {
                ViewState.write(this.iSource, ViewState.STATE_EXIT);
                this.mTouchMode = 3;
            }
        }
        this.vPager.onTouchEvent(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (this.mTouchMode == 1) {
            float x = motionEvent != null ? motionEvent2.getX() - motionEvent.getX() : 0.0f;
            float y = motionEvent != null ? motionEvent2.getY() - motionEvent.getY() : 0.0f;
            if (Math.abs(x) > this.mTouchSlop || Math.abs(y) > this.mTouchSlop) {
                ViewState write = ViewState.write(this.iSource, ViewState.STATE_CURRENT);
                ViewState read = ViewState.read(this.iSource, ViewState.STATE_DEFAULT);
                String str = (String) this.iSource.getTag(C4376R.C4379id.image_orientation);
                if (read == null) {
                    this.mTouchMode = 4;
                } else if (Math.abs(x) < this.mTouchSlop && y > Math.abs(x) * 3.0f && ((read.height * write.scaleY) / 2.0f) - (read.height / 2) <= this.iSource.getTranslationY()) {
                    if (this.mTouchMode != 3) {
                        ViewState.write(this.iSource, ViewState.STATE_EXIT);
                    }
                    this.mTouchMode = 3;
                } else if (write.scaleY > read.scaleY || write.scaleX > read.scaleX || write.scaleY * this.iSource.getHeight() > this.mHeight) {
                    if (this.mTouchMode != 2) {
                        ViewState.write(this.iSource, ViewState.STATE_DRAG);
                    }
                    this.mTouchMode = 2;
                    if ("horizontal".equals(str)) {
                        float f3 = (read.width * (write.scaleX - 1.0f)) / 2.0f;
                        if (write.translationX >= f3 && x > 0.0f) {
                            this.mTouchMode = 4;
                        } else if (write.translationX <= (-f3) && x < 0.0f) {
                            this.mTouchMode = 4;
                        }
                    } else if ("vertical".equals(str)) {
                        if (read.width * write.scaleX <= this.mWidth) {
                            if (Math.abs(y) < this.mTouchSlop && Math.abs(x) > this.mTouchSlop && Math.abs(x) > Math.abs(y) * 2.0f) {
                                this.mTouchMode = 4;
                            }
                        } else {
                            float f4 = ((read.width * write.scaleX) / 2.0f) - (read.width / 2);
                            float f5 = (this.mWidth - ((read.width * write.scaleX) / 2.0f)) - (read.width / 2);
                            if (write.translationX >= f4 && x > 0.0f) {
                                this.mTouchMode = 4;
                            } else if (write.translationX <= f5 && x < 0.0f) {
                                this.mTouchMode = 4;
                            }
                        }
                    }
                } else if (Math.abs(x) > this.mTouchSlop) {
                    this.mTouchMode = 4;
                }
            }
        }
        int i = this.mTouchMode;
        if (i == 4) {
            dispatchEventToViewPager(motionEvent2, motionEvent);
            return false;
        } else if (i == 5) {
            handleScaleGesture(motionEvent2);
            return false;
        } else if (i == 3) {
            handleExitGesture(motionEvent2, motionEvent);
            return false;
        } else if (i == 2) {
            handleDragGesture(motionEvent2, motionEvent);
            return false;
        } else {
            return false;
        }
    }

    public boolean onSingleTapConfirmed() {
        ImageView imageView = this.iSource;
        if (imageView == null) {
            return false;
        }
        ViewState write = ViewState.write(imageView, ViewState.STATE_CURRENT);
        ViewState read = ViewState.read(this.iSource, ViewState.STATE_DEFAULT);
        if (read == null || (write.scaleY <= read.scaleY && write.scaleX <= read.scaleX)) {
            this.mExitRef = 0.0f;
        } else {
            this.iSource.setTag(ViewState.STATE_EXIT, read);
            this.mExitRef = 1.0f;
        }
        handleExitTouchResult();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.mHandler.hasMessages(1)) {
            this.mHandler.removeMessages(1);
            handleDoubleTapTouchResult();
            return true;
        }
        this.mHandler.sendEmptyMessageDelayed(1, 350L);
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        OnPictureLongPressListener onPictureLongPressListener = this.pictureLongPressListener;
        if (onPictureLongPressListener != null) {
            onPictureLongPressListener.onPictureLongPress(this.iSource, this.mUrlList.get(this.vPager.getCurrentItem()), this.vPager.getCurrentItem());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0148, code lost:
        if (r3 < r13) goto L51;
     */
    @Override // android.view.GestureDetector.OnGestureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onFling(android.view.MotionEvent r11, android.view.MotionEvent r12, float r13, float r14) {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.ielse.imagewatcher.ImageWatcher.onFling(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
    }

    private void handleExitGesture(MotionEvent motionEvent, MotionEvent motionEvent2) {
        ImageView imageView = this.iSource;
        if (imageView == null) {
            return;
        }
        ViewState read = ViewState.read(imageView, ViewState.STATE_EXIT);
        ViewState read2 = ViewState.read(this.iSource, ViewState.STATE_DEFAULT);
        if (read == null || read2 == null) {
            return;
        }
        this.mExitRef = 1.0f;
        float y = motionEvent.getY() - motionEvent2.getY();
        float x = motionEvent.getX() - motionEvent2.getX();
        if (y > 0.0f) {
            this.mExitRef -= y / (this.mHeight / 2);
        }
        if (this.mExitRef < 0.0f) {
            this.mExitRef = 0.0f;
        }
        setBackgroundColor(this.mColorEvaluator.evaluate(this.mExitRef, 0, -16777216).intValue());
        float f = ((read.scaleX - 0.5f) * this.mExitRef) + 0.5f;
        this.iSource.setScaleX(f);
        this.iSource.setScaleY(f);
        this.iSource.setTranslationX(read2.translationX + ((read.translationX - read2.translationX) * this.mExitRef) + x);
        this.iSource.setTranslationY(read.translationY + y);
    }

    private void handleDragGesture(MotionEvent motionEvent, MotionEvent motionEvent2) {
        ImageView imageView = this.iSource;
        if (imageView == null) {
            return;
        }
        ViewState read = ViewState.read(imageView, ViewState.STATE_DEFAULT);
        ViewState read2 = ViewState.read(this.iSource, ViewState.STATE_DRAG);
        if (read == null || read2 == null) {
            return;
        }
        float x = read2.translationX + (motionEvent.getX() - motionEvent2.getX());
        float y = read2.translationY + (motionEvent.getY() - motionEvent2.getY());
        String str = (String) this.iSource.getTag(C4376R.C4379id.image_orientation);
        if ("horizontal".equals(str)) {
            float f = (read.width * (read2.scaleX - 1.0f)) / 2.0f;
            if (x > f) {
                x = ((x - f) * this.edgeResilience) + f;
            } else {
                float f2 = -f;
                if (x < f2) {
                    x = ((x - f2) * this.edgeResilience) + f2;
                }
            }
            this.iSource.setTranslationX(x);
        } else if ("vertical".equals(str)) {
            if (read.width * read2.scaleX <= this.mWidth) {
                x = read2.translationX;
            } else {
                float f3 = ((read.width * read2.scaleX) / 2.0f) - (read.width / 2);
                float f4 = (this.mWidth - ((read.width * read2.scaleX) / 2.0f)) - (read.width / 2);
                if (x > f3) {
                    x = ((x - f3) * this.edgeResilience) + f3;
                } else if (x < f4) {
                    x = ((x - f4) * this.edgeResilience) + f4;
                }
            }
            this.iSource.setTranslationX(x);
        }
        if (read.height * read2.scaleY > this.mHeight) {
            float f5 = ((read.height * read2.scaleY) / 2.0f) - (read.height / 2);
            float f6 = (this.mHeight - ((read.height * read2.scaleY) / 2.0f)) - (read.height / 2);
            if (y > f5) {
                y = ((y - f5) * this.edgeResilience) + f5;
            } else if (y < f6) {
                y = ((y - f6) * this.edgeResilience) + f6;
            }
            this.iSource.setTranslationY(y);
        }
    }

    private void handleScaleGesture(MotionEvent motionEvent) {
        ImageView imageView = this.iSource;
        if (imageView == null) {
            return;
        }
        ViewState read = ViewState.read(imageView, ViewState.STATE_DEFAULT);
        ViewState read2 = ViewState.read(this.iSource, ViewState.STATE_TOUCH_SCALE);
        if (read == null || read2 == null || motionEvent.getPointerCount() < 2) {
            return;
        }
        float x = motionEvent.getX(1) - motionEvent.getX(0);
        float y = motionEvent.getY(1) - motionEvent.getY(0);
        float sqrt = (float) Math.sqrt((x * x) + (y * y));
        if (this.mFingersDistance == 0.0f) {
            this.mFingersDistance = sqrt;
        }
        float f = (this.mFingersDistance - sqrt) / (this.mWidth * this.scaleSensitivity);
        float f2 = read2.scaleX - f;
        if (f2 < 0.5f) {
            f2 = 0.5f;
        } else if (f2 > 3.6f) {
            f2 = 3.6f;
        }
        this.iSource.setScaleX(f2);
        float f3 = read2.scaleY - f;
        if (f3 < 0.5f) {
            f3 = 0.5f;
        } else if (f3 > 3.6f) {
            f3 = 3.6f;
        }
        this.iSource.setScaleY(f3);
        float x2 = (motionEvent.getX(1) + motionEvent.getX(0)) / 2.0f;
        float y2 = (motionEvent.getY(1) + motionEvent.getY(0)) / 2.0f;
        if (this.mFingersCenterX == 0.0f && this.mFingersCenterY == 0.0f) {
            this.mFingersCenterX = x2;
            this.mFingersCenterY = y2;
        }
        this.iSource.setTranslationX((read2.translationX - (this.mFingersCenterX - x2)) + 0.0f);
        this.iSource.setTranslationY(read2.translationY - (this.mFingersCenterY - y2));
    }

    private void handleDoubleTapTouchResult() {
        ViewState read;
        ImageView imageView = this.iSource;
        if (imageView == null || (read = ViewState.read(imageView, ViewState.STATE_DEFAULT)) == null) {
            return;
        }
        ViewState write = ViewState.write(this.iSource, ViewState.STATE_CURRENT);
        if (write.scaleY <= read.scaleY && write.scaleX <= read.scaleX) {
            float f = ((3.6f - read.scaleX) * 0.4f) + read.scaleX;
            if (((String) this.iSource.getTag(C4376R.C4379id.image_orientation)).equals("horizontal")) {
                ViewState read2 = ViewState.read(this.iSource, ViewState.STATE_DEFAULT);
                float f2 = read2.width / read2.height;
                f = (((f2 > 2.0f ? (f2 * 3.6f) / 2.0f : 3.6f) - read.scaleX) * 0.4f) + read.scaleX;
            }
            ImageView imageView2 = this.iSource;
            animSourceViewStateTransform(imageView2, ViewState.write(imageView2, ViewState.STATE_TEMP).scaleX(f).scaleY(f));
            return;
        }
        animSourceViewStateTransform(this.iSource, read);
    }

    private void handleScaleTouchResult() {
        ViewState read;
        ImageView imageView = this.iSource;
        if (imageView == null || (read = ViewState.read(imageView, ViewState.STATE_DEFAULT)) == null) {
            return;
        }
        ViewState write = ViewState.write(this.iSource, ViewState.STATE_CURRENT);
        ViewState scaleY = ViewState.copy(read, ViewState.STATE_TEMP).scaleX(write.scaleX < read.scaleX ? read.scaleX : write.scaleX).scaleY(write.scaleY < read.scaleY ? read.scaleY : write.scaleY);
        if (this.iSource.getWidth() * write.scaleX > this.mWidth) {
            float f = (write.width * (write.scaleX - 1.0f)) / 2.0f;
            if (write.translationX <= f) {
                f = -f;
                if (write.translationX >= f) {
                    f = write.translationX;
                }
            }
            scaleY.translationX(f);
        }
        if (this.iSource.getHeight() * write.scaleY > this.mHeight) {
            float f2 = ((read.height * write.scaleY) / 2.0f) - (read.height / 2);
            float f3 = (this.mHeight - ((read.height * write.scaleY) / 2.0f)) - (read.height / 2);
            if (write.translationY <= f2) {
                f2 = write.translationY < f3 ? f3 : write.translationY;
            }
            scaleY.translationY(f2);
        }
        this.iSource.setTag(ViewState.STATE_TEMP, scaleY);
        animSourceViewStateTransform(this.iSource, scaleY);
        animBackgroundTransform(-16777216, 0);
    }

    private void handleDragTouchResult() {
        ViewState read;
        float f;
        float f2;
        ImageView imageView = this.iSource;
        if (imageView == null || (read = ViewState.read(imageView, ViewState.STATE_DEFAULT)) == null) {
            return;
        }
        ViewState write = ViewState.write(this.iSource, ViewState.STATE_CURRENT);
        String str = (String) this.iSource.getTag(C4376R.C4379id.image_orientation);
        if ("horizontal".equals(str)) {
            f = (read.width * (write.scaleX - 1.0f)) / 2.0f;
            if (write.translationX <= f) {
                f = -f;
                if (write.translationX >= f) {
                    f = write.translationX;
                }
            }
            if (read.height * write.scaleY <= this.mHeight) {
                f2 = read.translationY;
            } else {
                float f3 = ((read.height * write.scaleY) / 2.0f) - (read.height / 2);
                f2 = (this.mHeight - ((read.height * write.scaleY) / 2.0f)) - (read.height / 2);
                if (write.translationY > f3) {
                    f2 = f3;
                } else if (write.translationY >= f2) {
                    f2 = write.translationY;
                }
            }
        } else if (!"vertical".equals(str)) {
            return;
        } else {
            if (read.width * write.scaleX <= this.mWidth) {
                f = read.translationX;
            } else {
                f = ((read.width * write.scaleX) / 2.0f) - (read.width / 2);
                float f4 = (this.mWidth - ((read.width * write.scaleX) / 2.0f)) - (read.width / 2);
                if (write.translationX <= f) {
                    f = write.translationX < f4 ? f4 : write.translationX;
                }
            }
            float f5 = ((read.height * write.scaleY) / 2.0f) - (read.height / 2);
            float f6 = (this.mHeight - ((read.height * write.scaleY) / 2.0f)) - (read.height / 2);
            if (write.translationY > f5) {
                f2 = f5;
            } else {
                f2 = write.translationY < f6 ? f6 : write.translationY;
            }
        }
        if (write.translationX == f && write.translationY == f2) {
            return;
        }
        ImageView imageView2 = this.iSource;
        animSourceViewStateTransform(imageView2, ViewState.write(imageView2, ViewState.STATE_TEMP).translationX(f).translationY(f2));
        animBackgroundTransform(-16777216, 0);
    }

    private void handleExitTouchResult() {
        ImageView imageView = this.iSource;
        if (imageView == null) {
            return;
        }
        if (this.mExitRef > 0.75f) {
            ViewState read = ViewState.read(imageView, ViewState.STATE_EXIT);
            if (read != null) {
                animSourceViewStateTransform(this.iSource, read);
            }
            animBackgroundTransform(-16777216, 0);
            return;
        }
        ViewState read2 = ViewState.read(imageView, ViewState.STATE_ORIGIN);
        if (read2 != null) {
            if (read2.alpha == 0.0f) {
                read2.translationX(this.iSource.getTranslationX()).translationY(this.iSource.getTranslationY());
            }
            animSourceViewStateTransform(this.iSource, read2);
        }
        animBackgroundTransform(0, 4);
        ((FrameLayout) this.iSource.getParent()).getChildAt(2).animate().alpha(0.0f).start();
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.mPagerPositionOffsetPixels = i2;
        if (this.onPageChangeListeners.isEmpty()) {
            return;
        }
        for (ViewPager.OnPageChangeListener onPageChangeListener : this.onPageChangeListeners) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        this.iSource = (ImageView) this.adapter.mImageSparseArray.get(i);
        this.currentPosition = i;
        IndexProvider indexProvider = this.indexProvider;
        if (indexProvider != null) {
            indexProvider.onPageChanged(this, i, this.mUrlList);
        }
        ImageView imageView = (ImageView) this.adapter.mImageSparseArray.get(i - 1);
        if (ViewState.read(imageView, ViewState.STATE_DEFAULT) != null) {
            ViewState.restoreByAnim(imageView, ViewState.STATE_DEFAULT).create().start();
        }
        ImageView imageView2 = (ImageView) this.adapter.mImageSparseArray.get(i + 1);
        if (ViewState.read(imageView2, ViewState.STATE_DEFAULT) != null) {
            ViewState.restoreByAnim(imageView2, ViewState.STATE_DEFAULT).create().start();
        }
        if (!this.onPageChangeListeners.isEmpty()) {
            for (ViewPager.OnPageChangeListener onPageChangeListener : this.onPageChangeListeners) {
                onPageChangeListener.onPageSelected(i);
            }
        }
        NBSActionInstrumentation.onPageSelectedExit();
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (this.onPageChangeListeners.isEmpty()) {
            return;
        }
        for (ViewPager.OnPageChangeListener onPageChangeListener : this.onPageChangeListeners) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class ImagePagerAdapter extends PagerAdapter {
        private boolean hasPlayBeginAnimation;
        private final SparseArray<ImageView> mImageSparseArray = new SparseArray<>();

        @Override // android.support.p083v4.view.PagerAdapter
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        ImagePagerAdapter() {
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public int getCount() {
            if (ImageWatcher.this.mUrlList != null) {
                return ImageWatcher.this.mUrlList.size();
            }
            return 0;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
            viewGroup.removeView((View) obj);
            this.mImageSparseArray.remove(i);
        }

        @Override // android.support.p083v4.view.PagerAdapter
        @NonNull
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            viewGroup.addView(frameLayout);
            ImageView imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            frameLayout.addView(imageView);
            this.mImageSparseArray.put(i, imageView);
            View initialView = ImageWatcher.this.loadingUIProvider != null ? ImageWatcher.this.loadingUIProvider.initialView(viewGroup.getContext()) : null;
            if (initialView == null) {
                initialView = new View(viewGroup.getContext());
            }
            frameLayout.addView(initialView);
            ImageView imageView2 = new ImageView(viewGroup.getContext());
            imageView2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView2.setImageResource(ImageWatcher.this.mErrorImageRes);
            frameLayout.addView(imageView2);
            imageView2.setVisibility(8);
            if (setDefaultDisplayConfigs(imageView, i, this.hasPlayBeginAnimation)) {
                this.hasPlayBeginAnimation = true;
            }
            return frameLayout;
        }

        void notifyItemChanged(final int i) {
            final ImageView imageView = this.mImageSparseArray.get(i);
            if (imageView != null) {
                ImageWatcher.this.loader.load(imageView.getContext(), ImageWatcher.this.mUrlList.get(i), new LoadCallback() { // from class: com.github.ielse.imagewatcher.ImageWatcher.ImagePagerAdapter.1
                    @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadCallback
                    public void onResourceReady(Drawable drawable) {
                        int i2;
                        int i3;
                        int i4;
                        float intrinsicWidth = drawable.getIntrinsicWidth();
                        float intrinsicHeight = drawable.getIntrinsicHeight();
                        if ((intrinsicWidth * 1.0f) / intrinsicHeight > (ImageWatcher.this.mWidth * 1.0f) / ImageWatcher.this.mHeight) {
                            i2 = ImageWatcher.this.mWidth;
                            i3 = (int) (((i2 * 1.0f) / intrinsicWidth) * intrinsicHeight);
                            i4 = (ImageWatcher.this.mHeight - i3) / 2;
                            imageView.setTag(C4376R.C4379id.image_orientation, "horizontal");
                        } else {
                            i2 = ImageWatcher.this.mWidth;
                            i3 = (int) (((i2 * 1.0f) / intrinsicWidth) * intrinsicHeight);
                            imageView.setTag(C4376R.C4379id.image_orientation, "vertical");
                            i4 = 0;
                        }
                        imageView.setImageDrawable(drawable);
                        ImagePagerAdapter.this.notifyItemChangedState(i, false, false);
                        ViewState.restore(imageView, ViewState.write(imageView, ViewState.STATE_DEFAULT).width(i2).height(i3).translationX(0).translationY(i4).mTag);
                        imageView.setAlpha(1.0f);
                        imageView.animate().alpha(1.0f).start();
                        imageView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.github.ielse.imagewatcher.ImageWatcher.ImagePagerAdapter.1.1
                            @Override // android.view.View.OnAttachStateChangeListener
                            public void onViewAttachedToWindow(View view) {
                            }

                            @Override // android.view.View.OnAttachStateChangeListener
                            public void onViewDetachedFromWindow(View view) {
                                Drawable drawable2 = imageView.getDrawable();
                                if (drawable2 instanceof Animatable) {
                                    ((Animatable) drawable2).stop();
                                }
                            }
                        });
                        Drawable drawable2 = imageView.getDrawable();
                        if (drawable2 instanceof Animatable) {
                            Animatable animatable = (Animatable) drawable2;
                            if (animatable.isRunning()) {
                                return;
                            }
                            animatable.start();
                        }
                    }

                    @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadCallback
                    public void onLoadStarted(Drawable drawable) {
                        ImagePagerAdapter.this.notifyItemChangedState(i, true, false);
                    }

                    @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadCallback
                    public void onLoadFailed(Drawable drawable) {
                        ImagePagerAdapter.this.notifyItemChangedState(i, false, imageView.getDrawable() == null);
                    }
                });
            }
        }

        void notifyItemChangedState(int i, boolean z, boolean z2) {
            ImageView imageView = this.mImageSparseArray.get(i);
            if (imageView != null) {
                FrameLayout frameLayout = (FrameLayout) imageView.getParent();
                View childAt = frameLayout.getChildAt(1);
                if (ImageWatcher.this.loadingUIProvider != null) {
                    if (z) {
                        ImageWatcher.this.loadingUIProvider.start(childAt);
                    } else {
                        ImageWatcher.this.loadingUIProvider.stop(childAt);
                    }
                }
                ImageView imageView2 = (ImageView) frameLayout.getChildAt(2);
                imageView2.setAlpha(1.0f);
                imageView2.setVisibility(z2 ? 0 : 8);
            }
        }

        private boolean setDefaultDisplayConfigs(final ImageView imageView, final int i, boolean z) {
            final boolean z2;
            int[] iArr;
            int height;
            if (i != ImageWatcher.this.initPosition || z) {
                z2 = false;
            } else {
                ImageWatcher.this.iSource = imageView;
                z2 = true;
            }
            ImageView imageView2 = ImageWatcher.this.mImageGroupList != null ? ImageWatcher.this.mImageGroupList.get(i) : null;
            if (imageView2 != null) {
                imageView2.getLocationOnScreen(new int[2]);
                imageView.setTranslationX(iArr[0]);
                imageView.setTranslationY(iArr[1] - ImageWatcher.this.mStatusBarHeight);
                imageView.getLayoutParams().width = imageView2.getWidth();
                imageView.getLayoutParams().height = imageView2.getHeight();
                ViewState.write(imageView, ViewState.STATE_ORIGIN).width(imageView2.getWidth()).height(imageView2.getHeight());
                Drawable drawable = imageView2.getDrawable();
                if (drawable != null) {
                    int width = drawable.getBounds().width();
                    ViewState translationY = ViewState.write(imageView, ViewState.STATE_THUMB).width(width).height(drawable.getBounds().height()).translationX((ImageWatcher.this.mWidth - width) / 2).translationY((ImageWatcher.this.mHeight - height) / 2);
                    if (drawable instanceof Animatable) {
                        Drawable.ConstantState constantState = drawable.getConstantState();
                        if (constantState != null) {
                            imageView.setImageDrawable(constantState.newDrawable());
                        } else {
                            imageView.setImageDrawable(null);
                        }
                    } else {
                        imageView.setImageDrawable(drawable);
                    }
                    if (z2) {
                        ImageWatcher.this.animSourceViewStateTransform(imageView, translationY);
                    } else {
                        ViewState.restore(imageView, translationY.mTag);
                    }
                }
            } else {
                imageView.getLayoutParams().width = 0;
                imageView.getLayoutParams().height = 0;
                ViewState.write(imageView, ViewState.STATE_ORIGIN).alpha(0.0f).width(0).height(0).scaleXBy(1.5f).scaleY(1.5f);
            }
            ViewState.clear(imageView, ViewState.STATE_DEFAULT);
            ImageWatcher.this.loader.load(imageView.getContext(), ImageWatcher.this.mUrlList.get(i), new LoadCallback() { // from class: com.github.ielse.imagewatcher.ImageWatcher.ImagePagerAdapter.2
                @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadCallback
                public void onResourceReady(Drawable drawable2) {
                    int i2;
                    int i3;
                    int i4;
                    float intrinsicWidth = drawable2.getIntrinsicWidth();
                    float intrinsicHeight = drawable2.getIntrinsicHeight();
                    if ((intrinsicWidth * 1.0f) / intrinsicHeight > (ImageWatcher.this.mWidth * 1.0f) / ImageWatcher.this.mHeight) {
                        i2 = ImageWatcher.this.mWidth;
                        i3 = (int) (((i2 * 1.0f) / intrinsicWidth) * intrinsicHeight);
                        i4 = (ImageWatcher.this.mHeight - i3) / 2;
                        imageView.setTag(C4376R.C4379id.image_orientation, "horizontal");
                    } else {
                        i2 = ImageWatcher.this.mWidth;
                        i3 = (int) (((i2 * 1.0f) / intrinsicWidth) * intrinsicHeight);
                        imageView.setTag(C4376R.C4379id.image_orientation, "vertical");
                        i4 = 0;
                    }
                    imageView.setImageDrawable(drawable2);
                    ImagePagerAdapter.this.notifyItemChangedState(i, false, false);
                    ViewState translationY2 = ViewState.write(imageView, ViewState.STATE_DEFAULT).width(i2).height(i3).translationX(0).translationY(i4);
                    if (z2) {
                        ImageWatcher.this.animSourceViewStateTransform(imageView, translationY2);
                    } else {
                        ViewState.restore(imageView, translationY2.mTag);
                        imageView.setAlpha(0.0f);
                        imageView.animate().alpha(1.0f).start();
                    }
                    imageView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.github.ielse.imagewatcher.ImageWatcher.ImagePagerAdapter.2.1
                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewAttachedToWindow(View view) {
                        }

                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewDetachedFromWindow(View view) {
                            Drawable drawable3 = imageView.getDrawable();
                            if (drawable3 instanceof Animatable) {
                                ((Animatable) drawable3).stop();
                            }
                        }
                    });
                    Drawable drawable3 = imageView.getDrawable();
                    if (drawable3 instanceof Animatable) {
                        Animatable animatable = (Animatable) drawable3;
                        if (animatable.isRunning()) {
                            return;
                        }
                        animatable.start();
                    }
                }

                @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadCallback
                public void onLoadStarted(Drawable drawable2) {
                    ImagePagerAdapter.this.notifyItemChangedState(i, true, false);
                }

                @Override // com.github.ielse.imagewatcher.ImageWatcher.LoadCallback
                public void onLoadFailed(Drawable drawable2) {
                    ImagePagerAdapter.this.notifyItemChangedState(i, false, imageView.getDrawable() == null);
                }
            });
            if (z2) {
                ImageWatcher.this.animBackgroundTransform(-16777216, 3);
            }
            return z2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class RefHandler extends Handler {
        WeakReference<ImageWatcher> mRef;

        RefHandler(ImageWatcher imageWatcher) {
            this.mRef = new WeakReference<>(imageWatcher);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ImageWatcher imageWatcher = this.mRef.get();
            if (imageWatcher != null) {
                switch (message.what) {
                    case 1:
                        imageWatcher.onSingleTapConfirmed();
                        return;
                    case 2:
                        imageWatcher.internalDisplayDataAfterLayout();
                        return;
                    default:
                        throw new RuntimeException("Unknown message " + message);
                }
            }
        }
    }

    public void setTranslucentStatus(int i) {
        this.mStatusBarHeight = i;
    }

    public void setErrorImageRes(int i) {
        this.mErrorImageRes = i;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        super.setBackgroundColor(i);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
        if (this.isInitLayout) {
            return;
        }
        this.isInitLayout = true;
        this.mHandler.sendEmptyMessage(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalDisplayDataAfterLayout() {
        List<Uri> list = this.initUrlList;
        if (list != null) {
            showInternal(this.initI, this.initImageGroupList, list);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.animImageTransform;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.animImageTransform = null;
        ValueAnimator valueAnimator2 = this.animBackground;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        this.animBackground = null;
        ValueAnimator valueAnimator3 = this.animFling;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
        this.animFling = null;
    }

    public boolean handleBackPressed() {
        return !this.detachedParent && (this.isInTransformAnimation || (this.iSource != null && getVisibility() == 0 && onSingleTapConfirmed()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animSourceViewStateTransform(ImageView imageView, ViewState viewState) {
        if (imageView == null) {
            return;
        }
        ValueAnimator valueAnimator = this.animImageTransform;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.animImageTransform = ViewState.restoreByAnim(imageView, viewState.mTag).addListener(this.mAnimTransitionStateListener).create();
        if (this.animImageTransform != null) {
            if (viewState.mTag == ViewState.STATE_ORIGIN) {
                this.animImageTransform.addListener(new AnimatorListenerAdapter() { // from class: com.github.ielse.imagewatcher.ImageWatcher.3
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        ImageWatcher.this.setVisibility(8);
                    }
                });
            }
            this.animImageTransform.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animBackgroundTransform(final int i, final int i2) {
        if (i == this.mBackgroundColor) {
            return;
        }
        ValueAnimator valueAnimator = this.animBackground;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        final int i3 = this.mBackgroundColor;
        this.animBackground = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(200L);
        this.animBackground.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.ielse.imagewatcher.ImageWatcher.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                float floatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                ImageWatcher imageWatcher = ImageWatcher.this;
                imageWatcher.setBackgroundColor(((Integer) imageWatcher.mColorEvaluator.evaluate(floatValue, Integer.valueOf(i3), Integer.valueOf(i))).intValue());
                if (ImageWatcher.this.onStateChangedListeners.isEmpty()) {
                    return;
                }
                for (OnStateChangedListener onStateChangedListener : ImageWatcher.this.onStateChangedListeners) {
                    ImageWatcher imageWatcher2 = ImageWatcher.this;
                    onStateChangedListener.onStateChangeUpdate(imageWatcher2, imageWatcher2.iSource, ImageWatcher.this.getCurrentPosition(), ImageWatcher.this.getDisplayingUri(), floatValue, i2);
                }
            }
        });
        this.animBackground.addListener(new AnimatorListenerAdapter() { // from class: com.github.ielse.imagewatcher.ImageWatcher.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (ImageWatcher.this.onStateChangedListeners.isEmpty() || i2 != 3) {
                    return;
                }
                for (OnStateChangedListener onStateChangedListener : ImageWatcher.this.onStateChangedListeners) {
                    ImageWatcher imageWatcher = ImageWatcher.this;
                    onStateChangedListener.onStateChanged(imageWatcher, imageWatcher.getCurrentPosition(), ImageWatcher.this.getDisplayingUri(), i2);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!ImageWatcher.this.onStateChangedListeners.isEmpty() && i2 == 4) {
                    for (OnStateChangedListener onStateChangedListener : ImageWatcher.this.onStateChangedListeners) {
                        ImageWatcher imageWatcher = ImageWatcher.this;
                        onStateChangedListener.onStateChanged(imageWatcher, imageWatcher.getCurrentPosition(), ImageWatcher.this.getDisplayingUri(), i2);
                    }
                }
                if (ImageWatcher.this.detachAffirmative && i2 == 4) {
                    ImageWatcher.this.detachedParent = true;
                    if (ImageWatcher.this.getParent() != null) {
                        ((ViewGroup) ImageWatcher.this.getParent()).removeView(ImageWatcher.this);
                    }
                }
            }
        });
        this.animBackground.start();
    }

    private void animFling(ImageView imageView, ViewState viewState, long j) {
        if (j > 800) {
            j = 800;
        } else if (j < 100) {
            j = 100;
        }
        ValueAnimator valueAnimator = this.animFling;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.animFling = ViewState.restoreByAnim(imageView, viewState.mTag).addListener(new AnimatorListenerAdapter() { // from class: com.github.ielse.imagewatcher.ImageWatcher.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ImageWatcher.this.mTouchMode = 7;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ImageWatcher.this.mTouchMode = 6;
                ImageWatcher.this.onUp(null);
            }
        }).create();
        this.animFling.setInterpolator(this.decelerateInterpolator);
        this.animFling.setDuration(j);
        this.animFling.start();
    }
}
