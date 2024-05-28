package com.example.asus.detectionandalign.videocompress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import com.example.asus.detectionandalign.videocompress.videocompression.MediaController;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SiliCompressor {
    private static final String LOG_TAG = "SiliCompressor";
    private static Context mContext;
    static volatile SiliCompressor singleton;
    public static String videoCompressionPath;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Builder {
        private final Context context;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        public SiliCompressor build() {
            return new SiliCompressor(this.context);
        }
    }

    public SiliCompressor(Context context) {
        mContext = context;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int round;
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 > i2 || i4 > i) {
            round = Math.round(i3 / i2);
            int round2 = Math.round(i4 / i);
            if (round >= round2) {
                round = round2;
            }
        } else {
            round = 1;
        }
        while ((i4 * i3) / (round * round) > i * i2 * 2) {
            round++;
        }
        return round;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:1|(2:41|(1:43)(3:44|(1:46)(1:48)|47))|5|6|(2:7|8)|(2:9|10)|11|(2:12|13)|(1:15)(2:27|(1:29)(7:30|(1:32)|17|18|19|20|21))|16|17|18|19|20|21) */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0149, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x014a, code lost:
        r0.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String compressImage(java.lang.String r17, java.io.File r18) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.videocompress.SiliCompressor.compressImage(java.lang.String, java.io.File):java.lang.String");
    }

    private String getFilename(String str, File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + "/IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
    }

    private String getRealPathFromURI(String str) {
        Uri parse = Uri.parse(str);
        Cursor query = mContext.getContentResolver().query(parse, null, null, null, null);
        if (query == null) {
            return parse.getPath();
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        return string;
    }

    @SuppressLint({"NewApi"})
    public static String getRealPathFromURI_API19(Context context, Uri uri) {
        String[] strArr = {"_data"};
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "_id=?", new String[]{DocumentsContract.getDocumentId(uri).split(":")[1]}, null);
        String string = query.moveToFirst() ? query.getString(query.getColumnIndex(strArr[0])) : "";
        query.close();
        return string;
    }

    public static SiliCompressor with(Context context) {
        if (singleton == null) {
            synchronized (SiliCompressor.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    public String compress(int i) {
        Bitmap decodeResource = BitmapFactory.decodeResource(mContext.getApplicationContext().getResources(), i);
        if (decodeResource != null) {
            String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            File createTempFile = File.createTempFile("JPEG_" + format + "_", ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
            decodeResource.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(createTempFile));
            String compressImage = compressImage(Uri.fromFile(createTempFile).toString(), new File(Environment.getExternalStorageDirectory(), "Silicompressor/images"));
            if (createTempFile.exists()) {
                Log.d(LOG_TAG, createTempFile.delete() ? "SourceImage File deleted" : "SourceImage File not deleted");
            }
            return compressImage;
        }
        return null;
    }

    public String compress(String str, File file) {
        return compressImage(str, file);
    }

    public String compress(String str, File file, boolean z) {
        String compressImage = compressImage(str, file);
        if (z) {
            File file2 = new File(getRealPathFromURI(str));
            if (file2.exists()) {
                Log.d(LOG_TAG, file2.delete() ? "SourceImage File deleted" : "SourceImage File not deleted");
            }
        }
        return compressImage;
    }

    public String compressVideo(Uri uri, String str) {
        return compressVideo(uri, str, 0, 0, 0);
    }

    public String compressVideo(Uri uri, String str, int i, int i2, int i3) {
        String str2;
        String str3;
        if (MediaController.getInstance().convertVideo(Util.getFilePath(mContext, uri), new File(str), i, i2, i3)) {
            str2 = LOG_TAG;
            str3 = "Video Conversion Complete";
        } else {
            str2 = LOG_TAG;
            str3 = "Video conversion in progress";
        }
        Log.v(str2, str3);
        return MediaController.cachedFile.getPath();
    }

    public String compressVideo(String str, String str2) {
        return compressVideo(str, str2, 0, 0, 0);
    }

    public String compressVideo(String str, String str2, int i, int i2, int i3) {
        String str3;
        String str4;
        if (MediaController.getInstance().convertVideo(str, new File(str2), i, i2, i3)) {
            str3 = LOG_TAG;
            str4 = "Video Conversion Complete";
        } else {
            str3 = LOG_TAG;
            str4 = "Video conversion in progress";
        }
        Log.v(str3, str4);
        return MediaController.cachedFile.getPath();
    }

    public Bitmap getCompressBitmap(String str) {
        return MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(new File(compressImage(str, new File(Environment.getExternalStorageDirectory(), "Silicompressor/images")))));
    }

    public Bitmap getCompressBitmap(String str, boolean z) {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(new File(compressImage(str, new File(Environment.getExternalStorageDirectory(), "Silicompressor/images")))));
        if (z) {
            File file = new File(getRealPathFromURI(str));
            if (file.exists()) {
                Log.d(LOG_TAG, file.delete() ? "SourceImage File deleted" : "SourceImage File not deleted");
            }
        }
        return bitmap;
    }
}
