package com.baidu.p120ar.steploading;

import com.baidu.p120ar.utils.FileUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.steploading.BundleStepResReader */
/* loaded from: E:\10201592_dexfile_execute.dex */
class BundleStepResReader {
    private String mCaseDir;
    private Map<String, StepResInfo> mStepResConf;

    public BundleStepResReader(String str) {
        this.mCaseDir = str;
    }

    public StepResInfo getResInfo(String str) {
        Map<String, StepResInfo> loadStepResConfig = loadStepResConfig();
        if (loadStepResConfig.containsKey(str)) {
            return loadStepResConfig.get(str);
        }
        return null;
    }

    private Map<String, StepResInfo> loadStepResConfig() {
        Map<String, StepResInfo> map = this.mStepResConf;
        if (map != null) {
            return map;
        }
        this.mStepResConf = new HashMap();
        File file = new File(this.mCaseDir.concat(File.separator).concat("res_config.json"));
        if (file.exists()) {
            try {
                JSONArray optJSONArray = new JSONObject(FileUtils.readFileText(file)).optJSONArray("res");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            StepResInfo stepResInfo = new StepResInfo();
                            stepResInfo.resPath = optJSONObject.getString("resPath");
                            stepResInfo.resId = optJSONObject.optString("resId");
                            stepResInfo.encoding = optJSONObject.optString("encoding");
                            stepResInfo.md5 = optJSONObject.optString("md5");
                            this.mStepResConf.put(stepResInfo.resPath, stepResInfo);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this.mStepResConf;
    }
}
