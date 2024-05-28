package com.baidu.mapapi.map.track;

import com.baidu.mapapi.map.track.TraceOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.map.p145a.InterfaceC2913b;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TraceOverlay {

    /* renamed from: c */
    List<LatLng> f6582c;

    /* renamed from: f */
    boolean f6585f;

    /* renamed from: g */
    int f6586g;

    /* renamed from: h */
    boolean f6587h;
    public InterfaceC2913b mListener;

    /* renamed from: a */
    int f6580a = -265058817;

    /* renamed from: b */
    int f6581b = 14;

    /* renamed from: d */
    int f6583d = 300;

    /* renamed from: e */
    int f6584e = 0;

    public void clear() {
        this.mListener.mo18399c(this);
    }

    public int getAnimationDuration() {
        return this.f6584e;
    }

    public int getAnimationTime() {
        return this.f6583d;
    }

    public int getAnimationType() {
        return this.f6586g;
    }

    public int getColor() {
        return this.f6580a;
    }

    public LatLngBounds getLatLngBounds() {
        List<LatLng> list = this.f6582c;
        if (list == null || list.size() == 0) {
            return null;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(this.f6582c);
        return builder.build();
    }

    public List<LatLng> getPoints() {
        return this.f6582c;
    }

    public int getWidth() {
        return this.f6581b;
    }

    public boolean isAnimate() {
        return this.f6585f;
    }

    public boolean isTrackMove() {
        return this.f6587h;
    }

    public void remove() {
        this.mListener.mo18401a(this);
    }

    public void setAnimate(boolean z) {
        this.f6585f = z;
    }

    public void setAnimationDuration(int i) {
        this.f6584e = i;
    }

    public void setAnimationTime(int i) {
        if (i < 300) {
            throw new IllegalArgumentException("BDMapSDKException: Not less than 300 milliseconds");
        }
        this.f6583d = i;
    }

    public void setColor(int i) {
        this.f6580a = i;
    }

    public void setTraceAnimationType(TraceOptions.TraceAnimateType traceAnimateType) {
        if (traceAnimateType == null) {
            traceAnimateType = TraceOptions.TraceAnimateType.TraceOverlayAnimationEasingCurveLinear;
        }
        this.f6586g = traceAnimateType.ordinal();
    }

    public void setTracePoints(List<LatLng> list) {
        this.f6582c = list;
    }

    public void setTrackMove(boolean z) {
        this.f6587h = z;
    }

    public void setWidth(int i) {
        this.f6581b = i;
    }

    public void update() {
        this.mListener.mo18400b(this);
    }
}
