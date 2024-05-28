package com.megvii.livenesslib.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.bytedance.applog.tracker.Tracker;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DialogUtil {
    private Activity activity;

    public DialogUtil(Activity activity) {
        this.activity = activity;
    }

    public void showDialog(String str) {
        new AlertDialog.Builder(this.activity).setTitle(str).setNegativeButton("确认", new DialogInterface.OnClickListener() { // from class: com.megvii.livenesslib.util.DialogUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Tracker.onClick(dialogInterface, i);
                DialogUtil.this.activity.finish();
            }
        }).setCancelable(false).create().show();
    }

    public void onDestory() {
        this.activity = null;
    }
}
