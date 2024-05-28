package com.android.uaf.asmcore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.gmrz.android.client.utils.ActivityStarter;
import com.gmrz.android.client.utils.Logger;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SelectFromDialogActivity extends Activity {
    private static final String TAG = "SelectFromDialogActivity";
    AlertDialog mAlert = null;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logger.m15886v(TAG, "onCreate");
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new IllegalStateException("Unexpected empty bundle.");
        }
        ArrayList<String> stringArrayList = extras.getStringArrayList("DIALOGLIST");
        String str = TAG;
        Logger.m15886v(str, "items=" + stringArrayList);
        if (stringArrayList == null || stringArrayList.size() < 2) {
            throw new IllegalStateException("DIALOGLIST is not set");
        }
        String string = extras.getString("DIALOGTITLEID");
        String str2 = TAG;
        Logger.m15886v(str2, "title=" + string);
        if (string == null || "".equals(string)) {
            throw new IllegalStateException("DIALOGTITLEID is not set");
        }
        final CharSequence[] charSequenceArr = (CharSequence[]) stringArrayList.toArray(new CharSequence[stringArrayList.size()]);
        AlertDialog.Builder onKeyListener = new AlertDialog.Builder(this).setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.android.uaf.asmcore.SelectFromDialogActivity.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && keyEvent.getAction() == 1 && !keyEvent.isCanceled()) {
                    SelectFromDialogActivity.this.onBackPressed();
                    return true;
                }
                return false;
            }
        });
        String str3 = TAG;
        Logger.m15886v(str3, "AlertDialog.Builder=" + onKeyListener);
        this.mAlert = onKeyListener.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.android.uaf.asmcore.SelectFromDialogActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Tracker.onClick(dialogInterface, i);
                String str4 = SelectFromDialogActivity.TAG;
                Logger.m15889i(str4, "selectedItem = " + ((Object) charSequenceArr[i]));
                ActivityStarter.setResult(SelectFromDialogActivity.this.getIntent(), charSequenceArr[i]);
                SelectFromDialogActivity.this.finish();
            }
        }).create();
        String str4 = TAG;
        Logger.m15886v(str4, "mAlert=" + this.mAlert);
        this.mAlert.setTitle(string);
        this.mAlert.setCancelable(false);
        this.mAlert.setCanceledOnTouchOutside(false);
        this.mAlert.show();
        setActivity();
    }

    @Override // android.app.Activity
    protected void onPause() {
        Logger.m15889i(TAG, "onPause called");
        super.onPause();
        if (isFinishing()) {
            return;
        }
        if (this.mAlert.isShowing()) {
            this.mAlert.cancel();
        }
        ActivityStarter.setResult(getIntent(), null);
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Logger.m15889i(TAG, "onConfigurationChanged called");
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        Logger.m15889i(TAG, "onBackPressed called");
        ActivityStarter.setResult(getIntent(), null);
        finish();
    }

    private void setActivity() {
        if (ActivityStarter.setActivity(this, getIntent())) {
            return;
        }
        if (this.mAlert.isShowing()) {
            this.mAlert.cancel();
        }
        finish();
    }
}
