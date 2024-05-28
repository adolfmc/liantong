package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BaseActivity extends Activity {
    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (Build.VERSION.SDK_INT == 26 && m8048h()) {
            m8047i();
        }
        super.onCreate(bundle);
    }

    /* renamed from: h */
    private boolean m8048h() {
        Method method;
        boolean booleanValue;
        boolean z = false;
        try {
            TypedArray obtainStyledAttributes = obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null));
            method = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            method.setAccessible(true);
            booleanValue = ((Boolean) method.invoke(null, obtainStyledAttributes)).booleanValue();
        } catch (Exception e) {
            e = e;
        }
        try {
            method.setAccessible(false);
            return booleanValue;
        } catch (Exception e2) {
            e = e2;
            z = booleanValue;
            e.printStackTrace();
            return z;
        }
    }

    /* renamed from: i */
    private boolean m8047i() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mActivityInfo");
            declaredField.setAccessible(true);
            ((ActivityInfo) declaredField.get(this)).screenOrientation = -1;
            declaredField.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        if (Build.VERSION.SDK_INT == 26 && m8048h()) {
            return;
        }
        super.setRequestedOrientation(i);
    }
}
