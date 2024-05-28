package cn.finalteam.galleryfinal.widget.zoonview;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {
    private PhotoViewAttacher photoViewAttacher;

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public DefaultOnDoubleTapListener(PhotoViewAttacher photoViewAttacher) {
        setPhotoViewAttacher(photoViewAttacher);
    }

    public void setPhotoViewAttacher(PhotoViewAttacher photoViewAttacher) {
        this.photoViewAttacher = photoViewAttacher;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        RectF displayRect;
        PhotoViewAttacher photoViewAttacher = this.photoViewAttacher;
        if (photoViewAttacher == null) {
            return false;
        }
        ImageView imageView = photoViewAttacher.getImageView();
        if (this.photoViewAttacher.getOnPhotoTapListener() != null && (displayRect = this.photoViewAttacher.getDisplayRect()) != null) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (displayRect.contains(x, y)) {
                this.photoViewAttacher.getOnPhotoTapListener().onPhotoTap(imageView, (x - displayRect.left) / displayRect.width(), (y - displayRect.top) / displayRect.height());
                return true;
            }
        }
        if (this.photoViewAttacher.getOnViewTapListener() != null) {
            this.photoViewAttacher.getOnViewTapListener().onViewTap(imageView, motionEvent.getX(), motionEvent.getY());
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        PhotoViewAttacher photoViewAttacher = this.photoViewAttacher;
        if (photoViewAttacher == null) {
            return false;
        }
        try {
            float scale = photoViewAttacher.getScale();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (scale < this.photoViewAttacher.getMediumScale()) {
                this.photoViewAttacher.setScale(this.photoViewAttacher.getMediumScale(), x, y, true);
            } else if (scale >= this.photoViewAttacher.getMediumScale() && scale < this.photoViewAttacher.getMaximumScale()) {
                this.photoViewAttacher.setScale(this.photoViewAttacher.getMaximumScale(), x, y, true);
            } else {
                this.photoViewAttacher.setScale(this.photoViewAttacher.getMinimumScale(), x, y, true);
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
        }
        return true;
    }
}
