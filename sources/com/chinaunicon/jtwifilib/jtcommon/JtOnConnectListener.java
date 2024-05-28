package com.chinaunicon.jtwifilib.jtcommon;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface JtOnConnectListener {
    void dowloadHtmlSuccess(String str);

    void downLoadHtmlProgress(int i);

    void downloadHtmlFiled(Exception exc);

    void finish();

    void onClose(String str);

    void onError(String str);

    void onMessage(String str);

    void startDowloadHtml();

    void unZipFiled(Exception exc);

    void unZipStat();

    void unZipSuccess();
}
