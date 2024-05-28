package com.baidu.mapapi.map;

import android.text.TextUtils;
import com.baidu.mapsdkplatform.comapi.map.C2928f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.aa */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2749aa implements C2928f.InterfaceC2929a {

    /* renamed from: a */
    final /* synthetic */ CustomMapStyleCallBack f6518a;

    /* renamed from: b */
    final /* synthetic */ MapCustomStyleOptions f6519b;

    /* renamed from: c */
    final /* synthetic */ TextureMapView f6520c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2749aa(TextureMapView textureMapView, CustomMapStyleCallBack customMapStyleCallBack, MapCustomStyleOptions mapCustomStyleOptions) {
        this.f6520c = textureMapView;
        this.f6518a = customMapStyleCallBack;
        this.f6519b = mapCustomStyleOptions;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18241a(int i, String str, String str2) {
        boolean z;
        CustomMapStyleCallBack customMapStyleCallBack = this.f6518a;
        if (customMapStyleCallBack == null || !customMapStyleCallBack.onCustomMapStyleLoadFailed(i, str, str2)) {
            z = this.f6520c.f6438B;
            if (z) {
                return;
            }
            this.f6520c.m18855a(str2, this.f6519b);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18240a(String str) {
        CustomMapStyleCallBack customMapStyleCallBack = this.f6518a;
        if (customMapStyleCallBack == null || !customMapStyleCallBack.onPreLoadLastCustomMapStyle(str)) {
            this.f6520c.m18855a(str, this.f6519b);
            this.f6520c.f6438B = true;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.C2928f.InterfaceC2929a
    /* renamed from: a */
    public void mo18239a(boolean z, String str) {
        CustomMapStyleCallBack customMapStyleCallBack = this.f6518a;
        if ((customMapStyleCallBack == null || !customMapStyleCallBack.onCustomMapStyleLoadSuccess(z, str)) && !TextUtils.isEmpty(str)) {
            this.f6520c.m18854a(str, "");
            this.f6520c.setMapCustomStyleEnable(true);
        }
    }
}
