package com.sinovatech.unicom.separatemodule.baidumap.parser;

import android.util.Log;
import com.sinovatech.unicom.separatemodule.baidumap.entity.SearchTagEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchTagFunction implements Function<String, ArrayList<SearchTagEntity>> {
    @Override // io.reactivex.functions.Function
    public ArrayList<SearchTagEntity> apply(String str) throws Exception {
        ArrayList<SearchTagEntity> arrayList = new ArrayList<>();
        Log.d("SearchTagFunction", "推荐搜索：" + str);
        JSONArray jSONArray = new JSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
            SearchTagEntity searchTagEntity = new SearchTagEntity();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            searchTagEntity.setCreateTime(optJSONObject.optString("createTime"));
            searchTagEntity.setCreator(optJSONObject.optString("creator"));
            searchTagEntity.setCreatorProvinceCode(optJSONObject.optString("creatorProvinceCode"));
            searchTagEntity.setdEFAULTTAB(optJSONObject.optString("dEFAULTTAB"));
            searchTagEntity.setId(optJSONObject.optString("id"));
            searchTagEntity.setOrderNumber(optJSONObject.optString("orderNumber"));
            searchTagEntity.setStatus(optJSONObject.optString("status"));
            searchTagEntity.settABNAME(optJSONObject.optString("tABNAME"));
            arrayList.add(searchTagEntity);
        }
        return arrayList;
    }
}
