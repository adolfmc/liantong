package com.baidu.p120ar.filter;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.filter.FilterNode */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum FilterNode {
    lutFilter("globalLutFilter"),
    skinFilter("globalSkinFilter"),
    faceFilter("globalFaceFilter"),
    makeupFilter("globalBeautyMakeupFilter"),
    tuneColorFilter("globalTuneColorFilter"),
    highlightFilter("globalHighlightMeshFilter"),
    advanceBeautyFilter("globalAdvanceBeautyFilter"),
    unknown("unknown");
    
    private String mNodeName;

    FilterNode(String str) {
        this.mNodeName = str;
    }

    public String getNodeName() {
        return this.mNodeName;
    }
}
