package com.baidu.minivideo.arface.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BeautyEnableStatus implements IBeautyStatus {
    public boolean mMakeupEnable = true;
    public boolean mFilterLutEnable = true;
    public boolean mFaceSkinEnable = true;
    public boolean mFeaturesEnable = true;

    public boolean isMakeupEnable() {
        return this.mMakeupEnable;
    }

    @Override // com.baidu.minivideo.arface.bean.IBeautyStatus
    public void setMakeupEnable(boolean z) {
        this.mMakeupEnable = z;
    }

    @Override // com.baidu.minivideo.arface.bean.IBeautyStatus
    public boolean isFilterLutEnable() {
        return this.mFilterLutEnable;
    }

    @Override // com.baidu.minivideo.arface.bean.IBeautyStatus
    public void setFilterLutEnable(boolean z) {
        this.mFilterLutEnable = z;
    }

    @Override // com.baidu.minivideo.arface.bean.IBeautyStatus
    public boolean isFaceSkinEnable() {
        return this.mFaceSkinEnable;
    }

    @Override // com.baidu.minivideo.arface.bean.IBeautyStatus
    public void setFaceSkinEnable(boolean z) {
        this.mFaceSkinEnable = z;
    }

    @Override // com.baidu.minivideo.arface.bean.IBeautyStatus
    public boolean isFeaturesEnable() {
        return this.mFeaturesEnable;
    }

    @Override // com.baidu.minivideo.arface.bean.IBeautyStatus
    public void setFeaturesEnable(boolean z) {
        this.mFeaturesEnable = z;
    }

    @Override // com.baidu.minivideo.arface.bean.IBeautyStatus
    public void reset() {
        this.mMakeupEnable = true;
        this.mFaceSkinEnable = true;
        this.mFeaturesEnable = true;
        this.mFilterLutEnable = true;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            if (obj instanceof BeautyEnableStatus) {
                BeautyEnableStatus beautyEnableStatus = (BeautyEnableStatus) obj;
                if (beautyEnableStatus.mFaceSkinEnable != this.mFaceSkinEnable || beautyEnableStatus.mFeaturesEnable != this.mFeaturesEnable || beautyEnableStatus.mFilterLutEnable != this.mFilterLutEnable) {
                }
            }
            return false;
        }
        return true;
    }
}
