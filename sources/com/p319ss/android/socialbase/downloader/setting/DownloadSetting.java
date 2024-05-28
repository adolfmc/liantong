package com.p319ss.android.socialbase.downloader.setting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.utils.LruCache;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.socialbase.downloader.setting.DownloadSetting */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadSetting {
    private static final int POOL_SIZE = 16;
    private static JSONObject sDisabledTaskKeys;
    private static Boolean sGlobalBugFixDefault;
    private static JSONObject sGlobalBugFixSetting;
    @Deprecated
    private static JSONObject sGlobalSetting;
    private static DownloadSetting sLastSetting;
    private static boolean sTaskSettingDisabled;
    private final Boolean mBugFixDefault;
    private final JSONObject mBugFixSetting;
    private int mDownloadId;
    private final JSONObject mTaskSetting;
    private static final LruCache<Integer, DownloadSetting> sCache = new LruCache<>(16, 16);
    private static final DownloadSetting sGlobal = new DownloadSetting(null);

    static {
        init();
    }

    public static void init() {
        JSONObject downloadSetting = DownloadComponentManager.getDownloadSetting();
        sTaskSettingDisabled = downloadSetting.optInt("disable_task_setting", 0) == 1;
        sDisabledTaskKeys = downloadSetting.optJSONObject("disabled_task_keys");
        JSONObject optJSONObject = downloadSetting.optJSONObject("bugfix");
        Boolean bool = null;
        if (optJSONObject != null && optJSONObject.has("default")) {
            bool = Boolean.valueOf(optJSONObject.optInt("default", 0) == 1);
        }
        sGlobalBugFixSetting = optJSONObject;
        sGlobalBugFixDefault = bool;
    }

    private DownloadSetting(JSONObject jSONObject) {
        JSONObject jSONObject2;
        this.mTaskSetting = jSONObject;
        Boolean bool = null;
        if (jSONObject == null || isTaskKeyDisabled("bugfix")) {
            jSONObject2 = null;
        } else {
            jSONObject2 = jSONObject.optJSONObject("bugfix");
            if (jSONObject2 != null && jSONObject2.has("default") && !isTaskKeyDisabled("default")) {
                bool = Boolean.valueOf(jSONObject2.optInt("default", 0) == 1);
            }
        }
        this.mBugFixSetting = jSONObject2;
        this.mBugFixDefault = bool;
    }

    @NonNull
    public static JSONObject getGlobalSettings() {
        return DownloadComponentManager.getDownloadSetting();
    }

    public static void setGlobalBugFix(String str, boolean z) {
        try {
            if (sGlobalBugFixSetting == null) {
                sGlobalBugFixSetting = new JSONObject();
            }
            sGlobalBugFixSetting.put(str, z ? 1 : 0);
        } catch (JSONException unused) {
        }
    }

    @NonNull
    public static DownloadSetting obtainGlobal() {
        return sGlobal;
    }

    @NonNull
    public static DownloadSetting obtain(int i) {
        return obtain(i, null);
    }

    @NonNull
    public static DownloadSetting obtain(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return sGlobal;
        }
        return obtain(downloadInfo.getId(), downloadInfo);
    }

    private static DownloadSetting obtain(int i, DownloadInfo downloadInfo) {
        DownloadSetting downloadSetting;
        DownloadSetting downloadSetting2 = sLastSetting;
        if (downloadSetting2 == null || downloadSetting2.mDownloadId != i) {
            synchronized (sCache) {
                downloadSetting = sCache.get(Integer.valueOf(i));
            }
            if (downloadSetting == null) {
                downloadSetting = downloadInfo == null ? create(i) : create(downloadInfo);
                synchronized (sCache) {
                    sCache.put(Integer.valueOf(i), downloadSetting);
                }
            }
            downloadSetting.mDownloadId = i;
            sLastSetting = downloadSetting;
            return downloadSetting;
        }
        return downloadSetting2;
    }

    public boolean optBugFix(String str) {
        return optBugFix(str, false);
    }

    public boolean optBugFix(String str, boolean z) {
        if (this.mBugFixSetting != null && !isTaskKeyDisabled(str)) {
            if (this.mBugFixSetting.has(str)) {
                return this.mBugFixSetting.optInt(str, z ? 1 : 0) == 1;
            }
            Boolean bool = this.mBugFixDefault;
            if (bool != null) {
                return bool.booleanValue();
            }
        }
        JSONObject jSONObject = sGlobalBugFixSetting;
        if (jSONObject != null) {
            if (jSONObject.has(str)) {
                return sGlobalBugFixSetting.optInt(str, z ? 1 : 0) == 1;
            }
            Boolean bool2 = sGlobalBugFixDefault;
            if (bool2 != null) {
                return bool2.booleanValue();
            }
        }
        return z;
    }

    public int optInt(String str) {
        return optInt(str, 0);
    }

    public int optInt(String str, int i) {
        JSONObject jSONObject = this.mTaskSetting;
        if (jSONObject != null && jSONObject.has(str) && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.optInt(str, i);
        }
        return getGlobalSettings().optInt(str, i);
    }

    public long optLong(String str) {
        return optLong(str, 0L);
    }

    public long optLong(String str, long j) {
        JSONObject jSONObject = this.mTaskSetting;
        if (jSONObject != null && jSONObject.has(str) && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.optLong(str, j);
        }
        return getGlobalSettings().optLong(str, j);
    }

    public double optDouble(String str) {
        return optDouble(str, Double.NaN);
    }

    public double optDouble(String str, double d) {
        JSONObject jSONObject = this.mTaskSetting;
        if (jSONObject != null && jSONObject.has(str) && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.optDouble(str, d);
        }
        return getGlobalSettings().optDouble(str, d);
    }

    public boolean optBoolean(String str) {
        return optBoolean(str, false);
    }

    public boolean optBoolean(String str, boolean z) {
        JSONObject jSONObject = this.mTaskSetting;
        if (jSONObject != null && jSONObject.has(str) && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.optBoolean(str, z);
        }
        return getGlobalSettings().optBoolean(str, z);
    }

    public String optString(String str) {
        return optString(str, "");
    }

    public String optString(String str, String str2) {
        JSONObject jSONObject = this.mTaskSetting;
        if (jSONObject != null && jSONObject.has(str) && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.optString(str, str2);
        }
        return getGlobalSettings().optString(str, str2);
    }

    public JSONObject optJSONObject(String str) {
        JSONObject jSONObject = this.mTaskSetting;
        if (jSONObject != null && jSONObject.has(str) && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.optJSONObject(str);
        }
        return getGlobalSettings().optJSONObject(str);
    }

    public JSONArray optJSONArray(String str) {
        JSONObject jSONObject = this.mTaskSetting;
        if (jSONObject != null && jSONObject.has(str) && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.optJSONArray(str);
        }
        return getGlobalSettings().optJSONArray(str);
    }

    public Object opt(String str) {
        JSONObject jSONObject = this.mTaskSetting;
        if (jSONObject != null && jSONObject.has(str) && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.opt(str);
        }
        return getGlobalSettings().opt(str);
    }

    public boolean has(String str) {
        if (this.mTaskSetting != null && !isTaskKeyDisabled(str)) {
            return this.mTaskSetting.has(str);
        }
        return getGlobalSettings().has(str);
    }

    public static boolean isTaskKeyDisabled(String str) {
        JSONObject jSONObject = sDisabledTaskKeys;
        return jSONObject != null && jSONObject.optInt(str, 0) == 1;
    }

    @NonNull
    public static DownloadSetting obtain(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject == getGlobalSettings() || sTaskSettingDisabled) {
            return sGlobal;
        }
        DownloadSetting downloadSetting = sLastSetting;
        if (downloadSetting == null || downloadSetting.mTaskSetting != jSONObject) {
            synchronized (sCache) {
                for (DownloadSetting downloadSetting2 : sCache.values()) {
                    if (downloadSetting2.mTaskSetting == jSONObject) {
                        sLastSetting = downloadSetting2;
                        return downloadSetting2;
                    }
                }
                DownloadSetting downloadSetting3 = new DownloadSetting(jSONObject);
                sLastSetting = downloadSetting3;
                return downloadSetting3;
            }
        }
        return downloadSetting;
    }

    public static void addTaskDownloadSetting(int i, JSONObject jSONObject) {
        if (jSONObject == null || jSONObject == getGlobalSettings() || sTaskSettingDisabled) {
            return;
        }
        synchronized (sCache) {
            DownloadSetting downloadSetting = sLastSetting;
            if (downloadSetting != null && downloadSetting.mTaskSetting == jSONObject) {
                downloadSetting.mDownloadId = i;
            } else {
                downloadSetting = null;
                Iterator<DownloadSetting> it = sCache.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DownloadSetting next = it.next();
                    if (next.mTaskSetting == jSONObject) {
                        next.mDownloadId = i;
                        downloadSetting = next;
                        break;
                    }
                }
                if (downloadSetting == null) {
                    downloadSetting = new DownloadSetting(jSONObject);
                    downloadSetting.mDownloadId = i;
                }
                sLastSetting = downloadSetting;
            }
            sCache.put(Integer.valueOf(i), downloadSetting);
        }
    }

    public static void removeTaskDownloadSetting(int i) {
        DownloadSetting downloadSetting = sLastSetting;
        if (downloadSetting != null && downloadSetting.mDownloadId == i) {
            sLastSetting = null;
        }
        synchronized (sCache) {
            sCache.remove(Integer.valueOf(i));
        }
    }

    private static DownloadSetting create(int i) {
        DownloadInfo downloadInfo;
        if (sTaskSettingDisabled) {
            return sGlobal;
        }
        Context appContext = DownloadComponentManager.getAppContext();
        if (appContext != null && (downloadInfo = Downloader.getInstance(appContext).getDownloadInfo(i)) != null) {
            return create(downloadInfo);
        }
        return sGlobal;
    }

    private static DownloadSetting create(DownloadInfo downloadInfo) {
        if (sTaskSettingDisabled) {
            return sGlobal;
        }
        try {
            String downloadSettingString = downloadInfo.getDownloadSettingString();
            if (!TextUtils.isEmpty(downloadSettingString)) {
                return new DownloadSetting(new JSONObject(downloadSettingString));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sGlobal;
    }
}
