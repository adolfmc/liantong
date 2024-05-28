package com.huawei.hms.adapter.sysobs;

import android.content.Intent;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface SystemNotifier {
    void notifyNoticeObservers(int i);

    void notifyObservers(int i);

    void notifyObservers(Intent intent, String str);

    void registerObserver(SystemObserver systemObserver);

    void unRegisterObserver(SystemObserver systemObserver);
}
