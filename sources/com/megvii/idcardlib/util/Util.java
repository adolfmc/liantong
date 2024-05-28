package com.megvii.idcardlib.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.widget.Toast;
import com.megvii.idcardlib.C5301R;
import com.megvii.idcardquality.IDCardQualityResult;
import com.megvii.idcardquality.bean.IDCardAttr;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Util {
    public static Toast toast;

    public static void showToast(Context context, String str) {
        if (context != null) {
            Toast toast2 = toast;
            if (toast2 != null) {
                toast2.cancel();
            }
            toast = Toast.makeText(context, str, 0);
            toast.setGravity(48, 0, 30);
            toast.show();
        }
    }

    public static void cancleToast(Context context) {
        Toast toast2;
        if (context == null || (toast2 = toast) == null) {
            return;
        }
        toast2.cancel();
    }

    public static String getUUIDString(Context context) {
        SharedUtil sharedUtil = new SharedUtil(context);
        String stringValueByKey = sharedUtil.getStringValueByKey("key_uuid");
        if (stringValueByKey == null || stringValueByKey.trim().length() == 0) {
            String encodeToString = Base64.encodeToString(UUID.randomUUID().toString().getBytes(), 0);
            sharedUtil.saveStringValue("key_uuid", encodeToString);
            return encodeToString;
        }
        return stringValueByKey;
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

    public static Camera.Size getNearestRatioSize(Camera.Parameters parameters, final int i, final int i2) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        for (Camera.Size size : supportedPreviewSizes) {
            if (size.width == 1280 && size.height == 720) {
                return size;
            }
        }
        Collections.sort(supportedPreviewSizes, new Comparator<Camera.Size>() { // from class: com.megvii.idcardlib.util.Util.1
            @Override // java.util.Comparator
            public int compare(Camera.Size size2, Camera.Size size3) {
                return ((((int) (Math.abs((size2.width / size2.height) - (i / i2)) * 1000.0f)) << 16) - size2.width) - ((((int) (Math.abs((size3.width / size3.height) - (i / i2)) * 1000.0f)) << 16) - size3.width);
            }
        });
        return supportedPreviewSizes.get(0);
    }

    public static String getTimeStr() {
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    }

    public static void closeStreamSilently(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] bmp2byteArr(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        closeStreamSilently(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static String errorType2HumanStr(IDCardQualityResult.IDCardFailedType iDCardFailedType, IDCardAttr.IDCardSide iDCardSide) {
        switch (iDCardFailedType) {
            case QUALITY_FAILED_TYPE_NOIDCARD:
                return "请将身份证置于提示框内";
            case QUALITY_FAILED_TYPE_BLUR:
                return "请点击屏幕对焦";
            case QUALITY_FAILED_TYPE_BRIGHTNESSTOOHIGH:
                return "太亮";
            case QUALITY_FAILED_TYPE_BRIGHTNESSTOOLOW:
                return "太暗";
            case QUALITY_FAILED_TYPE_OUTSIDETHEROI:
                return "请将身份证与提示框对齐";
            case QUALITY_FAILED_TYPE_SIZETOOLARGE:
                return "请将身份证与提示框对齐";
            case QUALITY_FAILED_TYPE_SIZETOOSMALL:
                return "请将身份证与提示框对齐";
            case QUALITY_FAILED_TYPE_SPECULARHIGHLIGHT:
                return "请调整拍摄位置，以去除光斑";
            case QUALITY_FAILED_TYPE_TILT:
                return "请将身份证摆正";
            case QUALITY_FAILED_TYPE_SHADOW:
                return "请调整拍摄位置，以去除阴影";
            case QUALITY_FAILED_TYPE_WRONGSIDE:
                return iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_BACK ? "请翻到国徽面" : "请翻到人像面";
            default:
                return null;
        }
    }

    public static byte[] readModel(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        InputStream inputStream = null;
        try {
            try {
                try {
                    inputStream = context.getResources().openRawResource(C5301R.C5306raw.idcardmodel);
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
}
