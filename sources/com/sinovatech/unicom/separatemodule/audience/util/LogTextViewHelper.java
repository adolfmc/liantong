package com.sinovatech.unicom.separatemodule.audience.util;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import java.util.Locale;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LogTextViewHelper {
    private static final int MSG_UPDATE_LOG = 1;
    private Handler mHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.audience.util.LogTextViewHelper.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1 && LogTextViewHelper.this.mStarted) {
                LogTextViewHelper.this.updateLogInfo();
                LogTextViewHelper.this.mHandler.removeMessages(1);
                LogTextViewHelper.this.mHandler.sendEmptyMessageDelayed(1, 1000L);
            }
        }
    };
    private boolean mStarted;
    protected TextView mTextView;

    protected String getDebugString() {
        return "";
    }

    protected String getMediaInfoString() {
        return "";
    }

    protected String getStateString() {
        return "";
    }

    public LogTextViewHelper(TextView textView) {
        this.mTextView = textView;
    }

    public void start() {
        if (this.mStarted) {
            return;
        }
        this.mStarted = true;
        this.mHandler.sendEmptyMessageDelayed(1, 1000L);
    }

    public void stop() {
        if (this.mStarted) {
            this.mStarted = false;
            this.mHandler.removeMessages(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String formatedDurationMilli(long j) {
        return j >= 1000 ? String.format(Locale.US, "%.2f sec", Float.valueOf(((float) j) / 1000.0f)) : String.format(Locale.US, "%d msec", Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String formatedSpeed(long j, long j2) {
        if (j2 > 0 && j > 0) {
            float f = (((float) j) * 1000.0f) / ((float) j2);
            return f >= 1000000.0f ? String.format(Locale.US, "%.2f MB/s", Float.valueOf((f / 1000.0f) / 1000.0f)) : f >= 1000.0f ? String.format(Locale.US, "%.1f KB/s", Float.valueOf(f / 1000.0f)) : String.format(Locale.US, "%d B/s", Long.valueOf(f));
        }
        return "0 B/s";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String formatedSize(long j) {
        return j >= 100000 ? String.format(Locale.US, "%.2f MB", Float.valueOf((((float) j) / 1000.0f) / 1000.0f)) : j >= 100 ? String.format(Locale.US, "%.1f KB", Float.valueOf(((float) j) / 1000.0f)) : String.format(Locale.US, "%d B", Long.valueOf(j));
    }

    protected void updateLogInfo() {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(getLogInfoString());
        }
    }

    protected String getLogInfoString() {
        return getStateString() + getMediaInfoString() + getDebugString();
    }
}
