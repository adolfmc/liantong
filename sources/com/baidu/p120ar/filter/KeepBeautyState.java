package com.baidu.p120ar.filter;

import android.text.TextUtils;
import com.baidu.p120ar.utils.FileUtils;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.filter.KeepBeautyState */
/* loaded from: E:\10201592_dexfile_execute.dex */
class KeepBeautyState {
    private static final String DEFAULT_UNIFORM_LIST_KEY = "defaultUniformList";
    private static final String FILTER_LIST_KEY = "FilterList";
    private static final String FILTER_NAME_KEY = "FilterName";
    private static final String IS_ENABLE_KEY = "is_enable";
    private static final String PROPERTY_LIST_PATH = "/property_list.json";
    private static final String PROPERTY_NAME_KEY = "PropertyName";
    private static final String PROPERTY_VALUE_KEY = "PropertyValue";
    private final String mFilterNodeName = FilterNode.highlightFilter.getNodeName();
    private boolean mIsUseFilter;

    public void changeFilterName(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (new File(str + "/property_list.json").exists()) {
            try {
                JSONArray jSONArray = new JSONObject(FileUtils.readFileText(str + "/property_list.json")).getJSONArray("FilterList");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (this.mFilterNodeName.equals(jSONObject.getString("FilterName"))) {
                        JSONArray jSONArray2 = jSONObject.getJSONArray("defaultUniformList");
                        int i2 = 0;
                        while (true) {
                            if (i2 < jSONArray2.length()) {
                                JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                                if ("is_enable".equals(jSONObject2.getString("PropertyName"))) {
                                    boolean z = true;
                                    if (jSONObject2.getInt("PropertyValue") != 1) {
                                        z = false;
                                    }
                                    this.mIsUseFilter = z;
                                } else {
                                    i2++;
                                }
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFilterNodeName() {
        String str = this.mFilterNodeName;
        return str == null ? "" : str;
    }

    public boolean isUseFilter() {
        return this.mIsUseFilter;
    }
}
