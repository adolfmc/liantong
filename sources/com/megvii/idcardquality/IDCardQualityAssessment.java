package com.megvii.idcardquality;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import com.megvii.idcard.sdk.C5293a;
import com.megvii.idcard.sdk.jni.IDCardApi;
import com.megvii.idcardquality.IDCardQualityResult;
import com.megvii.idcardquality.bean.IDCardAttr;
import java.util.ArrayList;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IDCardQualityAssessment {

    /* renamed from: a */
    private C5293a f12259a;

    /* renamed from: b */
    private int f12260b;
    public String errorType;
    public float mClear;
    public int mImageMode;
    public float mInBound;
    public float mIsIdcard;
    public boolean mIsIgnoreHighlight;
    public boolean mIsIgnoreShadow;

    /* synthetic */ IDCardQualityAssessment(Builder builder, byte b) {
        this(builder);
    }

    public IDCardQualityAssessment() {
        IDCardAttr.IDCardSide iDCardSide = IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT;
        this.mImageMode = 2;
        this.mClear = 0.5f;
        this.mIsIdcard = 0.5f;
        this.mInBound = 0.5f;
        this.mIsIgnoreHighlight = false;
        this.mIsIgnoreShadow = false;
        this.f12259a = new C5293a();
    }

    private IDCardQualityAssessment(Builder builder) {
        IDCardAttr.IDCardSide iDCardSide = IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT;
        this.mImageMode = 2;
        this.mClear = 0.5f;
        this.mIsIdcard = 0.5f;
        this.mInBound = 0.5f;
        this.mIsIgnoreHighlight = false;
        this.mIsIgnoreShadow = false;
        this.f12259a = new C5293a();
        this.mClear = builder.f12261a;
        this.mIsIdcard = builder.f12262b;
        this.mInBound = builder.f12263c;
        this.mIsIgnoreShadow = builder.f12265e;
        this.mIsIgnoreHighlight = builder.f12264d;
    }

    public boolean init(Context context, byte[] bArr) {
        this.errorType = this.f12259a.m13722a(context, bArr);
        return this.errorType == null;
    }

    public void release() {
        this.f12259a.m13709c();
    }

    public IDCardQualityResult getQuality(byte[] bArr, int i, int i2, IDCardAttr.IDCardSide iDCardSide, Rect rect) {
        return getQuality(bArr, i, i2, iDCardSide, rect, this.mImageMode);
    }

    public IDCardQualityResult getQuality(byte[] bArr, int i, int i2, IDCardAttr.IDCardSide iDCardSide, Rect rect, int i3) {
        IDCardQualityResult iDCardQualityResult = new IDCardQualityResult(this.f12259a, bArr, i, i2);
        iDCardQualityResult.fails = new ArrayList();
        if (bArr == null || i == 0 || i2 == 0 || iDCardSide == null) {
            iDCardQualityResult.fails.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_ERRORARGUMENT);
            return iDCardQualityResult;
        }
        Rect rect2 = rect == null ? new Rect(0, 0, i, i2) : rect;
        setConfig(i, i2, rect2, iDCardSide, this.f12260b, i3);
        System.currentTimeMillis();
        C5293a.C5297d m13717a = this.f12259a.m13717a(bArr, i, i2, i3);
        if (m13717a == null) {
            iDCardQualityResult.fails.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_ERRORARGUMENT);
            return iDCardQualityResult;
        }
        float f = m13717a.f12245a;
        float f2 = m13717a.f12246b;
        float f3 = m13717a.f12247c;
        IDCardAttr iDCardAttr = new IDCardAttr();
        iDCardAttr.inBound = f;
        iDCardAttr.isIdcard = f2;
        iDCardAttr.lowQuality = f3;
        iDCardAttr.angles = new float[]{0.0f, 0.0f, 0.0f};
        iDCardAttr.type = IDCardAttr.IDCardType.NORMAL;
        iDCardAttr.side = iDCardSide;
        iDCardAttr.shadowCount = 0;
        iDCardAttr.specularHightlightCount = 0;
        if (f < this.mInBound) {
            iDCardQualityResult.fails.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_OUTSIDETHEROI);
        }
        if (f2 < this.mIsIdcard) {
            iDCardQualityResult.fails.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_OUTSIDETHEROI);
        }
        if (f3 < this.mClear) {
            iDCardQualityResult.fails.add(IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_BLUR);
        }
        if (f >= this.mInBound && f2 >= this.mIsIdcard && f3 >= this.mClear) {
            C5293a.C5298e m13711b = this.f12259a.m13711b();
            iDCardAttr.shadowCount = (m13711b == null || m13711b.f12250c == null) ? 0 : m13711b.f12250c.length;
            iDCardAttr.specularHightlightCount = (m13711b == null || m13711b.f12251d == null) ? 0 : m13711b.f12251d.length;
            iDCardAttr.hasSpecularHighlight = iDCardAttr.specularHightlightCount > 0;
            iDCardAttr.hasShadow = iDCardAttr.shadowCount > 0;
            iDCardAttr.Shadows = m13711b.f12250c;
            iDCardAttr.faculaes = m13711b.f12251d;
            iDCardAttr.cards = m13711b.f12252e;
            if (!this.mIsIgnoreShadow && !m13711b.f12248a) {
                iDCardQualityResult.fails.add(0, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SHADOW);
            }
            if (!this.mIsIgnoreHighlight && !m13711b.f12249b) {
                iDCardQualityResult.fails.add(0, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SPECULARHIGHLIGHT);
            }
            if ((this.mIsIgnoreHighlight || m13711b.f12249b) && (this.mIsIgnoreShadow || m13711b.f12248a)) {
                Point[] m13712a = C5293a.m13712a(m13711b.f12252e[0].f12229c, rect2);
                Bitmap m13719a = C5293a.m13719a(C5293a.m13714a(m13712a), bArr, i, i2);
                Bitmap m13720a = C5293a.m13720a(new Rect((int) (m13719a.getWidth() * 0.06f), (int) (m13719a.getHeight() * 0.08594f), (int) (m13719a.getWidth() * 0.255f), (int) (m13719a.getHeight() * 0.41406f)), m13719a);
                IDCardAttr.nationalEmblemBitmap = m13720a;
                int m13721a = C5293a.m13721a(m13720a);
                Log.w("ceshi", "NE_mean===" + m13721a);
                Bitmap m13720a2 = C5293a.m13720a(new Rect((int) (((float) m13719a.getWidth()) * 0.35f), (int) (((float) m13719a.getHeight()) * 0.35f), (int) (((float) m13719a.getWidth()) * 0.65f), (int) (((float) m13719a.getHeight()) * 0.65f)), m13719a);
                IDCardAttr.centerBitmap = m13720a2;
                int m13721a2 = (int) ((C5293a.m13721a(m13720a2) + 5) * 1.5f);
                Log.w("ceshi", "c_mean===" + m13721a2);
                if (m13721a < m13721a2) {
                    if (iDCardSide != IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
                        iDCardQualityResult.fails.add(0, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_WRONGSIDE);
                        return iDCardQualityResult;
                    }
                    Rect rect3 = new Rect();
                    int width = (int) ((m13719a.getWidth() * 0.6225f) + m13712a[0].x);
                    int height = (int) ((m13719a.getHeight() * 0.16633664f) + m13712a[0].y);
                    int width2 = (int) ((m13719a.getWidth() * 0.9375f) + m13712a[0].x);
                    int height2 = (int) ((m13719a.getHeight() * 0.740594f) + m13712a[0].y);
                    rect3.left = width;
                    rect3.top = height;
                    rect3.right = width2;
                    rect3.bottom = height2;
                    iDCardAttr.portraitPoints = new Point[]{new Point(width, height), new Point(width2, height), new Point(width2, height2), new Point(width, height2)};
                    iDCardAttr.headBitmap = C5293a.m13719a(rect3, bArr, i, i2);
                } else if (iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
                    iDCardQualityResult.fails.add(0, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_WRONGSIDE);
                    return iDCardQualityResult;
                }
                float m13710b = C5293a.m13710b(m13719a);
                iDCardAttr.IDCardBitmap = m13719a;
                iDCardAttr.brightness = m13710b;
                iDCardAttr.cornerPoints = m13712a;
            }
        }
        iDCardQualityResult.attr = iDCardAttr;
        return iDCardQualityResult;
    }

    public void setConfig(int i, int i2, Rect rect, IDCardAttr.IDCardSide iDCardSide, int i3, int i4) {
        this.mImageMode = i4;
        this.f12260b = i3;
        C5293a.C5296c m13724a = this.f12259a.m13724a();
        if (m13724a == null) {
            return;
        }
        int i5 = rect.left;
        int i6 = rect.top;
        int i7 = rect.right;
        int i8 = rect.bottom;
        m13724a.f12233a = i3;
        m13724a.f12234b = 20.0f;
        m13724a.f12235c = 20.0f;
        m13724a.f12236d = 20.0f;
        m13724a.f12240h = i5;
        m13724a.f12241i = i6;
        m13724a.f12242j = i7;
        m13724a.f12243k = i8;
        m13724a.f12244l = 0;
        this.f12259a.m13718a(m13724a);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class Builder {

        /* renamed from: a */
        private float f12261a = 0.5f;

        /* renamed from: b */
        private float f12262b = 0.5f;

        /* renamed from: c */
        private float f12263c = 0.5f;

        /* renamed from: d */
        private boolean f12264d = false;

        /* renamed from: e */
        private boolean f12265e = false;

        public final Builder setClear(float f) {
            this.f12261a = f;
            return this;
        }

        public final Builder setIsIdcard(float f) {
            this.f12262b = f;
            return this;
        }

        public final Builder setInBound(float f) {
            this.f12263c = f;
            return this;
        }

        public final Builder setIsIgnoreHighlight(boolean z) {
            this.f12264d = z;
            return this;
        }

        public final Builder setIsIgnoreShadow(boolean z) {
            this.f12265e = z;
            return this;
        }

        public final IDCardQualityAssessment build() {
            return new IDCardQualityAssessment(this, (byte) 0);
        }
    }

    public static String getVersion() {
        return IDCardApi.nativeGetVersion();
    }
}
