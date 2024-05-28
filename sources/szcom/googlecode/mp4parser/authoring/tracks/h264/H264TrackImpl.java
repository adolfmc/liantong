package szcom.googlecode.mp4parser.authoring.tracks.h264;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.tracks.AbstractH26XTrack;
import szcom.googlecode.mp4parser.authoring.tracks.h264.SliceHeader;
import szcom.googlecode.mp4parser.h264.model.PictureParameterSet;
import szcom.googlecode.mp4parser.h264.model.SeqParameterSet;
import szcom.googlecode.mp4parser.util.Mp4Arrays;
import szcom.googlecode.mp4parser.util.RangeStartMap;
import szcom.mp4parser.iso14496.part15.AvcConfigurationBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class H264TrackImpl extends AbstractH26XTrack {
    private static final Logger LOG = Logger.getLogger(H264TrackImpl.class.getName());
    PictureParameterSet currentPictureParameterSet;
    SeqParameterSet currentSeqParameterSet;
    private boolean determineFrameRate;
    PictureParameterSet firstPictureParameterSet;
    SeqParameterSet firstSeqParameterSet;
    int frameNrInGop;
    private int frametick;
    private int height;
    private String lang;
    int[] pictureOrderCounts;
    RangeStartMap<Integer, byte[]> pictureParameterRangeMap;
    Map<Integer, PictureParameterSet> ppsIdToPps;
    Map<Integer, byte[]> ppsIdToPpsBytes;
    int prevPicOrderCntLsb;
    int prevPicOrderCntMsb;
    SampleDescriptionBox sampleDescriptionBox;
    private List<Sample> samples;
    private SEIMessage seiMessage;
    RangeStartMap<Integer, byte[]> seqParameterRangeMap;
    Map<Integer, SeqParameterSet> spsIdToSps;
    Map<Integer, byte[]> spsIdToSpsBytes;
    private long timescale;
    private int width;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class ByteBufferBackedInputStream extends InputStream {
        private final ByteBuffer buf;

        public ByteBufferBackedInputStream(ByteBuffer byteBuffer) {
            this.buf = byteBuffer.duplicate();
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.buf.hasRemaining()) {
                return this.buf.get() & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            if (this.buf.hasRemaining()) {
                int min = Math.min(i2, this.buf.remaining());
                this.buf.get(bArr, i, min);
                return min;
            }
            return -1;
        }
    }

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class SEIMessage {
        boolean clock_timestamp_flag;
        int cnt_dropped_flag;
        int counting_type;
        int cpb_removal_delay;
        int ct_type;
        int discontinuity_flag;
        int dpb_removal_delay;
        int full_timestamp_flag;
        int hours_value;
        int minutes_value;
        int n_frames;
        int nuit_field_based_flag;
        int payloadSize;
        int payloadType;
        int pic_struct;
        boolean removal_delay_flag;
        int seconds_value;
        SeqParameterSet sps;
        int time_offset;
        int time_offset_length;

        /* JADX WARN: Code restructure failed: missing block: B:51:0x0157, code lost:
            if (r4.readBool("pic_timing SEI: hours_flag") != false) goto L42;
         */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0162  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x016b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public SEIMessage(java.io.InputStream r12, szcom.googlecode.mp4parser.h264.model.SeqParameterSet r13) {
            /*
                Method dump skipped, instructions count: 464
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: szcom.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl.SEIMessage.<init>(szcom.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl, java.io.InputStream, szcom.googlecode.mp4parser.h264.model.SeqParameterSet):void");
        }

        public String toString() {
            String str = "SEIMessage{payloadType=" + this.payloadType + ", payloadSize=" + this.payloadSize;
            if (this.payloadType == 1) {
                if (this.sps.vuiParams.nalHRDParams != null || this.sps.vuiParams.vclHRDParams != null) {
                    str = String.valueOf(str) + ", cpb_removal_delay=" + this.cpb_removal_delay + ", dpb_removal_delay=" + this.dpb_removal_delay;
                }
                if (this.sps.vuiParams.pic_struct_present_flag) {
                    str = String.valueOf(str) + ", pic_struct=" + this.pic_struct;
                    if (this.clock_timestamp_flag) {
                        str = String.valueOf(str) + ", ct_type=" + this.ct_type + ", nuit_field_based_flag=" + this.nuit_field_based_flag + ", counting_type=" + this.counting_type + ", full_timestamp_flag=" + this.full_timestamp_flag + ", discontinuity_flag=" + this.discontinuity_flag + ", cnt_dropped_flag=" + this.cnt_dropped_flag + ", n_frames=" + this.n_frames + ", seconds_value=" + this.seconds_value + ", minutes_value=" + this.minutes_value + ", hours_value=" + this.hours_value + ", time_offset_length=" + this.time_offset_length + ", time_offset=" + this.time_offset;
                    }
                }
            }
            return String.valueOf(str) + '}';
        }
    }

    public H264TrackImpl(DataSource dataSource) {
        this(dataSource, "eng");
    }

    public H264TrackImpl(DataSource dataSource, String str) {
        this(dataSource, str, -1L, -1);
    }

    public H264TrackImpl(DataSource dataSource, String str, long j, int i) {
        super(dataSource);
        this.spsIdToSpsBytes = new HashMap();
        this.spsIdToSps = new HashMap();
        this.ppsIdToPpsBytes = new HashMap();
        this.ppsIdToPps = new HashMap();
        this.firstSeqParameterSet = null;
        this.firstPictureParameterSet = null;
        this.currentSeqParameterSet = null;
        this.currentPictureParameterSet = null;
        this.seqParameterRangeMap = new RangeStartMap<>();
        this.pictureParameterRangeMap = new RangeStartMap<>();
        this.frameNrInGop = 0;
        this.pictureOrderCounts = new int[0];
        this.prevPicOrderCntLsb = 0;
        this.prevPicOrderCntMsb = 0;
        this.determineFrameRate = true;
        this.lang = "eng";
        this.lang = str;
        this.timescale = j;
        this.frametick = i;
        if (j > 0 && i > 0) {
            this.determineFrameRate = false;
        }
        parse(new AbstractH26XTrack.LookAhead(dataSource));
    }

    private int calcPOC0(H264NalUnitHeader h264NalUnitHeader, SliceHeader sliceHeader) {
        int i;
        int i2 = sliceHeader.pic_order_cnt_lsb;
        int i3 = 1 << (sliceHeader.sps.log2_max_pic_order_cnt_lsb_minus4 + 4);
        int i4 = this.prevPicOrderCntLsb;
        if (i2 >= i4 || i4 - i2 < i3 / 2) {
            int i5 = this.prevPicOrderCntLsb;
            i = (i2 <= i5 || i2 - i5 <= i3 / 2) ? this.prevPicOrderCntMsb : this.prevPicOrderCntMsb - i3;
        } else {
            i = this.prevPicOrderCntMsb + i3;
        }
        if (h264NalUnitHeader.nal_ref_idc != 0) {
            this.prevPicOrderCntMsb = i;
            this.prevPicOrderCntLsb = i2;
        }
        return i + i2;
    }

    private int calcPOC1(int i, H264NalUnitHeader h264NalUnitHeader, SliceHeader sliceHeader) {
        int i2;
        if (sliceHeader.sps.num_ref_frames_in_pic_order_cnt_cycle == 0) {
            i = 0;
        }
        if (h264NalUnitHeader.nal_ref_idc == 0 && i > 0) {
            i--;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < sliceHeader.sps.num_ref_frames_in_pic_order_cnt_cycle; i4++) {
            i3 += sliceHeader.sps.offsetForRefFrame[i4];
        }
        if (i > 0) {
            int i5 = i - 1;
            int i6 = i5 / sliceHeader.sps.num_ref_frames_in_pic_order_cnt_cycle;
            int i7 = i5 % sliceHeader.sps.num_ref_frames_in_pic_order_cnt_cycle;
            i2 = i6 * i3;
            for (int i8 = 0; i8 <= i7; i8++) {
                i2 += sliceHeader.sps.offsetForRefFrame[i8];
            }
        } else {
            i2 = 0;
        }
        if (h264NalUnitHeader.nal_ref_idc == 0) {
            i2 += sliceHeader.sps.offset_for_non_ref_pic;
        }
        return i2 + sliceHeader.delta_pic_order_cnt_0;
    }

    private int calcPOC2(int i, H264NalUnitHeader h264NalUnitHeader, SliceHeader sliceHeader) {
        return h264NalUnitHeader.nal_ref_idc == 0 ? (i * 2) - 1 : i * 2;
    }

    private int calcPoc(int i, H264NalUnitHeader h264NalUnitHeader, SliceHeader sliceHeader) {
        return sliceHeader.sps.pic_order_cnt_type == 0 ? calcPOC0(h264NalUnitHeader, sliceHeader) : sliceHeader.sps.pic_order_cnt_type == 1 ? calcPOC1(i, h264NalUnitHeader, sliceHeader) : calcPOC2(i, h264NalUnitHeader, sliceHeader);
    }

    private void configureFramerate() {
        if (this.determineFrameRate) {
            if (this.firstSeqParameterSet.vuiParams == null) {
                LOG.warning("Can't determine frame rate. Guessing 25 fps");
                this.timescale = 90000L;
                this.frametick = 3600;
                return;
            }
            this.timescale = this.firstSeqParameterSet.vuiParams.time_scale >> 1;
            this.frametick = this.firstSeqParameterSet.vuiParams.num_units_in_tick;
            if (this.timescale == 0 || this.frametick == 0) {
                Logger logger = LOG;
                logger.warning("vuiParams contain invalid values: time_scale: " + this.timescale + " and frame_tick: " + this.frametick + ". Setting frame rate to 25fps");
                this.timescale = 90000L;
                this.frametick = 3600;
            }
            if (this.timescale / this.frametick > 100) {
                Logger logger2 = LOG;
                logger2.warning("Framerate is " + (this.timescale / this.frametick) + ". That is suspicious.");
            }
        }
    }

    private void createSample(List<ByteBuffer> list) {
        int i;
        SampleDependencyTypeBox.Entry entry = new SampleDependencyTypeBox.Entry(0);
        H264NalUnitHeader h264NalUnitHeader = null;
        boolean z = false;
        for (ByteBuffer byteBuffer : list) {
            H264NalUnitHeader nalUnitHeader = getNalUnitHeader(byteBuffer);
            switch (nalUnitHeader.nal_unit_type) {
                case 5:
                    z = true;
                    break;
            }
            h264NalUnitHeader = nalUnitHeader;
        }
        if (h264NalUnitHeader == null) {
            LOG.warning("Sample without Slice");
            return;
        }
        if (z) {
            calcCtts();
        }
        SliceHeader sliceHeader = new SliceHeader(cleanBuffer(new ByteBufferBackedInputStream(list.get(list.size() - 1))), this.spsIdToSps, this.ppsIdToPps, z);
        if (h264NalUnitHeader.nal_ref_idc == 0) {
            entry.setSampleIsDependentOn(2);
        } else {
            entry.setSampleIsDependentOn(1);
        }
        if (sliceHeader.slice_type == SliceHeader.SliceType.I || sliceHeader.slice_type == SliceHeader.SliceType.SI) {
            entry.setSampleDependsOn(2);
        } else {
            entry.setSampleDependsOn(1);
        }
        Sample createSampleObject = createSampleObject(list);
        list.clear();
        SEIMessage sEIMessage = this.seiMessage;
        if (sEIMessage == null || sEIMessage.n_frames == 0) {
            this.frameNrInGop = 0;
        }
        if (sliceHeader.sps.pic_order_cnt_type == 0) {
            int i2 = 1 << (sliceHeader.sps.log2_max_pic_order_cnt_lsb_minus4 + 4);
            int i3 = sliceHeader.pic_order_cnt_lsb;
            int i4 = this.prevPicOrderCntLsb;
            if (i3 >= i4 || i4 - i3 < i2 / 2) {
                int i5 = this.prevPicOrderCntLsb;
                i = (i3 <= i5 || i3 - i5 <= i2 / 2) ? this.prevPicOrderCntMsb : this.prevPicOrderCntMsb - i2;
            } else {
                i = this.prevPicOrderCntMsb + i2;
            }
            this.pictureOrderCounts = Mp4Arrays.copyOfAndAppend(this.pictureOrderCounts, i + i3);
            this.prevPicOrderCntLsb = i3;
            this.prevPicOrderCntMsb = i;
        } else if (sliceHeader.sps.pic_order_cnt_type == 1) {
            throw new RuntimeException("pic_order_cnt_type == 1 needs to be implemented");
        } else {
            if (sliceHeader.sps.pic_order_cnt_type == 2) {
                this.pictureOrderCounts = Mp4Arrays.copyOfAndAppend(this.pictureOrderCounts, this.samples.size());
            }
        }
        this.sdtp.add(entry);
        this.frameNrInGop++;
        this.samples.add(createSampleObject);
        if (z) {
            this.stss.add(Integer.valueOf(this.samples.size()));
        }
    }

    public static H264NalUnitHeader getNalUnitHeader(ByteBuffer byteBuffer) {
        H264NalUnitHeader h264NalUnitHeader = new H264NalUnitHeader();
        byte b = byteBuffer.get(0);
        h264NalUnitHeader.nal_ref_idc = (b >> 5) & 3;
        h264NalUnitHeader.nal_unit_type = b & 31;
        return h264NalUnitHeader;
    }

    private void handlePPS(ByteBuffer byteBuffer) {
        ByteBufferBackedInputStream byteBufferBackedInputStream = new ByteBufferBackedInputStream(byteBuffer);
        byteBufferBackedInputStream.read();
        PictureParameterSet read = PictureParameterSet.read(byteBufferBackedInputStream);
        if (this.firstPictureParameterSet == null) {
            this.firstPictureParameterSet = read;
        }
        this.currentPictureParameterSet = read;
        byte[] array = toArray((ByteBuffer) byteBuffer.rewind());
        byte[] bArr = this.ppsIdToPpsBytes.get(Integer.valueOf(read.pic_parameter_set_id));
        if (bArr != null && !Arrays.equals(bArr, array)) {
            throw new RuntimeException("OMG - I got two SPS with same ID but different settings! (AVC3 is the solution)");
        }
        if (bArr == null) {
            this.pictureParameterRangeMap.put((RangeStartMap<Integer, byte[]>) Integer.valueOf(this.samples.size()), (Integer) array);
        }
        this.ppsIdToPpsBytes.put(Integer.valueOf(read.pic_parameter_set_id), array);
        this.ppsIdToPps.put(Integer.valueOf(read.pic_parameter_set_id), read);
    }

    private void handleSPS(ByteBuffer byteBuffer) {
        InputStream cleanBuffer = cleanBuffer(new ByteBufferBackedInputStream(byteBuffer));
        cleanBuffer.read();
        SeqParameterSet read = SeqParameterSet.read(cleanBuffer);
        if (this.firstSeqParameterSet == null) {
            this.firstSeqParameterSet = read;
            configureFramerate();
        }
        this.currentSeqParameterSet = read;
        byte[] array = toArray((ByteBuffer) byteBuffer.rewind());
        byte[] bArr = this.spsIdToSpsBytes.get(Integer.valueOf(read.seq_parameter_set_id));
        if (bArr != null && !Arrays.equals(bArr, array)) {
            throw new RuntimeException("OMG - I got two SPS with same ID but different settings!");
        }
        if (bArr != null) {
            this.seqParameterRangeMap.put((RangeStartMap<Integer, byte[]>) Integer.valueOf(this.samples.size()), (Integer) array);
        }
        this.spsIdToSpsBytes.put(Integer.valueOf(read.seq_parameter_set_id), array);
        this.spsIdToSps.put(Integer.valueOf(read.seq_parameter_set_id), read);
    }

    private void parse(AbstractH26XTrack.LookAhead lookAhead) {
        this.samples = new ArrayList();
        if (!readSamples(lookAhead)) {
            throw new IOException();
        }
        if (!readVariables()) {
            throw new IOException();
        }
        this.sampleDescriptionBox = new SampleDescriptionBox();
        VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.TYPE3);
        visualSampleEntry.setDataReferenceIndex(1);
        visualSampleEntry.setDepth(24);
        visualSampleEntry.setFrameCount(1);
        visualSampleEntry.setHorizresolution(72.0d);
        visualSampleEntry.setVertresolution(72.0d);
        visualSampleEntry.setWidth(this.width);
        visualSampleEntry.setHeight(this.height);
        visualSampleEntry.setCompressorname("AVC Coding");
        AvcConfigurationBox avcConfigurationBox = new AvcConfigurationBox();
        avcConfigurationBox.setSequenceParameterSets(new ArrayList(this.spsIdToSpsBytes.values()));
        avcConfigurationBox.setPictureParameterSets(new ArrayList(this.ppsIdToPpsBytes.values()));
        avcConfigurationBox.setAvcLevelIndication(this.firstSeqParameterSet.level_idc);
        avcConfigurationBox.setAvcProfileIndication(this.firstSeqParameterSet.profile_idc);
        avcConfigurationBox.setBitDepthLumaMinus8(this.firstSeqParameterSet.bit_depth_luma_minus8);
        avcConfigurationBox.setBitDepthChromaMinus8(this.firstSeqParameterSet.bit_depth_chroma_minus8);
        avcConfigurationBox.setChromaFormat(this.firstSeqParameterSet.chroma_format_idc.getId());
        avcConfigurationBox.setConfigurationVersion(1);
        avcConfigurationBox.setLengthSizeMinusOne(3);
        avcConfigurationBox.setProfileCompatibility((this.firstSeqParameterSet.constraint_set_0_flag ? 128 : 0) + (this.firstSeqParameterSet.constraint_set_1_flag ? 64 : 0) + (this.firstSeqParameterSet.constraint_set_2_flag ? 32 : 0) + (this.firstSeqParameterSet.constraint_set_3_flag ? 16 : 0) + (this.firstSeqParameterSet.constraint_set_4_flag ? 8 : 0) + ((int) (this.firstSeqParameterSet.reserved_zero_2bits & 3)));
        visualSampleEntry.addBox(avcConfigurationBox);
        this.sampleDescriptionBox.addBox(visualSampleEntry);
        this.trackMetaData.setCreationTime(new Date());
        this.trackMetaData.setModificationTime(new Date());
        this.trackMetaData.setLanguage(this.lang);
        this.trackMetaData.setTimescale(this.timescale);
        this.trackMetaData.setWidth(this.width);
        this.trackMetaData.setHeight(this.height);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [szcom.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl$1FirstVclNalDetector] */
    private boolean readSamples(AbstractH26XTrack.LookAhead lookAhead) {
        ArrayList arrayList = new ArrayList();
        C1FirstVclNalDetector c1FirstVclNalDetector = 0;
        while (true) {
            ByteBuffer findNextNal = findNextNal(lookAhead);
            if (findNextNal != null) {
                H264NalUnitHeader nalUnitHeader = getNalUnitHeader(findNextNal);
                switch (nalUnitHeader.nal_unit_type) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        ?? r5 = new Object(findNextNal, nalUnitHeader.nal_ref_idc, nalUnitHeader.nal_unit_type) { // from class: szcom.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl.1FirstVclNalDetector
                            boolean bottom_field_flag;
                            int delta_pic_order_cnt_0;
                            int delta_pic_order_cnt_1;
                            int delta_pic_order_cnt_bottom;
                            boolean field_pic_flag;
                            int frame_num;
                            boolean idrPicFlag;
                            int idr_pic_id;
                            int nal_ref_idc;
                            int pic_order_cnt_lsb;
                            int pic_order_cnt_type;
                            int pic_parameter_set_id;

                            {
                                SliceHeader sliceHeader = new SliceHeader(H264TrackImpl.cleanBuffer(new ByteBufferBackedInputStream(findNextNal)), H264TrackImpl.this.spsIdToSps, H264TrackImpl.this.ppsIdToPps, r8 == 5);
                                this.frame_num = sliceHeader.frame_num;
                                this.pic_parameter_set_id = sliceHeader.pic_parameter_set_id;
                                this.field_pic_flag = sliceHeader.field_pic_flag;
                                this.bottom_field_flag = sliceHeader.bottom_field_flag;
                                this.nal_ref_idc = r7;
                                this.pic_order_cnt_type = H264TrackImpl.this.spsIdToSps.get(Integer.valueOf(H264TrackImpl.this.ppsIdToPps.get(Integer.valueOf(sliceHeader.pic_parameter_set_id)).seq_parameter_set_id)).pic_order_cnt_type;
                                this.delta_pic_order_cnt_bottom = sliceHeader.delta_pic_order_cnt_bottom;
                                this.pic_order_cnt_lsb = sliceHeader.pic_order_cnt_lsb;
                                this.delta_pic_order_cnt_0 = sliceHeader.delta_pic_order_cnt_0;
                                this.delta_pic_order_cnt_1 = sliceHeader.delta_pic_order_cnt_1;
                                this.idr_pic_id = sliceHeader.idr_pic_id;
                            }

                            boolean isFirstInNew(C1FirstVclNalDetector c1FirstVclNalDetector2) {
                                boolean z;
                                boolean z2;
                                boolean z3;
                                if (c1FirstVclNalDetector2.frame_num == this.frame_num && c1FirstVclNalDetector2.pic_parameter_set_id == this.pic_parameter_set_id && (z = c1FirstVclNalDetector2.field_pic_flag) == this.field_pic_flag) {
                                    if ((!z || c1FirstVclNalDetector2.bottom_field_flag == this.bottom_field_flag) && c1FirstVclNalDetector2.nal_ref_idc == this.nal_ref_idc) {
                                        if (c1FirstVclNalDetector2.pic_order_cnt_type == 0 && this.pic_order_cnt_type == 0 && (c1FirstVclNalDetector2.pic_order_cnt_lsb != this.pic_order_cnt_lsb || c1FirstVclNalDetector2.delta_pic_order_cnt_bottom != this.delta_pic_order_cnt_bottom)) {
                                            return true;
                                        }
                                        if (!(c1FirstVclNalDetector2.pic_order_cnt_type == 1 && this.pic_order_cnt_type == 1 && (c1FirstVclNalDetector2.delta_pic_order_cnt_0 != this.delta_pic_order_cnt_0 || c1FirstVclNalDetector2.delta_pic_order_cnt_1 != this.delta_pic_order_cnt_1)) && (z2 = c1FirstVclNalDetector2.idrPicFlag) == (z3 = this.idrPicFlag)) {
                                            return z2 && z3 && c1FirstVclNalDetector2.idr_pic_id != this.idr_pic_id;
                                        }
                                        return true;
                                    }
                                    return true;
                                }
                                return true;
                            }
                        };
                        if (c1FirstVclNalDetector != 0 && c1FirstVclNalDetector.isFirstInNew(r5)) {
                            LOG.finer("Wrapping up cause of first vcl nal is found");
                            createSample(arrayList);
                        }
                        arrayList.add((ByteBuffer) findNextNal.rewind());
                        c1FirstVclNalDetector = r5;
                        continue;
                        break;
                    case 6:
                        if (c1FirstVclNalDetector != 0) {
                            LOG.finer("Wrapping up cause of SEI after vcl marks new sample");
                            createSample(arrayList);
                            c1FirstVclNalDetector = 0;
                        }
                        this.seiMessage = new SEIMessage(cleanBuffer(new ByteBufferBackedInputStream(findNextNal)), this.currentSeqParameterSet);
                        break;
                    case 7:
                        if (c1FirstVclNalDetector != 0) {
                            LOG.finer("Wrapping up cause of SPS after vcl marks new sample");
                            createSample(arrayList);
                            c1FirstVclNalDetector = 0;
                        }
                        handleSPS((ByteBuffer) findNextNal.rewind());
                        continue;
                    case 8:
                        if (c1FirstVclNalDetector != 0) {
                            LOG.finer("Wrapping up cause of PPS after vcl marks new sample");
                            createSample(arrayList);
                            c1FirstVclNalDetector = 0;
                        }
                        handlePPS((ByteBuffer) findNextNal.rewind());
                        continue;
                    case 9:
                        if (c1FirstVclNalDetector != 0) {
                            LOG.finer("Wrapping up cause of AU after vcl marks new sample");
                            createSample(arrayList);
                            c1FirstVclNalDetector = 0;
                            break;
                        }
                        break;
                    case 10:
                    case 11:
                        break;
                    case 12:
                    default:
                        LOG.warning("Unknown NAL unit type: " + nalUnitHeader.nal_unit_type);
                        continue;
                    case 13:
                        throw new RuntimeException("Sequence parameter set extension is not yet handled. Needs TLC.");
                }
                arrayList.add(findNextNal);
            }
        }
        if (arrayList.size() > 0) {
            createSample(arrayList);
        }
        calcCtts();
        this.decodingTimes = new long[this.samples.size()];
        Arrays.fill(this.decodingTimes, this.frametick);
        return true;
    }

    private boolean readVariables() {
        int i;
        this.width = (this.firstSeqParameterSet.pic_width_in_mbs_minus1 + 1) * 16;
        int i2 = this.firstSeqParameterSet.frame_mbs_only_flag ? 1 : 2;
        this.height = (this.firstSeqParameterSet.pic_height_in_map_units_minus1 + 1) * 16 * i2;
        if (this.firstSeqParameterSet.frame_cropping_flag) {
            if ((this.firstSeqParameterSet.residual_color_transform_flag ? 0 : this.firstSeqParameterSet.chroma_format_idc.getId()) != 0) {
                i = this.firstSeqParameterSet.chroma_format_idc.getSubWidth();
                i2 *= this.firstSeqParameterSet.chroma_format_idc.getSubHeight();
            } else {
                i = 1;
            }
            this.width -= i * (this.firstSeqParameterSet.frame_crop_left_offset + this.firstSeqParameterSet.frame_crop_right_offset);
            this.height -= i2 * (this.firstSeqParameterSet.frame_crop_top_offset + this.firstSeqParameterSet.frame_crop_bottom_offset);
        }
        return true;
    }

    public void calcCtts() {
        int i = 0;
        int i2 = -1;
        int i3 = 0;
        while (i3 < this.pictureOrderCounts.length) {
            int i4 = Integer.MAX_VALUE;
            int i5 = 0;
            for (int max = Math.max(0, i3 - 128); max < Math.min(this.pictureOrderCounts.length, i3 + 128); max++) {
                int[] iArr = this.pictureOrderCounts;
                if (iArr[max] > i2 && iArr[max] < i4) {
                    i4 = iArr[max];
                    i5 = max;
                }
            }
            int[] iArr2 = this.pictureOrderCounts;
            int i6 = iArr2[i5];
            iArr2[i5] = i;
            i3++;
            i2 = i6;
            i++;
        }
        for (int i7 = 0; i7 < this.pictureOrderCounts.length; i7++) {
            this.ctts.add(new CompositionTimeToSample.Entry(1, this.pictureOrderCounts[i7] - i7));
        }
        this.pictureOrderCounts = new int[0];
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "vide";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }
}
