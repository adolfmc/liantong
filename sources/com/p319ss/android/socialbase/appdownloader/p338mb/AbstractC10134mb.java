package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.util.Log;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/* renamed from: com.ss.android.socialbase.appdownloader.mb.mb */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC10134mb implements InterfaceC10128h {

    /* renamed from: b */
    protected final String f19541b;

    /* renamed from: mb */
    protected final Context f19542mb;

    /* renamed from: ox */
    protected final DownloadSetting f19543ox;

    public AbstractC10134mb(Context context, DownloadSetting downloadSetting, String str) {
        this.f19542mb = context;
        this.f19543ox = downloadSetting;
        this.f19541b = str;
    }

    /* renamed from: mb */
    public boolean m6731mb() {
        if (this.f19542mb == null) {
            return false;
        }
        try {
        } catch (Throwable th) {
            if (Logger.debug()) {
                Log.e("AbsDevicePlan", "check is valid failed!", th);
            }
        }
        return mo6729ox().resolveActivity(this.f19542mb.getPackageManager()) != null;
    }
}
