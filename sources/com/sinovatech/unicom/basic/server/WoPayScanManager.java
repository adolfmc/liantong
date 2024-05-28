package com.sinovatech.unicom.basic.server;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WoPayScanManager {
    private final String TAG = "WoPayScanManager";
    private Context context;

    public WoPayScanManager(Context context) {
        this.context = context;
    }

    public List<String> getWoPayScanWhiteList() {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(App.getSharePreferenceUtil().getString("WoPayScanWhiteList")).getJSONArray("data");
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(string);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void loadWoPayScanWhiteList() {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "woScanGet");
        hashMap.put("provinceCode", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("cityCode", UserManager.getInstance().getUserAreaid());
        hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
        hashMap.put("version", this.context.getString(2131886969));
        App.getAsyncHttpClient().rxGet(URLSet.getDataFromService(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.basic.server.WoPayScanManager.3
            @Override // io.reactivex.functions.Function
            public String apply(String str) throws Exception {
                App.getSharePreferenceUtil().putString("WoPayScanWhiteList", str);
                return "";
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.server.WoPayScanManager.1
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) throws Exception {
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.server.WoPayScanManager.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
    }

    public String assembleWoPayURL(String str) {
        return URLSet.getHttpPrefix() + URLSet.ApplicationServer_URL + "thirdRedirect.htm?redirect_uri=" + str;
    }
}
