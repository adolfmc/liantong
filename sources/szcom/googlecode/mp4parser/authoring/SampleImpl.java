package szcom.googlecode.mp4parser.authoring;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SampleImpl implements Sample {
    private ByteBuffer[] data;
    private final long offset;
    private final Container parent;
    private final long size;

    public SampleImpl(long j, long j2, ByteBuffer byteBuffer) {
        this.offset = j;
        this.size = j2;
        this.data = new ByteBuffer[]{byteBuffer};
        this.parent = null;
    }

    public SampleImpl(long j, long j2, Container container) {
        this.offset = j;
        this.size = j2;
        this.data = null;
        this.parent = container;
    }

    public SampleImpl(ByteBuffer byteBuffer) {
        this.offset = -1L;
        this.size = byteBuffer.limit();
        this.data = new ByteBuffer[]{byteBuffer};
        this.parent = null;
    }

    public SampleImpl(ByteBuffer[] byteBufferArr) {
        this.offset = -1L;
        int i = 0;
        for (ByteBuffer byteBuffer : byteBufferArr) {
            i += byteBuffer.remaining();
        }
        this.size = i;
        this.data = byteBufferArr;
        this.parent = null;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Sample
    public ByteBuffer asByteBuffer() {
        ensureData();
        ByteBuffer wrap = ByteBuffer.wrap(new byte[CastUtils.l2i(this.size)]);
        for (ByteBuffer byteBuffer : this.data) {
            wrap.put(byteBuffer.duplicate());
        }
        wrap.rewind();
        return wrap;
    }

    protected void ensureData() {
        if (this.data != null) {
            return;
        }
        Container container = this.parent;
        if (container == null) {
            throw new RuntimeException("Missing parent container, can't read sample " + this);
        }
        try {
            this.data = new ByteBuffer[]{container.getByteBuffer(this.offset, this.size)};
        } catch (IOException e) {
            throw new RuntimeException("couldn't read sample " + this, e);
        }
    }

    @Override // szcom.googlecode.mp4parser.authoring.Sample
    public long getSize() {
        return this.size;
    }

    public String toString() {
        return "SampleImpl{offset=" + this.offset + "{size=" + this.size + '}';
    }

    @Override // szcom.googlecode.mp4parser.authoring.Sample
    public void writeTo(WritableByteChannel writableByteChannel) {
        ensureData();
        for (ByteBuffer byteBuffer : this.data) {
            writableByteChannel.write(byteBuffer.duplicate());
        }
    }
}
