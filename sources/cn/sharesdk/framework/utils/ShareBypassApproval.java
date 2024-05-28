package cn.sharesdk.framework.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareBypassApproval {

    /* renamed from: a */
    private String f2970a;

    /* renamed from: b */
    private String f2971b;

    /* renamed from: c */
    private boolean f2972c = true;

    /* renamed from: a */
    public void m21684a(String str) {
        this.f2970a = str;
        this.f2971b = "";
    }

    /* renamed from: a */
    public void m21682a(String str, String str2) {
        this.f2970a = str;
        this.f2971b = str2;
    }

    /* renamed from: a */
    public void m21685a(Platform.ShareParams shareParams, Platform platform) throws Throwable {
        SSDKLog.m21740b().m21744a("ShareSDK QQ ShareBypassApproval toShare", new Object[0]);
        Intent intent = new Intent();
        String imagePath = shareParams.getImagePath();
        String imageUrl = shareParams.getImageUrl();
        List<String> arrayList = new ArrayList();
        if (shareParams.getImageArray() != null) {
            arrayList = Arrays.asList(shareParams.getImageArray());
        }
        String text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            String shortLintk = platform.getShortLintk(text, false);
            shareParams.setText(shortLintk);
            intent.putExtra("android.intent.extra.TEXT", shortLintk);
            intent.putExtra("Kdescription", shortLintk);
        }
        if (arrayList == null || arrayList.size() <= 0) {
            if (TextUtils.isEmpty(imagePath) || !new File(imagePath).exists()) {
                Bitmap imageData = shareParams.getImageData();
                if (imageData != null && !imageData.isRecycled()) {
                    String cachePath = ResHelper.getCachePath(MobSDK.getContext(), "images");
                    File file = new File(cachePath, System.currentTimeMillis() + ".png");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    imageData.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    imagePath = file.getAbsolutePath();
                } else if (!TextUtils.isEmpty(imageUrl)) {
                    imagePath = BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl);
                }
            }
            if (!TextUtils.isEmpty(imagePath)) {
                arrayList.add(imagePath);
            }
        }
        ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
        for (String str : arrayList) {
            if (str.startsWith("http")) {
                str = BitmapHelper.downloadBitmap(MobSDK.getContext(), str);
            }
            File file2 = new File(str);
            if (file2.exists()) {
                if (str.startsWith("/data/")) {
                    String cachePath2 = ResHelper.getCachePath(MobSDK.getContext(), "images");
                    File file3 = new File(cachePath2, System.currentTimeMillis() + file2.getName());
                    String absolutePath = file3.getAbsolutePath();
                    file3.createNewFile();
                    if (ResHelper.copyFile(str, absolutePath)) {
                        file2 = file3;
                    }
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        Context context = MobSDK.getContext();
                        Uri m21729a = ShareSDKFileProvider.m21729a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file2);
                        MobSDK.getContext().grantUriPermission(this.f2970a, m21729a, 3);
                        arrayList2.add(m21729a);
                    } catch (Throwable th) {
                        SSDKLog.m21740b().m21741a(th, "ShareSDK ShareBypassApproval getUriForFile exception", new Object[0]);
                    }
                } else {
                    arrayList2.add(Uri.fromFile(file2));
                }
            }
        }
        if (arrayList2.size() <= 0) {
            intent.setAction("android.intent.action.SEND");
            intent.setType("text/plain");
        } else if (arrayList2.size() == 1 && arrayList2.get(0) != null) {
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", arrayList2.get(0));
            String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(((Uri) arrayList2.get(0)).toString());
            intent.setType((contentTypeFor == null || contentTypeFor.length() <= 0) ? "image/*" : "image/*");
        } else {
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList2);
            intent.setType("image/*");
        }
        if (TextUtils.isEmpty(this.f2971b)) {
            intent.setPackage(this.f2970a);
        } else {
            intent.setClassName(this.f2970a, this.f2971b);
        }
        intent.addFlags(335544320);
        try {
            MobSDK.getContext().startActivity(intent);
        } catch (Throwable th2) {
            SSDKLog.m21740b().m21741a(th2, "ShareSDK  QQ ShareBypassApproval toShare catch", new Object[0]);
        }
    }

    /* renamed from: a */
    public void m21686a(Uri uri, Platform platform, PlatformActionListener platformActionListener) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("video/*");
        intent.setPackage(this.f2970a);
        intent.putExtra("android.intent.extra.STREAM", uri);
        if (TextUtils.isEmpty(this.f2971b)) {
            intent.setPackage(this.f2970a);
        } else {
            intent.setClassName(this.f2970a, this.f2971b);
        }
        try {
            intent.addFlags(268435456);
            MobSDK.getContext().startActivity(intent);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ShareParams", null);
        if (platformActionListener != null) {
            platformActionListener.onComplete(platform, 9, hashMap);
        }
    }

    /* renamed from: a */
    public void m21683a(String str, Platform platform, PlatformActionListener platformActionListener) {
        Intent intent = new Intent("android.intent.action.SEND");
        if (str.endsWith("mp4") || str.endsWith("mkv")) {
            intent.setType("video/*");
        }
        if (TextUtils.isEmpty(this.f2971b)) {
            intent.setPackage(this.f2970a);
        } else {
            intent.setClassName(this.f2970a, this.f2971b);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    File file = new File(str);
                    Context context = MobSDK.getContext();
                    Uri m21729a = ShareSDKFileProvider.m21729a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file);
                    MobSDK.getContext().grantUriPermission(this.f2970a, m21729a, 3);
                    intent.putExtra("android.intent.extra.STREAM", m21729a);
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21741a(th, "ShareSDK ShareBypassApproval getUriForFile exception", new Object[0]);
                }
            }
        } else {
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str)));
        }
        try {
            intent.addFlags(268435456);
            MobSDK.getContext().startActivity(intent);
        } catch (Throwable th2) {
            SSDKLog.m21740b().m21742a(th2);
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ShareParams", null);
        if (platformActionListener != null) {
            platformActionListener.onComplete(platform, 9, hashMap);
        }
    }
}
