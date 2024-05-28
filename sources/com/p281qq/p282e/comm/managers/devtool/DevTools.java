package com.p281qq.p282e.comm.managers.devtool;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: com.qq.e.comm.managers.devtool.DevTools */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DevTools {

    /* renamed from: a */
    private String f17924a;

    public String getDemoGameUrl() {
        String str = this.f17924a;
        this.f17924a = null;
        return str;
    }

    public void testDemoGame(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context.getPackageName().equals("com.qq.e.union.demo.union")) {
            this.f17924a = str;
        }
    }
}
