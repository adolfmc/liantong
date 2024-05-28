package com.baidu.mapapi.navi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.utils.MapOpenUtil;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaiduMapNavigation {

    /* renamed from: a */
    private static boolean f6610a = true;

    public static void setSupportWebNavi(boolean z) {
        f6610a = z;
    }

    public static boolean openBaiduMapNavi(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f6613c == null || naviParaOption.f6611a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (f6610a) {
                m18768a(naviParaOption, context);
                return true;
            }
            throw new BaiduMapAppNotSupportNaviException("BDMapSDKException: BaiduMap app is not installed.");
        } else if (baiduMapVersion >= 830) {
            return MapOpenUtil.m18610a(naviParaOption, context, 5);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.2");
            if (f6610a) {
                m18768a(naviParaOption, context);
                return true;
            }
            throw new BaiduMapAppNotSupportNaviException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.2");
        }
    }

    public static boolean openBaiduMapWalkNavi(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f6613c == null || naviParaOption.f6611a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            return false;
        } else if (baiduMapVersion >= 869) {
            return MapOpenUtil.m18610a(naviParaOption, context, 7);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.6.6");
            return false;
        }
    }

    public static boolean openBaiduMapWalkNaviAR(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f6613c == null || naviParaOption.f6611a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            return false;
        } else if (baiduMapVersion >= 869) {
            return MapOpenUtil.m18610a(naviParaOption, context, 9);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.6.6");
            return false;
        }
    }

    public static boolean openBaiduMapBikeNavi(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f6613c == null || naviParaOption.f6611a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            return false;
        } else if (baiduMapVersion >= 869) {
            return MapOpenUtil.m18610a(naviParaOption, context, 8);
        } else {
            Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.6.6");
            return false;
        }
    }

    @Deprecated
    public static void openWebBaiduMapNavi(NaviParaOption naviParaOption, Context context) throws IllegalNaviArgumentException {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f6611a != null && naviParaOption.f6613c != null) {
            GeoPoint ll2mc = CoordUtil.ll2mc(naviParaOption.f6611a);
            GeoPoint ll2mc2 = CoordUtil.ll2mc(naviParaOption.f6613c);
            Uri parse = Uri.parse("http://daohang.map.baidu.com/mobile/#navi/naving/start=" + ll2mc.getLongitudeE6() + "," + ll2mc.getLatitudeE6() + "&endp=" + ll2mc2.getLongitudeE6() + "," + ll2mc2.getLatitudeE6() + "&fromprod=" + m18769a(context) + "/vt=map&state=entry");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setData(parse);
            context.startActivity(intent);
        } else if (naviParaOption.f6612b != null && !naviParaOption.f6612b.equals("") && naviParaOption.f6614d != null && !naviParaOption.f6614d.equals("")) {
            Uri parse2 = Uri.parse("http://daohang.map.baidu.com/mobile/#search/search/qt=nav&sn=2$$$$$$" + naviParaOption.f6612b + "$$$$$$&en=2$$$$$$" + naviParaOption.f6614d + "$$$$$$&fromprod=" + m18769a(context));
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(parse2);
            context.startActivity(intent2);
        } else {
            throw new IllegalNaviArgumentException("BDMapSDKException: you must set start and end point or set the start and end name.");
        }
    }

    /* renamed from: a */
    private static void m18768a(NaviParaOption naviParaOption, Context context) throws IllegalNaviArgumentException {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f6611a != null && naviParaOption.f6613c != null) {
            GeoPoint ll2mc = CoordUtil.ll2mc(naviParaOption.f6611a);
            GeoPoint ll2mc2 = CoordUtil.ll2mc(naviParaOption.f6613c);
            StringBuilder sb = new StringBuilder();
            sb.append("http://app.navi.baidu.com/mobile/#navi/naving/");
            sb.append("&sy=0");
            sb.append("&endp=");
            sb.append("&start=");
            sb.append("&startwd=");
            sb.append("&endwd=");
            sb.append("&fromprod=map_sdk");
            sb.append("&app_version=");
            sb.append("7_5_2");
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put("type", "1");
                if (naviParaOption.f6612b != null && !naviParaOption.f6612b.equals("")) {
                    jSONObject.put("keyword", naviParaOption.f6612b);
                } else {
                    jSONObject.put("keyword", "");
                }
                jSONObject.put("xy", String.valueOf(ll2mc.getLongitudeE6()) + "," + String.valueOf(ll2mc.getLatitudeE6()));
                jSONArray.put(jSONObject);
                jSONObject2.put("type", "1");
                if (naviParaOption.f6614d != null && !naviParaOption.f6614d.equals("")) {
                    jSONObject.put("keyword", naviParaOption.f6614d);
                } else {
                    jSONObject.put("keyword", "");
                }
                jSONObject2.put("xy", String.valueOf(ll2mc2.getLongitudeE6()) + "," + String.valueOf(ll2mc2.getLatitudeE6()));
                jSONArray.put(jSONObject2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONArray.length() > 0) {
                sb.append("&positions=");
                sb.append(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
            }
            sb.append("&ctrl_type=");
            sb.append("&mrsl=");
            sb.append("/vt=map&state=entry");
            Uri parse = Uri.parse(sb.toString());
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setData(parse);
            context.startActivity(intent);
            return;
        }
        throw new IllegalNaviArgumentException("BDMapSDKException: you must set start and end point.");
    }

    public static void finish(Context context) {
        if (context != null) {
            MapOpenUtil.m18612a(context);
        }
    }

    /* renamed from: a */
    private static String m18769a(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }
}
