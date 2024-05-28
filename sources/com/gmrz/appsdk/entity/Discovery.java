package com.gmrz.appsdk.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Discovery {
    public List<Authenticator> availableAuthenticators;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Authenticator {
        public String aaid;
        public String authenticationAlgorithm;
        public String description;
        public String userVerification;
    }

    public List<Authenticator> getAvailableAuthenticators() {
        return this.availableAuthenticators;
    }

    public void setAvailableAuthenticators(List<Authenticator> list) {
        this.availableAuthenticators = list;
    }
}
