package com.bytedance.android.live.saas.middleware.applog;

import com.bytedance.android.live.base.api.IHostPermission;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AppLogConfig {
    private String appLogId;
    private IHostPermission hostPermission;
    private IAppLog impl;
    private boolean isToBVersion;
    private boolean standalone;

    public AppLogConfig() {
        this.impl = null;
        this.standalone = false;
        this.isToBVersion = false;
        this.appLogId = null;
        this.hostPermission = null;
    }

    public AppLogConfig(IAppLog iAppLog, boolean z) {
        this(iAppLog, z, false);
    }

    public AppLogConfig(IAppLog iAppLog, boolean z, boolean z2) {
        this(iAppLog, z, z2, null);
    }

    public AppLogConfig(IAppLog iAppLog, boolean z, boolean z2, String str) {
        this(iAppLog, z, z2, str, null);
    }

    public AppLogConfig(IAppLog iAppLog, boolean z, boolean z2, String str, IHostPermission iHostPermission) {
        this.impl = null;
        this.standalone = false;
        this.isToBVersion = false;
        this.appLogId = null;
        this.hostPermission = null;
        this.impl = iAppLog;
        this.standalone = z;
        this.isToBVersion = z2;
        this.appLogId = str;
        this.hostPermission = iHostPermission;
    }

    public IAppLog getImpl() {
        return this.impl;
    }

    public void setImpl(IAppLog iAppLog) {
        this.impl = iAppLog;
    }

    public boolean getStandalone() {
        return this.standalone;
    }

    public void setStandalone(boolean z) {
        this.standalone = z;
    }

    public boolean isToBVersion() {
        return this.isToBVersion;
    }

    public void setToBVersion(boolean z) {
        this.isToBVersion = z;
    }

    public String getAppLogId() {
        return this.appLogId;
    }

    public void setAppLogId(String str) {
        this.appLogId = str;
    }

    public IHostPermission getHostPermission() {
        return this.hostPermission;
    }

    public void setHostPermission(IHostPermission iHostPermission) {
        this.hostPermission = iHostPermission;
    }
}
