package com.baidu.mapapi.map;

import android.graphics.Typeface;
import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class TextOptions extends OverlayOptions {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;

    /* renamed from: a */
    int f6417a;

    /* renamed from: c */
    Bundle f6419c;

    /* renamed from: d */
    private String f6420d;

    /* renamed from: e */
    private LatLng f6421e;

    /* renamed from: f */
    private int f6422f;

    /* renamed from: i */
    private Typeface f6425i;

    /* renamed from: l */
    private float f6428l;

    /* renamed from: g */
    private int f6423g = -16777216;

    /* renamed from: h */
    private int f6424h = 12;

    /* renamed from: j */
    private int f6426j = 4;

    /* renamed from: k */
    private int f6427k = 32;

    /* renamed from: b */
    boolean f6418b = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        Text text = new Text();
        text.f6299H = this.f6418b;
        text.f6298G = this.f6417a;
        text.f6300I = this.f6419c;
        text.f6407a = this.f6420d;
        text.f6408b = this.f6421e;
        text.f6409c = this.f6422f;
        text.f6410d = this.f6423g;
        text.f6411e = this.f6424h;
        text.f6412f = this.f6425i;
        text.f6413g = this.f6426j;
        text.f6414h = this.f6427k;
        text.f6415i = this.f6428l;
        return text;
    }

    public TextOptions align(int i, int i2) {
        this.f6426j = i;
        this.f6427k = i2;
        return this;
    }

    public TextOptions bgColor(int i) {
        this.f6422f = i;
        return this;
    }

    public TextOptions extraInfo(Bundle bundle) {
        this.f6419c = bundle;
        return this;
    }

    public TextOptions fontColor(int i) {
        this.f6423g = i;
        return this;
    }

    public TextOptions fontSize(int i) {
        this.f6424h = i;
        return this;
    }

    public float getAlignX() {
        return this.f6426j;
    }

    public float getAlignY() {
        return this.f6427k;
    }

    public int getBgColor() {
        return this.f6422f;
    }

    public Bundle getExtraInfo() {
        return this.f6419c;
    }

    public int getFontColor() {
        return this.f6423g;
    }

    public int getFontSize() {
        return this.f6424h;
    }

    public LatLng getPosition() {
        return this.f6421e;
    }

    public float getRotate() {
        return this.f6428l;
    }

    public String getText() {
        return this.f6420d;
    }

    public Typeface getTypeface() {
        return this.f6425i;
    }

    public int getZIndex() {
        return this.f6417a;
    }

    public boolean isVisible() {
        return this.f6418b;
    }

    public TextOptions position(LatLng latLng) {
        if (latLng != null) {
            this.f6421e = latLng;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: position can not be null");
    }

    public TextOptions rotate(float f) {
        this.f6428l = f;
        return this;
    }

    public TextOptions text(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("BDMapSDKException: text can not be null or empty");
        }
        this.f6420d = str;
        return this;
    }

    public TextOptions typeface(Typeface typeface) {
        this.f6425i = typeface;
        return this;
    }

    public TextOptions visible(boolean z) {
        this.f6418b = z;
        return this;
    }

    public TextOptions zIndex(int i) {
        this.f6417a = i;
        return this;
    }
}
