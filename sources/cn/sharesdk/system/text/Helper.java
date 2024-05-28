package cn.sharesdk.system.text;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.ShareSDKFileProvider;
import cn.sharesdk.system.text.login.LoginActionListener;
import cn.sharesdk.system.text.login.gui.InputPhoneNumPage;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.ReflectHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Helper {

    /* renamed from: a */
    private static Helper f3041a;

    /* renamed from: a */
    public static Helper m21579a() {
        if (f3041a == null) {
            f3041a = new Helper();
        }
        return f3041a;
    }

    /* renamed from: a */
    public void m21575a(String str, String str2, String str3, String str4, ActionListener actionListener) {
        if (str4 == null || !new File(str4).exists()) {
            try {
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
                intent.setPackage(m21574b());
                intent.putExtra("sms_body", str3);
                intent.setFlags(268435456);
                MobSDK.getContext().startActivity(intent);
                return;
            } catch (Throwable th) {
                if (actionListener != null) {
                    actionListener.onError(th);
                    return;
                }
                return;
            }
        }
        File file = new File(str4);
        if (2147483647L <= file.length()) {
            try {
                file = m21577a(file);
            } catch (Throwable th2) {
                if (actionListener != null) {
                    actionListener.onError(th2);
                    return;
                }
                return;
            }
        }
        try {
            try {
                try {
                    MobSDK.getContext().startActivity(m21576a(str, str2, str3, file));
                } catch (Throwable unused) {
                    MobSDK.getContext().startActivity(m21573b(str, str2, str3, file));
                }
            } catch (Throwable unused2) {
                Intent m21573b = m21573b(str, str2, str3, file);
                m21573b.setPackage(m21574b());
                MobSDK.getContext().startActivity(m21573b);
            }
        } catch (Throwable th3) {
            if (actionListener != null) {
                actionListener.onError(th3);
            }
        }
    }

    /* renamed from: b */
    public void m21572b(String str, String str2, String str3, String str4, ActionListener actionListener) {
        if (str4 == null || !new File(str4).exists()) {
            try {
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
                if (Build.VERSION.SDK_INT < 29) {
                    intent.setPackage(m21574b());
                }
                intent.putExtra("sms_body", str3);
                intent.setFlags(268435456);
                MobSDK.getContext().startActivity(intent);
                return;
            } catch (Throwable th) {
                try {
                    Intent intent2 = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
                    intent2.putExtra("sms_body", str3);
                    intent2.setFlags(268435456);
                    MobSDK.getContext().startActivity(intent2);
                    return;
                } catch (Throwable unused) {
                    if (actionListener != null) {
                        actionListener.onError(th);
                        return;
                    }
                    return;
                }
            }
        }
        File file = new File(str4);
        if (2147483647L <= file.length()) {
            try {
                file = m21577a(file);
            } catch (Throwable th2) {
                if (actionListener != null) {
                    actionListener.onError(th2);
                    return;
                }
                return;
            }
        }
        try {
            try {
                try {
                    MobSDK.getContext().startActivity(m21576a(str, str2, str3, file));
                } catch (Throwable th3) {
                    if (actionListener != null) {
                        actionListener.onError(th3);
                    }
                }
            } catch (Throwable unused2) {
                Intent m21571c = m21571c(str, str2, str3, file);
                m21571c.setPackage(m21574b());
                MobSDK.getContext().startActivity(m21571c);
            }
        } catch (Throwable unused3) {
            MobSDK.getContext().startActivity(m21571c(str, str2, str3, file));
        }
    }

    /* renamed from: a */
    private File m21577a(File file) throws Throwable {
        File file2;
        double length = (file.length() / 2.147483647E9d) - 1.0d;
        do {
            file2 = new File(file.getParentFile(), "mms_tmp_file.jpg");
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            length += 1.0d;
            Bitmap bitmap = BitmapHelper.getBitmap(file, (int) Math.ceil(length));
            if (bitmap == null || bitmap.isRecycled()) {
                throw new RuntimeException("Failed to compress image file");
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        } while (file2.length() > 2147483647L);
        return file2;
    }

    /* renamed from: a */
    private Intent m21576a(String str, String str2, String str3, File file) {
        Intent intent = new Intent("android.intent.action.SEND_MSG");
        intent.putExtra("address", str);
        intent.setFlags(268435456);
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("subject", str2);
        intent.putExtra("sms_body", str3);
        intent.setType("text/plain");
        String absolutePath = file.getAbsolutePath();
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(absolutePath);
        if (contentTypeFor == null || contentTypeFor.length() <= 0) {
            String lowerCase = absolutePath.trim().toLowerCase();
            if (lowerCase.endsWith("png")) {
                contentTypeFor = "image/png";
            } else if (lowerCase.endsWith("jpg") || lowerCase.endsWith("jpeg")) {
                contentTypeFor = "image/jpeg";
            } else if (lowerCase.endsWith("gif")) {
                contentTypeFor = "image/gif";
            } else {
                contentTypeFor = lowerCase.endsWith("mp4") ? "video/mp4" : "*/*";
            }
        }
        Context context = MobSDK.getContext();
        intent.putExtra("android.intent.extra.STREAM", ShareSDKFileProvider.m21729a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file));
        intent.setType(contentTypeFor);
        return intent;
    }

    /* renamed from: b */
    private Intent m21573b(String str, String str2, String str3, File file) {
        Intent intent = new Intent("android.intent.action.SEND", Uri.parse("mms://"));
        intent.putExtra("address", str);
        intent.setFlags(268435456);
        if (str2 != null) {
            intent.putExtra("android.intent.extra.SUBJECT", str2);
            intent.putExtra("subject", str2);
        }
        if (str3 != null) {
            intent.putExtra("sms_body", str3);
            intent.putExtra("android.intent.extra.TEXT", str3);
            intent.setType("text/plain");
        }
        String absolutePath = file.getAbsolutePath();
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(absolutePath);
        if (contentTypeFor == null || contentTypeFor.length() <= 0) {
            contentTypeFor = absolutePath.trim().toLowerCase().endsWith("mp4") ? "video/mp4" : "video/*";
        }
        Context context = MobSDK.getContext();
        intent.putExtra("android.intent.extra.STREAM", ShareSDKFileProvider.m21729a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file));
        intent.setType(contentTypeFor);
        return intent;
    }

    /* renamed from: c */
    private Intent m21571c(String str, String str2, String str3, File file) {
        Intent intent = new Intent("android.intent.action.SEND", Uri.parse("mms://"));
        intent.putExtra("address", str);
        intent.setFlags(268435456);
        if (str2 != null) {
            intent.putExtra("android.intent.extra.SUBJECT", str2);
            intent.putExtra("subject", str2);
        }
        if (str3 != null) {
            intent.putExtra("sms_body", str3);
            intent.putExtra("android.intent.extra.TEXT", str3);
            intent.setType("text/plain");
        }
        String absolutePath = file.getAbsolutePath();
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(absolutePath);
        if (contentTypeFor == null || contentTypeFor.length() <= 0) {
            String lowerCase = absolutePath.trim().toLowerCase();
            if (lowerCase.endsWith("png")) {
                contentTypeFor = "image/png";
            } else if (lowerCase.endsWith("jpg") || lowerCase.endsWith("jpeg")) {
                contentTypeFor = "image/jpeg";
            } else {
                contentTypeFor = lowerCase.endsWith("gif") ? "image/gif" : "*/*";
            }
        }
        Context context = MobSDK.getContext();
        intent.putExtra("android.intent.extra.STREAM", ShareSDKFileProvider.m21729a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file));
        intent.setType(contentTypeFor);
        return intent;
    }

    /* renamed from: b */
    private String m21574b() {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                ReflectHelper.importClass("Telephony.Sms", "android.provider.Telephony$Sms");
                return (String) ReflectHelper.invokeStaticMethod("Telephony.Sms", "getDefaultSmsPackage", MobSDK.getContext());
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
                return "com.android.mms";
            }
        }
        return "com.android.mms";
    }

    /* renamed from: a */
    public void m21578a(LoginActionListener loginActionListener, String[] strArr) {
        InputPhoneNumPage inputPhoneNumPage = new InputPhoneNumPage();
        inputPhoneNumPage.setLoginActionListener(loginActionListener);
        boolean z = false;
        if (strArr != null && strArr[0] != null) {
            z = Boolean.parseBoolean(strArr[0]);
        }
        inputPhoneNumPage.IsShowCountryPage(z);
        inputPhoneNumPage.show(MobSDK.getContext());
    }
}
