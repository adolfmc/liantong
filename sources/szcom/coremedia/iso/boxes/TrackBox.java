package szcom.coremedia.iso.boxes;

import java.util.List;
import szcom.googlecode.mp4parser.AbstractContainerBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrackBox extends AbstractContainerBox {
    public static final String TYPE = "trak";
    private SampleTableBox sampleTableBox;

    public TrackBox() {
        super(TYPE);
    }

    public MediaBox getMediaBox() {
        for (Box box : getBoxes()) {
            if (box instanceof MediaBox) {
                return (MediaBox) box;
            }
        }
        return null;
    }

    public SampleTableBox getSampleTableBox() {
        MediaInformationBox mediaInformationBox;
        SampleTableBox sampleTableBox = this.sampleTableBox;
        if (sampleTableBox != null) {
            return sampleTableBox;
        }
        MediaBox mediaBox = getMediaBox();
        if (mediaBox == null || (mediaInformationBox = mediaBox.getMediaInformationBox()) == null) {
            return null;
        }
        this.sampleTableBox = mediaInformationBox.getSampleTableBox();
        return this.sampleTableBox;
    }

    public TrackHeaderBox getTrackHeaderBox() {
        for (Box box : getBoxes()) {
            if (box instanceof TrackHeaderBox) {
                return (TrackHeaderBox) box;
            }
        }
        return null;
    }

    @Override // szcom.googlecode.mp4parser.BasicContainer, szcom.coremedia.iso.boxes.Container
    public void setBoxes(List<Box> list) {
        super.setBoxes(list);
        this.sampleTableBox = null;
    }
}
