package com.baidu.cloud.mediaprocess.gles;

import java.nio.FloatBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Drawable2d {

    /* renamed from: a */
    public static final float[] f4800a = {0.0f, 0.57735026f, -0.5f, -0.28867513f, 0.5f, -0.28867513f};

    /* renamed from: b */
    public static final float[] f4801b = {0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};

    /* renamed from: c */
    public static final FloatBuffer f4802c = GlUtil.createFloatBuffer(f4800a);

    /* renamed from: d */
    public static final FloatBuffer f4803d = GlUtil.createFloatBuffer(f4801b);

    /* renamed from: e */
    public static final float[] f4804e = {-0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f};

    /* renamed from: f */
    public static final float[] f4805f = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};

    /* renamed from: g */
    public static final FloatBuffer f4806g = GlUtil.createFloatBuffer(f4804e);

    /* renamed from: h */
    public static final FloatBuffer f4807h = GlUtil.createFloatBuffer(f4805f);

    /* renamed from: i */
    public static final float[] f4808i = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};

    /* renamed from: j */
    public static final float[] f4809j = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};

    /* renamed from: k */
    public static float[] f4810k = {-1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f};

    /* renamed from: l */
    public static final FloatBuffer f4811l = GlUtil.createFloatBuffer(f4808i);

    /* renamed from: m */
    public static final FloatBuffer f4812m = GlUtil.createFloatBuffer(f4809j);

    /* renamed from: n */
    public static final FloatBuffer f4813n = GlUtil.createFloatBuffer(f4809j);

    /* renamed from: o */
    public static final FloatBuffer f4814o = GlUtil.createFloatBuffer(f4810k);

    /* renamed from: p */
    public FloatBuffer f4815p;

    /* renamed from: q */
    public FloatBuffer f4816q;

    /* renamed from: r */
    public FloatBuffer f4817r;

    /* renamed from: s */
    public int f4818s;

    /* renamed from: t */
    public int f4819t;

    /* renamed from: u */
    public int f4820u;

    /* renamed from: v */
    public int f4821v;

    /* renamed from: w */
    public Prefab f4822w;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.mediaprocess.gles.Drawable2d$1 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static /* synthetic */ class C25491 {

        /* renamed from: a */
        public static final /* synthetic */ int[] f4823a = new int[Prefab.values().length];

        static {
            try {
                f4823a[Prefab.TRIANGLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4823a[Prefab.RECTANGLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4823a[Prefab.FULL_RECTANGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4823a[Prefab.FULL_LINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum Prefab {
        TRIANGLE,
        RECTANGLE,
        FULL_RECTANGLE,
        FULL_LINE
    }

    public Drawable2d(Prefab prefab) {
        int i;
        int length;
        switch (prefab.ordinal()) {
            case 0:
                this.f4815p = f4802c;
                this.f4816q = f4803d;
                this.f4819t = 2;
                i = this.f4819t;
                this.f4820u = i * 4;
                length = f4800a.length;
                this.f4818s = length / i;
                break;
            case 1:
                this.f4815p = f4806g;
                this.f4816q = f4807h;
                this.f4819t = 2;
                i = this.f4819t;
                this.f4820u = i * 4;
                length = f4804e.length;
                this.f4818s = length / i;
                break;
            case 2:
                this.f4815p = f4811l;
                this.f4816q = f4812m;
                this.f4817r = f4813n;
                this.f4819t = 2;
                i = this.f4819t;
                this.f4820u = i * 4;
                length = f4808i.length;
                this.f4818s = length / i;
                break;
            case 3:
                this.f4815p = f4814o;
                break;
            default:
                throw new RuntimeException("Unknown shape " + prefab);
        }
        this.f4821v = 8;
        this.f4822w = prefab;
    }

    public int getCoordsPerVertex() {
        return this.f4819t;
    }

    public FloatBuffer getTexCoordArray() {
        return this.f4816q;
    }

    public FloatBuffer getTexCoordArray2() {
        return this.f4817r;
    }

    public int getTexCoordStride() {
        return this.f4821v;
    }

    public FloatBuffer getVertexArray() {
        return this.f4815p;
    }

    public int getVertexCount() {
        return this.f4818s;
    }

    public int getVertexStride() {
        return this.f4820u;
    }

    public void setScale(float f, float f2) {
        float f3 = f2 * (-1.0f);
        float f4 = (-1.0f) * f;
        float f5 = f2 * 1.0f;
        float f6 = f * 1.0f;
        this.f4815p.put(new float[]{f3, f4, f5, f4, f3, f6, f5, f6});
        this.f4815p.position(0);
    }

    public void setScaleAndTranslate(float f, float f2, float f3, float f4) {
        float[] fArr = new float[8];
        float f5 = f * (-1.0f);
        fArr[0] = f5;
        float f6 = (-1.0f) * f2;
        fArr[1] = f6;
        float f7 = f * 1.0f;
        fArr[2] = f7;
        fArr[3] = f6;
        fArr[4] = f5;
        float f8 = f2 * 1.0f;
        fArr[5] = f8;
        fArr[6] = f7;
        fArr[7] = f8;
        for (int i = 0; i < fArr.length; i++) {
            if (i % 2 == 0) {
                fArr[i] = fArr[i] + f3;
            } else if (i == 1 || i == 3) {
                fArr[i] = fArr[i] + f4;
            } else {
                fArr[i] = fArr[i] + f4;
            }
        }
        this.f4815p.put(fArr);
        this.f4815p.position(0);
    }

    public String toString() {
        if (this.f4822w != null) {
            return "[Drawable2d: " + this.f4822w + "]";
        }
        return "[Drawable2d: ...]";
    }
}
