package me.shaohui.advancedluban;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class LubanCompresser {
    private static final String TAG = "Luban Compress";
    private ByteArrayOutputStream mByteArrayOutputStream;
    private final LubanBuilder mLuban;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LubanCompresser(LubanBuilder lubanBuilder) {
        this.mLuban = lubanBuilder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<File> singleAction(final File file) {
        return Observable.fromCallable(new Callable<File>() { // from class: me.shaohui.advancedluban.LubanCompresser.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public File call() throws Exception {
                LubanCompresser lubanCompresser = LubanCompresser.this;
                return lubanCompresser.compressImage(lubanCompresser.mLuban.gear, file);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<List<File>> multiAction(List<File> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (final File file : list) {
            arrayList.add(Observable.fromCallable(new Callable<File>() { // from class: me.shaohui.advancedluban.LubanCompresser.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public File call() throws Exception {
                    LubanCompresser lubanCompresser = LubanCompresser.this;
                    return lubanCompresser.compressImage(lubanCompresser.mLuban.gear, file);
                }
            }));
        }
        return Observable.zip(arrayList, new Function<Object[], List<File>>() { // from class: me.shaohui.advancedluban.LubanCompresser.3
            @Override // io.reactivex.functions.Function
            public List<File> apply(Object[] objArr) throws Exception {
                ArrayList arrayList2 = new ArrayList(objArr.length);
                for (Object obj : objArr) {
                    arrayList2.add((File) obj);
                }
                return arrayList2;
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File compressImage(int i, File file) throws IOException {
        if (i != 1) {
            switch (i) {
                case 3:
                    return thirdCompress(file);
                case 4:
                    return customCompress(file);
                default:
                    return file;
            }
        }
        return firstCompress(file);
    }

    private File thirdCompress(@NonNull File file) throws IOException {
        double d;
        String cacheFilePath = getCacheFilePath();
        String absolutePath = file.getAbsolutePath();
        int imageSpinAngle = getImageSpinAngle(absolutePath);
        int i = getImageSize(absolutePath)[0];
        int i2 = getImageSize(absolutePath)[1];
        boolean z = i > i2;
        if (i % 2 == 1) {
            i++;
        }
        if (i2 % 2 == 1) {
            i2++;
        }
        int i3 = i > i2 ? i2 : i;
        int i4 = i > i2 ? i : i2;
        double d2 = i4;
        double d3 = i3 / d2;
        if (d3 > 1.0d || d3 <= 0.5625d) {
            if (d3 <= 0.5625d && d3 > 0.5d) {
                if (i4 < 1280 && file.length() / 1024 < 200) {
                    return file;
                }
                int i5 = i4 / 1280;
                if (i5 == 0) {
                    i5 = 1;
                }
                int i6 = i3 / i5;
                int i7 = i4 / i5;
                d = ((i6 * i7) / 3686400.0d) * 400.0d;
                if (d < 100.0d) {
                    d = 100.0d;
                }
                i2 = i7;
                i = i6;
            } else {
                double d4 = 1280.0d / d3;
                int ceil = (int) Math.ceil(d2 / d4);
                int i8 = i3 / ceil;
                int i9 = i4 / ceil;
                d = ((i8 * i9) / (d4 * 1280.0d)) * 500.0d;
                if (d < 100.0d) {
                    d = 100.0d;
                }
                i2 = i9;
                i = i8;
            }
        } else if (i4 < 1664) {
            if (file.length() / 1024 < 150) {
                return file;
            }
            d = ((i3 * i4) / Math.pow(1664.0d, 2.0d)) * 150.0d;
            if (d < 60.0d) {
                d = 60.0d;
            }
        } else if (i4 >= 1664 && i4 < 4990) {
            i = i3 / 2;
            i2 = i4 / 2;
            d = ((i * i2) / Math.pow(2495.0d, 2.0d)) * 300.0d;
            if (d < 60.0d) {
                d = 60.0d;
            }
        } else if (i4 >= 4990 && i4 < 10240) {
            i = i3 / 4;
            i2 = i4 / 4;
            d = ((i * i2) / Math.pow(2560.0d, 2.0d)) * 300.0d;
            if (d < 100.0d) {
                d = 100.0d;
            }
        } else {
            int i10 = i4 / 1280;
            if (i10 == 0) {
                i10 = 1;
            }
            int i11 = i3 / i10;
            int i12 = i4 / i10;
            d = ((i11 * i12) / Math.pow(2560.0d, 2.0d)) * 300.0d;
            if (d < 100.0d) {
                d = 100.0d;
            }
            i2 = i12;
            i = i11;
        }
        int i13 = z ? i2 : i;
        if (z) {
            i2 = i;
        }
        return compress(absolutePath, cacheFilePath, i13, i2, imageSpinAngle, (long) d);
    }

    private File firstCompress(@NonNull File file) throws IOException {
        int i;
        int i2;
        long j;
        int i3;
        int i4;
        String cacheFilePath = getCacheFilePath();
        String absolutePath = file.getAbsolutePath();
        long length = file.length() / 5;
        int imageSpinAngle = getImageSpinAngle(absolutePath);
        int[] imageSize = getImageSize(absolutePath);
        if (imageSize[0] <= imageSize[1]) {
            double d = imageSize[0] / imageSize[1];
            if (d <= 1.0d && d > 0.5625d) {
                int i5 = imageSize[0] > 1280 ? 1280 : imageSize[0];
                length = 60;
                int i6 = i5;
                i4 = (imageSize[1] * i5) / imageSize[0];
                i3 = i6;
            } else if (d <= 0.5625d) {
                i4 = imageSize[1] > 720 ? 720 : imageSize[1];
                i3 = (imageSize[0] * i4) / imageSize[1];
            } else {
                i3 = 0;
                i4 = 0;
                length = 0;
            }
            long j2 = length;
            i = i3;
            i2 = i4;
            j = j2;
        } else {
            double d2 = imageSize[1] / imageSize[0];
            if (d2 <= 1.0d && d2 > 0.5625d) {
                int i7 = imageSize[1] <= 1280 ? imageSize[1] : 1280;
                i = (imageSize[0] * i7) / imageSize[1];
                j = 60;
                i2 = i7;
            } else if (d2 <= 0.5625d) {
                int i8 = imageSize[0] > 720 ? 720 : imageSize[0];
                i2 = (imageSize[1] * i8) / imageSize[0];
                j = length;
                i = i8;
            } else {
                i = 0;
                i2 = 0;
                j = 0;
            }
        }
        return compress(absolutePath, cacheFilePath, i, i2, imageSpinAngle, j);
    }

    private File customCompress(@NonNull File file) throws IOException {
        String cacheFilePath = getCacheFilePath();
        String absolutePath = file.getAbsolutePath();
        int imageSpinAngle = getImageSpinAngle(absolutePath);
        long length = (this.mLuban.maxSize <= 0 || ((long) this.mLuban.maxSize) >= file.length() / 1024) ? file.length() / 1024 : this.mLuban.maxSize;
        int[] imageSize = getImageSize(absolutePath);
        int i = imageSize[0];
        int i2 = imageSize[1];
        if (this.mLuban.maxSize > 0 && this.mLuban.maxSize < ((float) file.length()) / 1024.0f) {
            float sqrt = (float) Math.sqrt((((float) file.length()) / 1024.0f) / this.mLuban.maxSize);
            i = (int) (i / sqrt);
            i2 = (int) (i2 / sqrt);
        }
        if (this.mLuban.maxWidth > 0) {
            i = Math.min(i, this.mLuban.maxWidth);
        }
        if (this.mLuban.maxHeight > 0) {
            i2 = Math.min(i2, this.mLuban.maxHeight);
        }
        float min = Math.min(i / imageSize[0], i2 / imageSize[1]);
        return (((float) this.mLuban.maxSize) <= ((float) file.length()) / 1024.0f || min != 1.0f) ? compress(absolutePath, cacheFilePath, (int) (imageSize[0] * min), (int) (imageSize[1] * min), imageSpinAngle, length) : file;
    }

    private String getCacheFilePath() {
        StringBuilder sb = new StringBuilder("Luban_" + System.currentTimeMillis());
        if (this.mLuban.compressFormat == Bitmap.CompressFormat.WEBP) {
            sb.append(".webp");
        } else {
            sb.append(".jpg");
        }
        return this.mLuban.cacheDir.getAbsolutePath() + File.separator + ((Object) sb);
    }

    public static int[] getImageSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(str, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    private Bitmap compress(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i3 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        while (true) {
            if (i4 / i3 <= i2 && i5 / i3 <= i) {
                options.inSampleSize = i3;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeFile(str, options);
            }
            i3 *= 2;
        }
    }

    private int getImageSpinAngle(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    if (attributeInt != 8) {
                        return 0;
                    }
                    return SubsamplingScaleImageView.ORIENTATION_270;
                }
                return 90;
            }
            return 180;
        } catch (IOException unused) {
            return 0;
        }
    }

    private File compress(String str, String str2, int i, int i2, int i3, long j) throws IOException {
        return saveImage(str2, rotatingImage(i3, compress(str, i, i2)), j);
    }

    private static Bitmap rotatingImage(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private File saveImage(String str, Bitmap bitmap, long j) throws IOException {
        Preconditions.checkNotNull(bitmap, "Luban Compressbitmap cannot be null");
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (file.exists() || file.mkdirs()) {
            ByteArrayOutputStream byteArrayOutputStream = this.mByteArrayOutputStream;
            if (byteArrayOutputStream == null) {
                this.mByteArrayOutputStream = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
            } else {
                byteArrayOutputStream.reset();
            }
            int i = 100;
            bitmap.compress(this.mLuban.compressFormat, 100, this.mByteArrayOutputStream);
            while (this.mByteArrayOutputStream.size() / 1024 > j && i > 6) {
                this.mByteArrayOutputStream.reset();
                i -= 6;
                bitmap.compress(this.mLuban.compressFormat, i, this.mByteArrayOutputStream);
            }
            bitmap.recycle();
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            this.mByteArrayOutputStream.writeTo(fileOutputStream);
            fileOutputStream.close();
            return new File(str);
        }
        return null;
    }
}
