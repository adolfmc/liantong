package cn.sharesdk.wechat.utils;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXLaunchMiniProgram;
import com.mob.MobSDK;
import com.mob.tools.utils.ResHelper;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WechatCore {

    /* renamed from: a */
    private String f3285a;

    /* renamed from: a */
    public boolean m21295a(String str) {
        try {
            this.f3285a = str;
            String str2 = "weixin://registerapp?appid=" + str;
            String packageName = MobSDK.getContext().getPackageName();
            Intent intent = new Intent("com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER");
            intent.putExtra("_mmessage_sdkVersion", 638058496);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", str2);
            intent.putExtra("_mmessage_support_content_type", 0L);
            intent.putExtra("_mmessage_checksum", m21294a(str2, packageName, 638058496));
            MobSDK.getContext().sendBroadcast(intent, "com.tencent.mm.permission.MM_MESSAGE");
            SSDKLog.m21740b().m21744a("sending broadcast, intent=com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER, perm=com.tencent.mm.permission.MM_MESSAGE", new Object[0]);
            return true;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21733d("WeChat registerApp catch " + th.getMessage(), new Object[0]);
            return false;
        }
    }

    /* renamed from: a */
    public void m21296a(WechatReq wechatReq, boolean z) throws Throwable {
        if (!wechatReq.mo21235b()) {
            throw new Throwable("sendReq checkArgs fail");
        }
        String packageName = MobSDK.getContext().getPackageName();
        String str = "weixin://sendreq?appid=" + this.f3285a;
        Intent intent = new Intent();
        intent.setClassName("com.tencent.mm", "com.tencent.mm.plugin.base.stub.WXEntryActivity");
        Bundle bundle = new Bundle();
        wechatReq.mo21234b(bundle);
        intent.putExtras(bundle);
        intent.putExtra("_mmessage_sdkVersion", 638058496);
        intent.putExtra("_mmessage_appPackage", packageName);
        intent.putExtra("_mmessage_content", str);
        intent.putExtra("_mmessage_checksum", m21294a(str, packageName, 638058496));
        if (wechatReq.mo21237a() == 2) {
            try {
                String m21301a = m21301a(MobSDK.getContext());
                if (!TextUtils.isEmpty(m21301a)) {
                    intent.putExtra("_message_token", m21301a);
                    SSDKLog.m21740b().m21744a("ShareSDK", " _message_token " + m21301a);
                }
            } catch (Exception e) {
                SSDKLog.m21740b().m21733d("ShareSDK", " WechatCore that put token catch " + e);
            }
        }
        intent.addFlags(268435456);
        intent.addFlags(134217728);
        if (Build.VERSION.SDK_INT < 29) {
            MobSDK.getContext().startActivity(intent);
        } else if (z) {
            m21291b(MobSDK.getContext(), intent);
        } else {
            m21299a(MobSDK.getContext(), intent);
        }
        SSDKLog.m21740b().m21744a("starting activity, packageName=com.tencent.mm, className=com.tencent.mm.plugin.base.stub.WXEntryActivity", new Object[0]);
    }

    /* renamed from: a */
    private static void m21299a(Context context, Intent intent) {
        try {
            SSDKLog.m21740b().m21744a("sendUsingPendingIntent", new Object[0]);
            m21300a(context, 3, intent, 134217728).send(context, 4, null, new PendingIntent.OnFinished() { // from class: cn.sharesdk.wechat.utils.j.1
                @Override // android.app.PendingIntent.OnFinished
                public void onSendFinished(PendingIntent pendingIntent, Intent intent2, int i, String str, Bundle bundle) {
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21744a("sendUsingPendingIntent onSendFinished resultCode: " + i + ", resultData: " + str, new Object[0]);
                }
            }, null);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("sendUsingPendingIntent fail, ex = " + e.getMessage(), new Object[0]);
            context.startActivity(intent);
        }
    }

    /* renamed from: b */
    private static void m21291b(Context context, Intent intent) {
        try {
            SSDKLog.m21740b().m21744a("launchWXUsingPendingIntent", new Object[0]);
            m21300a(context, 1, intent, 134217728).send(context, 2, null, new PendingIntent.OnFinished() { // from class: cn.sharesdk.wechat.utils.j.2
                @Override // android.app.PendingIntent.OnFinished
                public void onSendFinished(PendingIntent pendingIntent, Intent intent2, int i, String str, Bundle bundle) {
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21744a("launchWXUsingPendingIntent onSendFinished resultCode: " + i + ", resultData: " + str, new Object[0]);
                }
            }, null);
        } catch (Exception e) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("launchWXUsingPendingIntent pendingIntent send failed: " + e.getMessage(), new Object[0]);
            context.startActivity(intent);
        }
    }

    /* renamed from: a */
    private static PendingIntent m21300a(Context context, int i, Intent intent, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return PendingIntent.getActivity(context, i, intent, i2 | 67108864);
        }
        return PendingIntent.getActivity(context, i, intent, i2);
    }

    /* renamed from: a */
    private String m21301a(Context context) {
        try {
            Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/genTokenForOpenSdk"), null, null, new String[]{this.f3285a, "621086720"}, null);
            if (query == null || !query.moveToFirst()) {
                return null;
            }
            String string = query.getString(0);
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("ShareSDK", "MicroMsg.SDK.WXApiImplV10(WechatCore) getTokenFromWX token is " + string);
            query.close();
            return string;
        } catch (Throwable th) {
            SSDKLog m21740b2 = SSDKLog.m21740b();
            m21740b2.m21733d("ShareSDK", "WechatCore catch " + th);
            return null;
        }
    }

    /* renamed from: a */
    public void m21297a(WechatReq wechatReq) throws Throwable {
        SubscribeMessageReq subscribeMessageReq = (SubscribeMessageReq) wechatReq;
        String str = null;
        Cursor query = MobSDK.getContext().getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), null, str, new String[]{this.f3285a, "1", String.valueOf(subscribeMessageReq.f3267a), subscribeMessageReq.f3268b, subscribeMessageReq.f3269c}, str);
        if (query != null) {
            query.close();
        }
        Intent intent = new Intent();
        intent.setClassName("com.tencent.mm", "com.tencent.mm.plugin.base.stub.WXEntryActivity");
        intent.addFlags(268435456);
        intent.addFlags(134217728);
        MobSDK.getContext().startActivity(intent);
    }

    /* renamed from: b */
    public void m21290b(WechatReq wechatReq) throws Throwable {
        Intent launchIntentForPackage = MobSDK.getContext().getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
        if (Build.VERSION.SDK_INT < 29) {
            MobSDK.getContext().startActivity(launchIntentForPackage);
        } else {
            m21291b(MobSDK.getContext(), launchIntentForPackage);
        }
        WXLaunchMiniProgram.C1872a c1872a = (WXLaunchMiniProgram.C1872a) wechatReq;
        ContentResolver contentResolver = MobSDK.getContext().getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/launchWXMiniprogram");
        Cursor query = contentResolver.query(parse, null, null, new String[]{this.f3285a, c1872a.f3280a, c1872a.f3281b, c1872a.f3282c + "", c1872a.f3283d}, null);
        if (query != null) {
            query.close();
        }
    }

    /* renamed from: a */
    public boolean m21302a() {
        String str;
        try {
            str = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.mm", 0).versionName;
            SSDKLog.m21740b().m21738b("wechat versionName ==>> " + str);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            str = "0";
        }
        String[] split = str.split("_")[0].split("\\.");
        int[] iArr = new int[split.length];
        for (int i = 0; i < iArr.length; i++) {
            try {
                iArr[i] = ResHelper.parseInt(split[i]);
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21742a(th2);
                iArr[i] = 0;
            }
        }
        return iArr.length >= 4 && iArr[0] == 6 && iArr[1] == 0 && iArr[2] == 2 && iArr[3] <= 56;
    }

    /* renamed from: b */
    public boolean m21292b() {
        return m21287d();
    }

    /* renamed from: d */
    private boolean m21287d() {
        SSDKLog.m21740b().m21744a("checking signature of wechat client...", new Object[0]);
        PackageInfo m21715b = AppUtils.m21715b("com.tencent.mm", 64);
        if (m21715b == null) {
            return false;
        }
        int length = m21715b.signatures.length;
        for (int i = 0; i < length; i++) {
            if ("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499".equals(m21715b.signatures[i].toCharsString().toLowerCase())) {
                SSDKLog.m21740b().m21744a("pass!", new Object[0]);
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private byte[] m21294a(String str, String str2, int i) {
        String str3;
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        byte[] bytes = stringBuffer.toString().substring(1, 9).getBytes();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b : digest) {
                cArr2[i2] = cArr[(b >>> 4) & 15];
                int i3 = i2 + 1;
                cArr2[i3] = cArr[b & 15];
                i2 = i3 + 1;
            }
            str3 = new String(cArr2);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            str3 = null;
        }
        if (str3 != null) {
            return str3.getBytes();
        }
        return null;
    }

    @SuppressLint({"Range"})
    /* renamed from: b */
    private String m21289b(String str) {
        try {
            Cursor query = MobSDK.getContext().getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref"), new String[]{"_id", "key", "type", "value"}, "key = ?", new String[]{str}, null);
            if (query == null) {
                return null;
            }
            String string = query.moveToFirst() ? query.getString(query.getColumnIndex("value")) : null;
            query.close();
            return string;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }

    /* renamed from: c */
    public boolean m21288c() {
        int i;
        try {
            i = ResHelper.parseInt(m21289b("_build_info_sdk_int_"));
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            i = -1;
        }
        return i >= 553779201;
    }

    /* renamed from: a */
    public boolean m21298a(WechatHandlerActivity wechatHandlerActivity, WechatHandler wechatHandler) {
        Intent intent = wechatHandlerActivity.getIntent();
        if (intent == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra("wx_token_key");
        if (stringExtra == null || !stringExtra.equals("com.tencent.mm.openapi.token")) {
            SSDKLog.m21740b().m21744a("invalid argument, \"wx_token_key\" is empty or does not equals \"com.tencent.mm.openapi.token\"", new Object[0]);
            return false;
        }
        String stringExtra2 = intent.getStringExtra("_mmessage_appPackage");
        if (TextUtils.isEmpty(stringExtra2)) {
            SSDKLog.m21740b().m21744a("invalid argument, \"_mmessage_appPackage\" is empty", new Object[0]);
            return false;
        }
        if (!m21293a(intent.getByteArrayExtra("_mmessage_checksum"), m21294a(intent.getStringExtra("_mmessage_content"), stringExtra2, intent.getIntExtra("_mmessage_sdkVersion", 0)))) {
            SSDKLog.m21740b().m21744a("checksum fail", new Object[0]);
            return false;
        }
        Bundle extras = intent.getExtras();
        int i = extras.getInt("_wxapi_command_type", 0);
        if (i != 19) {
            switch (i) {
                case 1:
                    SSDKLog.m21740b().m21744a("Wechat Auth CallBack", new Object[0]);
                    wechatHandler.m21283a(new AuthResp(extras));
                    return true;
                case 2:
                    SSDKLog.m21740b().m21744a("Wechat Share CallBack", new Object[0]);
                    wechatHandler.m21283a(new SendMessageResp(extras));
                    return true;
                case 3:
                    wechatHandlerActivity.onGetMessageFromWXReq(new GetMessageFromWechatResp(extras).f3263a);
                    return true;
                case 4:
                    wechatHandlerActivity.onShowMessageFromWXReq(new ShowMessageFromWechatResp(extras).f3264a);
                    return true;
                default:
                    return false;
            }
        }
        SSDKLog.m21740b().m21744a("Wechat MiniProgram CallBack", new Object[0]);
        WXLaunchMiniProgram.C1873b c1873b = new WXLaunchMiniProgram.C1873b(extras);
        GetMessageFromWechatResp getMessageFromWechatResp = new GetMessageFromWechatResp(extras);
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.wxminiprogram_ext_msg = c1873b.f3284a;
        wXMediaMessage.openId = getMessageFromWechatResp.f3250j;
        wechatHandlerActivity.onGetMessageFromWXReq(wXMediaMessage);
        return true;
    }

    /* renamed from: a */
    private boolean m21293a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            SSDKLog.m21740b().m21744a("checkSumConsistent fail, invalid arguments, \"_mmessage_checksum\" is empty", new Object[0]);
            return false;
        } else if (bArr2 == null || bArr2.length == 0) {
            SSDKLog.m21740b().m21744a("checkSumConsistent fail, invalid arguments, checksum is empty", new Object[0]);
            return false;
        } else if (bArr.length != bArr2.length) {
            SSDKLog.m21740b().m21744a("checkSumConsistent fail, length is different", new Object[0]);
            return false;
        } else {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    SSDKLog.m21740b().m21744a("checkSumConsistent fail, not match", new Object[0]);
                    return false;
                }
            }
            return true;
        }
    }
}
