package com.baidu.rtc;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.webrtc.Logging;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SdpPrefer {
    private static final String AUDIO_CODEC_PARAM_BITRATE = "maxaveragebitrate";
    private static final String AUDIO_MAX_CAPTURER_RATE = "sprop-maxcapturerate";
    private static final String AUDIO_MAX_PLAYBACK_RATE = "maxplaybackrate";
    private static final String AUDIO_STEREO = "stereo";
    private static final String AUDIO_USE_DTX = "usedtx";
    private static final String CBR = "cbr";
    private static final String TAG = "SdpPrefer";

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AudioSdpAttribute {
        public boolean cbr = false;
        public boolean useinbandfec = true;
        public int maxplaybackrate = -1;
        public int sprop_maxcapturerate = -1;
        public int maxaveragebitrate = -1;
        public boolean usedtx = false;
        public boolean stereo = false;
    }

    public static String setAudioAttributes(String str, String str2, AudioSdpAttribute audioSdpAttribute) {
        String str3;
        boolean z;
        String[] split = str2.split("\r\n");
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) " + str + "(/\\d+)+[\r]?$");
        int i = 0;
        while (true) {
            if (i >= split.length) {
                i = -1;
                str3 = null;
                break;
            }
            Matcher matcher = compile.matcher(split[i]);
            if (matcher.matches()) {
                str3 = matcher.group(1);
                break;
            }
            i++;
        }
        if (str3 == null) {
            Logging.m5301w("SdpPrefer", "No rtpmap for " + str + " codec");
            return str2;
        }
        Logging.m5305d("SdpPrefer", "Found " + str + " rtpmap " + str3 + " at " + split[i]);
        StringBuilder sb = new StringBuilder();
        sb.append("^a=fmtp:");
        sb.append(str3);
        sb.append(" \\w+=\\d+.*[\r]?$");
        Pattern compile2 = Pattern.compile(sb.toString());
        int i2 = 0;
        while (true) {
            if (i2 >= split.length) {
                z = false;
                break;
            } else if (compile2.matcher(split[i2]).matches()) {
                Logging.m5305d("SdpPrefer", "Found " + str + " " + split[i2]);
                split[i2] = setAudioFmptParam(split[i2], audioSdpAttribute, false);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Update remote SDP line: ");
                sb2.append(split[i2]);
                Logging.m5305d("SdpPrefer", sb2.toString());
                z = true;
                break;
            } else {
                i2++;
            }
        }
        StringBuilder sb3 = new StringBuilder();
        for (int i3 = 0; i3 < split.length; i3++) {
            sb3.append(split[i3]);
            sb3.append("\r\n");
            if (!z && i3 == i) {
                String audioFmptParam = setAudioFmptParam("a=fmtp:" + str3 + " ", audioSdpAttribute, true);
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Add local SDP line: ");
                sb4.append(audioFmptParam);
                Logging.m5305d("SdpPrefer", sb4.toString());
                sb3.append(audioFmptParam);
                sb3.append("\r\n");
            }
        }
        return sb3.toString();
    }

    public static String setAudioFmptParam(String str, AudioSdpAttribute audioSdpAttribute, boolean z) {
        boolean z2;
        if (audioSdpAttribute.maxplaybackrate > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(z ? "" : "; ");
            sb.append("maxplaybackrate");
            sb.append("=");
            sb.append(audioSdpAttribute.maxplaybackrate);
            str = sb.toString();
            z2 = false;
        } else {
            z2 = true;
        }
        if (audioSdpAttribute.sprop_maxcapturerate > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append((z2 && z) ? "" : "; ");
            sb2.append("sprop-maxcapturerate");
            sb2.append("=");
            sb2.append(audioSdpAttribute.maxplaybackrate);
            str = sb2.toString();
            z2 = false;
        }
        if (audioSdpAttribute.cbr) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append((z2 && z) ? "" : "; ");
            sb3.append("cbr");
            sb3.append("=");
            sb3.append(audioSdpAttribute.cbr ? '1' : '0');
            str = sb3.toString();
            z2 = false;
        }
        if (audioSdpAttribute.maxaveragebitrate > 0) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append((z2 && z) ? "" : "; ");
            sb4.append("maxaveragebitrate");
            sb4.append("=");
            sb4.append(audioSdpAttribute.maxaveragebitrate * 1000);
            str = sb4.toString();
            z2 = false;
        }
        if (audioSdpAttribute.usedtx) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str);
            sb5.append((z2 && z) ? "" : "; ");
            sb5.append("usedtx");
            sb5.append("=");
            sb5.append(audioSdpAttribute.usedtx ? '1' : '0');
            str = sb5.toString();
            z2 = false;
        }
        if (audioSdpAttribute.stereo) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append(str);
            sb6.append((z2 && z) ? "" : "; ");
            sb6.append("stereo");
            sb6.append("=");
            sb6.append(audioSdpAttribute.stereo ? '1' : '0');
            return sb6.toString();
        }
        return str;
    }

    public static String preferCodec(String str, String str2, boolean z) {
        String[] split = str.split("\r\n");
        int findMediaDescriptionLine = findMediaDescriptionLine(z, split);
        if (findMediaDescriptionLine == -1) {
            Logging.m5301w("SdpPrefer", "No mediaDescription line, so can't prefer " + str2);
            return str;
        }
        ArrayList arrayList = new ArrayList();
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) " + str2 + "(/\\d+)+[\r]?$");
        for (String str3 : split) {
            Matcher matcher = compile.matcher(str3);
            if (matcher.matches()) {
                arrayList.add(matcher.group(1));
            }
        }
        if (arrayList.isEmpty()) {
            Logging.m5301w("SdpPrefer", "No payload types with name " + str2);
            return str;
        }
        String movePayloadTypesToFront = movePayloadTypesToFront(arrayList, split[findMediaDescriptionLine]);
        if (movePayloadTypesToFront == null) {
            return str;
        }
        Logging.m5305d("SdpPrefer", "Change media description from: " + split[findMediaDescriptionLine] + " to " + movePayloadTypesToFront);
        split[findMediaDescriptionLine] = movePayloadTypesToFront;
        return joinString(Arrays.asList(split), "\r\n", true);
    }

    @Nullable
    private static String movePayloadTypesToFront(List<String> list, String str) {
        List asList = Arrays.asList(str.split(" "));
        if (asList.size() <= 3) {
            Logging.m5305d("SdpPrefer", "Wrong SDP media description format: " + str);
            return null;
        }
        List subList = asList.subList(0, 3);
        ArrayList arrayList = new ArrayList(asList.subList(3, asList.size()));
        arrayList.removeAll(list);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(subList);
        arrayList2.addAll(list);
        arrayList2.addAll(arrayList);
        return joinString(arrayList2, " ", false);
    }

    private static String joinString(Iterable<? extends CharSequence> iterable, String str, boolean z) {
        Iterator<? extends CharSequence> it = iterable.iterator();
        if (it.hasNext()) {
            StringBuilder sb = new StringBuilder(it.next());
            while (it.hasNext()) {
                sb.append(str);
                sb.append(it.next());
            }
            if (z) {
                sb.append(str);
            }
            return sb.toString();
        }
        return "";
    }

    public static int findMediaDescriptionLine(boolean z, String[] strArr) {
        String str = z ? "m=audio " : "m=video ";
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].startsWith(str)) {
                return i;
            }
        }
        return -1;
    }

    public static String parseRemoteMediaIp(String str) {
        String[] split = str.split("\r\n");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("a=candidate:")) {
                String handleCandidate = handleCandidate(split[i]);
                return handleCandidate == null ? "" : handleCandidate;
            }
        }
        return "";
    }

    public static String handleCandidate(String str) {
        Matcher matcher = Pattern.compile("([0-9]{1,3}(\\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})").matcher(str);
        return matcher.find() ? matcher.group(1) : "";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class SdpStruct {
        public String header = new String();
        public Map<String, String> mids = new ConcurrentHashMap();
        public Map<String, SsrcInfo> midSsrcMap = new ConcurrentHashMap();

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class SsrcInfo {
            public String ssrc;
            public int type;

            public SsrcInfo(int i, String str) {
                this.type = i;
                this.ssrc = str;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.baidu.rtc.SdpPrefer.SdpStruct parseSdpStruct(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.rtc.SdpPrefer.parseSdpStruct(java.lang.String):com.baidu.rtc.SdpPrefer$SdpStruct");
    }

    public static String partialSdpCombine(String str, String str2) {
        Iterator it;
        SdpStruct parseSdpStruct = parseSdpStruct(str);
        for (Map.Entry<String, String> entry : parseSdpStruct(str2).mids.entrySet()) {
            parseSdpStruct.mids.put(entry.getKey(), entry.getValue());
        }
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.baidu.rtc.SdpPrefer.1
            @Override // java.util.Comparator
            public int compare(String str3, String str4) {
                return Integer.valueOf(str3).intValue() > Integer.valueOf(str4).intValue() ? 1 : -1;
            }
        });
        treeMap.putAll(parseSdpStruct.mids);
        String[] split = parseSdpStruct.header.split("\r\n");
        String str3 = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("a=group:BUNDLE")) {
                String str4 = "a=group:BUNDLE ";
                while (treeMap.entrySet().iterator().hasNext()) {
                    str4 = (str4 + ((String) ((Map.Entry) it.next()).getKey())) + " ";
                }
                split[i] = str4.substring(0, str4.length() - 1);
            }
            str3 = (str3 + split[i]) + "\r\n";
        }
        sb.append(str3);
        for (Map.Entry entry2 : treeMap.entrySet()) {
            sb.append(parseSdpStruct.mids.get(entry2.getKey()));
        }
        return sb.toString();
    }

    public static String partialLocalSdp(String str, String str2) {
        Iterator it;
        SdpStruct parseSdpStruct = parseSdpStruct(str);
        SdpStruct parseSdpStruct2 = parseSdpStruct(str2);
        StringBuilder sb = new StringBuilder();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (Map.Entry<String, String> entry : parseSdpStruct2.mids.entrySet()) {
            String str3 = parseSdpStruct.mids.get(entry.getKey());
            if (!TextUtils.isEmpty(str3)) {
                concurrentHashMap.put(entry.getKey(), str3);
            }
        }
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.baidu.rtc.SdpPrefer.2
            @Override // java.util.Comparator
            public int compare(String str4, String str5) {
                return Integer.valueOf(str4).intValue() > Integer.valueOf(str5).intValue() ? 1 : -1;
            }
        });
        treeMap.putAll(concurrentHashMap);
        String[] split = parseSdpStruct.header.split("\r\n");
        String str4 = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("a=group:BUNDLE")) {
                String str5 = "a=group:BUNDLE ";
                while (treeMap.entrySet().iterator().hasNext()) {
                    str5 = (str5 + ((String) ((Map.Entry) it.next()).getKey())) + " ";
                }
                split[i] = str5.substring(0, str5.length() - 1);
            }
            str4 = (str4 + split[i]) + "\r\n";
        }
        sb.append(str4);
        for (Map.Entry entry2 : treeMap.entrySet()) {
            sb.append((String) concurrentHashMap.get(entry2.getKey()));
        }
        return sb.toString();
    }

    public static String getMatchString(String str, String str2) {
        Matcher matcher = Pattern.compile(str2).matcher(str);
        if (matcher.find()) {
            return matcher.group(2).trim();
        }
        return null;
    }
}
