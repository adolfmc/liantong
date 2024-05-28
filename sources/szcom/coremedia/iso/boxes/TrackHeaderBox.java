package szcom.coremedia.iso.boxes;

import com.cjt2325.cameralibrary.CameraInterface;
import java.nio.ByteBuffer;
import java.util.Date;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.DateHelper;
import szcom.googlecode.mp4parser.util.Logger;
import szcom.googlecode.mp4parser.util.Matrix;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrackHeaderBox extends AbstractFullBox {
    private static Logger LOG = null;
    public static final String TYPE = "tkhd";
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
    private static final JoinPoint.StaticPart ajc$tjp_22 = null;
    private static final JoinPoint.StaticPart ajc$tjp_23 = null;
    private static final JoinPoint.StaticPart ajc$tjp_24 = null;
    private static final JoinPoint.StaticPart ajc$tjp_25 = null;
    private static final JoinPoint.StaticPart ajc$tjp_26 = null;
    private static final JoinPoint.StaticPart ajc$tjp_27 = null;
    private static final JoinPoint.StaticPart ajc$tjp_28 = null;
    private static final JoinPoint.StaticPart ajc$tjp_29 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private int alternateGroup;
    private Date creationTime;
    private long duration;
    private double height;
    private int layer;
    private Matrix matrix;
    private Date modificationTime;
    private long trackId;
    private float volume;
    private double width;

    static {
        ajc$preClinit();
        LOG = Logger.getLogger(TrackHeaderBox.class);
    }

    public TrackHeaderBox() {
        super(TYPE);
        this.creationTime = new Date(0L);
        this.modificationTime = new Date(0L);
        this.matrix = Matrix.ROTATE_0;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TrackHeaderBox.java", TrackHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCreationTime", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "java.util.Date"), 62);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getModificationTime", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "java.util.Date"), 66);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getContent", "szcom.coremedia.iso.boxes.TrackHeaderBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), CameraInterface.TYPE_CAPTURE);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "java.lang.String"), 173);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCreationTime", "szcom.coremedia.iso.boxes.TrackHeaderBox", "java.util.Date", "creationTime", "", "void"), 199);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setModificationTime", "szcom.coremedia.iso.boxes.TrackHeaderBox", "java.util.Date", "modificationTime", "", "void"), 206);
        ajc$tjp_14 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTrackId", "szcom.coremedia.iso.boxes.TrackHeaderBox", "long", "trackId", "", "void"), 214);
        ajc$tjp_15 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDuration", "szcom.coremedia.iso.boxes.TrackHeaderBox", "long", "duration", "", "void"), 218);
        ajc$tjp_16 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLayer", "szcom.coremedia.iso.boxes.TrackHeaderBox", "int", "layer", "", "void"), 225);
        ajc$tjp_17 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAlternateGroup", "szcom.coremedia.iso.boxes.TrackHeaderBox", "int", "alternateGroup", "", "void"), 229);
        ajc$tjp_18 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setVolume", "szcom.coremedia.iso.boxes.TrackHeaderBox", "float", "volume", "", "void"), 233);
        ajc$tjp_19 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMatrix", "szcom.coremedia.iso.boxes.TrackHeaderBox", "szcom.googlecode.mp4parser.util.Matrix", "matrix", "", "void"), 237);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTrackId", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "long"), 70);
        ajc$tjp_20 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setWidth", "szcom.coremedia.iso.boxes.TrackHeaderBox", "double", "width", "", "void"), 241);
        ajc$tjp_21 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setHeight", "szcom.coremedia.iso.boxes.TrackHeaderBox", "double", "height", "", "void"), 245);
        ajc$tjp_22 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isEnabled", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "boolean"), 250);
        ajc$tjp_23 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isInMovie", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "boolean"), 254);
        ajc$tjp_24 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isInPreview", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "boolean"), 258);
        ajc$tjp_25 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "isInPoster", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "boolean"), 262);
        ajc$tjp_26 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEnabled", "szcom.coremedia.iso.boxes.TrackHeaderBox", "boolean", "enabled", "", "void"), 266);
        ajc$tjp_27 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setInMovie", "szcom.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inMovie", "", "void"), 274);
        ajc$tjp_28 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setInPreview", "szcom.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inPreview", "", "void"), 282);
        ajc$tjp_29 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setInPoster", "szcom.coremedia.iso.boxes.TrackHeaderBox", "boolean", "inPoster", "", "void"), 290);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDuration", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "long"), 74);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLayer", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "int"), 78);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAlternateGroup", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "int"), 82);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getVolume", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "float"), 86);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMatrix", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "szcom.googlecode.mp4parser.util.Matrix"), 90);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getWidth", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "double"), 94);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getHeight", "szcom.coremedia.iso.boxes.TrackHeaderBox", "", "", "", "double"), 98);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        long j;
        parseVersionAndFlags(byteBuffer);
        if (getVersion() == 1) {
            this.creationTime = DateHelper.convert(IsoTypeReader.readUInt64(byteBuffer));
            this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt64(byteBuffer));
            this.trackId = IsoTypeReader.readUInt32(byteBuffer);
            IsoTypeReader.readUInt32(byteBuffer);
            j = byteBuffer.getLong();
        } else {
            this.creationTime = DateHelper.convert(IsoTypeReader.readUInt32(byteBuffer));
            this.modificationTime = DateHelper.convert(IsoTypeReader.readUInt32(byteBuffer));
            this.trackId = IsoTypeReader.readUInt32(byteBuffer);
            IsoTypeReader.readUInt32(byteBuffer);
            j = byteBuffer.getInt();
        }
        this.duration = j;
        if (this.duration < -1) {
            LOG.logWarn("tkhd duration is not in expected range");
        }
        IsoTypeReader.readUInt32(byteBuffer);
        IsoTypeReader.readUInt32(byteBuffer);
        this.layer = IsoTypeReader.readUInt16(byteBuffer);
        this.alternateGroup = IsoTypeReader.readUInt16(byteBuffer);
        this.volume = IsoTypeReader.readFixedPoint88(byteBuffer);
        IsoTypeReader.readUInt16(byteBuffer);
        this.matrix = Matrix.fromByteBuffer(byteBuffer);
        this.width = IsoTypeReader.readFixedPoint1616(byteBuffer);
        this.height = IsoTypeReader.readFixedPoint1616(byteBuffer);
    }

    public int getAlternateGroup() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return this.alternateGroup;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, byteBuffer));
        writeVersionAndFlags(byteBuffer);
        if (getVersion() == 1) {
            IsoTypeWriter.writeUInt64(byteBuffer, DateHelper.convert(this.creationTime));
            IsoTypeWriter.writeUInt64(byteBuffer, DateHelper.convert(this.modificationTime));
            IsoTypeWriter.writeUInt32(byteBuffer, this.trackId);
            IsoTypeWriter.writeUInt32(byteBuffer, 0L);
            byteBuffer.putLong(this.duration);
        } else {
            IsoTypeWriter.writeUInt32(byteBuffer, DateHelper.convert(this.creationTime));
            IsoTypeWriter.writeUInt32(byteBuffer, DateHelper.convert(this.modificationTime));
            IsoTypeWriter.writeUInt32(byteBuffer, this.trackId);
            IsoTypeWriter.writeUInt32(byteBuffer, 0L);
            byteBuffer.putInt((int) this.duration);
        }
        IsoTypeWriter.writeUInt32(byteBuffer, 0L);
        IsoTypeWriter.writeUInt32(byteBuffer, 0L);
        IsoTypeWriter.writeUInt16(byteBuffer, this.layer);
        IsoTypeWriter.writeUInt16(byteBuffer, this.alternateGroup);
        IsoTypeWriter.writeFixedPoint88(byteBuffer, this.volume);
        IsoTypeWriter.writeUInt16(byteBuffer, 0);
        this.matrix.getContent(byteBuffer);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.width);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.height);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (getVersion() == 1 ? 36L : 24L) + 60;
    }

    public Date getCreationTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.creationTime;
    }

    public long getDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.duration;
    }

    public double getHeight() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this));
        return this.height;
    }

    public int getLayer() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.layer;
    }

    public Matrix getMatrix() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        return this.matrix;
    }

    public Date getModificationTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.modificationTime;
    }

    public long getTrackId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.trackId;
    }

    public float getVolume() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.volume;
    }

    public double getWidth() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.width;
    }

    public boolean isEnabled() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_22, this, this));
        return (getFlags() & 1) > 0;
    }

    public boolean isInMovie() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_23, this, this));
        return (getFlags() & 2) > 0;
    }

    public boolean isInPoster() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_25, this, this));
        return (getFlags() & 8) > 0;
    }

    public boolean isInPreview() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_24, this, this));
        return (getFlags() & 4) > 0;
    }

    public void setAlternateGroup(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_17, this, this, Conversions.intObject(i)));
        this.alternateGroup = i;
    }

    public void setCreationTime(Date date) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this, date));
        this.creationTime = date;
        if (DateHelper.convert(date) >= 4294967296L) {
            setVersion(1);
        }
    }

    public void setDuration(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this, Conversions.longObject(j)));
        this.duration = j;
        if (j >= 4294967296L) {
            setFlags(1);
        }
    }

    public void setEnabled(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_26, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 1 : getFlags() & (-2));
    }

    public void setHeight(double d) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_21, this, this, Conversions.doubleObject(d)));
        this.height = d;
    }

    public void setInMovie(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_27, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 2 : getFlags() & (-3));
    }

    public void setInPoster(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_29, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 8 : getFlags() & (-9));
    }

    public void setInPreview(boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_28, this, this, Conversions.booleanObject(z)));
        setFlags(z ? getFlags() | 4 : getFlags() & (-5));
    }

    public void setLayer(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_16, this, this, Conversions.intObject(i)));
        this.layer = i;
    }

    public void setMatrix(Matrix matrix) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_19, this, this, matrix));
        this.matrix = matrix;
    }

    public void setModificationTime(Date date) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, date));
        this.modificationTime = date;
        if (DateHelper.convert(date) >= 4294967296L) {
            setVersion(1);
        }
    }

    public void setTrackId(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this, Conversions.longObject(j)));
        this.trackId = j;
    }

    public void setVolume(float f) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_18, this, this, Conversions.floatObject(f)));
        this.volume = f;
    }

    public void setWidth(double d) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_20, this, this, Conversions.doubleObject(d)));
        this.width = d;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this));
        return "TrackHeaderBox[creationTime=" + getCreationTime() + ";modificationTime=" + getModificationTime() + ";trackId=" + getTrackId() + ";duration=" + getDuration() + ";layer=" + getLayer() + ";alternateGroup=" + getAlternateGroup() + ";volume=" + getVolume() + ";matrix=" + this.matrix + ";width=" + getWidth() + ";height=" + getHeight() + "]";
    }
}
