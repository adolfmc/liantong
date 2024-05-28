package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.model.LatLng;
import com.sinovatech.unicom.separatemodule.baidumap.BaiduCollectPopWindow;
import com.sinovatech.unicom.separatemodule.baidumap.BaiduH5BusinessDetails;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/openExternalMap")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OpenExternalMapJSPlugin extends BaseJSPlugin {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface MapCallBack {
        void callBack(String str, String str2);
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        if (this.parameterJO == null) {
            callbackFail("11", "参数传递有问题");
            return;
        }
        try {
            final BaiduH5BusinessDetails baiduH5BusinessDetails = new BaiduH5BusinessDetails(this.activityContext, this.parameterJO.optString("epName"), new LatLng(this.parameterJO.optDouble("epWeidu"), this.parameterJO.optDouble("epJingdu")));
            BaiduCollectPopWindow baiduCollectPopWindow = new BaiduCollectPopWindow(this.activityContext);
            baiduCollectPopWindow.setOnClickListener(new BaiduCollectPopWindow.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.OpenExternalMapJSPlugin.1
                @Override // com.sinovatech.unicom.separatemodule.baidumap.BaiduCollectPopWindow.OnClickListener
                public void onClick(String str) {
                    if ("百度地图".equals(str)) {
                        baiduH5BusinessDetails.setUpBaiduAPPByMine(true, new MapCallBack() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.OpenExternalMapJSPlugin.1.1
                            @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.OpenExternalMapJSPlugin.MapCallBack
                            public void callBack(String str2, String str3) {
                                if ("0".equals(str2)) {
                                    OpenExternalMapJSPlugin.this.callbackSuccess(str2, str3, null);
                                } else {
                                    OpenExternalMapJSPlugin.this.callbackFail(str2, str3);
                                }
                            }
                        });
                    } else if ("高德地图".equals(str)) {
                        baiduH5BusinessDetails.setUpGaodeAppByMine(true, new MapCallBack() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.OpenExternalMapJSPlugin.1.2
                            @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.navigate.OpenExternalMapJSPlugin.MapCallBack
                            public void callBack(String str2, String str3) {
                                if ("0".equals(str2)) {
                                    OpenExternalMapJSPlugin.this.callbackSuccess(str2, str3, null);
                                } else {
                                    OpenExternalMapJSPlugin.this.callbackFail(str2, str3);
                                }
                            }
                        });
                    } else {
                        OpenExternalMapJSPlugin.this.callbackFail("12", "用户主动取消");
                    }
                }
            });
            baiduCollectPopWindow.collectPopWindow();
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("11", "参数传递有问题");
        }
    }
}
