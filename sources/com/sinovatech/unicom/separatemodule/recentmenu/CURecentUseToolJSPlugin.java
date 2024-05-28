package com.sinovatech.unicom.separatemodule.recentmenu;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/CURecentUseTool")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CURecentUseToolJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("type");
        JSONObject optJSONObject = this.parameterJO.optJSONObject("parameters");
        if (optJSONObject == null) {
            callbackFail("1", "参数为空");
            return;
        }
        RecentMiniEntity recentMiniEntity = RecentMiniEntity.getInstance(optJSONObject);
        recentMiniEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
        char c = 65535;
        int hashCode = optString.hashCode();
        if (hashCode != -1335458389) {
            if (hashCode != 3522941) {
                if (hashCode == 103877850 && optString.equals("deleteByAppId")) {
                    c = 2;
                }
            } else if (optString.equals("save")) {
                c = 0;
            }
        } else if (optString.equals("delete")) {
            c = 1;
        }
        switch (c) {
            case 0:
                if (TextUtils.isEmpty(recentMiniEntity.getAppId()) || TextUtils.isEmpty(recentMiniEntity.getAppName()) || TextUtils.isEmpty(recentMiniEntity.getAppletUrl()) || TextUtils.isEmpty(recentMiniEntity.getAppImg()) || TextUtils.isEmpty(recentMiniEntity.getProductId())) {
                    callbackFail("1", "参数为空");
                    return;
                }
                RecentBoxManager.getInstance().put(recentMiniEntity);
                callbackSuccess("0", "添加成功", new JSONObject());
                return;
            case 1:
                if (TextUtils.isEmpty(recentMiniEntity.getAppId()) || TextUtils.isEmpty(recentMiniEntity.getAppName()) || TextUtils.isEmpty(recentMiniEntity.getAppletUrl()) || TextUtils.isEmpty(recentMiniEntity.getAppImg()) || TextUtils.isEmpty(recentMiniEntity.getProductId())) {
                    callbackFail("1", "参数为空");
                    return;
                }
                RecentBoxManager.getInstance().reMove(recentMiniEntity);
                callbackSuccess("0", "删除成功", new JSONObject());
                return;
            case 2:
                RecentBoxManager.getInstance().removeAppId(recentMiniEntity.getAppId());
                callbackSuccess("0", "删除成功", new JSONObject());
                return;
            default:
                return;
        }
    }
}
