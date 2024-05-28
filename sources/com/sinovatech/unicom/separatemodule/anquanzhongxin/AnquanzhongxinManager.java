package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AnquanzhongxinManager {
    private static final String TAG = "AnquanzhongxinManager";
    private AppCompatActivity activityContext;
    private String user_id = "2494be02170bc68df17203deddaeacc289c9b7e55adaa03447cbd37248f9a89dcf6298d2b16b9564288915b8ea45d901";
    private String user_id_test = "2494be02170bc68df17203deddaeacc289c9b7e55adaa03447cbd37248f9a89d2073953114a2e43ece2b4fe8b291296f";

    public AnquanzhongxinManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void getWhiteList(Consumer<List<AnquanWhiteEntity>> consumer, Consumer<Throwable> consumer2) {
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getAnquanzhognxinlistPage(), new HashMap()).subscribeOn(Schedulers.m1934io()).map(new Function<String, List<AnquanWhiteEntity>>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinManager.1
            @Override // io.reactivex.functions.Function
            public List<AnquanWhiteEntity> apply(@NonNull String str) throws Exception {
                String str2 = AnquanzhongxinManager.TAG;
                MsLogUtil.m7979d(str2, "获取白名单：" + str);
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("code");
                if (TextUtils.isEmpty(optString)) {
                    throw new Exception();
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                ArrayList arrayList = new ArrayList();
                if ("0000".equals(optString) && optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            AnquanWhiteEntity anquanWhiteEntity = new AnquanWhiteEntity();
                            anquanWhiteEntity.setCode(optJSONObject.optString("code"));
                            anquanWhiteEntity.setName(optJSONObject.optString("name"));
                            anquanWhiteEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
                            arrayList.add(anquanWhiteEntity);
                        }
                    }
                }
                return arrayList;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, consumer2);
    }

    public void getGenerateKey(String str, String str2, String str3, String str4, Consumer<String> consumer, Consumer<Throwable> consumer2) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
            UIUtils.toast("您输入的验证码为空");
            try {
                consumer2.accept(new Exception());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("userMobile", UserManager.getInstance().getCurrentPhoneNumber());
        hashMap.put("vCode", str);
        hashMap.put("verification", str3);
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put(App.getInstance().getString(2131886495), DeviceHelper.getAndroidId());
        hashMap.put("provinceCode", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("cityCode", UserManager.getInstance().getCurrentCityCode());
        hashMap.put("sign", str2);
        hashMap.put(WPPayInfoBean.EVENT_TYPE_CHECK, str4);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getAnquanzhognxingenerate(), hashMap).subscribeOn(Schedulers.m1934io()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinManager.2
            @Override // io.reactivex.functions.Function
            public String apply(@NonNull String str5) throws Exception {
                String str6 = AnquanzhongxinManager.TAG;
                MsLogUtil.m7979d(str6, "安全校验接口：" + str5);
                return str5;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, consumer2);
    }

    public void sendSms() {
        HashMap hashMap = new HashMap();
        Random random = new Random();
        try {
            hashMap.put("mobile", EncodeHelper.encodeByAESAnquanzhongxin(UserManager.getInstance().getCurrentPhoneNumber() + (random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            hashMap.put("user_id", this.user_id_test);
        } else {
            hashMap.put("user_id", this.user_id);
        }
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getAnquanzhognxinSMS(), hashMap).subscribeOn(Schedulers.m1934io()).map(new Function<String, Object>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinManager.5
            @Override // io.reactivex.functions.Function
            public Object apply(@NonNull String str) throws Exception {
                String str2 = AnquanzhongxinManager.TAG;
                MsLogUtil.m7979d(str2, "发送短信：" + str);
                return str;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinManager.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinManager.4
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                MsLogUtil.m7979d(AnquanzhongxinManager.TAG, th.getMessage());
            }
        });
    }
}
