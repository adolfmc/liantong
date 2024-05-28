package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.os.Handler;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p315ui.fragment.HomeWebFragment;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AndroidH5CaijiJSBrige {
    private BaseWebFragment baseFragment;
    private String tabTitle;
    private TYCJBoxManager tycjBoxManager;

    public AndroidH5CaijiJSBrige(BaseWebFragment baseWebFragment, TYCJBoxManager tYCJBoxManager) {
        this.baseFragment = baseWebFragment;
        this.tycjBoxManager = tYCJBoxManager;
    }

    @JavascriptInterface
    public void exec(final String str) {
        new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.AndroidH5CaijiJSBrige.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String string = jSONObject.getString("action");
                    char c = 65535;
                    int hashCode = string.hashCode();
                    if (hashCode != 832516835) {
                        if (hashCode != 1058298011) {
                            if (hashCode == 1354458468 && string.equals("H5BussinessLog")) {
                                c = 1;
                            }
                        } else if (string.equals("H5Click")) {
                            c = 0;
                        }
                    } else if (string.equals("resourcesError")) {
                        c = 2;
                    }
                    switch (c) {
                        case 0:
                            JSONArray optJSONArray = jSONObject.optJSONArray("parameter");
                            if (optJSONArray != null) {
                                String limitString = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(0));
                                String limitString2 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(1));
                                String limitString3 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(2));
                                String limitString4 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(3));
                                String limitString5 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(4));
                                String limitString6 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(5));
                                String limitString7 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(6));
                                String limitString8 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(7));
                                String limitString9 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(8));
                                String limitString10 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(9));
                                String limitString11 = AndroidH5CaijiJSBrige.this.getLimitString(optJSONArray.optString(10));
                                if ((AndroidH5CaijiJSBrige.this.baseFragment instanceof HomeWebFragment) && !TextUtils.isEmpty(AndroidH5CaijiJSBrige.this.tabTitle)) {
                                    PvCurrencyLogUtils.pvLogMainDJ("13", limitString5, "", limitString2, "首页-TAB-点击", AndroidH5CaijiJSBrige.this.tabTitle + "_点击", "");
                                }
                                WebFragment.pageClickCount++;
                                AndroidH5CaijiJSBrige.this.tycjBoxManager.collectH5clickInfo(limitString, limitString2, limitString3, limitString4, limitString5, limitString6, limitString7, limitString8, limitString9, limitString10, limitString11);
                            }
                            MsLogUtil.m7979d("AndroidTabJSBridge", "config:" + str);
                            return;
                        case 1:
                            JSONObject optJSONObject = jSONObject.optJSONObject("parameter");
                            AndroidH5CaijiJSBrige.this.tycjBoxManager.collectApiInfo(optJSONObject.optString("channelId"), optJSONObject.optString("scence"), optJSONObject.optString("logInfo"));
                            MsLogUtil.m7979d("AndroidTabJSBridge", "config:" + str);
                            return;
                        case 2:
                            try {
                                JSONObject optJSONObject2 = jSONObject.optJSONObject("parameter");
                                String optString = optJSONObject2.optString("src");
                                String optString2 = optJSONObject2.optString("url");
                                MsLogUtil.m7979d("resourcesError", !(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2));
                                TYCJImageParseUtils.getInstance().collectH5Image(AndroidH5CaijiJSBrige.this.baseFragment.getActivity(), optString2, optString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            MsLogUtil.m7979d("AndroidTabJSBridge", "config:" + str);
                            return;
                        default:
                            MsLogUtil.m7979d("AndroidTabJSBridge", "config:" + str);
                            return;
                    }
                } catch (Exception e2) {
                    MsLogUtil.m7978e("BaseWebFragment>>AndroidMsJSBridge>>exec>>>>Exception: " + e2.getMessage());
                    e2.printStackTrace();
                }
            }
        });
    }

    public String getTabTitle() {
        return this.tabTitle;
    }

    public void setTabTitle(String str) {
        this.tabTitle = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLimitString(String str) {
        return (TextUtils.isEmpty(str) || str.length() <= 100) ? str : str.substring(0, 99);
    }
}
