package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.CastUtils;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SampleToChunkBox extends AbstractFullBox {
    public static final String TYPE = "stsc";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    List<Entry> entries;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class Entry {
        long firstChunk;
        long sampleDescriptionIndex;
        long samplesPerChunk;

        public Entry(long j, long j2, long j3) {
            this.firstChunk = j;
            this.samplesPerChunk = j2;
            this.sampleDescriptionIndex = j3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.firstChunk == entry.firstChunk && this.sampleDescriptionIndex == entry.sampleDescriptionIndex && this.samplesPerChunk == entry.samplesPerChunk;
        }

        public long getFirstChunk() {
            return this.firstChunk;
        }

        public long getSampleDescriptionIndex() {
            return this.sampleDescriptionIndex;
        }

        public long getSamplesPerChunk() {
            return this.samplesPerChunk;
        }

        public int hashCode() {
            long j = this.firstChunk;
            long j2 = this.samplesPerChunk;
            long j3 = this.sampleDescriptionIndex;
            return (((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) ((j3 >>> 32) ^ j3));
        }

        public void setFirstChunk(long j) {
            this.firstChunk = j;
        }

        public void setSampleDescriptionIndex(long j) {
            this.sampleDescriptionIndex = j;
        }

        public void setSamplesPerChunk(long j) {
            this.samplesPerChunk = j;
        }

        public String toString() {
            return "Entry{firstChunk=" + this.firstChunk + ", samplesPerChunk=" + this.samplesPerChunk + ", sampleDescriptionIndex=" + this.sampleDescriptionIndex + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public SampleToChunkBox() {
        super(TYPE);
        this.entries = Collections.emptyList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("SampleToChunkBox.java", SampleToChunkBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.coremedia.iso.boxes.SampleToChunkBox", "", "", "", "java.util.List"), 47);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.coremedia.iso.boxes.SampleToChunkBox", "java.util.List", "entries", "", "void"), 51);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.SampleToChunkBox", "", "", "", "java.lang.String"), 84);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "blowup", "szcom.coremedia.iso.boxes.SampleToChunkBox", "int", "chunkCount", "", "[J"), 95);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        int l2i = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        this.entries = new ArrayList(l2i);
        for (int i = 0; i < l2i; i++) {
            this.entries.add(new Entry(IsoTypeReader.readUInt32(byteBuffer), IsoTypeReader.readUInt32(byteBuffer), IsoTypeReader.readUInt32(byteBuffer)));
        }
    }

    public long[] blowup(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        long[] jArr = new long[i];
        LinkedList linkedList = new LinkedList(this.entries);
        Collections.reverse(linkedList);
        Iterator it = linkedList.iterator();
        Entry entry = (Entry) it.next();
        for (int length = jArr.length; length > 1; length--) {
            jArr[length - 1] = entry.getSamplesPerChunk();
            if (length == entry.getFirstChunk()) {
                entry = (Entry) it.next();
            }
        }
        jArr[0] = entry.getSamplesPerChunk();
        return jArr;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.entries.size());
        for (Entry entry : this.entries) {
            IsoTypeWriter.writeUInt32(byteBuffer, entry.getFirstChunk());
            IsoTypeWriter.writeUInt32(byteBuffer, entry.getSamplesPerChunk());
            IsoTypeWriter.writeUInt32(byteBuffer, entry.getSampleDescriptionIndex());
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (this.entries.size() * 12) + 8;
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
        return "SampleToChunkBox[entryCount=" + this.entries.size() + "]";
    }
}
