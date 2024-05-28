package com.tydic.tydic_tracker.app;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TimeLoop {
    @SuppressLint({"HandlerLeak"})
    Handler handler = new Handler() { // from class: com.tydic.tydic_tracker.app.TimeLoop.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Log.i("tyLog", "开始执行代码");
            TYApplication.sendAllData();
        }
    };
    private Timer timer;

    public void cancleTime() {
        this.timer.cancel();
    }

    public void startLister() {
        try {
            this.timer = new Timer();
            long j = 10000;
            this.timer.scheduleAtFixedRate(new TimerTask() { // from class: com.tydic.tydic_tracker.app.TimeLoop.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    TimeLoop.this.handler.sendMessage(message);
                }
            }, j, j);
        } catch (Exception e) {
            Log.i("tyLog", e.toString());
        }
    }
}
