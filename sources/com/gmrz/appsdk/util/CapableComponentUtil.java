package com.gmrz.appsdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CapableComponentUtil {
    private static CapableComponentUtil ourInstance;
    private final Context context;

    private CapableComponentUtil(Context context) {
        this.context = context;
    }

    public static CapableComponentUtil getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new CapableComponentUtil(context);
        }
        return ourInstance;
    }

    public ResolveInfo queryCapableASMActivity() {
        Context context = this.context;
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("org.fidoalliance.intent.FIDO_OPERATION");
        intent.setType("application/fido.uaf_asm+json");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() > 0) {
            return queryIntentActivities.get(0);
        }
        return null;
    }

    public ResolveInfo queryCapableActivity() {
        Context context = this.context;
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("org.fidoalliance.intent.FIDO_OPERATION");
        intent.setType("application/fido.uaf_client+json");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() > 0) {
            return queryIntentActivities.get(0);
        }
        return null;
    }

    public ResolveInfo queryCapableService() {
        Context context = this.context;
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("org.fidoalliance.aidl.FIDO_OPERATION");
        intent.setType("application/fido.uaf_client+json");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices.size() > 0) {
            return queryIntentServices.get(0);
        }
        return null;
    }
}
