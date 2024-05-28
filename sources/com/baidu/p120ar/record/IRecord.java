package com.baidu.p120ar.record;

import com.baidu.p120ar.bean.Watermark;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.record.IRecord */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IRecord {
    void pauseRecord();

    void resumeRecord();

    void setRecordWatermark(Watermark watermark);

    void startRecord(String str, long j, RecordCallback recordCallback);

    void stopRecord();
}
