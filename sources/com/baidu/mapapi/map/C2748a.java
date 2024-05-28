package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.map.C2925d;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2748a implements Overlay.InterfaceC2746a {

    /* renamed from: a */
    final /* synthetic */ BaiduMap f6517a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2748a(BaiduMap baiduMap) {
        this.f6517a = baiduMap;
    }

    @Override // com.baidu.mapapi.map.Overlay.InterfaceC2746a
    /* renamed from: a */
    public LatLngBounds mo18798a(Overlay overlay) {
        C2925d c2925d;
        C2925d c2925d2;
        c2925d = this.f6517a.f5961j;
        if (c2925d == null || overlay == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        overlay.mo18867a(bundle);
        c2925d2 = this.f6517a.f5961j;
        return c2925d2.m18324b(bundle);
    }

    @Override // com.baidu.mapapi.map.Overlay.InterfaceC2746a
    /* renamed from: b */
    public void mo18797b(Overlay overlay) {
        boolean z;
        List list;
        List list2;
        List list3;
        C2925d c2925d;
        C2925d c2925d2;
        List list4;
        List list5;
        List list6;
        C2925d c2925d3;
        List list7;
        C2925d c2925d4;
        z = this.f6517a.f5951X;
        if (z) {
            return;
        }
        if (overlay != null) {
            list6 = this.f6517a.f5962k;
            if (list6.contains(overlay)) {
                Bundle mo18868a = overlay.mo18868a();
                c2925d3 = this.f6517a.f5961j;
                if (c2925d3 != null) {
                    c2925d4 = this.f6517a.f5961j;
                    c2925d4.m18309f(mo18868a);
                }
                list7 = this.f6517a.f5962k;
                list7.remove(overlay);
            }
        }
        if (overlay != null) {
            list4 = this.f6517a.f5964m;
            if (list4.contains(overlay)) {
                list5 = this.f6517a.f5964m;
                list5.remove(overlay);
            }
        }
        if (overlay != null) {
            list = this.f6517a.f5963l;
            if (list.contains(overlay)) {
                Marker marker = (Marker) overlay;
                if (marker.f6237s != null) {
                    list2 = this.f6517a.f5963l;
                    list2.remove(marker);
                    list3 = this.f6517a.f5963l;
                    if (list3.size() == 0) {
                        c2925d = this.f6517a.f5961j;
                        if (c2925d != null) {
                            c2925d2 = this.f6517a.f5961j;
                            c2925d2.m18317c(false);
                        }
                    }
                }
            }
        }
    }

    @Override // com.baidu.mapapi.map.Overlay.InterfaceC2746a
    /* renamed from: c */
    public void mo18796c(Overlay overlay) {
        boolean z;
        List list;
        List list2;
        List list3;
        List list4;
        C2925d c2925d;
        boolean z2;
        C2925d c2925d2;
        List list5;
        List list6;
        C2925d c2925d3;
        C2925d c2925d4;
        List list7;
        C2925d c2925d5;
        boolean z3;
        C2925d c2925d6;
        List list8;
        z = this.f6517a.f5951X;
        if (z) {
            return;
        }
        if (overlay != null) {
            list4 = this.f6517a.f5962k;
            if (list4.contains(overlay)) {
                boolean z4 = false;
                if (overlay instanceof Marker) {
                    Marker marker = (Marker) overlay;
                    if (marker.f6220b != null) {
                        if (marker.f6237s != null && marker.f6237s.size() > 1) {
                            Bundle bundle = new Bundle();
                            c2925d5 = this.f6517a.f5961j;
                            if (c2925d5 != null) {
                                z3 = this.f6517a.f5951X;
                                if (!z3) {
                                    marker.remove();
                                    marker.f6237s.clear();
                                    c2925d6 = this.f6517a.f5961j;
                                    c2925d6.m18318c(overlay.mo18867a(bundle));
                                    list8 = this.f6517a.f5962k;
                                    list8.add(overlay);
                                    z4 = true;
                                }
                            }
                        }
                    } else if (marker.f6237s != null && marker.f6237s.size() != 0) {
                        list5 = this.f6517a.f5963l;
                        if (list5.contains(marker)) {
                            list7 = this.f6517a.f5963l;
                            list7.remove(marker);
                        }
                        list6 = this.f6517a.f5963l;
                        list6.add(marker);
                        c2925d3 = this.f6517a.f5961j;
                        if (c2925d3 != null) {
                            c2925d4 = this.f6517a.f5961j;
                            c2925d4.m18317c(true);
                        }
                    }
                }
                c2925d = this.f6517a.f5961j;
                if (c2925d != null && !z4) {
                    z2 = this.f6517a.f5951X;
                    if (!z2) {
                        Bundle bundle2 = new Bundle();
                        c2925d2 = this.f6517a.f5961j;
                        c2925d2.m18312e(overlay.mo18867a(bundle2));
                    }
                }
            }
        }
        list = this.f6517a.f5964m;
        if (list.contains(overlay)) {
            list3 = this.f6517a.f5964m;
            list3.remove(overlay);
        }
        if (overlay instanceof Marker) {
            list2 = this.f6517a.f5964m;
            list2.add((Marker) overlay);
        }
    }

    @Override // com.baidu.mapapi.map.Overlay.InterfaceC2746a
    /* renamed from: d */
    public boolean mo18795d(Overlay overlay) {
        List list;
        List list2;
        list = this.f6517a.f5962k;
        if (list != null) {
            list2 = this.f6517a.f5962k;
            if (!list2.contains(overlay)) {
                return true;
            }
        }
        return false;
    }
}
