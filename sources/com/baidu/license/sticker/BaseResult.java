package com.baidu.license.sticker;

import com.baidu.license.INotProguard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaseResult implements INotProguard {
    public static final String MSG_PARAMS_ERROR = "参数错误";
    public static final String MSG_RESPONSE_ERROR = "获取数据失败";
    public static final int RESPONSE_LICENSE_EXPIRED = 5012;
    public static final int RESPONSE_NET_ERROR = 9980;
    public static final int RESPONSE_PARAMS_ERROR = 4016;
    public static final int RESPONSE_RESULT_NULL = 9970;
    public static final int RESPONSE_SUCCESS = 3010;
    public int code;
    public String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
