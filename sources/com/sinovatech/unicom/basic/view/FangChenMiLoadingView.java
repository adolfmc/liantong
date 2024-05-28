package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sinovatech.unicom.p318ui.GlideApp;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FangChenMiLoadingView extends Dialog {
    Activity activity;
    private ImageView loadingIv;

    public FangChenMiLoadingView(Activity activity) {
        super(activity);
        this.activity = activity;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(this.activity).inflate(2131493117, (ViewGroup) null);
        this.loadingIv = (ImageView) inflate.findViewById(2131297810);
        setContentView(inflate);
        create();
    }

    public void showDialog() {
        GlideApp.with(this.activity).load((Integer) 2131231292).into(this.loadingIv);
        show();
    }
}
