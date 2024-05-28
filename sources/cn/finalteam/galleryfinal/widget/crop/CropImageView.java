package cn.finalteam.galleryfinal.widget.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import cn.finalteam.galleryfinal.widget.crop.HighlightView;
import cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class CropImageView extends ImageViewTouchBase {
    Context context;
    ArrayList<HighlightView> highlightViews;
    private float lastX;
    private float lastY;
    private int motionEdge;
    HighlightView motionHighlightView;
    private int validPointerId;

    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ Matrix getUnrotatedMatrix() {
        return super.getUnrotatedMatrix();
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase, android.view.View, android.view.KeyEvent.Callback
    public /* bridge */ /* synthetic */ boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase, android.view.View, android.view.KeyEvent.Callback
    public /* bridge */ /* synthetic */ boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase, android.widget.ImageView
    public /* bridge */ /* synthetic */ void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ void setImageBitmapResetBase(Bitmap bitmap, boolean z) {
        super.setImageBitmapResetBase(bitmap, z);
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ void setImageRotateBitmapResetBase(RotateBitmap rotateBitmap, boolean z) {
        super.setImageRotateBitmapResetBase(rotateBitmap, z);
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ void setRecycler(ImageViewTouchBase.Recycler recycler) {
        super.setRecycler(recycler);
    }

    public CropImageView(Context context) {
        super(context);
        this.highlightViews = new ArrayList<>();
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.highlightViews = new ArrayList<>();
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.highlightViews = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.bitmapDisplayed.getBitmap() != null) {
            Iterator<HighlightView> it = this.highlightViews.iterator();
            while (it.hasNext()) {
                HighlightView next = it.next();
                next.matrix.set(getUnrotatedMatrix());
                next.invalidate();
                if (next.hasFocus()) {
                    centerBasedOnHighlightView(next);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public void zoomTo(float f, float f2, float f3) {
        super.zoomTo(f, f2, f3);
        Iterator<HighlightView> it = this.highlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.matrix.set(getUnrotatedMatrix());
            next.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public void zoomIn() {
        super.zoomIn();
        Iterator<HighlightView> it = this.highlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.matrix.set(getUnrotatedMatrix());
            next.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public void zoomOut() {
        super.zoomOut();
        Iterator<HighlightView> it = this.highlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.matrix.set(getUnrotatedMatrix());
            next.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public void postTranslate(float f, float f2) {
        super.postTranslate(f, f2);
        Iterator<HighlightView> it = this.highlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.matrix.postTranslate(f, f2);
            next.invalidate();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        HighlightView.ModifyMode modifyMode;
        if (((CropImageActivity) this.context).isSaving()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                Iterator<HighlightView> it = this.highlightViews.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else {
                        HighlightView next = it.next();
                        int hit = next.getHit(motionEvent.getX(), motionEvent.getY());
                        if (hit != 1) {
                            this.motionEdge = hit;
                            this.motionHighlightView = next;
                            this.lastX = motionEvent.getX();
                            this.lastY = motionEvent.getY();
                            this.validPointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                            HighlightView highlightView = this.motionHighlightView;
                            if (hit == 32) {
                                modifyMode = HighlightView.ModifyMode.Move;
                            } else {
                                modifyMode = HighlightView.ModifyMode.Grow;
                            }
                            highlightView.setMode(modifyMode);
                            break;
                        }
                    }
                }
            case 1:
                HighlightView highlightView2 = this.motionHighlightView;
                if (highlightView2 != null) {
                    centerBasedOnHighlightView(highlightView2);
                    this.motionHighlightView.setMode(HighlightView.ModifyMode.None);
                }
                this.motionHighlightView = null;
                center();
                break;
            case 2:
                if (this.motionHighlightView != null && motionEvent.getPointerId(motionEvent.getActionIndex()) == this.validPointerId) {
                    this.motionHighlightView.handleMotion(this.motionEdge, motionEvent.getX() - this.lastX, motionEvent.getY() - this.lastY);
                    this.lastX = motionEvent.getX();
                    this.lastY = motionEvent.getY();
                }
                if (getScale() == 1.0f) {
                    center();
                    break;
                }
                break;
        }
        return true;
    }

    private void ensureVisible(HighlightView highlightView) {
        Rect rect = highlightView.drawRect;
        int max = Math.max(0, getLeft() - rect.left);
        int min = Math.min(0, getRight() - rect.right);
        int max2 = Math.max(0, getTop() - rect.top);
        int min2 = Math.min(0, getBottom() - rect.bottom);
        if (max == 0) {
            max = min;
        }
        if (max2 != 0) {
            min2 = max2;
        }
        if (max == 0 && min2 == 0) {
            return;
        }
        panBy(max, min2);
    }

    private void centerBasedOnHighlightView(HighlightView highlightView) {
        Rect rect = highlightView.drawRect;
        float max = Math.max(1.0f, Math.min((getWidth() / rect.width()) * 0.6f, (getHeight() / rect.height()) * 0.6f) * getScale());
        if (Math.abs(max - getScale()) / max > 0.1d) {
            float[] fArr = {highlightView.cropRect.centerX(), highlightView.cropRect.centerY()};
            getUnrotatedMatrix().mapPoints(fArr);
            zoomTo(max, fArr[0], fArr[1], 300.0f);
        }
        ensureVisible(highlightView);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<HighlightView> it = this.highlightViews.iterator();
        while (it.hasNext()) {
            it.next().draw(canvas);
        }
    }

    public void add(HighlightView highlightView) {
        this.highlightViews.clear();
        this.highlightViews.add(highlightView);
        invalidate();
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase
    public void clear() {
        this.highlightViews.clear();
        invalidate();
    }
}
