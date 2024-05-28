package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReaderVariable;
import szcom.coremedia.iso.IsoTypeWriterVariable;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AppleVariableSignedIntegerBox extends AppleDataBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    int intLength;
    long value;

    static {
        ajc$preClinit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AppleVariableSignedIntegerBox(String str) {
        super(str, 15);
        this.intLength = 1;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AppleVariableSignedIntegerBox.java", AppleVariableSignedIntegerBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getIntLength", "szcom.googlecode.mp4parser.boxes.apple.AppleVariableSignedIntegerBox", "", "", "", "int"), 19);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setIntLength", "szcom.googlecode.mp4parser.boxes.apple.AppleVariableSignedIntegerBox", "int", "intLength", "", "void"), 23);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getValue", "szcom.googlecode.mp4parser.boxes.apple.AppleVariableSignedIntegerBox", "", "", "", "long"), 27);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setValue", "szcom.googlecode.mp4parser.boxes.apple.AppleVariableSignedIntegerBox", "long", "value", "", "void"), 36);
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected int getDataLength() {
        return this.intLength;
    }

    public int getIntLength() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.intLength;
    }

    public long getValue() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        if (!isParsed()) {
            parseDetails();
        }
        return this.value;
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected void parseData(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        this.value = IsoTypeReaderVariable.read(byteBuffer, remaining);
        this.intLength = remaining;
    }

    public void setIntLength(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.intLength = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
        if (r2.intLength < 2) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0032, code lost:
        r2.intLength = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
        if (r2.intLength < 3) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setValue(long r3) {
        /*
            r2 = this;
            szorg.mp4parser.aspectj.lang.JoinPoint$StaticPart r0 = szcom.googlecode.mp4parser.boxes.apple.AppleVariableSignedIntegerBox.ajc$tjp_3
            java.lang.Object r1 = szorg.mp4parser.aspectj.runtime.internal.Conversions.longObject(r3)
            szorg.mp4parser.aspectj.lang.JoinPoint r0 = szorg.mp4parser.aspectj.runtime.reflect.Factory.makeJP(r0, r2, r2, r1)
            szcom.googlecode.mp4parser.RequiresParseDetailAspect r1 = szcom.googlecode.mp4parser.RequiresParseDetailAspect.aspectOf()
            r1.before(r0)
            r0 = 127(0x7f, double:6.27E-322)
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 > 0) goto L21
            r0 = -128(0xffffffffffffff80, double:NaN)
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L21
            r0 = 1
        L1e:
            r2.intLength = r0
            goto L4b
        L21:
            r0 = 32767(0x7fff, double:1.6189E-319)
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 > 0) goto L35
            r0 = -32768(0xffffffffffff8000, double:NaN)
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L35
            int r0 = r2.intLength
            r1 = 2
            if (r0 >= r1) goto L35
        L32:
            r2.intLength = r1
            goto L4b
        L35:
            r0 = 8388607(0x7fffff, double:4.1445225E-317)
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 > 0) goto L49
            r0 = -8388608(0xffffffffff800000, double:NaN)
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L49
            int r0 = r2.intLength
            r1 = 3
            if (r0 >= r1) goto L49
            goto L32
        L49:
            r0 = 4
            goto L1e
        L4b:
            r2.value = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: szcom.googlecode.mp4parser.boxes.apple.AppleVariableSignedIntegerBox.setValue(long):void");
    }

    @Override // szcom.googlecode.mp4parser.boxes.apple.AppleDataBox
    protected byte[] writeData() {
        int dataLength = getDataLength();
        ByteBuffer wrap = ByteBuffer.wrap(new byte[dataLength]);
        IsoTypeWriterVariable.write(this.value, wrap, dataLength);
        return wrap.array();
    }
}
