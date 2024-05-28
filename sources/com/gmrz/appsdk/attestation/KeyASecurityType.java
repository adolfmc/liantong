package com.gmrz.appsdk.attestation;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public enum KeyASecurityType {
    SOFTWARE,
    TEE,
    NOATTESTATION;

    public static KeyASecurityType convert(int i) {
        if (i == 0) {
            return SOFTWARE;
        }
        if (i == 1) {
            return TEE;
        }
        return NOATTESTATION;
    }
}
