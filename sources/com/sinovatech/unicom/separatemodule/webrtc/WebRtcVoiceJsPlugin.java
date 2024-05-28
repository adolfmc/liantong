package com.sinovatech.unicom.separatemodule.webrtc;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.p284qw.soul.permission.callbcak.SpecialPermissionListener;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule;
import com.sinovatech.unicom.separatemodule.webrtc.WebRtcVoiceJsPlugin;
import io.reactivex.disposables.Disposable;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/webRtcVideoToAudio")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WebRtcVoiceJsPlugin extends BaseJSPlugin {
    private Disposable freeTips;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        char c;
        Log.d("RtcVoiceModule", "调用webRtcVideoToAudio能力");
        String optString = this.parameterJO.optString("action");
        Log.d("RtcVoiceModule", "========action=" + optString);
        switch (optString.hashCode()) {
            case -1560193972:
                if (optString.equals("monitorChangeAudio")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1359842056:
                if (optString.equals("miniSize")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -436985374:
                if (optString.equals("defaultSize")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -197242187:
                if (optString.equals("resumeStreaming")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 3045982:
                if (optString.equals("call")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3237136:
                if (optString.equals("init")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 265516938:
                if (optString.equals("onlyAudio")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1557370132:
                if (optString.equals("destory")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1563846412:
                if (optString.equals("pauseStreaming")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1836729410:
                if (optString.equals("callerWaiting")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2140124850:
                if (optString.equals("mediaInfo")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                getMediaInfo();
                return;
            case 1:
                if (this.f18589wv != null) {
                    TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", this.f18589wv.getTitle(), "RTC_SDK", this.f18589wv.getUrl(), "com.baidu.rtc", "1");
                } else {
                    TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "", "RTC_SDK", "", "com.baidu.rtc", "1");
                }
                if (RtcHelper.getInstance().getRtcStatus()) {
                    Log.d("RtcVoiceModule", "========窗口还未销毁，重复拉起");
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("code", 60);
                        jSONObject.put("msg", "当前控制器已创建");
                        callbackFail(jSONObject);
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.RECORD_AUDIO"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.WebRtcVoiceJsPlugin.1
                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                    public void onAllPermissionOk(Permission[] permissionArr) {
                        PermissionDialog.dimissDialog();
                        SoulPermission.getInstance().checkAndRequestPermission(Special.SYSTEM_ALERT, new C96901());
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* renamed from: com.sinovatech.unicom.separatemodule.webrtc.WebRtcVoiceJsPlugin$1$1 */
                    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
                    public class C96901 implements SpecialPermissionListener {
                        C96901() {
                        }

                        @Override // com.p284qw.soul.permission.callbcak.SpecialPermissionListener
                        public void onGranted(Special special) {
                            StringBuilder sb = new StringBuilder();
                            UIUtils.setStatusBarMode(WebRtcVoiceJsPlugin.this.activityContext, true, true);
                            String optString = WebRtcVoiceJsPlugin.this.parameterJO.optJSONObject("params").optString("beautyLevel");
                            final String optString2 = WebRtcVoiceJsPlugin.this.parameterJO.optJSONObject("params").optString("tips");
                            sb.append(TextUtils.isEmpty(WebRtcVoiceJsPlugin.this.webFragment.currentH5Id) ? "" : WebRtcVoiceJsPlugin.this.webFragment.currentH5Id);
                            sb.append(DeviceHelper.getDeviceID(true));
                            sb.append(System.currentTimeMillis());
                            final String sb2 = sb.toString();
                            UIUtils.logD("RtcVoiceModule", "roomId = " + sb2);
                            RtcVoiceHelper.getInstance().buildModul(WebRtcVoiceJsPlugin.this.activityContext, null, sb2, 0, optString, new RtcVoiceModule.CallBack() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$WebRtcVoiceJsPlugin$1$1$AomxIK0_Z9scNGv2dzo7UFdXHak
                                @Override // com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.CallBack
                                public final void callback(int i, int i2) {
                                    WebRtcVoiceJsPlugin.C96891.C96901.lambda$onGranted$0(WebRtcVoiceJsPlugin.C96891.C96901.this, optString2, sb2, i, i2);
                                }
                            });
                        }

                        public static /* synthetic */ void lambda$onGranted$0(C96901 c96901, String str, String str2, int i, int i2) {
                            if (RtcVoiceHelper.getInstance() != null) {
                                RtcVoiceHelper.getInstance().setTips(str);
                            }
                            UIUtils.logD("RtcVoiceModule", "type = " + i + "|status = " + i2);
                            if (i == 0) {
                                WebRtcVoiceJsPlugin.this.callbackSuccess(WebRtcVoiceJsPlugin.this.getInfo(str2));
                            } else if (i == 1 && i2 == 12) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("code", i2);
                                    jSONObject.put("msg", "本人主动挂断");
                                    WebRtcVoiceJsPlugin.this.callbackFail(jSONObject);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (RtcHelper.getInstance() != null) {
                                    RtcHelper.getInstance().destory();
                                }
                            }
                        }

                        @Override // com.p284qw.soul.permission.callbcak.SpecialPermissionListener
                        public void onDenied(Special special) {
                            UIUtils.toast("未开启悬浮窗权限");
                        }
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                    public void onPermissionDenied(Permission[] permissionArr) {
                        PermissionDialog.dimissDialog();
                    }
                });
                return;
            case 2:
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().destory();
                    UIUtils.setStatusBarColor(this.activityContext);
                    return;
                }
                null2show();
                return;
            case 3:
                String optString2 = this.parameterJO.optJSONObject("params").optString("userNickName");
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().setUserNick(optString2);
                    RtcVoiceHelper.getInstance().setWaitType(new RtcVoiceModule.CallBack() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$WebRtcVoiceJsPlugin$TVGYAakeC6ggRelPvX3zT5-pJmo
                        @Override // com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.CallBack
                        public final void callback(int i, int i2) {
                            WebRtcVoiceJsPlugin.lambda$onExec$0(WebRtcVoiceJsPlugin.this, i, i2);
                        }
                    });
                    return;
                }
                null2show();
                return;
            case 4:
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().setConnectRoom(this.parameterJO.optJSONObject("params").optString("userId"), this.parameterJO.optJSONObject("params").optString("userNickName"), new RtcVoiceModule.CallBack() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$WebRtcVoiceJsPlugin$oiuqwl2Lug5mTnQ2OM2LKyE1Dr0
                        @Override // com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.CallBack
                        public final void callback(int i, int i2) {
                            WebRtcVoiceJsPlugin.lambda$onExec$1(WebRtcVoiceJsPlugin.this, i, i2);
                        }
                    });
                    return;
                } else {
                    null2show();
                    return;
                }
            case 5:
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().zoom(true);
                    return;
                } else {
                    null2show();
                    return;
                }
            case 6:
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().zoom(false);
                    return;
                } else {
                    null2show();
                    return;
                }
            case 7:
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().pauseStreaming(true);
                    return;
                } else {
                    null2show();
                    return;
                }
            case '\b':
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().pauseStreaming(false);
                    return;
                } else {
                    null2show();
                    return;
                }
            case '\t':
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().setMonitorChangeAudio(new RtcVoiceModule.CallBack() { // from class: com.sinovatech.unicom.separatemodule.webrtc.WebRtcVoiceJsPlugin.2
                        @Override // com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule.CallBack
                        public void callback(int i, int i2) {
                            if (i <= 0 || 14 != i2) {
                                return;
                            }
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put("code", i2);
                                jSONObject2.put("msg", "主动切换语音");
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            WebRtcVoiceJsPlugin.this.callbackSuccess(jSONObject2);
                        }
                    });
                    return;
                } else {
                    null2show();
                    return;
                }
            case '\n':
                if (RtcVoiceHelper.getInstance() != null) {
                    RtcVoiceHelper.getInstance().setOnlyAudio();
                    return;
                } else {
                    null2show();
                    return;
                }
            default:
                return;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$onExec$0(WebRtcVoiceJsPlugin webRtcVoiceJsPlugin, int i, int i2) {
        if (i > 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", i2);
                if (i2 != 50) {
                    switch (i2) {
                        case 11:
                            jSONObject.put("msg", "通话中");
                            webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                            return;
                        case 12:
                            jSONObject.put("msg", "本人主动挂断");
                            break;
                        case 13:
                            break;
                        case 14:
                            jSONObject.put("msg", "主动切换语音");
                            webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                            return;
                        case 15:
                            jSONObject.put("msg", "用户加入房间");
                            webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                            return;
                        default:
                            switch (i2) {
                                case 57:
                                    jSONObject.put("msg", "用户或者营业员关闭摄像头");
                                    webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                                    return;
                                case 58:
                                    jSONObject.put("msg", "用户或者营业员开启摄像头");
                                    webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                                    return;
                                default:
                                    return;
                            }
                    }
                    if (i2 != 12) {
                        jSONObject.put("msg", "对方挂断");
                    }
                    webRtcVoiceJsPlugin.callbackFail(jSONObject);
                    if (RtcVoiceHelper.getInstance() != null) {
                        RtcVoiceHelper.getInstance().destory();
                        return;
                    }
                    return;
                }
                jSONObject.put("msg", "呼叫中");
                webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                webRtcVoiceJsPlugin.callbackSuccess("崩溃报错");
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$onExec$1(WebRtcVoiceJsPlugin webRtcVoiceJsPlugin, int i, int i2) {
        if (i > 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", i2);
                if (i2 != 50) {
                    switch (i2) {
                        case 11:
                            jSONObject.put("msg", "通话中");
                            webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                            return;
                        case 12:
                            jSONObject.put("msg", "本人主动挂断");
                            break;
                        case 13:
                            break;
                        case 14:
                            jSONObject.put("msg", "主动切换语音");
                            webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                            return;
                        case 15:
                            jSONObject.put("msg", "用户加入房间");
                            webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                            return;
                        default:
                            switch (i2) {
                                case 57:
                                    jSONObject.put("msg", "用户或者营业员关闭摄像头");
                                    webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                                    return;
                                case 58:
                                    jSONObject.put("msg", "用户或者营业员开启摄像头");
                                    webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
                                    return;
                                default:
                                    return;
                            }
                    }
                    if (i2 != 12) {
                        jSONObject.put("msg", "对方挂断");
                    }
                    webRtcVoiceJsPlugin.callbackFail(jSONObject);
                    if (RtcVoiceHelper.getInstance() != null) {
                        RtcVoiceHelper.getInstance().destory();
                        return;
                    }
                    return;
                }
                jSONObject.put("msg", "呼叫中");
                webRtcVoiceJsPlugin.callbackSuccess(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                webRtcVoiceJsPlugin.callbackSuccess("崩溃报错");
            }
        }
    }

    private void null2show() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", 4);
            jSONObject.put("msg", "获取janus管理对象失败，请确认第一步init方法是否执行成功。");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callbackSuccess(jSONObject);
    }

    private void getMediaInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.CAMERA").isGranted()) {
                jSONObject.put("video", "0");
            } else {
                jSONObject.put("video", "1");
            }
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.RECORD_AUDIO").isGranted()) {
                jSONObject.put("audio", "0");
            } else {
                jSONObject.put("audio", "1");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callbackSuccess(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getInfo(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", 20);
            if (TextUtils.isEmpty(str)) {
                str = DeviceHelper.getDeviceID(false);
            }
            jSONObject.put("msg", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
