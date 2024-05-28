package com.chinaunicon.jtwifilib.core.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtDialog extends Dialog {
    private int animations;
    Context context;
    private int gravity;
    public boolean isback;
    private View view;

    public JtDialog(Context context, View view) {
        super(context);
        this.isback = false;
        this.animations = -1;
        this.gravity = -1;
        this.context = context;
        this.view = view;
    }

    public JtDialog(Context context, int i, View view) {
        super(context, i);
        this.isback = false;
        this.animations = -1;
        this.gravity = -1;
        this.context = context;
        this.view = view;
    }

    public JtDialog(Context context, int i, View view, int i2, int i3) {
        super(context, i);
        this.isback = false;
        this.animations = -1;
        this.gravity = -1;
        this.context = context;
        this.view = view;
        this.animations = i2;
        this.gravity = i3;
    }

    public JtDialog(Context context, int i, View view, int i2) {
        super(context, i);
        this.isback = false;
        this.animations = -1;
        this.gravity = -1;
        this.context = context;
        this.view = view;
        this.animations = i2;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.view);
        if (this.animations != -1) {
            Window window = getWindow();
            int i = this.gravity;
            if (i != -1) {
                window.setGravity(i);
            } else {
                window.setGravity(17);
            }
            window.setWindowAnimations(this.animations);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.isback && i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void setIsback(boolean z) {
        this.isback = z;
    }
}
