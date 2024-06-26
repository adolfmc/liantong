package szcom.coremedia.iso.boxes;

import szcom.googlecode.mp4parser.AbstractContainerBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MediaBox extends AbstractContainerBox {
    public static final String TYPE = "mdia";

    public MediaBox() {
        super(TYPE);
    }

    public HandlerBox getHandlerBox() {
        for (Box box : getBoxes()) {
            if (box instanceof HandlerBox) {
                return (HandlerBox) box;
            }
        }
        return null;
    }

    public MediaHeaderBox getMediaHeaderBox() {
        for (Box box : getBoxes()) {
            if (box instanceof MediaHeaderBox) {
                return (MediaHeaderBox) box;
            }
        }
        return null;
    }

    public MediaInformationBox getMediaInformationBox() {
        for (Box box : getBoxes()) {
            if (box instanceof MediaInformationBox) {
                return (MediaInformationBox) box;
            }
        }
        return null;
    }
}
