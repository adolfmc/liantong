package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Flake {
    static HashMap<Integer, Bitmap> bitmapMap = new HashMap<>();
    Bitmap bitmap;
    int direction;
    int height;
    float rotation;
    float rotationSpeed;
    float speed;
    int width;

    /* renamed from: x */
    float f18438x;

    /* renamed from: y */
    float f18439y;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Flake createFlake(float f, Bitmap bitmap, int i, Activity activity, int i2, int i3) {
        Flake flake = new Flake();
        flake.width = i2;
        flake.height = i3;
        flake.f18438x = ((float) Math.random()) * (f - flake.width);
        flake.f18439y = 0.0f - (flake.height + ((((float) Math.random()) * flake.height) * 7.0f));
        flake.speed = (((float) Math.random()) * 150.0f) + 200.0f;
        flake.direction = new Random().nextInt(2);
        flake.rotation = (((float) Math.random()) * 180.0f) - 90.0f;
        flake.rotationSpeed = ((float) ((Math.random() * 90.0d) - 45.0d)) / 3.0f;
        flake.bitmap = bitmapMap.get(Integer.valueOf(i));
        if (flake.bitmap == null) {
            flake.bitmap = Bitmap.createScaledBitmap(bitmap, flake.width, flake.height, true);
            bitmapMap.put(Integer.valueOf(i), flake.bitmap);
        }
        return flake;
    }
}
