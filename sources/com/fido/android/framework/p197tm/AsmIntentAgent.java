package com.fido.android.framework.p197tm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import com.fido.android.framework.service.Mfac;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.IUafAsmApi;
import com.gmrz.android.client.asm.api.uaf.IUafAsmBinder;
import com.gmrz.android.client.utils.ActivityStarter;
import com.gmrz.android.client.utils.Logger;

/* renamed from: com.fido.android.framework.tm.AsmIntentAgent */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AsmIntentAgent implements IUafAsmApi, IUafAsmBinder {
    private static final String TAG = "AsmIntentAgent";
    private ComponentName selectComponent;

    @Override // com.gmrz.android.client.asm.api.IAsmBinder
    public boolean bind() {
        return true;
    }

    @Override // com.gmrz.android.client.asm.api.uaf.IUafAsmBinder
    public IUafAsmApi getAsmApi() {
        return this;
    }

    @Override // com.gmrz.android.client.asm.api.IAsmBinder
    public Drawable getIcon() {
        return null;
    }

    @Override // com.gmrz.android.client.asm.api.IAsmBinder
    public void unbind() {
    }

    public AsmIntentAgent(ResolveInfo resolveInfo) {
        this.selectComponent = null;
        this.selectComponent = new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name);
    }

    public AsmIntentAgent() {
        this.selectComponent = null;
    }

    @Override // com.gmrz.android.client.asm.api.uaf.IUafAsmApi
    public String process(String str, Activity activity) throws AsmException {
        Activity activity2;
        String str2 = TAG;
        Logger.m15889i(str2, "process() inParam = " + str);
        Intent intent = new Intent(Mfac.Instance().getContext(), AsmIntentHelperActivity.class);
        if (activity != null) {
            Logger.m15895d(TAG, "AsmIntentHelperActivity will be started in same task");
            activity2 = activity;
        } else {
            Context context = Mfac.Instance().getContext();
            intent.addFlags(469762048);
            Logger.m15895d(TAG, "AsmIntentHelperActivity will be started in NEW task");
            activity2 = context;
        }
        intent.putExtra("COMPONENT_NAME", this.selectComponent);
        intent.putExtra("IN_PARAM", str);
        String str3 = (String) ActivityStarter.startActivityForResult(activity2, intent, null, 0);
        if (str3 != null) {
            String str4 = TAG;
            Logger.m15889i(str4, "process() response = " + str3);
            return str3;
        }
        throw new AsmException(AsmError.FAILURE);
    }

    @Override // com.gmrz.android.client.asm.api.IAsmBinder
    public ComponentName getComponentName() {
        return this.selectComponent;
    }
}
