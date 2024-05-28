package com.baidu.mapapi.favorite;

import android.util.Log;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapsdkplatform.comapi.favrite.C2906a;
import com.baidu.mapsdkplatform.comapi.favrite.FavSyncPoi;
import com.baidu.mapsdkplatform.comapi.map.C2934j;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FavoriteManager {

    /* renamed from: a */
    private static FavoriteManager f5864a;

    /* renamed from: b */
    private static C2906a f5865b;

    private FavoriteManager() {
    }

    public static FavoriteManager getInstance() {
        if (f5864a == null) {
            f5864a = new FavoriteManager();
        }
        return f5864a;
    }

    public int add(FavoritePoiInfo favoritePoiInfo) {
        String str;
        String str2;
        if (f5865b == null) {
            str = "baidumapsdk";
            str2 = "you may have not call init method!";
        } else if (favoritePoiInfo != null && favoritePoiInfo.f5868c != null) {
            if (favoritePoiInfo.f5867b == null || favoritePoiInfo.f5867b.equals("")) {
                Log.e("baidumapsdk", "poiName can not be null or empty!");
                return -1;
            }
            FavSyncPoi m19034a = C2740a.m19034a(favoritePoiInfo);
            int m18448a = f5865b.m18448a(m19034a.f7183b, m19034a);
            if (m18448a == 1) {
                favoritePoiInfo.f5866a = m19034a.f7182a;
                favoritePoiInfo.f5872g = Long.parseLong(m19034a.f7189h);
            }
            return m18448a;
        } else {
            str = "baidumapsdk";
            str2 = "object or pt can not be null!";
        }
        Log.e(str, str2);
        return 0;
    }

    public boolean clearAllFavPois() {
        C2906a c2906a = f5865b;
        if (c2906a == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return false;
        }
        return c2906a.m18444c();
    }

    public boolean deleteFavPoi(String str) {
        if (f5865b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return false;
        } else if (str == null || str.equals("")) {
            return false;
        } else {
            return f5865b.m18449a(str);
        }
    }

    public void destroy() {
        C2906a c2906a = f5865b;
        if (c2906a != null) {
            c2906a.m18447b();
            f5865b = null;
            BMapManager.destroy();
            C2934j.m18234b();
        }
    }

    public List<FavoritePoiInfo> getAllFavPois() {
        JSONArray optJSONArray;
        C2906a c2906a = f5865b;
        if (c2906a == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return null;
        }
        String m18440f = c2906a.m18440f();
        if (m18440f != null && !m18440f.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(m18440f);
                if (jSONObject.optInt("favpoinum") != 0 && (optJSONArray = jSONObject.optJSONArray("favcontents")) != null && optJSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        if (jSONObject2 != null) {
                            arrayList.add(C2740a.m19032a(jSONObject2));
                        }
                    }
                    return arrayList;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public FavoritePoiInfo getFavPoi(String str) {
        FavSyncPoi m18446b;
        if (f5865b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return null;
        } else if (str == null || str.equals("") || (m18446b = f5865b.m18446b(str)) == null) {
            return null;
        } else {
            return C2740a.m19033a(m18446b);
        }
    }

    public void init() {
        if (f5865b == null) {
            C2934j.m18235a();
            BMapManager.init();
            f5865b = C2906a.m18450a();
        }
    }

    public boolean updateFavPoi(String str, FavoritePoiInfo favoritePoiInfo) {
        String str2;
        String str3;
        if (f5865b == null) {
            str2 = "baidumapsdk";
            str3 = "you may have not call init method!";
        } else if (str == null || str.equals("") || favoritePoiInfo == null) {
            return false;
        } else {
            if (favoritePoiInfo == null || favoritePoiInfo.f5868c == null) {
                str2 = "baidumapsdk";
                str3 = "object or pt can not be null!";
            } else if (favoritePoiInfo.f5867b != null && !favoritePoiInfo.f5867b.equals("")) {
                favoritePoiInfo.f5866a = str;
                return f5865b.m18445b(str, C2740a.m19034a(favoritePoiInfo));
            } else {
                str2 = "baidumapsdk";
                str3 = "poiName can not be null or empty!";
            }
        }
        Log.e(str2, str3);
        return false;
    }
}
