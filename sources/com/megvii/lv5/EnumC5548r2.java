package com.megvii.lv5;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.r2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public enum EnumC5548r2 {
    LIVENESS_FINISH(1001, 1000, "LIVENESS_FINISH", "流程成功"),
    ILLEGAL_PARAMETER(1002, 1002, "ILLEGAL_PARAMETER:{%s}", "传入参数不合法"),
    REQUEST_FREQUENTLY(1006, 1006, "REQUEST_FREQUENTLY", "请求过于频繁"),
    BIZ_TOKEN_DENIED(1001, 1001, "BIZ_TOKEN_DENIED", "传入的 biz_token 不符合要求；"),
    AUTHENTICATION_FAIL(1003, 1003, "AUTHENTICATION_FAIL{%s}", "鉴权失败；"),
    MOBILE_PHONE_NOT_SUPPORT(1004, 1004, "MOBILE_PHONE_NOT_SUPPORT", "手机在不支持列表里；"),
    LOAD_LIB_FAILED(4205, 4200, "LOAD_LIB_FAILED", "加载so库失败；"),
    GET_CONFIG_FAIL(4206, 4200, "GET_CONFIG_FAIL", "读取配置失败；"),
    LIVENESS_UNKNOWN_ERROR(1005, 1005, "{%s}", "Exception"),
    API_KEY_BE_DISCONTINUED(5000, 5000, "API_KEY_BE_DISCONTINUED", "api_key被停用"),
    IP_NOT_ALLOWED(5000, 5000, "IP_NOT_ALLOWED", "不允许访问的IP"),
    BALANCE_NOT_ENOUGH(5000, 5000, "BALANCE_NOT_ENOUGH", "余额不足"),
    MORE_RETRY_TIMES(5000, 5000, "MORE_RETRY_TIMES", "获取服务器配置时超过重试次数"),
    ACCOUNT_DISCONTINUED(5000, 5000, "ACCOUNT_DISCONTINUED", "用户帐号已停用"),
    EXPIRED_SIGN(5000, 5000, "EXPIRED_SIGN", "签名过期"),
    INVALID_SIGN(5000, 5000, "INVALID_SIGN", "无效的签名"),
    CONCURRENCY_LIMIT_EXCEEDED(5000, 5000, "CONCURRENCY_LIMIT_EXCEEDED", "并发数超过限制"),
    INTERNAL_ERROR(1008, 1008, "INTERNAL_ERROR", "服务器内部错误，当此类错误发生时请再次请求，如果持续出现此类错误，请及时联系 FaceID 客服或商务"),
    NETWORK_CONFIG_ERROR(5000, 5000, "NETWORK_CONFIG_ERROR", "网络配置错误，当此类错误发生时请再次请求，如果持续出现此类错误，请及时联系 FaceID 客服或商务"),
    NETWORK_TIME_OUT(1007, 1007, "NETWORK_TIME_OUT", "网络请求超时"),
    INVALID_BUNDLE_ID(1009, 1009, "INVALID_BUNDLE_ID", "信息验证失败，请重启程序或设备后重试"),
    NETWORK_ERROR(1010, 1010, "NETWORK_ERROR", "连不上互联网，请连接上互联网后重试"),
    USER_CANCEL(1011, 1011, "USER_CANCEL", "用户取消"),
    GO_TO_BACKGROUND(1017, 1017, "GO_TO_BACKGROUND", "切到后台"),
    NO_CAMERA_PERMISSION(1012, 1012, "NO_CAMERA_PERMISSION", "没有打开相机的权限，请开启权限后重试"),
    DEVICE_NOT_SUPPORT(1013, 1013, "DEVICE_NOT_SUPPORT", "无法启动相机，请确认摄像头功能完好"),
    FACE_INIT_FAIL(1014, 1014, "FACE_INIT_FAIL", "无法启动人脸识别，请稍后重试"),
    LIVENESS_FAILURE(1016, 1016, "LIVENESS_FAILURE", "操作失败"),
    MEGLIVE_FLASH_ACTION_ERROR(1016, 1016, "MEGLIVE_FLASH_ACTION_ERROR", "灵动活体动作错误"),
    LIVENESS_TIME_OUT(1018, 1018, "LIVENESS_TIME_OUT", "操作超时，由于用户在长时间没有进行操作"),
    MEGLIVE_FLASH_ACTION_TIMEOUT(1018, 1018, "MEGLIVE_FLASH_ACTION_TIMEOUT", "灵动活体动作超时"),
    DATA_UPLOAD_FAIL(1019, 1019, "DATA_UPLOAD_FAIL", "数据上传失败"),
    MOBILE_PHONE_NOT_SUPPORT_SCRN(1022, 1022, "MOBILE_PHONE_NOT_SUPPORT_SCRN", "机型不支持录屏"),
    SCRN_AUTHORIZATION_FAIL(1023, 1023, "SCRN_AUTHORIZATION_FAIL", "录屏授权失败"),
    SCRN_RECORD_FAIL(1024, 1024, "SCRN_RECORD_FAIL", "视频录制失败"),
    VIDEO_SAVE_FAIL(1025, 1025, "VIDEO_SAVE_FAIL", "视频保存失败"),
    NO_AUDIO_RECORD_PERMISSION(1026, 1026, "NO_AUDIO_RECORD_PERMISSION", "没有语音录制权限");
    

    /* renamed from: a */
    public int f13245a;

    /* renamed from: b */
    public String f13246b;

    EnumC5548r2(int i, int i2, String str, String str2) {
        this.f13245a = i2;
        this.f13246b = str;
    }
}
