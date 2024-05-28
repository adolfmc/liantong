package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.util.Date;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.DateHelper;
import szcom.googlecode.mp4parser.util.Logger;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MediaHeaderBox extends AbstractFullBox {
    private static Logger LOG = null;
    public static final String TYPE = "mdhd";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private Date creationTime;
    private long duration;
    private String language;
    private Date modificationTime;
    private long timescale;

    static {
        ajc$preClinit();
        LOG = Logger.getLogger(MediaHeaderBox.class);
    }

    public MediaHeaderBox() {
        super(TYPE);
        this.creationTime = new Date();
        this.modificationTime = new Date();
        this.language = "eng";
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("MediaHeaderBox.java", MediaHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCreationTime", "szcom.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "java.util.Date"), 48);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getModificationTime", "szcom.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "java.util.Date"), 52);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "java.lang.String"), 125);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTimescale", "szcom.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "long"), 56);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDuration", "szcom.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "long"), 60);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLanguage", "szcom.coremedia.iso.boxes.MediaHeaderBox", "", "", "", "java.lang.String"), 64);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCreationTime", "szcom.coremedia.iso.boxes.MediaHeaderBox", "java.util.Date", "creationTime", "", "void"), 81);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setModificationTime", "szcom.coremedia.iso.boxes.MediaHeaderBox", "java.util.Date", "modificationTime", "", "void"), 85);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTimescale", "szcom.coremedia.iso.boxes.MediaHeaderBox", "long", "timescale", "", "void"), 89);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDuration", "szcom.coremedia.iso.boxes.MediaHeaderBox", "long", "duration", "", "void"), 93);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLanguage", "szcom.coremedia.iso.boxes.MediaHeaderBox", "java.lang.String", "language", "", "void"), 97);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        long j;
        parseVersionAndFlags(byteBuffer);
        if (getVersion() == 1) {
            this.creationTime = DateHelper.convert(IsoTypeReader.readUInt64(byteBuffer));
            this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt64(byteBuffer));
            this.timescale = IsoTypeReader.readUInt32(byteBuffer);
            j = byteBuffer.getLong();
        } else {
            this.creationTime = DateHelper.convert(IsoTypeReader.readUInt32(byteBuffer));
            this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt32(byteBuffer));
            this.timescale = IsoTypeReader.readUInt32(byteBuffer);
            j = byteBuffer.getInt();
        }
        this.duration = j;
        if (this.duration < -1) {
            LOG.logWarn("mdhd duration is not in expected range");
        }
        this.language = IsoTypeReader.readIso639(byteBuffer);
        IsoTypeReader.readUInt16(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        if (getVersion() == 1) {
            IsoTypeWriter.writeUInt64(byteBuffer, DateHelper.convert(this.creationTime));
            IsoTypeWriter.writeUInt64(byteBuffer, DateHelper.convert(this.modificationTime));
            IsoTypeWriter.writeUInt32(byteBuffer, this.timescale);
            byteBuffer.putLong(this.duration);
        } else {
            IsoTypeWriter.writeUInt32(byteBuffer, DateHelper.convert(this.creationTime));
            IsoTypeWriter.writeUInt32(byteBuffer, DateHelper.convert(this.modificationTime));
            IsoTypeWriter.writeUInt32(byteBuffer, this.timescale);
            byteBuffer.putInt((int) this.duration);
        }
        IsoTypeWriter.writeIso639(byteBuffer, this.language);
        IsoTypeWriter.writeUInt16(byteBuffer, 0);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (getVersion() == 1 ? 32L : 20L) + 2 + 2;
    }

    public Date getCreationTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.creationTime;
    }

    public long getDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.duration;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.language;
    }

    public Date getModificationTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.modificationTime;
    }

    public long getTimescale() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.timescale;
    }

    public void setCreationTime(Date date) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, date));
        this.creationTime = date;
    }

    public void setDuration(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this, Conversions.longObject(j)));
        this.duration = j;
    }

    public void setLanguage(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, str));
        this.language = str;
    }

    public void setModificationTime(Date date) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, date));
        this.modificationTime = date;
    }

    public void setTimescale(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.longObject(j)));
        this.timescale = j;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return "MediaHeaderBox[creationTime=" + getCreationTime() + ";modificationTime=" + getModificationTime() + ";timescale=" + getTimescale() + ";duration=" + getDuration() + ";language=" + getLanguage() + "]";
    }
}
