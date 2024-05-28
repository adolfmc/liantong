package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getNetAccessCode")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetNetAccessCodeJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String str;
        String str2;
        try {
            new OneKeyLoginUtil().start(new OneKeyLoginUtil.OnekeyLoginCallBack() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.GetNetAccessCodeJSPlugin.1
                @Override // com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil.OnekeyLoginCallBack
                public void onComplete(OneKeyLoginUtil.MianMiLoginEntity mianMiLoginEntity) {
                    try {
                        if (mianMiLoginEntity.getCode() == 0) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("accessCode", mianMiLoginEntity.getAccessCode());
                            jSONObject.put("fakeMobile", mianMiLoginEntity.getFakeMobile());
                            GetNetAccessCodeJSPlugin.this.callbackSuccess(jSONObject);
                        } else {
                            GetNetAccessCodeJSPlugin getNetAccessCodeJSPlugin = GetNetAccessCodeJSPlugin.this;
                            getNetAccessCodeJSPlugin.callbackFail(mianMiLoginEntity.getCode() + " " + mianMiLoginEntity.getMsg());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetNetAccessCodeJSPlugin.this.callbackFail(e.getMessage());
                    }
                }
            });
            if (this.f18589wv != null) {
                String title = TextUtils.isEmpty(this.f18589wv.getTitle()) ? "" : this.f18589wv.getTitle();
                if (TextUtils.isEmpty(this.f18589wv.getUrl())) {
                    str = title;
                    str2 = "";
                } else {
                    str = title;
                    str2 = this.f18589wv.getUrl();
                }
            } else {
                str = "";
                str2 = "";
            }
            TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", str, "联通免密取号", str2, "com.sdk.cp", "0");
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail(e.getMessage());
        }
    }
}
