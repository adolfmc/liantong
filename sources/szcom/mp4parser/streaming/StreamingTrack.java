package szcom.mp4parser.streaming;

import java.util.concurrent.BlockingQueue;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.TrackHeaderBox;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface StreamingTrack {
    void addTrackExtension(TrackExtension trackExtension);

    String getHandler();

    String getLanguage();

    SampleDescriptionBox getSampleDescriptionBox();

    BlockingQueue<StreamingSample> getSamples();

    long getTimescale();

    <T extends TrackExtension> T getTrackExtension(Class<T> cls);

    TrackHeaderBox getTrackHeaderBox();

    boolean hasMoreSamples();

    void removeTrackExtension(Class<? extends TrackExtension> cls);
}
