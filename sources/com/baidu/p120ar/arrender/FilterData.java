package com.baidu.p120ar.arrender;

import com.baidu.p120ar.ability.AbilityData;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.FilterData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FilterData extends AbilityData {
    private String mAdjustKey;
    private Object mAdjustValue;
    private AdjustValueType mAdjustValueType;
    private String mFilterName;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arrender.FilterData$AdjustValueType */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum AdjustValueType {
        INT,
        FLOAT,
        FLOAT_ARRAY,
        STRING
    }

    public String getFilterName() {
        return this.mFilterName;
    }

    public void setFilterName(String str) {
        this.mFilterName = str;
    }

    public String getAdjustKey() {
        return this.mAdjustKey;
    }

    public void setAdjustKey(String str) {
        this.mAdjustKey = str;
    }

    public Object getAdjustValue() {
        return this.mAdjustValue;
    }

    public void setAdjustValue(Object obj) {
        this.mAdjustValue = obj;
        setAdjustValueType(obj);
    }

    public AdjustValueType getAdjustValueType() {
        return this.mAdjustValueType;
    }

    private void setAdjustValueType(Object obj) {
        if ((obj instanceof Float) || (obj instanceof Double)) {
            this.mAdjustValueType = AdjustValueType.FLOAT;
        } else if (obj instanceof String) {
            this.mAdjustValueType = AdjustValueType.STRING;
        } else if (obj instanceof Integer) {
            this.mAdjustValueType = AdjustValueType.INT;
        } else if (obj instanceof float[]) {
            this.mAdjustValueType = AdjustValueType.FLOAT_ARRAY;
        }
    }
}
