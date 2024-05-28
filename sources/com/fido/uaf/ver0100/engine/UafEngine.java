package com.fido.uaf.ver0100.engine;

import android.util.Base64;
import com.fido.uaf.ver0100.message.DeregistrationRequest;
import com.fido.uaf.ver0100.message.UafMessage;
import com.fido.uaf.ver0100.types.DeregisterAuthenticator;
import com.fido.uaf.ver0100.types.UafError;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafEngine {

    /* renamed from: a */
    private static final String f10117a = "UafEngine";

    /* JADX WARN: Removed duplicated region for block: B:127:0x00b7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b3 A[Catch: JsonParseException -> 0x01cc, LOOP:0: B:12:0x002a->B:53:0x00b3, LOOP_END, TryCatch #0 {JsonParseException -> 0x01cc, blocks: (B:5:0x0010, B:7:0x0016, B:9:0x0022, B:11:0x0027, B:14:0x002d, B:16:0x0033, B:53:0x00b3, B:54:0x00b7, B:55:0x00c0, B:17:0x003c, B:19:0x0040, B:20:0x0049, B:22:0x004f, B:30:0x006c, B:40:0x008e, B:42:0x0094, B:44:0x009a, B:47:0x00a3, B:33:0x0074, B:35:0x007a, B:37:0x0082, B:23:0x0058, B:25:0x005c, B:28:0x0062, B:57:0x00c4, B:59:0x00c7, B:60:0x00ca, B:62:0x00cd, B:64:0x00df, B:65:0x00e2, B:66:0x00e9, B:67:0x00ea, B:69:0x00f6, B:72:0x00fd, B:73:0x0109, B:74:0x010c, B:115:0x01b6, B:116:0x01bb, B:75:0x0110, B:77:0x0116, B:79:0x0121, B:110:0x01a9, B:113:0x01b0, B:114:0x01b5, B:78:0x011b, B:80:0x0130, B:82:0x0136, B:84:0x0141, B:86:0x0152, B:88:0x0156, B:90:0x015c, B:91:0x015f, B:83:0x013b, B:92:0x0162, B:94:0x0168, B:96:0x0173, B:98:0x0184, B:100:0x0188, B:102:0x0190, B:104:0x019a, B:106:0x019e, B:108:0x01a4, B:109:0x01a7, B:95:0x016d, B:117:0x01bc, B:118:0x01c3, B:119:0x01c4, B:120:0x01cb, B:8:0x001b), top: B:125:0x0010 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.fido.uaf.ver0100.message.UafMessage parseRequest(java.lang.String r12) throws com.fido.uaf.ver0100.types.UafException {
        /*
            Method dump skipped, instructions count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fido.uaf.ver0100.engine.UafEngine.parseRequest(java.lang.String):com.fido.uaf.ver0100.message.UafMessage");
    }

    public String buildResponse(UafMessage[] uafMessageArr) {
        Gson create = new GsonBuilder().create();
        return !(create instanceof Gson) ? create.toJson(uafMessageArr) : NBSGsonInstrumentation.toJson(create, uafMessageArr);
    }

    /* renamed from: a */
    private static UafError.Error m15929a(DeregistrationRequest deregistrationRequest) {
        if (deregistrationRequest.authenticators != null && deregistrationRequest.authenticators.size() != 0) {
            for (DeregisterAuthenticator deregisterAuthenticator : deregistrationRequest.authenticators) {
                if (deregisterAuthenticator.aaid != null && deregisterAuthenticator.keyID != null) {
                    if (deregisterAuthenticator.aaid.isEmpty()) {
                        if (deregisterAuthenticator.keyID.isEmpty()) {
                            return UafError.Error.NO_ERROR;
                        }
                        Logger.m15892e(f10117a, "AAID is empty but KeyID is not");
                        return UafError.Error.PROTOCOL_ERROR;
                    } else if (deregisterAuthenticator.aaid.length() == 9 && deregisterAuthenticator.aaid.indexOf("#") == 4) {
                        if (!m15927a(deregisterAuthenticator.keyID, 32, 2048)) {
                            Logger.m15892e(f10117a, "KeyID format is invalid");
                            return UafError.Error.PROTOCOL_ERROR;
                        }
                    } else {
                        Logger.m15892e(f10117a, "AAID format is invalid");
                        return UafError.Error.PROTOCOL_ERROR;
                    }
                } else {
                    Logger.m15892e(f10117a, "Either AAID or KeyID is null");
                    return UafError.Error.PROTOCOL_ERROR;
                }
            }
            return UafError.Error.NO_ERROR;
        }
        Logger.m15892e(f10117a, "No authenticators list provided");
        return UafError.Error.PROTOCOL_ERROR;
    }

    /* renamed from: a */
    private static boolean m15928a(String str) {
        if (str != null && !str.equals("")) {
            if (m15927a(str, 8, 64)) {
                return true;
            }
            Logger.m15892e(f10117a, "Server Challenge format is incorrect.");
            return false;
        }
        Logger.m15892e(f10117a, "Server Challenge is missing.");
        return false;
    }

    /* renamed from: a */
    private static boolean m15927a(String str, int i, int i2) {
        char[] charArray;
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '-' && c != '_') {
                Logger.m15892e(f10117a, "Base64 data is not URL_SAFE encoded");
                return false;
            }
        }
        if (str.endsWith("=")) {
            Logger.m15892e(f10117a, "Base64 data is padded");
            return false;
        }
        try {
            byte[] decode = Base64.decode(str.getBytes(Charsets.utf8Charset), 8);
            if (decode.length < i || decode.length > i2) {
                Logger.m15892e(f10117a, "The data length is not in the range [" + i + "..." + i2 + "].");
                return false;
            }
            return true;
        } catch (IllegalArgumentException unused) {
            Logger.m15892e(f10117a, "The data is not a valid base64-encoded string.");
            return false;
        }
    }
}
