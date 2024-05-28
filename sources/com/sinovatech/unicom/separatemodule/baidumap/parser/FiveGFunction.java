package com.sinovatech.unicom.separatemodule.baidumap.parser;

import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.baidumap.entity.FiveGEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FiveGFunction implements Function<String, FiveGEntity> {
    @Override // io.reactivex.functions.Function
    public FiveGEntity apply(String str) throws Exception {
        UIUtils.logD("5g基站报文：" + str);
        JSONObject jSONObject = new JSONObject(str);
        FiveGEntity fiveGEntity = new FiveGEntity();
        fiveGEntity.setErrorMsg(jSONObject.optString("ErrorMsg"));
        fiveGEntity.setFiveGStationTips(jSONObject.optString("fiveGStationTips"));
        JSONArray optJSONArray = jSONObject.optJSONArray("baseStation");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                FiveGEntity.BaseStationBean baseStationBean = new FiveGEntity.BaseStationBean();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                baseStationBean.setEpJingDu(optJSONObject.optDouble("epJingDu"));
                baseStationBean.setEpWeiDu(optJSONObject.optDouble("epWeiDu"));
                arrayList.add(baseStationBean);
            }
        }
        fiveGEntity.setBaseStation(arrayList);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("experienceHall");
        ArrayList arrayList2 = new ArrayList();
        if (optJSONArray2 != null) {
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                FiveGEntity.ExperienceHallBean experienceHallBean = new FiveGEntity.ExperienceHallBean();
                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                experienceHallBean.setEpJingDu(optJSONObject2.optDouble("epJingDu"));
                experienceHallBean.setEpWeiDu(optJSONObject2.optDouble("epWeiDu"));
                experienceHallBean.setPinEpActImg(optJSONObject2.optString("PinEpActImg"));
                experienceHallBean.setId(optJSONObject2.optString("id"));
                experienceHallBean.setEhall_frontAddress(optJSONObject2.optString("ehall_frontAddress"));
                experienceHallBean.setEpName(optJSONObject2.optString("epName"));
                arrayList2.add(experienceHallBean);
            }
        }
        fiveGEntity.setExperienceHall(arrayList2);
        return fiveGEntity;
    }
}
