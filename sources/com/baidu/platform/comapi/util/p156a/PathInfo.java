package com.baidu.platform.comapi.util.p156a;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.baidu.p166vi.VIContext;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PathInfo {

    /* renamed from: a */
    private String f8055a;

    /* renamed from: b */
    private String f8056b;

    /* renamed from: c */
    private String f8057c;

    /* renamed from: d */
    private String f8058d;

    /* renamed from: e */
    private String f8059e;

    /* renamed from: f */
    private String f8060f;

    /* renamed from: g */
    private String f8061g;

    /* renamed from: a */
    public void m17693a(Context context) {
        this.f8055a = context.getFilesDir().getAbsolutePath();
        this.f8058d = context.getCacheDir().getAbsolutePath();
        this.f8059e = this.f8058d;
        this.f8056b = Environment.getExternalStorageDirectory().getPath();
        this.f8057c = Environment.getExternalStorageDirectory().getPath();
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            this.f8060f = externalFilesDir.getAbsolutePath();
        }
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            this.f8061g = externalCacheDir.getAbsolutePath();
        }
    }

    /* renamed from: a */
    public String m17694a() {
        if (TextUtils.isEmpty(this.f8055a)) {
            m17693a(VIContext.getContext());
        }
        return this.f8055a;
    }

    /* renamed from: b */
    public String m17691b() {
        if (TextUtils.isEmpty(this.f8056b)) {
            m17693a(VIContext.getContext());
        }
        return this.f8056b;
    }

    /* renamed from: c */
    public String m17690c() {
        if (TextUtils.isEmpty(this.f8057c)) {
            m17693a(VIContext.getContext());
        }
        return this.f8057c;
    }

    /* renamed from: d */
    public String m17689d() {
        if (TextUtils.isEmpty(this.f8058d)) {
            m17693a(VIContext.getContext());
        }
        return this.f8058d;
    }

    /* renamed from: e */
    public String m17688e() {
        if (TextUtils.isEmpty(this.f8060f)) {
            m17693a(VIContext.getContext());
        }
        return this.f8060f;
    }
}
