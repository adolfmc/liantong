package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.yalantis.ucrop.C11657R;
import com.yalantis.ucrop.callback.CropBoundsChangeListener;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class UCropView extends FrameLayout {
    private GestureCropImageView mGestureCropImageView;
    private final OverlayView mViewOverlay;

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public UCropView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UCropView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(C11657R.C11662layout.ucrop_view, (ViewGroup) this, true);
        this.mGestureCropImageView = (GestureCropImageView) findViewById(C11657R.C11660id.image_view_crop);
        this.mViewOverlay = (OverlayView) findViewById(C11657R.C11660id.view_overlay);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C11657R.styleable.ucrop_UCropView);
        this.mViewOverlay.processStyledAttributes(obtainStyledAttributes);
        this.mGestureCropImageView.processStyledAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        setListenersToViews();
    }

    private void setListenersToViews() {
        this.mGestureCropImageView.setCropBoundsChangeListener(new CropBoundsChangeListener() { // from class: com.yalantis.ucrop.view.UCropView.1
            @Override // com.yalantis.ucrop.callback.CropBoundsChangeListener
            public void onCropAspectRatioChanged(float f) {
                UCropView.this.mViewOverlay.setTargetAspectRatio(f);
            }
        });
        this.mViewOverlay.setOverlayViewChangeListener(new OverlayViewChangeListener() { // from class: com.yalantis.ucrop.view.UCropView.2
            @Override // com.yalantis.ucrop.callback.OverlayViewChangeListener
            public void onCropRectUpdated(RectF rectF) {
                UCropView.this.mGestureCropImageView.setCropRect(rectF);
            }
        });
    }

    @NonNull
    public GestureCropImageView getCropImageView() {
        return this.mGestureCropImageView;
    }

    @NonNull
    public OverlayView getOverlayView() {
        return this.mViewOverlay;
    }

    public void resetCropImageView() {
        removeView(this.mGestureCropImageView);
        this.mGestureCropImageView = new GestureCropImageView(getContext());
        setListenersToViews();
        this.mGestureCropImageView.setCropRect(getOverlayView().getCropViewRect());
        addView(this.mGestureCropImageView, 0);
    }
}
