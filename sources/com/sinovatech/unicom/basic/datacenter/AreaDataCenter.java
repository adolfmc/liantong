package com.sinovatech.unicom.basic.datacenter;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.p314po.AreaEntity;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.p318ui.App;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AreaDataCenter {
    public static List<AreaEntity> getAreaList(Context context) {
        String areaJson;
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(App.getAreaJson())) {
                areaJson = FileTools.readFile(context.getResources().getAssets().open("broadband_area.json"));
            } else {
                areaJson = App.getAreaJson();
            }
            JSONArray jSONArray = new JSONArray(areaJson);
            for (int i = 0; i < jSONArray.length(); i++) {
                AreaEntity areaEntity = new AreaEntity();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    areaEntity.setAreaid(optJSONObject.optString("areaid"));
                    areaEntity.setAreaname(optJSONObject.optString("areaname"));
                    areaEntity.setAreacode(optJSONObject.optString("areacode"));
                    areaEntity.setIdNumber(optJSONObject.optString("idNumber"));
                    areaEntity.setPinyin(optJSONObject.optString("pinyin"));
                }
                arrayList.add(areaEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
