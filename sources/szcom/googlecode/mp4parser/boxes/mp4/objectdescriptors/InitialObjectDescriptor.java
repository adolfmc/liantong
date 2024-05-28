package szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class InitialObjectDescriptor extends ObjectDescriptorBase {
    int audioProfileLevelIndication;
    int graphicsProfileLevelIndication;
    int includeInlineProfileLevelFlag;
    int oDProfileLevelIndication;
    private int objectDescriptorId;
    int sceneProfileLevelIndication;
    int urlFlag;
    int urlLength;
    String urlString;
    int visualProfileLevelIndication;
    List<ESDescriptor> esDescriptors = new ArrayList();
    List<ExtensionDescriptor> extensionDescriptors = new ArrayList();
    List<BaseDescriptor> unknownDescriptors = new ArrayList();

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) {
        int i;
        List list;
        List list2;
        int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
        this.objectDescriptorId = (65472 & readUInt16) >> 6;
        this.urlFlag = (readUInt16 & 63) >> 5;
        this.includeInlineProfileLevelFlag = (readUInt16 & 31) >> 4;
        int size = getSize() - 2;
        if (this.urlFlag == 1) {
            this.urlLength = IsoTypeReader.readUInt8(byteBuffer);
            this.urlString = IsoTypeReader.readString(byteBuffer, this.urlLength);
            i = size - (this.urlLength + 1);
        } else {
            this.oDProfileLevelIndication = IsoTypeReader.readUInt8(byteBuffer);
            this.sceneProfileLevelIndication = IsoTypeReader.readUInt8(byteBuffer);
            this.audioProfileLevelIndication = IsoTypeReader.readUInt8(byteBuffer);
            this.visualProfileLevelIndication = IsoTypeReader.readUInt8(byteBuffer);
            this.graphicsProfileLevelIndication = IsoTypeReader.readUInt8(byteBuffer);
            i = size - 5;
            if (i > 2) {
                BaseDescriptor createFrom = ObjectDescriptorFactory.createFrom(-1, byteBuffer);
                i -= createFrom.getSize();
                if (createFrom instanceof ESDescriptor) {
                    list = this.esDescriptors;
                    createFrom = (ESDescriptor) createFrom;
                } else {
                    list = this.unknownDescriptors;
                }
                list.add(createFrom);
            }
        }
        if (i > 2) {
            Object createFrom2 = ObjectDescriptorFactory.createFrom(-1, byteBuffer);
            if (createFrom2 instanceof ExtensionDescriptor) {
                list2 = this.extensionDescriptors;
                createFrom2 = (ExtensionDescriptor) createFrom2;
            } else {
                list2 = this.unknownDescriptors;
            }
            list2.add(createFrom2);
        }
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        return "InitialObjectDescriptor{objectDescriptorId=" + this.objectDescriptorId + ", urlFlag=" + this.urlFlag + ", includeInlineProfileLevelFlag=" + this.includeInlineProfileLevelFlag + ", urlLength=" + this.urlLength + ", urlString='" + this.urlString + "', oDProfileLevelIndication=" + this.oDProfileLevelIndication + ", sceneProfileLevelIndication=" + this.sceneProfileLevelIndication + ", audioProfileLevelIndication=" + this.audioProfileLevelIndication + ", visualProfileLevelIndication=" + this.visualProfileLevelIndication + ", graphicsProfileLevelIndication=" + this.graphicsProfileLevelIndication + ", esDescriptors=" + this.esDescriptors + ", extensionDescriptors=" + this.extensionDescriptors + ", unknownDescriptors=" + this.unknownDescriptors + '}';
    }
}
