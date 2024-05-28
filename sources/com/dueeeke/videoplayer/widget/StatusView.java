package com.dueeeke.videoplayer.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dueeeke.videoplayer.C4202R;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StatusView extends LinearLayout {
    private TextView btnAction;
    private float downX;
    private float downY;
    private ImageView ivClose;
    private TextView tvMessage;

    public StatusView(Context context) {
        this(context, null);
    }

    public StatusView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(C4202R.C4206layout.layout_status_view, this);
        this.tvMessage = (TextView) inflate.findViewById(C4202R.C4205id.message);
        this.btnAction = (TextView) inflate.findViewById(C4202R.C4205id.status_btn);
        this.ivClose = (ImageView) inflate.findViewById(C4202R.C4205id.btn_close);
        setBackgroundResource(17170444);
        setClickable(true);
    }

    public void setMessage(String str) {
        TextView textView = this.tvMessage;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setButtonTextAndAction(String str, View.OnClickListener onClickListener) {
        TextView textView = this.btnAction;
        if (textView != null) {
            textView.setText(str);
            this.btnAction.setOnClickListener(onClickListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.downX = motionEvent.getX();
            this.downY = motionEvent.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2) {
            float abs = Math.abs(motionEvent.getX() - this.downX);
            float abs2 = Math.abs(motionEvent.getY() - this.downY);
            if (abs > ViewConfiguration.get(getContext()).getScaledTouchSlop() || abs2 > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
