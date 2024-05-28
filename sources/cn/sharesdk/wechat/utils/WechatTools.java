package cn.sharesdk.wechat.utils;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WechatTools {

    /* renamed from: a */
    private static volatile WechatTools f3300a;

    /* renamed from: a */
    public static WechatTools m21233a() {
        if (f3300a == null) {
            synchronized (WechatTools.class) {
                if (f3300a == null) {
                    f3300a = new WechatTools();
                }
            }
        }
        return f3300a;
    }

    /* renamed from: a */
    public int m21229a(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        File file = new File(str);
        if (!file.exists()) {
            if (MobSDK.getContext() == null || !str.startsWith("content")) {
                return 0;
            }
            try {
                return m21232a(MobSDK.getContext().getContentResolver(), Uri.parse(str));
            } catch (Exception unused) {
                return 0;
            }
        }
        return (int) file.length();
    }

    /* renamed from: a */
    private static int m21232a(ContentResolver contentResolver, Uri uri) {
        SSDKLog.m21740b().m21744a("MicroMsg.SDK.Util", "getFileSize with content url");
        if (contentResolver == null || uri == null) {
            SSDKLog.m21740b().m21744a("MicroMsg.SDK.Util", "getFileSize fail, resolver or uri is null");
            return 0;
        }
        InputStream inputStream = null;
        try {
            try {
                InputStream openInputStream = contentResolver.openInputStream(uri);
                if (openInputStream == null) {
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (IOException e) {
                            SSDKLog m21740b = SSDKLog.m21740b();
                            m21740b.m21744a("ShareSDK", "WechatTools exception" + e);
                        }
                    }
                    return 0;
                }
                int available = openInputStream.available();
                if (openInputStream != null) {
                    try {
                        openInputStream.close();
                    } catch (IOException e2) {
                        SSDKLog m21740b2 = SSDKLog.m21740b();
                        m21740b2.m21744a("ShareSDK", "WechatTools exception" + e2);
                    }
                }
                return available;
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        SSDKLog m21740b3 = SSDKLog.m21740b();
                        m21740b3.m21744a("ShareSDK", "WechatTools exception" + e3);
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            SSDKLog m21740b4 = SSDKLog.m21740b();
            m21740b4.m21744a("MicroMsg.SDK.Util", "getFileSize fail, " + e4.getMessage());
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    SSDKLog m21740b5 = SSDKLog.m21740b();
                    m21740b5.m21744a("ShareSDK", "WechatTools exception" + e5);
                }
            }
            return 0;
        }
    }

    /* renamed from: a */
    public static String m21231a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return bundle.getString(str);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("getStringExtra exception:" + e.getMessage(), new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    public static int m21230a(Bundle bundle, String str, int i) {
        if (bundle == null) {
            return i;
        }
        try {
            return bundle.getInt(str, i);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("getIntExtra exception:" + e.getMessage(), new Object[0]);
            return i;
        }
    }
}
