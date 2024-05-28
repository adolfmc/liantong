package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

@BatteryHealth
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyBatteryMod {
    private final Context context;

    public EasyBatteryMod(Context context) {
        this.context = context;
    }

    public final int getBatteryPercentage() {
        Intent batteryStatusIntent = getBatteryStatusIntent();
        if (batteryStatusIntent != null) {
            return (int) ((batteryStatusIntent.getIntExtra("level", -1) / batteryStatusIntent.getIntExtra("scale", -1)) * 100.0f);
        }
        return 0;
    }

    private Intent getBatteryStatusIntent() {
        return this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    public final boolean isDeviceCharging() {
        int intExtra = getBatteryStatusIntent().getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    @BatteryHealth
    public final int getBatteryHealth() {
        Intent batteryStatusIntent = getBatteryStatusIntent();
        return (batteryStatusIntent == null || batteryStatusIntent.getIntExtra("health", 0) != 2) ? 0 : 1;
    }

    public final String getBatteryTechnology() {
        return CheckValidityUtil.checkValidData(getBatteryStatusIntent().getStringExtra("technology"));
    }

    public final float getBatteryTemperature() {
        Intent batteryStatusIntent = getBatteryStatusIntent();
        if (batteryStatusIntent != null) {
            return (float) (batteryStatusIntent.getIntExtra("temperature", 0) / 10.0d);
        }
        return 0.0f;
    }

    public final int getBatteryVoltage() {
        Intent batteryStatusIntent = getBatteryStatusIntent();
        if (batteryStatusIntent != null) {
            return batteryStatusIntent.getIntExtra("voltage", 0);
        }
        return 0;
    }

    @ChargingVia
    public final int getChargingSource() {
        int intExtra = getBatteryStatusIntent().getIntExtra("plugged", 0);
        if (intExtra != 4) {
            switch (intExtra) {
                case 1:
                    return 1;
                case 2:
                    return 0;
                default:
                    return 3;
            }
        }
        return 2;
    }

    public final boolean isBatteryPresent() {
        return getBatteryStatusIntent().getExtras() != null && getBatteryStatusIntent().getExtras().getBoolean("present");
    }
}
