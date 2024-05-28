package com.baidu.p120ar.filter;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.filter.FilterModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FilterModel {
    private String mFilterName;
    private String mFilterType;
    private List<Property> mUniformList;

    public String getFilterType() {
        return this.mFilterType;
    }

    public void setFilterType(String str) {
        this.mFilterType = str;
    }

    public String getFilterName() {
        return this.mFilterName;
    }

    public void setFilterName(String str) {
        this.mFilterName = str;
    }

    public List<Property> getUniformList() {
        return this.mUniformList;
    }

    public void setUniformList(List<Property> list) {
        this.mUniformList = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.filter.FilterModel$Property */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class Property {
        private String mPropertyName;
        private String mPropertyType;
        private String mPropertyValue;

        public Property() {
        }

        public String getPropertyName() {
            return this.mPropertyName;
        }

        public void setPropertyName(String str) {
            this.mPropertyName = str;
        }

        public String getPropertyType() {
            return this.mPropertyType;
        }

        public void setPropertyType(String str) {
            this.mPropertyType = str;
        }

        public String getPropertyValue() {
            return this.mPropertyValue;
        }

        public void setPropertyValue(String str) {
            this.mPropertyValue = str;
        }
    }
}
