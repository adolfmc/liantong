package com.sinovatech.unicom.basic.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import com.sinovatech.unicom.common.UIUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FlakeView extends View {
    ValueAnimator animator;
    private Activity context;
    Bitmap droid;
    Bitmap droid2;
    Bitmap droid3;
    Bitmap droid4;
    Bitmap droid5;
    ArrayList<Flake> flakes;
    float fps;
    String fpsString;
    int frames;

    /* renamed from: m */
    Matrix f18440m;
    int numFlakes;
    String numFlakesString;
    long prevTime;
    long startTime;
    Paint textPaint;
    private Map<Integer, C8050WH> whMap;

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public FlakeView(Context context) {
        super(context);
        this.numFlakes = 0;
        this.flakes = new ArrayList<>();
        this.animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.frames = 0;
        this.fps = 0.0f;
        this.f18440m = new Matrix();
        this.fpsString = "";
        this.numFlakesString = "";
        this.context = (Activity) context;
        this.whMap = new HashMap();
        this.droid = BitmapFactory.decodeResource(getResources(), 2131231429);
        this.droid2 = BitmapFactory.decodeResource(getResources(), 2131231429);
        this.droid3 = BitmapFactory.decodeResource(getResources(), 2131231429);
        this.droid4 = BitmapFactory.decodeResource(getResources(), 2131231429);
        this.droid5 = BitmapFactory.decodeResource(getResources(), 2131231429);
        this.whMap.put(1, getWH(this.droid));
        this.whMap.put(2, getWH(this.droid2));
        this.whMap.put(3, getWH(this.droid3));
        this.whMap.put(4, getWH(this.droid4));
        this.whMap.put(5, getWH(this.droid5));
        this.textPaint = new Paint(1);
        this.textPaint.setColor(-1);
        this.textPaint.setTextSize(24.0f);
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.basic.view.FlakeView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                long currentTimeMillis = System.currentTimeMillis();
                float f = ((float) (currentTimeMillis - FlakeView.this.prevTime)) / 1000.0f;
                FlakeView.this.prevTime = currentTimeMillis;
                for (int i = 0; i < FlakeView.this.numFlakes; i++) {
                    Flake flake = FlakeView.this.flakes.get(i);
                    flake.f18439y += flake.speed * f;
                    if (flake.direction == 0) {
                        flake.f18438x += 1.0f;
                    } else {
                        flake.f18438x -= 1.0f;
                    }
                    flake.rotation += flake.rotationSpeed * f;
                }
                FlakeView.this.invalidate();
            }
        });
        this.animator.setRepeatCount(-1);
        this.animator.setDuration(1000L);
    }

    public int getNumFlakes() {
        int i = this.numFlakes;
        if (i > 75) {
            return 75;
        }
        return i;
    }

    private void setNumFlakes(int i) {
        this.numFlakes = i;
        this.numFlakesString = "numFlakes: " + this.numFlakes;
    }

    public void addFlakes(int i) {
        C8050WH c8050wh;
        Bitmap bitmap;
        for (int i2 = 0; i2 < i; i2++) {
            int nextInt = new Random().nextInt(5);
            switch (nextInt) {
                case 0:
                    Bitmap bitmap2 = this.droid;
                    c8050wh = this.whMap.get(1);
                    bitmap = bitmap2;
                    break;
                case 1:
                    Bitmap bitmap3 = this.droid2;
                    c8050wh = this.whMap.get(2);
                    bitmap = bitmap3;
                    break;
                case 2:
                    Bitmap bitmap4 = this.droid2;
                    c8050wh = this.whMap.get(3);
                    bitmap = bitmap4;
                    break;
                case 3:
                    Bitmap bitmap5 = this.droid3;
                    c8050wh = this.whMap.get(4);
                    bitmap = bitmap5;
                    break;
                case 4:
                    Bitmap bitmap6 = this.droid4;
                    c8050wh = this.whMap.get(5);
                    bitmap = bitmap6;
                    break;
                default:
                    c8050wh = null;
                    bitmap = null;
                    break;
            }
            this.flakes.add(Flake.createFlake(getWidth(), bitmap, nextInt, this.context, c8050wh.width, c8050wh.height));
        }
        setNumFlakes(this.numFlakes + i);
    }

    public void subtractFlakes(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.numFlakes;
            int i4 = (i3 - i2) - 1;
            if (i4 < 0 || i4 >= i3) {
                return;
            }
            this.flakes.remove(i4);
        }
        setNumFlakes(this.numFlakes - i);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.flakes.clear();
        this.numFlakes = 0;
        addFlakes(0);
        this.animator.cancel();
        this.startTime = System.currentTimeMillis();
        this.prevTime = this.startTime;
        this.frames = 0;
        this.animator.start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.numFlakes; i++) {
            Flake flake = this.flakes.get(i);
            this.f18440m.setTranslate((-flake.width) / 2, (-flake.height) / 2);
            this.f18440m.postRotate(flake.rotation);
            this.f18440m.postTranslate((flake.width / 2) + flake.f18438x, (flake.height / 2) + flake.f18439y);
            canvas.drawBitmap(flake.bitmap, this.f18440m, null);
        }
        this.frames++;
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - this.startTime;
        if (j > 1000) {
            this.fps = this.frames / (((float) j) / 1000.0f);
            this.fpsString = "fps: " + this.fps;
            this.startTime = currentTimeMillis;
            this.frames = 0;
        }
    }

    public void pause() {
        this.animator.cancel();
    }

    public void resume() {
        this.animator.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.view.FlakeView$WH */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class C8050WH {
        int height;
        int width;

        C8050WH() {
        }
    }

    private C8050WH getWH(Bitmap bitmap) {
        C8050WH c8050wh = new C8050WH();
        c8050wh.width = (bitmap.getWidth() * UIUtils.getScreenWidth(this.context)) / 3260;
        c8050wh.height = (bitmap.getHeight() * UIUtils.getScreenWidth(this.context)) / 3260;
        return c8050wh;
    }
}
