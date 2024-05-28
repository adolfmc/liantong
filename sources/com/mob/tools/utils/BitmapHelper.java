package com.mob.tools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.opengl.GLES10;
import android.text.TextUtils;
import android.view.View;
import com.mob.commons.C5869r;
import com.mob.commons.C5873u;
import com.mob.tools.MobLog;
import com.mob.tools.network.HttpConnection;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BitmapHelper implements PublicMemberKeeper {

    /* renamed from: a */
    private static int f15059a;

    /* renamed from: b */
    private static int f15060b;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        f15059a = max;
        f15060b = max;
    }

    public static Bitmap getBitmapByCompressSize(String str, int i, int i2) throws Throwable {
        int i3;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        if (i <= 1 || i2 <= 1) {
            i3 = 1;
        } else {
            float f = 1.0f;
            float min = (Math.min(i4, i5) * 1.0f) / Math.min(i, i2);
            float max = (Math.max(i4, i5) * 1.0f) / Math.max(i, i2);
            float f2 = i4 / i5;
            if (f2 > 2.0f || f2 < 0.5d) {
                while (true) {
                    float f3 = f * 2.0f;
                    if (f3 > min) {
                        break;
                    }
                    f = f3;
                }
                i3 = (int) f;
            } else {
                float min2 = Math.min(min, max);
                while (true) {
                    float f4 = f * 2.0f;
                    if (f4 > min2) {
                        break;
                    }
                    f = f4;
                }
                i3 = (int) f;
            }
        }
        if (i3 < 1) {
            i3 = 1;
        }
        while (true) {
            if (i4 / i3 <= f15059a && i5 / i3 <= f15060b) {
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inPreferredConfig = Bitmap.Config.RGB_565;
                options2.inSampleSize = i3;
                return BitmapFactory.decodeFile(str, options2);
            }
            i3++;
        }
    }

    public static Bitmap getBitmapByCompressQuality(String str, int i, int i2, int i3, long j) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Bitmap bitmapByCompressSize;
        try {
            bitmapByCompressSize = getBitmapByCompressSize(str, i, i2);
            if (i3 < 10 || i3 > 100) {
                i3 = 100;
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            Bitmap.CompressFormat bmpFormat = getBmpFormat(str);
            bitmapByCompressSize.compress(bmpFormat, i3, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (j < 10240) {
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                C5873u.m12179a(byteArrayOutputStream);
                return decodeByteArray;
            }
            while (byteArray.length > j && i3 >= 11) {
                byteArrayOutputStream.reset();
                i3 -= 6;
                bitmapByCompressSize.compress(bmpFormat, i3, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
            }
            if (i3 != 100) {
                bitmapByCompressSize = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            C5873u.m12179a(byteArrayOutputStream);
            return bitmapByCompressSize;
        } catch (Throwable th2) {
            th = th2;
            C5873u.m12179a(byteArrayOutputStream);
            throw th;
        }
    }

    public static String saveBitmapByCompress(String str, int i, int i2, int i3) throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        Bitmap bitmapByCompressSize = getBitmapByCompressSize(str, i, i2);
        int i4 = 10;
        if (i3 > 100) {
            i4 = 100;
        } else if (i3 >= 10) {
            i4 = i3;
        }
        Bitmap.CompressFormat bmpFormat = getBmpFormat(str);
        String str2 = bmpFormat == Bitmap.CompressFormat.PNG ? ".png" : ".jpg";
        File file = new File(new File(str).getParent(), String.valueOf(System.currentTimeMillis()) + str2);
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th2) {
            fileOutputStream = null;
            th = th2;
        }
        try {
            bitmapByCompressSize.compress(bmpFormat, i4, fileOutputStream);
            fileOutputStream.flush();
            C5873u.m12179a(fileOutputStream);
            return file.getAbsolutePath();
        } catch (Throwable th3) {
            th = th3;
            C5873u.m12179a(fileOutputStream);
            throw th;
        }
    }

    public static Bitmap getBitmap(String str, int i) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getBitmap(new File(str), i);
    }

    public static Bitmap getBitmap(File file, int i) throws Throwable {
        FileInputStream fileInputStream = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                Bitmap bitmap = getBitmap(fileInputStream2, i);
                C5873u.m12179a(fileInputStream2);
                return bitmap;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                C5873u.m12179a(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static Bitmap getBitmap(InputStream inputStream, int i) {
        if (inputStream == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public static Bitmap getBitmap(String str) throws Throwable {
        return getBitmap(str, 1);
    }

    public static Bitmap getBitmap(Context context, String str) throws Throwable {
        return getBitmap(downloadBitmap(context, str));
    }

    public static String downloadBitmap(Context context, final String str) throws Throwable {
        final String cachePath = ResHelper.getCachePath(context, "images");
        File file = new File(cachePath, Data.MD5(str));
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        final HashMap hashMap = new HashMap();
        new NetworkHelper().rawGet(str, new HttpResponseCallback() { // from class: com.mob.tools.utils.BitmapHelper.1
            @Override // com.mob.tools.network.HttpResponseCallback
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                BufferedReader bufferedReader;
                InputStreamReader inputStreamReader;
                StringBuilder sb;
                FileOutputStream fileOutputStream;
                int responseCode = httpConnection.getResponseCode();
                FilterInputStream filterInputStream = null;
                if (responseCode == 200) {
                    String m11288b = BitmapHelper.m11288b(httpConnection, str);
                    File file2 = new File(cachePath, m11288b);
                    if (!file2.getParentFile().exists()) {
                        file2.getParentFile().mkdirs();
                    }
                    if (file2.exists()) {
                        file2.delete();
                    }
                    try {
                        FilterInputStream filterInputStream2 = new FilterInputStream(httpConnection.getInputStream()) { // from class: com.mob.tools.utils.BitmapHelper.1.1
                            @Override // java.io.FilterInputStream, java.io.InputStream
                            public long skip(long j) throws IOException {
                                long j2 = 0;
                                while (j2 < j) {
                                    long skip = this.in.skip(j - j2);
                                    if (skip == 0) {
                                        break;
                                    }
                                    j2 += skip;
                                }
                                return j2;
                            }
                        };
                        try {
                            Bitmap bitmap = BitmapHelper.getBitmap(filterInputStream2, 1);
                            if (bitmap == null || bitmap.isRecycled()) {
                                return;
                            }
                            try {
                                fileOutputStream = new FileOutputStream(file2);
                            } catch (Throwable th) {
                                th = th;
                                fileOutputStream = null;
                            }
                            try {
                                if (!m11288b.toLowerCase().endsWith(".gif") && !m11288b.toLowerCase().endsWith(".png")) {
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                                    fileOutputStream.flush();
                                    hashMap.put("bitmap", file2.getAbsolutePath());
                                    C5873u.m12179a(fileOutputStream);
                                }
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                                fileOutputStream.flush();
                                hashMap.put("bitmap", file2.getAbsolutePath());
                                C5873u.m12179a(fileOutputStream);
                            } catch (Throwable th2) {
                                th = th2;
                                C5873u.m12179a(fileOutputStream);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            filterInputStream = filterInputStream2;
                            C5873u.m12179a(filterInputStream);
                            if (file2.exists()) {
                                file2.delete();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } else {
                    try {
                        sb = new StringBuilder();
                        inputStreamReader = new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8"));
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                        } catch (Throwable th5) {
                            th = th5;
                            bufferedReader = null;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        bufferedReader = null;
                        inputStreamReader = null;
                    }
                    try {
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            if (sb.length() > 0) {
                                sb.append('\n');
                            }
                            sb.append(readLine);
                        }
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(C5869r.m12200a("005f=djdjeddj"), sb.toString());
                        hashMap2.put(C5869r.m12200a("0066fh3idiIdgfh"), Integer.valueOf(responseCode));
                        throw new Throwable(HashonHelper.fromHashMap(hashMap2));
                    } catch (Throwable th7) {
                        th = th7;
                        C5873u.m12179a(bufferedReader, inputStreamReader);
                        throw th;
                    }
                }
            }
        }, (NetworkHelper.NetworkTimeOut) null);
        return (String) hashMap.get("bitmap");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m11288b(HttpConnection httpConnection, String str) throws Throwable {
        String str2;
        List<String> list;
        int lastIndexOf;
        List<String> list2;
        String[] split;
        Map<String, List<String>> headerFields = httpConnection.getHeaderFields();
        if (headerFields == null || (list2 = headerFields.get("Content-Disposition")) == null || list2.size() <= 0) {
            str2 = null;
        } else {
            str2 = null;
            for (String str3 : list2.get(0).split(";")) {
                if (str3.trim().startsWith("filename")) {
                    String[] split2 = str3.split("=");
                    if (split2.length >= 2) {
                        str2 = split2[1];
                        if (!TextUtils.isEmpty(str2) && str2.startsWith("\"") && str2.endsWith("\"")) {
                            str2 = str2.substring(1, str2.length() - 1);
                        }
                    }
                }
            }
        }
        if (str2 == null) {
            String MD5 = Data.MD5(str);
            if (headerFields == null || (list = headerFields.get("Content-Type")) == null || list.size() <= 0) {
                return MD5;
            }
            String str4 = list.get(0);
            String trim = str4 == null ? "" : str4.trim();
            if (trim.startsWith("image/")) {
                String substring = trim.substring(6);
                StringBuilder sb = new StringBuilder();
                sb.append(MD5);
                sb.append(".");
                if ("jpeg".equals(substring)) {
                    substring = "jpg";
                }
                sb.append(substring);
                return sb.toString();
            }
            int lastIndexOf2 = str.lastIndexOf(47);
            String substring2 = lastIndexOf2 > 0 ? str.substring(lastIndexOf2 + 1) : null;
            if (substring2 == null || substring2.length() <= 0 || (lastIndexOf = substring2.lastIndexOf(46)) <= 0 || substring2.length() - lastIndexOf >= 10) {
                return MD5;
            }
            return MD5 + substring2.substring(lastIndexOf);
        }
        return str2;
    }

    public static String saveViewToImage(View view) throws Throwable {
        if (view == null) {
            return null;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        return saveViewToImage(view, width, height);
    }

    public static String saveViewToImage(View view, int i, int i2) throws Throwable {
        FileOutputStream fileOutputStream;
        Bitmap captureView = captureView(view, i, i2);
        if (captureView == null || captureView.isRecycled()) {
            return null;
        }
        File file = new File(ResHelper.getCachePath(view.getContext(), "screenshot"), String.valueOf(System.currentTimeMillis()) + ".jpg");
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            captureView.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            C5873u.m12179a(fileOutputStream);
            return file.getAbsolutePath();
        } catch (Throwable th2) {
            th = th2;
            C5873u.m12179a(fileOutputStream);
            throw th;
        }
    }

    public static Bitmap captureView(View view, int i, int i2) throws Throwable {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap blur(Bitmap bitmap, int i, int i2) {
        float f = i2;
        Bitmap createBitmap = Bitmap.createBitmap((int) ((bitmap.getWidth() / f) + 0.5f), (int) ((bitmap.getHeight() / f) + 0.5f), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f2 = 1.0f / f;
        canvas.scale(f2, f2);
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        m11292a(createBitmap, (int) ((i / f) + 0.5f), true);
        return createBitmap;
    }

    /* renamed from: a */
    private static Bitmap m11292a(Bitmap bitmap, int i, boolean z) {
        int i2;
        int[] iArr;
        int i3 = i;
        Bitmap copy = z ? bitmap : bitmap.copy(bitmap.getConfig(), true);
        if (i3 < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i4 = width * height;
        int[] iArr2 = new int[i4];
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i5 = width - 1;
        int i6 = height - 1;
        int i7 = i3 + i3 + 1;
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[i4];
        int[] iArr6 = new int[Math.max(width, height)];
        int i8 = (i7 + 1) >> 1;
        int i9 = i8 * i8;
        int i10 = i9 * 256;
        int[] iArr7 = new int[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            iArr7[i11] = i11 / i9;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, i7, 3);
        int i12 = i3 + 1;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < height) {
            Bitmap bitmap2 = copy;
            int i16 = -i3;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            while (i16 <= i3) {
                int i26 = i6;
                int i27 = height;
                int i28 = iArr2[i14 + Math.min(i5, Math.max(i16, 0))];
                int[] iArr9 = iArr8[i16 + i3];
                iArr9[0] = (i28 & 16711680) >> 16;
                iArr9[1] = (i28 & 65280) >> 8;
                iArr9[2] = i28 & 255;
                int abs = i12 - Math.abs(i16);
                i17 += iArr9[0] * abs;
                i18 += iArr9[1] * abs;
                i19 += iArr9[2] * abs;
                if (i16 > 0) {
                    i23 += iArr9[0];
                    i24 += iArr9[1];
                    i25 += iArr9[2];
                } else {
                    i20 += iArr9[0];
                    i21 += iArr9[1];
                    i22 += iArr9[2];
                }
                i16++;
                height = i27;
                i6 = i26;
            }
            int i29 = i6;
            int i30 = height;
            int i31 = i3;
            int i32 = 0;
            while (i32 < width) {
                iArr3[i14] = iArr7[i17];
                iArr4[i14] = iArr7[i18];
                iArr5[i14] = iArr7[i19];
                int i33 = i17 - i20;
                int i34 = i18 - i21;
                int i35 = i19 - i22;
                int[] iArr10 = iArr8[((i31 - i3) + i7) % i7];
                int i36 = i20 - iArr10[0];
                int i37 = i21 - iArr10[1];
                int i38 = i22 - iArr10[2];
                if (i13 == 0) {
                    iArr = iArr7;
                    iArr6[i32] = Math.min(i32 + i3 + 1, i5);
                } else {
                    iArr = iArr7;
                }
                int i39 = iArr2[i15 + iArr6[i32]];
                iArr10[0] = (i39 & 16711680) >> 16;
                iArr10[1] = (i39 & 65280) >> 8;
                iArr10[2] = i39 & 255;
                int i40 = i23 + iArr10[0];
                int i41 = i24 + iArr10[1];
                int i42 = i25 + iArr10[2];
                i17 = i33 + i40;
                i18 = i34 + i41;
                i19 = i35 + i42;
                i31 = (i31 + 1) % i7;
                int[] iArr11 = iArr8[i31 % i7];
                i20 = i36 + iArr11[0];
                i21 = i37 + iArr11[1];
                i22 = i38 + iArr11[2];
                i23 = i40 - iArr11[0];
                i24 = i41 - iArr11[1];
                i25 = i42 - iArr11[2];
                i14++;
                i32++;
                iArr7 = iArr;
            }
            i15 += width;
            i13++;
            copy = bitmap2;
            height = i30;
            i6 = i29;
        }
        Bitmap bitmap3 = copy;
        int i43 = i6;
        int i44 = height;
        int[] iArr12 = iArr7;
        int i45 = 0;
        while (i45 < width) {
            int i46 = -i3;
            int i47 = i46 * width;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            int i56 = 0;
            while (i46 <= i3) {
                int[] iArr13 = iArr6;
                int max = Math.max(0, i47) + i45;
                int[] iArr14 = iArr8[i46 + i3];
                iArr14[0] = iArr3[max];
                iArr14[1] = iArr4[max];
                iArr14[2] = iArr5[max];
                int abs2 = i12 - Math.abs(i46);
                i48 += iArr3[max] * abs2;
                i49 += iArr4[max] * abs2;
                i50 += iArr5[max] * abs2;
                if (i46 > 0) {
                    i54 += iArr14[0];
                    i55 += iArr14[1];
                    i56 += iArr14[2];
                    i2 = i43;
                } else {
                    i51 += iArr14[0];
                    i52 += iArr14[1];
                    i53 += iArr14[2];
                    i2 = i43;
                }
                if (i46 < i2) {
                    i47 += width;
                }
                i46++;
                i43 = i2;
                iArr6 = iArr13;
            }
            int[] iArr15 = iArr6;
            int i57 = i43;
            int i58 = i45;
            int i59 = i55;
            int i60 = i56;
            int i61 = 0;
            int i62 = i3;
            int i63 = i54;
            int i64 = i53;
            int i65 = i52;
            int i66 = i51;
            int i67 = i50;
            int i68 = i49;
            int i69 = i48;
            int i70 = i44;
            while (i61 < i70) {
                iArr2[i58] = (iArr2[i58] & (-16777216)) | (iArr12[i69] << 16) | (iArr12[i68] << 8) | iArr12[i67];
                int i71 = i69 - i66;
                int i72 = i68 - i65;
                int i73 = i67 - i64;
                int[] iArr16 = iArr8[((i62 - i3) + i7) % i7];
                int i74 = i66 - iArr16[0];
                int i75 = i65 - iArr16[1];
                int i76 = i64 - iArr16[2];
                if (i45 == 0) {
                    iArr15[i61] = Math.min(i61 + i12, i57) * width;
                }
                int i77 = iArr15[i61] + i45;
                iArr16[0] = iArr3[i77];
                iArr16[1] = iArr4[i77];
                iArr16[2] = iArr5[i77];
                int i78 = i63 + iArr16[0];
                int i79 = i59 + iArr16[1];
                int i80 = i60 + iArr16[2];
                i69 = i71 + i78;
                i68 = i72 + i79;
                i67 = i73 + i80;
                i62 = (i62 + 1) % i7;
                int[] iArr17 = iArr8[i62];
                i66 = i74 + iArr17[0];
                i65 = i75 + iArr17[1];
                i64 = i76 + iArr17[2];
                i63 = i78 - iArr17[0];
                i59 = i79 - iArr17[1];
                i60 = i80 - iArr17[2];
                i58 += width;
                i61++;
                i3 = i;
            }
            i45++;
            i43 = i57;
            i44 = i70;
            iArr6 = iArr15;
            i3 = i;
        }
        bitmap3.setPixels(iArr2, 0, width, 0, 0, width, i44);
        return bitmap3;
    }

    public static Bitmap roundBitmap(Bitmap bitmap, int i, int i2, float f, float f2, float f3, float f4) throws Throwable {
        Bitmap createBitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect rect = new Rect(0, 0, width, height);
        if (width != i || height != i2) {
            createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        } else {
            createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect2 = new Rect(0, 0, i, i2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        float[] fArr = {f, f, f2, f2, f3, f3, f4, f4};
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, new RectF(0.0f, 0.0f, 0.0f, 0.0f), fArr));
        shapeDrawable.setBounds(rect2);
        shapeDrawable.draw(canvas);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }

    public static int[] fixRect(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (iArr[0] / iArr[1] > iArr2[0] / iArr2[1]) {
            iArr3[0] = iArr2[0];
            iArr3[1] = (int) (((iArr[1] * iArr2[0]) / iArr[0]) + 0.5f);
        } else {
            iArr3[1] = iArr2[1];
            iArr3[0] = (int) (((iArr[0] * iArr2[1]) / iArr[1]) + 0.5f);
        }
        return iArr3;
    }

    public static int[] fixRect_2(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (iArr[0] / iArr[1] > iArr2[0] / iArr2[1]) {
            iArr3[1] = iArr2[1];
            iArr3[0] = (int) (((iArr[0] * iArr2[1]) / iArr[1]) + 0.5f);
        } else {
            iArr3[0] = iArr2[0];
            iArr3[1] = (int) (((iArr[1] * iArr2[0]) / iArr[0]) + 0.5f);
        }
        return iArr3;
    }

    public static String saveBitmap(Context context, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        File file = new File(ResHelper.getCachePath(context, "images"), String.valueOf(System.currentTimeMillis()) + (compressFormat == Bitmap.CompressFormat.PNG ? ".png" : ".jpg"));
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th2) {
            fileOutputStream = null;
            th = th2;
        }
        try {
            bitmap.compress(compressFormat, i, fileOutputStream);
            fileOutputStream.flush();
            C5873u.m12179a(fileOutputStream);
            return file.getAbsolutePath();
        } catch (Throwable th3) {
            th = th3;
            C5873u.m12179a(fileOutputStream);
            throw th;
        }
    }

    public static String saveBitmap(Context context, Bitmap bitmap) throws Throwable {
        return saveBitmap(context, bitmap, Bitmap.CompressFormat.JPEG, 80);
    }

    public static Bitmap.CompressFormat getBmpFormat(byte[] bArr) {
        String m11290a = m11290a(bArr);
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        return m11290a != null ? (m11290a.endsWith("png") || m11290a.endsWith("gif")) ? Bitmap.CompressFormat.PNG : compressFormat : compressFormat;
    }

    public static Bitmap.CompressFormat getBmpFormat(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.endsWith("png") || lowerCase.endsWith("gif")) {
            return Bitmap.CompressFormat.PNG;
        }
        if (lowerCase.endsWith("jpg") || lowerCase.endsWith("jpeg") || lowerCase.endsWith("bmp") || lowerCase.endsWith("tif")) {
            return Bitmap.CompressFormat.JPEG;
        }
        String mime = getMime(str);
        if (mime.endsWith("png") || mime.endsWith("gif")) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public static String getMime(String str) {
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(str);
                try {
                    byte[] bArr = new byte[8];
                    fileInputStream2.read(bArr);
                    String m11290a = m11290a(bArr);
                    C5873u.m12179a(fileInputStream2);
                    return m11290a;
                } catch (Exception e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    MobLog.getInstance().m11325w(e);
                    C5873u.m12179a(fileInputStream);
                    return "";
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    C5873u.m12179a(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    private static String m11290a(byte[] bArr) {
        byte[] bArr2 = {-1, -40, -1, -31};
        if (m11289a(bArr, new byte[]{-1, -40, -1, -32}) || m11289a(bArr, bArr2)) {
            return "jpg";
        }
        if (m11289a(bArr, new byte[]{-119, 80, 78, 71})) {
            return "png";
        }
        if (m11289a(bArr, "GIF".getBytes())) {
            return "gif";
        }
        if (m11289a(bArr, "BM".getBytes())) {
            return "bmp";
        }
        return (m11289a(bArr, new byte[]{73, 73, 42}) || m11289a(bArr, new byte[]{77, 77, 42})) ? "tif" : "";
    }

    /* renamed from: a */
    private static boolean m11289a(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static Bitmap cropBitmap(Bitmap bitmap, int i, int i2, int i3, int i4) {
        int width = (bitmap.getWidth() - i) - i3;
        int height = (bitmap.getHeight() - i2) - i4;
        if (width == bitmap.getWidth() && height == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, -i, -i2, new Paint());
        return createBitmap;
    }

    public static boolean isBlackBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return true;
        }
        int[] iArr = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= iArr.length) {
                break;
            } else if ((iArr[i] & 16777215) != 0) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        return !z;
    }

    public static int mixAlpha(int i, int i2) {
        int i3 = i >>> 24;
        int i4 = 255 - i3;
        return ((((((i & 16711680) >>> 16) * i3) + (((16711680 & i2) >>> 16) * i4)) / 255) << 16) | (-16777216) | ((((((i & 65280) >>> 8) * i3) + (((65280 & i2) >>> 8) * i4)) / 255) << 8) | (((i3 * (i & 255)) + (i4 * (i2 & 255))) / 255);
    }

    public static Bitmap scaleBitmapByHeight(Context context, int i, int i2) throws Throwable {
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        boolean z = i2 != decodeResource.getHeight();
        Bitmap scaleBitmapByHeight = scaleBitmapByHeight(decodeResource, i2);
        if (z) {
            decodeResource.recycle();
        }
        return scaleBitmapByHeight;
    }

    public static Bitmap scaleBitmapByHeight(Bitmap bitmap, int i) {
        return Bitmap.createScaledBitmap(bitmap, (bitmap.getWidth() * i) / bitmap.getHeight(), i, true);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, int i) {
        return compressByQuality(bitmap, i, false);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, int i, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        if (m11293a(bitmap)) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (z && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            C5873u.m12179a(byteArrayOutputStream);
            return decodeByteArray;
        } catch (Throwable th2) {
            th = th2;
            C5873u.m12179a(byteArrayOutputStream);
            throw th;
        }
    }

    public static Bitmap compressByQuality(Bitmap bitmap, long j) {
        return compressByQuality(bitmap, j, false);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, long j, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] byteArray;
        if (m11293a(bitmap) || j <= 0) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            int i = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            if (byteArrayOutputStream.size() <= j) {
                byteArray = byteArrayOutputStream.toByteArray();
            } else {
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArrayOutputStream);
                if (byteArrayOutputStream.size() >= j) {
                    byteArray = byteArrayOutputStream.toByteArray();
                } else {
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < i) {
                        i3 = (i2 + i) / 2;
                        byteArrayOutputStream.reset();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
                        int i4 = (byteArrayOutputStream.size() > j ? 1 : (byteArrayOutputStream.size() == j ? 0 : -1));
                        if (i4 == 0) {
                            break;
                        } else if (i4 > 0) {
                            i = i3 - 1;
                        } else {
                            i2 = i3 + 1;
                        }
                    }
                    if (i == i3 - 1) {
                        byteArrayOutputStream.reset();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                    }
                    byteArray = byteArrayOutputStream.toByteArray();
                }
            }
            if (z && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            C5873u.m12179a(byteArrayOutputStream);
            return decodeByteArray;
        } catch (Throwable th2) {
            th = th2;
            C5873u.m12179a(byteArrayOutputStream);
            throw th;
        }
    }

    /* renamed from: a */
    private static boolean m11293a(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        return save(bitmap, FileUtils.getFileByPath(str), compressFormat, false);
    }

    public static boolean save(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, boolean z) {
        boolean z2;
        Closeable[] closeableArr;
        if (m11293a(bitmap) || !FileUtils.createFileByDeleteOldFile(file)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
            try {
                z2 = bitmap.compress(compressFormat, 100, bufferedOutputStream2);
                if (z) {
                    try {
                        if (!bitmap.isRecycled()) {
                            bitmap.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        try {
                            MobLog.getInstance().m11341d(th);
                            closeableArr = new Closeable[]{bufferedOutputStream};
                            C5873u.m12179a(closeableArr);
                            return z2;
                        } catch (Throwable th2) {
                            C5873u.m12179a(bufferedOutputStream);
                            throw th2;
                        }
                    }
                }
                closeableArr = new Closeable[]{bufferedOutputStream2};
            } catch (Throwable th3) {
                th = th3;
                z2 = false;
            }
        } catch (Throwable th4) {
            th = th4;
            z2 = false;
        }
        C5873u.m12179a(closeableArr);
        return z2;
    }
}
