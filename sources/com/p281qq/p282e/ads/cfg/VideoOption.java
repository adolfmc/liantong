package com.p281qq.p282e.ads.cfg;

import com.p281qq.p282e.comm.util.GDTLogger;
import org.json.JSONObject;

/* renamed from: com.qq.e.ads.cfg.VideoOption */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoOption {

    /* renamed from: a */
    private final boolean f17771a;

    /* renamed from: b */
    private final int f17772b;

    /* renamed from: c */
    private final boolean f17773c;

    /* renamed from: d */
    private final boolean f17774d;

    /* renamed from: e */
    private final boolean f17775e;

    /* renamed from: f */
    private final boolean f17776f;

    /* renamed from: g */
    private final boolean f17777g;

    /* renamed from: h */
    private final int f17778h;

    /* renamed from: i */
    private final int f17779i;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.ads.cfg.VideoOption$AutoPlayPolicy */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class AutoPlayPolicy {
        public static final int ALWAYS = 1;
        public static final int NEVER = 2;
        public static final int WIFI = 0;
    }

    /* renamed from: com.qq.e.ads.cfg.VideoOption$Builder */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Builder {

        /* renamed from: a */
        private boolean f17780a = true;

        /* renamed from: b */
        private int f17781b = 1;

        /* renamed from: c */
        private boolean f17782c = true;

        /* renamed from: d */
        private boolean f17783d = true;

        /* renamed from: e */
        private boolean f17784e = true;

        /* renamed from: f */
        private boolean f17785f = false;

        /* renamed from: g */
        private boolean f17786g = false;

        /* renamed from: h */
        private int f17787h;

        /* renamed from: i */
        private int f17788i;

        public VideoOption build() {
            return new VideoOption(this);
        }

        public Builder setAutoPlayMuted(boolean z) {
            this.f17780a = z;
            return this;
        }

        public Builder setAutoPlayPolicy(int i) {
            if (i < 0 || i > 2) {
                i = 1;
                GDTLogger.m8234e("setAutoPlayPolicy 设置失败，值只能为0到2之间的数值, 重置为 : 1");
            }
            this.f17781b = i;
            return this;
        }

        public Builder setDetailPageMuted(boolean z) {
            this.f17786g = z;
            return this;
        }

        public Builder setEnableDetailPage(boolean z) {
            this.f17784e = z;
            return this;
        }

        public Builder setEnableUserControl(boolean z) {
            this.f17785f = z;
            return this;
        }

        public Builder setMaxVideoDuration(int i) {
            this.f17787h = i;
            return this;
        }

        public Builder setMinVideoDuration(int i) {
            this.f17788i = i;
            return this;
        }

        public Builder setNeedCoverImage(boolean z) {
            this.f17783d = z;
            return this;
        }

        public Builder setNeedProgressBar(boolean z) {
            this.f17782c = z;
            return this;
        }
    }

    private VideoOption(Builder builder) {
        this.f17771a = builder.f17780a;
        this.f17772b = builder.f17781b;
        this.f17773c = builder.f17782c;
        this.f17774d = builder.f17783d;
        this.f17775e = builder.f17784e;
        this.f17776f = builder.f17785f;
        this.f17777g = builder.f17786g;
        this.f17778h = builder.f17787h;
        this.f17779i = builder.f17788i;
    }

    public boolean getAutoPlayMuted() {
        return this.f17771a;
    }

    public int getAutoPlayPolicy() {
        return this.f17772b;
    }

    public int getMaxVideoDuration() {
        return this.f17778h;
    }

    public int getMinVideoDuration() {
        return this.f17779i;
    }

    public JSONObject getOptions() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("autoPlayMuted", Boolean.valueOf(this.f17771a));
            jSONObject.putOpt("autoPlayPolicy", Integer.valueOf(this.f17772b));
            jSONObject.putOpt("detailPageMuted", Boolean.valueOf(this.f17777g));
        } catch (Exception e) {
            GDTLogger.m8235d("Get video options error: " + e.getMessage());
        }
        return jSONObject;
    }

    public boolean isDetailPageMuted() {
        return this.f17777g;
    }

    public boolean isEnableDetailPage() {
        return this.f17775e;
    }

    public boolean isEnableUserControl() {
        return this.f17776f;
    }

    public boolean isNeedCoverImage() {
        return this.f17774d;
    }

    public boolean isNeedProgressBar() {
        return this.f17773c;
    }
}
