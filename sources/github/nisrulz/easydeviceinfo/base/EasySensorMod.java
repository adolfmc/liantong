package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class EasySensorMod {
    private final SensorManager sensorManager;

    public EasySensorMod(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
    }

    public List<Sensor> getAllSensors() {
        return this.sensorManager.getSensorList(-1);
    }
}
