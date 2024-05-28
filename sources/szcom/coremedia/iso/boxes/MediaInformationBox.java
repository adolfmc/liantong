package szcom.coremedia.iso.boxes;

import szcom.googlecode.mp4parser.AbstractContainerBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MediaInformationBox extends AbstractContainerBox {
    public static final String TYPE = "minf";

    public MediaInformationBox() {
        super(TYPE);
    }

    public AbstractMediaHeaderBox getMediaHeaderBox() {
        for (Box box : getBoxes()) {
            if (box instanceof AbstractMediaHeaderBox) {
                return (AbstractMediaHeaderBox) box;
            }
        }
        return null;
    }

    public SampleTableBox getSampleTableBox() {
        for (Box box : getBoxes()) {
            if (box instanceof SampleTableBox) {
                return (SampleTableBox) box;
            }
        }
        return null;
    }
}
