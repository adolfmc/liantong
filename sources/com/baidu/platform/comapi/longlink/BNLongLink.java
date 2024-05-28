package com.baidu.platform.comapi.longlink;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BNLongLink {

    /* renamed from: a */
    private static BNLongLinkInitCallBack f7551a;

    public static void initLongLink() {
        BNLongLinkInitCallBack bNLongLinkInitCallBack = f7551a;
        if (bNLongLinkInitCallBack != null) {
            bNLongLinkInitCallBack.onLongLinkInit();
        }
    }

    public static void registerLongLinkInitCallBack(BNLongLinkInitCallBack bNLongLinkInitCallBack) {
        f7551a = bNLongLinkInitCallBack;
    }

    public static void unRegisterLongLinkInitCallBack(BNLongLinkInitCallBack bNLongLinkInitCallBack) {
        f7551a = bNLongLinkInitCallBack;
    }
}
