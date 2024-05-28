package com.sinovatech.unicom.separatemodule.notice;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.utils.AesUtil;
import com.sinovatech.unicom.separatemodule.notice.utils.JsonUtil;
import com.sinovatech.unicom.separatemodule.notice.utils.Message;
import com.sinovatech.unicom.separatemodule.notice.utils.PushReq;
import com.sinovatech.unicom.separatemodule.notice.utils.ReqBody;
import com.sinovatech.unicom.separatemodule.notice.utils.ReqHead;
import com.sinovatech.unicom.separatemodule.notice.utils.UserInfo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PushServer {
    private String ClientType;
    private String ClientVersion;
    private ConfigManager configManager;
    private Context context;
    private SharePreferenceUtil preference = App.getSharePreferenceUtil();

    public PushServer(Context context) {
        this.context = context;
        String[] split = context.getString(2131886969).split("@");
        this.ClientType = split[0];
        this.ClientVersion = split[1];
        this.configManager = new ConfigManager(context);
    }

    public void registPushServer(String str) throws Exception {
        String flowNumber = getFlowNumber();
        String uuid = getUUID();
        int random = getRandom();
        String encoderByAes = encoderByAes(uuid + getRandomMD5Str(random));
        String deviceID = DeviceHelper.getDeviceID(true);
        PushReq pushReq = new PushReq();
        ReqHead reqHead = new ReqHead();
        ReqBody reqBody = new ReqBody();
        reqHead.setProcId(flowNumber);
        reqHead.setSrcCode(uuid);
        reqHead.setAesStr(encoderByAes);
        reqHead.setAesIndex(String.valueOf(random));
        reqHead.setBipCode(str);
        reqHead.setVersion(this.context.getString(2131886969));
        reqBody.setDeviceId(deviceID);
        reqBody.setClientType(this.ClientType);
        reqBody.setClientVersion(this.ClientVersion);
        try {
            if (DeviceHelper.getNeedHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isOppo() || DeviceHelper.isVivo()) {
                reqBody.setPushPlatform(DeviceHelper.getDeviceBrand().toUpperCase());
                reqBody.setPlatformToken(this.preference.getString("platformToken"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reqBody.setPhoneModel(DeviceHelper.getDeviceBrand());
        pushReq.setReqHead(reqHead);
        pushReq.setReqBody(reqBody);
        String json = JsonUtil.getJson(pushReq);
        HashMap hashMap = new HashMap();
        hashMap.put("message", json);
        sendRequest(this.configManager.getRegisterURL(), hashMap);
    }

    public Object[] sendPushServer(String str) throws Exception {
        String flowNumber = getFlowNumber();
        String uuid = getUUID();
        int random = getRandom();
        String encoderByAes = encoderByAes(uuid + getRandomMD5Str(random));
        String deviceID = DeviceHelper.getDeviceID(true);
        PushReq pushReq = new PushReq();
        ReqHead reqHead = new ReqHead();
        ReqBody reqBody = new ReqBody();
        reqHead.setProcId(flowNumber);
        reqHead.setSrcCode(uuid);
        reqHead.setAesStr(encoderByAes);
        reqHead.setAesIndex(String.valueOf(random));
        reqHead.setBipCode(str);
        reqHead.setVersion(this.context.getString(2131886969));
        reqBody.setDeviceId(deviceID);
        reqBody.setClientType(this.ClientType);
        reqBody.setClientVersion(this.ClientVersion);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserMobile(UserManager.getInstance().getCurrentPhoneNumber());
        reqBody.setUserInfo(userInfo);
        reqBody.setPhoneModel(DeviceHelper.getDeviceBrand());
        pushReq.setReqHead(reqHead);
        pushReq.setReqBody(reqBody);
        String json = JsonUtil.getJson(pushReq);
        HashMap hashMap = new HashMap();
        hashMap.put("message", json);
        String sendRequest = sendRequest(this.configManager.getGetMessageURL(), hashMap);
        UIUtils.logD("PushServer", "backData>>>   " + sendRequest + "     ---" + this.configManager.getGetMessageURL());
        return parseBackData(sendRequest);
    }

    public void sendPushStatus() throws Exception {
        String flowNumber = getFlowNumber();
        String uuid = getUUID();
        int random = getRandom();
        String encoderByAes = encoderByAes(uuid + getRandomMD5Str(random));
        String deviceID = DeviceHelper.getDeviceID(true);
        PushReq pushReq = new PushReq();
        ReqHead reqHead = new ReqHead();
        ReqBody reqBody = new ReqBody();
        reqHead.setProcId(flowNumber);
        reqHead.setSrcCode(uuid);
        reqHead.setAesStr(encoderByAes);
        reqHead.setAesIndex(String.valueOf(random));
        reqHead.setBipCode("PUSH0004");
        reqHead.setVersion(this.context.getString(2131886969));
        reqBody.setDeviceId(deviceID);
        reqBody.setClientType(this.ClientType);
        reqBody.setClientVersion(this.ClientVersion);
        reqBody.setPhoneModel(DeviceHelper.getDeviceBrand());
        reqBody.setPushStatus(this.preference.getBoolean("isAllowNotification") ? "1" : "0");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserMobile(UserManager.getInstance().getCurrentPhoneNumber());
        reqBody.setUserInfo(userInfo);
        pushReq.setReqHead(reqHead);
        pushReq.setReqBody(reqBody);
        String json = JsonUtil.getJson(pushReq);
        HashMap hashMap = new HashMap();
        hashMap.put("message", json);
        sendRequest(this.configManager.getGetMessageURL(), hashMap);
    }

    public void sendPushMsgReadStatusLog(Message message) throws Exception {
        String flowNumber = getFlowNumber();
        String uuid = getUUID();
        int random = getRandom();
        String encoderByAes = encoderByAes(uuid + getRandomMD5Str(random));
        String deviceID = DeviceHelper.getDeviceID(true);
        PushReq pushReq = new PushReq();
        ReqHead reqHead = new ReqHead();
        ReqBody reqBody = new ReqBody();
        reqHead.setProcId(flowNumber);
        reqHead.setSrcCode(uuid);
        reqHead.setAesStr(encoderByAes);
        reqHead.setAesIndex(String.valueOf(random));
        reqHead.setBipCode("PUSH0006");
        reqHead.setVersion(this.context.getString(2131886969));
        reqBody.setDeviceId(deviceID);
        reqBody.setClientType(this.ClientType);
        reqBody.setClientVersion(this.ClientVersion);
        ArrayList arrayList = new ArrayList();
        arrayList.add(message);
        reqBody.setMessages(arrayList);
        UserInfo userInfo = new UserInfo();
        UserManager userManager = UserManager.getInstance();
        userInfo.setUserMobile(userManager.getCurrentPhoneNumber());
        userInfo.setProvinceCode(userManager.getCurrentProvinceCode());
        reqBody.setUserInfo(userInfo);
        reqBody.setPhoneModel(DeviceHelper.getDeviceBrand());
        pushReq.setReqHead(reqHead);
        pushReq.setReqBody(reqBody);
        String json = JsonUtil.getJson(pushReq);
        HashMap hashMap = new HashMap();
        hashMap.put("message", json);
        sendRequest(this.configManager.getGetMessageURL(), hashMap);
    }

    public void sendPushMessageStatus(List<Message> list) throws Exception {
        String flowNumber = getFlowNumber();
        String uuid = getUUID();
        int random = getRandom();
        String encoderByAes = encoderByAes(uuid + getRandomMD5Str(random));
        String deviceID = DeviceHelper.getDeviceID(true);
        PushReq pushReq = new PushReq();
        ReqHead reqHead = new ReqHead();
        ReqBody reqBody = new ReqBody();
        reqHead.setProcId(flowNumber);
        reqHead.setSrcCode(uuid);
        reqHead.setAesStr(encoderByAes);
        reqHead.setAesIndex(String.valueOf(random));
        reqHead.setBipCode("PUSH0007");
        reqHead.setVersion(this.context.getString(2131886969));
        reqBody.setDeviceId(deviceID);
        reqBody.setClientType(this.ClientType);
        reqBody.setClientVersion(this.ClientVersion);
        reqBody.setPhoneModel(DeviceHelper.getDeviceBrand());
        reqBody.setMessages(list);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserMobile(UserManager.getInstance().getCurrentPhoneNumber());
        reqBody.setUserInfo(userInfo);
        pushReq.setReqHead(reqHead);
        pushReq.setReqBody(reqBody);
        String json = JsonUtil.getJson(pushReq);
        HashMap hashMap = new HashMap();
        hashMap.put("message", json);
        String sendRequest = sendRequest(this.configManager.getGetMessageURL(), hashMap);
        MsLogUtil.m7979d("PushServer", "PUSH0007----" + sendRequest);
    }

    public static Object[] parseBackData(String str) throws Exception {
        Object[] objArr = {false, null};
        List<PushMessageEntity> pushMessgeList = getPushMessgeList(str);
        return (pushMessgeList == null || pushMessgeList.size() <= 0) ? objArr : new Object[]{true, pushMessgeList};
    }

    private static List<PushMessageEntity> getPushMessgeList(String str) {
        ArrayList arrayList = new ArrayList();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optJSONObject("respBody").optString("bannerState");
        JSONArray optJSONArray = jSONObject.optJSONObject("respBody").optJSONArray("messages");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                PushMessageEntity pushMessageEntity = new PushMessageEntity();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                pushMessageEntity.setDate(System.currentTimeMillis() + "");
                pushMessageEntity.setContent(optJSONObject.optString("content"));
                pushMessageEntity.setId(optJSONObject.optString("id"));
                pushMessageEntity.setMsgType(optJSONObject.optString("firstLevel"));
                pushMessageEntity.setFirstLevel(optJSONObject.optString("firstLevel"));
                pushMessageEntity.setTitle(optJSONObject.optString("title"));
                pushMessageEntity.setUrl(optJSONObject.optString("url"));
                pushMessageEntity.setSecondLevel(optJSONObject.optString("secondLevel"));
                pushMessageEntity.setUserMobile("0");
                pushMessageEntity.setBannerState(optString);
                pushMessageEntity.setEndTime(optJSONObject.optString("endTime"));
                pushMessageEntity.setNewMsgType(optJSONObject.optString("msgType"));
                pushMessageEntity.setSaleDialogUrl(optJSONObject.optString("saleDialogUrl"));
                pushMessageEntity.setSaleImgUrl(optJSONObject.optString("saleImgUrl"));
                pushMessageEntity.setSaleMessListUrl(optJSONObject.optString("saleMessListUrl"));
                pushMessageEntity.setSaleNewTime(optJSONObject.optString("saleNewTime"));
                pushMessageEntity.setSaleUserMob(optJSONObject.optString("saleUserMob"));
                pushMessageEntity.setSaleUnReadCount(optJSONObject.optString("saleUnReadCount"));
                pushMessageEntity.setServiceId(optJSONObject.optString("serviceId"));
                arrayList.add(pushMessageEntity);
                MsLogUtil.m7979d("PushServer", "----获取消息  id" + pushMessageEntity.getId() + "   firstLevel :" + pushMessageEntity.getMsgType() + "  secondLevel :" + pushMessageEntity.getSecondLevel());
            }
        }
        return arrayList;
    }

    public int getRandom() {
        return (new Random().nextInt(9) % 10) + 0;
    }

    public String getRandomMD5Str(int i) {
        return new String[]{"CfAaVIBblv+0ZpR4tL96fw==", "ng3qqXjlHhjV7AyQ07Wd6Q==", "1JHdO8EfZz9H6lHir+klAQ==", "fha8y/FXu9WA3XJRTiiHkA==", "aGYYxBVCoiz6J/PlmKAjIQ==", "n5sniU+su4J0s4OqorWhkQ==", "8CtdIe4aN53MJaWVwSPZBg==", "u+D1kao8M8dnxmo6lVgnIg==", "EXrTBaCCXBqN0/WMpLvPkA==", "paAX4WcC5cK7n2Z14C70aw=="}[i];
    }

    public static String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf8"));
        StringBuilder sb = new StringBuilder(digest.length << 1);
        for (int i = 0; i < digest.length; i++) {
            sb.append(Character.forDigit((digest[i] >> 4) & 15, 16));
            sb.append(Character.forDigit(digest[i] & 15, 16));
        }
        return sb.toString();
    }

    private static String encoderByAes(String str) {
        return AesUtil.aesEncrypt(str);
    }

    private String getFlowNumber() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
        return "yh00001" + simpleDateFormat.format(date);
    }

    private String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    private String sendRequest(String str, Map<String, String> map) throws Exception {
        return App.getAsyncHttpClient().syncPost(str, map);
    }

    public static String getEmuiVersion() {
        try {
            Class<?>[] clsArr = {String.class};
            Object[] objArr = {"ro.build.version.emui"};
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", clsArr).invoke(cls, objArr);
        } catch (Exception unused) {
            return "";
        }
    }
}
