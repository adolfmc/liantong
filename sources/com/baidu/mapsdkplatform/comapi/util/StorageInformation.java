package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.util.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class StorageInformation {

    /* renamed from: a */
    private final boolean f7428a;

    /* renamed from: b */
    private final String f7429b;

    /* renamed from: c */
    private final String f7430c;

    /* renamed from: d */
    private final String f7431d;

    /* renamed from: e */
    private final String f7432e;

    /* renamed from: f */
    private final String f7433f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StorageInformation(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            File externalFilesDir = context.getExternalFilesDir(null);
            if (externalFilesDir != null && externalFilesDir.exists()) {
                this.f7429b = externalFilesDir.getPath();
            } else {
                this.f7429b = context.getFilesDir().getPath();
            }
        } else {
            this.f7429b = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        this.f7428a = false;
        this.f7430c = this.f7429b + File.separator + "BaiduMapSDKNew";
        this.f7431d = context.getCacheDir().getAbsolutePath();
        this.f7432e = "";
        this.f7433f = "";
    }

    /* renamed from: a */
    public String m18153a() {
        return this.f7429b;
    }

    /* renamed from: b */
    public String m18152b() {
        return this.f7429b + File.separator + "BaiduMapSDKNew";
    }

    /* renamed from: c */
    public String m18151c() {
        return this.f7431d;
    }

    /* renamed from: d */
    public String m18150d() {
        return this.f7432e;
    }

    public boolean equals(Object obj) {
        if (obj == null || !StorageInformation.class.isInstance(obj)) {
            return false;
        }
        return this.f7429b.equals(((StorageInformation) obj).f7429b);
    }
}
