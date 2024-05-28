package cn.finalteam.galleryfinal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class GFImageView extends ImageView {
    private OnImageViewListener mOnImageViewListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnImageViewListener {
        void onAttach();

        void onDetach();

        void onDraw(Canvas canvas);

        boolean verifyDrawable(Drawable drawable);
    }

    public GFImageView(Context context) {
        super(context);
    }

    public GFImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GFImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnImageViewListener(OnImageViewListener onImageViewListener) {
        this.mOnImageViewListener = onImageViewListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        OnImageViewListener onImageViewListener = this.mOnImageViewListener;
        if (onImageViewListener != null) {
            onImageViewListener.onDetach();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        OnImageViewListener onImageViewListener = this.mOnImageViewListener;
        if (onImageViewListener != null) {
            onImageViewListener.onAttach();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        OnImageViewListener onImageViewListener = this.mOnImageViewListener;
        if (onImageViewListener == null || !onImageViewListener.verifyDrawable(drawable)) {
            return super.verifyDrawable(drawable);
        }
        return true;
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        OnImageViewListener onImageViewListener = this.mOnImageViewListener;
        if (onImageViewListener != null) {
            onImageViewListener.onDetach();
        }
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        OnImageViewListener onImageViewListener = this.mOnImageViewListener;
        if (onImageViewListener != null) {
            onImageViewListener.onAttach();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        OnImageViewListener onImageViewListener = this.mOnImageViewListener;
        if (onImageViewListener != null) {
            onImageViewListener.onDraw(canvas);
        }
    }
}
