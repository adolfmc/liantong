package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.mob.commons.C5868q;
import com.mob.commons.C5873u;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.C6152DH;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ResHelper implements PublicMemberKeeper {

    /* renamed from: a */
    private static float f15265a;

    /* renamed from: b */
    private static int f15266b;

    /* renamed from: c */
    private static Uri f15267c;

    public static int getLayoutRes(Context context, String str) {
        return getResId(context, C5868q.m12203b("006fcTcjdccfTh"), str);
    }

    public static int getIdRes(Context context, String str) {
        return getResId(context, C5868q.m12203b("002Xchcb"), str);
    }

    public static int getStringRes(Context context, String str) {
        return getResId(context, "string", str);
    }

    public static int getStringArrayRes(Context context, String str) {
        return getResId(context, "array", str);
    }

    public static int getBitmapRes(Context context, String str) {
        int resId = getResId(context, "drawable", str);
        return resId <= 0 ? getResId(context, "mipmap", str) : resId;
    }

    public static int getStyleRes(Context context, String str) {
        return getResId(context, "style", str);
    }

    public static int getColorRes(Context context, String str) {
        return getResId(context, "color", str);
    }

    public static int getRawRes(Context context, String str) {
        return getResId(context, "raw", str);
    }

    public static int getAnimRes(Context context, String str) {
        return getResId(context, "anim", str);
    }

    public static int[] getStyleableRes(Context context, String str) {
        try {
            Object staticField = ReflectHelper.getStaticField(ReflectHelper.importClass(context.getPackageName() + ".R$styleable"), str);
            if (staticField == null) {
                return new int[0];
            }
            return staticField.getClass().isArray() ? (int[]) staticField : new int[]{((Integer) staticField).intValue()};
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return new int[0];
        }
    }

    public static int getResId(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return 0;
        }
        int identifier = context.getResources().getIdentifier(str2, str, packageName);
        if (identifier <= 0) {
            identifier = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
        }
        if (identifier <= 0) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11326w("failed to parse " + str + " resource \"" + str2 + "\"");
        }
        return identifier;
    }

    public static int designToDevice(Context context, int i, int i2) {
        if (f15266b == 0) {
            int[] screenSize = getScreenSize(context);
            f15266b = screenSize[0] < screenSize[1] ? screenSize[0] : screenSize[1];
        }
        return (int) (((i2 * f15266b) / i) + 0.5f);
    }

    public static int designToDevice(Context context, float f, int i) {
        if (f15265a <= 0.0f) {
            f15265a = context.getResources().getDisplayMetrics().density;
        }
        return (int) (((i * f15265a) / f) + 0.5f);
    }

    public static int dipToPx(Context context, int i) {
        if (f15265a <= 0.0f) {
            f15265a = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((i * f15265a) + 0.5f);
    }

    public static int pxToDip(Context context, int i) {
        if (f15265a <= 0.0f) {
            f15265a = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((i / f15265a) + 0.5f);
    }

    public static int getDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static float[] getDensityXYDpi(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new float[]{displayMetrics.xdpi, displayMetrics.ydpi};
    }

    public static float getDensity(Context context) {
        if (f15265a <= 0.0f) {
            f15265a = context.getResources().getDisplayMetrics().density;
        }
        return f15265a;
    }

    public static int[] getScreenSize(Context context) {
        WindowManager windowManager;
        Display display = null;
        try {
            windowManager = (WindowManager) C6152DH.SyncMtd.getSystemServiceSafe("window");
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            windowManager = null;
        }
        if (windowManager == null) {
            return new int[]{0, 0};
        }
        try {
            display = windowManager.getDefaultDisplay();
        } catch (Throwable th2) {
            MobLog.getInstance().m11325w(th2);
        }
        if (display == null) {
            try {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
            } catch (Throwable th3) {
                MobLog.getInstance().m11325w(th3);
                return new int[]{0, 0};
            }
        } else if (Build.VERSION.SDK_INT < 13) {
            try {
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                display.getMetrics(displayMetrics2);
                return new int[]{displayMetrics2.widthPixels, displayMetrics2.heightPixels};
            } catch (Throwable th4) {
                MobLog.getInstance().m11325w(th4);
                return new int[]{0, 0};
            }
        } else {
            try {
                Point point = new Point();
                Method method = display.getClass().getMethod(C5868q.m12203b("011?dd4eh7fgJecf7dichfbGe"), Point.class);
                method.setAccessible(true);
                method.invoke(display, point);
                return new int[]{point.x, point.y};
            } catch (Throwable th5) {
                MobLog.getInstance().m11325w(th5);
                return new int[]{0, 0};
            }
        }
    }

    public static int getScreenWidth(Context context) {
        return getScreenSize(context)[0];
    }

    public static int getScreenHeight(Context context) {
        return getScreenSize(context)[1];
    }

    public static double getScreenInch(Context context) {
        try {
            int screenWidth = getScreenWidth(context);
            int screenHeight = getScreenHeight(context);
            float[] densityXYDpi = getDensityXYDpi(context);
            if (densityXYDpi == null || densityXYDpi.length != 2) {
                return 0.0d;
            }
            double d = screenWidth / densityXYDpi[0];
            double d2 = screenHeight / densityXYDpi[1];
            return new BigDecimal(Math.sqrt((d * d) + (d2 * d2))).setScale(1, 4).doubleValue();
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return 0.0d;
        }
    }

    public static int getScreenPpi(Context context) {
        try {
            int screenWidth = getScreenWidth(context);
            int screenHeight = getScreenHeight(context);
            return (int) Math.round(Math.sqrt((screenWidth * screenWidth) + (screenHeight * screenHeight)) / getScreenInch(context));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return 0;
        }
    }

    public static File getCacheRootFile(Context context, String str) {
        try {
            String cacheRoot = getCacheRoot(context);
            if (cacheRoot != null) {
                File file = new File(cacheRoot, str);
                if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
                    file.getParentFile().delete();
                    file.getParentFile().mkdirs();
                }
                return file;
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    public static String getCacheRoot(Context context) {
        return getCacheRoot(context, false);
    }

    public static String getCacheRoot(Context context, boolean z) {
        String dataCache;
        if (z) {
            dataCache = null;
        } else {
            try {
                dataCache = getDataCache(context);
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
                return null;
            }
        }
        String sandboxPath = C6152DH.SyncMtd.getSandboxPath();
        if (sandboxPath != null) {
            dataCache = sandboxPath + C5868q.m12203b("001k") + "MobSDK";
        }
        if (TextUtils.isEmpty(dataCache)) {
            return null;
        }
        File file = new File(dataCache);
        if (!file.exists() || !file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
        return dataCache;
    }

    public static String getDataCache(Context context) {
        String str = context.getFilesDir().getAbsolutePath() + C5868q.m12203b("001k") + "MobSDK";
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
        return str;
    }

    public static File getDataCacheFile(Context context, String str) {
        return getDataCacheFile(context, str, false);
    }

    public static File getDataCacheFile(Context context, String str, boolean z) {
        File file = new File(getDataCache(context), str);
        if (z && !file.exists()) {
            try {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return file;
    }

    public static String getCachePath(Context context, String str) {
        String str2 = context.getFilesDir().getAbsolutePath() + C5868q.m12203b("001k") + "MobSDK" + C5868q.m12203b("007kbcbgek");
        try {
            String sandboxPath = C6152DH.SyncMtd.getSandboxPath();
            if (sandboxPath != null) {
                str2 = sandboxPath + C5868q.m12203b("001k") + "MobSDK" + C5868q.m12203b("001k") + C6152DH.SyncMtd.getPackageName() + C5868q.m12203b("007kbcbgek");
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + str + C5868q.m12203b("001k");
        }
        File file = new File(str2);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return str2;
    }

    public static void deleteFileAndFolder(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
            return;
        }
        String[] list = file.list();
        if (list == null || list.length <= 0) {
            file.delete();
            return;
        }
        for (String str : list) {
            File file2 = new File(file, str);
            if (file2.isDirectory()) {
                deleteFileAndFolder(file2);
            } else {
                file2.delete();
            }
        }
        file.delete();
    }

    @Deprecated
    public static int parseInt(String str) throws Throwable {
        return parseInt(str, 10);
    }

    @Deprecated
    public static int parseInt(String str, int i) throws Throwable {
        return Integer.parseInt(str, i);
    }

    @Deprecated
    public static long parseLong(String str) throws Throwable {
        return parseLong(str, 10);
    }

    @Deprecated
    public static long parseLong(String str, int i) throws Throwable {
        return Long.parseLong(str, i);
    }

    public static <T> T forceCast(Object obj) {
        return (T) forceCast(obj, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T forceCast(Object obj, T t) {
        if (obj != 0) {
            try {
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    if (t instanceof Long) {
                        return (T) Long.valueOf(intValue);
                    }
                }
                return obj;
            } catch (Throwable unused) {
            }
        }
        return t;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static boolean copyFile(java.lang.String r5, java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r1 = 0
            if (r0 != 0) goto L52
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto Le
            goto L52
        Le:
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L1a
            return r1
        L1a:
            r0 = 0
            r2 = 2
            r3 = 1
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L47
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L47
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L48
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L48
            copyFile(r4, r5)     // Catch: java.lang.Throwable -> L37
            java.io.Closeable[] r6 = new java.io.Closeable[r2]
            r6[r1] = r4
            r6[r3] = r5
            com.mob.commons.C5873u.m12179a(r6)
            return r3
        L34:
            r6 = move-exception
            r0 = r5
            goto L3d
        L37:
            r0 = r5
            goto L48
        L39:
            r6 = move-exception
            goto L3d
        L3b:
            r6 = move-exception
            r4 = r0
        L3d:
            java.io.Closeable[] r5 = new java.io.Closeable[r2]
            r5[r1] = r4
            r5[r3] = r0
            com.mob.commons.C5873u.m12179a(r5)
            throw r6
        L47:
            r4 = r0
        L48:
            java.io.Closeable[] r5 = new java.io.Closeable[r2]
            r5[r1] = r4
            r5[r3] = r0
            com.mob.commons.C5873u.m12179a(r5)
            return r1
        L52:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.copyFile(java.lang.String, java.lang.String):boolean");
    }

    public static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws Throwable {
        byte[] bArr = new byte[65536];
        int read = fileInputStream.read(bArr);
        while (read > 0) {
            fileOutputStream.write(bArr, 0, read);
            read = fileInputStream.read(bArr);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static boolean saveObjectToFile(java.lang.String r7, java.lang.Object r8) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 != 0) goto La6
            r0 = 0
            r2 = 1
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L40
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L40
            boolean r7 = r3.exists()     // Catch: java.lang.Throwable -> L40
            if (r7 == 0) goto L17
            r3.delete()     // Catch: java.lang.Throwable -> L40
        L17:
            if (r8 != 0) goto L1a
            return r2
        L1a:
            java.io.File r7 = r3.getParentFile()     // Catch: java.lang.Throwable -> L40
            boolean r7 = r7.exists()     // Catch: java.lang.Throwable -> L40
            if (r7 == 0) goto L2e
            java.io.File r7 = r3.getParentFile()     // Catch: java.lang.Throwable -> L40
            boolean r7 = r7.isDirectory()     // Catch: java.lang.Throwable -> L40
            if (r7 != 0) goto L3c
        L2e:
            java.io.File r7 = r3.getParentFile()     // Catch: java.lang.Throwable -> L40
            r7.delete()     // Catch: java.lang.Throwable -> L40
            java.io.File r7 = r3.getParentFile()     // Catch: java.lang.Throwable -> L40
            r7.mkdirs()     // Catch: java.lang.Throwable -> L40
        L3c:
            r3.createNewFile()     // Catch: java.lang.Throwable -> L40
            goto L49
        L40:
            r7 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()
            r3.m11341d(r7)
            r3 = r0
        L49:
            if (r3 == 0) goto La6
            r7 = 2
            r4 = 3
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L83
            r5.<init>(r3)     // Catch: java.lang.Throwable -> L83
            java.util.zip.GZIPOutputStream r3 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L7c
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L7c
            java.io.ObjectOutputStream r6 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L77
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L77
            r6.writeObject(r8)     // Catch: java.lang.Throwable -> L74
            r6.flush()     // Catch: java.lang.Throwable -> L74
            r6.close()     // Catch: java.lang.Throwable -> L74
            java.io.Closeable[] r8 = new java.io.Closeable[r4]
            r8[r1] = r6
            r8[r2] = r3
            r8[r7] = r5
            com.mob.commons.C5873u.m12179a(r8)
            return r2
        L71:
            r8 = move-exception
            r0 = r6
            goto L9a
        L74:
            r8 = move-exception
            r0 = r6
            goto L86
        L77:
            r8 = move-exception
            goto L86
        L79:
            r8 = move-exception
            r3 = r0
            goto L9a
        L7c:
            r8 = move-exception
            r3 = r0
            goto L86
        L7f:
            r8 = move-exception
            r3 = r0
            r5 = r3
            goto L9a
        L83:
            r8 = move-exception
            r3 = r0
            r5 = r3
        L86:
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L99
            r6.m11341d(r8)     // Catch: java.lang.Throwable -> L99
            java.io.Closeable[] r8 = new java.io.Closeable[r4]
            r8[r1] = r0
            r8[r2] = r3
            r8[r7] = r5
            com.mob.commons.C5873u.m12179a(r8)
            goto La6
        L99:
            r8 = move-exception
        L9a:
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r1] = r0
            r4[r2] = r3
            r4[r7] = r5
            com.mob.commons.C5873u.m12179a(r4)
            throw r8
        La6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.saveObjectToFile(java.lang.String, java.lang.Object):boolean");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.Object readObjectFromFile(java.lang.String r10) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            r1 = 0
            if (r0 != 0) goto L84
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L14
            r0.<init>(r10)     // Catch: java.lang.Throwable -> L14
            boolean r10 = r0.exists()     // Catch: java.lang.Throwable -> L14
            if (r10 != 0) goto L1d
            r0 = r1
            goto L1d
        L14:
            r10 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.m11341d(r10)
            r0 = r1
        L1d:
            if (r0 == 0) goto L84
            r10 = 2
            r2 = 1
            r3 = 0
            r4 = 3
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5b
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L5b
            java.util.zip.GZIPInputStream r0 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L53
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L53
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L4c
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L4c
            java.lang.Object r7 = r6.readObject()     // Catch: java.lang.Throwable -> L45
            r6.close()     // Catch: java.lang.Throwable -> L45
            java.io.Closeable[] r1 = new java.io.Closeable[r4]
            r1[r3] = r6
            r1[r2] = r0
            r1[r10] = r5
            com.mob.commons.C5873u.m12179a(r1)
            return r7
        L45:
            r7 = move-exception
            goto L5f
        L47:
            r6 = move-exception
            r9 = r5
            r5 = r0
            r0 = r6
            goto L77
        L4c:
            r7 = move-exception
            r6 = r1
            goto L5f
        L4f:
            r0 = move-exception
            r6 = r5
            r5 = r1
            goto L78
        L53:
            r7 = move-exception
            r0 = r1
            r6 = r0
            goto L5f
        L57:
            r0 = move-exception
            r5 = r1
            r6 = r5
            goto L78
        L5b:
            r7 = move-exception
            r0 = r1
            r5 = r0
            r6 = r5
        L5f:
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L72
            r8.m11341d(r7)     // Catch: java.lang.Throwable -> L72
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r3] = r6
            r4[r2] = r0
            r4[r10] = r5
            com.mob.commons.C5873u.m12179a(r4)
            goto L84
        L72:
            r1 = move-exception
            r9 = r5
            r5 = r0
            r0 = r1
            r1 = r6
        L77:
            r6 = r9
        L78:
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r3] = r1
            r4[r2] = r5
            r4[r10] = r6
            com.mob.commons.C5873u.m12179a(r4)
            throw r0
        L84:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.readObjectFromFile(java.lang.String):java.lang.Object");
    }

    public static void writeToFileNoCompress(File file, byte[] bArr) {
        FileOutputStream fileOutputStream;
        Closeable[] closeableArr;
        if (file == null || bArr == null) {
            return;
        }
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                MobLog.getInstance().m11341d(e);
            }
        }
        if (file.exists()) {
            FileChannel fileChannel = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                fileChannel = fileOutputStream.getChannel();
                fileChannel.write(ByteBuffer.wrap(bArr));
                fileChannel.force(true);
                closeableArr = new Closeable[]{fileChannel, fileOutputStream};
            } catch (Throwable th2) {
                th = th2;
                try {
                    MobLog.getInstance().m11341d(th);
                    closeableArr = new Closeable[]{fileChannel, fileOutputStream};
                    C5873u.m12179a(closeableArr);
                } catch (Throwable th3) {
                    C5873u.m12179a(fileChannel, fileOutputStream);
                    throw th3;
                }
            }
            C5873u.m12179a(closeableArr);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static byte[] readFromFileNoCompress(java.io.File r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L61
            boolean r1 = r8.exists()
            if (r1 == 0) goto L61
            r1 = 1
            r2 = 0
            r3 = 2
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L42
            r4.<init>(r8)     // Catch: java.lang.Throwable -> L42
            java.nio.channels.FileChannel r8 = r4.getChannel()     // Catch: java.lang.Throwable -> L3a
            long r5 = r8.size()     // Catch: java.lang.Throwable -> L33
            int r5 = (int) r5     // Catch: java.lang.Throwable -> L33
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r5)     // Catch: java.lang.Throwable -> L33
        L1e:
            int r6 = r8.read(r5)     // Catch: java.lang.Throwable -> L33
            if (r6 <= 0) goto L25
            goto L1e
        L25:
            byte[] r0 = r5.array()     // Catch: java.lang.Throwable -> L33
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r2] = r8
            r3[r1] = r4
            com.mob.commons.C5873u.m12179a(r3)
            return r0
        L33:
            r5 = move-exception
            goto L45
        L35:
            r8 = move-exception
            r7 = r0
            r0 = r8
            r8 = r7
            goto L57
        L3a:
            r5 = move-exception
            r8 = r0
            goto L45
        L3d:
            r8 = move-exception
            r4 = r0
            r0 = r8
            r8 = r4
            goto L57
        L42:
            r5 = move-exception
            r8 = r0
            r4 = r8
        L45:
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L56
            r6.m11341d(r5)     // Catch: java.lang.Throwable -> L56
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r2] = r8
            r3[r1] = r4
            com.mob.commons.C5873u.m12179a(r3)
            goto L61
        L56:
            r0 = move-exception
        L57:
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r2] = r8
            r3[r1] = r4
            com.mob.commons.C5873u.m12179a(r3)
            throw r0
        L61:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.readFromFileNoCompress(java.io.File):byte[]");
    }

    public static ArrayList<HashMap<String, String>> readArrayListFromFile(String str) {
        return readArrayListFromFile(str, false);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.String>> readArrayListFromFile(java.lang.String r12, boolean r13) {
        /*
            android.content.Context r0 = com.mob.MobSDK.getContext()
            r1 = 0
            java.io.File r12 = getDataCacheFile(r0, r12, r1)
            boolean r0 = r12.exists()
            if (r0 == 0) goto Lac
            long r2 = r12.length()
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto Lac
            r0 = 3
            r2 = 1
            r3 = 4
            r4 = 2
            r5 = 0
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L84
            r6.<init>()     // Catch: java.lang.Throwable -> L84
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L84
            r7.<init>(r12)     // Catch: java.lang.Throwable -> L84
            java.util.zip.GZIPInputStream r12 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L7b
            r12.<init>(r7)     // Catch: java.lang.Throwable -> L7b
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L74
            java.lang.String r9 = "utf-8"
            r8.<init>(r12, r9)     // Catch: java.lang.Throwable -> L74
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6f
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L6f
            java.lang.String r5 = r9.readLine()     // Catch: java.lang.Throwable -> L6c
        L3e:
            if (r5 == 0) goto L5b
            if (r13 == 0) goto L4f
            java.lang.String r10 = new java.lang.String     // Catch: java.lang.Throwable -> L6c
            byte[] r5 = android.util.Base64.decode(r5, r4)     // Catch: java.lang.Throwable -> L6c
            java.lang.String r11 = "utf-8"
            r10.<init>(r5, r11)     // Catch: java.lang.Throwable -> L6c
            r5 = r10
        L4f:
            java.util.HashMap r5 = com.mob.tools.utils.HashonHelper.fromJson(r5)     // Catch: java.lang.Throwable -> L6c
            r6.add(r5)     // Catch: java.lang.Throwable -> L6c
            java.lang.String r5 = r9.readLine()     // Catch: java.lang.Throwable -> L6c
            goto L3e
        L5b:
            java.io.Closeable[] r13 = new java.io.Closeable[r3]
            r13[r1] = r9
            r13[r2] = r8
            r13[r4] = r12
            r13[r0] = r7
            com.mob.commons.C5873u.m12179a(r13)
            return r6
        L69:
            r13 = move-exception
            r5 = r9
            goto L9e
        L6c:
            r13 = move-exception
            r5 = r9
            goto L88
        L6f:
            r13 = move-exception
            goto L88
        L71:
            r13 = move-exception
            r8 = r5
            goto L9e
        L74:
            r13 = move-exception
            r8 = r5
            goto L88
        L77:
            r13 = move-exception
            r12 = r5
            r8 = r12
            goto L9e
        L7b:
            r13 = move-exception
            r12 = r5
            r8 = r12
            goto L88
        L7f:
            r13 = move-exception
            r12 = r5
            r7 = r12
            r8 = r7
            goto L9e
        L84:
            r13 = move-exception
            r12 = r5
            r7 = r12
            r8 = r7
        L88:
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L9d
            r6.m11341d(r13)     // Catch: java.lang.Throwable -> L9d
            java.io.Closeable[] r13 = new java.io.Closeable[r3]
            r13[r1] = r5
            r13[r2] = r8
            r13[r4] = r12
            r13[r0] = r7
            com.mob.commons.C5873u.m12179a(r13)
            goto Lac
        L9d:
            r13 = move-exception
        L9e:
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r1] = r5
            r3[r2] = r8
            r3[r4] = r12
            r3[r0] = r7
            com.mob.commons.C5873u.m12179a(r3)
            throw r13
        Lac:
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.readArrayListFromFile(java.lang.String, boolean):java.util.ArrayList");
    }

    public static void saveArrayListToFile(ArrayList<HashMap<String, String>> arrayList, String str) {
        saveArrayListToFile(arrayList, str, false);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static void saveArrayListToFile(java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.String>> r9, java.lang.String r10, boolean r11) {
        /*
            android.content.Context r0 = com.mob.MobSDK.getContext()
            r1 = 1
            java.io.File r10 = getDataCacheFile(r0, r10, r1)
            r0 = 0
            r2 = 3
            r3 = 0
            r4 = 2
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L6f
            r5.<init>(r10)     // Catch: java.lang.Throwable -> L6f
            java.util.zip.GZIPOutputStream r10 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L67
            r10.<init>(r5)     // Catch: java.lang.Throwable -> L67
            java.io.OutputStreamWriter r6 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L61
            java.lang.String r7 = "utf-8"
            r6.<init>(r10, r7)     // Catch: java.lang.Throwable -> L61
            java.util.Iterator r9 = r9.iterator()     // Catch: java.lang.Throwable -> L5e
        L23:
            boolean r3 = r9.hasNext()     // Catch: java.lang.Throwable -> L5e
            if (r3 == 0) goto L53
            java.lang.Object r3 = r9.next()     // Catch: java.lang.Throwable -> L5e
            java.util.HashMap r3 = (java.util.HashMap) r3     // Catch: java.lang.Throwable -> L5e
            java.lang.String r3 = com.mob.tools.utils.HashonHelper.fromHashMap(r3)     // Catch: java.lang.Throwable -> L5e
            if (r11 == 0) goto L49
            java.lang.String r7 = new java.lang.String     // Catch: java.lang.Throwable -> L5e
            java.lang.String r8 = "utf-8"
            byte[] r3 = r3.getBytes(r8)     // Catch: java.lang.Throwable -> L5e
            byte[] r3 = android.util.Base64.encode(r3, r4)     // Catch: java.lang.Throwable -> L5e
            java.lang.String r8 = "utf-8"
            r7.<init>(r3, r8)     // Catch: java.lang.Throwable -> L5e
            r3 = r7
        L49:
            java.io.Writer r3 = r6.append(r3)     // Catch: java.lang.Throwable -> L5e
            r7 = 10
            r3.append(r7)     // Catch: java.lang.Throwable -> L5e
            goto L23
        L53:
            java.io.Closeable[] r9 = new java.io.Closeable[r2]
            r9[r0] = r6
            r9[r1] = r10
            r9[r4] = r5
            goto L81
        L5c:
            r9 = move-exception
            goto L87
        L5e:
            r9 = move-exception
            r3 = r6
            goto L72
        L61:
            r9 = move-exception
            goto L72
        L63:
            r9 = move-exception
            r10 = r3
            r6 = r10
            goto L87
        L67:
            r9 = move-exception
            r10 = r3
            goto L72
        L6a:
            r9 = move-exception
            r10 = r3
            r5 = r10
            r6 = r5
            goto L87
        L6f:
            r9 = move-exception
            r10 = r3
            r5 = r10
        L72:
            com.mob.tools.log.NLog r11 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L85
            r11.m11341d(r9)     // Catch: java.lang.Throwable -> L85
            java.io.Closeable[] r9 = new java.io.Closeable[r2]
            r9[r0] = r3
            r9[r1] = r10
            r9[r4] = r5
        L81:
            com.mob.commons.C5873u.m12179a(r9)
            return
        L85:
            r9 = move-exception
            r6 = r3
        L87:
            java.io.Closeable[] r11 = new java.io.Closeable[r2]
            r11[r0] = r6
            r11[r1] = r10
            r11[r4] = r5
            com.mob.commons.C5873u.m12179a(r11)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.saveArrayListToFile(java.util.ArrayList, java.lang.String, boolean):void");
    }

    public static void closeIOs(Closeable... closeableArr) {
        C5873u.m12179a(closeableArr);
    }

    public static <T> boolean isEqual(T t, T t2) {
        return !((t == null && t2 != null) || !(t == null || t.equals(t2)));
    }

    public static String getImageCachePath(Context context) {
        return getCachePath(context, "images");
    }

    public static void clearCache(Context context) throws Throwable {
        deleteFileAndFolder(new File(getCachePath(context, null)));
    }

    @Deprecated
    public static long dateToLong(String str) {
        try {
            Date date = new Date(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.getTimeInMillis();
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return 0L;
        }
    }

    @Deprecated
    public static long getFileSize(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        return getFileSize(new File(str));
    }

    @Deprecated
    public static long getFileSize(File file) throws Throwable {
        if (file.exists()) {
            if (file.isDirectory()) {
                int i = 0;
                for (String str : file.list()) {
                    i = (int) (i + getFileSize(new File(file, str)));
                }
                return i;
            }
            return file.length();
        }
        return 0L;
    }

    @Deprecated
    public static Uri pathToContentUri(Context context, String str) {
        try {
            if (C6152DH.SyncMtd.checkPermission(C5868q.m12203b("040cdPcbcidcchcbecHie>cicechegegchdcKdNecfgffdkejcgfffedjfffgdgdkebcgdidjfkfgdkhcff"))) {
                Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
                if (query != null && query.moveToFirst()) {
                    int i = query.getInt(query.getColumnIndex("_id"));
                    Uri parse = Uri.parse("content://media/external/images/media");
                    return Uri.withAppendedPath(parse, "" + i);
                } else if (new File(str).exists()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_data", str);
                    return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                } else {
                    return null;
                }
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    @Deprecated
    public static Bundle urlToBundle(String str) {
        int indexOf;
        String str2;
        if (str.indexOf("://") >= 0) {
            str2 = C5868q.m12203b("007ghhijkk") + str.substring(indexOf + 1);
        } else {
            str2 = C5868q.m12203b("007ghhijkk") + str;
        }
        try {
            URL url = new URL(str2);
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return new Bundle();
        }
    }

    @Deprecated
    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split("=");
                if (split.length < 2 || split[1] == null) {
                    bundle.putString(URLDecoder.decode(split[0]), "");
                } else {
                    bundle.putString(URLDecoder.decode(split[0]), URLDecoder.decode(split[1]));
                }
            }
        }
        return bundle;
    }

    @Deprecated
    public static long strToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0)).getTime();
    }

    @Deprecated
    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj == null) {
                obj = "";
            }
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(Data.urlEncode(str) + "=" + Data.urlEncode(String.valueOf(obj)));
        }
        return sb.toString();
    }

    @Deprecated
    public static String encodeUrl(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair<String> next = it.next();
            if (i > 0) {
                sb.append('&');
            }
            String str = next.name;
            String str2 = next.value;
            if (str != null) {
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(Data.urlEncode(str) + "=" + Data.urlEncode(str2));
                i++;
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static long dateStrToLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str, new ParsePosition(0)).getTime();
    }

    @Deprecated
    public static Uri videoPathToContentUri(Context context, String str) {
        try {
            if (C6152DH.SyncMtd.checkPermission(C5868q.m12203b("040cd^cbcidcchcbec_ieWcicechegegchdc>dAecfgffdkejcgfffedjfffgdgdkebcgdidjfkfgdkhcff"))) {
                Cursor query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
                if (query != null && query.moveToFirst()) {
                    int i = query.getInt(query.getColumnIndex("_id"));
                    Uri parse = Uri.parse("content://media/external/video/media");
                    return Uri.withAppendedPath(parse, "" + i);
                } else if (new File(str).exists()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_data", str);
                    return context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                } else {
                    return null;
                }
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    @Deprecated
    public static synchronized Uri getMediaUri(Context context, String str, String str2) {
        Uri uri;
        synchronized (ResHelper.class) {
            final Object obj = new Object();
            f15267c = null;
            MediaScannerConnection.scanFile(context, new String[]{str}, new String[]{str2}, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.mob.tools.utils.ResHelper.1
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str3, Uri uri2) {
                    Uri unused = ResHelper.f15267c = uri2;
                    synchronized (obj) {
                        obj.notifyAll();
                    }
                }
            });
            try {
                if (f15267c == null) {
                    synchronized (obj) {
                        obj.wait(10000L);
                    }
                }
            } catch (Throwable unused) {
            }
            uri = f15267c;
            f15267c = null;
        }
        return uri;
    }
}
