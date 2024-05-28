package cn.finalteam.galleryfinal.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.finalteam.galleryfinal.C1656R;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class FloatingActionButton extends ImageButton {
    private float mCircleSize;
    int mColorNormal;
    int mColorPressed;
    private int mDrawableSize;
    @DrawableRes
    private int mIcon;
    private float mShadowOffset;
    private float mShadowRadius;
    String mTitle;

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1656R.styleable.GFFloatingActionButton, 0, 0);
        this.mColorNormal = obtainStyledAttributes.getColor(C1656R.styleable.GFFloatingActionButton_fabColorNormal, -98555);
        this.mColorPressed = obtainStyledAttributes.getColor(C1656R.styleable.GFFloatingActionButton_fabColorPressed, -98555);
        this.mIcon = obtainStyledAttributes.getResourceId(C1656R.styleable.GFFloatingActionButton_fabIcon, 0);
        this.mTitle = obtainStyledAttributes.getString(C1656R.styleable.GFFloatingActionButton_fabTitle);
        obtainStyledAttributes.recycle();
        updateCircleSize();
        this.mShadowRadius = getDimension(C1656R.dimen.fab_shadow_radius);
        this.mShadowOffset = getDimension(C1656R.dimen.fab_shadow_offset);
        updateDrawableSize();
        updateBackground();
    }

    private void updateDrawableSize() {
        this.mDrawableSize = (int) (this.mCircleSize + (this.mShadowRadius * 2.0f));
    }

    private void updateCircleSize() {
        this.mCircleSize = getDimension(C1656R.dimen.fab_size_normal);
    }

    public void setIcon(@DrawableRes int i) {
        if (this.mIcon != i) {
            this.mIcon = i;
            updateBackground();
        }
    }

    public int getColorNormal() {
        return this.mColorNormal;
    }

    public void setColorNormalResId(@ColorRes int i) {
        setColorNormal(getColor(i));
    }

    public void setColorNormal(int i) {
        if (this.mColorNormal != i) {
            this.mColorNormal = i;
            updateBackground();
        }
    }

    public int getColorPressed() {
        return this.mColorPressed;
    }

    public void setColorPressedResId(@ColorRes int i) {
        setColorPressed(getColor(i));
    }

    public void setColorPressed(int i) {
        if (this.mColorPressed != i) {
            this.mColorPressed = i;
            updateBackground();
        }
    }

    int getColor(@ColorRes int i) {
        return getResources().getColor(i);
    }

    float getDimension(@DimenRes int i) {
        return getResources().getDimension(i);
    }

    public void setTitle(String str) {
        this.mTitle = str;
        TextView textView = (TextView) getTag(C1656R.C1659id.fab_label);
        if (textView != null) {
            textView.setText(str);
        }
    }

    public String getTitle() {
        return this.mTitle;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.mDrawableSize;
        setMeasuredDimension(i3, i3);
    }

    void updateBackground() {
        float f = this.mShadowRadius;
        float f2 = f - this.mShadowOffset;
        float f3 = this.mCircleSize;
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new BitmapDrawable(getResources()), createFillDrawable(new RectF(f, f2, f + f3, f3 + f2)), new BitmapDrawable(getResources()), getIconDrawable()});
        float dimension = (this.mCircleSize - getDimension(C1656R.dimen.fab_icon_size)) / 2.0f;
        float f4 = this.mShadowRadius;
        int i = (int) (f4 + dimension);
        layerDrawable.setLayerInset(3, i, (int) (f2 + dimension), i, (int) (f4 + this.mShadowOffset + dimension));
        setBackgroundCompat(layerDrawable);
    }

    Drawable getIconDrawable() {
        if (this.mIcon != 0) {
            return getResources().getDrawable(this.mIcon);
        }
        return new ColorDrawable(0);
    }

    private StateListDrawable createFillDrawable(RectF rectF) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, createCircleDrawable(rectF, this.mColorPressed));
        stateListDrawable.addState(new int[0], createCircleDrawable(rectF, this.mColorNormal));
        return stateListDrawable;
    }

    private Drawable createCircleDrawable(RectF rectF, int i) {
        int i2 = this.mDrawableSize;
        Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(i);
        canvas.drawOval(rectF, paint);
        return new BitmapDrawable(getResources(), createBitmap);
    }

    @SuppressLint({"NewApi"})
    private void setBackgroundCompat(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }
}
