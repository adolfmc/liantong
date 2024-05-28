package szcom.coremedia.iso.boxes.fragment;

import szcom.coremedia.iso.boxes.Box;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.annotations.DoNotParseDetail;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrackFragmentBox extends AbstractContainerBox {
    public static final String TYPE = "traf";

    public TrackFragmentBox() {
        super(TYPE);
    }

    @DoNotParseDetail
    public TrackFragmentHeaderBox getTrackFragmentHeaderBox() {
        for (Box box : getBoxes()) {
            if (box instanceof TrackFragmentHeaderBox) {
                return (TrackFragmentHeaderBox) box;
            }
        }
        return null;
    }
}
