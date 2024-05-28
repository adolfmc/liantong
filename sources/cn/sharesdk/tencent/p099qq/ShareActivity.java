package cn.sharesdk.tencent.p099qq;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.LinearLayout;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.tencent.qq.ShareActivity */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareActivity extends FakeActivity {
    private static final List<String> OPEN_MINI_APP_TYPES = Arrays.asList("develop", "trial", "release");
    private static final String QQ_PACKAGE_NAME = "com.tencent.mobileqq";
    private static final int START_ACTIVITY_CODE = 256;
    private String appId;
    private PlatformActionListener listener;
    private Platform platform;
    private String uriScheme;

    public void setPlatformActionListener(Platform platform, PlatformActionListener platformActionListener) {
        this.platform = platform;
        this.listener = platformActionListener;
    }

    public void setAppId(String str) {
        this.uriScheme = "tencent" + str;
        this.appId = str;
    }

    /* JADX WARN: Type inference failed for: r16v0, types: [cn.sharesdk.tencent.qq.ShareActivity$1] */
    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        String str;
        SSDKLog.m21740b().m21744a("ShareSDK", " QQ ShareActivity onCreate");
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m21740b().m21742a(e);
        }
        Bundle extras = this.activity.getIntent().getExtras();
        final String string = extras.getString("title");
        final String string2 = extras.getString("titleUrl");
        final String string3 = extras.getString("summary");
        final String string4 = extras.getString("imageUrl");
        final String string5 = extras.getString("musicUrl");
        final String appName = DeviceHelper.getInstance(this.activity).getAppName();
        final String string6 = extras.getString("appId");
        final int i = extras.getInt("hidden");
        String string7 = extras.getString("imagePath");
        final String string8 = extras.getString("mini_program_appid");
        final String string9 = extras.getString("mini_program_path");
        final String string10 = extras.getString("mini_program_type");
        final int i2 = extras.getInt("share_type");
        if (i2 == 15) {
            if (resultCompareVersion("8.0.8") < 0) {
                PlatformActionListener platformActionListener = this.listener;
                if (platformActionListener != null) {
                    platformActionListener.onError(this.platform, 9, new Throwable("808以下不支持分享小程序"));
                    return;
                }
                return;
            }
            shareMiniProgram(string, string2, string3, string4, string7, string5, appName, string6, i, string8, string9, string10, i2);
            if (this.listener != null) {
                this.listener.onComplete(this.platform, 9, new HashMap<>());
            }
        } else if (i2 == 22) {
            if (TextUtils.isEmpty(string8)) {
                PlatformActionListener platformActionListener2 = this.listener;
                if (platformActionListener2 != null) {
                    platformActionListener2.onError(this.platform, 9, new Throwable("Result is MINIAPP_ID_EMPTY : -1"));
                }
            } else if (!OPEN_MINI_APP_TYPES.contains(string10)) {
                PlatformActionListener platformActionListener3 = this.listener;
                if (platformActionListener3 != null) {
                    platformActionListener3.onError(this.platform, 9, new Throwable("Result is MINIAPP_VERSION_WRONG : -7"));
                }
            } else if (resultCompareVersion("8.1.8") < 0) {
                PlatformActionListener platformActionListener4 = this.listener;
                if (platformActionListener4 != null) {
                    platformActionListener4.onError(this.platform, 9, new Throwable("Result is MINIAPP_SHOULD_DOWNLOAD : -2"));
                }
            } else {
                openMiniProgram(string8, string9, string10);
                if (this.listener != null) {
                    this.listener.onComplete(this.platform, 9, new HashMap<>());
                }
            }
        } else {
            if (!TextUtils.isEmpty(string8) || !TextUtils.isEmpty(string9) || !TextUtils.isEmpty(string10) || !TextUtils.isEmpty(string) || !TextUtils.isEmpty(string3) || !TextUtils.isEmpty(string2)) {
                str = string10;
            } else if ((TextUtils.isEmpty(string7) || !new File(string7).exists()) && !TextUtils.isEmpty(string4)) {
                new Thread() { // from class: cn.sharesdk.tencent.qq.ShareActivity.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            String downloadBitmap = BitmapHelper.downloadBitmap(ShareActivity.this.activity, string4);
                            SSDKLog m21740b = SSDKLog.m21740b();
                            m21740b.m21744a("ShareSDK", " QQ ShareActivity path: " + downloadBitmap);
                            ShareActivity.this.advancedShare(string, string2, string3, string4, downloadBitmap, string5, appName, string6, i, string8, string9, string10, i2);
                        }
                    }
                }.start();
                return;
            } else {
                str = string10;
            }
            advancedShare(string, string2, string3, string4, string7, string5, appName, string6, i, string8, string9, str, i2);
        }
    }

    private void openMiniProgram(String str, String str2, String str3) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
                throw new RuntimeException("miniAppId or miniPath or miniType is null");
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(String.format("mqqapi://connect_miniapp/launch?app_type=%1$s&mini_app_id=%2$s&version=1&src_type=app&app_name=%3$s&app_id=%4$s&src_id=%5$s&mini_app_path=%6$s&mini_app_type=%7$s&open_id=%8$s", "mini_program_or_game", str, encodeToString(getActivitySourceDir(this.activity)), encodeToString(this.appId), "21", encodeToString(str2), encodeToString(str3), encodeToString(""))));
            intent.putExtra("pkg_name", this.activity.getPackageName());
            this.activity.startActivity(intent);
            if (Build.VERSION.SDK_INT >= 28) {
                SSDKLog.m21740b().m21744a("ShareSDK", " QQ ShareActivity Build.VERSION.SDK_INT >= 28 activity.finish ");
                this.activity.finish();
            }
        } catch (Throwable th) {
            PlatformActionListener platformActionListener = this.listener;
            if (platformActionListener != null) {
                platformActionListener.onError(this.platform, 9, th);
            }
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("ShareSDK", " QQ openMiniProgram catch " + th);
            this.activity.finish();
        }
    }

    public static String encodeToString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 2);
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("QQ convert2Base64String exception: " + th, new Object[0]);
            return "";
        }
    }

    public static String getActivitySourceDir(Activity activity) {
        try {
            ApplicationInfo m21713c = AppUtils.m21713c(activity.getApplicationContext().getPackageName(), 128);
            if (m21713c == null) {
                return null;
            }
            return m21713c.sourceDir;
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("QQ NameNotFoundException " + th, new Object[0]);
            return null;
        }
    }

    private void shareMiniProgram(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, String str11, int i2) {
        advancedShare(str, str2, str3, str4, str5, str6, str7, str8, i, str9, str10, str11, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void advancedShare(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, String str11, int i2) {
        String shareScheme = getShareScheme(str, str2, str3, str4, str5, str6, str7, str8, i, str9, str10, str11, i2);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(shareScheme));
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("ShareSDK", " QQ ShareActivity advancedShare scheme: " + shareScheme);
        try {
            int[] qQClientVersion = getQQClientVersion();
            ReceiveActivity.setUriScheme(this.uriScheme);
            ReceiveActivity.setPlatformActionListener(this.listener);
            if (qQClientVersion.length <= 1 || (qQClientVersion[0] < 4 && qQClientVersion[1] < 6)) {
                intent.putExtra("key_request_code", 0);
            }
            intent.putExtra("pkg_name", this.activity.getPackageName());
            this.activity.startActivityForResult(intent, 256);
            if (Build.VERSION.SDK_INT >= 28) {
                SSDKLog.m21740b().m21744a("ShareSDK", " QQ ShareActivity Build.VERSION.SDK_INT >= 28 activity.finish ");
                this.activity.finish();
            }
        } catch (Throwable th) {
            PlatformActionListener platformActionListener = this.listener;
            if (platformActionListener != null) {
                platformActionListener.onError(this.platform, 9, th);
            }
            SSDKLog m21740b2 = SSDKLog.m21740b();
            m21740b2.m21744a("ShareSDK", " QQ ShareActivity catch " + th);
            this.activity.finish();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @android.annotation.SuppressLint({"WrongConstant"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getShareScheme(java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, int r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, int r25) {
        /*
            Method dump skipped, instructions count: 908
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.tencent.p099qq.ShareActivity.getShareScheme(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, int):java.lang.String");
    }

    private int[] getQQClientVersion() {
        String str;
        try {
            str = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0).versionName;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            str = "0";
        }
        String[] split = str.split("\\.");
        int[] iArr = new int[split.length];
        for (int i = 0; i < iArr.length; i++) {
            try {
                iArr[i] = ResHelper.parseInt(split[i]);
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21742a(th2);
                iArr[i] = 0;
            }
        }
        return iArr;
    }

    @Override // com.mob.tools.FakeActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 256 && i2 == 0 && Build.VERSION.SDK_INT < 28) {
            this.listener.onCancel(this.platform, 9);
        }
        this.activity.finish();
    }

    public static int resultCompareVersion(String str) {
        return compareVersion(getPackageName("com.tencent.mobileqq"), str);
    }

    public static String getPackageName(String str) {
        try {
            return MobSDK.getContext().getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static int compareVersion(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str == null || str2 != null) {
            if (str != null || str2 == null) {
                String[] split = str.split("\\.");
                String[] split2 = str2.split("\\.");
                int i = 0;
                while (i < split.length && i < split2.length) {
                    try {
                        int parseInt = Integer.parseInt(split[i]);
                        int parseInt2 = Integer.parseInt(split2[i]);
                        if (parseInt < parseInt2) {
                            return -1;
                        }
                        if (parseInt > parseInt2) {
                            return 1;
                        }
                        i++;
                    } catch (NumberFormatException unused) {
                        return str.compareTo(str2);
                    }
                }
                if (split.length > i) {
                    return 1;
                }
                return split2.length > i ? -1 : 0;
            }
            return -1;
        }
        return 1;
    }
}
