package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.DateUtils;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.MD5Tools;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.BaseRequestManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaseRequestManager<T> {
    public static final String CACHE = "2";
    public static final String NET = "1";
    protected final AppCompatActivity activityContext;
    private final CacheDataCenter cacheDataCenter = CacheDataCenter.getInstance();
    private String cacheKey;
    private BaseFunction<T> function;
    private Observable<String> netRequestOb;
    private String requestType;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.sinovatech.unicom.basic.ui.home.manager.BaseRequestManager$RequestType */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface RequestType {
    }

    public BaseRequestManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    private void resetParams() {
        this.requestType = null;
        this.netRequestOb = null;
        this.function = null;
        this.cacheKey = null;
    }

    public BaseRequestManager<T> setRequestType(String str) {
        resetParams();
        this.requestType = str;
        return this;
    }

    public BaseRequestManager<T> setNetObserver(Observable<String> observable) {
        this.netRequestOb = observable;
        return this;
    }

    public BaseRequestManager<T> setCacheKey(String str, String str2) {
        this.cacheKey = MD5Tools.encode(str + str2);
        return this;
    }

    public BaseRequestManager<T> setFunction(BaseFunction<T> baseFunction) {
        this.function = baseFunction;
        return this;
    }

    public String getCacheData() {
        return CacheDataCenter.getInstance().getBaseRequestCache(this.cacheKey);
    }

    public Map<String, String> getHeader() {
        String string = this.activityContext.getString(2131886969);
        String uuid = RecentCustomManager.uuid();
        String timeStamp2DateRequset = DateUtils.timeStamp2DateRequset();
        String str = string + "&" + uuid + "&" + timeStamp2DateRequset;
        if (timeStamp2DateRequset.endsWith("1") || timeStamp2DateRequset.endsWith("4") || timeStamp2DateRequset.endsWith("7")) {
            str = timeStamp2DateRequset + WPBusinessInfoBean.SPLIT + string + WPBusinessInfoBean.SPLIT + uuid;
        }
        if (timeStamp2DateRequset.endsWith("2") || timeStamp2DateRequset.endsWith("5") || timeStamp2DateRequset.endsWith("8")) {
            str = uuid + "$" + timeStamp2DateRequset + "$" + string;
        }
        MsLogUtil.m7979d("getHeader--", "前： " + str);
        String encoderByMd5_32 = EncodeHelper.encoderByMd5_32(str);
        MsLogUtil.m7979d("getHeader--", "后： " + encoderByMd5_32);
        HashMap hashMap = new HashMap();
        hashMap.put("cVersion", string);
        hashMap.put("cTransId", uuid);
        hashMap.put("cReqTime", timeStamp2DateRequset);
        hashMap.put("cSign", encoderByMd5_32);
        return hashMap;
    }

    public ObservableSubscribeProxy<T> getObservable() {
        Observable<String> just;
        if (this.function == null) {
            throw new IllegalArgumentException("需要设置Function");
        }
        String str = this.requestType;
        char c = 65535;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c = 1;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c = 0;
                    break;
                }
                break;
        }
        if (c == 0) {
            if (TextUtils.isEmpty(this.cacheKey)) {
                throw new IllegalArgumentException("使用缓存功能需要使用缓存key,请进行设置");
            }
            just = Observable.just(CacheDataCenter.getInstance().getBaseRequestCache(this.cacheKey));
        } else {
            just = this.netRequestOb;
            if (just == null) {
                throw new IllegalArgumentException("使用网络请求需要设置Rx请求的Observer");
            }
        }
        return (ObservableSubscribeProxy) just.subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.BaseRequestManager.1
            @Override // io.reactivex.functions.Function
            public String apply(String str2) throws Exception {
                if (TextUtils.isEmpty(str2)) {
                    return "";
                }
                JSONObject jSONObject = new JSONObject(str2);
                String optString = jSONObject.optString("cacheTime");
                String optString2 = jSONObject.optString("respTime");
                if ("2".equals(BaseRequestManager.this.requestType)) {
                    try {
                        if (!TextUtils.isEmpty(optString) && !TextUtils.equals(optString, String.valueOf(Integer.MAX_VALUE))) {
                            if ((SystemTimeUtil.currentTimeMillis() - Long.parseLong(optString2)) / 1000 > Long.parseLong(optString)) {
                                BaseRequestManager.this.cacheDataCenter.setBaseRequestCache(BaseRequestManager.this.cacheKey, "");
                                return "";
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if ("1".equals(BaseRequestManager.this.requestType)) {
                    if (TextUtils.equals(optString, "0")) {
                        BaseRequestManager.this.cacheDataCenter.setBaseRequestCache(BaseRequestManager.this.cacheKey, "");
                    }
                    boolean z = true;
                    boolean z2 = !TextUtils.equals(optString, "0");
                    boolean z3 = !TextUtils.isEmpty(BaseRequestManager.this.cacheKey);
                    if (!"0000".equals(jSONObject.optString("respCode")) && !"0000".equals(jSONObject.optString("code"))) {
                        z = false;
                    }
                    if (z2 && z3 && z) {
                        BaseRequestManager.this.cacheDataCenter.setBaseRequestCache(BaseRequestManager.this.cacheKey, str2);
                    }
                }
                return str2;
            }
        }).map(this.function).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }
}
