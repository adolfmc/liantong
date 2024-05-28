package com.sinovatech.unicom.separatemodule.tms.mianliu;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.tms.TMSUtil;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FreeUrlAdapterUtil {
    public static final String CHARSET = "UTF-8";
    private static final String DIGEST = "MD5";
    private static final String ENCODE = "utf8";
    private static String FREE_APPTYPE = "app";
    private static String FREE_FLOW_URL_PARAM = "if5ax";
    private static String FREE_FLOW_URL_PREFIX = "http://dir.wo186.tv:809/";
    private static String FREE_IP = "127.0.0.1";
    private static String FREE_PID = "80001";
    private static String FREE_PORTALID = "500";
    private static String FREE_PREVIEW = "1";
    private static String FREE_SPID = "31146";
    private static String FREE_SPKEY = "38ed454c0e0b44fd947b41719d5cad0f";
    private static String MOBILE = "10000000000";
    private static String TAR1 = "tmssdk";
    private boolean open;
    private String requestUrl = "";
    private String post_param = "";
    private String free_spkey_md5 = "";

    public FreeUrlAdapterUtil(Context context) {
        this.open = false;
        ConfigManager configManager = new ConfigManager(context);
        if (!TextUtils.isEmpty(configManager.getMianLiuSPID())) {
            FREE_SPID = configManager.getMianLiuSPID();
        }
        if (!TextUtils.isEmpty(configManager.getMainLiuPID())) {
            FREE_PID = configManager.getMainLiuPID();
        }
        if (!TextUtils.isEmpty(configManager.getMianLiuPORTALID())) {
            FREE_PORTALID = configManager.getMianLiuPORTALID();
        }
        if (!TextUtils.isEmpty(configManager.getMianLiuSPKEY())) {
            FREE_SPKEY = configManager.getMianLiuSPKEY();
        }
        if ("01".equalsIgnoreCase(configManager.getTencentAdvertFlag())) {
            this.open = true;
        } else {
            this.open = false;
        }
    }

    public List<String> getFreeUrl(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (TMSUtil.checkIsUnicomSimCard(App.getInstance().getApplicationContext()) && this.open && (UserManager.getInstance().getLoginType().equals("01") || UserManager.getInstance().getLoginType().equals("23") || UserManager.getInstance().getLoginType().equals("999") || UserManager.getInstance().getLoginType().equals("06"))) {
            MOBILE = UserManager.getInstance().getDefaultPhoneNumber();
            FREE_IP = SystemServiceUtils.getLocalIpAddress();
            if (list != null) {
                try {
                    if (list.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("免流转换-原始url:");
                        sb.append(list.get(0));
                        UIUtils.logD("TMSSDK", sb.toString());
                        NewFreeFlowVO requestUrl = getRequestUrl(list.get(0));
                        String str = FREE_FLOW_URL_PARAM + requestUrl.getSppath();
                        String str2 = str.contains("?") ? str + "&tag1=" + TAR1 + "&videoname=" + TAR1 + "&apptype=" + FREE_APPTYPE + "&userid=" + MOBILE + "&userip=" + FREE_IP + "&spid=" + FREE_SPID + "&pid=" + FREE_PID + "&preview=" + FREE_PREVIEW + "&portalid=" + FREE_PORTALID + "&spip=" + requestUrl.getSpip() + "&spport=" + requestUrl.getSpport() : str + "?tag1=" + TAR1 + "&videoname=" + TAR1 + "&apptype=" + FREE_APPTYPE + "&userid=" + MOBILE + "&userip=" + FREE_IP + "&spid=" + FREE_SPID + "&pid=" + FREE_PID + "&preview=" + FREE_PREVIEW + "&portalid=" + FREE_PORTALID + "&spip=" + requestUrl.getSpip() + "&spport=" + requestUrl.getSpport();
                        ArrayList arrayList2 = new ArrayList();
                        for (int i = 0; i < list.size(); i++) {
                            NewFreeFlowVO requestUrl2 = getRequestUrl(list.get(i));
                            requestUrl2.setSpseq(i);
                            arrayList2.add(requestUrl2);
                        }
                        Gson gson = new Gson();
                        this.post_param = !(gson instanceof Gson) ? gson.toJson(arrayList2) : NBSGsonInstrumentation.toJson(gson, arrayList2);
                        this.free_spkey_md5 = encoderByMd5(str2 + this.post_param + FREE_SPKEY);
                        this.requestUrl = FREE_FLOW_URL_PREFIX + str2 + "&spkey=" + this.free_spkey_md5;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("免流转换-接口请求的url:");
                        sb2.append(this.requestUrl);
                        UIUtils.logD("TMSSDK", sb2.toString());
                        UIUtils.logD("TMSSDK", "免流转换-post参数为:" + this.post_param);
                        String syncPost = App.getAsyncHttpClient(5, 5, 5).syncPost(this.requestUrl, this.post_param);
                        UIUtils.logD("TMSSDK", "免流转换-返回数据为:" + syncPost);
                        JSONObject jSONObject = new JSONObject(syncPost);
                        if ("0".equals(jSONObject.getString("resultcode"))) {
                            JSONArray jSONArray = jSONObject.getJSONArray("urls");
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                UIUtils.logD("TMSSDK", "免流转换-转换后地址：" + jSONObject2.getString("url"));
                                arrayList.add(jSONObject2.getString("url"));
                            }
                        }
                    }
                } catch (Exception e) {
                    UIUtils.logE("TMSSDK", "免流转换-异常：" + e.getMessage());
                }
            }
        }
        return arrayList;
    }

    private NewFreeFlowVO getRequestUrl(String str) {
        NewFreeFlowVO newFreeFlowVO = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith("http://")) {
                    newFreeFlowVO = packageParams(str.substring(7), "80");
                } else if (str.startsWith("https://")) {
                    newFreeFlowVO = packageParams(str.substring(8), "443");
                } else {
                    newFreeFlowVO = packageParams(str, "80");
                }
            }
        } catch (Exception e) {
            UIUtils.logE("TMSSDK", "免流转换-异常：" + e.getMessage());
        }
        return newFreeFlowVO;
    }

    private NewFreeFlowVO packageParams(String str, String str2) {
        String substring;
        String substring2;
        try {
            String[] split = str.split(":");
            if (split.length > 1) {
                String str3 = split[0];
                str2 = split[1].substring(0, split[1].indexOf("/"));
                String substring3 = split[1].substring(str2.length() + 1);
                substring = str3;
                substring2 = substring3;
            } else {
                substring = str.substring(0, str.indexOf("/"));
                substring2 = str.substring(substring.length());
            }
            if (!substring2.startsWith("/")) {
                substring2 = "/" + substring2;
            }
            NewFreeFlowVO newFreeFlowVO = new NewFreeFlowVO();
            newFreeFlowVO.setSppath(substring2);
            newFreeFlowVO.setSpport(Integer.parseInt(str2));
            newFreeFlowVO.setSpip(substring);
            return newFreeFlowVO;
        } catch (Exception e) {
            UIUtils.logE("TMSSDK", "免流转换-异常：" + e.getMessage());
            return null;
        }
    }

    private String encoderByMd5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes(ENCODE));
            StringBuilder sb = new StringBuilder(digest.length << 1);
            for (int i = 0; i < digest.length; i++) {
                sb.append(Character.forDigit((digest[i] >> 4) & 15, 16));
                sb.append(Character.forDigit(digest[i] & 15, 16));
            }
            return sb.toString();
        } catch (Exception e) {
            UIUtils.logE("TMSSDK", "免流转换-MD5加密异常：" + e.getMessage());
            return "";
        }
    }
}
