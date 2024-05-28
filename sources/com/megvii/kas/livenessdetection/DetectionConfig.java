package com.megvii.kas.livenessdetection;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class DetectionConfig {
    public final float eyeOpenThreshold;
    public final float gaussianBlur;
    public final float integrity;
    public final int maxBrightness;
    public final int minBrightness;
    public final float minFaceSize;
    public final float motionBlur;
    public final float mouthOpenThreshold;
    public final float pitchAngle;
    public final long timeout;
    public final float yawAngle;

    /* synthetic */ DetectionConfig(Builder builder, byte b) {
        this(builder);
    }

    @Deprecated
    public final int getMinBrightness() {
        return this.minBrightness;
    }

    @Deprecated
    public final int getMaxBrightness() {
        return this.maxBrightness;
    }

    @Deprecated
    public final float getMotionBlur() {
        return this.motionBlur;
    }

    @Deprecated
    public final float getGaussianBlur() {
        return this.gaussianBlur;
    }

    @Deprecated
    public final long getTimeout() {
        return this.timeout;
    }

    @Deprecated
    public final float getYawAngle() {
        return this.yawAngle;
    }

    @Deprecated
    public final float getPitchAngle() {
        return this.pitchAngle;
    }

    @Deprecated
    public final float getMinFaceSize() {
        return this.minFaceSize;
    }

    @Deprecated
    public final float getEyeOpenThreshold() {
        return this.eyeOpenThreshold;
    }

    @Deprecated
    public final float getMouthOpenThreshold() {
        return this.mouthOpenThreshold;
    }

    private DetectionConfig(Builder builder) {
        this.gaussianBlur = builder.f12277f;
        this.motionBlur = builder.f12276e;
        this.pitchAngle = builder.f12273b;
        this.yawAngle = builder.f12272a;
        this.minBrightness = builder.f12274c;
        this.maxBrightness = builder.f12275d;
        this.minFaceSize = builder.f12278g;
        this.timeout = builder.f12279h;
        this.eyeOpenThreshold = builder.f12280i;
        this.mouthOpenThreshold = builder.f12281j;
        this.integrity = builder.f12282k;
    }

    public final String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("gaussianBlur", this.gaussianBlur);
            jSONObject.put("motionBlur", this.motionBlur);
            jSONObject.put("pitchAngle", this.pitchAngle);
            jSONObject.put("yawAngle", this.yawAngle);
            jSONObject.put("minBrightness", this.minBrightness);
            jSONObject.put("maxBrightness", this.maxBrightness);
            jSONObject.put("minFaceSize", this.minFaceSize);
            jSONObject.put("timeout", this.timeout);
            jSONObject.put("eyeOpenThreshold", this.eyeOpenThreshold);
            jSONObject.put("mouthOpenThreshold", this.mouthOpenThreshold);
            jSONObject.put("integrity", this.integrity);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class Builder {

        /* renamed from: a */
        private float f12272a = 0.17f;

        /* renamed from: b */
        private float f12273b = 0.17f;

        /* renamed from: c */
        private int f12274c = 80;

        /* renamed from: d */
        private int f12275d = 170;

        /* renamed from: e */
        private float f12276e = 0.1f;

        /* renamed from: f */
        private float f12277f = 0.08f;

        /* renamed from: g */
        private float f12278g = 150.0f;

        /* renamed from: h */
        private int f12279h = 10000;

        /* renamed from: i */
        private float f12280i = 0.3f;

        /* renamed from: j */
        private float f12281j = 0.4f;

        /* renamed from: k */
        private float f12282k = 0.9f;

        public final Builder setMinFaceSize(int i) {
            this.f12278g = i;
            return this;
        }

        public final Builder setDetectionTimeout(int i) {
            this.f12279h = i;
            return this;
        }

        public final Builder setMaxAngle(float f, float f2, float f3) {
            this.f12273b = f;
            this.f12272a = f2;
            return this;
        }

        public final Builder setBrightness(int i, int i2) {
            this.f12274c = i;
            this.f12275d = i2;
            return this;
        }

        public final Builder setMouthHwratio(float f) {
            this.f12281j = f;
            return this;
        }

        public final Builder setEyeHwratio(float f) {
            this.f12280i = f;
            return this;
        }

        public final Builder setBlur(float f, float f2) {
            this.f12277f = f;
            this.f12276e = f2;
            return this;
        }

        public final Builder setIntegrity(float f) {
            this.f12282k = f;
            return this;
        }

        public final DetectionConfig build() {
            return new DetectionConfig(this, (byte) 0);
        }
    }
}
