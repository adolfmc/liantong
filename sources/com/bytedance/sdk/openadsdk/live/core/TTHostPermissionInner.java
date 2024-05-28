package com.bytedance.sdk.openadsdk.live.core;

import com.bytedance.android.live.base.api.IHostPermission;
import com.bytedance.android.live.base.api.LocationProvider;
import com.bytedance.sdk.openadsdk.TTCustomController;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TTHostPermissionInner implements IHostPermission {

    /* renamed from: mb */
    private TTCustomController f9655mb;

    public TTHostPermissionInner(TTCustomController tTCustomController) {
        if (tTCustomController == null) {
            this.f9655mb = new TTCustomController() { // from class: com.bytedance.sdk.openadsdk.live.core.TTHostPermissionInner.1
            };
        } else {
            this.f9655mb = tTCustomController;
        }
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public boolean isCanUseLocation() {
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null) {
            return true;
        }
        return tTCustomController.isCanUseLocation();
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public LocationProvider getTTLocation() {
        final com.bytedance.sdk.openadsdk.LocationProvider tTLocation;
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null || (tTLocation = tTCustomController.getTTLocation()) == null) {
            return null;
        }
        return new LocationProvider() { // from class: com.bytedance.sdk.openadsdk.live.core.TTHostPermissionInner.2
            @Override // com.bytedance.android.live.base.api.LocationProvider
            public double getLatitude() {
                return tTLocation.getLatitude();
            }

            @Override // com.bytedance.android.live.base.api.LocationProvider
            public double getLongitude() {
                return tTLocation.getLongitude();
            }
        };
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public boolean alist() {
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null) {
            return false;
        }
        return tTCustomController.alist();
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public boolean isCanUsePhoneState() {
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null) {
            return false;
        }
        return tTCustomController.isCanUsePhoneState();
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public String getDevImei() {
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null) {
            return null;
        }
        return tTCustomController.getDevImei();
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public boolean isCanUseWifiState() {
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null) {
            return false;
        }
        return tTCustomController.isCanUseWifiState();
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public String getMacAddress() {
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null) {
            return null;
        }
        return tTCustomController.getMacAddress();
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public boolean isCanUseWriteExternal() {
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null) {
            return false;
        }
        return tTCustomController.isCanUseWriteExternal();
    }

    @Override // com.bytedance.android.live.base.api.IHostPermission
    public String getDevOaid() {
        TTCustomController tTCustomController = this.f9655mb;
        if (tTCustomController == null) {
            return null;
        }
        return tTCustomController.getDevOaid();
    }
}
