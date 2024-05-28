package p404l;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;

/* renamed from: l.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12293d {

    /* renamed from: b */
    public static final int[] f24914b = {-4276546, -657931};

    /* renamed from: c */
    public static final int[] f24915c = {-8015639, -15311684};

    /* renamed from: d */
    public static final int[] f24916d = {-8015639, -15311684};

    /* renamed from: a */
    public LayerDrawable f24917a;

    public C12293d() {
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{m1863a(), m1861c(), m1862b()});
        this.f24917a = layerDrawable;
        layerDrawable.setId(0, 16908288);
        this.f24917a.setId(1, 16908303);
        this.f24917a.setId(2, 16908301);
    }

    /* renamed from: a */
    public final GradientDrawable m1863a() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, f24914b);
        gradientDrawable.setCornerRadius(5.0f);
        gradientDrawable.setGradientCenter(0.5f, 0.75f);
        return gradientDrawable;
    }

    /* renamed from: b */
    public final ClipDrawable m1862b() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, f24916d);
        gradientDrawable.setCornerRadius(5.0f);
        gradientDrawable.setGradientCenter(0.5f, 0.75f);
        return new ClipDrawable(gradientDrawable, 3, 1);
    }

    /* renamed from: c */
    public final ClipDrawable m1861c() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, f24915c);
        gradientDrawable.setGradientCenter(0.5f, 0.75f);
        return new ClipDrawable(gradientDrawable, 3, 1);
    }
}
