package com.baidu.mapapi.map.track;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface TraceAnimationListener {
    void onTraceAnimationFinish();

    void onTraceAnimationUpdate(int i);

    void onTraceUpdatePosition(LatLng latLng);
}
