package com.sinovatech.unicom.separatemodule.onekeylogin;

import com.sdk.base.api.CallBack;
import com.sdk.base.api.ToolUtils;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.mobile.manager.login.cucc.UiOauthManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OneKeyLoginUtil {
    public static final String apiKey = "7a09e16b3d418105a2aa7fcbfd1bd7547a016ad2";
    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZCdjaX9NlW6/TVvA+5fYQgMBI88nRnD/CeNAoE3OGEA6wdHACFxvZ75ZHQ+6IuasRG+gai72lK9mSYaquv+KI9OAX0kecrn7ez/nEjDcPxGeAJ7y1rU64yjFdx5A4OjtFW8YXlWGcJ83UTyYz735L3JVv5NWZrFInJmfT+l01+wIDAQAB";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnekeyLoginCallBack {
        void onComplete(MianMiLoginEntity mianMiLoginEntity);
    }

    public void start(final OnekeyLoginCallBack onekeyLoginCallBack) {
        Observable.create(new ObservableOnSubscribe<MianMiLoginEntity>() { // from class: com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil.3
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(final ObservableEmitter<MianMiLoginEntity> observableEmitter) throws Exception {
                final MianMiLoginEntity mianMiLoginEntity = new MianMiLoginEntity();
                SDKManager.setDebug(false);
                SDKManager.setUseCache(false);
                SDKManager.setSupport_GM(true);
                SDKManager.setSupport3net(true);
                if (SDKManager.isSupport_GM()) {
                    OneKeyLoginUtil.addBouncyCastlelib();
                }
                SDKManager.init(App.getInstance(), OneKeyLoginUtil.apiKey, OneKeyLoginUtil.publicKey);
                ToolUtils.clearCache(App.getInstance());
                UiOauthManager.getInstance(App.getInstance()).login(5, new CallBack<Object>() { // from class: com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil.3.1
                    @Override // com.sdk.base.api.CallBack
                    public void onSuccess(int i, String str, int i2, Object obj, String str2) {
                        StringBuilder sb;
                        try {
                            sb = new StringBuilder();
                            sb.append("免密登录>>>onSuccess：");
                            sb.append(i);
                            sb.append(" ");
                            sb.append(str);
                            sb.append(" ");
                            sb.append(i2);
                            sb.append(" ");
                        } catch (Exception e) {
                            e = e;
                        }
                        try {
                            sb.append(str2);
                            MsLogUtil.m7980d(sb.toString());
                            ToolUtils.clearCache(App.getInstance());
                            mianMiLoginEntity.setCode(i);
                            mianMiLoginEntity.setMsg(str);
                            if (i == 0) {
                                JSONObject jSONObject = new JSONObject(obj.toString());
                                String string = jSONObject.getString("fakeMobile");
                                String string2 = jSONObject.getString("accessCode");
                                long optLong = jSONObject.optLong("exp");
                                int optInt = jSONObject.optInt("operator");
                                mianMiLoginEntity.setAccessCode(string2);
                                mianMiLoginEntity.setFakeMobile(string);
                                mianMiLoginEntity.setExp(optLong);
                                mianMiLoginEntity.setOperator(optInt);
                            }
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            mianMiLoginEntity.setCode(-1);
                            mianMiLoginEntity.setMsg("解析数据失败");
                            PvCurrencyLogUtils.pvCurrencyLog("", 2, "upload_onekey", str2, "", i + " " + str, "", i2 + "", "", "");
                            observableEmitter.onNext(mianMiLoginEntity);
                            observableEmitter.onComplete();
                        }
                        PvCurrencyLogUtils.pvCurrencyLog("", 2, "upload_onekey", str2, "", i + " " + str, "", i2 + "", "", "");
                        observableEmitter.onNext(mianMiLoginEntity);
                        observableEmitter.onComplete();
                    }

                    @Override // com.sdk.base.api.CallBack
                    public void onFailed(int i, int i2, String str, String str2) {
                        MsLogUtil.m7980d("免密登录>>>onFailed：" + i + " " + i2 + " " + str + " " + str2);
                        StringBuilder sb = new StringBuilder();
                        sb.append(i);
                        sb.append(" ");
                        sb.append(str);
                        String sb2 = sb.toString();
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i2);
                        sb3.append("");
                        PvCurrencyLogUtils.pvCurrencyLog("", 2, "upload_onekey", str2, "", sb2, "", sb3.toString(), "", "");
                        mianMiLoginEntity.setMsg(str);
                        mianMiLoginEntity.setCode(-2);
                        observableEmitter.onNext(mianMiLoginEntity);
                        observableEmitter.onComplete();
                    }
                });
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MianMiLoginEntity>() { // from class: com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil.1
            @Override // io.reactivex.functions.Consumer
            public void accept(MianMiLoginEntity mianMiLoginEntity) throws Exception {
                onekeyLoginCallBack.onComplete(mianMiLoginEntity);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                MianMiLoginEntity mianMiLoginEntity = new MianMiLoginEntity();
                mianMiLoginEntity.setCode(-3);
                onekeyLoginCallBack.onComplete(mianMiLoginEntity);
                PvCurrencyLogUtils.pvCurrencyLog("", 2, "upload_onekey", "", "", th.getMessage(), "", "", "", "");
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class MianMiLoginEntity {
        private String accessCode;
        private int code;
        private long exp;
        private String fakeMobile;
        private String msg;
        private int operator;

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public String getAccessCode() {
            return this.accessCode;
        }

        public void setAccessCode(String str) {
            this.accessCode = str;
        }

        public String getFakeMobile() {
            return this.fakeMobile;
        }

        public void setFakeMobile(String str) {
            this.fakeMobile = str;
        }

        public long getExp() {
            return this.exp;
        }

        public void setExp(long j) {
            this.exp = j;
        }

        public int getOperator() {
            return this.operator;
        }

        public void setOperator(int i) {
            this.operator = i;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }
    }

    public static void addBouncyCastlelib() {
        if (Security.getProvider("BC") != null && Security.getProvider("BC").getVersion() < 1.69d) {
            Security.removeProvider("BC");
        }
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }
}
