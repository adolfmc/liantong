package szcom.googlecode.mp4parser.authoring.tracks.h265;

import java.io.InputStream;
import java.lang.reflect.Array;
import szcom.googlecode.mp4parser.h264.read.CAVLCReader;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SequenceParameterSetRbsp {
    public SequenceParameterSetRbsp(InputStream inputStream) {
        CAVLCReader cAVLCReader = new CAVLCReader(inputStream);
        cAVLCReader.readNBit(4, "sps_video_parameter_set_id");
        int readNBit = (int) cAVLCReader.readNBit(3, "sps_max_sub_layers_minus1");
        cAVLCReader.readBool("sps_temporal_id_nesting_flag");
        profile_tier_level(readNBit, cAVLCReader);
        cAVLCReader.readUE("sps_seq_parameter_set_id");
        if (cAVLCReader.readUE("chroma_format_idc") == 3) {
            cAVLCReader.read1Bit();
            cAVLCReader.readUE("pic_width_in_luma_samples");
            cAVLCReader.readUE("pic_width_in_luma_samples");
            if (cAVLCReader.readBool("conformance_window_flag")) {
                cAVLCReader.readUE("conf_win_left_offset");
                cAVLCReader.readUE("conf_win_right_offset");
                cAVLCReader.readUE("conf_win_top_offset");
                cAVLCReader.readUE("conf_win_bottom_offset");
            }
        }
        cAVLCReader.readUE("bit_depth_luma_minus8");
        cAVLCReader.readUE("bit_depth_chroma_minus8");
        cAVLCReader.readUE("log2_max_pic_order_cnt_lsb_minus4");
        boolean readBool = cAVLCReader.readBool("sps_sub_layer_ordering_info_present_flag");
        int i = (readNBit - (readBool ? 0 : readNBit)) + 1;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        int[] iArr3 = new int[i];
        for (int i2 = readBool ? 0 : readNBit; i2 <= readNBit; i2++) {
            iArr[i2] = cAVLCReader.readUE("sps_max_dec_pic_buffering_minus1[" + i2 + "]");
            iArr2[i2] = cAVLCReader.readUE("sps_max_num_reorder_pics[" + i2 + "]");
            iArr3[i2] = cAVLCReader.readUE("sps_max_latency_increase_plus1[" + i2 + "]");
        }
        cAVLCReader.readUE("log2_min_luma_coding_block_size_minus3");
        cAVLCReader.readUE("log2_diff_max_min_luma_coding_block_size");
        cAVLCReader.readUE("log2_min_transform_block_size_minus2");
        cAVLCReader.readUE("log2_diff_max_min_transform_block_size");
        cAVLCReader.readUE("max_transform_hierarchy_depth_inter");
        cAVLCReader.readUE("max_transform_hierarchy_depth_intra");
        if (cAVLCReader.readBool("scaling_list_enabled_flag") && cAVLCReader.readBool("sps_scaling_list_data_present_flag")) {
            scaling_list_data(cAVLCReader);
        }
        cAVLCReader.readBool("amp_enabled_flag");
        cAVLCReader.readBool("sample_adaptive_offset_enabled_flag");
        if (cAVLCReader.readBool("pcm_enabled_flag")) {
            cAVLCReader.readNBit(4, "pcm_sample_bit_depth_luma_minus1");
            cAVLCReader.readNBit(4, "pcm_sample_bit_depth_chroma_minus1");
            cAVLCReader.readUE("log2_min_pcm_luma_coding_block_size_minus3");
        }
    }

    private void profile_tier_level(int i, CAVLCReader cAVLCReader) {
        int i2 = i;
        int i3 = 2;
        cAVLCReader.readU(2, "general_profile_space");
        cAVLCReader.readBool("general_tier_flag");
        cAVLCReader.readU(5, "general_profile_idc");
        int i4 = 32;
        boolean[] zArr = new boolean[32];
        int i5 = 0;
        while (i5 < i4) {
            zArr[i5] = cAVLCReader.readBool();
            i5++;
            i2 = i;
            i4 = 32;
            i3 = 2;
        }
        cAVLCReader.readBool("general_progressive_source_flag");
        cAVLCReader.readBool("general_interlaced_source_flag");
        cAVLCReader.readBool("general_non_packed_constraint_flag");
        cAVLCReader.readBool("general_frame_only_constraint_flag");
        cAVLCReader.readNBit(44, "general_reserved_zero_44bits");
        cAVLCReader.readByte();
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
            int[] iArr = new int[8];
            for (int i7 = i2; i7 < 8; i7++) {
                iArr[i7] = cAVLCReader.readU(i3, "reserved_zero_2bits[" + i7 + "]");
            }
        }
        int[] iArr2 = new int[i2];
        boolean[] zArr4 = new boolean[i2];
        int[] iArr3 = new int[i2];
        boolean[][] zArr5 = (boolean[][]) Array.newInstance(boolean.class, i2, i4);
        boolean[] zArr6 = new boolean[i2];
        boolean[] zArr7 = new boolean[i2];
        boolean[] zArr8 = new boolean[i2];
        boolean[] zArr9 = new boolean[i2];
        long[] jArr = new long[i2];
        int[] iArr4 = new int[i2];
        int i8 = 0;
        while (i8 < i2) {
            if (zArr2[i8]) {
                iArr2[i8] = cAVLCReader.readU(2, "sub_layer_profile_space[" + i8 + "]");
                zArr4[i8] = cAVLCReader.readBool("sub_layer_tier_flag[" + i8 + "]");
                iArr3[i8] = cAVLCReader.readU(5, "sub_layer_profile_idc[" + i8 + "]");
                int i9 = 0;
                while (i9 < 32) {
                    boolean[] zArr10 = zArr5[i8];
                    zArr10[i9] = cAVLCReader.readBool("sub_layer_profile_compatibility_flag[" + i8 + "][" + i9 + "]");
                    i9++;
                    zArr7 = zArr7;
                }
                zArr6[i8] = cAVLCReader.readBool("sub_layer_progressive_source_flag[" + i8 + "]");
                zArr7[i8] = cAVLCReader.readBool("sub_layer_interlaced_source_flag[" + i8 + "]");
                zArr8[i8] = cAVLCReader.readBool("sub_layer_non_packed_constraint_flag[" + i8 + "]");
                zArr9[i8] = cAVLCReader.readBool("sub_layer_frame_only_constraint_flag[" + i8 + "]");
                jArr[i8] = cAVLCReader.readNBit(44);
            }
            boolean[] zArr11 = zArr7;
            if (zArr3[i8]) {
                iArr4[i8] = cAVLCReader.readU(8, "sub_layer_level_idc[" + i8 + "]");
            }
            i8++;
            zArr7 = zArr11;
            i2 = i;
        }
    }

    private void scaling_list_data(CAVLCReader cAVLCReader) {
        boolean[][] zArr = new boolean[4];
        int[][] iArr = new int[4];
        int[][] iArr2 = new int[2];
        int[][][] iArr3 = new int[4][];
        int i = 0;
        while (i < 4) {
            int i2 = 0;
            while (true) {
                if (i2 >= (i == 3 ? 2 : 6)) {
                    break;
                }
                zArr[i] = new boolean[i == 3 ? 2 : 6];
                iArr[i] = new int[i == 3 ? 2 : 6];
                iArr3[i] = new int[i == 3 ? 2 : 6];
                zArr[i][i2] = cAVLCReader.readBool();
                if (zArr[i][i2]) {
                    int min = Math.min(64, 1 << ((i << 1) + 4));
                    int i3 = 8;
                    if (i > 1) {
                        int i4 = i - 2;
                        iArr2[i4][i2] = cAVLCReader.readSE("scaling_list_dc_coef_minus8[" + i + "- 2][" + i2 + "]");
                        i3 = 8 + iArr2[i4][i2];
                    }
                    iArr3[i][i2] = new int[min];
                    int i5 = i3;
                    for (int i6 = 0; i6 < min; i6++) {
                        i5 = ((i5 + cAVLCReader.readSE("scaling_list_delta_coef ")) + 256) % 256;
                        iArr3[i][i2][i6] = i5;
                    }
                } else {
                    iArr[i][i2] = cAVLCReader.readUE("scaling_list_pred_matrix_id_delta[" + i + "][" + i2 + "]");
                }
                i2++;
            }
            i++;
        }
    }
}
