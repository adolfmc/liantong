package com.sinovatech.unicom.separatemodule.login.fengkong;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LoginFilterUtil {
    private static final String ECS99998 = "ECS99998";
    private static final String ECS99999 = "ECS99999";
    public static String resultToken;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CallBackInterface {
        void complete(String str);
    }

    public static boolean filerLogin(final Activity activity, String str, final CallBackInterface callBackInterface) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            final String optString = jSONObject.optString("code");
            if ("ECS99999".equals(optString) || "ECS99998".equals(optString)) {
                try {
                    Activity topActivity = SoulPermission.getInstance().getTopActivity();
                    if (topActivity instanceof WebDetailActivity) {
                        if (((WebDetailActivity) topActivity).isIskControlPage()) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String optString2 = jSONObject.optString("url");
                if (!TextUtils.isEmpty(optString2) && optString2.startsWith("http")) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("viewStrongValue", jSONObject);
                    WebParamsEntity webParamsEntity = new WebParamsEntity();
                    webParamsEntity.setUrl(optString2);
                    webParamsEntity.setTitle("");
                    webParamsEntity.setBackMode("1");
                    webParamsEntity.setRequestType("get");
                    webParamsEntity.setNeedTitle(false);
                    webParamsEntity.setYule(false);
                    webParamsEntity.setType("webViewNavIsHidden");
                    String uuid = UUID.randomUUID().toString();
                    App.navigateParamsCacheMap.put(uuid, hashMap);
                    webParamsEntity.setNavigateParamsUUID(uuid);
                    Intent intent = new Intent(activity, WebDetailActivity.class);
                    intent.putExtra(WebFragment.webParams, webParamsEntity);
                    intent.putExtra("shouldLoadJs", true);
                    new AvoidOnResult(activity).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.1
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public void onActivityResult(int i, Intent intent2) {
                            if ("ECS99999".equals(optString)) {
                                if (i == -1 && intent2 != null) {
                                    String stringExtra = intent2.getStringExtra("resultToken");
                                    LoginFilterUtil.resultToken = stringExtra;
                                    callBackInterface.complete(stringExtra);
                                    MsLogUtil.m7979d("setStrongLoginResult", "客户端Result成功--" + stringExtra);
                                } else if (App.hasLogined()) {
                                    LoginManager.logout(activity);
                                    MsLogUtil.m7979d("setStrongLoginResult", "客户端Result失败--退出登录");
                                }
                            }
                            if ("ECS99998".equals(optString) && i == -1 && intent2 != null) {
                                String stringExtra2 = intent2.getStringExtra("resultToken");
                                LoginFilterUtil.resultToken = stringExtra2;
                                callBackInterface.complete(stringExtra2);
                            }
                        }
                    });
                    return true;
                }
                return false;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
