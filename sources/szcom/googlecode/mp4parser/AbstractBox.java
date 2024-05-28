package szcom.googlecode.mp4parser;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.Hex;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.annotations.DoNotParseDetail;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.Logger;
import szcom.googlecode.mp4parser.util.Path;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AbstractBox implements Box {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Logger LOG = Logger.getLogger(AbstractBox.class);
    private ByteBuffer content;
    DataSource dataSource;
    private ByteBuffer deadBytes = null;
    boolean isParsed = true;
    long offset;
    private Container parent;
    protected String type;
    private byte[] userType;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBox(String str) {
        this.type = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBox(String str, byte[] bArr) {
        this.type = str;
        this.userType = bArr;
    }

    private void getHeader(ByteBuffer byteBuffer) {
        if (isSmallBox()) {
            IsoTypeWriter.writeUInt32(byteBuffer, getSize());
            byteBuffer.put(IsoFile.fourCCtoBytes(getType()));
        } else {
            IsoTypeWriter.writeUInt32(byteBuffer, 1L);
            byteBuffer.put(IsoFile.fourCCtoBytes(getType()));
            IsoTypeWriter.writeUInt64(byteBuffer, getSize());
        }
        if ("uuid".equals(getType())) {
            byteBuffer.put(getUserType());
        }
    }

    private boolean isSmallBox() {
        int i = "uuid".equals(getType()) ? 24 : 8;
        if (!this.isParsed) {
            return ((long) (this.content.limit() + i)) < 4294967296L;
        }
        long contentSize = getContentSize();
        ByteBuffer byteBuffer = this.deadBytes;
        return (contentSize + ((long) (byteBuffer != null ? byteBuffer.limit() : 0))) + ((long) i) < 4294967296L;
    }

    private boolean verify(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(getContentSize() + (this.deadBytes != null ? byteBuffer2.limit() : 0)));
        getContent(allocate);
        ByteBuffer byteBuffer3 = this.deadBytes;
        if (byteBuffer3 != null) {
            byteBuffer3.rewind();
            while (this.deadBytes.remaining() > 0) {
                allocate.put(this.deadBytes);
            }
        }
        byteBuffer.rewind();
        allocate.rewind();
        if (byteBuffer.remaining() != allocate.remaining()) {
            System.err.print(String.valueOf(getType()) + ": remaining differs " + byteBuffer.remaining() + " vs. " + allocate.remaining());
            LOG.logError(String.valueOf(getType()) + ": remaining differs " + byteBuffer.remaining() + " vs. " + allocate.remaining());
            return false;
        }
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - 1;
        int limit2 = allocate.limit() - 1;
        while (limit >= position) {
            byte b = byteBuffer.get(limit);
            byte b2 = allocate.get(limit2);
            if (b != b2) {
                LOG.logError(String.format("%s: buffers differ at %d: %2X/%2X", getType(), Integer.valueOf(limit), Byte.valueOf(b), Byte.valueOf(b2)));
                byte[] bArr = new byte[byteBuffer.remaining()];
                byte[] bArr2 = new byte[allocate.remaining()];
                byteBuffer.get(bArr);
                allocate.get(bArr2);
                System.err.println("original      : " + Hex.encodeHex(bArr, 4));
                System.err.println("reconstructed : " + Hex.encodeHex(bArr2, 4));
                return false;
            }
            limit--;
            limit2--;
        }
        return true;
    }

    protected abstract void _parseDetails(ByteBuffer byteBuffer);

    @Override // szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        Buffer position;
        if (this.isParsed) {
            ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(getSize()));
            getHeader(allocate);
            getContent(allocate);
            ByteBuffer byteBuffer = this.deadBytes;
            if (byteBuffer != null) {
                byteBuffer.rewind();
                while (this.deadBytes.remaining() > 0) {
                    allocate.put(this.deadBytes);
                }
            }
            position = allocate.rewind();
        } else {
            ByteBuffer allocate2 = ByteBuffer.allocate((isSmallBox() ? 8 : 16) + ("uuid".equals(getType()) ? 16 : 0));
            getHeader(allocate2);
            writableByteChannel.write((ByteBuffer) allocate2.rewind());
            position = this.content.position(0);
        }
        writableByteChannel.write((ByteBuffer) position);
    }

    protected abstract void getContent(ByteBuffer byteBuffer);

    protected abstract long getContentSize();

    @Override // szcom.coremedia.iso.boxes.Box
    public long getOffset() {
        return this.offset;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    @DoNotParseDetail
    public Container getParent() {
        return this.parent;
    }

    @DoNotParseDetail
    public String getPath() {
        return Path.createPath(this);
    }

    @Override // szcom.coremedia.iso.boxes.Box
    public long getSize() {
        long limit;
        ByteBuffer byteBuffer;
        if (this.isParsed) {
            limit = getContentSize();
        } else {
            ByteBuffer byteBuffer2 = this.content;
            limit = byteBuffer2 != null ? byteBuffer2.limit() : 0;
        }
        return limit + (limit >= 4294967288L ? 8 : 0) + 8 + ("uuid".equals(getType()) ? 16 : 0) + (this.deadBytes != null ? byteBuffer.limit() : 0);
    }

    @Override // szcom.coremedia.iso.boxes.Box
    @DoNotParseDetail
    public String getType() {
        return this.type;
    }

    @DoNotParseDetail
    public byte[] getUserType() {
        return this.userType;
    }

    public boolean isParsed() {
        return this.isParsed;
    }

    @Override // szcom.coremedia.iso.boxes.Box
    @DoNotParseDetail
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        this.offset = dataSource.position() - byteBuffer.remaining();
        this.dataSource = dataSource;
        this.content = ByteBuffer.allocate(CastUtils.l2i(j));
        while (this.content.remaining() > 0) {
            dataSource.read(this.content);
        }
        this.content.position(0);
        this.isParsed = false;
    }

    public final synchronized void parseDetails() {
        Logger logger = LOG;
        logger.logDebug("parsing details of " + getType());
        if (this.content != null) {
            ByteBuffer byteBuffer = this.content;
            this.isParsed = true;
            byteBuffer.rewind();
            _parseDetails(byteBuffer);
            if (byteBuffer.remaining() > 0) {
                this.deadBytes = byteBuffer.slice();
            }
            this.content = null;
        }
    }

    @Override // szcom.coremedia.iso.boxes.Box
    @DoNotParseDetail
    public void setParent(Container container) {
        this.parent = container;
    }
}
