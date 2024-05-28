package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GeoCodManager {
    private static GeoCoder geoCoder;
    private static GeoCodManager getCoderManager;

    public static GeoCodManager getInstance() {
        if (getCoderManager == null) {
            getCoderManager = new GeoCodManager();
        }
        if (geoCoder == null) {
            geoCoder = GeoCoder.newInstance();
        }
        return getCoderManager;
    }

    public void getGeCoder(String str, OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        geoCoder.setOnGetGeoCodeResultListener(onGetGeoCoderResultListener);
        geoCoder.geocode(new GeoCodeOption().city(str).address(str));
    }

    public void inverseGeocode(LatLng latLng, OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        geoCoder.setOnGetGeoCodeResultListener(onGetGeoCoderResultListener);
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng).newVersion(1));
    }

    public void GeoCodeStop() {
        GeoCoder geoCoder2 = geoCoder;
        if (geoCoder2 != null) {
            geoCoder2.destroy();
        }
    }
}
