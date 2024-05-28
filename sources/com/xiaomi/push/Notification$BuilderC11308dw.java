package com.xiaomi.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11635x;

/* renamed from: com.xiaomi.push.dw */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Notification$BuilderC11308dw extends AbstractNotification$BuilderC11309dx {

    /* renamed from: a */
    private int f22018a;

    /* renamed from: a */
    private PendingIntent f22019a;

    /* renamed from: b */
    private int f22020b;

    /* renamed from: b */
    private Bitmap f22021b;

    /* renamed from: c */
    private int f22022c;

    /* renamed from: c */
    private CharSequence f22023c;

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx
    /* renamed from: a */
    protected String mo4093a() {
        return "notification_colorful";
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx
    /* renamed from: b */
    protected String mo4080b() {
        return "notification_colorful_copy";
    }

    public Notification$BuilderC11308dw(Context context, int i, String str) {
        super(context, i, str);
        this.f22018a = 16777216;
        this.f22020b = 16777216;
        this.f22022c = 16777216;
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx
    /* renamed from: a */
    protected boolean mo4091a() {
        if (C11469j.m2974a()) {
            Resources resources = mo4093a().getResources();
            String packageName = mo4093a().getPackageName();
            return (m4105a(resources, "icon", "id", packageName) == 0 || m4105a(resources, "title", "id", packageName) == 0 || m4105a(resources, "content", "id", packageName) == 0) ? false : true;
        }
        return false;
    }

    /* renamed from: a */
    public Notification$BuilderC11308dw m4099a(CharSequence charSequence, PendingIntent pendingIntent) {
        if (mo4080b()) {
            super.addAction(0, charSequence, pendingIntent);
            this.f22023c = charSequence;
            this.f22019a = pendingIntent;
        }
        return this;
    }

    @Override // com.xiaomi.push.Notification$BuilderC11307dv
    /* renamed from: a */
    public Notification$BuilderC11308dw mo4098a(String str) {
        if (mo4080b() && !TextUtils.isEmpty(str)) {
            try {
                this.f22020b = Color.parseColor(str);
            } catch (Exception unused) {
                AbstractC11049b.m5282a("parse colorful notification button bg color error");
            }
        }
        return this;
    }

    /* renamed from: b */
    public Notification$BuilderC11308dw m4097b(String str) {
        if (mo4080b() && !TextUtils.isEmpty(str)) {
            try {
                this.f22018a = Color.parseColor(str);
            } catch (Exception unused) {
                AbstractC11049b.m5282a("parse colorful notification bg color error");
            }
        }
        return this;
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx
    /* renamed from: a */
    public Notification$BuilderC11308dw mo4084a(Bitmap bitmap) {
        if (mo4080b() && bitmap != null) {
            if (bitmap.getWidth() == 984 && bitmap.getHeight() >= 177 && bitmap.getHeight() <= 207) {
                this.f22021b = bitmap;
            } else {
                AbstractC11049b.m5282a("colorful notification bg image resolution error, must [984*177, 984*207]");
            }
        }
        return this;
    }

    /* renamed from: c */
    public Notification$BuilderC11308dw m4096c(String str) {
        if (mo4080b() && !TextUtils.isEmpty(str)) {
            try {
                this.f22022c = Color.parseColor(str);
            } catch (Exception unused) {
                AbstractC11049b.m5282a("parse colorful notification image text color error");
            }
        }
        return this;
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx, com.xiaomi.push.Notification$BuilderC11307dv
    /* renamed from: a */
    public void mo4092a() {
        if (mo4080b()) {
            super.mo4092a();
            Resources resources = mo4093a().getResources();
            String packageName = mo4093a().getPackageName();
            int a = m4105a(resources, "icon", "id", packageName);
            if (this.f22025a == null) {
                m4089a(a);
            } else {
                mo4093a().setImageViewBitmap(a, this.f22025a);
            }
            int a2 = m4105a(resources, "title", "id", packageName);
            int a3 = m4105a(resources, "content", "id", packageName);
            mo4093a().setTextViewText(a2, this.f22027a);
            mo4093a().setTextViewText(a3, this.f22033b);
            if (!TextUtils.isEmpty(this.f22023c)) {
                int a4 = m4105a(resources, "buttonContainer", "id", packageName);
                int a5 = m4105a(resources, "button", "id", packageName);
                int a6 = m4105a(resources, "buttonBg", "id", packageName);
                mo4093a().setViewVisibility(a4, 0);
                mo4093a().setTextViewText(a5, this.f22023c);
                mo4093a().setOnClickPendingIntent(a4, this.f22019a);
                if (this.f22020b != 16777216) {
                    int a7 = m4090a(70.0f);
                    int a8 = m4090a(29.0f);
                    mo4093a().setImageViewBitmap(a6, C11635x.m2315a(m4101a(this.f22020b, a7, a8, a8 / 2.0f)));
                    mo4093a().setTextColor(a5, m4089a(this.f22020b) ? -1 : -16777216);
                }
            }
            int a9 = m4105a(resources, "bg", "id", packageName);
            int a10 = m4105a(resources, "container", "id", packageName);
            if (this.f22018a != 16777216) {
                if (C11469j.m2973a(mo4093a()) >= 10) {
                    mo4093a().setImageViewBitmap(a9, C11635x.m2315a(m4101a(this.f22018a, 984, 192, 30.0f)));
                } else {
                    mo4093a().setImageViewBitmap(a9, C11635x.m2315a(m4101a(this.f22018a, 984, 192, 0.0f)));
                }
                m4100a(mo4093a(), a10, a2, a3, m4089a(this.f22018a));
            } else if (this.f22021b != null) {
                if (C11469j.m2973a(mo4093a()) >= 10) {
                    mo4093a().setImageViewBitmap(a9, m4083a(this.f22021b, 30.0f));
                } else {
                    mo4093a().setImageViewBitmap(a9, this.f22021b);
                }
                if (this.f22030a != null && this.f22022c == 16777216) {
                    m4096c(this.f22030a.get("notification_image_text_color"));
                }
                int i = this.f22022c;
                m4100a(mo4093a(), a10, a2, a3, i == 16777216 || !m4089a(i));
            } else if (Build.VERSION.SDK_INT >= 24) {
                mo4093a().setViewVisibility(a, 8);
                mo4093a().setViewVisibility(a9, 8);
                try {
                    C11176aw.m4812a((Object) this, "setStyle", C11479r.m2929a(mo4093a(), "android.app.Notification$DecoratedCustomViewStyle").getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                    AbstractC11049b.m5282a("load class DecoratedCustomViewStyle failed");
                }
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("miui.customHeight", true);
            addExtras(bundle);
            setCustomContentView(mo4093a());
            return;
        }
        mo4080b();
    }

    /* renamed from: a */
    private void m4100a(RemoteViews remoteViews, int i, int i2, int i3, boolean z) {
        int a = m4090a(6.0f);
        remoteViews.setViewPadding(i, a, 0, a, 0);
        if (z) {
            remoteViews.setTextColor(i2, -1);
            remoteViews.setTextColor(i3, -1);
            return;
        }
        remoteViews.setTextColor(i2, -16777216);
        remoteViews.setTextColor(i3, -16777216);
    }

    /* renamed from: a */
    private Drawable m4101a(int i, int i2, int i3, float f) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, null));
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.setIntrinsicWidth(i2);
        shapeDrawable.setIntrinsicHeight(i3);
        return shapeDrawable;
    }
}
