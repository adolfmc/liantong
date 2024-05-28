package com.mabeijianxi.smallvideorecord2.model;

import com.mabeijianxi.smallvideorecord2.FileUtils;
import com.mabeijianxi.smallvideorecord2.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MediaObject implements Serializable {
    public static final int DEFAULT_MAX_DURATION = 10000;
    public static final int DEFAULT_VIDEO_BITRATE = 800;
    public static final int MEDIA_PART_TYPE_IMPORT_IMAGE = 2;
    public static final int MEDIA_PART_TYPE_IMPORT_VIDEO = 1;
    public static final int MEDIA_PART_TYPE_RECORD = 0;
    public static final int MEDIA_PART_TYPE_RECORD_MP4 = 3;
    private volatile transient MediaPart mCurrentPart;
    private String mKey;
    private int mMaxDuration;
    private LinkedList<MediaPart> mMediaList;
    private String mOutputDirectory;
    private String mOutputObjectPath;
    private String mOutputVideoPath;
    private String mOutputVideoThumbPath;
    public MediaThemeObject mThemeObject;
    private int mVideoBitrate;
    private String outputTempVideoPath;

    public MediaObject(String str, String str2) {
        this(str, str2, 800);
    }

    public MediaObject(String str, String str2, int i) {
        this.mMediaList = new LinkedList<>();
        this.mKey = str;
        this.mOutputDirectory = str2;
        this.mVideoBitrate = i;
        this.mOutputObjectPath = this.mOutputDirectory + File.separator + this.mKey + ".obj";
        StringBuilder sb = new StringBuilder();
        sb.append(this.mOutputDirectory);
        sb.append(".mp4");
        this.mOutputVideoPath = sb.toString();
        this.mOutputVideoThumbPath = this.mOutputDirectory + File.separator + this.mKey + ".jpg";
        this.mMaxDuration = 10000;
        this.outputTempVideoPath = this.mOutputDirectory + File.separator + this.mKey + "_temp.mp4";
    }

    public String getBaseName() {
        return this.mKey;
    }

    public int getVideoBitrate() {
        return this.mVideoBitrate;
    }

    public int getMaxDuration() {
        return this.mMaxDuration;
    }

    public void setMaxDuration(int i) {
        if (i >= 1000) {
            this.mMaxDuration = i;
        }
    }

    public String getOutputDirectory() {
        return this.mOutputDirectory;
    }

    public String getOutputTempVideoPath() {
        return this.outputTempVideoPath;
    }

    public void setOutputTempVideoPath(String str) {
        this.outputTempVideoPath = str;
    }

    public String getOutputTempTranscodingVideoPath() {
        return this.mOutputDirectory + File.separator + this.mKey + ".mp4";
    }

    public void cleanTheme() {
        this.mThemeObject = null;
        LinkedList<MediaPart> linkedList = this.mMediaList;
        if (linkedList != null) {
            Iterator<MediaPart> it = linkedList.iterator();
            while (it.hasNext()) {
                MediaPart next = it.next();
                next.cutStartTime = 0;
                next.cutEndTime = next.duration;
            }
        }
    }

    public String getObjectFilePath() {
        if (StringUtils.isEmpty(this.mOutputObjectPath)) {
            File file = new File(this.mOutputVideoPath);
            this.mOutputObjectPath = this.mOutputDirectory + File.separator + file.getName() + ".obj";
        }
        return this.mOutputObjectPath;
    }

    public String getOutputVideoPath() {
        return this.mOutputVideoPath;
    }

    public String getOutputVideoThumbPath() {
        return this.mOutputVideoThumbPath;
    }

    public int getDuration() {
        LinkedList<MediaPart> linkedList = this.mMediaList;
        int i = 0;
        if (linkedList != null) {
            Iterator<MediaPart> it = linkedList.iterator();
            while (it.hasNext()) {
                i += it.next().getDuration();
            }
        }
        return i;
    }

    public int getCutDuration() {
        LinkedList<MediaPart> linkedList = this.mMediaList;
        int i = 0;
        if (linkedList != null) {
            Iterator<MediaPart> it = linkedList.iterator();
            while (it.hasNext()) {
                MediaPart next = it.next();
                int i2 = next.cutEndTime - next.cutStartTime;
                if (next.speed != 10) {
                    i2 = (int) (i2 * (10.0f / next.speed));
                }
                i += i2;
            }
        }
        return i;
    }

    public void removePart(MediaPart mediaPart, boolean z) {
        LinkedList<MediaPart> linkedList = this.mMediaList;
        if (linkedList != null) {
            linkedList.remove(mediaPart);
        }
        if (mediaPart != null) {
            mediaPart.stop();
            if (z) {
                mediaPart.delete();
            }
            this.mMediaList.remove(mediaPart);
            if (this.mCurrentPart == null || !mediaPart.equals(this.mCurrentPart)) {
                return;
            }
            this.mCurrentPart = null;
        }
    }

    public MediaPart buildMediaPart(int i) {
        this.mCurrentPart = new MediaPart();
        this.mCurrentPart.position = getDuration();
        this.mCurrentPart.index = this.mMediaList.size();
        MediaPart mediaPart = this.mCurrentPart;
        mediaPart.mediaPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + ".v";
        MediaPart mediaPart2 = this.mCurrentPart;
        mediaPart2.audioPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + ".a";
        MediaPart mediaPart3 = this.mCurrentPart;
        mediaPart3.thumbPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + ".jpg";
        this.mCurrentPart.cameraId = i;
        this.mCurrentPart.prepare();
        this.mCurrentPart.recording = true;
        this.mCurrentPart.startTime = System.currentTimeMillis();
        this.mCurrentPart.type = 1;
        this.mMediaList.add(this.mCurrentPart);
        return this.mCurrentPart;
    }

    public MediaPart buildMediaPart(int i, String str) {
        this.mCurrentPart = new MediaPart();
        this.mCurrentPart.position = getDuration();
        this.mCurrentPart.index = this.mMediaList.size();
        MediaPart mediaPart = this.mCurrentPart;
        mediaPart.mediaPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + str;
        MediaPart mediaPart2 = this.mCurrentPart;
        mediaPart2.audioPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + ".a";
        MediaPart mediaPart3 = this.mCurrentPart;
        mediaPart3.thumbPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + ".jpg";
        this.mCurrentPart.recording = true;
        this.mCurrentPart.cameraId = i;
        this.mCurrentPart.startTime = System.currentTimeMillis();
        this.mCurrentPart.type = 1;
        this.mMediaList.add(this.mCurrentPart);
        return this.mCurrentPart;
    }

    public MediaPart buildMediaPart(String str, int i, int i2) {
        this.mCurrentPart = new MediaPart();
        this.mCurrentPart.position = getDuration();
        this.mCurrentPart.index = this.mMediaList.size();
        MediaPart mediaPart = this.mCurrentPart;
        mediaPart.mediaPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + ".v";
        MediaPart mediaPart2 = this.mCurrentPart;
        mediaPart2.audioPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + ".a";
        MediaPart mediaPart3 = this.mCurrentPart;
        mediaPart3.thumbPath = this.mOutputDirectory + File.separator + this.mCurrentPart.index + ".jpg";
        this.mCurrentPart.duration = i;
        this.mCurrentPart.startTime = 0L;
        this.mCurrentPart.endTime = (long) i;
        this.mCurrentPart.cutStartTime = 0;
        this.mCurrentPart.cutEndTime = i;
        this.mCurrentPart.tempPath = str;
        this.mCurrentPart.type = i2;
        this.mMediaList.add(this.mCurrentPart);
        return this.mCurrentPart;
    }

    public String getConcatYUV() {
        StringBuilder sb = new StringBuilder();
        LinkedList<MediaPart> linkedList = this.mMediaList;
        if (linkedList != null && linkedList.size() > 0) {
            int i = 0;
            if (this.mMediaList.size() == 1) {
                if (StringUtils.isEmpty(this.mMediaList.get(0).tempMediaPath)) {
                    sb.append(this.mMediaList.get(0).mediaPath);
                } else {
                    sb.append(this.mMediaList.get(0).tempMediaPath);
                }
            } else {
                sb.append("concat:");
                int size = this.mMediaList.size();
                while (i < size) {
                    MediaPart mediaPart = this.mMediaList.get(i);
                    if (StringUtils.isEmpty(mediaPart.tempMediaPath)) {
                        sb.append(mediaPart.mediaPath);
                    } else {
                        sb.append(mediaPart.tempMediaPath);
                    }
                    i++;
                    if (i < size) {
                        sb.append("|");
                    }
                }
            }
        }
        return sb.toString();
    }

    public String getConcatPCM() {
        StringBuilder sb = new StringBuilder();
        LinkedList<MediaPart> linkedList = this.mMediaList;
        if (linkedList != null && linkedList.size() > 0) {
            int i = 0;
            if (this.mMediaList.size() == 1) {
                if (StringUtils.isEmpty(this.mMediaList.get(0).tempAudioPath)) {
                    sb.append(this.mMediaList.get(0).audioPath);
                } else {
                    sb.append(this.mMediaList.get(0).tempAudioPath);
                }
            } else {
                sb.append("concat:");
                int size = this.mMediaList.size();
                while (i < size) {
                    MediaPart mediaPart = this.mMediaList.get(i);
                    if (StringUtils.isEmpty(mediaPart.tempAudioPath)) {
                        sb.append(mediaPart.audioPath);
                    } else {
                        sb.append(mediaPart.tempAudioPath);
                    }
                    i++;
                    if (i < size) {
                        sb.append("|");
                    }
                }
            }
        }
        return sb.toString();
    }

    public MediaPart getCurrentPart() {
        if (this.mCurrentPart != null) {
            return this.mCurrentPart;
        }
        LinkedList<MediaPart> linkedList = this.mMediaList;
        if (linkedList != null && linkedList.size() > 0) {
            LinkedList<MediaPart> linkedList2 = this.mMediaList;
            this.mCurrentPart = linkedList2.get(linkedList2.size() - 1);
        }
        return this.mCurrentPart;
    }

    public int getCurrentIndex() {
        MediaPart currentPart = getCurrentPart();
        if (currentPart != null) {
            return currentPart.index;
        }
        return 0;
    }

    public MediaPart getPart(int i) {
        if (this.mCurrentPart == null || i >= this.mMediaList.size()) {
            return null;
        }
        return this.mMediaList.get(i);
    }

    public void delete() {
        LinkedList<MediaPart> linkedList = this.mMediaList;
        if (linkedList != null) {
            Iterator<MediaPart> it = linkedList.iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
        }
        FileUtils.deleteDir(this.mOutputDirectory);
    }

    public LinkedList<MediaPart> getMedaParts() {
        return this.mMediaList;
    }

    public static void preparedMediaObject(MediaObject mediaObject) {
        LinkedList<MediaPart> linkedList;
        if (mediaObject == null || (linkedList = mediaObject.mMediaList) == null) {
            return;
        }
        int i = 0;
        Iterator<MediaPart> it = linkedList.iterator();
        while (it.hasNext()) {
            MediaPart next = it.next();
            next.startTime = i;
            next.endTime = next.startTime + next.duration;
            i += next.duration;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.mMediaList != null) {
            stringBuffer.append("[" + this.mMediaList.size() + "]");
            Iterator<MediaPart> it = this.mMediaList.iterator();
            while (it.hasNext()) {
                MediaPart next = it.next();
                stringBuffer.append(next.mediaPath + ":" + next.duration + "\n");
            }
        }
        return stringBuffer.toString();
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class MediaPart implements Serializable {
        public String audioPath;
        public int cameraId;
        public int cutEndTime;
        public int cutStartTime;
        public int duration;
        public transient long endTime;
        public int index;
        public transient FileOutputStream mCurrentOutputAudio;
        public transient FileOutputStream mCurrentOutputVideo;
        public String mediaPath;
        public int position;
        public volatile transient boolean recording;
        public transient boolean remove;
        public transient long startTime;
        public String tempAudioPath;
        public String tempMediaPath;
        public String tempPath;
        public String thumbPath;
        public int yuvHeight;
        public int yuvWidth;
        public int type = 0;
        public int speed = 10;

        public void delete() {
            FileUtils.deleteFile(this.mediaPath);
            FileUtils.deleteFile(this.audioPath);
            FileUtils.deleteFile(this.thumbPath);
            FileUtils.deleteFile(this.tempMediaPath);
            FileUtils.deleteFile(this.tempAudioPath);
        }

        public void writeAudioData(byte[] bArr) throws IOException {
            FileOutputStream fileOutputStream = this.mCurrentOutputAudio;
            if (fileOutputStream != null) {
                fileOutputStream.write(bArr);
            }
        }

        public void writeVideoData(byte[] bArr) throws IOException {
            FileOutputStream fileOutputStream = this.mCurrentOutputVideo;
            if (fileOutputStream != null) {
                fileOutputStream.write(bArr);
            }
        }

        public void prepare() {
            try {
                this.mCurrentOutputVideo = new FileOutputStream(this.mediaPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            prepareAudio();
        }

        public void prepareAudio() {
            try {
                this.mCurrentOutputAudio = new FileOutputStream(this.audioPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public int getDuration() {
            int i = this.duration;
            return i > 0 ? i : (int) (System.currentTimeMillis() - this.startTime);
        }

        public void stop() {
            FileOutputStream fileOutputStream = this.mCurrentOutputVideo;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    this.mCurrentOutputVideo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.mCurrentOutputVideo = null;
            }
            FileOutputStream fileOutputStream2 = this.mCurrentOutputAudio;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    this.mCurrentOutputAudio.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                this.mCurrentOutputAudio = null;
            }
        }
    }
}
