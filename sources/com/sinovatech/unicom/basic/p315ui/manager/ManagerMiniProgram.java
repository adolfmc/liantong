package com.sinovatech.unicom.basic.p315ui.manager;

import android.support.p086v7.app.AppCompatActivity;
import cn.sharesdk.framework.Platform;
import com.sinovatech.unicom.common.UIUtils;
import com.tencent.p348mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.p348mm.opensdk.openapi.IWXAPI;
import com.tencent.p348mm.opensdk.openapi.WXAPIFactory;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMiniProgram */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerMiniProgram {
    public void openWxMiniProgramFormClient(AppCompatActivity appCompatActivity, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("miniProgramUserName", "");
            String optString2 = jSONObject.optString("miniProgramPath", "");
            String optString3 = jSONObject.optString("miniProgramType", "");
            new Platform.ShareParams();
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(appCompatActivity, "wxa13d0b8c5270d1ff");
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = optString;
            req.path = optString2;
            int i = 0;
            try {
                i = Integer.parseInt(optString3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.miniprogramType = i;
            createWXAPI.sendReq(req);
        } catch (Exception e2) {
            e2.printStackTrace();
            UIUtils.toastLong("小程序信息配置错误 无法启动小程序");
        }
    }
}
