package com.baidu.minivideo.arface.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IBeautyStatus {
    boolean isFaceSkinEnable();

    boolean isFeaturesEnable();

    boolean isFilterLutEnable();

    void reset();

    void setFaceSkinEnable(boolean z);

    void setFeaturesEnable(boolean z);

    void setFilterLutEnable(boolean z);

    void setMakeupEnable(boolean z);
}
