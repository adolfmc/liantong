package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapsdkplatform.comapi.map.C2938n;
import com.baidu.mapsdkplatform.comapi.map.C2941q;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OfflineMapUtil {
    public static MKOLSearchRecord getSearchRecordFromLocalCityInfo(C2938n c2938n) {
        C2938n next;
        if (c2938n == null) {
            return null;
        }
        MKOLSearchRecord mKOLSearchRecord = new MKOLSearchRecord();
        mKOLSearchRecord.cityID = c2938n.f7326a;
        mKOLSearchRecord.cityName = c2938n.f7327b;
        mKOLSearchRecord.cityType = c2938n.f7329d;
        long j = 0;
        if (c2938n.m18226a() != null) {
            ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
            Iterator<C2938n> it = c2938n.m18226a().iterator();
            while (it.hasNext()) {
                arrayList.add(getSearchRecordFromLocalCityInfo(it.next()));
                j += next.f7328c;
                mKOLSearchRecord.childCities = arrayList;
            }
        }
        if (mKOLSearchRecord.cityType != 1) {
            j = c2938n.f7328c;
        }
        mKOLSearchRecord.dataSize = j;
        return mKOLSearchRecord;
    }

    public static MKOLUpdateElement getUpdatElementFromLocalMapElement(C2941q c2941q) {
        if (c2941q == null) {
            return null;
        }
        MKOLUpdateElement mKOLUpdateElement = new MKOLUpdateElement();
        mKOLUpdateElement.cityID = c2941q.f7337a;
        mKOLUpdateElement.cityName = c2941q.f7338b;
        if (c2941q.f7343g != null) {
            mKOLUpdateElement.geoPt = CoordUtil.mc2ll(c2941q.f7343g);
        }
        mKOLUpdateElement.level = c2941q.f7341e;
        mKOLUpdateElement.ratio = c2941q.f7345i;
        mKOLUpdateElement.serversize = c2941q.f7344h;
        mKOLUpdateElement.size = c2941q.f7345i == 100 ? c2941q.f7344h : (c2941q.f7344h / 100) * c2941q.f7345i;
        mKOLUpdateElement.status = c2941q.f7348l;
        mKOLUpdateElement.update = c2941q.f7346j;
        return mKOLUpdateElement;
    }
}
