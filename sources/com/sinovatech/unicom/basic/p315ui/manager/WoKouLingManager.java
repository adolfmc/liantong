package com.sinovatech.unicom.basic.p315ui.manager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import android.util.Base64;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.WoKouLingManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WoKouLingManager {
    private AppCompatActivity activityContext;

    public WoKouLingManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public boolean woKouLingCommond() {
        try {
            final ClipboardManager clipboardManager = (ClipboardManager) this.activityContext.getSystemService("clipboard");
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.WoKouLingManager.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ClipData primaryClip = clipboardManager.getPrimaryClip();
                        StringBuilder sb = new StringBuilder();
                        sb.append("剪切板是否有数据 = ");
                        sb.append(primaryClip != null && primaryClip.getItemCount() > 0);
                        UIUtils.logD("ShareLogUtil", sb.toString());
                        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
                            return;
                        }
                        CharSequence text = primaryClip.getItemAt(0).getText();
                        String charSequence = text.toString();
                        UIUtils.logD("ShareLogUtil", "剪切板数据为 = " + ((Object) text));
                        if (charSequence.equals(App.getSharePreferenceUtil().getString("share_wokoulingcontent"))) {
                            clipboardManager.setText("");
                            App.getSharePreferenceUtil().putString("share_wokoulingcontent", "");
                        } else if (!App.isHandleWoKouLing) {
                            clipboardManager.setText("");
                            App.isHandleWoKouLing = true;
                        } else if (text.toString().contains("CNCC_NEW")) {
                            String charSequence2 = text.toString();
                            String substring = charSequence2.substring(charSequence2.indexOf("『") + 1, charSequence2.indexOf("』"));
                            HashMap hashMap = new HashMap();
                            hashMap.put("woCommand", substring);
                            UIUtils.logD("ShareLogUtil", "沃口令 = " + text.toString());
                            UIUtils.logD("ShareLogUtil", "沃口令 字符串 = " + substring);
                            ShareLogUtil.getwokouling(WoKouLingManager.this.activityContext, hashMap);
                            clipboardManager.setText("");
                        } else if (charSequence.contains("8.0 chinaunicom:/!")) {
                            String[] split = charSequence.split("8.0 chinaunicom:/!");
                            if (split[1].contains("dwguide=")) {
                                String urlDecode = EncodeUtils.urlDecode(new String(Base64.decode(split[1].split("dwguide=")[1].getBytes(), 0)));
                                MsLogUtil.m7979d("剪贴板内容解码", urlDecode);
                                clipboardManager.setText("");
                                JSONObject jSONObject = new JSONObject(urlDecode);
                                String optString = jSONObject.optString("title");
                                String optString2 = jSONObject.optString("openUrl");
                                if ("".equals(App.getSharePreferenceUtil().getString("share_first_open"))) {
                                    UnicomCollectManager unicomCollectManager = UnicomCollectManager.getInstance();
                                    unicomCollectManager.setTransId("S2ndpage1172" + CollectConfig.montageTag1 + "沃口令");
                                    IntentManager.gotoWebViewActivity(WoKouLingManager.this.activityContext, optString2, optString, "1", "get");
                                }
                                App.xinLaoUserOpenUrl = optString2;
                                if (CacheDataCenter.getInstance().isFirstInstall()) {
                                    StatisticsUploadUtils.uploadXinLao(WoKouLingManager.this.activityContext, "S2ndpage1172", "APP激活", optString2, DeviceHelper.getDeviceID(true));
                                    CacheDataCenter.getInstance().setFirstInstallData();
                                }
                                App.getSharePreferenceUtil().putString("share_first_open", "NO");
                            }
                        }
                    } catch (Exception e) {
                        UIUtils.logE(e.getMessage());
                    }
                }
            }, 200L);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
