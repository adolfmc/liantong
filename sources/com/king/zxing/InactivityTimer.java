package com.king.zxing;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class InactivityTimer {
    private static final long INACTIVITY_DELAY_MS = 300000;
    private static final String TAG = "InactivityTimer";
    private final Activity activity;
    private AsyncTask<Object, Object, Object> inactivityTask;
    private final BroadcastReceiver powerStatusReceiver = new PowerStatusReceiver(this);
    private boolean registered = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InactivityTimer(Activity activity) {
        this.activity = activity;
        onActivity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivity() {
        cancel();
        this.inactivityTask = new InactivityAsyncTask(this.activity);
        try {
            this.inactivityTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        } catch (RejectedExecutionException unused) {
            Log.w(TAG, "Couldn't schedule inactivity task; ignoring");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPause() {
        cancel();
        if (this.registered) {
            this.activity.unregisterReceiver(this.powerStatusReceiver);
            this.registered = false;
            return;
        }
        Log.w(TAG, "PowerStatusReceiver was never registered?");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onResume() {
        if (this.registered) {
            Log.w(TAG, "PowerStatusReceiver was already registered?");
        } else {
            this.activity.registerReceiver(this.powerStatusReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.registered = true;
        }
        onActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancel() {
        AsyncTask<Object, Object, Object> asyncTask = this.inactivityTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.inactivityTask = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void shutdown() {
        cancel();
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class PowerStatusReceiver extends BroadcastReceiver {
        private WeakReference<InactivityTimer> weakReference;

        public PowerStatusReceiver(InactivityTimer inactivityTimer) {
            this.weakReference = new WeakReference<>(inactivityTimer);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            InactivityTimer inactivityTimer;
            if (!"android.intent.action.BATTERY_CHANGED".equals(intent.getAction()) || (inactivityTimer = this.weakReference.get()) == null) {
                return;
            }
            if (!(intent.getIntExtra("plugged", -1) <= 0)) {
                inactivityTimer.cancel();
            } else {
                inactivityTimer.onActivity();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class InactivityAsyncTask extends AsyncTask<Object, Object, Object> {
        private WeakReference<Activity> weakReference;

        public InactivityAsyncTask(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(300000L);
                Log.i(InactivityTimer.TAG, "Finishing activity due to inactivity");
                Activity activity = this.weakReference.get();
                if (activity != null) {
                    activity.finish();
                    return null;
                }
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }
}
