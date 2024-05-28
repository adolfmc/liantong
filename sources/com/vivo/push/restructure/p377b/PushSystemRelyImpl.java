package com.vivo.push.restructure.p377b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.DebugUtil;
import com.vivo.push.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.vivo.push.restructure.b.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushSystemRelyImpl implements IPushSystemRely {
    @Override // com.vivo.push.util.ISystemRely
    /* renamed from: a */
    public final List<String> mo5381a(Context context) {
        DebugUtil.m5390a("findAllCoreClientPush");
        List<ResolveInfo> list = null;
        if (!PushClientController.m5593a().m5588e().mo5524l().isAgreePrivacyStatement()) {
            LogUtil.m5341d("PushSystemRelyImpl", " findAllCorePush  isAgreePrivacyStatement() is false ");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            list = context.getPackageManager().queryIntentServices(new Intent("com.vivo.pushservice.action.PUSH_SERVICE"), 576);
        } catch (Exception unused) {
        }
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = list.get(i);
                if (resolveInfo != null) {
                    String str = resolveInfo.serviceInfo.packageName;
                    if (!TextUtils.isEmpty(str)) {
                        arrayList.add(str);
                    }
                }
            }
        }
        if (arrayList.size() <= 0) {
            LogUtil.m5341d("PushSystemRelyImpl", "get all push packages is null");
        }
        return arrayList;
    }
}
