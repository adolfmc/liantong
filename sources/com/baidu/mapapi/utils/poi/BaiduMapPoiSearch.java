package com.baidu.mapapi.utils.poi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.MapOpenUtil;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.comapi.pano.PanoHttpUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaiduMapPoiSearch {

    /* renamed from: a */
    private static boolean f7025a = true;

    public static void setSupportWebPoi(boolean z) {
        f7025a = z;
    }

    public static boolean openBaiduMapPoiDetialsPage(PoiParaOption poiParaOption, Context context) {
        if (poiParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (poiParaOption.f7027a == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: poi uid can not be null.");
        }
        if (poiParaOption.f7027a.equals("")) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: poi uid can not be empty string.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (f7025a) {
                m18574a(poiParaOption, context);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 810) {
            return MapOpenUtil.m18608a(poiParaOption, context, 3);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
            if (f7025a) {
                m18574a(poiParaOption, context);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
    }

    public static boolean openBaiduMapPoiNearbySearch(PoiParaOption poiParaOption, Context context) {
        if (poiParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (poiParaOption.f7028b == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: poi search key can not be null.");
        }
        if (poiParaOption.f7029c == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: poi search center can not be null.");
        }
        if (poiParaOption.f7029c.longitude == 0.0d || poiParaOption.f7029c.latitude == 0.0d) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: poi search center longitude or latitude can not be 0.");
        }
        if (poiParaOption.f7030d == 0) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: poi search radius larger than 0.");
        }
        if (poiParaOption.f7028b.equals("")) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: poi key can not be empty string");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (f7025a) {
                m18572b(poiParaOption, context);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 810) {
            return MapOpenUtil.m18608a(poiParaOption, context, 4);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
            if (f7025a) {
                m18572b(poiParaOption, context);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
    }

    public static void openBaiduMapPanoShow(String str, Context context) {
        new PanoHttpUtil().m17714a(str, new C2859a(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m18571b(String str, Context context) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("BDMapSDKException: pano id can not be null.");
        }
        if (context == null) {
            throw new RuntimeException("BDMapSDKException: context cannot be null.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/streetscape?");
        sb.append("panoid=");
        sb.append(str);
        sb.append("&pid=");
        sb.append(str);
        sb.append("&panotype=");
        sb.append("street");
        sb.append("&src=");
        sb.append("sdk_[" + context.getPackageName() + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        throw new RuntimeException("BDMapSDKException: BaiduMap app is not installed.");
    }

    public static boolean dispatchPoiToBaiduMap(List<DispathcPoiData> list, Context context) throws Exception {
        if (list.isEmpty() || list.size() <= 0) {
            throw new NullPointerException("BDMapSDKException: dispatch poidata is null");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            return false;
        } else if (baiduMapVersion >= 840) {
            return MapOpenUtil.m18603a(list, context, 6);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.4");
            return false;
        }
    }

    public static void finish(Context context) {
        if (context != null) {
            MapOpenUtil.m18612a(context);
        }
    }

    /* renamed from: a */
    private static void m18574a(PoiParaOption poiParaOption, Context context) {
        Uri parse = Uri.parse("http://api.map.baidu.com/place/detail?uid=" + poiParaOption.f7027a + "&output=html&src=" + context.getPackageName());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(parse);
        context.startActivity(intent);
    }

    /* renamed from: b */
    private static void m18572b(PoiParaOption poiParaOption, Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://api.map.baidu.com/place/search?");
        sb.append("query=");
        sb.append(poiParaOption.f7028b);
        sb.append("&location=");
        LatLng latLng = poiParaOption.f7029c;
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            latLng = CoordTrans.gcjToBaidu(latLng);
        }
        sb.append(latLng.latitude);
        sb.append(",");
        sb.append(latLng.longitude);
        sb.append("&radius=");
        sb.append(poiParaOption.f7030d);
        sb.append("&output=html");
        sb.append("&src=");
        sb.append(context.getPackageName());
        Uri parse = Uri.parse(sb.toString());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(parse);
        context.startActivity(intent);
    }
}
