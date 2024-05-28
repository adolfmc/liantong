package com.crb.jscard;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class BaseActivity extends AppCompatActivity {
    /* renamed from: a */
    public int m16303a() {
        return C4182R.C4183color.white;
    }

    /* renamed from: b */
    public boolean m16302b() {
        return true;
    }

    /* renamed from: c */
    public boolean m16301c() {
        return false;
    }

    /* renamed from: d */
    public final void m16300d() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (m16301c()) {
                StatusBarUtil.m2020a(this);
            } else {
                StatusBarUtil.m2019a(this, m16303a());
            }
            if (m16302b()) {
                StatusBarUtil.m2018a((Activity) this, true, m16301c());
            }
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        m16300d();
    }
}
