package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class StorageJSPlugin extends BaseJSPlugin {
    public String getDefaultCrossDomainID() {
        return "DefaultStorage_";
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        try {
            if (TextUtils.isEmpty(App.getSharePreferenceUtil().getString("StorageJSPluginClearData"))) {
                MsLogUtil.m7980d("升级到8.9.2及以上新版本，StorageJSPlugin清空一次历史数据");
                JSStorageBox.clearAll();
                App.getSharePreferenceUtil().putString("StorageJSPluginClearData", "8.9.2");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("升级到8.9.2及以上新版本，StorageJSPlugin清空一次历史数据 " + e.getMessage());
        }
    }

    public String getStoragePrefixId() {
        String str = "";
        try {
            if (this.webFragment instanceof BaseWebFragment) {
                String str2 = this.webFragment.currentCumpAppId;
                if (TextUtils.isEmpty(str2)) {
                    Uri parse = Uri.parse(this.webFragment.currentURLForPlugin);
                    String host = parse.getHost();
                    List<String> pathSegments = parse.getPathSegments();
                    if (pathSegments == null || pathSegments.size() <= 0) {
                        str = host;
                    } else {
                        str = host + "/" + pathSegments.get(0);
                    }
                } else {
                    str = str2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            str = "DefaultStorage";
        }
        return str + "_";
    }
}
