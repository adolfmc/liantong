package com.huawei.hms.framework.network.grs;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class GrsClient {
    private static final String EMPTY_STRING = "";
    private final C4920c grsClientGlobal;

    public GrsClient(Context context, GrsBaseInfo grsBaseInfo) {
        if (context == null || grsBaseInfo == null) {
            throw new NullPointerException("invalid init params for context is null or GrsBaseInfo instance is null Object.");
        }
        this.grsClientGlobal = C4922d.m15005a(grsBaseInfo, context);
    }

    public void ayncGetGrsUrl(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack) {
        C4920c c4920c = this.grsClientGlobal;
        if (c4920c == null) {
            iQueryUrlCallBack.onCallBackFail(-8);
        } else {
            c4920c.m15016a(str, str2, iQueryUrlCallBack);
        }
    }

    public void ayncGetGrsUrls(String str, IQueryUrlsCallBack iQueryUrlsCallBack) {
        C4920c c4920c = this.grsClientGlobal;
        if (c4920c == null) {
            iQueryUrlsCallBack.onCallBackFail(-8);
        } else {
            c4920c.m15018a(str, iQueryUrlsCallBack);
        }
    }

    public void clearSp() {
        C4920c c4920c = this.grsClientGlobal;
        if (c4920c == null) {
            return;
        }
        c4920c.m15029a();
    }

    public boolean forceExpire() {
        C4920c c4920c = this.grsClientGlobal;
        if (c4920c == null) {
            return false;
        }
        return c4920c.m15014b();
    }

    public String synGetGrsUrl(String str, String str2) {
        C4920c c4920c = this.grsClientGlobal;
        return c4920c == null ? "" : c4920c.m15017a(str, str2);
    }

    public Map<String, String> synGetGrsUrls(String str) {
        C4920c c4920c = this.grsClientGlobal;
        return c4920c == null ? new HashMap() : c4920c.m15019a(str);
    }
}
