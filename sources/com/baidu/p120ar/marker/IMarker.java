package com.baidu.p120ar.marker;

import com.baidu.p120ar.marker.model.LocationMarkerData;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.marker.IMarker */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IMarker {
    void chioceOneCoordinate(LocationMarkerData locationMarkerData);

    void clearRoute();

    void createSession();

    void hideFinalPoint();

    void hideNavigationContent();

    void initMarkerByTrackerType(TrackerType trackerType);

    float[] location2ScreenPoint(float[] fArr);

    void postArrow(float f, float f2, float f3, float f4, int i);

    void postArrow(String str, int i, double[] dArr, double[] dArr2, double[] dArr3, float f, float f2);

    void postFinalArrow(String str, double[] dArr);

    void postLiftDown(double[] dArr, float f);

    void postLiftUp(double[] dArr, float f);

    void postRoute(List<double[]> list);

    void removeAllArrow();

    void removeArrowByArrowId(String str);

    void removeLiftDown();

    void removeLiftUp();

    void resetMarker();

    void setAvailableFrame(MarkerFrameInfo markerFrameInfo);

    void setAxisCallback(IMakerAxisCallback iMakerAxisCallback);

    void setMarkerCompassCallback(OnCompassCallback onCompassCallback);

    void setMarkerStateListener(IMarkerStateListener iMarkerStateListener);

    void setTrackerSessionCallback(OnTrackerSessionCallback onTrackerSessionCallback);

    void showNavigationContent();
}
