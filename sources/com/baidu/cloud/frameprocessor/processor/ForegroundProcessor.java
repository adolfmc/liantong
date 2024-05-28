package com.baidu.cloud.frameprocessor.processor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.text.TextUtils;
import com.baidu.cloud.frameprocessor.gles.GlUtil;
import com.baidu.cloud.frameprocessor.gles.OpenGlUtils;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ForegroundProcessor extends BaseFrameProcessor {
    private WeakReference<Context> mContext;
    private List<ForegroundParam> mParams;
    private String mWatermarkRes;
    private int mWatermarkResHeight;
    private int mWatermarkResWidth;
    private String mWatermarkTag;
    private int mWatermarkTextureId;

    public ForegroundProcessor(Context context) {
        this.mContext = new WeakReference<>(context.getApplicationContext());
    }

    @Override // com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public VideoFrameBuffer onFrame(VideoFrameBuffer videoFrameBuffer) {
        if (!this.mEnable || videoFrameBuffer == null || videoFrameBuffer.textureBuffer == null) {
            return videoFrameBuffer;
        }
        int width = getWidth(videoFrameBuffer);
        int height = getHeight(videoFrameBuffer);
        initDefaultFbo(width, height);
        bindDefaultFbo();
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        drawInputFrame(width, height, videoFrameBuffer);
        drawWatermark2(width, height);
        GLES20.glDisable(3042);
        unbindFbo();
        return getOutputData(videoFrameBuffer);
    }

    @Override // com.baidu.cloud.frameprocessor.processor.BaseFrameProcessor, com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void release() {
        super.release();
        List<ForegroundParam> list = this.mParams;
        if (list == null) {
            return;
        }
        for (ForegroundParam foregroundParam : list) {
            GlUtil.destroyTextureObject(foregroundParam.foregroundTexture);
            foregroundParam.foregroundTexture = -1;
        }
    }

    private void drawWatermark(int i, int i2) {
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        float f = i;
        float f2 = (f * 1.0f) / 720.0f;
        float f3 = this.mWatermarkResWidth * f2;
        float f4 = f2 * this.mWatermarkResHeight;
        float f5 = 10;
        float f6 = i2;
        Matrix.translateM(fArr, 0, (((-(f - f3)) / 2.0f) + f5) / (f / 2.0f), (((f6 - f4) / 2.0f) - f5) / (f6 / 2.0f), 1.0f);
        Matrix.scaleM(fArr, 0, f3 / f, (f4 * (-1.0f)) / f6, 1.0f);
        this.mFullScreen2D.drawFrame(fArr, this.mWatermarkTextureId, GlUtil.IDENTITY_MATRIX);
    }

    private void drawWatermark2(int i, int i2) {
        List<ForegroundParam> list = this.mParams;
        if (list == null) {
            return;
        }
        for (ForegroundParam foregroundParam : list) {
            float[] fArr = new float[16];
            Matrix.setIdentityM(fArr, 0);
            float width = foregroundParam.rect.width();
            float height = foregroundParam.rect.height();
            float f = i;
            float f2 = i2;
            Matrix.translateM(fArr, 0, (((-(f - width)) / 2.0f) + foregroundParam.rect.left) / (f / 2.0f), (((f2 - height) / 2.0f) - foregroundParam.rect.top) / (f2 / 2.0f), 1.0f);
            Matrix.scaleM(fArr, 0, width / f, (height * (-1.0f)) / f2, 1.0f);
            this.mFullScreen2D.drawFrame(fArr, foregroundParam.foregroundTexture, GlUtil.IDENTITY_MATRIX);
        }
    }

    private void generateWatermarkTexture() {
        Context context;
        Bitmap decodeResource;
        Context context2;
        if (TextUtils.isEmpty(this.mWatermarkRes)) {
            WeakReference<Context> weakReference = this.mContext;
            if (weakReference == null || (context2 = weakReference.get()) == null) {
                return;
            }
            String timeString = getTimeString();
            if (TextUtils.equals(this.mWatermarkTag, timeString)) {
                return;
            }
            Bitmap drawText2Bitmap = drawText2Bitmap(context2, timeString);
            this.mWatermarkTextureId = OpenGlUtils.loadTexture(drawText2Bitmap, -1);
            this.mWatermarkTag = timeString;
            if (drawText2Bitmap != null) {
                this.mWatermarkResWidth = drawText2Bitmap.getWidth();
                this.mWatermarkResHeight = drawText2Bitmap.getHeight();
            }
        } else if (this.mWatermarkTextureId != 0) {
        } else {
            if (this.mWatermarkRes.contains(File.separator)) {
                decodeResource = OpenGlUtils.decodeBitmap(this.mWatermarkRes);
            } else {
                WeakReference<Context> weakReference2 = this.mContext;
                if (weakReference2 == null || (context = weakReference2.get()) == null) {
                    return;
                }
                decodeResource = BitmapFactory.decodeResource(context.getResources(), getImageIdByName(this.mWatermarkRes, context));
            }
            if (decodeResource != null) {
                this.mWatermarkResWidth = decodeResource.getWidth();
                this.mWatermarkResHeight = decodeResource.getHeight();
            }
            this.mWatermarkTextureId = OpenGlUtils.loadTexture(decodeResource, -1);
        }
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

    public void setWatermarkParams(List<ForegroundParam> list) {
        this.mParams = list;
    }

    private String getTimeString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(Long.valueOf(System.currentTimeMillis()));
    }

    private Bitmap drawText2Bitmap(Context context, String str) {
        try {
            float f = context.getResources().getDisplayMetrics().density;
            Paint paint = new Paint(1);
            paint.setColor(Color.rgb(255, 0, 0));
            paint.setTextSize((int) (13.0f * f));
            paint.setShadowLayer(1.0f, 0.0f, 1.0f, -12303292);
            Rect rect = new Rect();
            paint.getTextBounds(str, 0, str.length(), rect);
            int i = (int) (f * 10.0f);
            Bitmap createBitmap = Bitmap.createBitmap(rect.width() + i, rect.height() + i, Bitmap.Config.ARGB_8888);
            createBitmap.eraseColor(0);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.drawText(str, (createBitmap.getWidth() - rect.width()) / 2, (createBitmap.getHeight() + rect.height()) / 2, paint);
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }
}
