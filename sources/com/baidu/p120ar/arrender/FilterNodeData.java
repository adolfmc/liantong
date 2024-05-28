package com.baidu.p120ar.arrender;

import com.baidu.p120ar.ability.AbilityData;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.FilterNodeData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FilterNodeData extends AbilityData {
    private String mNodeName;
    private HashMap<String, Object> mValueMap;

    public String getNodeName() {
        return this.mNodeName;
    }

    public void setNodeName(String str) {
        this.mNodeName = str;
    }

    public HashMap<String, Object> getValueMap() {
        return this.mValueMap;
    }

    public void setValueMap(HashMap<String, Object> hashMap) {
        this.mValueMap = hashMap;
    }
}
