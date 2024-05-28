package com.bytedance.applog.picker;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.bytedance.applog.C3672q0;
import com.bytedance.applog.C3701u0;
import com.bytedance.applog.C3704u2;
import com.bytedance.applog.C3727x0;
import com.bytedance.applog.IPicker;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.View$OnClickListenerC3537a1;
import com.bytedance.applog.View$OnClickListenerC3552b1;
import com.bytedance.applog.View$OnClickListenerC3648o0;
import com.bytedance.applog.View$OnClickListenerC3717w0;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Picker implements IPicker {

    /* renamed from: a */
    public final Application f8756a;

    /* renamed from: b */
    public final C3701u0 f8757b;

    /* renamed from: c */
    public final View$OnClickListenerC3648o0 f8758c;

    /* renamed from: d */
    public View f8759d;

    /* renamed from: e */
    public final C3727x0 f8760e;

    /* renamed from: f */
    public long f8761f;

    /* renamed from: g */
    public final View$OnClickListenerC3552b1 f8762g;

    /* renamed from: h */
    public WindowManager f8763h;

    /* renamed from: i */
    public View$OnClickListenerC3537a1 f8764i;

    /* renamed from: j */
    public String f8765j;

    public Picker(Application application, InitConfig initConfig) {
        this.f8756a = application;
        this.f8757b = new C3701u0(this.f8756a, this);
        this.f8757b.setFocusableInTouchMode(true);
        this.f8758c = new View$OnClickListenerC3648o0(this.f8756a, this, initConfig);
        this.f8758c.setFocusableInTouchMode(true);
        this.f8762g = new View$OnClickListenerC3552b1(this.f8756a, this);
        this.f8762g.setFocusableInTouchMode(true);
        this.f8760e = new View$OnClickListenerC3717w0(this.f8756a, this, this.f8757b);
        this.f8760e.setFocusable(false);
        this.f8764i = new View$OnClickListenerC3537a1(this.f8756a, this, initConfig);
        this.f8764i.setFocusableInTouchMode(true);
        this.f8763h = (WindowManager) this.f8756a.getSystemService("window");
    }

    /* renamed from: a */
    public String m17153a() {
        return this.f8756a.getSharedPreferences("sp_app_log_picker", 0).getString("account", "");
    }

    /* renamed from: a */
    public final void m17152a(View view) {
        if (view != null) {
            try {
                this.f8763h.removeView(view);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public void m17151a(C3672q0 c3672q0) {
        if (TextUtils.isEmpty(this.f8765j)) {
            m17143f();
            return;
        }
        m17146c();
        View$OnClickListenerC3648o0 view$OnClickListenerC3648o0 = this.f8758c;
        view$OnClickListenerC3648o0.f8650s = c3672q0;
        view$OnClickListenerC3648o0.f8655x.setChecked(false);
        view$OnClickListenerC3648o0.f8657z.setChecked(true);
        m17150a(this.f8758c, -1, false, true);
    }

    /* renamed from: a */
    public void m17149a(String str, String str2) {
        this.f8756a.getSharedPreferences("sp_app_log_picker", 0).edit().putString("account", str).putString("token", str2).apply();
    }

    /* renamed from: a */
    public void m17148a(boolean z) {
        if (z) {
            m17150a(this.f8757b, -1, true, true);
        } else {
            m17152a(this.f8757b);
        }
    }

    /* renamed from: b */
    public String m17147b() {
        return this.f8756a.getSharedPreferences("sp_app_log_picker", 0).getString("token", "");
    }

    /* renamed from: c */
    public final void m17146c() {
        m17152a(this.f8760e);
        m17152a(this.f8757b);
        m17152a(this.f8762g);
        m17152a(this.f8758c);
        m17152a(this.f8764i);
        this.f8759d = null;
    }

    /* renamed from: d */
    public boolean m17145d() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f8761f > 1000) {
            this.f8761f = currentTimeMillis;
            View view = this.f8759d;
            if (view == this.f8760e) {
                return false;
            }
            if (view == this.f8758c || view == this.f8762g || view == this.f8764i) {
                m17146c();
                m17150a(this.f8760e, -2, false, false);
                return true;
            }
            return false;
        }
        return false;
    }

    /* renamed from: e */
    public void m17144e() {
        m17146c();
        m17150a(this.f8764i, -1, true, true);
    }

    /* renamed from: f */
    public void m17143f() {
        m17146c();
        m17150a(this.f8762g, -1, false, true);
    }

    @Override // com.bytedance.applog.IPicker
    public String getMarqueeCookie() {
        return this.f8765j;
    }

    @Override // com.bytedance.applog.IPicker
    public void setMarqueeCookie(String str) {
        this.f8765j = str;
    }

    @Override // com.bytedance.applog.IPicker
    public void show(boolean z) {
        if (z) {
            m17146c();
            m17150a(this.f8760e, -2, false, false);
            return;
        }
        m17146c();
    }

    /* renamed from: a */
    public final void m17150a(C3727x0 c3727x0, int i, boolean z, boolean z2) {
        try {
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) c3727x0.getTag();
            if (layoutParams == null) {
                layoutParams = new WindowManager.LayoutParams();
                layoutParams.type = Build.VERSION.SDK_INT >= 26 ? 2038 : 2003;
                layoutParams.format = 1;
                layoutParams.width = i;
                layoutParams.height = i;
                layoutParams.gravity = 83;
                layoutParams.flags = 544;
                if (z) {
                    layoutParams.flags |= 256;
                }
                if (!z2) {
                    layoutParams.flags |= 8;
                }
                if (Build.VERSION.SDK_INT >= 28) {
                    layoutParams.layoutInDisplayCutoutMode = 1;
                }
                c3727x0.setTag(layoutParams);
            }
            c3727x0.mo17042b();
            this.f8763h.addView(c3727x0, layoutParams);
            this.f8759d = c3727x0;
        } catch (Exception e) {
            e.printStackTrace();
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
                    intent.setFlags(268435456);
                    intent.setData(Uri.parse("package:" + this.f8756a.getPackageName()));
                    this.f8756a.startActivity(intent);
                } catch (Throwable th) {
                    C3704u2.m17108a("", th);
                }
            }
            Toast.makeText(this.f8756a, "请开启弹窗权限，才能展示圈选入口！", 1).show();
        }
    }
}
