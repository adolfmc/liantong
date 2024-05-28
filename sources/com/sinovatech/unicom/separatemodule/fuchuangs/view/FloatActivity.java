package com.sinovatech.unicom.separatemodule.fuchuangs.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.yhao.floatwindow.IFloatWindowImpl;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.PermissionUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FloatActivity extends BaseActivity {
    private static PermissionListener mPermissionListener;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 23) {
            requestAlertWindowPermission();
        }
    }

    @RequiresApi(api = 23)
    private void requestAlertWindowPermission() {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 11);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 11) {
            if (PermissionUtil.hasPermissionOnActivityResult(this)) {
                mPermissionListener.onSuccess();
            } else {
                IFloatWindowImpl.isShow = false;
                mPermissionListener.onFail();
            }
        }
        finish();
    }

    public static synchronized void request(Context context, PermissionListener permissionListener) {
        synchronized (FloatActivity.class) {
            if (PermissionUtil.hasPermission(context)) {
                permissionListener.onSuccess();
                return;
            }
            mPermissionListener = permissionListener;
            Intent intent = new Intent(context, FloatActivity.class);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }
}
