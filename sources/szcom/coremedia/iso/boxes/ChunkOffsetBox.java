package szcom.coremedia.iso.boxes;

import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class ChunkOffsetBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;

    static {
        ajc$preClinit();
    }

    public ChunkOffsetBox(String str) {
        super(str);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("ChunkOffsetBox.java", ChunkOffsetBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.ChunkOffsetBox", "", "", "", "java.lang.String"), 18);
    }

    public abstract long[] getChunkOffsets();

    public abstract void setChunkOffsets(long[] jArr);

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return String.valueOf(getClass().getSimpleName()) + "[entryCount=" + getChunkOffsets().length + "]";
    }
}
