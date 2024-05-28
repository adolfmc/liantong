package com.sinovatech.unicom.separatemodule.miniprogram.cumphome;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.fragment.HomeWebFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/HideHomeSkeletonscreen")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HideHomeSkeletonscreenJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.HideHomeSkeletonscreenJSPlugin.1
            @Override // java.lang.Runnable
            public void run() {
                if (HideHomeSkeletonscreenJSPlugin.this.webFragment instanceof HomeWebFragment) {
                    MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "调用JS隐藏骨架屏 HideHomeSkeletonscreenJSPlugin");
                    ((HomeWebFragment) HideHomeSkeletonscreenJSPlugin.this.webFragment).hideLoadingAndShowWebview();
                }
            }
        });
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.HideHomeSkeletonscreenJSPlugin.2
            @Override // java.lang.Runnable
            public void run() {
                if (HideHomeSkeletonscreenJSPlugin.this.webFragment instanceof HomeWebFragment) {
                    MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "调用JS隐藏骨架屏 HideHomeSkeletonscreenJSPlugin");
                    ((HomeWebFragment) HideHomeSkeletonscreenJSPlugin.this.webFragment).hideLoadingAndShowWebview();
                }
            }
        });
        return callbackSuccessSync(new JSONObject());
    }
}
