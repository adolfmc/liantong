package szcom.googlecode.mp4parser.h264.model;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ChromaFormat {
    public static ChromaFormat MONOCHROME = new ChromaFormat(0, 0, 0);
    public static ChromaFormat YUV_420 = new ChromaFormat(1, 2, 2);
    public static ChromaFormat YUV_422 = new ChromaFormat(2, 2, 1);
    public static ChromaFormat YUV_444 = new ChromaFormat(3, 1, 1);

    /* renamed from: id */
    private int f27669id;
    private int subHeight;
    private int subWidth;

    public ChromaFormat(int i, int i2, int i3) {
        this.f27669id = i;
        this.subWidth = i2;
        this.subHeight = i3;
    }

    public static ChromaFormat fromId(int i) {
        ChromaFormat chromaFormat = MONOCHROME;
        if (i == chromaFormat.f27669id) {
            return chromaFormat;
        }
        ChromaFormat chromaFormat2 = YUV_420;
        if (i == chromaFormat2.f27669id) {
            return chromaFormat2;
        }
        ChromaFormat chromaFormat3 = YUV_422;
        if (i == chromaFormat3.f27669id) {
            return chromaFormat3;
        }
        ChromaFormat chromaFormat4 = YUV_444;
        if (i == chromaFormat4.f27669id) {
            return chromaFormat4;
        }
        return null;
    }

    public int getId() {
        return this.f27669id;
    }

    public int getSubHeight() {
        return this.subHeight;
    }

    public int getSubWidth() {
        return this.subWidth;
    }

    public String toString() {
        return "ChromaFormat{\nid=" + this.f27669id + ",\n subWidth=" + this.subWidth + ",\n subHeight=" + this.subHeight + '}';
    }
}
