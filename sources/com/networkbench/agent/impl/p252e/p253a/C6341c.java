package com.networkbench.agent.impl.p252e.p253a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.bytedance.applog.tracker.Tracker;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6341c {

    /* renamed from: a */
    private AlertDialog f15963a;

    /* renamed from: b */
    private Context f15964b;

    public C6341c(Context context) {
        this.f15964b = context;
    }

    /* renamed from: a */
    public boolean m10338a(Activity activity) {
        AbstractC6348i m10339a = C6340b.m10339a(activity);
        Intent mo10322b = m10339a.mo10322b();
        if (m10339a.mo10324a()) {
            return true;
        }
        try {
            m10337a(activity, mo10322b);
            return false;
        } catch (Exception unused) {
            this.f15963a = null;
            return false;
        }
    }

    /* renamed from: a */
    private void m10337a(Activity activity, final Intent intent) {
        AlertDialog alertDialog = this.f15963a;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.f15963a = new AlertDialog.Builder(activity).setTitle("Tingyun SDK提示").setMessage("使用点选功能，您需要开启当前应用的悬浮窗权限").setPositiveButton(intent == null ? "自行设置" : "去设置", new DialogInterface.OnClickListener() { // from class: com.networkbench.agent.impl.e.a.c.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    Tracker.onClick(dialogInterface, i);
                    if (intent != null) {
                        C6341c.this.f15964b.startActivity(intent);
                    }
                    if (C6341c.this.f15963a != null) {
                        C6341c.this.f15963a.dismiss();
                    }
                    C6341c.this.f15963a = null;
                }
            }).setCancelable(false).create();
            this.f15963a.show();
        }
    }
}
