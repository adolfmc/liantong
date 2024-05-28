package com.sinovatech.unicom.separatemodule.comonjsplugin.zhuxiao;

import android.content.Context;
import android.util.Base64;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.nio.charset.Charset;
import java.util.Random;

@Route(path = "/MsJSPlugin/CNEncryptionRequest")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CNEncryptionRequestJSPlugin extends BaseJSPlugin {

    /* renamed from: pd */
    private CustomePorgressDialog f18516pd;
    private Random random;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        this.f18516pd = new CustomePorgressDialog(this.activityContext);
        this.f18516pd.setMessage("加载中");
        String optString = this.parameterJO.optString("encryptedfield");
        this.random = new Random();
        refreshTuxing(optString);
    }

    private void refreshTuxing(String str) {
        this.f18516pd.show();
        RequestParams requestParams = new RequestParams();
        String str2 = str + (this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9));
        try {
            requestParams.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(str2.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
        } catch (Exception e) {
            requestParams.put("mobile", EncodeHelper.encodeByAES(str2));
            e.printStackTrace();
        }
        requestParams.put("version", App.getInstance().getString(2131886969));
        App.getAsyncHttpClient().post(URLSet.getYanzhemgma(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.comonjsplugin.zhuxiao.CNEncryptionRequestJSPlugin.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                CNEncryptionRequestJSPlugin.this.f18516pd.setMessage("正在刷新请稍后");
                CNEncryptionRequestJSPlugin.this.f18516pd.show();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str3) {
                super.onSuccess(i, str3);
                try {
                    CNEncryptionRequestJSPlugin.this.callbackSuccess(str3);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    UIUtils.toast("图形验证码获取失败，请重试");
                    CNEncryptionRequestJSPlugin.this.callbackFail("");
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str3) {
                super.onFailure(th, str3);
                UIUtils.toast("图形验证码获取失败，请重试");
                CNEncryptionRequestJSPlugin.this.callbackFail("");
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                if (CNEncryptionRequestJSPlugin.this.f18516pd != null) {
                    CNEncryptionRequestJSPlugin.this.f18516pd.dismiss();
                }
            }
        });
    }
}
