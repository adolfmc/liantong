package com.example.asus.detectionandalign.p193a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.example.asus.detectionandalign.p194b.C4283a;
import com.example.asus.detectionandalign.p194b.p195a.C4286b;
import com.example.landmarksdk.faceDetectionResult;
import com.example.landmarksdk.faceRect;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.example.asus.detectionandalign.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4257a {

    /* renamed from: d */
    private static String f9884d;

    /* renamed from: a */
    public final String f9885a = "BaseAsyncTaskTool";

    /* renamed from: b */
    public boolean f9886b = true;

    /* renamed from: c */
    public boolean f9887c = false;

    /* renamed from: e */
    private Handler f9888e = new Handler() { // from class: com.example.asus.detectionandalign.a.a.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            String str2;
            C4257a c4257a;
            C4257a.this.f9887c = false;
            if (message.what == 888) {
                int i = message.arg1;
                if (i > 45 && i < 135) {
                    str = "BaseAsyncTaskTool";
                    str2 = "横屏翻转: ";
                } else if (i > 135 && i < 225) {
                    Log.e("BaseAsyncTaskTool", "竖屏翻转: ");
                    c4257a = C4257a.this;
                    c4257a.f9887c = true;
                    c4257a.f9886b = false;
                } else if (i > 225 && i < 315) {
                    str = "BaseAsyncTaskTool";
                    str2 = "横屏: ";
                } else if ((i > 315 && i < 360) || (i > 0 && i < 45)) {
                    Log.e("BaseAsyncTaskTool", "竖屏: ");
                    C4257a.this.f9886b = true;
                }
                Log.e(str, str2);
                c4257a = C4257a.this;
                c4257a.f9886b = false;
            }
            super.handleMessage(message);
        }
    };

    /* renamed from: a */
    public static C4286b[] m16236a(float[] fArr) {
        C4286b[] c4286bArr = new C4286b[fArr.length / 2];
        if (fArr == null) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            int i3 = i2 + 1;
            C4286b c4286b = new C4286b(fArr[i2], fArr[i3]);
            C4286b.m15982a(fArr[i2], fArr[i3]);
            c4286bArr[i] = c4286b;
            i++;
        }
        return c4286bArr;
    }

    /* renamed from: a */
    public double m16244a(int i, int i2, int i3, int i4) {
        Point point = new Point(i, i2);
        Point point2 = new Point(i3, i4);
        return Math.atan2(point2.y - point.y, point2.x - point.x) * 57.29577951308232d;
    }

    /* renamed from: a */
    public Bitmap m16237a(byte[] bArr, int i, int i2) {
        new BitmapFactory.Options().inJustDecodeBounds = true;
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        byteArrayOutputStream.reset();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    /* renamed from: a */
    public String m16243a(Context context) {
        return (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir() : context.getCacheDir()).getPath();
    }

    /* renamed from: a */
    public void m16240a(String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    file2.delete();
                }
            }
        }
    }

    /* renamed from: a */
    public void m16239a(String str, String str2, Bitmap bitmap) {
        try {
            File file = new File(str, str2 + ".jpg");
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmap.getByteCount());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Log.e("bitmapCompress_length", String.valueOf(bitmap.getByteCount()));
            Log.e("bitmapCompress：", String.valueOf(bitmap.getByteCount() / 1024) + "kb");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            byteArrayOutputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m16238a(boolean z, Bitmap bitmap, String str) {
        if (z) {
            if (TextUtils.isEmpty(f9884d)) {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                f9884d = absolutePath + "/FailedLiveness";
            }
            try {
                String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
                new DecimalFormat("0.00");
                String str2 = f9884d;
                File file = new File(str2, format + "_" + str + ".jpg");
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmap.getByteCount());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                Log.e("bitmapCompress_length", String.valueOf(bitmap.getByteCount()));
                Log.e("bitmapCompress：", String.valueOf(bitmap.getByteCount() / 1024) + "kb");
                if (bitmap.getByteCount() / 1024 > 100) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                }
                byteArrayOutputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public boolean m16242a(Bitmap bitmap, faceDetectionResult[] facedetectionresultArr) {
        faceRect facerect = facedetectionresultArr[0].rect;
        float[] fArr = facedetectionresultArr[0].raw_facial_points;
        Log.e("BaseAsyncTaskTool", " w:" + bitmap.getWidth() + "  h：" + bitmap.getHeight());
        float width = ((float) bitmap.getWidth()) * 0.8333333f;
        float height = ((float) bitmap.getHeight()) * 0.8333333f;
        float width2 = (((float) bitmap.getWidth()) - width) / 2.0f;
        float height2 = (((float) bitmap.getHeight()) - height) / 2.0f;
        Log.e("BaseAsyncTaskTool", "限制框x：" + width + "  限制框y：" + height);
        Log.e("BaseAsyncTaskTool", "x轴左边界：" + width2 + "  x轴起点：" + facerect.f10107x + "   x轴终点:" + (facerect.f10107x + facerect.width) + "  x轴右边界：" + (bitmap.getWidth() - width2));
        Log.e("BaseAsyncTaskTool", "y轴上边界：" + height2 + "  y轴起点： " + facerect.f10108y + "  y轴终点:" + (facerect.f10108y + facerect.height) + "  y轴下边界：" + (((float) bitmap.getHeight()) - height2));
        return facerect.f10107x > width2 && facerect.f10107x + facerect.width < ((float) bitmap.getWidth()) - width2 && facerect.f10108y > height2 && facerect.f10108y + facerect.height < ((float) bitmap.getHeight()) - height2;
    }

    /* renamed from: a */
    public boolean m16241a(faceDetectionResult facedetectionresult) {
        return Math.abs(m16244a((int) facedetectionresult.raw_facial_points[0], (int) facedetectionresult.raw_facial_points[1], (int) facedetectionresult.raw_facial_points[2], (int) facedetectionresult.raw_facial_points[3])) < 15.0d && !this.f9887c;
    }

    /* renamed from: a */
    public float[] m16235a(C4286b[] c4286bArr) {
        float[] fArr = {C4283a.m15987d(c4286bArr), C4283a.m15986e(c4286bArr), C4283a.m15991a(c4286bArr), C4283a.m15989b(c4286bArr), C4283a.m15988c(c4286bArr)};
        Log.e("checkAction-resultBoolen", ("【眨眼：" + fArr[2] + "】") + ("【张嘴：" + fArr[3] + "】") + ("【抬头：" + fArr[4] + "】") + ("【左转：" + fArr[0] + "】") + ("【右转：" + fArr[1] + "】") + "");
        return fArr;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: b */
    public String m16234b(String str) {
        char c;
        switch (str.hashCode()) {
            case -1221271397:
                if (str.equals("headUp")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 100913:
                if (str.equals("eye")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 99151462:
                if (str.equals("headF")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 99151468:
                if (str.equals("headL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 99151474:
                if (str.equals("headR")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 104086727:
                if (str.equals("mouth")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return "3";
            case 1:
                return "2";
            case 2:
                return "1";
            case 3:
                return "4";
            case 4:
                return "5";
            case 5:
                return "0";
            default:
                return "";
        }
    }
}
