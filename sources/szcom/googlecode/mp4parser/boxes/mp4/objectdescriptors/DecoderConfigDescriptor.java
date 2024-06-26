package szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import szcom.coremedia.iso.Hex;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;

@Descriptor(tags = {4})
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DecoderConfigDescriptor extends BaseDescriptor {
    private static Logger log = Logger.getLogger(DecoderConfigDescriptor.class.getName());
    AudioSpecificConfig audioSpecificInfo;
    long avgBitRate;
    int bufferSizeDB;
    byte[] configDescriptorDeadBytes;
    DecoderSpecificInfo decoderSpecificInfo;
    long maxBitRate;
    int objectTypeIndication;
    List<ProfileLevelIndicationDescriptor> profileLevelIndicationDescriptors = new ArrayList();
    int streamType;
    int upStream;

    public DecoderConfigDescriptor() {
        this.tag = 4;
    }

    public AudioSpecificConfig getAudioSpecificInfo() {
        return this.audioSpecificInfo;
    }

    public long getAvgBitRate() {
        return this.avgBitRate;
    }

    public int getBufferSizeDB() {
        return this.bufferSizeDB;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    int getContentSize() {
        AudioSpecificConfig audioSpecificConfig = this.audioSpecificInfo;
        int size = (audioSpecificConfig == null ? 0 : audioSpecificConfig.getSize()) + 13;
        DecoderSpecificInfo decoderSpecificInfo = this.decoderSpecificInfo;
        int size2 = size + (decoderSpecificInfo != null ? decoderSpecificInfo.getSize() : 0);
        for (ProfileLevelIndicationDescriptor profileLevelIndicationDescriptor : this.profileLevelIndicationDescriptors) {
            size2 += profileLevelIndicationDescriptor.getSize();
        }
        return size2;
    }

    public DecoderSpecificInfo getDecoderSpecificInfo() {
        return this.decoderSpecificInfo;
    }

    public long getMaxBitRate() {
        return this.maxBitRate;
    }

    public int getObjectTypeIndication() {
        return this.objectTypeIndication;
    }

    public List<ProfileLevelIndicationDescriptor> getProfileLevelIndicationDescriptors() {
        return this.profileLevelIndicationDescriptors;
    }

    public int getStreamType() {
        return this.streamType;
    }

    public int getUpStream() {
        return this.upStream;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) {
        int size;
        this.objectTypeIndication = IsoTypeReader.readUInt8(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.streamType = readUInt8 >>> 2;
        this.upStream = (readUInt8 >> 1) & 1;
        this.bufferSizeDB = IsoTypeReader.readUInt24(byteBuffer);
        this.maxBitRate = IsoTypeReader.readUInt32(byteBuffer);
        this.avgBitRate = IsoTypeReader.readUInt32(byteBuffer);
        while (byteBuffer.remaining() > 2) {
            int position = byteBuffer.position();
            BaseDescriptor createFrom = ObjectDescriptorFactory.createFrom(this.objectTypeIndication, byteBuffer);
            int position2 = byteBuffer.position() - position;
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append(createFrom);
            sb.append(" - DecoderConfigDescr1 read: ");
            sb.append(position2);
            sb.append(", size: ");
            sb.append(createFrom != null ? Integer.valueOf(createFrom.getSize()) : null);
            logger.finer(sb.toString());
            if (createFrom != null && position2 < (size = createFrom.getSize())) {
                this.configDescriptorDeadBytes = new byte[size - position2];
                byteBuffer.get(this.configDescriptorDeadBytes);
            }
            if (createFrom instanceof DecoderSpecificInfo) {
                this.decoderSpecificInfo = (DecoderSpecificInfo) createFrom;
            } else if (createFrom instanceof AudioSpecificConfig) {
                this.audioSpecificInfo = (AudioSpecificConfig) createFrom;
            } else if (createFrom instanceof ProfileLevelIndicationDescriptor) {
                this.profileLevelIndicationDescriptors.add((ProfileLevelIndicationDescriptor) createFrom);
            }
        }
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        ByteBuffer allocate = ByteBuffer.allocate(getSize());
        IsoTypeWriter.writeUInt8(allocate, this.tag);
        writeSize(allocate, getContentSize());
        IsoTypeWriter.writeUInt8(allocate, this.objectTypeIndication);
        IsoTypeWriter.writeUInt8(allocate, (this.streamType << 2) | (this.upStream << 1) | 1);
        IsoTypeWriter.writeUInt24(allocate, this.bufferSizeDB);
        IsoTypeWriter.writeUInt32(allocate, this.maxBitRate);
        IsoTypeWriter.writeUInt32(allocate, this.avgBitRate);
        DecoderSpecificInfo decoderSpecificInfo = this.decoderSpecificInfo;
        if (decoderSpecificInfo != null) {
            allocate.put(decoderSpecificInfo.serialize());
        }
        AudioSpecificConfig audioSpecificConfig = this.audioSpecificInfo;
        if (audioSpecificConfig != null) {
            allocate.put(audioSpecificConfig.serialize());
        }
        for (ProfileLevelIndicationDescriptor profileLevelIndicationDescriptor : this.profileLevelIndicationDescriptors) {
            allocate.put(profileLevelIndicationDescriptor.serialize());
        }
        return (ByteBuffer) allocate.rewind();
    }

    public void setAudioSpecificInfo(AudioSpecificConfig audioSpecificConfig) {
        this.audioSpecificInfo = audioSpecificConfig;
    }

    public void setAvgBitRate(long j) {
        this.avgBitRate = j;
    }

    public void setBufferSizeDB(int i) {
        this.bufferSizeDB = i;
    }

    public void setDecoderSpecificInfo(DecoderSpecificInfo decoderSpecificInfo) {
        this.decoderSpecificInfo = decoderSpecificInfo;
    }

    public void setMaxBitRate(long j) {
        this.maxBitRate = j;
    }

    public void setObjectTypeIndication(int i) {
        this.objectTypeIndication = i;
    }

    public void setStreamType(int i) {
        this.streamType = i;
    }

    public void setUpStream(int i) {
        this.upStream = i;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DecoderConfigDescriptor");
        sb.append("{objectTypeIndication=");
        sb.append(this.objectTypeIndication);
        sb.append(", streamType=");
        sb.append(this.streamType);
        sb.append(", upStream=");
        sb.append(this.upStream);
        sb.append(", bufferSizeDB=");
        sb.append(this.bufferSizeDB);
        sb.append(", maxBitRate=");
        sb.append(this.maxBitRate);
        sb.append(", avgBitRate=");
        sb.append(this.avgBitRate);
        sb.append(", decoderSpecificInfo=");
        sb.append(this.decoderSpecificInfo);
        sb.append(", audioSpecificInfo=");
        sb.append(this.audioSpecificInfo);
        sb.append(", configDescriptorDeadBytes=");
        byte[] bArr = this.configDescriptorDeadBytes;
        if (bArr == null) {
            bArr = new byte[0];
        }
        sb.append(Hex.encodeHex(bArr));
        sb.append(", profileLevelIndicationDescriptors=");
        List<ProfileLevelIndicationDescriptor> list = this.profileLevelIndicationDescriptors;
        sb.append(list == null ? "null" : Arrays.asList(list).toString());
        sb.append('}');
        return sb.toString();
    }
}
