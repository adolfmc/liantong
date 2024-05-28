package com.networkbench.agent.impl.harvest.type;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public enum MetricCategory {
    NONE("None"),
    VIEW_LOADING("ViewLoading"),
    VIEW_LAYOUT("ViewLayout"),
    DATABASE("Storage"),
    IMAGE("Image"),
    JSON("Json"),
    NETWORK("Network"),
    BACKGROUND("Background"),
    CUSTOMEVENT("CustomEvent");
    
    private static final Map<String, MetricCategory> methodMap = new HashMap<String, MetricCategory>() { // from class: com.networkbench.agent.impl.harvest.type.MetricCategory.1

        /* renamed from: a */
        private static final long f16333a = -1342597889479051466L;

        {
            put("onCreate", MetricCategory.VIEW_LOADING);
        }
    };
    private String categoryName;

    MetricCategory(String str) {
        this.categoryName = str;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public static MetricCategory categoryForMethod(String str) {
        if (str == null) {
            return NONE;
        }
        int indexOf = str.indexOf("#");
        MetricCategory metricCategory = methodMap.get(indexOf >= 0 ? str.substring(indexOf + 1) : null);
        return metricCategory == null ? NONE : metricCategory;
    }
}
