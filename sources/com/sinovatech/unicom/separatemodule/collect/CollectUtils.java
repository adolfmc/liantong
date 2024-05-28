package com.sinovatech.unicom.separatemodule.collect;

import com.baidu.location.BDLocation;
import com.sinovatech.unicom.basic.server.CityCodingManager;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectUtils {
    public String formatTime(long j, String str) {
        try {
            return new SimpleDateFormat(str, Locale.CHINA).format(new Date(j));
        } catch (Exception e) {
            MsLogUtil.m7979d("UnicomCollectManager", e.getMessage());
            return "";
        }
    }

    public CollectAddressEntity getAddressEntity() {
        CollectAddressEntity collectAddressEntity = new CollectAddressEntity();
        try {
            ManagerLocation.LocationEntity locationEntity = ManagerLocation.getInstance().getLocationEntity();
            if (locationEntity.isLocationSuccess()) {
                BDLocation bdLocation = locationEntity.getBdLocation();
                double latitude = bdLocation.getLatitude();
                double longitude = bdLocation.getLongitude();
                String city = bdLocation.getCity();
                if ("市".equals(city.substring(city.length() - 1, city.length()))) {
                    city = city.substring(0, city.length() - 1);
                }
                String privienceCode = CityCodingManager.privienceCode(App.getInstance(), city);
                String cityCode = CityCodingManager.cityCode(App.getInstance(), city);
                MsLogUtil.m7979d("UnicomCollectManager", "latitude:" + latitude + "   longitude:" + longitude + "  cityName:" + city + "  provinceCode:" + privienceCode + "  cityCode:" + cityCode);
                collectAddressEntity.setLatitude(latitude);
                collectAddressEntity.setLongitude(longitude);
                collectAddressEntity.setCityName(city);
                collectAddressEntity.setLocateProvinceCode(privienceCode);
                collectAddressEntity.setLocateCityCode(cityCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collectAddressEntity;
    }

    public JSONObject mapToJson(Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("UnicomCollectManager", "数据转换json失败" + e.getMessage());
            return new JSONObject();
        }
    }

    public void saveTransId(String str) {
        App.getSharePreferenceUtil().putString(CollectConfig.TRANS_ID_KEY, str);
    }

    public String getTransId() {
        return App.getSharePreferenceUtil().getString(CollectConfig.TRANS_ID_KEY);
    }
}
