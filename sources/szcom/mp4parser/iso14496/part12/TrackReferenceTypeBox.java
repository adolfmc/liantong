package szcom.mp4parser.iso14496.part12;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.Mp4Arrays;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrackReferenceTypeBox extends AbstractBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    long[] trackIds;

    static {
        ajc$preClinit();
    }

    public TrackReferenceTypeBox(String str) {
        super(str);
        this.trackIds = new long[0];
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TrackReferenceTypeBox.java", TrackReferenceTypeBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTrackIds", "szcom.mp4parser.iso14496.part12.TrackReferenceTypeBox", "", "", "", "[J"), 58);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTrackIds", "szcom.mp4parser.iso14496.part12.TrackReferenceTypeBox", "[J", "trackIds", "", "void"), 62);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        while (byteBuffer.remaining() >= 4) {
            this.trackIds = Mp4Arrays.copyOfAndAppend(this.trackIds, IsoTypeReader.readUInt32(byteBuffer));
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        for (long j : this.trackIds) {
            IsoTypeWriter.writeUInt32(byteBuffer, j);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.trackIds.length * 4;
    }

    public long[] getTrackIds() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.trackIds;
    }

    public void setTrackIds(long[] jArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, jArr));
        this.trackIds = jArr;
    }
}
