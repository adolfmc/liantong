package com.sinovatech.unicom.basic.p315ui.manager;

import android.content.Context;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.datacenter.AreaDataCenter;
import com.sinovatech.unicom.basic.p314po.AreaEntity;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerHomeOMO */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerHomeOMO {
    private static final String TAG = "ManagerHomeOMO";
    private static List<AreaEntity> areaList;
    private AppCompatActivity context;
    private boolean isSaveHuanCun = false;
    private String resultNet = "";

    public ManagerHomeOMO(AppCompatActivity appCompatActivity) {
        this.context = appCompatActivity;
    }

    public static void initOMO(AppCompatActivity appCompatActivity, final String str, String str2, final String str3) {
        try {
            if (TextUtils.isEmpty(str)) {
                MsLogUtil.m7979d("登录---->", "日志调用入参:号码为空所以不调用接口了");
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("appSerial", System.currentTimeMillis() + "");
            hashMap.put("appVersion", appCompatActivity.getString(2131886969));
            hashMap.put("appId", DeviceHelper.getDeviceID(false));
            hashMap.put("serialNumber", EncodeHelper.encodeByAESOMO(str));
            hashMap.put("type", str2);
            String str4 = URLSet.getInitOMO() + "/" + System.currentTimeMillis() + "/" + appCompatActivity.getString(2131886969) + "/" + DeviceHelper.getDeviceID(false) + "/" + EncodeHelper.encodeByAESOMO(str) + "/" + str2;
            MsLogUtil.m7979d("登录---->", "日志调用入参:" + str4 + ",phone = " + str + ",type=" + str2 + ",actCode=" + str3);
            App.getAsyncHttpClient().rxGet(str4, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeOMO.3
                @Override // io.reactivex.functions.Function
                public String apply(String str5) {
                    try {
                        if (!TextUtils.isEmpty(str5)) {
                            MsLogUtil.m7979d("登录---->", "日志调用结果:" + str5);
                            if (!new JSONObject(str5).optString("code").equals("0000")) {
                                PvCurrencyLogUtils.pvLogInitOmo(str, str3);
                            }
                        } else {
                            MsLogUtil.m7979d("登录---->", "日志调用结果:返回为null或者空");
                        }
                        return "";
                    } catch (Exception e) {
                        MsLogUtil.m7979d("登录---->", "日志调用结果:异常报错了，" + e.getMessage());
                        return "";
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeOMO.1
                @Override // io.reactivex.functions.Consumer
                public void accept(String str5) throws Exception {
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeOMO.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("登录---->", "日志调用结果:catch," + e.getMessage());
        }
    }

    public static String getAreaCode(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (areaList == null) {
            areaList = AreaDataCenter.getAreaList(context);
        }
        for (int i = 0; i < areaList.size(); i++) {
            if (str.equals(areaList.get(i).getAreaid())) {
                return areaList.get(i).getAreacode() + str2;
            }
        }
        return null;
    }
}
