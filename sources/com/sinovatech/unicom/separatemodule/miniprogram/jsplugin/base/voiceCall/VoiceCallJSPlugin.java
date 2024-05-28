package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.voiceCall;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.voiceCall.broadcastreceiver.NetBroadcastReceiver;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.web.MsJSEvent;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.tydic.softphone.CallActivity;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/voiceCall")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VoiceCallJSPlugin extends BaseJSPlugin {
    private static final String TAG = "FacePlusPlusJSPlugin";
    private Handler handler;
    private NetBroadcastReceiver networkChangeReceiver;
    private String voiceCallObjectString;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        this.handler = new Handler();
        call();
        this.webFragment.addLifeListener("/MsJSPlugin/voiceCall", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.voiceCall.VoiceCallJSPlugin.1
            @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
            public void onDestory() {
                if (VoiceCallJSPlugin.this.networkChangeReceiver != null) {
                    VoiceCallJSPlugin.this.activityContext.unregisterReceiver(VoiceCallJSPlugin.this.networkChangeReceiver);
                }
            }
        });
    }

    public void call() {
        try {
            if (this.parameterJO != null) {
                this.voiceCallObjectString = this.parameterJO.optString("voiceCallObjectString", "");
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.tydic.softphone.callid");
            this.networkChangeReceiver = new NetBroadcastReceiver(this);
            this.activityContext.registerReceiver(this.networkChangeReceiver, intentFilter);
            PermissionDialog.show("软电话为了给您带来更好的服务，需要获取您音频权限，用于刷脸验证、软电话、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
            SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.RECORD_AUDIO"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.voiceCall.VoiceCallJSPlugin.2
                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onAllPermissionOk(Permission[] permissionArr) {
                    String str;
                    String str2;
                    String str3;
                    String str4 = "";
                    str = "";
                    try {
                        if (VoiceCallJSPlugin.this.f18589wv != null) {
                            str = TextUtils.isEmpty(VoiceCallJSPlugin.this.f18589wv.getUrl()) ? "" : VoiceCallJSPlugin.this.f18589wv.getUrl();
                            if (!TextUtils.isEmpty(VoiceCallJSPlugin.this.f18589wv.getTitle())) {
                                str4 = VoiceCallJSPlugin.this.f18589wv.getTitle();
                            }
                        }
                        str2 = str4;
                        str3 = str;
                    } catch (Exception e) {
                        MsLogUtil.m7978e("SDK调用软电话获取参数异常：" + e.getMessage());
                        str2 = "";
                        str3 = str;
                    }
                    TYCJBoxManager.getInstance().collectClickSdk(VoiceCallJSPlugin.this.activityContext, "S2ndpage1214", str2, "软电话", str3, "com.tydic.softphone", "1");
                    try {
                        PermissionDialog.dimissDialog();
                        Intent intent = new Intent();
                        Log.d(VoiceCallJSPlugin.TAG, VoiceCallJSPlugin.this.voiceCallObjectString);
                        intent.putExtra("voiceCall", VoiceCallJSPlugin.this.voiceCallObjectString);
                        intent.setClass(VoiceCallJSPlugin.this.activityContext, CallActivity.class);
                        VoiceCallJSPlugin.this.activityContext.startActivity(intent);
                        VoiceCallJSPlugin.this.sendMessage("0", "", "", "调用拨打电话成功", "");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        UIUtils.toastLong("软电话错误");
                        VoiceCallJSPlugin voiceCallJSPlugin = VoiceCallJSPlugin.this;
                        voiceCallJSPlugin.sendMessage("10", "", "", "软电话错误异常:" + e2.getMessage(), "");
                    }
                }

                @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                public void onPermissionDenied(Permission[] permissionArr) {
                    try {
                        PermissionDialog.dimissDialog();
                        if (permissionArr.length > 0 && TextUtils.equals("android.permission.RECORD_AUDIO", permissionArr[0].permissionName)) {
                            UIUtils.toast("需要开启录音权限");
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                        UIUtils.toast("需要开启录音权限");
                    }
                    VoiceCallJSPlugin.this.sendMessage("11", "", "", "需要开启录音权限！", "");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("10", "", "", "软电话异常:" + e.getMessage(), "");
            UIUtils.toastLong("软电话错误");
        }
    }

    public void postJSVoiceCall(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.webFragment.postEventToJS(MsJSEvent.onVoiceCall, jSONObject);
        }
    }

    public void stopBoardCast() {
        try {
            if (this.networkChangeReceiver != null) {
                this.activityContext.unregisterReceiver(this.networkChangeReceiver);
            }
        } catch (Exception unused) {
        }
    }

    public void sendMessage(String str, String str2, String str3, String str4, String str5) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            jSONObject.put("callid", str3);
            jSONObject.put("code", String.valueOf(str2));
            jSONObject.put("msg", str4);
            jSONObject.put("durationTalk", str5);
            postJSVoiceCall(jSONObject);
        } catch (Exception unused) {
        }
    }
}
