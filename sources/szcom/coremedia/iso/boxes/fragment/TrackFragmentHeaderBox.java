package szcom.coremedia.iso.boxes.fragment;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrackFragmentHeaderBox extends AbstractFullBox {
    public static final String TYPE = "tfhd";
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
    private static final JoinPoint.StaticPart ajc$tjp_20 = null;
    private static final JoinPoint.StaticPart ajc$tjp_21 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private long baseDataOffset;
    private boolean defaultBaseIsMoof;
    private long defaultSampleDuration;
    private SampleFlags defaultSampleFlags;
    private long defaultSampleSize;
    private boolean durationIsEmpty;
    private long sampleDescriptionIndex;
    private long trackId;

    static {
        ajc$preClinit();
    }

    public TrackFragmentHeaderBox() {
        super(TYPE);
        this.baseDataOffset = -1L;
        this.defaultSampleDuration = -1L;
        this.defaultSampleSize = -1L;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TrackFragmentHeaderBox.java", TrackFragmentHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hasBaseDataOffset", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 126);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hasSampleDescriptionIndex", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 130);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleDescriptionIndex", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "sampleDescriptionIndex", "", "void"), 171);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleDuration", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 180);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultSampleDuration", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "defaultSampleDuration", "", "void"), 184);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleSize", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 191);
        ajc$tjp_14 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultSampleSize", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "defaultSampleSize", "", "void"), 195);
        ajc$tjp_15 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleFlags", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "szcom.coremedia.iso.boxes.fragment.SampleFlags"), 204);
        ajc$tjp_16 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultSampleFlags", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "szcom.coremedia.iso.boxes.fragment.SampleFlags", "defaultSampleFlags", "", "void"), 208);
        ajc$tjp_17 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isDurationIsEmpty", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 217);
        ajc$tjp_18 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDurationIsEmpty", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "boolean", "durationIsEmpty", "", "void"), 221);
        ajc$tjp_19 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isDefaultBaseIsMoof", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 230);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hasDefaultSampleDuration", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 134);
        ajc$tjp_20 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultBaseIsMoof", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "boolean", "defaultBaseIsMoof", "", "void"), 234);
        ajc$tjp_21 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "java.lang.String"), 244);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hasDefaultSampleSize", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 138);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hasDefaultSampleFlags", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 142);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTrackId", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 146);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTrackId", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "trackId", "", "void"), 150);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBaseDataOffset", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 154);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBaseDataOffset", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "baseDataOffset", "", "void"), 158);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSampleDescriptionIndex", "szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 167);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.trackId = IsoTypeReader.readUInt32(byteBuffer);
        if ((getFlags() & 1) == 1) {
            this.baseDataOffset = IsoTypeReader.readUInt64(byteBuffer);
        }
        if ((getFlags() & 2) == 2) {
            this.sampleDescriptionIndex = IsoTypeReader.readUInt32(byteBuffer);
        }
        if ((getFlags() & 8) == 8) {
            this.defaultSampleDuration = IsoTypeReader.readUInt32(byteBuffer);
        }
        if ((getFlags() & 16) == 16) {
            this.defaultSampleSize = IsoTypeReader.readUInt32(byteBuffer);
        }
        if ((getFlags() & 32) == 32) {
            this.defaultSampleFlags = new SampleFlags(byteBuffer);
        }
        if ((getFlags() & 65536) == 65536) {
            this.durationIsEmpty = true;
        }
        if ((getFlags() & 131072) == 131072) {
            this.defaultBaseIsMoof = true;
        }
    }

    public long getBaseDataOffset() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        return this.baseDataOffset;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.trackId);
        if ((getFlags() & 1) == 1) {
            IsoTypeWriter.writeUInt64(byteBuffer, getBaseDataOffset());
        }
        if ((getFlags() & 2) == 2) {
            IsoTypeWriter.writeUInt32(byteBuffer, getSampleDescriptionIndex());
        }
        if ((getFlags() & 8) == 8) {
            IsoTypeWriter.writeUInt32(byteBuffer, getDefaultSampleDuration());
        }
        if ((getFlags() & 16) == 16) {
            IsoTypeWriter.writeUInt32(byteBuffer, getDefaultSampleSize());
        }
        if ((getFlags() & 32) == 32) {
            this.defaultSampleFlags.getContent(byteBuffer);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        int flags = getFlags();
        long j = (flags & 1) == 1 ? 16L : 8L;
        if ((flags & 2) == 2) {
            j += 4;
        }
        if ((flags & 8) == 8) {
            j += 4;
        }
        if ((flags & 16) == 16) {
            j += 4;
        }
        return (flags & 32) == 32 ? j + 4 : j;
    }

    public long getDefaultSampleDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this));
        return this.defaultSampleDuration;
    }

    public SampleFlags getDefaultSampleFlags() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this));
        return this.defaultSampleFlags;
    }

    public long getDefaultSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this));
        return this.defaultSampleSize;
    }

    public long getSampleDescriptionIndex() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this));
        return this.sampleDescriptionIndex;
    }

    public long getTrackId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return this.trackId;
    }

    public boolean hasBaseDataOffset() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return (getFlags() & 1) != 0;
    }

    public boolean hasDefaultSampleDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return (getFlags() & 8) != 0;
    }

    public boolean hasDefaultSampleFlags() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return (getFlags() & 32) != 0;
    }

    public boolean hasDefaultSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return (getFlags() & 16) != 0;
    }

    public boolean hasSampleDescriptionIndex() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return (getFlags() & 2) != 0;
    }

    public boolean isDefaultBaseIsMoof() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_19, this, this));
        return this.defaultBaseIsMoof;
    }

    public boolean isDurationIsEmpty() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_17, this, this));
        return this.durationIsEmpty;
    }

    public void setBaseDataOffset(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this, Conversions.longObject(j)));
        setFlags(j == -1 ? getFlags() & 2147483646 : getFlags() | 1);
        this.baseDataOffset = j;
    }

    public void setDefaultBaseIsMoof(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_20, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 131072 : getFlags() & 16646143);
        this.defaultBaseIsMoof = z;
    }

    public void setDefaultSampleDuration(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this, Conversions.longObject(j)));
        setFlags(getFlags() | 8);
        this.defaultSampleDuration = j;
    }

    public void setDefaultSampleFlags(SampleFlags sampleFlags) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_16, this, this, sampleFlags));
        setFlags(sampleFlags != null ? getFlags() | 32 : getFlags() & 16777183);
        this.defaultSampleFlags = sampleFlags;
    }

    public void setDefaultSampleSize(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this, Conversions.longObject(j)));
        setFlags(j != -1 ? getFlags() | 16 : getFlags() & 16777199);
        this.defaultSampleSize = j;
    }

    public void setDurationIsEmpty(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_18, this, this, Conversions.booleanObject(z)));
        setFlags(this.defaultBaseIsMoof ? getFlags() | 65536 : getFlags() & 16711679);
        this.durationIsEmpty = z;
    }

    public void setSampleDescriptionIndex(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, Conversions.longObject(j)));
        setFlags(j == -1 ? getFlags() & 2147483645 : getFlags() | 2);
        this.sampleDescriptionIndex = j;
    }

    public void setTrackId(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, Conversions.longObject(j)));
        this.trackId = j;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_21, this, this));
        return "TrackFragmentHeaderBox{trackId=" + this.trackId + ", baseDataOffset=" + this.baseDataOffset + ", sampleDescriptionIndex=" + this.sampleDescriptionIndex + ", defaultSampleDuration=" + this.defaultSampleDuration + ", defaultSampleSize=" + this.defaultSampleSize + ", defaultSampleFlags=" + this.defaultSampleFlags + ", durationIsEmpty=" + this.durationIsEmpty + ", defaultBaseIsMoof=" + this.defaultBaseIsMoof + '}';
    }
}
