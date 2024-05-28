package com.sinovatech.unicom.separatemodule.miniprogram.jspermission;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthManager;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5RegisterEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogEnu;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JSPermissionManager {
    private static final String TAG = "JSPermissionManager";
    private static JSPermissionManager instance;
    private Context context;
    private Map<String, JSPermissionEntity> jsMap;

    public static synchronized JSPermissionManager getInstance(Context context) {
        JSPermissionManager jSPermissionManager;
        synchronized (JSPermissionManager.class) {
            if (instance == null) {
                synchronized (JSPermissionManager.class) {
                    if (instance == null) {
                        instance = new JSPermissionManager(context);
                    }
                }
            }
            jSPermissionManager = instance;
        }
        return jSPermissionManager;
    }

    public JSPermissionManager(Context context) {
        this.context = context;
        initJsPermissionData();
    }

    private void initJsPermissionData() {
        this.jsMap = new HashMap();
        try {
            JSONArray jSONArray = new JSONArray(FileTools.readFile(this.context.getResources().getAssets().open("jsplugin_permission.json")));
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSPermissionEntity jSPermissionEntity = new JSPermissionEntity(jSONObject.getString("innerAction"), jSONObject.getString("callAction"), jSONObject.getString("plugCode"), jSONObject.getString("scope"), jSONObject.getBoolean("reqBgGrant_edop"), jSONObject.getBoolean("reqBgGrant_H5"), jSONObject.getBoolean("reqUserGrant_edop"), jSONObject.getBoolean("reqUserGrant_H5"), jSONObject.getBoolean("onlyEdop"), jSONObject.getString("remark"));
                this.jsMap.put(jSPermissionEntity.getInnerAction(), jSPermissionEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public JSPermissionEntity getJSPermissionEntity(String str) {
        return this.jsMap.get(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.String] */
    public boolean checkJSPermission(String str, String str2, String str3, String str4) {
        H5RegisterEntity h5RegisterEntity;
        boolean z;
        String str5;
        String str6;
        MsLogUtil.m7979d(TAG, "校验JS接口使用权限 appId=" + str + " h5AppId=" + str2 + " 调用JS=" + str3 + " URL=" + str4);
        JSPermissionEntity jSPermissionEntity = this.jsMap.get(str3);
        boolean z2 = true;
        if (jSPermissionEntity != null) {
            ?? r1 = 0;
            try {
                MsLogUtil.m7979d(TAG, "JS对象打印:" + jSPermissionEntity.toString());
                try {
                    if (!TextUtils.isEmpty(str) && str.startsWith("edop_unicom_") && !str.equals("edop_unicom_debug") && jSPermissionEntity.isReqBgGrant_edop()) {
                        CumpEntity appInfoFromBox = CumpLanucher.getInstance(this.context).getAppInfoFromBox(str);
                        if (appInfoFromBox != null && !appInfoFromBox.isInnerMiniP()) {
                            if (appInfoFromBox.getPlugCodeList().contains(jSPermissionEntity.getPlugCode())) {
                                MsLogUtil.m7979d(CumpLanucher.TAG, "校验JS接口使用权限 包含在里面>>当前小程序权限列表=" + appInfoFromBox.getPlugCodeList());
                            } else {
                                MsLogUtil.m7977e(CumpLanucher.TAG, jSPermissionEntity.getCallAction() + "能力未授权 请到开发者中心申请");
                                UIUtils.toast(jSPermissionEntity.getCallAction() + "能力未授权 请到开发者中心申请");
                                z2 = false;
                            }
                        }
                    } else if (!TextUtils.isEmpty(str2) && jSPermissionEntity.isReqBgGrant_H5() && (h5RegisterEntity = H5AuthManager.getInstance(this.context).getH5RegisterEntity(str2)) != null) {
                        if (h5RegisterEntity.getGrantConfig().get(jSPermissionEntity.getPlugCode()) != null) {
                            MsLogUtil.m7979d(CumpLanucher.TAG, "校验JS接口使用权限 包含在里面>>当前业务权限列表=" + h5RegisterEntity.getGrantConfig().keySet().toString());
                            String[] split = h5RegisterEntity.getH5Urls().split(",");
                            int i = 0;
                            while (true) {
                                if (i >= split.length) {
                                    z = false;
                                    break;
                                } else if (str4.contains(split[i])) {
                                    z = true;
                                    break;
                                } else {
                                    i++;
                                }
                            }
                            if (!z) {
                                MsLogUtil.m7977e(CumpLanucher.TAG, jSPermissionEntity.getCallAction() + "能力未授权 URL不匹配 请到开发者中心申请");
                                UIUtils.toast(jSPermissionEntity.getCallAction() + "能力域名未授权 URL不匹配 请到开发者中心申请");
                                z2 = false;
                            }
                        } else {
                            MsLogUtil.m7977e(CumpLanucher.TAG, jSPermissionEntity.getCallAction() + "能力未授权 请到开发者中心申请");
                            UIUtils.toast(jSPermissionEntity.getCallAction() + "能力插件编码未授权 请到开发者中心申请");
                            z2 = false;
                        }
                    }
                    try {
                        CumpEntity appInfoFromBox2 = CumpLanucher.getInstance(this.context).getAppInfoFromBox(str);
                        r1 = "";
                        if (appInfoFromBox2 != null) {
                            str5 = appInfoFromBox2.getOfficialVersion();
                            str6 = appInfoFromBox2.getAppName();
                        } else {
                            str5 = "";
                            str6 = "";
                        }
                        CumpLogManager.getInstance(this.context).log_SCE03(new Date(), str, str6, str4, str5, jSPermissionEntity.getPlugCode(), jSPermissionEntity.getCallAction(), z2 ? CumpLogEnu.interceptStatus_yes : CumpLogEnu.interceptStatus_no);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e = e2;
                    z2 = r1;
                    e.printStackTrace();
                    MsLogUtil.m7977e(CumpLanucher.TAG, "小程序校验JS权限错误：" + e.getMessage());
                    return z2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        return z2;
    }

    public boolean isOnlyGrantEdop(String str, String str2) {
        String str3 = CumpLanucher.TAG;
        MsLogUtil.m7979d(str3, "isOnlyGrantEdop-校验JS接口使用权限 appId=" + str + " 调用JS=" + str2);
        JSPermissionEntity jSPermissionEntity = this.jsMap.get(str2);
        if (jSPermissionEntity != null) {
            try {
                if (jSPermissionEntity.isOnlyEdop()) {
                    if (!TextUtils.isEmpty(str)) {
                        if (str.startsWith("edop_unicom_")) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                String str4 = CumpLanucher.TAG;
                MsLogUtil.m7977e(str4, "isOnlyGrantEdop-小程序校验JS权限错误：" + e.getMessage());
                return true;
            }
        }
        return true;
    }
}
