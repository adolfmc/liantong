package com.baidu.cloud.media.player.misc;

import android.annotation.TargetApi;
import android.text.TextUtils;
import com.baidu.cloud.media.player.BDCloudMediaMeta;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BDCloudMediaFormat implements IMediaFormat {
    public static final String CODEC_NAME_H264 = "h264";
    public static final String KEY_IJK_BIT_RATE_UI = "ijk-bit-rate-ui";
    public static final String KEY_IJK_CHANNEL_UI = "ijk-channel-ui";
    public static final String KEY_IJK_CODEC_LONG_NAME_UI = "ijk-codec-long-name-ui";
    public static final String KEY_IJK_CODEC_NAME_UI = "ijk-codec-name-ui";
    public static final String KEY_IJK_CODEC_PIXEL_FORMAT_UI = "ijk-pixel-format-ui";
    public static final String KEY_IJK_CODEC_PROFILE_LEVEL_UI = "ijk-profile-level-ui";
    public static final String KEY_IJK_FRAME_RATE_UI = "ijk-frame-rate-ui";
    public static final String KEY_IJK_RESOLUTION_UI = "ijk-resolution-ui";
    public static final String KEY_IJK_SAMPLE_RATE_UI = "ijk-sample-rate-ui";
    private static final Map<String, Formatter> sFormatterMap = new HashMap();
    public final BDCloudMediaMeta.BDCloudStreamMeta mMediaFormat;

    public BDCloudMediaFormat(BDCloudMediaMeta.BDCloudStreamMeta bDCloudStreamMeta) {
        sFormatterMap.put("ijk-codec-long-name-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.1
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            public String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                return BDCloudMediaFormat.this.mMediaFormat.getString("codec_long_name");
            }
        });
        sFormatterMap.put("ijk-codec-name-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.2
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            public String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                return BDCloudMediaFormat.this.mMediaFormat.getString("codec_name");
            }
        });
        sFormatterMap.put("ijk-bit-rate-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.3
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            protected String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                int integer = bDCloudMediaFormat.getInteger("bitrate");
                if (integer <= 0) {
                    return null;
                }
                return integer < 1000 ? String.format(Locale.US, "%d bit/s", Integer.valueOf(integer)) : String.format(Locale.US, "%d kb/s", Integer.valueOf(integer / 1000));
            }
        });
        sFormatterMap.put("ijk-profile-level-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.4
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            protected String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                String str;
                switch (bDCloudMediaFormat.getInteger("codec_profile_id")) {
                    case 44:
                        str = "CAVLC 4:4:4";
                        break;
                    case 66:
                        str = "Baseline";
                        break;
                    case 77:
                        str = "Main";
                        break;
                    case 88:
                        str = "Extended";
                        break;
                    case 100:
                        str = "High";
                        break;
                    case 110:
                        str = "High 10";
                        break;
                    case 122:
                        str = "High 4:2:2";
                        break;
                    case 144:
                        str = "High 4:4:4";
                        break;
                    case 244:
                        str = "High 4:4:4 Predictive";
                        break;
                    case 578:
                        str = "Constrained Baseline";
                        break;
                    case 2158:
                        str = "High 10 Intra";
                        break;
                    case 2170:
                        str = "High 4:2:2 Intra";
                        break;
                    case 2292:
                        str = "High 4:4:4 Intra";
                        break;
                    default:
                        return null;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                String string = bDCloudMediaFormat.getString("codec_name");
                if (!TextUtils.isEmpty(string) && string.equalsIgnoreCase("h264")) {
                    int integer = bDCloudMediaFormat.getInteger("codec_level");
                    if (integer < 10) {
                        return sb.toString();
                    }
                    sb.append(" Profile Level ");
                    sb.append((integer / 10) % 10);
                    int i = integer % 10;
                    if (i != 0) {
                        sb.append(".");
                        sb.append(i);
                    }
                }
                return sb.toString();
            }
        });
        sFormatterMap.put("ijk-pixel-format-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.5
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            protected String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                return bDCloudMediaFormat.getString("codec_pixel_format");
            }
        });
        sFormatterMap.put("ijk-resolution-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.6
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            protected String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                int integer = bDCloudMediaFormat.getInteger("width");
                int integer2 = bDCloudMediaFormat.getInteger("height");
                int integer3 = bDCloudMediaFormat.getInteger("sar_num");
                int integer4 = bDCloudMediaFormat.getInteger("sar_den");
                if (integer <= 0 || integer2 <= 0) {
                    return null;
                }
                return (integer3 <= 0 || integer4 <= 0) ? String.format(Locale.US, "%d x %d", Integer.valueOf(integer), Integer.valueOf(integer2)) : String.format(Locale.US, "%d x %d [SAR %d:%d]", Integer.valueOf(integer), Integer.valueOf(integer2), Integer.valueOf(integer3), Integer.valueOf(integer4));
            }
        });
        sFormatterMap.put("ijk-frame-rate-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.7
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            protected String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                int integer = bDCloudMediaFormat.getInteger("fps_num");
                int integer2 = bDCloudMediaFormat.getInteger("fps_den");
                if (integer <= 0 || integer2 <= 0) {
                    return null;
                }
                return String.valueOf(integer / integer2);
            }
        });
        sFormatterMap.put("ijk-sample-rate-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.8
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            protected String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                int integer = bDCloudMediaFormat.getInteger("sample_rate");
                if (integer <= 0) {
                    return null;
                }
                return String.format(Locale.US, "%d Hz", Integer.valueOf(integer));
            }
        });
        sFormatterMap.put("ijk-channel-ui", new Formatter() { // from class: com.baidu.cloud.media.player.misc.BDCloudMediaFormat.9
            @Override // com.baidu.cloud.media.player.misc.BDCloudMediaFormat.Formatter
            protected String doFormat(BDCloudMediaFormat bDCloudMediaFormat) {
                int integer = bDCloudMediaFormat.getInteger("channel_layout");
                if (integer <= 0) {
                    return null;
                }
                long j = integer;
                return j == 4 ? "mono" : j == 3 ? "stereo" : String.format(Locale.US, "%x", Integer.valueOf(integer));
            }
        });
        this.mMediaFormat = bDCloudStreamMeta;
    }

    @Override // com.baidu.cloud.media.player.misc.IMediaFormat
    @TargetApi(16)
    public int getInteger(String str) {
        BDCloudMediaMeta.BDCloudStreamMeta bDCloudStreamMeta = this.mMediaFormat;
        if (bDCloudStreamMeta == null) {
            return 0;
        }
        return bDCloudStreamMeta.getInt(str);
    }

    @Override // com.baidu.cloud.media.player.misc.IMediaFormat
    public String getString(String str) {
        if (this.mMediaFormat == null) {
            return null;
        }
        if (sFormatterMap.containsKey(str)) {
            return sFormatterMap.get(str).format(this);
        }
        return this.mMediaFormat.getString(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class Formatter {
        protected abstract String doFormat(BDCloudMediaFormat bDCloudMediaFormat);

        protected String getDefaultString() {
            return "N/A";
        }

        private Formatter() {
        }

        public String format(BDCloudMediaFormat bDCloudMediaFormat) {
            String doFormat = doFormat(bDCloudMediaFormat);
            return TextUtils.isEmpty(doFormat) ? getDefaultString() : doFormat;
        }
    }
}
