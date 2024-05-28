package com.fidoalliance.uaf.app.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DiscoveryData {
    public String clientVendor;
    public Version clientVersion;
    public List<Version> supportedUAFVersions = new ArrayList(Collections.singletonList(new Version(1, 0)));
    public List<Authenticator> availableAuthenticators = new ArrayList();
}
