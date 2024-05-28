package szcom.googlecode.mp4parser.authoring.builder;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ByteBufferHelper {
    public static List<ByteBuffer> mergeAdjacentBuffers(List<ByteBuffer> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ByteBuffer byteBuffer : list) {
            int size = arrayList.size() - 1;
            if (size >= 0 && byteBuffer.hasArray() && ((ByteBuffer) arrayList.get(size)).hasArray() && byteBuffer.array() == ((ByteBuffer) arrayList.get(size)).array() && ((ByteBuffer) arrayList.get(size)).arrayOffset() + ((ByteBuffer) arrayList.get(size)).limit() == byteBuffer.arrayOffset()) {
                ByteBuffer byteBuffer2 = (ByteBuffer) arrayList.remove(size);
                byteBuffer = ByteBuffer.wrap(byteBuffer.array(), byteBuffer2.arrayOffset(), byteBuffer2.limit() + byteBuffer.limit()).slice();
            } else if (size >= 0 && (byteBuffer instanceof MappedByteBuffer) && (arrayList.get(size) instanceof MappedByteBuffer) && ((ByteBuffer) arrayList.get(size)).limit() == ((ByteBuffer) arrayList.get(size)).capacity() - byteBuffer.capacity()) {
                ByteBuffer byteBuffer3 = (ByteBuffer) arrayList.get(size);
                byteBuffer3.limit(byteBuffer.limit() + byteBuffer3.limit());
            } else {
                byteBuffer.reset();
            }
            arrayList.add(byteBuffer);
        }
        return arrayList;
    }
}
