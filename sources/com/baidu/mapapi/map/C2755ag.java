package com.baidu.mapapi.map;

import android.text.TextUtils;
import com.baidu.mapsdkplatform.comapi.map.C2928f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.ag */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2755ag implements C2928f.InterfaceC2929a {

    /* renamed from: a */
    final /* synthetic */ CustomMapStyleCallBack f6531a;

    /* renamed from: b */
    final /* synthetic */ MapCustomStyleOptions f6532b;

    /* renamed from: c */
    final /* synthetic */ WearMapView f6533c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2755ag(WearMapView wearMapView, CustomMapStyleCallBack customMapStyleCallBack, MapCustomStyleOptions mapCustomStyleOptions) {
        this.f6533c = wearMapView;
        this.f6531a = customMapStyleCallBack;
        this.f6532b = mapCustomStyleOptions;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18241a(int i, String str, String str2) {
        boolean z;
        CustomMapStyleCallBack customMapStyleCallBack = this.f6531a;
        if (customMapStyleCallBack == null || !customMapStyleCallBack.onCustomMapStyleLoadFailed(i, str, str2)) {
            z = this.f6533c.f6493H;
            if (z) {
                return;
            }
            this.f6533c.m18816a(str2, this.f6532b);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18240a(String str) {
        CustomMapStyleCallBack customMapStyleCallBack = this.f6531a;
        if (customMapStyleCallBack == null || !customMapStyleCallBack.onPreLoadLastCustomMapStyle(str)) {
            this.f6533c.f6493H = true;
            this.f6533c.m18816a(str, this.f6532b);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18239a(boolean z, String str) {
        CustomMapStyleCallBack customMapStyleCallBack = this.f6531a;
        if ((customMapStyleCallBack == null || !customMapStyleCallBack.onCustomMapStyleLoadSuccess(z, str)) && !TextUtils.isEmpty(str)) {
            this.f6533c.m18815a(str, "");
            this.f6533c.setMapCustomStyleEnable(true);
        }
    }
}
