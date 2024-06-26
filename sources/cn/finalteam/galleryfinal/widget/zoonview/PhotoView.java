package cn.finalteam.galleryfinal.widget.zoonview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import cn.finalteam.galleryfinal.widget.GFImageView;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoView extends GFImageView implements IPhotoView {
    private PhotoViewAttacher mAttacher;
    private ImageView.ScaleType mPendingScaleType;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        init();
    }

    protected void init() {
        PhotoViewAttacher photoViewAttacher = this.mAttacher;
        if (photoViewAttacher == null || photoViewAttacher.getImageView() == null) {
            this.mAttacher = new PhotoViewAttacher(this);
        }
        ImageView.ScaleType scaleType = this.mPendingScaleType;
        if (scaleType != null) {
            setScaleType(scaleType);
            this.mPendingScaleType = null;
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setPhotoViewRotation(float f) {
        this.mAttacher.setRotationTo(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setRotationTo(float f) {
        this.mAttacher.setRotationTo(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setRotationBy(float f) {
        this.mAttacher.setRotationBy(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public boolean canZoom() {
        return this.mAttacher.canZoom();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public RectF getDisplayRect() {
        return this.mAttacher.getDisplayRect();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public Matrix getDisplayMatrix() {
        return this.mAttacher.getDisplayMatrix();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public boolean setDisplayMatrix(Matrix matrix) {
        return this.mAttacher.setDisplayMatrix(matrix);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public float getMinimumScale() {
        return this.mAttacher.getMinimumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public float getMediumScale() {
        return this.mAttacher.getMediumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public float getMaximumScale() {
        return this.mAttacher.getMaximumScale();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public float getScale() {
        return this.mAttacher.getScale();
    }

    @Override // android.widget.ImageView, cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public ImageView.ScaleType getScaleType() {
        return this.mAttacher.getScaleType();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAttacher.setAllowParentInterceptOnEdge(z);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public void setMinScale(float f) {
        setMinimumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setMinimumScale(float f) {
        this.mAttacher.setMinimumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public void setMidScale(float f) {
        setMediumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setMediumScale(float f) {
        this.mAttacher.setMediumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    @Deprecated
    public void setMaxScale(float f) {
        setMaximumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setMaximumScale(float f) {
        this.mAttacher.setMaximumScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScaleLevels(float f, float f2, float f3) {
        this.mAttacher.setScaleLevels(f, f2, f3);
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        PhotoViewAttacher photoViewAttacher = this.mAttacher;
        if (photoViewAttacher != null) {
            photoViewAttacher.update();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        PhotoViewAttacher photoViewAttacher = this.mAttacher;
        if (photoViewAttacher != null) {
            photoViewAttacher.update();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        PhotoViewAttacher photoViewAttacher = this.mAttacher;
        if (photoViewAttacher != null) {
            photoViewAttacher.update();
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener onMatrixChangedListener) {
        this.mAttacher.setOnMatrixChangeListener(onMatrixChangedListener);
    }

    @Override // android.view.View, cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mAttacher.setOnLongClickListener(onLongClickListener);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener onPhotoTapListener) {
        this.mAttacher.setOnPhotoTapListener(onPhotoTapListener);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public PhotoViewAttacher.OnPhotoTapListener getOnPhotoTapListener() {
        return this.mAttacher.getOnPhotoTapListener();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener onViewTapListener) {
        this.mAttacher.setOnViewTapListener(onViewTapListener);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public PhotoViewAttacher.OnViewTapListener getOnViewTapListener() {
        return this.mAttacher.getOnViewTapListener();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScale(float f) {
        this.mAttacher.setScale(f);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScale(float f, boolean z) {
        this.mAttacher.setScale(f, z);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScale(float f, float f2, float f3, boolean z) {
        this.mAttacher.setScale(f, f2, f3, z);
    }

    @Override // android.widget.ImageView, cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setScaleType(ImageView.ScaleType scaleType) {
        PhotoViewAttacher photoViewAttacher = this.mAttacher;
        if (photoViewAttacher != null) {
            photoViewAttacher.setScaleType(scaleType);
        } else {
            this.mPendingScaleType = scaleType;
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setZoomable(boolean z) {
        this.mAttacher.setZoomable(z);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public Bitmap getVisibleRectangleBitmap() {
        return this.mAttacher.getVisibleRectangleBitmap();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setZoomTransitionDuration(int i) {
        this.mAttacher.setZoomTransitionDuration(i);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public IPhotoView getIPhotoViewImplementation() {
        return this.mAttacher;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.mAttacher.setOnDoubleTapListener(onDoubleTapListener);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.IPhotoView
    public void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener onScaleChangeListener) {
        this.mAttacher.setOnScaleChangeListener(onScaleChangeListener);
    }

    @Override // cn.finalteam.galleryfinal.widget.GFImageView, android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        this.mAttacher.cleanup();
        super.onDetachedFromWindow();
    }

    @Override // cn.finalteam.galleryfinal.widget.GFImageView, android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        init();
        super.onAttachedToWindow();
    }
}
