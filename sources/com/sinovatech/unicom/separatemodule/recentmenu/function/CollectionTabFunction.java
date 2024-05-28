package com.sinovatech.unicom.separatemodule.recentmenu.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionTabEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectionTabFunction implements Function<String, List<CollectionTabEntity>> {
    @Override // io.reactivex.functions.Function
    public List<CollectionTabEntity> apply(String str) {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        try {
            RecentStateEntity recentStateEntity = RecentStateEntity.getInstance(str);
            if (recentStateEntity.isSuccess() && !TextUtils.isEmpty(recentStateEntity.getBody()) && (optJSONArray = new JSONObject(recentStateEntity.getBody()).optJSONArray("categoryList")) != null && optJSONArray.length() > 0) {
                int i = 0;
                while (i < optJSONArray.length()) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        CollectionTabEntity collectionTabEntity = new CollectionTabEntity();
                        collectionTabEntity.setSelect(i == 0);
                        collectionTabEntity.setCategoryName(optJSONObject.optString("categoryName"));
                        collectionTabEntity.setCategoryId(optJSONObject.optString("id"));
                        collectionTabEntity.setSort(optJSONObject.optString("sort"));
                        arrayList.add(collectionTabEntity);
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
