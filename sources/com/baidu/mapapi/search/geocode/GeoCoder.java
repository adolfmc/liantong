package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.p159c.GeoCoderImp;
import com.baidu.platform.core.p159c.IGeoCoder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GeoCoder extends SearcherInternal {

    /* renamed from: a */
    private IGeoCoder f6740a = new GeoCoderImp();

    /* renamed from: b */
    private boolean f6741b;

    private GeoCoder() {
    }

    public static GeoCoder newInstance() {
        BMapManager.init();
        return new GeoCoder();
    }

    public boolean geocode(GeoCodeOption geoCodeOption) {
        if (this.f6740a == null) {
            throw new IllegalStateException("BDMapSDKException: GeoCoder is null, please call newInstance() first.");
        }
        if (geoCodeOption == null || geoCodeOption.mAddress == null || geoCodeOption.mCity == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or address or city can not be null");
        }
        return this.f6740a.mo17605a(geoCodeOption);
    }

    public boolean reverseGeoCode(ReverseGeoCodeOption reverseGeoCodeOption) {
        if (this.f6740a == null) {
            throw new IllegalStateException("BDMapSDKException: GeoCoder is null, please call newInstance() first.");
        }
        if (reverseGeoCodeOption == null || reverseGeoCodeOption.getLocation() == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or mLocation can not be null");
        }
        return this.f6740a.mo17603a(reverseGeoCodeOption);
    }

    public void setOnGetGeoCodeResultListener(OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        IGeoCoder iGeoCoder = this.f6740a;
        if (iGeoCoder == null) {
            throw new IllegalStateException("BDMapSDKException: GeoCoder is null, please call newInstance() first.");
        }
        if (onGetGeoCoderResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iGeoCoder.mo17604a(onGetGeoCoderResultListener);
    }

    public void destroy() {
        if (this.f6741b) {
            return;
        }
        this.f6741b = true;
        this.f6740a.mo17606a();
        BMapManager.destroy();
    }
}
