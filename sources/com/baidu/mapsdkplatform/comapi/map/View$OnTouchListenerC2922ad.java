package com.baidu.mapsdkplatform.comapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapsdkplatform.comapi.commonutils.C2888a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.ad */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class View$OnTouchListenerC2922ad extends LinearLayout implements View.OnTouchListener {

    /* renamed from: a */
    private ImageView f7230a;

    /* renamed from: b */
    private ImageView f7231b;

    /* renamed from: c */
    private Context f7232c;

    /* renamed from: d */
    private Bitmap f7233d;

    /* renamed from: e */
    private Bitmap f7234e;

    /* renamed from: f */
    private Bitmap f7235f;

    /* renamed from: g */
    private Bitmap f7236g;

    /* renamed from: h */
    private Bitmap f7237h;

    /* renamed from: i */
    private Bitmap f7238i;

    /* renamed from: j */
    private Bitmap f7239j;

    /* renamed from: k */
    private Bitmap f7240k;

    /* renamed from: l */
    private int f7241l;

    /* renamed from: m */
    private boolean f7242m;

    /* renamed from: n */
    private boolean f7243n;

    @Deprecated
    public View$OnTouchListenerC2922ad(Context context) {
        super(context);
        this.f7242m = false;
        this.f7243n = false;
        this.f7232c = context;
        m18388c();
        if (this.f7233d == null || this.f7234e == null || this.f7235f == null || this.f7236g == null) {
            return;
        }
        this.f7230a = new ImageView(this.f7232c);
        this.f7231b = new ImageView(this.f7232c);
        this.f7230a.setImageBitmap(this.f7233d);
        this.f7231b.setImageBitmap(this.f7235f);
        this.f7241l = m18396a(this.f7235f.getHeight() / 6);
        m18394a(this.f7230a, "main_topbtn_up.9.png");
        m18394a(this.f7231b, "main_bottombtn_up.9.png");
        this.f7230a.setId(0);
        this.f7231b.setId(1);
        this.f7230a.setClickable(true);
        this.f7231b.setClickable(true);
        this.f7230a.setOnTouchListener(this);
        this.f7231b.setOnTouchListener(this);
        setOrientation(1);
        setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        addView(this.f7230a);
        addView(this.f7231b);
        this.f7243n = true;
    }

    public View$OnTouchListenerC2922ad(Context context, boolean z) {
        super(context);
        this.f7242m = false;
        this.f7243n = false;
        this.f7232c = context;
        this.f7242m = z;
        this.f7230a = new ImageView(this.f7232c);
        this.f7231b = new ImageView(this.f7232c);
        if (z) {
            m18387d();
            if (this.f7237h == null || this.f7238i == null || this.f7239j == null || this.f7240k == null) {
                return;
            }
            this.f7230a.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            this.f7231b.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            this.f7230a.setImageBitmap(this.f7237h);
            this.f7231b.setImageBitmap(this.f7239j);
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            setOrientation(0);
        } else {
            m18388c();
            Bitmap bitmap = this.f7233d;
            if (bitmap == null || this.f7234e == null || this.f7235f == null || this.f7236g == null) {
                return;
            }
            this.f7230a.setImageBitmap(bitmap);
            this.f7231b.setImageBitmap(this.f7235f);
            this.f7241l = m18396a(this.f7235f.getHeight() / 6);
            m18394a(this.f7230a, "main_topbtn_up.9.png");
            m18394a(this.f7231b, "main_bottombtn_up.9.png");
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            setOrientation(1);
        }
        this.f7230a.setId(0);
        this.f7231b.setId(1);
        this.f7230a.setClickable(true);
        this.f7231b.setClickable(true);
        this.f7230a.setOnTouchListener(this);
        this.f7231b.setOnTouchListener(this);
        addView(this.f7230a);
        addView(this.f7231b);
        this.f7243n = true;
    }

    /* renamed from: a */
    private int m18396a(int i) {
        return (int) ((this.f7232c.getResources().getDisplayMetrics().density * i) + 0.5f);
    }

    /* renamed from: a */
    private Bitmap m18393a(String str) {
        Matrix matrix = new Matrix();
        int densityDpi = SysOSUtil.getDensityDpi();
        float f = densityDpi > 480 ? 1.8f : (densityDpi <= 320 || densityDpi > 480) ? 1.2f : 1.5f;
        matrix.postScale(f, f);
        Bitmap m18473a = C2888a.m18473a(str, this.f7232c);
        if (m18473a == null) {
            return null;
        }
        return Bitmap.createBitmap(m18473a, 0, 0, m18473a.getWidth(), m18473a.getHeight(), matrix, true);
    }

    /* renamed from: a */
    private void m18394a(View view, String str) {
        Bitmap m18473a = C2888a.m18473a(str, this.f7232c);
        byte[] ninePatchChunk = m18473a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        view.setBackgroundDrawable(new NinePatchDrawable(m18473a, ninePatchChunk, new Rect(), null));
        int i = this.f7241l;
        view.setPadding(i, i, i, i);
    }

    /* renamed from: c */
    private void m18388c() {
        this.f7233d = m18393a("main_icon_zoomin.png");
        this.f7234e = m18393a("main_icon_zoomin_dis.png");
        this.f7235f = m18393a("main_icon_zoomout.png");
        this.f7236g = m18393a("main_icon_zoomout_dis.png");
    }

    /* renamed from: d */
    private void m18387d() {
        this.f7237h = m18393a("wear_zoom_in.png");
        this.f7238i = m18393a("wear_zoom_in_pressed.png");
        this.f7239j = m18393a("wear_zoon_out.png");
        this.f7240k = m18393a("wear_zoom_out_pressed.png");
    }

    /* renamed from: a */
    public void m18395a(View.OnClickListener onClickListener) {
        this.f7230a.setOnClickListener(onClickListener);
    }

    /* renamed from: a */
    public void m18392a(boolean z) {
        ImageView imageView;
        Bitmap bitmap;
        ImageView imageView2 = this.f7230a;
        if (imageView2 == null) {
            return;
        }
        imageView2.setEnabled(z);
        if (z) {
            imageView = this.f7230a;
            bitmap = this.f7233d;
        } else {
            imageView = this.f7230a;
            bitmap = this.f7234e;
        }
        imageView.setImageBitmap(bitmap);
    }

    /* renamed from: a */
    public boolean m18397a() {
        return this.f7243n;
    }

    /* renamed from: b */
    public void m18391b() {
        Bitmap bitmap = this.f7233d;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f7233d.recycle();
            this.f7233d = null;
        }
        Bitmap bitmap2 = this.f7234e;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.f7234e.recycle();
            this.f7234e = null;
        }
        Bitmap bitmap3 = this.f7235f;
        if (bitmap3 != null && !bitmap3.isRecycled()) {
            this.f7235f.recycle();
            this.f7235f = null;
        }
        Bitmap bitmap4 = this.f7236g;
        if (bitmap4 != null && !bitmap4.isRecycled()) {
            this.f7236g.recycle();
            this.f7236g = null;
        }
        Bitmap bitmap5 = this.f7237h;
        if (bitmap5 != null && !bitmap5.isRecycled()) {
            this.f7237h.recycle();
            this.f7237h = null;
        }
        Bitmap bitmap6 = this.f7238i;
        if (bitmap6 != null && !bitmap6.isRecycled()) {
            this.f7238i.recycle();
            this.f7238i = null;
        }
        Bitmap bitmap7 = this.f7239j;
        if (bitmap7 != null && !bitmap7.isRecycled()) {
            this.f7239j.recycle();
            this.f7239j = null;
        }
        Bitmap bitmap8 = this.f7240k;
        if (bitmap8 == null || bitmap8.isRecycled()) {
            return;
        }
        this.f7240k.recycle();
        this.f7240k = null;
    }

    /* renamed from: b */
    public void m18390b(View.OnClickListener onClickListener) {
        this.f7231b.setOnClickListener(onClickListener);
    }

    /* renamed from: b */
    public void m18389b(boolean z) {
        ImageView imageView;
        Bitmap bitmap;
        ImageView imageView2 = this.f7231b;
        if (imageView2 == null) {
            return;
        }
        imageView2.setEnabled(z);
        if (z) {
            imageView = this.f7231b;
            bitmap = this.f7235f;
        } else {
            imageView = this.f7231b;
            bitmap = this.f7236g;
        }
        imageView.setImageBitmap(bitmap);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView imageView;
        Bitmap bitmap;
        ImageView imageView2;
        String str;
        if (view instanceof ImageView) {
            switch (((ImageView) view).getId()) {
                case 0:
                    if (motionEvent.getAction() == 0) {
                        if (this.f7242m) {
                            imageView = this.f7230a;
                            bitmap = this.f7238i;
                            imageView.setImageBitmap(bitmap);
                            return false;
                        }
                        imageView2 = this.f7230a;
                        str = "main_topbtn_down.9.png";
                        m18394a(imageView2, str);
                        return false;
                    } else if (motionEvent.getAction() == 1) {
                        if (this.f7242m) {
                            imageView = this.f7230a;
                            bitmap = this.f7237h;
                            imageView.setImageBitmap(bitmap);
                            return false;
                        }
                        imageView2 = this.f7230a;
                        str = "main_topbtn_up.9.png";
                        m18394a(imageView2, str);
                        return false;
                    } else {
                        return false;
                    }
                case 1:
                    if (motionEvent.getAction() == 0) {
                        if (this.f7242m) {
                            imageView = this.f7231b;
                            bitmap = this.f7240k;
                            imageView.setImageBitmap(bitmap);
                            return false;
                        }
                        imageView2 = this.f7231b;
                        str = "main_bottombtn_down.9.png";
                        m18394a(imageView2, str);
                        return false;
                    } else if (motionEvent.getAction() == 1) {
                        if (this.f7242m) {
                            imageView = this.f7231b;
                            bitmap = this.f7239j;
                            imageView.setImageBitmap(bitmap);
                            return false;
                        }
                        imageView2 = this.f7231b;
                        str = "main_bottombtn_up.9.png";
                        m18394a(imageView2, str);
                        return false;
                    } else {
                        return false;
                    }
                default:
                    return false;
            }
        }
        return false;
    }
}
