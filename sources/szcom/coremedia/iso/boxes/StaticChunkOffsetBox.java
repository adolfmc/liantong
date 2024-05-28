package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.CastUtils;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class StaticChunkOffsetBox extends ChunkOffsetBox {
    public static final String TYPE = "stco";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private long[] chunkOffsets;

    static {
        ajc$preClinit();
    }

    public StaticChunkOffsetBox() {
        super(TYPE);
        this.chunkOffsets = new long[0];
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("StaticChunkOffsetBox.java", StaticChunkOffsetBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getChunkOffsets", "szcom.coremedia.iso.boxes.StaticChunkOffsetBox", "", "", "", "[J"), 39);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setChunkOffsets", "szcom.coremedia.iso.boxes.StaticChunkOffsetBox", "[J", "chunkOffsets", "", "void"), 48);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        int l2i = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        this.chunkOffsets = new long[l2i];
        for (int i = 0; i < l2i; i++) {
            this.chunkOffsets[i] = IsoTypeReader.readUInt32(byteBuffer);
        }
    }

    @Override // szcom.coremedia.iso.boxes.ChunkOffsetBox
    public long[] getChunkOffsets() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.chunkOffsets;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.chunkOffsets.length);
        for (long j : this.chunkOffsets) {
            IsoTypeWriter.writeUInt32(byteBuffer, j);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (this.chunkOffsets.length * 4) + 8;
    }

    @Override // szcom.coremedia.iso.boxes.ChunkOffsetBox
    public void setChunkOffsets(long[] jArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, jArr));
        this.chunkOffsets = jArr;
    }
}
