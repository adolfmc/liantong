package com.baidu.cloud.media.download;

import java.util.Observable;
import java.util.Observer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class DownloadObserver implements Observer {
    public abstract void update(DownloadableVideoItem downloadableVideoItem);

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        if (observable instanceof DownloadableVideoItem) {
            update((DownloadableVideoItem) observable);
        }
    }
}
