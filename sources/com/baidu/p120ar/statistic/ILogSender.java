package com.baidu.p120ar.statistic;

import android.content.Context;
import com.baidu.p120ar.ihttp.HttpException;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.ILogSender */
/* loaded from: E:\10201592_dexfile_execute.dex */
interface ILogSender {
    void send(Context context, EventData eventData) throws IOException, HttpException;

    void send(Context context, List<EventData> list) throws IOException, HttpException;
}
