package szcom.googlecode.mp4parser.authoring.tracks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.coremedia.iso.boxes.TimeToSampleBox;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CroppedTrack extends AbstractTrack {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int fromSample;
    Track origTrack;
    private int toSample;

    public CroppedTrack(Track track, long j, long j2) {
        super("crop(" + track.getName() + ")");
        this.origTrack = track;
        this.fromSample = (int) j;
        this.toSample = (int) j2;
    }

    static List<CompositionTimeToSample.Entry> getCompositionTimeEntries(List<CompositionTimeToSample.Entry> list, long j, long j2) {
        CompositionTimeToSample.Entry next;
        CompositionTimeToSample.Entry entry;
        if (list == null || list.isEmpty()) {
            return null;
        }
        long j3 = 0;
        ListIterator<CompositionTimeToSample.Entry> listIterator = list.listIterator();
        ArrayList arrayList = new ArrayList();
        while (true) {
            next = listIterator.next();
            if (next.getCount() + j3 > j) {
                break;
            }
            j3 += next.getCount();
        }
        if (next.getCount() + j3 >= j2) {
            entry = new CompositionTimeToSample.Entry((int) (j2 - j), next.getOffset());
        } else {
            arrayList.add(new CompositionTimeToSample.Entry((int) ((next.getCount() + j3) - j), next.getOffset()));
            while (true) {
                j3 += next.getCount();
                if (!listIterator.hasNext()) {
                    break;
                }
                next = listIterator.next();
                if (next.getCount() + j3 >= j2) {
                    break;
                }
                arrayList.add(next);
            }
            entry = new CompositionTimeToSample.Entry((int) (j2 - j3), next.getOffset());
        }
        arrayList.add(entry);
        return arrayList;
    }

    static List<TimeToSampleBox.Entry> getDecodingTimeEntries(List<TimeToSampleBox.Entry> list, long j, long j2) {
        TimeToSampleBox.Entry next;
        TimeToSampleBox.Entry entry;
        if (list == null || list.isEmpty()) {
            return null;
        }
        long j3 = 0;
        ListIterator<TimeToSampleBox.Entry> listIterator = list.listIterator();
        LinkedList linkedList = new LinkedList();
        while (true) {
            next = listIterator.next();
            if (next.getCount() + j3 > j) {
                break;
            }
            j3 += next.getCount();
        }
        if (next.getCount() + j3 >= j2) {
            entry = new TimeToSampleBox.Entry(j2 - j, next.getDelta());
        } else {
            linkedList.add(new TimeToSampleBox.Entry((next.getCount() + j3) - j, next.getDelta()));
            while (true) {
                j3 += next.getCount();
                if (!listIterator.hasNext()) {
                    break;
                }
                next = listIterator.next();
                if (next.getCount() + j3 >= j2) {
                    break;
                }
                linkedList.add(next);
            }
            entry = new TimeToSampleBox.Entry(j2 - j3, next.getDelta());
        }
        linkedList.add(entry);
        return linkedList;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.origTrack.close();
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return getCompositionTimeEntries(this.origTrack.getCompositionTimeEntries(), this.fromSample, this.toSample);
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.origTrack.getHandler();
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        if (this.origTrack.getSampleDependencies() == null || this.origTrack.getSampleDependencies().isEmpty()) {
            return null;
        }
        return this.origTrack.getSampleDependencies().subList(this.fromSample, this.toSample);
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.origTrack.getSampleDescriptionBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public synchronized long[] getSampleDurations() {
        long[] jArr;
        jArr = new long[this.toSample - this.fromSample];
        System.arraycopy(this.origTrack.getSampleDurations(), this.fromSample, jArr, 0, jArr.length);
        return jArr;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.origTrack.getSamples().subList(this.fromSample, this.toSample);
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.origTrack.getSubsampleInformationBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public synchronized long[] getSyncSamples() {
        if (this.origTrack.getSyncSamples() != null) {
            long[] syncSamples = this.origTrack.getSyncSamples();
            int length = syncSamples.length;
            int i = 0;
            while (i < syncSamples.length && syncSamples[i] < this.fromSample) {
                i++;
            }
            while (length > 0 && this.toSample < syncSamples[length - 1]) {
                length--;
            }
            int i2 = length - i;
            long[] jArr = new long[i2];
            System.arraycopy(this.origTrack.getSyncSamples(), i, jArr, 0, i2);
            for (int i3 = 0; i3 < jArr.length; i3++) {
                jArr[i3] = jArr[i3] - this.fromSample;
            }
            return jArr;
        }
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.origTrack.getTrackMetaData();
    }
}
