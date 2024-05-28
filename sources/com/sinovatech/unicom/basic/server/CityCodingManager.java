package com.sinovatech.unicom.basic.server;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.CitySelectEntity;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CityCodingManager {
    public static String privienceCode(Context context, String str) {
        List<CitySelectEntity> list;
        CityListManager cityListManager = new CityListManager(context);
        String cityData = CacheDataCenter.getInstance().getCityData();
        if ("".equals(cityData) || TextUtils.isEmpty(cityData)) {
            list = cityListManager.getList(cityListManager.getProvinceData(context), true);
        } else {
            list = cityListManager.getList(cityData);
        }
        if (TextUtils.isEmpty(str)) {
            return CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!TextUtils.isEmpty(str) && str.equals(list.get(i).getCityName())) {
                return list.get(i).getPrivienceCode();
            }
        }
        return CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE;
    }

    public static String cityCode(Context context, String str) {
        List<CitySelectEntity> list;
        CityListManager cityListManager = new CityListManager(context);
        String cityData = CacheDataCenter.getInstance().getCityData();
        if ("".equals(cityData) || TextUtils.isEmpty(cityData)) {
            list = cityListManager.getList(cityListManager.getProvinceData(context), true);
        } else {
            list = cityListManager.getList(cityData);
        }
        if (TextUtils.isEmpty(str)) {
            return CityChangeManager.DEFAULT_SELECT_CITY_CODE;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!TextUtils.isEmpty(str) && str.equals(list.get(i).getCityName())) {
                return list.get(i).getCityCode();
            }
        }
        return CityChangeManager.DEFAULT_SELECT_CITY_CODE;
    }
}
