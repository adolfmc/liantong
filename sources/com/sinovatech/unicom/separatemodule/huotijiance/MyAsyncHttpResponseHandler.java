package com.sinovatech.unicom.separatemodule.huotijiance;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyAsyncHttpResponseHandler {
    protected static final int FAILURE_MESSAGE = 1;
    protected static final int FINISH_MESSAGE = 3;
    protected static final int START_MESSAGE = 2;
    protected static final int SUCCESS_MESSAGE = 0;
    private int delay;
    private Handler handler;

    public void onFailure(Throwable th, String str) {
    }

    public void onFinish() {
    }

    public void onStart() {
    }

    public void onSuccess(int i, String str) {
    }

    public void onSuccess(int i, String str, String str2) {
    }

    public MyAsyncHttpResponseHandler() {
        this.delay = 0;
        init();
    }

    public MyAsyncHttpResponseHandler(int i) {
        this.delay = 0;
        this.delay = i;
        init();
    }

    private void init() {
        if (Looper.myLooper() != null) {
            this.handler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    MyAsyncHttpResponseHandler.this.emulateHandleMessage(message);
                }
            };
        }
    }

    public int getDelay() {
        return this.delay;
    }

    private void sendMessage(Message message) {
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendMessage(message);
        } else {
            emulateHandleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendSuccessMessage(int i, String str) {
        sendMessage(obtainMessage(0, new Object[]{new Integer(i), str}));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendFailureMessage(Throwable th, String str) {
        sendMessage(obtainMessage(1, new Object[]{th, str}));
    }

    protected void sendFailureMessage(Throwable th, byte[] bArr) {
        sendMessage(obtainMessage(1, new Object[]{th, bArr}));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendStartMessage() {
        sendMessage(obtainMessage(2, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendFinishMessage() {
        sendMessage(obtainMessage(3, null));
    }

    private void handleSuccessMessage(int i, String str) {
        onSuccess(i, str);
    }

    private void handleFailureMessage(Throwable th, String str) {
        onFailure(th, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emulateHandleMessage(Message message) {
        switch (message.what) {
            case 0:
                Object[] objArr = (Object[]) message.obj;
                handleSuccessMessage(((Integer) objArr[0]).intValue(), (String) objArr[1]);
                return;
            case 1:
                Object[] objArr2 = (Object[]) message.obj;
                handleFailureMessage((Throwable) objArr2[0], (String) objArr2[1]);
                return;
            case 2:
                onStart();
                return;
            case 3:
                onFinish();
                return;
            default:
                return;
        }
    }

    private Message obtainMessage(int i, Object obj) {
        Handler handler = this.handler;
        if (handler != null) {
            return handler.obtainMessage(i, obj);
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        return obtain;
    }
}
