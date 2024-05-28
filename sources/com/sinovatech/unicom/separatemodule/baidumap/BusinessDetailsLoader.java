package com.sinovatech.unicom.separatemodule.baidumap;

import android.app.Activity;
import com.baidu.mapapi.model.LatLng;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessDetailsEntity;
import com.sinovatech.unicom.separatemodule.baidumap.parser.BusinessDetailsJsonDataParser;
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
public class BusinessDetailsLoader {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.BusinessDetailsLoader$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C85371 implements Function<String, BusinessDetailsEntity> {
        @Override // io.reactivex.functions.Function
        public BusinessDetailsEntity apply(String str) throws Exception {
            UIUtils.logD("详情报文：" + str);
            return BusinessDetailsJsonDataParser.parseSingleBusinessDetails(new JSONObject(str));
        }
    }

    public static void loadYingyetingDetails(Activity activity, LatLng latLng, String str, String str2, Observer<BusinessDetailsEntity> observer) {
        try {
            if (latLng != null || str != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("mejd", String.valueOf(latLng.longitude));
                hashMap.put("mewd", String.valueOf(latLng.latitude));
                hashMap.put("ehallId", str);
                hashMap.put("type", str2);
                hashMap.put("version", activity.getString(2131886969));
                UIUtils.logD("详情URL：" + URLSet.getStaionDetailUrl());
                UIUtils.logD("详情入参：" + hashMap);
                App.getAsyncHttpClient().rxGet(URLSet.getStaionDetailUrl(), hashMap, 0, 0).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new C85371()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
            } else {
                UIUtils.toast("营业厅还没有加载完成~");
            }
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toastLong("加载营业厅详情数据,程序错误【" + e.getMessage() + "】");
        }
    }
}
