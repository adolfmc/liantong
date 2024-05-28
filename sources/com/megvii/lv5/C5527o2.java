package com.megvii.lv5;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.megvii.lv5.C5435f1;
import com.megvii.lv5.InterfaceC5509m3;
import com.megvii.lv5.lib.jni.MegDelta;
import com.megvii.lv5.sdk.result.LivenessFile;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
@NBSInstrumented
/* renamed from: com.megvii.lv5.o2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5527o2 {

    /* renamed from: a */
    public static long f13107a;

    /* renamed from: b */
    public static float f13108b;

    /* renamed from: c */
    public static int f13109c;

    /* renamed from: d */
    public static int f13110d;

    /* renamed from: e */
    public static float f13111e;

    /* renamed from: f */
    public static long f13112f;

    /* renamed from: a */
    public static int m13257a(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    /* renamed from: a */
    public static int m13242a(byte[] bArr) {
        if (bArr.length != 4) {
            return -1;
        }
        return ((bArr[3] & 255) << 0) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    /* renamed from: a */
    public static Drawable m13255a(Context context, int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912}, context.getResources().getDrawable(i));
        stateListDrawable.addState(new int[0], context.getResources().getDrawable(i2));
        return stateListDrawable;
    }

    /* renamed from: a */
    public static InterfaceC5509m3.C5510a m13248a(C5622u3 c5622u3) {
        boolean z;
        long j;
        long j2;
        long j3;
        long j4;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = c5622u3.f13747c;
        String str = map.get("Date");
        long m13229c = str != null ? m13229c(str) : 0L;
        String str2 = map.get("Cache-Control");
        int i = 0;
        if (str2 != null) {
            String[] split = str2.split(",");
            int i2 = 0;
            j = 0;
            j2 = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    i2 = 1;
                }
                i++;
            }
            i = i2;
            z = true;
        } else {
            z = false;
            j = 0;
            j2 = 0;
        }
        String str3 = map.get("Expires");
        long m13229c2 = str3 != null ? m13229c(str3) : 0L;
        String str4 = map.get("Last-Modified");
        long m13229c3 = str4 != null ? m13229c(str4) : 0L;
        String str5 = map.get("ETag");
        if (z) {
            j3 = currentTimeMillis + (j * 1000);
            if (i == 0) {
                Long.signum(j2);
                j4 = (j2 * 1000) + j3;
            }
            j4 = j3;
        } else if (m13229c <= 0 || m13229c2 < m13229c) {
            j3 = 0;
            j4 = j3;
        } else {
            j4 = (m13229c2 - m13229c) + currentTimeMillis;
            j3 = j4;
        }
        InterfaceC5509m3.C5510a c5510a = new InterfaceC5509m3.C5510a();
        c5510a.f12887a = c5622u3.f13746b;
        c5510a.f12888b = str5;
        c5510a.f12892f = j3;
        c5510a.f12891e = j4;
        c5510a.f12889c = m13229c;
        c5510a.f12890d = m13229c3;
        c5510a.f12893g = map;
        return c5510a;
    }

    /* renamed from: a */
    public static Object m13252a(Context context, String str, Object obj) {
        if (context == null) {
            return obj;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("meglive_data", 0);
        return obj instanceof Boolean ? Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue())) : obj instanceof Float ? Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue())) : obj instanceof Integer ? Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue())) : obj instanceof Long ? Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue())) : obj instanceof String ? sharedPreferences.getString(str, (String) obj) : obj;
    }

    /* renamed from: a */
    public static <T> T m13246a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    /* renamed from: a */
    public static String m13256a(Context context) {
        return (String) m13252a(context, "megvii_liveness_bizToken", "");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m13249a(android.content.Context r4, byte[] r5, java.lang.String r6) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            java.lang.String r1 = "meglive"
            java.io.File r4 = r4.getExternalFilesDir(r1)
            boolean r1 = r4.exists()
            if (r1 != 0) goto L17
            boolean r1 = r4.mkdirs()
            if (r1 != 0) goto L17
            return r0
        L17:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r1.<init>()     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r2 = "_"
            r1.append(r2)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r1.append(r6)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r6 = ".jpg"
            r1.append(r6)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r2.<init>()     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r2.append(r4)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r3 = "/"
            r2.append(r3)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r2.append(r6)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r2.write(r5)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L9c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L9c
            r5.<init>()     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L9c
            java.lang.String r4 = r4.getAbsolutePath()     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L9c
            r5.append(r4)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L9c
            java.lang.String r4 = "/"
            r5.append(r4)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L9c
            r5.append(r6)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L9c
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L9c
            r2.close()     // Catch: java.io.IOException -> L6a
            goto L6e
        L6a:
            r5 = move-exception
            r5.printStackTrace()
        L6e:
            r1.close()     // Catch: java.io.IOException -> L72
            goto L76
        L72:
            r5 = move-exception
            r5.printStackTrace()
        L76:
            return r4
        L77:
            r4 = move-exception
            goto L84
        L79:
            r4 = move-exception
            goto L9e
        L7b:
            r4 = move-exception
            r2 = r0
            goto L84
        L7e:
            r4 = move-exception
            r1 = r0
            goto L9e
        L81:
            r4 = move-exception
            r1 = r0
            r2 = r1
        L84:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L9c
            if (r2 == 0) goto L91
            r2.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L8d:
            r4 = move-exception
            r4.printStackTrace()
        L91:
            if (r1 == 0) goto L9b
            r1.close()     // Catch: java.io.IOException -> L97
            goto L9b
        L97:
            r4 = move-exception
            r4.printStackTrace()
        L9b:
            return r0
        L9c:
            r4 = move-exception
            r0 = r2
        L9e:
            if (r0 == 0) goto La8
            r0.close()     // Catch: java.io.IOException -> La4
            goto La8
        La4:
            r5 = move-exception
            r5.printStackTrace()
        La8:
            if (r1 == 0) goto Lb2
            r1.close()     // Catch: java.io.IOException -> Lae
            goto Lb2
        Lae:
            r5 = move-exception
            r5.printStackTrace()
        Lb2:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13249a(android.content.Context, byte[], java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static String m13243a(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m13253a(android.content.Context r5, java.lang.String r6) {
        /*
            if (r5 != 0) goto L3
            return
        L3:
            java.io.File r5 = r5.getFilesDir()
            java.io.File r0 = new java.io.File
            java.lang.String r1 = ".mg"
            r0.<init>(r5, r1)
            boolean r5 = r0.exists()
            if (r5 != 0) goto L1b
            boolean r5 = r0.mkdirs()
            if (r5 != 0) goto L1b
            return
        L1b:
            r5 = 0
            java.lang.String r1 = "iiid"
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            r4.<init>()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            r4.append(r0)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            java.lang.String r0 = java.io.File.separator     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            r4.append(r0)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            r4.append(r1)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            byte[] r5 = r6.getBytes()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            r2.write(r5)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            goto L54
        L44:
            r5 = move-exception
            goto L5d
        L46:
            r5 = move-exception
            goto L4f
        L48:
            r6 = move-exception
            r2 = r5
            r5 = r6
            goto L5d
        L4c:
            r6 = move-exception
            r2 = r5
            r5 = r6
        L4f:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L44
            if (r2 == 0) goto L5c
        L54:
            r2.close()     // Catch: java.io.IOException -> L58
            goto L5c
        L58:
            r5 = move-exception
            r5.printStackTrace()
        L5c:
            return
        L5d:
            if (r2 == 0) goto L67
            r2.close()     // Catch: java.io.IOException -> L63
            goto L67
        L63:
            r6 = move-exception
            r6.printStackTrace()
        L67:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13253a(android.content.Context, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m13250a(android.content.Context r3, byte[] r4, com.megvii.lv5.EnumC5556s2 r5) {
        /*
            if (r3 == 0) goto L7b
            java.io.File r3 = r3.getCacheDir()
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "model"
            r0.<init>(r3, r1)
            boolean r3 = r0.exists()
            if (r3 != 0) goto L1a
            boolean r3 = r0.mkdirs()
            if (r3 != 0) goto L1a
            return
        L1a:
            java.lang.String r3 = ""
            com.megvii.lv5.s2 r1 = com.megvii.lv5.EnumC5556s2.RECT
            if (r5 != r1) goto L23
            java.lang.String r3 = "model_rect"
            goto L30
        L23:
            com.megvii.lv5.s2 r1 = com.megvii.lv5.EnumC5556s2.LMK
            if (r5 != r1) goto L2a
            java.lang.String r3 = "model_lmk"
            goto L30
        L2a:
            com.megvii.lv5.s2 r1 = com.megvii.lv5.EnumC5556s2.ACTION
            if (r5 != r1) goto L30
            java.lang.String r3 = "model_action"
        L30:
            r5 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            r2.<init>()     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            r2.append(r0)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            java.lang.String r0 = "/"
            r2.append(r0)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            r2.append(r3)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            java.lang.String r3 = r2.toString()     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f
            r3.write(r4)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            r3.close()     // Catch: java.io.IOException -> L6a
            goto L6e
        L56:
            r4 = move-exception
            r5 = r3
            goto L70
        L59:
            r4 = move-exception
            r5 = r3
            goto L61
        L5c:
            r3 = move-exception
            r4 = r3
            goto L70
        L5f:
            r3 = move-exception
            r4 = r3
        L61:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L6f
            if (r5 == 0) goto L6e
            r5.close()     // Catch: java.io.IOException -> L6a
            goto L6e
        L6a:
            r3 = move-exception
            r3.printStackTrace()
        L6e:
            return
        L6f:
            r4 = move-exception
        L70:
            if (r5 == 0) goto L7a
            r5.close()     // Catch: java.io.IOException -> L76
            goto L7a
        L76:
            r3 = move-exception
            r3.printStackTrace()
        L7a:
            throw r4
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13250a(android.content.Context, byte[], com.megvii.lv5.s2):void");
    }

    /* renamed from: a */
    public static void m13240a(byte[] bArr, String str) {
        FileOutputStream fileOutputStream;
        if (bArr == null) {
            return;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(str);
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                    try {
                        bufferedOutputStream2.write(bArr);
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        bufferedOutputStream = bufferedOutputStream2;
                        e.printStackTrace();
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (fileOutputStream == null) {
                            return;
                        }
                        fileOutputStream.close();
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e6) {
                    e = e6;
                }
            } catch (Exception e7) {
                e = e7;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
            try {
                fileOutputStream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    public static boolean m13247a(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                return file.delete();
            }
            for (File file2 : file.listFiles()) {
                m13247a(file2);
            }
            return file.delete();
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b3, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b7, code lost:
        return false;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e3 A[LOOP:0: B:11:0x0034->B:48:0x00e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e1 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m13245a(java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13245a(java.lang.String):boolean");
    }

    /* renamed from: a */
    public static byte[] m13254a(Context context, EnumC5556s2 enumC5556s2) {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        IOException e;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (context == null) {
            return null;
        }
        File file = new File(context.getCacheDir(), "model");
        String str = "";
        if (enumC5556s2 == EnumC5556s2.RECT) {
            str = "model_rect";
        } else if (enumC5556s2 == EnumC5556s2.LMK) {
            str = "model_lmk";
        } else if (enumC5556s2 == EnumC5556s2.ACTION) {
            str = "model_action";
        }
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file + "/" + str));
            try {
                try {
                    int available = bufferedInputStream.available();
                    byte[] bArr = new byte[available];
                    bufferedInputStream.read(bArr);
                    byteArrayOutputStream = new ByteArrayOutputStream(available);
                    try {
                        byteArrayOutputStream.write(bArr);
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } catch (IOException e3) {
                        e = e3;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        e.printStackTrace();
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e5) {
                                e = e5;
                                byteArrayOutputStream = byteArrayOutputStream2;
                                e.printStackTrace();
                                return byteArrayOutputStream.toByteArray();
                            }
                        }
                        byteArrayOutputStream = byteArrayOutputStream2;
                        return byteArrayOutputStream.toByteArray();
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e8) {
                        e = e8;
                        e.printStackTrace();
                        return byteArrayOutputStream.toByteArray();
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e9) {
                e = e9;
            }
        } catch (IOException e10) {
            e = e10;
            bufferedInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    public static Drawable m13237b(Context context, int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, context.getResources().getDrawable(i));
        stateListDrawable.addState(new int[0], context.getResources().getDrawable(i2));
        return stateListDrawable;
    }

    /* renamed from: b */
    public static void m13236b(Context context, String str) {
        if (str != null) {
            Resources resources = context.getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            Locale locale = new Locale(str);
            if (Build.VERSION.SDK_INT >= 17) {
                configuration.setLocale(locale);
            } else {
                configuration.locale = locale;
            }
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    /* renamed from: b */
    public static void m13235b(Context context, String str, Object obj) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("meglive_data", 0).edit();
        if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else {
            edit.putString(str, (String) obj);
        }
        edit.commit();
    }

    /* renamed from: b */
    public static void m13233b(String str) {
        f13108b = 0.0f;
        f13109c = 0;
        f13110d = 0;
        f13111e = 0.0f;
        f13112f = 0L;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        if (str == null) {
            return;
        }
        mediaMetadataRetriever.setDataSource(str);
        String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
        f13108b = Integer.parseInt(extractMetadata) / 1000.0f;
        f13108b = Float.parseFloat(new DecimalFormat("#.00").format(f13108b));
        f13109c = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
        f13110d = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
        try {
            f13111e = 1000.0f / ((Integer.parseInt(extractMetadata) * 1.0f) / ((float) Long.valueOf(mediaMetadataRetriever.extractMetadata(32)).longValue()));
        } catch (Throwable unused) {
        }
        File file = new File(str);
        if (file.exists()) {
            f13112f = file.length() / 1024;
        }
    }

    /* renamed from: b */
    public static boolean m13238b(Context context) {
        return ((Boolean) m13252a((Context) null, "credit_is_check", Boolean.FALSE)).booleanValue();
    }

    /* renamed from: b */
    public static byte[] m13234b(File file) {
        if (file == null) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static int m13232c() {
        Context context = C5435f1.C5437b.f12597a.f12591b;
        boolean m13221j = m13221j(context);
        boolean m13226e = m13226e(context);
        if (m13221j) {
            return 2;
        }
        return m13226e ? 3 : 1;
    }

    /* renamed from: c */
    public static int m13231c(Context context) {
        String str = (String) m13252a(context, "exposure_info", "");
        if (TextUtils.isEmpty(str)) {
            return 20;
        }
        try {
            return new JSONObject(str).optInt("all_frame", 20);
        } catch (JSONException e) {
            e.printStackTrace();
            return 20;
        }
    }

    /* renamed from: c */
    public static long m13229c(String str) {
        try {
            return C5511m4.m13437a(str).getTime();
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* renamed from: d */
    public static int m13227d(Context context) {
        String str = (String) m13252a(context, "exposure_info", "");
        if (TextUtils.isEmpty(str)) {
            return 10;
        }
        try {
            return new JSONObject(str).getJSONArray("frame_sequence").optInt(1, 10);
        } catch (JSONException e) {
            e.printStackTrace();
            return 10;
        }
    }

    /* renamed from: d */
    public static String m13228d() {
        try {
            JSONObject jSONObject = new JSONObject();
            Context context = C5435f1.C5437b.f12597a.f12591b;
            jSONObject.put("biz_token", m13256a(context));
            jSONObject.put("bid", context.getPackageName());
            jSONObject.put("liveness_type", m13223h(context).f12973b);
            jSONObject.put("liveness_config", (String) m13252a(context, "megvii_liveness_config", ""));
            jSONObject.put("liveness_level", ((Integer) m13252a(context, "liveness_level", (Object) 0)).intValue());
            jSONObject.put("datetime", System.currentTimeMillis() / 1000);
            jSONObject.put("sdk_version", "MegLiveStill 5.6.4A");
            jSONObject.put("user_info", m13239b());
            jSONObject.put("video_type", m13232c());
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: e */
    public static boolean m13226e(Context context) {
        return ((Boolean) m13252a(context, "exposure_status", Boolean.FALSE)).booleanValue();
    }

    /* renamed from: f */
    public static int m13225f(Context context) {
        String str = (String) m13252a(context, "exposure_info", "");
        if (TextUtils.isEmpty(str)) {
            return 30;
        }
        try {
            return new JSONObject(str).optInt("threshold", 30);
        } catch (JSONException e) {
            e.printStackTrace();
            return 30;
        }
    }

    /* renamed from: g */
    public static int m13224g(Context context) {
        String str = (String) m13252a(context, "exposure_info", "");
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return new JSONObject(str).optInt("trend", 0);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0823  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0825  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x082b  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0833  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0835  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x083b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x083d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0843  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0845  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x084b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x084d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0853  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0855  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0877  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0879  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0893  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0895  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x08dd  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x08df  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x08f5 A[Catch: JSONException -> 0x09ca, TryCatch #0 {JSONException -> 0x09ca, blocks: (B:4:0x0019, B:8:0x002a, B:10:0x0056, B:12:0x005c, B:13:0x0063, B:15:0x0069, B:16:0x0072, B:19:0x0078, B:20:0x009c, B:24:0x025e, B:26:0x0284, B:28:0x028c, B:35:0x02d2, B:37:0x02e0, B:39:0x02e8, B:72:0x04c1, B:76:0x074d, B:77:0x077a, B:79:0x0782, B:81:0x078c, B:83:0x0797, B:85:0x079e, B:87:0x07c5, B:91:0x07d0, B:94:0x07e0, B:98:0x081e, B:102:0x0826, B:106:0x082e, B:110:0x0836, B:114:0x083e, B:118:0x0846, B:122:0x084e, B:126:0x0856, B:130:0x087a, B:134:0x0896, B:138:0x08e0, B:140:0x08f5, B:142:0x08fe, B:143:0x0907, B:144:0x090a, B:148:0x0925, B:149:0x092f, B:159:0x095e, B:152:0x0937, B:155:0x0945, B:158:0x0953, B:160:0x0966, B:164:0x09b4, B:82:0x0791, B:52:0x0337, B:54:0x033f, B:56:0x0345, B:57:0x034c, B:59:0x0352, B:60:0x035d, B:62:0x0363, B:63:0x036e, B:64:0x0373, B:65:0x037d, B:69:0x039d, B:40:0x02f9, B:42:0x0301, B:44:0x031c, B:43:0x030f, B:45:0x0320, B:29:0x029d, B:31:0x02a5, B:32:0x02b2, B:33:0x02b6, B:34:0x02c4), top: B:169:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0922  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x09b2  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x09b3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x02e0 A[Catch: JSONException -> 0x09ca, TryCatch #0 {JSONException -> 0x09ca, blocks: (B:4:0x0019, B:8:0x002a, B:10:0x0056, B:12:0x005c, B:13:0x0063, B:15:0x0069, B:16:0x0072, B:19:0x0078, B:20:0x009c, B:24:0x025e, B:26:0x0284, B:28:0x028c, B:35:0x02d2, B:37:0x02e0, B:39:0x02e8, B:72:0x04c1, B:76:0x074d, B:77:0x077a, B:79:0x0782, B:81:0x078c, B:83:0x0797, B:85:0x079e, B:87:0x07c5, B:91:0x07d0, B:94:0x07e0, B:98:0x081e, B:102:0x0826, B:106:0x082e, B:110:0x0836, B:114:0x083e, B:118:0x0846, B:122:0x084e, B:126:0x0856, B:130:0x087a, B:134:0x0896, B:138:0x08e0, B:140:0x08f5, B:142:0x08fe, B:143:0x0907, B:144:0x090a, B:148:0x0925, B:149:0x092f, B:159:0x095e, B:152:0x0937, B:155:0x0945, B:158:0x0953, B:160:0x0966, B:164:0x09b4, B:82:0x0791, B:52:0x0337, B:54:0x033f, B:56:0x0345, B:57:0x034c, B:59:0x0352, B:60:0x035d, B:62:0x0363, B:63:0x036e, B:64:0x0373, B:65:0x037d, B:69:0x039d, B:40:0x02f9, B:42:0x0301, B:44:0x031c, B:43:0x030f, B:45:0x0320, B:29:0x029d, B:31:0x02a5, B:32:0x02b2, B:33:0x02b6, B:34:0x02c4), top: B:169:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0320 A[Catch: JSONException -> 0x09ca, TryCatch #0 {JSONException -> 0x09ca, blocks: (B:4:0x0019, B:8:0x002a, B:10:0x0056, B:12:0x005c, B:13:0x0063, B:15:0x0069, B:16:0x0072, B:19:0x0078, B:20:0x009c, B:24:0x025e, B:26:0x0284, B:28:0x028c, B:35:0x02d2, B:37:0x02e0, B:39:0x02e8, B:72:0x04c1, B:76:0x074d, B:77:0x077a, B:79:0x0782, B:81:0x078c, B:83:0x0797, B:85:0x079e, B:87:0x07c5, B:91:0x07d0, B:94:0x07e0, B:98:0x081e, B:102:0x0826, B:106:0x082e, B:110:0x0836, B:114:0x083e, B:118:0x0846, B:122:0x084e, B:126:0x0856, B:130:0x087a, B:134:0x0896, B:138:0x08e0, B:140:0x08f5, B:142:0x08fe, B:143:0x0907, B:144:0x090a, B:148:0x0925, B:149:0x092f, B:159:0x095e, B:152:0x0937, B:155:0x0945, B:158:0x0953, B:160:0x0966, B:164:0x09b4, B:82:0x0791, B:52:0x0337, B:54:0x033f, B:56:0x0345, B:57:0x034c, B:59:0x0352, B:60:0x035d, B:62:0x0363, B:63:0x036e, B:64:0x0373, B:65:0x037d, B:69:0x039d, B:40:0x02f9, B:42:0x0301, B:44:0x031c, B:43:0x030f, B:45:0x0320, B:29:0x029d, B:31:0x02a5, B:32:0x02b2, B:33:0x02b6, B:34:0x02c4), top: B:169:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x033f A[Catch: JSONException -> 0x09ca, TryCatch #0 {JSONException -> 0x09ca, blocks: (B:4:0x0019, B:8:0x002a, B:10:0x0056, B:12:0x005c, B:13:0x0063, B:15:0x0069, B:16:0x0072, B:19:0x0078, B:20:0x009c, B:24:0x025e, B:26:0x0284, B:28:0x028c, B:35:0x02d2, B:37:0x02e0, B:39:0x02e8, B:72:0x04c1, B:76:0x074d, B:77:0x077a, B:79:0x0782, B:81:0x078c, B:83:0x0797, B:85:0x079e, B:87:0x07c5, B:91:0x07d0, B:94:0x07e0, B:98:0x081e, B:102:0x0826, B:106:0x082e, B:110:0x0836, B:114:0x083e, B:118:0x0846, B:122:0x084e, B:126:0x0856, B:130:0x087a, B:134:0x0896, B:138:0x08e0, B:140:0x08f5, B:142:0x08fe, B:143:0x0907, B:144:0x090a, B:148:0x0925, B:149:0x092f, B:159:0x095e, B:152:0x0937, B:155:0x0945, B:158:0x0953, B:160:0x0966, B:164:0x09b4, B:82:0x0791, B:52:0x0337, B:54:0x033f, B:56:0x0345, B:57:0x034c, B:59:0x0352, B:60:0x035d, B:62:0x0363, B:63:0x036e, B:64:0x0373, B:65:0x037d, B:69:0x039d, B:40:0x02f9, B:42:0x0301, B:44:0x031c, B:43:0x030f, B:45:0x0320, B:29:0x029d, B:31:0x02a5, B:32:0x02b2, B:33:0x02b6, B:34:0x02c4), top: B:169:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0352 A[Catch: JSONException -> 0x09ca, TryCatch #0 {JSONException -> 0x09ca, blocks: (B:4:0x0019, B:8:0x002a, B:10:0x0056, B:12:0x005c, B:13:0x0063, B:15:0x0069, B:16:0x0072, B:19:0x0078, B:20:0x009c, B:24:0x025e, B:26:0x0284, B:28:0x028c, B:35:0x02d2, B:37:0x02e0, B:39:0x02e8, B:72:0x04c1, B:76:0x074d, B:77:0x077a, B:79:0x0782, B:81:0x078c, B:83:0x0797, B:85:0x079e, B:87:0x07c5, B:91:0x07d0, B:94:0x07e0, B:98:0x081e, B:102:0x0826, B:106:0x082e, B:110:0x0836, B:114:0x083e, B:118:0x0846, B:122:0x084e, B:126:0x0856, B:130:0x087a, B:134:0x0896, B:138:0x08e0, B:140:0x08f5, B:142:0x08fe, B:143:0x0907, B:144:0x090a, B:148:0x0925, B:149:0x092f, B:159:0x095e, B:152:0x0937, B:155:0x0945, B:158:0x0953, B:160:0x0966, B:164:0x09b4, B:82:0x0791, B:52:0x0337, B:54:0x033f, B:56:0x0345, B:57:0x034c, B:59:0x0352, B:60:0x035d, B:62:0x0363, B:63:0x036e, B:64:0x0373, B:65:0x037d, B:69:0x039d, B:40:0x02f9, B:42:0x0301, B:44:0x031c, B:43:0x030f, B:45:0x0320, B:29:0x029d, B:31:0x02a5, B:32:0x02b2, B:33:0x02b6, B:34:0x02c4), top: B:169:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x04c1 A[Catch: JSONException -> 0x09ca, TryCatch #0 {JSONException -> 0x09ca, blocks: (B:4:0x0019, B:8:0x002a, B:10:0x0056, B:12:0x005c, B:13:0x0063, B:15:0x0069, B:16:0x0072, B:19:0x0078, B:20:0x009c, B:24:0x025e, B:26:0x0284, B:28:0x028c, B:35:0x02d2, B:37:0x02e0, B:39:0x02e8, B:72:0x04c1, B:76:0x074d, B:77:0x077a, B:79:0x0782, B:81:0x078c, B:83:0x0797, B:85:0x079e, B:87:0x07c5, B:91:0x07d0, B:94:0x07e0, B:98:0x081e, B:102:0x0826, B:106:0x082e, B:110:0x0836, B:114:0x083e, B:118:0x0846, B:122:0x084e, B:126:0x0856, B:130:0x087a, B:134:0x0896, B:138:0x08e0, B:140:0x08f5, B:142:0x08fe, B:143:0x0907, B:144:0x090a, B:148:0x0925, B:149:0x092f, B:159:0x095e, B:152:0x0937, B:155:0x0945, B:158:0x0953, B:160:0x0966, B:164:0x09b4, B:82:0x0791, B:52:0x0337, B:54:0x033f, B:56:0x0345, B:57:0x034c, B:59:0x0352, B:60:0x035d, B:62:0x0363, B:63:0x036e, B:64:0x0373, B:65:0x037d, B:69:0x039d, B:40:0x02f9, B:42:0x0301, B:44:0x031c, B:43:0x030f, B:45:0x0320, B:29:0x029d, B:31:0x02a5, B:32:0x02b2, B:33:0x02b6, B:34:0x02c4), top: B:169:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0782 A[Catch: JSONException -> 0x09ca, TryCatch #0 {JSONException -> 0x09ca, blocks: (B:4:0x0019, B:8:0x002a, B:10:0x0056, B:12:0x005c, B:13:0x0063, B:15:0x0069, B:16:0x0072, B:19:0x0078, B:20:0x009c, B:24:0x025e, B:26:0x0284, B:28:0x028c, B:35:0x02d2, B:37:0x02e0, B:39:0x02e8, B:72:0x04c1, B:76:0x074d, B:77:0x077a, B:79:0x0782, B:81:0x078c, B:83:0x0797, B:85:0x079e, B:87:0x07c5, B:91:0x07d0, B:94:0x07e0, B:98:0x081e, B:102:0x0826, B:106:0x082e, B:110:0x0836, B:114:0x083e, B:118:0x0846, B:122:0x084e, B:126:0x0856, B:130:0x087a, B:134:0x0896, B:138:0x08e0, B:140:0x08f5, B:142:0x08fe, B:143:0x0907, B:144:0x090a, B:148:0x0925, B:149:0x092f, B:159:0x095e, B:152:0x0937, B:155:0x0945, B:158:0x0953, B:160:0x0966, B:164:0x09b4, B:82:0x0791, B:52:0x0337, B:54:0x033f, B:56:0x0345, B:57:0x034c, B:59:0x0352, B:60:0x035d, B:62:0x0363, B:63:0x036e, B:64:0x0373, B:65:0x037d, B:69:0x039d, B:40:0x02f9, B:42:0x0301, B:44:0x031c, B:43:0x030f, B:45:0x0320, B:29:0x029d, B:31:0x02a5, B:32:0x02b2, B:33:0x02b6, B:34:0x02c4), top: B:169:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x079b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x07c5 A[Catch: JSONException -> 0x09ca, TryCatch #0 {JSONException -> 0x09ca, blocks: (B:4:0x0019, B:8:0x002a, B:10:0x0056, B:12:0x005c, B:13:0x0063, B:15:0x0069, B:16:0x0072, B:19:0x0078, B:20:0x009c, B:24:0x025e, B:26:0x0284, B:28:0x028c, B:35:0x02d2, B:37:0x02e0, B:39:0x02e8, B:72:0x04c1, B:76:0x074d, B:77:0x077a, B:79:0x0782, B:81:0x078c, B:83:0x0797, B:85:0x079e, B:87:0x07c5, B:91:0x07d0, B:94:0x07e0, B:98:0x081e, B:102:0x0826, B:106:0x082e, B:110:0x0836, B:114:0x083e, B:118:0x0846, B:122:0x084e, B:126:0x0856, B:130:0x087a, B:134:0x0896, B:138:0x08e0, B:140:0x08f5, B:142:0x08fe, B:143:0x0907, B:144:0x090a, B:148:0x0925, B:149:0x092f, B:159:0x095e, B:152:0x0937, B:155:0x0945, B:158:0x0953, B:160:0x0966, B:164:0x09b4, B:82:0x0791, B:52:0x0337, B:54:0x033f, B:56:0x0345, B:57:0x034c, B:59:0x0352, B:60:0x035d, B:62:0x0363, B:63:0x036e, B:64:0x0373, B:65:0x037d, B:69:0x039d, B:40:0x02f9, B:42:0x0301, B:44:0x031c, B:43:0x030f, B:45:0x0320, B:29:0x029d, B:31:0x02a5, B:32:0x02b2, B:33:0x02b6, B:34:0x02c4), top: B:169:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x07cd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x07cf  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x07df  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x081b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x081d  */
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.megvii.lv5.C5515n0 m13223h(android.content.Context r16) {
        /*
            Method dump skipped, instructions count: 2512
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13223h(android.content.Context):com.megvii.lv5.n0");
    }

    /* renamed from: i */
    public static int m13222i(Context context) {
        String str = (String) m13252a(context, "white_balance_info", "");
        if (TextUtils.isEmpty(str)) {
            return 10;
        }
        try {
            return new JSONObject(str).getJSONArray("frame_sequence").optInt(1, 10);
        } catch (JSONException e) {
            e.printStackTrace();
            return 10;
        }
    }

    /* renamed from: j */
    public static boolean m13221j(Context context) {
        return ((Boolean) m13252a(context, "white_balance_status", Boolean.FALSE)).booleanValue();
    }

    /* renamed from: k */
    public static int m13220k(Context context) {
        String str = (String) m13252a(context, "white_balance_info", "");
        if (TextUtils.isEmpty(str)) {
            return 25;
        }
        try {
            return new JSONObject(str).optInt("all_frame", 25);
        } catch (JSONException e) {
            e.printStackTrace();
            return 25;
        }
    }

    /* renamed from: l */
    public static int m13219l(Context context) {
        String str = (String) m13252a(context, "white_balance_info", "");
        if (!TextUtils.isEmpty(str)) {
            try {
                return new JSONObject(str).optInt("threshold", 10);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 25;
    }

    /* renamed from: m */
    public static int m13218m(Context context) {
        String str = (String) m13252a(context, "white_balance_info", "");
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return new JSONObject(str).optInt("trend", 0);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: n */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m13217n(android.content.Context r5) {
        /*
            if (r5 != 0) goto L5
            java.lang.String r5 = ""
            return r5
        L5:
            java.io.File r5 = r5.getFilesDir()
            java.io.File r0 = new java.io.File
            java.lang.String r1 = ".mg"
            r0.<init>(r5, r1)
            boolean r5 = r0.exists()
            if (r5 != 0) goto L19
            java.lang.String r5 = ""
            return r5
        L19:
            r5 = 0
            java.lang.String r1 = "iiid"
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            r4.<init>()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            r4.append(r0)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            java.lang.String r0 = java.io.File.separator     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            r4.append(r0)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            r4.append(r1)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            int r5 = r2.available()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.read(r5)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r0 = new java.lang.String     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.close()     // Catch: java.io.IOException -> L4c
            goto L50
        L4c:
            r5 = move-exception
            r5.printStackTrace()
        L50:
            return r0
        L51:
            r5 = move-exception
            goto L6c
        L53:
            r5 = move-exception
            goto L5c
        L55:
            r0 = move-exception
            r2 = r5
            r5 = r0
            goto L6c
        L59:
            r0 = move-exception
            r2 = r5
            r5 = r0
        L5c:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L51
            java.lang.String r5 = ""
            if (r2 == 0) goto L6b
            r2.close()     // Catch: java.io.IOException -> L67
            goto L6b
        L67:
            r0 = move-exception
            r0.printStackTrace()
        L6b:
            return r5
        L6c:
            if (r2 == 0) goto L76
            r2.close()     // Catch: java.io.IOException -> L72
            goto L76
        L72:
            r0 = move-exception
            r0.printStackTrace()
        L76:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13217n(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0079, code lost:
        if (r3.contains("arm64-v8a") != false) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0177 A[Catch: JSONException -> 0x0188, TryCatch #2 {JSONException -> 0x0188, blocks: (B:3:0x0005, B:5:0x000e, B:7:0x0014, B:9:0x001b, B:11:0x0022, B:13:0x002a, B:24:0x0080, B:26:0x0085, B:28:0x00ad, B:31:0x012b, B:32:0x0132, B:36:0x013b, B:50:0x016a, B:49:0x0167, B:51:0x016d, B:53:0x0177, B:54:0x0182, B:38:0x0142, B:40:0x0149, B:42:0x0150, B:44:0x0158, B:46:0x0160), top: B:64:0x0005, inners: #1 }] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject m13239b() {
        /*
            Method dump skipped, instructions count: 397
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13239b():org.json.JSONObject");
    }

    /* renamed from: c */
    public static void m13230c(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            JSONArray optJSONArray = jSONObject2.optJSONArray("files");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if ("1".equals(optJSONObject.optString("type"))) {
                        jSONArray.put(optJSONObject);
                    }
                }
                jSONObject.put("files", jSONArray);
            }
            jSONObject.put("appList", new JSONArray());
            JSONArray optJSONArray2 = jSONObject2.optJSONArray("property");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                    String optString = optJSONObject2.optString("name");
                    if ("RO_SECURE".equals(optString) || "RO_BUILD_TAGS".equals(optString) || "RO_BUILD_TYPE".equals(optString)) {
                        jSONArray2.put(optJSONObject2);
                    }
                }
                jSONObject.put("property", jSONArray2);
            }
            JSONArray optJSONArray3 = jSONObject2.optJSONArray("exec");
            if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                JSONArray jSONArray3 = new JSONArray();
                for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                    JSONObject optJSONObject3 = optJSONArray3.optJSONObject(i3);
                    if ("FIND_SU_BY_WHICH".equals(optJSONObject3.optString("name"))) {
                        jSONArray3.put(optJSONObject3);
                    }
                }
                jSONObject.put("exec", jSONArray3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        m13235b(context, "megvii_risk_config_v5", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m13251a(android.content.Context r11, byte[] r12, float r13) {
        /*
            r0 = 0
            if (r11 == 0) goto Lc0
            if (r12 == 0) goto Lc0
            int r1 = r12.length
            if (r1 != 0) goto La
            goto Lc0
        La:
            int r1 = r12.length
            r2 = 90
            r3 = 0
            if (r1 > 0) goto L13
            r11 = -1
            r1 = r11
            goto L72
        L13:
            java.io.File r11 = r11.getFilesDir()
            java.io.File r1 = new java.io.File
            java.lang.String r4 = "hd_image"
            r1.<init>(r11, r4)
            boolean r11 = r1.exists()
            if (r11 == 0) goto L27
            r1.delete()
        L27:
            r1.mkdirs()
            boolean r11 = r1.canWrite()
            if (r11 == 0) goto L38
            java.io.File r11 = new java.io.File
            java.lang.String r4 = "hdImage"
            r11.<init>(r1, r4)
            goto L39
        L38:
            r11 = r0
        L39:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Exception -> L6d
            r1.<init>(r11)     // Catch: java.lang.Exception -> L6d
            r1.write(r12)     // Catch: java.lang.Exception -> L6d
            r1.close()     // Catch: java.lang.Exception -> L6d
            android.media.ExifInterface r1 = new android.media.ExifInterface     // Catch: java.lang.Exception -> L6d
            java.lang.String r4 = r11.getPath()     // Catch: java.lang.Exception -> L6d
            r1.<init>(r4)     // Catch: java.lang.Exception -> L6d
            java.lang.String r4 = "Orientation"
            r5 = 1
            int r1 = r1.getAttributeInt(r4, r5)     // Catch: java.lang.Exception -> L6d
            r4 = 3
            if (r1 == r4) goto L65
            r4 = 6
            if (r1 == r4) goto L63
            r4 = 8
            if (r1 == r4) goto L60
            r1 = r3
            goto L67
        L60:
            r1 = 270(0x10e, float:3.78E-43)
            goto L67
        L63:
            r1 = r2
            goto L67
        L65:
            r1 = 180(0xb4, float:2.52E-43)
        L67:
            r11.delete()     // Catch: java.lang.Exception -> L6b
            goto L72
        L6b:
            r11 = move-exception
            goto L6f
        L6d:
            r11 = move-exception
            r1 = r3
        L6f:
            r11.printStackTrace()
        L72:
            int r1 = r1 - r2
            int r11 = r12.length
            android.graphics.Bitmap r11 = android.graphics.BitmapFactory.decodeByteArray(r12, r3, r11)
            float r12 = (float) r1
            r1 = 0
            int r1 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r1 == 0) goto Lac
            if (r11 == 0) goto Lac
            android.graphics.Matrix r9 = new android.graphics.Matrix
            r9.<init>()
            int r1 = r11.getWidth()
            float r1 = (float) r1
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            int r3 = r11.getHeight()
            float r3 = (float) r3
            float r3 = r3 / r2
            r9.setRotate(r12, r1, r3)
            int r7 = r11.getWidth()
            int r8 = r11.getHeight()
            r5 = 0
            r6 = 0
            r10 = 1
            r4 = r11
            android.graphics.Bitmap r12 = android.graphics.Bitmap.createBitmap(r4, r5, r6, r7, r8, r9, r10)
            if (r11 == r12) goto Lac
            r11.recycle()
            r11 = r12
        Lac:
            if (r11 == 0) goto Lc0
            java.io.ByteArrayOutputStream r12 = new java.io.ByteArrayOutputStream
            r12.<init>()
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG
            r1 = 1120403456(0x42c80000, float:100.0)
            float r13 = r13 * r1
            int r13 = (int) r13
            r11.compress(r0, r13, r12)
            byte[] r0 = r12.toByteArray()
        Lc0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13251a(android.content.Context, byte[], float):byte[]");
    }

    /* renamed from: a */
    public static File m13244a(String str, List<LivenessFile> list, String str2, String str3, String str4) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("livenessType", str);
            JSONArray jSONArray = new JSONArray();
            for (LivenessFile livenessFile : list) {
                File file = new File(livenessFile.getPath());
                if (file.exists()) {
                    arrayList.add(file);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("length", file.length());
                    jSONObject2.put("fileType", livenessFile.getFileType());
                    jSONObject2.put("actionType", livenessFile.getActionType());
                    jSONObject2.put("isScreenRecord", livenessFile.isScreenRecord());
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("file", jSONArray);
            String jSONObject3 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            C5628v2.m12958b("RecordFinish", "jsonRecord=" + jSONObject3);
            File file2 = new File(str2);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2, str3);
            byte[] bytes = jSONObject3.getBytes();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length = bytes.length;
            byteArrayOutputStream.write(new byte[]{(byte) (length >>> 24), (byte) (length >>> 16), (byte) (length >>> 8), (byte) length});
            byteArrayOutputStream.write(bytes);
            byte[] bArr = new byte[102400];
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                FileInputStream fileInputStream = new FileInputStream((File) it.next());
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (Exception unused) {
                        }
                    }
                }
                fileInputStream.close();
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            byte[] encodeParameter = MegDelta.encodeParameter(true, str4, byteArrayOutputStream.toByteArray());
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 102400);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encodeParameter);
            while (true) {
                int read2 = byteArrayInputStream.read(bArr);
                if (read2 <= 0) {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                    return file3;
                }
                bufferedOutputStream.write(bArr, 0, read2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m13259a() {
        JSONObject jSONObject = new JSONObject();
        try {
            Context context = C5435f1.C5437b.f12597a.f12591b;
            int i = -1;
            Class<?> cls = C5665z0.m12886a().f13947a;
            int i2 = 0;
            i2 = 0;
            i2 = 0;
            if (cls != null) {
                try {
                    try {
                        i = ((Integer) cls.getMethod("checkCharggingLevel", Context.class).invoke(null, context)).intValue();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e2) {
                        e2.printStackTrace();
                    }
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
            }
            jSONObject.put("battery_power", i);
            Class<?> cls2 = C5665z0.m12886a().f13947a;
            if (cls2 != null) {
                try {
                    try {
                        try {
                            i2 = ((Boolean) cls2.getMethod("checkIsChargging", Context.class).invoke(null, context)).booleanValue();
                        } catch (InvocationTargetException e4) {
                            e4.printStackTrace();
                        }
                    } catch (IllegalAccessException e5) {
                        e5.printStackTrace();
                    }
                } catch (NoSuchMethodException e6) {
                    e6.printStackTrace();
                }
            } else {
                i2 = 1;
            }
            jSONObject.put("is_charging", i2);
            jSONObject.put("charging_type", C5665z0.m12886a().m12885a(context));
        } catch (JSONException e7) {
            e7.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0111 A[Catch: JSONException -> 0x0153, TryCatch #0 {JSONException -> 0x0153, blocks: (B:2:0x0000, B:4:0x000b, B:6:0x0018, B:8:0x0033, B:10:0x003a, B:15:0x0050, B:17:0x0056, B:19:0x0070, B:21:0x0084, B:23:0x008e, B:25:0x0097, B:27:0x009a, B:28:0x00a5, B:30:0x00a8, B:31:0x00b5, B:33:0x00c1, B:35:0x00c7, B:54:0x0102, B:55:0x0105, B:56:0x010a, B:58:0x0111, B:59:0x0116, B:61:0x0119, B:62:0x0121, B:64:0x0128, B:66:0x013a, B:67:0x0143, B:69:0x0147, B:70:0x014c), top: B:75:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0147 A[Catch: JSONException -> 0x0153, TryCatch #0 {JSONException -> 0x0153, blocks: (B:2:0x0000, B:4:0x000b, B:6:0x0018, B:8:0x0033, B:10:0x003a, B:15:0x0050, B:17:0x0056, B:19:0x0070, B:21:0x0084, B:23:0x008e, B:25:0x0097, B:27:0x009a, B:28:0x00a5, B:30:0x00a8, B:31:0x00b5, B:33:0x00c1, B:35:0x00c7, B:54:0x0102, B:55:0x0105, B:56:0x010a, B:58:0x0111, B:59:0x0116, B:61:0x0119, B:62:0x0121, B:64:0x0128, B:66:0x013a, B:67:0x0143, B:69:0x0147, B:70:0x014c), top: B:75:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x014c A[Catch: JSONException -> 0x0153, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0153, blocks: (B:2:0x0000, B:4:0x000b, B:6:0x0018, B:8:0x0033, B:10:0x003a, B:15:0x0050, B:17:0x0056, B:19:0x0070, B:21:0x0084, B:23:0x008e, B:25:0x0097, B:27:0x009a, B:28:0x00a5, B:30:0x00a8, B:31:0x00b5, B:33:0x00c1, B:35:0x00c7, B:54:0x0102, B:55:0x0105, B:56:0x010a, B:58:0x0111, B:59:0x0116, B:61:0x0119, B:62:0x0121, B:64:0x0128, B:66:0x013a, B:67:0x0143, B:69:0x0147, B:70:0x014c), top: B:75:0x0000 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m13258a(int r8, int r9, int[] r10, java.util.List<java.lang.Integer> r11, int r12, float r13, java.lang.String[] r14, int r15) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5527o2.m13258a(int, int, int[], java.util.List, int, float, java.lang.String[], int):java.lang.String");
    }

    /* renamed from: a */
    public static byte[] m13241a(byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        if (i3 == 90) {
            int i5 = i * i2;
            int i6 = (i5 * 3) / 2;
            byte[] bArr2 = new byte[i6];
            int i7 = 0;
            for (int i8 = 0; i8 < i; i8++) {
                for (int i9 = i2 - 1; i9 >= 0; i9--) {
                    bArr2[i7] = bArr[(i9 * i) + i8];
                    i7++;
                }
            }
            int i10 = i6 - 1;
            int i11 = i - 1;
            while (i11 > 0) {
                int i12 = i10;
                for (int i13 = 0; i13 < i2 / 2; i13++) {
                    int i14 = (i13 * i) + i5;
                    bArr2[i12] = bArr[i14 + i11];
                    int i15 = i12 - 1;
                    bArr2[i15] = bArr[i14 + (i11 - 1)];
                    i12 = i15 - 1;
                }
                i11 -= 2;
                i10 = i12;
            }
            return bArr2;
        } else if (i3 == 180) {
            int i16 = i * i2;
            int i17 = (i16 * 3) / 2;
            byte[] bArr3 = new byte[i17];
            for (int i18 = i16 - 1; i18 >= 0; i18--) {
                bArr3[i4] = bArr[i18];
                i4++;
            }
            for (int i19 = i17 - 1; i19 >= i16; i19 -= 2) {
                int i20 = i4 + 1;
                bArr3[i4] = bArr[i19 - 1];
                i4 = i20 + 1;
                bArr3[i20] = bArr[i19];
            }
            return bArr3;
        } else if (i3 != 270) {
            return bArr;
        } else {
            int i21 = i * i2;
            byte[] bArr4 = new byte[(i21 * 3) / 2];
            int i22 = i - 1;
            int i23 = 0;
            int i24 = i22;
            while (i24 >= 0) {
                int i25 = 0;
                int i26 = i23;
                for (int i27 = 0; i27 < i2; i27++) {
                    bArr4[i26] = bArr[i25 + i24];
                    i26++;
                    i25 += i;
                }
                i24--;
                i23 = i26;
            }
            int i28 = i21;
            while (i22 > 0) {
                int i29 = i21;
                int i30 = i28;
                for (int i31 = 0; i31 < i2 / 2; i31++) {
                    bArr4[i30] = bArr[(i22 - 1) + i29];
                    int i32 = i30 + 1;
                    bArr4[i32] = bArr[i29 + i22];
                    i30 = i32 + 1;
                    i29 += i;
                }
                i22 -= 2;
                i28 = i30;
            }
            return bArr4;
        }
    }
}
