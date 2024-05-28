package com.sinovatech.unicom.separatemodule.huidu;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerHuiDu {
    public static String TAG = "HuiDu";
    private static ManagerHuiDu instance;

    public static synchronized ManagerHuiDu getInstance() {
        ManagerHuiDu managerHuiDu;
        synchronized (ManagerHuiDu.class) {
            if (instance == null) {
                synchronized (ManagerHuiDu.class) {
                    if (instance == null) {
                        instance = new ManagerHuiDu();
                    }
                }
            }
            managerHuiDu = instance;
        }
        return managerHuiDu;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class HuiduConfig {
        public String headers = "";
        public List<String> whiteDomainList = new ArrayList();
        public JSONObject fePathJO = new JSONObject();

        public HuiduConfig() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class HuiDuWebViewURLConfig {
        public boolean enableHuiDu = false;
        public String huiduURL = "";

        public HuiDuWebViewURLConfig() {
        }
    }

    public void loadHuiduConfig() {
        try {
            clearHuiduConfig();
            HashMap hashMap = new HashMap();
            hashMap.put("mobile", App.hasLogined() ? UserManager.getInstance().getCurrentPhoneNumber() : "");
            hashMap.put("province", App.hasLogined() ? UserManager.getInstance().getCurrentProvinceCode() : "");
            hashMap.put("uuid", DeviceHelper.getDeviceCode());
            App.getAsyncHttpClient().rxPost(URLSet.getHuiDuConfig(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu.1
                @Override // io.reactivex.functions.Consumer
                public void accept(String str) throws Exception {
                    JSONObject optJSONObject;
                    try {
                        String str2 = ManagerHuiDu.TAG;
                        MsLogUtil.m7979d(str2, "灰度配置URL：" + URLSet.getHuiDuConfig() + " 请求返回结果:" + str);
                        JSONObject jSONObject = new JSONObject(str);
                        if (!"0000".equals(jSONObject.optString("code")) || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
                            return;
                        }
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("fePath");
                        JSONArray optJSONArray = optJSONObject.optJSONArray("tags");
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("bePath");
                        HuiduConfig huiduConfig = new HuiduConfig();
                        huiduConfig.fePathJO = optJSONObject2;
                        huiduConfig.headers = ManagerHuiDu.this.parseTags(optJSONArray);
                        huiduConfig.whiteDomainList = ManagerHuiDu.this.parseBepath(optJSONArray2);
                        App.getInstance().huiduConfig = huiduConfig;
                    } catch (Exception e) {
                        String str3 = ManagerHuiDu.TAG;
                        MsLogUtil.m7979d(str3, "灰度接口请求错误 " + e.getMessage());
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str = ManagerHuiDu.TAG;
                    MsLogUtil.m7979d(str, "灰度接口请求错误 " + th.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearHuiduConfig() {
        App.getInstance().huiduConfig = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String parseTags(JSONArray jSONArray) {
        String str = "";
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        str = i == jSONArray.length() - 1 ? str + jSONArray.getString(i) : str + jSONArray.getString(i) + ",";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> parseBepath(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public HuiDuWebViewURLConfig getHuiduURLConfig(String str) {
        HuiDuWebViewURLConfig huiDuWebViewURLConfig = new HuiDuWebViewURLConfig();
        try {
            huiDuWebViewURLConfig.enableHuiDu = false;
            huiDuWebViewURLConfig.huiduURL = str;
            if (App.getInstance().huiduConfig != null && App.getInstance().huiduConfig.fePathJO != null) {
                JSONObject jSONObject = App.getInstance().huiduConfig.fePathJO;
                Iterator<String> keys = jSONObject.keys();
                while (true) {
                    if (!keys.hasNext()) {
                        break;
                    }
                    String next = keys.next();
                    String string = jSONObject.getString(next);
                    if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(string) && str.startsWith(next) && !next.equals(string)) {
                        huiDuWebViewURLConfig.enableHuiDu = true;
                        huiDuWebViewURLConfig.huiduURL = string + str.substring(next.length());
                        break;
                    }
                }
            }
            MsLogUtil.m7979d(TAG, "灰度URL配置检测 灰度URL：" + huiDuWebViewURLConfig.huiduURL + " 原始URL：" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return huiDuWebViewURLConfig;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class HuiDuHeaderInterceptor implements Interceptor {
        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder newBuilder = request.newBuilder();
            try {
                String huiduHeader = ManagerHuiDu.getInstance().getHuiduHeader(request.url().host());
                if (!TextUtils.isEmpty(huiduHeader)) {
                    String str = ManagerHuiDu.TAG;
                    MsLogUtil.m7979d(str, "灰度http header追加 " + huiduHeader + " Url=" + request.url().toString());
                    newBuilder.addHeader("grayflag", huiduHeader);
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    String str2 = ManagerHuiDu.TAG;
                    MsLogUtil.m7979d(str2, "灰度http拦截器错误 " + e.getMessage() + " " + request.url().toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return chain.proceed(newBuilder.build());
        }
    }

    public String getHuiduHeader(String str) {
        if (App.getInstance() == null || App.getInstance().huiduConfig == null) {
            return "";
        }
        String str2 = App.getInstance().huiduConfig.headers;
        List<String> list = App.getInstance().huiduConfig.whiteDomainList;
        return (list == null || list.size() <= 0 || TextUtils.isEmpty(str2) || !list.contains(str)) ? "" : str2;
    }
}
