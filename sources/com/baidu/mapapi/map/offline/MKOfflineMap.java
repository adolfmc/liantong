package com.baidu.mapapi.map.offline;

import com.baidu.mapsdkplatform.comapi.map.C2934j;
import com.baidu.mapsdkplatform.comapi.map.C2938n;
import com.baidu.mapsdkplatform.comapi.map.C2939o;
import com.baidu.mapsdkplatform.comapi.map.C2942r;
import com.baidu.mapsdkplatform.comapi.map.InterfaceC2943s;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MKOfflineMap {
    public static final int TYPE_DOWNLOAD_UPDATE = 0;
    public static final int TYPE_NETWORK_ERROR = 2;
    public static final int TYPE_NEW_OFFLINE = 6;
    public static final int TYPE_VER_UPDATE = 4;

    /* renamed from: a */
    private static final String f6561a = "MKOfflineMap";

    /* renamed from: b */
    private C2939o f6562b;

    /* renamed from: c */
    private MKOfflineMapListener f6563c;

    public void destroy() {
        this.f6562b.m18212d(0);
        this.f6562b.m18216b((InterfaceC2943s) null);
        this.f6562b.m18218b();
        C2934j.m18234b();
    }

    public ArrayList<MKOLUpdateElement> getAllUpdateInfo() {
        ArrayList<C2942r> m18211e = this.f6562b.m18211e();
        if (m18211e == null) {
            return null;
        }
        ArrayList<MKOLUpdateElement> arrayList = new ArrayList<>();
        Iterator<C2942r> it = m18211e.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getUpdatElementFromLocalMapElement(it.next().m18204a()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getHotCityList() {
        ArrayList<C2938n> m18215c = this.f6562b.m18215c();
        if (m18215c == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<C2938n> it = m18215c.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getOfflineCityList() {
        ArrayList<C2938n> m18213d = this.f6562b.m18213d();
        if (m18213d == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<C2938n> it = m18213d.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public MKOLUpdateElement getUpdateInfo(int i) {
        C2942r m18206g = this.f6562b.m18206g(i);
        if (m18206g == null) {
            return null;
        }
        return OfflineMapUtil.getUpdatElementFromLocalMapElement(m18206g.m18204a());
    }

    @Deprecated
    public int importOfflineData() {
        return importOfflineData(false);
    }

    @Deprecated
    public int importOfflineData(boolean z) {
        ArrayList<C2942r> m18211e = this.f6562b.m18211e();
        int size = m18211e != null ? m18211e.size() : 0;
        int i = size;
        this.f6562b.m18219a(z, true);
        ArrayList<C2942r> m18211e2 = this.f6562b.m18211e();
        if (m18211e2 != null) {
            i = m18211e2.size();
        }
        return i - size;
    }

    public boolean init(MKOfflineMapListener mKOfflineMapListener) {
        C2934j.m18235a();
        this.f6562b = C2939o.m18224a();
        C2939o c2939o = this.f6562b;
        if (c2939o == null) {
            return false;
        }
        c2939o.m18221a(new C2774a(this));
        this.f6563c = mKOfflineMapListener;
        return true;
    }

    public boolean pause(int i) {
        return this.f6562b.m18214c(i);
    }

    public boolean remove(int i) {
        return this.f6562b.m18210e(i);
    }

    public ArrayList<MKOLSearchRecord> searchCity(String str) {
        ArrayList<C2938n> m18220a = this.f6562b.m18220a(str);
        if (m18220a == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<C2938n> it = m18220a.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public boolean start(int i) {
        C2939o c2939o = this.f6562b;
        if (c2939o == null) {
            return false;
        }
        if (c2939o.m18211e() != null) {
            Iterator<C2942r> it = this.f6562b.m18211e().iterator();
            while (it.hasNext()) {
                C2942r next = it.next();
                if (next.f7349a.f7337a == i) {
                    if (next.f7349a.f7346j || next.f7349a.f7348l == 2 || next.f7349a.f7348l == 3 || next.f7349a.f7348l == 6) {
                        return this.f6562b.m18217b(i);
                    }
                    return false;
                }
            }
        }
        return this.f6562b.m18223a(i);
    }

    public boolean update(int i) {
        C2939o c2939o = this.f6562b;
        if (c2939o != null && c2939o.m18211e() != null) {
            Iterator<C2942r> it = this.f6562b.m18211e().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C2942r next = it.next();
                if (next.f7349a.f7337a == i) {
                    if (next.f7349a.f7346j) {
                        return this.f6562b.m18208f(i);
                    }
                }
            }
        }
        return false;
    }
}
