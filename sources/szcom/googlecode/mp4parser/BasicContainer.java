package szcom.googlecode.mp4parser;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.LazyList;
import szcom.googlecode.mp4parser.util.Logger;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class BasicContainer implements Closeable, Iterator<Box>, Container {
    private static final Box EOF = new AbstractBox("eof ") { // from class: szcom.googlecode.mp4parser.BasicContainer.1
        @Override // szcom.googlecode.mp4parser.AbstractBox
        protected void _parseDetails(ByteBuffer byteBuffer) {
        }

        @Override // szcom.googlecode.mp4parser.AbstractBox
        protected void getContent(ByteBuffer byteBuffer) {
        }

        @Override // szcom.googlecode.mp4parser.AbstractBox
        protected long getContentSize() {
            return 0L;
        }
    };
    private static Logger LOG = Logger.getLogger(BasicContainer.class);
    protected BoxParser boxParser;
    protected DataSource dataSource;
    Box lookahead = null;
    long parsePosition = 0;
    long startPosition = 0;
    long endPosition = 0;
    private List<Box> boxes = new ArrayList();

    public void addBox(Box box) {
        if (box != null) {
            this.boxes = new ArrayList(getBoxes());
            box.setParent(this);
            this.boxes.add(box);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.dataSource.close();
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public List<Box> getBoxes() {
        return (this.dataSource == null || this.lookahead == EOF) ? this.boxes : new LazyList(this.boxes, this);
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public <T extends Box> List<T> getBoxes(Class<T> cls) {
        List<Box> boxes = getBoxes();
        ArrayList arrayList = null;
        Box box = null;
        for (int i = 0; i < boxes.size(); i++) {
            Box box2 = boxes.get(i);
            if (cls.isInstance(box2)) {
                if (box == null) {
                    box = box2;
                } else {
                    if (arrayList == null) {
                        arrayList = new ArrayList(2);
                        arrayList.add(box);
                    }
                    arrayList.add(box2);
                }
            }
        }
        return arrayList != null ? arrayList : box != null ? Collections.singletonList(box) : Collections.emptyList();
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public <T extends Box> List<T> getBoxes(Class<T> cls, boolean z) {
        ArrayList arrayList = new ArrayList(2);
        List<Box> boxes = getBoxes();
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);
            if (cls.isInstance(box)) {
                arrayList.add(box);
            }
            if (z && (box instanceof Container)) {
                arrayList.addAll(((Container) box).getBoxes(cls, z));
            }
        }
        return arrayList;
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public ByteBuffer getByteBuffer(long j, long j2) {
        long j3;
        long size;
        ByteBuffer map;
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            synchronized (dataSource) {
                map = this.dataSource.map(this.startPosition + j, j2);
            }
            return map;
        }
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(j2));
        long j4 = j + j2;
        long j5 = 0;
        for (Box box : this.boxes) {
            long size2 = box.getSize() + j5;
            if (size2 > j && j5 < j4) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                WritableByteChannel newChannel = Channels.newChannel(byteArrayOutputStream);
                box.getBox(newChannel);
                newChannel.close();
                int i = (j5 > j ? 1 : (j5 == j ? 0 : -1));
                if (i < 0 || size2 > j4) {
                    if (i < 0 && size2 > j4) {
                        j3 = j - j5;
                        size = (box.getSize() - j3) - (size2 - j4);
                    } else if (i < 0 && size2 <= j4) {
                        j3 = j - j5;
                        size = box.getSize() - j3;
                    } else if (i >= 0 && size2 > j4) {
                        allocate.put(byteArrayOutputStream.toByteArray(), 0, CastUtils.l2i(box.getSize() - (size2 - j4)));
                    }
                    allocate.put(byteArrayOutputStream.toByteArray(), CastUtils.l2i(j3), CastUtils.l2i(size));
                } else {
                    allocate.put(byteArrayOutputStream.toByteArray());
                }
            }
            j5 = size2;
        }
        return (ByteBuffer) allocate.rewind();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getContainerSize() {
        long j = 0;
        for (int i = 0; i < getBoxes().size(); i++) {
            j += this.boxes.get(i).getSize();
        }
        return j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        Box box = this.lookahead;
        if (box == EOF) {
            return false;
        }
        if (box != null) {
            return true;
        }
        try {
            this.lookahead = next();
            return true;
        } catch (NoSuchElementException unused) {
            this.lookahead = EOF;
            return false;
        }
    }

    public void initContainer(DataSource dataSource, long j, BoxParser boxParser) {
        this.dataSource = dataSource;
        long position = dataSource.position();
        this.startPosition = position;
        this.parsePosition = position;
        dataSource.position(dataSource.position() + j);
        this.endPosition = dataSource.position();
        this.boxParser = boxParser;
    }

    @Override // java.util.Iterator
    public Box next() {
        Box parseBox;
        Box box = this.lookahead;
        if (box != null && box != EOF) {
            this.lookahead = null;
            return box;
        }
        DataSource dataSource = this.dataSource;
        if (dataSource == null || this.parsePosition >= this.endPosition) {
            this.lookahead = EOF;
            throw new NoSuchElementException();
        }
        try {
            synchronized (dataSource) {
                this.dataSource.position(this.parsePosition);
                parseBox = this.boxParser.parseBox(this.dataSource, this);
                this.parsePosition = this.dataSource.position();
            }
            return parseBox;
        } catch (EOFException unused) {
            throw new NoSuchElementException();
        } catch (IOException unused2) {
            throw new NoSuchElementException();
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void setBoxes(List<Box> list) {
        this.boxes = new ArrayList(list);
        this.lookahead = EOF;
        this.dataSource = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        for (int i = 0; i < this.boxes.size(); i++) {
            if (i > 0) {
                sb.append(";");
            }
            sb.append(this.boxes.get(i).toString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public final void writeContainer(WritableByteChannel writableByteChannel) {
        for (Box box : getBoxes()) {
            box.getBox(writableByteChannel);
        }
    }
}
