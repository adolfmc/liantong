package com.mob.commons;

import com.mob.tools.proguard.EverythingKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public enum InternationalDomain implements EverythingKeeper {
    JP("jp", "Japan"),
    US("us", "United States of America"),
    DEFAULT(null, null);
    
    private String domain;
    private String region;

    InternationalDomain(String str, String str2) {
        this.domain = str;
        this.region = str2;
    }

    public static InternationalDomain domainOf(String str) {
        InternationalDomain[] values;
        if (str == null) {
            return DEFAULT;
        }
        for (InternationalDomain internationalDomain : values()) {
            if (str.equalsIgnoreCase(internationalDomain.domain)) {
                return internationalDomain;
            }
        }
        return DEFAULT;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getRegion() {
        return this.region;
    }
}
