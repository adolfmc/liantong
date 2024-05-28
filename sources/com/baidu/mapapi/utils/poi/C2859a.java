package com.baidu.mapapi.utils.poi;

import android.content.Context;
import android.util.Log;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comapi.pano.PanoHttpUtil;
import com.baidu.platform.comapi.pano.PanoResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BaiduMapPoiSearch.java */
/* renamed from: com.baidu.mapapi.utils.poi.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2859a implements PanoHttpUtil.InterfaceC3085a<PanoResult> {

    /* renamed from: a */
    final /* synthetic */ Context f7031a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2859a(Context context) {
        this.f7031a = context;
    }

    @Override // com.baidu.platform.comapi.pano.PanoHttpUtil.InterfaceC3085a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo17712a(PanoResult panoResult) {
        if (panoResult == null) {
            Log.d("baidumapsdk", "pano info is null");
            return;
        }
        switch (panoResult.m17711a()) {
            case PANO_UID_ERROR:
                Log.d("baidumapsdk", "pano uid is error, please check param poi uid");
                return;
            case PANO_NOT_FOUND:
                Log.d("baidumapsdk", "pano id not found for this poi point");
                return;
            case PANO_NO_TOKEN:
                Log.d("baidumapsdk", "please check ak for permission");
                return;
            case PANO_NO_ERROR:
                if (panoResult.m17707c() == 1) {
                    try {
                        BaiduMapPoiSearch.m18571b(panoResult.m17708b(), this.f7031a);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Log.d("baidumapsdk", "this point do not support for pano show");
                return;
            default:
                return;
        }
    }

    @Override // com.baidu.platform.comapi.pano.PanoHttpUtil.InterfaceC3085a
    /* renamed from: a */
    public void mo17713a(HttpClient.HttpStateError httpStateError) {
        switch (httpStateError) {
            case NETWORK_ERROR:
                Log.d("baidumapsdk", "current network is not available");
                return;
            case INNER_ERROR:
                Log.d("baidumapsdk", "network inner error, please check network");
                return;
            default:
                return;
        }
    }
}
