package com.baidu.cloud.license.util;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.baidu.cloud.license.INotProguard;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NetHelper implements INotProguard {
    public static final String CMWAP = "cmwap";
    public static final String CTWAP = "ctwap";
    public static final String OTHER = "other";
    public static final Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
    public static final int TYPE_CM_CU_WAP = 4;
    public static final int TYPE_CT_WAP = 5;
    public static final int TYPE_NET_WORK_DISABLED = 0;
    public static final int TYPE_OTHER_NET = 6;
    public static final String UNIWAP = "uniwap";
    public static final String WAP_3G = "3gwap";
    private static int mNetworkClass = -1;
    private static boolean sIsNeedRefreshCache = true;

    public static void updateNetworkClassCache() {
        sIsNeedRefreshCache = true;
    }

    public static int getMobileNetworkClassCache(Context context) {
        if (mNetworkClass == -1 || sIsNeedRefreshCache) {
            mNetworkClass = getMobileNetworkClass(context);
            sIsNeedRefreshCache = false;
        }
        return mNetworkClass;
    }

    public static int getMobileNetworkClass(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        NetworkInfo networkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return 0;
        }
        if (activeNetworkInfo.getType() != 1 && activeNetworkInfo.getType() != 9) {
            if (activeNetworkInfo.getType() != 0 || (networkInfo = connectivityManager.getNetworkInfo(0)) == null) {
                return 0;
            }
            switch (networkInfo.getSubtype()) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 2;
                case 5:
                    return 3;
                case 6:
                    return 3;
                case 7:
                    return 2;
                case 8:
                    return 3;
                case 9:
                    return 3;
                case 10:
                    return 3;
                case 11:
                    return 3;
                case 12:
                    return 3;
                case 13:
                    return 4;
                case 14:
                    return 4;
                case 15:
                    return 4;
            }
            return 0;
        }
        return 1;
    }

    public static void postDelayed(Runnable runnable, int i) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (i == 0) {
            runnable.run();
        } else {
            handler.postDelayed(runnable, i);
        }
    }

    public static void exit() {
        Process.killProcess(Process.myPid());
    }

    private static Proxy createProxy(String str, int i) {
        InetAddress byName;
        Matcher matcher = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})").matcher(str);
        if (matcher != null && matcher.find()) {
            byName = InetAddress.getByAddress(str, new byte[]{(byte) Integer.parseInt(matcher.group(1)), (byte) Integer.parseInt(matcher.group(2)), (byte) Integer.parseInt(matcher.group(3)), (byte) Integer.parseInt(matcher.group(4))});
        } else {
            byName = InetAddress.getByName(str);
        }
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(byName, i));
    }

    public static Proxy getProxy(Context context) {
        Throwable th;
        Cursor cursor;
        try {
            if (!isWifiConnected(context) && isNetworkConnected(context)) {
                cursor = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToNext()) {
                            String string = cursor.getString(cursor.getColumnIndex("proxy"));
                            int i = cursor.getInt(cursor.getColumnIndex("port"));
                            if (string != null && string.trim().length() > 0) {
                                if (i == -1) {
                                    i = 80;
                                }
                                Proxy createProxy = createProxy(string, i);
                                if (cursor != null && !cursor.isClosed()) {
                                    try {
                                        cursor.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                return createProxy;
                            }
                        }
                    } catch (Exception unused) {
                        if (cursor != null && !cursor.isClosed()) {
                            try {
                                cursor.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null && !cursor.isClosed()) {
                            try {
                                cursor.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                if (cursor != null && !cursor.isClosed()) {
                    try {
                        cursor.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                return null;
            }
            return null;
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager;
        if (context != null && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isConnected();
                }
            } catch (ClassCastException unused) {
                return true;
            } catch (NullPointerException unused2) {
                return true;
            } catch (StringIndexOutOfBoundsException unused3) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager;
        int type;
        if (context != null && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && ((type = activeNetworkInfo.getType()) == 1 || type == 9)) {
                    return activeNetworkInfo.isConnected();
                }
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static boolean is3GConnected(Context context) {
        ConnectivityManager connectivityManager;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || connectivityManager.getActiveNetworkInfo() == null || getMobileNetworkClass(context) != 3) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0077, code lost:
        if (r8.equals("uniwap") != false) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String checkNetworkType(android.content.Context r8) {
        /*
            r0 = 0
            java.lang.String r1 = "connectivity"
            java.lang.Object r1 = r8.getSystemService(r1)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r1 == 0) goto L87
            boolean r2 = r1.isAvailable()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r2 != 0) goto L16
            goto L87
        L16:
            int r2 = r1.getType()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            r3 = 1
            if (r2 != r3) goto L20
            java.lang.String r8 = "other"
            return r8
        L20:
            if (r2 != 0) goto L7f
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            android.net.Uri r3 = com.baidu.cloud.license.util.NetHelper.PREFERRED_APN_URI     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r0 == 0) goto L56
            r0.moveToFirst()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            java.lang.String r8 = "user"
            int r8 = r0.getColumnIndex(r8)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            java.lang.String r8 = r0.getString(r8)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            boolean r2 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r2 != 0) goto L56
            java.lang.String r2 = "ctwap"
            boolean r8 = r2.startsWith(r8)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r8 == 0) goto L56
            java.lang.String r8 = "ctwap"
            if (r0 == 0) goto L55
            r0.close()     // Catch: java.lang.Exception -> L55
        L55:
            return r8
        L56:
            java.lang.String r8 = r1.getExtraInfo()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r8 == 0) goto L7f
            java.lang.String r8 = r8.toLowerCase()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            java.lang.String r1 = "cmwap"
            boolean r1 = r8.equals(r1)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r1 != 0) goto L79
            java.lang.String r1 = "3gwap"
            boolean r1 = r8.equals(r1)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r1 != 0) goto L79
            java.lang.String r1 = "uniwap"
            boolean r1 = r8.equals(r1)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L91
            if (r1 == 0) goto L7f
        L79:
            if (r0 == 0) goto L7e
            r0.close()     // Catch: java.lang.Exception -> L7e
        L7e:
            return r8
        L7f:
            if (r0 == 0) goto L84
            r0.close()     // Catch: java.lang.Exception -> L84
        L84:
            java.lang.String r8 = "other"
            return r8
        L87:
            java.lang.String r8 = "other"
            return r8
        L8a:
            r8 = move-exception
            if (r0 == 0) goto L90
            r0.close()     // Catch: java.lang.Exception -> L90
        L90:
            throw r8
        L91:
            java.lang.String r8 = "other"
            if (r0 == 0) goto L98
            r0.close()     // Catch: java.lang.Exception -> L98
        L98:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.license.util.NetHelper.checkNetworkType(android.content.Context):java.lang.String");
    }
}
