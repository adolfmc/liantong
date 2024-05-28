package com.baidu.arface.module.face;

import com.baidu.minivideo.arface.ArFaceSdk;
import com.baidu.minivideo.arface.DuArResConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FilterConfig {
    private HashMap<String, FilterGroup> mFilterGroupSet;
    private String mStartFilterGroupId;

    public void parse(JSONObject jSONObject) {
        HashMap<String, FilterGroup> hashMap = this.mFilterGroupSet;
        if (hashMap == null) {
            this.mFilterGroupSet = new HashMap<>();
        } else {
            hashMap.clear();
        }
        this.mStartFilterGroupId = jSONObject.optString("start_filter_group_id");
        JSONArray optJSONArray = jSONObject.optJSONArray("filter_group_set");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return;
        }
        int length = optJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                FilterGroup filterGroup = new FilterGroup();
                filterGroup.parse(optJSONObject);
                this.mFilterGroupSet.put(filterGroup.mFilterGroupId, filterGroup);
            }
        }
    }

    public String getFilterGroupResFile(String str) {
        FilterGroup filterGroup;
        HashMap<String, FilterGroup> hashMap = this.mFilterGroupSet;
        if (hashMap == null || (filterGroup = hashMap.get(str)) == null || filterGroup.mMixTarget == null || filterGroup.mMixTarget.mPassList == null || filterGroup.mMixTarget.mPassList.size() <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ArFaceSdk.getResConfig();
        sb.append(DuArResConfig.getDefaultFilterPath());
        sb.append(((Filter) filterGroup.mMixTarget.mPassList.get(0)).mSourceFile);
        return sb.toString();
    }

    public JSONObject toJson() {
        FilterGroup value;
        JSONObject json;
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.mFilterGroupSet != null && this.mFilterGroupSet.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (Map.Entry<String, FilterGroup> entry : this.mFilterGroupSet.entrySet()) {
                    if (entry != null && (value = entry.getValue()) != null && (json = value.toJson()) != null) {
                        jSONArray.put(json);
                    }
                }
                jSONObject.put("filter_group_set", jSONArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class FilterGroup {
        private boolean mDisableFilterGroup;
        private String mFilterGroupId;
        private boolean mGlobal;
        private FilterList mMixTarget;

        public void parse(JSONObject jSONObject) {
            this.mFilterGroupId = jSONObject.optString("filter_group_id");
            this.mGlobal = jSONObject.optInt("global", 0) == 1;
            this.mDisableFilterGroup = jSONObject.optInt("disable_filter_group", 0) == 1;
            JSONObject optJSONObject = jSONObject.optJSONObject("mix_target");
            if (optJSONObject != null) {
                this.mMixTarget = new FilterList();
                this.mMixTarget.parse(optJSONObject);
            }
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("filter_group_id", this.mFilterGroupId);
                int i = 1;
                jSONObject.put("global", this.mGlobal ? 1 : 0);
                if (!this.mDisableFilterGroup) {
                    i = 0;
                }
                jSONObject.put("disable_filter_group", i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class FilterList {
        private ArrayList<Filter> mPassList;

        public void parse(JSONObject jSONObject) {
            ArrayList<Filter> arrayList = this.mPassList;
            if (arrayList == null) {
                this.mPassList = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("pass_list");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return;
            }
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Filter filter2 = new Filter();
                    filter2.parse(optJSONObject);
                    this.mPassList.add(filter2);
                }
            }
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.mPassList != null && this.mPassList.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<Filter> it = this.mPassList.iterator();
                    while (it.hasNext()) {
                        Filter next = it.next();
                        if (next != null) {
                            jSONArray.put(next.toJson());
                        }
                    }
                    jSONObject.put("pass_list", jSONArray);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Filter {
        private String mSourceFile;

        public void parse(JSONObject jSONObject) {
            this.mSourceFile = jSONObject.optString("source_file");
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("source_file", this.mSourceFile);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }
}
