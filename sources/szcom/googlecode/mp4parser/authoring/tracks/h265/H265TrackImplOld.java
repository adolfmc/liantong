package szcom.googlecode.mp4parser.authoring.tracks.h265;

import java.io.EOFException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.FileDataSourceImpl;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.SampleImpl;
import szcom.googlecode.mp4parser.h264.read.CAVLCReader;
import szcom.googlecode.mp4parser.util.ByteBufferByteChannel;
import szcom.mp4parser.iso14496.part15.HevcDecoderConfigurationRecord;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class H265TrackImplOld {
    public static final int AUD_NUT = 35;
    private static final int BLA_N_LP = 18;
    private static final int BLA_W_LP = 16;
    private static final int BLA_W_RADL = 17;
    private static final long BUFFER = 1048576;
    private static final int CRA_NUT = 21;
    private static final int IDR_N_LP = 20;
    private static final int IDR_W_RADL = 19;
    public static final int PPS_NUT = 34;
    public static final int PREFIX_SEI_NUT = 39;
    private static final int RADL_N = 6;
    private static final int RADL_R = 7;
    private static final int RASL_N = 8;
    private static final int RASL_R = 9;
    public static final int RSV_NVCL41 = 41;
    public static final int RSV_NVCL42 = 42;
    public static final int RSV_NVCL43 = 43;
    public static final int RSV_NVCL44 = 44;
    public static final int SPS_NUT = 33;
    private static final int STSA_N = 4;
    private static final int STSA_R = 5;
    private static final int TRAIL_N = 0;
    private static final int TRAIL_R = 1;
    private static final int TSA_N = 2;
    private static final int TSA_R = 3;
    public static final int UNSPEC48 = 48;
    public static final int UNSPEC49 = 49;
    public static final int UNSPEC50 = 50;
    public static final int UNSPEC51 = 51;
    public static final int UNSPEC52 = 52;
    public static final int UNSPEC53 = 53;
    public static final int UNSPEC54 = 54;
    public static final int UNSPEC55 = 55;
    public static final int VPS_NUT = 32;
    LinkedHashMap<Long, ByteBuffer> videoParamterSets = new LinkedHashMap<>();
    LinkedHashMap<Long, ByteBuffer> sequenceParamterSets = new LinkedHashMap<>();
    LinkedHashMap<Long, ByteBuffer> pictureParamterSets = new LinkedHashMap<>();
    List<Long> syncSamples = new ArrayList();
    List<Sample> samples = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class LookAhead {
        ByteBuffer buffer;
        DataSource dataSource;
        long start;
        long bufferStartPos = 0;
        int inBufferPos = 0;

        LookAhead(DataSource dataSource) {
            this.dataSource = dataSource;
            fillBuffer();
        }

        void discardByte() {
            this.inBufferPos++;
        }

        void discardNext3AndMarkStart() {
            this.inBufferPos += 3;
            this.start = this.bufferStartPos + this.inBufferPos;
        }

        public void fillBuffer() {
            DataSource dataSource = this.dataSource;
            this.buffer = dataSource.map(this.bufferStartPos, Math.min(dataSource.size() - this.bufferStartPos, 1048576L));
        }

        public ByteBuffer getNal() {
            long j = this.start;
            long j2 = this.bufferStartPos;
            if (j >= j2) {
                this.buffer.position((int) (j - j2));
                ByteBuffer slice = this.buffer.slice();
                slice.limit((int) (this.inBufferPos - (this.start - this.bufferStartPos)));
                return slice;
            }
            throw new RuntimeException("damn! NAL exceeds buffer");
        }

        boolean nextThreeEquals000or001orEof() {
            int limit = this.buffer.limit();
            int i = this.inBufferPos;
            if (limit - i >= 3) {
                return this.buffer.get(i) == 0 && this.buffer.get(this.inBufferPos + 1) == 0 && (this.buffer.get(this.inBufferPos + 2) == 0 || this.buffer.get(this.inBufferPos + 2) == 1);
            } else if (this.bufferStartPos + i + 3 > this.dataSource.size()) {
                return this.bufferStartPos + ((long) this.inBufferPos) == this.dataSource.size();
            } else {
                this.bufferStartPos = this.start;
                this.inBufferPos = 0;
                fillBuffer();
                return nextThreeEquals000or001orEof();
            }
        }

        boolean nextThreeEquals001() {
            int limit = this.buffer.limit();
            int i = this.inBufferPos;
            if (limit - i >= 3) {
                return this.buffer.get(i) == 0 && this.buffer.get(this.inBufferPos + 1) == 0 && this.buffer.get(this.inBufferPos + 2) == 1;
            } else if (this.bufferStartPos + i == this.dataSource.size()) {
                throw new EOFException();
            } else {
                throw new RuntimeException("buffer repositioning require");
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class NalUnitHeader {
        int forbiddenZeroFlag;
        int nalUnitType;
        int nuhLayerId;
        int nuhTemporalIdPlusOne;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum PARSE_STATE {
        AUD_SEI_SLICE,
        SEI_SLICE,
        SLICE_OES_EOB;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static PARSE_STATE[] valuesCustom() {
            PARSE_STATE[] valuesCustom = values();
            int length = valuesCustom.length;
            PARSE_STATE[] parse_stateArr = new PARSE_STATE[length];
            System.arraycopy(valuesCustom, 0, parse_stateArr, 0, length);
            return parse_stateArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ab A[LOOP:1: B:22:0x0090->B:25:0x00ab, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ff A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public H265TrackImplOld(szcom.googlecode.mp4parser.DataSource r17) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: szcom.googlecode.mp4parser.authoring.tracks.h265.H265TrackImplOld.<init>(szcom.googlecode.mp4parser.DataSource):void");
    }

    private ByteBuffer findNextNal(LookAhead lookAhead) {
        while (!lookAhead.nextThreeEquals001()) {
            try {
                lookAhead.discardByte();
            } catch (EOFException unused) {
                return null;
            }
        }
        lookAhead.discardNext3AndMarkStart();
        while (!lookAhead.nextThreeEquals000or001orEof()) {
            lookAhead.discardByte();
        }
        return lookAhead.getNal();
    }

    private List<HevcDecoderConfigurationRecord.Array> getArrays() {
        HevcDecoderConfigurationRecord.Array array = new HevcDecoderConfigurationRecord.Array();
        array.array_completeness = true;
        array.nal_unit_type = 32;
        array.nalUnits = new ArrayList();
        for (ByteBuffer byteBuffer : this.videoParamterSets.values()) {
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.position(0);
            byteBuffer.get(bArr);
            array.nalUnits.add(bArr);
        }
        HevcDecoderConfigurationRecord.Array array2 = new HevcDecoderConfigurationRecord.Array();
        array2.array_completeness = true;
        array2.nal_unit_type = 33;
        array2.nalUnits = new ArrayList();
        for (ByteBuffer byteBuffer2 : this.sequenceParamterSets.values()) {
            byte[] bArr2 = new byte[byteBuffer2.limit()];
            byteBuffer2.position(0);
            byteBuffer2.get(bArr2);
            array2.nalUnits.add(bArr2);
        }
        HevcDecoderConfigurationRecord.Array array3 = new HevcDecoderConfigurationRecord.Array();
        array3.array_completeness = true;
        array3.nal_unit_type = 33;
        array3.nalUnits = new ArrayList();
        for (ByteBuffer byteBuffer3 : this.pictureParamterSets.values()) {
            byte[] bArr3 = new byte[byteBuffer3.limit()];
            byteBuffer3.position(0);
            byteBuffer3.get(bArr3);
            array3.nalUnits.add(bArr3);
        }
        return Arrays.asList(array, array2, array3);
    }

    private void hrd_parameters(boolean z, int i, CAVLCReader cAVLCReader) {
        boolean z2;
        boolean z3;
        boolean z4;
        if (z) {
            z2 = cAVLCReader.readBool("nal_hrd_parameters_present_flag");
            z3 = cAVLCReader.readBool("vcl_hrd_parameters_present_flag");
            if (z2 || z3) {
                z4 = cAVLCReader.readBool("sub_pic_hrd_params_present_flag");
                if (z4) {
                    cAVLCReader.readU(8, "tick_divisor_minus2");
                    cAVLCReader.readU(5, "du_cpb_removal_delay_increment_length_minus1");
                    cAVLCReader.readBool("sub_pic_cpb_params_in_pic_timing_sei_flag");
                    cAVLCReader.readU(5, "dpb_output_delay_du_length_minus1");
                }
                cAVLCReader.readU(4, "bit_rate_scale");
                cAVLCReader.readU(4, "cpb_size_scale");
                if (z4) {
                    cAVLCReader.readU(4, "cpb_size_du_scale");
                }
                cAVLCReader.readU(5, "initial_cpb_removal_delay_length_minus1");
                cAVLCReader.readU(5, "au_cpb_removal_delay_length_minus1");
                cAVLCReader.readU(5, "dpb_output_delay_length_minus1");
            } else {
                z4 = false;
            }
        } else {
            z2 = false;
            z3 = false;
            z4 = false;
        }
        boolean[] zArr = new boolean[i];
        boolean[] zArr2 = new boolean[i];
        boolean[] zArr3 = new boolean[i];
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        for (int i2 = 0; i2 <= i; i2++) {
            zArr[i2] = cAVLCReader.readBool("fixed_pic_rate_general_flag[" + i2 + "]");
            if (!zArr[i2]) {
                zArr2[i2] = cAVLCReader.readBool("fixed_pic_rate_within_cvs_flag[" + i2 + "]");
            }
            if (zArr2[i2]) {
                iArr2[i2] = cAVLCReader.readUE("elemental_duration_in_tc_minus1[" + i2 + "]");
            } else {
                zArr3[i2] = cAVLCReader.readBool("low_delay_hrd_flag[" + i2 + "]");
            }
            if (!zArr3[i2]) {
                iArr[i2] = cAVLCReader.readUE("cpb_cnt_minus1[" + i2 + "]");
            }
            if (z2) {
                sub_layer_hrd_parameters(i2, iArr[i2], z4, cAVLCReader);
            }
            if (z3) {
                sub_layer_hrd_parameters(i2, iArr[i2], z4, cAVLCReader);
            }
        }
    }

    public static void main(String[] strArr) {
        new H265TrackImplOld(new FileDataSourceImpl("c:\\content\\test-UHD-HEVC_01_FMV_Med_track1.hvc"));
    }

    protected Sample createSample(List<ByteBuffer> list) {
        byte[] bArr = new byte[list.size() * 4];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        for (ByteBuffer byteBuffer : list) {
            wrap.putInt(byteBuffer.remaining());
        }
        ByteBuffer[] byteBufferArr = new ByteBuffer[list.size() * 2];
        for (int i = 0; i < list.size(); i++) {
            int i2 = i * 2;
            byteBufferArr[i2] = ByteBuffer.wrap(bArr, i * 4, 4);
            byteBufferArr[i2 + 1] = list.get(i);
        }
        return new SampleImpl(byteBufferArr);
    }

    public int getFrameRate(ByteBuffer byteBuffer) {
        CAVLCReader cAVLCReader = new CAVLCReader(Channels.newInputStream(new ByteBufferByteChannel((ByteBuffer) byteBuffer.position(0))));
        cAVLCReader.readU(4, "vps_parameter_set_id");
        cAVLCReader.readU(2, "vps_reserved_three_2bits");
        cAVLCReader.readU(6, "vps_max_layers_minus1");
        int readU = cAVLCReader.readU(3, "vps_max_sub_layers_minus1");
        cAVLCReader.readBool("vps_temporal_id_nesting_flag");
        cAVLCReader.readU(16, "vps_reserved_0xffff_16bits");
        profile_tier_level(readU, cAVLCReader);
        boolean readBool = cAVLCReader.readBool("vps_sub_layer_ordering_info_present_flag");
        int[] iArr = new int[readBool ? 0 : readU];
        int[] iArr2 = new int[readBool ? 0 : readU];
        int[] iArr3 = new int[readBool ? 0 : readU];
        for (int i = readBool ? 0 : readU; i <= readU; i++) {
            iArr[i] = cAVLCReader.readUE("vps_max_dec_pic_buffering_minus1[" + i + "]");
            iArr2[i] = cAVLCReader.readUE("vps_max_dec_pic_buffering_minus1[" + i + "]");
            iArr3[i] = cAVLCReader.readUE("vps_max_dec_pic_buffering_minus1[" + i + "]");
        }
        int readU2 = cAVLCReader.readU(6, "vps_max_layer_id");
        int readUE = cAVLCReader.readUE("vps_num_layer_sets_minus1");
        boolean[][] zArr = (boolean[][]) Array.newInstance(boolean.class, readUE, readU2);
        for (int i2 = 1; i2 <= readUE; i2++) {
            for (int i3 = 0; i3 <= readU2; i3++) {
                zArr[i2][i3] = cAVLCReader.readBool("layer_id_included_flag[" + i2 + "][" + i3 + "]");
            }
        }
        if (cAVLCReader.readBool("vps_timing_info_present_flag")) {
            cAVLCReader.readU(32, "vps_num_units_in_tick");
            cAVLCReader.readU(32, "vps_time_scale");
            if (cAVLCReader.readBool("vps_poc_proportional_to_timing_flag")) {
                cAVLCReader.readUE("vps_num_ticks_poc_diff_one_minus1");
            }
            int readUE2 = cAVLCReader.readUE("vps_num_hrd_parameters");
            int[] iArr4 = new int[readUE2];
            boolean[] zArr2 = new boolean[readUE2];
            for (int i4 = 0; i4 < readUE2; i4++) {
                iArr4[i4] = cAVLCReader.readUE("hrd_layer_set_idx[" + i4 + "]");
                if (i4 > 0) {
                    zArr2[i4] = cAVLCReader.readBool("cprms_present_flag[" + i4 + "]");
                } else {
                    zArr2[0] = true;
                }
                hrd_parameters(zArr2[i4], readU, cAVLCReader);
            }
        }
        if (cAVLCReader.readBool("vps_extension_flag")) {
            while (cAVLCReader.moreRBSPData()) {
                cAVLCReader.readBool("vps_extension_data_flag");
            }
        }
        cAVLCReader.readTrailingBits();
        return 0;
    }

    public NalUnitHeader getNalUnitHeader(ByteBuffer byteBuffer) {
        byteBuffer.position(0);
        int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
        NalUnitHeader nalUnitHeader = new NalUnitHeader();
        nalUnitHeader.forbiddenZeroFlag = (32768 & readUInt16) >> 15;
        nalUnitHeader.nalUnitType = (readUInt16 & 32256) >> 9;
        nalUnitHeader.nuhLayerId = (readUInt16 & 504) >> 3;
        nalUnitHeader.nuhTemporalIdPlusOne = readUInt16 & 7;
        return nalUnitHeader;
    }

    boolean isFirstOfAU(int i, ByteBuffer byteBuffer, List<ByteBuffer> list) {
        if (list.isEmpty()) {
            return true;
        }
        boolean z = getNalUnitHeader(list.get(list.size() - 1)).nalUnitType <= 31;
        switch (i) {
            case 32:
            case 33:
            case 34:
            case 35:
            case 39:
            case 41:
            case 42:
            case 43:
            case 44:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                if (z) {
                    return true;
                }
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                break;
            default:
                switch (i) {
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                        break;
                    default:
                        return false;
                }
        }
        byteBuffer.position(0);
        byteBuffer.get(new byte[50]);
        byteBuffer.position(2);
        return z && (IsoTypeReader.readUInt8(byteBuffer) & 128) > 0;
    }

    public void profile_tier_level(int i, CAVLCReader cAVLCReader) {
        int i2 = i;
        int i3 = 2;
        cAVLCReader.readU(2, "general_profile_space ");
        cAVLCReader.readBool("general_tier_flag");
        cAVLCReader.readU(5, "general_profile_idc");
        int i4 = 32;
        boolean[] zArr = new boolean[32];
        int i5 = 0;
        while (i5 < i4) {
            zArr[i5] = cAVLCReader.readBool("general_profile_compatibility_flag[" + i5 + "]");
            i5++;
            i2 = i;
            i4 = 32;
            i3 = 2;
        }
        cAVLCReader.readBool("general_progressive_source_flag");
        cAVLCReader.readBool("general_interlaced_source_flag");
        cAVLCReader.readBool("general_non_packed_constraint_flag");
        cAVLCReader.readBool("general_frame_only_constraint_flag");
        cAVLCReader.readU(44, "general_reserved_zero_44bits");
        cAVLCReader.readU(8, "general_level_idc");
        boolean[] zArr2 = new boolean[i2];
        boolean[] zArr3 = new boolean[i2];
        int i6 = 0;
        while (i6 < i2) {
            zArr2[i6] = cAVLCReader.readBool("sub_layer_profile_present_flag[" + i6 + "]");
            zArr3[i6] = cAVLCReader.readBool("sub_layer_level_present_flag[" + i6 + "]");
            i6++;
            i2 = i;
            i4 = 32;
            i3 = 2;
        }
        if (i2 > 0) {
            for (int i7 = i2; i7 < 8; i7++) {
                cAVLCReader.readU(i3, "reserved_zero_2bits");
            }
        }
        int[] iArr = new int[i2];
        boolean[] zArr4 = new boolean[i2];
        int[] iArr2 = new int[i2];
        boolean[][] zArr5 = (boolean[][]) Array.newInstance(boolean.class, i2, i4);
        boolean[] zArr6 = new boolean[i2];
        boolean[] zArr7 = new boolean[i2];
        boolean[] zArr8 = new boolean[i2];
        boolean[] zArr9 = new boolean[i2];
        int[] iArr3 = new int[i2];
        int i8 = 0;
        while (i8 < i2) {
            if (zArr2[i8]) {
                iArr[i8] = cAVLCReader.readU(2, "sub_layer_profile_space[" + i8 + "]");
                zArr4[i8] = cAVLCReader.readBool("sub_layer_tier_flag[" + i8 + "]");
                iArr2[i8] = cAVLCReader.readU(5, "sub_layer_profile_idc[" + i8 + "]");
                for (int i9 = 0; i9 < 32; i9++) {
                    boolean[] zArr10 = zArr5[i8];
                    zArr10[i9] = cAVLCReader.readBool("sub_layer_profile_compatibility_flag[" + i8 + "][" + i9 + "]");
                }
                zArr6[i8] = cAVLCReader.readBool("sub_layer_progressive_source_flag[" + i8 + "]");
                zArr7[i8] = cAVLCReader.readBool("sub_layer_interlaced_source_flag[" + i8 + "]");
                zArr8[i8] = cAVLCReader.readBool("sub_layer_non_packed_constraint_flag[" + i8 + "]");
                zArr9[i8] = cAVLCReader.readBool("sub_layer_frame_only_constraint_flag[" + i8 + "]");
                cAVLCReader.readNBit(44, "reserved");
            }
            if (zArr3[i8]) {
                iArr3[i8] = cAVLCReader.readU(8, "sub_layer_level_idc");
            }
            i8++;
            i2 = i;
        }
    }

    void sub_layer_hrd_parameters(int i, int i2, boolean z, CAVLCReader cAVLCReader) {
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        int[] iArr4 = new int[i2];
        boolean[] zArr = new boolean[i2];
        for (int i3 = 0; i3 <= i2; i3++) {
            iArr[i3] = cAVLCReader.readUE("bit_rate_value_minus1[" + i3 + "]");
            iArr2[i3] = cAVLCReader.readUE("cpb_size_value_minus1[" + i3 + "]");
            if (z) {
                iArr3[i3] = cAVLCReader.readUE("cpb_size_du_value_minus1[" + i3 + "]");
                iArr4[i3] = cAVLCReader.readUE("bit_rate_du_value_minus1[" + i3 + "]");
            }
            zArr[i3] = cAVLCReader.readBool("cbr_flag[" + i3 + "]");
        }
    }
}
