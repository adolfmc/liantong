package com.android.client.asm.api.uaf;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.client.asm.core.uaf.AsmFactory;
import com.gmrz.android.client.asm.api.uaf.IUafAsmApi;
import com.gmrz.android.client.asm.api.uaf.IUafAsmBinder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class UafLocalAsmAgent implements IUafAsmBinder {
    public static final String TAG = "UafLocalAsmAgent";
    private static UafLocalAsmAgent mService;
    protected Drawable mIcon = null;
    protected ComponentName mInfo;
    protected IUafAsmApi mUafAsmApi;

    @Override // com.gmrz.android.client.asm.api.IAsmBinder
    public boolean bind() {
        return false;
    }

    @Override // com.gmrz.android.client.asm.api.IAsmBinder
    public void unbind() {
    }

    public static UafLocalAsmAgent instance(Context context) {
        if (mService == null) {
            mService = new UafLocalAsmAgent(context);
        }
        return mService;
    }

    public UafLocalAsmAgent(Context context) {
        this.mInfo = null;
        this.mUafAsmApi = null;
        this.mUafAsmApi = new AsmFactory(context).createAsmInstance(context.getPackageName());
        this.mInfo = new ComponentName(context.getPackageName(), UafLocalAsmAgent.class.getName());
    }

    @Override // com.gmrz.android.client.asm.api.IAsmBinder
    public ComponentName getComponentName() {
        return this.mInfo;
    }

    @Override // com.gmrz.android.client.asm.api.IAsmBinder
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Override // com.gmrz.android.client.asm.api.uaf.IUafAsmBinder
    public IUafAsmApi getAsmApi() {
        return this.mUafAsmApi;
    }
}
