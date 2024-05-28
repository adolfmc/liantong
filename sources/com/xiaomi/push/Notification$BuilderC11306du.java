package com.xiaomi.push;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* renamed from: com.xiaomi.push.du */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Notification$BuilderC11306du extends AbstractNotification$BuilderC11309dx {

    /* renamed from: a */
    private int f22014a;

    /* renamed from: b */
    private Bitmap f22015b;

    /* renamed from: c */
    private Bitmap f22016c;

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx, android.app.Notification.Builder
    /* renamed from: a */
    public AbstractNotification$BuilderC11309dx setLargeIcon(Bitmap bitmap) {
        return this;
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx
    /* renamed from: a */
    protected String mo4093a() {
        return "notification_banner";
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx
    /* renamed from: b */
    protected String mo4080b() {
        return null;
    }

    public Notification$BuilderC11306du(Context context, String str) {
        super(context, str);
        this.f22014a = 16777216;
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx
    /* renamed from: a */
    protected boolean mo4091a() {
        if (C11469j.m2974a()) {
            Resources resources = mo4093a().getResources();
            String packageName = mo4093a().getPackageName();
            return (m4105a(mo4093a().getResources(), "bg", "id", mo4093a().getPackageName()) == 0 || m4105a(resources, "icon", "id", packageName) == 0 || m4105a(resources, "title", "id", packageName) == 0 || C11469j.m2973a(mo4093a()) < 9) ? false : true;
        }
        return false;
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx
    /* renamed from: a */
    public Notification$BuilderC11306du mo4084a(Bitmap bitmap) {
        if (mo4080b() && bitmap != null) {
            if (bitmap.getWidth() == 984 && 184 <= bitmap.getHeight() && bitmap.getHeight() <= 1678) {
                this.f22015b = bitmap;
            } else {
                AbstractC11049b.m5282a("colorful notification banner image resolution error, must belong to [984*184, 984*1678]");
            }
        }
        return this;
    }

    /* renamed from: b */
    public Notification$BuilderC11306du m4107b(Bitmap bitmap) {
        if (mo4080b() && bitmap != null) {
            this.f22016c = bitmap;
        }
        return this;
    }

    @Override // com.xiaomi.push.Notification$BuilderC11307dv
    /* renamed from: a */
    public Notification$BuilderC11306du mo4098a(String str) {
        if (mo4080b() && !TextUtils.isEmpty(str)) {
            try {
                this.f22014a = Color.parseColor(str);
            } catch (Exception unused) {
                AbstractC11049b.m5282a("parse banner notification image text color error");
            }
        }
        return this;
    }

    @Override // com.xiaomi.push.AbstractNotification$BuilderC11309dx, com.xiaomi.push.Notification$BuilderC11307dv
    /* renamed from: a */
    public void mo4092a() {
        if (mo4080b() && this.f22015b != null) {
            super.mo4092a();
            Resources resources = mo4093a().getResources();
            String packageName = mo4093a().getPackageName();
            int a = m4105a(resources, "bg", "id", packageName);
            if (C11469j.m2973a(mo4093a()) >= 10) {
                mo4093a().setImageViewBitmap(a, m4083a(this.f22015b, 30.0f));
            } else {
                mo4093a().setImageViewBitmap(a, this.f22015b);
            }
            int a2 = m4105a(resources, "icon", "id", packageName);
            if (this.f22016c != null) {
                mo4093a().setImageViewBitmap(a2, this.f22016c);
            } else {
                m4089a(a2);
            }
            int a3 = m4105a(resources, "title", "id", packageName);
            mo4093a().setTextViewText(a3, this.f22027a);
            if (this.f22030a != null && this.f22014a == 16777216) {
                mo4098a(this.f22030a.get("notification_image_text_color"));
            }
            RemoteViews a4 = mo4093a();
            int i = this.f22014a;
            a4.setTextColor(a3, (i == 16777216 || !m4089a(i)) ? -1 : -16777216);
            setCustomContentView(mo4093a());
            Bundle bundle = new Bundle();
            bundle.putBoolean("miui.customHeight", true);
            addExtras(bundle);
            return;
        }
        mo4080b();
    }
}
