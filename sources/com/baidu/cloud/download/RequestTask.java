package com.baidu.cloud.download;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RequestTask {
    public static final int STATUS_COMPLETE = 6;
    public static final int STATUS_CONNECTING = 1;
    public static final int STATUS_CONNECT_ERROR = 2;
    public static final int STATUS_DOWNLOADING = 3;
    public static final int STATUS_DOWNLOAD_ERROR = 5;
    public static final int STATUS_NOT_DOWNLOAD = 0;
    public static final int STATUS_PAUSED = 4;
    private int progress;
    private String savePath;
    private String name = "";

    /* renamed from: id */
    private String f4347id = "";
    private String image = "";
    private String url = "";
    private String downloadPerSize = "";
    private int status = 0;

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.name);
            jSONObject.put("id", this.f4347id);
            jSONObject.put("image", this.image);
            jSONObject.put("url", this.url);
            jSONObject.put("progress", this.progress);
            jSONObject.put("downloadPerSize", this.downloadPerSize);
            jSONObject.put("status", this.status);
            jSONObject.put("savePath", this.savePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public boolean parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.name = jSONObject.optString("name");
            this.f4347id = jSONObject.optString("id");
            this.image = jSONObject.optString("image");
            this.url = jSONObject.optString("url");
            this.progress = jSONObject.optInt("progress");
            this.downloadPerSize = jSONObject.optString("downloadPerSize");
            this.status = jSONObject.optInt("status");
            this.savePath = jSONObject.optString("savePath");
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    public static RequestTask createTaskWithUrl(String str) {
        RequestTask requestTask = new RequestTask();
        requestTask.url = str;
        return requestTask;
    }

    public static RequestTask createTaskWithUrlAndCover(String str, String str2) {
        RequestTask requestTask = new RequestTask();
        requestTask.url = str2;
        requestTask.image = str;
        return requestTask;
    }

    public static RequestTask createDetailTask(String str, String str2, String str3, String str4) {
        RequestTask requestTask = new RequestTask();
        requestTask.url = str4;
        requestTask.image = str3;
        requestTask.f4347id = str;
        requestTask.name = str2;
        return requestTask;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getId() {
        return this.f4347id;
    }

    public void setId(String str) {
        this.f4347id = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getDownloadPerSize() {
        return this.downloadPerSize;
    }

    public void setDownloadPerSize(String str) {
        this.downloadPerSize = str;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public void setSavePath(String str) {
        this.savePath = str;
    }
}
