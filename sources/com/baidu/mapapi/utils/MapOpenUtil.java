package com.baidu.mapapi.utils;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.common.AppTools;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.navi.TruckNaviOption;
import com.baidu.mapapi.utils.poi.DispathcPoiData;
import com.baidu.mapapi.utils.poi.PoiParaOption;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.mapframework.open.aidl.IMapOpenService;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.p151d.SignUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapapi.utils.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapOpenUtil {

    /* renamed from: A */
    private static double f6983A = 0.0d;

    /* renamed from: B */
    private static double f6984B = 0.0d;

    /* renamed from: C */
    private static double f6985C = 0.0d;

    /* renamed from: D */
    private static double f6986D = 0.0d;

    /* renamed from: E */
    private static int f6987E = 0;

    /* renamed from: F */
    private static boolean f6988F = false;

    /* renamed from: G */
    private static String f6989G = null;

    /* renamed from: H */
    private static int f6990H = 0;

    /* renamed from: I */
    private static int f6991I = 0;

    /* renamed from: J */
    private static int f6992J = 0;

    /* renamed from: K */
    private static int f6993K = 0;

    /* renamed from: L */
    private static int f6994L = 0;

    /* renamed from: a */
    public static int f6995a = -1;

    /* renamed from: c */
    private static final String f6997c = "com.baidu.mapapi.utils.b";

    /* renamed from: d */
    private static IMapOpenService f6998d;

    /* renamed from: e */
    private static IComOpenClient f6999e;

    /* renamed from: f */
    private static int f7000f;

    /* renamed from: g */
    private static String f7001g;

    /* renamed from: h */
    private static String f7002h;

    /* renamed from: i */
    private static String f7003i;

    /* renamed from: p */
    private static String f7010p;

    /* renamed from: q */
    private static RouteParaOption.EBusStrategyType f7011q;

    /* renamed from: x */
    private static Thread f7018x;

    /* renamed from: y */
    private static int f7019y;

    /* renamed from: z */
    private static double f7020z;

    /* renamed from: j */
    private static List<DispathcPoiData> f7004j = new ArrayList();

    /* renamed from: k */
    private static LatLng f7005k = null;

    /* renamed from: l */
    private static LatLng f7006l = null;

    /* renamed from: m */
    private static String f7007m = null;

    /* renamed from: n */
    private static String f7008n = null;

    /* renamed from: o */
    private static String f7009o = null;

    /* renamed from: r */
    private static String f7012r = null;

    /* renamed from: s */
    private static String f7013s = null;

    /* renamed from: t */
    private static LatLng f7014t = null;

    /* renamed from: u */
    private static int f7015u = 0;

    /* renamed from: v */
    private static boolean f7016v = false;

    /* renamed from: w */
    private static boolean f7017w = false;

    /* renamed from: b */
    static ServiceConnection f6996b = new ServiceConnectionC2856d();

    /* renamed from: a */
    public static String m18615a() {
        return AppTools.getBaiduMapToken();
    }

    /* renamed from: a */
    public static void m18612a(Context context) {
        if (f7017w) {
            context.unbindService(f6996b);
            f7017w = false;
        }
    }

    /* renamed from: a */
    public static boolean m18607a(RouteParaOption routeParaOption, Context context, int i) {
        m18596b(routeParaOption, context, i);
        return m18611a(context, i);
    }

    /* renamed from: a */
    public static boolean m18608a(PoiParaOption poiParaOption, Context context, int i) {
        m18597b(poiParaOption, context, i);
        return m18611a(context, i);
    }

    /* renamed from: a */
    public static boolean m18610a(NaviParaOption naviParaOption, Context context, int i) {
        m18598b(naviParaOption, context, i);
        return m18611a(context, i);
    }

    /* renamed from: a */
    public static boolean m18603a(List<DispathcPoiData> list, Context context, int i) {
        m18604a(list, context);
        return m18611a(context, i);
    }

    /* renamed from: a */
    public static boolean m18611a(Context context, int i) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!SignUtils.m18047a(context)) {
            Log.d(f6997c, "package sign verify failed");
            return false;
        }
        f7016v = false;
        switch (i) {
            case 0:
                f6995a = 0;
                break;
            case 1:
                f6995a = 1;
                break;
            case 2:
                f6995a = 2;
                break;
            case 3:
                f6995a = 3;
                break;
            case 4:
                f6995a = 4;
                break;
            case 5:
                f6995a = 5;
                break;
            case 6:
                f6995a = 6;
                break;
            case 7:
                f6995a = 7;
                break;
            case 8:
                f6995a = 8;
                break;
            case 9:
                f6995a = 9;
                break;
            default:
                switch (i) {
                    case 101:
                        f6995a = 101;
                        break;
                    case 102:
                        f6995a = 102;
                        break;
                    case 103:
                        f6995a = 103;
                        break;
                    case 104:
                        f6995a = 104;
                        break;
                }
        }
        if (i == 9) {
            f7017w = false;
        }
        if (f6998d != null && f7017w) {
            if (f6999e != null) {
                f7016v = true;
                return m18614a(i);
            }
            f6998d.mo18562a(new BinderC2855c(i));
        } else {
            m18599b(context, i);
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static boolean m18614a(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                break;
            case 3:
                return m18583h();
            case 4:
                return m18575m();
            case 5:
                return m18579j();
            case 6:
                return m18581i();
            case 7:
                return m18577k();
            case 8:
                return m18576l();
            default:
                switch (i) {
                    case 101:
                    case 102:
                        break;
                    case 103:
                        return false;
                    case 104:
                        return false;
                    default:
                        return false;
                }
        }
        return m18585g();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static void m18613a(int i, Context context) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                break;
            case 3:
                m18593c(context);
                return;
            case 4:
                m18590d(context);
                return;
            case 5:
                m18588e(context);
                return;
            case 6:
                return;
            case 7:
                m18582h(context);
                return;
            case 8:
                m18580i(context);
                return;
            case 9:
                m18578j(context);
                return;
            default:
                switch (i) {
                    case 101:
                    case 102:
                        break;
                    case 103:
                        m18586f(context);
                        return;
                    case 104:
                        m18584g(context);
                        return;
                    default:
                        return;
                }
        }
        m18592c(context, i);
    }

    /* renamed from: b */
    private static void m18599b(Context context, int i) {
        Intent intent = new Intent();
        String m18615a = m18615a();
        if (m18615a == null) {
            return;
        }
        intent.putExtra("api_token", m18615a);
        intent.setAction("com.baidu.map.action.OPEN_SERVICE");
        intent.setPackage("com.baidu.BaiduMap");
        if (i != 9) {
            f7017w = context.bindService(intent, f6996b, 1);
        }
        if (f7017w) {
            f7018x = new Thread(new RunnableC2858f(context, i));
            f7018x.setDaemon(true);
            f7018x.start();
            return;
        }
        Log.e("baidumapsdk", "bind service failed，call openapi");
        m18613a(i, context);
    }

    /* renamed from: c */
    private static void m18592c(Context context, int i) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        if (i == 101) {
            i = 3;
        }
        if (i == 102) {
            i = 4;
        }
        String[] strArr = {"driving", "transit", "walking", "neweng", "truck"};
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/direction?");
        sb.append("origin=");
        if (f7005k != null && SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7005k = CoordTrans.gcjToBaidu(f7005k);
        }
        if (!TextUtils.isEmpty(f7007m) && f7005k != null) {
            sb.append("name:");
            sb.append(f7007m);
            sb.append("|latlng:");
            sb.append(f7005k.latitude);
            sb.append(",");
            sb.append(f7005k.longitude);
        } else if (!TextUtils.isEmpty(f7007m)) {
            sb.append(f7007m);
        } else {
            LatLng latLng = f7005k;
            if (latLng != null) {
                sb.append(latLng.latitude);
                sb.append(",");
                sb.append(f7005k.longitude);
            }
        }
        sb.append("&destination=");
        if (f7006l != null && SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7006l = CoordTrans.gcjToBaidu(f7006l);
        }
        if (!TextUtils.isEmpty(f7008n) && f7006l != null) {
            sb.append("name:");
            sb.append(f7008n);
            sb.append("|latlng:");
            sb.append(f7006l.latitude);
            sb.append(",");
            sb.append(f7006l.longitude);
        } else if (!TextUtils.isEmpty(f7008n)) {
            sb.append(f7008n);
        } else {
            LatLng latLng2 = f7006l;
            if (latLng2 != null) {
                sb.append(latLng2.latitude);
                sb.append(",");
                sb.append(f7006l.longitude);
            }
        }
        sb.append("&mode=");
        sb.append(strArr[i]);
        sb.append("&target=");
        sb.append("1");
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: c */
    private static void m18593c(Context context) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/place/detail?");
        sb.append("uid=");
        sb.append(f7012r);
        sb.append("&show_type=");
        sb.append("detail_page");
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: d */
    private static void m18590d(Context context) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/nearbysearch?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7014t = CoordTrans.gcjToBaidu(f7014t);
        }
        sb.append("center=");
        sb.append(f7014t.latitude);
        sb.append(",");
        sb.append(f7014t.longitude);
        sb.append("&query=");
        sb.append(f7013s);
        sb.append("&radius=");
        sb.append(f7015u);
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: e */
    private static void m18588e(Context context) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/navi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7005k = CoordTrans.gcjToBaidu(f7005k);
            f7006l = CoordTrans.gcjToBaidu(f7006l);
        }
        sb.append("origin=");
        sb.append(f7005k.latitude);
        sb.append(",");
        sb.append(f7005k.longitude);
        sb.append("&location=");
        sb.append(f7006l.latitude);
        sb.append(",");
        sb.append(f7006l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        if (!TextUtils.isEmpty(f7009o)) {
            sb.append("&viaPoints=");
            sb.append(f7009o);
        }
        sb.append("&type=");
        sb.append(f7010p);
        sb.append("&mode=");
        sb.append("driving");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: f */
    private static void m18586f(Context context) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/navi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7005k = CoordTrans.gcjToBaidu(f7005k);
            f7006l = CoordTrans.gcjToBaidu(f7006l);
        }
        sb.append("origin=");
        sb.append(f7005k.latitude);
        sb.append(",");
        sb.append(f7005k.longitude);
        sb.append("&location=");
        sb.append(f7006l.latitude);
        sb.append(",");
        sb.append(f7006l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        if (!TextUtils.isEmpty(f7009o)) {
            sb.append("&viaPoints=");
            sb.append(f7009o);
        }
        sb.append("&type=");
        sb.append(f7010p);
        sb.append("&mode=");
        sb.append("neweng");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: g */
    private static void m18584g(Context context) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/truck/navigation?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7006l = CoordTrans.gcjToBaidu(f7006l);
        }
        sb.append("location=");
        sb.append(f7006l.latitude);
        sb.append(",");
        sb.append(f7006l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        if (!TextUtils.isEmpty(f7009o)) {
            sb.append("&viaPoints=");
            sb.append(f7009o);
        }
        sb.append("&type=");
        sb.append(f7010p);
        int i = f7019y;
        if (i > 0 && i <= 4) {
            sb.append("&truck_type=");
            sb.append(f7019y);
            double d = f7020z;
            if (d > 0.0d && d <= 10.0d) {
                sb.append("&height=");
                sb.append(f7020z);
                double d2 = f6983A;
                if (d2 > 0.0d && d2 <= 5.0d) {
                    sb.append("&width=");
                    sb.append(f6983A);
                    double d3 = f6984B;
                    if (d3 > 0.0d && d3 <= 100.0d) {
                        sb.append("&weight=");
                        sb.append(f6984B);
                        double d4 = f6985C;
                        if (d4 > 0.0d && d4 <= 25.0d) {
                            sb.append("&length=");
                            sb.append(f6985C);
                            double d5 = f6986D;
                            if (d5 >= 0.0d && d5 <= 100.0d) {
                                sb.append("&axle_weight=");
                                sb.append(f6986D);
                            }
                            int i2 = f6987E;
                            if (i2 > 1 && i2 <= 8) {
                                sb.append("&axle_count=");
                                sb.append(f6987E);
                                if (f6988F) {
                                    sb.append("&is_trailer=");
                                    sb.append(1);
                                } else {
                                    sb.append("&is_trailer=");
                                    sb.append(0);
                                }
                                if (!TextUtils.isEmpty(f6989G)) {
                                    if (f6989G.length() == 7 || f6989G.length() == 8) {
                                        sb.append("&plate_number=");
                                        sb.append(f6989G);
                                        int i3 = f6990H;
                                        if (i3 >= 0 && i3 <= 4) {
                                            sb.append("&plate_color=");
                                            sb.append(f6990H);
                                        }
                                        if (f6991I > 0) {
                                            sb.append("&displacement=");
                                            sb.append(f6991I);
                                        }
                                        int i4 = f6992J;
                                        if (i4 > 0 && i4 <= 4) {
                                            sb.append("&power_type=");
                                            sb.append(f6992J);
                                            int i5 = f6993K;
                                            if (i5 >= 0 && i5 <= 6) {
                                                sb.append("&emission_limit=");
                                                sb.append(f6993K);
                                                int i6 = f6994L;
                                                if (i6 > 0 && i6 <= 100) {
                                                    sb.append("&load_weight=");
                                                    sb.append(f6994L);
                                                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
                                                    intent.setFlags(268435456);
                                                    context.startActivity(intent);
                                                    return;
                                                }
                                                throw new IllegalArgumentException("BDMapSDKException: loadWeight Must be between 1 and 100");
                                            }
                                            throw new IllegalArgumentException("BDMapSDKException: emissionLimit Must be between 0 and 6");
                                        }
                                        throw new IllegalArgumentException("BDMapSDKException: powerType Must be between 1 and 4");
                                    }
                                    throw new IllegalArgumentException("BDMapSDKException: plateNumber error Please fill in the correct license plate");
                                }
                                throw new IllegalArgumentException("BDMapSDKException: plateNumber Can not be null");
                            }
                            throw new IllegalArgumentException("BDMapSDKException: axleCount Must be between 2 and 8");
                        }
                        throw new IllegalArgumentException("BDMapSDKException: length Must be between 1 and 25");
                    }
                    throw new IllegalArgumentException("BDMapSDKException: weight Must be between 1 and 100");
                }
                throw new IllegalArgumentException("BDMapSDKException: width Must be between 1 and 5");
            }
            throw new IllegalArgumentException("BDMapSDKException: height Must be between 1 and 10");
        }
        throw new IllegalArgumentException("BDMapSDKException: truckType Must be between 1 and 4");
    }

    /* renamed from: h */
    private static void m18582h(Context context) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/walknavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7005k = CoordTrans.gcjToBaidu(f7005k);
            f7006l = CoordTrans.gcjToBaidu(f7006l);
        }
        if (f7005k == null || f7006l == null) {
            return;
        }
        sb.append("origin=");
        sb.append(f7005k.latitude);
        sb.append(",");
        sb.append(f7005k.longitude);
        sb.append("&destination=");
        sb.append(f7006l.latitude);
        sb.append(",");
        sb.append(f7006l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: i */
    private static void m18580i(Context context) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/bikenavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7005k = CoordTrans.gcjToBaidu(f7005k);
            f7006l = CoordTrans.gcjToBaidu(f7006l);
        }
        if (f7005k == null || f7006l == null) {
            return;
        }
        sb.append("origin=");
        sb.append(f7005k.latitude);
        sb.append(",");
        sb.append(f7005k.longitude);
        sb.append("&destination=");
        sb.append(f7006l.latitude);
        sb.append(",");
        sb.append(f7006l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: j */
    private static void m18578j(Context context) {
        Thread thread = f7018x;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/walknavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            f7005k = CoordTrans.gcjToBaidu(f7005k);
            f7006l = CoordTrans.gcjToBaidu(f7006l);
        }
        if (f7005k == null || f7006l == null) {
            return;
        }
        sb.append("origin=");
        sb.append(f7005k.latitude);
        sb.append(",");
        sb.append(f7005k.longitude);
        sb.append("&destination=");
        sb.append(f7006l.latitude);
        sb.append(",");
        sb.append(f7006l.longitude);
        sb.append("&mode=");
        sb.append("walking_ar");
        sb.append("&src=");
        sb.append("sdk_[" + f7001g + "]");
        Log.e("test", sb.toString());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: g */
    private static boolean m18585g() {
        String mo18566a;
        try {
            Log.d(f6997c, "callDispatchTakeOutRoute");
            mo18566a = f6999e.mo18566a("map.android.baidu.mainmap");
        } catch (RemoteException e) {
            Log.d(f6997c, "callDispatchTakeOut exception", e);
        }
        if (mo18566a != null) {
            Bundle bundle = new Bundle();
            bundle.putString("target", "route_search_page");
            Bundle bundle2 = new Bundle();
            if (f7000f == 102) {
                f7000f = 7;
            }
            bundle2.putInt("route_type", f7000f);
            bundle2.putInt("bus_strategy", f7011q.ordinal());
            bundle2.putInt("cross_city_bus_strategy", 5);
            if (f7005k != null) {
                bundle2.putInt("start_type", 1);
                bundle2.putInt("start_longitude", (int) CoordUtil.ll2mc(f7005k).getLongitudeE6());
                bundle2.putInt("start_latitude", (int) CoordUtil.ll2mc(f7005k).getLatitudeE6());
            } else {
                bundle2.putInt("start_type", 2);
                bundle2.putInt("start_longitude", 0);
                bundle2.putInt("start_latitude", 0);
            }
            if (f7007m != null) {
                bundle2.putString("start_keyword", f7007m);
            } else {
                bundle2.putString("start_keyword", "地图上的点");
            }
            bundle2.putString("start_uid", "");
            if (f7006l != null) {
                bundle2.putInt("end_type", 1);
                bundle2.putInt("end_longitude", (int) CoordUtil.ll2mc(f7006l).getLongitudeE6());
                bundle2.putInt("end_latitude", (int) CoordUtil.ll2mc(f7006l).getLatitudeE6());
            } else {
                bundle2.putInt("end_type", 2);
                bundle2.putInt("end_longitude", 0);
                bundle2.putInt("end_latitude", 0);
            }
            if (f7008n != null) {
                bundle2.putString("end_keyword", f7008n);
            } else {
                bundle2.putString("end_keyword", "地图上的点");
            }
            bundle2.putString("end_uid", "");
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f7001g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f6999e.mo18565a("map.android.baidu.mainmap", mo18566a, bundle);
        }
        Log.d(f6997c, "callDispatchTakeOut com not found");
        return false;
    }

    /* renamed from: h */
    private static boolean m18583h() {
        try {
            Log.d(f6997c, "callDispatchTakeOutPoiDetials");
            String mo18566a = f6999e.mo18566a("map.android.baidu.mainmap");
            if (mo18566a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "request_poi_detail_page");
                Bundle bundle2 = new Bundle();
                if (f7012r != null) {
                    bundle2.putString("uid", f7012r);
                } else {
                    bundle2.putString("uid", "");
                }
                bundle.putBundle("base_params", bundle2);
                Bundle bundle3 = new Bundle();
                bundle3.putString("launch_from", "sdk_[" + f7001g + "]");
                bundle.putBundle("ext_params", bundle3);
                return f6999e.mo18565a("map.android.baidu.mainmap", mo18566a, bundle);
            }
            Log.d(f6997c, "callDispatchTakeOut com not found");
            return false;
        } catch (RemoteException e) {
            Log.d(f6997c, "callDispatchTakeOut exception", e);
            return false;
        }
    }

    /* renamed from: i */
    private static boolean m18581i() {
        String mo18566a;
        List<DispathcPoiData> list = f7004j;
        if (list == null || list.size() <= 0) {
            return false;
        }
        try {
            Log.d(f6997c, "callDispatchPoiToBaiduMap");
            mo18566a = f6999e.mo18566a("map.android.baidu.mainmap");
        } catch (RemoteException e) {
            Log.d(f6997c, "callDispatchPoiToBaiduMap exception", e);
        }
        if (mo18566a != null) {
            Bundle bundle = new Bundle();
            bundle.putString("target", "favorite_page");
            Bundle bundle2 = new Bundle();
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            for (int i2 = 0; i2 < f7004j.size(); i2++) {
                if (f7004j.get(i2).name != null && !f7004j.get(i2).name.equals("") && f7004j.get(i2).f7026pt != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("name", f7004j.get(i2).name);
                        GeoPoint ll2mc = CoordUtil.ll2mc(f7004j.get(i2).f7026pt);
                        jSONObject.put("ptx", ll2mc.getLongitudeE6());
                        jSONObject.put("pty", ll2mc.getLatitudeE6());
                        jSONObject.put("addr", f7004j.get(i2).addr);
                        jSONObject.put("uid", f7004j.get(i2).uid);
                        i++;
                        jSONArray.put(jSONObject);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (i == 0) {
                return false;
            }
            bundle2.putString("data", !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
            bundle2.putString("from", f7002h);
            bundle2.putString("pkg", f7001g);
            bundle2.putString("cls", f7003i);
            bundle2.putInt("count", i);
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f7001g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f6999e.mo18565a("map.android.baidu.mainmap", mo18566a, bundle);
        }
        Log.d(f6997c, "callDispatchPoiToBaiduMap com not found");
        return false;
    }

    /* renamed from: j */
    private static boolean m18579j() {
        String mo18566a;
        try {
            Log.d(f6997c, "callDispatchTakeOutRouteNavi");
            mo18566a = f6999e.mo18566a("map.android.baidu.mainmap");
        } catch (RemoteException e) {
            Log.d(f6997c, "callDispatchTakeOut exception", e);
        }
        if (mo18566a != null) {
            Bundle bundle = new Bundle();
            bundle.putString("target", "navigation_page");
            Bundle bundle2 = new Bundle();
            bundle2.putString("coord_type", "bd09ll");
            StringBuffer stringBuffer = new StringBuffer();
            if (f7007m != null) {
                stringBuffer.append("name:" + f7007m + "|");
            }
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                f7005k = CoordTrans.gcjToBaidu(f7005k);
            }
            stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(f7005k.latitude), Double.valueOf(f7005k.longitude)));
            StringBuffer stringBuffer2 = new StringBuffer();
            if (f7008n != null) {
                stringBuffer2.append("name:" + f7008n + "|");
            }
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                f7006l = CoordTrans.gcjToBaidu(f7006l);
            }
            stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(f7006l.latitude), Double.valueOf(f7006l.longitude)));
            bundle2.putString("origin", stringBuffer.toString());
            bundle2.putString("destination", stringBuffer2.toString());
            if (!TextUtils.isEmpty(f7009o)) {
                bundle2.putString("viaPoints", f7009o);
            }
            if (!TextUtils.isEmpty(f7010p)) {
                bundle2.putString("type", f7010p);
            }
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f7001g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f6999e.mo18565a("map.android.baidu.mainmap", mo18566a, bundle);
        }
        Log.d(f6997c, "callDispatchTakeOut com not found");
        return false;
    }

    /* renamed from: k */
    private static boolean m18577k() {
        String mo18566a;
        try {
            Log.d(f6997c, "callDispatchTakeOutRouteNavi");
            mo18566a = f6999e.mo18566a("map.android.baidu.mainmap");
        } catch (Exception e) {
            Log.d(f6997c, "callDispatchTakeOut exception", e);
        }
        if (mo18566a != null) {
            Bundle bundle = new Bundle();
            bundle.putString("target", "walknavi_page");
            Bundle bundle2 = new Bundle();
            bundle2.putString("coord_type", "bd09ll");
            StringBuffer stringBuffer = new StringBuffer();
            if (f7007m != null) {
                stringBuffer.append("name:" + f7007m + "|");
            }
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                f7005k = CoordTrans.gcjToBaidu(f7005k);
            }
            stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(f7005k.latitude), Double.valueOf(f7005k.longitude)));
            StringBuffer stringBuffer2 = new StringBuffer();
            if (f7008n != null) {
                stringBuffer2.append("name:" + f7008n + "|");
            }
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                f7006l = CoordTrans.gcjToBaidu(f7006l);
            }
            stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(f7006l.latitude), Double.valueOf(f7006l.longitude)));
            bundle2.putString("origin", stringBuffer.toString());
            bundle2.putString("destination", stringBuffer2.toString());
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f7001g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f6999e.mo18565a("map.android.baidu.mainmap", mo18566a, bundle);
        }
        Log.d(f6997c, "callDispatchTakeOut com not found");
        return false;
    }

    /* renamed from: l */
    private static boolean m18576l() {
        String mo18566a;
        try {
            Log.d(f6997c, "callDispatchTakeOutRouteRidingNavi");
            mo18566a = f6999e.mo18566a("map.android.baidu.mainmap");
        } catch (RemoteException e) {
            Log.d(f6997c, "callDispatchTakeOut exception", e);
        }
        if (mo18566a != null) {
            Bundle bundle = new Bundle();
            bundle.putString("target", "bikenavi_page");
            Bundle bundle2 = new Bundle();
            bundle2.putString("coord_type", "bd09ll");
            StringBuffer stringBuffer = new StringBuffer();
            if (f7007m != null) {
                stringBuffer.append("name:" + f7007m + "|");
            }
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                f7005k = CoordTrans.gcjToBaidu(f7005k);
            }
            stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(f7005k.latitude), Double.valueOf(f7005k.longitude)));
            StringBuffer stringBuffer2 = new StringBuffer();
            if (f7008n != null) {
                stringBuffer2.append("name:" + f7008n + "|");
            }
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                f7006l = CoordTrans.gcjToBaidu(f7006l);
            }
            stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(f7006l.latitude), Double.valueOf(f7006l.longitude)));
            bundle2.putString("origin", stringBuffer.toString());
            bundle2.putString("destination", stringBuffer2.toString());
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f7001g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f6999e.mo18565a("map.android.baidu.mainmap", mo18566a, bundle);
        }
        Log.d(f6997c, "callDispatchTakeOut com not found");
        return false;
    }

    /* renamed from: m */
    private static boolean m18575m() {
        try {
            Log.d(f6997c, "callDispatchTakeOutPoiNearbySearch");
            String mo18566a = f6999e.mo18566a("map.android.baidu.mainmap");
            if (mo18566a != null) {
                Bundle bundle = new Bundle();
                bundle.putString("target", "poi_search_page");
                Bundle bundle2 = new Bundle();
                if (f7013s != null) {
                    bundle2.putString("search_key", f7013s);
                } else {
                    bundle2.putString("search_key", "");
                }
                if (f7014t != null) {
                    bundle2.putInt("center_pt_x", (int) CoordUtil.ll2mc(f7014t).getLongitudeE6());
                    bundle2.putInt("center_pt_y", (int) CoordUtil.ll2mc(f7014t).getLatitudeE6());
                } else {
                    bundle2.putString("search_key", "");
                }
                if (f7015u != 0) {
                    bundle2.putInt("search_radius", f7015u);
                } else {
                    bundle2.putInt("search_radius", 1000);
                }
                bundle2.putBoolean("is_direct_search", true);
                bundle2.putBoolean("is_direct_area_search", true);
                bundle.putBundle("base_params", bundle2);
                Bundle bundle3 = new Bundle();
                bundle3.putString("launch_from", "sdk_[" + f7001g + "]");
                bundle.putBundle("ext_params", bundle3);
                return f6999e.mo18565a("map.android.baidu.mainmap", mo18566a, bundle);
            }
            Log.d(f6997c, "callDispatchTakeOut com not found");
            return false;
        } catch (RemoteException e) {
            Log.d(f6997c, "callDispatchTakeOut exception", e);
            return false;
        }
    }

    /* renamed from: b */
    private static void m18596b(RouteParaOption routeParaOption, Context context, int i) {
        f7007m = null;
        f7005k = null;
        f7008n = null;
        f7006l = null;
        f7001g = context.getPackageName();
        if (routeParaOption.getStartPoint() != null) {
            f7005k = routeParaOption.getStartPoint();
        }
        if (routeParaOption.getEndPoint() != null) {
            f7006l = routeParaOption.getEndPoint();
        }
        if (routeParaOption.getStartName() != null) {
            f7007m = routeParaOption.getStartName();
        }
        if (routeParaOption.getEndName() != null) {
            f7008n = routeParaOption.getEndName();
        }
        if (routeParaOption.getBusStrategyType() != null) {
            f7011q = routeParaOption.getBusStrategyType();
        }
        switch (i) {
            case 0:
                f7000f = 0;
                return;
            case 1:
                f7000f = 1;
                return;
            case 2:
                f7000f = 2;
                return;
            default:
                switch (i) {
                    case 101:
                        f7000f = 101;
                        return;
                    case 102:
                        f7000f = 102;
                        return;
                    default:
                        return;
                }
        }
    }

    /* renamed from: b */
    private static void m18597b(PoiParaOption poiParaOption, Context context, int i) {
        f7012r = null;
        f7013s = null;
        f7014t = null;
        f7015u = 0;
        f7001g = context.getPackageName();
        if (poiParaOption.getUid() != null) {
            f7012r = poiParaOption.getUid();
        }
        if (poiParaOption.getKey() != null) {
            f7013s = poiParaOption.getKey();
        }
        if (poiParaOption.getCenter() != null) {
            f7014t = poiParaOption.getCenter();
        }
        if (poiParaOption.getRadius() != 0) {
            f7015u = poiParaOption.getRadius();
        }
    }

    /* renamed from: b */
    private static void m18598b(NaviParaOption naviParaOption, Context context, int i) {
        f7001g = context.getPackageName();
        f7007m = null;
        f7005k = null;
        f7008n = null;
        f7006l = null;
        f7009o = null;
        if (naviParaOption.getStartPoint() != null) {
            f7005k = naviParaOption.getStartPoint();
        }
        if (naviParaOption.getEndPoint() != null) {
            f7006l = naviParaOption.getEndPoint();
        }
        if (naviParaOption.getStartName() != null) {
            f7007m = naviParaOption.getStartName();
        }
        if (naviParaOption.getEndName() != null) {
            f7008n = naviParaOption.getEndName();
        }
        if (naviParaOption.getNaviRoutePolicy() != null) {
            f7010p = naviParaOption.getNaviRoutePolicy();
        }
        JSONArray wayPoint = naviParaOption.getWayPoint();
        if (wayPoint != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("viaPoints", wayPoint);
                try {
                    f7009o = URLEncoder.encode(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (naviParaOption instanceof TruckNaviOption) {
            m18609a((TruckNaviOption) naviParaOption);
        }
    }

    /* renamed from: a */
    private static void m18609a(TruckNaviOption truckNaviOption) {
        f7019y = 0;
        f7020z = 0.0d;
        f6983A = 0.0d;
        f6984B = 0.0d;
        f6985C = 0.0d;
        f6986D = 0.0d;
        f6987E = 0;
        f6988F = false;
        f6989G = null;
        f6990H = 0;
        f6991I = 0;
        f6992J = 0;
        f6993K = 0;
        f6994L = 0;
        if (truckNaviOption.getNaviRoutePolicy() != null) {
            f7010p = truckNaviOption.getNaviRoutePolicy();
        }
        JSONArray wayPoint = truckNaviOption.getWayPoint();
        if (wayPoint != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("viaPoints", wayPoint);
                try {
                    f7009o = URLEncoder.encode(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        f7019y = truckNaviOption.getTruckType();
        f7020z = truckNaviOption.getHeight();
        f6983A = truckNaviOption.getWidth();
        f6984B = truckNaviOption.getWeight();
        f6985C = truckNaviOption.getLength();
        f6986D = truckNaviOption.getAxleWeight();
        f6987E = truckNaviOption.getAxleCount();
        f6988F = truckNaviOption.getIsTrailer();
        f6989G = truckNaviOption.getPlateNumber();
        f6990H = truckNaviOption.getPlateColor();
        f6991I = truckNaviOption.getDisplacement();
        f6992J = truckNaviOption.getPowerType();
        f6993K = truckNaviOption.getEmissionLimit();
        f6994L = truckNaviOption.getLoadWeight();
    }

    /* renamed from: a */
    private static void m18604a(List<DispathcPoiData> list, Context context) {
        f7001g = context.getPackageName();
        f7002h = m18600b(context);
        f7003i = "";
        List<DispathcPoiData> list2 = f7004j;
        if (list2 != null) {
            list2.clear();
        }
        for (DispathcPoiData dispathcPoiData : list) {
            f7004j.add(dispathcPoiData);
        }
    }

    /* renamed from: b */
    public static String m18600b(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
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
