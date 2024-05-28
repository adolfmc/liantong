package com.baidu.mapapi.utils.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.IllegalNaviArgumentException;
import com.baidu.mapapi.utils.MapOpenUtil;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.poi.IllegalPoiSearchArgumentException;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaiduMapRoutePlan {

    /* renamed from: a */
    private static boolean f7034a = true;

    public static void setSupportWebRoute(boolean z) {
        f7034a = z;
    }

    public static boolean openBaiduMapWalkingRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (routeParaOption.f7037c == null && routeParaOption.f7035a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and startName not all null.");
        }
        if (routeParaOption.f7038d == null && routeParaOption.f7036b == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: endPoint and endName not all null.");
        }
        if ((TextUtils.isEmpty(routeParaOption.f7037c) && routeParaOption.f7035a == null) || (TextUtils.isEmpty(routeParaOption.f7038d) && routeParaOption.f7036b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f7040f == null) {
            routeParaOption.f7040f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (f7034a) {
                m18569a(routeParaOption, context, 2);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 810) {
            return MapOpenUtil.m18607a(routeParaOption, context, 2);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
            if (f7034a) {
                m18569a(routeParaOption, context, 2);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
    }

    public static boolean openBaiduMapTransitRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (routeParaOption.f7037c == null && routeParaOption.f7035a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and startName not all null.");
        }
        if (routeParaOption.f7038d == null && routeParaOption.f7036b == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: endPoint and endName not all null.");
        }
        if ((TextUtils.isEmpty(routeParaOption.f7037c) && routeParaOption.f7035a == null) || (TextUtils.isEmpty(routeParaOption.f7038d) && routeParaOption.f7036b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f7040f == null) {
            routeParaOption.f7040f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (f7034a) {
                m18569a(routeParaOption, context, 1);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 810) {
            return MapOpenUtil.m18607a(routeParaOption, context, 1);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
            if (f7034a) {
                m18569a(routeParaOption, context, 1);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
    }

    public static void finish(Context context) {
        if (context != null) {
            MapOpenUtil.m18612a(context);
        }
    }

    public static boolean openBaiduMapDrivingRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (routeParaOption.f7037c == null && routeParaOption.f7035a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and startName not all null.");
        }
        if (routeParaOption.f7038d == null && routeParaOption.f7036b == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: endPoint and endName not all null.");
        }
        if ((TextUtils.isEmpty(routeParaOption.f7037c) && routeParaOption.f7035a == null) || (TextUtils.isEmpty(routeParaOption.f7038d) && routeParaOption.f7036b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f7040f == null) {
            routeParaOption.f7040f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (f7034a) {
                m18569a(routeParaOption, context, 0);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 810) {
            return MapOpenUtil.m18607a(routeParaOption, context, 0);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
            if (f7034a) {
                m18569a(routeParaOption, context, 0);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
    }

    /* renamed from: a */
    private static void m18569a(RouteParaOption routeParaOption, Context context, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://api.map.baidu.com/direction?");
        sb.append("origin=");
        LatLng latLng = routeParaOption.f7035a;
        if (SDKInitializer.getCoordType() == CoordType.GCJ02 && latLng != null) {
            latLng = CoordTrans.gcjToBaidu(latLng);
        }
        if (routeParaOption.f7035a != null && routeParaOption.f7037c != null && !routeParaOption.f7037c.equals("") && latLng != null) {
            sb.append("latlng:");
            sb.append(latLng.latitude);
            sb.append(",");
            sb.append(latLng.longitude);
            sb.append("|");
            sb.append("name:");
            sb.append(routeParaOption.f7037c);
        } else if (routeParaOption.f7035a != null && latLng != null) {
            sb.append(latLng.latitude);
            sb.append(",");
            sb.append(latLng.longitude);
        } else {
            sb.append(routeParaOption.f7037c);
        }
        LatLng latLng2 = routeParaOption.f7036b;
        if (SDKInitializer.getCoordType() == CoordType.GCJ02 && latLng2 != null) {
            latLng2 = CoordTrans.gcjToBaidu(latLng2);
        }
        sb.append("&destination=");
        if (routeParaOption.f7036b != null && routeParaOption.f7038d != null && !routeParaOption.f7038d.equals("") && latLng2 != null) {
            sb.append("latlng:");
            sb.append(latLng2.latitude);
            sb.append(",");
            sb.append(latLng2.longitude);
            sb.append("|");
            sb.append("name:");
            sb.append(routeParaOption.f7038d);
        } else if (routeParaOption.f7036b != null && latLng2 != null) {
            sb.append(latLng2.latitude);
            sb.append(",");
            sb.append(latLng2.longitude);
        } else {
            sb.append(routeParaOption.f7038d);
        }
        String str = "";
        switch (i) {
            case 0:
                str = "driving";
                break;
            case 1:
                str = "transit";
                break;
            case 2:
                str = "walking";
                break;
        }
        sb.append("&mode=");
        sb.append(str);
        sb.append("&region=");
        if (routeParaOption.getCityName() == null || routeParaOption.getCityName().equals("")) {
            sb.append("全国");
        } else {
            sb.append(routeParaOption.getCityName());
        }
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

    public static boolean openBaiduMapNewEnergyRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (routeParaOption.f7037c == null && routeParaOption.f7035a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and startName not all null.");
        }
        if (routeParaOption.f7038d == null && routeParaOption.f7036b == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: endPoint and endName not all null.");
        }
        if ((TextUtils.isEmpty(routeParaOption.f7037c) && routeParaOption.f7035a == null) || (TextUtils.isEmpty(routeParaOption.f7038d) && routeParaOption.f7036b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f7040f == null) {
            routeParaOption.f7040f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (f7034a) {
                m18569a(routeParaOption, context, 101);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 810) {
            return MapOpenUtil.m18607a(routeParaOption, context, 101);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
            if (f7034a) {
                m18569a(routeParaOption, context, 101);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
    }

    public static boolean openBaiduMapTruckRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (routeParaOption.f7037c == null && routeParaOption.f7035a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and startName not all null.");
        }
        if (routeParaOption.f7038d == null && routeParaOption.f7036b == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: endPoint and endName not all null.");
        }
        if ((TextUtils.isEmpty(routeParaOption.f7037c) && routeParaOption.f7035a == null) || (TextUtils.isEmpty(routeParaOption.f7038d) && routeParaOption.f7036b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f7040f == null) {
            routeParaOption.f7040f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (f7034a) {
                m18569a(routeParaOption, context, 7);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 810) {
            return MapOpenUtil.m18607a(routeParaOption, context, 102);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
            if (f7034a) {
                m18569a(routeParaOption, context, 7);
                return true;
            }
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
    }
}
