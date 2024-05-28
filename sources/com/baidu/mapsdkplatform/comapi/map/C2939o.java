package com.baidu.mapsdkplatform.comapi.map;

import android.os.Handler;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2939o {

    /* renamed from: a */
    private static final String f7331a = "o";

    /* renamed from: c */
    private static C2939o f7332c;

    /* renamed from: b */
    private AppBaseMap f7333b;

    /* renamed from: d */
    private C2944t f7334d;

    /* renamed from: e */
    private Handler f7335e;

    private C2939o() {
    }

    /* renamed from: a */
    public static C2939o m18224a() {
        if (f7332c == null) {
            f7332c = new C2939o();
            f7332c.m18207g();
        }
        return f7332c;
    }

    /* renamed from: g */
    private void m18207g() {
        m18205h();
        this.f7334d = new C2944t();
        this.f7335e = new HandlerC2940p(this);
        MessageCenter.registMessage(65289, this.f7335e);
    }

    /* renamed from: h */
    private void m18205h() {
        EnvironmentUtilities.initAppDirectory(BMapManager.getContext());
        this.f7333b = new AppBaseMap();
        this.f7333b.Create();
        String moduleFileName = SysOSUtil.getModuleFileName();
        String appSDCardPath = EnvironmentUtilities.getAppSDCardPath();
        String appCachePath = EnvironmentUtilities.getAppCachePath();
        String appSecondCachePath = EnvironmentUtilities.getAppSecondCachePath();
        int mapTmpStgMax = EnvironmentUtilities.getMapTmpStgMax();
        int domTmpStgMax = EnvironmentUtilities.getDomTmpStgMax();
        int itsTmpStgMax = EnvironmentUtilities.getItsTmpStgMax();
        int ssgTmpStgMax = EnvironmentUtilities.getSsgTmpStgMax();
        String str = com.baidu.platform.comapi.util.SysOSUtil.getInstance().getDensityDPI() >= 180 ? "/h/" : "/l/";
        String str2 = moduleFileName + "/cfg";
        String str3 = appSDCardPath + "/vmp";
        String str4 = str2 + "/idrres/";
        String str5 = str3 + str;
        String str6 = str3 + str;
        String str7 = appCachePath + "/tmp/";
        this.f7333b.Init(str2 + "/a/", str4, str5, str7, appSecondCachePath + "/tmp/", str6, str2 + "/a/", com.baidu.platform.comapi.util.SysOSUtil.getInstance().getScreenWidth(), com.baidu.platform.comapi.util.SysOSUtil.getInstance().getScreenHeight(), com.baidu.platform.comapi.util.SysOSUtil.getInstance().getDensityDPI(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, ssgTmpStgMax, false, false);
        this.f7333b.OnResume();
    }

    /* renamed from: a */
    public ArrayList<C2938n> m18220a(String str) {
        AppBaseMap appBaseMap;
        String OnSchcityGet;
        JSONArray optJSONArray;
        if (!str.equals("") && (appBaseMap = this.f7333b) != null && (OnSchcityGet = appBaseMap.OnSchcityGet(str)) != null && !OnSchcityGet.equals("")) {
            ArrayList<C2938n> arrayList = new ArrayList<>();
            try {
                JSONObject jSONObject = new JSONObject(OnSchcityGet);
                if (jSONObject.length() == 0 || (optJSONArray = jSONObject.optJSONArray("dataset")) == null) {
                    return null;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    C2938n c2938n = new C2938n();
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    int optInt = jSONObject2.optInt("id");
                    if (optInt <= 2000 || optInt == 2912 || optInt == 2911 || optInt == 9000) {
                        c2938n.f7326a = optInt;
                        c2938n.f7327b = jSONObject2.optString("name");
                        c2938n.f7328c = jSONObject2.optInt("mapsize");
                        c2938n.f7329d = jSONObject2.optInt("cty");
                        if (jSONObject2.has("child")) {
                            JSONArray optJSONArray2 = jSONObject2.optJSONArray("child");
                            ArrayList<C2938n> arrayList2 = new ArrayList<>();
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                C2938n c2938n2 = new C2938n();
                                JSONObject optJSONObject = optJSONArray2.optJSONObject(i2);
                                c2938n2.f7326a = optJSONObject.optInt("id");
                                c2938n2.f7327b = optJSONObject.optString("name");
                                c2938n2.f7328c = optJSONObject.optInt("mapsize");
                                c2938n2.f7329d = optJSONObject.optInt("cty");
                                arrayList2.add(c2938n2);
                            }
                            c2938n.m18225a(arrayList2);
                        }
                        arrayList.add(c2938n);
                    }
                }
                return arrayList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m18221a(InterfaceC2943s interfaceC2943s) {
        C2944t c2944t = this.f7334d;
        if (c2944t != null) {
            c2944t.m18200a(interfaceC2943s);
        }
    }

    /* renamed from: a */
    public boolean m18223a(int i) {
        if (this.f7333b == null || i < 0) {
            return false;
        }
        if (i <= 2000 || i == 2912 || i == 2911 || i == 9000) {
            return this.f7333b.OnRecordAdd(i);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m18219a(boolean z, boolean z2) {
        AppBaseMap appBaseMap = this.f7333b;
        if (appBaseMap == null) {
            return false;
        }
        return appBaseMap.OnRecordImport(z, z2);
    }

    /* renamed from: b */
    public void m18218b() {
        MessageCenter.unregistMessage(65289, this.f7335e);
        this.f7333b.releaseFromOfflineMap();
        f7332c = null;
    }

    /* renamed from: b */
    public void m18216b(InterfaceC2943s interfaceC2943s) {
        C2944t c2944t = this.f7334d;
        if (c2944t != null) {
            c2944t.m18199b(interfaceC2943s);
        }
    }

    /* renamed from: b */
    public boolean m18217b(int i) {
        if (this.f7333b == null || i < 0) {
            return false;
        }
        if (i <= 2000 || i == 2912 || i == 2911 || i == 9000) {
            return this.f7333b.OnRecordStart(i, false, 0);
        }
        return false;
    }

    /* renamed from: c */
    public ArrayList<C2938n> m18215c() {
        AppBaseMap appBaseMap = this.f7333b;
        if (appBaseMap == null) {
            return null;
        }
        String OnHotcityGet = appBaseMap.OnHotcityGet();
        ArrayList<C2938n> arrayList = new ArrayList<>();
        try {
            JSONArray optJSONArray = new JSONObject(OnHotcityGet).optJSONArray("dataset");
            if (optJSONArray == null) {
                return null;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                C2938n c2938n = new C2938n();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                c2938n.f7326a = optJSONObject.optInt("id");
                c2938n.f7327b = optJSONObject.optString("name");
                c2938n.f7328c = optJSONObject.optInt("mapsize");
                c2938n.f7329d = optJSONObject.optInt("cty");
                if (optJSONObject.has("child")) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("child");
                    ArrayList<C2938n> arrayList2 = new ArrayList<>();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        C2938n c2938n2 = new C2938n();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        c2938n2.f7326a = optJSONObject2.optInt("id");
                        c2938n2.f7327b = optJSONObject2.optString("name");
                        c2938n2.f7328c = optJSONObject2.optInt("mapsize");
                        c2938n2.f7329d = optJSONObject2.optInt("cty");
                        arrayList2.add(c2938n2);
                    }
                    c2938n.m18225a(arrayList2);
                }
                arrayList.add(c2938n);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public boolean m18214c(int i) {
        AppBaseMap appBaseMap = this.f7333b;
        if (appBaseMap == null || i < 0) {
            return false;
        }
        return appBaseMap.OnRecordSuspend(i, false, 0);
    }

    /* renamed from: d */
    public ArrayList<C2938n> m18213d() {
        AppBaseMap appBaseMap = this.f7333b;
        if (appBaseMap == null) {
            return null;
        }
        String OnSchcityGet = appBaseMap.OnSchcityGet("");
        ArrayList<C2938n> arrayList = new ArrayList<>();
        try {
            JSONArray optJSONArray = new JSONObject(OnSchcityGet).optJSONArray("dataset");
            for (int i = 0; i < optJSONArray.length(); i++) {
                C2938n c2938n = new C2938n();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                int optInt = optJSONObject.optInt("id");
                if (optInt <= 2000 || optInt == 2912 || optInt == 2911 || optInt == 9000) {
                    c2938n.f7326a = optInt;
                    c2938n.f7327b = optJSONObject.optString("name");
                    c2938n.f7328c = optJSONObject.optInt("mapsize");
                    c2938n.f7329d = optJSONObject.optInt("cty");
                    if (optJSONObject.has("child")) {
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("child");
                        ArrayList<C2938n> arrayList2 = new ArrayList<>();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            C2938n c2938n2 = new C2938n();
                            JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                            c2938n2.f7326a = optJSONObject2.optInt("id");
                            c2938n2.f7327b = optJSONObject2.optString("name");
                            c2938n2.f7328c = optJSONObject2.optInt("mapsize");
                            c2938n2.f7329d = optJSONObject2.optInt("cty");
                            arrayList2.add(c2938n2);
                        }
                        c2938n.m18225a(arrayList2);
                    }
                    arrayList.add(c2938n);
                }
            }
            return arrayList;
        } catch (JSONException | Exception unused) {
            return null;
        }
    }

    /* renamed from: d */
    public boolean m18212d(int i) {
        AppBaseMap appBaseMap = this.f7333b;
        if (appBaseMap == null) {
            return false;
        }
        return appBaseMap.OnRecordSuspend(0, true, i);
    }

    /* renamed from: e */
    public ArrayList<C2942r> m18211e() {
        String OnRecordGetAll;
        AppBaseMap appBaseMap = this.f7333b;
        if (appBaseMap != null && (OnRecordGetAll = appBaseMap.OnRecordGetAll()) != null && !OnRecordGetAll.equals("")) {
            ArrayList<C2942r> arrayList = new ArrayList<>();
            try {
                JSONObject jSONObject = new JSONObject(OnRecordGetAll);
                if (jSONObject.length() == 0) {
                    return null;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("dataset");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    C2942r c2942r = new C2942r();
                    C2941q c2941q = new C2941q();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    c2941q.f7337a = optJSONObject.optInt("id");
                    c2941q.f7338b = optJSONObject.optString("name");
                    c2941q.f7339c = optJSONObject.optString("pinyin");
                    c2941q.f7344h = optJSONObject.optInt("mapoldsize");
                    c2941q.f7345i = optJSONObject.optInt("ratio");
                    c2941q.f7348l = optJSONObject.optInt("status");
                    c2941q.f7343g = new GeoPoint(optJSONObject.optInt("y"), optJSONObject.optInt("x"));
                    boolean z = true;
                    if (optJSONObject.optInt("up") != 1) {
                        z = false;
                    }
                    c2941q.f7346j = z;
                    c2941q.f7341e = optJSONObject.optInt("lev");
                    if (c2941q.f7346j) {
                        c2941q.f7347k = optJSONObject.optInt("mapsize");
                    } else {
                        c2941q.f7347k = 0;
                    }
                    c2942r.m18203a(c2941q);
                    arrayList.add(c2942r);
                }
                return arrayList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: e */
    public boolean m18210e(int i) {
        AppBaseMap appBaseMap = this.f7333b;
        if (appBaseMap == null || i < 0) {
            return false;
        }
        return appBaseMap.OnRecordRemove(i, false);
    }

    /* renamed from: f */
    public boolean m18208f(int i) {
        if (this.f7333b == null || i < 0) {
            return false;
        }
        if (i <= 2000 || i == 2912 || i == 2911 || i == 9000) {
            return this.f7333b.OnRecordReload(i, false);
        }
        return false;
    }

    /* renamed from: g */
    public C2942r m18206g(int i) {
        String OnRecordGetAt;
        AppBaseMap appBaseMap = this.f7333b;
        if (appBaseMap != null && i >= 0 && (OnRecordGetAt = appBaseMap.OnRecordGetAt(i)) != null && !OnRecordGetAt.equals("")) {
            C2942r c2942r = new C2942r();
            C2941q c2941q = new C2941q();
            try {
                JSONObject jSONObject = new JSONObject(OnRecordGetAt);
                if (jSONObject.length() == 0) {
                    return null;
                }
                int optInt = jSONObject.optInt("id");
                if (optInt <= 2000 || optInt == 2912 || optInt == 2911 || optInt == 9000) {
                    c2941q.f7337a = optInt;
                    c2941q.f7338b = jSONObject.optString("name");
                    c2941q.f7339c = jSONObject.optString("pinyin");
                    c2941q.f7340d = jSONObject.optString("headchar");
                    c2941q.f7344h = jSONObject.optInt("mapoldsize");
                    c2941q.f7345i = jSONObject.optInt("ratio");
                    c2941q.f7348l = jSONObject.optInt("status");
                    c2941q.f7343g = new GeoPoint(jSONObject.optInt("y"), jSONObject.optInt("x"));
                    boolean z = true;
                    if (jSONObject.optInt("up") != 1) {
                        z = false;
                    }
                    c2941q.f7346j = z;
                    c2941q.f7341e = jSONObject.optInt("lev");
                    if (c2941q.f7346j) {
                        c2941q.f7347k = jSONObject.optInt("mapsize");
                    } else {
                        c2941q.f7347k = 0;
                    }
                    c2941q.f7342f = jSONObject.optInt("ver");
                    c2942r.m18203a(c2941q);
                    return c2942r;
                }
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
