package szcom.mp4parser.iso14496.part15;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SyncSampleEntry extends GroupEntry {
    public static final String TYPE = "sync";
    int nalUnitType;
    int reserved;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SyncSampleEntry syncSampleEntry = (SyncSampleEntry) obj;
        return this.nalUnitType == syncSampleEntry.nalUnitType && this.reserved == syncSampleEntry.reserved;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public ByteBuffer get() {
        ByteBuffer allocate = ByteBuffer.allocate(1);
        IsoTypeWriter.writeUInt8(allocate, this.nalUnitType + (this.reserved << 6));
        return (ByteBuffer) allocate.rewind();
    }

    public int getNalUnitType() {
        return this.nalUnitType;
    }

    public int getReserved() {
        return this.reserved;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public String getType() {
        return "sync";
    }

    public int hashCode() {
        return (this.reserved * 31) + this.nalUnitType;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public void parse(ByteBuffer byteBuffer) {
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved = (readUInt8 & 192) >> 6;
        this.nalUnitType = readUInt8 & 63;
    }

    public void setNalUnitType(int i) {
        this.nalUnitType = i;
    }

    public void setReserved(int i) {
        this.reserved = i;
    }

    public String toString() {
        return "SyncSampleEntry{reserved=" + this.reserved + ", nalUnitType=" + this.nalUnitType + '}';
    }
}
