package com.baidu.p120ar.ability;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ability.AbilityData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AbilityData {
    private String mAbilityName;
    private long mTimestamp;
    private boolean mSyncWithCamera = false;
    private boolean mFrontCameraData = false;
    private boolean mControllData = false;

    public String getAbilityName() {
        return this.mAbilityName;
    }

    public void setAbilityName(String str) {
        this.mAbilityName = str;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public boolean isSyncWithCamera() {
        return this.mSyncWithCamera;
    }

    public void setSyncWithCamera(boolean z) {
        this.mSyncWithCamera = z;
    }

    public boolean isFrontCameraData() {
        return this.mFrontCameraData;
    }

    public void setFrontCameraData(boolean z) {
        this.mFrontCameraData = z;
    }

    public boolean isControllData() {
        return this.mControllData;
    }

    public void setControllData(boolean z) {
        this.mControllData = z;
    }
}
