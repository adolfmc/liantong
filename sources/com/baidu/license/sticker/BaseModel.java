package com.baidu.license.sticker;

import com.baidu.license.INotProguard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaseModel implements INotProguard {
    public Object message;
    public boolean success;

    public Object getMessage() {
        return this.message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setMessage(Object obj) {
        this.message = obj;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }
}
