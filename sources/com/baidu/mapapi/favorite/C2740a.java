package com.baidu.mapapi.favorite;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.favrite.FavSyncPoi;
import com.baidu.platform.comapi.basestruct.Point;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.favorite.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2740a {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static FavoritePoiInfo m19033a(FavSyncPoi favSyncPoi) {
        if (favSyncPoi == null || favSyncPoi.f7184c == null || favSyncPoi.f7183b.equals("")) {
            return null;
        }
        FavoritePoiInfo favoritePoiInfo = new FavoritePoiInfo();
        favoritePoiInfo.f5866a = favSyncPoi.f7182a;
        favoritePoiInfo.f5867b = favSyncPoi.f7183b;
        favoritePoiInfo.f5868c = new LatLng(favSyncPoi.f7184c.f7537y / 1000000.0d, favSyncPoi.f7184c.f7536x / 1000000.0d);
        favoritePoiInfo.f5870e = favSyncPoi.f7186e;
        favoritePoiInfo.f5871f = favSyncPoi.f7187f;
        favoritePoiInfo.f5869d = favSyncPoi.f7185d;
        favoritePoiInfo.f5872g = Long.parseLong(favSyncPoi.f7189h);
        return favoritePoiInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static FavoritePoiInfo m19032a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FavoritePoiInfo favoritePoiInfo = new FavoritePoiInfo();
        JSONObject optJSONObject = jSONObject.optJSONObject("pt");
        if (optJSONObject != null) {
            favoritePoiInfo.f5868c = new LatLng(optJSONObject.optInt("y") / 1000000.0d, optJSONObject.optInt("x") / 1000000.0d);
        }
        favoritePoiInfo.f5867b = jSONObject.optString("uspoiname");
        favoritePoiInfo.f5872g = Long.parseLong(jSONObject.optString("addtimesec"));
        favoritePoiInfo.f5869d = jSONObject.optString("addr");
        favoritePoiInfo.f5871f = jSONObject.optString("uspoiuid");
        favoritePoiInfo.f5870e = jSONObject.optString("ncityid");
        favoritePoiInfo.f5866a = jSONObject.optString("key");
        return favoritePoiInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static FavSyncPoi m19034a(FavoritePoiInfo favoritePoiInfo) {
        if (favoritePoiInfo == null || favoritePoiInfo.f5868c == null || favoritePoiInfo.f5867b == null || favoritePoiInfo.f5867b.equals("")) {
            return null;
        }
        FavSyncPoi favSyncPoi = new FavSyncPoi();
        favSyncPoi.f7183b = favoritePoiInfo.f5867b;
        favSyncPoi.f7184c = new Point((int) (favoritePoiInfo.f5868c.longitude * 1000000.0d), (int) (favoritePoiInfo.f5868c.latitude * 1000000.0d));
        favSyncPoi.f7185d = favoritePoiInfo.f5869d;
        favSyncPoi.f7186e = favoritePoiInfo.f5870e;
        favSyncPoi.f7187f = favoritePoiInfo.f5871f;
        favSyncPoi.f7190i = false;
        return favSyncPoi;
    }
}
