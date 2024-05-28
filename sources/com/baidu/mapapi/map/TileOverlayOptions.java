package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class TileOverlayOptions {

    /* renamed from: c */
    private static Bundle f6467c = null;

    /* renamed from: j */
    private static final String f6468j = "TileOverlayOptions";

    /* renamed from: b */
    private TileProvider f6470b;
    public int datasource;
    public String urlString;

    /* renamed from: a */
    private int f6469a = 209715200;

    /* renamed from: d */
    private int f6471d = 20;

    /* renamed from: e */
    private int f6472e = 3;

    /* renamed from: f */
    private int f6473f = 15786414;

    /* renamed from: g */
    private int f6474g = -20037726;

    /* renamed from: h */
    private int f6475h = -15786414;

    /* renamed from: i */
    private int f6476i = 20037726;

    public TileOverlayOptions() {
        f6467c = new Bundle();
        f6467c.putInt("rectr", this.f6473f);
        f6467c.putInt("rectb", this.f6474g);
        f6467c.putInt("rectl", this.f6475h);
        f6467c.putInt("rectt", this.f6476i);
    }

    /* renamed from: a */
    private TileOverlayOptions m18831a(int i, int i2) {
        this.f6471d = i;
        this.f6472e = i2;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Bundle m18832a() {
        f6467c.putString("url", this.urlString);
        f6467c.putInt("datasource", this.datasource);
        f6467c.putInt("maxDisplay", this.f6471d);
        f6467c.putInt("minDisplay", this.f6472e);
        f6467c.putInt("sdktiletmpmax", this.f6469a);
        return f6467c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public TileOverlay m18830a(BaiduMap baiduMap) {
        return new TileOverlay(baiduMap, this.f6470b);
    }

    public TileOverlayOptions setMaxTileTmp(int i) {
        this.f6469a = i;
        return this;
    }

    public TileOverlayOptions setPositionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds != null) {
            GeoPoint ll2mc = CoordUtil.ll2mc(latLngBounds.northeast);
            GeoPoint ll2mc2 = CoordUtil.ll2mc(latLngBounds.southwest);
            double latitudeE6 = ll2mc.getLatitudeE6();
            double longitudeE6 = ll2mc2.getLongitudeE6();
            double latitudeE62 = ll2mc2.getLatitudeE6();
            double longitudeE62 = ll2mc.getLongitudeE6();
            if (latitudeE6 <= latitudeE62 || longitudeE62 <= longitudeE6) {
                Log.e(f6468j, "BDMapSDKException: bounds is illegal, use default bounds");
            } else {
                f6467c.putInt("rectr", (int) longitudeE62);
                f6467c.putInt("rectb", (int) latitudeE62);
                f6467c.putInt("rectl", (int) longitudeE6);
                f6467c.putInt("rectt", (int) latitudeE6);
            }
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: bound can not be null");
    }

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        String str;
        String str2;
        int maxDisLevel;
        if (tileProvider == null) {
            return null;
        }
        if (!(tileProvider instanceof UrlTileProvider)) {
            if (!(tileProvider instanceof FileTileProvider)) {
                str = f6468j;
                str2 = "tileProvider must be UrlTileProvider or FileTileProvider";
                Log.e(str, str2);
                return null;
            }
            this.datasource = 0;
            this.f6470b = tileProvider;
            maxDisLevel = tileProvider.getMaxDisLevel();
            int minDisLevel = tileProvider.getMinDisLevel();
            if (maxDisLevel <= 21) {
            }
            Log.e(f6468j, "display level is illegal");
            return this;
        }
        this.datasource = 1;
        String tileUrl = ((UrlTileProvider) tileProvider).getTileUrl();
        if (tileUrl == null || "".equals(tileUrl) || !tileUrl.contains("{x}") || !tileUrl.contains("{y}") || !tileUrl.contains("{z}")) {
            str = f6468j;
            str2 = "tile url template is illegal, must contains {x}、{y}、{z}";
            Log.e(str, str2);
            return null;
        }
        this.urlString = tileUrl;
        this.f6470b = tileProvider;
        maxDisLevel = tileProvider.getMaxDisLevel();
        int minDisLevel2 = tileProvider.getMinDisLevel();
        if (maxDisLevel <= 21 || minDisLevel2 < 3) {
            Log.e(f6468j, "display level is illegal");
        } else {
            m18831a(maxDisLevel, minDisLevel2);
        }
        return this;
    }
}
