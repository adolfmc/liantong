package szcom.googlecode.mp4parser.authoring.tracks.ttml;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.googlecode.mp4parser.authoring.AbstractTrack;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.mp4parser.iso14496.part30.XMLSubtitleSampleEntry;
import szorg.w3c.dom.Document;
import szorg.w3c.dom.Node;
import szorg.w3c.dom.NodeList;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TtmlTrackImpl extends AbstractTrack {
    SampleDescriptionBox sampleDescriptionBox;
    private long[] sampleDurations;
    List<Sample> samples;
    SubSampleInformationBox subSampleInformationBox;
    TrackMetaData trackMetaData;
    XMLSubtitleSampleEntry xmlSubtitleSampleEntry;

    public TtmlTrackImpl(String str, List<Document> list) {
        super(str);
        this.trackMetaData = new TrackMetaData();
        this.sampleDescriptionBox = new SampleDescriptionBox();
        this.xmlSubtitleSampleEntry = new XMLSubtitleSampleEntry();
        this.samples = new ArrayList();
        this.subSampleInformationBox = new SubSampleInformationBox();
        extractLanguage(list);
        HashSet hashSet = new HashSet();
        this.sampleDurations = new long[list.size()];
        XPathFactory.newInstance().newXPath().setNamespaceContext(TtmlHelpers.NAMESPACE_CONTEXT);
        for (int i = 0; i < list.size(); i++) {
            Document document = list.get(i);
            SubSampleInformationBox.SubSampleEntry subSampleEntry = new SubSampleInformationBox.SubSampleEntry();
            this.subSampleInformationBox.getEntries().add(subSampleEntry);
            subSampleEntry.setSampleDelta(1L);
            this.sampleDurations[i] = extractDuration(document);
            List<byte[]> extractImages = extractImages(document);
            hashSet.addAll(extractMimeTypes(document));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            TtmlHelpers.pretty(document, byteArrayOutputStream, 4);
            SubSampleInformationBox.SubSampleEntry.SubsampleEntry subsampleEntry = new SubSampleInformationBox.SubSampleEntry.SubsampleEntry();
            subsampleEntry.setSubsampleSize(byteArrayOutputStream.size());
            subSampleEntry.getSubsampleEntries().add(subsampleEntry);
            for (byte[] bArr : extractImages) {
                byteArrayOutputStream.write(bArr);
                SubSampleInformationBox.SubSampleEntry.SubsampleEntry subsampleEntry2 = new SubSampleInformationBox.SubSampleEntry.SubsampleEntry();
                subsampleEntry2.setSubsampleSize(bArr.length);
                subSampleEntry.getSubsampleEntries().add(subsampleEntry2);
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            this.samples.add(new Sample() { // from class: szcom.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl.1
                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public ByteBuffer asByteBuffer() {
                    return ByteBuffer.wrap(byteArray);
                }

                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public long getSize() {
                    return byteArray.length;
                }

                @Override // szcom.googlecode.mp4parser.authoring.Sample
                public void writeTo(WritableByteChannel writableByteChannel) {
                    writableByteChannel.write(ByteBuffer.wrap(byteArray));
                }
            });
        }
        this.xmlSubtitleSampleEntry.setNamespace(join(",", TtmlHelpers.getAllNamespaces(list.get(0))));
        this.xmlSubtitleSampleEntry.setSchemaLocation("");
        this.xmlSubtitleSampleEntry.setAuxiliaryMimeTypes(join(",", (String[]) new ArrayList(hashSet).toArray(new String[hashSet.size()])));
        this.sampleDescriptionBox.addBox(this.xmlSubtitleSampleEntry);
        this.trackMetaData.setTimescale(30000L);
        this.trackMetaData.setLayer(65535);
    }

    protected static List<byte[]> extractImages(Document document) {
        NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile("//*/@backgroundImage").evaluate(document, XPathConstants.NODESET);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 1;
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            Node item = nodeList.item(i2);
            String nodeValue = item.getNodeValue();
            String substring = nodeValue.substring(nodeValue.lastIndexOf("."));
            String str = (String) linkedHashMap.get(nodeValue);
            if (str == null) {
                str = "urn:mp4parser:" + i + substring;
                linkedHashMap.put(str, nodeValue);
                i++;
            }
            item.setNodeValue(str);
        }
        ArrayList arrayList = new ArrayList();
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                arrayList.add(streamToByteArray(new URI(document.getDocumentURI()).resolve((String) entry.getValue()).toURL().openStream()));
            }
        }
        return arrayList;
    }

    public static String getLanguage(Document document) {
        return document.getDocumentElement().getAttribute("xml:lang");
    }

    private static String join(String str, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            sb.append(str2);
            sb.append(str);
        }
        sb.setLength(sb.length() > 0 ? sb.length() - 1 : 0);
        return sb.toString();
    }

    private static long latestTimestamp(Document document) {
        XPath newXPath = XPathFactory.newInstance().newXPath();
        newXPath.setNamespaceContext(TtmlHelpers.NAMESPACE_CONTEXT);
        try {
            NodeList nodeList = (NodeList) newXPath.compile("//*[name()='p']").evaluate(document, XPathConstants.NODESET);
            long j = 0;
            for (int i = 0; i < nodeList.getLength(); i++) {
                j = Math.max(TtmlHelpers.getEndTime(nodeList.item(i)), j);
            }
            return j;
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] streamToByteArray(InputStream inputStream) {
        byte[] bArr = new byte[8096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    long extractDuration(Document document) {
        return "subt";
    }

    protected void extractLanguage(List<Document> list) {
    }

    protected List<String> extractMimeTypes(Document document) {
        return "subt";
    }

    protected long firstTimestamp(Document document) {
        return "subt";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "subt";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<szcom.googlecode.mp4parser.authoring.Sample>, long[]] */
    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.samples;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.subSampleInformationBox;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    protected long lastTimestamp(szorg.w3c.dom.Document r0) {
        /*
            r-1 = this;
            java.lang.String r0 = "^\ufeff?WEBVTT((\\u0020|\t).*)?$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack.WEBVTT_FILE_HEADER = r0
            java.lang.String r0 = "\\S*[:=]\\S*"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack.WEBVTT_METADATA_HEADER = r0
            java.lang.String r0 = "^(?!.*(-->)).*$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack.WEBVTT_CUE_IDENTIFIER = r0
            java.lang.String r0 = "(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{3}"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack.WEBVTT_TIMESTAMP = r0
            java.lang.String r0 = "\\S*:\\S*"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack.WEBVTT_CUE_SETTING = r0
            szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack$1 r0 = new szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack$1
            r0.<init>()
            szcom.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack.EMPTY_SAMPLE = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: szcom.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl.lastTimestamp(szorg.w3c.dom.Document):long");
    }
}
