package com.sinovatech.unicom.basic.p315ui.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.entity.LiBaoTiXingEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiBaoTiXingListEntity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerHomeLiBaoTiXing */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerHomeLiBaoTiXing {
    private static final String TAG = "ManagerHomeLiBaoTiXing";
    private AppCompatActivity context;

    public ManagerHomeLiBaoTiXing(AppCompatActivity appCompatActivity) {
        this.context = appCompatActivity;
    }

    public ObservableSubscribeProxy<LiBaoTiXingListEntity> getLiBaoTiXing() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.context.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getLiBaoTiXing(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.basic.ui.manager.-$$Lambda$ManagerHomeLiBaoTiXing$d6EQtsAxiogTVMh4SNpthajQ_k0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerHomeLiBaoTiXing.lambda$getLiBaoTiXing$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LiBaoTiXingListEntity lambda$getLiBaoTiXing$0(String str) throws Exception {
        JSONObject jSONObject;
        LiBaoTiXingListEntity liBaoTiXingListEntity = new LiBaoTiXingListEntity();
        JSONObject jSONObject2 = new JSONObject(str);
        liBaoTiXingListEntity.setMessage(jSONObject2.optString("desc", ""));
        liBaoTiXingListEntity.setStatusCode(jSONObject2.optString("code", ""));
        JSONObject optJSONObject = jSONObject2.optJSONObject("data");
        if (optJSONObject != null) {
            String optString = optJSONObject.optString("callBackUrl", "");
            JSONArray optJSONArray = optJSONObject.optJSONArray("records");
            if (optJSONArray != null && optJSONArray.length() > 0 && (jSONObject = optJSONArray.getJSONObject(0)) != null) {
                ArrayList arrayList = new ArrayList();
                LiBaoTiXingEntity liBaoTiXingEntity = new LiBaoTiXingEntity();
                liBaoTiXingEntity.setCallBackUrl(optString);
                liBaoTiXingEntity.setActiveName(jSONObject.optString("activeName", ""));
                liBaoTiXingEntity.setPrizeName(jSONObject.optString("prizeName", ""));
                liBaoTiXingEntity.setPrizeUrl(jSONObject.optString("prizeUrl", ""));
                arrayList.add(liBaoTiXingEntity);
                liBaoTiXingListEntity.setData(arrayList);
            }
        }
        return liBaoTiXingListEntity;
    }
}
