package com.baidu.mapapi.map.offline;

import com.baidu.mapsdkplatform.comapi.map.C2939o;
import com.baidu.mapsdkplatform.comapi.map.InterfaceC2943s;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.offline.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2774a implements InterfaceC2943s {

    /* renamed from: a */
    final /* synthetic */ MKOfflineMap f6564a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2774a(MKOfflineMap mKOfflineMap) {
        this.f6564a = mKOfflineMap;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.InterfaceC2943s
    /* renamed from: a */
    public void mo18202a(int i, int i2) {
        MKOfflineMapListener mKOfflineMapListener;
        MKOfflineMapListener mKOfflineMapListener2;
        MKOfflineMapListener mKOfflineMapListener3;
        C2939o c2939o;
        if (i == 4) {
            ArrayList<MKOLUpdateElement> allUpdateInfo = this.f6564a.getAllUpdateInfo();
            if (allUpdateInfo != null) {
                for (MKOLUpdateElement mKOLUpdateElement : allUpdateInfo) {
                    if (mKOLUpdateElement.update) {
                        mKOfflineMapListener = this.f6564a.f6563c;
                        mKOfflineMapListener.onGetOfflineMapState(4, mKOLUpdateElement.cityID);
                    }
                }
                return;
            }
            return;
        }
        int i3 = 6;
        if (i == 6) {
            mKOfflineMapListener2 = this.f6564a.f6563c;
        } else if (i == 8) {
            int i4 = i2 >> 8;
            mKOfflineMapListener3 = this.f6564a.f6563c;
            mKOfflineMapListener3.onGetOfflineMapState(0, i4);
            return;
        } else if (i != 10) {
            if (i != 12) {
                return;
            }
            c2939o = this.f6564a.f6562b;
            c2939o.m18219a(true, false);
            return;
        } else {
            mKOfflineMapListener2 = this.f6564a.f6563c;
            i3 = 2;
        }
        mKOfflineMapListener2.onGetOfflineMapState(i3, i2);
    }
}
