package com.bytedance.sdk.openadsdk.downloadnew.core;

import android.graphics.drawable.Drawable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DialogBuilder {
    public IDialogStatusChangedListener dialogStatusChangedListener;
    public Drawable icon;
    public String message;
    public String negativeBtnText;
    public String positiveBtnText;
    public String title;

    public static DialogBuilder builder() {
        return new DialogBuilder();
    }

    public DialogBuilder setTitle(String str) {
        this.title = str;
        return this;
    }

    public DialogBuilder setMessage(String str) {
        this.message = str;
        return this;
    }

    public DialogBuilder setPositiveBtnText(String str) {
        this.positiveBtnText = str;
        return this;
    }

    public DialogBuilder setNegativeBtnText(String str) {
        this.negativeBtnText = str;
        return this;
    }

    public DialogBuilder setIcon(Drawable drawable) {
        this.icon = drawable;
        return this;
    }

    public DialogBuilder setDialogStatusChangedListener(IDialogStatusChangedListener iDialogStatusChangedListener) {
        this.dialogStatusChangedListener = iDialogStatusChangedListener;
        return this;
    }
}
