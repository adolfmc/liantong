package com.baidu.cloud.frameprocessor.gles;

import java.nio.FloatBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Drawable2d {
    private static final int SIZEOF_FLOAT = 4;
    private int mCoordsPerVertex;
    private Prefab mPrefab;
    private FloatBuffer mTexCoordArray;
    private int mTexCoordStride;
    private FloatBuffer mVertexArray;
    private int mVertexCount;
    private int mVertexStride;
    private static final float[] TRIANGLE_COORDS = {0.0f, 0.57735026f, -0.5f, -0.28867513f, 0.5f, -0.28867513f};
    private static final float[] TRIANGLE_TEX_COORDS = {0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
    private static final FloatBuffer TRIANGLE_BUF = GlUtil.createFloatBuffer(TRIANGLE_COORDS);
    private static final FloatBuffer TRIANGLE_TEX_BUF = GlUtil.createFloatBuffer(TRIANGLE_TEX_COORDS);
    private static final float[] RECTANGLE_COORDS = {-0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f};
    private static final float[] RECTANGLE_TEX_COORDS = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private static final FloatBuffer RECTANGLE_BUF = GlUtil.createFloatBuffer(RECTANGLE_COORDS);
    private static final FloatBuffer RECTANGLE_TEX_BUF = GlUtil.createFloatBuffer(RECTANGLE_TEX_COORDS);
    private static final float[] FULL_RECTANGLE_COORDS = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    private static final float[] FULL_RECTANGLE_TEX_COORDS = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
    private static final float[] FULL_RECTANGLE_LINE_COORDS = {-1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f};
    private static final FloatBuffer FULL_RECTANGLE_BUF = GlUtil.createFloatBuffer(FULL_RECTANGLE_COORDS);
    private static final FloatBuffer FULL_RECTANGLE_TEX_BUF = GlUtil.createFloatBuffer(FULL_RECTANGLE_TEX_COORDS);
    private static final FloatBuffer FULL_RECTANGLE_LINE_BUF = GlUtil.createFloatBuffer(FULL_RECTANGLE_LINE_COORDS);

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
        switch (prefab) {
            case TRIANGLE:
                this.mVertexArray = TRIANGLE_BUF;
                this.mTexCoordArray = TRIANGLE_TEX_BUF;
                this.mCoordsPerVertex = 2;
                int i = this.mCoordsPerVertex;
                this.mVertexStride = i * 4;
                this.mVertexCount = TRIANGLE_COORDS.length / i;
                break;
            case RECTANGLE:
                this.mVertexArray = RECTANGLE_BUF;
                this.mTexCoordArray = RECTANGLE_TEX_BUF;
                this.mCoordsPerVertex = 2;
                int i2 = this.mCoordsPerVertex;
                this.mVertexStride = i2 * 4;
                this.mVertexCount = RECTANGLE_COORDS.length / i2;
                break;
            case FULL_RECTANGLE:
                this.mVertexArray = FULL_RECTANGLE_BUF;
                this.mTexCoordArray = FULL_RECTANGLE_TEX_BUF;
                this.mCoordsPerVertex = 2;
                int i3 = this.mCoordsPerVertex;
                this.mVertexStride = i3 * 4;
                this.mVertexCount = FULL_RECTANGLE_COORDS.length / i3;
                break;
            case FULL_LINE:
                this.mVertexArray = FULL_RECTANGLE_LINE_BUF;
                break;
            default:
                throw new RuntimeException("Unknown shape " + prefab);
        }
        this.mTexCoordStride = 8;
        this.mPrefab = prefab;
    }

    public FloatBuffer getVertexArray() {
        return this.mVertexArray;
    }

    public FloatBuffer getTexCoordArray() {
        return this.mTexCoordArray;
    }

    public int getVertexCount() {
        return this.mVertexCount;
    }

    public int getVertexStride() {
        return this.mVertexStride;
    }

    public int getTexCoordStride() {
        return this.mTexCoordStride;
    }

    public int getCoordsPerVertex() {
        return this.mCoordsPerVertex;
    }

    public String toString() {
        if (this.mPrefab != null) {
            return "[Drawable2d: " + this.mPrefab + "]";
        }
        return "[Drawable2d: ...]";
    }
}
