package szcom.coremedia.iso.boxes.fragment;

import java.nio.ByteBuffer;
import java.util.ArrayList;
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
public class TrackRunBox extends AbstractFullBox {
    public static final String TYPE = "trun";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final JoinPoint.StaticPart ajc$tjp_14 = null;
    private static final JoinPoint.StaticPart ajc$tjp_15 = null;
    private static final JoinPoint.StaticPart ajc$tjp_16 = null;
    private static final JoinPoint.StaticPart ajc$tjp_17 = null;
    private static final JoinPoint.StaticPart ajc$tjp_18 = null;
    private static final JoinPoint.StaticPart ajc$tjp_19 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private int dataOffset;
    private List<Entry> entries;
    private SampleFlags firstSampleFlags;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class Entry {
        private long sampleCompositionTimeOffset;
        private long sampleDuration;
        private SampleFlags sampleFlags;
        private long sampleSize;

        public Entry() {
        }

        public Entry(long j, long j2, SampleFlags sampleFlags, int i) {
            this.sampleDuration = j;
            this.sampleSize = j2;
            this.sampleFlags = sampleFlags;
            this.sampleCompositionTimeOffset = i;
        }

        public long getSampleCompositionTimeOffset() {
            return this.sampleCompositionTimeOffset;
        }

        public long getSampleDuration() {
            return this.sampleDuration;
        }

        public SampleFlags getSampleFlags() {
            return this.sampleFlags;
        }

        public long getSampleSize() {
            return this.sampleSize;
        }

        public void setSampleCompositionTimeOffset(int i) {
            this.sampleCompositionTimeOffset = i;
        }

        public void setSampleDuration(long j) {
            this.sampleDuration = j;
        }

        public void setSampleFlags(SampleFlags sampleFlags) {
            this.sampleFlags = sampleFlags;
        }

        public void setSampleSize(long j) {
            this.sampleSize = j;
        }

        public String toString() {
            return "Entry{duration=" + this.sampleDuration + ", size=" + this.sampleSize + ", dlags=" + this.sampleFlags + ", compTimeOffset=" + this.sampleCompositionTimeOffset + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public TrackRunBox() {
        super(TYPE);
        this.entries = new ArrayList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TrackRunBox.java", TrackRunBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "java.util.List"), 57);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDataOffset", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "int", "dataOffset", "", "void"), 120);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDataOffsetPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 267);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleSizePresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 275);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleDurationPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 283);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleFlagsPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 292);
        ajc$tjp_14 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleCompositionTimeOffsetPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 300);
        ajc$tjp_15 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDataOffset", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "int"), 309);
        ajc$tjp_16 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFirstSampleFlags", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "szcom.coremedia.iso.boxes.fragment.SampleFlags"), 313);
        ajc$tjp_17 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setFirstSampleFlags", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "szcom.coremedia.iso.boxes.fragment.SampleFlags", "firstSampleFlags", "", "void"), 317);
        ajc$tjp_18 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "java.lang.String"), 327);
        ajc$tjp_19 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "java.util.List", "entries", "", "void"), 342);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSampleCompositionTimeOffsets", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "[J"), 129);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSampleCount", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "long"), 238);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isDataOffsetPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 242);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isFirstSampleFlagsPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 246);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isSampleSizePresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 251);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isSampleDurationPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 255);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isSampleFlagsPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 259);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isSampleCompositionTimeOffsetPresent", "szcom.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 263);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        long readUInt32 = IsoTypeReader.readUInt32(byteBuffer);
        this.dataOffset = (getFlags() & 1) == 1 ? CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer)) : -1;
        if ((getFlags() & 4) == 4) {
            this.firstSampleFlags = new SampleFlags(byteBuffer);
        }
        for (int i = 0; i < readUInt32; i++) {
            Entry entry = new Entry();
            if ((getFlags() & 256) == 256) {
                entry.sampleDuration = IsoTypeReader.readUInt32(byteBuffer);
            }
            if ((getFlags() & 512) == 512) {
                entry.sampleSize = IsoTypeReader.readUInt32(byteBuffer);
            }
            if ((getFlags() & 1024) == 1024) {
                entry.sampleFlags = new SampleFlags(byteBuffer);
            }
            if ((getFlags() & 2048) == 2048) {
                entry.sampleCompositionTimeOffset = byteBuffer.getInt();
            }
            this.entries.add(entry);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.entries.size());
        int flags = getFlags();
        if ((flags & 1) == 1) {
            IsoTypeWriter.writeUInt32(byteBuffer, this.dataOffset);
        }
        if ((flags & 4) == 4) {
            this.firstSampleFlags.getContent(byteBuffer);
        }
        for (Entry entry : this.entries) {
            if ((flags & 256) == 256) {
                IsoTypeWriter.writeUInt32(byteBuffer, entry.sampleDuration);
            }
            if ((flags & 512) == 512) {
                IsoTypeWriter.writeUInt32(byteBuffer, entry.sampleSize);
            }
            if ((flags & 1024) == 1024) {
                entry.sampleFlags.getContent(byteBuffer);
            }
            if ((flags & 2048) == 2048) {
                if (getVersion() == 0) {
                    IsoTypeWriter.writeUInt32(byteBuffer, entry.sampleCompositionTimeOffset);
                } else {
                    byteBuffer.putInt((int) entry.sampleCompositionTimeOffset);
                }
            }
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        int flags = getFlags();
        long j = (flags & 1) == 1 ? 12L : 8L;
        if ((flags & 4) == 4) {
            j += 4;
        }
        long j2 = (flags & 256) == 256 ? 4L : 0L;
        if ((flags & 512) == 512) {
            j2 += 4;
        }
        if ((flags & 1024) == 1024) {
            j2 += 4;
        }
        if ((flags & 2048) == 2048) {
            j2 += 4;
        }
        return j + (j2 * this.entries.size());
    }

    public int getDataOffset() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this));
        return this.dataOffset;
    }

    public List<Entry> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.entries;
    }

    public SampleFlags getFirstSampleFlags() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_16, this, this));
        return this.firstSampleFlags;
    }

    public long[] getSampleCompositionTimeOffsets() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        if (isSampleCompositionTimeOffsetPresent()) {
            long[] jArr = new long[this.entries.size()];
            for (int i = 0; i < jArr.length; i++) {
                jArr[i] = this.entries.get(i).getSampleCompositionTimeOffset();
            }
            return jArr;
        }
        return null;
    }

    public long getSampleCount() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.entries.size();
    }

    public boolean isDataOffsetPresent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return (getFlags() & 1) == 1;
    }

    public boolean isFirstSampleFlagsPresent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return (getFlags() & 4) == 4;
    }

    public boolean isSampleCompositionTimeOffsetPresent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this));
        return (getFlags() & 2048) == 2048;
    }

    public boolean isSampleDurationPresent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        return (getFlags() & 256) == 256;
    }

    public boolean isSampleFlagsPresent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return (getFlags() & 1024) == 1024;
    }

    public boolean isSampleSizePresent() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return (getFlags() & 512) == 512;
    }

    public void setDataOffset(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        setFlags(i == -1 ? getFlags() & 16777214 : getFlags() | 1);
        this.dataOffset = i;
    }

    public void setDataOffsetPresent(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 1 : getFlags() & 16777214);
    }

    public void setEntries(List<Entry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_19, this, this, list));
        this.entries = list;
    }

    public void setFirstSampleFlags(SampleFlags sampleFlags) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_17, this, this, sampleFlags));
        setFlags(sampleFlags == null ? getFlags() & 16777211 : getFlags() | 4);
        this.firstSampleFlags = sampleFlags;
    }

    public void setSampleCompositionTimeOffsetPresent(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 2048 : getFlags() & 16775167);
    }

    public void setSampleDurationPresent(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 256 : getFlags() & 16776959);
    }

    public void setSampleFlagsPresent(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 1024 : getFlags() & 16776191);
    }

    public void setSampleSizePresent(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 512 : getFlags() & 16776703);
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_18, this, this));
        return "TrackRunBox{sampleCount=" + this.entries.size() + ", dataOffset=" + this.dataOffset + ", dataOffsetPresent=" + isDataOffsetPresent() + ", sampleSizePresent=" + isSampleSizePresent() + ", sampleDurationPresent=" + isSampleDurationPresent() + ", sampleFlagsPresentPresent=" + isSampleFlagsPresent() + ", sampleCompositionTimeOffsetPresent=" + isSampleCompositionTimeOffsetPresent() + ", firstSampleFlags=" + this.firstSampleFlags + '}';
    }
}
