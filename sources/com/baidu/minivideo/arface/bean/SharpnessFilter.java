package com.baidu.minivideo.arface.bean;

import com.baidu.p120ar.filter.FilterNode;
import com.baidu.p120ar.filter.FilterParam;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum SharpnessFilter implements FilterParam {
    sharpness("intensity_sharpness");
    
    private String mParamName;

    SharpnessFilter(String str) {
        this.mParamName = str;
    }

    @Override // com.baidu.p120ar.filter.FilterParam
    public String getParamName() {
        return this.mParamName;
    }

    @Override // com.baidu.p120ar.filter.FilterParam
    public FilterNode getFilterNode() {
        return FilterNode.skinFilter;
    }
}
