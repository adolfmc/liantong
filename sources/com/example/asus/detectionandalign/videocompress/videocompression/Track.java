package com.example.asus.detectionandalign.videocompress.videocompression;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import com.mabeijianxi.smallvideorecord2.MediaRecorderBase;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import szcom.coremedia.iso.boxes.AbstractMediaHeaderBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SoundMediaHeaderBox;
import szcom.coremedia.iso.boxes.VideoMediaHeaderBox;
import szcom.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.AudioSpecificConfig;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;
import szcom.mp4parser.iso14496.part15.AvcConfigurationBox;

@TargetApi(16)
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Track {
    private static Map<Integer, Integer> samplingFrequencyIndexMap = new HashMap();
    private long duration;
    private String handler;
    private AbstractMediaHeaderBox headerBox;
    private int height;
    private SampleDescriptionBox sampleDescriptionBox;
    private LinkedList<Integer> syncSamples;
    private int timeScale;
    private long trackId;
    private float volume;
    private int width;
    private ArrayList<Sample> samples = new ArrayList<>();
    private Date creationTime = new Date();
    private ArrayList<Long> sampleDurations = new ArrayList<>();
    private boolean isAudio = false;
    private long lastPresentationTimeUs = 0;
    private boolean first = true;

    static {
        samplingFrequencyIndexMap.put(96000, 0);
        samplingFrequencyIndexMap.put(88200, 1);
        samplingFrequencyIndexMap.put(64000, 2);
        samplingFrequencyIndexMap.put(48000, 3);
        samplingFrequencyIndexMap.put(44100, 4);
        samplingFrequencyIndexMap.put(32000, 5);
        samplingFrequencyIndexMap.put(24000, 6);
        samplingFrequencyIndexMap.put(22050, 7);
        samplingFrequencyIndexMap.put(16000, 8);
        samplingFrequencyIndexMap.put(12000, 9);
        samplingFrequencyIndexMap.put(11025, 10);
        samplingFrequencyIndexMap.put(8000, 11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Track(int i, MediaFormat mediaFormat, boolean z) {
        VisualSampleEntry visualSampleEntry;
        this.trackId = 0L;
        this.duration = 0L;
        this.headerBox = null;
        this.sampleDescriptionBox = null;
        this.syncSamples = null;
        this.volume = 0.0f;
        this.trackId = i;
        if (z) {
            this.sampleDurations.add(1024L);
            this.duration = 1024L;
            this.volume = 1.0f;
            this.timeScale = mediaFormat.getInteger("sample-rate");
            this.handler = "soun";
            this.headerBox = new SoundMediaHeaderBox();
            this.sampleDescriptionBox = new SampleDescriptionBox();
            AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.TYPE3);
            audioSampleEntry.setChannelCount(mediaFormat.getInteger("channel-count"));
            audioSampleEntry.setSampleRate(mediaFormat.getInteger("sample-rate"));
            audioSampleEntry.setDataReferenceIndex(1);
            audioSampleEntry.setSampleSize(16);
            ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
            ESDescriptor eSDescriptor = new ESDescriptor();
            eSDescriptor.setEsId(0);
            SLConfigDescriptor sLConfigDescriptor = new SLConfigDescriptor();
            sLConfigDescriptor.setPredefined(2);
            eSDescriptor.setSlConfigDescriptor(sLConfigDescriptor);
            DecoderConfigDescriptor decoderConfigDescriptor = new DecoderConfigDescriptor();
            decoderConfigDescriptor.setObjectTypeIndication(64);
            decoderConfigDescriptor.setStreamType(5);
            decoderConfigDescriptor.setBufferSizeDB(MediaRecorderBase.VIDEO_BITRATE_MEDIUM);
            decoderConfigDescriptor.setMaxBitRate(96000L);
            decoderConfigDescriptor.setAvgBitRate(96000L);
            AudioSpecificConfig audioSpecificConfig = new AudioSpecificConfig();
            audioSpecificConfig.setAudioObjectType(2);
            audioSpecificConfig.setSamplingFrequencyIndex(samplingFrequencyIndexMap.get(Integer.valueOf((int) audioSampleEntry.getSampleRate())).intValue());
            audioSpecificConfig.setChannelConfiguration(audioSampleEntry.getChannelCount());
            decoderConfigDescriptor.setAudioSpecificInfo(audioSpecificConfig);
            eSDescriptor.setDecoderConfigDescriptor(decoderConfigDescriptor);
            ByteBuffer serialize = eSDescriptor.serialize();
            eSDescriptorBox.setEsDescriptor(eSDescriptor);
            eSDescriptorBox.setData(serialize);
            audioSampleEntry.addBox(eSDescriptorBox);
            visualSampleEntry = audioSampleEntry;
        } else {
            this.sampleDurations.add(3015L);
            this.duration = 3015L;
            this.width = mediaFormat.getInteger("width");
            this.height = mediaFormat.getInteger("height");
            this.timeScale = 90000;
            this.syncSamples = new LinkedList<>();
            this.handler = "vide";
            this.headerBox = new VideoMediaHeaderBox();
            this.sampleDescriptionBox = new SampleDescriptionBox();
            String string = mediaFormat.getString("mime");
            if (string.equals("video/avc")) {
                VisualSampleEntry visualSampleEntry2 = new VisualSampleEntry(VisualSampleEntry.TYPE3);
                visualSampleEntry2.setDataReferenceIndex(1);
                visualSampleEntry2.setDepth(24);
                visualSampleEntry2.setFrameCount(1);
                visualSampleEntry2.setHorizresolution(72.0d);
                visualSampleEntry2.setVertresolution(72.0d);
                visualSampleEntry2.setWidth(this.width);
                visualSampleEntry2.setHeight(this.height);
                AvcConfigurationBox avcConfigurationBox = new AvcConfigurationBox();
                if (mediaFormat.getByteBuffer("csd-0") != null) {
                    ArrayList arrayList = new ArrayList();
                    ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-0");
                    byteBuffer.position(4);
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    arrayList.add(bArr);
                    ArrayList arrayList2 = new ArrayList();
                    ByteBuffer byteBuffer2 = mediaFormat.getByteBuffer("csd-1");
                    byteBuffer2.position(4);
                    byte[] bArr2 = new byte[byteBuffer2.remaining()];
                    byteBuffer2.get(bArr2);
                    arrayList2.add(bArr2);
                    avcConfigurationBox.setSequenceParameterSets(arrayList);
                    avcConfigurationBox.setPictureParameterSets(arrayList2);
                }
                avcConfigurationBox.setAvcLevelIndication(13);
                avcConfigurationBox.setAvcProfileIndication(100);
                avcConfigurationBox.setBitDepthLumaMinus8(-1);
                avcConfigurationBox.setBitDepthChromaMinus8(-1);
                avcConfigurationBox.setChromaFormat(-1);
                avcConfigurationBox.setConfigurationVersion(1);
                avcConfigurationBox.setLengthSizeMinusOne(3);
                avcConfigurationBox.setProfileCompatibility(0);
                visualSampleEntry2.addBox(avcConfigurationBox);
                visualSampleEntry = visualSampleEntry2;
            } else if (!string.equals("video/mp4v")) {
                return;
            } else {
                VisualSampleEntry visualSampleEntry3 = new VisualSampleEntry(VisualSampleEntry.TYPE1);
                visualSampleEntry3.setDataReferenceIndex(1);
                visualSampleEntry3.setDepth(24);
                visualSampleEntry3.setFrameCount(1);
                visualSampleEntry3.setHorizresolution(72.0d);
                visualSampleEntry3.setVertresolution(72.0d);
                visualSampleEntry3.setWidth(this.width);
                visualSampleEntry3.setHeight(this.height);
                visualSampleEntry = visualSampleEntry3;
            }
        }
        this.sampleDescriptionBox.addBox(visualSampleEntry);
    }

    public void addSample(long j, MediaCodec.BufferInfo bufferInfo) {
        boolean z = (this.isAudio || (bufferInfo.flags & 1) == 0) ? false : true;
        this.samples.add(new Sample(j, bufferInfo.size));
        LinkedList<Integer> linkedList = this.syncSamples;
        if (linkedList != null && z) {
            linkedList.add(Integer.valueOf(this.samples.size()));
        }
        long j2 = bufferInfo.presentationTimeUs - this.lastPresentationTimeUs;
        this.lastPresentationTimeUs = bufferInfo.presentationTimeUs;
        long j3 = ((j2 * this.timeScale) + 500000) / 1000000;
        if (!this.first) {
            ArrayList<Long> arrayList = this.sampleDurations;
            arrayList.add(arrayList.size() - 1, Long.valueOf(j3));
            this.duration += j3;
        }
        this.first = false;
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getHandler() {
        return this.handler;
    }

    public int getHeight() {
        return this.height;
    }

    public AbstractMediaHeaderBox getMediaHeaderBox() {
        return this.headerBox;
    }

    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    public ArrayList<Long> getSampleDurations() {
        return this.sampleDurations;
    }

    public ArrayList<Sample> getSamples() {
        return this.samples;
    }

    public long[] getSyncSamples() {
        LinkedList<Integer> linkedList = this.syncSamples;
        if (linkedList == null || linkedList.isEmpty()) {
            return null;
        }
        long[] jArr = new long[this.syncSamples.size()];
        for (int i = 0; i < this.syncSamples.size(); i++) {
            jArr[i] = this.syncSamples.get(i).intValue();
        }
        return jArr;
    }

    public int getTimeScale() {
        return this.timeScale;
    }

    public long getTrackId() {
        return this.trackId;
    }

    public float getVolume() {
        return this.volume;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isAudio() {
        return this.isAudio;
    }
}
