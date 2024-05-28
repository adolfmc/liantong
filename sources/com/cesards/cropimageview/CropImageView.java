package com.cesards.cropimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.cesards.cropimageview.model.CropImage;
import com.cesards.cropimageview.model.CropImageFactory;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CropImageView extends ImageView {
    private CropImage cropImage;
    private int cropType;

    public CropImageView(Context context) {
        super(context);
        this.cropType = -1;
        initImageView();
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.cropType = -1;
        parseAttributes(attributeSet);
        initImageView();
    }

    @TargetApi(21)
    public CropImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.cropType = -1;
        parseAttributes(attributeSet);
        initImageView();
    }

    public void setCropType(int i) {
        this.cropType = i;
        setWillNotCacheDrawing(false);
        requestLayout();
        invalidate();
    }

    public int getCropType() {
        return this.cropType;
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (!isInEditMode() && this.cropImage != null && getDrawable() != null) {
            this.cropImage.computeImageTransformation();
        }
        return frame;
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        initImageView();
    }

    private void parseAttributes(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C4077R.styleable.civ_CropImageView);
        this.cropType = obtainStyledAttributes.getInt(C4077R.styleable.civ_CropImageView_crop, -1);
        obtainStyledAttributes.recycle();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        initImageView();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        initImageView();
    }

    private void initImageView() {
        setScaleType(ImageView.ScaleType.MATRIX);
        if (getDrawable() != null) {
            this.cropImage = new CropImageFactory().getCropImage(this);
        }
    }
}
