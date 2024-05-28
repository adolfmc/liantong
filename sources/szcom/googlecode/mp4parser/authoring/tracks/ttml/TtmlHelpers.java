package szcom.googlecode.mp4parser.authoring.tracks.ttml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import szcom.googlecode.mp4parser.authoring.Movie;
import szcom.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import szorg.w3c.dom.Document;
import szorg.w3c.dom.Node;
import szorg.w3c.dom.NodeList;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TtmlHelpers {
    public static final String SMPTE_TT_NAMESPACE = "http://www.smpte-ra.org/schemas/2052-1/2010/smpte-tt";
    public static final String TTML_NAMESPACE = "http://www.w3.org/ns/ttml";
    static byte[] namespacesStyleSheet1 = "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n    <xsl:output method=\"text\"/>\n    <xsl:key name=\"kElemByNSURI\"\n             match=\"*[namespace::*[not(. = ../../namespace::*)]]\"\n              use=\"namespace::*[not(. = ../../namespace::*)]\"/>\n    <xsl:template match=\"/\">\n        <xsl:for-each select=\n            \"//namespace::*[not(. = ../../namespace::*)]\n                           [count(..|key('kElemByNSURI',.)[1])=1]\">\n            <xsl:value-of select=\"concat(.,'&#xA;')\"/>\n        </xsl:for-each>\n    </xsl:template>\n</xsl:stylesheet>".getBytes();
    public static final NamespaceContext NAMESPACE_CONTEXT = new TextTrackNamespaceContext(null);

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    static class TextTrackNamespaceContext implements NamespaceContext {
        private TextTrackNamespaceContext() {
        }

        /* synthetic */ TextTrackNamespaceContext(TextTrackNamespaceContext textTrackNamespaceContext) {
            this();
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getNamespaceURI(String str) {
            if (str.equals("ttml")) {
                return "http://www.w3.org/ns/ttml";
            }
            if (str.equals("smpte")) {
                return "http://www.smpte-ra.org/schemas/2052-1/2010/smpte-tt";
            }
            return null;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getPrefix(String str) {
            if (str.equals("http://www.w3.org/ns/ttml")) {
                return "ttml";
            }
            if (str.equals("http://www.smpte-ra.org/schemas/2052-1/2010/smpte-tt")) {
                return "smpte";
            }
            return null;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public Iterator getPrefixes(String str) {
            return Arrays.asList("ttml", "smpte").iterator();
        }
    }

    private static long copyLarge(InputStream inputStream, File file) {
        byte[] bArr = new byte[16384];
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        long j = 0;
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    return j;
                }
                fileOutputStream.write(bArr, 0, read);
                j += read;
            } finally {
                fileOutputStream.close();
            }
        }
    }

    public static void deepCopyDocument(Document document, File file) {
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile("//*/@backgroundImage").evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                URI create = URI.create(nodeList.item(i).getNodeValue());
                if (!create.isAbsolute()) {
                    copyLarge(new URI(document.getDocumentURI()).resolve(create).toURL().openStream(), new File(file.toURI().resolve(create).toURL().getFile()));
                }
            }
            copyLarge(new URI(document.getDocumentURI()).toURL().openStream(), file);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        } catch (XPathExpressionException e2) {
            throw new IOException(e2);
        }
    }

    public static String[] getAllNamespaces(Document document) {
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer(new StreamSource(new ByteArrayInputStream(namespacesStyleSheet1)));
            StringWriter stringWriter = new StringWriter();
            newTransformer.transform(new DOMSource((Node) document), new StreamResult(stringWriter));
            ArrayList arrayList = new ArrayList(new LinkedHashSet(Arrays.asList(stringWriter.getBuffer().toString().split("\n"))));
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static long getEndTime(Node node) {
        long j = 0;
        Node node2 = node;
        while (true) {
            node2 = node2.getParentNode();
            if (node2 == null) {
                break;
            } else if (node2.getAttributes() != null && node2.getAttributes().getNamedItem("begin") != null) {
                j += toTime(node2.getAttributes().getNamedItem("begin").getNodeValue());
            }
        }
        return (node.getAttributes() == null || node.getAttributes().getNamedItem("end") == null) ? j : j + toTime(node.getAttributes().getNamedItem("end").getNodeValue());
    }

    public static long getStartTime(Node node) {
        long j = 0;
        Node node2 = node;
        while (true) {
            node2 = node2.getParentNode();
            if (node2 == null) {
                break;
            } else if (node2.getAttributes() != null && node2.getAttributes().getNamedItem("begin") != null) {
                j += toTime(node2.getAttributes().getNamedItem("begin").getNodeValue());
            }
        }
        return (node.getAttributes() == null || node.getAttributes().getNamedItem("begin") == null) ? j : j + toTime(node.getAttributes().getNamedItem("begin").getNodeValue());
    }

    public static void main(String[] strArr) {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        TtmlTrackImpl ttmlTrackImpl = new TtmlTrackImpl("a.xml", TtmlSegmenter.split(newInstance.newDocumentBuilder().parse("C:\\dev\\mp4parser\\a.xml"), 60));
        Movie movie = new Movie();
        movie.addTrack(ttmlTrackImpl);
        new DefaultMp4Builder().build(movie).writeContainer(new FileOutputStream("output.mp4").getChannel());
    }

    public static void pretty(Document document, OutputStream outputStream, int i) {
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("encoding", "UTF-8");
            if (i > 0) {
                newTransformer.setOutputProperty("indent", "yes");
                newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(i));
            }
            try {
                newTransformer.transform(new DOMSource((Node) document), new StreamResult(outputStream));
            } catch (TransformerException e) {
                throw new IOException(e);
            }
        } catch (TransformerConfigurationException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static long toTime(String str) {
        double d;
        double parseDouble;
        Matcher matcher = Pattern.compile("(-?)([0-9][0-9]):([0-9][0-9]):([0-9][0-9])([\\.:][0-9][0-9]?[0-9]?)?").matcher(str);
        if (!matcher.matches()) {
            throw new RuntimeException("Cannot match '" + str + "' to time expression");
        }
        String group2 = matcher.group(1);
        String group3 = matcher.group(2);
        String group4 = matcher.group(3);
        String group5 = matcher.group(4);
        String group6 = matcher.group(5);
        if (group6 == null) {
            group6 = ".000";
        }
        String replace = group6.replace(":", ".");
        long parseLong = (Long.parseLong(group3) * 60 * 60 * 1000) + (Long.parseLong(group4) * 60 * 1000) + (Long.parseLong(group5) * 1000);
        if (replace.contains(":")) {
            d = parseLong;
            parseDouble = Double.parseDouble("0" + replace.replace(":", ".")) * 40.0d;
        } else {
            d = parseLong;
            parseDouble = Double.parseDouble("0" + replace);
        }
        return ((long) (d + (parseDouble * 1000.0d))) * ("-".equals(group2) ? -1 : 1);
    }

    public static String toTimeExpression(long j) {
        return toTimeExpression(j, -1);
    }

    public static String toTimeExpression(long j, int i) {
        String str = j >= 0 ? "" : "-";
        long abs = Math.abs(j);
        long j2 = ((abs / 1000) / 60) / 60;
        long j3 = abs - (((j2 * 1000) * 60) * 60);
        long j4 = (j3 / 1000) / 60;
        long j5 = j3 - ((j4 * 1000) * 60);
        long j6 = j5 / 1000;
        return i >= 0 ? String.format("%s%02d:%02d:%02d:%d", str, Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j6), Integer.valueOf(i)) : String.format("%s%02d:%02d:%02d.%03d", str, Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j6), Long.valueOf(j5 - (1000 * j6)));
    }
}
