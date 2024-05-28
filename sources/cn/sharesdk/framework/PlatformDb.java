package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PlatformDb {
    private static final String DB_NAME = "cn_sharesdk_weibodb";
    private String platformNname;
    private int platformVersion;

    /* renamed from: sp */
    private SharePrefrenceHelper f2744sp = new SharePrefrenceHelper(MobSDK.getContext());

    public PlatformDb(String str, int i) {
        SharePrefrenceHelper sharePrefrenceHelper = this.f2744sp;
        sharePrefrenceHelper.open("cn_sharesdk_weibodb_" + str, i);
        this.platformNname = str;
        this.platformVersion = i;
    }

    public void put(String str, String str2) {
        this.f2744sp.putString(str, str2);
    }

    public String get(String str) {
        return this.f2744sp.getString(str);
    }

    public String getToken() {
        return this.f2744sp.getString("token");
    }

    public void putToken(String str) {
        this.f2744sp.putString("token", str);
    }

    public String getTokenSecret() {
        return this.f2744sp.getString("secret");
    }

    public void putTokenSecret(String str) {
        this.f2744sp.putString("secret", str);
    }

    public long getExpiresIn() {
        try {
            try {
                return this.f2744sp.getLong("expiresIn");
            } catch (Throwable unused) {
                return this.f2744sp.getInt("expiresIn");
            }
        } catch (Throwable unused2) {
            return 0L;
        }
    }

    public void putExpiresIn(long j) {
        this.f2744sp.putLong("expiresIn", Long.valueOf(j));
        this.f2744sp.putLong("expiresTime", Long.valueOf(System.currentTimeMillis()));
    }

    public long getExpiresTime() {
        return this.f2744sp.getLong("expiresTime") + (getExpiresIn() * 1000);
    }

    public int getPlatformVersion() {
        return this.platformVersion;
    }

    public String getPlatformNname() {
        return this.platformNname;
    }

    public void putUserId(String str) {
        this.f2744sp.putString("userID", str);
    }

    public String getUserId() {
        String string = this.f2744sp.getString("userID");
        return TextUtils.isEmpty(string) ? this.f2744sp.getString("weibo") : string;
    }

    public String getUserName() {
        return this.f2744sp.getString("nickname");
    }

    public String getUserIcon() {
        return this.f2744sp.getString("icon");
    }

    public void removeAccount() {
        this.f2744sp.clear();
    }

    public String exportData() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(this.f2744sp.getAll());
            return new Hashon().fromHashMap(hashMap);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }

    public void importData(String str) {
        try {
            HashMap<String, Object> fromJson = new Hashon().fromJson(str);
            if (fromJson != null) {
                this.f2744sp.putAll(fromJson);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }

    public boolean isValid() {
        String token = getToken();
        if (token == null || token.length() <= 0) {
            return false;
        }
        return getExpiresIn() == 0 || getExpiresTime() > System.currentTimeMillis();
    }

    public String getUserGender() {
        String string = this.f2744sp.getString("gender");
        if ("0".equals(string)) {
            return "m";
        }
        if ("1".equals(string)) {
            return "f";
        }
        return null;
    }
}
