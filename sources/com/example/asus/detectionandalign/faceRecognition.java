package com.example.asus.detectionandalign;

import android.support.p086v7.app.AppCompatActivity;
import android.util.Log;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class faceRecognition extends AppCompatActivity {
    public static native int Authorize(String str);

    private native float[] AutoDetect(int[] iArr, int i, int i2, int i3, int i4);

    public static native float Cosine(float[] fArr, float[] fArr2);

    public static native boolean Init();

    private native float[] SingleFeature(int[] iArr, int i, int i2, float[] fArr, int i3);

    private native float SingleLiveness(int[] iArr, int i, int i2, float[] fArr, int i3);

    static {
        System.loadLibrary("detectionAndRecognition");
        System.loadLibrary("c++_shared");
    }

    public faceDetectionResult[] getDetectionResult(int[] iArr, int i, int i2, int i3, int i4) {
        float[] AutoDetect = AutoDetect(iArr, i, i2, i3, i4);
        int i5 = (int) AutoDetect[AutoDetect.length - 1];
        faceDetectionResult[] facedetectionresultArr = new faceDetectionResult[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            facedetectionresultArr[i6] = new faceDetectionResult();
        }
        for (int i7 = 0; i7 < i5; i7++) {
            facedetectionresultArr[i7].rect = getRect(AutoDetect, i7);
            facedetectionresultArr[i7].raw_facial_points = getFacialPoints(AutoDetect, i7);
        }
        return facedetectionresultArr;
    }

    public float[] extractFeature(int[] iArr, int i, int i2, faceDetectionResult facedetectionresult, int i3) {
        float[] fArr = new float[14];
        fArr[0] = facedetectionresult.rect.f10069x;
        fArr[1] = facedetectionresult.rect.f10070y;
        fArr[2] = facedetectionresult.rect.width;
        fArr[3] = facedetectionresult.rect.height;
        for (int i4 = 0; i4 < 10; i4++) {
            fArr[i4 + 4] = facedetectionresult.raw_facial_points[i4];
        }
        return SingleFeature(iArr, i, i2, fArr, i3);
    }

    public float liveRecognition(int[] iArr, int i, int i2, faceDetectionResult facedetectionresult, int i3) {
        float[] fArr = {facedetectionresult.rect.f10069x, facedetectionresult.rect.f10070y, facedetectionresult.rect.width, facedetectionresult.rect.height};
        Log.d("TAG", String.valueOf(fArr[0]) + " " + String.valueOf(fArr[1]) + " " + String.valueOf(fArr[2]) + " " + String.valueOf(fArr[3]));
        return SingleLiveness(iArr, i, i2, fArr, i3);
    }

    private faceRect getRect(float[] fArr, int i) {
        faceRect facerect = new faceRect();
        int i2 = i * 14;
        facerect.f10069x = fArr[i2 + 0];
        facerect.f10070y = fArr[i2 + 1];
        facerect.width = fArr[i2 + 2];
        facerect.height = fArr[i2 + 3];
        return facerect;
    }

    private float[] getFacialPoints(float[] fArr, int i) {
        float[] fArr2 = new float[10];
        for (int i2 = 0; i2 < 10; i2++) {
            fArr2[i2] = fArr[(i * 14) + 4 + i2];
        }
        return fArr2;
    }

    public static float calculateSimilarity(float[] fArr, float[] fArr2) {
        return (float) ((1.0d / (Math.exp((0.35d - Cosine(fArr, fArr2)) * 6.5d) + 1.0d)) / 0.9857d);
    }

    public static int deviceAuthorization(String str) {
        return Authorize(str);
    }
}
