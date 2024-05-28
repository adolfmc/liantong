package szcom.coremedia.iso;

import java.io.Closeable;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.MovieBox;
import szcom.googlecode.mp4parser.BasicContainer;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.FileDataSourceImpl;
import szcom.googlecode.mp4parser.annotations.DoNotParseDetail;
import szcom.googlecode.mp4parser.util.Logger;

@DoNotParseDetail
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class IsoFile extends BasicContainer implements Closeable {
    private static Logger LOG = Logger.getLogger(IsoFile.class);

    public IsoFile(String str) {
        this(new FileDataSourceImpl(new File(str)));
    }

    public IsoFile(DataSource dataSource) {
        this(dataSource, new PropertyBoxParserImpl(new String[0]));
    }

    public IsoFile(DataSource dataSource, BoxParser boxParser) {
        initContainer(dataSource, dataSource.size(), boxParser);
    }

    public static String bytesToFourCC(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        if (bArr != null) {
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, 4));
        }
        try {
            return new String(bArr2, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new Error("Required character encoding is missing", e);
        }
    }

    public static byte[] fourCCtoBytes(String str) {
        byte[] bArr = new byte[4];
        if (str != null) {
            for (int i = 0; i < Math.min(4, str.length()); i++) {
                bArr[i] = (byte) str.charAt(i);
            }
        }
        return bArr;
    }

    @Override // szcom.googlecode.mp4parser.BasicContainer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.dataSource.close();
    }

    public void getBox(WritableByteChannel writableByteChannel) {
        writeContainer(writableByteChannel);
    }

    public MovieBox getMovieBox() {
        for (Box box : getBoxes()) {
            if (box instanceof MovieBox) {
                return (MovieBox) box;
            }
        }
        return null;
    }

    public long getSize() {
        return getContainerSize();
    }

    @Override // szcom.googlecode.mp4parser.BasicContainer
    public String toString() {
        return "model(" + this.dataSource.toString() + ")";
    }
}
