package szcom.coremedia.iso.boxes;

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
public class MovieHeaderBox extends AbstractFullBox {
    private static Logger LOG = null;
    public static final String TYPE = "mvhd";
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
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private Date creationTime;
    private int currentTime;
    private long duration;
    private Matrix matrix;
    private Date modificationTime;
    private long nextTrackId;
    private int posterTime;
    private int previewDuration;
    private int previewTime;
    private double rate;
    private int selectionDuration;
    private int selectionTime;
    private long timescale;
    private float volume;

    static {
        ajc$preClinit();
        LOG = Logger.getLogger(MovieHeaderBox.class);
    }

    public MovieHeaderBox() {
        super(TYPE);
        this.rate = 1.0d;
        this.volume = 1.0f;
        this.matrix = Matrix.ROTATE_0;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("MovieHeaderBox.java", MovieHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCreationTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "java.util.Date"), 66);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getModificationTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "java.util.Date"), 70);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setModificationTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "java.util.Date", "modificationTime", "", "void"), 212);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTimescale", "szcom.coremedia.iso.boxes.MovieHeaderBox", "long", "timescale", "", "void"), 220);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDuration", "szcom.coremedia.iso.boxes.MovieHeaderBox", "long", "duration", "", "void"), 224);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setRate", "szcom.coremedia.iso.boxes.MovieHeaderBox", "double", "rate", "", "void"), 231);
        ajc$tjp_14 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setVolume", "szcom.coremedia.iso.boxes.MovieHeaderBox", "float", "volume", "", "void"), 235);
        ajc$tjp_15 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMatrix", "szcom.coremedia.iso.boxes.MovieHeaderBox", "szcom.googlecode.mp4parser.util.Matrix", "matrix", "", "void"), 239);
        ajc$tjp_16 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setNextTrackId", "szcom.coremedia.iso.boxes.MovieHeaderBox", "long", "nextTrackId", "", "void"), 243);
        ajc$tjp_17 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPreviewTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 247);
        ajc$tjp_18 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPreviewTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "int", "previewTime", "", "void"), 251);
        ajc$tjp_19 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPreviewDuration", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 255);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTimescale", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "long"), 74);
        ajc$tjp_20 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPreviewDuration", "szcom.coremedia.iso.boxes.MovieHeaderBox", "int", "previewDuration", "", "void"), 259);
        ajc$tjp_21 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPosterTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 263);
        ajc$tjp_22 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPosterTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "int", "posterTime", "", "void"), 267);
        ajc$tjp_23 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSelectionTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 271);
        ajc$tjp_24 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSelectionTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "int", "selectionTime", "", "void"), 275);
        ajc$tjp_25 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSelectionDuration", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 279);
        ajc$tjp_26 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSelectionDuration", "szcom.coremedia.iso.boxes.MovieHeaderBox", "int", "selectionDuration", "", "void"), 283);
        ajc$tjp_27 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCurrentTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "int"), 287);
        ajc$tjp_28 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCurrentTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "int", "currentTime", "", "void"), 291);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDuration", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "long"), 78);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getRate", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "double"), 82);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getVolume", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "float"), 86);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMatrix", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "szcom.googlecode.mp4parser.util.Matrix"), 90);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getNextTrackId", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "long"), 94);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.MovieHeaderBox", "", "", "", "java.lang.String"), 148);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCreationTime", "szcom.coremedia.iso.boxes.MovieHeaderBox", "java.util.Date", "creationTime", "", "void"), 204);
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
            LOG.logWarn("mvhd duration is not in expected range");
        }
        this.rate = IsoTypeReader.readFixedPoint1616(byteBuffer);
        this.volume = IsoTypeReader.readFixedPoint88(byteBuffer);
        IsoTypeReader.readUInt16(byteBuffer);
        IsoTypeReader.readUInt32(byteBuffer);
        IsoTypeReader.readUInt32(byteBuffer);
        this.matrix = Matrix.fromByteBuffer(byteBuffer);
        this.previewTime = byteBuffer.getInt();
        this.previewDuration = byteBuffer.getInt();
        this.posterTime = byteBuffer.getInt();
        this.selectionTime = byteBuffer.getInt();
        this.selectionDuration = byteBuffer.getInt();
        this.currentTime = byteBuffer.getInt();
        this.nextTrackId = IsoTypeReader.readUInt32(byteBuffer);
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
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.rate);
        IsoTypeWriter.writeFixedPoint88(byteBuffer, this.volume);
        IsoTypeWriter.writeUInt16(byteBuffer, 0);
        IsoTypeWriter.writeUInt32(byteBuffer, 0L);
        IsoTypeWriter.writeUInt32(byteBuffer, 0L);
        this.matrix.getContent(byteBuffer);
        byteBuffer.putInt(this.previewTime);
        byteBuffer.putInt(this.previewDuration);
        byteBuffer.putInt(this.posterTime);
        byteBuffer.putInt(this.selectionTime);
        byteBuffer.putInt(this.selectionDuration);
        byteBuffer.putInt(this.currentTime);
        IsoTypeWriter.writeUInt32(byteBuffer, this.nextTrackId);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (getVersion() == 1 ? 32L : 20L) + 80;
    }

    public Date getCreationTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.creationTime;
    }

    public int getCurrentTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_27, this, this));
        return this.currentTime;
    }

    public long getDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.duration;
    }

    public Matrix getMatrix() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.matrix;
    }

    public Date getModificationTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.modificationTime;
    }

    public long getNextTrackId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        return this.nextTrackId;
    }

    public int getPosterTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_21, this, this));
        return this.posterTime;
    }

    public int getPreviewDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_19, this, this));
        return this.previewDuration;
    }

    public int getPreviewTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_17, this, this));
        return this.previewTime;
    }

    public double getRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.rate;
    }

    public int getSelectionDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_25, this, this));
        return this.selectionDuration;
    }

    public int getSelectionTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_23, this, this));
        return this.selectionTime;
    }

    public long getTimescale() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.timescale;
    }

    public float getVolume() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return this.volume;
    }

    public void setCreationTime(Date date) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, date));
        this.creationTime = date;
        if (DateHelper.convert(date) >= 4294967296L) {
            setVersion(1);
        }
    }

    public void setCurrentTime(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_28, this, this, Conversions.intObject(i)));
        this.currentTime = i;
    }

    public void setDuration(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this, Conversions.longObject(j)));
        this.duration = j;
        if (j >= 4294967296L) {
            setVersion(1);
        }
    }

    public void setMatrix(Matrix matrix) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this, matrix));
        this.matrix = matrix;
    }

    public void setModificationTime(Date date) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, date));
        this.modificationTime = date;
        if (DateHelper.convert(date) >= 4294967296L) {
            setVersion(1);
        }
    }

    public void setNextTrackId(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_16, this, this, Conversions.longObject(j)));
        this.nextTrackId = j;
    }

    public void setPosterTime(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_22, this, this, Conversions.intObject(i)));
        this.posterTime = i;
    }

    public void setPreviewDuration(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_20, this, this, Conversions.intObject(i)));
        this.previewDuration = i;
    }

    public void setPreviewTime(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_18, this, this, Conversions.intObject(i)));
        this.previewTime = i;
    }

    public void setRate(double d) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, Conversions.doubleObject(d)));
        this.rate = d;
    }

    public void setSelectionDuration(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_26, this, this, Conversions.intObject(i)));
        this.selectionDuration = i;
    }

    public void setSelectionTime(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_24, this, this, Conversions.intObject(i)));
        this.selectionTime = i;
    }

    public void setTimescale(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.longObject(j)));
        this.timescale = j;
    }

    public void setVolume(float f) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this, Conversions.floatObject(f)));
        this.volume = f;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return "MovieHeaderBox[creationTime=" + getCreationTime() + ";modificationTime=" + getModificationTime() + ";timescale=" + getTimescale() + ";duration=" + getDuration() + ";rate=" + getRate() + ";volume=" + getVolume() + ";matrix=" + this.matrix + ";nextTrackId=" + getNextTrackId() + "]";
    }
}
