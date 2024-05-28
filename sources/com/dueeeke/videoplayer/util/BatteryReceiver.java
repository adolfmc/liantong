package com.dueeeke.videoplayer.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.dueeeke.videoplayer.C4202R;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BatteryReceiver extends BroadcastReceiver {
    private ImageView pow;

    public BatteryReceiver(ImageView imageView) {
        this.pow = imageView;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        int i = (extras.getInt("level") * 100) / extras.getInt("scale");
        if (i < 15) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_10);
        } else if (i < 25) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_20);
        } else if (i < 35) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_30);
        } else if (i < 45) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_40);
        } else if (i < 55) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_50);
        } else if (i < 65) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_60);
        } else if (i < 75) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_70);
        } else if (i < 85) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_80);
        } else if (i < 95) {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery_90);
        } else {
            this.pow.setImageResource(C4202R.C4204drawable.ic_action_battery);
        }
    }
}
