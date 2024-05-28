package com.baidu.license.util;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.baidu.license.INotProguard;
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
    public static int mNetworkClass = -1;
    public static boolean sIsNeedRefreshCache = true;

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0075, code lost:
        if (r8.equals("uniwap") != false) goto L43;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String checkNetworkType(android.content.Context r8) {
        /*
            java.lang.String r0 = "connectivity"
            r1 = 0
            java.lang.Object r0 = r8.getSystemService(r0)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r0 == 0) goto L87
            boolean r2 = r0.isAvailable()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r2 != 0) goto L16
            goto L87
        L16:
            int r2 = r0.getType()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            r3 = 1
            if (r2 != r3) goto L20
            java.lang.String r8 = "other"
            return r8
        L20:
            if (r2 != 0) goto L7f
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            android.net.Uri r3 = com.baidu.license.util.NetHelper.PREFERRED_APN_URI     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r1 == 0) goto L54
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            java.lang.String r8 = "user"
            int r8 = r1.getColumnIndex(r8)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            java.lang.String r8 = r1.getString(r8)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            boolean r2 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r2 != 0) goto L54
            java.lang.String r2 = "ctwap"
            boolean r8 = r2.startsWith(r8)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r8 == 0) goto L54
            java.lang.String r8 = "ctwap"
            r1.close()     // Catch: java.lang.Exception -> L53
        L53:
            return r8
        L54:
            java.lang.String r8 = r0.getExtraInfo()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r8 == 0) goto L7f
            java.lang.String r8 = r8.toLowerCase()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            java.lang.String r0 = "cmwap"
            boolean r0 = r8.equals(r0)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r0 != 0) goto L77
            java.lang.String r0 = "3gwap"
            boolean r0 = r8.equals(r0)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r0 != 0) goto L77
            java.lang.String r0 = "uniwap"
            boolean r0 = r8.equals(r0)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L90
            if (r0 == 0) goto L7f
        L77:
            if (r1 == 0) goto L7c
            r1.close()     // Catch: java.lang.Exception -> L7c
        L7c:
            return r8
        L7d:
            r8 = move-exception
            goto L8a
        L7f:
            if (r1 == 0) goto L84
            r1.close()     // Catch: java.lang.Exception -> L84
        L84:
            java.lang.String r8 = "other"
            return r8
        L87:
            java.lang.String r8 = "other"
            return r8
        L8a:
            if (r1 == 0) goto L8f
            r1.close()     // Catch: java.lang.Exception -> L8f
        L8f:
            throw r8
        L90:
            java.lang.String r8 = "other"
            if (r1 == 0) goto L97
            r1.close()     // Catch: java.lang.Exception -> L97
        L97:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.license.util.NetHelper.checkNetworkType(android.content.Context):java.lang.String");
    }

    public static Proxy createProxy(String str, int i) {
        InetAddress byName;
        Matcher matcher = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})").matcher(str);
        if (matcher != null && matcher.find()) {
            byName = InetAddress.getByAddress(str, new byte[]{(byte) Integer.parseInt(matcher.group(1)), (byte) Integer.parseInt(matcher.group(2)), (byte) Integer.parseInt(matcher.group(3)), (byte) Integer.parseInt(matcher.group(4))});
        } else {
            byName = InetAddress.getByName(str);
        }
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(byName, i));
    }

    public static void exit() {
        Process.killProcess(Process.myPid());
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
                case 6:
                    return 3;
                case 7:
                    return 2;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    return 3;
                case 13:
                case 14:
                case 15:
                    return 4;
            }
            return 0;
        }
        return 1;
    }

    public static int getMobileNetworkClassCache(Context context) {
        if (mNetworkClass == -1 || sIsNeedRefreshCache) {
            mNetworkClass = getMobileNetworkClass(context);
            sIsNeedRefreshCache = false;
        }
        return mNetworkClass;
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
                                if (!cursor.isClosed()) {
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
            }
            return null;
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    public static boolean is3GConnected(Context context) {
        ConnectivityManager connectivityManager;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || connectivityManager.getActiveNetworkInfo() == null || getMobileNetworkClass(context) != 3) ? false : true;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager;
        if (context != null && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isConnected();
                }
            } catch (ClassCastException | NullPointerException | StringIndexOutOfBoundsException unused) {
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
            }
        }
        return false;
    }

    public static void postDelayed(Runnable runnable, int i) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (i == 0) {
            runnable.run();
        } else {
            handler.postDelayed(runnable, i);
        }
    }

    public static void updateNetworkClassCache() {
        sIsNeedRefreshCache = true;
    }
}
