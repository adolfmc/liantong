package com.baidu.platform.comapi.map;

import com.baidu.platform.comjni.map.basemap.AppBaseMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3040d extends InnerOverlay {
    public C3040d() {
        super(20);
    }

    public C3040d(AppBaseMap appBaseMap) {
        super(20, appBaseMap);
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public boolean getDefaultShowStatus() {
        return true;
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public String getLayerTag() {
        return "compass";
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public void setData(String str) {
        super.setData(str);
    }
}
