package szcom.googlecode.mp4parser.boxes.microsoft;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class XtraBox extends AbstractBox {
    private static final long FILETIME_EPOCH_DIFF = 11644473600000L;
    private static final long FILETIME_ONE_MILLISECOND = 10000;
    public static final int MP4_XTRA_BT_FILETIME = 21;
    public static final int MP4_XTRA_BT_GUID = 72;
    public static final int MP4_XTRA_BT_INT64 = 19;
    public static final int MP4_XTRA_BT_UNICODE = 8;
    public static final String TYPE = "Xtra";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    ByteBuffer data;
    private boolean successfulParse;
    Vector<XtraTag> tags;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class XtraTag {
        private int inputSize;
        private String tagName;
        private Vector<XtraValue> values;

        private XtraTag() {
            this.values = new Vector<>();
        }

        private XtraTag(String str) {
            this();
            this.tagName = str;
        }

        /* synthetic */ XtraTag(String str, XtraTag xtraTag) {
            this(str);
        }

        /* synthetic */ XtraTag(XtraTag xtraTag) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void getContent(ByteBuffer byteBuffer) {
            byteBuffer.putInt(getContentSize());
            byteBuffer.putInt(this.tagName.length());
            XtraBox.writeAsciiString(byteBuffer, this.tagName);
            byteBuffer.putInt(this.values.size());
            for (int i = 0; i < this.values.size(); i++) {
                this.values.elementAt(i).getContent(byteBuffer);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentSize() {
            int length = this.tagName.length() + 12;
            for (int i = 0; i < this.values.size(); i++) {
                length += this.values.elementAt(i).getContentSize();
            }
            return length;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void parse(ByteBuffer byteBuffer) {
            this.inputSize = byteBuffer.getInt();
            this.tagName = XtraBox.readAsciiString(byteBuffer, byteBuffer.getInt());
            int i = byteBuffer.getInt();
            for (int i2 = 0; i2 < i; i2++) {
                XtraValue xtraValue = new XtraValue((XtraValue) null);
                xtraValue.parse(byteBuffer);
                this.values.addElement(xtraValue);
            }
            if (this.inputSize == getContentSize()) {
                return;
            }
            throw new RuntimeException("Improperly handled Xtra tag: Sizes don't match ( " + this.inputSize + "/" + getContentSize() + ") on " + this.tagName);
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.tagName);
            stringBuffer.append(" [");
            stringBuffer.append(this.inputSize);
            stringBuffer.append("/");
            stringBuffer.append(this.values.size());
            stringBuffer.append("]:\n");
            for (int i = 0; i < this.values.size(); i++) {
                stringBuffer.append("  ");
                stringBuffer.append(this.values.elementAt(i).toString());
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class XtraValue {
        public Date fileTimeValue;
        public long longValue;
        public byte[] nonParsedValue;
        public String stringValue;
        public int type;

        private XtraValue() {
        }

        private XtraValue(long j) {
            this.type = 19;
            this.longValue = j;
        }

        /* synthetic */ XtraValue(long j, XtraValue xtraValue) {
            this(j);
        }

        private XtraValue(String str) {
            this.type = 8;
            this.stringValue = str;
        }

        /* synthetic */ XtraValue(String str, XtraValue xtraValue) {
            this(str);
        }

        private XtraValue(Date date) {
            this.type = 21;
            this.fileTimeValue = date;
        }

        /* synthetic */ XtraValue(Date date, XtraValue xtraValue) {
            this(date);
        }

        /* synthetic */ XtraValue(XtraValue xtraValue) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void getContent(ByteBuffer byteBuffer) {
            long j;
            try {
                byteBuffer.putInt(getContentSize());
                byteBuffer.putShort((short) this.type);
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                int i = this.type;
                if (i != 8) {
                    if (i == 19) {
                        j = this.longValue;
                    } else if (i != 21) {
                        byteBuffer.put(this.nonParsedValue);
                    } else {
                        j = XtraBox.millisToFiletime(this.fileTimeValue.getTime());
                    }
                    byteBuffer.putLong(j);
                } else {
                    XtraBox.writeUtf16String(byteBuffer, this.stringValue);
                }
            } finally {
                byteBuffer.order(ByteOrder.BIG_ENDIAN);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentSize() {
            int length;
            int i = this.type;
            if (i == 8) {
                length = (this.stringValue.length() * 2) + 2;
            } else if (i == 19 || i == 21) {
                return 14;
            } else {
                length = this.nonParsedValue.length;
            }
            return length + 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object getValueAsObject() {
            int i = this.type;
            return i != 8 ? i != 19 ? i != 21 ? this.nonParsedValue : this.fileTimeValue : new Long(this.longValue) : this.stringValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void parse(ByteBuffer byteBuffer) {
            int i = byteBuffer.getInt() - 6;
            this.type = byteBuffer.getShort();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            int i2 = this.type;
            if (i2 == 8) {
                this.stringValue = XtraBox.readUtf16String(byteBuffer, i);
            } else if (i2 == 19) {
                this.longValue = byteBuffer.getLong();
            } else if (i2 != 21) {
                this.nonParsedValue = new byte[i];
                byteBuffer.get(this.nonParsedValue);
            } else {
                this.fileTimeValue = new Date(XtraBox.filetimeToMillis(byteBuffer.getLong()));
            }
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        public String toString() {
            StringBuilder sb;
            String str;
            int i = this.type;
            if (i == 8) {
                sb = new StringBuilder("[string]");
                str = this.stringValue;
            } else if (i == 19) {
                sb = new StringBuilder("[long]");
                str = String.valueOf(this.longValue);
            } else if (i != 21) {
                return "[GUID](nonParsed)";
            } else {
                sb = new StringBuilder("[filetime]");
                str = this.fileTimeValue.toString();
            }
            sb.append(str);
            return sb.toString();
        }
    }

    static {
        ajc$preClinit();
    }

    public XtraBox() {
        super(TYPE);
        this.successfulParse = false;
        this.tags = new Vector<>();
    }

    public XtraBox(String str) {
        super(str);
        this.successfulParse = false;
        this.tags = new Vector<>();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("XtraBox.java", XtraBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "", "", "", "java.lang.String"), 88);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAllTagNames", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "", "", "", "[Ljava.lang.String;"), 151);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTagValue", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String:long", "name:value", "", "void"), 289);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFirstStringValue", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "java.lang.String"), 166);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFirstDateValue", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "java.util.Date"), 183);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFirstLongValue", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "java.lang.Long"), 200);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getValues", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "[Ljava.lang.Object;"), 216);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "removeTag", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "void"), 236);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTagValues", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String:[Ljava.lang.String;", "name:values", "", "void"), 249);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTagValue", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String:java.lang.String", "name:value", "", "void"), 265);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTagValue", "szcom.googlecode.mp4parser.boxes.microsoft.XtraBox", "java.lang.String:java.util.Date", "name:date", "", "void"), 276);
    }

    private int detailSize() {
        int i = 0;
        for (int i2 = 0; i2 < this.tags.size(); i2++) {
            i += this.tags.elementAt(i2).getContentSize();
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long filetimeToMillis(long j) {
        return (j / 10000) - FILETIME_EPOCH_DIFF;
    }

    private XtraTag getTagByName(String str) {
        Iterator<XtraTag> it = this.tags.iterator();
        while (it.hasNext()) {
            XtraTag next = it.next();
            if (next.tagName.equals(str)) {
                return next;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long millisToFiletime(long j) {
        return (j + FILETIME_EPOCH_DIFF) * 10000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String readAsciiString(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Shouldn't happen", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String readUtf16String(ByteBuffer byteBuffer, int i) {
        int i2 = (i / 2) - 1;
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = byteBuffer.getChar();
        }
        byteBuffer.getChar();
        return new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeAsciiString(ByteBuffer byteBuffer, String str) {
        try {
            byteBuffer.put(str.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Shouldn't happen", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeUtf16String(ByteBuffer byteBuffer, String str) {
        for (char c : str.toCharArray()) {
            byteBuffer.putChar(c);
        }
        byteBuffer.putChar((char) 0);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        int detailSize;
        int remaining = byteBuffer.remaining();
        this.data = byteBuffer.slice();
        this.successfulParse = false;
        try {
            try {
                this.tags.clear();
                while (byteBuffer.remaining() > 0) {
                    XtraTag xtraTag = new XtraTag((XtraTag) null);
                    xtraTag.parse(byteBuffer);
                    this.tags.addElement(xtraTag);
                }
                detailSize = detailSize();
            } catch (Exception e) {
                this.successfulParse = false;
                PrintStream printStream = System.err;
                printStream.println("Malformed Xtra Tag detected: " + e.toString());
                e.printStackTrace();
                byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
            }
            if (remaining == detailSize) {
                this.successfulParse = true;
                return;
            }
            throw new RuntimeException("Improperly handled Xtra tag: Calculated sizes don't match ( " + remaining + "/" + detailSize + ")");
        } finally {
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }
    }

    public String[] getAllTagNames() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        String[] strArr = new String[this.tags.size()];
        for (int i = 0; i < this.tags.size(); i++) {
            strArr[i] = this.tags.elementAt(i).tagName;
        }
        return strArr;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        if (!this.successfulParse) {
            this.data.rewind();
            byteBuffer.put(this.data);
            return;
        }
        for (int i = 0; i < this.tags.size(); i++) {
            this.tags.elementAt(i).getContent(byteBuffer);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.successfulParse ? detailSize() : this.data.limit();
    }

    public Date getFirstDateValue(String str) {
        Object[] values;
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        for (Object obj : getValues(str)) {
            if (obj instanceof Date) {
                return (Date) obj;
            }
        }
        return null;
    }

    public Long getFirstLongValue(String str) {
        Object[] values;
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, str));
        for (Object obj : getValues(str)) {
            if (obj instanceof Long) {
                return (Long) obj;
            }
        }
        return null;
    }

    public String getFirstStringValue(String str) {
        Object[] values;
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, str));
        for (Object obj : getValues(str)) {
            if (obj instanceof String) {
                return (String) obj;
            }
        }
        return null;
    }

    public Object[] getValues(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, str));
        XtraTag tagByName = getTagByName(str);
        if (tagByName != null) {
            Object[] objArr = new Object[tagByName.values.size()];
            for (int i = 0; i < tagByName.values.size(); i++) {
                objArr[i] = ((XtraValue) tagByName.values.elementAt(i)).getValueAsObject();
            }
            return objArr;
        }
        return new Object[0];
    }

    public void removeTag(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, str));
        XtraTag tagByName = getTagByName(str);
        if (tagByName != null) {
            this.tags.remove(tagByName);
        }
    }

    public void setTagValue(String str, long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, str, Conversions.longObject(j)));
        removeTag(str);
        XtraTag xtraTag = new XtraTag(str, null);
        xtraTag.values.addElement(new XtraValue(j, (XtraValue) null));
        this.tags.addElement(xtraTag);
    }

    public void setTagValue(String str, String str2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this, str, str2));
        setTagValues(str, new String[]{str2});
    }

    public void setTagValue(String str, Date date) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, str, date));
        removeTag(str);
        XtraTag xtraTag = new XtraTag(str, null);
        xtraTag.values.addElement(new XtraValue(date, (XtraValue) null));
        this.tags.addElement(xtraTag);
    }

    public void setTagValues(String str, String[] strArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, str, strArr));
        removeTag(str);
        XtraTag xtraTag = new XtraTag(str, null);
        for (String str2 : strArr) {
            xtraTag.values.addElement(new XtraValue(str2, (XtraValue) null));
        }
        this.tags.addElement(xtraTag);
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        if (!isParsed()) {
            parseDetails();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("XtraBox[");
        Iterator<XtraTag> it = this.tags.iterator();
        while (it.hasNext()) {
            XtraTag next = it.next();
            Iterator it2 = next.values.iterator();
            while (it2.hasNext()) {
                stringBuffer.append(next.tagName);
                stringBuffer.append("=");
                stringBuffer.append(((XtraValue) it2.next()).toString());
                stringBuffer.append(";");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
