package com.sinovatech.unicom.separatemodule.miniprogram.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpLogManager {
    public static String TAG = "CumpLogManager";
    private static CumpLogManager instance;
    private Context context;
    private SimpleDateFormat timeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static synchronized CumpLogManager getInstance(Context context) {
        CumpLogManager cumpLogManager;
        synchronized (CumpLogManager.class) {
            if (instance == null) {
                synchronized (CumpLogManager.class) {
                    if (instance == null) {
                        instance = new CumpLogManager(context);
                    }
                }
            }
            cumpLogManager = instance;
        }
        return cumpLogManager;
    }

    public CumpLogManager(Context context) {
        this.context = context;
    }

    public void log_SCE01(String str, Date date, Date date2, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            try {
                submitLog(createLogEntity(CumpLogEnu.APP_SCE_01, "", str, date, date2, str2, str3, str4, "", str5, str6, str7, str8, "", "", ""));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void log_SCE02(String str, Date date, Date date2, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            try {
                submitLog(createLogEntity(CumpLogEnu.APP_SCE_02, str, "", date, date2, str2, str3, str4, "", str5, str6, "", str7, "", "", str8));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void log_SCE03(Date date, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            try {
                submitLog(createLogEntity(CumpLogEnu.APP_SCE_03, "", "", date, null, str, str2, str3, "", "", "", "", str4, str5, str6, str7));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void log_SCE04(String str, Date date, Date date2, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            try {
                submitLog(createLogEntity(CumpLogEnu.APP_SCE_04, str, "", date, date2, str2, str3, str4, str5, str6, str7, "", str8, "", "", ""));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void log_SCE05(Date date, Date date2, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            try {
                submitLog(createLogEntity(CumpLogEnu.APP_SCE_05, "", "", date, date2, str, str2, str3, "", str4, str5, "", str6, str7, "request", ""));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void log_5gsimOma(Date date, Date date2, String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            try {
                submitLog(createLogEntity(CumpLogEnu.APP_SCE_07, "", "", date, date2, str, str2, str3, "", str4, str5, "", str6, "OMApi", "OMApi", ""));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @NotNull
    private CumpLogEntity createLogEntity(String str, String str2, String str3, Date date, Date date2, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) throws Exception {
        CumpLogEntity cumpLogEntity = new CumpLogEntity();
        cumpLogEntity.setSceneNumber(str);
        cumpLogEntity.setSubSceneNumber(str2);
        cumpLogEntity.setStepNumber(str3);
        cumpLogEntity.setSerialNumber(generateSessionId());
        if (date != null) {
            cumpLogEntity.setStartTime(this.timeSDF.format(date));
            if (date2 != null) {
                cumpLogEntity.setEndTime(this.timeSDF.format(date2));
                cumpLogEntity.setTakeupTime(String.valueOf(date2.getTime() - date.getTime()));
            } else {
                Date date3 = new Date();
                cumpLogEntity.setEndTime(this.timeSDF.format(date3));
                cumpLogEntity.setTakeupTime(String.valueOf(date3.getTime() - date.getTime()));
            }
        }
        cumpLogEntity.setEdopAppId(str4);
        cumpLogEntity.setEdopAppName(str5);
        cumpLogEntity.setUrl(str6);
        cumpLogEntity.setTitle(str7);
        cumpLogEntity.setResponse(str8);
        cumpLogEntity.setStatus(str9);
        cumpLogEntity.setOldEdopAppVersion(str10);
        cumpLogEntity.setNewEdopAppVersion(str11);
        cumpLogEntity.setAuthFunctionCode(str12);
        cumpLogEntity.setJsFunction(str13);
        cumpLogEntity.setInterceptStatus(str14);
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        cumpLogEntity.setMobile((TextUtils.isEmpty(currentPhoneNumber) || "0".equals(currentPhoneNumber)) ? "" : Base64.encodeToString(currentPhoneNumber.getBytes("utf-8"), 2));
        cumpLogEntity.setProvinceCode(UserManager.getInstance().getCurrentProvinceCode());
        cumpLogEntity.setCityCode(UserManager.getInstance().getCurrentCityCode());
        cumpLogEntity.setOsType("android");
        cumpLogEntity.setClientVersion(this.context.getString(2131886969));
        return cumpLogEntity;
    }

    private void submitLog(final CumpLogEntity cumpLogEntity) {
        try {
            try {
                if (!TextUtils.isEmpty(cumpLogEntity.getEdopAppId()) && cumpLogEntity.getEdopAppId().contains("edop_unicom_debug")) {
                    String str = TAG;
                    MsLogUtil.m7979d(str, "落监控日志 " + cumpLogEntity.getSerialNumber() + "  小程序是debug模式，不落监控");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sceneNumber", cumpLogEntity.getSceneNumber());
            jSONObject.put("subSceneNumber", cumpLogEntity.getSubSceneNumber());
            jSONObject.put("stepNumber", cumpLogEntity.getStepNumber());
            jSONObject.put("serialNumber", cumpLogEntity.getSerialNumber());
            jSONObject.put("startTime", cumpLogEntity.getStartTime());
            jSONObject.put("endTime", cumpLogEntity.getEndTime());
            jSONObject.put("takeupTime", cumpLogEntity.getTakeupTime());
            jSONObject.put("edopAppId", cumpLogEntity.getEdopAppId());
            jSONObject.put("edopAppName", cumpLogEntity.getEdopAppName());
            jSONObject.put("url", cumpLogEntity.getUrl());
            jSONObject.put("title", cumpLogEntity.getTitle());
            jSONObject.put("response", cumpLogEntity.getResponse());
            jSONObject.put("status", cumpLogEntity.getStatus());
            jSONObject.put("oldEdopAppVersion", cumpLogEntity.getOldEdopAppVersion());
            jSONObject.put("newEdopAppVersion", cumpLogEntity.getNewEdopAppVersion());
            jSONObject.put("authFunctionCode", cumpLogEntity.getAuthFunctionCode());
            jSONObject.put("jsFunction", cumpLogEntity.getJsFunction());
            jSONObject.put("interceptStatus", cumpLogEntity.getInterceptStatus());
            jSONObject.put("mobile", cumpLogEntity.getMobile());
            jSONObject.put("provinceCode", cumpLogEntity.getProvinceCode());
            jSONObject.put("cityCode", cumpLogEntity.getCityCode());
            jSONObject.put("deviceId", "");
            jSONObject.put("osType", cumpLogEntity.getOsType());
            jSONObject.put("osVersion", "");
            jSONObject.put("clientVersion", cumpLogEntity.getClientVersion());
            jSONObject.put("deviceBrand", "");
            jSONObject.put("deviceModel", "");
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("落监控日志 ");
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            MsLogUtil.m7979d(str2, sb.toString());
            App.getAsyncHttpClient().rxPost(URLSet.getEdopLogUrl(), !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 1, 3000).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogManager.1
                @Override // io.reactivex.functions.Consumer
                public void accept(String str3) throws Exception {
                    String str4 = CumpLogManager.TAG;
                    MsLogUtil.m7979d(str4, "落监控日志 " + cumpLogEntity.getSerialNumber() + "  完成");
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogManager.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str3 = CumpLogManager.TAG;
                    MsLogUtil.m7979d(str3, "落监控日志 " + cumpLogEntity.getSerialNumber() + "  " + th.getMessage());
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String generateSessionId() {
        try {
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String uuid = UUID.randomUUID().toString();
            return format + (uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
