package org.webrtc.voiceengine;

import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import android.support.annotation.Nullable;
import java.util.UUID;
import org.webrtc.Logging;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class WebRtcAudioEffects {
    private static final UUID AOSP_ACOUSTIC_ECHO_CANCELER = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");
    private static final UUID AOSP_NOISE_SUPPRESSOR = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioEffects";
    @Nullable
    private static AudioEffect.Descriptor[] cachedEffects;
    @Nullable
    private AcousticEchoCanceler aec;
    @Nullable

    /* renamed from: ns */
    private NoiseSuppressor f27441ns;
    private boolean shouldEnableAec;
    private boolean shouldEnableNs;

    public static boolean isAcousticEchoCancelerSupported() {
        return isAcousticEchoCancelerEffectAvailable();
    }

    public static boolean isNoiseSuppressorSupported() {
        return isNoiseSuppressorEffectAvailable();
    }

    public static boolean isAcousticEchoCancelerBlacklisted() {
        boolean contains = WebRtcAudioUtils.getBlackListedModelsForAecUsage().contains(Build.MODEL);
        if (contains) {
            Logging.m202w("WebRtcAudioEffects", Build.MODEL + " is blacklisted for HW AEC usage!");
        }
        return contains;
    }

    public static boolean isNoiseSuppressorBlacklisted() {
        boolean contains = WebRtcAudioUtils.getBlackListedModelsForNsUsage().contains(Build.MODEL);
        if (contains) {
            Logging.m202w("WebRtcAudioEffects", Build.MODEL + " is blacklisted for HW NS usage!");
        }
        return contains;
    }

    private static boolean isAcousticEchoCancelerExcludedByUUID() {
        AudioEffect.Descriptor[] availableEffects;
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        for (AudioEffect.Descriptor descriptor : getAvailableEffects()) {
            if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_AEC) && descriptor.uuid.equals(AOSP_ACOUSTIC_ECHO_CANCELER)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNoiseSuppressorExcludedByUUID() {
        AudioEffect.Descriptor[] availableEffects;
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        for (AudioEffect.Descriptor descriptor : getAvailableEffects()) {
            if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_NS) && descriptor.uuid.equals(AOSP_NOISE_SUPPRESSOR)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAcousticEchoCancelerEffectAvailable() {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_AEC);
    }

    private static boolean isNoiseSuppressorEffectAvailable() {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_NS);
    }

    public static boolean canUseAcousticEchoCanceler() {
        boolean z = (!isAcousticEchoCancelerSupported() || WebRtcAudioUtils.useWebRtcBasedAcousticEchoCanceler() || isAcousticEchoCancelerBlacklisted() || isAcousticEchoCancelerExcludedByUUID()) ? false : true;
        Logging.m206d("WebRtcAudioEffects", "canUseAcousticEchoCanceler: " + z);
        return z;
    }

    public static boolean canUseNoiseSuppressor() {
        boolean z = (!isNoiseSuppressorSupported() || WebRtcAudioUtils.useWebRtcBasedNoiseSuppressor() || isNoiseSuppressorBlacklisted() || isNoiseSuppressorExcludedByUUID()) ? false : true;
        Logging.m206d("WebRtcAudioEffects", "canUseNoiseSuppressor: " + z);
        return z;
    }

    public static WebRtcAudioEffects create() {
        return new WebRtcAudioEffects();
    }

    private WebRtcAudioEffects() {
        Logging.m206d("WebRtcAudioEffects", "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    public boolean setAEC(boolean z) {
        Logging.m206d("WebRtcAudioEffects", "setAEC(" + z + ")");
        if (!canUseAcousticEchoCanceler()) {
            Logging.m202w("WebRtcAudioEffects", "Platform AEC is not supported");
            this.shouldEnableAec = false;
            return false;
        } else if (this.aec != null && z != this.shouldEnableAec) {
            Logging.m205e("WebRtcAudioEffects", "Platform AEC state can't be modified while recording");
            return false;
        } else {
            this.shouldEnableAec = z;
            return true;
        }
    }

    public boolean setNS(boolean z) {
        Logging.m206d("WebRtcAudioEffects", "setNS(" + z + ")");
        if (!canUseNoiseSuppressor()) {
            Logging.m202w("WebRtcAudioEffects", "Platform NS is not supported");
            this.shouldEnableNs = false;
            return false;
        } else if (this.f27441ns != null && z != this.shouldEnableNs) {
            Logging.m205e("WebRtcAudioEffects", "Platform NS state can't be modified while recording");
            return false;
        } else {
            this.shouldEnableNs = z;
            return true;
        }
    }

    public void enable(int i) {
        Logging.m206d("WebRtcAudioEffects", "enable(audioSession=" + i + ")");
        boolean z = true;
        assertTrue(this.aec == null);
        assertTrue(this.f27441ns == null);
        if (isAcousticEchoCancelerSupported()) {
            this.aec = AcousticEchoCanceler.create(i);
            AcousticEchoCanceler acousticEchoCanceler = this.aec;
            if (acousticEchoCanceler != null) {
                boolean enabled = acousticEchoCanceler.getEnabled();
                boolean z2 = this.shouldEnableAec && canUseAcousticEchoCanceler();
                if (this.aec.setEnabled(z2) != 0) {
                    Logging.m205e("WebRtcAudioEffects", "Failed to set the AcousticEchoCanceler state");
                }
                StringBuilder sb = new StringBuilder();
                sb.append("AcousticEchoCanceler: was ");
                sb.append(enabled ? "enabled" : "disabled");
                sb.append(", enable: ");
                sb.append(z2);
                sb.append(", is now: ");
                sb.append(this.aec.getEnabled() ? "enabled" : "disabled");
                Logging.m206d("WebRtcAudioEffects", sb.toString());
            } else {
                Logging.m205e("WebRtcAudioEffects", "Failed to create the AcousticEchoCanceler instance");
            }
        }
        if (isNoiseSuppressorSupported()) {
            this.f27441ns = NoiseSuppressor.create(i);
            NoiseSuppressor noiseSuppressor = this.f27441ns;
            if (noiseSuppressor != null) {
                boolean enabled2 = noiseSuppressor.getEnabled();
                if (!this.shouldEnableNs || !canUseNoiseSuppressor()) {
                    z = false;
                }
                if (this.f27441ns.setEnabled(z) != 0) {
                    Logging.m205e("WebRtcAudioEffects", "Failed to set the NoiseSuppressor state");
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("NoiseSuppressor: was ");
                sb2.append(enabled2 ? "enabled" : "disabled");
                sb2.append(", enable: ");
                sb2.append(z);
                sb2.append(", is now: ");
                sb2.append(this.f27441ns.getEnabled() ? "enabled" : "disabled");
                Logging.m206d("WebRtcAudioEffects", sb2.toString());
                return;
            }
            Logging.m205e("WebRtcAudioEffects", "Failed to create the NoiseSuppressor instance");
        }
    }

    public void release() {
        Logging.m206d("WebRtcAudioEffects", "release");
        AcousticEchoCanceler acousticEchoCanceler = this.aec;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
            this.aec = null;
        }
        NoiseSuppressor noiseSuppressor = this.f27441ns;
        if (noiseSuppressor != null) {
            noiseSuppressor.release();
            this.f27441ns = null;
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

    private static boolean isEffectTypeAvailable(UUID uuid) {
        AudioEffect.Descriptor[] availableEffects = getAvailableEffects();
        if (availableEffects == null) {
            return false;
        }
        for (AudioEffect.Descriptor descriptor : availableEffects) {
            if (descriptor.type.equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
