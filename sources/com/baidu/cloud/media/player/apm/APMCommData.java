package com.baidu.cloud.media.player.apm;

import android.content.Context;
import android.util.Log;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class APMCommData {
    static final String TAG = "BaseInfo";
    private AppInfo appInfo;
    private JSONObject commDataJson;
    private Device device;
    private JSONObject envInfoJson;
    private Network network;
    private String url;
    private String vvid;
    private String playId = "";
    private String playerVersion = "2.3.6";
    private String decodeMode = "sw";

    /* renamed from: ak */
    private String f4371ak = "";

    public APMCommData(Context context, String str) {
        this.device = new Device(context, str);
        this.network = new Network(context);
        this.appInfo = new AppInfo(context);
    }

    public void updateSession(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        this.url = str;
        this.vvid = generateVVID();
        Log.i("BaseInfo", "Current vvid is:" + this.vvid);
        this.commDataJson = null;
    }

    public void updateplyId(String str) {
        this.playId = str;
    }

    public void setPlayerRelated(String str, String str2, String str3) {
        this.playerVersion = str;
        this.decodeMode = str2;
        this.f4371ak = str3;
        this.commDataJson = null;
    }

    public JSONObject toJson() {
        if (this.commDataJson == null) {
            this.commDataJson = new JSONObject();
            try {
                this.commDataJson.put("url", this.url);
                this.commDataJson.put("vvid", this.vvid);
                this.commDataJson.put("isLive", true);
                this.commDataJson.put("playerVersion", this.playerVersion);
                this.commDataJson.put("decodeMode", this.decodeMode);
                this.commDataJson.put("ak", this.f4371ak);
                this.commDataJson.put("bitrate", 0);
            } catch (Exception e) {
                Log.d("BaseInfo", "" + e.getMessage());
            }
        }
        try {
            this.commDataJson.put("playID", this.playId);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return this.commDataJson;
    }

    public JSONObject toEnvJson() {
        if (this.envInfoJson == null) {
            this.envInfoJson = new JSONObject();
            try {
                this.envInfoJson.put("device", this.device.toJson());
                this.envInfoJson.put("network", this.network.toJson());
                this.envInfoJson.put("app", this.appInfo.toJson());
            } catch (Exception e) {
                Log.d("BaseInfo", "" + e.getMessage());
            }
        }
        return this.envInfoJson;
    }

    private String generateVVID() {
        return ("" + System.currentTimeMillis()) + randomString(8);
    }

    public String getVvid() {
        return this.vvid;
    }

    private String randomString(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz".charAt(random.nextInt(26)));
        }
        return stringBuffer.toString();
    }
}
