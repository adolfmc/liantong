package com.gmrz.android.uaf.framework.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import com.fido.android.framework.service.Mfac;
import com.fido.uaf.ver0100.types.ChannelBinding;
import com.fido.uaf.ver0100.types.UafError;
import com.fidoalliance.uaf.app.api.DiscoveryData;
import com.fidoalliance.uaf.app.api.UAFIntentType;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.android.uaf.framework.service.UafProcessor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import org.fidoalliance.uaf.client.UAFMessage;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafIntentProcessor {
    private static final String TAG = "UafIntentProcessor";
    private final Gson mGson = new GsonBuilder().create();
    private Intent mRequestIntent;
    private Intent mResponseIntent;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class UafIntentException extends Exception {
        private static final long serialVersionUID = 1;
        private final UafError.Error mError;

        UafIntentException(UafError.Error error, String str) {
            this.mError = error;
        }
    }

    public Intent processIntent(Intent intent, Context context) {
        Activity activity;
        boolean z;
        this.mResponseIntent = new Intent();
        try {
        } catch (UafIntentException e) {
            Logger.m15891e(TAG, " Error while processing Intent", e);
            setResult(e.mError);
        }
        if (context == null) {
            throw new UafIntentException(UafError.Error.PROTOCOL_ERROR, "callerContext is null");
        }
        setComponentName(context.getPackageName());
        Mfac.init(context.getApplicationContext());
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else {
            Logger.m15883w(TAG, "processIntent: callerContext is not an 'Activity' context. Some features may not work.");
            activity = null;
        }
        if (intent == null) {
            throw new UafIntentException(UafError.Error.PROTOCOL_ERROR, "requestIntent is null");
        }
        this.mRequestIntent = intent;
        Bundle extras = this.mRequestIntent.getExtras();
        if (extras == null) {
            Logger.m15892e(TAG, "Malformed request intent: request intent must have extras.");
            return this.mResponseIntent;
        }
        String string = extras.getString("UAFIntentType");
        if (string == null) {
            throw new UafIntentException(UafError.Error.PROTOCOL_ERROR, "IEN_UAF_INTENT_TYPE is missing");
        }
        switch (UAFIntentType.valueOf(string)) {
            case CHECK_POLICY:
                setType(UAFIntentType.CHECK_POLICY_RESULT);
                z = true;
                break;
            case UAF_OPERATION:
                setType(UAFIntentType.UAF_OPERATION_RESULT);
                if (extras.getString("channelBindings") != null) {
                    z = false;
                    break;
                } else {
                    throw new UafIntentException(UafError.Error.PROTOCOL_ERROR, "IEN_CHANNEL_BINDINGS is missing");
                }
            case DISCOVER:
                setType(UAFIntentType.DISCOVER_RESULT);
                setDiscoveryData(new UafProcessor().getDiscoveryData(activity));
                setResult(UafError.Error.NO_ERROR);
                return this.mResponseIntent;
            default:
                throw new UafIntentException(UafError.Error.PROTOCOL_ERROR, "Unsupported Intent Type " + string);
        }
        UAFMessage uAFMessage = getUAFMessage();
        if (uAFMessage == null) {
            throw new UafIntentException(UafError.Error.PROTOCOL_ERROR, "IEN_MESSAGE is missing ");
        }
        UafProcessor.UafRequestTask uafRequestTask = new UafProcessor.UafRequestTask(uAFMessage.uafProtocolMessage, getChannelBinding(), getOrigin(), getCallerPackageName(activity, context), Binder.getCallingPid(), Binder.getCallingUid(), context, uAFMessage.additionalData, z, activity);
        Logger.m15895d(TAG + "-###", "UAF Request: " + uafRequestTask);
        UafProcessor.UafResponseTask processUafRequest = new UafProcessor().processUafRequest(uafRequestTask);
        Logger.m15895d(TAG + "-###", "UAF Response: " + processUafRequest.toString());
        setUAFMessage(processUafRequest.uafResponseMessage);
        setResult(processUafRequest.returnCode);
        return this.mResponseIntent;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getCallerPackageName(android.app.Activity r2, android.content.Context r3) {
        /*
            r1 = this;
            if (r2 == 0) goto L14
            android.content.ComponentName r2 = r2.getCallingActivity()
            if (r2 == 0) goto Ld
            java.lang.String r2 = r2.getPackageName()
            goto L15
        Ld:
            java.lang.String r2 = com.gmrz.android.uaf.framework.service.UafIntentProcessor.TAG
            java.lang.String r0 = "processIntent: The activity is not started for result"
            com.gmrz.android.client.utils.Logger.m15883w(r2, r0)
        L14:
            r2 = 0
        L15:
            if (r2 != 0) goto L22
            java.lang.String r2 = com.gmrz.android.uaf.framework.service.UafIntentProcessor.TAG
            java.lang.String r0 = "processIntent: Assuming this is the local case. The caller is this application."
            com.gmrz.android.client.utils.Logger.m15889i(r2, r0)
            java.lang.String r2 = r3.getPackageName()
        L22:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.android.uaf.framework.service.UafIntentProcessor.getCallerPackageName(android.app.Activity, android.content.Context):java.lang.String");
    }

    private UAFMessage getUAFMessage() {
        String stringExtra = this.mRequestIntent.getStringExtra("message");
        if (stringExtra == null || stringExtra.length() <= 0) {
            return null;
        }
        Gson gson = this.mGson;
        return (UAFMessage) (!(gson instanceof Gson) ? gson.fromJson(stringExtra, (Class<Object>) UAFMessage.class) : NBSGsonInstrumentation.fromJson(gson, stringExtra, (Class<Object>) UAFMessage.class));
    }

    private String getOrigin() {
        return this.mRequestIntent.getStringExtra("origin");
    }

    private ChannelBinding getChannelBinding() {
        String stringExtra = this.mRequestIntent.getStringExtra("channelBindings");
        if (stringExtra != null) {
            Gson gson = this.mGson;
            return (ChannelBinding) (!(gson instanceof Gson) ? gson.fromJson(stringExtra, (Class<Object>) ChannelBinding.class) : NBSGsonInstrumentation.fromJson(gson, stringExtra, (Class<Object>) ChannelBinding.class));
        }
        return null;
    }

    private void setDiscoveryData(DiscoveryData discoveryData) {
        Intent intent = this.mResponseIntent;
        Gson gson = this.mGson;
        intent.putExtra("discoveryData", !(gson instanceof Gson) ? gson.toJson(discoveryData) : NBSGsonInstrumentation.toJson(gson, discoveryData));
    }

    private void setType(UAFIntentType uAFIntentType) {
        this.mResponseIntent.putExtra("UAFIntentType", uAFIntentType.name());
    }

    private void setResult(UafError.Error error) {
        this.mResponseIntent.putExtra("errorCode", error.code());
    }

    private void setComponentName(String str) {
        this.mResponseIntent.putExtra("componentName", str);
    }

    private void setUAFMessage(UAFMessage uAFMessage) {
        if (uAFMessage != null) {
            Gson gson = this.mGson;
            this.mResponseIntent.putExtra("message", !(gson instanceof Gson) ? gson.toJson(uAFMessage) : NBSGsonInstrumentation.toJson(gson, uAFMessage));
        }
    }
}
