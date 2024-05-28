package com.vivo.push.p370d;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.p370d.p371a.SyncProfileInfoCommand;
import com.vivo.push.p370d.p371a.SyncProfileInfoInputDS;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.request.CommandRequest;
import com.vivo.push.restructure.request.IPushRequestCallback;
import com.vivo.push.restructure.request.RequestManager;
import com.vivo.push.util.ConcurrentUtils;
import com.vivo.push.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.vivo.push.d.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SyncProfileInfoImpl implements ISyncProfileInfo {
    @Override // com.vivo.push.p370d.ISyncProfileInfo
    public final void addProfileId(String str, IPushRequestCallback<Integer> iPushRequestCallback) {
        ConcurrentUtils.m5404a().execute(new RunnableC10938e(this, str, iPushRequestCallback));
    }

    @Override // com.vivo.push.p370d.ISyncProfileInfo
    public final void deleteProfileId(String str, IPushRequestCallback<Integer> iPushRequestCallback) {
        ConcurrentUtils.m5404a().execute(new RunnableC10939f(this, str, iPushRequestCallback));
    }

    @Override // com.vivo.push.p370d.ISyncProfileInfo
    public final void deleteAllProfileId(IPushRequestCallback<Integer> iPushRequestCallback) {
        ConcurrentUtils.m5404a().execute(new RunnableC10940g(this, iPushRequestCallback));
    }

    @Override // com.vivo.push.p370d.ISyncProfileInfo
    public final void queryProfileIds(IPushRequestCallback<List<String>> iPushRequestCallback) {
        ConcurrentUtils.m5404a().execute(new RunnableC10941h(this, iPushRequestCallback));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m5715a(SyncProfileInfoImpl syncProfileInfoImpl, String str, IPushRequestCallback iPushRequestCallback, int i) {
        if (PushClientController.m5593a().m5586g().m5756b() != 0) {
            LogUtil.m5347b("core not support sync profileInfo");
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(8102);
                return;
            }
        }
        Context m5591b = PushClientController.m5593a().m5591b();
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new ProfileInfoDS(str));
        }
        RequestManager.m5499a().m5496a(new CommandRequest(new SyncProfileInfoCommand(new SyncProfileInfoInputDS(m5591b.getPackageName(), arrayList, i)), new C10943j(syncProfileInfoImpl, iPushRequestCallback, i), (byte) 0));
    }
}
