package com.fido.android.framework.service;

import android.graphics.drawable.Drawable;
import com.gmrz.android.client.asm.api.AsmException;
import com.google.gson.annotations.Expose;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IAuthenticatorUIAdapter {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class GetUIOut {
        @Expose
        public String Icon;
        @Expose
        public String Text;
        @Expose
        public String Title;
        @Expose
        public boolean hasSettings;
    }

    Drawable getIcon() throws AsmException;

    String getId();

    GetUIOut getUI() throws AsmException;

    boolean isEnabled();

    boolean isTokenRegistered();

    void openSettings(String str) throws AsmException;

    void setEnabled(boolean z) throws AsmException;
}
