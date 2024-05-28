package com.sinovatech.unicom.separatemodule.recentmenu.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentBoxManager;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectionTabDetailFunction implements Function<String, List<RecentMiniEntity>> {
    @Override // io.reactivex.functions.Function
    public List<RecentMiniEntity> apply(String str) {
        JSONArray optJSONArray;
        RecentMiniEntity recentMiniEntity;
        int miniEntityType;
        ArrayList arrayList = new ArrayList();
        try {
            RecentStateEntity recentStateEntity = RecentStateEntity.getInstance(str);
            if (recentStateEntity.isSuccess() && !TextUtils.isEmpty(recentStateEntity.getBody()) && (optJSONArray = new JSONObject(recentStateEntity.getBody()).optJSONArray("appList")) != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null && (miniEntityType = RecentBoxManager.miniEntityType((recentMiniEntity = RecentMiniEntity.getInstance(optJSONObject)))) >= 0) {
                        recentMiniEntity.setType(miniEntityType);
                        arrayList.add(recentMiniEntity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
