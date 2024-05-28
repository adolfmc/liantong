package com.tydic.softphone;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.tydic.softphone.widgets.VoiceFloatingView;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class VoiceFloatingService extends Service {
    static String ACTION_DISMISS_FLOATING = "action_dismiss_floating";
    static String ACTION_SHOW_FIRST = "action_show_first";
    static String ACTION_SHOW_FLOATING = "action_show_floating";
    static String ACTION_SHOW_TALK = "action_show_talking";
    static ScreenReceiver receiver;
    private VoiceFloatingView mVoiceFloatingView = null;
    String showText = "";
    static Boolean isStart = false;
    static Boolean isFirstInto = false;

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    @RequiresApi(api = 23)
    public void onCreate() {
        super.onCreate();
        isStart = true;
        Log.e("tydic", "VoiceFloatingService   onCreate -->");
        this.mVoiceFloatingView = new VoiceFloatingView(this);
        receiver = new ScreenReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SHOW_FLOATING);
        intentFilter.addAction(ACTION_DISMISS_FLOATING);
        intentFilter.addAction(ACTION_SHOW_TALK);
        intentFilter.addAction(ACTION_SHOW_FIRST);
        getApplicationContext().registerReceiver(receiver, intentFilter);
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class ScreenReceiver extends BroadcastReceiver {
        public ScreenReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        @RequiresApi(api = 23)
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("tydic", "receivew--->");
            Log.i("tydic", "action--->" + action);
            if (VoiceFloatingService.ACTION_SHOW_TALK.equals(action)) {
                String string = intent.getExtras().getString("time");
                VoiceFloatingService voiceFloatingService = VoiceFloatingService.this;
                voiceFloatingService.showText = string;
                if (voiceFloatingService.mVoiceFloatingView != null) {
                    VoiceFloatingService.this.mVoiceFloatingView.drawTime(string);
                }
            } else if (VoiceFloatingService.ACTION_SHOW_FLOATING.equals(action)) {
                if (VoiceFloatingService.this.mVoiceFloatingView != null) {
                    VoiceFloatingService.this.mVoiceFloatingView.show();
                }
            } else if (VoiceFloatingService.ACTION_DISMISS_FLOATING.equals(action)) {
                if (VoiceFloatingService.this.mVoiceFloatingView != null) {
                    VoiceFloatingService.this.mVoiceFloatingView.dismiss();
                }
                VoiceFloatingService.this.stopSelf();
            }
        }
    }

    @Override // android.app.Service
    @RequiresApi(api = 23)
    public int onStartCommand(Intent intent, int i, int i2) {
        VoiceFloatingView voiceFloatingView;
        Log.e("tydic", "onStartCommand");
        Log.e("tydic", "isStart:" + isStart);
        Log.e("tydic", "isFirstInto:" + isFirstInto);
        if (isStart.booleanValue() && (voiceFloatingView = this.mVoiceFloatingView) != null) {
            voiceFloatingView.show();
        }
        this.mVoiceFloatingView.setOnClickListener(new View.OnClickListener() { // from class: com.tydic.softphone.VoiceFloatingService.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                Log.i("tydicflow:showText", VoiceFloatingService.this.showText);
                VoiceFloatingService.this.showText.equals(Configs.TALK_CLOSE);
                String string = VoiceFloatingService.this.getSharedPreferences("tydic_softphone_voicecall", 0).getString("voiceCallStr", "");
                Intent intent2 = new Intent(VoiceFloatingService.this, CallActivity.class);
                intent2.setFlags(268435456);
                Log.i("tydic_voicecall", string);
                intent2.putExtra("voiceCall", string);
                VoiceFloatingService.this.startActivity(intent2);
                VoiceFloatingService.this.stopSelf();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        return super.onStartCommand(intent, i, i2);
    }

    public static void stopVoiceSelf() {
        receiver = null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        getApplicationContext().unregisterReceiver(receiver);
        Log.e("tydic", "close Service");
        VoiceFloatingView voiceFloatingView = this.mVoiceFloatingView;
        if (voiceFloatingView != null) {
            voiceFloatingView.dismiss();
            this.mVoiceFloatingView = null;
        }
        isStart = false;
    }
}
