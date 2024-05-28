package cn.finalteam.galleryfinal.widget.crop;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import cn.finalteam.galleryfinal.C1656R;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.utils.ILogger;
import cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase;
import cn.finalteam.galleryfinal.widget.crop.MonitoredActivity;
import cn.finalteam.toolsfinal.p093io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class CropImageActivity extends MonitoredActivity {
    private static final int SIZE_DEFAULT = 2048;
    private static final int SIZE_LIMIT = 4096;
    private int aspectX;
    private int aspectY;
    private boolean cropEnabled;
    private HighlightView cropView;
    private int exifRotation;
    private final Handler handler = new Handler();
    private CropImageView imageView;
    private boolean isSaving;
    private int maxX;
    private int maxY;
    private RotateBitmap rotateBitmap;
    private int sampleSize;
    private Uri sourceUri;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }

    public abstract void setCropSaveException(Throwable th);

    public abstract void setCropSaveSuccess(File file);

    @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity
    public /* bridge */ /* synthetic */ void addLifeCycleListener(MonitoredActivity.LifeCycleListener lifeCycleListener) {
        super.addLifeCycleListener(lifeCycleListener);
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity
    public /* bridge */ /* synthetic */ void removeLifeCycleListener(MonitoredActivity.LifeCycleListener lifeCycleListener) {
        super.removeLifeCycleListener(lifeCycleListener);
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity, cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupWindowFlags();
    }

    @TargetApi(19)
    private void setupWindowFlags() {
        requestWindowFeature(1);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().clearFlags(67108864);
        }
    }

    public void initCrop(CropImageView cropImageView, boolean z, int i, int i2) {
        if (z) {
            this.aspectX = 1;
            this.aspectY = 1;
        }
        this.maxX = i;
        this.maxY = i2;
        this.imageView = cropImageView;
        cropImageView.context = this;
        cropImageView.setRecycler(new ImageViewTouchBase.Recycler() { // from class: cn.finalteam.galleryfinal.widget.crop.CropImageActivity.1
            @Override // cn.finalteam.galleryfinal.widget.crop.ImageViewTouchBase.Recycler
            public void recycle(Bitmap bitmap) {
                bitmap.recycle();
                System.gc();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setSourceUri(android.net.Uri r4) {
        /*
            r3 = this;
            cn.finalteam.galleryfinal.widget.crop.RotateBitmap r0 = r3.rotateBitmap
            r1 = 0
            if (r0 == 0) goto La
            r0.recycle()
            r3.rotateBitmap = r1
        La:
            r3.sourceUri = r4
            r0 = 0
            r3.isSaving = r0
            r3.sampleSize = r0
            r3.rotateBitmap = r1
            r3.cropView = r1
            cn.finalteam.galleryfinal.widget.crop.CropImageView r0 = r3.imageView
            r0.clear()
            if (r4 == 0) goto L6e
            android.content.ContentResolver r0 = r3.getContentResolver()
            java.io.File r0 = cn.finalteam.galleryfinal.widget.crop.CropUtil.getFromMediaUri(r3, r0, r4)
            int r0 = cn.finalteam.galleryfinal.widget.crop.CropUtil.getExifRotation(r0)
            r3.exifRotation = r0
            int r0 = r3.calculateBitmapSampleSize(r4)     // Catch: java.lang.Throwable -> L5a java.lang.OutOfMemoryError -> L5d java.io.IOException -> L62
            r3.sampleSize = r0     // Catch: java.lang.Throwable -> L5a java.lang.OutOfMemoryError -> L5d java.io.IOException -> L62
            android.content.ContentResolver r0 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L5a java.lang.OutOfMemoryError -> L5d java.io.IOException -> L62
            java.io.InputStream r4 = r0.openInputStream(r4)     // Catch: java.lang.Throwable -> L5a java.lang.OutOfMemoryError -> L5d java.io.IOException -> L62
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            r0.<init>()     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            int r2 = r3.sampleSize     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            r0.inSampleSize = r2     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            cn.finalteam.galleryfinal.widget.crop.RotateBitmap r2 = new cn.finalteam.galleryfinal.widget.crop.RotateBitmap     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r4, r1, r0)     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            int r1 = r3.exifRotation     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            r2.<init>(r0, r1)     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            r3.rotateBitmap = r2     // Catch: java.lang.Throwable -> L52 java.lang.OutOfMemoryError -> L54 java.io.IOException -> L57
            cn.finalteam.galleryfinal.widget.crop.CropUtil.closeSilently(r4)
            goto L6e
        L52:
            r0 = move-exception
            goto L6a
        L54:
            r0 = move-exception
            r1 = r4
            goto L5e
        L57:
            r0 = move-exception
            r1 = r4
            goto L63
        L5a:
            r0 = move-exception
            r4 = r1
            goto L6a
        L5d:
            r0 = move-exception
        L5e:
            cn.finalteam.galleryfinal.utils.ILogger.m22037e(r0)     // Catch: java.lang.Throwable -> L5a
            goto L66
        L62:
            r0 = move-exception
        L63:
            cn.finalteam.galleryfinal.utils.ILogger.m22037e(r0)     // Catch: java.lang.Throwable -> L5a
        L66:
            cn.finalteam.galleryfinal.widget.crop.CropUtil.closeSilently(r1)
            goto L6e
        L6a:
            cn.finalteam.galleryfinal.widget.crop.CropUtil.closeSilently(r4)
            throw r0
        L6e:
            cn.finalteam.galleryfinal.widget.crop.RotateBitmap r4 = r3.rotateBitmap
            if (r4 == 0) goto L75
            r3.startCrop()
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.galleryfinal.widget.crop.CropImageActivity.setSourceUri(android.net.Uri):void");
    }

    private int calculateBitmapSampleSize(Uri uri) throws IOException {
        InputStream openInputStream;
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        InputStream inputStream = null;
        try {
            openInputStream = getContentResolver().openInputStream(uri);
        } catch (Throwable th) {
            th = th;
        }
        try {
            BitmapFactory.decodeStream(openInputStream, null, options);
            CropUtil.closeSilently(openInputStream);
            int maxImageSize = getMaxImageSize();
            while (true) {
                if (options.outHeight / i <= maxImageSize && options.outWidth / i <= maxImageSize) {
                    return i;
                }
                i <<= 1;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = openInputStream;
            CropUtil.closeSilently(inputStream);
            throw th;
        }
    }

    private int getMaxImageSize() {
        int maxTextureSize = getMaxTextureSize();
        if (maxTextureSize == 0) {
            return 2048;
        }
        return Math.min(maxTextureSize, 4096);
    }

    private int getMaxTextureSize() {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        return iArr[0];
    }

    private void startCrop() {
        if (isFinishing()) {
            return;
        }
        this.imageView.setImageRotateBitmapResetBase(this.rotateBitmap, true);
        CropUtil.startBackgroundJob(this, null, getResources().getString(C1656R.string.waiting), new Runnable() { // from class: cn.finalteam.galleryfinal.widget.crop.CropImageActivity.2
            @Override // java.lang.Runnable
            public void run() {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                CropImageActivity.this.handler.post(new Runnable() { // from class: cn.finalteam.galleryfinal.widget.crop.CropImageActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (CropImageActivity.this.imageView.getScale() == 1.0f) {
                            CropImageActivity.this.imageView.center();
                        }
                        countDownLatch.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                    new Cropper().crop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, this.handler);
    }

    public void setCropEnabled(boolean z) {
        this.cropEnabled = z;
        if (z) {
            startCrop();
        }
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    class Cropper {
        private Cropper() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void makeDefault() {
            int i;
            if (CropImageActivity.this.rotateBitmap == null) {
                return;
            }
            HighlightView highlightView = new HighlightView(CropImageActivity.this.imageView, GalleryFinal.getGalleryTheme().getCropControlColor());
            int width = CropImageActivity.this.rotateBitmap.getWidth();
            int height = CropImageActivity.this.rotateBitmap.getHeight();
            boolean z = false;
            Rect rect = new Rect(0, 0, width, height);
            int min = (Math.min(width, height) * 4) / 5;
            if (CropImageActivity.this.aspectX == 0 || CropImageActivity.this.aspectY == 0) {
                i = min;
            } else if (CropImageActivity.this.aspectX > CropImageActivity.this.aspectY) {
                i = (CropImageActivity.this.aspectY * min) / CropImageActivity.this.aspectX;
            } else {
                i = min;
                min = (CropImageActivity.this.aspectX * min) / CropImageActivity.this.aspectY;
            }
            int i2 = (width - min) / 2;
            int i3 = (height - i) / 2;
            RectF rectF = new RectF(i2, i3, i2 + min, i3 + i);
            Matrix unrotatedMatrix = CropImageActivity.this.imageView.getUnrotatedMatrix();
            if (CropImageActivity.this.aspectX != 0 && CropImageActivity.this.aspectY != 0) {
                z = true;
            }
            highlightView.setup(unrotatedMatrix, rect, rectF, z);
            CropImageActivity.this.imageView.add(highlightView);
        }

        public void crop() {
            CropImageActivity.this.handler.post(new Runnable() { // from class: cn.finalteam.galleryfinal.widget.crop.CropImageActivity.Cropper.1
                @Override // java.lang.Runnable
                public void run() {
                    Cropper.this.makeDefault();
                    CropImageActivity.this.imageView.invalidate();
                    if (CropImageActivity.this.imageView.highlightViews.size() == 1) {
                        CropImageActivity.this.cropView = CropImageActivity.this.imageView.highlightViews.get(0);
                        CropImageActivity.this.cropView.setFocus(true);
                    }
                }
            });
        }
    }

    public void onSaveClicked(File file) {
        int i;
        HighlightView highlightView = this.cropView;
        if (highlightView == null || this.isSaving) {
            return;
        }
        this.isSaving = true;
        Rect scaledCropRect = highlightView.getScaledCropRect(this.sampleSize);
        int width = scaledCropRect.width();
        int height = scaledCropRect.height();
        int i2 = this.maxX;
        if (i2 > 0 && (i = this.maxY) > 0 && (width > i2 || height > i)) {
            float f = width / height;
            int i3 = this.maxX;
            int i4 = this.maxY;
            if (i3 / i4 > f) {
                width = (int) ((i4 * f) + 0.5f);
                height = i4;
            } else {
                height = (int) ((i3 / f) + 0.5f);
                width = i3;
            }
        }
        try {
            Bitmap decodeRegionCrop = decodeRegionCrop(scaledCropRect, width, height);
            if (decodeRegionCrop != null) {
                this.imageView.setImageRotateBitmapResetBase(new RotateBitmap(decodeRegionCrop, this.exifRotation), true);
                this.imageView.center();
                this.imageView.highlightViews.clear();
            }
            saveImage(decodeRegionCrop, file);
        } catch (IllegalArgumentException e) {
            setCropSaveException(e);
        }
    }

    private void saveImage(final Bitmap bitmap, final File file) {
        if (bitmap != null) {
            CropUtil.startBackgroundJob(this, null, getResources().getString(C1656R.string.saving), new Runnable() { // from class: cn.finalteam.galleryfinal.widget.crop.CropImageActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    CropImageActivity.this.saveOutput(bitmap, file);
                }
            }, this.handler);
        }
    }

    private Bitmap decodeRegionCrop(Rect rect, int i, int i2) {
        Bitmap bitmap;
        InputStream inputStream;
        Rect rect2;
        clearImageView();
        InputStream inputStream2 = null;
        try {
            try {
                inputStream = getContentResolver().openInputStream(this.sourceUri);
                try {
                    BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(inputStream, false);
                    int width = newInstance.getWidth();
                    int height = newInstance.getHeight();
                    if (this.exifRotation != 0) {
                        Matrix matrix = new Matrix();
                        matrix.setRotate(-this.exifRotation);
                        RectF rectF = new RectF();
                        matrix.mapRect(rectF, new RectF(rect));
                        rectF.offset(rectF.left < 0.0f ? width : 0.0f, rectF.top < 0.0f ? height : 0.0f);
                        rect2 = new Rect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                    } else {
                        rect2 = rect;
                    }
                    try {
                        Bitmap decodeRegion = newInstance.decodeRegion(rect2, new BitmapFactory.Options());
                        if (decodeRegion != null && (rect2.width() > i || rect2.height() > i2)) {
                            Matrix matrix2 = new Matrix();
                            matrix2.postScale(i / rect2.width(), i2 / rect2.height());
                            decodeRegion = Bitmap.createBitmap(decodeRegion, 0, 0, decodeRegion.getWidth(), decodeRegion.getHeight(), matrix2, true);
                        }
                        CropUtil.closeSilently(inputStream);
                        return decodeRegion;
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Rectangle " + rect2 + " is outside of the image (" + width + "," + height + "," + this.exifRotation + ")", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    bitmap = null;
                    inputStream2 = inputStream;
                    ILogger.m22037e(e);
                    setCropSaveException(e);
                    CropUtil.closeSilently(inputStream2);
                    return bitmap;
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    bitmap = null;
                    inputStream2 = inputStream;
                    ILogger.m22037e(e);
                    setCropSaveException(e);
                    CropUtil.closeSilently(inputStream2);
                    return bitmap;
                } catch (Throwable th) {
                    th = th;
                    CropUtil.closeSilently(inputStream);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                bitmap = null;
            } catch (OutOfMemoryError e5) {
                e = e5;
                bitmap = null;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    private void clearImageView() {
        this.imageView.clear();
        RotateBitmap rotateBitmap = this.rotateBitmap;
        if (rotateBitmap != null) {
            rotateBitmap.recycle();
        }
        System.gc();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.graphics.Bitmap] */
    public void saveOutput(final Bitmap bitmap, File file) {
        if (file != null) {
            ?? r0 = 0;
            r0 = 0;
            try {
                try {
                    r0 = getContentResolver().openOutputStream(Uri.fromFile(file));
                    if (r0 != 0) {
                        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
                        if (!extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("jpeg")) {
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, r0);
                        }
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, r0);
                    }
                } catch (IOException e) {
                    setCropSaveException(e);
                    ILogger.m22037e(e);
                }
                CropUtil.closeSilently(r0);
                r0 = CropUtil.getFromMediaUri(this, getContentResolver(), this.sourceUri);
                CropUtil.copyExifRotation(r0, CropUtil.getFromMediaUri(this, getContentResolver(), Uri.fromFile(file)));
                setCropSaveSuccess(file);
            } catch (Throwable th) {
                CropUtil.closeSilently(r0);
                throw th;
            }
        }
        this.handler.post(new Runnable() { // from class: cn.finalteam.galleryfinal.widget.crop.CropImageActivity.4
            @Override // java.lang.Runnable
            public void run() {
                CropImageActivity.this.imageView.clear();
                bitmap.recycle();
            }
        });
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity, cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        RotateBitmap rotateBitmap = this.rotateBitmap;
        if (rotateBitmap != null) {
            rotateBitmap.recycle();
        }
    }

    public boolean isSaving() {
        return this.isSaving;
    }
}
