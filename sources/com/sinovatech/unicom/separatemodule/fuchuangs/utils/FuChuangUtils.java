package com.sinovatech.unicom.separatemodule.fuchuangs.utils;

import android.app.Activity;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.animation.BounceInterpolator;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.blankj.utilcode.util.PermissionUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;
import com.yhao.floatwindow.IFloatWindowImpl;
import com.yhao.floatwindow.PermissionListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FuChuangUtils {
    public static void createWindow(final AppCompatActivity appCompatActivity, String str) {
        try {
            final FloatWindowWebVIew floatWindowWebVIew = new FloatWindowWebVIew(appCompatActivity);
            if (floatWindowWebVIew instanceof Object) {
                NBSWebLoadInstrument.loadUrl(floatWindowWebVIew, str);
            } else {
                floatWindowWebVIew.loadUrl(str);
            }
            try {
                FloatWindow.destroy();
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
            FloatWindow.with(App.getInstance()).setView(floatWindowWebVIew.getWv()).setWidth(1).setHeight(1).setX(0, 0.0f).setY(1, 0.0f).setMoveType(3, 0, 0).setMoveStyle(1L, new BounceInterpolator()).setViewStateListener(null).setPermissionListener(new PermissionListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.utils.FuChuangUtils.1
                @Override // com.yhao.floatwindow.PermissionListener
                public void onSuccess() {
                }

                @Override // com.yhao.floatwindow.PermissionListener
                public void onFail() {
                    IFloatWindowImpl.isShow = false;
                    try {
                        FloatWindow.destroy();
                    } catch (Exception unused) {
                    }
                }
            }).setDesktopShow(true).build();
            FloatWindow.get().setDrag(false);
            FloatWindow.get().hide();
            floatWindowWebVIew.setListener(new FloatWindowWebVIew.WebLoadListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.utils.FuChuangUtils.2
                @Override // com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew.WebLoadListener
                public void onSuccess() {
                    try {
                        final IFloatWindow iFloatWindow = FloatWindow.get();
                        if (iFloatWindow != null) {
                            if (!iFloatWindow.isShowing()) {
                                IFloatWindowImpl.once = true;
                            }
                            iFloatWindow.setDrag(false);
                            iFloatWindow.show();
                            iFloatWindow.updateSize(UIUtils.getScreenWidth((Activity) AppCompatActivity.this), UIUtils.getFullScreenHeight(AppCompatActivity.this));
                            FloatWindow.get().updateX(0);
                            FloatWindow.get().updateY(0);
                            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.utils.FuChuangUtils.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    iFloatWindow.getView().invalidate();
                                }
                            }, 100L);
                        }
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew.WebLoadListener
                public void onFail() {
                    try {
                        floatWindowWebVIew.destory();
                        FloatWindow.destroy();
                    } catch (Exception e2) {
                        MsLogUtil.m7977e("", e2.getMessage());
                    }
                }
            });
        } catch (Exception e2) {
            MsLogUtil.m7978e(e2.getMessage());
        }
    }

    public static void checkPer(final AppCompatActivity appCompatActivity, final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (PermissionUtils.isGrantedDrawOverlays()) {
            checkAudio(appCompatActivity, str);
            return;
        }
        try {
            PermissionUtils.requestDrawOverlays(new PermissionUtils.SimpleCallback() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.utils.FuChuangUtils.3
                @Override // com.blankj.utilcode.util.PermissionUtils.SimpleCallback
                public void onDenied() {
                }

                @Override // com.blankj.utilcode.util.PermissionUtils.SimpleCallback
                public void onGranted() {
                    FuChuangUtils.checkAudio(AppCompatActivity.this, str);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkAudio(final AppCompatActivity appCompatActivity, final String str) {
        String[] strArr = {"android.permission.RECORD_AUDIO"};
        PermissionDialog.show("为了给您带来更好的服务，需要获取您音频权限，用于刷脸验证、软电话、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        try {
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build(strArr), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.utils.FuChuangUtils.4
                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onAllPermissionOk(Permission[] permissionArr) {
                    try {
                        PermissionDialog.dimissDialog();
                        FuChuangUtils.createWindow(AppCompatActivity.this, str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIUtils.toastLong("需要开启音频权限");
                    }
                }

                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onPermissionDenied(Permission[] permissionArr) {
                    try {
                        PermissionDialog.dimissDialog();
                        if (permissionArr.length <= 0 || !TextUtils.equals("android.permission.RECORD_AUDIO", permissionArr[0].permissionName)) {
                            return;
                        }
                        UIUtils.toast("需要开启音频权限");
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                        UIUtils.toast("需要开启音频权限");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
