package com.sinovatech.unicom.basic.p315ui.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import com.blankj.utilcode.util.CleanUtils;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.p284qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.sinovatech.unicom.basic.eventbus.FinishActivityEvent;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.CheckPermissionsUtil;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.utils.CheckDeviceUtils */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CheckDeviceUtils {
    public static void checkDeviceModel(final Activity activity) {
        try {
            String deviceModel = DeviceHelper.getDeviceModel();
            String str = null;
            if (App.getSharePreferenceUtil().getBoolean("hasShowYinsi")) {
                try {
                    str = Build.MODEL;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(str)) {
                    str = "unkown";
                } else {
                    App.getSharePreferenceUtil().putString("DeviceHelper-getDeviceModel", str);
                }
            }
            if (!TextUtils.isEmpty(deviceModel) && !TextUtils.isEmpty(str) && !deviceModel.equals(str)) {
                CustomDialogManager.show(activity, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.utils.CheckDeviceUtils.1
                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onBackKeyDown() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onCancel() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onClickCancel() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onShow() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onClickOk() {
                        CleanUtils.cleanAppUserData();
                        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.utils.CheckDeviceUtils.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                EventBusUtils.post(new FinishActivityEvent(0));
                                try {
                                    activity.finish();
                                } catch (Exception unused) {
                                }
                                Process.killProcess(Process.myPid());
                                System.exit(0);
                            }
                        }, 1000L);
                    }
                });
            } else {
                checkPermission(activity);
            }
        } catch (Exception unused) {
        }
    }

    private static void checkPermission(final Activity activity) {
        boolean checkPermissions = UIUtils.checkPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE");
        MsLogUtil.m7979d("申请权限", "首页是否拥有权限" + checkPermissions);
        if (checkPermissions || !CheckPermissionsUtil.checkPermission()) {
            return;
        }
        final String string = App.getSharePreferenceUtil().getString("home_permission_dialog_flag");
        CustomDialogManager.show(activity, "权限申请", CheckPermissionsUtil.getPermissionContent(), 3, true, "以后再说", TextUtils.isEmpty(string) ? "允许" : "去开启", true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.utils.CheckDeviceUtils.2
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                if (TextUtils.isEmpty(string)) {
                    StatisticsUploadUtils.upload(activity, "S2ndpage1076", "首页-权限弹窗", "允许", "", "按钮", "");
                    MsLogUtil.m7979d("申请权限", "请求权限主页");
                    try {
                        SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.basic.ui.utils.CheckDeviceUtils.2.1
                            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                            public void onAllPermissionOk(Permission[] permissionArr) {
                                MsLogUtil.m7979d("申请权限", "权限通过");
                                StatisticsUploadUtils.upload(activity, "S2ndpage1076", "首页-权限弹窗", "允许", "", "权限通过", "");
                            }

                            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                            public void onPermissionDenied(Permission[] permissionArr) {
                                StatisticsUploadUtils.upload(activity, "S2ndpage1076", "首页-权限弹窗", "允许", "", "拒绝权限", "");
                                MsLogUtil.m7979d("申请权限", "权限通过未通过 = " + permissionArr[0].permissionName);
                            }
                        });
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                StatisticsUploadUtils.upload(activity, "S2ndpage1076", "首页-权限弹窗", "去开启", "", "按钮", "");
                try {
                    SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() { // from class: com.sinovatech.unicom.basic.ui.utils.CheckDeviceUtils.2.2
                        @Override // com.p284qw.soul.permission.callbcak.GoAppDetailCallBack
                        public void onBackFromAppDetail(Intent intent) {
                            if (UIUtils.checkPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE")) {
                                StatisticsUploadUtils.upload(activity, "S2ndpage1076", "首页-权限弹窗", "去开启", "", "权限通过", "");
                            } else {
                                StatisticsUploadUtils.upload(activity, "S2ndpage1076", "首页-权限弹窗", "去开启", "", "拒绝权限", "");
                            }
                            App.getSharePreferenceUtil().putString("home_permission_dialog_flag", String.valueOf(System.currentTimeMillis()));
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                    App.getSharePreferenceUtil().putString("home_permission_dialog_flag", String.valueOf(System.currentTimeMillis()));
                }
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
                StatisticsUploadUtils.upload(activity, "S2ndpage1076", "首页-权限弹窗", "以后再说", "", "", "");
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
                StatisticsUploadUtils.upload(activity, "S2ndpage1076", "首页-权限弹窗", "返回键", "", "拒绝权限", "");
            }
        });
        App.getSharePreferenceUtil().putString("home_permission_dialog_flag", String.valueOf(System.currentTimeMillis()));
    }
}
