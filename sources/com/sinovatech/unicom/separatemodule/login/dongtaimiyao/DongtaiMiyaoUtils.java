package com.sinovatech.unicom.separatemodule.login.dongtaimiyao;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DongtaiMiyaoUtils {
    public static final String LOGIN_AES = "5e7c7dbe7724402f97a572915f8727e7";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getLoginCertInfo$0(Object obj) throws Exception {
    }

    public static LoginParamsEntity getHeaderAndBody(String str, Map<String, String> map) {
        MsLogUtil.m7979d("查看加密的手机号码", "0：mobile：" + str);
        LoginParamsEntity loginParamsEntity = new LoginParamsEntity();
        if (isOpen()) {
            try {
                String encodeByAESDongtaimiyao = EncodeHelper.encodeByAESDongtaimiyao(str, getParamsJsonString(map));
                HashMap hashMap = new HashMap();
                hashMap.put("__p", encodeByAESDongtaimiyao);
                loginParamsEntity.setBodyMap(hashMap);
                loginParamsEntity.setHeaderMap(getHeader(str, encodeByAESDongtaimiyao));
            } catch (Exception e) {
                e.printStackTrace();
                saveMiayoMessage(str, UserManager.getInstance(), "", "", "", "");
                loginParamsEntity.setHeaderMap(new HashMap());
                loginParamsEntity.setBodyMap(map);
            }
        } else {
            loginParamsEntity.setBodyMap(map);
        }
        return loginParamsEntity;
    }

    public static LoginParamsEntity getOnlineHeaderAndBody(String str, Map<String, String> map, boolean z) {
        if (isHasDongtaimaiyao(str)) {
            if (z) {
                return getEsimHeaderAndBody(str, map);
            }
            return getHeaderAndBody(str, map);
        }
        LoginParamsEntity loginParamsEntity = new LoginParamsEntity();
        loginParamsEntity.setHeaderMap(new HashMap());
        loginParamsEntity.setBodyMap(map);
        return loginParamsEntity;
    }

    private static String getParamsJsonString(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    private static Map<String, String> getHeader(String str, String str2) {
        boolean z;
        String valueOf = String.valueOf(SystemTimeUtil.currentTimeMillis());
        HashMap hashMap = new HashMap();
        String xid = UserManager.getInstance().getXid(str);
        String duid = UserManager.getInstance().getDuid(str);
        String loid = UserManager.getInstance().getLoid(str);
        String loginTimestamp = UserManager.getInstance().getLoginTimestamp(str);
        if (isHasDongtaimaiyao(str)) {
            z = false;
        } else {
            z = true;
            xid = "5e7c7dbe7724402f97a572915f8727e7";
            loginTimestamp = valueOf;
        }
        String encoderByMd5_32 = EncodeHelper.encoderByMd5_32(xid);
        if (!TextUtils.isEmpty(encoderByMd5_32) && encoderByMd5_32.length() > 10) {
            encoderByMd5_32 = encoderByMd5_32.substring(0, 10);
        }
        hashMap.put("s", getSHA256(str2 + loginTimestamp + encoderByMd5_32));
        hashMap.put("uuid", DeviceHelper.getDeviceCode());
        hashMap.put("timestamp", valueOf);
        hashMap.put("version", App.getInstance().getString(2131886969));
        if (!z) {
            hashMap.put("duid", duid);
            hashMap.put("loid", loid);
            hashMap.put("loginTimestamp", loginTimestamp);
        }
        return hashMap;
    }

    private static String getSHA256(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            return byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String byte2Hex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static void getLoginCertInfo(final String str, String str2) {
        if (isOpen() && !isHasDongtaimaiyao(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("tokenOnline", str2);
            hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
            hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
            hashMap.put("deviceOs", DeviceHelper.getDeviceOSVersion());
            hashMap.put("version", App.getInstance().getString(2131886969));
            hashMap.put("uuid", DeviceHelper.getDeviceCode());
            LoginParamsEntity headerAndBody = getHeaderAndBody(str, hashMap);
            App.getAsyncHttpClient().rxPost(URLSet.getLoginCertInfo(), headerAndBody.getBodyMap(), headerAndBody.getHeaderMap()).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<String, Object>() { // from class: com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils.1
                @Override // io.reactivex.functions.Function
                public Object apply(String str3) throws Exception {
                    JSONObject optJSONObject;
                    JSONObject jSONObject = new JSONObject(str3);
                    if ("0000".equals(jSONObject.optString("code")) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                        String optString = optJSONObject.optString("duid");
                        String optString2 = optJSONObject.optString("loid");
                        String optString3 = optJSONObject.optString("xid");
                        String optString4 = optJSONObject.optString("xidSignTimestamp");
                        String optString5 = optJSONObject.optString("mobile");
                        if (!TextUtils.isEmpty(optString5)) {
                            DongtaiMiyaoUtils.saveMiayoMessage(optString5, UserManager.getInstance(), optString3, optString2, optString, optString4);
                        } else {
                            DongtaiMiyaoUtils.saveMiayoMessage(str, UserManager.getInstance(), optString3, optString2, optString, optString4);
                        }
                    }
                    return str3;
                }
            }).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.login.dongtaimiyao.-$$Lambda$DongtaiMiyaoUtils$XDRd6fta6BB_zA2VlwpfVQaa98s
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    DongtaiMiyaoUtils.lambda$getLoginCertInfo$0(obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.login.dongtaimiyao.-$$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ((Throwable) obj).printStackTrace();
                }
            });
        }
    }

    private static boolean isOpen() {
        return "on".equals(LoginConfigDataCenter.getInstance().getEntity().getDongtaimiyao());
    }

    private static boolean isEsimOpen() {
        return "on".equals(LoginConfigDataCenter.getInstance().getEntity().getEsimCertSwitch());
    }

    public static void saveMiayoMessage(String str, UserManager userManager, String str2, String str3, String str4, String str5) {
        try {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5)) {
                MsLogUtil.m7979d("动态xid", "原始数据：" + str2);
                String substring = str5.substring(str5.length() + (-1));
                MsLogUtil.m7979d("动态xid", "截取的索引：" + substring + "   " + str5);
                StringBuilder reverse = new StringBuilder(str2).reverse();
                StringBuilder sb = new StringBuilder();
                sb.append("反转后的密钥：");
                sb.append(reverse.toString());
                MsLogUtil.m7979d("动态xid", sb.toString());
                try {
                    StringBuilder deleteCharAt = reverse.deleteCharAt(Integer.parseInt(substring));
                    MsLogUtil.m7979d("动态xid", "最后生成的：" + deleteCharAt.toString());
                    userManager.setXid(str, deleteCharAt.toString());
                    userManager.setLoid(str, str3);
                    userManager.setDuid(str, str4);
                    userManager.setLoginTimestamp(str, str5);
                } catch (Exception e) {
                    e.printStackTrace();
                    userManager.setXid(str, "");
                    userManager.setLoid(str, "");
                    userManager.setDuid(str, "");
                    userManager.setLoginTimestamp(str, "");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isHasDongtaimaiyao(String str) {
        return ("0".equals(str) || TextUtils.isEmpty(UserManager.getInstance().getXid(str)) || TextUtils.isEmpty(UserManager.getInstance().getDuid(str)) || TextUtils.isEmpty(UserManager.getInstance().getLoid(str)) || TextUtils.isEmpty(UserManager.getInstance().getLoginTimestamp(str))) ? false : true;
    }

    public static LoginParamsEntity getEsimHeaderAndBody(String str, Map<String, String> map) {
        MsLogUtil.m7979d("查看加密的手机号码", "0：mobile：" + str);
        LoginParamsEntity loginParamsEntity = new LoginParamsEntity();
        if (isEsimOpen()) {
            try {
                String encodeByAESDongtaimiyao = EncodeHelper.encodeByAESDongtaimiyao(str, getParamsJsonString(map));
                HashMap hashMap = new HashMap();
                hashMap.put("__p", encodeByAESDongtaimiyao);
                loginParamsEntity.setBodyMap(hashMap);
                loginParamsEntity.setHeaderMap(getHeader(str, encodeByAESDongtaimiyao));
            } catch (Exception e) {
                e.printStackTrace();
                saveMiayoMessage(str, UserManager.getInstance(), "", "", "", "");
                loginParamsEntity.setHeaderMap(new HashMap());
                loginParamsEntity.setBodyMap(map);
            }
        } else {
            loginParamsEntity.setBodyMap(map);
        }
        return loginParamsEntity;
    }
}
