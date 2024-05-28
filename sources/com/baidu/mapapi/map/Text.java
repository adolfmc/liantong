package com.baidu.mapapi.map;

import android.graphics.Typeface;
import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import vi.com.gdi.bgl.android.java.EnvDrawText;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Text extends Overlay {

    /* renamed from: k */
    private static final String f6406k = "Text";

    /* renamed from: a */
    String f6407a;

    /* renamed from: b */
    LatLng f6408b;

    /* renamed from: c */
    int f6409c;

    /* renamed from: d */
    int f6410d;

    /* renamed from: e */
    int f6411e;

    /* renamed from: f */
    Typeface f6412f;

    /* renamed from: g */
    int f6413g;

    /* renamed from: h */
    int f6414h;

    /* renamed from: i */
    float f6415i;

    /* renamed from: j */
    int f6416j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Text() {
        this.type = EnumC2933i.text;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18868a() {
        Typeface typeface = this.f6412f;
        if (typeface != null) {
            EnvDrawText.removeFontCache(typeface.hashCode());
        }
        return super.mo18868a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009b  */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.Bundle mo18867a(android.os.Bundle r6) {
        /*
            r5 = this;
            super.mo18867a(r6)
            com.baidu.mapapi.model.LatLng r0 = r5.f6408b
            if (r0 == 0) goto Lb1
            java.lang.String r0 = "text"
            java.lang.String r1 = r5.f6407a
            r6.putString(r0, r1)
            com.baidu.mapapi.model.LatLng r0 = r5.f6408b
            com.baidu.platform.comapi.basestruct.GeoPoint r0 = com.baidu.mapapi.model.CoordUtil.ll2mc(r0)
            java.lang.String r1 = "location_x"
            double r2 = r0.getLongitudeE6()
            r6.putDouble(r1, r2)
            java.lang.String r1 = "location_y"
            double r2 = r0.getLatitudeE6()
            r6.putDouble(r1, r2)
            int r0 = r5.f6410d
            int r1 = r0 >>> 24
            int r2 = r0 >> 16
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r0 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = android.graphics.Color.argb(r1, r0, r3, r2)
            java.lang.String r1 = "font_color"
            r6.putInt(r1, r0)
            int r0 = r5.f6409c
            int r1 = r0 >>> 24
            int r2 = r0 >> 16
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r0 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = android.graphics.Color.argb(r1, r0, r3, r2)
            java.lang.String r1 = "bg_color"
            r6.putInt(r1, r0)
            java.lang.String r0 = "font_size"
            int r1 = r5.f6411e
            r6.putInt(r0, r1)
            android.graphics.Typeface r0 = r5.f6412f
            if (r0 == 0) goto L75
            int r0 = r0.hashCode()
            android.graphics.Typeface r1 = r5.f6412f
            vi.com.gdi.bgl.android.java.EnvDrawText.registFontCache(r0, r1)
            java.lang.String r0 = "type_face"
            android.graphics.Typeface r1 = r5.f6412f
            int r1 = r1.hashCode()
            r6.putInt(r0, r1)
        L75:
            int r0 = r5.f6413g
            r1 = 4
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            r4 = 1056964608(0x3f000000, float:0.5)
            if (r0 == r1) goto L82
            switch(r0) {
                case 1: goto L86;
                case 2: goto L84;
                default: goto L82;
            }
        L82:
            r0 = r4
            goto L87
        L84:
            r0 = r2
            goto L87
        L86:
            r0 = r3
        L87:
            java.lang.String r1 = "align_x"
            r6.putFloat(r1, r0)
            int r0 = r5.f6414h
            r1 = 8
            if (r0 == r1) goto L9b
            r1 = 16
            if (r0 == r1) goto L99
            r1 = 32
            goto L9c
        L99:
            r4 = r2
            goto L9c
        L9b:
            r4 = r3
        L9c:
            java.lang.String r0 = "align_y"
            r6.putFloat(r0, r4)
            java.lang.String r0 = "rotate"
            float r1 = r5.f6415i
            r6.putFloat(r0, r1)
            java.lang.String r0 = "update"
            int r1 = r5.f6416j
            r6.putInt(r0, r1)
            return r6
        Lb1:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "BDMapSDKException: when you add a text overlay, you must provide text and the position info."
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.Text.mo18867a(android.os.Bundle):android.os.Bundle");
    }

    public float getAlignX() {
        return this.f6413g;
    }

    public float getAlignY() {
        return this.f6414h;
    }

    public int getBgColor() {
        return this.f6409c;
    }

    public int getFontColor() {
        return this.f6410d;
    }

    public int getFontSize() {
        return this.f6411e;
    }

    public LatLng getPosition() {
        return this.f6408b;
    }

    public float getRotate() {
        return this.f6415i;
    }

    public String getText() {
        return this.f6407a;
    }

    public Typeface getTypeface() {
        return this.f6412f;
    }

    public void setAlign(int i, int i2) {
        this.f6413g = i;
        this.f6414h = i2;
        this.f6416j = 1;
        this.listener.mo18796c(this);
    }

    public void setBgColor(int i) {
        this.f6409c = i;
        this.f6416j = 1;
        this.listener.mo18796c(this);
    }

    public void setFontColor(int i) {
        this.f6410d = i;
        this.f6416j = 1;
        this.listener.mo18796c(this);
    }

    public void setFontSize(int i) {
        this.f6411e = i;
        this.f6416j = 1;
        this.listener.mo18796c(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: position can not be null");
        }
        this.f6408b = latLng;
        this.f6416j = 1;
        this.listener.mo18796c(this);
    }

    public void setRotate(float f) {
        this.f6415i = f;
        this.f6416j = 1;
        this.listener.mo18796c(this);
    }

    public void setText(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("BDMapSDKException: text can not be null or empty");
        }
        this.f6407a = str;
        this.f6416j = 1;
        this.listener.mo18796c(this);
    }

    public void setTypeface(Typeface typeface) {
        this.f6412f = typeface;
        this.f6416j = 1;
        this.listener.mo18796c(this);
    }
}
