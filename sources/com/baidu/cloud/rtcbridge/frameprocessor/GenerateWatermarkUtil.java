package com.baidu.cloud.rtcbridge.frameprocessor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.baidu.cloud.frameprocessor.gles.GlUtil;
import com.baidu.cloud.frameprocessor.gles.OpenGlUtils;
import com.baidu.cloud.frameprocessor.processor.ForegroundParam;
import com.baidu.cloud.rtcbridge.frameprocessor.BRTCWatermarkParams;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GenerateWatermarkUtil {
    private List<ForegroundParam> foregroundParams = new ArrayList();
    private int index;

    public List<ForegroundParam> generateWatermark(Context context, BRTCWatermarkParams bRTCWatermarkParams) {
        if (context == null || bRTCWatermarkParams == null || bRTCWatermarkParams.watermarkParamList == null) {
            return null;
        }
        this.index = 0;
        for (BRTCWatermarkParams.BRTCWatermarkParam bRTCWatermarkParam : bRTCWatermarkParams.watermarkParamList) {
            generateWatermarkTexture(context, bRTCWatermarkParam);
            this.index++;
        }
        if (this.index < this.foregroundParams.size()) {
            for (int i = this.index; i < this.foregroundParams.size(); i++) {
                ForegroundParam foregroundParam = this.foregroundParams.get(i);
                GlUtil.destroyTextureObject(foregroundParam.foregroundTexture);
                foregroundParam.foregroundTexture = -1;
            }
            this.foregroundParams = this.foregroundParams.subList(0, this.index);
        }
        return this.foregroundParams;
    }

    public void release(BRTCWatermarkParams bRTCWatermarkParams) {
        if (bRTCWatermarkParams == null || bRTCWatermarkParams.watermarkParamList == null) {
            return;
        }
        for (BRTCWatermarkParams.BRTCWatermarkParam bRTCWatermarkParam : bRTCWatermarkParams.watermarkParamList) {
            GlUtil.destroyTextureObject(bRTCWatermarkParam.watermarkTexture);
            bRTCWatermarkParam.watermarkTexture = -1;
            bRTCWatermarkParam.watermarkTag = "";
        }
    }

    private void generateWatermarkTexture(Context context, BRTCWatermarkParams.BRTCWatermarkParam bRTCWatermarkParam) {
        int generateStringAndTimeWatermark;
        if (bRTCWatermarkParam.watermarkType == BRTCWatermarkParams.WatermarkType.IMAGE) {
            generateStringAndTimeWatermark = generateImageWatermark(context, bRTCWatermarkParam);
        } else {
            generateStringAndTimeWatermark = generateStringAndTimeWatermark(context, bRTCWatermarkParam);
        }
        if (generateStringAndTimeWatermark <= 0 || bRTCWatermarkParam.rect == null) {
            return;
        }
        int size = this.foregroundParams.size();
        int i = this.index;
        if (size > i) {
            ForegroundParam foregroundParam = this.foregroundParams.get(i);
            foregroundParam.foregroundTexture = generateStringAndTimeWatermark;
            foregroundParam.rect = bRTCWatermarkParam.rect;
            return;
        }
        ForegroundParam foregroundParam2 = new ForegroundParam();
        foregroundParam2.foregroundTexture = generateStringAndTimeWatermark;
        foregroundParam2.rect = bRTCWatermarkParam.rect;
        this.foregroundParams.add(foregroundParam2);
    }

    private int generateStringAndTimeWatermark(Context context, BRTCWatermarkParams.BRTCWatermarkParam bRTCWatermarkParam) {
        String substring;
        int i;
        Bitmap bitmap;
        float f = bRTCWatermarkParam.autoScaleDensity ? context.getResources().getDisplayMetrics().density : 1.0f;
        if (bRTCWatermarkParam.watermarkType == BRTCWatermarkParams.WatermarkType.TIME) {
            substring = getTimeString(bRTCWatermarkParam.watermarkResource);
        } else {
            String str = bRTCWatermarkParam.watermarkResource;
            substring = str.length() > 50 ? str.substring(0, 50) : str;
        }
        if (TextUtils.equals(bRTCWatermarkParam.watermarkTag, substring) && bRTCWatermarkParam.watermarkTexture > 0) {
            return bRTCWatermarkParam.watermarkTexture;
        }
        bRTCWatermarkParam.watermarkTag = substring;
        try {
            Paint paint = new Paint(1);
            paint.setColor(bRTCWatermarkParam.textColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize((int) (bRTCWatermarkParam.textSize * f));
            paint.setShadowLayer(bRTCWatermarkParam.shadowRadius, bRTCWatermarkParam.shadowDx, bRTCWatermarkParam.shadowDy, bRTCWatermarkParam.shadowColor);
            float measureText = paint.measureText(substring);
            if (bRTCWatermarkParam.watermarkType == BRTCWatermarkParams.WatermarkType.STRING) {
                if (bRTCWatermarkParam.rect != null && bRTCWatermarkParam.rect.width() != 0 && measureText > bRTCWatermarkParam.rect.width()) {
                    measureText = bRTCWatermarkParam.rect.width();
                }
                TextPaint textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
                textPaint.setColor(bRTCWatermarkParam.textColor);
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setTextSize((int) (bRTCWatermarkParam.textSize * f));
                textPaint.setShadowLayer(bRTCWatermarkParam.shadowRadius, bRTCWatermarkParam.shadowDx, bRTCWatermarkParam.shadowDy, bRTCWatermarkParam.shadowColor);
                StaticLayout staticLayout = new StaticLayout(substring, textPaint, (int) measureText, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                bitmap = Bitmap.createBitmap(bRTCWatermarkParam.rect.width(), staticLayout.getHeight(), Bitmap.Config.ARGB_8888);
                bitmap.eraseColor(0);
                staticLayout.draw(new Canvas(bitmap));
            } else {
                String[] split = substring.split("\n");
                int i2 = 0;
                String str2 = "";
                for (String str3 : split) {
                    if (str3.length() > i2) {
                        i2 = str3.length();
                        str2 = str3;
                    }
                }
                Rect rect = new Rect();
                paint.getTextBounds(str2, 0, str2.length(), rect);
                int height = rect.height();
                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                Bitmap createBitmap = Bitmap.createBitmap((int) paint.measureText(str2), split.length * ((int) (fontMetrics.descent - fontMetrics.ascent)), Bitmap.Config.ARGB_8888);
                createBitmap.eraseColor(0);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                int i3 = 0;
                while (i3 < split.length) {
                    String str4 = split[i3];
                    i3++;
                    canvas.drawText(str4, 0.0f, ((i + height) * i3) >> 1, paint);
                }
                bitmap = createBitmap;
            }
            if (bRTCWatermarkParam.rect != null) {
                bRTCWatermarkParam.rect.right = bRTCWatermarkParam.rect.left + bitmap.getWidth();
                bRTCWatermarkParam.rect.bottom = bRTCWatermarkParam.rect.top + bitmap.getHeight();
            }
            bRTCWatermarkParam.watermarkTexture = OpenGlUtils.loadTexture(bitmap, bRTCWatermarkParam.watermarkTexture);
            return bRTCWatermarkParam.watermarkTexture;
        } catch (Exception unused) {
            bRTCWatermarkParam.watermarkTag = "";
            return 0;
        }
    }

    private String getTimeString(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        if (TextUtils.isEmpty(str)) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        simpleDateFormat.applyPattern(str);
        return simpleDateFormat.format(Long.valueOf(System.currentTimeMillis()));
    }

    private int generateImageWatermark(Context context, BRTCWatermarkParams.BRTCWatermarkParam bRTCWatermarkParam) {
        Bitmap decodeResource;
        if (TextUtils.equals(bRTCWatermarkParam.watermarkTag, bRTCWatermarkParam.watermarkResource) && bRTCWatermarkParam.watermarkTexture > 0) {
            return bRTCWatermarkParam.watermarkTexture;
        }
        if (bRTCWatermarkParam.watermarkType != BRTCWatermarkParams.WatermarkType.IMAGE) {
            return 0;
        }
        if (bRTCWatermarkParam.watermarkResource.contains(File.separator)) {
            decodeResource = OpenGlUtils.decodeBitmap(bRTCWatermarkParam.watermarkResource);
        } else if (context == null) {
            return 0;
        } else {
            decodeResource = BitmapFactory.decodeResource(context.getResources(), getImageIdByName(bRTCWatermarkParam.watermarkResource, context));
        }
        if (bRTCWatermarkParam.rect != null && (bRTCWatermarkParam.rect.right == 0 || bRTCWatermarkParam.rect.bottom == 0)) {
            bRTCWatermarkParam.rect.right = bRTCWatermarkParam.rect.left + decodeResource.getWidth();
            bRTCWatermarkParam.rect.bottom = bRTCWatermarkParam.rect.top + decodeResource.getHeight();
        }
        bRTCWatermarkParam.watermarkTag = bRTCWatermarkParam.watermarkResource;
        bRTCWatermarkParam.watermarkTexture = OpenGlUtils.loadTexture(decodeResource, bRTCWatermarkParam.watermarkTexture);
        return bRTCWatermarkParam.watermarkTexture;
    }

    private int getImageIdByName(String str, Context context) {
        String[] strArr;
        try {
            for (String str2 : new String[]{context.getPackageName()}) {
                int identifier = context.getResources().getIdentifier(str, "drawable", str2);
                if (identifier == 0) {
                    identifier = context.getResources().getIdentifier(str, "mipmap", str2);
                }
                if (identifier != 0) {
                    return identifier;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
