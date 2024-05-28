package cn.sharesdk.system.text.login.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.widget.EditText;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.login.utils.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CommEditText extends EditText {
    public CommEditText(Context context) {
        super(context);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(13290186);
        paint.setStrokeWidth(1.0f);
        canvas.drawLine(0.0f, 0.0f, getWidth() - 1, 0.0f, paint);
        canvas.drawLine(0.0f, 0.0f, 0.0f, getHeight() - 1, paint);
        canvas.drawLine(getWidth() - 1, 0.0f, getWidth() - 1, getHeight() - 1, paint);
        paint.setColor(-3487030);
        canvas.drawLine(0.0f, getHeight() - 1, getWidth() - 1, getHeight() - 1, paint);
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(-16777216);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setStrokeWidth(0.1f);
    }
}
