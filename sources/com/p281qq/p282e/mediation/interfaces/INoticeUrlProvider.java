package com.p281qq.p282e.mediation.interfaces;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.mediation.interfaces.INoticeUrlProvider */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface INoticeUrlProvider {
    public static final String IMPL_CLASS_NAME = "util.NoticeUrlProviderImpl";

    String getAssembledLossNoticeUrl(String str, String str2, boolean z);

    String getAssembledWinNoticeUrl(String str, String str2, boolean z);

    String getOtherAssembledLossNoticeUrl(String str, String str2);
}
