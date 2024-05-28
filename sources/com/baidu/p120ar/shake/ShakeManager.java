package com.baidu.p120ar.shake;

import android.content.Context;
import com.baidu.p120ar.shake.ShakeListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.shake.ShakeManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShakeManager {
    private static ShakeManager mInstance;
    private boolean mIsListening = false;
    private ShakeListener mShakeListener;

    private ShakeManager(Context context) {
        this.mShakeListener = new ShakeListener(context);
    }

    public static synchronized ShakeManager getInstance(Context context) {
        ShakeManager shakeManager;
        synchronized (ShakeManager.class) {
            if (mInstance == null) {
                mInstance = new ShakeManager(context);
            }
            shakeManager = mInstance;
        }
        return shakeManager;
    }

    public void start(ShakeListener.OnShakeListener onShakeListener) {
        ShakeListener shakeListener = this.mShakeListener;
        if (shakeListener != null) {
            shakeListener.setOnShakeListener(onShakeListener);
            if (this.mIsListening) {
                return;
            }
            try {
                this.mShakeListener.start();
                this.mIsListening = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        ShakeListener shakeListener = this.mShakeListener;
        if (shakeListener != null) {
            shakeListener.stop();
            this.mIsListening = false;
        }
    }

    public void setShakeEnable(boolean z) {
        ShakeListener shakeListener = this.mShakeListener;
        if (shakeListener != null) {
            shakeListener.setShakeEnable(z);
        }
    }

    public void destroy() {
        try {
            stop();
        } catch (Throwable unused) {
        }
        if (this.mShakeListener != null) {
            this.mShakeListener = null;
        }
        if (mInstance != null) {
            mInstance = null;
        }
        this.mIsListening = false;
    }
}
