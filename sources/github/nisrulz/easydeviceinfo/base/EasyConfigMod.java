package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyConfigMod {
    private final Context context;

    public EasyConfigMod(Context context) {
        this.context = context;
    }

    public final boolean hasSdCard() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public final boolean isRunningOnEmulator() {
        return (Build.BRAND.contains("generic") || Build.DEVICE.contains("generic") || Build.PRODUCT.contains("sdk") || Build.HARDWARE.contains("goldfish")) || (Build.MANUFACTURER.contains("Genymotion") || Build.PRODUCT.contains("vbox86p") || Build.DEVICE.contains("vbox86p") || Build.HARDWARE.contains("vbox86"));
    }

    @RingerMode
    public final int getDeviceRingerMode() {
        AudioManager audioManager = (AudioManager) this.context.getSystemService("audio");
        if (audioManager != null) {
            switch (audioManager.getRingerMode()) {
                case 0:
                    return 0;
                case 1:
                    return 2;
                case 2:
                default:
                    return 1;
            }
        }
        return 1;
    }

    public final long getTime() {
        return System.currentTimeMillis();
    }

    public final String getFormattedTime() {
        return SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
    }

    public final long getUpTime() {
        return SystemClock.uptimeMillis();
    }

    public final String getFormattedUpTime() {
        return SimpleDateFormat.getTimeInstance().format(Long.valueOf(SystemClock.uptimeMillis()));
    }

    public final Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public final String getFormattedDate() {
        return SimpleDateFormat.getDateInstance().format(Calendar.getInstance().getTime());
    }
}
