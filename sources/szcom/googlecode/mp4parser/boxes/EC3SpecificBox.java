package szcom.googlecode.mp4parser.boxes;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitWriterBuffer;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class EC3SpecificBox extends AbstractBox {
    public static final String TYPE = "dec3";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    int dataRate;
    List<Entry> entries;
    int numIndSub;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class Entry {
        public int acmod;
        public int bsid;
        public int bsmod;
        public int chan_loc;
        public int fscod;
        public int lfeon;
        public int num_dep_sub;
        public int reserved;
        public int reserved2;

        public String toString() {
            return "Entry{fscod=" + this.fscod + ", bsid=" + this.bsid + ", bsmod=" + this.bsmod + ", acmod=" + this.acmod + ", lfeon=" + this.lfeon + ", reserved=" + this.reserved + ", num_dep_sub=" + this.num_dep_sub + ", chan_loc=" + this.chan_loc + ", reserved2=" + this.reserved2 + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public EC3SpecificBox() {
        super(TYPE);
        this.entries = new LinkedList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("EC3SpecificBox.java", EC3SpecificBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getContentSize", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "", "", "", "long"), 25);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getContent", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), 65);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "", "", "", "java.util.List"), 86);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "java.util.List", "entries", "", "void"), 90);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "addEntry", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox$Entry", "entry", "", "void"), 94);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDataRate", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "", "", "", "int"), 98);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDataRate", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "int", "dataRate", "", "void"), 102);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getNumIndSub", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "", "", "", "int"), 106);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setNumIndSub", "szcom.googlecode.mp4parser.boxes.EC3SpecificBox", "int", "numIndSub", "", "void"), 110);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        this.dataRate = bitReaderBuffer.readBits(13);
        this.numIndSub = bitReaderBuffer.readBits(3) + 1;
        for (int i = 0; i < this.numIndSub; i++) {
            Entry entry = new Entry();
            entry.fscod = bitReaderBuffer.readBits(2);
            entry.bsid = bitReaderBuffer.readBits(5);
            entry.bsmod = bitReaderBuffer.readBits(5);
            entry.acmod = bitReaderBuffer.readBits(3);
            entry.lfeon = bitReaderBuffer.readBits(1);
            entry.reserved = bitReaderBuffer.readBits(3);
            entry.num_dep_sub = bitReaderBuffer.readBits(4);
            if (entry.num_dep_sub > 0) {
                entry.chan_loc = bitReaderBuffer.readBits(9);
            } else {
                entry.reserved2 = bitReaderBuffer.readBits(1);
            }
            this.entries.add(entry);
        }
    }

    public void addEntry(Entry entry) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, entry));
        this.entries.add(entry);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, byteBuffer));
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
        bitWriterBuffer.writeBits(this.dataRate, 13);
        bitWriterBuffer.writeBits(this.entries.size() - 1, 3);
        for (Entry entry : this.entries) {
            bitWriterBuffer.writeBits(entry.fscod, 2);
            bitWriterBuffer.writeBits(entry.bsid, 5);
            bitWriterBuffer.writeBits(entry.bsmod, 5);
            bitWriterBuffer.writeBits(entry.acmod, 3);
            bitWriterBuffer.writeBits(entry.lfeon, 1);
            bitWriterBuffer.writeBits(entry.reserved, 3);
            bitWriterBuffer.writeBits(entry.num_dep_sub, 4);
            if (entry.num_dep_sub > 0) {
                bitWriterBuffer.writeBits(entry.chan_loc, 9);
            } else {
                bitWriterBuffer.writeBits(entry.reserved2, 1);
            }
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        long j = 2;
        for (Entry entry : this.entries) {
            j += entry.num_dep_sub > 0 ? 4L : 3L;
        }
        return j;
    }

    public int getDataRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return this.dataRate;
    }

    public List<Entry> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.entries;
    }

    public int getNumIndSub() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        return this.numIndSub;
    }

    public void setDataRate(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, Conversions.intObject(i)));
        this.dataRate = i;
    }

    public void setEntries(List<Entry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, list));
        this.entries = list;
    }

    public void setNumIndSub(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this, Conversions.intObject(i)));
        this.numIndSub = i;
    }
}
