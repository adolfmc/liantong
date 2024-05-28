package com.sinovatech.unicom.separatemodule.user.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MsgAutoScrollView extends HorizontalScrollView {
    private final int MSG_SCROLL;
    private final int MSG_SCROLL_Loop;
    private boolean autoToScroll;
    private int fistTimeScroll;
    private Handler mHandler;
    private ISmartScrollChangedListener mSmartScrollChangedListener;
    private int paddingTop;
    private boolean scrollAble;
    private boolean scrollLoop;
    private int scrollRate;
    private boolean scrolledToBottom;
    private boolean scrolledToTop;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ISmartScrollChangedListener {
        void onScrolledToBottom();

        void onScrolledToTop();
    }

    static /* synthetic */ int access$212(MsgAutoScrollView msgAutoScrollView, int i) {
        int i2 = msgAutoScrollView.paddingTop + i;
        msgAutoScrollView.paddingTop = i2;
        return i2;
    }

    public MsgAutoScrollView(Context context) {
        this(context, null);
    }

    public MsgAutoScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MsgAutoScrollView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MsgAutoScrollView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.scrolledToTop = true;
        this.scrolledToBottom = false;
        this.paddingTop = 0;
        this.MSG_SCROLL = 10;
        this.MSG_SCROLL_Loop = 11;
        this.scrollAble = true;
        this.autoToScroll = true;
        this.scrollLoop = false;
        this.fistTimeScroll = 5000;
        this.scrollRate = 500;
        this.mHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.user.view.MsgAutoScrollView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 10:
                        if (MsgAutoScrollView.this.scrollAble && MsgAutoScrollView.this.autoToScroll) {
                            MsgAutoScrollView msgAutoScrollView = MsgAutoScrollView.this;
                            msgAutoScrollView.scrollTo(msgAutoScrollView.paddingTop, 0);
                            MsgAutoScrollView.access$212(MsgAutoScrollView.this, 1);
                            MsgAutoScrollView.this.mHandler.removeMessages(10);
                            MsgAutoScrollView.this.mHandler.sendEmptyMessageDelayed(10, MsgAutoScrollView.this.scrollRate);
                            return;
                        }
                        return;
                    case 11:
                        MsgAutoScrollView.this.paddingTop = 0;
                        MsgAutoScrollView.this.autoToScroll = true;
                        MsgAutoScrollView.this.mHandler.sendEmptyMessageDelayed(10, MsgAutoScrollView.this.fistTimeScroll);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void setScanScrollChangedListener(ISmartScrollChangedListener iSmartScrollChangedListener) {
        this.mSmartScrollChangedListener = iSmartScrollChangedListener;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        int measuredWidth = getChildAt(0).getMeasuredWidth() - getMeasuredWidth();
        if (getScrollX() != 0 && getScrollX() == measuredWidth) {
            this.mHandler.removeMessages(10);
            this.mHandler.sendEmptyMessageDelayed(11, this.fistTimeScroll);
        }
    }

    private void notifyScrollChangedListeners() {
        Log.i("lln", "scrolledToTop==" + this.scrolledToTop);
        if (this.scrolledToTop) {
            ISmartScrollChangedListener iSmartScrollChangedListener = this.mSmartScrollChangedListener;
            if (iSmartScrollChangedListener != null) {
                iSmartScrollChangedListener.onScrolledToTop();
            }
        } else if (this.scrolledToBottom) {
            this.mHandler.removeMessages(10);
            if (!this.scrollLoop) {
                this.scrollAble = false;
            }
            if (this.scrollLoop) {
                this.mHandler.sendEmptyMessageDelayed(11, this.fistTimeScroll);
            }
            ISmartScrollChangedListener iSmartScrollChangedListener2 = this.mSmartScrollChangedListener;
            if (iSmartScrollChangedListener2 != null) {
                iSmartScrollChangedListener2.onScrolledToBottom();
            }
        }
    }

    public void setAutoToScroll(boolean z) {
        this.autoToScroll = z;
    }

    public void setFistTimeScroll(int i) {
        this.fistTimeScroll = i;
    }

    public void starPoll() {
        scrollTo(0, 0);
        this.mHandler.removeMessages(10);
        this.mHandler.sendEmptyMessageDelayed(11, this.fistTimeScroll);
    }

    public void stopPoll() {
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void setScrollRate(int i) {
        this.scrollRate = i;
    }

    public void setScrollLoop(boolean z) {
        this.scrollLoop = z;
    }
}
