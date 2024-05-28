package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.screensoft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.megvii.livenesslib.util.SDCardUtil;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ImageUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/screenshotForH5")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScreenshotForH5JSPlugin extends BaseJSPlugin {
    private String TAG = "ScreenshotForH5JSPlugin";
    private int compressSize;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034 A[Catch: Exception -> 0x0065, TryCatch #0 {Exception -> 0x0065, blocks: (B:2:0x0000, B:4:0x0018, B:7:0x001d, B:9:0x0021, B:11:0x0027, B:13:0x002c, B:15:0x0034, B:16:0x0044, B:18:0x004c, B:19:0x005c, B:12:0x002a), top: B:24:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0044 A[Catch: Exception -> 0x0065, TryCatch #0 {Exception -> 0x0065, blocks: (B:2:0x0000, B:4:0x0018, B:7:0x001d, B:9:0x0021, B:11:0x0027, B:13:0x002c, B:15:0x0034, B:16:0x0044, B:18:0x004c, B:19:0x005c, B:12:0x002a), top: B:24:0x0000 }] */
    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onExec() throws java.lang.Exception {
        /*
            r4 = this;
            org.json.JSONObject r0 = r4.parameterJO     // Catch: java.lang.Exception -> L65
            java.lang.String r1 = "screenShotType"
            java.lang.String r0 = r0.optString(r1)     // Catch: java.lang.Exception -> L65
            org.json.JSONObject r1 = r4.parameterJO     // Catch: java.lang.Exception -> L65
            java.lang.String r2 = "compressSize"
            r3 = 500(0x1f4, float:7.0E-43)
            int r1 = r1.optInt(r2, r3)     // Catch: java.lang.Exception -> L65
            r4.compressSize = r1     // Catch: java.lang.Exception -> L65
            int r1 = r4.compressSize     // Catch: java.lang.Exception -> L65
            if (r1 > r3) goto L2a
            int r1 = r4.compressSize     // Catch: java.lang.Exception -> L65
            if (r1 >= 0) goto L1d
            goto L2a
        L1d:
            int r1 = r4.compressSize     // Catch: java.lang.Exception -> L65
            if (r1 < 0) goto L2c
            int r1 = r4.compressSize     // Catch: java.lang.Exception -> L65
            r2 = 50
            if (r1 >= r2) goto L2c
            r4.compressSize = r2     // Catch: java.lang.Exception -> L65
            goto L2c
        L2a:
            r4.compressSize = r3     // Catch: java.lang.Exception -> L65
        L2c:
            java.lang.String r1 = "0"
            boolean r1 = android.text.TextUtils.equals(r0, r1)     // Catch: java.lang.Exception -> L65
            if (r1 == 0) goto L44
            java.lang.String r0 = r4.TAG     // Catch: java.lang.Exception -> L65
            java.lang.String r1 = "单屏截取"
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r0, r1)     // Catch: java.lang.Exception -> L65
            java.lang.String r0 = r4.createScreenShot()     // Catch: java.lang.Exception -> L65
            r4.callBackBase64(r0)     // Catch: java.lang.Exception -> L65
            goto L71
        L44:
            java.lang.String r1 = "1"
            boolean r0 = android.text.TextUtils.equals(r0, r1)     // Catch: java.lang.Exception -> L65
            if (r0 == 0) goto L5c
            java.lang.String r0 = r4.TAG     // Catch: java.lang.Exception -> L65
            java.lang.String r1 = "截取长截屏"
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r0, r1)     // Catch: java.lang.Exception -> L65
            java.lang.String r0 = r4.createLongScreenShot()     // Catch: java.lang.Exception -> L65
            r4.callBackBase64(r0)     // Catch: java.lang.Exception -> L65
            goto L71
        L5c:
            java.lang.String r0 = "11"
            java.lang.String r1 = "传值异常"
            r4.callbackFail(r0, r1)     // Catch: java.lang.Exception -> L65
            goto L71
        L65:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r0 = "10"
            java.lang.String r1 = "程序异常"
            r4.callbackFail(r0, r1)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.screensoft.ScreenshotForH5JSPlugin.onExec():void");
    }

    private void callBackBase64(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (isImageSizeThan1MB(str)) {
                String str2 = this.TAG;
                MsLogUtil.m7979d(str2, "大于" + this.compressSize + "kb进行压缩");
                compress(new File(str));
            } else {
                String str3 = this.TAG;
                MsLogUtil.m7979d(str3, "小于" + this.compressSize + "进行转化Base64");
                jSONObject.put("imageData", ImageUtil.fileToBase64(new File(str)));
                callbackSuccess(jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常");
        }
    }

    private void compress(File file) {
        Luban.compress(this.activityContext, file).putGear(4).setMaxSize(this.compressSize).setCompressFormat(Bitmap.CompressFormat.JPEG).launch(new OnCompressListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.screensoft.ScreenshotForH5JSPlugin.1
            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onStart() {
                MsLogUtil.m7980d("截屏图片压缩 onStart");
            }

            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onSuccess(File file2) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("imageData", ImageUtil.fileToBase64(file2));
                    ScreenshotForH5JSPlugin.this.callbackSuccess(jSONObject);
                    String str = ScreenshotForH5JSPlugin.this.TAG;
                    MsLogUtil.m7979d(str, "压缩后" + (file2.length() / 1024));
                } catch (JSONException e) {
                    e.printStackTrace();
                    ScreenshotForH5JSPlugin.this.callbackFail("10", "程序异常");
                }
            }

            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onError(Throwable th) {
                MsLogUtil.m7980d("截屏图片压缩 onError" + th.getMessage());
                ScreenshotForH5JSPlugin.this.callbackFail("12", "压缩异常");
            }
        });
    }

    public boolean isImageSizeThan1MB(String str) {
        long length = new File(str).length() / 1024;
        String str2 = this.TAG;
        MsLogUtil.m7979d(str2, "截图kb" + length);
        return length >= ((long) this.compressSize);
    }

    private String createLongScreenShot() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.f18589wv.measure(makeMeasureSpec, makeMeasureSpec);
        Bitmap createBitmap = Bitmap.createBitmap(this.f18589wv.getMeasuredWidth(), this.f18589wv.getMeasuredHeight(), Bitmap.Config.RGB_565);
        this.f18589wv.draw(new Canvas(createBitmap));
        String str = SDCardUtil.getOwnFileUrl("unicom_screenShot") + "screenshot.png";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private String createScreenShot() {
        View decorView = this.activityContext.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        String str = "";
        if (drawingCache != null) {
            try {
                str = SDCardUtil.getOwnFileUrl("unicom_screenShot") + "screenshot.png";
                FileTools.createPath(str);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
                drawingCache.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return str;
            } catch (Exception e) {
                e.printStackTrace();
                return str;
            }
        }
        return "";
    }
}
