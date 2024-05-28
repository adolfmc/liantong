package szcom.googlecode.mp4parser.authoring;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Track extends Closeable {
    List<CompositionTimeToSample.Entry> getCompositionTimeEntries();

    long getDuration();

    List<Edit> getEdits();

    String getHandler();

    String getName();

    List<SampleDependencyTypeBox.Entry> getSampleDependencies();

    SampleDescriptionBox getSampleDescriptionBox();

    long[] getSampleDurations();

    Map<GroupEntry, long[]> getSampleGroups();

    List<Sample> getSamples();

    SubSampleInformationBox getSubsampleInformationBox();

    long[] getSyncSamples();

    TrackMetaData getTrackMetaData();
}
