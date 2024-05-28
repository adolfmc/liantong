package com.baidu.minivideo.arface.utils;

import com.baidu.minivideo.arface.utils.ITask;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BaseTask implements ITask {
    private ITask.Callback mCallback;
    private int mState = 0;

    @Override // com.baidu.minivideo.arface.utils.ITask
    public void start(ITask.Callback callback) {
        int i = this.mState;
        if (i == 0 || 3 == i || 2 == i) {
            setState(1);
            setCallback(callback);
            run();
            return;
        }
        setCallback(callback);
    }

    @Override // com.baidu.minivideo.arface.utils.ITask
    public void cancel() {
        setState(0);
    }

    @Override // com.baidu.minivideo.arface.utils.ITask
    public boolean isRunning() {
        return 1 == this.mState;
    }

    @Override // com.baidu.minivideo.arface.utils.ITask
    public boolean isSuccess() {
        return 2 == this.mState;
    }

    public boolean isFailed() {
        return 3 == this.mState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setState(int i) {
        if (this.mState == i) {
            return;
        }
        this.mState = i;
        ITask.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onResult(this.mState, this);
        }
    }

    protected int getState() {
        return this.mState;
    }

    public void setCallback(ITask.Callback callback) {
        this.mCallback = callback;
        ITask.Callback callback2 = this.mCallback;
        if (callback2 != null) {
            callback2.onResult(getState(), this);
        }
    }
}
