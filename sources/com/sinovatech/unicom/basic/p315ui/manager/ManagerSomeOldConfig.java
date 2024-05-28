package com.sinovatech.unicom.basic.p315ui.manager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.view.CopyDialogManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerSomeOldConfig */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerSomeOldConfig {
    private AppCompatActivity activityContext;

    public ManagerSomeOldConfig(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void loadCityData() {
        App.getAsyncHttpClient().post(URLSet.getCityLatlongUrl(), new RequestParams(), new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerSomeOldConfig.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str) {
                super.onSuccess(i, str);
                if (i != 200 || TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    CacheDataCenter.getInstance().setCityData(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean loadCopyCommond() {
        try {
            ClipboardManager clipboardManager = (ClipboardManager) this.activityContext.getSystemService("clipboard");
            ClipData primaryClip = clipboardManager.getPrimaryClip();
            if (primaryClip != null && primaryClip.getItemCount() > 0) {
                CharSequence text = primaryClip.getItemAt(0).getText();
                String charSequence = text.toString();
                Log.d("粘贴板内容", "loadCopyCommond: " + charSequence);
                if (charSequence.equals(App.getSharePreferenceUtil().getString("wokoulingcontent"))) {
                    clipboardManager.setText("");
                    App.getSharePreferenceUtil().putString("wokoulingcontent", "");
                    return false;
                } else if (text.toString().contains("CNCC#")) {
                    getZCommandInfo(text.toString());
                    clipboardManager.setText("");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void getZCommandInfo(String str) {
        try {
            RequestParams requestParams = new RequestParams();
            requestParams.put("zCommand", str);
            App.getAsyncHttpClient().post(URLSet.getZCommandInfo(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerSomeOldConfig.2
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str2) {
                    super.onSuccess(i, str2);
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        String optString = jSONObject.optString("shareURL");
                        ManagerSomeOldConfig.this.showCopyDialog(jSONObject.optString("copyUrlContent"), optString, jSONObject.optString("copyUrlImg"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str2) {
                    super.onFailure(th, str2);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFinish() {
                    super.onFinish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCopyDialog(String str, String str2, String str3) {
        CopyDialogManager.show(this.activityContext, str, str2, str3, "", new CopyDialogManager.CopyDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerSomeOldConfig.3
            @Override // com.sinovatech.unicom.basic.view.CopyDialogManager.CopyDialogListener
            public void onCancel() {
            }
        });
    }
}
