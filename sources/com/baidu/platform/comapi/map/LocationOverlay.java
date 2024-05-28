package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import com.baidu.platform.comjni.tools.ParcelItem;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LocationOverlay extends InnerOverlay {

    /* renamed from: a */
    private AppBaseMap f7578a;

    public LocationOverlay() {
        super(7);
    }

    public LocationOverlay(AppBaseMap appBaseMap) {
        super(7, appBaseMap);
        this.f7578a = appBaseMap;
    }

    public void beginLocationLayerAnimation() {
        this.f7578a.BeginLocationLayerAnimation();
    }

    public void clearLocationLayerData(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putLong("locationaddr", this.mLayerID);
        this.f7578a.ClearLocationLayerData(bundle);
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public boolean getDefaultShowStatus() {
        return true;
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public String getLayerTag() {
        return "location";
    }

    public void setLocationLayerData(List<OverlayLocationData> list) {
        byte[] array;
        if (list == null || list.size() <= 0 || this.mLayerID == 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("locationaddr", this.mLayerID);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            OverlayLocationData overlayLocationData = list.get(i);
            if (overlayLocationData.getImage() == null) {
                return;
            }
            ParcelItem parcelItem = new ParcelItem();
            Bitmap image = overlayLocationData.getImage();
            Bundle bundle2 = new Bundle();
            ByteBuffer allocate = ByteBuffer.allocate(image.getWidth() * image.getHeight() * 4);
            image.copyPixelsToBuffer(allocate);
            bundle2.putByteArray("imgbin", allocate.array());
            bundle2.putInt("w", overlayLocationData.getImgWidth());
            bundle2.putInt("h", overlayLocationData.getImgHeight());
            bundle2.putInt("rotation", overlayLocationData.isRotation());
            bundle2.putString("name", overlayLocationData.getImgName() + "_" + Arrays.hashCode(array));
            parcelItem.setBundle(bundle2);
            arrayList.add(parcelItem);
        }
        if (arrayList.size() > 0) {
            ParcelItem[] parcelItemArr = new ParcelItem[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                parcelItemArr[i2] = (ParcelItem) arrayList.get(i2);
            }
            bundle.putParcelableArray("imagedata", parcelItemArr);
        }
        this.f7578a.SetLocationLayerData(bundle);
    }
}
