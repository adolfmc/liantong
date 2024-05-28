package com.p281qq.p282e.ads.hybrid;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.hybrid.HybridADSetting */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HybridADSetting {
    public static final int TYPE_REWARD_VIDEO = 1;

    /* renamed from: f */
    private String f17808f;

    /* renamed from: g */
    private String f17809g;

    /* renamed from: h */
    private String f17810h;

    /* renamed from: a */
    private int f17803a = 1;

    /* renamed from: b */
    private int f17804b = 44;

    /* renamed from: c */
    private int f17805c = -1;

    /* renamed from: d */
    private int f17806d = -14013133;

    /* renamed from: e */
    private int f17807e = 16;

    /* renamed from: i */
    private int f17811i = -1776153;

    /* renamed from: j */
    private int f17812j = 16;

    public HybridADSetting backButtonImage(String str) {
        this.f17809g = str;
        return this;
    }

    public HybridADSetting backSeparatorLength(int i) {
        this.f17812j = i;
        return this;
    }

    public HybridADSetting closeButtonImage(String str) {
        this.f17810h = str;
        return this;
    }

    public String getBackButtonImage() {
        return this.f17809g;
    }

    public int getBackSeparatorLength() {
        return this.f17812j;
    }

    public String getCloseButtonImage() {
        return this.f17810h;
    }

    public int getSeparatorColor() {
        return this.f17811i;
    }

    public String getTitle() {
        return this.f17808f;
    }

    public int getTitleBarColor() {
        return this.f17805c;
    }

    public int getTitleBarHeight() {
        return this.f17804b;
    }

    public int getTitleColor() {
        return this.f17806d;
    }

    public int getTitleSize() {
        return this.f17807e;
    }

    public int getType() {
        return this.f17803a;
    }

    public HybridADSetting separatorColor(int i) {
        this.f17811i = i;
        return this;
    }

    public HybridADSetting title(String str) {
        this.f17808f = str;
        return this;
    }

    public HybridADSetting titleBarColor(int i) {
        this.f17805c = i;
        return this;
    }

    public HybridADSetting titleBarHeight(int i) {
        this.f17804b = i;
        return this;
    }

    public HybridADSetting titleColor(int i) {
        this.f17806d = i;
        return this;
    }

    public HybridADSetting titleSize(int i) {
        this.f17807e = i;
        return this;
    }

    public HybridADSetting type(int i) {
        this.f17803a = i;
        return this;
    }
}
