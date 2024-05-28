package com.yhao.floatwindow;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class FloatPhone extends FloatView {
    private final Context mContext;
    private PermissionListener mPermissionListener;
    private View mView;
    private final WindowManager mWindowManager;

    /* renamed from: mX */
    private int f23822mX;

    /* renamed from: mY */
    private int f23823mY;
    private boolean isRemove = false;
    private final WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();

    /* JADX INFO: Access modifiers changed from: package-private */
    public FloatPhone(Context context, PermissionListener permissionListener) {
        this.mContext = context;
        this.mPermissionListener = permissionListener;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.format = 1;
        layoutParams.flags = 552;
        layoutParams.windowAnimations = 0;
    }

    @Override // com.yhao.floatwindow.FloatView
    public void setSize(int i, int i2) {
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.width = i;
        layoutParams.height = i2;
    }

    @Override // com.yhao.floatwindow.FloatView
    public void setView(View view) {
        this.mView = view;
    }

    @Override // com.yhao.floatwindow.FloatView
    public void setGravity(int i, int i2, int i3) {
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.gravity = i;
        this.f23822mX = i2;
        layoutParams.x = i2;
        this.f23823mY = i3;
        layoutParams.y = i3;
    }

    @Override // com.yhao.floatwindow.FloatView
    public void init() {
        if (Build.VERSION.SDK_INT >= 25) {
            req();
        } else if (Miui.rom()) {
            if (Build.VERSION.SDK_INT >= 23) {
                req();
                return;
            }
            this.mLayoutParams.type = 2002;
            Miui.req(this.mContext, new PermissionListener() { // from class: com.yhao.floatwindow.FloatPhone.1
                @Override // com.yhao.floatwindow.PermissionListener
                public void onSuccess() {
                    FloatPhone.this.mWindowManager.addView(FloatPhone.this.mView, FloatPhone.this.mLayoutParams);
                    if (FloatPhone.this.mPermissionListener != null) {
                        FloatPhone.this.mPermissionListener.onSuccess();
                    }
                }

                @Override // com.yhao.floatwindow.PermissionListener
                public void onFail() {
                    if (FloatPhone.this.mPermissionListener != null) {
                        FloatPhone.this.mPermissionListener.onFail();
                    }
                }
            });
        } else {
            try {
                this.mLayoutParams.type = 2005;
                this.mWindowManager.addView(this.mView, this.mLayoutParams);
            } catch (Exception unused) {
                this.mWindowManager.removeView(this.mView);
                LogUtil.m2253e("TYPE_TOAST 失败");
                req();
            }
        }
    }

    private void req() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mLayoutParams.type = 2038;
        } else {
            this.mLayoutParams.type = 2002;
        }
        FloatActivity.request(this.mContext, new PermissionListener() { // from class: com.yhao.floatwindow.FloatPhone.2
            @Override // com.yhao.floatwindow.PermissionListener
            public void onSuccess() {
                FloatPhone.this.mWindowManager.addView(FloatPhone.this.mView, FloatPhone.this.mLayoutParams);
                try {
                    FloatWindow.get().getView().setVisibility(0);
                } catch (Exception e) {
                    Log.e("floatwindow", "onSuccess: ", e);
                }
                if (FloatPhone.this.mPermissionListener != null) {
                    FloatPhone.this.mPermissionListener.onSuccess();
                }
            }

            @Override // com.yhao.floatwindow.PermissionListener
            public void onFail() {
                if (FloatPhone.this.mPermissionListener != null) {
                    FloatPhone.this.mPermissionListener.onFail();
                }
            }
        });
    }

    @Override // com.yhao.floatwindow.FloatView
    public void dismiss() {
        this.isRemove = true;
        this.mWindowManager.removeView(this.mView);
    }

    @Override // com.yhao.floatwindow.FloatView
    public void updateXY(int i, int i2) {
        if (this.isRemove) {
            return;
        }
        Log.d("haha", "updateXY: x = " + i);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        this.f23822mX = i;
        layoutParams.x = i;
        this.f23823mY = i2;
        layoutParams.y = i2;
        this.mWindowManager.updateViewLayout(this.mView, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.yhao.floatwindow.FloatView
    public void updateX(int i) {
        if (this.isRemove) {
            return;
        }
        if (!FloatWindow.get().isDrag()) {
            i = 0;
        }
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        this.f23822mX = i;
        layoutParams.x = i;
        this.mWindowManager.updateViewLayout(this.mView, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.yhao.floatwindow.FloatView
    public void updateY(int i) {
        if (this.isRemove) {
            return;
        }
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        this.f23823mY = i;
        layoutParams.y = i;
        this.mWindowManager.updateViewLayout(this.mView, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.yhao.floatwindow.FloatView
    public int getX() {
        return this.f23822mX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.yhao.floatwindow.FloatView
    public int getY() {
        return this.f23823mY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.yhao.floatwindow.FloatView
    public void updateSize(int i, int i2) {
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.width = i;
        layoutParams.height = i2;
        this.mWindowManager.updateViewLayout(this.mView, layoutParams);
    }
}
