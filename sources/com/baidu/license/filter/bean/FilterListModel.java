package com.baidu.license.filter.bean;

import com.baidu.license.sticker.BaseModel;
import com.baidu.license.sticker.BaseResult;
import java.io.Serializable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FilterListModel extends BaseModel implements Serializable {
    public FilterResult result;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class FilterResult extends BaseResult implements Serializable {
        public List<FilterData> filterDataList;

        public List<FilterData> getFilterDataList() {
            return this.filterDataList;
        }

        public void setFilterDataList(List<FilterData> list) {
            this.filterDataList = list;
        }
    }

    public FilterResult getResult() {
        return this.result;
    }

    public void setResult(FilterResult filterResult) {
        this.result = filterResult;
    }
}
