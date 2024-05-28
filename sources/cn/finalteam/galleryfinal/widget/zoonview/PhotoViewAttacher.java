package cn.finalteam.galleryfinal.widget.zoonview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoViewAttacher implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, IPhotoView, OnGestureListener {
    static final int EDGE_BOTH = 2;
    static final int EDGE_LEFT = 0;
    static final int EDGE_NONE = -1;
    static final int EDGE_RIGHT = 1;
    static final Interpolator sInterpolator = new AccelerateDecelerateInterpolator();
    int ZOOM_DURATION;
    private boolean mAllowParentInterceptOnEdge;
    private final Matrix mBaseMatrix;
    private boolean mBlockParentIntercept;
    private FlingRunnable mCurrentFlingRunnable;
    private final RectF mDisplayRect;
    private final Matrix mDrawMatrix;
    private android.view.GestureDetector mGestureDetector;
    private WeakReference<ImageView> mImageView;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    private View.OnLongClickListener mLongClickListener;
    private OnMatrixChangedListener mMatrixChangeListener;
    private final float[] mMatrixValues;
    private float mMaxScale;
    private float mMidScale;
    private float mMinScale;
    private OnPhotoTapListener mPhotoTapListener;
    private OnScaleChangeListener mScaleChangeListener;
    private GestureDetector mScaleDragDetector;
    private ImageView.ScaleType mScaleType;
    private int mScrollEdge;
    private final Matrix mSuppMatrix;
    private OnViewTapListener mViewTapListener;
    private boolean mZoomEnabled;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMatrixChangedListener {
        void onMatrixChanged(RectF rectF);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnPhotoTapListener {
        void onPhotoTap(View view, float f, float f2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnScaleChangeListener {
        void onScaleChange(float f, float f2, float f3);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnViewTapListener {
        void onViewTap(View view, float f, float f2);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public IPhotoView getIPhotoViewImplementation() {
        return this;
    }

    private static void checkZoomLevels(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        }
        if (f2 >= f3) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
        }
    }

    private static boolean hasDrawable(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher$2 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static /* synthetic */ class C16832 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static boolean isSupportedScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        if (C16832.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()] != 1) {
            return true;
        }
        throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
    }

    private static void setImageViewScaleTypeMatrix(ImageView imageView) {
        if (imageView == null || (imageView instanceof IPhotoView) || ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            return;
        }
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
    }

    public PhotoViewAttacher(ImageView imageView) {
        this(imageView, true);
    }

    public PhotoViewAttacher(ImageView imageView, boolean z) {
        this.ZOOM_DURATION = 200;
        this.mMinScale = 1.0f;
        this.mMidScale = 1.75f;
        this.mMaxScale = 3.0f;
        this.mAllowParentInterceptOnEdge = true;
        this.mBlockParentIntercept = false;
        this.mBaseMatrix = new Matrix();
        this.mDrawMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayRect = new RectF();
        this.mMatrixValues = new float[9];
        this.mScrollEdge = 2;
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mImageView = new WeakReference<>(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        setImageViewScaleTypeMatrix(imageView);
        if (imageView.isInEditMode()) {
            return;
        }
        this.mScaleDragDetector = VersionedGestureDetector.newInstance(imageView.getContext(), this);
        this.mGestureDetector = new android.view.GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                if (PhotoViewAttacher.this.mLongClickListener != null) {
                    PhotoViewAttacher.this.mLongClickListener.onLongClick(PhotoViewAttacher.this.getImageView());
                }
            }
        });
        this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        setZoomable(z);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnScaleChangeListener(OnScaleChangeListener onScaleChangeListener) {
        this.mScaleChangeListener = onScaleChangeListener;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public boolean canZoom() {
        return this.mZoomEnabled;
    }

    public void cleanup() {
        WeakReference<ImageView> weakReference = this.mImageView;
        if (weakReference == null) {
            return;
        }
        ImageView imageView = weakReference.get();
        if (imageView != null) {
            ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
            imageView.setOnTouchListener(null);
            cancelFling();
        }
        android.view.GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.setOnDoubleTapListener(null);
        }
        this.mMatrixChangeListener = null;
        this.mPhotoTapListener = null;
        this.mViewTapListener = null;
        this.mImageView = null;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public boolean setDisplayMatrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        ImageView imageView = getImageView();
        if (imageView == null || imageView.getDrawable() == null) {
            return false;
        }
        this.mSuppMatrix.set(matrix);
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
        return true;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setPhotoViewRotation(float f) {
        this.mSuppMatrix.setRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setRotationTo(float f) {
        this.mSuppMatrix.setRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setRotationBy(float f) {
        this.mSuppMatrix.postRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    public ImageView getImageView() {
        WeakReference<ImageView> weakReference = this.mImageView;
        ImageView imageView = weakReference != null ? weakReference.get() : null;
        if (imageView == null) {
            cleanup();
        }
        return imageView;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public float getMinimumScale() {
        return this.mMinScale;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public float getMediumScale() {
        return this.mMidScale;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public float getMaximumScale() {
        return this.mMaxScale;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public float getScale() {
        return (float) Math.sqrt(((float) Math.pow(getValue(this.mSuppMatrix, 0), 2.0d)) + ((float) Math.pow(getValue(this.mSuppMatrix, 3), 2.0d)));
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.OnGestureListener
    public void onDrag(float f, float f2) {
        if (this.mScaleDragDetector.isScaling()) {
            return;
        }
        ImageView imageView = getImageView();
        this.mSuppMatrix.postTranslate(f, f2);
        checkAndDisplayMatrix();
        ViewParent parent = imageView.getParent();
        if (!this.mAllowParentInterceptOnEdge || this.mScaleDragDetector.isScaling() || this.mBlockParentIntercept) {
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
                return;
            }
            return;
        }
        int i = this.mScrollEdge;
        if ((i == 2 || ((i == 0 && f >= 1.0f) || (this.mScrollEdge == 1 && f <= -1.0f))) && parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.OnGestureListener
    public void onFling(float f, float f2, float f3, float f4) {
        ImageView imageView = getImageView();
        this.mCurrentFlingRunnable = new FlingRunnable(imageView.getContext());
        this.mCurrentFlingRunnable.fling(getImageViewWidth(imageView), getImageViewHeight(imageView), (int) f3, (int) f4);
        imageView.post(this.mCurrentFlingRunnable);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView != null) {
            if (this.mZoomEnabled) {
                int top = imageView.getTop();
                int right = imageView.getRight();
                int bottom = imageView.getBottom();
                int left = imageView.getLeft();
                if (top == this.mIvTop && bottom == this.mIvBottom && left == this.mIvLeft && right == this.mIvRight) {
                    return;
                }
                updateBaseMatrix(imageView.getDrawable());
                this.mIvTop = top;
                this.mIvRight = right;
                this.mIvBottom = bottom;
                this.mIvLeft = left;
                return;
            }
            updateBaseMatrix(imageView.getDrawable());
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.OnGestureListener
    public void onScale(float f, float f2, float f3) {
        if (getScale() < this.mMaxScale || f < 1.0f) {
            OnScaleChangeListener onScaleChangeListener = this.mScaleChangeListener;
            if (onScaleChangeListener != null) {
                onScaleChangeListener.onScaleChange(f, f2, f3);
            }
            this.mSuppMatrix.postScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // android.view.View.OnTouchListener
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r11, android.view.MotionEvent r12) {
        /*
            r10 = this;
            boolean r0 = r10.mZoomEnabled
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L95
            r0 = r11
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            boolean r0 = hasDrawable(r0)
            if (r0 == 0) goto L95
            android.view.ViewParent r0 = r11.getParent()
            int r3 = r12.getAction()
            r4 = 3
            if (r3 == r4) goto L27
            switch(r3) {
                case 0: goto L1e;
                case 1: goto L27;
                default: goto L1d;
            }
        L1d:
            goto L51
        L1e:
            if (r0 == 0) goto L23
            r0.requestDisallowInterceptTouchEvent(r2)
        L23:
            r10.cancelFling()
            goto L51
        L27:
            float r0 = r10.getScale()
            float r3 = r10.mMinScale
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L51
            android.graphics.RectF r0 = r10.getDisplayRect()
            if (r0 == 0) goto L51
            cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher$AnimatedZoomRunnable r9 = new cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher$AnimatedZoomRunnable
            float r5 = r10.getScale()
            float r6 = r10.mMinScale
            float r7 = r0.centerX()
            float r8 = r0.centerY()
            r3 = r9
            r4 = r10
            r3.<init>(r5, r6, r7, r8)
            r11.post(r9)
            r11 = r2
            goto L52
        L51:
            r11 = r1
        L52:
            cn.finalteam.galleryfinal.widget.zoonview.GestureDetector r0 = r10.mScaleDragDetector
            if (r0 == 0) goto L89
            boolean r11 = r0.isScaling()
            cn.finalteam.galleryfinal.widget.zoonview.GestureDetector r0 = r10.mScaleDragDetector
            boolean r0 = r0.isDragging()
            cn.finalteam.galleryfinal.widget.zoonview.GestureDetector r3 = r10.mScaleDragDetector
            boolean r3 = r3.onTouchEvent(r12)
            if (r11 != 0) goto L72
            cn.finalteam.galleryfinal.widget.zoonview.GestureDetector r11 = r10.mScaleDragDetector
            boolean r11 = r11.isScaling()
            if (r11 != 0) goto L72
            r11 = r2
            goto L73
        L72:
            r11 = r1
        L73:
            if (r0 != 0) goto L7f
            cn.finalteam.galleryfinal.widget.zoonview.GestureDetector r0 = r10.mScaleDragDetector
            boolean r0 = r0.isDragging()
            if (r0 != 0) goto L7f
            r0 = r2
            goto L80
        L7f:
            r0 = r1
        L80:
            if (r11 == 0) goto L85
            if (r0 == 0) goto L85
            r1 = r2
        L85:
            r10.mBlockParentIntercept = r1
            r1 = r3
            goto L8a
        L89:
            r1 = r11
        L8a:
            android.view.GestureDetector r11 = r10.mGestureDetector
            if (r11 == 0) goto L95
            boolean r11 = r11.onTouchEvent(r12)
            if (r11 == 0) goto L95
            r1 = r2
        L95:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAllowParentInterceptOnEdge = z;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public void setMinScale(float f) {
        setMinimumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setMinimumScale(float f) {
        checkZoomLevels(f, this.mMidScale, this.mMaxScale);
        this.mMinScale = f;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public void setMidScale(float f) {
        setMediumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setMediumScale(float f) {
        checkZoomLevels(this.mMinScale, f, this.mMaxScale);
        this.mMidScale = f;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public void setMaxScale(float f) {
        setMaximumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setMaximumScale(float f) {
        checkZoomLevels(this.mMinScale, this.mMidScale, f);
        this.mMaxScale = f;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScaleLevels(float f, float f2, float f3) {
        checkZoomLevels(f, f2, f3);
        this.mMinScale = f;
        this.mMidScale = f2;
        this.mMaxScale = f3;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mLongClickListener = onLongClickListener;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.mMatrixChangeListener = onMatrixChangedListener;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.mPhotoTapListener = onPhotoTapListener;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public OnPhotoTapListener getOnPhotoTapListener() {
        return this.mPhotoTapListener;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.mViewTapListener = onViewTapListener;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public OnViewTapListener getOnViewTapListener() {
        return this.mViewTapListener;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScale(float f) {
        setScale(f, false);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScale(float f, boolean z) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            setScale(f, imageView.getRight() / 2, imageView.getBottom() / 2, z);
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScale(float f, float f2, float f3, boolean z) {
        ImageView imageView = getImageView();
        if (imageView == null || f < this.mMinScale || f > this.mMaxScale) {
            return;
        }
        if (z) {
            imageView.post(new AnimatedZoomRunnable(getScale(), f, f2, f3));
            return;
        }
        this.mSuppMatrix.setScale(f, f, f2, f3);
        checkAndDisplayMatrix();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (!isSupportedScaleType(scaleType) || scaleType == this.mScaleType) {
            return;
        }
        this.mScaleType = scaleType;
        update();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setZoomable(boolean z) {
        this.mZoomEnabled = z;
        update();
    }

    public void update() {
        ImageView imageView = getImageView();
        if (imageView != null) {
            if (this.mZoomEnabled) {
                setImageViewScaleTypeMatrix(imageView);
                updateBaseMatrix(imageView.getDrawable());
                return;
            }
            resetMatrix();
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public Matrix getDisplayMatrix() {
        return new Matrix(getDrawMatrix());
    }

    public Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    private void cancelFling() {
        FlingRunnable flingRunnable = this.mCurrentFlingRunnable;
        if (flingRunnable != null) {
            flingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    private void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private void checkImageViewScaleType() {
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private boolean checkMatrixBounds() {
        RectF displayRect;
        float f;
        ImageView imageView = getImageView();
        if (imageView == null || (displayRect = getDisplayRect(getDrawMatrix())) == null) {
            return false;
        }
        float height = displayRect.height();
        float width = displayRect.width();
        float imageViewHeight = getImageViewHeight(imageView);
        float f2 = 0.0f;
        if (height <= imageViewHeight) {
            switch (C16832.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                case 2:
                    f = -displayRect.top;
                    break;
                case 3:
                    f = (imageViewHeight - height) - displayRect.top;
                    break;
                default:
                    f = ((imageViewHeight - height) / 2.0f) - displayRect.top;
                    break;
            }
        } else if (displayRect.top > 0.0f) {
            f = -displayRect.top;
        } else {
            f = displayRect.bottom < imageViewHeight ? imageViewHeight - displayRect.bottom : 0.0f;
        }
        float imageViewWidth = getImageViewWidth(imageView);
        if (width <= imageViewWidth) {
            switch (C16832.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                case 2:
                    f2 = -displayRect.left;
                    break;
                case 3:
                    f2 = (imageViewWidth - width) - displayRect.left;
                    break;
                default:
                    f2 = ((imageViewWidth - width) / 2.0f) - displayRect.left;
                    break;
            }
            this.mScrollEdge = 2;
        } else if (displayRect.left > 0.0f) {
            this.mScrollEdge = 0;
            f2 = -displayRect.left;
        } else if (displayRect.right < imageViewWidth) {
            f2 = imageViewWidth - displayRect.right;
            this.mScrollEdge = 1;
        } else {
            this.mScrollEdge = -1;
        }
        this.mSuppMatrix.postTranslate(f2, f);
        return true;
    }

    private RectF getDisplayRect(Matrix matrix) {
        Drawable drawable;
        ImageView imageView = getImageView();
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return null;
        }
        this.mDisplayRect.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public Bitmap getVisibleRectangleBitmap() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return null;
        }
        return imageView.getDrawingCache();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setZoomTransitionDuration(int i) {
        if (i < 0) {
            i = 200;
        }
        this.ZOOM_DURATION = i;
    }

    private float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageViewMatrix(Matrix matrix) {
        RectF displayRect;
        ImageView imageView = getImageView();
        if (imageView != null) {
            checkImageViewScaleType();
            imageView.setImageMatrix(matrix);
            if (this.mMatrixChangeListener == null || (displayRect = getDisplayRect(matrix)) == null) {
                return;
            }
            this.mMatrixChangeListener.onMatrixChanged(displayRect);
        }
    }

    private void updateBaseMatrix(Drawable drawable) {
        ImageView imageView = getImageView();
        if (imageView == null || drawable == null) {
            return;
        }
        float imageViewWidth = getImageViewWidth(imageView);
        float imageViewHeight = getImageViewHeight(imageView);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.mBaseMatrix.reset();
        float f = intrinsicWidth;
        float f2 = imageViewWidth / f;
        float f3 = intrinsicHeight;
        float f4 = imageViewHeight / f3;
        if (this.mScaleType == ImageView.ScaleType.CENTER) {
            this.mBaseMatrix.postTranslate((imageViewWidth - f) / 2.0f, (imageViewHeight - f3) / 2.0f);
        } else if (this.mScaleType == ImageView.ScaleType.CENTER_CROP) {
            float max = Math.max(f2, f4);
            this.mBaseMatrix.postScale(max, max);
            this.mBaseMatrix.postTranslate((imageViewWidth - (f * max)) / 2.0f, (imageViewHeight - (f3 * max)) / 2.0f);
        } else if (this.mScaleType == ImageView.ScaleType.CENTER_INSIDE) {
            float min = Math.min(1.0f, Math.min(f2, f4));
            this.mBaseMatrix.postScale(min, min);
            this.mBaseMatrix.postTranslate((imageViewWidth - (f * min)) / 2.0f, (imageViewHeight - (f3 * min)) / 2.0f);
        } else {
            RectF rectF = new RectF(0.0f, 0.0f, f, f3);
            RectF rectF2 = new RectF(0.0f, 0.0f, imageViewWidth, imageViewHeight);
            switch (C16832.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                case 2:
                    this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                    break;
                case 3:
                    this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                    break;
                case 4:
                    this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                    break;
                case 5:
                    this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                    break;
            }
        }
        resetMatrix();
    }

    private int getImageViewWidth(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int getImageViewHeight(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class AnimatedZoomRunnable implements Runnable {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime = System.currentTimeMillis();
        private final float mZoomEnd;
        private final float mZoomStart;

        public AnimatedZoomRunnable(float f, float f2, float f3, float f4) {
            this.mFocalX = f3;
            this.mFocalY = f4;
            this.mZoomStart = f;
            this.mZoomEnd = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView == null) {
                return;
            }
            float interpolate = interpolate();
            float f = this.mZoomStart;
            PhotoViewAttacher.this.onScale((f + ((this.mZoomEnd - f) * interpolate)) / PhotoViewAttacher.this.getScale(), this.mFocalX, this.mFocalY);
            if (interpolate < 1.0f) {
                Compat.postOnAnimation(imageView, this);
            }
        }

        private float interpolate() {
            return PhotoViewAttacher.sInterpolator.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / PhotoViewAttacher.this.ZOOM_DURATION));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final ScrollerProxy mScroller;

        public FlingRunnable(Context context) {
            this.mScroller = ScrollerProxy.getScroller(context);
        }

        public void cancelFling() {
            this.mScroller.forceFinished(true);
        }

        public void fling(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF displayRect = PhotoViewAttacher.this.getDisplayRect();
            if (displayRect == null) {
                return;
            }
            int round = Math.round(-displayRect.left);
            float f = i;
            if (f < displayRect.width()) {
                i6 = Math.round(displayRect.width() - f);
                i5 = 0;
            } else {
                i5 = round;
                i6 = i5;
            }
            int round2 = Math.round(-displayRect.top);
            float f2 = i2;
            if (f2 < displayRect.height()) {
                i8 = Math.round(displayRect.height() - f2);
                i7 = 0;
            } else {
                i7 = round2;
                i8 = i7;
            }
            this.mCurrentX = round;
            this.mCurrentY = round2;
            if (round == i6 && round2 == i8) {
                return;
            }
            this.mScroller.fling(round, round2, i3, i4, i5, i6, i7, i8, 0, 0);
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView;
            if (this.mScroller.isFinished() || (imageView = PhotoViewAttacher.this.getImageView()) == null || !this.mScroller.computeScrollOffset()) {
                return;
            }
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            PhotoViewAttacher.this.mSuppMatrix.postTranslate(this.mCurrentX - currX, this.mCurrentY - currY);
            PhotoViewAttacher photoViewAttacher = PhotoViewAttacher.this;
            photoViewAttacher.setImageViewMatrix(photoViewAttacher.getDrawMatrix());
            this.mCurrentX = currX;
            this.mCurrentY = currY;
            Compat.postOnAnimation(imageView, this);
        }
    }
}
