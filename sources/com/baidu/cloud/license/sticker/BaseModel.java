package com.baidu.cloud.license.sticker;

import com.baidu.cloud.license.INotProguard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaseModel implements INotProguard {
    private Object message;
    private boolean success;

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public Object getMessage() {
        return this.message;
    }

    public void setMessage(Object obj) {
        this.message = obj;
    }
}
