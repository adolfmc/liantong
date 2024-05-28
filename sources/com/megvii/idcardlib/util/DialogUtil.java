package com.megvii.idcardlib.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.bytedance.applog.tracker.Tracker;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DialogUtil {
    private Activity activity;
    private AlertDialog alertDialog;

    public DialogUtil(Activity activity) {
        this.activity = activity;
    }

    public void showDialog(String str) {
        this.alertDialog = new AlertDialog.Builder(this.activity).setTitle(str).setNegativeButton("确认", new DialogInterface.OnClickListener() { // from class: com.megvii.idcardlib.util.DialogUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Tracker.onClick(dialogInterface, i);
                DialogUtil.this.activity.finish();
            }
        }).setCancelable(false).create();
        this.alertDialog.show();
    }

    public void onDestory() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.activity = null;
    }
}
