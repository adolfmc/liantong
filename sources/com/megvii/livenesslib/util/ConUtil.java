package com.megvii.livenesslib.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.megvii.livenesslib.C5351R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ConUtil {
    public static byte[] readModel(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        InputStream inputStream = null;
        try {
            try {
                try {
                    inputStream = context.getResources().openRawResource(C5351R.C5355raw.model);
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byteArrayOutputStream.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String getUUIDString(Context context) {
        SharedUtil sharedUtil = new SharedUtil(context);
        String stringValueByKey = sharedUtil.getStringValueByKey("key_uuid");
        if (stringValueByKey != null) {
            return stringValueByKey;
        }
        String phoneNumber = getPhoneNumber(context);
        if ((phoneNumber == null || phoneNumber.trim().length() == 0) && (((phoneNumber = getMacAddress(context)) == null || phoneNumber.trim().length() == 0) && ((phoneNumber = getDeviceID(context)) == null || phoneNumber.trim().length() == 0))) {
            phoneNumber = Base64.encodeToString(UUID.randomUUID().toString().getBytes(), 0);
        }
        sharedUtil.saveStringValue("key_uuid", phoneNumber);
        return phoneNumber;
    }

    public static String getPhoneNumber(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
    }

    public static String getDeviceID(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    public static String getMacAddress(Context context) {
        String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        return (macAddress == null || macAddress.length() <= 0) ? macAddress : macAddress.replace(":", "");
    }

    public static byte[] getGrayscale(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] bArr = new byte[bitmap.getWidth() * bitmap.getHeight()];
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int i2 = 0; i2 < bitmap.getWidth(); i2++) {
                int pixel = bitmap.getPixel(i2, i);
                bArr[(bitmap.getWidth() * i) + i2] = (byte) ((((((16711680 & pixel) >> 16) * 299) + (((65280 & pixel) >> 8) * 587)) + ((pixel & 255) * 114)) / 1000);
            }
        }
        return bArr;
    }

    public static int readPictureDegree(String str) {
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
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap rotateImage(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static Bitmap getBitMap(String str, int i) {
        if (i == -1) {
            return BitmapFactory.decodeFile(str);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        double d = i;
        options.inSampleSize = Math.max(1, (int) Math.max(options.outWidth / d, options.outHeight / d));
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap getBitmapConsiderExif(String str) {
        Bitmap bitMap = getBitMap(str, 800);
        if (bitMap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(readPictureDegree(str));
        Bitmap copy = Bitmap.createBitmap(bitMap, 0, 0, bitMap.getWidth(), bitMap.getHeight(), matrix, true).copy(Bitmap.Config.ARGB_8888, true);
        float height = (copy.getHeight() > copy.getWidth() ? copy.getHeight() : copy.getWidth()) / 800.0f;
        return height > 1.0f ? Bitmap.createScaledBitmap(copy, (int) (copy.getWidth() / height), (int) (copy.getHeight() / height), false) : copy;
    }

    public static Bitmap cropImage(RectF rectF, Bitmap bitmap) {
        float width = rectF.width() * 2.0f;
        if (width > bitmap.getWidth()) {
            width = bitmap.getWidth();
        }
        float height = rectF.height() * 2.0f;
        if (height > bitmap.getHeight()) {
            height = bitmap.getHeight();
        }
        float centerX = rectF.centerX() - (width / 2.0f);
        if (centerX < 0.0f) {
            centerX = 0.0f;
        }
        float centerY = rectF.centerY() - (height / 2.0f);
        if (centerY < 0.0f) {
            centerY = 0.0f;
        }
        if (centerX + width > bitmap.getWidth()) {
            width = bitmap.getWidth() - centerX;
        }
        if (centerY + height > bitmap.getHeight()) {
            height = bitmap.getHeight() - centerY;
        }
        return Bitmap.createBitmap(bitmap, (int) centerX, (int) centerY, (int) width, (int) height);
    }

    public static Bitmap cutImage(RectF rectF, String str) {
        return cropImage(rectF, BitmapFactory.decodeFile(str));
    }

    public static File getOutputMediaFile(Context context) {
        File externalFilesDir = context.getExternalFilesDir(Constant.cacheCampareImage);
        if (externalFilesDir.exists() || externalFilesDir.mkdirs()) {
            String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            return new File(externalFilesDir.getPath() + File.separator + "IMG_" + format + ".jpg");
        }
        return null;
    }

    public static void isGoneKeyBoard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        }
    }

    public static void showToast(Context context, String str) {
        if (context != null) {
            Toast makeText = Toast.makeText(context, str, 0);
            makeText.setGravity(48, 0, 30);
            makeText.show();
        }
    }

    public static void showLongToast(Context context, String str) {
        if (context != null) {
            Toast makeText = Toast.makeText(context, str, 1);
            makeText.setGravity(48, 0, 30);
            makeText.show();
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap convert(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Matrix matrix = new Matrix();
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
        }
        Bitmap createBitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        canvas.drawBitmap(createBitmap2, new Rect(0, 0, createBitmap2.getWidth(), createBitmap2.getHeight()), new Rect(0, 0, width, height), (Paint) null);
        return createBitmap;
    }

    public static String saveBitmap(Context context, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        if (bitmap == null) {
            return null;
        }
        File externalFilesDir = context.getExternalFilesDir(Constant.cacheImage);
        if (externalFilesDir.exists() || externalFilesDir.mkdirs()) {
            String str = System.currentTimeMillis() + "";
            try {
                fileOutputStream = new FileOutputStream(externalFilesDir + "/" + str);
            } catch (FileNotFoundException e) {
                e = e;
                fileOutputStream = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
                try {
                    fileOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                throw th;
            }
            try {
                try {
                    if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream)) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        return null;
                    }
                    String str2 = externalFilesDir.getAbsolutePath() + "/" + str;
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                e.printStackTrace();
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                return null;
            }
        }
        return null;
    }

    public static String getFormatterTime(long j) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(j));
    }
}
