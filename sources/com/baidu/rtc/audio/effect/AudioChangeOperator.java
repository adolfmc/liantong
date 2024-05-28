package com.baidu.rtc.audio.effect;

import com.baidu.feedcv.soundchanger.VoiceChangerOp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioChangeOperator implements IVoiceChanger {
    private VoiceChangerOp mVoiceChanger;

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void initVoiceChanger(int i, int i2, int i3) {
        this.mVoiceChanger = new VoiceChangerOp(0, i, i2, i3, 1.0f, 1.0f);
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void initVoiceChanger(int i, int i2, int i3, int i4, float f, float f2) {
        this.mVoiceChanger = new VoiceChangerOp(i, i2, i3, i4, f, f2);
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setVoiceChangeType(int i) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            if (i == 0) {
                setPitchSemi(1.0f);
                setTempo(1.0f);
                setRate(1.0f);
                return;
            }
            voiceChangerOp.setVoiceChangeType(i);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setBytesWidth(int i) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.setBytesPerSample(i);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setChannels(int i) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.setChannels(i);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setSamplingRate(int i) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.setSamplingRate(i);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setPitchSemi(float f) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.setPitchSemi(f);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setTempoChange(float f) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.setTempoChange(f);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setRateChange(float f) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.setRateChange(f);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setRate(float f) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.setRate(f);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void putBytes(byte[] bArr) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.putBytes(bArr);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public int getBytes(byte[] bArr) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            return voiceChangerOp.getBytes(bArr);
        }
        return 0;
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void finish() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.finish();
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void release() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.release();
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public int getChannels() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            return voiceChangerOp.getChannels();
        }
        return 0;
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public float getPitchSemi() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            return voiceChangerOp.getPitchSemi();
        }
        return 0.0f;
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public int getSamplingRate() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            return voiceChangerOp.getSamplingRate();
        }
        return 0;
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public long getOutputBufferSize() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            return voiceChangerOp.getOutputBufferSize();
        }
        return 0L;
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public float getTempo() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            return voiceChangerOp.getTempo();
        }
        return 0.0f;
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public void setTempo(float f) {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            voiceChangerOp.setTempo(f);
        }
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public int getTrackId() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            return voiceChangerOp.getTrackId();
        }
        return 0;
    }

    @Override // com.baidu.rtc.audio.effect.IVoiceChanger
    public float getRate() {
        VoiceChangerOp voiceChangerOp = this.mVoiceChanger;
        if (voiceChangerOp != null) {
            return voiceChangerOp.getRate();
        }
        return 0.0f;
    }
}
