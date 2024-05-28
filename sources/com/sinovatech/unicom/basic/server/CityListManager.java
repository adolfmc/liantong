package com.sinovatech.unicom.basic.server;

import android.content.Context;
import com.sinovatech.unicom.basic.p314po.CitySelectEntity;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CityListManager {
    final int BUFFER_SIZE = 4096;
    final String CITY_JSON_FILENAME = "city_select_json.json";
    private Context context;

    public CityListManager(Context context) {
        this.context = context;
    }

    public String getProvinceData(Context context) {
        try {
            InputStream open = context.getAssets().open("city_select_json.json");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = open.read(bArr, 0, 4096);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return new String(byteArrayOutputStream.toByteArray(), "utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CitySelectEntity> getList(String str) {
        return getList(str, false);
    }

    public List<CitySelectEntity> getList(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                CitySelectEntity citySelectEntity = new CitySelectEntity();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                citySelectEntity.setCityCode(jSONObject.optString("cityCode"));
                citySelectEntity.setCityName(jSONObject.optString("cityName"));
                citySelectEntity.setCityRank(jSONObject.optString("cityRank"));
                citySelectEntity.setPingyin(jSONObject.optString("pingyin"));
                citySelectEntity.setPrivienceCode(jSONObject.optString("privienceCode"));
                citySelectEntity.setProvienceName(jSONObject.optString("provienceName"));
                citySelectEntity.setSortLetters(jSONObject.optString("sortLetters"));
                citySelectEntity.setMapCode(jSONObject.optString("mapCode"));
                citySelectEntity.setLatitude(jSONObject.optDouble("latitude"));
                citySelectEntity.setLongitude(jSONObject.optDouble("longitude"));
                arrayList.add(citySelectEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!z) {
            Collections.sort(arrayList);
        }
        return arrayList;
    }
}
