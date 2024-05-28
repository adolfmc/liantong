package com.networkbench.agent.impl.instrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSLocation {
    private final String countryCode;
    private final String region;

    public NBSLocation(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Country code and region must not be null.");
        }
        this.countryCode = str;
        this.region = str2;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getRegion() {
        return this.region;
    }
}
