package com.baidu.cloud.mediaprocess.device;

import com.baidu.cloud.framework.InPort;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.frame.AudioFrameBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ExternalAudioDevice {

    /* renamed from: a */
    public InPort<AudioFrameBuffer> f4566a = new ExtAudioDeviceInPortFactory(null).createInPort();

    /* renamed from: b */
    public OutPort<AudioFrameBuffer> f4567b = new OutPortFactory().createOutPort();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class ExtAudioDeviceInPortFactory implements InPort.Factory<AudioFrameBuffer> {
        public /* synthetic */ ExtAudioDeviceInPortFactory(C25321 c25321) {
        }

        @Override // com.baidu.cloud.framework.InPort.Factory
        public InPort<AudioFrameBuffer> createInPort() {
            return new InPort<AudioFrameBuffer>() { // from class: com.baidu.cloud.mediaprocess.device.ExternalAudioDevice.ExtAudioDeviceInPortFactory.1
                @Override // com.baidu.cloud.framework.IPort
                public void onConfigure(Object obj) {
                }

                @Override // com.baidu.cloud.framework.IPort
                public void onFrame(AudioFrameBuffer audioFrameBuffer) {
                    if (ExternalAudioDevice.this.getOutPort().isPortLinked()) {
                        ExternalAudioDevice.this.getOutPort().onFrame((OutPort<AudioFrameBuffer>) audioFrameBuffer);
                    }
                }
            };
        }
    }

    public InPort<AudioFrameBuffer> getInPort() {
        return this.f4566a;
    }

    public OutPort<AudioFrameBuffer> getOutPort() {
        return this.f4567b;
    }
}
