package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.CastUtils;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class EditListBox extends AbstractFullBox {
    public static final String TYPE = "elst";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private List<Entry> entries;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class Entry {
        EditListBox editListBox;
        private double mediaRate;
        private long mediaTime;
        private long segmentDuration;

        public Entry(EditListBox editListBox, long j, long j2, double d) {
            this.segmentDuration = j;
            this.mediaTime = j2;
            this.mediaRate = d;
            this.editListBox = editListBox;
        }

        public Entry(EditListBox editListBox, ByteBuffer byteBuffer) {
            long j;
            if (editListBox.getVersion() == 1) {
                this.segmentDuration = IsoTypeReader.readUInt64(byteBuffer);
                j = byteBuffer.getLong();
            } else {
                this.segmentDuration = IsoTypeReader.readUInt32(byteBuffer);
                j = byteBuffer.getInt();
            }
            this.mediaTime = j;
            this.mediaRate = IsoTypeReader.readFixedPoint1616(byteBuffer);
            this.editListBox = editListBox;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.mediaTime == entry.mediaTime && this.segmentDuration == entry.segmentDuration;
        }

        public void getContent(ByteBuffer byteBuffer) {
            if (this.editListBox.getVersion() == 1) {
                IsoTypeWriter.writeUInt64(byteBuffer, this.segmentDuration);
                byteBuffer.putLong(this.mediaTime);
            } else {
                IsoTypeWriter.writeUInt32(byteBuffer, CastUtils.l2i(this.segmentDuration));
                byteBuffer.putInt(CastUtils.l2i(this.mediaTime));
            }
            IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.mediaRate);
        }

        public double getMediaRate() {
            return this.mediaRate;
        }

        public long getMediaTime() {
            return this.mediaTime;
        }

        public long getSegmentDuration() {
            return this.segmentDuration;
        }

        public int hashCode() {
            long j = this.segmentDuration;
            long j2 = this.mediaTime;
            return (((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2));
        }

        public void setMediaRate(double d) {
            this.mediaRate = d;
        }

        public void setMediaTime(long j) {
            this.mediaTime = j;
        }

        public void setSegmentDuration(long j) {
            this.segmentDuration = j;
        }

        public String toString() {
            return "Entry{segmentDuration=" + this.segmentDuration + ", mediaTime=" + this.mediaTime + ", mediaRate=" + this.mediaRate + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public EditListBox() {
        super(TYPE);
        this.entries = new LinkedList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("EditListBox.java", EditListBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.coremedia.iso.boxes.EditListBox", "", "", "", "java.util.List"), 68);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.coremedia.iso.boxes.EditListBox", "java.util.List", "entries", "", "void"), 72);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.EditListBox", "", "", "", "java.lang.String"), 108);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        int l2i = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        this.entries = new LinkedList();
        for (int i = 0; i < l2i; i++) {
            this.entries.add(new Entry(this, byteBuffer));
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.entries.size());
        for (Entry entry : this.entries) {
            entry.getContent(byteBuffer);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (getVersion() == 1 ? this.entries.size() * 20 : this.entries.size() * 12) + 8;
    }

    public List<Entry> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List<Entry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, list));
        this.entries = list;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "EditListBox{entries=" + this.entries + '}';
    }
}
