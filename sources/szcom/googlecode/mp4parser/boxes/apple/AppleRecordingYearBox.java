package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AppleRecordingYearBox extends AppleDataBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    Date date;

    /* renamed from: df */
    DateFormat f27666df;

    static {
        ajc$preClinit();
    }

    public AppleRecordingYearBox() {
        super("©day", 1);
        this.date = new Date();
        this.f27666df = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ssZ");
        this.f27666df.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleRecordingYearBox.java", AppleRecordingYearBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDate", "szcom.googlecode.mp4parser.boxes.apple.AppleRecordingYearBox", "", "", "", "java.util.Date"), 27);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDate", "szcom.googlecode.mp4parser.boxes.apple.AppleRecordingYearBox", "java.util.Date", "date", "", "void"), 31);
    }

    protected static String iso8601toRfc822Date(String str) {
        return str.replaceAll("Z$", "+0000").replaceAll("([0-9][0-9]):([0-9][0-9])$", "$1$2");
    }

    protected static String rfc822toIso8601Date(String str) {
        return str.replaceAll("\\+0000$", "Z");
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected int getDataLength() {
        return Utf8.convert(rfc822toIso8601Date(this.f27666df.format(this.date))).length;
    }

    public Date getDate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.date;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected void parseData(ByteBuffer byteBuffer) {
        try {
            this.date = this.f27666df.parse(iso8601toRfc822Date(IsoTypeReader.readString(byteBuffer, byteBuffer.remaining())));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDate(Date date) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, date));
        this.date = date;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected byte[] writeData() {
        return Utf8.convert(rfc822toIso8601Date(this.f27666df.format(this.date)));
    }
}
