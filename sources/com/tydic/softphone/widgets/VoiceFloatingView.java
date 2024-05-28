package com.tydic.softphone.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.tydic.softphone.C10458R;
import com.tydic.softphone.Configs;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class VoiceFloatingView extends View {
    private final String TAG;
    private Canvas canvas;
    int fontHeight;
    int fontWidth;
    int iconHeight;
    private Bitmap mBitmap;
    private int mDefaultHeight;
    private int mDefaultWidth;
    private Direction mDirection;
    private int mHeight;
    private boolean mIsShow;
    private WindowManager.LayoutParams mLayoutParams;
    private int mOrientation;
    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXfermode;
    private int mWidth;
    private int mWidthPixels;
    private WindowManager mWindowManager;
    String tagText;

    /* renamed from: x */
    private int f20029x;

    /* renamed from: y */
    private int f20030y;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum Direction {
        left,
        right,
        move
    }

    @RequiresApi(api = 23)
    public VoiceFloatingView(Context context) {
        super(context);
        this.TAG = VoiceFloatingView.class.getSimpleName();
        this.tagText = "等待接听";
        this.mDirection = Direction.right;
        init();
    }

    public static boolean canDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 27) {
            return Settings.canDrawOverlays(context);
        }
        if (Settings.canDrawOverlays(context)) {
            return true;
        }
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return false;
            }
            View view = new View(context);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(0, 0, Build.VERSION.SDK_INT >= 26 ? 2038 : 2003, 24, -2);
            view.setLayoutParams(layoutParams);
            windowManager.addView(view, layoutParams);
            windowManager.removeView(view);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresApi(api = 23)
    private void init() {
        Context context = getContext();
        getContext();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mLayoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 25) {
            this.mLayoutParams.type = 2038;
        } else {
            this.mLayoutParams.type = 2003;
        }
        boolean canDrawOverlays = Settings.canDrawOverlays(getContext());
        Log.e("PermissionRequest", "22 open = " + canDrawOverlays);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.format = 1;
        layoutParams.gravity = 8388659;
        layoutParams.flags = 8;
        layoutParams.width = -2;
        layoutParams.height = -2;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
        this.mBitmap = ((BitmapDrawable) getResources().getDrawable(C10458R.mipmap.tydic_softphone_b8e)).getBitmap();
        this.mDefaultHeight = 180;
        this.mDefaultWidth = 180;
        recordScreenWidth();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWidth = measureSize(this.mDefaultWidth, i2);
        this.mHeight = measureSize(this.mDefaultHeight, i);
        setMeasuredDimension(this.mWidth, this.mHeight);
        this.fontWidth = (this.mWidth / 2) - 50;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        super.onDraw(canvas);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        this.mPaint.setColor(Color.parseColor("#D9E1E1E1"));
        float f = 30;
        canvas.drawRoundRect(0.0f, 0.0f, this.mWidth, this.mHeight, f, f, this.mPaint);
        this.mPaint.setXfermode(null);
        this.mPaint.setColor(-1);
        float f2 = 10;
        canvas.drawRoundRect(f2, f2, this.mWidth - 10, this.mHeight - 10, f, f, this.mPaint);
        this.iconHeight = ((this.mHeight - this.mBitmap.getHeight()) / 2) - 20;
        this.fontHeight = this.iconHeight + this.mBitmap.getHeight() + 40;
        this.mPaint.setColor(-16711936);
        if (this.tagText.equals(Configs.TALK_CLOSE)) {
            this.mBitmap = ((BitmapDrawable) getResources().getDrawable(C10458R.mipmap.tydic_softphone_hangup)).getBitmap();
            this.mPaint.setColor(-16711936);
            this.mPaint.setColor(Color.parseColor("#E60027"));
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.mBitmap, 60, 60, true);
        int width = (this.mWidth - this.mBitmap.getWidth()) / 2;
        canvas.drawBitmap(createScaledBitmap, 60.0f, 45.0f, this.mPaint);
        this.mPaint.setTextSize(24.0f);
        canvas.drawText(this.tagText, this.fontWidth, 149.0f, this.mPaint);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.tydic.softphone.widgets.VoiceFloatingView$1] */
    public void startTalk(Canvas canvas) {
        new Thread() { // from class: com.tydic.softphone.widgets.VoiceFloatingView.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000L);
                    Log.i("tydic", "startTalk");
                    VoiceFloatingView.this.tagText = "00:01";
                    VoiceFloatingView.this.invalidate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void drawTime(String str) {
        Log.i("tydic", "我在重绘");
        if (str.equals(Configs.TALK_CLOSE)) {
            this.fontWidth = (this.mWidth / 2) - 50;
        } else {
            this.fontWidth = (this.mWidth / 2) - 30;
        }
        this.tagText = str;
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mWindowManager != null) {
            if (getResources().getConfiguration().orientation != this.mOrientation) {
                recordScreenWidth();
            }
            switch (motionEvent.getAction()) {
                case 0:
                    this.f20029x = (int) motionEvent.getRawX();
                    this.f20030y = (int) motionEvent.getRawY();
                    break;
                case 1:
                    handleDirection((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                    invalidate();
                    this.mWindowManager.updateViewLayout(this, this.mLayoutParams);
                    break;
                case 2:
                    int rawX = (int) motionEvent.getRawX();
                    int rawY = (int) motionEvent.getRawY();
                    int i = rawX - this.f20029x;
                    int i2 = rawY - this.f20030y;
                    this.f20029x = rawX;
                    this.f20030y = rawY;
                    this.mLayoutParams.x += i;
                    this.mLayoutParams.y += i2;
                    if (this.mLayoutParams.x < 0) {
                        this.mLayoutParams.x = 0;
                    }
                    if (this.mLayoutParams.y < 0) {
                        this.mLayoutParams.y = 0;
                    }
                    if (this.mDirection != Direction.move) {
                        this.mDirection = Direction.move;
                        invalidate();
                    }
                    this.mWindowManager.updateViewLayout(this, this.mLayoutParams);
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private int measureSize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        return mode == 1073741824 ? size : mode == Integer.MIN_VALUE ? Math.min(i, size) : i;
    }

    private void recordScreenWidth() {
        this.mOrientation = getResources().getConfiguration().orientation;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mWidthPixels = displayMetrics.widthPixels;
    }

    private void handleDirection(int i, int i2) {
        if (i > this.mWidthPixels / 2) {
            this.mDirection = Direction.right;
            this.mLayoutParams.x = this.mWidthPixels - getMeasuredWidth();
            return;
        }
        this.mDirection = Direction.left;
        this.mLayoutParams.x = 0;
    }

    @RequiresApi(api = 23)
    public void show() {
        if (this.mIsShow) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 25) {
            boolean canDrawOverlays = canDrawOverlays(getContext());
            if (canDrawOverlays) {
                if (this.mLayoutParams.x == 0 && this.mLayoutParams.y == 0 && this.mDirection == Direction.right) {
                    WindowManager.LayoutParams layoutParams = this.mLayoutParams;
                    layoutParams.x = this.mWidthPixels - this.mDefaultWidth;
                    layoutParams.y = 0;
                }
                if (this.mDirection == Direction.move) {
                    handleDirection(this.mLayoutParams.x, this.mLayoutParams.y);
                }
                this.mWindowManager.addView(this, this.mLayoutParams);
                this.mIsShow = true;
            }
            Log.e("PermissionRequest", "22 open = " + canDrawOverlays);
            return;
        }
        if (this.mLayoutParams.x == 0 && this.mLayoutParams.y == 0 && this.mDirection == Direction.right) {
            WindowManager.LayoutParams layoutParams2 = this.mLayoutParams;
            layoutParams2.x = this.mWidthPixels - this.mDefaultWidth;
            layoutParams2.y = 0;
        }
        if (this.mDirection == Direction.move) {
            handleDirection(this.mLayoutParams.x, this.mLayoutParams.y);
        }
        this.mWindowManager.addView(this, this.mLayoutParams);
        this.mIsShow = true;
    }

    public void updateViewLayout(int i, int i2) {
        if (this.mIsShow) {
            handleDirection(i, i2);
            invalidate();
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            layoutParams.y = i2;
            this.mWindowManager.updateViewLayout(this, layoutParams);
        }
    }

    public void dismiss() {
        if (this.mIsShow) {
            this.mWindowManager.removeView(this);
            this.mIsShow = false;
        }
    }
}
