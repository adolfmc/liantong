package com.baidu.mapapi.map;

import android.text.TextUtils;
import com.baidu.mapsdkplatform.comapi.map.C2928f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.q */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2776q implements C2928f.InterfaceC2929a {

    /* renamed from: a */
    final /* synthetic */ CustomMapStyleCallBack f6565a;

    /* renamed from: b */
    final /* synthetic */ MapCustomStyleOptions f6566b;

    /* renamed from: c */
    final /* synthetic */ MapView f6567c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2776q(MapView mapView, CustomMapStyleCallBack customMapStyleCallBack, MapCustomStyleOptions mapCustomStyleOptions) {
        this.f6567c = mapView;
        this.f6565a = customMapStyleCallBack;
        this.f6566b = mapCustomStyleOptions;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18241a(int i, String str, String str2) {
        boolean z;
        CustomMapStyleCallBack customMapStyleCallBack = this.f6565a;
        if (customMapStyleCallBack == null || !customMapStyleCallBack.onCustomMapStyleLoadFailed(i, str, str2)) {
            z = this.f6567c.f6178B;
            if (z) {
                return;
            }
            this.f6567c.m18908a(str2, this.f6566b);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18240a(String str) {
        CustomMapStyleCallBack customMapStyleCallBack = this.f6565a;
        if (customMapStyleCallBack == null || !customMapStyleCallBack.onPreLoadLastCustomMapStyle(str)) {
            this.f6567c.m18908a(str, this.f6566b);
            this.f6567c.f6178B = true;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18239a(boolean z, String str) {
        CustomMapStyleCallBack customMapStyleCallBack = this.f6565a;
        if ((customMapStyleCallBack == null || !customMapStyleCallBack.onCustomMapStyleLoadSuccess(z, str)) && z && !TextUtils.isEmpty(str)) {
            this.f6567c.m18907a(str, "");
            this.f6567c.setMapCustomStyleEnable(true);
        }
    }
}
