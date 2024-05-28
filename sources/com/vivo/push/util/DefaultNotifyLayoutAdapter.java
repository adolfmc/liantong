package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

/* renamed from: com.vivo.push.util.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class DefaultNotifyLayoutAdapter implements BaseNotifyLayoutAdapter {

    /* renamed from: a */
    private Resources f21221a;

    /* renamed from: b */
    private String f21222b;

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final void init(Context context) {
        this.f21222b = context.getPackageName();
        this.f21221a = context.getResources();
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getNotificationLayout() {
        return this.f21221a.getIdentifier("push_notify", "layout", this.f21222b);
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getSuitIconId() {
        Resources resources;
        String str;
        if (Device.f21226d) {
            resources = this.f21221a;
            str = "notify_icon_rom30";
        } else if (Device.f21225c) {
            resources = this.f21221a;
            str = "notify_icon_rom20";
        } else {
            resources = this.f21221a;
            str = "notify_icon";
        }
        return resources.getIdentifier(str, "id", this.f21222b);
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getTitleColor() {
        int i;
        try {
            i = ((Integer) Utility.m5439a("com.android.internal.R$color", "vivo_notification_title_text_color")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        if (i > 0) {
            return this.f21221a.getColor(i);
        }
        if (Device.f21226d) {
            return -1;
        }
        if (Device.f21225c) {
            if (Device.f21226d) {
                return Color.parseColor("#ff999999");
            }
            return -1;
        }
        return -16777216;
    }
}
