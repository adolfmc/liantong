package com.baidu.platform.comapi.map;

import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class InnerOverlay extends Overlay {

    /* renamed from: a */
    private boolean f7565a;

    /* renamed from: b */
    protected String f7566b;

    /* renamed from: c */
    protected Bundle f7567c;
    public AppBaseMap mBaseMap;

    public InnerOverlay() {
        this.mBaseMap = null;
        this.f7566b = null;
        this.f7567c = null;
        this.f7565a = true;
    }

    public InnerOverlay(int i) {
        this.mBaseMap = null;
        this.f7566b = null;
        this.f7567c = null;
        this.f7565a = true;
        setType(i);
    }

    public InnerOverlay(int i, AppBaseMap appBaseMap) {
        this.mBaseMap = null;
        this.f7566b = null;
        this.f7567c = null;
        this.f7565a = true;
        setType(i);
        this.mBaseMap = appBaseMap;
    }

    public boolean IsOverlayShow() {
        AppBaseMap appBaseMap;
        return (this.mLayerID == 0 || (appBaseMap = this.mBaseMap) == null || appBaseMap.GetId() == 0 || !this.mBaseMap.LayersIsShow(this.mLayerID)) ? false : true;
    }

    public void SetMapParam(long j, AppBaseMap appBaseMap) {
        this.mLayerID = j;
        this.mBaseMap = appBaseMap;
    }

    public void SetOverlayShow(boolean z) {
        AppBaseMap appBaseMap;
        if (this.mLayerID == 0 || (appBaseMap = this.mBaseMap) == null || appBaseMap.GetId() == 0) {
            return;
        }
        long currentTimeMillis = C3084z.f8021a ? System.currentTimeMillis() : 0L;
        this.mBaseMap.ShowLayers(this.mLayerID, z);
        if (C3084z.f8021a) {
            C3084z.m17719a("InnerOverlay", "ShowLayer:" + this.mLayerID + ":" + z + " tag:" + getLayerTag() + " [" + (System.currentTimeMillis() - currentTimeMillis) + "ms]");
        }
    }

    public void UpdateOverlay() {
        AppBaseMap appBaseMap;
        if (this.mLayerID == 0 || (appBaseMap = this.mBaseMap) == null || appBaseMap.GetId() == 0) {
            return;
        }
        long currentTimeMillis = C3084z.f8021a ? System.currentTimeMillis() : 0L;
        this.mBaseMap.UpdateLayers(this.mLayerID);
        if (C3084z.f8021a) {
            C3084z.m17719a("InnerOverlay", "UpdateLayer:" + this.mLayerID + " tag:" + getLayerTag() + " [" + (System.currentTimeMillis() - currentTimeMillis) + "ms]");
        }
    }

    public boolean addedToMapView() {
        AppBaseMap appBaseMap = this.mBaseMap;
        if (appBaseMap != null && appBaseMap.GetId() != 0) {
            long currentTimeMillis = C3084z.f8021a ? System.currentTimeMillis() : 0L;
            this.mLayerID = this.mBaseMap.AddLayer(getUpdateType(), getUpdateTimeInterval(), getLayerTag());
            if (C3084z.f8021a) {
                C3084z.m17719a("InnerOverlay", "AddLayer:" + this.mLayerID + " type:" + this.mType + " tag:" + getLayerTag() + " [" + (System.currentTimeMillis() - currentTimeMillis) + "ms]");
            }
            if (this.mLayerID != 0) {
                this.mBaseMap.SetLayersClickable(this.mLayerID, this.f7565a);
                SetOverlayShow(getDefaultShowStatus());
                return true;
            }
        }
        return false;
    }

    public void clear() {
        long currentTimeMillis = C3084z.f8021a ? System.currentTimeMillis() : 0L;
        if (!TextUtils.isEmpty(this.f7566b)) {
            this.f7566b = null;
            AppBaseMap appBaseMap = this.mBaseMap;
            if (appBaseMap != null) {
                appBaseMap.ClearLayer(this.mLayerID);
            }
        }
        if (C3084z.f8021a) {
            C3084z.m17719a("InnerOverlay", "ClearLayer:" + this.mLayerID + " tag:" + getLayerTag() + " [" + (System.currentTimeMillis() - currentTimeMillis) + "ms]");
        }
    }

    public String getData() {
        return this.f7566b;
    }

    public boolean getDefaultShowStatus() {
        return false;
    }

    public String getLayerTag() {
        return "default";
    }

    public Bundle getParam() {
        return this.f7567c;
    }

    public int getType() {
        return this.mType;
    }

    public int getUpdateTimeInterval() {
        return 0;
    }

    public int getUpdateType() {
        return 0;
    }

    public void setClickAble(boolean z) {
        this.f7565a = z;
        AppBaseMap appBaseMap = this.mBaseMap;
        if (appBaseMap == null || appBaseMap.GetId() == 0 || this.mLayerID == 0) {
            return;
        }
        this.mBaseMap.SetLayersClickable(this.mLayerID, z);
    }

    public void setData(String str) {
        if (str != null) {
            this.f7566b = str;
        }
    }

    public void setFocus(int i, boolean z) {
        setFocus(i, z, null);
    }

    public void setFocus(int i, boolean z, String str) {
        AppBaseMap appBaseMap = this.mBaseMap;
        if (appBaseMap == null || appBaseMap.GetId() == 0) {
            return;
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("uid", str);
        }
        this.mBaseMap.SetFocus(this.mLayerID, i, z, bundle);
    }

    public void setParam(Bundle bundle) {
        this.f7567c = bundle;
    }

    public void setType(int i) {
        this.mType = i;
    }
}
