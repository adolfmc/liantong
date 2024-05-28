package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BitmapDescriptor {

    /* renamed from: a */
    Bitmap f5990a;

    /* renamed from: b */
    private Bundle f5991b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitmapDescriptor(Bitmap bitmap) {
        if (bitmap != null) {
            this.f5990a = m18977a(bitmap, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    /* renamed from: a */
    private Bitmap m18977a(Bitmap bitmap, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    /* renamed from: a */
    byte[] m18978a() {
        ByteBuffer allocate = ByteBuffer.allocate(this.f5990a.getWidth() * this.f5990a.getHeight() * 4);
        this.f5990a.copyPixelsToBuffer(allocate);
        return allocate.array();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public Bundle m18976b() {
        if (this.f5990a != null) {
            if (this.f5991b == null) {
                Bundle bundle = new Bundle();
                bundle.putInt("image_width", this.f5990a.getWidth());
                bundle.putInt("image_height", this.f5990a.getHeight());
                byte[] m18978a = m18978a();
                bundle.putByteArray("image_data", m18978a);
                MessageDigest messageDigest = null;
                try {
                    messageDigest = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if (messageDigest != null) {
                    messageDigest.update(m18978a, 0, m18978a.length);
                    byte[] digest = messageDigest.digest();
                    StringBuilder sb = new StringBuilder("");
                    for (byte b : digest) {
                        sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
                    }
                    bundle.putString("image_hashcode", sb.toString());
                }
                this.f5991b = bundle;
            }
            return this.f5991b;
        }
        throw new IllegalStateException("BDMapSDKException: the bitmap has been recycled! you can not use it again");
    }

    public void clearCache() {
        Bundle bundle = this.f5991b;
        if (bundle != null) {
            bundle.clear();
            this.f5991b = null;
        }
    }

    public Bitmap getBitmap() {
        return this.f5990a;
    }

    public void recycle() {
        Bitmap bitmap = this.f5990a;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f5990a.recycle();
        this.f5990a = null;
    }
}
