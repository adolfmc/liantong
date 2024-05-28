package com.baidu.p120ar.algo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.algo.PreviewInfo */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PreviewInfo {
    private static final int BASE_WIDTH = 640;
    private static PreviewInfo S_16_9 = new PreviewInfo();
    private static PreviewInfo S_4_3;
    private static PreviewInfo S_CUT;
    private float[] camIntrinsicPara;
    private float[] camUndistortPara;
    public int height;
    private boolean isSupport;
    private float scale;
    public int width;

    public float getScale() {
        return 1.0f;
    }

    static {
        PreviewInfo previewInfo = S_16_9;
        previewInfo.width = 1280;
        previewInfo.height = 720;
        previewInfo.isSupport = true;
        previewInfo.camIntrinsicPara = new float[]{1110.8284f, 0.0f, 640.0f, 0.0f, 1111.2183f, 360.0f, 0.0f, 0.0f, 1.0f};
        previewInfo.camUndistortPara = new float[]{1.0E-5f, 0.0f, 0.0f, 0.0f, 0.0f};
        S_4_3 = new PreviewInfo();
        PreviewInfo previewInfo2 = S_4_3;
        previewInfo2.width = 640;
        previewInfo2.height = 480;
        S_16_9.isSupport = true;
        previewInfo2.camIntrinsicPara = new float[]{594.25995f, 0.0f, 313.4141f, 0.0f, 594.826f, 237.53111f, 0.0f, 0.0f, 1.0f};
        previewInfo2.camUndistortPara = new float[]{0.184825f, -0.433983f, -0.003168f, -0.010542f, 0.0f};
        S_CUT = new PreviewInfo();
        PreviewInfo previewInfo3 = S_CUT;
        previewInfo3.width = 640;
        previewInfo3.height = 360;
        previewInfo3.isSupport = true;
        previewInfo3.camIntrinsicPara = new float[]{585.7661f, 0.0f, 310.29126f, 0.0f, 585.70685f, 174.72643f, 0.0f, 0.0f, 1.0f};
        previewInfo3.camUndistortPara = new float[]{0.170531f, -0.380857f, -0.005316f, 0.011078f, 0.0f};
    }

    private PreviewInfo() {
        this.scale = 1.0f;
        this.isSupport = false;
    }

    public PreviewInfo(int i, int i2) {
        this.scale = 1.0f;
        this.isSupport = false;
        this.width = i;
        this.height = i2;
        this.scale = i > 640 ? 0.5f : 1.0f;
    }

    public static PreviewInfo getInstance(int i, int i2) {
        PreviewInfo previewInfo;
        PreviewInfo previewInfo2;
        PreviewInfo previewInfo3 = new PreviewInfo(i, i2);
        float f = i;
        float f2 = f / i2;
        PreviewInfo previewInfo4 = S_16_9;
        if (Math.abs(f2 - (previewInfo4.width / previewInfo4.height)) < 0.03d) {
            previewInfo3.copy(S_16_9, f / previewInfo2.width);
            return previewInfo3;
        }
        PreviewInfo previewInfo5 = S_4_3;
        if (Math.abs(f2 - (previewInfo5.width / previewInfo5.height)) < 0.03d) {
            previewInfo3.copy(S_4_3, f / previewInfo.width);
            return previewInfo3;
        }
        return previewInfo3;
    }

    public static PreviewInfo getInstance(int i, int i2, boolean z) {
        PreviewInfo previewInfo;
        if (!z) {
            return getInstance(i, i2);
        }
        PreviewInfo previewInfo2 = new PreviewInfo(i, i2);
        float f = i;
        PreviewInfo previewInfo3 = S_CUT;
        if (Math.abs((f / i2) - (previewInfo3.width / previewInfo3.height)) < 0.03d) {
            previewInfo2.copy(S_CUT, f / previewInfo.width);
            return previewInfo2;
        }
        return previewInfo2;
    }

    private void copy(PreviewInfo previewInfo, float f) {
        this.camIntrinsicPara = new float[9];
        this.camUndistortPara = new float[5];
        this.isSupport = true;
        for (int i = 0; i < 8; i++) {
            this.camIntrinsicPara[i] = previewInfo.camIntrinsicPara[i] * f;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            this.camUndistortPara[i2] = previewInfo.camUndistortPara[i2];
        }
    }

    public boolean isSupport() {
        return this.isSupport;
    }

    public float[] getCamIntrinsicPara() {
        return this.camIntrinsicPara;
    }

    public float[] getCamUndistortPara() {
        return this.camUndistortPara;
    }
}
