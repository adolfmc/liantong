package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.scancode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.king.zxing.util.QRCodeParseUtils;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/decodeQRCode")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DecodeQRCodeJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("url");
            String optString2 = this.parameterJO.optString("base64Data");
            if (!TextUtils.isEmpty(optString) && optString.startsWith("http")) {
                Glide.with(this.activityContext).asBitmap().load(optString).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.scancode.DecodeQRCodeJSPlugin.1
                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                    }

                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            if (bitmap != null) {
                                DecodeQRCodeJSPlugin.this.decodeQRCode(bitmap);
                            } else {
                                DecodeQRCodeJSPlugin.this.callbackFail("11", "图片url地址下载失败");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            DecodeQRCodeJSPlugin decodeQRCodeJSPlugin = DecodeQRCodeJSPlugin.this;
                            decodeQRCodeJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                        }
                    }

                    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                        DecodeQRCodeJSPlugin.this.callbackFail("11", "图片url地址下载失败");
                    }
                });
            } else if (!TextUtils.isEmpty(optString2)) {
                Bitmap base64ToBitmap = FileTools.base64ToBitmap(optString2);
                if (base64ToBitmap != null) {
                    decodeQRCode(base64ToBitmap);
                    return;
                }
                throw new RuntimeException("base64数据无法转换图片");
            } else {
                throw new RuntimeException("参数缺失或非法");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decodeQRCode(Bitmap bitmap) throws Exception {
        String syncDecodeQRCode = QRCodeParseUtils.syncDecodeQRCode(bitmap);
        if (TextUtils.isEmpty(syncDecodeQRCode)) {
            callbackFail("12", "无法识别图片二维码或者图片本身并不是二维码图片");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("result", syncDecodeQRCode);
        callbackSuccess(jSONObject);
    }
}
