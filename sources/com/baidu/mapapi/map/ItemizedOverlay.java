package com.baidu.mapapi.map;

import android.graphics.drawable.Drawable;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ItemizedOverlay extends Overlay {

    /* renamed from: a */
    MapView f6126a;

    public ItemizedOverlay(Drawable drawable, MapView mapView) {
        this.type = EnumC2933i.marker;
        this.f6126a = mapView;
    }

    public void addItem(OverlayOptions overlayOptions) {
        if (overlayOptions == null || overlayOptions == null) {
            return;
        }
        this.f6126a.getMap().addOverlay(overlayOptions);
    }

    public void reAddAll() {
    }

    public void removeAll() {
        this.f6126a.getMap().clear();
    }
}
