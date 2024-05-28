package szcom.googlecode.mp4parser.authoring.tracks.webvtt;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes.CuePayloadBox;
import szcom.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes.CueSettingsBox;
import szcom.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes.VTTCueBox;
import szcom.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes.VTTEmptyCueBox;
import szcom.googlecode.mp4parser.util.ByteBufferByteChannel;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.Mp4Arrays;
import szcom.mp4parser.iso14496.part30.WebVTTConfigurationBox;
import szcom.mp4parser.iso14496.part30.WebVTTSampleEntry;
import szcom.mp4parser.iso14496.part30.WebVTTSourceLabelBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class WebVttTrack extends AbstractTrack {
    long[] sampleDurations;
    WebVTTSampleEntry sampleEntry;
    List<Sample> samples;
    SampleDescriptionBox stsd;
    TrackMetaData trackMetaData;
    private static final String WEBVTT_FILE_HEADER_STRING = "^\ufeff?WEBVTT((\\u0020|\t).*)?$";
    private static final Pattern WEBVTT_FILE_HEADER = Pattern.compile(WEBVTT_FILE_HEADER_STRING);
    private static final String WEBVTT_METADATA_HEADER_STRING = "\\S*[:=]\\S*";
    private static final Pattern WEBVTT_METADATA_HEADER = Pattern.compile(WEBVTT_METADATA_HEADER_STRING);
    private static final String WEBVTT_CUE_IDENTIFIER_STRING = "^(?!.*(-->)).*$";
    private static final Pattern WEBVTT_CUE_IDENTIFIER = Pattern.compile(WEBVTT_CUE_IDENTIFIER_STRING);
    private static final String WEBVTT_TIMESTAMP_STRING = "(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{3}";
    private static final Pattern WEBVTT_TIMESTAMP = Pattern.compile(WEBVTT_TIMESTAMP_STRING);
    private static final String WEBVTT_CUE_SETTING_STRING = "\\S*:\\S*";
    private static final Pattern WEBVTT_CUE_SETTING = Pattern.compile(WEBVTT_CUE_SETTING_STRING);
    private static final Sample EMPTY_SAMPLE = new C141411();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C141411 implements Sample {
        ByteBuffer vtte;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C141411() {
            VTTEmptyCueBox vTTEmptyCueBox = new VTTEmptyCueBox();
            this.vtte = ByteBuffer.allocate(CastUtils.l2i(vTTEmptyCueBox.getSize()));
            try {
                vTTEmptyCueBox.getBox(new ByteBufferByteChannel(this.vtte));
                this.vtte.rewind();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public ByteBuffer asByteBuffer() {
            return this.vtte.duplicate();
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public long getSize() {
            return this.vtte.remaining();
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public void writeTo(WritableByteChannel writableByteChannel) {
            writableByteChannel.write(this.vtte.duplicate());
        }
    }

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    static class BoxBearingSample implements Sample {
        List<Box> boxes;

        public BoxBearingSample(List<Box> list) {
            this.boxes = list;
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public ByteBuffer asByteBuffer() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                writeTo(Channels.newChannel(byteArrayOutputStream));
                return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public long getSize() {
            long j = 0;
            for (Box box : this.boxes) {
                j += box.getSize();
            }
            return j;
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public void writeTo(WritableByteChannel writableByteChannel) {
            for (Box box : this.boxes) {
                box.getBox(writableByteChannel);
            }
        }
    }

    public WebVttTrack(InputStream inputStream, String str, Locale locale) {
        super(str);
        this.trackMetaData = new TrackMetaData();
        this.samples = new ArrayList();
        this.sampleDurations = new long[0];
        this.trackMetaData.setTimescale(1000L);
        this.trackMetaData.setLanguage(locale.getISO3Language());
        this.stsd = new SampleDescriptionBox();
        this.sampleEntry = new WebVTTSampleEntry();
        this.stsd.addBox(this.sampleEntry);
        WebVTTConfigurationBox webVTTConfigurationBox = new WebVTTConfigurationBox();
        this.sampleEntry.addBox(webVTTConfigurationBox);
        this.sampleEntry.addBox(new WebVTTSourceLabelBox());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String readLine = bufferedReader.readLine();
        if (readLine == null || !WEBVTT_FILE_HEADER.matcher(readLine).matches()) {
            throw new IOException("Expected WEBVTT. Got " + readLine);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(webVTTConfigurationBox.getConfig()));
        while (true) {
            sb.append("\n");
            sb.append(readLine);
            webVTTConfigurationBox.setConfig(sb.toString());
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                throw new IOException("Expected an empty line after webvtt header");
            }
            if (readLine.isEmpty()) {
                long j = 0;
                while (true) {
                    String readLine2 = bufferedReader.readLine();
                    if (readLine2 == null) {
                        return;
                    }
                    if (!"".equals(readLine2.trim())) {
                        readLine2 = WEBVTT_CUE_IDENTIFIER.matcher(readLine2).find() ? bufferedReader.readLine() : readLine2;
                        Matcher matcher = WEBVTT_TIMESTAMP.matcher(readLine2);
                        if (!matcher.find()) {
                            throw new IOException("Expected cue start time: " + readLine2);
                        }
                        long parseTimestampUs = parseTimestampUs(matcher.group());
                        if (!matcher.find()) {
                            throw new IOException("Expected cue end time: " + readLine2);
                        }
                        String group2 = matcher.group();
                        long parseTimestampUs2 = parseTimestampUs(group2);
                        Matcher matcher2 = WEBVTT_CUE_SETTING.matcher(readLine2.substring(readLine2.indexOf(group2) + group2.length()));
                        String str2 = null;
                        while (matcher2.find()) {
                            str2 = matcher2.group();
                        }
                        StringBuilder sb2 = new StringBuilder();
                        while (true) {
                            String readLine3 = bufferedReader.readLine();
                            if (readLine3 == null || readLine3.isEmpty()) {
                                break;
                            }
                            if (sb2.length() > 0) {
                                sb2.append("\n");
                            }
                            sb2.append(readLine3.trim());
                        }
                        if (parseTimestampUs != j) {
                            this.sampleDurations = Mp4Arrays.copyOfAndAppend(this.sampleDurations, parseTimestampUs - j);
                            this.samples.add(EMPTY_SAMPLE);
                        }
                        this.sampleDurations = Mp4Arrays.copyOfAndAppend(this.sampleDurations, parseTimestampUs2 - parseTimestampUs);
                        VTTCueBox vTTCueBox = new VTTCueBox();
                        if (str2 != null) {
                            CueSettingsBox cueSettingsBox = new CueSettingsBox();
                            cueSettingsBox.setContent(str2);
                            vTTCueBox.setCueSettingsBox(cueSettingsBox);
                        }
                        CuePayloadBox cuePayloadBox = new CuePayloadBox();
                        cuePayloadBox.setContent(sb2.toString());
                        vTTCueBox.setCuePayloadBox(cuePayloadBox);
                        this.samples.add(new BoxBearingSample(Collections.singletonList(vTTCueBox)));
                        j = parseTimestampUs2;
                    }
                }
            } else if (!WEBVTT_METADATA_HEADER.matcher(readLine).find()) {
                throw new IOException("Expected WebVTT metadata header. Got " + readLine);
            } else {
                sb = new StringBuilder(String.valueOf(webVTTConfigurationBox.getConfig()));
            }
        }
    }

    private static long parseTimestampUs(String str) {
        if (str.matches(WEBVTT_TIMESTAMP_STRING)) {
            String[] split = str.split("\\.", 2);
            long j = 0;
            for (String str2 : split[0].split(":")) {
                j = (j * 60) + Long.parseLong(str2);
            }
            return (j * 1000) + Long.parseLong(split[1]);
        }
        throw new NumberFormatException("has invalid format");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "text";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.stsd;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        long[] jArr = new long[this.sampleDurations.length];
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = (this.sampleDurations[i] * this.trackMetaData.getTimescale()) / 1000;
        }
        return jArr;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }
}
