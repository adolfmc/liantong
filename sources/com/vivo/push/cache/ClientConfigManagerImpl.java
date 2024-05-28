package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.model.ConfigItem;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.LogUtil;
import java.util.HashSet;
import java.util.Set;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ClientConfigManagerImpl implements IConfigManager {
    private static final String TAG = "ClientConfigManager";
    private static volatile ClientConfigManagerImpl sClientConfigManagerImpl;
    private AppConfigSettings mAppConfigSettings;
    private Context mContext;
    private PushConfigSettings mPushConfigSettings;

    public Set<String> getBlackEventList() {
        return null;
    }

    private ClientConfigManagerImpl(Context context) {
        this.mContext = ContextDelegate.getContext(context);
        this.mAppConfigSettings = new AppConfigSettings(this.mContext);
        this.mPushConfigSettings = new PushConfigSettings(this.mContext);
    }

    public static synchronized ClientConfigManagerImpl getInstance(Context context) {
        ClientConfigManagerImpl clientConfigManagerImpl;
        synchronized (ClientConfigManagerImpl.class) {
            if (sClientConfigManagerImpl == null) {
                sClientConfigManagerImpl = new ClientConfigManagerImpl(context);
            }
            clientConfigManagerImpl = sClientConfigManagerImpl;
        }
        return clientConfigManagerImpl;
    }

    public boolean isEnablePush() {
        prepareAppConfig();
        ConfigItem m5750c = this.mAppConfigSettings.m5750c(this.mContext.getPackageName());
        if (m5750c != null) {
            return "1".equals(m5750c.m5603b());
        }
        return true;
    }

    private void prepareAppConfig() {
        AppConfigSettings appConfigSettings = this.mAppConfigSettings;
        if (appConfigSettings == null) {
            this.mAppConfigSettings = new AppConfigSettings(this.mContext);
        } else {
            appConfigSettings.m5746c();
        }
    }

    public void clearPush() {
        this.mAppConfigSettings.m5744d();
    }

    @Override // com.vivo.push.cache.IConfigManager
    public boolean isInBlackList(long j) {
        String[] split;
        String m5737c = preparePushConfigSettings().m5737c("BL");
        if (!TextUtils.isEmpty(m5737c)) {
            for (String str : m5737c.split(",")) {
                try {
                    if (!TextUtils.isEmpty(str) && Long.parseLong(str) == j) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0021 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isCancleBroadcastReceiver() {
        /*
            r3 = this;
            com.vivo.push.cache.e r0 = r3.preparePushConfigSettings()
            java.lang.String r1 = "PSM"
            java.lang.String r0 = r0.m5737c(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 != 0) goto L1a
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L16
            goto L1b
        L16:
            r0 = move-exception
            r0.printStackTrace()
        L1a:
            r0 = r2
        L1b:
            r0 = r0 & 4
            if (r0 == 0) goto L21
            r0 = 1
            return r0
        L21:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.cache.ClientConfigManagerImpl.isCancleBroadcastReceiver():boolean");
    }

    private PushConfigSettings preparePushConfigSettings() {
        PushConfigSettings pushConfigSettings = this.mPushConfigSettings;
        if (pushConfigSettings == null) {
            this.mPushConfigSettings = new PushConfigSettings(this.mContext);
        } else {
            pushConfigSettings.m5746c();
        }
        return this.mPushConfigSettings;
    }

    public String getSuitTag() {
        return preparePushConfigSettings().m5737c("CSPT");
    }

    public boolean isDebug() {
        this.mAppConfigSettings.m5746c();
        return AppConfigSettings.m5752a(this.mAppConfigSettings.m5751b());
    }

    public boolean isDebug(int i) {
        return AppConfigSettings.m5752a(i);
    }

    public String getValueByKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        this.mPushConfigSettings.m5746c();
        return this.mPushConfigSettings.m5737c(str);
    }

    public Set<Long> getWhiteLogList() {
        HashSet hashSet = new HashSet();
        String valueByKey = getValueByKey("WLL");
        if (!TextUtils.isEmpty(valueByKey)) {
            for (String str : valueByKey.split(",")) {
                try {
                    hashSet.add(Long.valueOf(Long.parseLong(str)));
                } catch (Exception unused) {
                }
            }
        }
        LogUtil.m5341d(TAG, " initWhiteLogList ".concat(String.valueOf(hashSet)));
        return hashSet;
    }
}
