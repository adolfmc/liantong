package com.blankj.utilcode.util;

import android.app.Application;
import android.support.p083v4.content.FileProvider;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UtilsFileProvider extends FileProvider {
    @Override // android.support.p083v4.content.FileProvider, android.content.ContentProvider
    public boolean onCreate() {
        Utils.init((Application) getContext().getApplicationContext());
        return true;
    }
}
