package szcom.googlecode.mp4parser.authoring.tracks.h265;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.FileDataSourceImpl;
import szcom.googlecode.mp4parser.authoring.Movie;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import szcom.googlecode.mp4parser.authoring.tracks.AbstractH26XTrack;
import szcom.mp4parser.iso14496.part15.HevcConfigurationBox;
import szcom.mp4parser.iso14496.part15.HevcDecoderConfigurationRecord;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class H265TrackImpl extends AbstractH26XTrack implements H265NalUnitTypes {
    ArrayList<ByteBuffer> pps;
    ArrayList<Sample> samples;
    ArrayList<ByteBuffer> sps;
    SampleDescriptionBox stsd;
    ArrayList<ByteBuffer> vps;

    /* JADX WARN: Code restructure failed: missing block: B:12:0x006d, code lost:
        if ((r5.get(2) & Byte.MIN_VALUE) != 0) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public H265TrackImpl(szcom.googlecode.mp4parser.DataSource r11) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: szcom.googlecode.mp4parser.authoring.tracks.h265.H265TrackImpl.<init>(szcom.googlecode.mp4parser.DataSource):void");
    }

    private SampleDescriptionBox createSampleDescriptionBox() {
        this.stsd = new SampleDescriptionBox();
        VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.TYPE6);
        visualSampleEntry.setDataReferenceIndex(1);
        visualSampleEntry.setDepth(24);
        visualSampleEntry.setFrameCount(1);
        visualSampleEntry.setHorizresolution(72.0d);
        visualSampleEntry.setVertresolution(72.0d);
        visualSampleEntry.setWidth(640);
        visualSampleEntry.setHeight(480);
        visualSampleEntry.setCompressorname("HEVC Coding");
        HevcConfigurationBox hevcConfigurationBox = new HevcConfigurationBox();
        HevcDecoderConfigurationRecord.Array array = new HevcDecoderConfigurationRecord.Array();
        array.array_completeness = true;
        array.nal_unit_type = 33;
        array.nalUnits = new ArrayList();
        Iterator<ByteBuffer> it = this.sps.iterator();
        while (it.hasNext()) {
            array.nalUnits.add(toArray(it.next()));
        }
        HevcDecoderConfigurationRecord.Array array2 = new HevcDecoderConfigurationRecord.Array();
        array2.array_completeness = true;
        array2.nal_unit_type = 34;
        array2.nalUnits = new ArrayList();
        Iterator<ByteBuffer> it2 = this.pps.iterator();
        while (it2.hasNext()) {
            array2.nalUnits.add(toArray(it2.next()));
        }
        HevcDecoderConfigurationRecord.Array array3 = new HevcDecoderConfigurationRecord.Array();
        array3.array_completeness = true;
        array3.nal_unit_type = 34;
        array3.nalUnits = new ArrayList();
        Iterator<ByteBuffer> it3 = this.vps.iterator();
        while (it3.hasNext()) {
            array3.nalUnits.add(toArray(it3.next()));
        }
        hevcConfigurationBox.getArrays().addAll(Arrays.asList(array, array3, array2));
        visualSampleEntry.addBox(hevcConfigurationBox);
        this.stsd.addBox(visualSampleEntry);
        return this.stsd;
    }

    public static H265NalUnitHeader getNalUnitHeader(ByteBuffer byteBuffer) {
        byteBuffer.position(0);
        int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
        H265NalUnitHeader h265NalUnitHeader = new H265NalUnitHeader();
        h265NalUnitHeader.forbiddenZeroFlag = (32768 & readUInt16) >> 15;
        h265NalUnitHeader.nalUnitType = (readUInt16 & 32256) >> 9;
        h265NalUnitHeader.nuhLayerId = (readUInt16 & 504) >> 3;
        h265NalUnitHeader.nuhTemporalIdPlusOne = readUInt16 & 7;
        return h265NalUnitHeader;
    }

    public static void main(String[] strArr) {
        H265TrackImpl h265TrackImpl = new H265TrackImpl(new FileDataSourceImpl("c:\\content\\test-UHD-HEVC_01_FMV_Med_track1.hvc"));
        Movie movie = new Movie();
        movie.addTrack(h265TrackImpl);
        new DefaultMp4Builder().build(movie).writeContainer(new FileOutputStream("output.mp4").getChannel());
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "vide";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    boolean isVcl(H265NalUnitHeader h265NalUnitHeader) {
        return h265NalUnitHeader.nalUnitType >= 0 && h265NalUnitHeader.nalUnitType <= 31;
    }

    public void wrapUp(List<ByteBuffer> list, boolean[] zArr, boolean[] zArr2) {
        this.samples.add(createSampleObject(list));
        PrintStream printStream = System.err;
        printStream.print("Create AU from " + list.size() + " NALs");
        if (zArr2[0]) {
            System.err.println("  IDR");
        } else {
            System.err.println();
        }
        zArr[0] = false;
        zArr2[0] = true;
        list.clear();
    }
}
