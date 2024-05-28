package cn.sharesdk.framework.authorize;

import cn.sharesdk.framework.Platform;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface AuthorizeHelper {
    AuthorizeListener getAuthorizeListener();

    String getAuthorizeUrl();

    AuthorizeWebviewClient getAuthorizeWebviewClient(WebAuthorizeActivity webAuthorizeActivity);

    Platform getPlatform();

    String getRedirectUri();

    SSOListener getSSOListener();

    SSOProcessor getSSOProcessor(SSOAuthorizeActivity sSOAuthorizeActivity);
}
