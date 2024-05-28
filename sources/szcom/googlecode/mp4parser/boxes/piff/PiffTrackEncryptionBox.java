package szcom.googlecode.mp4parser.boxes.piff;

import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.boxes.AbstractTrackEncryptionBox;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class PiffTrackEncryptionBox extends AbstractTrackEncryptionBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;

    static {
        ajc$preClinit();
    }

    public PiffTrackEncryptionBox() {
        super("uuid");
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("PiffTrackEncryptionBox.java", PiffTrackEncryptionBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFlags", "szcom.googlecode.mp4parser.boxes.piff.PiffTrackEncryptionBox", "", "", "", "int"), 29);
    }

    @Override // szcom.googlecode.mp4parser.AbstractFullBox, szcom.coremedia.iso.boxes.FullBox
    public int getFlags() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return 0;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public byte[] getUserType() {
        return new byte[]{-119, 116, -37, -50, 123, -25, 76, 81, -124, -7, 113, 72, -7, -120, 37, 84};
    }
}
