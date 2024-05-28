package com.xiaomi.push;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.Map;

@SuppressLint({"NewApi"})
/* renamed from: com.xiaomi.push.dv */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Notification$BuilderC11307dv extends Notification.Builder {

    /* renamed from: a */
    private Context f22017a;

    /* renamed from: a */
    public Notification$BuilderC11307dv mo4081a(Map<String, String> map) {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo4092a() {
    }

    public Notification$BuilderC11307dv(Context context) {
        super(context);
        this.f22017a = context;
    }

    /* renamed from: a */
    protected Context m4106a() {
        return this.f22017a;
    }

    /* renamed from: a */
    public int m4105a(Resources resources, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return resources.getIdentifier(str, str2, str3);
    }

    /* renamed from: a */
    public final int m4102a(String str) {
        return m4105a(m4106a().getResources(), str, "id", m4106a().getPackageName());
    }

    @Override // android.app.Notification.Builder
    public Notification build() {
        mo4092a();
        return super.build();
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a */
    public Notification$BuilderC11307dv addExtras(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 20) {
            super.addExtras(bundle);
        }
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a */
    public Notification$BuilderC11307dv setCustomContentView(RemoteViews remoteViews) {
        if (Build.VERSION.SDK_INT >= 24) {
            super.setCustomContentView(remoteViews);
        } else {
            super.setContent(remoteViews);
        }
        return this;
    }

    /* renamed from: a */
    public Notification$BuilderC11307dv mo4098a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                C11176aw.m4812a((Object) this, "setColor", Integer.valueOf(Color.parseColor(str)));
            } catch (Exception e) {
                AbstractC11049b.m5268d("fail to set color. " + e);
            }
        }
        return this;
    }
}
