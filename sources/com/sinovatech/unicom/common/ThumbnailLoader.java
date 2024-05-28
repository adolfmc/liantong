package com.sinovatech.unicom.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.p083v4.util.LruCache;
import android.text.TextUtils;
import android.widget.ImageView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ThumbnailLoader {
    public Context context;
    public ExecutorService executorService;
    public Handler handler = new Handler();
    public LruCache<String, Bitmap> memoryCache;

    public ThumbnailLoader(Context context) {
        this.context = context;
        initMemoryCache();
        initExecutorService();
    }

    private void initMemoryCache() {
        this.memoryCache = new LruCache<String, Bitmap>((Utils.getMemoryClass(this.context) / 8) * 1024 * 1024) { // from class: com.sinovatech.unicom.common.ThumbnailLoader.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.p083v4.util.LruCache
            public int sizeOf(String str, Bitmap bitmap) {
                return Utils.getBitmapSize(bitmap);
            }
        };
    }

    private void initExecutorService() {
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void loadPhoto(ImageView imageView, String str, int i, int i2, boolean z) {
        Bitmap bitmapFromMemoryCache = getBitmapFromMemoryCache(str);
        if (bitmapFromMemoryCache != null) {
            imageView.setImageBitmap(bitmapFromMemoryCache);
        }
        if (!z) {
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            addBitmapToMemoryCache(str, decodeFile);
            imageView.setImageBitmap(decodeFile);
            return;
        }
        this.executorService.execute(new resizeBitmapRunnable(imageView, str, i, i2));
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class resizeBitmapRunnable implements Runnable {
        String path;
        int reqHeight;
        int reqWidth;
        ImageView sourceImageView;

        public resizeBitmapRunnable(ImageView imageView, String str, int i, int i2) {
            this.sourceImageView = imageView;
            this.path = str;
            this.reqWidth = i;
            this.reqHeight = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            final Bitmap resizeBitmap = ThumbnailLoader.this.resizeBitmap(this.path, this.reqWidth, this.reqHeight);
            ThumbnailLoader.this.addBitmapToMemoryCache(this.path, resizeBitmap);
            ThumbnailLoader.this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.common.ThumbnailLoader.resizeBitmapRunnable.1
                @Override // java.lang.Runnable
                public void run() {
                    resizeBitmapRunnable.this.sourceImageView.setImageBitmap(resizeBitmap);
                }
            });
        }
    }

    public void addBitmapToMemoryCache(String str, Bitmap bitmap) {
        LruCache<String, Bitmap> lruCache;
        if (TextUtils.isEmpty(str) || bitmap == null || (lruCache = this.memoryCache) == null || lruCache.get(str) != null) {
            return;
        }
        this.memoryCache.put(str, bitmap);
    }

    public Bitmap getBitmapFromMemoryCache(String str) {
        Bitmap bitmap;
        LruCache<String, Bitmap> lruCache = this.memoryCache;
        if (lruCache == null || (bitmap = lruCache.get(str)) == null) {
            return null;
        }
        return bitmap;
    }

    public void clearMemoryCache() {
        this.memoryCache.evictAll();
    }

    public Bitmap resizeBitmap(String str, int i, int i2) {
        int i3 = i / 3;
        int i4 = i2 / 3;
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i5 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i6 = options.outHeight;
        int i7 = options.outWidth;
        if (i6 > i4 || i7 > i3) {
            if (i7 > i6) {
                i5 = Math.round(i6 / i4);
            } else {
                i5 = Math.round(i7 / i3);
            }
            while ((i7 * i6) / (i5 * i5) > i3 * i4 * 2) {
                i5++;
            }
        }
        options.inSampleSize = i5;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }
}
