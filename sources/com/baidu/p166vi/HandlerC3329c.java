package com.baidu.p166vi;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class HandlerC3329c extends Handler {
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SensorManager sensorManager;
        SensorManager sensorManager2;
        SensorManager sensorManager3;
        SensorEventListener sensorEventListener;
        SensorManager sensorManager4;
        SensorEventListener sensorEventListener2;
        VCompass vCompass = (VCompass) message.obj;
        if (vCompass == null) {
            return;
        }
        switch (message.what) {
            case 1:
                Context context = VIContext.getContext();
                sensorManager = vCompass.f8190a;
                if (sensorManager == null) {
                    vCompass.f8190a = (SensorManager) context.getSystemService("sensor");
                }
                sensorManager2 = vCompass.f8190a;
                List<Sensor> sensorList = sensorManager2.getSensorList(3);
                if (sensorList.size() > 0) {
                    sensorManager3 = vCompass.f8190a;
                    sensorEventListener = vCompass.f8194f;
                    sensorManager3.registerListener(sensorEventListener, sensorList.get(0), 1);
                    return;
                }
                return;
            case 2:
                sensorManager4 = vCompass.f8190a;
                sensorEventListener2 = vCompass.f8194f;
                sensorManager4.unregisterListener(sensorEventListener2);
                return;
            default:
                return;
        }
    }
}
