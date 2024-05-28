package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.navmenu;

import android.content.Context;
import android.util.Log;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.home.view.UnicomJinGangQuView;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.PreferenceConstUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/clientNavMenu")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ClientNavMenuJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String string = this.parameterJO.getString("type");
            if (string.equals("getAllNavMenu")) {
                getAllNavMenu();
            } else if (string.equals("getChangYongNavMenu")) {
                getChangYongNavMenu();
            } else if (string.equals("getHomeCustomNavMenu")) {
                getHomeCustomNavMenu();
            } else if (string.equals("updateHomeCustomNavMenu")) {
                String string2 = this.parameterJO.getString("value");
                App.getSharePreferenceUtil().putBoolean(PreferenceConstUtils.hasCustomflgNew(), true);
                App.getSharePreferenceUtil().putBoolean(PreferenceConstUtils.hasCustomflg(), false);
                UnicomJinGangQuView.clickCustomMenu = true;
                PvCurrencyLogUtils.pvLogHomeJingangqu("1070210", "金刚区-更多-自定义", "更多", "", "", "", "");
                updateHomeCustomNavMenu(string2);
            } else if (string.equals("getLanguageJson")) {
                callbackSuccess(getLanguageJson());
            } else if (string.equals("getFuWuCustomNavMenu")) {
                getFuwUCustomNavMenu();
            } else if (string.equals("updateFuWuCustomNavMenu")) {
                String string3 = this.parameterJO.getString("value");
                Log.d("updateFuWuCustomNavMenu", "onExec: " + string3);
                updateFuWuCustomNavMenu(string3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("msg", e.getMessage());
            callbackFail(jSONObject);
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        if (this.parameterJO.getString("type").equals("getLanguageJson")) {
            return callbackSuccessSync(getLanguageJson());
        }
        return null;
    }

    private void getAllNavMenu() throws Exception {
        callbackSuccess(MenuDataCenter.getInstance().getMenuEntity().getContent());
    }

    private String getLanguageJson() {
        return LanguageUtil.getInstance().isChinese() ? "" : LanguageUtil.getInstance().getLanguageJson();
    }

    public void getChangYongNavMenu() throws Exception {
        List<MenuEntity> serviceByTag = MenuDataCenter.getInstance().getServiceByTag(MenuDataCenter.chagnyong);
        JSONArray jSONArray = new JSONArray();
        for (MenuEntity menuEntity : serviceByTag) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("title", menuEntity.getMenuTitle());
            jSONObject.put("id", menuEntity.getMenuId());
            jSONArray.put(jSONObject);
        }
        callbackSuccess(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
    }

    public String getHomeCustomNavMenu() throws Exception {
        String customContentNew = CacheDataCenter.getInstance().getCustomContentNew(UserManager.getInstance().getCurrentPhoneNumber());
        callbackSuccess(customContentNew);
        return customContentNew;
    }

    public void updateHomeCustomNavMenu(String str) throws Exception {
        CacheDataCenter.getInstance().setCustomContentNew(str, UserManager.getInstance().getCurrentPhoneNumber());
        callbackSuccess("success");
    }

    public void getFuwUCustomNavMenu() throws Exception {
        callbackSuccess(CacheDataCenter.getInstance().getFuWuContent(UserManager.getInstance().getCurrentPhoneNumber()));
    }

    public void updateFuWuCustomNavMenu(String str) throws Exception {
        CacheDataCenter.getInstance().setFuWuContent(str, UserManager.getInstance().getCurrentPhoneNumber());
        callbackSuccess("success");
    }
}
