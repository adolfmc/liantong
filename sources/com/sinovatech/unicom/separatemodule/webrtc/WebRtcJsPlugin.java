package com.sinovatech.unicom.separatemodule.webrtc;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.p284qw.soul.permission.callbcak.SpecialPermissionListener;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.webrtc.RtcModule;
import com.sinovatech.unicom.separatemodule.webrtc.WebRtcJsPlugin;
import io.reactivex.disposables.Disposable;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/webrtc")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WebRtcJsPlugin extends BaseJSPlugin {
    private Disposable freeTips;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        char c;
        Log.d("RtcModule", "调用webrtc能力");
        String optString = this.parameterJO.optString("action");
        Log.d("RtcModule", "========action=" + optString);
        switch (optString.hashCode()) {
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
                    Log.d("RtcModule", "========窗口还未销毁，重复拉起");
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
                hideKeyboard(this.activityContext);
                PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限、音频权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.RECORD_AUDIO"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.WebRtcJsPlugin.1

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* renamed from: com.sinovatech.unicom.separatemodule.webrtc.WebRtcJsPlugin$1$1 */
                    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
                    public class C96881 implements SpecialPermissionListener {
                        C96881() {
                        }

                        @Override // com.p284qw.soul.permission.callbcak.SpecialPermissionListener
                        public void onGranted(Special special) {
                            StringBuilder sb = new StringBuilder();
                            WebRtcJsPlugin.this.parameterJO.optJSONObject("params").optString("userName");
                            String optString = WebRtcJsPlugin.this.parameterJO.optJSONObject("params").optString("beautyLevel");
                            String optString2 = WebRtcJsPlugin.this.parameterJO.optJSONObject("params").optString("role");
                            final String optString3 = WebRtcJsPlugin.this.parameterJO.optJSONObject("params").optString("tips");
                            sb.append(TextUtils.isEmpty(WebRtcJsPlugin.this.webFragment.currentH5Id) ? "" : WebRtcJsPlugin.this.webFragment.currentH5Id);
                            sb.append(DeviceHelper.getDeviceID(true));
                            sb.append(System.currentTimeMillis());
                            final String sb2 = sb.toString();
                            RtcHelper.getInstance().buildModul(WebRtcJsPlugin.this.activityContext, null, sb2, 0, optString, optString2, new RtcModule.CallBack() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$WebRtcJsPlugin$1$1$5wQkbd1u5W9jvZUU882agxrE6nE
                                @Override // com.sinovatech.unicom.separatemodule.webrtc.RtcModule.CallBack
                                public final void callback(int i, int i2) {
                                    WebRtcJsPlugin.C96871.C96881.lambda$onGranted$0(WebRtcJsPlugin.C96871.C96881.this, optString3, sb2, i, i2);
                                }
                            });
                        }

                        public static /* synthetic */ void lambda$onGranted$0(C96881 c96881, String str, String str2, int i, int i2) {
                            if (RtcHelper.getInstance() != null) {
                                RtcHelper.getInstance().setTips(str);
                            }
                            UIUtils.logD("RtcModule", "type = " + i + "|status = " + i2);
                            if (i == 0) {
                                WebRtcJsPlugin.this.callbackSuccess(WebRtcJsPlugin.this.getInfo(str2));
                            } else if (i == 1 && i2 == 12) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("code", i2);
                                    jSONObject.put("msg", "本人主动挂断");
                                    WebRtcJsPlugin.this.callbackFail(jSONObject);
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
                    public void onAllPermissionOk(Permission[] permissionArr) {
                        PermissionDialog.dimissDialog();
                        SoulPermission.getInstance().checkAndRequestPermission(Special.SYSTEM_ALERT, new C96881());
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                    public void onPermissionDenied(Permission[] permissionArr) {
                        PermissionDialog.dimissDialog();
                    }
                });
                return;
            case 2:
                if (RtcHelper.getInstance() != null) {
                    RtcHelper.getInstance().destory();
                    return;
                } else {
                    null2show();
                    return;
                }
            case 3:
                String optString2 = this.parameterJO.optJSONObject("params").optString("userNickName");
                if (RtcHelper.getInstance() != null) {
                    RtcHelper.getInstance().setUserNick(optString2);
                    RtcHelper.getInstance().setWaitType(new RtcModule.CallBack() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$WebRtcJsPlugin$3I5Q7x2fP2ZdTiHT9HD1p69qoyU
                        @Override // com.sinovatech.unicom.separatemodule.webrtc.RtcModule.CallBack
                        public final void callback(int i, int i2) {
                            WebRtcJsPlugin.lambda$onExec$0(WebRtcJsPlugin.this, i, i2);
                        }
                    });
                    return;
                }
                null2show();
                return;
            case 4:
                if (RtcHelper.getInstance() != null) {
                    RtcHelper.getInstance().setConnectRoom(this.parameterJO.optJSONObject("params").optString("userId"), this.parameterJO.optJSONObject("params").optString("userNickName"), new RtcModule.CallBack() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$WebRtcJsPlugin$51-TTvc3kwP8aVKR4TJKudYsoWo
                        @Override // com.sinovatech.unicom.separatemodule.webrtc.RtcModule.CallBack
                        public final void callback(int i, int i2) {
                            WebRtcJsPlugin.lambda$onExec$1(WebRtcJsPlugin.this, i, i2);
                        }
                    });
                    return;
                } else {
                    null2show();
                    return;
                }
            case 5:
                if (RtcHelper.getInstance() != null) {
                    RtcHelper.getInstance().zoom(true);
                    return;
                } else {
                    null2show();
                    return;
                }
            case 6:
                if (RtcHelper.getInstance() != null) {
                    RtcHelper.getInstance().zoom(false);
                    return;
                } else {
                    null2show();
                    return;
                }
            case 7:
                if (RtcHelper.getInstance() != null) {
                    RtcHelper.getInstance().pauseStreaming(true);
                    return;
                } else {
                    null2show();
                    return;
                }
            case '\b':
                if (RtcHelper.getInstance() != null) {
                    RtcHelper.getInstance().pauseStreaming(false);
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
    public static /* synthetic */ void lambda$onExec$0(WebRtcJsPlugin webRtcJsPlugin, int i, int i2) {
        if (i > 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", i2);
                if (i2 == 15) {
                    jSONObject.put("msg", "用户加入房间");
                    webRtcJsPlugin.callbackSuccess(jSONObject);
                } else if (i2 != 50) {
                    switch (i2) {
                        case 11:
                            jSONObject.put("msg", "通话中");
                            webRtcJsPlugin.callbackSuccess(jSONObject);
                            return;
                        case 12:
                            jSONObject.put("msg", "本人主动挂断");
                            break;
                        case 13:
                            break;
                        default:
                            switch (i2) {
                                case 57:
                                    jSONObject.put("msg", "用户或者营业员关闭摄像头");
                                    webRtcJsPlugin.callbackSuccess(jSONObject);
                                    return;
                                case 58:
                                    jSONObject.put("msg", "用户或者营业员开启摄像头");
                                    webRtcJsPlugin.callbackSuccess(jSONObject);
                                    return;
                                default:
                                    return;
                            }
                    }
                    if (i2 != 12) {
                        jSONObject.put("msg", "对方挂断");
                    }
                    webRtcJsPlugin.callbackFail(jSONObject);
                    Log.d("RtcModule", "挂断状态" + i2);
                    if (RtcHelper.getInstance() != null) {
                        Log.d("RtcModule", "执行挂断状态" + i2);
                        RtcHelper.getInstance().destory();
                    }
                } else {
                    jSONObject.put("msg", "呼叫中");
                    webRtcJsPlugin.callbackSuccess(jSONObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
                webRtcJsPlugin.callbackSuccess("崩溃报错");
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$onExec$1(WebRtcJsPlugin webRtcJsPlugin, int i, int i2) {
        if (i > 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", i2);
                if (i2 == 15) {
                    jSONObject.put("msg", "用户加入房间");
                    webRtcJsPlugin.callbackSuccess(jSONObject);
                } else if (i2 != 50) {
                    switch (i2) {
                        case 11:
                            jSONObject.put("msg", "通话中");
                            webRtcJsPlugin.callbackSuccess(jSONObject);
                            return;
                        case 12:
                            jSONObject.put("msg", "本人主动挂断");
                            break;
                        case 13:
                            break;
                        default:
                            switch (i2) {
                                case 57:
                                    jSONObject.put("msg", "用户或者营业员关闭摄像头");
                                    webRtcJsPlugin.callbackSuccess(jSONObject);
                                    return;
                                case 58:
                                    jSONObject.put("msg", "用户或者营业员开启摄像头");
                                    webRtcJsPlugin.callbackSuccess(jSONObject);
                                    return;
                                default:
                                    return;
                            }
                    }
                    if (i2 != 12) {
                        jSONObject.put("msg", "对方挂断");
                    }
                    webRtcJsPlugin.callbackFail(jSONObject);
                    Log.d("RtcModule", "挂断状态" + i2);
                    if (RtcHelper.getInstance() != null) {
                        Log.d("RtcModule", "执行挂断状态" + i2);
                        RtcHelper.getInstance().destory();
                    }
                } else {
                    jSONObject.put("msg", "呼叫中");
                    webRtcJsPlugin.callbackSuccess(jSONObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
                webRtcJsPlugin.callbackSuccess("崩溃报错");
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
        Log.d("RtcModule", "tel:" + UserManager.getInstance().getCurrentPhoneNumber());
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

    public void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager == null || activity.getWindow().getAttributes().softInputMode == 2 || activity.getCurrentFocus() == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
    }
}
