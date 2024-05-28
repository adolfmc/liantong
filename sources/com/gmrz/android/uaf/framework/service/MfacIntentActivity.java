package com.gmrz.android.uaf.framework.service;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.gmrz.android.client.utils.Logger;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MfacIntentActivity extends Activity {
    private static final String TAG = "MfacIntentActivity";

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logger.m15889i(TAG, "MfacIntentActivity.onCreate");
        new UafProcessorTask(this).execute(getIntent());
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class UafProcessorTask extends AsyncTask<Intent, Void, Intent> {
        private Activity mActivity;

        UafProcessorTask(Activity activity) {
            this.mActivity = null;
            this.mActivity = activity;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Intent doInBackground(Intent... intentArr) {
            if (intentArr[0] == null) {
                return null;
            }
            Logger.m15889i(MfacIntentActivity.TAG, "MfacIntentActivity: doInBackground");
            return new UafIntentProcessor().processIntent(intentArr[0], this.mActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Intent intent) {
            super.onPostExecute((UafProcessorTask) intent);
            Logger.m15889i(MfacIntentActivity.TAG, "MfacIntentActivity: onPostExecute");
            this.mActivity.setResult(intent == null ? 0 : -1, intent);
            this.mActivity.finish();
        }
    }
}
