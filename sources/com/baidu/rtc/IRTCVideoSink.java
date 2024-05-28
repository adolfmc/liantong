package com.baidu.rtc;

import com.baidu.rtc.logreport.SLIReportInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IRTCVideoSink {
    void attach();

    void deatach();

    void setEnableSLIDataReport(boolean z);

    void setFirstFrameEventListener(Runnable runnable);

    void setStuckEventListener(SLIReportInterface sLIReportInterface);
}
