package com.xiaomi.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11533af;
import com.xiaomi.push.service.C11534ag;
import com.xiaomi.push.service.C11635x;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.xiaomi.push.dx */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractNotification$BuilderC11309dx extends Notification$BuilderC11307dv {

    /* renamed from: a */
    private int f22024a;

    /* renamed from: a */
    protected Bitmap f22025a;

    /* renamed from: a */
    private RemoteViews f22026a;

    /* renamed from: a */
    protected CharSequence f22027a;

    /* renamed from: a */
    private String f22028a;

    /* renamed from: a */
    private ArrayList<Notification.Action> f22029a;

    /* renamed from: a */
    protected Map<String, String> f22030a;

    /* renamed from: a */
    private boolean f22031a;

    /* renamed from: b */
    private int f22032b;

    /* renamed from: b */
    protected CharSequence f22033b;

    /* renamed from: b */
    private boolean f22034b;

    /* renamed from: a */
    protected abstract String mo4093a();

    /* renamed from: a */
    protected void m4087a(int i, Notification.Action action) {
    }

    /* renamed from: a */
    protected abstract boolean mo4091a();

    /* renamed from: b */
    protected abstract String mo4080b();

    public AbstractNotification$BuilderC11309dx(Context context, String str) {
        this(context, 0, str);
    }

    public AbstractNotification$BuilderC11309dx(Context context, int i, String str) {
        super(context);
        this.f22029a = new ArrayList<>();
        this.f22032b = 0;
        this.f22028a = str;
        this.f22024a = i;
        m4075c();
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a */
    public AbstractNotification$BuilderC11309dx setContentTitle(CharSequence charSequence) {
        this.f22027a = charSequence;
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: b */
    public AbstractNotification$BuilderC11309dx setContentText(CharSequence charSequence) {
        this.f22033b = charSequence;
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a */
    public AbstractNotification$BuilderC11309dx setLargeIcon(Bitmap bitmap) {
        this.f22025a = bitmap;
        return this;
    }

    @Override // com.xiaomi.push.Notification$BuilderC11307dv
    /* renamed from: a */
    public Notification$BuilderC11307dv mo4081a(Map<String, String> map) {
        this.f22030a = map;
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a */
    public AbstractNotification$BuilderC11309dx addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        addAction(new Notification.Action(i, charSequence, pendingIntent));
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a */
    public AbstractNotification$BuilderC11309dx addAction(Notification.Action action) {
        if (action != null) {
            this.f22029a.add(action);
        }
        int i = this.f22032b;
        this.f22032b = i + 1;
        m4087a(i, action);
        return this;
    }

    /* renamed from: c */
    private boolean m4074c() {
        Map<String, String> map = this.f22030a;
        return map != null && Boolean.parseBoolean(map.get("custom_builder_set_title"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaomi.push.Notification$BuilderC11307dv
    /* renamed from: a */
    public void mo4092a() {
        super.mo4092a();
        Bundle bundle = new Bundle();
        if (m4072d()) {
            bundle.putBoolean("mipush.customCopyLayout", this.f22034b);
        } else {
            bundle.putBoolean("mipush.customCopyLayout", false);
        }
        bundle.putBoolean("miui.customHeight", false);
        bundle.putBoolean("mipush.customNotification", true);
        bundle.putInt("mipush.customLargeIconId", m4102a("large_icon"));
        if (this.f22029a.size() > 0) {
            Notification.Action[] actionArr = new Notification.Action[this.f22029a.size()];
            this.f22029a.toArray(actionArr);
            bundle.putParcelableArray("mipush.customActions", actionArr);
        }
        if (m4074c() || !C11534ag.m2735a(m4095a().getContentResolver())) {
            m4073d();
        } else {
            bundle.putCharSequence("mipush.customTitle", this.f22027a);
            bundle.putCharSequence("mipush.customContent", this.f22033b);
        }
        addExtras(bundle);
    }

    /* renamed from: c */
    private void m4075c() {
        int a = m4105a(m4095a().getResources(), m4076c(), "layout", m4095a().getPackageName());
        if (a != 0) {
            this.f22026a = new RemoteViews(m4095a().getPackageName(), a);
            this.f22031a = mo4091a();
            return;
        }
        AbstractC11049b.m5282a("create RemoteViews failed, no such layout resource was found");
    }

    /* renamed from: b */
    protected final void m4079b() {
        super.setContentTitle(this.f22027a);
        super.setContentText(this.f22033b);
        Bitmap bitmap = this.f22025a;
        if (bitmap != null) {
            super.setLargeIcon(bitmap);
        }
    }

    /* renamed from: d */
    private void m4073d() {
        super.setContentTitle(this.f22027a);
        super.setContentText(this.f22033b);
    }

    /* renamed from: c */
    private String m4076c() {
        this.f22034b = m4071e();
        return this.f22034b ? mo4080b() : mo4093a();
    }

    /* renamed from: a */
    public final RemoteViews m4094a() {
        return this.f22026a;
    }

    /* renamed from: b */
    protected final boolean m4078b() {
        return this.f22031a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m4089a(int i) {
        Bitmap m4095a = m4095a();
        if (m4095a != null) {
            m4094a().setImageViewBitmap(i, m4095a);
            return;
        }
        int m3713b = C11395g.m3713b(m4095a(), this.f22028a);
        if (m3713b != 0) {
            m4094a().setImageViewResource(i, m3713b);
        }
    }

    /* renamed from: a */
    private Bitmap m4095a() {
        return C11635x.m2315a(C11395g.m3718a(m4095a(), this.f22028a));
    }

    /* renamed from: d */
    private boolean m4072d() {
        return (TextUtils.isEmpty(mo4080b()) || TextUtils.isEmpty(this.f22028a)) ? false : true;
    }

    /* renamed from: e */
    private boolean m4071e() {
        return m4072d() && m4070f();
    }

    /* renamed from: f */
    private boolean m4070f() {
        List<StatusBarNotification> m2749b;
        if (Build.VERSION.SDK_INT < 20 || (m2749b = C11533af.m2760a(m4095a(), this.f22028a).m2749b()) == null || m2749b.isEmpty()) {
            return false;
        }
        for (StatusBarNotification statusBarNotification : m2749b) {
            if (statusBarNotification.getId() == this.f22024a) {
                Notification notification = statusBarNotification.getNotification();
                if (notification == null) {
                    return false;
                }
                return !notification.extras.getBoolean("mipush.customCopyLayout", true);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public int m4090a(float f) {
        return (int) ((f * m4095a().getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Bitmap m4083a(Bitmap bitmap, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(new RectF(rect), f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* renamed from: a */
    protected final boolean m4088a(int i) {
        return ((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d) < 192.0d;
    }
}
