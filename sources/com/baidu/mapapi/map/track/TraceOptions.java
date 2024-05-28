package com.baidu.mapapi.map.track;

import com.baidu.mapapi.model.LatLng;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TraceOptions {

    /* renamed from: c */
    private List<LatLng> f6573c;

    /* renamed from: f */
    private int f6576f;

    /* renamed from: a */
    private int f6571a = -15794282;

    /* renamed from: b */
    private int f6572b = 14;

    /* renamed from: d */
    private int f6574d = 300;

    /* renamed from: e */
    private boolean f6575e = false;

    /* renamed from: g */
    private int f6577g = TraceAnimateType.TraceOverlayAnimationEasingCurveLinear.ordinal();

    /* renamed from: h */
    private boolean f6578h = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum TraceAnimateType {
        TraceOverlayAnimationEasingCurveLinear,
        TraceOverlayAnimationEasingCurveEaseIn,
        TraceOverlayAnimationEasingCurveEaseOut,
        TraceOverlayAnimationEasingCurveEaseInOut
    }

    public TraceOptions animate(boolean z) {
        this.f6575e = z;
        return this;
    }

    public TraceOptions animationDuration(int i) {
        this.f6576f = i;
        return this;
    }

    public TraceOptions animationTime(int i) {
        if (i >= 300) {
            this.f6574d = i;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: Not less than 300 milliseconds");
    }

    public TraceOptions animationType(TraceAnimateType traceAnimateType) {
        if (traceAnimateType == null) {
            traceAnimateType = TraceAnimateType.TraceOverlayAnimationEasingCurveLinear;
        }
        this.f6577g = traceAnimateType.ordinal();
        return this;
    }

    public TraceOptions color(int i) {
        this.f6571a = i;
        return this;
    }

    public TraceAnimateType getAnimateType() {
        switch (this.f6577g) {
            case 1:
                return TraceAnimateType.TraceOverlayAnimationEasingCurveEaseIn;
            case 2:
                return TraceAnimateType.TraceOverlayAnimationEasingCurveEaseOut;
            case 3:
                return TraceAnimateType.TraceOverlayAnimationEasingCurveEaseInOut;
            default:
                return TraceAnimateType.TraceOverlayAnimationEasingCurveLinear;
        }
    }

    public int getAnimationDuration() {
        return this.f6576f;
    }

    public int getAnimationTime() {
        return this.f6574d;
    }

    public int getColor() {
        return this.f6571a;
    }

    public TraceOverlay getOverlay() {
        TraceOverlay traceOverlay = new TraceOverlay();
        traceOverlay.f6580a = this.f6571a;
        traceOverlay.f6581b = this.f6572b;
        traceOverlay.f6582c = this.f6573c;
        traceOverlay.f6583d = this.f6574d;
        traceOverlay.f6585f = this.f6575e;
        traceOverlay.f6584e = this.f6576f;
        traceOverlay.f6586g = this.f6577g;
        traceOverlay.f6587h = this.f6578h;
        return traceOverlay;
    }

    public List<LatLng> getPoints() {
        return this.f6573c;
    }

    public int getWidth() {
        return this.f6572b;
    }

    public boolean isAnimation() {
        return this.f6575e;
    }

    public boolean isTrackMove() {
        return this.f6578h;
    }

    public TraceOptions points(List<LatLng> list) {
        if (list != null) {
            if (list.size() >= 2) {
                if (list.contains(null)) {
                    throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
                }
                this.f6573c = list;
                return this;
            }
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than 2");
        }
        throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
    }

    public TraceOptions setTrackMove(boolean z) {
        this.f6578h = z;
        return this;
    }

    public TraceOptions width(int i) {
        this.f6572b = i;
        return this;
    }
}
