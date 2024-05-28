package com.gmrz.appsdk.commlib;

import android.content.Intent;
import android.os.Bundle;
import com.gmrz.appsdk.FidoOut;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.util.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.appsdk.commlib.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UafIntentParser {

    /* renamed from: a */
    private static final String f10312a = "e";

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: UafIntentParser.java */
    /* renamed from: com.gmrz.appsdk.commlib.e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static /* synthetic */ class C4418a {

        /* renamed from: a */
        static final /* synthetic */ int[] f10313a;

        static {
            int[] iArr = new int[UAFIntentType.values().length];
            f10313a = iArr;
            try {
                iArr[UAFIntentType.UAF_OPERATION_RESULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10313a[UAFIntentType.CHECK_POLICY_RESULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10313a[UAFIntentType.DISCOVER_RESULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10313a[UAFIntentType.UAF_OPERATION_COMPLETION_STATUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: a */
    public static FidoOut m15815a(Intent intent) {
        FidoOut fidoOut = new FidoOut();
        fidoOut.fidoStatus = FidoStatus.PROTOCOL_ERROR;
        if (intent == null) {
            Logger.m15757e(f10312a, "Malformed response: data is missing");
            return fidoOut;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Logger.m15757e(f10312a, "Malformed response: UAF intent must have extras");
            return fidoOut;
        } else if (!extras.containsKey("componentName")) {
            Logger.m15757e(f10312a, "Malformed response: mandatory field IEN_COMPONENT_NAME is missing");
            return fidoOut;
        } else if (!extras.containsKey("errorCode")) {
            Logger.m15757e(f10312a, "Malformed response: mandatory field IEN_ERROR_CODE is missing");
            return fidoOut;
        } else if (!extras.containsKey("UAFIntentType")) {
            Logger.m15757e(f10312a, "Malformed response: mandatory field IEN_UAF_INTENT_TYPE is missing");
            return fidoOut;
        } else {
            try {
                switch (C4418a.f10313a[UAFIntentType.valueOf(extras.getString("UAFIntentType")).ordinal()]) {
                    case 1:
                        if (extras.containsKey("message")) {
                            try {
                                JSONObject jSONObject = new JSONObject(extras.getString("message"));
                                if (jSONObject.getString("uafProtocolMessage") != null) {
                                    fidoOut.fidoResponse = jSONObject.getString("uafProtocolMessage");
                                    break;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                break;
                            }
                        } else {
                            Logger.m15756i(f10312a, "IEN_MESSAGE is not set");
                            break;
                        }
                        break;
                    case 2:
                    case 4:
                        break;
                    case 3:
                        if (extras.containsKey("discoveryData")) {
                            fidoOut.discoveryData = extras.getString("discoveryData");
                            break;
                        } else {
                            Logger.m15756i(f10312a, "IEN_DISCOVERY_DATA is not set");
                            break;
                        }
                    default:
                        Logger.m15757e(f10312a, "Unsupported IEN_UAF_INTENT_TYPE");
                        return fidoOut;
                }
                fidoOut.fidoStatus = UafLocalCommClient.m15814a(extras.getShort("errorCode"));
                return fidoOut;
            } catch (IllegalArgumentException e2) {
                Logger.m15757e(f10312a, "Error while processing value of IEN_UAF_INTENT_TYPE");
                e2.printStackTrace();
                return fidoOut;
            }
        }
    }
}
