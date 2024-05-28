package cn.sharesdk.sina.weibo.sdk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LoadingBar extends TextView {

    /* renamed from: a */
    private int f3008a;

    /* renamed from: b */
    private int f3009b;

    /* renamed from: c */
    private Paint f3010c;

    /* renamed from: d */
    private Handler f3011d;

    /* renamed from: e */
    private Runnable f3012e;

    public LoadingBar(Context context) {
        super(context);
        this.f3012e = new Runnable() { // from class: cn.sharesdk.sina.weibo.sdk.LoadingBar.1
            @Override // java.lang.Runnable
            public void run() {
                LoadingBar.this.f3008a++;
                LoadingBar loadingBar = LoadingBar.this;
                loadingBar.m21618a(loadingBar.f3008a);
            }
        };
        m21617a(context);
    }

    public LoadingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m21617a(context);
    }

    public LoadingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m21617a(context);
    }

    /* renamed from: a */
    private void m21617a(Context context) {
        this.f3011d = new Handler();
        this.f3010c = new Paint();
        m21619a();
    }

    /* renamed from: a */
    public void m21619a() {
        this.f3009b = -11693826;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f3010c.setColor(this.f3009b);
        canvas.drawRect(m21614b(), this.f3010c);
    }

    /* renamed from: b */
    private Rect m21614b() {
        int left = getLeft();
        int top = getTop();
        return new Rect(0, 0, (getLeft() + (((getRight() - getLeft()) * this.f3008a) / 100)) - left, getBottom() - top);
    }

    /* renamed from: a */
    public void m21618a(int i) {
        if (i < 7) {
            this.f3011d.postDelayed(this.f3012e, 70L);
        } else {
            this.f3011d.removeCallbacks(this.f3012e);
            this.f3008a = i;
        }
        invalidate();
    }
}
