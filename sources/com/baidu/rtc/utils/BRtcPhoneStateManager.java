package com.baidu.rtc.utils;

import android.content.Context;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class BRtcPhoneStateManager {
    private Context mContext;
    private InnerPhoneStateListener stateListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public interface IPhoneStateChangeListener {
        void onPhoneStateChanged(int i);
    }

    public static boolean isPhoneUsing(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return (telephonyManager == null || telephonyManager.getCallState() == 0) ? false : true;
    }

    public void listenPhoneState(Context context, IPhoneStateChangeListener iPhoneStateChangeListener) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (this.stateListener == null) {
            this.stateListener = new InnerPhoneStateListener();
        }
        this.stateListener.setInnerListener(iPhoneStateChangeListener);
        if (telephonyManager != null) {
            telephonyManager.listen(this.stateListener, 32);
        }
    }

    public void release() {
        if (this.stateListener != null) {
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
            if (telephonyManager != null) {
                telephonyManager.listen(this.stateListener, 0);
            }
            this.stateListener.release();
        }
        this.stateListener = null;
        this.mContext = null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\567196_dexfile_execute.dex */
    static class InnerPhoneStateListener extends PhoneStateListener {
        private IPhoneStateChangeListener innerListener;

        public void setInnerListener(IPhoneStateChangeListener iPhoneStateChangeListener) {
            this.innerListener = iPhoneStateChangeListener;
        }

        public void release() {
            this.innerListener = null;
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            IPhoneStateChangeListener iPhoneStateChangeListener = this.innerListener;
            if (iPhoneStateChangeListener != null) {
                iPhoneStateChangeListener.onPhoneStateChanged(i);
            }
            super.onCallStateChanged(i, str);
        }
    }
}
