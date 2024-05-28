package com.baidu.p120ar.marker;

import com.baidu.p120ar.marker.model.LocationMarkerData;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.marker.IMarkerStateListener */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IMarkerStateListener {
    void onCoordinateResult(int i, double[] dArr);

    void onError(int i, String str);

    void onLocationResult(boolean z, List<LocationMarkerData> list);

    void onSessionCreated(boolean z, String str);
}
