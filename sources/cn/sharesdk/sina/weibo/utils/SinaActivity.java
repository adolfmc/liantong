package cn.sharesdk.sina.weibo.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.LinearLayout;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.sina.weibo.sdk.C1809a;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.UIHandler;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SinaActivity extends FakeActivity implements Handler.Callback {
    private static final int ERR_CANCEL = 1;
    private static final int ERR_FAIL = 2;
    private static final int ERR_OK = 0;
    private static final String SDK_VERSION = "0031405000";
    private String appKey;
    private AuthorizeListener authListener;
    private boolean isCallback;
    private Platform.ShareParams params;
    private long thumbSize = 2097152;

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m21740b().m21742a(e);
        }
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setShareParams(Platform.ShareParams shareParams) {
        this.params = shareParams;
    }

    public void setSinaAuthListener(AuthorizeListener authorizeListener) {
        this.authListener = authorizeListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        SSDKLog.m21740b().m21744a("sina activity requestCode = %s, resultCode = %s", Integer.valueOf(i), Integer.valueOf(i));
        finish();
    }

    @Override // com.mob.tools.FakeActivity
    public void onNewIntent(Intent intent) {
        this.isCallback = true;
        Bundle extras = intent.getExtras();
        SSDKLog.m21740b().m21735c("onNewIntent ==>>", extras.toString());
        String stringExtra = intent.getStringExtra("_weibo_appPackage");
        String stringExtra2 = intent.getStringExtra("_weibo_transaction");
        if (TextUtils.isEmpty(stringExtra)) {
            SSDKLog.m21740b().m21733d("handleWeiboResponse faild appPackage is null", new Object[0]);
            return;
        }
        String callingPackage = this.activity.getCallingPackage();
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("handleWeiboResponse getCallingPackage : " + callingPackage, new Object[0]);
        if (TextUtils.isEmpty(stringExtra2)) {
            SSDKLog.m21740b().m21733d("handleWeiboResponse faild intent _weibo_transaction is null", new Object[0]);
        } else if (!WeiboAppManager.m21593a(stringExtra) && !stringExtra.equals(this.activity.getPackageName())) {
            SSDKLog.m21740b().m21733d("handleWeiboResponse faild appPackage validateSign faild", new Object[0]);
        } else {
            onResponse(extras.getInt("_weibo_resp_errcode"), extras.getString("_weibo_resp_errstr"));
        }
    }

    private void onResponse(int i, String str) {
        switch (i) {
            case 0:
                AuthorizeListener authorizeListener = this.authListener;
                if (authorizeListener != null) {
                    authorizeListener.onComplete(null);
                    break;
                }
                break;
            case 1:
                AuthorizeListener authorizeListener2 = this.authListener;
                if (authorizeListener2 != null) {
                    authorizeListener2.onCancel();
                    break;
                }
                break;
            case 2:
                AuthorizeListener authorizeListener3 = this.authListener;
                if (authorizeListener3 != null) {
                    authorizeListener3.onError(new Throwable(str));
                    break;
                }
                break;
        }
        finish();
    }

    private void sendMessage() {
        UIHandler.sendEmptyMessageDelayed(1, 200L, this);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        AuthorizeListener authorizeListener;
        if (message.what == 1) {
            if (!this.isCallback && (authorizeListener = this.authListener) != null) {
                authorizeListener.onCancel();
            }
            finish();
            return false;
        }
        return false;
    }

    @Override // com.mob.tools.FakeActivity
    public void onStop() {
        super.onStop();
    }

    private boolean startClientShare(Activity activity, String str, String str2, Bundle bundle) {
        if (activity == null || TextUtils.isEmpty("com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY") || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            SSDKLog.m21740b().m21733d("launchWeiboActivity fail, invalid arguments", new Object[0]);
            return false;
        }
        String packageName = activity.getPackageName();
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction("com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", str2);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", C1809a.m21612a(activity, packageName));
        intent.putExtra("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("launchWeiboActivity intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
            startActivityForResult(intent, 765);
            return true;
        } catch (ActivityNotFoundException e) {
            SSDKLog.m21740b().m21733d(e.getMessage(), new Object[0]);
            return false;
        }
    }

    private String generateGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = this.params.getText();
        return textObject;
    }

    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        try {
            if (this.params.getImageData() != null) {
                imageObject.imageData = getThumb(this.activity, this.params.getImageData());
            } else if (!TextUtils.isEmpty(this.params.getImagePath())) {
                DeviceHelper deviceHelper = DeviceHelper.getInstance(this.activity);
                if (deviceHelper.getSdcardState() && this.params.getImagePath().startsWith(deviceHelper.getSdcardPath())) {
                    File file = new File(this.params.getImagePath());
                    if (file.exists() && file.length() != 0 && file.length() < 10485760) {
                        imageObject.imagePath = this.params.getImagePath();
                        return imageObject;
                    }
                }
                imageObject.imageData = getThumb(this.activity, this.params.getImagePath());
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        return imageObject;
    }

    private byte[] getThumb(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        }
        if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
        return bitmapTByte(context, bitmap);
    }

    private byte[] getThumb(Context context, String str) throws Throwable {
        if (!new File(str).exists()) {
            throw new FileNotFoundException();
        }
        return bitmapTByte(context, BitmapHelper.getBitmap(str));
    }

    private byte[] bitmapTByte(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        }
        if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        while (true) {
            long j = this.thumbSize;
            if (length <= j) {
                return byteArray;
            }
            bitmap = getResizedBitmap(bitmap, length / j);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream2);
            byteArrayOutputStream2.flush();
            byteArrayOutputStream2.close();
            byteArray = byteArrayOutputStream2.toByteArray();
            length = byteArray.length;
        }
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, double d) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double sqrt = Math.sqrt(d);
        return Bitmap.createScaledBitmap(bitmap, (int) (width / sqrt), (int) (height / sqrt), true);
    }
}
