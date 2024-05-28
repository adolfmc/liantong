package com.baidu.platform.comjni.engine;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MessageProxy {

    /* renamed from: a */
    private static final SparseArray<List<Handler>> f8075a = new SparseArray<>();

    public static void destroy() {
        int size = f8075a.size();
        for (int i = 0; i < size; i++) {
            SparseArray<List<Handler>> sparseArray = f8075a;
            List<Handler> list = sparseArray.get(sparseArray.keyAt(i));
            if (list != null) {
                list.clear();
            }
        }
        f8075a.clear();
    }

    public static void dispatchMessage(int i, int i2, int i3, long j) {
        if (i != 2000) {
        }
        synchronized (f8075a) {
            List<Handler> list = f8075a.get(i);
            if (list != null && !list.isEmpty()) {
                for (Handler handler : list) {
                    Message obtain = Message.obtain(handler, i, i2, i3, Long.valueOf(j));
                    if (i != 41 && (i != 39 || (i2 != 0 && i2 != 1))) {
                        obtain.sendToTarget();
                    }
                    handler.handleMessage(obtain);
                }
            }
        }
    }

    public static void registerMessageHandler(int i, Handler handler) {
        if (handler == null) {
            return;
        }
        synchronized (f8075a) {
            List<Handler> list = f8075a.get(i);
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(handler);
                f8075a.put(i, arrayList);
            } else if (!list.contains(handler)) {
                list.add(handler);
            }
        }
    }

    public static void unRegisterMessageHandler(int i, Handler handler) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            synchronized (f8075a) {
                List<Handler> list = f8075a.get(i);
                if (list != null) {
                    list.remove(handler);
                }
            }
        }
    }
}
