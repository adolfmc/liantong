package com.baidu.p120ar.auth;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.AuthSetting */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class AuthSetting {
    public int authType;
    public long expiredTimestamp;
    public List<Integer> features;
    public boolean grantedAll;
    public boolean ignoreNetError;
    public boolean noAuthTip;
    public String respectApiKey;
    public String respectAppId;
    public final List<String> respectPackageMd5s = new ArrayList();
}
