package szcom.googlecode.mp4parser.boxes.mp4.samplegrouping;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class RateShareEntry extends GroupEntry {
    public static final String TYPE = "rash";
    private short discardPriority;
    private List<Entry> entries = new LinkedList();
    private int maximumBitrate;
    private int minimumBitrate;
    private short operationPointCut;
    private short targetRateShare;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class Entry {
        int availableBitrate;
        short targetRateShare;

        public Entry(int i, short s) {
            this.availableBitrate = i;
            this.targetRateShare = s;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.availableBitrate == entry.availableBitrate && this.targetRateShare == entry.targetRateShare;
        }

        public int getAvailableBitrate() {
            return this.availableBitrate;
        }

        public short getTargetRateShare() {
            return this.targetRateShare;
        }

        public int hashCode() {
            return (this.availableBitrate * 31) + this.targetRateShare;
        }

        public void setAvailableBitrate(int i) {
            this.availableBitrate = i;
        }

        public void setTargetRateShare(short s) {
            this.targetRateShare = s;
        }

        public String toString() {
            return "{availableBitrate=" + this.availableBitrate + ", targetRateShare=" + ((int) this.targetRateShare) + '}';
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RateShareEntry rateShareEntry = (RateShareEntry) obj;
        if (this.discardPriority == rateShareEntry.discardPriority && this.maximumBitrate == rateShareEntry.maximumBitrate && this.minimumBitrate == rateShareEntry.minimumBitrate && this.operationPointCut == rateShareEntry.operationPointCut && this.targetRateShare == rateShareEntry.targetRateShare) {
            List<Entry> list = this.entries;
            return list == null ? rateShareEntry.entries == null : list.equals(rateShareEntry.entries);
        }
        return false;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public ByteBuffer get() {
        short s = this.operationPointCut;
        ByteBuffer allocate = ByteBuffer.allocate(s == 1 ? 13 : (s * 6) + 11);
        allocate.putShort(this.operationPointCut);
        if (this.operationPointCut == 1) {
            allocate.putShort(this.targetRateShare);
        } else {
            for (Entry entry : this.entries) {
                allocate.putInt(entry.getAvailableBitrate());
                allocate.putShort(entry.getTargetRateShare());
            }
        }
        allocate.putInt(this.maximumBitrate);
        allocate.putInt(this.minimumBitrate);
        IsoTypeWriter.writeUInt8(allocate, this.discardPriority);
        allocate.rewind();
        return allocate;
    }

    public short getDiscardPriority() {
        return this.discardPriority;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public int getMaximumBitrate() {
        return this.maximumBitrate;
    }

    public int getMinimumBitrate() {
        return this.minimumBitrate;
    }

    public short getOperationPointCut() {
        return this.operationPointCut;
    }

    public short getTargetRateShare() {
        return this.targetRateShare;
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public String getType() {
        return TYPE;
    }

    public int hashCode() {
        int i = ((this.operationPointCut * 31) + this.targetRateShare) * 31;
        List<Entry> list = this.entries;
        return ((((((i + (list != null ? list.hashCode() : 0)) * 31) + this.maximumBitrate) * 31) + this.minimumBitrate) * 31) + this.discardPriority;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    @Override // szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry
    public void parse(ByteBuffer byteBuffer) {
        this.operationPointCut = byteBuffer.getShort();
        short s = this.operationPointCut;
        if (s == 1) {
            this.targetRateShare = byteBuffer.getShort();
        } else {
            while (true) {
                ?? r1 = s - 1;
                if (s <= 0) {
                    break;
                }
                this.entries.add(new Entry(CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer)), byteBuffer.getShort()));
                s = r1;
            }
        }
        this.maximumBitrate = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        this.minimumBitrate = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        this.discardPriority = (short) IsoTypeReader.readUInt8(byteBuffer);
    }

    public void setDiscardPriority(short s) {
        this.discardPriority = s;
    }

    public void setEntries(List<Entry> list) {
        this.entries = list;
    }

    public void setMaximumBitrate(int i) {
        this.maximumBitrate = i;
    }

    public void setMinimumBitrate(int i) {
        this.minimumBitrate = i;
    }

    public void setOperationPointCut(short s) {
        this.operationPointCut = s;
    }

    public void setTargetRateShare(short s) {
        this.targetRateShare = s;
    }
}
