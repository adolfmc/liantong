package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrackLoadSettingsAtom extends AbstractBox {
    public static final String TYPE = "load";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    int defaultHints;
    int preloadDuration;
    int preloadFlags;
    int preloadStartTime;

    static {
        ajc$preClinit();
    }

    public TrackLoadSettingsAtom() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TrackLoadSettingsAtom.java", TrackLoadSettingsAtom.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPreloadStartTime", "szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom", "", "", "", "int"), 49);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPreloadStartTime", "szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom", "int", "preloadStartTime", "", "void"), 53);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPreloadDuration", "szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom", "", "", "", "int"), 57);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPreloadDuration", "szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom", "int", "preloadDuration", "", "void"), 61);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPreloadFlags", "szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom", "", "", "", "int"), 65);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPreloadFlags", "szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom", "int", "preloadFlags", "", "void"), 69);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultHints", "szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom", "", "", "", "int"), 73);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultHints", "szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom", "int", "defaultHints", "", "void"), 77);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.preloadStartTime = byteBuffer.getInt();
        this.preloadDuration = byteBuffer.getInt();
        this.preloadFlags = byteBuffer.getInt();
        this.defaultHints = byteBuffer.getInt();
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.preloadStartTime);
        byteBuffer.putInt(this.preloadDuration);
        byteBuffer.putInt(this.preloadFlags);
        byteBuffer.putInt(this.defaultHints);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 16L;
    }

    public int getDefaultHints() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.defaultHints;
    }

    public int getPreloadDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.preloadDuration;
    }

    public int getPreloadFlags() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.preloadFlags;
    }

    public int getPreloadStartTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.preloadStartTime;
    }

    public void setDefaultHints(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.defaultHints = i;
    }

    public void setPreloadDuration(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.preloadDuration = i;
    }

    public void setPreloadFlags(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.preloadFlags = i;
    }

    public void setPreloadStartTime(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.preloadStartTime = i;
    }
}
