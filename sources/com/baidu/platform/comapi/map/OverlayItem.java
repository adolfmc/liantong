package com.baidu.platform.comapi.map;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OverlayItem {
    public static final int ALIGN_BOTTON = 2;
    public static final int ALIGN_TOP = 3;
    public static final int ALING_CENTER = 1;

    /* renamed from: a */
    protected GeoPoint f7703a;

    /* renamed from: b */
    protected String f7704b;

    /* renamed from: c */
    protected String f7705c;

    /* renamed from: e */
    private int f7707e;

    /* renamed from: f */
    private int f7708f;

    /* renamed from: m */
    private Bundle f7715m;

    /* renamed from: n */
    private Bundle f7716n;

    /* renamed from: o */
    private float f7717o;

    /* renamed from: p */
    private byte[] f7718p;

    /* renamed from: q */
    private float f7719q;

    /* renamed from: s */
    private int f7721s;

    /* renamed from: i */
    private CoordType f7711i = CoordType.CoordType_BD09;

    /* renamed from: g */
    private Drawable f7709g = null;

    /* renamed from: r */
    private int f7720r = 0;

    /* renamed from: d */
    private int f7706d = 2;

    /* renamed from: h */
    private String f7710h = "";

    /* renamed from: j */
    private float f7712j = 0.5f;

    /* renamed from: k */
    private float f7713k = 1.0f;

    /* renamed from: l */
    private ArrayList<Bundle> f7714l = new ArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum AnimEffect {
        NONE,
        GROWTH,
        WAVE,
        SHRINK,
        FADE_OUT,
        FADE_IN,
        GROWTH_FADE_IN,
        SHRINK_FADE_OUT,
        GROWTH_REBOUND,
        ALPHA,
        ANCHOR_GROUTH,
        ROTATE
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum AnimationSubType {
        NONE,
        RADAR
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum CoordType {
        CoordType_BD09LL,
        CoordType_BD09
    }

    public OverlayItem(GeoPoint geoPoint, String str, String str2) {
        this.f7703a = geoPoint;
        this.f7704b = str;
        this.f7705c = str2;
    }

    public void addClickRect(Bundle bundle) {
        if (this.f7714l == null) {
            this.f7714l = new ArrayList<>();
        }
        this.f7714l.add(bundle);
    }

    public float getAnchorX() {
        return this.f7712j;
    }

    public float getAnchorY() {
        return this.f7713k;
    }

    public Bundle getAnimate() {
        return this.f7715m;
    }

    public int getBound() {
        return this.f7706d;
    }

    public ArrayList<Bundle> getClickRect() {
        return this.f7714l;
    }

    public CoordType getCoordType() {
        return this.f7711i;
    }

    public Bundle getDelay() {
        return this.f7716n;
    }

    public float getGeoZ() {
        return this.f7717o;
    }

    public byte[] getGifData() {
        return this.f7718p;
    }

    public String getId() {
        return this.f7710h;
    }

    public int getIndoorPoi() {
        return this.f7721s;
    }

    public int getLevel() {
        return this.f7707e;
    }

    public final Drawable getMarker() {
        return this.f7709g;
    }

    public int getMask() {
        return this.f7708f;
    }

    public float getMultiplyDpi() {
        return this.f7720r;
    }

    public GeoPoint getPoint() {
        return this.f7703a;
    }

    public int getResId() {
        if (getMarker() == null) {
            return -1;
        }
        return getMarker().hashCode();
    }

    public float getScale() {
        return this.f7719q;
    }

    public String getSnippet() {
        return this.f7705c;
    }

    public String getTitle() {
        return this.f7704b;
    }

    public void setAnchor(float f, float f2) {
        this.f7712j = f;
        this.f7713k = f2;
    }

    public void setAnchor(int i) {
        float f;
        switch (i) {
            case 1:
                setAnchor(0.5f, 0.5f);
                return;
            case 2:
                f = 1.0f;
                break;
            case 3:
                f = 0.0f;
                break;
            default:
                return;
        }
        setAnchor(0.5f, f);
    }

    public void setAnimate(Bundle bundle) {
        this.f7715m = bundle;
    }

    public void setAnimateDuration(int i) {
        if (this.f7715m == null) {
            this.f7715m = new Bundle();
        }
        this.f7715m.putInt("dur", i);
    }

    public void setAnimateEffect(AnimEffect animEffect) {
        Bundle bundle;
        String str;
        int i;
        if (this.f7715m == null) {
            this.f7715m = new Bundle();
        }
        switch (animEffect) {
            case GROWTH:
                bundle = this.f7715m;
                str = "type";
                i = 1;
                break;
            case WAVE:
                bundle = this.f7715m;
                str = "type";
                i = 2;
                break;
            case SHRINK:
                bundle = this.f7715m;
                str = "type";
                i = 3;
                break;
            case FADE_OUT:
                bundle = this.f7715m;
                str = "type";
                i = 4;
                break;
            case FADE_IN:
                bundle = this.f7715m;
                str = "type";
                i = 5;
                break;
            case GROWTH_FADE_IN:
                bundle = this.f7715m;
                str = "type";
                i = 6;
                break;
            case SHRINK_FADE_OUT:
                bundle = this.f7715m;
                str = "type";
                i = 7;
                break;
            case GROWTH_REBOUND:
                bundle = this.f7715m;
                str = "type";
                i = 8;
                break;
            case ALPHA:
                bundle = this.f7715m;
                str = "type";
                i = 9;
                break;
            case ANCHOR_GROUTH:
                bundle = this.f7715m;
                str = "type";
                i = 10;
                break;
            case ROTATE:
                bundle = this.f7715m;
                str = "type";
                i = 11;
                break;
            default:
                bundle = this.f7715m;
                str = "type";
                i = 0;
                break;
        }
        bundle.putInt(str, i);
    }

    public void setAnimateEndSize(int i, int i2) {
        if (this.f7715m == null) {
            this.f7715m = new Bundle();
        }
        this.f7715m.putInt("en_w", i);
        this.f7715m.putInt("en_h", i2);
    }

    public void setAnimateStartSize(int i, int i2) {
        if (this.f7715m == null) {
            this.f7715m = new Bundle();
        }
        this.f7715m.putInt("st_w", i);
        this.f7715m.putInt("st_h", i2);
    }

    public void setBound(int i) {
        this.f7706d = i;
    }

    public void setClickRect(ArrayList<Bundle> arrayList) {
        this.f7714l = arrayList;
    }

    public void setCoordType(CoordType coordType) {
        this.f7711i = coordType;
    }

    public void setDelay(Bundle bundle) {
        this.f7716n = bundle;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.f7703a = geoPoint;
    }

    public void setGeoZ(float f) {
        this.f7717o = f;
    }

    public void setGifData(byte[] bArr) {
        this.f7718p = bArr;
    }

    public void setId(String str) {
        this.f7710h = str;
    }

    public void setIndoorPoi(int i) {
        this.f7721s = i;
    }

    public void setLevel(int i) {
        this.f7707e = i;
    }

    public void setMarker(Drawable drawable) {
        this.f7709g = drawable;
    }

    public void setMask(int i) {
        this.f7708f = i;
    }

    public void setMultiplyDpi(int i) {
        this.f7720r = i;
    }

    public void setScale(float f) {
        this.f7719q = f;
    }

    public void setSnippet(String str) {
        this.f7705c = str;
    }

    public void setSubAnimateEffect(AnimationSubType animationSubType) {
        if (this.f7715m == null) {
            this.f7715m = new Bundle();
        }
        if (C3001ad.f7730b[animationSubType.ordinal()] != 1) {
            this.f7715m.putInt("sub_type", 0);
        } else {
            this.f7715m.putInt("sub_type", 1);
        }
    }

    public void setTitle(String str) {
        this.f7704b = str;
    }
}
