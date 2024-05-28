package com.bytedance.pangle.download;

import android.support.annotation.Keep;
import java.util.List;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PluginDownloadBean {
    public List<String> mBackupUrlList;
    public int mFlag;
    public long mFollowId;
    public String mMd5;
    public String mPackageName;
    public String mUrl;
    public int mVersionCode;
    public int mApiVersionMin = 0;
    public int mApiVersionMax = Integer.MAX_VALUE;

    public boolean isUnInstall() {
        return this.mFlag == 1;
    }

    public boolean isRevert() {
        return this.mFlag == 3;
    }
}
