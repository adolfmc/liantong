package com.dueeeke.videoplayer.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dueeeke.videoplayer.C4202R;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CenterView extends LinearLayout {
    private ImageView ivIcon;
    private ProgressBar proPercent;
    private TextView tvPercent;

    public CenterView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setGravity(17);
        View inflate = LayoutInflater.from(getContext()).inflate(C4202R.C4206layout.layout_center_window, this);
        this.ivIcon = (ImageView) inflate.findViewById(C4202R.C4205id.iv_icon);
        this.tvPercent = (TextView) inflate.findViewById(C4202R.C4205id.tv_percent);
        this.proPercent = (ProgressBar) inflate.findViewById(C4202R.C4205id.pro_percent);
    }

    public void setIcon(int i) {
        ImageView imageView = this.ivIcon;
        if (imageView != null) {
            imageView.setImageResource(i);
        }
    }

    public void setTextView(String str) {
        TextView textView = this.tvPercent;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setProPercent(int i) {
        ProgressBar progressBar = this.proPercent;
        if (progressBar != null) {
            progressBar.setProgress(i);
        }
    }

    public void setProVisibility(int i) {
        ProgressBar progressBar = this.proPercent;
        if (progressBar != null) {
            progressBar.setVisibility(i);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i != 0) {
            startAnimation(AnimationUtils.loadAnimation(getContext(), C4202R.anim.anim_alpha_out));
        }
    }
}
