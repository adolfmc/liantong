package com.baidu.mapsdkplatform.comapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.WinRound;
import com.baidu.platform.comapi.basestruct.Point;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.x */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2948x {

    /* renamed from: t */
    private static final String f7368t = "x";

    /* renamed from: m */
    public double f7381m;

    /* renamed from: n */
    public double f7382n;

    /* renamed from: o */
    public int f7383o;

    /* renamed from: p */
    public String f7384p;

    /* renamed from: q */
    public float f7385q;

    /* renamed from: r */
    public boolean f7386r;

    /* renamed from: s */
    public int f7387s;

    /* renamed from: a */
    public float f7369a = 12.0f;

    /* renamed from: b */
    public int f7370b = 0;

    /* renamed from: c */
    public int f7371c = 0;

    /* renamed from: d */
    public double f7372d = 1.2958162E7d;

    /* renamed from: e */
    public double f7373e = 4825907.0d;

    /* renamed from: h */
    public float f7376h = 0.0f;

    /* renamed from: i */
    public float f7377i = 0.0f;

    /* renamed from: f */
    public int f7374f = -1;

    /* renamed from: g */
    public int f7375g = -1;

    /* renamed from: j */
    public WinRound f7378j = new WinRound();

    /* renamed from: k */
    public C2949a f7379k = new C2949a();

    /* renamed from: l */
    public boolean f7380l = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.map.x$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2949a {

        /* renamed from: a */
        public long f7388a = 0;

        /* renamed from: b */
        public long f7389b = 0;

        /* renamed from: c */
        public long f7390c = 0;

        /* renamed from: d */
        public long f7391d = 0;

        /* renamed from: e */
        public Point f7392e = new Point(0, 0);

        /* renamed from: f */
        public Point f7393f = new Point(0, 0);

        /* renamed from: g */
        public Point f7394g = new Point(0, 0);

        /* renamed from: h */
        public Point f7395h = new Point(0, 0);

        public C2949a() {
        }
    }

    /* renamed from: a */
    public Bundle m18190a(C2925d c2925d) {
        int i;
        if (this.f7369a < c2925d.f7272b) {
            this.f7369a = c2925d.f7272b;
        }
        if (this.f7369a > c2925d.f7271a) {
            if (this.f7369a == 1096.0f || C2925d.f7250d == 26.0f) {
                this.f7369a = 26.0f;
                C2925d.f7250d = 26.0f;
            } else {
                this.f7369a = c2925d.f7271a;
            }
        }
        while (true) {
            i = this.f7370b;
            if (i >= 0) {
                break;
            }
            this.f7370b = i + 360;
        }
        this.f7370b = i % 360;
        if (this.f7371c > 0) {
            this.f7371c = 0;
        }
        if (this.f7371c < -45) {
            this.f7371c = -45;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("level", this.f7369a);
        bundle.putDouble("rotation", this.f7370b);
        bundle.putDouble("overlooking", this.f7371c);
        bundle.putDouble("centerptx", this.f7372d);
        bundle.putDouble("centerpty", this.f7373e);
        bundle.putInt("left", this.f7378j.left);
        bundle.putInt("right", this.f7378j.right);
        bundle.putInt("top", this.f7378j.top);
        bundle.putInt("bottom", this.f7378j.bottom);
        int i2 = this.f7374f;
        if (i2 >= 0 && this.f7375g >= 0 && i2 <= this.f7378j.right && this.f7375g <= this.f7378j.bottom && this.f7378j.right > 0 && this.f7378j.bottom > 0) {
            int i3 = this.f7374f - ((this.f7378j.right - this.f7378j.left) / 2);
            int i4 = this.f7375g - ((this.f7378j.bottom - this.f7378j.top) / 2);
            this.f7376h = i3;
            this.f7377i = -i4;
            bundle.putFloat("xoffset", this.f7376h);
            bundle.putFloat("yoffset", this.f7377i);
        }
        bundle.putInt("lbx", this.f7379k.f7392e.getIntX());
        bundle.putInt("lby", this.f7379k.f7392e.getIntY());
        bundle.putInt("ltx", this.f7379k.f7393f.getIntX());
        bundle.putInt("lty", this.f7379k.f7393f.getIntY());
        bundle.putInt("rtx", this.f7379k.f7394g.getIntX());
        bundle.putInt("rty", this.f7379k.f7394g.getIntY());
        bundle.putInt("rbx", this.f7379k.f7395h.getIntX());
        bundle.putInt("rby", this.f7379k.f7395h.getIntY());
        bundle.putLong("gleft", this.f7379k.f7388a);
        bundle.putLong("gbottom", this.f7379k.f7391d);
        bundle.putLong("gtop", this.f7379k.f7390c);
        bundle.putLong("gright", this.f7379k.f7389b);
        bundle.putInt("bfpp", this.f7380l ? 1 : 0);
        bundle.putInt("animation", 1);
        bundle.putInt("animatime", this.f7383o);
        bundle.putString("panoid", this.f7384p);
        bundle.putInt("autolink", 0);
        bundle.putFloat("siangle", this.f7385q);
        bundle.putInt("isbirdeye", this.f7386r ? 1 : 0);
        bundle.putInt("ssext", this.f7387s);
        return bundle;
    }

    /* renamed from: a */
    public void m18191a(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.f7369a = (float) bundle.getDouble("level");
        this.f7370b = (int) bundle.getDouble("rotation");
        this.f7371c = (int) bundle.getDouble("overlooking");
        this.f7372d = bundle.getDouble("centerptx");
        this.f7373e = bundle.getDouble("centerpty");
        this.f7378j.left = bundle.getInt("left");
        this.f7378j.right = bundle.getInt("right");
        this.f7378j.top = bundle.getInt("top");
        this.f7378j.bottom = bundle.getInt("bottom");
        this.f7376h = bundle.getFloat("xoffset");
        this.f7377i = bundle.getFloat("yoffset");
        if (this.f7378j.right != 0 && this.f7378j.bottom != 0) {
            int i = (int) this.f7376h;
            int i2 = (int) (-this.f7377i);
            this.f7374f = i + ((this.f7378j.right - this.f7378j.left) / 2);
            this.f7375g = i2 + ((this.f7378j.bottom - this.f7378j.top) / 2);
        }
        this.f7379k.f7388a = bundle.getLong("gleft");
        this.f7379k.f7389b = bundle.getLong("gright");
        this.f7379k.f7390c = bundle.getLong("gtop");
        this.f7379k.f7391d = bundle.getLong("gbottom");
        if (this.f7379k.f7388a <= -20037508) {
            this.f7379k.f7388a = -20037508L;
        }
        if (this.f7379k.f7389b >= 20037508) {
            this.f7379k.f7389b = 20037508L;
        }
        if (this.f7379k.f7390c >= 20037508) {
            this.f7379k.f7390c = 20037508L;
        }
        if (this.f7379k.f7391d <= -20037508) {
            this.f7379k.f7391d = -20037508L;
        }
        this.f7379k.f7392e.doubleX = this.f7379k.f7388a;
        this.f7379k.f7392e.doubleY = this.f7379k.f7391d;
        this.f7379k.f7393f.doubleX = this.f7379k.f7388a;
        this.f7379k.f7393f.doubleY = this.f7379k.f7390c;
        this.f7379k.f7394g.doubleX = this.f7379k.f7389b;
        this.f7379k.f7394g.doubleY = this.f7379k.f7390c;
        this.f7379k.f7395h.doubleX = this.f7379k.f7389b;
        this.f7379k.f7395h.doubleY = this.f7379k.f7391d;
        this.f7380l = bundle.getInt("bfpp") == 1;
        this.f7381m = bundle.getFloat("adapterZoomUnits");
        this.f7382n = bundle.getDouble("zoomunit");
        this.f7384p = bundle.getString("panoid");
        this.f7385q = bundle.getFloat("siangle");
        this.f7386r = bundle.getInt("isbirdeye") != 0;
        this.f7387s = bundle.getInt("ssext");
    }
}
