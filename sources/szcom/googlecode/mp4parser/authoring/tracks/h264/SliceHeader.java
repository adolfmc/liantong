package szcom.googlecode.mp4parser.authoring.tracks.h264;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import szcom.googlecode.mp4parser.h264.model.PictureParameterSet;
import szcom.googlecode.mp4parser.h264.model.SeqParameterSet;
import szcom.googlecode.mp4parser.h264.read.CAVLCReader;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SliceHeader {
    public boolean bottom_field_flag;
    public int colour_plane_id;
    public int delta_pic_order_cnt_0;
    public int delta_pic_order_cnt_1;
    public int delta_pic_order_cnt_bottom;
    public boolean field_pic_flag;
    public int first_mb_in_slice;
    public int frame_num;
    public int idr_pic_id;
    public int pic_order_cnt_lsb;
    public int pic_parameter_set_id;
    PictureParameterSet pps;
    public SliceType slice_type;
    SeqParameterSet sps;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum SliceType {
        P,
        B,
        I,
        SP,
        SI;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static SliceType[] valuesCustom() {
            SliceType[] valuesCustom = values();
            int length = valuesCustom.length;
            SliceType[] sliceTypeArr = new SliceType[length];
            System.arraycopy(valuesCustom, 0, sliceTypeArr, 0, length);
            return sliceTypeArr;
        }
    }

    public SliceHeader(InputStream inputStream, Map<Integer, SeqParameterSet> map, Map<Integer, PictureParameterSet> map2, boolean z) {
        SliceType sliceType;
        this.field_pic_flag = false;
        this.bottom_field_flag = false;
        try {
            inputStream.read();
            CAVLCReader cAVLCReader = new CAVLCReader(inputStream);
            this.first_mb_in_slice = cAVLCReader.readUE("SliceHeader: first_mb_in_slice");
            switch (cAVLCReader.readUE("SliceHeader: slice_type")) {
                case 0:
                case 5:
                    sliceType = SliceType.P;
                    this.slice_type = sliceType;
                    break;
                case 1:
                case 6:
                    sliceType = SliceType.B;
                    this.slice_type = sliceType;
                    break;
                case 2:
                case 7:
                    sliceType = SliceType.I;
                    this.slice_type = sliceType;
                    break;
                case 3:
                case 8:
                    sliceType = SliceType.SP;
                    this.slice_type = sliceType;
                    break;
                case 4:
                case 9:
                    sliceType = SliceType.SI;
                    this.slice_type = sliceType;
                    break;
            }
            this.pic_parameter_set_id = cAVLCReader.readUE("SliceHeader: pic_parameter_set_id");
            this.pps = map2.get(Integer.valueOf(this.pic_parameter_set_id));
            this.sps = map.get(Integer.valueOf(this.pps.seq_parameter_set_id));
            if (this.sps.residual_color_transform_flag) {
                this.colour_plane_id = cAVLCReader.readU(2, "SliceHeader: colour_plane_id");
            }
            this.frame_num = cAVLCReader.readU(this.sps.log2_max_frame_num_minus4 + 4, "SliceHeader: frame_num");
            if (!this.sps.frame_mbs_only_flag) {
                this.field_pic_flag = cAVLCReader.readBool("SliceHeader: field_pic_flag");
                if (this.field_pic_flag) {
                    this.bottom_field_flag = cAVLCReader.readBool("SliceHeader: bottom_field_flag");
                }
            }
            if (z) {
                this.idr_pic_id = cAVLCReader.readUE("SliceHeader: idr_pic_id");
            }
            if (this.sps.pic_order_cnt_type == 0) {
                this.pic_order_cnt_lsb = cAVLCReader.readU(this.sps.log2_max_pic_order_cnt_lsb_minus4 + 4, "SliceHeader: pic_order_cnt_lsb");
                if (this.pps.bottom_field_pic_order_in_frame_present_flag && !this.field_pic_flag) {
                    this.delta_pic_order_cnt_bottom = cAVLCReader.readSE("SliceHeader: delta_pic_order_cnt_bottom");
                }
            }
            if (this.sps.pic_order_cnt_type != 1 || this.sps.delta_pic_order_always_zero_flag) {
                return;
            }
            this.delta_pic_order_cnt_0 = cAVLCReader.readSE("delta_pic_order_cnt_0");
            if (!this.pps.bottom_field_pic_order_in_frame_present_flag || this.field_pic_flag) {
                return;
            }
            this.delta_pic_order_cnt_1 = cAVLCReader.readSE("delta_pic_order_cnt_1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "SliceHeader{first_mb_in_slice=" + this.first_mb_in_slice + ", slice_type=" + this.slice_type + ", pic_parameter_set_id=" + this.pic_parameter_set_id + ", colour_plane_id=" + this.colour_plane_id + ", frame_num=" + this.frame_num + ", field_pic_flag=" + this.field_pic_flag + ", bottom_field_flag=" + this.bottom_field_flag + ", idr_pic_id=" + this.idr_pic_id + ", pic_order_cnt_lsb=" + this.pic_order_cnt_lsb + ", delta_pic_order_cnt_bottom=" + this.delta_pic_order_cnt_bottom + '}';
    }
}
