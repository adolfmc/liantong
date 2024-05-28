package com.networkbench.agent.impl.p252e;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.m */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6364m extends FrameLayout {

    /* renamed from: b */
    private static final float f16022b = 46.0f;

    /* renamed from: m */
    protected static int f16023m;

    /* renamed from: n */
    protected static int f16024n;

    /* renamed from: a */
    private final InterfaceC6393e f16025a;

    /* renamed from: d */
    protected Activity f16026d;

    /* renamed from: e */
    protected C6359j f16027e;

    /* renamed from: f */
    protected WindowManager.LayoutParams f16028f;

    /* renamed from: g */
    protected Button f16029g;

    /* renamed from: h */
    protected C6367o f16030h;

    /* renamed from: i */
    protected boolean f16031i;

    /* renamed from: j */
    protected String f16032j;

    /* renamed from: k */
    protected boolean f16033k;

    /* renamed from: l */
    protected String f16034l;

    /* renamed from: a */
    public abstract void mo10266a();

    /* renamed from: a */
    public abstract void mo10265a(MotionEvent motionEvent, int i, int i2);

    /* renamed from: a */
    protected boolean mo10269a(AbstractC6364m abstractC6364m) {
        return false;
    }

    /* renamed from: c */
    public void mo10287c() {
    }

    protected float getBtnRadius() {
        return 46.0f;
    }

    /* renamed from: l */
    public boolean mo10267l() {
        return true;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.f16029g.setOnTouchListener(onTouchListener);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f16029g.setOnClickListener(onClickListener);
    }

    public void setmContext(Activity activity) {
        this.f16026d = activity;
    }

    @TargetApi(16)
    public AbstractC6364m(Activity activity, C6367o c6367o) {
        super(activity);
        this.f16025a = C6394f.m10150a();
        this.f16026d = activity;
        f16023m = getDisplayMetrics().widthPixels - 200;
        f16024n = getDisplayMetrics().heightPixels - 300;
        this.f16029g = new Button(activity);
        this.f16029g.setBackground(m10289a(-11440145, new OvalShape()));
        this.f16027e = C6359j.m10299a(this.f16026d);
        this.f16028f = m10281k();
        this.f16033k = false;
        this.f16030h = c6367o;
        this.f16031i = false;
        this.f16034l = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public void m10286f() {
        if (this.f16033k) {
            this.f16033k = false;
        } else {
            this.f16033k = true;
        }
    }

    @TargetApi(16)
    /* renamed from: g */
    public void m10285g() {
        if (TextUtils.isEmpty(this.f16032j)) {
            return;
        }
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(this.f16032j);
            if (decodeFile != null) {
                this.f16029g.setText("");
                this.f16029g.setBackground(new BitmapDrawable(this.f16026d.getResources(), decodeFile));
            } else {
                this.f16029g.setText(this.f16034l);
            }
            this.f16029g.invalidate();
        } catch (Throwable th) {
            this.f16025a.mo10121a("set view image error", th);
        }
    }

    public String getViewImageBmpPath() {
        return this.f16032j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo10263b() {
        for (AbstractC6364m abstractC6364m : this.f16030h.m10276b()) {
            if (!mo10269a(abstractC6364m)) {
                abstractC6364m.setVisible(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: h */
    public void m10284h() {
        for (AbstractC6364m abstractC6364m : this.f16030h.m10276b()) {
            if (!mo10269a(abstractC6364m)) {
                abstractC6364m.setVisible(4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getPosBeginX() {
        return f16023m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getPosBeginY() {
        return f16024n;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public LayerDrawable m10289a(int i, Shape shape) {
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{m10288b(i, shape)});
        layerDrawable.setLayerInset(0, 0, 0, 0, 0);
        return layerDrawable;
    }

    /* renamed from: b */
    private ShapeDrawable m10288b(int i, Shape shape) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }

    /* renamed from: i */
    public void m10283i() {
        m10285g();
        m10290a(getPosBeginX(), getPosBeginY());
        this.f16027e.m10297a(this.f16029g, this.f16028f);
        if (!this.f16031i) {
            setVisible(4);
        }
        this.f16031i = true;
    }

    /* renamed from: j */
    public void m10282j() {
        this.f16027e.m10298a(this.f16029g);
    }

    /* renamed from: a */
    public void m10290a(int i, int i2) {
        WindowManager.LayoutParams layoutParams = this.f16028f;
        layoutParams.x = i;
        layoutParams.y = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: k */
    public WindowManager.LayoutParams m10281k() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 51;
        if (Build.VERSION.SDK_INT >= 26) {
            layoutParams.type = 2038;
        } else {
            layoutParams.type = 2003;
        }
        layoutParams.format = 1;
        layoutParams.flags = 327976;
        float f = getDisplayMetrics().density;
        layoutParams.width = (int) (getBtnRadius() * f);
        layoutParams.height = (int) (getBtnRadius() * f);
        return layoutParams;
    }

    protected DisplayMetrics getDisplayMetrics() {
        return this.f16026d.getResources().getDisplayMetrics();
    }

    /* renamed from: m */
    public void m10280m() {
        m10290a(getPosBeginX(), getPosBeginY());
        this.f16027e.m10296b(this.f16029g, this.f16028f);
    }

    public void setVisible(int i) {
        this.f16029g.setVisibility(i);
    }
}
