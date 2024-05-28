package com.networkbench.agent.impl.harvest;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ApplicationInformation extends HarvestableArray {
    public static final String CHANNEL_ID_DEFAULT_NAME = "TINGYUN_UNKNOWN";
    public static final String CHANNEL_ID_NAME = "NBS_CHANNEL";
    private String packageId;
    private PackageManager packageManager;
    private static final InterfaceC6393e log = C6394f.m10150a();
    public static String customSetChannelId = "TINGYUN_UNKNOWN";
    private String sdkBuildId = NBSAgent.getBuildId();
    private String appName = "";
    private String appVersion = "";
    private String channelId = customSetChannelId;

    public ApplicationInformation(Context context) {
        this.packageId = context.getPackageName();
        this.packageManager = context.getPackageManager();
    }

    public void generateAppInfo() {
        generateAppVersion();
        generateAppName();
        generateChannel();
    }

    private void generateAppVersion() {
        try {
            PackageInfo packageInfo = this.packageManager.getPackageInfo(this.packageId, 0);
            if (packageInfo != null) {
                if (TextUtils.isEmpty(C6638h.m8963w().f17180f)) {
                    this.appVersion = packageInfo.versionName;
                } else {
                    this.appVersion = C6638h.m8963w().f17180f;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            log.mo10121a("Could not determine package version", e);
        }
    }

    private void generateAppName() {
        try {
            ApplicationInfo applicationInfo = this.packageManager.getApplicationInfo(this.packageId, 0);
            if (applicationInfo != null) {
                this.appName = this.packageManager.getApplicationLabel(applicationInfo).toString();
            } else {
                this.appName = this.packageId;
            }
        } catch (Throwable th) {
            log.mo10115e(th.toString());
            this.appName = this.packageId;
        }
    }

    private void generateChannel() {
        this.channelId = getChannelIdStr().trim();
        if (isValidChannelId(this.channelId)) {
            return;
        }
        this.channelId = "";
    }

    private String getChannelIdStr() {
        if (this.channelId.equalsIgnoreCase("TINGYUN_UNKNOWN")) {
            return getChannelIdFromManifest();
        }
        return this.channelId;
    }

    private String getChannelIdFromManifest() {
        try {
            ApplicationInfo applicationInfo = this.packageManager.getApplicationInfo(this.packageId, 128);
            if (applicationInfo.metaData != null) {
                return applicationInfo.metaData.getString("NBS_CHANNEL") == null ? "" : applicationInfo.metaData.getString("NBS_CHANNEL");
            }
            log.mo10122a(" not configure the channelID  in the AndroidManifest");
            return "";
        } catch (Exception e) {
            log.mo10115e(e.toString());
            return "";
        }
    }

    private boolean isValidChannelId(String str) {
        if (str.matches("[0-9]+") || str.length() > 256 || "TINGYUN_UNKNOWN".equalsIgnoreCase(str)) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10115e("channelId is invalid:" + str);
            return false;
        }
        return true;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        configureJsonArrayWithAppInfo(jsonArray);
        jsonArray.add(new JsonPrimitive(this.sdkBuildId));
        m9931h(this.channelId);
        jsonArray.add(new JsonPrimitive(this.channelId));
        return jsonArray;
    }

    private void configureJsonArrayWithAppInfo(JsonArray jsonArray) {
        m9932g(this.packageId);
        jsonArray.add(new JsonPrimitive(this.packageId));
        m9932g(this.appName);
        jsonArray.add(new JsonPrimitive(this.appName));
        m9932g(this.appVersion);
        jsonArray.add(new JsonPrimitive(this.appVersion));
    }

    public JsonArray asSocketJsonArray() {
        JsonArray jsonArray = new JsonArray();
        configureJsonArrayWithAppInfo(jsonArray);
        jsonArray.add(new JsonPrimitive("agent-android"));
        jsonArray.add(new JsonPrimitive(NBSAgent.getVersion()));
        jsonArray.add(new JsonPrimitive(this.sdkBuildId));
        m9931h(this.channelId);
        jsonArray.add(new JsonPrimitive(this.channelId));
        return jsonArray;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getChannelId() {
        return this.channelId;
    }
}
