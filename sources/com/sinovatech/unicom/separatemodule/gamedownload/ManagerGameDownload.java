package com.sinovatech.unicom.separatemodule.gamedownload;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.tms.TMSUtil;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerGameDownload {
    private static ManagerGameDownload instance;

    public static synchronized ManagerGameDownload getInstance() {
        ManagerGameDownload managerGameDownload;
        synchronized (ManagerGameDownload.class) {
            if (instance == null) {
                synchronized (ManagerGameDownload.class) {
                    if (instance == null) {
                        instance = new ManagerGameDownload();
                    }
                }
            }
            managerGameDownload = instance;
        }
        return managerGameDownload;
    }

    public void handleJS(AppCompatActivity appCompatActivity, WebView webView, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("openGameApp".equals(jSONObject.getString("action").trim())) {
                String string = jSONObject.getString("gameMainTitle");
                String string2 = jSONObject.getString("gameSubTitle");
                String string3 = jSONObject.getString("gameIconUrl");
                String optString = jSONObject.optString("gamePackageName");
                String optString2 = jSONObject.optString("gameDownloadUrl");
                if (TMSUtil.isPkgInstalled(App.getInstance().getApplicationContext(), optString)) {
                    appCompatActivity.startActivity(appCompatActivity.getPackageManager().getLaunchIntentForPackage(optString));
                } else {
                    GameEntity gameEntity = new GameEntity("", string, string2, string3, optString2, optString);
                    Intent intent = new Intent(appCompatActivity, GameSingleDownloadActivity.class);
                    intent.putExtra("gameEntity", gameEntity);
                    appCompatActivity.startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
