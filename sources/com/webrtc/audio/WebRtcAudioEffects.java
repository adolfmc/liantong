package com.webrtc.audio;

import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import android.support.annotation.Nullable;
import com.webrtc.Logging;
import java.util.UUID;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WebRtcAudioEffects {
    private static final UUID AOSP_ACOUSTIC_ECHO_CANCELER = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");
    private static final UUID AOSP_NOISE_SUPPRESSOR = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioEffectsExternal";
    @Nullable
    private static AudioEffect.Descriptor[] cachedEffects;
    @Nullable
    private AcousticEchoCanceler aec;
    @Nullable

    /* renamed from: ns */
    private NoiseSuppressor f21271ns;
    private boolean shouldEnableAec;
    private boolean shouldEnableNs;

    public static boolean isAcousticEchoCancelerSupported() {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_AEC, AOSP_ACOUSTIC_ECHO_CANCELER);
    }

    public static boolean isNoiseSuppressorSupported() {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_NS, AOSP_NOISE_SUPPRESSOR);
    }

    public WebRtcAudioEffects() {
        Logging.m5305d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    public boolean setAEC(boolean z) {
        Logging.m5305d(TAG, "setAEC(" + z + ")");
        if (!isAcousticEchoCancelerSupported()) {
            Logging.m5301w(TAG, "Platform AEC is not supported");
            this.shouldEnableAec = false;
            return false;
        } else if (this.aec != null && z != this.shouldEnableAec) {
            Logging.m5304e(TAG, "Platform AEC state can't be modified while recording");
            return false;
        } else {
            this.shouldEnableAec = z;
            return true;
        }
    }

    public boolean setNS(boolean z) {
        Logging.m5305d(TAG, "setNS(" + z + ")");
        if (!isNoiseSuppressorSupported()) {
            Logging.m5301w(TAG, "Platform NS is not supported");
            this.shouldEnableNs = false;
            return false;
        } else if (this.f21271ns != null && z != this.shouldEnableNs) {
            Logging.m5304e(TAG, "Platform NS state can't be modified while recording");
            return false;
        } else {
            this.shouldEnableNs = z;
            return true;
        }
    }

    public void enable(int i) {
        Logging.m5305d(TAG, "enable(audioSession=" + i + ")");
        boolean z = true;
        assertTrue(this.aec == null);
        assertTrue(this.f21271ns == null);
        if (isAcousticEchoCancelerSupported()) {
            this.aec = AcousticEchoCanceler.create(i);
            AcousticEchoCanceler acousticEchoCanceler = this.aec;
            if (acousticEchoCanceler != null) {
                boolean enabled = acousticEchoCanceler.getEnabled();
                boolean z2 = this.shouldEnableAec && isAcousticEchoCancelerSupported();
                if (this.aec.setEnabled(z2) != 0) {
                    Logging.m5304e(TAG, "Failed to set the AcousticEchoCanceler state");
                }
                StringBuilder sb = new StringBuilder();
                sb.append("AcousticEchoCanceler: was ");
                sb.append(enabled ? "enabled" : "disabled");
                sb.append(", enable: ");
                sb.append(z2);
                sb.append(", is now: ");
                sb.append(this.aec.getEnabled() ? "enabled" : "disabled");
                Logging.m5305d(TAG, sb.toString());
            } else {
                Logging.m5304e(TAG, "Failed to create the AcousticEchoCanceler instance");
            }
        }
        if (isNoiseSuppressorSupported()) {
            this.f21271ns = NoiseSuppressor.create(i);
            NoiseSuppressor noiseSuppressor = this.f21271ns;
            if (noiseSuppressor != null) {
                boolean enabled2 = noiseSuppressor.getEnabled();
                if (!this.shouldEnableNs || !isNoiseSuppressorSupported()) {
                    z = false;
                }
                if (this.f21271ns.setEnabled(z) != 0) {
                    Logging.m5304e(TAG, "Failed to set the NoiseSuppressor state");
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("NoiseSuppressor: was ");
                sb2.append(enabled2 ? "enabled" : "disabled");
                sb2.append(", enable: ");
                sb2.append(z);
                sb2.append(", is now: ");
                sb2.append(this.f21271ns.getEnabled() ? "enabled" : "disabled");
                Logging.m5305d(TAG, sb2.toString());
                return;
            }
            Logging.m5304e(TAG, "Failed to create the NoiseSuppressor instance");
        }
    }

    public void release() {
        Logging.m5305d(TAG, "release");
        AcousticEchoCanceler acousticEchoCanceler = this.aec;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
            this.aec = null;
        }
        NoiseSuppressor noiseSuppressor = this.f21271ns;
        if (noiseSuppressor != null) {
            noiseSuppressor.release();
            this.f21271ns = null;
        }
    }

    private boolean effectTypeIsVoIP(UUID uuid) {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        return (AudioEffect.EFFECT_TYPE_AEC.equals(uuid) && isAcousticEchoCancelerSupported()) || (AudioEffect.EFFECT_TYPE_NS.equals(uuid) && isNoiseSuppressorSupported());
    }

    private static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    @Nullable
    private static AudioEffect.Descriptor[] getAvailableEffects() {
        AudioEffect.Descriptor[] descriptorArr = cachedEffects;
        if (descriptorArr != null) {
            return descriptorArr;
        }
        cachedEffects = AudioEffect.queryEffects();
        return cachedEffects;
    }

    private static boolean isEffectTypeAvailable(UUID uuid, UUID uuid2) {
        AudioEffect.Descriptor[] availableEffects = getAvailableEffects();
        if (availableEffects == null) {
            return false;
        }
        for (AudioEffect.Descriptor descriptor : availableEffects) {
            if (descriptor.type.equals(uuid)) {
                return !descriptor.uuid.equals(uuid2);
            }
        }
        return false;
    }
}
