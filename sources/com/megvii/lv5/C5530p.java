package com.megvii.lv5;

import android.content.Context;
import com.megvii.lv5.sdk.C5559R;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.p */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5530p {

    /* renamed from: J */
    public static final C5530p f13115J = new C5530p();

    /* renamed from: A */
    public float f13116A;

    /* renamed from: B */
    public float f13117B;

    /* renamed from: C */
    public float f13118C;

    /* renamed from: E */
    public C5545r f13120E;

    /* renamed from: F */
    public C5545r f13121F;

    /* renamed from: G */
    public float f13122G;

    /* renamed from: H */
    public float f13123H;

    /* renamed from: I */
    public Context f13124I;

    /* renamed from: e */
    public float f13129e;

    /* renamed from: f */
    public float f13130f;

    /* renamed from: g */
    public float f13131g;

    /* renamed from: h */
    public float f13132h;

    /* renamed from: i */
    public float f13133i;

    /* renamed from: j */
    public float f13134j;

    /* renamed from: k */
    public float f13135k;

    /* renamed from: l */
    public float f13136l;

    /* renamed from: m */
    public float f13137m;

    /* renamed from: n */
    public float f13138n;

    /* renamed from: o */
    public float f13139o;

    /* renamed from: p */
    public float f13140p;

    /* renamed from: q */
    public float f13141q;

    /* renamed from: r */
    public float f13142r;

    /* renamed from: s */
    public float f13143s;

    /* renamed from: t */
    public float f13144t;

    /* renamed from: u */
    public float f13145u;

    /* renamed from: v */
    public float f13146v;

    /* renamed from: w */
    public float f13147w;

    /* renamed from: x */
    public float f13148x;

    /* renamed from: y */
    public float f13149y;

    /* renamed from: z */
    public float f13150z;

    /* renamed from: a */
    public float f13125a = 0.78f;

    /* renamed from: b */
    public float f13126b = 0.0f;

    /* renamed from: c */
    public float f13127c = 0.0f;

    /* renamed from: d */
    public float f13128d = 0.0f;

    /* renamed from: D */
    public boolean f13119D = false;

    /* renamed from: a */
    public float m13213a(int i) {
        float f;
        int dimensionPixelSize = this.f13124I.getResources().getDimensionPixelSize(C5559R.dimen.action_animation_image_size);
        int i2 = C5527o2.m13223h(this.f13124I).f12913E1;
        if (i2 == 1 || i2 == 2) {
            float f2 = i;
            float f3 = ((this.f13131g - f2) - dimensionPixelSize) / 2.0f;
            f = f3 < 0.0f ? f2 + (this.f13150z * 0.05f) : f2 + f3;
        } else {
            f = ((this.f13131g - dimensionPixelSize) * 2.0f) / 3.0f;
        }
        this.f13149y = f;
        if (this.f13149y < 0.0f) {
            this.f13149y = 0.0f;
        }
        return this.f13149y;
    }

    /* renamed from: a */
    public C5545r m13214a() {
        return this.f13121F;
    }

    /* renamed from: b */
    public float m13212b(int i) {
        float f;
        int dimensionPixelSize = this.f13124I.getResources().getDimensionPixelSize(C5559R.dimen.action_animation_image_size);
        int i2 = C5527o2.m13223h(this.f13124I).f12913E1;
        if (i2 == 1 || i2 == 2) {
            float f2 = this.f13131g - i;
            f = dimensionPixelSize;
            if ((f2 - f) / 2.0f < 0.0f) {
                this.f13150z = f2 * 1.1f;
                return this.f13150z;
            }
        } else {
            f = dimensionPixelSize;
        }
        this.f13150z = f;
        return this.f13150z;
    }

    public String toString() {
        return "CoordinateManager{previewScale=" + this.f13125a + ", u_circle_redius=" + this.f13126b + ", u_center_x=" + this.f13127c + ", u_center_y=" + this.f13128d + ", u_blur_center_x=" + this.f13129e + ", u_blur_center_y=" + this.f13130f + ", textureTop=" + this.f13131g + ", failTipsY=" + this.f13132h + ", actionTipsY=" + this.f13133i + ", finishTipsY=" + this.f13134j + ", countDownTipsY=" + this.f13135k + ", colorTipsY=" + this.f13136l + ", aX=" + this.f13137m + ", aY=" + this.f13138n + ", bX=" + this.f13139o + ", bY=" + this.f13140p + ", aX_preview=" + this.f13141q + ", aY_preview=" + this.f13142r + ", bX_preview=" + this.f13143s + ", bY_preview=" + this.f13144t + ", textureWidth=" + this.f13145u + ", textureHeight=" + this.f13146v + ", previewTextureWidth=" + this.f13147w + ", previewTextureHeight=" + this.f13148x + ", imageY=" + this.f13149y + ", imageAdjustSize=" + this.f13150z + ", verticalY=" + this.f13116A + ", verticalWidth=" + this.f13117B + ", verticalHeight=" + this.f13118C + ", isCustomAdjusted=" + this.f13119D + ", transform=" + this.f13120E + ", transformPreview=" + this.f13121F + ", wbLeft=" + this.f13122G + ", wbRight=" + this.f13123H + '}';
    }
}
