package com.baidu.platform.comapi.map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.ao */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3013ao extends InnerOverlay {
    public C3013ao() {
        super(30);
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public boolean addedToMapView() {
        if (this.mBaseMap == null) {
            return false;
        }
        this.mLayerID = this.mBaseMap.AddLayer(2, 0, "streetpopup");
        if (this.mLayerID != 0) {
            this.mBaseMap.SetLayersClickable(this.mLayerID, true);
            this.mBaseMap.ShowLayers(this.mLayerID, false);
            return true;
        }
        return false;
    }
}
