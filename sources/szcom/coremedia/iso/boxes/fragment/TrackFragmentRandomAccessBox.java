package szcom.coremedia.iso.boxes.fragment;

import com.cjt2325.cameralibrary.CameraInterface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeReaderVariable;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.IsoTypeWriterVariable;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrackFragmentRandomAccessBox extends AbstractFullBox {
    public static final String TYPE = "tfra";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private List<Entry> entries;
    private int lengthSizeOfSampleNum;
    private int lengthSizeOfTrafNum;
    private int lengthSizeOfTrunNum;
    private int reserved;
    private long trackId;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class Entry {
        private long moofOffset;
        private long sampleNumber;
        private long time;
        private long trafNumber;
        private long trunNumber;

        public Entry() {
        }

        public Entry(long j, long j2, long j3, long j4, long j5) {
            this.moofOffset = j2;
            this.sampleNumber = j5;
            this.time = j;
            this.trafNumber = j3;
            this.trunNumber = j4;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.moofOffset == entry.moofOffset && this.sampleNumber == entry.sampleNumber && this.time == entry.time && this.trafNumber == entry.trafNumber && this.trunNumber == entry.trunNumber;
        }

        public long getMoofOffset() {
            return this.moofOffset;
        }

        public long getSampleNumber() {
            return this.sampleNumber;
        }

        public long getTime() {
            return this.time;
        }

        public long getTrafNumber() {
            return this.trafNumber;
        }

        public long getTrunNumber() {
            return this.trunNumber;
        }

        public int hashCode() {
            long j = this.time;
            long j2 = this.moofOffset;
            long j3 = this.trafNumber;
            long j4 = this.trunNumber;
            long j5 = this.sampleNumber;
            return (((((((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) ((j5 >>> 32) ^ j5));
        }

        public void setMoofOffset(long j) {
            this.moofOffset = j;
        }

        public void setSampleNumber(long j) {
            this.sampleNumber = j;
        }

        public void setTime(long j) {
            this.time = j;
        }

        public void setTrafNumber(long j) {
            this.trafNumber = j;
        }

        public void setTrunNumber(long j) {
            this.trunNumber = j;
        }

        public String toString() {
            return "Entry{time=" + this.time + ", moofOffset=" + this.moofOffset + ", trafNumber=" + this.trafNumber + ", trunNumber=" + this.trunNumber + ", sampleNumber=" + this.sampleNumber + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public TrackFragmentRandomAccessBox() {
        super(TYPE);
        this.lengthSizeOfTrafNum = 2;
        this.lengthSizeOfTrunNum = 2;
        this.lengthSizeOfSampleNum = 2;
        this.entries = Collections.emptyList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TrackFragmentRandomAccessBox.java", TrackFragmentRandomAccessBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTrackId", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "long", "trackId", "", "void"), CameraInterface.TYPE_CAPTURE);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLengthSizeOfTrafNum", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "int", "lengthSizeOfTrafNum", "", "void"), 149);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "java.util.List"), 185);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "java.util.List", "entries", "", "void"), 189);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "java.lang.String"), 290);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLengthSizeOfTrunNum", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "int", "lengthSizeOfTrunNum", "", "void"), 153);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLengthSizeOfSampleNum", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "int", "lengthSizeOfSampleNum", "", "void"), 157);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTrackId", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "long"), 161);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "int"), 165);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLengthSizeOfTrafNum", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "int"), 169);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLengthSizeOfTrunNum", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "int"), 173);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLengthSizeOfSampleNum", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "int"), 177);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getNumberOfEntries", "szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox", "", "", "", "long"), 181);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        long readUInt32;
        parseVersionAndFlags(byteBuffer);
        this.trackId = IsoTypeReader.readUInt32(byteBuffer);
        long readUInt322 = IsoTypeReader.readUInt32(byteBuffer);
        this.reserved = (int) (readUInt322 >> 6);
        this.lengthSizeOfTrafNum = (((int) (63 & readUInt322)) >> 4) + 1;
        this.lengthSizeOfTrunNum = (((int) (12 & readUInt322)) >> 2) + 1;
        this.lengthSizeOfSampleNum = ((int) (readUInt322 & 3)) + 1;
        long readUInt323 = IsoTypeReader.readUInt32(byteBuffer);
        this.entries = new ArrayList();
        for (int i = 0; i < readUInt323; i++) {
            Entry entry = new Entry();
            if (getVersion() == 1) {
                entry.time = IsoTypeReader.readUInt64(byteBuffer);
                readUInt32 = IsoTypeReader.readUInt64(byteBuffer);
            } else {
                entry.time = IsoTypeReader.readUInt32(byteBuffer);
                readUInt32 = IsoTypeReader.readUInt32(byteBuffer);
            }
            entry.moofOffset = readUInt32;
            entry.trafNumber = IsoTypeReaderVariable.read(byteBuffer, this.lengthSizeOfTrafNum);
            entry.trunNumber = IsoTypeReaderVariable.read(byteBuffer, this.lengthSizeOfTrunNum);
            entry.sampleNumber = IsoTypeReaderVariable.read(byteBuffer, this.lengthSizeOfSampleNum);
            this.entries.add(entry);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.trackId);
        IsoTypeWriter.writeUInt32(byteBuffer, (this.reserved << 6) | (((this.lengthSizeOfTrafNum - 1) & 3) << 4) | (((this.lengthSizeOfTrunNum - 1) & 3) << 2) | ((this.lengthSizeOfSampleNum - 1) & 3));
        IsoTypeWriter.writeUInt32(byteBuffer, this.entries.size());
        for (Entry entry : this.entries) {
            if (getVersion() == 1) {
                IsoTypeWriter.writeUInt64(byteBuffer, entry.time);
                IsoTypeWriter.writeUInt64(byteBuffer, entry.moofOffset);
            } else {
                IsoTypeWriter.writeUInt32(byteBuffer, entry.time);
                IsoTypeWriter.writeUInt32(byteBuffer, entry.moofOffset);
            }
            IsoTypeWriterVariable.write(entry.trafNumber, byteBuffer, this.lengthSizeOfTrafNum);
            IsoTypeWriterVariable.write(entry.trunNumber, byteBuffer, this.lengthSizeOfTrunNum);
            IsoTypeWriterVariable.write(entry.sampleNumber, byteBuffer, this.lengthSizeOfSampleNum);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 16 + (getVersion() == 1 ? this.entries.size() * 16 : this.entries.size() * 8) + (this.lengthSizeOfTrafNum * this.entries.size()) + (this.lengthSizeOfTrunNum * this.entries.size()) + (this.lengthSizeOfSampleNum * this.entries.size());
    }

    public List<Entry> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return Collections.unmodifiableList(this.entries);
    }

    public int getLengthSizeOfSampleNum() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.lengthSizeOfSampleNum;
    }

    public int getLengthSizeOfTrafNum() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.lengthSizeOfTrafNum;
    }

    public int getLengthSizeOfTrunNum() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        return this.lengthSizeOfTrunNum;
    }

    public long getNumberOfEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this));
        return this.entries.size();
    }

    public int getReserved() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return this.reserved;
    }

    public long getTrackId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.trackId;
    }

    public void setEntries(List<Entry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, list));
        this.entries = list;
    }

    public void setLengthSizeOfSampleNum(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.lengthSizeOfSampleNum = i;
    }

    public void setLengthSizeOfTrafNum(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.lengthSizeOfTrafNum = i;
    }

    public void setLengthSizeOfTrunNum(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, Conversions.intObject(i)));
        this.lengthSizeOfTrunNum = i;
    }

    public void setTrackId(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this, Conversions.longObject(j)));
        this.trackId = j;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return "TrackFragmentRandomAccessBox{trackId=" + this.trackId + ", entries=" + this.entries + '}';
    }
}
