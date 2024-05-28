package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MapStatusUpdateFactory {
    MapStatusUpdateFactory() {
    }

    public static MapStatusUpdate newLatLng(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(2);
        mapStatusUpdate.f6158b = latLng;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(3);
        mapStatusUpdate.f6159c = latLngBounds;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2) {
        if (latLngBounds == null || i <= 0 || i2 <= 0) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(9);
        mapStatusUpdate.f6159c = latLngBounds;
        mapStatusUpdate.f6160d = i;
        mapStatusUpdate.f6161e = i2;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        if (latLngBounds == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(10);
        mapStatusUpdate.f6159c = latLngBounds;
        mapStatusUpdate.f6167k = i;
        mapStatusUpdate.f6168l = i2;
        mapStatusUpdate.f6169m = i3;
        mapStatusUpdate.f6170n = i4;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngZoom(LatLng latLng, float f) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(4);
        if (latLng == null) {
            return null;
        }
        mapStatusUpdate.f6158b = latLng;
        mapStatusUpdate.f6162f = f;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngZoom(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        if (latLngBounds == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(11);
        mapStatusUpdate.f6159c = latLngBounds;
        mapStatusUpdate.f6167k = i;
        mapStatusUpdate.f6168l = i2;
        mapStatusUpdate.f6169m = i3;
        mapStatusUpdate.f6170n = i4;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newMapStatus(MapStatus mapStatus) {
        if (mapStatus == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(1);
        mapStatusUpdate.f6157a = mapStatus;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate scrollBy(int i, int i2) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(5);
        mapStatusUpdate.f6163g = i;
        mapStatusUpdate.f6164h = i2;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomBy(float f) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(6);
        mapStatusUpdate.f6165i = f;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomBy(float f, Point point) {
        if (point == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(7);
        mapStatusUpdate.f6165i = f;
        mapStatusUpdate.f6166j = point;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomIn() {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(6);
        mapStatusUpdate.f6165i = 1.0f;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomOut() {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(6);
        mapStatusUpdate.f6165i = -1.0f;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomTo(float f) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(8);
        mapStatusUpdate.f6162f = f;
        return mapStatusUpdate;
    }
}
