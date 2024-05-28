package com.baidu.mapapi.map;

import com.baidu.mapsdkplatform.comapi.map.C2925d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class UiSettings {

    /* renamed from: a */
    private C2925d f6477a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UiSettings(C2925d c2925d) {
        this.f6477a = c2925d;
    }

    public boolean isCompassEnabled() {
        return this.f6477a.m18277t();
    }

    public boolean isOverlookingGesturesEnabled() {
        return this.f6477a.m18386A();
    }

    public boolean isRotateGesturesEnabled() {
        return this.f6477a.m18265z();
    }

    public boolean isScrollGesturesEnabled() {
        return this.f6477a.m18269x();
    }

    public boolean isZoomGesturesEnabled() {
        return this.f6477a.m18267y();
    }

    public void setAllGesturesEnabled(boolean z) {
        setRotateGesturesEnabled(z);
        setScrollGesturesEnabled(z);
        setOverlookingGesturesEnabled(z);
        setZoomGesturesEnabled(z);
        setDoubleClickZoomEnabled(z);
        setTwoTouchClickZoomEnabled(z);
    }

    public void setCompassEnabled(boolean z) {
        this.f6477a.m18288n(z);
    }

    public void setDoubleClickZoomEnabled(boolean z) {
        this.f6477a.m18272v(z);
    }

    public void setEnlargeCenterWithDoubleClickEnable(boolean z) {
        this.f6477a.m18268x(z);
    }

    public void setFlingEnable(boolean z) {
        this.f6477a.m18266y(z);
    }

    public void setInertialAnimation(boolean z) {
        this.f6477a.m18274u(z);
    }

    public void setOverlookingGesturesEnabled(boolean z) {
        this.f6477a.m18385A(z);
    }

    public void setRotateGesturesEnabled(boolean z) {
        this.f6477a.m18264z(z);
    }

    public void setScrollGesturesEnabled(boolean z) {
        this.f6477a.m18278s(z);
    }

    public void setTwoTouchClickZoomEnabled(boolean z) {
        this.f6477a.m18270w(z);
    }

    public void setZoomGesturesEnabled(boolean z) {
        this.f6477a.m18276t(z);
    }
}
