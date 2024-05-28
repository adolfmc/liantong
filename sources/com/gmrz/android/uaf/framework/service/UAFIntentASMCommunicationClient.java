package com.gmrz.android.uaf.framework.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.fido.android.framework.p197tm.AsmIntentHelperActivity;
import com.fido.android.framework.service.Mfac;
import com.gmrz.android.client.commlib.ICommunicationClient;
import com.gmrz.android.client.commlib.ICommunicationClientResponse;
import com.gmrz.android.client.mfac.ASMCommunicationClientRequest;
import com.gmrz.android.client.utils.ActivityStarter;
import com.gmrz.android.client.utils.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UAFIntentASMCommunicationClient implements ICommunicationClient {
    private static final String TAG = "UAFIntentASMCommunicationClient";

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void postRequest(String str, String str2, Object obj) {
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void removeRequest(long j) {
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void waitForResponse(long j) {
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public long sendRequest(String str, String str2, Object obj, ICommunicationClientResponse iCommunicationClientResponse) {
        Context context;
        ASMCommunicationClientRequest aSMCommunicationClientRequest = (ASMCommunicationClientRequest) obj;
        Intent intent = new Intent(Mfac.Instance().getContext(), AsmIntentHelperActivity.class);
        if (aSMCommunicationClientRequest.callerActivityContext != null) {
            context = aSMCommunicationClientRequest.callerActivityContext;
            Logger.m15895d(TAG, "AsmIntentHelperActivity will be started in same task");
        } else {
            context = Mfac.Instance().getContext();
            intent.addFlags(469762048);
            Logger.m15895d(TAG, "AsmIntentHelperActivity will be started in NEW task");
        }
        String[] split = str.split(",");
        intent.putExtra("COMPONENT_NAME", new ComponentName(split[0], split[1]));
        intent.putExtra("IN_PARAM", aSMCommunicationClientRequest.sRequest);
        intent.setClass(context, AsmIntentHelperActivity.class);
        iCommunicationClientResponse.onResponse(null, (String) ActivityStarter.startActivityForResult(context, intent, null, 0));
        return 0L;
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public String[] getServiceModuleList(String str) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = Mfac.Instance().getContext().getApplicationContext().getPackageManager();
        Intent intent = new Intent();
        intent.setAction("org.fidoalliance.intent.FIDO_OPERATION");
        intent.setType("application/fido.uaf_asm+json");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 64);
        intent.setAction("org.fidoalliance.aidl.FIDO_OPERATION");
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (true) {
            if (it.hasNext()) {
                ResolveInfo next = it.next();
                intent.setPackage(next.activityInfo.applicationInfo.packageName);
                if (!(packageManager.resolveService(intent, 64) != null) && (!Mfac.Instance().usePreloadedOnly() || (next.activityInfo.applicationInfo.flags & 1) != 0)) {
                    arrayList.add(next.activityInfo.applicationInfo.packageName + "," + next.activityInfo.name);
                }
            } else {
                String str2 = TAG;
                Logger.m15889i(str2, "UAF Intent ASM list: " + arrayList);
                return (String[]) arrayList.toArray(new String[0]);
            }
        }
    }
}
