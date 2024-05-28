package com.baidu.mapapi.navi;

import android.text.TextUtils;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NaviParaOption {

    /* renamed from: a */
    LatLng f6611a;

    /* renamed from: b */
    String f6612b;

    /* renamed from: c */
    LatLng f6613c;

    /* renamed from: d */
    String f6614d;

    /* renamed from: e */
    WayPoint f6615e;

    /* renamed from: f */
    NaviRoutePolicy f6616f = NaviRoutePolicy.DEFAULT;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum NaviRoutePolicy {
        BLK,
        TIME,
        DIS,
        FEE,
        HIGHWAY,
        DEFAULT
    }

    public NaviParaOption setNaviRoutePolicy(NaviRoutePolicy naviRoutePolicy) {
        this.f6616f = naviRoutePolicy;
        return this;
    }

    public String getNaviRoutePolicy() {
        switch (this.f6616f) {
            case BLK:
                return "BLK";
            case TIME:
                return "TIME";
            case DIS:
                return "DIS";
            case FEE:
                return "FEE";
            case HIGHWAY:
                return "HIGHWAY";
            default:
                return "DEFAULT";
        }
    }

    public NaviParaOption setWayPoint(WayPoint wayPoint) {
        if (wayPoint == null) {
            return null;
        }
        this.f6615e = wayPoint;
        return this;
    }

    public JSONArray getWayPoint() {
        List<WayPointInfo> viaPoints;
        WayPoint wayPoint = this.f6615e;
        if (wayPoint == null || (viaPoints = wayPoint.getViaPoints()) == null || viaPoints.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < viaPoints.size(); i++) {
            WayPointInfo wayPointInfo = viaPoints.get(i);
            if (wayPointInfo != null) {
                try {
                    if (!TextUtils.isEmpty(wayPointInfo.getWayPointName())) {
                        jSONObject.put("name", wayPointInfo.getWayPointName());
                    }
                    LatLng latLng = wayPointInfo.getLatLng();
                    if (latLng != null) {
                        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                            latLng = CoordTrans.gcjToBaidu(latLng);
                        }
                        jSONObject.put("lng", latLng.longitude);
                        jSONObject.put("lat", latLng.latitude);
                        jSONArray.put(jSONObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    public NaviParaOption startPoint(LatLng latLng) {
        this.f6611a = latLng;
        return this;
    }

    public NaviParaOption startName(String str) {
        this.f6612b = str;
        return this;
    }

    public NaviParaOption endPoint(LatLng latLng) {
        this.f6613c = latLng;
        return this;
    }

    public NaviParaOption endName(String str) {
        this.f6614d = str;
        return this;
    }

    public LatLng getStartPoint() {
        return this.f6611a;
    }

    public LatLng getEndPoint() {
        return this.f6613c;
    }

    public String getStartName() {
        return this.f6612b;
    }

    public String getEndName() {
        return this.f6614d;
    }
}
