package com.baidu.p120ar.face;

import android.graphics.PointF;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.IFaceResultData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IFaceResultData extends Parcelable {
    int getAlgoImageHeight();

    int getAlgoImageWidth();

    float[] getFaceBoxes();

    int getFaceCount();

    int[] getFaceIds();

    List<PointF> getFacePoints();

    float[] getGenders();

    List<float[]> getHeadPoses();

    float[] getNormalizedFaceBoxes();

    List<PointF> getNormalizedFacePoints();

    List<PointF> getSingleFacePoints(int i);

    List<PointF> getSingleNormalizedFacePoints(int i);

    long getTimestamp();

    List<String[]> getTriggers();

    boolean isFrontCamera();

    boolean isTracked();
}
