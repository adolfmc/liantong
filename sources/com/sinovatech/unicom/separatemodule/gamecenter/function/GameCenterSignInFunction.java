package com.sinovatech.unicom.separatemodule.gamecenter.function;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInHistoryEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCenterSignInFunction implements Function<String, SignInHistoryEntity> {
    @Override // io.reactivex.functions.Function
    public SignInHistoryEntity apply(String str) {
        SignInHistoryEntity signInHistoryEntity = new SignInHistoryEntity();
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("respCode");
                signInHistoryEntity.setRespCode(optString);
                if ("0000".equals(optString)) {
                    CacheDataCenter.getInstance().setGameCenterSingInHistory(str);
                } else {
                    String gameCenterSingInHistory = CacheDataCenter.getInstance().getGameCenterSingInHistory();
                    if (!TextUtils.isEmpty(gameCenterSingInHistory)) {
                        jSONObject = new JSONObject(gameCenterSingInHistory);
                        optString = jSONObject.optString("code");
                        signInHistoryEntity.setRespCode(optString);
                    }
                }
                if ("0000".equals(optString)) {
                    String optString2 = jSONObject.optString("signin_history");
                    if (!TextUtils.isEmpty(optString2)) {
                        JSONObject jSONObject2 = new JSONObject(optString2);
                        Iterator<String> keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            JSONObject optJSONObject = jSONObject2.optJSONObject(keys.next());
                            SignInHistoryEntity.HistoryEntity historyEntity = new SignInHistoryEntity.HistoryEntity();
                            historyEntity.setDayIndex(optJSONObject.optInt("dayIndex"));
                            historyEntity.setIgmUrl(optJSONObject.optString("img_url"));
                            historyEntity.setRewardVal(optJSONObject.optString("reward_val"));
                            historyEntity.setSignState(optJSONObject.optString("sign_state"));
                            historyEntity.setRewardType(optJSONObject.optString("reward_type"));
                            historyEntity.setRewardInfo(optJSONObject.optString("reward_info"));
                            historyEntity.setUserId(optJSONObject.optString("user_id"));
                            arrayList.add(historyEntity);
                        }
                    }
                    Collections.sort(arrayList, new Comparator<SignInHistoryEntity.HistoryEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterSignInFunction.1
                        @Override // java.util.Comparator
                        public int compare(SignInHistoryEntity.HistoryEntity historyEntity2, SignInHistoryEntity.HistoryEntity historyEntity3) {
                            return historyEntity2.getDayIndex() - historyEntity3.getDayIndex();
                        }
                    });
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (arrayList.isEmpty()) {
            int i = 0;
            while (i < 7) {
                SignInHistoryEntity.HistoryEntity historyEntity2 = new SignInHistoryEntity.HistoryEntity();
                int i2 = i + 1;
                historyEntity2.setDayIndex(i2);
                historyEntity2.setSignState(i == 0 ? "Y" : "N");
                arrayList.add(historyEntity2);
                i = i2;
            }
        }
        signInHistoryEntity.setSignInHistoryList(arrayList);
        return signInHistoryEntity;
    }
}
