package com.bytedance.pangle.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.Keep;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.pangle.log.ZeusLogger;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* renamed from: com.bytedance.pangle.provider.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3893a {
    /* renamed from: a */
    private static Uri m16744a(String str, Uri uri) {
        return m16743a(str, uri, (String) null);
    }

    /* renamed from: a */
    private static Uri m16743a(String str, Uri uri, String str2) {
        ContentProviderManager contentProviderManager = ContentProviderManager.getInstance();
        if (uri != null) {
            str2 = uri.getAuthority();
        }
        String pluginProcessNameByAuthority = contentProviderManager.getPluginProcessNameByAuthority(str2);
        String m16741a = C3894b.m16741a(pluginProcessNameByAuthority, str, uri);
        String encodeToString = TextUtils.isEmpty(m16741a) ? "" : Base64.encodeToString(m16741a.getBytes(), 10);
        String str3 = ContentProviderManager.getInstance().getSystemProviderInfoMap().get(pluginProcessNameByAuthority).f9277b;
        Uri parse = Uri.parse("content://" + str3 + File.separator + "proxy?provider_params=" + encodeToString);
        ZeusLogger.m16794d("Zeus/provider_pangle", "进程[processName=" + pluginProcessNameByAuthority + "] build provider 【 uri:" + parse + " 】");
        return parse;
    }

    @RequiresApi(api = 16)
    /* renamed from: a */
    public static Cursor m16748a(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal, String str3) {
        if (contentResolver == null) {
            return null;
        }
        try {
            try {
                return contentResolver.query(m16745a(uri, str3), strArr, str, strArr2, str2, cancellationSignal);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute query(6 params) !!!");
                return contentResolver.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (Exception e) {
                e = e;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#query(6 params) throw exception:", e);
                return null;
            } catch (IncompatibleClassChangeError e2) {
                e = e2;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#query(6 params) throw exception:", e);
                return null;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "not found host provider-ContentProviderManager#query(6 params) throw exception:", th);
            return null;
        }
    }

    @RequiresApi(api = 26)
    /* renamed from: a */
    public static Cursor m16749a(ContentResolver contentResolver, Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal, String str) {
        if (contentResolver == null) {
            return null;
        }
        try {
            try {
                return contentResolver.query(m16745a(uri, str), strArr, bundle, cancellationSignal);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute query(5 params) !!!");
                return contentResolver.query(uri, strArr, bundle, cancellationSignal);
            } catch (Exception e) {
                e = e;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#query(4 params) throw exception:", e);
                return null;
            } catch (IncompatibleClassChangeError e2) {
                e = e2;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#query(4 params) throw exception:", e);
                return null;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "not found host provider-ContentProviderManager#query(4 params) throw exception:", th);
            return null;
        }
    }

    /* renamed from: a */
    public static Cursor m16747a(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        if (contentResolver == null) {
            return null;
        }
        try {
            try {
                return contentResolver.query(m16745a(uri, str3), strArr, str, strArr2, str2);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute query(5 params) !!!");
                return contentResolver.query(uri, strArr, str, strArr2, str2);
            } catch (Exception e) {
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#query(5 params) throw exception:", e);
                return null;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "not found host provider-ContentProviderManager#query(5 params) throw exception:", th);
            return null;
        }
    }

    /* renamed from: a */
    public static String m16752a(ContentResolver contentResolver, Uri uri, String str) {
        if (contentResolver == null) {
            return null;
        }
        try {
            try {
                return contentResolver.getType(m16745a(uri, str));
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute getType !!!");
                return contentResolver.getType(uri);
            } catch (Exception e) {
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#getType throw exception:", e);
                return null;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "not found host provider-ContentProviderManager#getType throw exception:", th);
            return null;
        }
    }

    /* renamed from: a */
    public static Uri m16755a(ContentResolver contentResolver, Uri uri, ContentValues contentValues, String str) {
        if (contentResolver == null) {
            return null;
        }
        try {
            try {
                return contentResolver.insert(m16745a(uri, str), contentValues);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute insert !!!");
                return contentResolver.insert(uri, contentValues);
            } catch (Exception e) {
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#insert throw exception:", e);
                return null;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "not found host provider-ContentProviderManager#insert throw exception:", th);
            return null;
        }
    }

    /* renamed from: a */
    public static Uri m16756a(ContentResolver contentResolver, Uri uri, ContentValues contentValues, Bundle bundle, String str) {
        if (contentResolver == null) {
            return null;
        }
        try {
            try {
                return contentResolver.insert(m16745a(uri, str), contentValues, bundle);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute insert !!!");
                return contentResolver.insert(uri, contentValues, bundle);
            } catch (Exception e) {
                e = e;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#insert throw exception:", e);
                return null;
            } catch (IncompatibleClassChangeError e2) {
                e = e2;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#insert throw exception:", e);
                return null;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "not found host provider-ContentProviderManager#insert throw exception:", th);
            return null;
        }
    }

    /* renamed from: a */
    public static int m16750a(ContentResolver contentResolver, Uri uri, String str, String[] strArr, String str2) {
        if (contentResolver == null) {
            return -1;
        }
        try {
            try {
                return contentResolver.delete(m16745a(uri, str2), str, strArr);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute delete !!!");
                return contentResolver.delete(uri, str, strArr);
            } catch (Exception e) {
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#delete throw exception:", e);
                return -1;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#delete throw exception:", th);
            return -1;
        }
    }

    /* renamed from: a */
    public static int m16753a(ContentResolver contentResolver, Uri uri, Bundle bundle, String str) {
        if (contentResolver == null) {
            return -1;
        }
        try {
            try {
                return contentResolver.delete(m16745a(uri, str), bundle);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute delete !!!");
                return contentResolver.delete(uri, bundle);
            } catch (Exception e) {
                e = e;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#delete throw exception:", e);
                return -1;
            } catch (IncompatibleClassChangeError e2) {
                e = e2;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#delete throw exception:", e);
                return -1;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#delete throw exception:", th);
            return -1;
        }
    }

    /* renamed from: a */
    public static int m16754a(ContentResolver contentResolver, Uri uri, ContentValues contentValues, String str, String[] strArr, String str2) {
        if (contentResolver == null) {
            return 0;
        }
        try {
            try {
                return contentResolver.update(m16745a(uri, str2), contentValues, str, strArr);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute update !!!");
                return contentResolver.update(uri, contentValues, str, strArr);
            } catch (Exception e) {
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#update throw exception:", e);
                return 0;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#update throw exception:", th);
            return 0;
        }
    }

    /* renamed from: b */
    public static int m16742b(ContentResolver contentResolver, Uri uri, ContentValues contentValues, Bundle bundle, String str) {
        if (contentResolver == null) {
            return 0;
        }
        try {
            try {
                return contentResolver.update(m16745a(uri, str), contentValues, bundle);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute update !!!");
                return contentResolver.update(uri, contentValues, bundle);
            } catch (Exception e) {
                e = e;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#update throw exception:", e);
                return 0;
            } catch (IncompatibleClassChangeError e2) {
                e = e2;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#update throw exception:", e);
                return 0;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#update throw exception:", th);
            return 0;
        }
    }

    /* renamed from: a */
    public static Bundle m16751a(ContentResolver contentResolver, Uri uri, String str, String str2, Bundle bundle, String str3) {
        if (contentResolver == null || uri == null) {
            return null;
        }
        try {
            try {
                Uri m16745a = m16745a(uri, str3);
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString("provider_params", uri.getAuthority());
                bundle.putString(ContentProviderManager.PROVIDER_PROXY_URI, m16745a.toString());
                return contentResolver.call(m16745a, str, str2, bundle);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute call !!!");
                return contentResolver.call(uri, str, str2, bundle);
            } catch (Exception e) {
                e = e;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#call throw exception:", e);
                return null;
            } catch (IncompatibleClassChangeError e2) {
                e = e2;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#call throw exception:", e);
                return null;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#call throw exception:", th);
            return null;
        }
    }

    /* renamed from: a */
    public static Bundle m16746a(ContentResolver contentResolver, String str, String str2, String str3, Bundle bundle, String str4) {
        if (contentResolver == null) {
            return null;
        }
        String str5 = ContentProviderManager.getInstance().getSystemProviderInfoMap().get(ContentProviderManager.getInstance().getPluginProcessNameByAuthority(str)).f9277b;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("provider_params", str);
        bundle.putString(ContentProviderManager.PROVIDER_PROXY_URI, m16743a(str4, (Uri) null, str).toString());
        try {
            try {
                return contentResolver.call(str5, str2, str3, bundle);
            } catch (IllegalArgumentException unused) {
                ZeusLogger.m16788w("Zeus/provider_pangle", "not found plugin provider, but found host ContentResolver execute call !!!");
                return contentResolver.call(str, str2, str3, bundle);
            } catch (Exception e) {
                e = e;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#call throw exception:", e);
                return null;
            } catch (IncompatibleClassChangeError e2) {
                e = e2;
                ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#call throw exception:", e);
                return null;
            }
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus/provider_pangle", "ContentProviderManager#call throw exception:", th);
            return null;
        }
    }

    /* renamed from: a */
    public static Uri m16745a(Uri uri, String str) {
        if (ContentProviderManager.getInstance().isPluginProvider(uri)) {
            Uri m16744a = m16744a(str, uri);
            ZeusLogger.m16794d("Zeus/provider_pangle", "ContentProviderManager#buildProxyUri-->proxyUri=".concat(String.valueOf(m16744a)));
            return m16744a;
        }
        return uri;
    }
}
