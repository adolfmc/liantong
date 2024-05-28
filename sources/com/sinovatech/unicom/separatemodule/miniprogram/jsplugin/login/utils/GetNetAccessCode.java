package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.GetNetAccessCode;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetNetAccessCode {
    private static GetNetAccessCode instance;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface GetNetAccessCodeClick {
        void onGetData(String str);
    }

    public static GetNetAccessCode getInstance() {
        if (instance == null) {
            instance = new GetNetAccessCode();
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.GetNetAccessCode$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C92701 implements OneKeyLoginUtil.OnekeyLoginCallBack {
        final /* synthetic */ AppCompatActivity val$activityContext;
        final /* synthetic */ String val$channelCode;
        final /* synthetic */ GetNetAccessCodeClick val$getNetAccessCodeClick;

        C92701(String str, AppCompatActivity appCompatActivity, GetNetAccessCodeClick getNetAccessCodeClick) {
            this.val$channelCode = str;
            this.val$activityContext = appCompatActivity;
            this.val$getNetAccessCodeClick = getNetAccessCodeClick;
        }

        @Override // com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil.OnekeyLoginCallBack
        public void onComplete(OneKeyLoginUtil.MianMiLoginEntity mianMiLoginEntity) {
            if (mianMiLoginEntity.getCode() == 0) {
                ObservableSubscribeProxy<BaseVideoEntity<String>> userNumberByNetForOutSide = GetNetAccessCode.this.getUserNumberByNetForOutSide(mianMiLoginEntity.getAccessCode(), this.val$channelCode, this.val$activityContext);
                final GetNetAccessCodeClick getNetAccessCodeClick = this.val$getNetAccessCodeClick;
                userNumberByNetForOutSide.subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.-$$Lambda$GetNetAccessCode$1$_0KJQNPkZ14Jb56eriVJb4bjYQE
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        GetNetAccessCode.C92701.lambda$onComplete$0(GetNetAccessCode.GetNetAccessCodeClick.this, (BaseVideoEntity) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.-$$Lambda$GetNetAccessCode$1$6pOY80O8Sd7zRVms2cOBgQGx-tk
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        GetNetAccessCode.C92701.lambda$onComplete$1(GetNetAccessCode.GetNetAccessCodeClick.this, (Throwable) obj);
                    }
                });
                return;
            }
            GetNetAccessCodeClick getNetAccessCodeClick2 = this.val$getNetAccessCodeClick;
            if (getNetAccessCodeClick2 != null) {
                getNetAccessCodeClick2.onGetData("");
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onComplete$0(GetNetAccessCodeClick getNetAccessCodeClick, BaseVideoEntity baseVideoEntity) throws Exception {
            if (getNetAccessCodeClick != null) {
                if (!TextUtils.isEmpty((CharSequence) baseVideoEntity.getData())) {
                    getNetAccessCodeClick.onGetData((String) baseVideoEntity.getData());
                } else {
                    getNetAccessCodeClick.onGetData("");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onComplete$1(GetNetAccessCodeClick getNetAccessCodeClick, Throwable th) throws Exception {
            if (getNetAccessCodeClick != null) {
                getNetAccessCodeClick.onGetData("");
            }
        }
    }

    public void getSDKAccessCode(AppCompatActivity appCompatActivity, String str, GetNetAccessCodeClick getNetAccessCodeClick) {
        new OneKeyLoginUtil().start(new C92701(str, appCompatActivity, getNetAccessCodeClick));
        TYCJBoxManager.getInstance().collectClickSdk(appCompatActivity, "S2ndpage1214", "", "联通免密取号", "", "com.sdk.cp", "1");
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> getUserNumberByNetForOutSide(String str, String str2, AppCompatActivity appCompatActivity) {
        String userNumberByNetForOutSide = URLSet.getUserNumberByNetForOutSide();
        HashMap hashMap = new HashMap();
        hashMap.put("accessCode", str);
        hashMap.put("appVersion", "android");
        hashMap.put("invokeChannel", str2);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 30, 30).rxPost(userNumberByNetForOutSide, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.-$$Lambda$GetNetAccessCode$t1FIIQG5l8Kpv9MS2zNR11BmagQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GetNetAccessCode.lambda$getUserNumberByNetForOutSide$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getUserNumberByNetForOutSide$0(String str) throws Exception {
        MsLogUtil.m7980d("getUserNumberByNetForOutSide" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setData(jSONObject.optString("data"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        return baseVideoEntity;
    }
}
