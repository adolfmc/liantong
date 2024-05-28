package com.baidu.p120ar.async;

import android.os.Looper;
import android.text.TextUtils;
import com.baidu.p120ar.bus.ARBus;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.async.AsyncTaskManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AsyncTaskManager {
    private ARBus mARBus;
    private ARThreadPool mThreadPool;

    public static AsyncTaskManager getInstance() {
        return Builder.sInstance;
    }

    private AsyncTaskManager() {
        this.mARBus = new ARBus();
        this.mThreadPool = new ARThreadPool();
    }

    public void setControllerLooper(Looper looper) {
        this.mARBus.reSetControllerLooper(looper);
    }

    public void registerCallBack(Object obj) {
        this.mARBus.register(obj);
    }

    public void unRegisterCallBack(Object obj) {
        this.mARBus.unRegister(obj);
    }

    public void postEvent(Object obj) {
        this.mARBus.post(obj);
    }

    public boolean postTask(ARTask aRTask) {
        this.mThreadPool.execute(aRTask);
        return true;
    }

    public boolean isTaskRunning(String str) {
        return this.mThreadPool.isTaskRunning(str);
    }

    public int removeTasks(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.mThreadPool.removeTasks(str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.async.AsyncTaskManager$Builder */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class Builder {
        private static AsyncTaskManager sInstance = new AsyncTaskManager();

        private Builder() {
        }
    }
}
