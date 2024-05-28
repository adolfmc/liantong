package com.sinovatech.unicom.separatemodule.baidumap;

import android.app.Activity;
import com.baidu.mapapi.model.LatLng;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessEntity;
import com.sinovatech.unicom.separatemodule.baidumap.parser.BusinessJsonDataParser;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BusinessDataLoader {
    public static void loadYingyetingData(Activity activity, LatLng latLng, LatLng latLng2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Observer<BusinessEntity> observer) {
        try {
            if (latLng != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("longitude", String.valueOf(latLng.longitude));
                hashMap.put("latitude", String.valueOf(latLng.latitude));
                hashMap.put("destination_jd", String.valueOf(latLng2.longitude));
                hashMap.put("destination_wd", String.valueOf(latLng2.latitude));
                hashMap.put("beginNum", str);
                hashMap.put("deviceNo", str2);
                hashMap.put("titleFlag", str3);
                hashMap.put("labelFlag", str4);
                hashMap.put("labelDetail", str5);
                hashMap.put("provinceCode", str7);
                hashMap.put("cityCode", str6);
                hashMap.put("chargeFlag", str8);
                hashMap.put("version", activity.getString(2131886969));
                UIUtils.logD("列表URL：" + URLSet.getEhallList());
                UIUtils.logD("列表入参：" + hashMap);
                App.getAsyncHttpClient().rxGet(URLSet.getEhallList(), hashMap, 0, 0).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new C85361()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
            } else {
                UIUtils.toast("您还没有定位当前位置~");
            }
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toastLong("程序错误【加载附近营业厅数据:" + e.getMessage() + "】");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.BusinessDataLoader$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C85361 implements Function<String, BusinessEntity> {
        @Override // io.reactivex.functions.Function
        public BusinessEntity apply(String str) throws Exception {
            UIUtils.logD("列表报文：" + str);
            return BusinessJsonDataParser.parseSingleBusiness(new JSONObject(str));
        }
    }
}
