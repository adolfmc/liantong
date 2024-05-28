package com.sinovatech.unicom.separatemodule.tongyicaiji;

import com.baidu.location.BDLocation;
import com.sinovatech.unicom.basic.server.CityCodingManager;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJAddressEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYCJAddressUtil {
    public static TYCJAddressEntity getAddressEntity() {
        TYCJAddressEntity tYCJAddressEntity = new TYCJAddressEntity();
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
                MsLogUtil.m7979d("TYCJAddressEntity", "latitude:" + latitude + "   longitude:" + longitude + "  cityName:" + city + "  provinceCode:" + privienceCode + "  cityCode:" + cityCode);
                tYCJAddressEntity.setLatitude(latitude);
                tYCJAddressEntity.setLongitude(longitude);
                tYCJAddressEntity.setCityName(city);
                tYCJAddressEntity.setLocateProvinceCode(privienceCode);
                tYCJAddressEntity.setLocateCityCode(cityCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tYCJAddressEntity;
    }
}
