package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.widget.LinearLayout;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.ShareSDKFileProvider;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.ResHelper;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MultiImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoSourceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.SdkListener;
import com.sina.weibo.sdk.openapi.WBAPIFactory;
import com.sina.weibo.sdk.share.WbShareCallback;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SinaWeiboShareOfficial extends FakeActivity implements WbShareCallback {
    private String appkey;
    private IWBAPI iwbapi;
    private AuthorizeListener listener;
    private Platform.ShareParams params;
    private String permissions;
    private String redirectUrl;
    private long thumbSize = 2097152;

    public SinaWeiboShareOfficial(String str, String str2, String str3, Platform.ShareParams shareParams, AuthorizeListener authorizeListener) {
        this.appkey = str;
        this.redirectUrl = str2;
        this.permissions = str3;
        this.params = shareParams;
        this.listener = authorizeListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        super.onCreate();
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m21740b().m21742a(e);
        }
        try {
            AuthInfo authInfo = new AuthInfo(this.activity, this.appkey, this.redirectUrl, this.permissions);
            this.iwbapi = WBAPIFactory.createWBAPI(this.activity);
            this.iwbapi.registerApp(this.activity, authInfo, new SdkListener() { // from class: cn.sharesdk.sina.weibo.SinaWeiboShareOfficial.1
                @Override // com.sina.weibo.sdk.openapi.SdkListener
                public void onInitSuccess() {
                    SinaWeibo.initFlag = true;
                    SinaWeiboShareOfficial.this.actionShare();
                }

                @Override // com.sina.weibo.sdk.openapi.SdkListener
                public void onInitFailure(Exception exc) {
                    if (SinaWeiboShareOfficial.this.listener != null) {
                        SinaWeiboShareOfficial.this.listener.onError(exc);
                    }
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21744a("SinaWeiboShareOfficial", "WeiboInitFailure " + exc);
                }
            });
            try {
                if (SinaWeibo.initFlag) {
                    actionShare();
                }
                SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial actionShare() ");
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(new Throwable("Share catch: " + th));
                }
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21743a("SinaWeiboShareOfficial  catch: " + th);
                finish();
            }
        } catch (Throwable th2) {
            if (this.listener != null) {
                this.listener.onError(new Throwable("sinaweibo sdk init failed: " + th2));
            }
            SSDKLog m21740b2 = SSDKLog.m21740b();
            m21740b2.m21744a("SinaWeiboShareOfficial", "onCreate AuthInfo " + th2);
            finish();
        }
    }

    private WebpageObject getWebpageObj() {
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.title = this.params.getTitle();
        webpageObject.description = this.params.getText();
        webpageObject.actionUrl = this.params.getUrl();
        webpageObject.defaultText = this.params.getText();
        try {
            if (this.params.getImageData() != null) {
                webpageObject.thumbData = getThumb(this.activity, this.params.getImageData());
            } else if (!TextUtils.isEmpty(this.params.getImagePath())) {
                webpageObject.thumbData = getThumb(this.activity, this.params.getImagePath());
            }
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("SinaWeiboShareOfficial getWebpageObj catch: " + th, new Object[0]);
            webpageObject.thumbData = null;
        }
        return webpageObject;
    }

    private MultiImageObject getMultiImageObject() {
        MultiImageObject multiImageObject = new MultiImageObject();
        try {
            List<String> asList = Arrays.asList(this.params.getImageArray());
            ArrayList<Uri> arrayList = new ArrayList<>();
            for (String str : asList) {
                File file = new File(str);
                if (file.exists()) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        File file2 = new File(MobSDK.getContext().getExternalFilesDir(null).getPath() + "/" + file.getName());
                        if (file.getPath().equals(file2.getPath()) ? true : ResHelper.copyFile(file.getPath(), file2.getPath())) {
                            Context context = MobSDK.getContext();
                            Uri m21729a = ShareSDKFileProvider.m21729a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file);
                            MobSDK.getContext().grantUriPermission("com.sina.weibo", m21729a, 3);
                            arrayList.add(m21729a);
                        }
                    } else {
                        arrayList.add(Uri.fromFile(file));
                    }
                }
            }
            multiImageObject.imageList = arrayList;
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("SinaWeiboShareOfficial getMultiImageObject catch: " + th, new Object[0]);
        }
        return multiImageObject;
    }

    private VideoSourceObject getVideoSourceObject() {
        String filePath;
        Uri uri;
        VideoSourceObject videoSourceObject = new VideoSourceObject();
        try {
            filePath = this.params.getFilePath();
            uri = null;
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("SinaWeiboShareOfficial getVideoSourceObject catch: " + th, new Object[0]);
        }
        if (TextUtils.isEmpty(filePath)) {
            SSDKLog.m21740b().m21744a("SinaWeiboShareOfficial getVideoSourceObject filePath is null ", new Object[0]);
            return null;
        }
        File file = new File(filePath);
        if (file.exists()) {
            if (Build.VERSION.SDK_INT >= 24) {
                Context context = MobSDK.getContext();
                Uri m21729a = ShareSDKFileProvider.m21729a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file);
                MobSDK.getContext().grantUriPermission("com.sina.weibo", m21729a, 3);
                uri = m21729a;
            } else {
                uri = Uri.fromFile(file);
            }
        }
        videoSourceObject.videoPath = uri;
        return videoSourceObject;
    }

    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        try {
            imageObject.imageData = getThumb(this.activity, this.params.getImageData());
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("SinaWeiboShareOfficial getImageObj catch: " + th, new Object[0]);
        }
        return imageObject;
    }

    private MultiImageObject getOneImage() {
        MultiImageObject multiImageObject = new MultiImageObject();
        try {
            List<String> asList = Arrays.asList(this.params.getImagePath());
            ArrayList<Uri> arrayList = new ArrayList<>();
            for (String str : asList) {
                File file = new File(str);
                if (file.exists()) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        File file2 = new File(MobSDK.getContext().getExternalFilesDir(null).getPath() + "/" + file.getName());
                        if (!file.getPath().equals(file2.getPath()) ? ResHelper.copyFile(file.getPath(), file2.getPath()) : true) {
                            Context context = MobSDK.getContext();
                            Uri m21729a = ShareSDKFileProvider.m21729a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file);
                            MobSDK.getContext().grantUriPermission("com.sina.weibo", m21729a, 3);
                            arrayList.add(m21729a);
                        } else {
                            SSDKLog.m21740b().m21744a("QQQ", " SinaWeiboShareOfficial copy failed ");
                        }
                    } else {
                        arrayList.add(Uri.fromFile(file));
                    }
                }
            }
            multiImageObject.imageList = arrayList;
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("SinaWeiboShareOfficial getOneImage catch: " + th, new Object[0]);
            multiImageObject.imageList = null;
        }
        return multiImageObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void actionShare() {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        if (!TextUtils.isEmpty(this.params.getText())) {
            TextObject textObject = new TextObject();
            textObject.text = this.params.getText();
            weiboMultiMessage.textObject = textObject;
        }
        if (!TextUtils.isEmpty(this.params.getUrl())) {
            weiboMultiMessage.mediaObject = getWebpageObj();
        } else if (this.params.getImageArray() != null && this.params.getImageArray().length > 0) {
            weiboMultiMessage.multiImageObject = getMultiImageObject();
        } else if (!TextUtils.isEmpty(this.params.getFilePath())) {
            if (getVideoSourceObject() != null) {
                weiboMultiMessage.videoSourceObject = getVideoSourceObject();
            }
        } else if (this.params.getImageData() != null || !TextUtils.isEmpty(this.params.getImagePath())) {
            if (this.params.getImageData() != null) {
                weiboMultiMessage.imageObject = getImageObj();
            } else if (!TextUtils.isEmpty(this.params.getImagePath())) {
                weiboMultiMessage.multiImageObject = getOneImage();
            }
        }
        IWBAPI iwbapi = this.iwbapi;
        if (iwbapi != null) {
            try {
                iwbapi.shareMessage(this.activity, weiboMultiMessage, true);
                return;
            } catch (Throwable th) {
                AuthorizeListener authorizeListener = this.listener;
                if (authorizeListener != null) {
                    authorizeListener.onError(new Throwable("Share to sina failed: " + th));
                }
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21743a("SinaWeiboShareOfficial share catch: " + th);
                finish();
                return;
            }
        }
        AuthorizeListener authorizeListener2 = this.listener;
        if (authorizeListener2 != null) {
            authorizeListener2.onError(new Throwable("SinaWeibo SDK init failed"));
        }
        finish();
    }

    @Override // com.mob.tools.FakeActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        IWBAPI iwbapi = this.iwbapi;
        if (iwbapi != null) {
            iwbapi.doResultIntent(intent, this);
        } else {
            SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial onActivityResult iwbapi is null");
        }
        super.onActivityResult(i, i2, intent);
        SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial onActivityResult");
    }

    @Override // com.sina.weibo.sdk.share.WbShareCallback
    public void onComplete() {
        AuthorizeListener authorizeListener = this.listener;
        if (authorizeListener != null) {
            authorizeListener.onComplete(null);
        }
        SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial doShare onComplete ");
        finish();
    }

    @Override // com.sina.weibo.sdk.share.WbShareCallback
    public void onError(UiError uiError) {
        if (this.listener != null) {
            this.listener.onError(new Throwable("errorCode: " + uiError.errorCode + " errorMessage: " + uiError.errorMessage + " errorDetail: " + uiError.errorDetail));
        }
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21743a("SinaWeiboShareOfficial doShare onError: " + uiError.errorDetail);
        finish();
    }

    @Override // com.sina.weibo.sdk.share.WbShareCallback
    public void onCancel() {
        AuthorizeListener authorizeListener = this.listener;
        if (authorizeListener != null) {
            authorizeListener.onCancel();
        }
        SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial doShare onCancel");
        finish();
    }

    @Override // com.mob.tools.FakeActivity
    public void onResume() {
        super.onResume();
        SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial doShare onResume");
    }

    @Override // com.mob.tools.FakeActivity
    public void onPause() {
        super.onPause();
        SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial doShare onPause");
    }

    @Override // com.mob.tools.FakeActivity
    public void onStop() {
        super.onStop();
        SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial doShare onStop");
    }

    @Override // com.mob.tools.FakeActivity
    public void onDestroy() {
        super.onDestroy();
        SSDKLog.m21740b().m21743a("SinaWeiboShareOfficial doShare onDestroy");
    }

    private String generateGUID() {
        return UUID.randomUUID().toString().replace("-", "");
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

    public final void setThumbImage(MultiImageObject multiImageObject, Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
                multiImageObject.thumbData = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream2 = byteArrayOutputStream;
                try {
                    SSDKLog.m21740b().m21742a(th);
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                } catch (Throwable th3) {
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Throwable th4) {
                            SSDKLog.m21740b().m21742a(th4);
                        }
                    }
                    throw th3;
                }
            }
        } catch (Throwable th5) {
            SSDKLog.m21740b().m21742a(th5);
        }
    }
}
