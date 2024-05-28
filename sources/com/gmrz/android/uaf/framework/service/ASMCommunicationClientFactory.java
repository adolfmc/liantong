package com.gmrz.android.uaf.framework.service;

import com.gmrz.android.client.commlib.ICommunicationClient;
import com.gmrz.android.client.utils.Logger;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ASMCommunicationClientFactory {
    private static final String TAG = "ASMCommunicationClientFactory";

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ASMDestinationType {
        public static final String ASM_INTENT = "INTENT";
        public static final String ASM_LOCAL = "LOCAL";
        public static final String ASM_SERVICE = "SERVICE";
    }

    ICommunicationClient createInstance(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -2130433380) {
            if (str.equals("INTENT")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1592831339) {
            if (hashCode == 72607563 && str.equals("LOCAL")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("SERVICE")) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return new UAFIntentASMCommunicationClient();
            case 1:
                return new UAFLocalASMCommunicationClient();
            case 2:
                return null;
            default:
                String str2 = TAG;
                Logger.m15892e(str2, "createInstance: Unsupported Destination type : " + str);
                return null;
        }
    }
}
